(function(){
  'use strict';

  angular
    .module('app')
    .factory('locationFactory', locationFactory);

    function locationFactory($http){
      return{
        getLocation: function(){
          return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllLocation').then(function (data) {
            return data;
          });
        },
        getProducts: function(){
          return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllProduct').then(function (data) {
            return data;
          });
        }
      }
    }
})();
