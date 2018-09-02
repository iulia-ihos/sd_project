function TrainingDetailsVM(initData) {
    var self = this;
    
    self.urlBase = initData.urlBase;
    self.id = initData.idTraining;
    
    self.training = ko.observable();
   
    self.soldierArray = ko.observableArray();
    self.soldierToBeAdded = ko.observable();
    self.baseArray = ko.observableArray();
    self.baseToBeAdded = ko.observable();
    self.soldierToAdd = ko.observable();
    self.tagNumber;
    
    self.isEditable = ko.observable();
    self.ready = ko.observable(false);
    
    self.startTime = ko.observable();
    self.endTime = ko.observable();
    
    
    
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
    

	self.getMode = ko.computed(function() {
		if(self.isEditable()) {
			return "edit";
		}
		return "view";
	});

    self.delete = function () {
    swal({
		  title: 'Are you sure?',
		  text: "You won't be able to revert this!",
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
		  if (result.value) {
				$.ajax({
					type: "DELETE",
					url: this.urlBase + "/deleteById/" + self.id,
					success: function (data) {
					   console.log(data);
						swal({
							title: "The entity has been deleted successfully!",
							onClose: () => {
							 window.location.assign("/training"); }
							});
					},
					error: function(errors) {   
							swal({
						title: errors.responseJSON.message,
						type: "error"
						});         
					}
				});
			}			 
		});
	}
	
	
	self.goBack = function () {
        window.location.assign("/training");
    }

     self.makeEditable = function () { 
       self.setDateTimes(); 
       self.isEditable(true);  
       self.getMilitaryBases(); 
       self.getSoldiers();
      
    }

    self.discard = function () {
        self.isEditable(false);    
    }
	
    self.loadData = function () {
        $.ajax({
            type: "GET",
            url: self.urlBase + "/getById/" + self.id,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                self.training(new TrainingViewModel(data));  
                console.log(data);  
                self.ready(true);   
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
	             
		$('#dateTime').attr({
					   "min" : tomorrow
					});

	 }
	 
	self.checkDates = function() {
	console.log("start " + (self.training().start()));
	console.log("end "+ self.training().end());
	
		if(moment(self.training().end()).isBefore(moment(self.training().start()))) {
			swal({
                    title: "The start time must be before the end time",
                    type: "error"
                    });
           return false;   
		}
		return true;
	}

    self.updateTraining = function () {
        if(self.checkDates()) {
        var training = {
        	idTraining : self.id,
            description : self.training().description,
            instructor : {
            	idSoldier:self.soldierToAdd().id
            	},
            trainingBase : {
            	idMilitaryBase : self.baseToBeAdded().id
            	},
            startTime : new Date(self.training().start()).toUTCString(),
            endTime : new Date(self.training().end()).toUTCString()        
        }
        console.log(training);
        $.ajax({
            type: "PUT",
            url: self.urlBase + "/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(training),
            success: function (data) {
                swal({
                    title: "The entity was updated successfully!",
                    type: 'success',
                    onClose: () => {
				    location.reload(); 
				  }
                    });
                self.isEditable(false);
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

    self.start = ko.observable(moment(data.startTime).format().split(/[+]+/)[0]);
    self.end = ko.observable(moment(data.endTime).format().split(/[+]+/)[0]);
    
    console.log("start " + self.start() );
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