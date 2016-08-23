(function () {
    'use strict';
    angular
        .module('app')
        .controller('productQueryController', productQueryController);

    function productQueryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, queryFactory) {
        var vm = this;
        vm.getCategory = getCategory;
        vm.getStatus = getStatus;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;
        vm.selectCategoryInTable = selectCategoryInTable;
        vm.selectStatusInTable = selectStatusInTable;
        vm.queryProductSearch = '';
        vm.dateFormat = ["MMMM/D/YYYY"];
        vm.aifilter = [
            {"id": 1,"strEmpValue": 'ACTIVE'},
            {"id": 2,"strEmpValue": 'INACTIVE'}
            ];
        vm.selOption = '';

        function dtInstanceCallback (dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable () {
            var query = vm.queryProductSearch;
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

        queryFactory.getProduct().then(function (data) {
            vm.productList = data.productList;
            vm.category = getCategory(vm.productList);
            vm.prodStatus = getStatus(vm.productList);

        });

        function getCategory(category) {
            var categories = [];
            angular.forEach(category, function(item, i) {
                    categories.push(item.strProductCategory);
            });
            
            return categories;
        }

        function getStatus(status) {
            var stat = [];
            angular.forEach(status, function(item, i) {
                stat.push(item.intProductStatus);
            });

            return stat;
        }


    }
})();
