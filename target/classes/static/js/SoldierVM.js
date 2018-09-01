function SoldierVM(initData) {
    var self = this;
    self.urlBase = initData.urlBase;
    self.soldierArray = ko.observableArray();
    self.soldierToBeAdded = ko.observable();
    self.openEditable = ko.observable(false);
    self.baseArray = ko.observableArray();
    self.baseToBeAdded = ko.observable();
    self.rankArray = ko.observableArray();
    self.rankToBeAdded = ko.observable();

    self.loadData = function () {
        $.ajax({
            type: "GET",
            url: this.urlBase + "/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                self.soldierArray.removeAll();
                data.forEach(function(entry) {
                    self.soldierArray.push(new SoldierViewModel(entry));               
                 });
                 console.log(self.soldierArray());
            },
            fail: function () {
                alert("failed getting the data");
            }
        });

    }

    
    self.getDetails = function (soldier) {
        window.location.assign("soldier/" + soldier.id);
    }

     self.openEditableSoldier = function () {
        self.soldierToBeAdded(new SoldierViewModel({
            fullName : "", dob : new Date(),
            tagNumber : "",
            alias : "",rank : "", base : "",
            operations : ""
       }));  
       self.openEditable(true);   
       self.getMilitaryBases();
       self.getRanks();
       console.log(self.soldierToBeAdded());
       
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

    self.addSoldier = function () {
        var soldier = {
            fullName : self.soldierToBeAdded().name ,
            dob : new Date(self.soldierToBeAdded().dob).toISOString().split("T")[0],
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
                    onClose: () => {
				     window.location.assign("/soldier"); 
				  }
                    });
                self.openEditableSoldier(false);
            },
            fail: function () {
                alert("failed post");
            },
            error: function(errors) {
                console.log(errors.responseJSON);
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

function SoldierViewModel(data) {
    var self = this;

    self.id = data.idSoldier;
    self.name = data.fullName;
    self.rank = data.rank.rankName;
    self.tagNumber = data.tagNumber;
    self.alias = data.alias;
    self.operations = data.soldierOperations;
    self.base = data.base.name;
    self.dob = (data.dob == null)? "unknown" : moment(data.dob).format('ll');
}