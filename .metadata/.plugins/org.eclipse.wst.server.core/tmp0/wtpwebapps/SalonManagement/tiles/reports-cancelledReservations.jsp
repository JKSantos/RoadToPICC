<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="cancelledReservationController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class=" center light">Cancelled Reservation Reports</h3>
            <div class="row" style="margin-bottom: -100px !important; margin-top: 50px !important;">
                <div class="col s12">
                    <div class="input-field col s2">
                        <input input-date
                               type="text"
                               id="ngDateFrom"
                               placeholder="January/1/2016"
                               ng-model="vm.datFrom"
                               disable="disable"
                               today="today"
                               clear="clear"
                               close="close"
                               select-years="15"/>
                        <label for="ngDateFrom"><b>Date From</b></label>
                    </div>
                    <div class="input-field col s2">
                        <input input-date
                               ng-disabled="!vm.datFrom"
                               type="text"
                               id="ngDateTo"
                               placeholder="January/1/2016"
                               ng-model="vm.datTo"
                               disable="disable"
                               min="{{ vm.datFrom }}"
                               today="today"
                               clear="clear"
                               close="close"
                               select-years="15"/>
                        <label for="ngDateTo"><b>Date To</b></label>
                    </div>
                    <div class="input-field col s2">
                        <button class="waves-effect waves-light btn white-text purple"
                                ng-disabled="!vm.datTo"
                                ng-click="vm.searchReport(vm.datFrom, vm.datTo);">
                            <i class="material-icons">search</i>
                        </button>
                    </div>
                    <div class="input-field col s5 offset-s1">
                        <nav class="right white hoverable  z-depth-1"
                             style="margin-top: -20px !important;">
                            <div class="nav-wrapper col s12">
                                <form>
                                    <div class="input-field">
                                        <input id="productTagsSearch"
                                               placeholder="Search"
                                               class="grey-text text-darken-4"
                                               type="search"
                                               ng-model="vm.productTagsSearch"
                                               ng-change="vm.searchTable()">
                                        <label for="productTagsSearch">
                                            <i class="material-icons grey-text text-darken-4">search</i>
                                        </label>
                                    </div>
                                </form>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col s12" ng-if="vm.searchRep == 1">
                    <div class="col s12 right-align">
                        <button class="btn purple darken-2 white-text" title="DOWNLOAD PDF"
                                ng-click="vm.printPdf('printCancelledReservation');">PDF
                        </button>
                    </div>
                    <table id="productTagsTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                           style="margin-top: -40px !important;"
                           class="table-barts hoverable z-depth-1 cell-border row-border display responsive-table highlight">
                        <thead>
                        <tr>
                            <th class="left-align">Customer Name</th>
                            <th class="left-align">Customer Type</th>
                            <th class="left-align">Reservation Type</th>
                            <th class="right-align">Date Created</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr style="border: 1px solid #bdbdbd;">
                            <th class="left-align">Total Cancelled Reservations: {{vm.cr.length}}</th>
                            <th class="left-align"></th>
                            <th class="left-align"></th>
                            <th class="right-align"></th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <tr ng-repeat="cr in vm.cr">
                            <td class="left-align">{{cr.customer.strName}}</td>
                            <td class="left-align">{{cr.customer.strCustomerType}}</td>
                            <td class="left-align" ng-if="cr.intReservationType == 1">HOME SERVICE</td>
                            <td class="left-align" ng-if="cr.intReservationType == 2">EVENT</td>
                            <td class="right-align">{{cr.dateCreated | date: 'MMMM d, yyyy'}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row" id="printCancelledReservation" style="display: none;">
                <h5 class="center"><img src="img/logo.png" alt="sms" style="height: 50px;"> Salon Management System -
                    Report for {{vm.thisDate | date: "MMMM d, yyyy 'at' h:mma"}}</h5>
                <h3 class="center">Cancelled Reservations</h3>
                <div class="col s12">
                    <table class="table-barts cell-border row-border display">
                        <tbody>
                        <tr class="border: 1px black;">
                            <td class="left-align"><b>Customer Name</b></td>
                            <td class="left-align"><b>Customer Type</b></td>
                            <td class="left-align"><b>Reservation Type</b></td>
                            <td class="right-align"><b>Date Created</b></td>
                        </tr>
                        <tr ng-repeat="cr in vm.cr" style="border: 1px black !important;">
                            <td class="left-align">{{cr.customer.strName}}</td>
                            <td class="left-align">{{cr.customer.strCustomerType}}</td>
                            <td class="left-align" ng-if="cr.intReservationType == 1">HOME SERVICE</td>
                            <td class="left-align" ng-if="cr.intReservationType == 2">EVENT</td>
                            <td class="right-align">{{cr.dateCreated | date: 'MMMM d, yyyy'}}</td>
                        </tr>
                        <tr>
                            <td class="left-align"><b>Total Cancelled Reservations: {{vm.cr.length}}</b></td>
                            <td class="left-align"></td>
                            <td class="left-align"></td>
                            <td class="right-align"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


</div>