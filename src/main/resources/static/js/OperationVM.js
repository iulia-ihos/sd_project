function SoldierVM(initData) {
    var self = this;
    self.urlBase = initData.urlBase;

    self.operationArray = ko.observableArray();

    self.removeOperation = function (op) {
        self.operationArmy.remove(op)
    }

    self.viewSoldiers = function () {
        $.ajax({
            type: "POST",
            url: loc + "/" + methodName,
            data: "{" + args + "}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function () {

            },
            fail: function () {

            },
        });
    }

    self.loadData = function () {
        $.ajax({
            type: "GET",
            url: this.urlBase + "/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                self.operationArray.removeAll();
                data.forEach(function(entry) {
                    self.operationArray.push(new OperationViewModel(entry));               
                 });
            },
            fail: function () {
                alert("failed getting the data");
            }
        });

    }

}

function OperationViewModel(data) {
    var self = this;

    self.id = data.idOperation;
    self.description = data.description;
    self.commander = data.commander
    self.status = data.status;
    self.startDate = data.startDate;
    self.endDate = data.endDate;
    self.operationsSoldiers = data.operationsToSoldiers;
}