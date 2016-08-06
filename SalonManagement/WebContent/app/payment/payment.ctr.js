(function(){
  'use strict';
  angular
    .module('app')
    .controller('paymentCtrl', paymentCtrl);

    function paymentCtrl($scope){
        var vm = this;
        vm.paymentDetails = [];
        vm.getPayments = getPayments;

        function getPayments(){
          // vm.paymentDetails = paymentFactory.getPayments();
        }
    }
})();
