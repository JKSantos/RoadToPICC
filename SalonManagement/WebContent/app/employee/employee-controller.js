/**
 * Created by Castillo on 9/12/2016.
 */
/**
 * Created by Castillo on 8/23/2016.
 */
(function () {
    'use strict';
    angular
        .module('app')
        .controller('employeeAccountSetting', employeeAccountSetting);

    function employeeAccountSetting($scope, $http, $window, $filter, empAccountFactory) {
        var vm = this;

        vm.saveUsername = saveUsername;
        vm.validateUsername = validateUsername;
        vm.retypeActive = retypeActive;
        vm.passwordMatch = passwordMatch;
        vm.savePassword = savePassword;

        vm.user = 0;
        vm.pass = 0;
        vm.retypefield = 0;
        vm.passLength = 0;
        vm.confirmation = 0;
        vm.success = 0;
        vm.passwordValid = 0;
        vm.notEmpty = 0;
        vm.ajaxPassword = 0;

        empAccountFactory.getUsername().then(function (data) {
            vm.usernames = data.usernames;
        });

        function validateUsername(name) {
            vm.success = 0;
            if (typeof name !== "undefined") {
                if (name.length < 5 && name != '') {
                    vm.user = 5;
                } else {
                    $('#empUserName').keyup(function (e) {
                        if (e.which === 32) {
                            var str = $(this).val();
                            str = str.replace(/\s/g, '');
                            $(this).val(str);
                        }
                    }).blur(function () {
                        var str = $(this).val();
                        str = str.replace(/\s/g, '');
                        $(this).val(str);
                    });

                    if (name) {
                        var u = 0,
                            user = name.toLowerCase();
                        console.log(user);
                        console.log(vm.usernames);
                        for (var i = 0; i < vm.usernames.length; i++) {
                            if (user == vm.usernames[i].toLowerCase()) {
                                u = 1;
                            }
                        }
                        if (u == 1) {
                            vm.user = 1;
                        } else {
                            vm.user = 2;
                        }
                    } else {
                        vm.user = 0;
                    }
                }
            } else {
                vm.user = 0;
            }
        }

        function saveUsername(username) {
            $.ajax({
                type: 'post',
                url: 'changeEmpUsername',
                dataType: 'json',
                data: {
                    'intEmployeeID': $('#sessionID').val(),
                    'strUsername': username
                },
                success: function (data) {
                    if (data.result == 'success') {
                        $window.location.reload();
                    } else if (data.result == 'failed') {
                        vm.user = 0;
                        vm.success = 4;
                    }
                },
                error: function (data) {
                    vm.user = 0;
                    vm.success = 4;
                }

            });
        }

        function retypeActive(pass, re) {
            console.log(pass);
            $('#empNewPassword').keyup(function (e) {
                if (e.which === 32) {
                    var str = $(this).val();
                    str = str.replace(/\s/g, '');
                    $(this).val(str);
                }
            }).blur(function () {
                var str = $(this).val();
                str = str.replace(/\s/g, '');
                $(this).val(str);
            });
            if (typeof pass !== "undefined") {
                if (pass.length > 7) {
                    vm.retypefield = 1;
                    vm.passLength = 0;
                    if (pass.length > 0 && re) {
                        vm.notEmpty = 1;
                        if (pass == re) {
                            vm.passwordValid = 1;
                        } else if (pass != re) {
                            vm.passwordValid = 2;
                        } else {
                            vm.passwordValid = 0;
                        }
                    } else {
                        vm.passwordValid = 0;
                    }
                } else if (pass.length < 8 && pass != '') {
                    vm.retypefield = 0;
                    vm.passLength = 1;
                    vm.passwordValid = 0;
                } else {
                    vm.passLength = 0;
                    vm.passwordValid = 0;
                }
            } else {
                vm.passwordValid = 0;
                vm.retypefield = 0;
                $scope.password.retype = '';
            }
        }

        function passwordMatch(newpass, retypepass) {
            if (retypepass.length > 0) {
                vm.notEmpty = 1;
                if (newpass == retypepass) {
                    vm.passwordValid = 1;
                } else if (newpass != retypepass) {
                    vm.passwordValid = 2;
                } else {
                    vm.passwordValid = 0;
                }
            } else {
                vm.notEmpty = 0;
                vm.passwordValid = 0;
            }
        }

        function savePassword(password) {
            $.ajax({
                type: 'post',
                url: 'changeEmpPassword',
                dataType: 'json',
                data: {
                    'intEmployeeID': $('#sessionIDPassword').val(),
                    'strPassword': password.new,
                    'oldPassword': password.current
                },
                async: true,
                success: function (data) {
                    console.log(data);
                    if(data.result == 'success') {
                        $window.location.reload();
                    } else if(data.result == 'incorrect') {
                        $('#passwordIncorrect .failed').remove();
                        $('#passwordIncorrect').append('<span class="incorrect red-text text-lighten-1">Incorrect Current Password!</span>');
                    } else {
                        $('#passwordIncorrect .incorrect').remove();
                        $('#passwordIncorrect').append('<span class="failed red-text text-lighten-1">Failed!</span>');
                    }
                },
                error: function (data) {
                    $('#passwordIncorrect .failed').remove();
                    $('#passwordIncorrect .incorrect').remove();
                    $('#passwordIncorrect').append('<span class="failed red-text text-lighten-1">Failed!</span>');
                }
            });
        }

        $('#empCurrentPassword').on('change', function () {
            $('#passwordIncorrect .failed').remove();
            $('#passwordIncorrect .incorrect').remove();
        });


    }
})();
