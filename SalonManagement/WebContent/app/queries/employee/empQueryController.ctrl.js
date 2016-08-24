(function () {
    'use strict';
    angular
        .module('app')
        .controller('empQueryController', empQueryController);

    function empQueryController($scope, $resource, $filter, SweetAlert, DTOptionsBuilder, DTColumnBuilder, queryFactory) {
        var vm = this;
        vm.getPosition = getPosition;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;
        vm.selectPositionInTable = selectPositionInTable;
        vm.selectStatusInTable = selectStatusInTable;
        vm.queryEmployeeSearch = '';
        vm.dateFormat = ["MMMM/D/YYYY"];
        vm.aifilter = [
            {"id": 1,"strEmpValue": 'ACTIVE'},
            {"id": 2,"strEmpValue": 'INACTIVE'}
            ];
        // vm.dtOptions = DTOptionsBuilder.newOptions()
        //     .withPaginationType('full_numbers')
        //     .withDisplayLength(10)
        //     .withLanguage({
        //         "sLoadingRecords": "Loading..."
        //     });
        // vm.dtColumns = [
        //     DTColumnBuilder.newColumn(0),
        //     DTColumnBuilder.newColumn(1).notSortable().withOption('width','100px'),
        //     DTColumnBuilder.newColumn(2).notSortable(),
        //     DTColumnBuilder.newColumn(3),
        //     DTColumnBuilder.newColumn(4),
        //     DTColumnBuilder.newColumn(5).notSortable()
        // ];

        function dtInstanceCallback (dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable () {
            var query = vm.queryEmployeeSearch;
            vm.tableInstance.search(query).draw();
        }

        function selectPositionInTable () {
            var sel = vm.selOption;
            vm.tableInstance.search(sel).draw();
        }

        function selectStatusInTable () {
            var sel = vm.selectStatus;
            if(sel != 'ALL'){
                vm.tableInstance.column(5).search('^'+sel+'$', true).draw();
            } else {
                vm.tableInstance.search(sel).draw();
            }
        }

        queryFactory.getEmployee().then(function (data) {
            vm.employeeList = data.employeeList;
            vm.position = getPosition(vm.employeeList);
            vm.status = getStatus(vm.employeeList);
        });

        function getPosition(position) {
            var pos = [];

            angular.forEach(position, function(item) {
                angular.forEach(item.jobQualification, function(job) {
                    pos.push(job.strJobDesc);
                });
            });

            return pos;
        }

        function getStatus(status) {
            var st = [];

            angular.forEach(status, function(item) {
               st.push(item.strEmpStatus);
            });

            return st;
        }

    }
})();
