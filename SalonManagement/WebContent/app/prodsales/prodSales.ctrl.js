(function(){
  'use strict';

  angular
    .module('app')
    .controller('prodSalesCtrl', prodSalesCtrl);

    function prodSalesCtrl($scope, paymentFactory, locationFactory){
      var vm = this;
      vm.setProdSalesPayment = setProdSalesPayment;
      $scope.details = {};
      $scope.orderDetails = [{}]

      $scope.orderType = [
       { id: 1, value: 'pickup', name: 'Pick Up'},
       { id: 2, value: 'delivery', name: 'Delivery'}
   ]
      $scope.details.order = { id: 1, value: 'pickup', name: 'Pick Up'};

      locationFactory.getLocation().then(function(data){
        $scope.locationList = data.data.locationList;
        console.log($scope.locationList);
      });

      locationFactory.getProducts().then(function(data){
        $scope.productList = data.data.productList;
        console.log($scope.productList);
      });


      function setProdSalesPayment(details){
          vm.paymentDetails.push(details);
      }

    }
})();
