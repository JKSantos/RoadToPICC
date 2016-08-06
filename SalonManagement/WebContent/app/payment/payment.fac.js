(function(){
  'use strict';

  angular
    .module('app')
    .factory('paymentFactory', paymentFactory);

    function paymentFactory(){
      var paymentDetails = {};

      return{
        getPayments: function(){
          return paymentDetails;
        }
      }
    }
})();
