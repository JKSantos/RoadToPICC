/**
 * Created by Castillo on 9/12/2016.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .factory('empAccountFactory', empAccountFactory);

    function empAccountFactory($http, $resource) {
        return {
            getUsername: function () {
                return $resource('getEmpUsernames').get().$promise.then(function (data) {
                    return data;
                });
            }


        }
    }
})();