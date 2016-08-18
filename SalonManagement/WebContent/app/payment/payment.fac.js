(function () {
    'use strict';

    angular
        .module('app')
        .factory('paymentFactory', paymentFactory)
        .factory('sampleFactory', sampleFactory);

    function paymentFactory($resource) {
        var orderDetails = [{}];
        var paymentDetails = [{}];
        var subtotal = 0;
        return {
            getUnpaidPayments: function () {
                $resource('getAllUnpaidTransaction');
            },
            getPayments: function () {
                return paymentDetails;
            },
            getOrderDetails: function () {
                return orderDetails;
            },
            getSubTotal: function () {
                return subtotal;
            },
            insertPayments: function (list) {
                paymentDetails.push(list);
                console.log(paymentDetails);
            },
            insertOrder: function (productId, quantity) {
                orderDetails.push({
                    productID: productId,
                    productQuantity: quantity
                });
            },
            insertTotal: function (total) {
                subtotal = total;
                console.log(subtotal);
            },
            subtractTotal: function (total, index) {
                subtotal -= total;
                orderDetails.splice(index, 1);
                console.log(subtotal);
            },
            addTotal: function (total, order) {
                subtotal += total;
                orderDetails.splice(order.index, 1);
                orderDetails.push({
                    productID: order.product,
                    productQuantity: order.quantity
                });
            },
            minusTotal: function (total, order) {
                subtotal -= total;
                orderDetails.splice(order.index, 1);
                orderDetails.push({
                    productID: order.product,
                    productQuantity: order.quantity
                });
            }

        }
    }
})();