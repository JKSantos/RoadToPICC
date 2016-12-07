(function () {
    'use strict';

    angular
        .module('app')
        .controller('reservationCalendarCtrl', reservationCalendarCtrl);

    function reservationCalendarCtrl($scope, paymentFactory, locationFactory, reservationFactory) {
        var vm = this;
        vm.data = [];
        locationFactory.getReservations().then(function (data) {
            vm.customerList = data.reservationList;
            for (var i = 0; i < vm.customerList.length; i++) {
                vm.data.push({
                	title: vm.customerList[i].customer.strName,
                	start: vm.customerList[i].datFrom,
                	end: vm.customerList[i].datTo,
                	allDay: false,
                	headcount: vm.customerList[i].headcount,
                	venue: vm.customerList[i].strVenue
                });
            }
           console.log(vm.customerList[13]);
            calendarInit(vm.data);
        });
        
        function calendarInit(data){
        	$(document).ready(function() {

                // page is now ready, initialize the calendar...

                $('#calendar').fullCalendar({
                    // put your options and callbacks here
                	 events: data,
                	      color: 'yellow',   // an option!
                	      textColor: 'black', // an option!
                	    	  
        	    	  eventClick: function(calEvent, jsEvent, view) {

        	    	        alert('Event: ' + calEvent.venue);
        	    	        alert('Event: ' + calEvent.headcount);
        	    	        // change the border color just for fun
        	    	        $(this).css('border-color', 'red');
        	    	  },
        	    	  eventMouseover: function( calEvent, event, jsEvent, view){
        	    		  //alert('Event: ' + calEvent.title);
        	    	  }

                })

            });
        }
       
        
    }
})();







