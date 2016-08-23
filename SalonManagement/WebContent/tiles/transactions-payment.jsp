<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="paymentCtrl as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Payment</h3>
            <!--<a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"-->
            <!--href="#" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>-->
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="paymentSearch"
                                   placeholder="Search"
                                   class="grey-text text-darken-4"
                                   type="search"
                                   ng-model="paymentSearch">
                            <label for="paymentSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <table id="paymentTable" datatable="ng" dt-options="vm.dtOptions" dt-columns="vm.dtColumns"
                   class="table-barts hoverable z-depth-1 cell-border row-border display responsive-table highlight">
                <thead>
                </thead>
                <tfoot>
                <tr style="border: 1px solid #bdbdbd;">
                    <th class="left-align">Customer Name</th>
                    <th class="left-align">Transaction Name</th>
                    <th class="right-align">Transaction Date</th>
                    <th class="left-align">Transaction Type</th>
                    <th class="right-align">Total Balance</th>
                    <th class="right-align">Remaining Balance</th>
                    <th class="center-align">Action</th>
                </tr>
                </tfoot>
                <tbody>
                <tr ng-repeat="payment in vm.paymentList"
                    ng-if="payment.strStatus != 'COMPLETE'">
                    <td ng-if="payment.strName" class="left-align">{{ payment.strName }}</td>
                    <td ng-if="payment.customer" class="left-align">{{ payment.customer.strName }}</td>
                    <td ng-if="payment.strName" class="left-align">Product Order</td>
                    <td ng-if="payment.customer" class="left-align">Reservation</td>
                    <td ng-if="payment.strName" class="right-align">{{ payment.datCreated | date: "MMMM/d/yyyy" }}</td>
                    <td ng-if="payment.customer" class="right-align">{{ payment.dateCreated | date: "MMMM/d/yyyy" }}</td>
                    <td ng-if="payment.strName" class="left-align">
                        <span ng-if="payment.intType==1">DELIVERY</span>
                        <span ng-if="payment.intType==2">PICK UP</span>
                    </td>
                    <td ng-if="payment.customer" class="left-align">
                        <span ng-if="reserve.intReservationType==1">HOME SERVICE</span>
                        <span ng-if="reserve.intReservationType==2">EVENT</span>
                    </td>
                    <td ng-if="payment.strName" class="right-align">
                        {{ payment.invoice.dblTotalPrice | currency: "Php " }}
                    </td>
                    <td ng-if="payment.customer" class="right-align">
                        {{ payment.invoice.dblTotalPrice | currency: "Php " }}
                    </td>
                    <td ng-if="payment.strName" class="right-align">
                        {{ payment.invoice.dblRemainingBalance | currency: "Php " }}
                    </td>
                    <td ng-if="payment.customer" class="right-align">
                        {{ payment.invoice.dblRemainingBalance | currency: "Php " }}
                    </td>
                    <td class="center-align">
                        <button class="waves-effect waves-purple btn-flat transparent black-text"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Payment"
                                ng-click="vm.createPOPayment(payment, $index, vm.type[0].option1)"
                                ng-if="payment.strName">
                            <i class='material-icons medium'>payment</i>
                        </button>
                        <button class="waves-effect waves-purple btn-flat transparent black-text"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Payment"
                                ng-click="vm.createPOPayment(payment, $index, vm.type[0].option3)"
                                ng-if="payment.customer">
                            <i class='material-icons medium'>payment</i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div id="paymentModal" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1"
                ng-if="vm.paymentDetails.type == 'order'">
                Payment for {{vm.paymentDetails.strName}}
            </h4>
            <h4 class="grey-text center text-darken-1"
                ng-if="vm.paymentDetails.type == 'reservation'">
                Payment for {{vm.paymentDetails.customer.strName}}
            </h4>
            <div class="container">
                <div class="row">
                    <div class="input-field col s6">
                        <input type="hidden"
                               ng-model="vm.paymentDetails.index"
                               ng-if="vm.paymentDetails.type == 'order'"/>
                        <input type="hidden"
                               ng-model="vm.paymentDetails.index"
                               ng-if="vm.paymentDetails.type == 'reservation'"/>
                        <input type="text" id="paymentDetails.name" placeholder="Customer Name" readonly
                               ng-model="vm.paymentDetails.strName"
                               ng-if="vm.paymentDetails.type == 'order'">
                        <input type="text" id="paymentDetails.name" placeholder="Customer Name" readonly
                               ng-model="vm.paymentDetails.customer.strName"
                               ng-if="vm.paymentDetails.type == 'reservation'">
                        <label for="paymentDetails.name" class="active"><b>Customer Name</b></label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="paymentDetails.date" placeholder="Payment Date" readonly
                               ng-model="vm.paymentDetails.paymentCreated"
                               ng-if="vm.paymentDetails.type == 'order'"/>
                        <input type="text" id="paymentDetails.date" placeholder="Payment Date" readonly
                               ng-model="vm.paymentDetails.paymentCreated"
                               ng-if="vm.paymentDetails.type == 'reservation'"/>
                        <label for="paymentDetails.date" class="active"><b>Payment Date</b></label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="paymentDetails.totalBalance" class="right-align"
                               placeholder="Total Balance" readonly
                               ng-model="vm.paymentDetails.totalBalance"
                               ng-if="vm.paymentDetails.type == 'order'"/>
                        <input type="text" id="paymentDetails.totalBalance" class="right-align"
                               placeholder="Total Balance" readonly
                               ng-model="vm.paymentDetails.totalBalance"
                               ng-if="vm.paymentDetails.type == 'reservation'"/>
                        <label for="paymentDetails.totalBalance" class="active"><b>Total Balance</b></label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="paymentDetails.remainingBalance" class="right-align"
                               placeholder="Total Balance" readonly
                               ng-model="vm.paymentDetails.remainingBalance"
                               ng-if="vm.paymentDetails.type == 'order'"/>
                        <input type="text" id="paymentDetails.remainingBalance" class="right-align"
                               placeholder="Total Balance" readonly
                               ng-model="vm.paymentDetails.remainingBalance"
                               ng-if="vm.paymentDetails.type == 'reservation'"/>
                        <label for="paymentDetails.remainingBalance" class="active"><b>Remaining Balance</b></label>
                    </div>
                    <div class="input-field col s6">
                        <select id="paymentDetails.type"
                                ng-model="vm.paymentDetails.paymentType"
                                ng-if="vm.paymentDetails.type == 'order'"
                                ng-options="pay.name for pay in vm.paymentType track by pay.id">
                        </select>
                        <select id="paymentDetails.type"
                                ng-model="vm.paymentDetails.paymentType"
                                ng-if="vm.paymentDetails.type == 'reservation'"
                                ng-options="pay.name for pay in vm.paymentType track by pay.id">
                        </select>
                        <label><b>Payment Type</b>
                            <i class="material-icons tiny red-text">error_outline</i>
                        </label>
                    </div>
                    <div class="input-field col s6">
                        <input type="text" id="paymentDetails.paymentAmount" class="right-align"
                               placeholder="Payment Amount" required
                               ng-model="vm.paymentDetails.paymentAmount"
                               format="number"/>
                        <label for="paymentDetails.paymentAmount" class="active"><b>Payment Amount</b>
                            <i class="material-icons tiny red-text">error_outline</i>
                        </label>
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