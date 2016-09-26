(function () {
    'use strict';
    angular
        .module('app')
        .controller('packageQueryController', packageQueryController);

    function packageQueryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, queryFactory) {
        var vm = this;
        vm.getStatus = getStatus;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;
        vm.selectTypeInTable = selectTypeInTable;
        vm.selectStatusInTable = selectStatusInTable;
        vm.queryPackageSearch = '';
        vm.type = [
            'EVENT',
            'HOME SERVICE',
            'WALK IN'
        ];
        vm.status = [
            'ACTIVE',
            'INACTIVE'
        ];

        function dtInstanceCallback (dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable () {
            var query = vm.queryPackageSearch;
            vm.tableInstance.search(query).draw();
        }

        function selectTypeInTable () {
            var sel = vm.selType;
            vm.tableInstance.search(sel).draw();
        }

        function selectStatusInTable () {
            var sel = vm.selStatus;
            if(sel == 'ACTIVE' || sel == 'INACTIVE'){
                vm.tableInstance.column(4).search('^'+sel+'$', true).draw();
            } else if (sel == '') {
                vm.tableInstance.column(4).search(sel).draw();
            }
        }

        queryFactory.getPackage().then(function (data) {
            vm.packageList = data.packageList;
            vm.prodStatus = getStatus(vm.packageList);
        });


        function getStatus(status) {
            var stat = [];
            angular.forEach(status, function(item, i) {
                stat.push(item.intProductStatus);
            });

            return stat;
        }


    }
})();
