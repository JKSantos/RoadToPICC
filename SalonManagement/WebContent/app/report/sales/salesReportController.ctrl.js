(function () {
    'use strict';
    angular
        .module('app')
        .controller('salesReportController', salesReportController);

    function salesReportController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, reportsFactory) {
        var vm = this;
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


        reportsFactory.getAllOrders().then(function (data) {
            vm.orderList = data.data.orderList;
            console.log(vm.orderList);
        });
       }
})();
