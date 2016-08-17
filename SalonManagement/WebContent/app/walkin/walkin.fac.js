(function(){
  'use strict';

  angular
    .module('app')
    .factory('walkinFactory', walkinFactory)

    function walkinFactory(SweetAlert){
      var walkinDetails = [];
      
      return{
    	  getCustomers: function(){
    		  return walkinDetails;
    	  },
    	  insertCustomer: function(name, contact, email, selectEmp,
					   selectprod, quantprod, packageDetails, promoDetails,
	  					 serviceDetails, selectdiscount, total){
    		console.log("I'm on insert!");
    		console.log(total);
    		var topID = walkinDetails.length + 1;
        walkinDetails.push({
      		  id: topID,
          	name: name,
  	        contact: contact,
  	        productString: selectprod,
  	        productQuantity: quantprod,
  	        serviceDetails: serviceDetails,
  	        packageList: packageDetails,
  	        promoList: promoDetails,
  	        discounts: selectdiscount,
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
         swal({
                title:"",
                text: "",
                type: "",
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            },
            function () {
                setTimeout(function () {
                    $.ajax({
                        url: 'createWalkin',
                        type: 'post',
                        data: walkinDetails,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                                // SweetAlert.swal("Successfully created!", ".", "success");
                               console.log("error");
                               console.log(walkinDetails);
                                $('#createWalkinModal').closeModal();
                        },
                        error: function () {
                           consol.log("success"); // SweetAlert.swal("Oops", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });
        }
    }
  }
})();
