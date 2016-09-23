/**
 * Created by Castillo on 9/24/2016.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .factory('dashboardFactory', dashboardFactory);

    function dashboardFactory($http, $resource) {
        return {
            getEmployee: function () {
                return $resource('employeeQuery').get().$promise.then(function (data) {
                    return data;
                });
            }


        }
    }
})();