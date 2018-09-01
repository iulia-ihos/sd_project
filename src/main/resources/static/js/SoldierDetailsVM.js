function SoldierDetailsVM(initData) {
    var self = this;
    self.urlBase = initData.urlBase;
    self.id = initData.idSoldier;
    self.soldier = ko.observable();
   
    console.log(self.id);
    
    self.isEditable = ko.observable(false);
    
    self.operationsArray = ko.observableArray();
    self.operationsInChargeArray = ko.observableArray();
    self.baseToBeAdded = ko.observable();
    self.rankArray = ko.observableArray();
    self.rankToBeAdded = ko.observable();



    self.delete = function (soldier) {
    $.ajax({
            type: "DELETE",
            url: this.urlBase + "/deleteById/" + self.id,
            success: function (data) {
               console.log(data);
               swal({
                    title: "The soldier was deleted successfully!",
                    onClose: () => {
				     window.location.assign("/soldier"); 
				  }
                    });
              
            }, error: function(errors) {
                
                        swal({
                    title: errors.responseJSON.message,
                    icon: "error"
                    });         
            }
        });
    }

    self.loadData = function () {
    	self.getOperations();
    	self.getOperationsInCharge();
        $.ajax({
            type: "GET",
            url: this.urlBase + "/" + self.id,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
               console.log(data);
               self.soldier(new SoldierDetailsViewModel(data));
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
       self.isEditable(true);  
       self.getMilitaryBases(); 
    }

    self.getMilitaryBases = function() {
         $.ajax({
            type: "GET",
            url: "base/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log(data);
                self.baseArray.removeAll();
                data.forEach(function(entry) {
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

     self.getRanks = function() {
         $.ajax({
            type: "GET",
            url: "rank/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log(data);
                self.rankArray.removeAll();
                data.forEach(function(entry) {
                    self.rankArray.push(new RankViewModel(entry));               
                 });
                 console.log(self.rankArray());
                 self.rankToBeAdded(self.rankArray()[0]);
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
                  console.log(self.operationsArray());             
                 });
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }

    self.addSoldier = function () {
        var soldier = {
            fullName : self.soldierToBeAdded().name ,
            dob : self.soldierToBeAdded().dob,
            tagNumber : self.soldierToBeAdded().tagNumber,
            alias : self.soldierToBeAdded().alias ,
            soldierOperations : self.soldierToBeAdded().operations ,
            rank : {idRank  : self.rankToBeAdded().id,
                rankName : self.rankToBeAdded().name
            },
            base : {idMilitaryBase : self.baseToBeAdded().id,
               name : self.baseToBeAdded().name
            }
        }
        console.log(soldier);
        $.ajax({
            type: "POST",
            url: self.urlBase + "/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(soldier),
            success: function (data) {
                self.soldierArray.push(new SoldierViewModel(data));
                swal({
                    title: "The soldier was added successfully!",
                    icon: "success"
                    });
                self.openEditableSoldier(false);
            },
            error: function(errors) {
                
                        swal({
                    title: errors.responseJSON.message,
                    icon: "error"
                    });         
            }
        });
    }
    
     self.updateSoldier = function () {
        var soldier = {
            fullName : self.soldierUpdated().name ,
            dob : self.soldier().dob,
            tagNumber : self.soldier().tagNumber,
            alias : self.soldier().alias ,
            soldierOperations : self.soldier().operations ,
            rank : {idRank  : self.rankToBeAdded().id,
                rankName : self.rankToBeAdded().name
            },
            base : {idMilitaryBase : self.baseToBeAdded().id,
               name : self.baseToBeAdded().name
            }
        }
        console.log(soldier);
        $.ajax({
            type: "POST",
            url: self.urlBase + "/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(soldier),
            success: function (data) {
                self.soldierArray.push(new SoldierViewModel(data));
                swal({
                    title: "The soldier was added successfully!",
                    icon: "success"
                    });
                self.openEditableSoldier(false);
            },
            error: function(errors) {
                
                        swal({
                    title: errors.responseJSON.message,
                    icon: "error"
                    });         
            }
        });
        
    }

    self.discard = function () {
        self.openEditable(false);    
    }

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

function OperationViewModel(data) {
    var self = this;

    self.id = data.idOperation;
    self.description = ko.observable(data.description);
    self.status = ko.observable(data.status);
    self.endDate = (data.endDate == null)? "unknown" : moment(data.endDate).format('MMMM Do YYYY, h:mm:ss a');
    self.startDate = (data.startDate == null)? "unknown" : moment(data.startDate).format('MMMM Do YYYY, h:mm:ss a');
}

function SoldierDetailsViewModel(data) {
    var self = this;

    self.id = data.idSoldier;
    self.name = ko.observable(data.fullName);
    self.rank = ko.observable(data.rank.rankName);
    self.tagNumber = ko.observable(data.tagNumber);
    self.alias = ko.observable(data.alias);
    self.base = ko.observable(data.base.name);
    self.dob = (data.dob == null)? "unknown" : moment(data.dob).format('ll');
    self.operationsInCharge = data.operationsInCharge;
    self.trainingsInCharge = data.trainingsInCharge;
}