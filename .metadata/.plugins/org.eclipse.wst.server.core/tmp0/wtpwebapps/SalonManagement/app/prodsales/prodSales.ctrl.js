(function () {
    'use strict';

    angular
        .module('app')
        .directive('select', materialSelect)
        .controller('prodSalesCtrl', prodSalesCtrl);

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

    function prodSalesCtrl($scope, $window, paymentFactory, locationFactory, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder) {
        var vm = this;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;
        vm.closeCard = closeCard;
        vm.crProductSales = crProductSales;
        vm.closePSModal = closePSModal

        vm.loadingBubble = 1;
        vm.searchInTable = '';
        $scope.details = {};

        $scope.orderDetails = [{}];
        $scope.customerDetails = [{
            orderType: '',
            contactNumber: '',
            name: '',
            Street: '',
            location: '',
            orderDetails: '',
            subtotal: 0
        }];
        $scope.productList = {};
        $scope.itemTotal = {};
        $scope.orderList = [{product: '', total: 0}];
        $scope.orderType = [
            {id: 1, value: 'pickup', name: 'Pick Up'},
            {id: 2, value: 'delivery', name: 'Delivery'}
        ];
        $scope.details.order = {id: 1, value: 'pickup', name: 'Pick Up'};

        function dtInstanceCallback(dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable() {
            var s = vm.searchInTable;
            vm.tableInstance.search(s).draw();
        }

        locationFactory.getLocation().then(function (data) {
            $scope.locationList = data.locationList;
            if ($scope.locationList && $scope.locationList.length > 0) {
                $scope.details.location = $scope.locationList[0];
            }
        });

        locationFactory.getEmployees().then(function (data) {
           $scope.employeeList = data.data.employeeList;
        });

        locationFactory.getProducts().then(function (data) {
            $scope.productList = data.data.productList;
        });

        locationFactory.getRequest().then(function (data) {
            $scope.requestOrder = data.productSalesList;
        });

        locationFactory.getOrders().then(function (data) {
            $scope.requestOrderList = data.orderList;
            console.log($scope.requestOrderList);
        });


        var orderDetails = paymentFactory.getOrderDetails();
        var sum = paymentFactory.getSubTotal();

        function closePSModal() {
            $scope.details.contact = '';
            $scope.details.name = '';
            $scope.details.street = '';
        }

        function closeCard(id) {
            var prodClose = 'prodClose' + id;
            $('#' + prodClose).click();
        }

        function crProductSales() {
            $('#crProductSales').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
        }

        $scope.addToCart = function (index) {
            $scope.itemTotal = $scope.productList[index].dblProductPrice * $scope.details.quantity;
            $scope.orderList.push({
                product: $scope.productList[index].strProductName,
                total: $scope.itemTotal,
                strPhotoPath: $scope.productList[index].strPhotoPath,
                quantity: $scope.details.quantity,
                price: $scope.productList[index].dblProductPrice
            });


            var productId = $scope.productList[index].intProductID;
            var quantity = $scope.details.quantity;
            paymentFactory.insertOrder(productId, quantity);

            $scope.details.quantity = '';
        };

        $scope.removeToCart = function (index, orders) {
            $scope.orderList.splice(index, 1);
            $scope.totalAmount -= orders.total;
            paymentFactory.subtractTotal(orders.total, index);
        };

        $scope.openEditItem = function (index, orders) {
            $('#editItem').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            $scope.orderToBeEdit = {
                product: orders.product,
                total: orders.total,
                strPhotoPath: orders.strPhotoPath,
                quantity: orders.quantity,
                price: orders.price,
                index: index
            };
        };

        $scope.editInCart = function (order) {
            $('#editItem').closeModal();

            var beforeTotal = order.total,
                finalTotal = order.price * order.quantity,
                t = 0;
            if (finalTotal > beforeTotal) {
                t = finalTotal - beforeTotal;
                console.log(t + 't');
                $scope.totalAmount = $scope.totalAmount + t;
                console.log($scope.totalAmount + 'totalAmt');
                paymentFactory.addTotal(t, order)
            } else {
                console.log('asdasd');
                console.log(beforeTotal + '//' + finalTotal);
                t = beforeTotal - finalTotal;
                console.log(t + 't');
                $scope.totalAmount = $scope.totalAmount - t;
                console.log($scope.totalAmount + 'totalAmt');
                paymentFactory.minusTotal(t, order);
            }
            $scope.orderList.splice(order.index, 1);
            $scope.orderList.unshift({
                product: order.product,
                total: $scope.totalAmount,
                strPhotoPath: order.strPhotoPath,
                quantity: order.quantity,
                price: order.price
            });

        };

        function commaProducts() {
                var selectedProducts = "";
                for (var i = 1; i < orderDetails.length; i++) {
                    var odrdah = orderDetails[i];
                    selectedProducts += orderDetails[i].productID + ",";
                }
                return selectedProducts;
        }

        function commaQuantity() {
                var selectedQuantity = "";
                for (var i = 1; i < orderDetails.length; i++) {
                    var odrdah = orderDetails[i];
                    selectedQuantity += orderDetails[i].productQuantity + ",";
                }

                return selectedQuantity;
            console.log(selectedQuantity);
        }

        $scope.calculateTotal = function () {
            var total = 0;

            for (var i = 0; i < $scope.orderList.length; i++) {
                var product = $scope.orderList[i];
                total += (product.total);
            }
            $scope.totalAmount = total;
            paymentFactory.insertTotal($scope.totalAmount);
        }; //end

        var st = paymentFactory.getSubTotal();
        $scope.setProdSalesPayment = function (custDetails, createPSForm) {
                $scope.customerDetails.push({
                    orderType: custDetails.order.value,
                    strContactNo: custDetails.contact,
                    strName: custDetails.name,
                    strStreet: custDetails.street,
                    intLocationID: custDetails.location.intLocationID,
                    selectedProducts: commaProducts(),
                    productQuantity: commaQuantity(),
                    strTotalPrice: paymentFactory.getSubTotal()
                });
                console.log($scope.customerDetails);
                $scope.saveDetails($scope.customerDetails[1]);
        }; //end

        $scope.saveDetails = function (myData) {
            vm.loadingBubble = 0;
            var psdata = {
                "intLocationID": myData.intLocationID,
                "orderType": myData.orderType,
                "productQuantity": myData.productQuantity,
                "selectedProducts": myData.selectedProducts,
                "strContactNo": myData.strContactNo,
                "strName": myData.strName,
                "strStreet": myData.strStreet,
                "strTotalPrice": myData.strTotalPrice
            };
            setTimeout(function () {
                $.ajax({
                    url: 'createOrder',
                    type: 'post',
                    data: psdata,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        if (data.status == "success") {
                            swal("Successfully created!", ".", "success");
                            console.log($scope.customerDetails[1]);
                            $scope.requestOrder.push({
                                intSalesID: data.intCreatedID,
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
                            console.log(data);
                            $window.location.reload();
                        } else {
                            SweetAlert.swal("Oops", "Something went wrong!", "error");
                        }
                    },
                    error: function () {
                        vm.loadingBubble = 1;
                        SweetAlert.swal("Oops", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        }; //end
        
        $scope.openPickUpOrder = function (request) {
            var index = $scope.requestOrder.indexOf(request);
            $('#AcceptPickupModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
            $scope.pickup = {
                'strName': request.strName,
                'strStatus': request.strStatus,
                'strAddress': request.strAddress,
                'intType': request.intType,
                'productList': request.productList,
                'strContactNo': request.strContactNo,
                'intSalesID': request.intSalesID,
                'intLocationID': request.intLocationID,
                'deliveryDate': request.deliveryDate,
                'datCreated': request.datCreated,
                'index': index
            }
            console.log($scope.pickup);
        };

        $scope.acceptPickupOrder = function (request) {
            var index = $scope.requestOrder.indexOf(request);
            swal({
                    title: "Are you sure you want to accept the order of " + request.strName + "?",
                    text: "",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#9575cd ",
                    confirmButtonText: "Yes!",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    setTimeout(function () {
                        $.ajax({
                            url: 'acceptOrder',
                            type: 'post',
                            data: {
                                "intEmpID": request.selEmployee.intEmpID,
                                "intOrderID": request.intSalesID
                            },
                            dataType: 'json',
                            async: true,
                            success: function (data) {
                                if (data.result == "success") {
                                    SweetAlert.swal("Your order request was accepted!", ".", "success");
                                    $scope.requestOrder.splice(index, 1);
                                    $scope.requestOrderList.push({
                                        strName: request.strName,
                                        strAddress: request.strAddress,
                                        strContactNo: request.strContactNo,
                                        intType: request.intType,
                                        strStatus: 'PENDING'
                                    });
                                    $('#AcceptPickupModal').closeModal();
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
        }; //end

        $scope.acceptDeliveryOrder = function (request) {
            var index = $scope.requestOrder.indexOf(request);
            console.log(request);
            $scope.delivery = {
                strName: request.strName,
                strAddress: request.strAddress,
                strContactNo: request.strContactNo,
                intType: request.intType,
                strStatus: request.strStatus,
                intSalesID: request.intSalesID,
                deliveryDate: request.deliveryDate,
                index: index
            };
            $('#AcceptDeliveryModal').openModal({
                dismissible: true, // Modal can be dismissed by clicking outside of the modal
                opacity: .7, // Opacity of modal background
                in_duration: 200, // Transition in duration
                out_duration: 200, // Transition out duration
            });
        }; //end

        $scope.sendDeliveryDetails = function (delivery) {
            swal({
                    title: "Are you sure you want to set this date for " + delivery.strName + "?",
                    text: "",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#9575cd ",
                    confirmButtonText: "Yes!",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    setTimeout(function () {
                        $.ajax({
                            url: 'acceptOrder',
                            type: 'post',
                            data: {
                                "intEmployeeID": delivery.selEmployee.intEmpID,
                                "intOrderID": delivery.intSalesID,
                                "datDeliveryDate": delivery.deliveryDate
                            },
                            dataType: 'json',
                            async: true,
                            success: function (data) {
                                if (data.result == "success") {
                                    SweetAlert.swal("Your order request was accepted!", ".", "success");
                                    $scope.requestOrder.splice(delivery.index, 1);
                                    $scope.requestOrderList.push({
                                        strName: delivery.strName,
                                        strAddress: delivery.strAddress,
                                        strContactNo: delivery.strContactNo,
                                        intType: delivery.intType,
                                        strStatus: 'PENDING'
                                    });
                                    $('#AcceptDeliveryModal').closeModal();
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
        }; //end

        $scope.declineOrder = function (request) {
            var index = $scope.requestOrder.indexOf(request);
            swal({
                    title: "Are you sure you want to decline the order of " + request.strName + "?",
                    text: "",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#e57373 ",
                    confirmButtonText: "Yes!",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    setTimeout(function () {
                        $.ajax({
                            url: 'declineOrder',
                            type: 'post',
                            data: {
                                "intOrderID": request.intSalesID
                            },
                            dataType: 'json',
                            async: true,
                            success: function (data) {
                                if (data.result == "success") {
                                    SweetAlert.swal("The order request was declined!", ".", "success");
                                    $scope.requestOrder.splice(index, 1);
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
        }; //end

        $scope.deactivateOrder = function (order) {
            var index = $scope.requestOrderList.indexOf(order);
            swal({
                    title: "Are you sure you want to cancel the order of " + order.strName + "?",
                    text: "",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#e57373 ",
                    confirmButtonText: "Yes!",
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    setTimeout(function () {
                        $.ajax({
                            url: 'deactivateOrder',
                            type: 'post',
                            data: {
                                "intOrderID": order.intSalesID
                            },
                            dataType: 'json',
                            async: true,
                            success: function (data) {
                                if (data.result == "success") {
                                    SweetAlert.swal("The order was successfully cancelled!", ".", "success");
                                    $scope.requestOrderList.splice(index, 1);
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
        }; //end
    }
})();