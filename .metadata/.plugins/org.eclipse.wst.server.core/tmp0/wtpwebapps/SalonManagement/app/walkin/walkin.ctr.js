(function(){
  'use strict';

  angular
    .module('app')
    .controller('walkinCtrl', walkinCtrl);

    function walkinCtrl(paymentFactory){
      var vm = this;

      vm.setWalkinPayment = setWalkinPayment;

        vm.paymentDetails = paymentFactory.getPayments();


      function setWalkinPayment(details){
          vm.paymentDetails.push(details);
      }

    }
})();
