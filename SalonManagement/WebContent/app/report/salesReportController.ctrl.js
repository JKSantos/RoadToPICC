(function () {
    'use strict';
    angular
        .module('app')
        .controller('salesReportController', salesReportController);

    function salesReportController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, reportsFactory) {
        var vm = this;
        vm.reportList = [];
        vm.tagSum = [];
        vm.names = [];
        vm.quantity = [];
        vm.consumed = [];
        vm.defective = [];
        vm.expired = [];
        vm.lost = [];
        
         vm.currentTime = new Date();
        vm.month = ['Januar', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
        vm.monthShort = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        vm.weekdaysFull = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
        vm.weekdaysLetter = ['S', 'M', 'T', 'W', 'T', 'F', 'S'];
        vm.today = 'Today';
        vm.clear = 'Clear';
        vm.close = 'Close';
        vm.minDate = $filter('date')(vm.currentTime, "MMMM/d/yyyy");
        vm.onStart = function () {
            console.log('onStart');
        };
        vm.onRender = function () {
            console.log('onRender');
        };
        vm.onOpen = function () {
            console.log('onOpen');
        };
        vm.onClose = function () {
            console.log('onClose');
        };

        vm.changeDatFrom = changeDatFrom;
        vm.changeDatTo = changeDatTo;

        function changeDatFrom(date) {
            var datFrom = new Date(date);
            console.log(date);

            vm.details.datFrom = $filter('date')(datFrom, "yyyy/MM/dd");
        }

        function changeDatTo(date) {
            var datTo = new Date(date);

            vm.details.datTo = $filter('date')(datTo, "yyyy/MM/dd");
        }
        
        vm.sendFilters = function(){
        	chartsInit();
        	console.log(vm.details.datFrom);
        	console.log(vm.details.datTo);
        	var dtFr = "";
        	var dtTo = "";
        	
        	dtFr = vm.details.datFrom;
        	dtTo = vm.details.datTo;
        	
        	var filterData = ({
        			"dateFrom": dtFr,
        			"dateTo": dtTo
        	});
        	 $.ajax({
                 url: 'getPoductTags',
                 dataType: 'json',
                 type: 'post',
                 contentType: 'application/x-www-form-urlencoded',
                 data: filterData,
                 async: true,
                 success: function( data, textStatus, jQxhr ){
                	 console.log(data);
                    getData(data);
                    
                 },
                 error: function( jqXhr, textStatus, errorThrown ){
                     console.log("Eeerror");
                 }
             });
        };
        
        function getData(data){
        	
        };
        
//        reportsFactory.getAllProductTags().then(function (data) {
//            vm.reportList = data.data; 
//            vm.tagSum = data.data.tagSum;
//            for (var i = 0; i < vm.tagSum.length; i++) {
//                vm.names[i] = vm.tagSum[i].strProductName;
//                vm.quantity[i] = vm.tagSum[i].intQuantity;
//                vm.consumed[i] = vm.tagSum[i].intConsumed;
//                vm.defective[i] = vm.tagSum[i].intDefective;
//                vm.expired[i] = vm.tagSum[i].intExpired;
//                vm.lost[i] = vm.tagSum[i].intLost;
//            }
//            chartsInit(vm.names, vm.quantity, vm.consumed, vm.defective, vm.expired, vm.lost);
//             
//        });
//        console.log(vm.quantity);
        
        
        function chartsInit(names, quantity, consumed, defective, expired, lost){
        	
        	$(function () {
        	    $('#container').highcharts({
        	        chart: {
        	            type: 'bar'
        	        },
        	        title: {
        	            text: 'Product Reports'
        	        },
        	        xAxis: {
        	            categories: names
        	        },
        	        yAxis: {
        	            min: 0,
        	            title: {
        	                text: 'Total product consumption'
        	            }
        	        },
        	        legend: {
        	            reversed: true
        	        },
        	        plotOptions: {
        	            series: {
        	                stacking: null
        	            }
        	        },
        	        series: [{
        	            name: 'Total',
        	            data: quantity
        	        },{
        	            name: 'Consumed',
        	            data: consumed
        	        },{
        	            name: 'Defective',
        	            data: defective
        	        },
        	        {
        	            name: 'Expired',
        	            data: expired
        	        }, {
        	            name: 'Lost',
        	            data: lost
        	        }]
        	    });
        	});
        };
             	
        
      }
})();
