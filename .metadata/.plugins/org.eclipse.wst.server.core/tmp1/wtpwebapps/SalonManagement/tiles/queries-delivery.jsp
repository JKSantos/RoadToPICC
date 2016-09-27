<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="deliveryQueryController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Query - Delivery Charge</h3>
            <div class="row" style="margin-bottom: -100px !important; margin-top: 50px !important;">
                <div class="col s12">
                    <div class="input-field col s3">
                        <select ng-model="vm.selCity" id="selectCity"
                                ng-change="vm.selectCityInTable()">
                            <option value="" selected>ALL</option>
                            <option ng-repeat="cat in vm.category | unique: 'strCity'"
                                    value="{{cat}}">{{cat | uppercase}}
                            </option>
                        </select>
                        <label for="selectCity"><b>City</b></label>
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
                                        <input id="queryDelivery" placeholder="Search" class="grey-text text-darken-4"
                                               type="search"
                                               ng-model="vm.queryDeliverySearch"
                                               ng-change="vm.searchTable()">
                                        <label for="queryDelivery">
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
                <table id="deliveryQueryTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                       class="table-barts row-border hoverable cell-border responsive z-depth-1"
                       style="margin-top: -20px !important;">
                    <thead>
                    <tr>
                        <th class="left-align">Barangay</th>
                        <th class="left-align">City</th>
                        <th class="right-align">Base Rate</th>
                        <th class="center">Status</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr style="border: 1px solid #bdbdbd;">
                        <th class="left-align">Barangay</th>
                        <th class="left-align">City</th>
                        <th class="right-align">Base Rate</th>
                        <th class="center">Status</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <tr ng-repeat="location in vm.locationList">
                        <td class="left-align">{{ location.strBarangay | uppercase }}</td>
                        <td class="left-align">{{ location.strCity | uppercase }}</td>
                        <td class="right-align">{{ location.dblLocationPrice | currency: "Php " }}</td>
                        <td class="center" ng-if="location.intLocationStatus == 0">INACTIVE</td>
                        <td class="center" ng-if="location.intLocationStatus == 1">ACTIVE</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>


</div>