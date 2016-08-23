/**
 * Created by Castillo on 8/23/2016.
 */
/**
 * Created by Castillo on 8/23/2016.
 */
(function () {
    'use strict';
    angular
        .module('app')
        .controller('extraQueryController', extraQueryController);

    function extraQueryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, queryFactory) {
        var vm = this;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;
        vm.selectCityInTable = selectCityInTable;
        vm.selectStatusInTable = selectStatusInTable;
        vm.queryExtraSearch = '';
        vm.status = [
            'ACTIVE',
            'INACTIVE'
        ];

        function dtInstanceCallback (dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable () {
            var query = vm.queryExtraSearch;
            vm.tableInstance.search(query).draw();
        }

        function selectCityInTable () {
            var sel = vm.selCity;
            vm.tableInstance.search(sel).draw();
        }

        function selectStatusInTable () {
            var sel = vm.selStatus;
            vm.tableInstance.search(sel).draw();
        }

        queryFactory.getExtra().then(function (data) {
            vm.otherChargeList = data.otherChargeList;
        });

    }
})();
