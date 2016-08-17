(function () {
    'use strict';

    angular
        .module('app')
        .factory('queryFactory', queryFactory);

    function queryFactory($resource) {
        return {
            getEmployee: function () {
                return $resource('employeeQuery').get().$promise.then(function (data) {
                    return data;
                });
            }

        }
    }
})();