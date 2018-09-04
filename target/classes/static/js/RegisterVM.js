function RegisterVM(initData) {
    var self = this;
    self.soldierUrlBase = initData.soldierUrlBase;
    self.userUrlBase = initData.userUrlBase;
    self.email = ko.observable();
    self.tagNumber = ko.observable();
    self.password = ko.observable();
    self.repeatPassword = ko.observable();

    self.soldier = ko.observable();
    self.soldierExists = ko.observable(false);
    self.emailExists = ko.observable(true);
    self.idUser;


    function checkTagNumber() {
        console.log(self.soldierUrlBase + "/getByTagNumber/" + self.tagNumber());
        $.ajax({
            type: "GET",
            async: false,
            url: self.soldierUrlBase + "/getByTagNumber/" + self.tagNumber(),
            success: function (result) {
                if(isEmpty(result)){
                   self.soldierExists(false);
                   console.log("soldier doesn t exist");
                }
                else{
                       console.log("soldier exists");
                self.soldier(new SoldierViewModel(result));
                 self.soldierExists(true);
                }
            }, 
             error: function(errors) {
                        swal({
                        	title:"Tag Number",
		                    text: errors.responseJSON.message,
		                    type: "error"
                    });    
             }  
        });

    }

    function checkEmail() {
        console.log(self.userUrlBase + "/getByEmail/" + self.email());
        $.ajax({
            type: "GET",
            async: false,
            url: self.userUrlBase + "/getByEmail/" + self.email(),
            success: function (result) {
                if (isEmpty(result)) {
                    self.emailExists(false);
                }
                else self.emailExists(true);
            },
            fail: function () {
                console.log("email fail");
            }
        });
    }


    self.checkData = function () {
        var errors = "";
        console.log(self.tagNumber());

        if (self.tagNumber().length != 7) {
            errors += "The Tag Number field length should be 7.\n";
            document.getElementById("tagError").innerHTML = "This should be a 7-digit number";
        }
        if (self.repeatPassword() != self.password()) {
            errors += "The passwords don't match.\n";
            document.getElementsByClassName('passwordError')[0].innerHTML = "The passwords do not match";
            document.getElementsByClassName('passwordError')[1].innerHTML = "The passwords do not match";


        }
        return errors;
    };

    function isEmpty(obj) {
        console.log(obj);
        for(var prop in obj) {
            if(obj.hasOwnProperty(prop))
            return false;   
        }
        return true;
    }

    self.register = function () {
        if (self.checkData() == "") {
            checkTagNumber();
            checkEmail();

            console.log("soldier out " + self.soldier());
            console.log("user out " + self.emailExists());

            if (!self.soldierExists()) {
                swal({
		                    title: "No Such Tag Name!",
		                    type: 'success',
		                    onClose: () => {
						    window.location.assign("/register"); 
						  }
                   		 });
            }
            else
            if (self.emailExists()) {
                 swal({
		                    title: "Email already in use!",
		                    type: 'success',
		                    onClose: () => {
						    window.location.assign("/register"); 
						  }
                   		 });
            }
            else {

                var usr = {
                    idUser: 0,
                    email: self.email(),
                    pass: self.password(),
                    rol: {
                        idRole: 1
                    },
                    soldier: {
                        idSoldier: self.soldier.id
                    }
                }
                $.ajax({
                    type: "POST",
                    url: self.userUrlBase + "/register",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: JSON.stringify(usr),
                    success: function (result) {
                    	self.idUser = result.idUser;
                    	self.updateSoldier();
                        swal({
		                    title: "The registration was successful!",
		                    type: 'success',
		                    onClose: () => {
						    window.location.assign("/login"); 
						  }
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
            self.tagNumber("");
            self.email("");
            self.password("");
            self.repeatPassword("");
        }

    }

    self.updateSoldier = function () {
        var soldier = {
        	idSoldier : self.soldier().id, 
            user : {idUser: self.idUser}
        }
        console.log(soldier);
        $.ajax({
            type: "PUT",
            url: "rest/soldier/update",
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
            },
            error: function(errors) {
                        swal({
                    title: errors.responseJSON.message,
                    });         
            }
        });
        

}
}

function SoldierViewModel(data) {
    var self = this;

    self.id = data.idSoldier;
    self.tagNumber = data.tagNumber;
}

