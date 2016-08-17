(function () {
    'use strict';

    angular
        .module('app')
        .factory('reportsFactory', reportsFactory);

    function reportsFactory($http) {
        var orderDetails = [{}];
        var paymentDetails = [{}];
        var subtotal = 0;
        return {
            getAllOrders: function () {
                return $http.get('orders').then(function (data) {
                    return data;
                });
            }

        }
    }
})();