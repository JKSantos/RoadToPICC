(function () {
    'use strict';
    angular
        .module('app')
        .controller('salesReportController', salesReportController);

    function salesReportController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, reportsFactory) {
        var vm = this;
        
        reportsFactory.getAllProductTags().then(function (data) {
            vm.reportList = data.data.report;
        });
        
        var data = [{}];
        
        for (var i = 0; i < reportList.length; i++) {
            data.push({
            	name: reportlist[i].strProductName,
            	y: reportList[i].intDefective
            });
           console.log(data);
        }
        	
        $(function () {
            $(document).ready(function () {
                // Build the chart
                $('#container').highcharts({
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
                        data: data
                    }]
                });
            });
        });


        reportsFactory.getAllOrders().then(function (data) {
            vm.orderList = data.data.orderList;
            console.log(vm.orderList);
        });
      }
})();
