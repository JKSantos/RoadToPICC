<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="productTagsReportController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class=" center light">Product Tags Reports</h3>
            <div class="row" style="margin-bottom: -100px !important; margin-top: 50px !important;">
                <div class="col s12">
                    <div class="input-field col s3">
                        <select id="selectStatus">
                            <option value="" selected>ALL</option>
                        </select>
                        <label for="selectStatus"><b>Status</b></label>
                    </div>
                    <div class="input-field col s5 offset-s4">
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
                <div class="col s12">
                    <div class="col s12 right-align">
                        <button class="btn purple darken-2 white-text" title="DOWNLOAD PDF"
                                ng-click="vm.printPdf('printProductTags');">PDF</button>
                    </div>
                    <table id="productTagsTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                           style="margin-top: -40px !important;"
                           class="table-barts hoverable z-depth-1 cell-border row-border display responsive-table highlight">
                        <thead>
                        <th class="left-align">Name</th>
                        <th class="right-align">Date</th>
                        <th class="right-align">Quantity</th>
                        <th class="right-align">Consumed</th>
                        <th class="right-align">Defective</th>
                        <th class="right-align">Expired</th>
                        <th class="right-align">Lost</th>
                        </thead>
                        <tfoot>
                        <tr style="border: 1px solid #bdbdbd;">
                            <th class="left-align">Name</th>
                            <th class="right-align">Date</th>
                            <th class="right-align">Quantity</th>
                            <th class="right-align">Consumed</th>
                            <th class="right-align">Defective</th>
                            <th class="right-align">Expired</th>
                            <th class="right-align">Lost</th>
                        </tr>
                        </tfoot>
                        <tbody>
                        <tr ng-repeat="tags in vm.productTags">
                            <td>{{tags.strProductName}}</td>
                            <td class="right-align">date</td>
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
                <h5 class="center"><img src="img/logo.png" alt="sms" style="height: 50px;"> Salon Management System - Report for {{vm.thisDate |  date: "MMMM d, yyyy"}}</h5>
                <h3 class="center">Product Tags</h3>
                <div class="col s12">
                    <table class="table-barts cell-border row-border display">
                        <thead>
                        <th class="left-align">Name</th>
                        <th class="right-align">Date</th>
                        <th class="right-align">Quantity</th>
                        <th class="right-align">Consumed</th>
                        <th class="right-align">Defective</th>
                        <th class="right-align">Expired</th>
                        <th class="right-align">Lost</th>
                        </thead>
                        <tbody>
                        <tr ng-repeat="tags in vm.productTags" style="border: 1px black;">
                            <td>{{tags.strProductName}}</td>
                            <td class="right-align">date</td>
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