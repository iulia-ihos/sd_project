function OperationVM(initData) {
    var self = this;
    
    self.urlBase = initData.urlBase;
    
    self.operationArray = ko.observableArray();
    self.operationToBeAdded = ko.observable();
    
    self.soldierArray = ko.observableArray();
    self.soldierToBeAdded = ko.observable();
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
                self.operationArray.removeAll();
                data.forEach(function(entry) {
                    self.operationArray.push(new OperationViewModel(entry));               
                 });
                 console.log(self.operationArray());
            }
        });
    }
 
    self.getDetails = function (operation) {
        window.location.assign(self.urlBase + "/" + operation.id);
    }

    self.openEditableOperation = function () {
        self.getSoldiers();
        self.operationToBeAdded(new OperationViewModel({
        	commander : {fullName:"", rank:{rankName:""}},
            description : "", 
            startDate : "",
            endDate : "",
            status : ""}));  
       self.setDateTimes();
       self.openEditable(true);
       console.log(self.operationToBeAdded());
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
        
    self.setDateTimes = function() {
	    var x = new Date();
	    x.setDate(x.getDate() + 1);
	
		var day = ("0" + x.getDate()).slice(-2);
		var month = ("0" + (x.getMonth()+1)).slice(-2);
		var hour = ("0" + x.getHours()).slice(-2);
		var minute = ("0" + x.getMinutes()).slice(-2);

		
		var tomorrow = x.getFullYear()+"-"+(month)+"-"+(day) + "T" + (hour) + ":" + (minute);
	    
	    console.log(tomorrow);
		self.operationToBeAdded().startDate(tomorrow);
		self.operationToBeAdded().endDate(tomorrow);
	               
		$('#dateTime').attr({
					   "min" : tomorrow
					});

	 }

    self.addOperation = function () {
        var operation = {
            description : self.operationToBeAdded().description,
            commander : {
            	idSoldier:self.soldierToAdd().id
            	},
            startDate : new Date(self.operationToBeAdded().startDate()).toUTCString(),
            endDate : new Date(self.operationToBeAdded().endDate()).toUTCString(),
            status : self.operationToBeAdded().status        
        }
        console.log(operation);
        $.ajax({
            type: "POST",
            url: self.urlBase + "/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(training),
            success: function (data) {
                self.operationArray.push(new OperationViewModel(data));
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

function OperationViewModel(data) {
    var self = this;
	
	self.id = data.idOperation;
    self.description = data.description;
    self.commander = data.commander.fullName + ", " + data.commander.rank.rankName;
    self.endDate = ko.observable((data.endDate == null)? "unknown" : 
    	moment(data.endDate).format('lll'));
    self.startDate = ko.observable((data.startDate == null)? "unknown" : 
    	moment(data.startDate).format('lll'));
    self.status = data.status;
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
    self.tagNumber = data.tagNumber;
}