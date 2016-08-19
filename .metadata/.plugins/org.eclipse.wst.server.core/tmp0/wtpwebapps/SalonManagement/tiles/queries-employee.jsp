<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="empQueryController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Query</h3>
            <!--<a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"-->
               <!--href="#" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>-->
            <div class="row">
                <div class="input-field col s3">
                    <select ng-model="vm.selOption">
                        <option ng-repeat="position in vm.position" value="{{position}}">{{position}}</option>
                    </select>
                </div>
            </div>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="querySearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                    ng-model="queryEmployeeSearch">
                            <label for="querySearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <div class="col s12">
                <table id="employeeQueryTable" datatable="ng" dt-options="vm.dtOptions" dt-column-defs="vm.dtColumnDefs"
                   class="row-border hoverable cell-border responsive z-depth-1" rowspan="10"
                   style="margin-top: -20px !important;">
                <thead>
                <tr>
                    <th class="left-align">Name</th>
                    <th class="left-align">Position</th>
                    <th class="right-align">Contact</th>
                    <th class="left-align">Email</th>
                    <th class="left-align">Address</th>
                    <th class="left-align">Status</th>
                </tr>
                </thead>
                <tfoot>
                <tr style="border: 1px solid #bdbdbd;">
                    <th class="left-align">Name</th>
                    <th class="left-align">Position</th>
                    <th class="right-align">Contact</th>
                    <th class="left-align">Email</th>
                    <th class="left-align">Address</th>
                    <th class="left-align">Status</th>
                </tr>
                </tfoot>
                <tbody>
                <tr ng-repeat="employee in vm.employeeList | filter: filter.aiFilter
                                                           | filter: queryEmployeeSearch">
                    <td class="left-align" style="width: 300px !important;"
                        title="{{ employee.strEmpFirstName + ' ' + employee.strEmpMiddleName + ' ' + employee.strEmpLastName }}">
                        {{ employee.strEmpFirstName + ' ' + employee.strEmpMiddleName + ' ' + employee.strEmpLastName }}
                    </td>
                    <td class="left-align" style="width: 300px !important;">
                        {{ employee.jobQualification.strJobDesc}},
                    </td>
                    <td class="right-align">{{ employee.strEmpContactNo }}</td>
                    <td class="left-align" style="width: 100px !important;">{{ employee.strEmpEmail }}</td>
                    <td class="left-align">{{ employee.strEmpAddress }}</td>
                    <td class="left-align"
                        ng-if="employee.strEmpStatus == 'I'">
                        INACTIVE
                    </td>
                    <td class="left-align"
                        ng-if="employee.strEmpStatus == 'A'">
                        ACTIVE
                    </td>
                </tr>
                </tbody>
            </table>
            </div>

        </div>
    </div>

    <div id="paymentModal" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Payment for {{vm.paymentDetails.strName}}</h4>
            <div class="container">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="hidden" ng-model="vm.paymentDetails.index">
                        <input type="text" id="paymentDetails.name" placeholder="Customer Name" readonly
                               ng-model="vm.paymentDetails.strName">
                        <label for="paymentDetails.name" class="active"><b>Customer Name</b></label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="paymentDetails.date" placeholder="Payment Date" readonly
                               ng-model="vm.paymentDetails.paymentCreated"/>
                        <label for="paymentDetails.date" class="active"><b>Payment Date</b></label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="paymentDetails.totalBalance" class="right-align" placeholder="Total Balance" readonly
                               ng-model="vm.paymentDetails.totalBalance"/>
                        <label for="paymentDetails.totalBalance" class="active"><b>Total Balance</b></label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="paymentDetails.remainingBalance" class="right-align" placeholder="Total Balance" readonly
                               ng-model="vm.paymentDetails.remainingBalance"/>
                        <label for="paymentDetails.remainingBalance" class="active"><b>Remaining Balance</b></label>
                    </div>
                    <div class="input-field col s6">
                        <select id="paymentDetails.type"
                                ng-model="vm.paymentDetails.paymentType">
                            <option value="{{vm.paymentType[0].option1}}" selected>Full Payment</option>
                            <option ng-if="vm.paymentDetails.type == 'reservation'"
                                    value="{{vm.paymentType[0].option2}}">Down Payment</option>
                            <option ng-if="vm.paymentDetails.type=='reservation' || vm.paymentDetails.type=='walkin'"
                                    value="{{vm.paymentType[0].option2}}">Complementary Payment</option>
                        </select>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="paymentDetails.paymentAmount" class="right-align" placeholder="Payment Amount" required
                               ng-model="vm.paymentDetails.paymentAmount"
                               format="number"/>
                        <label for="paymentDetails.paymentAmount" class="active"><b>Payment Amount</b>
                            <i class="material-icons tiny red-text">error_outline</i></label>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="red-text btn-flat transparent left" disabled
                    style="margin:0px !important; padding:0px !important;"><i
                    class="material-icons">error_outline</i>&nbspRequired field
            </button>
            <button type="submit" value="Submit" id="paymentDetails.submit"
                    class="waves-effect waves-light white-text btn-flat purple"
                    style="margin-left:3px; margin-right:3px;"
                    ng-click="vm.paymentSubmit(vm.paymentDetails)">CREATE
            </button>
        </div>
    </div>


</div>