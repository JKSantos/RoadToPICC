<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="walkinCtrl as vm">
    <div class="aside asideAside1 z-depth-barts z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
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
                           href="#walkinModal" style="margin-left: 15px;"><i
                                class="material-icons">archive</i></a>
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
                        <div class="col s3 order" ng-repeat="product in vm.productList | toArray: false |filter: vm.walkinSearch">
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
                        <div class="col s3" ng-repeat="service in vm.serviceList | toArray: false | filter: vm.walkinSearch">
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
                                        <label for="cREmp"><b>Employees</b></label>
                                    </div>

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

                <div ng-show="vm.selected == 'package'" style="margin-top: -50px !important;">
                    <div class="row ">
                        <div class="col s3" ng-repeat="package in vm.packageList | toArray: false | filter: vm.walkinSearch">
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
                        <div class="col s3" ng-repeat="promo in vm.promoList | toArray: false | filter: vm.walkinSearch">
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

                <div class="col s12 z-depth-barts white" id="">
                    <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                    <div class="col s12" id="pslist"
                         style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                </div>
                <div class="col s4" style="margin-top: 10px;">
                    <div class="input-field col s12">
                        <select multiple ng-model="vm.selDiscounts" id="crRDiscount"
                                ng-options="discount.strDiscountName for discount in vm.discountList"></select>
                        <label for="crRDiscount"><b>Discounts</b></label>
                    </div>
                </div>
                <div class="col s4 z-depth-barts white" style="margin-top: 10px;">
                    <div class="col s12">
                        <div class="input-field col s12">
                            <p>Total:{{vm.sum}}</p>
                        </div>
                        <div class="input-field col s12" style="margin-top: 20px !important;">
                            <input type="text" class="right-align prodPrice" name="" id="crPackPrice"
                                   placeholder="Price"/>
                            <label for="crPackPrice" class="active"><b>Price</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="createWalkinModal" class="modal modal-fixed-footer"
         style="width: 90% !important; height: 90% !important; max-height: 100% !important; margin-top: -40px;">
        <form class="col s12" id="createWalkinForm" method="post" action="">
            <div class="modal-content">

                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Walk-In<a id="btnCrExtraExit" type="reset"
                                                                                value="Reset"
                                                                                class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
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
                <button type="button" id="nextbtn"
                        class="actionwalkin nextformwalkin waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button type="submit" value="submit" id="createReservationSubmitForm"
                        class="actionwalkin submitformwalkin waves-effect waves-light white-text btn-flat purple"
                        ng-click="vm.saveWalkin(details)"
                        style="margin-left:3px; margin-right:3px;">CREATE
                </button>
                <button type="button" id="backbtn"
                        class="actionwalkin backformwalkin waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>

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
                            <label for="cREmp1"><b>Employees</b></label>
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
                            <label for="cREmp1"><b>Employees</b></label>
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
                        <li class="collection-item left-align" style="margin-left:0px !important; padding-left: 5px !important;"
                            ng-repeat="orders in orderList"
                            ng-if="orders.product!='' && orders.total!=0">
                            <img ng-src="{{orders.strPhotoPath}}" class="circle" height="30" width="30">
                                <span style="padding-left: 5px !important;" title="{{orders.product}} - {{orders.total | currency: 'Php '}}">
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
                    <h5>Total: {{totalAmount | currency: "Php "}}</h5>
                </div>
            </div>
        </div>
        <div class="aside white z-depth-barts">
            <div class="row">
                <div class="col s12 transparent">
                    <h5>Request of</h5>
                    <ul class="collection with-header reqList">
                        <li class="collection-item left-align" style="margin-left:0px !important; padding-left: 10px !important;"
                            ng-repeat="request in requestOrder.slice().reverse()">
                            <i class="material-icons left" style="margin-right:10px !important; margin-top: -3px !important;"
                               ng-if="request.intType==1 || request.intType=='delivery'">
                                local_shipping
                            </i>
                            <i class="material-icons left" style="margin-right:10px !important; margin-top: -3px !important;"
                               ng-if="request.intType==2 || request.intType=='pickup'">
                                shopping_basket
                            </i>
                            <span title="{{ request.strName }}">{{ request.strName | truncate: 13 }}</span>
                            <button name="" title="Decline" class="secondary-content red-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;"
                                    ng-click="declineOrder(request)">
                                <i class="material-icons" style="padding-top: 7px !important;">clear</i>
                            </button>
                            <button name="" title="Accept" class="secondary-content black-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border: 0px !important;"
                                    ng-if="request.intType==1 || request.intType=='delivery'"
                                    ng-click="acceptDeliveryOrder(request)">
                                <i class="material-icons" style="padding-top: 7px !important;">done</i>
                            </button>
                            <button name="" title="Accept" class="secondary-content black-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border: 0px !important;"
                                    ng-if="request.intType==2 || request.intType=='pickup'"
                                    ng-click="acceptPickupOrder(request)">
                                <i class="material-icons" style="padding-top: 7px !important;">done</i>
                            </button>
                        </li>
                    </ul>
                    <p>
                        <i class="material-icons">local_shipping</i> - Delivery
                        <i class="material-icons">shopping_basket</i> - Pick-up
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
