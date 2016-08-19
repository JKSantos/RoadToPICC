/**
 * Created by Castillo on 8/11/2016.
 */
(function () {
    'use strict';
    
    angular
        .module('app')
        .factory('orderFactory', orderFactory);

    function orderFactory($resource) {
        var custDetails = [];
        return {
            getOrders: function () {
                return $resource('orders').get().$promise.then(function (data) {
                    return data;
                });
            },
            insertCustDetails: function(data) {
                custDetails.push({
                    "orderType": data.orderType,
                    "strName": data.strName
                });
            },
            getCustDetails: function() {
                return custDetails;
            }
        }
    }
    
    
})();