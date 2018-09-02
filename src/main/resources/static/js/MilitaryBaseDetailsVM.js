function MilitaryBaseDetailsVM(initData) {
    var self = this;

    self.urlBase = initData.urlBase;
    self.id = initData.idBase;
    self.base = ko.observable();
    
    self.isEditable = ko.observable();
    self.ready = ko.observable(false);
    
    self.trainingsArray = ko.observableArray();
    self.soldiersArray = ko.observableArray();
    
	self.dmsLat = ko.observable();
	self.dmsLon = ko.observable();
	self.longitudeOptions = ["E","W"];
    self.latitudeOptions = ["N","S"];
	   
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
							 window.location.assign("/base"); }
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
        $.ajax({
            type: "GET",
            url: this.urlBase + "/getById/" + self.id,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
               console.log(data);
               self.base(new MilitaryBaseViewModel(data));
               self.ready(true);
               self.prepareCoordinates();
               self.getTrainings();
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
 
    self.goBack = function () {
        window.location.assign("/base");
    }

     self.makeEditable = function () { 
       self.isEditable(true);  
    }

    
     self.getTrainings = function() {
         $.ajax({
            type: "GET",
            url: self.urlBase + "/getTrainings/"+ self.id,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
            console.log(data);
            	self.trainingsArray.removeAll();
                data.forEach(function(entry) {
                    self.trainingsArray.push(new TrainingViewModel(entry));      
                 });
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }
    
    self.getSoldiers = function() {
         $.ajax({
            type: "GET",
            url: self.urlBase + "/getSoldiers/"+ self.id,
            contentType: "application/json; charset=utf-8",
            success: function (data) {
            console.log(data);
            	self.soldiersArray.removeAll();
                data.forEach(function(entry) {
                    self.soldiersArray.push(new SoldierViewModel(entry));      
                 });
            },
            fail: function () {
                alert("failed getting the data");
            }
        });
    }
    


     self.update = function () {
        var base = {
	        idMilitaryBase : self.base().id,
	    	name : self.base().name ,
	        description : self.base().description(),
	        latitude : fromDMS2DD(self.dmsLat()),
	        longitude : fromDMS2DD(self.dmsLon()) 
        }
        console.log(base);
        $.ajax({
            type: "PUT",
            url: self.urlBase + "/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(base),
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
    
    self.prepareCoordinates = function() { 
    	var lon = self.base().longitude.split(/[°\s'"]+/);
    	var lat = self.base().latitude.split(/[°\s'"]+/);
    	
    	self.dmsLon({
		    days : lon[0],
		    minutes : lon[1],
		    seconds : lon[2],
		    direction : lon[3]}
	   );
		self.dmsLat({
		    days : lat[0],
		    minutes : lat[1],
		    seconds : lat[2],
		    direction : lat[3]}
	   );
    }

}

function fromDMS2DD(dms) {
    var days = parseFloat(dms.days);
    var minutes = dms.minutes;
    var seconds = dms.seconds;
    var direction = dms.direction;
    direction.toUpperCase();
    var dd = days + minutes/60 + seconds/(60*60);

    if (direction == "S" || direction == "W") {
        dd = dd*-1;
    } // Don't do anything for N or E
    return dd;
}

function fromDD2DMS(dms, type){
    var sign = 1, Abs=0;
    var days, minutes, sec, direction;

    if(dms < 0)  { sign = -1; }
    Abs = Math.abs( Math.round(dms * 1000000.));
    //Math.round is used to eliminate the small error caused by rounding in the computer:
  
    //Error checks
    if(type == "lat" && Abs > (90 * 1000000)){
        return "...";
    } else if(type == "lon" && Abs > (180 * 1000000)){
        return "...";
    }

    days = Math.floor(Abs / 1000000);
    minutes = Math.floor(((Abs/1000000) - days) * 60);
    sec = ( Math.floor((( ((Abs/1000000) - days) * 60) - minutes) * 100000) *60/100000 ).toFixed();
    days = days * sign;
    if(type == 'lat')  direction = days<0 ? 'S' : 'N';
    if(type == 'lon') direction = days<0 ? 'W' : 'E';
    return (days * sign) + '° ' + minutes + "' " + sec + "'' " + direction;
}

function MilitaryBaseViewModel(data) {
    var self = this;
    
    self.id = data.idMilitaryBase;
    self.latitude = fromDD2DMS(data.latitude, "lat");
    self.longitude = fromDD2DMS(data.longitude,"lon");
    self.description = ko.observable(data.description);
    self.name = data.name;
}

function TrainingViewModel(data) {
    var self = this;

    self.description = data.description;
    self.instructor = data.instructor.rank.rankName + " " + data.instructor.fullName;
    self.endTime = (data.endTime == null)? "unknown" : moment(data.endTime).format('MMMM Do YYYY, h:mm:ss a');
    self.startTime = (data.startTime == null)? "unknown" : moment(data.startTime).format('MMMM Do YYYY, h:mm:ss a');
    self.base = data.trainingBase.name;
}

function SoldierViewModel(data) {
    var self = this;
î
    self.id = data.idSoldier;
    self.name = ko.observable(data.fullName);
    self.rank = ko.observable(data.rank.rankName);
    self.tagNumber = ko.observable(data.tagNumber);
    self.alias = ko.observable(data.alias);
    self.base = ko.observable(data.base.name);
    self.dob = ko.observable((data.dob == null)? "unknown" : moment(data.dob).format('ll'));
}