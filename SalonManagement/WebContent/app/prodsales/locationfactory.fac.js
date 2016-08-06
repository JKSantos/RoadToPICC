(function(){
  'use strict';

  angular
    .module('app')
    .factory('locationFactory', locationFactory);

    function locationFactory($http){
      var locations = {};

      return{
        getLocation: function(){
          return $http.get('http://localhost:8080/SalonManagement/api/v1/getAllLocation').success(function (data) {
            return data;
          });
        }
      }
    }
})();
