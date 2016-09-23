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

        // dashboardFactory.getUsername().then(function (data) {
        //     vm.usernames = data.usernames;
        // });

        vm.date = moment().format("MMM Do, YYYY");
        vm.time = moment().format('LTS');

        $scope.clock = "loading clock..."; // initialise the time variable
        $scope.tickInterval = 1000 //ms

        var tick = function() {
            $scope.clock = Date.now() // get the current time
            $timeout(tick, $scope.tickInterval); // reset the timer
        };

        // Start the timer
        $timeout(tick, $scope.tickInterval);


    }
})();
