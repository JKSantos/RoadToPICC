(function(){
  'use strict';

  angular
    .module('app')
    .factory('walkinFactory', walkinFactory)

    function walkinFactory(){
      var walkinDetails = [];
      
      return{
    	  getCustomers: function(){
    		  return walkinDetails;
    	  },
    	  insertCustomer: function(name, contact, email, selEmployee, selProducts,
    			  				   selServices, selPackages, selPromos, quantProdcut,
    			  				   quantService, quantPackage, quantPromo, selDiscounts,
    			  				   totalPrice){
    		console.log("I'm on insert!");
    		var topID = walkinDetails.length + 1;
        	walkinDetails.push({
    		id: topID,
        	name: name,
	        contact: contact,
	        email: email,
	        selectedEmployees: selEmployee,
	        selectedProducts: selProducts,
	        selectedServices: selServices,
	        selectedPackages: selPackages,
	        selectedPromos: selPromos,
	        productQuantity: quantProdcut,
	        serviceQuantity: quantService,
	        packageQuantity: quantPackage,
	        promoQuantity: quantPromo,
	        selectedDiscounts: selDiscounts,
	        strTotalPrice: totalPrice  
    	  });
        	
        	console.log(walkinDetails);
        },
        moveToPayment: function(id){
        	 for (var i = walkinDetails.length - 1; i >= 0; i--) {
                 if (walkinDetails[i].id === id) {
                	 walkinDetails.splice(i, 1);
                     break;
                 }
             }
        }
    }
  }
})();
