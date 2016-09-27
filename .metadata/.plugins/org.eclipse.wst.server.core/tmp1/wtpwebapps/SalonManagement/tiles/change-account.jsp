<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
        color: #757575;
    }

    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color: #757575;
    }

    ::-moz-placeholder { /* Mozilla Firefox 19+ */
        color: #757575;
    }

    :-ms-input-placeholder { /* Internet Explorer 10-11 */
        color: #757575;
    }
</style>


<div class="wrapper" ng-controller="employeeAccountSetting as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Account Settings</h3>
            <div class="row">
                <div class="col s12">
                    <div class="container">
                        <ul class="collapsible" data-collapsible="accordion">
                            <li>
                                <div class="collapsible-header">
                                    Username
                                </div>
                                <div class="collapsible-body">
                                    <form name="empAcctForm" novalidate>
                                        <div class="row">
                                            <input type="hidden" id="sessionID" value="${sessionScope.id}">
                                            <div class="input-field col s4 offset-s4">
                                                <input type="text" name="empUserName" id="empUserName" class="center"
                                                       placeholder="Username"
                                                       ng-value="'${sessionScope.username}'"
                                                       ng-pattern="/^[A-Za-z0-9]+$/"
                                                       ng-model="emp.username"
                                                       ng-change="vm.validateUsername(emp.username, empUserName);">
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-show="vm.user == 5">
                                                <span class="red-text text-lighten-1">Must be at least 5 characters!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-if="empAcctForm.empUserName.$valid == false">
                                                <span class="red-text text-lighten-1">Invalid username!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-show="vm.user == 1 && empAcctForm.empUserName.$valid == true">
                                                <span class="red-text text-lighten-1">Username is not available!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-show="vm.user == 2">
                                                <span class="green-text text-darken-1">Username is available!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-show="vm.success == 3">
                                                <span class="green-text text-darken-1">Successfully saved!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-show="vm.success == 4">
                                                <span class="red-text text-lighten-1">Failed</span>
                                            </div>
                                            <div class="input-field col s12">
                                                <a class="btn waves-effect waves-light purple"
                                                   ng-if="vm.user == 2"
                                                   ng-click="vm.saveUsername(emp.username);">
                                                    Save Changes
                                                </a>
                                                <a class="btn purple"
                                                   ng-if="vm.user != 2" style="cursor: not-allowed; opacity: 0.3;"
                                                   ng-disabled="true">
                                                    Save Changes
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <li>
                                <div class="collapsible-header">
                                    Password
                                </div>
                                <div class="collapsible-body">
                                    <form name="empAccountPassword" novalidate>
                                        <div class="row">
                                            <div class="input-field col s4 offset-s4">
                                                <input type="hidden" id="sessionIDPassword" value="${sessionScope.id}">
                                                <input type="password" id="empCurrentPassword" placeholder="Current"
                                                       ng-model="password.current"
                                                       ng-change="vm.currentPasswordField(password.current);">
                                            </div>
                                            <div class="input-field col s4 offset-s4">
                                                <input type="password" id="empNewPassword" name="empNewPassword" placeholder="New"
                                                       ng-model="password.new"
                                                       ng-pattern="/^[A-Za-z0-9]+$/"
                                                       ng-change="vm.retypeActive(password.new, password.retype);">
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-if="vm.retypefield == 0 && vm.notEmpty == 0">
                                                <input type="password" placeholder="Re-type" disabled>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-if="vm.retypefield == 1 || vm.notEmpty == 1">
                                                <input type="password" id="empRetypePassword" placeholder="Re-type"
                                                       ng-model="password.retype"
                                                       ng-change="vm.passwordMatch(password.new, password.retype)">
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-if="empAccountPassword.empNewPassword.$valid == false">
                                                <span class="red-text text-lighten-1">Invalid password!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-show="vm.passLength == 1 && empAccountPassword.empNewPassword.$valid == true">
                                                <span class="red-text text-lighten-1">Must be at least 8 characters!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-if="vm.passwordValid == 2 && empAccountPassword.empNewPassword.$valid == true">
                                                <span class="red-text text-lighten-1">Passwords do not match!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4"
                                                 ng-if="vm.ajaxPassword == 1">
                                                <span class="red-text text-lighten-1">Failed!</span>
                                            </div>
                                            <div class="input-field col s4 offset-s4" id="passwordIncorrect">
                                            </div>
                                            <div class="input-field col s12">
                                                <a class="btn purple"
                                                   ng-if="vm.passwordValid == 0 || vm.passwordValid == 2" style="cursor: not-allowed; opacity: 0.3;"
                                                   ng-disabled="true">
                                                    Save Changes
                                                </a>
                                                <a class="btn waves-effect waves-light purple"
                                                   ng-if="vm.passwordValid == 1"
                                                   ng-click="vm.savePassword(password);">
                                                    Save Changes
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col s12">
            <button onclick="clickclick();">Click me!</button>
            </div>

        </div>
    </div>


</div>