(function(){
  'use strict';

  angular
    .module('app')
    .controller('reservationTable', reservationTable);

    function reservationTable($compile, $scope, DTOptionsBuilder, DTColumnDefBuilder, paymentFactory, locationFactory){

    var vm = this;
    vm.productList = [];
    vm.serviceList = [];
    vm.promoList = [];
    vm.packageList = [];
    vm.packageList = [];
    vm.dtOptions = DTOptionsBuilder.newOptions().withPaginationType('full_numbers').withDisplayLength(5);
    vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1),
        DTColumnDefBuilder.newColumnDef(2),
        DTColumnDefBuilder.newColumnDef(3)
    ];
    locationFactory.getProducts().then(function(data){
        vm.productList = data.data.productList;
      });
    locationFactory.getServices().then(function(data){
        vm.serviceList = data.data.serviceList;
      });

    locationFactory.getPromos().then(function(data){
        vm.promoList = data.data.promoList;
      });

    locationFactory.getPackages().then(function(data){
        vm.packageList = data.data.packageList;
      });

    locationFactory.getDiscounts().then(function(data){
        vm.discountList = data.data.discountList;
      });

    vm.getProductIDQuantity = function(){
        for(var i = 0; i < $scope.productList.length; i++){
              var product = $scope.productList[i];
              if(product.){

              }else{
                
              }
          }
    };
  }
})();
