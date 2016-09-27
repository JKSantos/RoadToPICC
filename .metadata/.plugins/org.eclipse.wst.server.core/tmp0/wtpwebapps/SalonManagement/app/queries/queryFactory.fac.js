(function () {
    'use strict';

    angular
        .module('app')
        .factory('queryFactory', queryFactory);

    function queryFactory($http, $resource) {
        return {
            getEmployee: function () {
                return $resource('employeeQuery').get().$promise.then(function (data) {
                    return data;
                });
            },
            getProduct: function () {
                return $resource('productQuery').get().$promise.then(function (data) {
                    return data;
                });
            },
            getService: function () {
                return $resource('serviceQuery').get().$promise.then(function (data) {
                    return data;
                });
            },
            getPackage: function () {
                return $resource('package').get().$promise.then(function (data) {
                    return data;
                });
            },
            getDelivery: function () {
                return $resource('deliveryCharge').get().$promise.then(function (data) {
                    return data;
                });
            },
            getExtra: function () {
                return $resource('otherCharge').get().$promise.then(function (data) {
                    return data;
                });
            },
            getPromo: function () {
                return $resource('promo').get().$promise.then(function (data) {
                    return data;
                });
            },
            getDiscount: function () {
                return $resource('discount').get().$promise.then(function (data) {
                    return data;
                });
            }


        }
    }
})();