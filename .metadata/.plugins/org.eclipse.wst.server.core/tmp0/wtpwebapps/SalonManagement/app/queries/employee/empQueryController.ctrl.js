(function () {
    'use strict';
    angular
        .module('app')
        .controller('empQueryController', empQueryController);

    function empQueryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, queryFactory) {
        var vm = this;
        vm.getPosition = getPosition;
        vm.dateFormat = ["MMMM/D/YYYY"];
        vm.aifilter = [
            {"id": 1,"strEmpValue": 'ACTIVE'},
            {"id": 2,"strEmpValue": 'INACTIVE'}
            ];
        vm.selOption = '';
        // vm.dtOptions = DTOptionsBuilder.newOptions()
        //     .withPaginationType('full_numbers')
        //     .withDisplayLength(10)
        //     .withLanguage({
        //         "sLoadingRecords": "Loading..."
        //     });
        vm.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(0),
            DTColumnDefBuilder.newColumnDef(1).notSortable().withOption('width','100px'),
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3),
            DTColumnDefBuilder.newColumnDef(4),
            DTColumnDefBuilder.newColumnDef(5).notSortable()
        ];


        queryFactory.getEmployee().then(function (data) {
            vm.employeeList = data.data.employeeList;
            console.log(vm.employeeList);
            vm.position = getPosition(vm.employeeList);
            console.log(vm.position);
        });

        function getPosition(position) {
            var pos = [];

            angular.forEach(position, function(item) {
                angular.forEach(item.jobQualification, function(job) {
                    pos.push(job.strJobDesc);
                });
            });

            return _.uniq(pos);
        }


    }
})();
