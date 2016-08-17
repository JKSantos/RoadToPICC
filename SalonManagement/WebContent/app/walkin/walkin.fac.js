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
          	strName: name,
  	        strContactNo: contact,
  	        productString: selectprod,
  	        productQuantity: quantprod,
  	        serviceDetails: serviceDetails,
  	        packageList: packageDetails,
  	        promoList: promoDetails,
  	        discounts: selectdiscount,
  	        strTotalPrice: total  
    	  });
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
                        data: psdata,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                            if (data.status == "success") {
                                SweetAlert.swal("Successfully created!", ".", "success");
                                $('#createWalkinModal').closeModal();
                            } else {
                                SweetAlert.swal("Oops", "Something went wrong!", "error");
                            }
                        },
                        error: function () {
                            SweetAlert.swal("Oops", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });
        	
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
