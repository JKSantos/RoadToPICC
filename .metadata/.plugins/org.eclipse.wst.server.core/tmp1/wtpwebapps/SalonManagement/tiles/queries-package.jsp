<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="packageQueryController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Query - Package</h3>
            <div class="row" style="margin-bottom: -100px !important; margin-top: 50px !important;">
                <div class="col s12">
                    <div class="input-field col s3">
                        <select ng-model="vm.selType" id="selectType"
                                ng-change="vm.selectTypeInTable()">
                            <option value="" selected>ALL</option>
                            <option ng-repeat="type in vm.type"
                                    value="{{type}}">{{type | uppercase}}
                            </option>
                        </select>
                        <label for="selectType"><b>Type</b></label>
                    </div>
                    <div class="input-field col s3">
                        <select ng-model="vm.selStatus" id="selectStatus"
                                ng-change="vm.selectStatusInTable()">
                            <option value="" selected>ALL</option>
                            <option ng-repeat="status in vm.status"
                                    value="{{status}}">{{status}}
                            </option>
                        </select>
                        <label for="selectStatus"><b>Status</b></label>
                    </div>
                    <div class="input-field col s5 offset-s1">
                        <nav class="right white hoverable  z-depth-1"
                             style="margin-top: -20px !important;">
                            <div class="nav-wrapper col s12">
                                <form>
                                    <div class="input-field">
                                        <input id="queryPackage" placeholder="Search" class="grey-text text-darken-4"
                                               type="search"
                                               ng-model="vm.queryPackageSearch"
                                               ng-change="vm.searchTable()">
                                        <label for="queryPackage">
                                            <i class="material-icons grey-text text-darken-4">search</i>
                                        </label>
                                    </div>
                                </form>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="col s12">
                <table id="packageQueryTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                       class="table-barts row-border hoverable cell-border responsive z-depth-1"
                       style="margin-top: -20px !important;">
                    <thead>
                    <tr>
                        <th class="left-align">Name</th>
                        <th class="left-align">Type</th>
                        <th class="left-align">Description</th>
                        <th class="right-align">Price</th>
                        <th class="left-align">Status</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr style="border: 1px solid #bdbdbd;">
                        <th class="left-align">Name</th>
                        <th class="left-align">Type</th>
                        <th class="left-align">Description</th>
                        <th class="right-align">Price</th>
                        <th class="left-align">Status</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <tr ng-repeat="package in vm.packageList">
                        <td class="left-align" style="width: 200px !important;"
                            title="{{ package.strPackageName | uppercase }}">
                            {{ package.strPackageName | uppercase }}
                        </td>
                        <td class="left-align" style="width: 150px !important;"
                            ng-if="package.intPackageType == 1">
                            EVENT
                        </td>
                        <td class="left-align" style="width: 150px !important;"
                            ng-if="package.intPackageType == 2">
                            HOME SERVICE
                        </td>
                        <td class="left-align" style="width: 150px !important;"
                            ng-if="package.intPackageType == 3">
                            WALK IN
                        </td>
                        <td class="left-align" style="width: 150px !important;"
                            ng-if="package.intPackageType == 4">
                            EVENT <br>
                            HOME SERVICE
                        </td>
                        <td class="left-align" style="width: 150px !important;"
                            ng-if="package.intPackageType == 5">
                            EVENT <br>
                            WALK IN
                        </td>
                        <td class="left-align" style="width: 150px !important;"
                            ng-if="package.intPackageType == 6">
                            HOME SERVICE <br>
                            WALK IN
                        </td>
                        <td class="left-align" style="width: 150px !important;"
                            ng-if="package.intPackageType == 7">
                            EVENT <br>
                            HOME SERVICE <br>
                            WALK IN
                        </td>
                        <td class="left-align">{{ package.strPackageDesc | uppercase }}</td>
                        <td class="right-align">{{ package.dblPackagePrice | currency: "Php " }}</td>
                        <td class="left-align" ng-if="package.intPackageStatus == 0">INACTIVE</td>
                        <td class="left-align" ng-if="package.intPackageStatus == 1">ACTIVE</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>


</div>