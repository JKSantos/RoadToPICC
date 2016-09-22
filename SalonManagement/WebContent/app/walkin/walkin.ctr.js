(function () {
        'use strict';

        angular
            .module('app')
            .controller('walkinCtrl', walkinCtrl);

        function walkinCtrl($scope, $http, $window, paymentFactory, locationFactory, walkinFactory, SweetAlert) {
            materialSelect.$inject = ['$timeout'];

            function materialSelect($timeout) {
                var directive = {
                    link: link,
                    restrict: 'E',
                    require: '?ngModel'
                };

                function link(scope, element, attrs, ngModel) {

                    $timeout(create);
                    if (ngModel) {
                        ngModel.$render = create;
                    }

                    function create() {
                        element.material_select();
                    }

                    //if using materialize v0.96.0 use this
                    element.one('$destroy', function () {
                        element.material_select('destroy');
                    });


                }

                return directive;
            }

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

            vm.editWalkin = editWalkin;
            vm.editInCart = editInCart;

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
            // locationFactory.getServices().then(function (data) {
            //     vm.serviceList = data.data.serviceList;
            //     console.log(vm.serviceList);
            // });

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

            $scope.filterEmployee = function(index) {
                console.log(index);
                vm.serviceIntID = vm.serviceList[index].intServiceID;
                console.log(vm.serviceIntID);
                for(var i = 0; i < vm.employeeList[i].specialization.length; i ++) {
                    for(var j = 0; j < vm.employeeList[i].specialization.length; j++) {
                        console.log(vm.serviceIntID + '==' + vm.employeeList[i].specialization[j].id);
                        if(vm.serviceIntID == vm.employeeList[i].specialization[j].id) {
                            console.log(vm.employeeList[i].strEmpFirstName);
                        }
                    }
                }
            };

            locationFactory.getPromosWithDetails().then(function (data) {
                vm.promoList = data.promoList;
            });

            locationFactory.getPackagesWithDetails().then(function (data) {
                vm.packageList = data.data.packageList;
            });

            locationFactory.getDiscounts().then(function (data) {
                vm.discountList = data.data.discountList;
            });

            locationFactory.getWalkin().then(function (data) {
                vm.walkinList = data.walkInList;
            });

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
                    if(discount.applicability == "TOTAL SALES") {
                        vm.sumWithDiscount = vm.sum;
                        var p = 0,
                            s = 0,
                            pck = 0,
                            pr = 0;
                        vm.discountApplicability = 'ALL ITEMS ARE INCLUDED';
                        angular.forEach(vm.productOrder, function(prod) {
                           if(discount.intDiscountType == 1) {
                               p = prod.productTotal * (discount.stringPrice/100);
                               vm.sumWithDiscount -= p;
                           } else if(discount.intDiscountType == 2) {
                               if(prod.productTotal > discount.stringPrice) {
                                   vm.sumWithDiscount -= discount.stringPrice;
                               } else if(prod.productTotal < discount.stringPrice) {
                                   vm.sumWithDiscount -= prod.productTotal;
                               }
                           }
                        });
                        angular.forEach(vm.serviceOrder, function(serv) {
                           if(discount.intDiscountType == 1) {
                               s = serv.serviceTotal * (discount.stringPrice/100);
                               vm.sumWithDiscount -= s;
                           } else if(discount.intDiscountType == 2) {
                               if(serv.serviceTotal > discount.stringPrice) {
                                   vm.sumWithDiscount -= discount.stringPrice;
                               } else if(serv.serviceTotal < discount.stringPrice) {
                                   vm.sumWithDiscount -= serv.serviceTotal;
                               }
                           }
                        });
                        angular.forEach(vm.packageOrder, function(pack) {
                            if(discount.intDiscountType == 1) {
                                pck = pack.packageTotal * (discount.stringPrice/100);
                                vm.sumWithDiscount -= pck;
                            } else if(discount.intDiscountType == 2) {
                                if(pack.packageTotal > discount.stringPrice) {
                                    vm.sumWithDiscount -= discount.stringPrice;
                                } else if(pack.packageTotal < discount.stringPrice) {
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
                        angular.forEach(discount.productList, function(prod) {
                            product.push(prod);
                        });
                        angular.forEach(discount.serviceList, function(serv) {
                            service.push(serv);
                        });
                        angular.forEach(discount.packageList, function(pack) {
                            packageItem.push(pack);
                        });
                        angular.forEach(discount.promoList, function(promo) {
                            promoItem.push(promo);
                        });
                        vm.prodInDiscount = product;
                        vm.servInDiscount = service;
                        vm.packInDiscount = packageItem;
                        vm.promoInDiscount = promoItem;

                        var p = 0,
                            s = 0,
                            pck = 0;

                        angular.forEach(product, function(prod) {
                           angular.forEach(vm.productOrder, function(po) {
                              if(prod.intProductID == po.productID) {
                                  if(discount.intDiscountType == 1) {
                                      p = po.productTotal * (discount.stringPrice/100);
                                      vm.sumWithDiscount -= p;
                                  } else if (discount.intDiscountType == 2) {
                                      if(po.productTotal > discount.stringPrice) {
                                          vm.sumWithDiscount -= discount.stringPrice;
                                      } else if (po.productTotal < discount.stringPrice) {
                                          vm.sumWithDiscount -= po.productTotal;
                                      }
                                  }
                              }
                           });
                        });

                        angular.forEach(service, function(serv) {
                            angular.forEach(vm.serviceOrder, function(se) {
                                if(serv.intServiceID == se.serviceID) {
                                    if(discount.intDiscountType == 1) {
                                        s = se.serviceTotal * (discount.stringPrice/100);
                                        vm.sumWithDiscount -= p;
                                    } else if (discount.intDiscountType == 2) {
                                        if(se.serviceTotal > discount.stringPrice) {
                                            vm.sumWithDiscount -= discount.stringPrice;
                                        } else if (se.serviceTotal < discount.stringPrice) {
                                            vm.sumWithDiscount -= se.serviceTotal;
                                        }
                                    }
                                }
                            });
                        });

                        angular.forEach(packageItem, function(pack) {
                            angular.forEach(vm.packageOrder, function(pa) {
                                if(pack.intPackageID == pa.packageID) {
                                    if(discount.intDiscountType == 1) {
                                        pck = pa.packageTotal * (discount.stringPrice/100);
                                        vm.sumWithDiscount -= p;
                                    } else if (discount.intDiscountType == 2) {
                                        if(pa.packageTotal > discount.stringPrice) {
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
                    console.log('walkin: ' + walkin);
                    vm.walkinProdSelected = walkin.products;
                    vm.walkinServSelected = walkin.services;
                    vm.selectedProductFromWalkin = pushProduct(vm.walkinProdSelected);
                    vm.selectedServiceFromWalkin = pushService(vm.walkinServSelected);
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
                                'prodqty': prod.intQuantity
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
                    'prodqty': product.qty
                });
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
                    'employeeAssigned': service.selEmployee
                });
                console.log(service);
            };

            $scope.removeFromProductList = function (index) {
                vm.selectedProductFromWalkin.splice(index, 1);
            };

            function editInCart(order) {
                var order = order;
                $('#editItem').closeModal();
                if(order.type == 'product') {
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
                    console.log(order);
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

            function removeToCartProduct (index, orders) {
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

            function removeToCartService (index, service) {
                vm.serviceOrder.splice(index, 1);
                vm.sum -= service.serviceTotal;
            }

            function removeToCartPackage (index, pack) {
                vm.packageOrder.splice(index, 1);
                vm.sum -= pack.packageTotal;
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
                    vm.sum += vm.packageTotal;
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
                vm.selPackageDetails.push({
                    intServiceID: vm.packageContainService[index].service.intServiceID,
                    intQuantity: 1,
                    intEmployeeID: vm.selEmployeePerService.intEmpID,
                    strStatus: 'PENDING'
                });
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
                console.log(vm.selPackageDetails);
                var walkinData = $.param({
                    'productString': selectprod,
                    'productQuantity': quantprod,
                    'serviceString': selectserv,
                    'employeeAssigned': selectEmp,
                    'promoList': vm.selPromoDetails,
                    'strTotalPrice': vm.sum,
                    'discounts': selectdiscount,
                    'strName': vm.details.name,
                    'strContactNo': vm.details.contact
                });

                $http({
                    method: 'post',
                    url: 'http://localhost:8080/SalonManagement/createWalkin',
                    data: walkinData,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(function successCallback(data) {
                    SweetAlert.swal("Successfully created!", ".", "success");
                    $('#createWalkinModal').closeModal();
                }, function errorCallback(response) {
                    SweetAlert.swal("Oops", "Something went wrong!", "error");
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







