(function () {
    'use strict';

    angular
        .module('app')
        .controller('walkinCtrl', walkinCtrl);

    function walkinCtrl($scope, $http, $window, $timeout, paymentFactory, locationFactory, walkinFactory, SweetAlert) {
        var vm = this;

        vm.openEditItem = openEditItem;
        vm.openPackageModal = openPackageModal;
        vm.assignEmployeePackage = assignEmployeePackage;
        vm.openPromoModal = openPromoModal;
        vm.closeCard = closeCard;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.dtInstanceCallbackAppointment = dtInstanceCallbackAppointment;
        vm.searchTable = searchTable;
        vm.completePendingFunc = completePendingFunc;
        vm.selDiscountDetails = selDiscountDetails;
        vm.removeToCartProduct = removeToCartProduct;
        vm.removeToCartService = removeToCartService;
        vm.removeToCartPackage = removeToCartPackage;
        vm.removeToCartPromo = removeToCartPromo;
        vm.filterEmployee = filterEmployee;
        vm.closeNotSelectedInProd = closeNotSelectedInProd;
        vm.selEmpServ = selEmpServ;
        vm.filterEmployeeInUpdate = filterEmployeeInUpdate;

        vm.editWalkin = editWalkin;
        vm.editInCart = editInCart;
        vm.saveUpdateWalkin = saveUpdateWalkin;

        vm.createWalkinOpen = createWalkinOpen;

        vm.selected = 'product';
        vm.walkinTableFilter = 'walkin';

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

        vm.packageID = '';
        vm.packageObject = [];

        vm.promoList = {};

        vm.selServiceDetails = [];

        vm.selPackageDetails = [];

        vm.searchInTable = '';
        vm.completePendingFilter = '';

        vm.walkinProdSelected = [];

        vm.count = 0;
        vm.discountApplicability = '';

        vm.sum = 0;
        vm.qtyInvalid = 0;

        vm.empSpecialization = [];
        vm.selEmployeeEdit = [];

        vm.loadingBubble = 1;

        function dtInstanceCallback(dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function dtInstanceCallbackAppointment(dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstanceAppointment = datatableObj;
        }

        function searchTable() {
            var query1 = vm.searchInTable;
            vm.tableInstance.search(query1).draw();
            vm.tableInstanceAppointment.search(query1).draw();
        }

        function completePendingFunc() {
            if (vm.walkinTableFilter == 'walkin') {
                var sel1 = vm.completePendingFilter;
                if (sel1 == 'pending' || sel1 == 'complete') {
                    vm.tableInstance.column(3).search('^' + sel1 + '$', true).draw();
                } else if (sel1 == '') {
                    vm.tableInstance.column(3).search(sel1).draw();
                }
            } else {
                var sel2 = vm.completePendingFilter;
                if (sel2 == 'pending' || sel2 == 'complete') {
                    vm.tableInstanceAppointment.column(5).search('^' + sel2 + '$', true).draw();
                } else if (sel2 == '') {
                    vm.tableInstanceAppointment.column(5).search(sel2).draw();
                }
            }
        }

        locationFactory.getExtraCharges().then(function (data) {
            vm.extraChargeList = data.data.extraChargeList;
        });

        locationFactory.getDiscounts().then(function (data) {
            vm.discountList = data.data.discountList;
        });

        locationFactory.getProducts().then(function (data) {
            vm.productList = data.data.productList;
        });

        locationFactory.getDiscounts().then(function (data) {
            vm.discountList = data.data.discountList;
        });

        locationFactory.getWalkin().then(function (data) {
            vm.walkinList = data.walkInList;
            console.log(vm.walkinList);
        });

        function createWalkinOpen() {
            $('#createWalkinModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });

            vm.selDiscounts = '';
            vm.count = 0;
        }

        var serviceTypeData = $.param({
            'type': 'walkin'
        });

        $http({
            method: 'post',
            url: 'http://localhost:8080/SalonManagement/getServiceByType',
            data: serviceTypeData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            vm.serviceList = data.data.serviceList;
        }, function errorCallback(response) {
            console.log(response);
        });

        var packageTypeData = $.param({
            'type': 'walkin'
        });

        $http({
            method: 'post',
            url: 'http://localhost:8080/SalonManagement/getPackageByType',
            data: packageTypeData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            vm.packageList = data.data.packageList;
        }, function errorCallback(response) {
            console.log(response);
        });

        var promoTypeData = $.param({
            'type': 'walkin'
        });

        $http({
            method: 'post',
            url: 'http://localhost:8080/SalonManagement/getPromoByType',
            data: promoTypeData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            vm.promoList = data.data.promoList;
            console.log(vm.promoList);
        }, function errorCallback(response) {
            console.log(response);
        });



        var employeeTypeData = $.param({
            'type': 'walkin'
        });

        $http({
            method: 'post',
            url: 'http://localhost:8080/SalonManagement/getAvailableEmployee',
            data: employeeTypeData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(data) {
            vm.employeeList = data.data.empList;
            console.log(vm.employeeList);

        }, function errorCallback(response) {
            console.log(response);
        });

        function filterEmployeeInUpdate(index, newService) {
            var f = [];
            console.log(newService);
            vm.upServiceStrName = newService.service.strServiceCategory;
            for (var i = 0; i < vm.employeeList.length; i++) {
                for (var j = 0; j < vm.employeeList[i].specialization.length; j++) {
                    if (vm.upServiceStrName.toLowerCase() == vm.employeeList[i].specialization[j].name.toLowerCase()) {
                        console.log(vm.upServiceStrName + '///' + vm.employeeList[i].specialization[j].name);
                        f.push(vm.employeeList[i]);
                    }
                }
            }
            vm.upFilteredEmpForService = f;

        }

        function filterEmployee(index, id) {
            var f = [];
            vm.serviceStrName = vm.serviceList[index].strServiceCategory;
            for (var i = 0; i < vm.employeeList.length; i++) {
                for (var j = 0; j < vm.employeeList[i].specialization.length; j++) {
                    if (vm.serviceStrName.toLowerCase() == vm.employeeList[i].specialization[j].name.toLowerCase()) {
                        f.push(vm.employeeList[i]);
                    }
                }
            }
            vm.filteredEmpForService = f;
            var servClose1 = 'servClose' + index;
            $('.servReveal i:not(.' + servClose1 + ')').click();

        }

        function closeNotSelectedInProd(index) {
            var prodClose1 = 'prodClose' + index;
            $('.prodClose123 i:not(.' + prodClose1 + ')').click();
        }

        function selEmpServ(se) {
            var selID = se.selectedEmployee;
            for (var i = 0; i < vm.filteredEmpForService.length; i++) {
                if (selID == vm.filteredEmpForService[i].intEmpID) {
                    vm.selEmployeeEdit = vm.filteredEmpForService[i];
                    console.log(selID + '///' + vm.filteredEmpForService[i].intEmpID);
                }
            }
        }

        function selDiscountDetails(discount) {
            vm.count = 1;
            var require = [];
            console.log(discount);
            angular.forEach(discount.requirements, function (req) {
                require.push(req.strRequirementName);
            });
            vm.requirement = _.uniq(require);

            vm.discountDetail = discount;

            var discountByIDData = $.param({
                'intDiscountID': discount.intDiscountID
            });

            $http({
                method: 'post',
                url: 'http://localhost:8080/SalonManagement/getDiscountByID',
                data: discountByIDData,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                var discount = data.data.discount;
                console.log(discount);
                if (discount.applicability == "TOTAL SALES") {
                    vm.sumWithDiscount = vm.sum;
                    var p = 0,
                        s = 0,
                        pck = 0,
                        pr = 0;
                    vm.discountApplicability = 'ALL ITEMS ARE INCLUDED';
                    angular.forEach(vm.productOrder, function (prod) {
                        if (discount.intDiscountType == 1) {
                            p = prod.productTotal * (discount.stringPrice / 100);
                            vm.sumWithDiscount -= p;
                        } else if (discount.intDiscountType == 2) {
                            if (prod.productTotal > discount.stringPrice) {
                                vm.sumWithDiscount -= discount.stringPrice;
                            } else if (prod.productTotal < discount.stringPrice) {
                                vm.sumWithDiscount -= prod.productTotal;
                            }
                        }
                    });
                    angular.forEach(vm.serviceOrder, function (serv) {
                        if (discount.intDiscountType == 1) {
                            s = serv.serviceTotal * (discount.stringPrice / 100);
                            vm.sumWithDiscount -= s;
                        } else if (discount.intDiscountType == 2) {
                            if (serv.serviceTotal > discount.stringPrice) {
                                vm.sumWithDiscount -= discount.stringPrice;
                            } else if (serv.serviceTotal < discount.stringPrice) {
                                vm.sumWithDiscount -= serv.serviceTotal;
                            }
                        }
                    });
                    angular.forEach(vm.packageOrder, function (pack) {
                        if (discount.intDiscountType == 1) {
                            pck = pack.packageTotal * (discount.stringPrice / 100);
                            vm.sumWithDiscount -= pck;
                        } else if (discount.intDiscountType == 2) {
                            if (pack.packageTotal > discount.stringPrice) {
                                vm.sumWithDiscount -= discount.stringPrice;
                            } else if (pack.packageTotal < discount.stringPrice) {
                                vm.sumWithDiscount -= pack.packageTotal;
                            }
                        }
                    });
                } else if (discount.applicability == "SELECTED ITEM") {
                    vm.sumWithDiscount = vm.sum;
                    vm.discountApplicability = '';
                    var product = [],
                        service = [],
                        packageItem = [],
                        promoItem = [];
                    angular.forEach(discount.productList, function (prod) {
                        product.push(prod);
                    });
                    angular.forEach(discount.serviceList, function (serv) {
                        service.push(serv);
                    });
                    angular.forEach(discount.packageList, function (pack) {
                        packageItem.push(pack);
                    });
                    angular.forEach(discount.promoList, function (promo) {
                        promoItem.push(promo);
                    });
                    vm.prodInDiscount = product;
                    vm.servInDiscount = service;
                    vm.packInDiscount = packageItem;
                    vm.promoInDiscount = promoItem;

                    var p = 0,
                        s = 0,
                        pck = 0;

                    angular.forEach(product, function (prod) {
                        angular.forEach(vm.productOrder, function (po) {
                            if (prod.intProductID == po.productID) {
                                if (discount.intDiscountType == 1) {
                                    p = po.productTotal * (discount.stringPrice / 100);
                                    vm.sumWithDiscount -= p;
                                } else if (discount.intDiscountType == 2) {
                                    if (po.productTotal > discount.stringPrice) {
                                        vm.sumWithDiscount -= discount.stringPrice;
                                    } else if (po.productTotal < discount.stringPrice) {
                                        vm.sumWithDiscount -= po.productTotal;
                                    }
                                }
                            }
                        });
                    });

                    angular.forEach(service, function (serv) {
                        angular.forEach(vm.serviceOrder, function (se) {
                            if (serv.intServiceID == se.serviceID) {
                                if (discount.intDiscountType == 1) {
                                    s = se.serviceTotal * (discount.stringPrice / 100);
                                    vm.sumWithDiscount -= p;
                                } else if (discount.intDiscountType == 2) {
                                    if (se.serviceTotal > discount.stringPrice) {
                                        vm.sumWithDiscount -= discount.stringPrice;
                                    } else if (se.serviceTotal < discount.stringPrice) {
                                        vm.sumWithDiscount -= se.serviceTotal;
                                    }
                                }
                            }
                        });
                    });

                    angular.forEach(packageItem, function (pack) {
                        angular.forEach(vm.packageOrder, function (pa) {
                            if (pack.intPackageID == pa.packageID) {
                                if (discount.intDiscountType == 1) {
                                    pck = pa.packageTotal * (discount.stringPrice / 100);
                                    vm.sumWithDiscount -= p;
                                } else if (discount.intDiscountType == 2) {
                                    if (pa.packageTotal > discount.stringPrice) {
                                        vm.sumWithDiscount -= discount.stringPrice;
                                    } else if (pa.packageTotal < discount.stringPrice) {
                                        vm.sumWithDiscount -= pa.packageTotal;
                                    }
                                }
                            }
                        });
                    });

                    product = [];
                    service = [];
                    packageItem = [];
                    promoItem = [];
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
        }

        function editWalkin(walkin) {
            var data = $.param({
                'intWalkInID': walkin.intWalkInID
            });

            $http({
                method: 'post',
                url: 'http://localhost:8080/SalonManagement/getWalkInByID',
                data: data,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                var walkin = data.data.walkin;
                vm.infoUpdateWalkin = (walkin);
                console.log(walkin);
                vm.walkinProdSelected = walkin.products;
                vm.walkinServSelected = walkin.services;
                vm.selectedProductFromWalkin = pushProduct(vm.walkinProdSelected);
                console.log(vm.selectedProductFromWalkin);
                vm.selectedServiceFromWalkin = pushService(vm.walkinServSelected);
                console.log(vm.selectedServiceFromWalkin);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });

            $('#editWalkin').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
        }

        $scope.editAppointment = function(walkin) {
            var data = $.param({
                'intWalkInID': walkin.intWalkInID
            });
            console.log("Edit Appointnemnt!");
            $http({
                method: 'post',
                url: 'http://localhost:8080/SalonManagement/getWalkInByID',
                data: data,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                var walkin = data.data.walkin;
                vm.infoUpdateWalkin = (walkin);
                console.log(walkin);
                vm.walkinProdSelected = walkin.products;
                vm.walkinServSelected = walkin.services;
                vm.selectedProductFromWalkin = pushProduct(vm.walkinProdSelected);
                console.log("Selected Servicios" + vm.selectedProductFromWalkin);
                vm.selectedServiceFromWalkin = pushService(vm.walkinServSelected);
                console.log(vm.selectedServiceFromWalkin);
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                console.log("failed to fetch");
            });

            $('#editAppointment').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
        }

        function pushProduct(products) {
            var p = [];
            console.log(products);
            angular.forEach(products, function (prod) {
                // p.push({
                //     'intProductWalkInID': prod.intProductWalkInID,
                //     'intQuantity': prod.intQuantity
                // });
                for (var i = 0; i < vm.productList.length; i++) {
                    if (prod.product.intProductID == vm.productList[i].intProductID) {
                        p.push({
                            'prodID': vm.productList[i].intProductID,
                            'prodName': vm.productList[i].strProductName,
                            'prodqty': prod.intQuantity,
                            'prodprice': vm.productList[i].stringPrice
                        });
                    }
                }
            });
            console.log(p);
            return p;
        }

        function pushService(service) {
            var p = [];
            angular.forEach(service, function (serv) {
                // p.push({
                //     'intProductWalkInID': prod.intProductWalkInID,
                //     'intQuantity': prod.intQuantity
                // });
                for (var i = 0; i < vm.serviceList.length; i++) {
                    if (serv.service.intServiceID == vm.serviceList[i].intServiceID) {
                        p.push({
                            'servID': vm.serviceList[i].intServiceID,
                            'servName': vm.serviceList[i].strServiceName,
                            'employeeAssigned': serv.employeeAssigned
                        });
                    }
                }
            });
            return p;
        }

        $scope.addProduct = function (product) {
            vm.selectedProductFromWalkin.push({
                'prodID': product.product.intProductID,
                'prodName': product.product.strProductName,
                'prodqty': product.qty,
                'prodprice': product.product.stringPrice
            });
            console.log(product);
            console.log(vm.selectedProductFromWalkin);
        };

        $scope.addService = function (service) {
            $('#addServiceModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            $scope.serviceInsideModal = service;
        };

        $scope.addServiceInTable = function (service) {
            vm.selectedServiceFromWalkin.push({
                'servID': service.service.intServiceID,
                'servName': service.service.strServiceName,
                'employeeAssigned': service.selEmployee,
                'servPrice': service.service.stringPrice
            });
            console.log(service);
            $('#addServiceModal').closeModal();
        };

        $scope.removeFromProductList = function (index) {
            vm.selectedProductFromWalkin.splice(index, 1);
        };

        $scope.removeFromServiceList = function (index) {
            vm.selectedServiceFromWalkin.splice(index, 1);
        };

        function editInCart(order) {
            var order = order;
            $('#editItem').closeModal();
            if (order.type == 'product') {
                var po = vm.productOrder[order.index],
                    p = ({
                        product: order.product,
                        productID: order.productID,
                        productQuantity: order.productQuantity,
                        productTotal: order.productPrice * order.productQuantity,
                        productPrice: order.productPrice,
                        strPhotoPath: order.strPhotoPath,
                        type: 'product'
                    });
                vm.productOrder[order.index] = p;
                vm.productTotal = order.productPrice * order.productQuantity;
                vm.productTotal -= po.productTotal;
                vm.productTotal += order.productPrice * order.productQuantity;
                vm.sum -= po.productTotal;
                vm.sum += order.productPrice * order.productQuantity;

                var quant = quantprod.split(',');
                quant[order.index] = order.productQuantity;
                quantprod = quant.join(',');
            } else if (order.type == 'service') {
                var e = selectEmp.split(',');
                e[order.index] = vm.selEmployeeEdit.intEmpID;
                selectEmp = e.join(',');
                vm.serviceOrder[order.index].selectedEmployee = e[order.index];
            }

        }

        function openEditItem(index, item) {
            $('#editItem').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200 // Transition out duration
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

                console.log(item.selectedEmployee);
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

        function removeToCartProduct(index, orders) {
            console.log('index: ' + index);
            vm.productOrder.splice(index, 1);

            var ppp = selectprod.split(',');
            ppp.splice(index, 1);
            selectprod = ppp.join(',');

            var qqq = quantprod.split(',');
            qqq.splice(index, 1);
            quantprod = qqq.join(',');

            vm.sum -= orders.productTotal;
        }

        function removeToCartService(index, service) {
            console.log('index: ' + index);
            vm.serviceOrder.splice(index, 1);

            var sss = selectserv.split(',');
            sss.splice(index, 1);
            selectserv = sss.join(',');

            var eee = selectEmp.split(',');
            eee.splice(index, 1);
            selectEmp = eee.join(',');

            vm.sum -= service.serviceTotal;

            console.log(selectserv + '///' + selectEmp);
        }

        function removeToCartPackage(index, pack) {
            vm.packageOrder.splice(index, 1);
            vm.sum -= pack.packageTotal;
        }
        
        function removeToCartPromo(index, promo) {
            vm.promoOrder.splice(index, 1);
            vm.sum -= promo.promoTotal;
        }

        vm.addToCart = function (index, selected) {
            console.log(index);
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
                    type: 'service',
                    idx: index
                });

                vm.selServiceDetails.push({
                    intServiceID: vm.serviceList[index].intServiceID,
                    intQuantity: '1',
                    intEmployeeID: vm.selEmployee.intEmpID,
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
                vm.selEmployee = null;
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

                vm.packageObject.push({'intPackageID':vm.packageList[index].intPackageID,'serviceList':vm.selPackageDetails});

                selectpack = selectedPackage;
                vm.packageTotal = subTotalPackage;
                vm.sum += vm.packageTotal;
                vm.quantity = '';
                vm.selPackageDetails = [];
                console.log("PackageList: " + JSON.stringify(vm.packageObject));
            } else if (selected == 'promo') {
                console.log(index);
                $('#promoListModal').closeModal();

                vm.assignEmployeePromo = function (index) {

                    vm.selPromoDetails = [];
                    vm.selPromoDetails.push({
                        intServiceID: vm.promoContainsService[index].service.intServiceID,
                        intQuantity: 1,
                        intEmployeeID: vm.selEmployeePerService.intEmpID,
                        strStatus: 'pending'
                    });
                    console.log(vm.selPromoDetails);
                };
                console.log(index);
                var promoName = vm.promoList[index].strPromoName;
                var promoID = vm.promoList[index].strPromoName;
                var promoPrice = vm.promoList[index].dblPromoPrice;
                vm.promoOrder.push({
                    promo: promoName,
                    promoID: promoID,
                    promoQuantity: 1,
                    promoTotal: promoPrice
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

        function assignEmployeePackage(index) {
            vm.selPackageDetails.push({
                'intServiceID': vm.packageContainService[index].service.intServiceID,
                'intQuantity': 1,
                'intEmployeeID': vm.selEmployeePerService.intEmpID,
                'strStatus': 'PENDING'
            });

        }

        function openPromoModal(index, promo) {
            $('#promoListModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            var id = $.param({
                'intPromoID': vm.promoList[index].intPromoID
            });
            $http({
                method: 'post',
                url: 'http://localhost:8080/SalonManagement/getPromoByID',
                data: id,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data){
                  vm.promoContainsProduct = data.data.promo.productList;
                  vm.promoContainsService  = data.data.promo.serviceList;                
            }, function errorCallback(response) {
                console.log(response);
            });
            //vm.promoContainsPackage = getServiceInPackage(promo.packageList);
            vm.promoIndex = index;
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

        function openPackageModal(index, contains, packageID) {
            $('#packageListModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            vm.packageID = '';
            vm.packageID = packageID;
            vm.packageContainService = vm.packageList[index].serviceList;
            vm.packageContainProduct = vm.packageList[index].productList;
            vm.packageIndex = index;
        }

        vm.sumTotal = function () {
            var summed = vm.productTotal + vm.serviceTotal + vm.packageTotal + vm.promoTotal;
            vm.sum = summed;
        };

        function toString() {
            var selectedDiscount = "";
                selectedDiscount += vm.selDiscounts.intDiscountID + ",";
            selectdiscount = selectedDiscount;
            console.log(vm.selDiscounts);
            console.log(selectedDiscount);
        }

        vm.saveWalkin = function (details) {
            vm.loadingBubble = 0;
            toString();
            var packageObj = vm.packageObject;
            
            if(selectdiscount == "undefined,"){
                selectdiscount = "";
            }
            console.log(selectdiscount);
            console.log(packageObj);

            vm.total_price = vm.sum;
//////////////////////////////////////////////////////////
            if(vm.sumWithDiscount > 0) {
                vm.total_price = vm.sumWithDiscount;
            }
/////////////////////////////////////////////////////////
            var walkinData = $.param({
                'productString': selectprod,
                'productQuantity': quantprod,
                'serviceString': selectserv,
                'employeeAssigned': selectEmp,
                'packageLists': JSON.stringify(packageObj),
                'promoLists': JSON.stringify(vm.selPromoDetails),
                'strTotalPrice': parseFloat(vm.total_price).toFixed(2),
                'discounts': selectdiscount,
                'strName': vm.details.name,
                'strContactNo': vm.details.contact
            });
            
            var data = {
                    'productString': selectprod,
                'productQuantity': quantprod,
                'serviceString': selectserv,
                'employeeAssigned': selectEmp,
                'packageList': packageObj,
                'promoList': vm.selPromoDetails,
                'strTotalPrice': parseFloat(vm.sum).toFixed(2),
                'discounts': selectdiscount,
                'strName': vm.details.name,
                'strContactNo': vm.details.contact
            };

            var ppp = {
                'productString': selectprod,
                'productQuantity': quantprod,
                'serviceString': selectserv,
                'employeeAssigned': selectEmp,
                'packageList': packageObj,
                'promoList': vm.selPromoDetails,
                'strTotalPrice': parseFloat(vm.sum).toFixed(2),
                'discounts': selectdiscount,
                'strName': vm.details.name,
                'strContactNo': vm.details.contact
            };

            console.log(walkinData);
            
            var data1 = JSON.stringify(data);
        
            $timeout(function () {
                $http({
                    method: 'post',
                    url: 'http://localhost:8080/SalonManagement/createWalkin',
                    data:walkinData,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(function successCallback(data) {
                    console.log(data1);
                    $('#createWalkinModal').closeModal();
                    SweetAlert.swal("Oops", "Successfulyy Saved!", "success");
                }, function errorCallback(response) {
                    SweetAlert.swal("Oops", "Something went wrong!", "error");
                    vm.loadingBubble = 1;
                });
            }, 1000);
        };

        $scope.acceptAppointment = function(index, walkin) {
            console.log(walkin);
            var dataWalkinStatus = $.param({
                'intAppointmentID': walkin.intWalkInID,
                'status': 'PENDING'
            });

            $http({
                method: 'post',
                url: 'updateWalkInStatus',
                data: dataWalkinStatus,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                vm.walkinList[index].strWalkInStatus = 'PENDING';
            }, function errorCallback(data) {

            });
        };

        $scope.declineAppointment = function(index, walkin) {
            var dataWalkinStatus = $.param({
                'intAppointmentID': walkin.intWalkInID,
                'status': 'REJECTED'
            });

             $http({
                method: 'post',
                url: 'updateWalkInStatus',
                data: dataWalkinStatus,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                vm.walkinList.splice(index, 1);
            }, function errorCallback(data) {

            });
        };

        $scope.cancelAppointment = function(index, walkin) {
            var dataWalkinStatus = $.param({
                'intAppointmentID': walkin.intWalkInID,
                'status': 'CANCELLED'
            });

            $http({
                method: 'post',
                url: 'updateWalkInStatus',
                data: dataWalkinStatus,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                vm.walkinList.splice(index, 1);
            }, function errorCallback(data) {

            });
        };

        $scope.noshowAppointment = function (index, walkin) {
            var dataNoShow = $.param({
                'appointmentID': walkin.intWalkInID
            });

            $http({
                method: 'post',
                url: 'cancelAppointment',
                data: dataNoShow,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                vm.walkinList.splice(index, 1);
                console.log(data);
            }, function errorCallback(data) {

            });
        };


        vm.upTotal = 0;

        function saveUpdateWalkin() {
            var selServ = [],
                selServEmp = [],
                selProd = [],
                prodQty = [],
                totalprod = 0,
                totalserv = 0

            for (var p = 0; p < vm.selectedProductFromWalkin.length; p++) {
                console.log(vm.selectedProductFromWalkin[p]);
                selProd += vm.selectedProductFromWalkin[p].prodID + ',';
                prodQty += vm.selectedProductFromWalkin[p].prodqty + ',';
                totalprod += parseFloat(vm.selectedProductFromWalkin[p].prodqty) * parseFloat(vm.selectedProductFromWalkin[p].prodprice);
            }

            for (var i = 0; i < vm.selectedServiceFromWalkin.length; i++) {
                selServ += vm.selectedServiceFromWalkin[i].servID + ',';
                selServEmp += vm.selectedServiceFromWalkin[i].employeeAssigned.intEmpID + ',';
                totalserv += parseFloat(vm.selectedServiceFromWalkin[i].servPrice);
            }

            vm.upTotal = totalprod + totalserv;

            var dta = $.param({
                'intWalkInID': vm.infoUpdateWalkin.intWalkInID,
                'strName': vm.infoUpdateWalkin.strName,
                'strContactNo': vm.infoUpdateWalkin.strContactNo,
                'productString': selProd,
                'productQuantity': prodQty,
                'serviceString': selServ,
                'employeeAssigned': selServEmp,
                'strTotalPrice': vm.upTotal
            });

            $http({
               method: 'post',
                url: 'updateWalkIn',
                data: dta,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                console.log(data);
                console.log('success');
            }, function errorCallback(response) {
                SweetAlert.swal("Oops", "Something went wrong!", "error");
            });
        }

        $scope.deactivateWalkin = function(id,index) {
            console.log(index);
            var dataID = $.param({
                'appointmentID' : id
           });


            $http({ 
               method: 'post',
                url: 'cancelAppointment',
                data: dataID,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(function successCallback(data) {
                SweetAlert.swal("Success", "Walkin was cancelled Successfully", "success");
                delete vm.walkInList[index];
            }, function errorCallback(response) {
                SweetAlert.swal("Oops", "Something went wrong!", "error"); 
            });
        }

    }
})();
