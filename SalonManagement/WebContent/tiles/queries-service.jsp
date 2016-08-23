<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="serviceQueryController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Query - Service</h3>
            <div class="row" style="margin-bottom: -100px !important; margin-top: 50px !important;">
                <div class="col s12">
                    <div class="input-field col s3">
                        <select ng-model="vm.selCategory" id="selectCategory"
                                ng-change="vm.selectCategoryInTable()">
                            <option value="" selected>ALL</option>
                            <option ng-repeat="category in vm.category | unique: 'strServiceCategory'"
                                    value="{{category}}">{{category | uppercase}}</option>
                        </select>
                        <label for="selectCategory"><b>Category</b></label>
                    </div>
                    <div class="input-field col s3">
                        <select ng-model="vm.selStatus" id="selectStatus"
                                ng-change="vm.selectStatusInTable()">
                            <option value="" selected>ALL</option>
                            <option ng-repeat="status in vm.servStatus | unique: 'intServiceStatus'"
                                    ng-if="status == 1"
                                    value="{{status}}">ACTIVE</option>
                            <option ng-repeat="status in vm.servStatus | unique: 'intServiceStatus'"
                                    ng-if="status == 0"
                                    value="{{status}}">INACTIVE</option>
                        </select>
                        <label for="selectStatus"><b>Status</b></label>
                    </div>
                    <div class="input-field col s5 offset-s1">
                        <nav class="right white hoverable  z-depth-1"
                             style="margin-top: -20px !important;">
                            <div class="nav-wrapper col s12">
                                <form>
                                    <div class="input-field">
                                        <input id="querySearch" placeholder="Search" class="grey-text text-darken-4"
                                               type="search"
                                               ng-model="vm.queryServiceSearch"
                                               ng-change="vm.searchTable()">
                                        <label for="querySearch">
                                            <i class="material-icons grey-text text-darken-4">search</i></label>
                                    </div>
                                </form>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>

            <div class="col s12">
                <table id="serviceQueryTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                       class="table-barts row-border hoverable cell-border responsive z-depth-1"
                       style="margin-top: -20px !important;">
                    <thead>
                    <tr>
                        <th class="left-align">Name</th>
                        <th class="left-align">Category</th>
                        <th class="left-align">Detail</th>
                        <th class="right-align">Price</th>
                        <th class="left-align">Status</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr style="border: 1px solid #bdbdbd;">
                        <th class="left-align">Name</th>
                        <th class="left-align">Category</th>
                        <th class="left-align">Detail</th>
                        <th class="right-align">Price</th>
                        <th class="left-align">Status</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <tr ng-repeat="service in vm.serviceList">
                        <td class="left-align" style="width: 200px !important;"
                            title="{{ service.strServiceName | uppercase }}">
                            {{ service.strServiceName | uppercase}}
                        </td>
                        <td class="left-align" style="width: 200px !important;">
                            {{ service.strServiceCategory | uppercase }}
                        </td>
                        <td class="left-align">{{ service.strServiceDesc }}</td>
                        <td class="right-align">{{ service.dblServicePrice | currency: "Php " }}</td>
                        <td class="left-align" ng-if="service.intServiceStatus == 0">INACTIVE</td>
                        <td class="left-align" ng-if="service.intServiceStatus == 1">ACTIVE</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>


</div>