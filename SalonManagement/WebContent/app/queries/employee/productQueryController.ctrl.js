(function () {
    'use strict';
    angular
        .module('app')
        .controller('productQueryController', productQueryController);

    function productQueryController($scope, $filter, SweetAlert, DTOptionsBuilder, DTColumnDefBuilder, DTDefaultOptions, queryFactory) {
        var vm = this;
        vm.getCategory = getCategory;
        vm.getStatus = getStatus;
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
        // vm.dtColumnDefs = [
        //     DTColumnDefBuilder.newColumnDef(0),
        //     DTColumnDefBuilder.newColumnDef(1).notSortable().withOption('width','100px'),
        //     DTColumnDefBuilder.newColumnDef(2).notSortable(),
        //     DTColumnDefBuilder.newColumnDef(3),
        //     DTColumnDefBuilder.newColumnDef(4)
        // ];

        queryFactory.getProduct().then(function (data) {
            vm.productList = data.data.productList;
            vm.category = getCategory(vm.productList);
            vm.prodStatus = getStatus(vm.productList);
            console.log(vm.productList);
            console.log(vm.prodStatus);

        });

        function getCategory(category) {
            var categories = [];
            angular.forEach(category, function(item, i) {
                    categories.push(item.strProductCategory);
            });
            
            return _.uniq(categories);
        }

        function getStatus(status) {
            var status = [];
            angular.forEach(status, function(item, i) {
                status.push(item.intProductStatus);
            });

            return _.uniq(status);
        }


    }
})();
