(function () {
    'use strict';

    angular
        .module('app')
        .factory('reportsFactory', reportsFactory);

    function reportsFactory($http) {
        return {
            getProductTags: function () {
                return $http.get('http://localhost:8080/SalonManagement/getProductTags').then(function (data) {
                    return data;
                });
            }
        }
    }
})();