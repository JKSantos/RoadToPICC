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
                        <option value="walkin" selected>WALKIN</option>
                        <option value="appointment">APPOINTMENT</option>
                    </select>
                    <label for="walkinTableFilter"><b>Select</b></label>
                </div>
                <div class="input-field col s3">
                    <select id="completePendingFilter"
                            ng-model="vm.completePendingFilter"
                            ng-change="vm.completePendingFunc()">
                        <option value="" selected>ALL</option>
                        <option value="pending">PENDING</option>
                        <option value="complete">COMPLETE</option>
                    </select>
                    <label for="walkinTableFilter"><b>Status</b></label>
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
        <div ng-show="vm.walkinTableFilter == 'walkin'">
            <table id="walkinRecordTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                   class="row-border hoverable cell-border z-depth-1" width="100%"
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
                <tr ng-repeat="walkin in vm.walkinList"
                    ng-if="walkin.strWalkInType == 'WALKIN'">
                    <td class="left-align">{{walkin.strName | uppercase}}</td>
                    <td class="right-align">{{walkin.strContactNo}}</td>
                    <td class="left-align">{{walkin.strWalkInType | uppercase}}</td>
                    <td class="left-align">{{walkin.strWalkInStatus | uppercase}}</td>
                    <td class="center-align">
                        <button class="waves-effect waves-purple btn-flat transparent black-text text-accent-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="View"
                                ng-if="walkin.strWalkInStatus=='PENDING'"
                                ng-click="viewWalkin(walkin)">
                            <i class='material-icons'>visibility</i>
                        </button>
                        <button class="waves-effect waves-purple btn-flat transparent black-text text-accent-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="View"
                                ng-if="walkin.strWalkInStatus=='COMPLETE'"
                                ng-click="viewWalkin(walkin)">
                            <i class='material-icons'>visibility</i>
                        </button>
                        <button class="waves-effect waves-purple btn-flat transparent black-text text-accent-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Update"
                                ng-if="walkin.strWalkInStatus=='PENDING'"
                                ng-click="vm.editWalkin(walkin)">
                            <i class='material-icons'>edit</i>
                        </button>
                        <button class="waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Deactivate"
                                ng-if="walkin.strWalkInStatus=='PENDING'"
                                ng-click="deactivateWalkin(walkin)">
                            <i class='material-icons'>delete</i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div ng-show="vm.walkinTableFilter == 'appointment'">
            <table id="walkinAppointmentRecordTable" datatable="ng" dt-instance="vm.dtInstanceCallbackAppointment"
                   class="row-border hoverable cell-border z-depth-1" width="100%"

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
                <tr ng-repeat="walkin in vm.walkinList"
                    ng-if="walkin.strWalkInType == 'APPOINTMENT'">
                    <td class="left-align">{{walkin.strName | uppercase}}</td>
                    <td class="right-align">{{walkin.strContactNo}}</td>
                    <th class="right-align">{{walkin.appointmentDate | date: 'MMMM/d/yyyy'}}</th>
                    <th class="right-align">{{walkin.appointmentTime | date: 'shortTime'}}</th>
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

    <div id="editWalkin" class="modal modal-fixed-footer">
        <form>
        <div class="modal-content">
            <div class="wrapper">
                <div class="aside asideAside1 transparent" style="width: 5px !important;">
                    <div class="row">
                        <div class="col s12">
                            <div class="input-field col s6">
                                <select ng-model="newProduct.product" id="selProductEdit"
                                        ng-options="product.strProductName for product in vm.productList">
                                    <option value="" disabled selected>Product</option>
                                </select>
                                <label for="selProductEdit">Product</label>
                            </div>
                            <div class="input-field col s3">
                                <input type="number" class="right-align" id="selPrductQty" ng-model="newProduct.qty">
                                <label for="selPrductQty">Quantity</label>
                            </div>
                            <div class="input-field col s3">
                                <button class="waves-effect waves-light btn-flat purple white-text" title="Add"
                                        ng-click="addProduct(newProduct)">
                                    <i class='material-icons'>add</i>
                                </button>
                            </div>
                        </div>
                        <div class="col s12">
                            <div class="input-field col s9">
                                <select ng-model="newService.service" id="selServiceEdit"
                                        ng-options="service.strServiceName for service in vm.serviceList">
                                    <option value="" disabled selected>Service</option>
                                </select>
                                <label for="selServiceEdit">Product</label>
                            </div>
                            <div class="input-field col s3">
                                <button class="waves-effect waves-light btn-flat purple white-text" title="Add"
                                        ng-click="addService(newService)">
                                    <i class='material-icons'>add</i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="aside asideAside2 transparent" style="width: 10px !important;">
                    <div class="wrapper">
                        <div class="row">
                            <div class="col s12">
                                <div class="col s6">
                                    <ul class="collection">
                                        <li class="collection-item"><b>Selected Product</b></li>
                                        <li class="collection-item"
                                            ng-repeat="selectedProduct in vm.selectedProductFromWalkin">
                                            {{selectedProduct.prodName}}
                                            <span ng-if="selectedProduct.prodqty > 1">({{selectedProduct.prodqty}}pcs)</span>
                                            <span ng-if="selectedProduct.prodqty == 1">({{selectedProduct.prodqty}}pc)</span>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col s6">
                                    <ul class="collection">
                                        <li class="collection-item"><b>Selected Service</b></li>
                                        <li class="collection-item"
                                            ng-repeat="selectedService in vm.selectedServiceFromWalkin">
                                            {{selectedService.servName}}
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="waves-effect waves-light btn-flat purple white-text"
                    ng-click="editInCart(orderToBeEdit)">
                SAVE
            </button>
        </div>
        </form>
    </div>


</div>
