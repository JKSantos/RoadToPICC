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
      
        reportsFactory.getAllProductTags().then(function (data) {
            vm.reportList = data.data; 
            vm.tagSum = data.data.tagSum;
            for (var i = 0; i < vm.tagSum.length; i++) {
                vm.names[i] = vm.tagSum[i].strProductName;
                vm.quantity[i] = vm.tagSum[i].intQuantity;
                vm.consumed[i] = vm.tagSum[i].intConsumed;
                vm.defective[i] = vm.tagSum[i].intDefective;
                vm.expired[i] = vm.tagSum[i].intExpired;
                vm.lost[i] = vm.tagSum[i].intLost;
            }
            chartsInit(vm.names, vm.quantity, vm.consumed, vm.defective, vm.expired, vm.lost);
             
        });
        console.log(vm.quantity);
        
        
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
