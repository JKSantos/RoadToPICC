/**
 * Created by Castillo on 8/23/2016.
 */
(function () {
    'use strict';
    angular
        .module('app')
        .controller('deliveryQueryController', deliveryQueryController);

    function deliveryQueryController($scope, $filter, SweetAlert, queryFactory) {
        var vm = this;
        vm.getCategory = getCategory;
        vm.dtInstanceCallback = dtInstanceCallback;
        vm.searchTable = searchTable;
        vm.selectCityInTable = selectCityInTable;
        vm.selectStatusInTable = selectStatusInTable;
        vm.queryDeliverySearch = '';
        vm.status = [
            'ACTIVE',
            'INACTIVE'
        ];

        function dtInstanceCallback (dtInstance) {
            var datatableObj = dtInstance.DataTable;
            vm.tableInstance = datatableObj;
        }

        function searchTable () {
            var query = vm.queryDeliverySearch;
            vm.tableInstance.search(query).draw();
        }

        function selectCityInTable () {
            var sel = vm.selCity;
            vm.tableInstance.column(1).search(sel).draw();
        }

        function selectStatusInTable () {
            var sel = vm.selStatus;
            if(sel == 'ACTIVE' || sel == 'INACTIVE'){
                vm.tableInstance.column(3).search('^'+sel+'$', true).draw();
            } else if (sel == '') {
                vm.tableInstance.column(3).search(sel).draw();
            }
        }

        queryFactory.getDelivery().then(function (data) {
            vm.locationList = data.locationList;
            vm.category = getCategory(vm.locationList);
        });


        function getCategory(category) {
            var cat = [];
            angular.forEach(category, function(item, i) {
                cat.push(item.strCity);
            });

            return cat;
        }


    }
})();
