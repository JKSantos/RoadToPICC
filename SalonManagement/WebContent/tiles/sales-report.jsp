<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="salesReportController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Sales Report</h3>
            <!--<a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"-->
               <!--href="#" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>-->
            <div class="row" style="margin-top: 40px !important;">
                <div class="col s3">
                    <input type="text" id="filterDateFrom" ng-model="filter.DateFrom" placeholder="Date From"/>
                    <label for="filterDateFrom" class="active"><b>Date From</b></label>
                </div>
                <div class="col s3">
                    <input type="text" id="filterDateTo" ng-model="filter.DateTo" placeholder="Date To"/>
                    <label for="filterDateTo" class="active"><b>Date To</b></label>
                </div>
            </div>        
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="salesReportSearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                    ng-model="queryEmployeeSearch">
                            <label for="salesReportSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <div class="col s12" style="margin-top: -10px !important;">
                <table id="salesReportTable" datatable="ng" dt-options="vm.dtOptions" dt-column-defs="vm.dtColumnDefs"
                   class="row-border hoverable cell-border responsive z-depth-1" rowspan="10"
                   style="margin-top: -20px !important;">
                <thead>
                <tr>
                    <th class="left-align">Customer Name</th>
                    <th class="right-align">Transaction Date</th>
                    <th class="left-align">Transaction Type</th>
                    <th class="right-align">Total Price</th>
                    <th class="left-align">Status</th>
                </tr>
                </thead>
                <tfoot>
                <tr style="border: 1px solid #bdbdbd;">
                    <th class="left-align">Customer Name</th>
                    <th class="right-align">Transaction Date</th>
                    <th class="left-align">Transaction Type</th>
                    <th class="right-align">Total Price</th>
                    <th class="left-align">Status</th>
                </tr>
                </tfoot>
                <tbody>
                <tr ng-repeat="order in vm.orderList"
                    ng-if="order.strStatus == 'COMPLETE'">
                    <td class="left-align"
                        title="{{ order.strName }}">
                        {{ order.strName }}
                    </td>
                    <td class="right-align">
                        {{ order.datCreated | date: 'MMMM/d/yyyy' }}
                    </td>
                    <td class="left-align" ng-if="order.intType == 1">DELIVERY</td>
                    <td class="left-align" ng-if="order.intType == 2">PICK UP</td>
                    <td class="left-align">{{ order.invoice.dblTotalPrice | currency: "Php " }}</td>
                    <td class="left-align">{{ order.strStatus }}</td>
                </tr>
                </tbody>
            </table>
            </div>

        </div>
    </div>



</div>