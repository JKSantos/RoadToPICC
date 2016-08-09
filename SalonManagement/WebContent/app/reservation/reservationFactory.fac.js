(function(){
  'use strict';

  angular
    .module('app')
    .factory('paymentFactory', paymentFactory);

    function paymentFactory($http){
      var orderDetails = [{}];
      var paymentDetails = [{}];
      var subtotal;
      return{
        getPayments: function(){
          return paymentDetails;
        },
        getOrderDetails: function(){
          return orderDetails;
        },
        getSubTotal: function(){
          return subtotal;
        },
        insertOrder: function(productId, quantity){
          orderDetails.push({
            productID: productId,
            productQuantity: quantity
          });
        },
        insertTotal: function(total){
          subtotal = total;
        }
    }
  }
})();
