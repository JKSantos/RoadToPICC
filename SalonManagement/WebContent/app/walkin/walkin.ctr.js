(function () {
    'use strict';

    angular
        .module('app')
        .controller('walkinCtrl', walkinCtrl);

    function walkinCtrl($scope, $http, $window, paymentFactory, locationFactory, walkinFactory, SweetAlert) {
        var vm = this;
        vm.selected = 'product';
        vm.selEmployee = '';

        var selectprod = "";
        var quantprod = "";
        var selectserv = "";
        var quantserv = "";
        var selectpack = "";
        var quantpack = "";
        var selectprom = "";
        var quantprom = "";
        var selectdiscount = "";
        var selectEmp = "";

        vm.selDiscounts = [];
        vm.quantity = '';
        vm.productTotal = 0;
        vm.serviceTotal = 0;
        vm.packageTotal = 0;
        vm.promoTotal = 0;

        vm.productOrder = [];
        vm.serviceOrder = [];
        vm.promoOrder = [];
        vm.packageOrder = [];

        vm.details = [{}];

        vm.customerList = walkinFactory.getCustomers();
        vm.packageList = {};
        vm.promoList = {};

        vm.selServiceDetails = [];

        vm.openEditItem = openEditItem;
        vm.openPackageModal = openPackageModal;
        vm.assignEmployeePackage = assignEmployeePackage;
        vm.openPromoModal = openPromoModal;
        vm.closeCard = closeCard;


        locationFactory.getEmployees().then(function (data) {
            vm.employeeList = data.data.employeeList;
            vm.selEmployee = vm.employeeList[0];
            vm.selEmployeePerService = vm.employeeList[0];
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

        locationFactory.getPromosWithDetails().then(function (data) {
            vm.promoList = data.data.promoList;
            console.log(data.data);
        });

        locationFactory.getPackagesWithDetails().then(function (data) {
            vm.packageList = data.data.packageList;
        });

        locationFactory.getDiscounts().then(function (data) {
            vm.discountList = data.data.discountList;
        });

        locationFactory.getWalkin().then(function(data) {
           vm.walkinList = data.walkInList;
        });

        function openEditItem(index, item) {
            $('#editItem').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            if (item.type == 'product') {
                vm.orderToBeEdit = {
                    product: item.product,
                    productID: item.productID,
                    productQuantity: item.productQuantity,
                    productTotal: item.productTotal,
                    productPrice: item.productPrice,
                    strPhotoPath: item.strPhotoPath,
                    type: item.type,
                    index: index
                };
            } else if (item.type == 'service') {
                vm.orderToBeEdit = {
                    service: item.service,
                    serviceID: item.serviceID,
                    serviceQuantity: item.serviceQuantity,
                    serviceTotal: item.serviceTotal,
                    servicePrice: item.servicePrice,
                    strPhotoPath: item.strPhotoPath,
                    serviceEmployee: item.serviceEmployee,
                    selectedEmployee: item.selectedEmployee,
                    type: item.type,
                    index: index
                };
            }

        }

        function closeCard(id, sel) {
            if (sel == 'product') {
                var prodClose = 'prodClose' + id;
                $('#' + prodClose).click();
            } else if (sel == 'service') {
                var servClose = 'servClose' + id;
                $('#' + servClose).click();
            }
        }

        vm.addToCart = function (index, selected) {
            var selectedProducts = "";
            var selectedProductQuantity = "";
            var subTotalProducts = 0;
            if (selected == 'product') {

                vm.productOrder.push({
                    product: vm.productList[index].strProductName,
                    productID: vm.productList[index].intProductID,
                    productQuantity: vm.quantity,
                    productTotal: vm.productList[index].dblProductPrice * vm.quantity,
                    productPrice: vm.productList[index].dblProductPrice,
                    strPhotoPath: vm.productList[index].strPhotoPath,
                    type: 'product'
                });
                for (var x = 0; x < vm.productOrder.length; x++) {
                    selectedProducts += vm.productOrder[x].productID + ",";
                    selectedProductQuantity += vm.productOrder[x].productQuantity + ",";
                    subTotalProducts += vm.productOrder[x].productTotal;
                }

                selectprod = selectedProducts;
                quantprod = selectedProductQuantity;
                vm.productTotal = subTotalProducts;
                vm.quantity = '';

            } else if (selected == 'service') {

                vm.serviceOrder.push({
                    service: vm.serviceList[index].strServiceName,
                    serviceID: vm.serviceList[index].intServiceID,
                    serviceQuantity: 1,
                    serviceTotal: vm.serviceList[index].dblServicePrice,
                    servicePrice: vm.serviceList[index].dblServicePrice,
                    strPhotoPath: vm.serviceList[index].strPhotoPath,
                    serviceEmployee: vm.selEmployee.strEmpFirstName + ' ' + vm.selEmployee.strEmpLastName,
                    selectedEmployee: vm.selEmployee.intEmpID,
                    type: 'service'
                });

                vm.selServiceDetails.push({
                    intServiceID: vm.serviceList[index].intServiceID.toString(),
                    intQuantity: '1',
                    intEmployeeID: vm.selEmployee.intEmpID.toString(),
                    strStatus: 'pending'
                });
                console.log(vm.selServiceDetails);
                var selectedService = "";
                var selectedServiceQuantity = "";
                var selectedEmployee = "";
                var subTotalService = 0;
                for (var i = 0; i < vm.serviceOrder.length; i++) {
                    selectedService += vm.serviceOrder[i].serviceID + ",";
                    selectedServiceQuantity += vm.serviceOrder[i].serviceQuantity + ",";
                    selectedEmployee += vm.serviceOrder[i].selectedEmployee + ",";
                    subTotalService += vm.serviceOrder[i].serviceTotal;
                }
                selectserv = selectedService;
                quantserv = selectedServiceQuantity;
                selectEmp = selectedEmployee;
                vm.serviceTotal = subTotalService;
                vm.quantity = '';
            } else if (selected == 'package') {
                $('#packageListModal').closeModal();

                vm.packageOrder.push({
                    package: vm.packageList[index].strPackageName,
                    packageID: vm.packageList[index].intPackageID,
                    packageQuantity: 1,
                    packageTotal: vm.packageList[index].dblPackagePrice
                });
                var selectedPackage = "";
                var subTotalPackage = 0;
                for (var i = 0; i < vm.packageOrder.length; i++) {
                    selectedPackage += vm.packageOrder[i].packageID + ",";
                    subTotalPackage += vm.packageOrder[i].packageTotal;

                }
                selectpack = selectedPackage;
                vm.packageTotal = subTotalPackage;
                vm.quantity = '';
            } else if (selected == 'promo') {
                $('#promoListModal').closeModal();

                vm.assignEmployeePromo = function (index) {
                    console.log('Ken Pogi');
                    vm.selPromoDetails = [];
                    console.log(vm.promoContains);
                    console.log(index);
                    vm.selPromoDetails.push({
                        intServiceID: vm.promoContains[index].service.intServiceID,
                        intQuantity: 1,
                        intEmployeeID: vm.selEmployeePerService.intEmpID,
                        strStatus: 'pending'
                    });
                    console.log(vm.selPromoDetails);
                };

                vm.promoOrder.push({
                    promo: vm.promoList[index].strPromoName,
                    promoID: vm.promoList[index].strPromoName,
                    promoQuantity: 1,
                    promoTotal: vm.promoList[index].dblPromoPrice
                });
                var selectedPromo = "";
                var selectedPromoQuantity = "";
                var subTotalPromo = 0;
                for (var i = 0; i < vm.promoOrder.length; i++) {
                    selectedPromo += vm.promoOrder[i].promoID + ", ";

                    subTotalPromo += vm.promoOrder[i].promoTotal;

                }
                selectprom = selectedPromo;
                vm.promoTotal = subTotalPromo;
                vm.quantity = '';
            }
        };

        function assignEmployeePackage(index, id) {
            vm.selPackageDetails = [];
            vm.selPackageDetails.push({
                intServiceID: vm.packageContainService[index].service.intServiceID,
                intQuantity: 1,
                intEmployeeID: vm.selEmployeePerService.intEmpID,
                strStatus: 'pending'
            });
            console.log(vm.selPackageDetails);
        }

        function openPromoModal(index, promo) {
            $('#promoListModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            vm.promoContains = vm.promoList[index].serviceList;
            vm.promoContainsPackage = getServiceInPackage(promo.packageList);
        }

        function getServiceInPackage(pack) {
            var p = [];
            angular.forEach(pack, function (item, i) {
                for (var ii = 0; ii < item.pack.serviceList.length; ii++) {
                    p.push(item.pack.serviceList[ii]);
                }
            });
            return p;
        }

        function openPackageModal(index, contains) {
            $('#packageListModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            vm.packageContainService = vm.packageList[index].serviceList;
            vm.packageContainProduct = vm.packageList[index].productList;
            vm.packageIndex = index;
        }

        vm.sumTotal = function () {
            var summed = vm.productTotal + vm.serviceTotal + vm.packageTotal + vm.promoTotal;
            vm.sum = summed;
            console.log(summed);
        };

        function toString() {
            var selectedDiscount = "";
            for (var i = 0; i < vm.selDiscounts.length; i++) {
                selectedDiscount += vm.selDiscounts[i].intDiscountID + ",";
            }
            selectdiscount = selectedDiscount;
        }


        vm.saveWalkin = function (details) {
            toString();
            console.log(selectserv + '//' + selectEmp);
            console.log(selectprod + '/' + quantprod);
            console.log(vm.selServiceDetails);
            var walkinData = {
                'productString': selectprod,
                'productQuantity': quantprod,
                'serviceString': selectserv,
                'employeeAssigned': selectEmp,
                'packageList': vm.selPackageDetails,
                'promoList': vm.selPromoDetails,
                'strTotalPrice': vm.sum,
                'discounts': selectdiscount,
                'strName': vm.details.name,
                'strContactNo': vm.details.contact
            };
            $.ajax({
                url: 'createWalkin',
                type: 'post',
                data: walkinData,
                dataType: 'json',
                async: true,
                success: function (data) {
                    SweetAlert.swal("Successfully created!", ".", "success");
                    $('#createWalkinModal').closeModal();
                    $window.location.reload();
                },
                error: function () {
                    SweetAlert.swal("Oops", "Something went wrong!", "error");
                }
            });


            var total = vm.sum;
            var name = vm.details.name;
            var contact = vm.details.contact;
            var email = vm.details.email;
            var packageDetails = vm.selPackageDetails;
            var promoDetails = vm.selPromoDetails;
            var serviceDetails = vm.selServiceDetails;
            // AINAN WALA YUNG PRODUCT NA PINAPASA. YUNG SELECTPROD. SAN KINUKUHA YON


            // walkinFactory.insertCustomer(name, contact, email, selectEmp,
            //     selectprod, quantprod, packageDetails, promoDetails,
            //     serviceDetails, selectdiscount, total);
        };

        vm.moveToPay = function (id) {
            walkinFactory.moveToPayment(id);

        }
    }
})();







