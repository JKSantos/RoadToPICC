(function(){
  'use strict';

  angular
    .module('app')
    .controller('reservationCtrl', reservationCtrl);

    function reservationCtrl($scope, paymentFactory, locationFactory){
      $scope.selected = 'product';

      $scope.customerDetails = [{
        intID: 1,
        strName: '',
        strAddress: '',
        strContactNo: '',
        strEmail: ''
      }]

      $scope.reserVationDetails = [{
        customer: $scope.customerDetails,
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
      }]

      locationFactory.getProducts().then(function(data){
        $scope.productList = data.data.productList;
      });

      locationFactory.getEmployees().then(function(data){
        $scope.employeeList = data.data.employeeList;
      });

      locationFactory.getServices().then(function(data){
        $scope.serviceList = data.data.serviceList;
      });

      locationFactory.getPromos().then(function(data){
        $scope.promoList = data.data.promoList;
      });

      locationFactory.getPackages().then(function(data){
        $scope.packageList = data.data.packageList;
      });

       locationFactory.getExtraCharges().then(function(data){
        $scope.extraChargeList = data.data.extraChargeList;
      });

      locationFactory.getDiscounts().then(function(data){
        $scope.discountList = data.data.discountList;
      });

      var sum = paymentFactory.getSubTotal();
      
      $scope.addToCart = function(index){
        $scope.itemTotal = $scope.productList[index].dblProductPrice * $scope.details.quantity;
        $scope.orderList.push({product: $scope.productList[index].strProductName, total: $scope.itemTotal});

        var productId = $scope.productList[index].intProductID;
        var quantity =  $scope.details.quantity;
        paymentFactory.insertOrder(productId, quantity);
      };

      function commaProducts(){
        var selectedProducts = "";
          for(var i = 1; i < orderDetails.length; i++){
              var odrdah = orderDetails[i];
              selectedProducts += orderDetails[i].productID + ",";
          }
          return selectedProducts;
      }

       function commaQuantity(){
        var selectedQuantity = "";
          for(var i = 1; i < orderDetails.length; i++){
              var odrdah = orderDetails[i];
              selectedQuantity += orderDetails[i].productQuantity + ",";
          }

          return selectedQuantity;
      }

      $scope.calculateTotal = function(){
          var total = 0;

          for(var i = 0; i < $scope.orderList.length; i++){
              var product = $scope.orderList[i];
              total += (product.total);
          }
          $scope.totalAmount = total;

          var subTotal = $scope.totalAmount;
          paymentFactory.insertTotal(subTotal);
      };

      $scope.setProdSalesPayment = function(custDetails){
        $scope.customerDetails.push({
          orderType: custDetails.order.value,
          strContactNo: custDetails.contact,
          strName: custDetails.name,
          strStreet: custDetails.street,
          intLocationID: custDetails.location.intLocationID,
          selectedProducts:  commaProducts(),
          productQuantity: commaQuantity()
        });
          console.log($scope.customerDetails);
          paymentFactory.saveOrderDetails($scope.customerDetails[1]);
    };
  }
})();
