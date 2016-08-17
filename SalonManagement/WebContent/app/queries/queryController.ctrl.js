(function () {
    'use strict';
    angular
        .module('app')
        .controller('queryController', queryController);

    function queryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, paymentFactory) {
        var vm = this;
        vm.dateFormat = ["MMMM/D/YYYY"];
        vm.type = [{
            option1: "order",
            option2: "walkin",
            option3: "reservation"
        }];
        vm.createPOPayment = createPOPayment;
        vm.paymentSubmit = paymentSubmit;
        // vm.dtOptions = DTOptionsBuilder.newOptions()
        //     .withPaginationType('full_numbers')
        //     .withDisplayLength(10)
        //     .withLanguage({
        //         "sLoadingRecords": "Loading..."
        //     });
        vm.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(0),
            DTColumnDefBuilder.newColumnDef(1).notSortable(),
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3),
            DTColumnDefBuilder.newColumnDef(4),
            DTColumnDefBuilder.newColumnDef(5).notSortable()
        ];


        paymentFactory.getUnpaidPayments().then(function (data) {
            vm.paymentList = data.orderList;
        });
        paymentFactory.getUnpaidPayments().then(function (data) {
            vm.payReservationList = data.reservationList;
        });


    }
})();
