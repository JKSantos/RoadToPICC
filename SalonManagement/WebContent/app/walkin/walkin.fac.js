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
<<<<<<< HEAD
          	name: name,
  	        contact: contact,
  	        email: email,
  	        selectedProducts: selectprod,
  	        productQuantity: quantprod,
=======
      		strName: name,
      		strContactNo: contact,
      		productString: selectprod,
      		productQuantity: quantprod,
>>>>>>> 3e7b91870db30d6d22897335945f787a05c02edf
  	        serviceDetails: serviceDetails,
  	        packageList: packageDetails,
  	        promoList: promoDetails,
  	        selectedDiscounts: selectdiscount,
  	        strTotalPrice: total  
    	  });
<<<<<<< HEAD
        var walkindata = {
            id: topID,
            strName: name,
            strContactNo: contact,
            email: email,
            selectedProducts: selectprod,
            productQuantity: quantprod,
            serviceDetails: serviceDetails,
            packageList: packageDetails,
            promoList: promoDetails,
            selectedDiscounts: selectdiscount,
            strTotalPrice: total 
        }
        swal({
=======
       console.log(walkinDetails);
        	
        },
        moveToPayment: function(id){
         swal({
>>>>>>> 3e7b91870db30d6d22897335945f787a05c02edf
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
                                SweetAlert.swal("Successfully created!", ".", "success");
<<<<<<< HEAD
                                console.log($scope.customerDetails[1]);
                                $scope.requestOrder.push({
                                    strName: myData.strName,
                                    intType: myData.orderType
                                });
                                console.log($scope.requestOrder);
                                $('#crProductSales').closeModal();
                                $scope.customerDetails = [{
                                    orderType: '',
                                    contactNumber: '',
                                    name: '',
                                    Street: '',
                                    location: '',
                                    orderDetails: '',
                                    subtotal: 0
                                }];
                                $window.location.reload();
                            } else {
                                SweetAlert.swal("Oops", "Something went wrong!", "error");
                            }
=======
                                $('#createWalkinModal').closeModal();
                                   for (var i = walkinDetails.length - 1; i >= 0; i--) {
                                         if (walkinDetails[i].id === id) {
                                           walkinDetails.splice(i, 1);
                                             break;
                                         }
                                     }
>>>>>>> 3e7b91870db30d6d22897335945f787a05c02edf
                        },
                        error: function () {
                           SweetAlert.swal("Oops", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });
        }
    }
  }
})();
