(function () {
    'use strict';
    angular
        .module('app')
        .controller('serviceQueryController', serviceQueryController);

    function serviceQueryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, queryFactory) {
        var vm = this;
        vm.getCategory = getCategory;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;
        vm.selectCategoryInTable = selectCategoryInTable;
        vm.getStatus = getStatus;
        vm.selectStatusInTable = selectStatusInTable;
        vm.queryServiceSearch = '';

        function dtInstanceCallback (dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable () {
            var query = vm.queryServiceSearch;
            vm.tableInstance.search(query).draw();
        }

        function selectCategoryInTable () {
            var sel = vm.selCategory;
            vm.tableInstance.search(sel).draw();
        }

        function selectStatusInTable () {
            var sel = vm.selStatus;
            vm.tableInstance.search(sel).draw();
        }


        queryFactory.getService().then(function (data) {
            vm.serviceList = data.serviceList;
            vm.category = getCategory(vm.serviceList);
            vm.servStatus = getStatus(vm.serviceList);
        });

        function getCategory(category) {
            var categories = [];
            angular.forEach(category, function(item, i) {
                categories.push(item.strServiceCategory);
            });

            return categories;
        }

        function getStatus(status) {
            var stat = [];
            angular.forEach(status, function(item, i) {
                stat.push(item.intServiceStatus);
            });

            return stat;
        }


    }
})();
