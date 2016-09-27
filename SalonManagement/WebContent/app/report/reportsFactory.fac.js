(function () {
    'use strict';

    angular
        .module('app')
        .factory('reportsFactory', reportsFactory);

    function reportsFactory($http) {

       var monthlyTags = [{}];
        return {
            setMonthlyTags: function(data){
            	monthlyTags = data;
            	console.log(monthlyTags);
            },
            getMonthlyTags: function(){
            	
            	return monthlyTags;
            	console.log(monthlyTags);
            }
        }
    }
})();