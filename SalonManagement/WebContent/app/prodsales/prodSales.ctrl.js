(function () {
    'use strict';

    angular
        .module('app')
        .directive('select', materialSelect)
        .controller('prodSalesCtrl', prodSalesCtrl);

    materialSelect.$inject = ['$timeout'];

    function materialSelect($timeout) {
        var directive = {
            link: link,
            restrict: 'E',
            require: '?ngModel'
        };

        function link(scope, element, attrs, ngModel) {

            $timeout(create);
            if (ngModel){
                ngModel.$render = create;
            }

            function create() {
                element.material_select();
            }

            //if using materialize v0.96.0 use this
            element.one('$destroy', function () {
                element.material_select('destroy');
            });


        }

        return directive;
    }

    function prodSalesCtrl($scope, paymentFactory, locationFactory) {
        var vm = this;


        $scope.details = {};
        $scope.orderDetails = [{}];
        $scope.customerDetails = [{
            orderType: '',
            contactNumber: '',
            name: '',
            Street: '',
            location: '',
            orderDetails: '',
            subtotal: 0
        }];
        $scope.productList = {};
        $scope.itemTotal = {};
        $scope.orderList = [{product: '', total: 0}];
        $scope.orderType = [
            {id: 1, value: 'pickup', name: 'Pick Up'},
            {id: 2, value: 'delivery', name: 'Delivery'}
        ];
        $scope.details.order = {id: 1, value: 'pickup', name: 'Pick Up'};

        locationFactory.getLocation().then(function (data) {
            $scope.locationList = data.data.locationList;
        });

        locationFactory.getProducts().then(function (data) {
            $scope.productList = data.data.productList;
        });


        var orderDetails = paymentFactory.getOrderDetails();
        var sum = paymentFactory.getSubTotal();

        $scope.addToCart = function (index) {
            $scope.itemTotal = $scope.productList[index].dblProductPrice * $scope.details.quantity;
            $scope.orderList.push({product: $scope.productList[index].strProductName, total: $scope.itemTotal});

            var productId = $scope.productList[index].intProductID;
            var quantity = $scope.details.quantity;
            paymentFactory.insertOrder(productId, quantity);
        };

        function commaProducts() {
            var selectedProducts = "";
            for (var i = 1; i < orderDetails.length; i++) {
                var odrdah = orderDetails[i];
                selectedProducts += orderDetails[i].productID + ",";
            }
            return selectedProducts;
        }

        function commaQuantity() {
            var selectedQuantity = "";
            for (var i = 1; i < orderDetails.length; i++) {
                var odrdah = orderDetails[i];
                selectedQuantity += orderDetails[i].productQuantity + ",";
            }

            return selectedQuantity;
        }

        $scope.calculateTotal = function () {
            var total = 0;

            for (var i = 0; i < $scope.orderList.length; i++) {
                var product = $scope.orderList[i];
                total += (product.total);
            }
            $scope.totalAmount = total;
            paymentFactory.insertTotal($scope.totalAmount);
        };

        var st = paymentFactory.getSubTotal();
        $scope.setProdSalesPayment = function (custDetails) {
            $scope.customerDetails.push({
                orderType: custDetails.order.value,
                strContactNo: custDetails.contact,
                strName: custDetails.name,
                strStreet: custDetails.street,
                intLocationID: custDetails.location.intLocationID,
                selectedProducts: commaProducts(),
                productQuantity: commaQuantity(),
                strTotalPrice: paymentFactory.getSubTotal()
            });

            paymentFactory.saveOrderDetails($scope.customerDetails[1]);
        };
    }
})();
