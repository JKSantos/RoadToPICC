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
    vm.packageList = {};
    vm.promoList = {};
    

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

    locationFactory.getPromosWithDetails().then(function(data){
        vm.promoList = data.data.promoList;
      });

    locationFactory.getPackagesWithDetails().then(function(data){
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
        	console.log(vm.selEmployee);
        	vm.selServiceDetails = [];
        	
           vm.serviceOrder.push({
                            service: vm.serviceList[index].strServiceName, 
                            serviceID: vm.serviceList[index].intServiceID,
                            serviceQuantity: 1,
                            serviceTotal: vm.serviceList[index].dblServicePrice ,
                            selectedEmployee: vm.selEmployee.intEmpID
                              });
           
           vm.selServiceDetails.push({
 		 	   intServiceID:vm.serviceList[index].intServiceID,
 		 	   intQuantity: 1,
 		 	   intEmployeeID:  vm.selEmployee.intEmpID,
 		 	   strStatus: 'pending'
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
           
           vm.packageContains = vm.packageList[index].serviceList;
           
           vm.assignEmployeePackage = function(index){
        	    vm.selPackageDetails = [];
                
			   vm.selPackageDetails.push({
		 	   intServiceID: vm.packageContains[index].service.intServiceID,
		 	   intQuantity: 1,
		 	   intEmployeeID: vm.selEmployeePerService.intEmpID,
		 	   strStatus: 'pending'
		        });
			   	console.log(vm.selPackageDetails);
           };
           $('#packageListModal').openModal({
               dismissible: true, // Modal can be dismissed by clicking outside of the modal
               opacity: .7, // Opacity of modal background
               in_duration: 200, // Transition in duration
               out_duration: 200, // Transition out duration
           });
           vm.packageOrder.push({
                            package: vm.packageList[index].strPackageName, 
                            packageID: vm.packageList[index].intPackageID,
                            packageQuantity: 1,
                            packageTotal: vm.packageList[index].dblPackagePrice
                              });
           var selectedPackage = "";
           var subTotalPackage = 0;
           for(var i = 1; i < vm.packageOrder.length; i++){
               selectedPackage += vm.packageOrder[i].packageID + ",";
               subTotalPackage += vm.packageOrder[i].packageTotal;

           }
           selectpack = selectedPackage;
           vm.packageTotal = subTotalPackage;
           vm.quantity = '';
        }else if(selected == 'promo'){
        	 
        	 vm.promoContains = vm.promoList[index].serviceList;
            
             vm.assignEmployeePromo = function(index){
            	 console.log('Ken Pogi');
          	   vm.selPromoDetails = [];
               console.log(vm.promoContains);
               console.log(index);
  			   vm.selPromoDetails.push({
  		 	   intServiceID: vm.promoContains[index].service.intServiceID,
  		 	   intQuantity: 1,
  		 	   intEmployeeID: vm.selEmployeePerService.intEmpID,
  		 	   strStatus: 'pending'
  		        });
  			   	console.log(vm.selPromoDetails);
             };
             
             $('#promoListModal').openModal({
                 dismissible: true, // Modal can be dismissed by clicking outside of the modal
                 opacity: .7, // Opacity of modal background
                 in_duration: 200, // Transition in duration
                 out_duration: 200, // Transition out duration
             });
             
           vm.promoOrder.push({
                            promo: vm.promoList[index].strPromoName, 
                            promoID: vm.promoList[index].strPromoName,
                            promoQuantity: 1,
                            promoTotal: vm.promoList[index].dblPromoPrice
                              });
           var selectedPromo = "";
           var selectedPromoQuantity = "";
           var subTotalPromo = 0;
           for(var i = 1; i < vm.promoOrder.length; i++){
               selectedPromo += vm.promoOrder[i].promoID + ",";
               subTotalPromo += vm.promoOrder[i].promoTotal;

           }
           selectprom = selectedPromo;
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
    	  var name = vm.details.name;
    	  var contact = vm.details.contact;
    	  var email = vm.details.email;
    	  var packageDetails = vm.selPackageDetails;
    	  var promoDetails = vm.selPromoDetails;
    	  var serviceDetails = vm.selServiceDetails;
    	  
    
    	
    	  walkinFactory.insertCustomer(name, contact, email, selectEmp,
    			  					   selectprod, quantprod, packageDetails, promoDetails,
    			  					 serviceDetails, selectdiscount, total);
      };
      
      vm.moveToPay = function(id){
    	  walkinFactory.moveToPayment(id);
      }
  }
})();







