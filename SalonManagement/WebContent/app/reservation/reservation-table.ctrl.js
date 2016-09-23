(function () {
    'use strict';

    angular
        .module('app')
        .controller('reservationTable', reservationTable);

    function reservationTable($scope, $http, paymentFactory, locationFactory, reservationFactory, $filter, SweetAlert) {
        var vm = this;
        vm.customerDetails = [{}];
        vm.reservationDetails = [{}];
        vm.reservationType = [{
            id: 1,
            type: 'Home Service'
        }, {
            id: 2,
            type: 'Event'
        }];
        var selectprod = "";
        var quantprod = "";
        var selectserv = "";
        var quantserv = "";
        var selectpack = "";
        var quantpack = "";
        var selectprom = "";
        var quantprom = "";
        var selectextra = "";
        var selectdiscount = "";
        var selectemployees = "";

        vm.selected = 'product';
        vm.quantity = '';
        vm.productTotal = 0;
        vm.serviceTotal = 0;
        vm.packageTotal = 0;
        vm.promoTotal = 0;
        vm.productOrder = [{}];
        vm.serviceOrder = [{}];
        vm.promoOrder = [{}];
        vm.packageOrder = [{}];
        vm.productList = [];
        vm.serviceList = [];
        vm.promoList = [];
        vm.packageList = [];
        vm.details = [{}];
        // vm.customerList = reservationFactory.getCustomers();


        vm.details.reservationType = vm.reservationType[0];
        vm.currentTime = new Date();
        vm.month = ['Januar', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
        vm.monthShort = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        vm.weekdaysFull = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
        vm.weekdaysLetter = ['S', 'M', 'T', 'W', 'T', 'F', 'S'];
        vm.today = 'Today';
        vm.clear = 'Clear';
        vm.close = 'Close';
        vm.minDate = $filter('date')(vm.currentTime, "MMMM/d/yyyy");
        vm.onStart = function () {
            console.log('onStart');
        };
        vm.onRender = function () {
            console.log('onRender');
        };
        vm.onOpen = function () {
            console.log('onOpen');
        };
        vm.onClose = function () {
            console.log('onClose');
        };

        vm.changeDatFrom = changeDatFrom;
        vm.changeDatTo = changeDatTo;
        vm.closeCard = closeCard;
        vm.closeService = closeService;
        vm.closePackage = closePackage;
        vm.closePromo = closePromo;

        function changeDatFrom(date) {
            var datFrom = new Date(date);
            console.log(date);

            vm.details.datFrom = $filter('date')(datFrom, "MMMM/d/yyyy");
        }

        function changeDatTo(date) {
            var datTo = new Date(date);

            vm.details.datTo = $filter('date')(datTo, "MMMM/d/yyyy");
        }
        
        vm.data = [];
        locationFactory.getReservations().then(function (data) {
            vm.customerList = data.reservationList;
            for (var i = 0; i < vm.customerList.length; i++) {
                vm.data.push({
                    title: vm.customerList[i].customer.strName,
                    start: vm.customerList[i].datFrom,
                    end: vm.customerList[i].datTo,
                    allDay: false,
                    headcount: vm.customerList[i].headcount,
                    venue: vm.customerList[i].strVenue
                });
            }
            calendarInit(vm.data);
        });

        locationFactory.getEmployees().then(function (data) {
            vm.employeeList = data.data.employeeList;
        });

        locationFactory.getExtraCharges().then(function (data) {
            vm.extraChargeList = data.data.extraChargeList;
        });

        locationFactory.getDiscounts().then(function (data) {
            vm.discountList = data.data.discountList;
        });

        locationFactory.getProducts().then(function (data) {
            vm.productList = data.data.productList;
        });
        locationFactory.getServices().then(function (data) {
            vm.serviceList = data.data.serviceList;
        });

        locationFactory.getPromos().then(function (data) {
            vm.promoList = data.data.promoList;
        });

        locationFactory.getPackages().then(function (data) {
            vm.packageList = data.data.packageList;
        });

        locationFactory.getDiscounts().then(function (data) {
            vm.discountList = data.data.discountList;
        });

        function calendarInit(data){
            console.log(data);
                $('#reservationCalendar').fullCalendar({
                    // put your options and callbacks here
                    events: data,
                    color: 'yellow',   // an option!
                    textColor: 'black', // an option!

                    eventClick: function(calEvent, jsEvent, view) {

                        alert('Event: ' + calEvent.venue);
                        alert('Event: ' + calEvent.headcount);
                        // change the border color just for fun
                        $(this).css('border-color', 'red');
                    },
                    eventMouseover: function( calEvent, event, jsEvent, view){
                        //alert('Event: ' + calEvent.title);
                    }

                })
        }

        function closeCard(id) {
                var prodClose = 'prodClose' + id;
                $('#' + prodClose).click();
        }
        
        function closeService(id) {
            var servClose = 'servClose' + id;
            $('#' + servClose).click();
        }

        function closePackage(id) {
            var packClose = 'packClose' + id;
            $('#' + packClose).click();
        }

        function closePromo(id) {
            var promoClose = 'promoClose' + id;
            $('#' + promoClose).click();
        }

        vm.addToCart = function (index, selected) {
            if (selected == 'product') {
                vm.productOrder.push({
                    product: vm.productList[index].strProductName,
                    productID: vm.productList[index].intProductID,
                    productQuantity: vm.quantity,
                    productTotal: vm.productList[index].dblProductPrice * vm.quantity,
                    photo: vm.productList[index].strPhotoPath
                });
                var selectedProducts = "";
                var selectedProductQuantity = "";
                var subTotalProducts = 0;
                for (var i = 1; i < vm.productOrder.length; i++) {
                    selectedProducts += vm.productOrder[i].productID + ",";
                    selectedProductQuantity += vm.productOrder[i].productQuantity + ",";
                    subTotalProducts += vm.productOrder[i].productTotal;

                }
                selectprod = selectedProducts;
                quantprod = selectedProductQuantity;
                vm.productTotal = subTotalProducts;
                vm.quantity = '';

            } else if (selected == 'service') {
                vm.serviceOrder.push({
                    service: vm.serviceList[index].strServiceName,
                    serviceID: vm.serviceList[index].intServiceID,
                    serviceQuantity: vm.quantity,
                    serviceTotal: vm.serviceList[index].dblServicePrice * vm.quantity,
                    photo: vm.serviceList[index].strPhotoPath
                });
                var selectedService = "";
                var selectedServiceQuantity = "";
                var subTotalService = 0;
                for (var i = 1; i < vm.serviceOrder.length; i++) {
                    selectedService += vm.serviceOrder[i].serviceID + ",";
                    selectedServiceQuantity += vm.serviceOrder[i].serviceQuantity + ",";
                    subTotalService += vm.serviceOrder[i].serviceTotal;
                }
                selectserv = selectedService;
                quantserv = selectedServiceQuantity;
                vm.serviceTotal = subTotalService;
                vm.quantity = '';
            } else if (selected == 'package') {
                vm.packageOrder.push({
                    package: vm.packageList[index].strPackageName,
                    packageID: vm.packageList[index].intPackageID,
                    packageQuantity: vm.quantity,
                    packageTotal: vm.packageList[index].dblPackagePrice * vm.quantity
                });
                var selectedPackage = "";
                var selectedPackageQuantity = "";
                var subTotalPackage = 0;
                for (var i = 1; i < vm.packageOrder.length; i++) {
                    selectedPackage += vm.packageOrder[i].packageID + ",";
                    selectedPackageQuantity += vm.packageOrder[i].packageQuantity + ",";
                    subTotalPackage += vm.packageOrder[i].packageTotal;

                }
                selectpack = selectedPackage;
                quantpack = selectedPackageQuantity;
                vm.packageTotal = subTotalPackage;
                vm.quantity = '';
            } else if (selected == 'promo') {
                vm.promoOrder.push({
                    promo: vm.promoList[index].strPromoName,
                    promoID: vm.promoList[index].intPromoID,
                    promoQuantity: vm.quantity,
                    promoTotal: vm.promoList[index].dblPromoPrice * vm.quantity
                });
                var selectedPromo = "";
                var selectedPromoQuantity = "";
                var subTotalPromo = 0;
                for (var i = 1; i < vm.promoOrder.length; i++) {
                    selectedPromo += vm.promoOrder[i].promoID + ",";
                    selectedPromoQuantity += vm.promoOrder[i].promoQuantity + ",";
                    subTotalPromo += vm.promoOrder[i].promoTotal;

                }
                selectprom = selectedPromo;
                quantprom = selectedPromoQuantity;
                vm.promoTotal = subTotalPromo;
                vm.quantity = '';
            }
        };

        vm.sumTotal = function () {
            var summed = vm.productTotal + vm.serviceTotal + vm.packageTotal + vm.promoTotal;
            vm.sum = summed;
            console.log(summed);
        };

        function toString() {
            var selectedExtra = "";
            var selectedDiscount = "";
            var selectedEmployeee = "";
            for (var i = 0; i < vm.extraChargeList.length; i++) {
                selectedExtra += vm.extraChargeList[i].intECID + ",";
            }
            for (var i = 0; i < vm.selDiscounts.length; i++) {
                selectedDiscount += vm.selDiscounts[i].intDiscountID + ",";
            }
            for (var i = 0; i < vm.selEmployees.length; i++) {
                selectedEmployeee += vm.selEmployees[i].intEmpID + ",";
            }
            console.log(vm.selEmployees);
            selectextra = selectedExtra;
            selectdiscount = selectedDiscount;
            selectemployees = selectedEmployeee;
        }


        vm.saveReservation = function (details) {
            toString();
            var name = vm.details.name;
            var address = vm.details.address;
            var contact = vm.details.contact;
            var email = vm.details.email;
            var reservationType = vm.details.reservationType.id; //Kukunin ko palang from view
            var datFrom = vm.details.datFrom;
            var datTo = vm.details.datTo;
            var timFrom = vm.details.timeFrom;
            var timTo = vm.details.timeTo;
            var venue = vm.details.venue;
            var headCount = vm.details.headCount;
            var total = vm.sum;

            var reservationData = ({
                "strName": vm.details.name,
                "strAddress": vm.details.address,
                "strContactNo": vm.details.contact,
                "strEmail": vm.details.email,
                "intReservationType": reservationType,
                "datFrom": datFrom,
                "datTo": datTo,
                "timFrom": timFrom,
                "timTo": timTo,
                "strVenue": venue,
                "headCount": headCount,
                "selectedProducts": selectprod,
                "selectedServices": selectserv,
                "selectedPackages": selectpack,
                "selectedPromos": selectprom,
                "productQuantity": quantprod,
                "serviceQuantity": quantserv,
                "packageQuantity": quantpack,
                "promoQuantity": quantprom,
                "selectedEmployees": selectemployees,
                "selectedExtraCharges": selectextra,
                "selectedDiscounts": selectdiscount,
                "strTotalPrice": total
            });
            console.log(reservationData);

            swal({
                    title: "Create reservation for " + name + "?",
                    text: "",
                    type: "",
                    confirmButtonColor: "#81d4fa",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    setTimeout(function () {
                        $.ajax({
                            url: 'createReservation',
                            type: 'post',
                            data: reservationData,
                            dataType: 'json',
                            async: true,
                            success: function (data) {

                                var dd = $.param({
                                	'fineName': data.path
                                });
                                console.log(data);

                                $http({
                                    method: 'post',
                                    url: 'http://localhost:8080/SalonManagement/downloadReports',
                                    data: dd,
                                    headers: {
                                        'Content-Type': 'application/x-www-form-urlencoded'
                                    }
                                }).then(function successCallback(data) {
                                	alert("YES");
                                }, function errorCallback(response) {
                                	alert("NO");
                                });
                                SweetAlert.swal("Successfully created!", ".", "success");
                                $('#createReservationModal').closeModal();
                                if(reservationType == 1) {
                                    vm.customerList.push({
                                        "customer": {
                                            "strName": vm.details.name,
                                            "strAddress": vm.details.address,
                                            "strContactNo": vm.details.contact,
                                            "strEmail": vm.details.email
                                        },
                                        "intReservationType": reservationType,
                                        "datFrom": datFrom,
                                        "datTo": datTo,
                                        "timFrom": timFrom,
                                        "timTo": timTo,
                                        "strVenue": venue,
                                        "headCount": headCount,
                                        "selectedProducts": selectprod,
                                        "selectedServices": selectserv,
                                        "selectedPackages": selectpack,
                                        "selectedPromos": selectprom,
                                        "productQuantity": quantprod,
                                        "serviceQuantity": quantserv,
                                        "packageQuantity": quantpack,
                                        "promoQuantity": quantprom,
                                        "selectedEmployees": selectemployees,
                                        "selectedExtraCharges": selectextra,
                                        "selectedDiscounts": selectdiscount,
                                        "strTotalPrice": total,
                                        "strStatus": 'REQUEST'
                                    });
                                    
                                } else {
                                    vm.customerList.push({
                                        "customer": {
                                            "strName": vm.details.name,
                                            "strAddress": vm.details.address,
                                            "strContactNo": vm.details.contact,
                                            "strEmail": vm.details.email
                                        },
                                        "intReservationType": reservationType,
                                        "datFrom": datFrom,
                                        "datTo": datTo,
                                        "timFrom": timFrom,
                                        "timTo": timTo,
                                        "strVenue": venue,
                                        "headCount": headCount,
                                        "selectedProducts": selectprod,
                                        "selectedServices": selectserv,
                                        "selectedPackages": selectpack,
                                        "selectedPromos": selectprom,
                                        "productQuantity": quantprod,
                                        "serviceQuantity": quantserv,
                                        "packageQuantity": quantpack,
                                        "promoQuantity": quantprom,
                                        "selectedEmployees": selectemployees,
                                        "selectedExtraCharges": selectextra,
                                        "selectedDiscounts": selectdiscount,
                                        "strTotalPrice": total,
                                        "strStatus": 'PENDING'
                                    });
                                }
                                
                            },
                            error: function () {
                                SweetAlert.swal("Oops", "Something went wrong!", "error");
                            }
                        });
                    }, 1000);
                });
            // reservationFactory.insertCustomer(name, address, contact, email, reservationType,
            //     datFrom, datTo, timFrom, timTo, venue,
            //     headCount, selectemployees, selectprod, selectserv, selectpack,
            //     selectprom, quantprod, quantserv, quantpack, quantprom,
            //     selectextra, selectdiscount, selectemployees, total);
        };
    }
})();







