(function(){
  'use strict';

  angular
    .module('app')
    .factory('paymentFactory', paymentFactory);

    function paymentFactory($http){
      var orderDetails = [{}];
      var paymentDetails = [{}];
      var subtotal = 0;
      return{
        getPayments: function(){
          return paymentDetails;
        },
        getOrderDetails: function(){
          return orderDetails;
        },
        getSubTotal: function(){
          return subtotal;
        },
        insertPayments: function(list){
          paymentDetails.push(list);
          console.log(paymentDetails);
        },
        insertOrder: function(productId, quantity){
          orderDetails.push({
            productID: productId,
            productQuantity: quantity
          });
        },
        insertTotal: function(total){
          subtotal = total;
          console.log(subtotal);
        },
        saveOrderDetails: function(myData){
            console.log(myData);
            var psdata = {
              "intLocationID": myData.intLocationID,
              "orderType": myData.orderType,
              "productQuantity": myData.productQuantity,
              "selectedProducts": myData.selectedProducts,
              "strContactNo": myData.strContactNo,
              "strName": myData.strName,
              "strStreet": myData.strStreet,
              "strTotalPrice": myData.strTotalPrice
            }

            swal({
                title: "Create this order?",
                text: "",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            },
            function () {
                setTimeout(function () {
                    $.ajax({
                        url: 'createOrder',
                        type: 'post',
                        data: psdata,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                            swal("Successfully created!", ".", "success");
                            $('#crProductSales').closeModal();
                        },
                        error: function () {
                            sweetAlert("Oops...", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });

            }
        }
    }
})();
