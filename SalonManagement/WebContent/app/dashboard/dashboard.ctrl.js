/**
 * Created by Castillo on 9/24/2016.
 */
(function () {
    'use strict';
    angular
        .module('app')
        .controller('dashBoardController', dashBoardController);

    function dashBoardController($scope, $timeout, $http, $window, $filter, dashboardFactory) {
        var vm = this;

        vm.week = false;
        vm.client = false;

        // dashboardFactory.getUsername().then(function (data) {
        //     vm.usernames = data.usernames;
        // });

        vm.date = moment().format("MMM Do, YYYY");
        vm.time = moment().format('LTS');

        $scope.clock = "loading clock..."; // initialise the time variable
        $scope.tickInterval = 1000 //ms

        var tick = function () {
            $scope.clock = Date.now() // get the current time
            $timeout(tick, $scope.tickInterval); // reset the timer
        };

        // Start the timer
        $timeout(tick, $scope.tickInterval);

        var dataWK = $.param({
            'currentWhat': 'week'
        });

        $http({
            method: 'post',
            url: 'dashBoardSales',
            data: dataWK,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            var d = data.data.sales.details,
                de = [];
            angular.forEach(d, function (dd) {
                de.push(dd);
            });

            vm.totalSalesThisWeek = de[0].delivery + de[0].eventService + de[0].homeService + de[0].pickup + de[0].walkin;
        }, function errorCallback(response) {
        });

        var dataMN = $.param({
            'currentWhat': 'month'
        });

        $http({
            method: 'post',
            url: 'dashBoardSales',
            data: dataMN,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            var d = data.data.sales.details,
                de = [];
            angular.forEach(d, function (dd) {
                de.push(dd);
            });

            vm.totalSalesThisMonth = de[0].delivery + de[0].eventService + de[0].homeService + de[0].pickup + de[0].walkin;
        }, function errorCallback(response) {
        });

        var dataWKEmp = $.param({
            'currentWhat': 'week'
        });

        $http({
            method: 'post',
            url: 'GetTotalClients',
            data: dataWKEmp,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            var d = data.data.clients,
                de = [];
            angular.forEach(d, function (dd) {
                de.push(dd);
            });

            var count = 0;

            for (var i = 0; i < de.length; i++) {
                count += de[i];
            }
            vm.totalClientsThisWeek = count;
        }, function errorCallback(response) {

        });

        var dataMNEmp = $.param({
            'currentWhat': 'month'
        });

        $http({
            method: 'post',
            url: 'GetTotalClients',
            data: dataMNEmp,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            var d = data.data.clients,
                de = [];
            angular.forEach(d, function (dd) {
                de.push(dd);
            });

            var count = 0;

            for (var i = 0; i < de.length; i++) {
                count += de[i];
            }
            vm.totalClientsThisMonth = count;
        }, function errorCallback(response) {

        });

        var salesChartMonthly = $.param({
            'type': 'monthly'
        });

        $http({
            method: 'post',
            url: 'salesChart',
            data: salesChartMonthly,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            var details = data.data.sales.details;
            var del = [],
                es = [],
                hs = [],
                pu = [],
                wi = [];

            angular.forEach(details, function (de) {
                del.push(de.delivery);
                es.push(de.eventService);
                hs.push(de.homeService);
                pu.push(de.pickup);
                wi.push(de.walkin);
            });

            hchart(del,es,hs,pu,wi);

        }, function errorCallback(response) {

        });

        function hchart(del,es,hs,pu,wi) {
            $('#container').highcharts({
                title: {
                    text: 'Monthly Sales',
                    x: -20 //center
                },
                subtitle: {
                    text: 'Year 2016',
                    x: -20
                },
                xAxis: {
                    categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                        'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                },
                yAxis: {
                    title: {
                        text: 'Sales'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: 'Php'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: 'Delivery',
                    data: del
                }, {
                    name: 'Event Service',
                    data: es

                }, {
                    name: 'Home Service',
                    data: hs
                }, {
                    name: 'Pick Up',
                    data: pu
                }, {
                    name: 'Walk In',
                    data: wi
                }]
            });
        }

    }
})();
