(function(){
  'use strict';

  angular
    .module('app')
    .controller('walkinCtrl', walkinCtrl);

    function walkinCtrl($scope, paymentFactory, locationFactory, walkinFactory){
    var vm = this;
    vm.selected = 'product';
    vm.selEmployee = '';
    
    var selectprod = "";
    var quantprod = "";
    var selectserv = "";
    var quantserv = "";
    var selectpack = "";
    var quantpack = "";
    var selectprom = "";
    var quantprom = "";
    var selectdiscount = "";
    var selectEmp = "";

    vm.selDiscounts = [];
    vm.quantity = '';
    vm.productTotal = 0;
    vm.serviceTotal = 0;
    vm.packageTotal = 0;
    vm.promoTotal = 0;

    vm.productOrder = [];
    vm.serviceOrder = [];
    vm.promoOrder = [];
    vm.packageOrder = [];

    vm.details = [{}];
    
    vm.customerList = walkinFactory.getCustomers();
    

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
    	 console.log(vm.customerList);
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
        	console.log(vm.selEmployee);
           vm.serviceOrder.push({
                            service: vm.serviceList[index].strServiceName, 
                            serviceID: vm.serviceList[index].intServiceID,
                            serviceQuantity: vm.quantity,
                            serviceTotal: vm.serviceList[index].dblServicePrice * vm.quantity,
                            selectedEmployee: vm.selEmployee.intEmpID
                              });
           var selectedService = "";
           var selectedServiceQuantity = "";
           var selectedEmployee = "";
           var subTotalService = 0;
           for(var i = 1; i < vm.serviceOrder.length; i++){
               selectedService += vm.serviceOrder[i].serviceID + ",";
               selectedServiceQuantity += vm.serviceOrder[i].serviceQuantity + ",";
               selectedEmployee += vm.serviceOrder[i].selectedEmployee + ",";
               subTotalService += vm.serviceOrder[i].serviceTotal;
           }
           selectserv = selectedService;
           quantserv = selectedServiceQuantity;   
           selectEmp = selectedEmployee;
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
        var selectedDiscount = "";
        for(var i = 0; i < vm.selDiscounts.length; i++){  
            selectedDiscount += vm.selDiscounts[i].intDiscountID  + ",";
        }
        selectdiscount = selectedDiscount;
      }


      vm.saveWalkin = function(details){
    	  toString();
    	  var total = vm.sum;
    	  var name = details.name;
    	  var contact = details.contact;
    	  var email = details.email;
    	  walkinFactory.insertCustomer(name, contact, email, selectEmp,
    			  					   selectprod, selectserv, selectpack, selectprom,
    			  					   quantprod, quantserv, quantpack, quantprom, selectdiscount, total);
      };
      
      vm.moveToPay = function(id){
    	  walkinFactory.moveToPayment(id);
      }
  }
})();






