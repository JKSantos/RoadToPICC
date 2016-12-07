(function () {
    'use strict';
    angular
        .module('app')
        .controller('paymentCtrl', paymentCtrl);

    function paymentCtrl($scope, $resource, $window, $filter, SweetAlert, DTOptionsBuilder, DTColumnBuilder, DTDefaultOptions, paymentFactory, locationFactory) {
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
        vm.downPaymentError = 0;
        vm.fullPaymentError = 0;

        vm.paymentList = [];
        vm.createPOPayment = createPOPayment;
        vm.paymentSubmit = paymentSubmit;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;

        function dtInstanceCallback(dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable() {
            var query = vm.paymentSearch;
            vm.tableInstance.search(query).draw();
        }

        paymentFactory.getUnpaidPayments().then(function (data) {
            for (var x = 0; x < data.orderList.length; x++) {
                vm.paymentList.push(data.orderList[x]);
            }
            for (var i = 0; i < data.reservationList.length; i++) {
                vm.paymentList.push(data.reservationList[i]);
            }
            for (var j = 0; j < data.walkinList.length; j++) {
                vm.paymentList.push(data.walkinList[j]);
            }
            console.log(vm.paymentList);
        });

        locationFactory.getDependencies().then(function (data) {
            vm.dependencies = data.dependencies;

            angular.forEach(vm.dependencies, function (i) {
                if (i.strName == 'Down Payment' || i.strName == 'DOWN PAYMENT') {
                    var val = i.strValue.replace('%', '');
                    vm.downPayment = val;
                }
            });
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
            } else if (type == 'walkin') {
                vm.paymentDetails = {
                    datWalkIn: payment.datWalkIn,
                    intWalkInID: payment.intWalkInID,
                    invoice: payment.invoice,
                    packages: payment.packages,
                    payment: payment.payment,
                    products: payment.products,
                    promo: payment.promo,
                    services: payment.services,
                    strContactNo: payment.strContactNo,
                    strName: payment.strName,
                    strPaymentStatus: payment.strPaymentStatus,
                    strWalkInStatus: payment.strWalkInStatus,
                    strWalkInType: payment.strWalkInType,
                    paymentCreated: new Date(),
                    type: type,
                    index: index
                }
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
                console.log(vm.paymentDetails.invoice.dblTotalPrice);
                console.log(vm.paymentDetails.invoice.dblRemainingBalance);
                if (vm.paymentDetails.invoice.dblTotalPrice != vm.paymentDetails.invoice.dblRemainingBalance) {
                    vm.paymentType = [
                        {id: 1, value: 'FULL PAYMENT', name: 'FULL PAYMENT'},
                        {id: 3, value: 'COMPLEMENTARY PAYMENT', name: 'COMPLEMENTARY PAYMENT'}
                    ];
                } else if (vm.paymentDetails.invoice.dblTotalPrice == vm.paymentDetails.invoice.dblRemainingBalance) {
                    vm.paymentType = [
                        {id: 1, value: 'FULL PAYMENT', name: 'FULL PAYMENT'},
                        {id: 2, value: 'HALF PAYMENT', name: 'HALF PAYMENT'},
                    ];
                    console.log(vm.paymentDetails.invoice);
                }
                vm.paymentDetails.paymentCreated = $filter('date')(vm.paymentDetails.paymentCreated, "MMMM/d/yyyy");
                vm.paymentDetails.totalBalance = $filter('currency')(vm.paymentDetails.invoice.dblTotalPrice, "Php ");
                vm.paymentDetails.remainingBalance = $filter('currency')(vm.paymentDetails.invoice.dblRemainingBalance, "Php ");
            } else if (type == 'walkin') {
                vm.paymentType = [
                    {id: 1, value: 'FULL PAYMENT', name: 'FULL PAYMENT'}
                ];
                vm.paymentDetails.paymentCreated = $filter('date')(vm.paymentDetails.paymentCreated, "MMMM/d/yyyy");
                vm.paymentDetails.totalBalance = $filter('currency')(vm.paymentDetails.invoice.dblTotalPrice, "Php ");
                vm.paymentDetails.remainingBalance = $filter('currency')(vm.paymentDetails.invoice.dblRemainingBalance, "Php ");
                vm.paymentDetails.paymentAmount = $filter('currency')(vm.paymentDetails.paymentAmount, "Php ");
            }
            vm.paymentDetails.paymentType = vm.paymentType[0];
        }


        function paymentSubmit(payment) {
            var go = 0;
            console.log(vm.paymentDetails.paymentAmount);
            if(typeof vm.paymentDetails.paymentAmount !== undefined) {
                if (vm.paymentDetails.paymentType.name == 'FULL PAYMENT') {
                    var tb = vm.paymentDetails.totalBalance.replace(/[^\d.]/g, ''),
                        amt = vm.paymentDetails.paymentAmount.replace(/[^\d.]/g, '');
                    if (parseFloat(amt) > parseFloat(tb)) {
                        vm.fullPaymentError = 0;
                        go = 1;
                    } else {
                        vm.fullPaymentError = 1;
                    }
                } else if (vm.paymentDetails.paymentType.name == 'DOWN PAYMENT') {
                    var dp = vm.paymentDetails.totalBalance.replace(/[^\d.]/g, '') * (vm.downPayment / 100); //down payment
                    if (parseFloat(vm.paymentDetails.paymentAmount.replace(/[^\d.]/g, '')) < parseFloat(dp)) {
                        vm.downPaymentError = 1;
                    } else {
                        vm.downPaymentError = 0;
                        go = 1;
                    }
                } else {
                    go = 1;
                }

                if (go > 0) {
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
                    } else if (payment.type == 'walkin') {
                        paymentData = {
                            "intPaymentID": payment.intWalkInID,
                            "intInvoiceID": payment.invoice.intInvoiceID,
                            "strPaymentType": payment.type,
                            "dblPaymentAmount": payment.paymentAmount,
                            "datDateOfPayment": payment.paymentCreated,
                            "paymentType": payment.paymentType.value
                        };
                        var index = payment.index;
                        name = payment.strName
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
                                            SweetAlert.swal("Successful!", ".", "success");

                                            $window.open('http://localhost:8080/SalonManagement/open?path='+data.url);

                                            if (vm.paymentDetails.paymentType.value == 'FULL PAYMENT') {
                                                vm.paymentList.splice(payment.index, 1);
                                            } else if (vm.paymentDetails.paymentType.value == 'DOWN PAYMENT') {
                                                var dp = vm.paymentDetails.totalBalance.replace(/[^\d.]/g, '') * (vm.downPayment / 100),
                                                    am = vm.paymentList[payment.index].invoice.dblRemainingBalance - dp;

                                                vm.paymentList[payment.index].invoice.dblRemainingBalance = am;
                                                vm.paymentList[payment.index].invoice.paymentType = 'DOWN PAYMENT';
                                            } else if (vm.paymentDetails.paymentType.value == 'COMPLEMENTARY PAYMENT') {
                                                if (vm.paymentList[payment.index].invoice.dblRemainingBalance > vm.paymentDetails.paymentAmount.replace(/[^\d.]/g, '')) {
                                                    $('#paymentModal').closeModal();
                                                    var am = vm.paymentList[payment.index].invoice.dblRemainingBalance - vm.paymentDetails.paymentAmount.replace(/[^\d.]/g, '');
                                                } else if (vm.paymentDetails.paymentAmount.replace(/[^\d.]/g, '') > vm.paymentList[payment.index].invoice.dblRemainingBalance) {
                                                    $('#paymentModal').closeModal();
                                                    vm.paymentList.splice(payment.index, 1);
                                                    var am = vm.paymentDetails.paymentAmount.replace(/[^\d.]/g, '') - vm.paymentList[payment.index].invoice.dblRemainingBalance;
                                                } else if (vm.paymentDetails.paymentAmount.replace(/[^\d.]/g, '') == vm.paymentList[payment.index].invoice.dblRemainingBalance) {
                                                    $('#paymentModal').closeModal();
                                                    vm.paymentList.splice(payment.index, 1);
                                                    var am = vm.paymentList[payment.index].invoice.dblRemainingBalance - vm.paymentDetails.paymentAmount.replace(/[^\d.]/g, '');
                                                }
                                                vm.paymentList[payment.index].invoice.dblRemainingBalance = am;
                                                vm.paymentList[payment.index].invoice.paymentType = 'COMPLEMENTARY PAYMENT';
                                            }
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
            } else {

            }
        }

        vm.openReceipt = function(url) {

            var data = {"path":url};

            $http({
                method: 'get',
                url: 'http://localhost:8080/SalonManagement/open?path='+url,
                dataType: 'json',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                $window.open(data);
                console.log(typeof data);

            }, function errorCallback(response) {
                console.log(response);
            });
        }
    }
})();
