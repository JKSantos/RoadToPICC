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
            }

        }
    }
})();