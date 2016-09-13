<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="walkinCtrl as vm" style="margin-top: 5px !important;">
    <div class="aside asideAside1 z-depth-barts z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="row">
            <div class="col s12">
                <a href="transWalkIn" class="left btn-flat purple white-text waves-effect waves-light"
                   style="margin-top: 20px;">
                    <i class="material-icons large">keyboard_arrow_left</i>
                </a>
                <h4 class="grey-text center text-darken-1">Walk In</h4>
            </div>
        </div>
        <div class="row" style="margin-bottom: -30px !important;">
            <div class="col s12">
                <div class="input-field col s3">
                    <select id="walkinTableFilter" ng-model="vm.walkinTableFilter">
                        <option value="walkin" selected>Walkin</option>
                        <option value="appointment">Appointment</option>
                    </select>
                    <label for="walkinTableFilter"><b>Select</b></label>
                </div>
                <div class="input-field col s4">
                    <a class="crWalkin z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
                       href="#createWalkinModal" style="margin-left: 15px;">
                        <i class="material-icons">add</i>
                    </a>
                    <a class="z-depth-1 hoverable waves-effect waves-light btn purple darken-2 left white-text"
                       href="transWalkinTable" style="margin-left: 15px;">
                        <i class="material-icons">border_all</i>
                    </a>
                </div>
                <nav class="right white hoverable z-depth-1" style="width: 300px; margin-right: 20px;">
                    <div class="nav-wrapper col s12">
                        <form>
                            <div class="input-field">
                                <input id="searchBoxID" placeholder="Search" class="grey-text text-darken-4"
                                       type="search"
                                       ng-model="vm.searchInTable"
                                       ng-change="vm.searchTable()">
                                <label for="searchBoxID">
                                    <i class="material-icons grey-text text-darken-4">search</i>
                                </label>
                            </div>
                        </form>
                    </div>
                </nav>
            </div>


        </div>
        <table id="walkinRecordTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
               class="row-border hoverable cell-border z-depth-1" width="100%"
               ng-show="vm.walkinTableFilter == 'walkin'"
               style="margin-top: -15px !important;">
            <thead>
            <tr>
                <th class="left-align">Name</th>
                <th class="right-align">Contact</th>
                <th class="left-align">Type</th>
                <th class="left-align">Status</th>
                <th class="center-align">Action</th>
            </tr>
            </thead>
            <tfoot>
            <tr style="border: 1px solid #bdbdbd;">
                <th class="left-align">Name</th>
                <th class="right-align">Contact</th>
                <th class="left-align">Type</th>
                <th class="left-align">Status</th>
                <th class="center-align">Action</th>
            </tr>
            </tfoot>
            <tbody>
            <tr ng-repeat="walkin in vm.walkinList">
                <td class="left-align">{{walkin.strName | uppercase}}</td>
                <td class="right-align">{{walkin.strContactNo}}</td>
                <td class="left-align">{{walkin.strWalkInType | uppercase}}</td>
                <td class="left-align">{{walkin.strWalkInStatus | uppercase}}</td>
                <td class="center-align">
                    <button class="waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                            style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Deactivate"
                            ng-if="walkin.strWalkInStatus=='PENDING'"
                            ng-click="deactivateOrder(order)">
                        <i class='material-icons'>delete</i>
                    </button>
                    <button class="btn-flat transparent red-text text-lighten-4"
                            style="padding-left: 10px;padding-right:10px; margin: 5px;"
                            ng-if="walkin.strWalkInStatus=='COMPLETE'"
                            ng-disabled=true>
                        <i class='material-icons'>delete</i>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
        <table id="walkinAppointmentRecordTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
               class="row-border hoverable cell-border z-depth-1" width="100%"
               ng-show="vm.walkinTableFilter == 'appointment'"
               style="margin-top: -15px !important;">
            <thead>
            <tr>
                <th class="left-align">Name</th>
                <th class="right-align">Contact</th>
                <th class="right-align">Date</th>
                <th class="right-align">Time</th>
                <th class="left-align">Type</th>
                <th class="left-align">Status</th>
                <th class="center-align">Action</th>
            </tr>
            </thead>
            <tfoot>
            <tr style="border: 1px solid #bdbdbd;">
                <th class="left-align">Name</th>
                <th class="right-align">Contact</th>
                <th class="right-align">Date</th>
                <th class="right-align">Time</th>
                <th class="left-align">Type</th>
                <th class="left-align">Status</th>
                <th class="center-align">Action</th>
            </tr>
            </tfoot>
            <tbody>
            <tr ng-repeat="walkin in vm.walkinList">
                <td class="left-align">{{walkin.strName | uppercase}}</td>
                <td class="right-align">{{walkin.strContactNo}}</td>
                <th class="right-align">Date</th>
                <th class="right-align">Time</th>
                <td class="left-align">{{walkin.strWalkInType | uppercase}}</td>
                <td class="left-align">{{walkin.strWalkInStatus | uppercase}}</td>
                <td class="center-align">
                    <button class="waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                            style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Deactivate"
                            ng-if="walkin.strWalkInStatus=='PENDING'"
                            ng-click="deactivateOrder(order)">
                        <i class='material-icons'>delete</i>
                    </button>
                    <button class="btn-flat transparent red-text text-lighten-4"
                            style="padding-left: 10px;padding-right:10px; margin: 5px;"
                            ng-if="walkin.strWalkInStatus=='COMPLETE'"
                            ng-disabled=true>
                        <i class='material-icons'>delete</i>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
