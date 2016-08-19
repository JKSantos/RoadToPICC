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
    	  insertCustomer: function(name, contact, email,
					   selectprod, quantprod, packageDetails, promoDetails,
	  					 serviceDetails, selectdiscount, total){
    		console.log("I'm on insert!");
    		var topID = walkinDetails.length + 1;
        	walkinDetails.push({
    		id: topID,
        	name: name,
	        contact: contact,
	        email: email,
	        selectedProducts: selectprod,
	        productQuantity: quantprod,
	        serviceDetails: serviceDetails,
	        packageList: packageDetails,
	        promoList: promoDetails,
	        selectedDiscounts: selectdiscount,
	        strTotalPrice: total  
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
