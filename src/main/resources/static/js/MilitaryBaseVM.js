function MilitaryBaseVM(initData) {
    var self = this;

    self.urlBase = initData.urlBase;
    self.baseArray = ko.observableArray();
    self.baseToBeAdded = ko.observable(new MilitaryBaseViewModel({
            name : "",
            latitude : "", 
            longitude : "",
            description : ""
       }));
    self.dmsLat = ko.observable({
	    days : 0,
	    minutes : 0,
	    seconds : 0,
	   direction : ""}
	   );
	 self.dmsLon = ko.observable({
	    days : 0,
	    minutes : 0,
	    seconds : 0,
	   direction : ""}
	   );
    self.longitudeOptions = ["E","W"];
    self.latitudeOptions = ["N","S"];


 	self.openEditable = ko.observable(false);

	self.charCount = ko.computed(function(){
		return self.baseToBeAdded().description().length + " characters";
	});
    

    self.loadData = function () {
        $.ajax({
            type: "GET",
            url: this.urlBase + "/getAll",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                self.baseArray.removeAll();
                data.forEach(function(entry) {
                    self.baseArray.push(new MilitaryBaseViewModel(entry));                });
            },
            fail: function () {
                alert("fail getting the data");
            }
        });

    }



 self.getDetails = function (soldier) {
        window.location.assign("base/" + soldier.id);
    }
    
 self.openEditableBase = function () { 
       self.openEditable(true);   
       console.log(self.baseToBeAdded());
       
    }
    
     self.addBase = function () {
        var base = {
            name : self.baseToBeAdded().name ,
            description : self.baseToBeAdded().description(),
            latitude : fromDMS2DD(self.dmsLat()),
            longitude : fromDMS2DD(self.dmsLon()) 
        }
        console.log(base);
        $.ajax({
            type: "POST",
            url: self.urlBase + "/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data : JSON.stringify(base),
            success: function (data) {
                self.baseArray.push(new MilitaryBaseViewModel(data));
                swal({
                    title: "The base was added successfully!",
                    icon: "success"
                    });
                self.openEditable(false);
            },
            fail: function () {
                 swal({
                    title: "The base could not be added!",
                    icon: "error"
                    });
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
    if(type == 'lat') direction = days<0 ? 'S' : 'N';
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