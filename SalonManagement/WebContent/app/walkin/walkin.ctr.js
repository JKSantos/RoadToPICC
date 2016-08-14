(function(){
  'use strict';

  angular
    .module('app')
    .controller('walkinCtrl', walkinCtrl);

    function walkinCtrl($scope, paymentFactory, locationFactory){
    var vm = this;
    vm.selected = 'product';
    vm.customerDetails = [{}];
    vm.reservationDetails =[{}];
    var selectprod = "";
    var quantprod = "";
    var selectserv = "";
    var quantserv = "";
    var selectpack = "";
    var quantpack = "";
    var selectprom = "";
    var quantprom = "";
    var selectextra = "";
    var selectdiscount = "";
    var selectemployees = "";

  
    vm.quantity = 0;
    vm.productTotal = 0;
    vm.serviceTotal = 0;
    vm.packageTotal = 0;
    vm.promoTotal = 0;

    vm.productOrder = [{}];
    vm.serviceOrder = [{}];
    vm.promoOrder = [{}];
    vm.packageOrder = [{}];

    vm.details = [{}];

     locationFactory.getEmployees().then(function(data){
        vm.employeeList = data.data.employeeList;
      });

       locationFactory.getExtraCharges().then(function(data){
        vm.extraChargeList = data.data.extraChargeList;
      });

      locationFactory.getDiscounts().then(function(data){
        vm.discountList = data.data.discountList;
      });

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

     vm.addToCart = function(index, selected){
        if(selected == 'product'){
          vm.productOrder.push({
                            product: vm.productList[index].strProductName, 
                            productID: vm.productList[index].intProductID,
                            productQuantity: vm.quantity,
                            productTotal: vm.productList[index].dblProductPrice * vm.quantity
                              });
          var selectedProducts = "";
          var selectedProductQuantity = "";
          var subTotalProducts = 0;
          for(var i = 1; i < vm.productOrder.length; i++){
              selectedProducts += vm.productOrder[i].productID + ",";
              selectedProductQuantity += vm.productOrder[i].productQuantity + ",";
              subTotalProducts += vm.productOrder[i].productTotal;

          }
          selectprod = selectedProducts;
          quantprod = selectedProductQuantity;
          vm.productTotal = subTotalProducts;
          vm.quantity = '';
          
        }else if(selected == 'service'){
           vm.serviceOrder.push({
                            service: vm.serviceList[index].strServiceName, 
                            serviceID: vm.serviceList[index].intServiceID,
                            serviceQuantity: vm.quantity,
                            serviceTotal: vm.serviceList[index].dblServicePrice * vm.quantity
                              });
           var selectedService = "";
           var selectedServiceQuantity = "";
           var subTotalService = 0;
           for(var i = 1; i < vm.serviceOrder.length; i++){
               selectedService += vm.serviceOrder[i].serviceID + ",";
               selectedServiceQuantity += vm.serviceOrder[i].serviceQuantity + ",";
               subTotalService += vm.serviceOrder[i].serviceTotal;
           }
           selectserv = selectedService;
           quantserv = selectedServiceQuantity;    
           vm.serviceTotal = subTotalService;
           vm.quantity = '';
        }else if(selected == 'package'){
           vm.packageOrder.push({
                            package: vm.packageList[index].strPackageName, 
                            packageID: vm.packageList[index].intPackageID,
                            packageQuantity: vm.quantity,
                            packageTotal: vm.packageList[index].dblPackagePrice * vm.quantity
                              });
           var selectedPackage = "";
           var selectedPackageQuantity = "";
           var subTotalPackage = 0;
           for(var i = 1; i < vm.packageOrder.length; i++){
               selectedPackage += vm.packageOrder[i].packageID + ",";
               selectedPackageQuantity += vm.packageOrder[i].packageQuantity + ",";
               subTotalPackage += vm.packageOrder[i].packageTotal;

           }
           selectpack = selectedPackage;
           quantpack = selectedPackageQuantity;
           vm.packageTotal = subTotalPackage;
           vm.quantity = '';
        }else if(selected == 'promo'){
           vm.promoOrder.push({
                            promo: vm.promoList[index].strPromoName, 
                            promoID: vm.promoList[index].strPromoName,
                            promoQuantity: vm.quantity,
                            promoTotal: vm.promoList[index].dblPromoPrice * vm.quantity
                              });
           var selectedPromo = "";
           var selectedPromoQuantity = "";
           var subTotalPromo = 0;
           for(var i = 1; i < vm.promoOrder.length; i++){
               selectedPromo += vm.promoOrder[i].promoID + ",";
               selectedPromoQuantity += vm.promoOrder[i].promoQuantity + ",";
               subTotalPromo += vm.promoOrder[i].promoTotal;

           }
           selectprom = selectedPromo;
           quantprom = selectedPromoQuantity;
           vm.promoTotal = subTotalPromo;
           vm.quantity = '';
        }
      };

      vm.sumTotal = function(){
        var summed = vm.productTotal + vm.serviceTotal + vm.packageTotal + vm.promoTotal;
        vm.sum = summed;
        console.log(summed);
      };

      function toString(){
        var selectedExtra = "";
        var selectedDiscount = "";
        var selectedEmployeee = "";
        for(var i = 0; i < vm.extraCharge.length; i++){
            selectedExtra += vm.extraCharge[i].intECID + ",";
        }
        for(var i = 0; i < vm.selDiscounts.length; i++){  
            selectedDiscount += vm.selDiscounts[i].intDiscountID  + ",";
        }
        for(var i = 0; i < vm.selEmployees.length; i++){
            selectedEmployeee += vm.selEmployees[i].intEmpID + ",";
        }
        selectextra = selectedExtra;
        selectdiscount = selectedDiscount;
        selectemployees = selectedEmployeee;
      }


      vm.saveReservation = function(details){
      toString();
      vm.customerDetails.push({
        intID: 1,
        strName: vm.details.name,
        strAddress: vm.details.address,
        strContactNo: vm.details.contact,
        strEmail: vm.details.email
      });

      vm.reservationDetails.push({
        customer: vm.customerDetails,
        intReservationType: 1,
        datFrom: vm.details.dtFrom,
        datTo: vm.details.dtTo,
        timFrom: vm.details.tmFrom,
        timTo: vm.details.tmTo,
        strVenue: vm.details.venue,
        headCount: vm.details.headCount,
        selectedEmployees: selectemployees,
        selectedProducts: selectprod,
        selectedServices: selectserv,
        selectedPackages: selectpack,
        selectedPromos: selectprom,
        productQuantity: quantprod,
        serviceQuantity: quantserv,
        packageQuantity: quantpack,
        promoQuantity: quantprom,
        selectedExtraCharges: selectextra,
        selectedDiscounts: selectdiscount,
        strTotalPrice: vm.sum
      });

      console.log(vm.reservationDetails);
      };
  }
})();







