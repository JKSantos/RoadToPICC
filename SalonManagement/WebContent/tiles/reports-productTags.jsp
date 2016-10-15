<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="productTagsReportController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class=" center light">Product Tags Reports</h3>
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
                <div class="col s12"
                     ng-if="vm.searchRep == 1">
                    <div class="col s12 right-align">
                        <button class="btn purple darken-2 white-text" title="DOWNLOAD PDF"
                                ng-click="vm.printPdf('printProductTags', 'printProductTagsTotal');">PDF
                        </button>
                    </div>
                    <table id="productTagsTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                           style="margin-top: -40px !important;"
                           class="table-barts hoverable z-depth-1 cell-border row-border display responsive-table highlight">
                        <thead>
                        <th class="left-align">Product Name</th>
                        <th class="left-align">Type</th>
                        <th class="left-align">Tagged By</th>
                        <th class="right-align">Quantity</th>
                        <th class="right-align">Tagged Date</th>
                        </thead>
                        <tfoot>
                        <tr style="border: 1px solid #bdbdbd;">
                            <th class="left-align">Product Name</th>
                            <th class="left-align">Type</th>
                            <th class="left-align">Tagged By</th>
                            <th class="right-align">Quantity</th>
                            <th class="right-align">Tagged Date</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <tr ng-repeat="tags in vm.productTag">
                            <th style="font-weight: normal !important;" class="left-align">{{tags.strProductName}}</th>
                            <th style="font-weight: normal !important;" class="left-align">{{tags.tagType}}</th>
                            <th style="font-weight: normal !important;" class="left-align">{{tags.strEmployee}}</th>
                            <th style="font-weight: normal !important;" class="right-align">{{tags.intQuantity}}</th>
                            <th style="font-weight: normal !important;" class="right-align">{{tags.datDateTagged | date:
                                "MMM d, y 'at' h:mma"}}
                            </th>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col s12" ng-if="vm.searchRep == 1">
                    <h5>Total</h5>
                    <table id="productTagsTableTotal" datatable="ng"
                           style="margin-top: -40px !important;"
                           class="table-barts hoverable z-depth-1 cell-border row-border display responsive-table highlight">
                        <thead>
                        <th class="left-align">Name</th>
                        <th class="right-align">Quantity</th>
                        <th class="right-align">Consumed</th>
                        <th class="right-align">Defective</th>
                        <th class="right-align">Expired</th>
                        <th class="right-align">Lost</th>
                        </thead>
                        <tfoot>
                        <tr style="border: 1px solid #bdbdbd;">
                            <th class="left-align">Total</th>
                            <th class="right-align">{{vm.totalq}}</th>
                            <th class="right-align">{{vm.totalc}}</th>
                            <th class="right-align">{{vm.totald}}</th>
                            <th class="right-align">{{vm.totale}}</th>
                            <th class="right-align">{{vm.totall}}</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <tr ng-repeat="tags in vm.productTags">
                            <td>{{tags.strProductName}}</td>
                            <td class="right-align">{{tags.intQuantity}}</td>
                            <td class="right-align">{{tags.intConsumed}}</td>
                            <td class="right-align">{{tags.intDefective}}</td>
                            <td class="right-align">{{tags.intExpired}}</td>
                            <td class="right-align">{{tags.intLost}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row" id="printProductTags" style="display: none;">
                <h5 class="center"><img src="img/logo.png" alt="sms" style="height: 50px;"> Salon Management System -
                    Report for {{vm.thisDate | date: "MMMM d, yyyy 'at' h:mma"}}</h5>
                <h3 class="center">Product Tags</h3>
                <div class="col s12">
                    <table class="table-barts cell-border row-border display">
                        <thead>
                        <th class="left-align">Product Name</th>
                        <th class="left-align">Type</th>
                        <th class="left-align">Tagged By</th>
                        <th class="right-align">Quantity</th>
                        <th class="right-align">Tagged Date</th>
                        </thead>
                        <tbody>
                        <tr ng-repeat="tags in vm.productTag" style="border: 1px black !important;">
                            <td class="left-align">{{tags.strProductName}}</td>
                            <td class="left-align">{{tags.tagType}}</td>
                            <td class="left-align">{{tags.strEmployee}}</td>
                            <td class="right-align">{{tags.intQuantity}}</td>
                            <td class="right-align">{{tags.datDateTagged | date:"MMM d, y 'at' h:mma"}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>


            <div class="row" id="printProductTagsTotal" style="display: none;">
                <div class="col s12">
                    <h4>Total</h4>
                    <table class="table-barts cell-border row-border display">
                        <thead>
                        <th class="left-align">Name</th>
                        <th class="right-align">Quantity</th>
                        <th class="right-align">Consumed</th>
                        <th class="right-align">Defective</th>
                        <th class="right-align">Expired</th>
                        <th class="right-align">Lost</th>
                        </thead>
                        <tbody>
                        <tr ng-repeat="tags in vm.productTags" style="border: 1px black;">
                            <td>{{tags.strProductName}}</td>
                            <td class="right-align">{{tags.intQuantity}}</td>
                            <td class="right-align">{{tags.intConsumed}}</td>
                            <td class="right-align">{{tags.intDefective}}</td>
                            <td class="right-align">{{tags.intExpired}}</td>
                            <td class="right-align">{{tags.intLost}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


</div>