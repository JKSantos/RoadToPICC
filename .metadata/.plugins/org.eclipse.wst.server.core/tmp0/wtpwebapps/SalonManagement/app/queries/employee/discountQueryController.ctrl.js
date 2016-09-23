/**
 * Created by Castillo on 8/24/2016.
 */
/**
 * Created by Castillo on 8/24/2016.
 */
(function () {
    'use strict';
    angular
        .module('app')
        .controller('discountQueryController', discountQueryController);

    function discountQueryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, queryFactory) {
        var vm = this;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;
        vm.selectStatusInTable = selectStatusInTable;
        vm.queryDiscountSearch = '';
        vm.status = [
            'ACTIVE',
            'INACTIVE'
        ];

        function dtInstanceCallback (dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable () {
            var query = vm.queryDiscountSearch;
            vm.tableInstance.search(query).draw();
        }
        function selectStatusInTable () {
            var sel = vm.selStatus;
            if(sel == 'ACTIVE' || sel == 'INACTIVE'){
                vm.tableInstance.column(2).search('^'+sel+'$', true).draw();
            } else if (sel == '') {
                vm.tableInstance.column(2).search(sel).draw();
            }
        }

        queryFactory.getDiscount().then(function (data) {
            vm.discountList = data.discountList;
        });

    }
})();
