(function(){
  'use strict';

  angular
    .module('app')
    .factory('walkinFactory', walkinFactory)

    function walkinFactory(SweetAlert){
      var walkinDetails = [],
          walkinData = [];
      
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
  	        email: email,
  	        selectedProducts: selectprod,
  	        productQuantity: quantprod,
  	        serviceDetails: serviceDetails,
  	        packageList: packageDetails,
  	        promoList: promoDetails,
  	        selectedDiscounts: selectdiscount,
  	        strTotalPrice: total  
    	  });
        walkinData.push({
            "id": topID,
            "strName": name,
            "strContactNo": contact,
            "productString": selectprod,
            "productQuantity": quantprod,
            "serviceDetails": serviceDetails,
            "packageList": packageDetails,
            "promoList": promoDetails,
            "discounts": selectdiscount,
            "strTotalPrice": total 
        });
       console.log(walkinData[0]);
        	
        },
        moveToPayment: function(id){
          var walkin = walkinData[0];
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
                        data: walkin,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                                SweetAlert.swal("Successfully created!", ".", "success");
                                $('#createWalkinModal').closeModal();
                                   for (var i = walkinData.length - 1; i >= 0; i--) {
                                         if (walkinData[i].id === id) {
                                           walkinData.splice(i, 1);
                                             break;
                                         }
                                     }
                        },
                        error: function () {
                          console.log(walkin);
                           SweetAlert.swal("Oops", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });
        }
    }
  }
})();
