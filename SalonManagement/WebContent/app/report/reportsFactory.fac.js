(function () {
    'use strict';

    angular
        .module('app')
        .factory('reportsFactory', reportsFactory);

    function reportsFactory($http) {
        return {
            getAllProductTags: function () {
                return $http.get('http://localhost:8080/SalonManagement/getPoductTags').then(function (data) {
                    return data;
                });
            }

        }
    }
})();