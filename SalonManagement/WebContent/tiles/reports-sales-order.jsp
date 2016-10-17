<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="prodPurchase as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class=" center light">Product Purchase Reports</h3>
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
                                ng-click="vm.printPdf('printProductPurchases');">PDF
                        </button>
                    </div>
                    <table id="posTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                           style="margin-top: -40px !important;"
                           class="table-barts hoverable z-depth-1 cell-border row-border display responsive-table highlight">
                        <thead>
                        <tr>
                            <th class="left-align">Product Name</th>
                            <th class="right-align">Quantity</th>
                            <th class="left-align">Transaction Name</th>
                            <th class="left-align">Transaction Type</th>
                            <th class="right-align">Transaction Date</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr style="border: 1px solid #bdbdbd;">
                            <th class="left-align">Product Name</th>
                            <th class="right-align">Quantity</th>
                            <th class="left-align">Transaction Name</th>
                            <th class="left-align">Transaction Type</th>
                            <th class="right-align">Transaction Date</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <tr ng-repeat="pos in vm.pos">
                            <td class="left-align">{{pos.productName}}</td>
                            <td class="right-align">{{pos.quantity}}</td>
                            <td class="left-align">{{pos.transactionName}}</td>
                            <td class="left-align">{{pos.transactionType}}</td>
                            <td class="right-align">{{pos.transactionDate | date: 'MMMM d, yyyy'}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row" id="printProductPurchases" style="display: none;">
                <h5 class="center"><img src="img/logo.png" alt="sms" style="height: 50px;"> Salon Management System -
                    Report for {{vm.thisDate | date: "MMMM d, yyyy 'at' h:mma"}}</h5>
                <h3 class="center">Product Purchases</h3>
                <div class="col s12">
                    <table class="table-barts cell-border row-border display">
                        <tbody>
                        <tr style="border: 1px black !important;">
                            <td class="left-align"><b>Product Name</b></td>
                            <td class="right-align"><b>Quantity</b></td>
                            <td class="left-align"><b>Transaction Name</b></td>
                            <td class="left-align"><b>Transaction Type</b></td>
                            <td class="right-align"><b>Transaction Date</b></td>
                        </tr>
                        <tr ng-repeat="pos in vm.pos" style="border: 1px black !important;">
                            <td class="left-align">{{pos.productName}}</td>
                            <td class="right-align">{{pos.quantity}}</td>
                            <td class="left-align">{{pos.transactionName}}</td>
                            <td class="left-align">{{pos.transactionType}}</td>
                            <td class="right-align">{{pos.transactionDate | date: 'MMMM d, yyyy'}}</td>
                        </tr>
                        <tr style="border: 1px solid #bdbdbd;">
                            <td class="left-align"><b>Product Name</b></td>
                            <td class="right-align"><b>Quantity</b></td>
                            <td class="left-align"><b>Transaction Name</b></td>
                            <td class="left-align"><b>Transaction Type</b></td>
                            <td class="right-align"><b>Transaction Date</b></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


</div>