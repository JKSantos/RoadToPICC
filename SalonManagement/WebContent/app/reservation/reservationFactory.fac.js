(function(){
  'use strict';

  angular
    .module('app')
    .factory('reservationFactory', reservationFactory)

    function reservationFactory(){
      var reservationDetails = [];
      var customerDetails = [];
      
      return{
        getCustomers: function(){
          return reservationDetails;
        },
        insertCustomer: function(name, address, contact, email, resrvationType, 
                                  datFrom, datTo, tmFrom, tmTo, venue, 
                                  headCount, selEmployee, selProducts, selServices, selPackages, 
                                  selPromos, quantProdcut, quantService, quantPackage, quantPromo, 
                                  selectextra, selectdiscount, selectemployees, totalPrice){
        console.log("I'm on insert!");
        var topID = reservationDetails.length + 1;

          customerDetails.push({
            intID: 1,
            strName: name,
            strAddress: address,
            strContactNo: contact,
            strEmail: email
          });
          reservationDetails.push({
            id: topID,
            customer: customerDetails,
            intReservationType: resrvationType,
            datFrom: datFrom,
            datTo: datTo,
            timFrom: tmFrom,
            timTo: tmTo,
            strVenue: venue,
            headCount: headCount,
            selectedEmployees: selectemployees,
            selectedProducts: selProducts,
            selectedServices: selServices,
            selectedPackages: selPackages,
            selectedPromos: selPromos,
            productQuantity: quantProdcut,
            serviceQuantity: quantService,
            packageQuantity: quantPackage,
            promoQuantity: quantPromo,
            selectedExtraCharges: selectextra,
            selectedDiscounts: selectdiscount,
            strTotalPrice: totalPrice
          
        });
        },
        moveToPayment: function(id){
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
                        url: 'createReservation',
                        type: 'post',
                        data: reservationDetails,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                            if (data.status == "success") {
                                SweetAlert.swal("Successfully created!", ".", "success");
                                for (var i = reservationDetails.length - 1; i >= 0; i--) {
                                      if (reservationDetails[i].id === id) {
                                        reservationDetails.splice(i, 1);
                                          break;
                                      }
                                  }
                                $('#createReservationModal').closeModal();
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
        }
    }
  }
})();
