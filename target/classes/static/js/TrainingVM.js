function TrainingVM(initData) {
    var self = this;
    self.urlBase = initData.urlBase;

    self.trainingArray = ko.observableArray();

    self.removeTraining = function (training) {
        self.trainingArray.remove(training)
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
                self.trainingArray.removeAll();
                data.forEach(function(entry) {
                    self.trainingArray.push(new TrainingViewModel(entry));               
                 });
            },
            fail: function () {
                alert("failed getting the data");
            }
        });

    }

}

function TrainingViewModel(data) {
    var self = this;

    self.id = data.idTraining;
    self.description = data.description;
    self.date = data.date;
    self.instructor = data.instructor;
    self.base = data.base;
    self.operations = data.operations;
}