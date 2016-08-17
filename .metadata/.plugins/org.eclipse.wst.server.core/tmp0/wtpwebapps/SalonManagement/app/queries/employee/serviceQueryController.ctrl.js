(function () {
    'use strict';
    angular
        .module('app')
        .controller('serviceQueryController', serviceQueryController);

    function serviceQueryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, queryFactory) {
        var vm = this;
        vm.getCategory = getCategory;
        vm.dateFormat = ["MMMM/D/YYYY"];
        vm.aifilter = [
            {"id": 1,"strEmpValue": 'ACTIVE'},
            {"id": 2,"strEmpValue": 'INACTIVE'}
            ];
        vm.selOption = '';
        vm.dtOptions = DTOptionsBuilder.newOptions()
            .withPaginationType('full_numbers')
            .withDisplayLength(10)
            .withLanguage({
                "sLoadingRecords": "Loading..."
            });
        vm.dtColumnDefs = [
            DTColumnDefBuilder.newColumnDef(0),
            DTColumnDefBuilder.newColumnDef(1).notSortable().withOption('width','100px'),
            DTColumnDefBuilder.newColumnDef(2).notSortable(),
            DTColumnDefBuilder.newColumnDef(3),
            DTColumnDefBuilder.newColumnDef(4)
        ];


        queryFactory.getService().then(function (data) {
            vm.serviceList = data.data.serviceList;
            vm.category = getCategory(vm.serviceList);
            // vm.position = getPosition(vm.employeeList);
            // console.log(vm.position);

        });

        function getCategory(category) {
            var categories = [];
            angular.forEach(category, function(item) {
                angular.forEach(item.strProductCategory, function(job) {
                    categories.push(job);
                });
            });

            console.log(categories);
            return _.uniq(categories);
        }


    }
})();
