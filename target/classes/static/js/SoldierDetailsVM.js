function SoldierDetailsVM(initData) {
    var self = this;
    
    self.urlBase = initData.urlBase;
    self.id = initData.idSoldier;
    self.soldier = ko.observable();
    
    self.isEditable = ko.observable();
    self.ready = ko.observable(false);
    
    self.operationsArray = ko.observableArray();
    self.operationsInChargeArray = ko.observableArray();
    self.trainingsInChargeArray = ko.observableArray();
    self.baseArray = ko.observableArray();
    self.baseToBeAdded = ko.observable();
    self.rankArray = ko.observableArray();
    self.rankToBeAdded = ko.observable();

	self.getMode = ko.computed(function() {
		if(self.isEditable()) {
			return "edit";
		}
		return "view";
	});

    self.delete = function (soldier) {
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
							 window.location.assign("/soldier"); }
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

	
    self.loadData = function () {
    	self.isEditable(false);
    	self.getOperations();
    	self.getOperationsInCharge();
    	self.getTrainingsInCharge();
    	self.getRoles();
    	
        $.ajax({
            type: "GET",
            url: this.urlBase + "/" + self.id,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
               console.log(data);
               if(data.user!=null) {
               		self.idUser = data.user.idUser;
               }
               self.soldier(new SoldierDetailsViewModel(data,"view"));
               self.ready(true);
               self.rankToBeAdded(new RankViewModel(data.rank));
               self.baseToBeAdded(new MilitaryBaseViewModel(data.base));
               if(self.soldier().user!=null) {
                    self.role(self.soldier().user.rol.roleName);
                    console.log(self.role());
               		document.getElementById("role").innerHtml = self.role();
               	}
            },
            fail: function () {
                alert("failed getting the data");
            }
        });

    }
 
    self.goBack = function () {
        window.location.assign("/soldier");
    }

     self.makeEditable = function () { 
       self.soldier().mode("edit"); 
       self.setDob(); 
       self.isEditable(true);  
       self.getMilitaryBases(); 
       self.getRanks();
      
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
                if(entry.name !="" && entry.name!= self.soldier().base()){
                console.log(entry.name);
                console.log(self.soldier().base);
                    self.baseArray.push(new MilitaryBaseViewModel(entry));  
                    }             
                 });
                 console.log(self.baseArray());
                
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }

     self.getRanks = function() {
         $.ajax({
            type: "GET",
            url: "/rank/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log(data);
                self.rankArray.removeAll();
                data.forEach(function(entry) {
                if(entry.rankName!= self.soldier().rank())
                    self.rankArray.push(new RankViewModel(entry));               
                 });
                 console.log(self.rankArray());
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }
    
    self.getOperations = function() {
         $.ajax({
            type: "GET",
            url: self.urlBase + "/getOperations/"+ self.id,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data);
                self.operationsArray.removeAll();
                  data.forEach(function(entry) {
                    self.operationsArray.push(new OperationViewModel(entry));  
                  console.log(self.operationsArray());             
                 });
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }
    
    self.getOperationsInCharge = function() {
         $.ajax({
            type: "GET",
            url: self.urlBase + "/getOperationsInCharge/"+ self.id,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
            self.operationsInChargeArray.removeAll();
                console.log(data);
                  data.forEach(function(entry) {
                    self.operationsInChargeArray.push(new OperationViewModel(entry));               
                 });
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }
    
     self.getTrainingsInCharge = function() {
         $.ajax({
            type: "GET",
            url: self.urlBase + "/getTrainingsInCharge/"+ self.id,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
            console.log(data);
            	self.trainingsInChargeArray.removeAll();
                
                  data.forEach(function(entry) {
                    self.trainingsInChargeArray.push(new TrainingViewModel(entry));      
                 });
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }
    
	self.setDob = function() {
	    var now = new Date();
		var day = ("0" + now.getDate()).slice(-2);
		var month = ("0" + (now.getMonth() + 1)).slice(-2);
		var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
	    
	    var birthDate = new Date(self.soldier().dob());
		var birthDay = ("0" + birthDate.getDate()).slice(-2);
		var birthMonth = ("0" + (birthDate.getMonth() + 1)).slice(-2);
		var sDob = birthDate.getFullYear()+"-"+(birthMonth)+"-"+(birthDay);
		self.soldier().dob(sDob);
	               
		$('#soldierDob').attr({
					   "max" : today
					});
	               
	    console.log(self.soldier());
	 }


     self.updateSoldier = function () {
        var soldier = {
        	idSoldier : self.id, 
            fullName : self.soldier().name() ,
            dob : self.soldier().dob(),
            tagNumber : self.soldier().tagNumber(),
            alias : self.soldier().alias() ,
            rank : self.rankToBeAdded(),
            base : self.baseToBeAdded()
        }
        console.log(soldier);
        $.ajax({
            type: "PUT",
            url: self.urlBase + "/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(soldier),
            success: function (data) {
                swal({
                    title: "The update was succesfull!",
                    type: "success",
                    onClose: () => {
				     location.reload(); 
				  }
                    });
                self.isEditable(false);
            },
            error: function(errors) {
                        swal({
                    title: errors.responseJSON.message,
                    });         
            }
        });
        
    }

    self.discard = function () {
        self.isEditable(false);    
    }
    
    self.roleArray = ko.observableArray();
    self.roleToBeAdded = ko.observable();
    self.role  = ko.observable();
    self.idUser;
    
    
    self.getRoles = function() {
    	$.ajax({
            type: "GET",
            url: "/role/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log(data);
                self.roleArray.removeAll();
                data.forEach(function(entry) { 
                    self.roleArray.push(new RoleViewModel(entry));             
                 });
                 console.log(self.roleArray());
                
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    
    }
    
    self.updateRole = function() {
    	$.ajax({
            type: "PUT",
            url: "/rest/soldier/updateRole/" + self.id + "/" + self.roleToBeAdded().id ,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                swal({
                    title: "The update was succesfull!",
                    type: "success",
                    onClose: () => {
				     location.reload(); 
				  }
                    });
                self.isEditable(false);
            },
            error: function(errors) {
                        swal({
                    title: errors.responseJSON.message,
                    });         
            }
        });
    	
    }

}

function MilitaryBaseViewModel(data) {
    var self = this;
    self.idMilitaryBase = data.idMilitaryBase;
    self.name = data.name;
}

function RankViewModel(data) {
    var self = this;
    self.idRank = data.idRank;
    self.name = data.rankName;
}

function OperationViewModel(data) {
    var self = this;

    self.id = data.idOperation;
    self.description = ko.observable(data.description);
    self.status = ko.observable(data.status);
    self.endDate = (data.endDate == null)? "unknown" : moment(data.endDate).format('MMMM Do YYYY, h:mm:ss a');
    self.startDate = (data.startDate == null)? "unknown" : moment(data.startDate).format('MMMM Do YYYY, h:mm:ss a');
}

function TrainingViewModel(data) {
    var self = this;

    self.description = data.description;
    self.instructor = data.instructor.rank.rankName + " " + data.instructor.fullName;
    self.endTime = (data.endTime == null)? "unknown" : moment(data.endTime).format('MMMM Do YYYY, h:mm:ss a');
    self.startTime = (data.startTime == null)? "unknown" : moment(data.startTime).format('MMMM Do YYYY, h:mm:ss a');
    self.base = data.trainingBase.name;
}

function SoldierDetailsViewModel(data,mode) {
    var self = this;

    self.mode = ko.observable(mode);
    
    self.id = data.idSoldier;
    self.name = ko.observable(data.fullName);
    self.rank = ko.observable(data.rank.rankName);
    self.tagNumber = ko.observable(data.tagNumber);
    self.alias = ko.observable(data.alias);
    self.base = ko.observable(data.base.name);
    self.dob = ko.observable((data.dob == null)? "unknown" : moment(data.dob).format('ll'));
    self.user = data.user;
}

function RoleViewModel(data) {
	var self = this;
	
	self.id = data.idRole;
	self.name = data.roleName;
}
