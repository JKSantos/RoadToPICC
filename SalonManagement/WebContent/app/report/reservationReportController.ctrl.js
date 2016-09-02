(function () {
    'use strict';
    angular
        .module('app')
        .controller('reservationReportController', reservationReportController);

    function reservationReportController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, reportsFactory) {
        var vm = this;
        var reserve;
        var walk;
        var prod;
        
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
        vm.reservationList = [];
        vm.productOrderList = [];
        vm.walkinList = [];
        vm.data = [];
        
        vm.reservationTotal = '';
        vm.orderTotal = '';
        vm.walkinTotal = '';
        vm.asdf = '';
        
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
        	vm.reservationList = data.reservation;
        	vm.walkinList = data.walkinSales;
        	vm.productOrderList = data.orderSales;
        	
        	 var reservationTotal = data.reservationTotal[0].dblPrice;
        	 var orderTotal = data.orderTotalSales[0].dblPrice;
        	 var walkinTotal = data.walkinTotal[0].dblPrice;
        	 
        	 vm.reservationTotal = reservationTotal;
        	 vm.orderTotal = orderTotal;
        	 vm.walkinTotal = walkinTotal;
        	 
        	 vm.returnReserve(reservationTotal);
        	
        	reserve = vm.reservationList.length;
        	walk = vm.walkinList.length;
        	prod = vm.productOrderList.length;
        	chartsInit(reserve, prod, walk);
        	
        	
        	console.log(vm.reservationTotal);
        
        };
        
        vm.returnReserve = function(total){
        //	vm.asdf = 0;
        	vm.asdf = total;
        	console.log(vm.asdf);
        	return total;
        	
        }
      
        
        function chartsInit(reserve, prod, walk){
        	
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
        	                    name: 'Walk In',
        	                    y: walk
        	                }, {
        	                    name: 'Reservation',
        	                    y: reserve,
        	                    sliced: true,
        	                    selected: true
        	                }, {
        	                    name: 'Order Sales',
        	                    y: prod 
        	                }]
        	            }]
        	        });
        	    });
        	});
        };
             	
        
      }
})();
