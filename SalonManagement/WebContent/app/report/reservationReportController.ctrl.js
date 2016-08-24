(function () {
    'use strict';
    angular
        .module('app')
        .controller('reservationReportController', reservationReportController);

    function reservationReportController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, reportsFactory) {
        var vm = this;
        chartsInit();
        vm.reportList = [];
        vm.tagSum = [];
        vm.names = [];
        vm.quantity = [];
        vm.consumed = [];
        vm.defective = [];
        vm.expired = [];
        vm.lost = [];
        vm.filter = [];
        vm.filter.datFrom = '';
        vm.filter.datTo = '';
        vm.customerList = [];
        
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
                 url: 'reservationSales',
                 dataType: 'json',
                 type: 'post',
                 contentType: 'application/x-www-form-urlencoded',
                 data: filterData,
                 async: true,
                 success: function( data, textStatus, jQxhr ){
                    getData(data);
                     
                     
                 },
                 error: function( jqXhr, textStatus, errorThrown ){
                     console.log("Eeerror");
                 }
             });
        };
        
        function getData(data){
        	//vm.customerList = [];
        	vm.customerList = data.reservation;
        	console.log(vm.customerList);
        };
      
        
        function chartsInit(names, quantity, consumed, defective, expired, lost){
        	
        	$(function () {

        	    $(document).ready(function () {

        	        // Build the chart
        	        $('#containerReservation').highcharts({
        	            chart: {
        	                plotBackgroundColor: null,
        	                plotBorderWidth: null,
        	                plotShadow: false,
        	                type: 'pie'
        	            },
        	            title: {
        	                text: 'Browser market shares January, 2015 to May, 2015'
        	            },
        	            tooltip: {
        	                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        	            },
        	            plotOptions: {
        	                pie: {
        	                    allowPointSelect: true,
        	                    cursor: 'pointer',
        	                    dataLabels: {
        	                        enabled: false
        	                    },
        	                    showInLegend: true
        	                }
        	            },
        	            series: [{
        	                name: 'Brands',
        	                colorByPoint: true,
        	                data: [{
        	                    name: 'Microsoft Internet Explorer',
        	                    y: 56.33
        	                }, {
        	                    name: 'Chrome',
        	                    y: 24.03,
        	                    sliced: true,
        	                    selected: true
        	                }, {
        	                    name: 'Firefox',
        	                    y: 10.38
        	                }, {
        	                    name: 'Safari',
        	                    y: 4.77
        	                }, {
        	                    name: 'Opera',
        	                    y: 0.91
        	                }, {
        	                    name: 'Proprietary or Undetectable',
        	                    y: 0.2
        	                }]
        	            }]
        	        });
        	    });
        	});
        };
             	
        
      }
})();
