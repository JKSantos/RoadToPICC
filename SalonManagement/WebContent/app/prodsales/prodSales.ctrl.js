(function(){
  'use strict';

  angular
    .module('app')
    .controller('prodSalesCtrl', prodSalesCtrl);

    function prodSalesCtrl($scope, paymentFactory, locationFactory){
      var vm = this;
      vm.setProdSalesPayment = setProdSalesPayment;
      $scope.details = {};
      $scope.locations = {};
      $scope.orderType = [
       { id: 1, value: 'pickup', name: 'Pick Up'},
       { id: 2, value: 'delivery', name: 'Delivery'}
   ]
      $scope.details.order = { id: 1, value: 'pickup', name: 'Pick Up'};

      locationFactory.getLocation().then(function(data){
        $scope.locations = data.data;
        console.log(locationFactory.getLocation());
      });

      //console.log($scope.locations);

      function setProdSalesPayment(details){
          vm.paymentDetails.push(details);
      }

    }
})();
