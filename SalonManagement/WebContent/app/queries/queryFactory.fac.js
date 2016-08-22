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
                return $http.get('productQuery').then(function (data) {
                    return data;
                });
            },
            getService: function () {
                return $http.get('serviceQueries').then(function (data) {
                    return data;
                });
            }

        }
    }
})();