(function(){
  'use strict';

  angular
    .module('app')
    .controller('reservationCtrl', reservationCtrl);

    function reservationCtrl($scope, paymentFactory, locationFactory, DTOptionsBuilder, DTColumnDefBuilder){
      var vm = this;
     $scope.selected = 'product';

        vm.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(10)
            .withLanguage({
                "sLoadingRecords": "Loading..."
            });
        vm.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(0),
            DTColumnDefBuilder.newColumnDef(1),
            DTColumnDefBuilder.newColumnDef(2),
            DTColumnDefBuilder.newColumnDef(3).notSortable(),
            DTColumnDefBuilder.newColumnDef(4),
            DTColumnDefBuilder.newColumnDef(5),
            DTColumnDefBuilder.newColumnDef(6).notSortable()
        ];

      vm.customerDetails = [{
        intID: 1,
        strName: '',
        strAddress: '',
        strContactNo: '',
        strEmail: ''
      }];

      vm.reserVationDetails = [{
        customer: vm.customerDetails,
        intReservationType: 1,
        datFrom: '',
        datTo: '',
        timFrom: '',
        timTo: '',
        strVenue: '',
        headCount: 0,
        selectedEmployees: '',
        selectedProducts: '',
        selectedServices: '',
        selectedPackages: '',
        selectedPromos: '',
        productQuantity: '',
        serviceQuantity: '',
        packageQuantity: '',
        promoQuantity: '',
        selectedExtraCharges: '',
        selectedDiscounts: ''
      }];

      locationFactory.getEmployees().then(function(data){
        vm.employeeList = data.data.employeeList;
      });

       locationFactory.getExtraCharges().then(function(data){
        vm.extraChargeList = data.data.extraChargeList;
      });

      locationFactory.getDiscounts().then(function(data){
        vm.discountList = data.data.discountList;
      });

      var sum = paymentFactory.getSubTotal();
      
     
      function commaProducts(){
        var selectedProducts = "";
          for(var i = 1; i < orderDetails.length; i++){
              selectedProducts += orderDetails[i].productID + ",";
          }
          return selectedProducts;
      }

       function commaQuantity(){
        var selectedQuantity = "";
          for(var i = 1; i < orderDetails.length; i++){
              selectedQuantity += orderDetails[i].productQuantity + ",";
          }

          return selectedQuantity;
      }

      vm.calculateTotal = function(){
          var total = 0;

          for(var i = 0; i < vm.orderList.length; i++){
              var product = vm.orderList[i];
              total += (product.total);
          }
          vm.totalAmount = total;

          var subTotal = vm.totalAmount;
          paymentFactory.insertTotal(subTotal);
      };

      vm.setProdSalesPayment = function(custDetails){
        vm.customerDetails.push({
          orderType: custDetails.order.value,
          strContactNo: custDetails.contact,
          strName: custDetails.name,
          strStreet: custDetails.street,
          intLocationID: custDetails.location.intLocationID,
          selectedProducts:  commaProducts(),
          productQuantity: commaQuantity()
        });
          console.log(vm.customerDetails);
          paymentFactory.saveOrderDetails(vm.customerDetails[1]);
    };
  }
})();