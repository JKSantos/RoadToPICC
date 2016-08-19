(function () {
    'use strict';

    angular
        .module('app')
        .factory('reportsFactory', reportsFactory);

    function reportsFactory($http) {
        return {
            getAllOrders: function () {
                return $http.get('orders').then(function (data) {
                    return data;
                });
            }

        }
    }
})();