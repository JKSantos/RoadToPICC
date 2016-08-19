(function () {
    'use strict';

    angular
        .module('app')
        .factory('locationFactory', locationFactory);

    function locationFactory($http, $resource) {
        return {
            getLocation: function () {
                return $resource('api/v1/getAllLocation').get().$promise.then(function (data) {
                    return data;
                });
            },
            getProducts: function () {
                return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllProduct').then(function (data) {
                    return data;
                });
            },
            getEmployees: function () {
                return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllEmployee').then(function (data) {
                    return data;
                });
            },
            getServices: function () {
                return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllService').then(function (data) {
                    return data;
                });
            },
            getPromos: function () {
                return $http.get('http://localhost:8080/SalonManagement/getAllPromoNoDetails').then(function (data) {
                    return data;
                });
            },
            getPromosWithDetails: function () {
                return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllPromo').then(function (data) {
                    return data;
                });
            },
            getPackages: function () {
                return $http.get('http://localhost:8080/SalonManagement/getPackageNoDetails').then(function (data) {
                    return data;
                });
            },
            getPackagesWithDetails: function () {
                return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllPackage').then(function (data) {
                    return data;
                });
            },
            getExtraCharges: function () {
                return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllOtherCharge').then(function (data) {
                    return data;
                });
            },
            getDiscounts: function () {
                return $http.get('http://localhost:8080/SalonManagement/getAllDiscountNoDetails').then(function (data) {
                    return data;
                });
            },
            getRequest: function () {
                return $resource('getAllProductRequest').get().$promise.then(function (data) {
                    return data;
                });
            },
            getOrders: function() {
                return $resource('orders').get().$promise.then(function (data) {
                    return data;
                });
            }
        }
    }
})();