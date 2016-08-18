(function () {
    'use strict';
    angular
        .module('app')
        .controller('paymentCtrl', paymentCtrl);

    function paymentCtrl($scope, $resource, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, paymentFactory, sampleFactory) {
        var vm = this;
        vm.dateFormat = ["MMMM/D/YYYY"];
        vm.type = [{
            option1: "order",
            option2: "walkin",
            option3: "reservation"
        }];
        vm.createPOPayment = createPOPayment;
        vm.paymentSubmit = paymentSubmit;
        vm.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(10)
            .withLanguage({
                "sLoadingRecords": "Loading..."
            });
        vm.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(0),
            DTColumnDefBuilder.newColumnDef(1).notSortable(),
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3),
            DTColumnDefBuilder.newColumnDef(4),
            DTColumnDefBuilder.newColumnDef(5).notSortable()
        ];


        $resource('getAllUnpaidTransaction').query().$promise.then(function (data) {
            vm.paymentList = data.orderList;
        });
        
        $resource('getAllUnpaidTransaction').query().$promise.then(function (data) {
            vm.reservationList = data.reservationList;
        });
        function createPOPayment(payment, index, type) {
            $('#paymentModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            vm.paymentType = [{
                option1: 'FULL PAYMENT',
                option2: 'DOWN PAYMENT',
                option3: 'COMPLIMENTARY PAYMENT'
            }];
            vm.paymentDetails = [];
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
            console.log(vm.paymentDetails.type + index + type);
            vm.paymentDetails.paymentType = vm.paymentType[0].option1;
            vm.paymentDetails.paymentCreated = $filter('date')(vm.paymentDetails.paymentCreated, "MMMM/d/yyyy");
            vm.paymentDetails.totalBalance = $filter('currency')(vm.paymentDetails.invoice.dblTotalPrice, "Php ");
            vm.paymentDetails.remainingBalance = $filter('currency')(vm.paymentDetails.invoice.dblRemainingBalance, "Php ");
            vm.paymentDetails.paymentAmount = $filter('currency')(vm.paymentDetails.paymentAmount, "Php ");

        }

        function paymentSubmit(payment) {
            var paymentData = {
                "intPaymentID": payment.intSalesID,
                "intInvoiceID": payment.invoice.intInvoiceID,
                "strPaymentType": payment.type,
                "dblPaymentAmount": payment.paymentAmount,
                "datDateOfPayment": payment.paymentCreated,
                "paymentType": payment.paymentType
            };
            console.log(paymentData);
            swal({
                    title: "Create the payment for " + payment.strName + "?",
                    text: "",
                    type: "",
                    showCancelButton: true,
                    confirmButtonColor: "#81d4fa",
                    confirmButtonText: "Yes",
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
                                    vm.paymentList.splice(payment.index, 1);
                                    $('#paymentModal').closeModal();
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
})();
