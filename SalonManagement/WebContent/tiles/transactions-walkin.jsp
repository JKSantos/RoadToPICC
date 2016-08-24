<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="walkinCtrl as vm" style="margin-top: 5px !important;">
    <div class="aside asideAside1 z-depth-barts z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12">
            <h3 class="grey-text text-darken-1">Walk-In</h3>
            <div class="row">
                <div class="col s12">
                    <div class="input-field col s3">
                        <select id="discountFilter" ng-model="vm.selected">
                            <option value="product">Product</option>
                            <option value="service">Service</option>
                            <option value="package">Package</option>
                            <option value="promo">Promo</option>
                        </select>
                        <label for="discountFilter"><b>Select</b></label>
                    </div>
                    <div class="input-field col s4">
                        <a class="crWalkin z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
                           href="#createWalkinModal" style="margin-left: 15px;"><i
                                class="material-icons">add</i></a>
                        <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
                           href="#walkinTable" style="margin-left: 15px;"><i
                                class="material-icons">border_all</i></a>
                    </div>
                    <div class="input-field col s5">
                        <nav class="right white hoverable z-depth-1"
                             style=" margin-right: 20px; margin-top: -20px !important;">
                            <div class="nav-wrapper col s12">
                                <form>
                                    <div class="input-field">
                                        <input id="walkinSearch" placeholder="Search" class="grey-text text-darken-4"
                                               type="search"
                                               ng-model="vm.walkinSearch">
                                        <label for="walkinSearch"><i
                                                class="material-icons grey-text text-darken-4">search</i></label>
                                    </div>
                                </form>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>


            <div class="row col s12">

            </div>
            <div class="row">

                <div class="row col s12">

                </div>

                <div ng-show="vm.selected == 'product'" style="margin-top: -50px !important;">
                    <div class="row ">
                        <div class="col s3 order"
                             ng-repeat="product in vm.productList | toArray: false |filter: vm.walkinSearch">
                            <div class="card small">
                                <div class="card-image waves-effect waves-block waves-light">
                                    <img class="activator" ng-src="{{product.strPhotoPath}}">
                                </div>
                                <div class="card-content">
                                    <a class="activator grey-text text-darken-4 light btn btn-small center"
                                       style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
                                    <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{product.strProductName}}</b>
                                        <p>{{product.dblProductPrice | currency:"P"}}</p></h5>
                                </div>
                                <div class="card-reveal">

                                                <span class="card-title grey-text text-darken-4"><i
                                                        class="material-icons right">close</i></span>
                                    <h4 style='font-size: 12px; line-height: 15px !important;'>
                                        <b>{{product.strProductName}}</b><br/>
                                        <span class="grey-text text-darken-4">{{product.dblProductPrice | currency:"Php "}}</span>
                                    </h4>
                                    <div class="input-field col s12">
                                        <input type="number" id="crPSQty{{product.intProductID}}"
                                               ng-model="vm.quantity"/>
                                        <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                                    </div>
                                    <h6 class="grey-text text-darken-4">
                                        {{product.dblProductPrice * vm.quantity | currency: "Php "}}
                                    </h6>
                                    <a class="waves-effect waves-light btn"
                                       ng-click="vm.addToCart($index, vm.selected); vm.sumTotal()">
                                        <i class="material-icons left"
                                           style="padding: 0px !important; margin: 0px !important;">
                                            shopping_basket</i>BUY NOW!
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div ng-show="vm.selected == 'service'" style="margin-top: -50px !important;">
                    <div class="row ">
                        <div class="col s3"
                             ng-repeat="service in vm.serviceList | toArray: false | filter: vm.walkinSearch">
                            <div class="card small">
                                <div class="card-image waves-effect waves-block waves-light">
                                    <img class="activator" ng-src="{{service.strPhotoPath}}">
                                </div>
                                <div class="card-content">
                                    <a class="activator grey-text text-darken-4 light btn btn-small center"
                                       style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
                                    <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{service.strServiceName}}</b>
                                        <p>{{service.dblServicePrice | currency:"P"}}</p></h5>
                                </div>
                                <div class="card-reveal">

                                                <span class="card-title grey-text text-darken-4"><i
                                                        class="material-icons right">close</i></span>
                                    <h4 style='font-size: 12px; line-height: 15px !important;'>
                                        <b>{{service.strServiceName}}</b><br/>
                                        <span class="grey-text text-darken-4">{{service.dblServicePrice | currency:"Php "}}</span>
                                    </h4>
                                    <div class="input-field col s12">
                                        <select ng-model="vm.selEmployee" id="cREmp"
                                                ng-options="employee.strEmpFirstName for employee in vm.employeeList"></select>
                                        <label for="cREmp"><b>Employee</b></label>
                                    </div>

                                    <a class="waves-effect waves-light btn"
                                       ng-click="vm.addToCart($index, vm.selected); vm.sumTotal()">
                                        <i class="material-icons left"
                                           style="padding: 0px !important; margin: 0px !important;">
                                            shopping_basket</i>GET SERVICE!
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div ng-show="vm.selected == 'package'" style="margin-top: -50px !important;">
                    <div class="row ">
                        <div class="col s3"
                             ng-repeat="package in vm.packageList | toArray: false | filter: vm.walkinSearch">
                            <div class="card small">
                                <div class="card-image waves-effect waves-block waves-light">
                                    <img class="activator" ng-src="{{service.strPhotoPath}}">
                                </div>
                                <div class="card-content">
                                    <a class="activator grey-text text-darken-4 light btn btn-small center"
                                       style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
                                    <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{package.strPackageName}}</b>
                                        <p>{{package.dblPackagePrice | currency:"P"}}</p></h5>
                                </div>
                                <div class="card-reveal">

                                               <span class="card-title grey-text text-darken-4"><i
                                                       class="material-icons right">close</i></span>
                                    <h4 style='font-size: 12px; line-height: 15px !important;'>
                                        <b>{{package.strPackageName}}</b><br/>
                                        <ul ng-repeat="contains in vm.packageContains">
                                            <li>{{contains.service.strServiceName}}</li>
                                        </ul>
                                        <span class="grey-text tex 	t-darken-4"></span>
                                    </h4>

                                    <a class="waves-effect waves-light btn"
                                       ng-click="vm.addToCart($index, vm.selected); vm.sumTotal()">
                                        <i class="material-icons left"
                                           style="padding: 0px !important; margin: 0px !important;">
                                            shopping_basket</i>BUY NOW!
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div ng-show="vm.selected == 'promo'" style="margin-top: -50px !important;">
                    <div class="row ">
                        <div class="col s3"
                             ng-repeat="promo in vm.promoList | toArray: false | filter: vm.walkinSearch">
                            <div class="card small">

                                <div class="card-content">
                                    <a class="activator grey-text text-darken-4 light btn btn-small center"
                                       style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
                                    <h5 style='font-size: 12px; line-height: 10px !important;'>
                                        <b>{{promo.strPromoName}}</b>
                                        <p>{{promo.dblPromoPrice | currency:"P"}}</p></h5>
                                </div>
                                <div class="card-reveal">

                                               <span class="card-title grey-text text-darken-4"><i
                                                       class="material-icons right">close</i></span>
                                    <h4 style='font-size: 12px; line-height: 15px !important;'>
                                        <b>{{promo.strPromoName}}</b><br/>
                                        <span class="grey-text text-darken-4">{{promo.dblPromoPrice | currency:"Php "}}</span>
                                    </h4>

                                    <a class="waves-effect waves-light btn"
                                       ng-click="vm.addToCart($index, vm.selected); vm.sumTotal()">
                                        <i class="material-icons left"
                                           style="padding: 0px !important; margin: 0px !important;">
                                            shopping_basket</i>BUY NOW!
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="createWalkinModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createWalkinForm" method="post" action="">
            <div class="modal-content">

                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Walk-In<a id="btnCrExtraExit" type="reset"
                                                                                value="Reset"
                                                                                class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="walkinerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="stepwalkin well" style="margin-top: -5px;">
                        <div class="row">
                            <div class="container">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" id="crWIName"
                                           ng-model="vm.details.name" placeholder="Name"
                                           style='font-size: 22px; line-height: 15px !important;'/>
                                    <label for="crWIName" class="active"><b
                                            style='font-size: 20px; line-height: 15px !important;'>Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" id="crWIContact" ng-model="vm.details.contact"
                                           placeholder="contact"
                                           style='font-size: 22px; line-height: 15px !important;'/>
                                    <label for="crWIContact" class="active"><b>Contact</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 15px;">
                                    <input type="email" ng-model="vm.details.email" id="crWIEmail" placeholder="Email"
                                           style='font-size: 22px; line-height: 15px !important;'/>
                                    <label for="crWIEmail" class="active"><b>Email</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <select multiple ng-model="vm.selDiscounts" id="crRDiscount"
                                            ng-options="discount.strDiscountName for discount in vm.discountList"></select>
                                    <label for="crRDiscount"><b>Discounts</b></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="modal-footer">
                <button class="red-text btn-flat transparent left" disabled
                        style="margin:0px !important; padding:0px !important;"><i
                        class="material-icons">error_outline</i>&nbspRequired field
                </button>
                <a class="waves-effect waves-light white-text btn-flat purple"
                        ng-click="vm.saveWalkin(details)"
                        style="margin-left:3px; margin-right:3px;">CREATE
                </a>

            </div>
        </form>
    </div>

    <div id="packageListModal" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Pacakge Contains</h4>
            <table>
                <tr ng-repeat="services in vm.packageContains">
                    <td>{{services.service.strServiceName}}</td>
                    <td>
                        <div class="input-field col s12">
                            <select ng-model="vm.selEmployeePerService" id="cREmp1"
                                    ng-options="employee.strEmpFirstName for employee in vm.employeeList"></select>
                            <label for="cREmp1"><b>Employee</b></label>
                        </div>
                    </td>
                    <td>
                        <button id="btnAssign"
                                class="waves-effect waves-light white-text btn-flat purple"
                                ng-click="vm.assignEmployeePackage($index)">ASSIGN
                        </button>
                    </td>
                </tr>
            </table>
            <div class="container">
                <div class="row">

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
                    ng-click="">DONE
            </button>
        </div>
    </div>

    <div id="promoListModal" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Promo Contains</h4>
            <table>
                <tr ng-repeat="services in vm.promoContains">
                    <td>{{services.service.strServiceName}}</td>
                    <td>
                        <div class="input-field col s12">
                            <select ng-model="vm.selEmployeePerService" id="cREmp1"
                                    ng-options="employee.strEmpFirstName for employee in vm.employeeList"></select>
                            <label for="cREmp1"><b>Employee</b></label>
                        </div>
                    </td>
                    <td>
                        <button id="btnAssign"
                                class="waves-effect waves-light white-text btn-flat purple"
                                ng-click="vm.assignEmployeePromo($index)">ASSIGN
                        </button>
                    </td>
                </tr>
            </table>
            <div class="container">
                <div class="row">

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
                    ng-click="">DONE
            </button>
        </div>
    </div>

    <div class="aside asideAside2 transparent">
        <div class="aside white z-depth-barts">
            <div class="row">
                <div class="col s12">
                    <h5>Selected Item</h5>
                    <ul class="collection with-header reqList" style="height: 15em !important;">
                        <li class="collection-item left-align"
                            style="margin-left:0px !important; padding-left: 5px !important;"
                            ng-repeat="orders in orderList"
                            ng-if="orders.product!='' && orders.total!=0">
                            <img ng-src="{{orders.strPhotoPath}}" class="circle" height="30" width="30">
                                <span style="padding-left: 5px !important;"
                                      title="{{orders.product}} - {{orders.total | currency: 'Php '}}">
                                    {{orders.product | truncate: 14}}
                                </span>
                            <button name="" title="Decline" class="secondary-content red-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;"
                                    ng-click="removeToCart($index, orders)">
                                <i class="material-icons" style="padding-top: 7px !important;">clear</i>
                            </button>
                            <button name="" title="Accept" class="secondary-content black-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border: 0px !important;"
                                    ng-click="openEditItem($index, orders)">
                                <i class="material-icons" style="padding-top: 7px !important;">edit</i>
                            </button>
                        </li>
                    </ul>
                    <h5>Total: {{ vm.sum | currency: "Php "}}</h5>
                </div>
            </div>
        </div>
        <!--<div class="aside white z-depth-barts">-->
            <!--<div class="row">-->
                <!--<div class="col s12 transparent">-->
                    <!--<h5>Request of</h5>-->
                    <!--<ul class="collection with-header reqList">-->
                        <!--<li class="collection-item left-align"-->
                            <!--style="margin-left:0px !important; padding-left: 10px !important;"-->
                            <!--ng-repeat="request in requestOrder.slice().reverse()">-->
                            <!--<i class="material-icons left"-->
                               <!--style="margin-right:10px !important; margin-top: -3px !important;"-->
                               <!--ng-if="request.intType==1 || request.intType=='delivery'">-->
                                <!--local_shipping-->
                            <!--</i>-->
                            <!--<i class="material-icons left"-->
                               <!--style="margin-right:10px !important; margin-top: -3px !important;"-->
                               <!--ng-if="request.intType==2 || request.intType=='pickup'">-->
                                <!--shopping_basket-->
                            <!--</i>-->
                            <!--<span title="{{ request.strName }}">{{ request.strName | truncate: 13 }}</span>-->
                            <!--<button name="" title="Decline" class="secondary-content red-text transparent"-->
                                    <!--style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;"-->
                                    <!--ng-click="declineOrder(request)">-->
                                <!--<i class="material-icons" style="padding-top: 7px !important;">clear</i>-->
                            <!--</button>-->
                            <!--<button name="" title="Accept" class="secondary-content black-text transparent"-->
                                    <!--style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border: 0px !important;"-->
                                    <!--ng-if="request.intType==1 || request.intType=='delivery'"-->
                                    <!--ng-click="acceptDeliveryOrder(request)">-->
                                <!--<i class="material-icons" style="padding-top: 7px !important;">done</i>-->
                            <!--</button>-->
                            <!--<button name="" title="Accept" class="secondary-content black-text transparent"-->
                                    <!--style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border: 0px !important;"-->
                                    <!--ng-if="request.intType==2 || request.intType=='pickup'"-->
                                    <!--ng-click="acceptPickupOrder(request)">-->
                                <!--<i class="material-icons" style="padding-top: 7px !important;">done</i>-->
                            <!--</button>-->
                        <!--</li>-->
                    <!--</ul>-->
                    <!--<p>-->
                        <!--<i class="material-icons">local_shipping</i> - Delivery-->
                        <!--<i class="material-icons">shopping_basket</i> - Pick-up-->
                    <!--</p>-->
                <!--</div>-->
            <!--</div>-->
        <!--</div>-->
    </div>

    <div id="walkinTable" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Walk In</h4>
            <nav class="right white hoverable z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="searchBoxID" placeholder="Search" class="grey-text text-darken-4" type="search"
                                   ng-model="vm.searchInTable"
                                   ng-change="vm.searchTable()">
                            <label for="searchBoxID">
                                <i class="material-icons grey-text text-darken-4">search</i>
                            </label>
                        </div>
                    </form>
                </div>
            </nav>
            <table id="psRecordTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                   class="row-border hoverable cell-border z-depth-1" width="100%"
                   style="margin-top: -15px !important;">
                <thead>
                <tr>
                    <th class="left-align">Name</th>
                    <th class="left-align">Address</th>
                    <th class="right-align">Contact</th>
                    <th class="left-align">Type</th>
                    <th class="left-align">Status</th>
                    <th class="center-align">Action</th>
                </tr>
                </thead>
                <tfoot>
                <tr style="border: 1px solid #bdbdbd;">
                    <th class="left-align">Name</th>
                    <th class="left-align">Address</th>
                    <th class="right-align">Contact</th>
                    <th class="left-align">Type</th>
                    <th class="left-align">Status</th>
                    <th class="center-align">Action</th>
                </tr>
                </tfoot>
                <tbody>
                <tr ng-repeat="order in requestOrderList"
                    ng-if="order.strStatus != 'REQUEST'">
                    <td class="left-align">{{ order.strName }}</td>
                    <td class="left-align">{{ order.strAddress }}</td>
                    <td class="right-align">{{ order.strContactNo }}</td>
                    <td class="left-align">
                        <span ng-if="order.intType==1">DELIVERY</span>
                        <span ng-if="order.intType==2">PICK UP</span>
                    </td>
                    <td class="left-align">{{ order.strStatus }}</td>
                    <td class="center-align">
                        <button class="waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Deactivate"
                                ng-if="order.strStatus=='PENDING'"
                                ng-click="deactivateOrder(order)">
                            <i class='material-icons'>delete</i>
                        </button>
                        <button class="btn-flat transparent red-text text-lighten-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                ng-if="order.strStatus=='COMPLETE'"
                                ng-disabled=true>
                            <i class='material-icons'>delete</i>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Close</a>
        </div>
    </div>
</div>
