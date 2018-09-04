function OperationDetailsVM(initData) {
    var self = this;
    
    self.urlBase = initData.urlBase;
    self.id = initData.idOperation;
    
    self.operation = ko.observable();
    self.status = ko.observable();
    
    
    self.soldierArray = ko.observableArray();
  
    self.soldierMembers = ko.observableArray();
    self.soldierOriginalMembers = ko.observableArray();
    self.soldierMember = ko.observable();
    
    self.toBeDeleted = ko.observableArray();
        
    self.markDelete = function(soldier) {
    	for (var i = 0; i < self.soldierOriginalMembers().length; i++) {
		   if(soldier.id == self.soldierOriginalMembers()[i].id){
		   		self.soldierOriginalMembers()[i].toBeDeleted = "true";
			}
		}
    }
    
    self.removeMember = function(soldier) {
    	self.markDelete(soldier);
    	self.soldierMembers.remove(function(member) {
        	return member.id == soldier.id;
    });
    }
    
    self.addMember = function() {
    	self.soldierMembers.push(new SoldierViewModel(self.memberToAdd()));
    }
    
    self.isEditable = ko.observable();
    self.ready = ko.observable(false);
    
    self.commander;
    
    self.setCommander = function() { 
    	self.commander = self.commanderToAdd();
    }
    
    self.commanderToBeAdded = ko.observable();
    self.memberToBeAdded = ko.observable();
    
    self.tagNumber;
     
    self.commanderByTag = ko.observable();
    self.memberByTag = ko.observable();
    self.commanderToAdd = ko.observable();
    self.memberToAdd = ko.observable();
   
    self.commanderByTag.subscribe(function(newValue) {
    	document.getElementById('selectedCommander').innerHTML = newValue.name + ", " + newValue.rank.rankName;
    	self.commanderToAdd(self.commanderByTag());
	});
 
    self.commanderToBeAdded.subscribe(function(newValue) {
    	document.getElementById('selectedCommander').innerHTML = newValue.name + ", " + newValue.rank.rankName;
    	self.commanderToAdd(self.commanderToBeAdded());
	});
	
	self.memberByTag.subscribe(function(newValue) {
    	document.getElementById('selectedMember').innerHTML = newValue.name + ", " + newValue.rank.rankName;
    	self.memberToAdd(self.memberByTag());
	});
 
    self.memberToBeAdded.subscribe(function(newValue) {
    	document.getElementById('selectedMember').innerHTML = newValue.name + ", " + newValue.rank.rankName;
    	self.memberToAdd(self.memberToBeAdded());
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
							 window.location.assign("/operation"); }
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
        window.location.assign("/operation");
    }

     self.makeEditable = function () { 
       self.setDateTimes(); 
       self.isEditable(true);  
       self.getOperationSoldiers(); 
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
                self.operation(new OperationViewModel(data));    
                self.commander = data.commander;  
                self.status(data.status); 
                self.ready(true);
                self.getOperationSoldiers();
            },
             error: function(errors) {
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
    
    self.getOperationSoldiers = function () {
        $.ajax({
            type: "GET",
            url: self.urlBase + "/getSoldiers/" + self.id,
            dataType: "json",
            success: function (data) {
                self.soldierMembers.removeAll();
                self.soldierOriginalMembers.removeAll();
                data.forEach(function(entry) {
                if(entry.fullName.trim()!="")
                    self.soldierMembers.push(new SoldierViewModel(entry));     
                    self.soldierOriginalMembers.push(new SoldierViewModel(entry));          
                 });
                 console.log(self.soldierMembers());
            }
        });
    }
    
    self.addOperationSoldier = function(idOp, idSold) {
    console.log(idSold);
             var body = {
       			operationToSoldier : {idOperation : idOp},
            	soldierToOperation : {idSoldier : idSold}
   			 }
        $.ajax({
            type: "POST",
            url: "/soldierOp/add",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(body),
            success: function (data) {
                console.log(data);
            },
            error: function(errors) {
               
                        swal({
                    title: errors.responseJSON.message,
                    type: "error"
                    });         
            }
        });
    	
    }
    
    self.manageOperationMembers = function(){
    console.log("members " + self.soldierMembers());
    console.log("og " + self.soldierOriginalMembers());
    //delete
    	for (var i = 0; i < self.soldierOriginalMembers().length; i++) {
    		if(self.soldierOriginalMembers()[i].toBeDeleted) {
    			self.deleteOperationSoldier(self.id,self.soldierOriginalMembers()[i].id);
    		}
		    
		}
	//add
		for (var i = 0; i < self.soldierMembers().length; i++) {
		    var add = true;
			for (var j = 0; j < self.soldierOriginalMembers().length; j++) {
	    		if(self.soldierOriginalMembers()[j].id == self.soldierMembers()[i].id) {
	    			var add = false;
	    			break;
	    		}    
			}
		    if(add) {
		    	self.addOperationSoldier(self.id, self.soldierMembers()[i].id);
		    }
		}
    }
    
    self.deleteOperationSoldier = function(idOp,idSold) {
        var body = {
       			operationToSoldier : {idOperation : idOp},
            	soldierToOperation : {idSoldier : idSold}
   			 }
        $.ajax({
            type: "DELETE",
            url: "/soldierOp/delete",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(body),
            success: function (data) {
                console.log(data);
            },
            error: function(errors) {
                        swal({
                    title: errors.responseJSON.message,
                    type: "error"
                    });         
            }
        });
    }
    
    
    self.setCommanderByTag = function() {
    	self.commanderToBeAdded(self.getSoldierByTagNumber());
    }
    
    self.setMemberByTag = function() {
    	self.memberToBeAdded(self.getSoldierByTagNumber());
    }
    
     self.getSoldierByTagNumber = function () {
        $.ajax({
            type: "GET",
            url: "/rest/soldier/getByTagNumber/" + self.tagNumber,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                return new SoldierViewModel(data);
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
		self.operation().startDate(tomorrow);
		self.operation().endDate(tomorrow);
	               
		$('#dateTime').attr({
					   "min" : tomorrow
					});

	 }
	self.checkDates = function() {
		if(moment(self.operation().end()).isBefore(moment(self.operation().start()))) {
			swal({
                    title: "The start time must be before the end time",
                    type: "error"
                    });
           return false;   
		}
		return true;
	}
	
    self.updateOperation = function () {
     self.manageOperationMembers();
        var operation = {
        	idOperation : self.id,
            description : self.operation().description,
            commander : {
            	idSoldier: (self.commander.idSoldier == undefined)? self.commander.id : self.commander.idSoldier
            	},
            startDate : new Date(self.operation().start()).toUTCString(),
            endTime : new Date(self.operation().end()).toUTCString()        
        }
        console.log(operation);
        $.ajax({
            type: "PUT",
            url: self.urlBase + "/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(operation),
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

    self.discard = function () {
        self.openEditable(false);    
    }
    
    self.acceptOperation = function() {
    	self.updateStatus("ACCEPTED");
    }
    
    self.rejectOperation = function() { 
    	self.updateStatus("REJECTED");
    }
    
    self.updateStatus = function(s) {
    	 var operation = {
        	idOperation : self.id,
            status : s    
        }
        console.log(operation);
        $.ajax({
            type: "PUT",
            url: self.urlBase + "/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(operation),
            success: function (data) {
                swal({
                    title: "Operation new status is " + s,
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

function OperationViewModel(data) {
    var self = this;
	
	self.id = data.idOperation;
    self.description = data.description;
    self.commander = data.commander.fullName;
    self.endDate = ko.observable((data.endDate == null)? "unknown" : 
    	moment(data.endDate).format('lll'));
    self.startDate = ko.observable((data.startDate == null)? "unknown" : 
    	moment(data.startDate).format('lll'));
    self.status = data.status;
    
    self.start = ko.observable(moment(data.startDate).format().split(/[+]+/)[0]);
    self.end = ko.observable(moment(data.endDate).format().split(/[+]+/)[0]);
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

    self.id = (data.idSoldier == undefined)? data.id : data.idSoldier;
    self.name = (data.fullName == undefined)? data.name: data.fullName;
    self.rank = data.rank;
    self.tagNumber = data.tagNumber;
    self.rankName = data.rank.rankName; 
    
    self.toBeDeleted;
}