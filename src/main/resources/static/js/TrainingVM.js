function TrainingVM(initData) {
    var self = this;
    
    self.urlBase = initData.urlBase;
    
    self.trainingArray = ko.observableArray();
    self.trainingToBeAdded = ko.observable();
    
    self.soldierArray = ko.observableArray();
    self.soldierArrayForBase = ko.observableArray();
    self.baseSoldierToBeAdded = ko.observable();
    self.soldierToBeAdded = ko.observable();
    self.baseArray = ko.observableArray();
    self.baseToBeAdded = ko.observable();
    self.soldierToAdd = ko.observable();
    self.tagNumber;
    
    self.soldierByTag = ko.observable();
   
    self.soldierByTag.subscribe(function(newValue) {
    	document.getElementById('selectedSoldier').innerHTML = newValue.name + ", " + newValue.rank.rankName;
    	self.soldierToAdd(self.soldierByTag());
	});
 
    self.soldierToBeAdded.subscribe(function(newValue) {
    	document.getElementById('selectedSoldier').innerHTML = newValue.name + ", " + newValue.rank.rankName;
    	self.soldierToAdd(self.soldierToBeAdded());
	});
	
    self.openEditable = ko.observable(false);
    

    self.loadData = function () {
        $.ajax({
            type: "GET",
            url: self.urlBase + "/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                self.trainingArray.removeAll();
                data.forEach(function(entry) {
                    self.trainingArray.push(new TrainingViewModel(entry));               
                 });
                 console.log(self.trainingArray());
            }
        });
    }
 
    self.getDetails = function (training) {
        window.location.assign(self.urlBase + "/" + training.id);
    }

     self.openEditableTraining = function () {
        self.getMilitaryBases();
        self.getSoldiers();
        self.trainingToBeAdded(new TrainingViewModel({
        	instructor : {fullName:"", rank:{rankName:""}},
        	trainingBase : { name:""},
            description : "", 
            startTime : "",
            endTime : ""})
            );  
       self.setDateTimes();
       self.openEditable(true);
       console.log(self.trainingToBeAdded());
    }
    
     self.getSoldiers = function () {
        $.ajax({
            type: "GET",
            url: "/rest/soldier/getAll",
            dataType: "json",
            success: function (data) {
                self.soldierArray.removeAll();
                data.forEach(function(entry) {
                if(entry.fullName.trim()!="")
                    self.soldierArray.push(new SoldierViewModel(entry));               
                 });
                 console.log(self.soldierArray());
            }
        });
    }
    
     self.getSoldierByTagNumber = function () {
        $.ajax({
            type: "GET",
            url: "/rest/soldier/getByTagNumber/" + self.tagNumber,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                self.soldierByTag(new SoldierViewModel(data));
            },
            error: function(errors) {
                console.log(errors.responseJSON);
                        swal({
                    title: errors.responseJSON.message,
                    type: "error"
                    });         
            }
        });
    }

    self.getMilitaryBases = function() {
         $.ajax({
            type: "GET",
            url: "/base/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log(data);
                self.baseArray.removeAll();
                data.forEach(function(entry) {
                if(entry.name.trim()!="")
                    self.baseArray.push(new MilitaryBaseViewModel(entry));               
                 });
                 console.log(self.baseArray());
                 self.baseToBeAdded(self.baseArray()[0]);
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }

    
    self.setDateTimes = function() {
	    var x = new Date();
	    x.setDate(x.getDate() + 1);
	
		var day = ("0" + x.getDate()).slice(-2);
		var month = ("0" + (x.getMonth()+1)).slice(-2);
		var hour = ("0" + x.getHours()).slice(-2);
		var minute = ("0" + x.getMinutes()).slice(-2);

		
		var tomorrow = x.getFullYear()+"-"+(month)+"-"+(day) + "T" + (hour) + ":" + (minute);
	    
	    console.log(tomorrow);
		self.trainingToBeAdded().startTime(tomorrow);
		self.trainingToBeAdded().endTime(tomorrow);
	               
		$('#dateTime').attr({
					   "min" : tomorrow
					});

	 }
	 
	 self.checkDates = function() {
			if(moment(self.trainingToBeAdded().endTime()).isBefore(moment(self.trainingToBeAdded().startTime()))) {
				swal({
	                    title: "The start time must be before the end time",
	                    type: "error"
	                    });
	           return false;   
			}
			return true;
	}

    self.addTraining = function () {
    console.log(self.soldierToBeAdded());
    if(self.checkDates()) {
        var training = {
            description : self.trainingToBeAdded().description,
            instructor : {
            	idSoldier:self.soldierToAdd().id,
            	fullName : self.soldierToAdd().name,
            	rank :self.soldierToAdd().rank
            	},
            trainingBase : {
            	idMilitaryBase : self.baseToBeAdded().id,
            	name : self.baseToBeAdded().name
            	},
            startTime : new Date(self.trainingToBeAdded().startTime()).toUTCString(),
            endTime : new Date(self.trainingToBeAdded().endTime()).toUTCString()        
        }
        console.log(training);
        $.ajax({
            type: "POST",
            url: self.urlBase + "/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(training),
            success: function (data) {
                self.trainingArray.push(new TrainingViewModel(data));
                swal({
                    title: "The entity was added successfully!",
                    type: 'success',
                    onClose: () => {
				     window.location.assign(self.urlBase); 
				  }
                    });
                self.openEditable(false);
            },
            error: function(errors) {
                console.log(errors.responseJSON);
                        swal({
                    title: errors.responseJSON.message,
                    type: "error"
                    });         
            }
        });
        }
    }

    self.discard = function () {
        self.openEditable(false);    
    }

}

function TrainingViewModel(data) {
    var self = this;
	
	self.id = data.idTraining;
    self.description = data.description;
    self.instructor = data.instructor.fullName + ", " + data.instructor.rank.rankName;
    self.endTime = ko.observable((data.endTime == null)? "unknown" : 
    	moment(data.endTime).format('lll'));
    self.startTime = ko.observable((data.startTime == null)? "unknown" : 
    	moment(data.startTime).format('lll'));
    self.base = data.trainingBase.name;
}

function MilitaryBaseViewModel(data) {
    var self = this;
    self.id = data.idMilitaryBase;
    self.name = data.name;
}

function RankViewModel(data) {
    var self = this;
    self.id = data.idRank;
    self.name = data.rankName;
}

function SoldierViewModel(data) {
    var self = this;

    self.id = data.idSoldier;
    self.name = data.fullName;
    self.rank = data.rank;
}