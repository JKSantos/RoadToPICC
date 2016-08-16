/**
 * Created by Castillo on 8/12/2016.
 */
(function () {

    'use strict';

    angular
        .module('app')
        .controller('orderController', orderController);

    function orderController(orderFactory, DTOptionsBuilder, DTColumnDefBuilder, SweetAlert) {
        var vm = this;
        vm.orders = [];
        vm.custDetails = [];
        vm.deleteOrder = deleteOrder;

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

        orderFactory.getOrders().then(function (data) {
            vm.orders = data.orderList;
        });

        var custDetails = orderFactory.getCustDetails();
        vm.custDetails.push(custDetails);

        function deleteOrder(order) {
            console.log(vm.custDetails);
            swal({
                    title: "Are you sure you want to cancel?",
                    text: "Order of " + order.strName,
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#3399ff",
                    confirmButtonText: "Yes, delete it!",
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
                                    var index = vm.orders.indexOf(order);
                                    vm.orders.splice(index, 1);
                                    SweetAlert.swal("Successfully cancelled!", "", "success");
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