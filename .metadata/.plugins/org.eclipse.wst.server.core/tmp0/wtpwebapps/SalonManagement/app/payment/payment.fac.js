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
        insertPayments: function(list){
          paymentDetails.push(list);
          console.log(paymentDetails);
        },
        insertOrder: function(productId, quantity){
          orderDetails.push({
            productID: productId,
            productQuantity: quantity
          });
        },
        insertTotal: function(total){
          subtotal = total;
        },
        saveOrderDetails: function(myData){
          console.log(data);
            var name = myData.strName;
            var street = excape(angular.toJson($scope.street));
            var contact = excape(angular.toJson($scope.contact));
               
               $http({
                    method: 'POST',
                    url: 'http://localhost:8080/SalonManagement/createOrder',
                    data: {'strName=' +name,
                           'strStreet='+myData.strStreet,
                           'strContactNo='+myData.strContactNo,
                           'inLocationID='+myData.inLocationID,
                           'orderType=' +myData.orderType,
                           'selectedProducts=' +myData.selectedProducts,
                           'productQuantity=' +myData.productQuantity
                    },
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                 }).success(function(data, status, headers, config) {
                      alert(status);
                 }).error(function(data, status, headers, config) {
                      alert(status);
                 });
            }
        }
    }
})();
