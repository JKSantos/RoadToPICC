(function () {
    'use strict';
    angular
        .module('app')
        .controller('paymentCtrl', paymentCtrl);

    function paymentCtrl($scope, $resource, $filter, SweetAlert, DTOptionsBuilder, DTColumnBuilder, DTDefaultOptions, paymentFactory) {
        var vm = this;
        vm.dateFormat = ["MMMM/D/YYYY"];
        vm.type = [{
            option1: "order",
            option2: "walkin",
            option3: "reservation"
        }];
        vm.sortType = 'strName';
        vm.sortReverse = false;
        vm.paymentSearch = '';

        vm.paymentList = [];
        vm.createPOPayment = createPOPayment;
        vm.paymentSubmit = paymentSubmit;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;

        function dtInstanceCallback (dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable () {
            var query = vm.paymentSearch;
            vm.tableInstance.search(query).draw();
        }

        paymentFactory.getUnpaidPayments().then(function (data) {
            for (var i = 0; i < data.orderList.length; i++) {
                vm.paymentList.push(data.orderList[i]);
            }
            for (var i = 0; i < data.reservationList.length; i++) {
                vm.paymentList.push(data.reservationList[i]);
            }
        });


        function createPOPayment(payment, index, type) {
            $('#paymentModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            vm.paymentDetails = [];
            if (type == 'order') {
                vm.paymentDetails = {
                    datCreated: payment.datCreated,
                    deliveryDate: payment.deliveryDate,
                    intLocationID: payment.intLocationID,
                    intSalesID: payment.intSalesID,
                    intType: payment.intType,
                    invoice: payment.invoice,
                    productList: payment.productList,
                    strAddress: payment.strAddress,
                    strContactNo: payment.strContactNo,
                    strName: payment.strName,
                    strStatus: payment.strStatus,
                    paymentCreated: new Date(),
                    index: index,
                    type: type //reservation or walkin or order
                };
            } else if (type == 'reservation') {
                vm.paymentDetails = {
                    customer: payment.customer,
                    datFrom: payment.datFrom,
                    datTo: payment.datTo,
                    dateCreated: payment.dateCreate,
                    employeeAssigned: payment.employeeAssigned,
                    headCount: payment.headCount,
                    includedItems: payment.includedItems,
                    intReservationID: payment.intReservationID,
                    intReservationType: payment.intReservationType,
                    invoice: payment.invoice,
                    strStatus: payment.strStatus,
                    strVenue: payment.strVenue,
                    timFrom: payment.timFrom,
                    timTo: payment.timTo,
                    paymentCreated: new Date(),
                    type: type,
                    index: index
                };
                console.log(vm.paymentDetails);
            }
            if (type == 'order') {
                vm.paymentType = [
                    {id: 1, value: 'FULL PAYMENT', name: 'FULL PAYMENT'}
                ];
                vm.paymentDetails.paymentCreated = $filter('date')(vm.paymentDetails.paymentCreated, "MMMM/d/yyyy");
                vm.paymentDetails.totalBalance = $filter('currency')(vm.paymentDetails.invoice.dblTotalPrice, "Php ");
                vm.paymentDetails.remainingBalance = $filter('currency')(vm.paymentDetails.invoice.dblRemainingBalance, "Php ");
                vm.paymentDetails.paymentAmount = $filter('currency')(vm.paymentDetails.paymentAmount, "Php ");
            } else if (type == 'reservation') {
                vm.paymentType = [
                    {id: 1, value: 'FULL PAYMENT', name: 'FULL PAYMENT'},
                    {id: 2, value: 'DOWN PAYMENT', name: 'DOWN PAYMENT'},
                    {id: 3, value: 'COMPLIMENTARY PAYMENT', name: 'COMPLIMENTARY PAYMENT'}
                ];
                vm.paymentDetails.paymentCreated = $filter('date')(vm.paymentDetails.paymentCreated, "MMMM/d/yyyy");
                vm.paymentDetails.totalBalance = $filter('currency')(vm.paymentDetails.invoice.dblTotalPrice, "Php ");
                vm.paymentDetails.remainingBalance = $filter('currency')(vm.paymentDetails.invoice.dblRemainingBalance, "Php ");
                vm.paymentDetails.paymentAmount = $filter('currency')(vm.paymentDetails.paymentAmount, "Php ");
            }
            vm.paymentDetails.paymentType = vm.paymentType[0];
        }

        function paymentSubmit(payment) {
            var name = "",
                paymentData = {};
            if (payment.type == 'order') {
                paymentData = {
                    "intPaymentID": payment.intSalesID,
                    "intInvoiceID": payment.invoice.intInvoiceID,
                    "strPaymentType": payment.type,
                    "dblPaymentAmount": payment.paymentAmount,
                    "datDateOfPayment": payment.paymentCreated,
                    "paymentType": payment.paymentType.value
                };
                var index = payment.index;
                name = payment.strName;
            } else if (payment.type == 'reservation') {
                paymentData = {
                    "intPaymentID": payment.intReservationID,
                    "intInvoiceID": payment.invoice.intInvoiceID,
                    "strPaymentType": payment.type,
                    "dblPaymentAmount": payment.paymentAmount,
                    "datDateOfPayment": payment.paymentCreated,
                    "paymentType": payment.paymentType.value
                };
                var index = payment.index;
                name = payment.customer.strName
            }
            console.log(paymentData);
            swal({
                    title: "Create payment for " + name + "?",
                    text: "",
                    type: "",
                    confirmButtonColor: "#81d4fa",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    setTimeout(function () {
                        $.ajax({
                            url: 'createPayment',
                            type: 'post',
                            data: paymentData,
                            dataType: 'json',
                            async: true,
                            success: function (data) {
                                if (data.result == "success") {
                                    SweetAlert.swal("Successfully created!", ".", "success");
                                    vm.paymentList.splice(index, 1);
                                    $('#paymentModal').closeModal();

                                } else {
                                    SweetAlert.swal("Oops", "Record Not Saved!", "error");
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
})();
