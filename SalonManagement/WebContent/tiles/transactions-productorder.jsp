<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div ng-controller="prodSalesCtrl as vm">
    <div class="wrapper" id="MainWrap">
        <div class="aside asideAside1 z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
            <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                <h3 class="grey-text text-darken-1">Product Sales</h3>
                <a class="pscrbtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn left green darken-2 white-text"
                   style="margin-top: 30px; margin-left: 15px;"
                   ng-if="orderList.length > 1 && totalAmount > vm.minAmt"
                   ng-click="vm.crProductSales(); vm.checkOut();">CHECK OUT
                    <i class="material-icons right">shopping_cart</i>
                </a>
                <a class="btn left green darken-2 white-text"
                   style="margin-top: 30px; margin-left: 15px; opacity: 0.5 !important; cursor: not-allowed !important;"
                   ng-disabled="true"
                   ng-if="orderList.length < 2 || totalAmount < vm.minAmt">CHECK OUT
                    <i class="material-icons right">shopping_cart</i>
                </a>
                <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
                   href="#prodsalesTable" style="margin-top: 30px; margin-left: 15px;">
                    <i class="material-icons">border_all</i>
                </a>
                <nav class="right white hoverable  z-depth-1" style="width: 40%;">
                    <div class="nav-wrapper col s4">
                        <form>
                            <div class="input-field">
                                <input id="psSearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                       ng-model="searchProduct"/>
                                <label for="psSearch"><i
                                        class="material-icons grey-text text-darken-4">search</i></label>
                            </div>
                        </form>
                    </div>
                </nav>

            </div>

            <div class="row col s12">

            </div>

            <div class="row ">
                <div class="col s3" ng-repeat="product in productList | toArray | filter: searchProduct">
                    <div class="card small">
                        <div class="card-image waves-effect waves-block waves-light">
                            <img class="activator" ng-src="{{product.strPhotoPath}}">
                        </div>
                        <div class="card-content">
                            <span class="activator grey-text text-darken-4 light"><i class="material-icons right">add_shopping_cart</i></span>
                            <h5 style='font-size: 15px; line-height: 15px !important;'><b>{{product.strProductName}}</b>
                                <p>{{product.dblProductPrice | currency:"Php "}}</p></h5>
                        </div>
                        <div class="card-reveal">

                            <span class="card-title grey-text text-darken-4">
                                <i class="material-icons right"
                                   id="prodClose{{product.intProductID}}">close</i>
                            </span>
                            <h4 style='font-size: 15px; line-height: 15px !important;'>
                                <b>{{product.strProductName}}</b><br/>
                                <span class="grey-text text-darken-4">{{product.dblProductPrice | currency:"Php "}}</span>
                            </h4>
                            <div class="input-field col s12">
                                <input type="number" id="crPSQty{{product.intProductID}}" ng-model="details.quantity" min="1"/>
                                <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                            </div>
                            <h6 class="grey-text text-darken-4">{{product.dblProductPrice * details.quantity | currency:
                                "Php "}}</h6>

                            <button class="waves-effect waves-light btn"
                                    ng-if="details.quantity > 0 && details.quantity <= product.intProductQuantity"
                                    ng-click="addToCart($index); calculateTotal(); vm.closeCard(product.intProductID)">
                                <i class="material-icons left" style="padding: 0px !important; margin: 0px !important;">
                                    shopping_basket</i>BUY NOW!
                            </button>
                            <button class="btn"
                                    ng-if="details.quantity < 1 && details.quantity <= product.intProductQuantity"
                                    ng-disabled="true" style="opacity: 0.3; cursor: not-allowed !important;">
                                <i class="material-icons left" style="padding: 0px !important; margin: 0px !important;">
                                    shopping_basket</i>BUY NOW!
                            </button>
                            <span class="red-text" ng-if="details.quantity > product.intProductQuantity">INSUFFICIENT STOCK</span>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div id="editItem" class="modal modal-fixed-footer" style="width: 400px !important; height: 500px !important;">
            <div class="modal-content">
                <h4 class="center">{{orderToBeEdit.product}}<br/>
                    <img class="circle z-depth-1" ng-src="{{orderToBeEdit.strPhotoPath}}" height="150" width="150"></h4>
                <h6 class="center">Price: {{orderToBeEdit.price | currency: "Php "}}</h6>
                <div class="container">
                    <div class="row">
                        <div class="input-field col s4 offset-s4" style="margin-top: -20px !important;">
                            <input type="text" class="center-align" ng-model="orderToBeEdit.quantity" min="1" numbers-only>
                        </div>
                        <div class="input-field col s12" style="margin-top: -10px !important;">
                            <h5 class="center">Total: {{ (orderToBeEdit.price * orderToBeEdit.quantity) | currency: "Php "}}</h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="waves-effect waves-light btn-flat purple white-text"
                        ng-if="orderToBeEdit.quantity > 0"
                        ng-click="editInCart(orderToBeEdit)">
                    SAVE
                </button>
            </div>
        </div>

        <div class="aside asideAside2 transparent">
            <div class="aside white z-depth-barts" ng-if="orderList.length > 1">
                <div class="row">
                    <div class="col s12">
                        <h5>Selected Item</h5>
                        <ul class="collection with-header reqList" style="height: 15em !important;">
                            <li class="collection-item left-align" style="margin-left:0px !important; padding-left: 5px !important;"
                                ng-repeat="orders in orderList"
                                ng-if="orders.product!='' && orders.total!=0">
                                <img ng-src="{{orders.strPhotoPath}}" class="circle" height="30" width="30">
                                <span style="padding-left: 5px !important;" title="{{orders.product}} - {{orders.total | currency: 'Php '}}" ng-click="openEditItem($index, orders)">
                                    {{orders.product | truncate: 15}}
                                </span>
                                <button name="" title="Decline" class="secondary-content red-text transparent"
                                        style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;" ng-click="removeToCart($index, orders)">
                                    <i class="material-icons" style="padding-top: 8px !important;">clear</i>
                                </button>
                                <!--<button name="" title="Accept" class="secondary-content black-text transparent"-->
                                        <!--style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border: 0px !important;"-->
                                        <!--ng-click="openEditItem($index, orders)">-->
                                    <!--<i class="material-icons" style="padding-top: 7px !important;">edit</i>-->
                                <!--</button>-->
                            </li>
                        </ul>
                        <h5>Total: {{totalAmount | currency: "Php "}}</h5>
                        <h6 class="red-text"  ng-if="totalAmount < vm.minAmt">
                            Minimum Amount is {{vm.minAmt | currency: "Php "}}
                        </h6>
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
                                <span title="{{ request.strName }}" ng-click="vm.reqName(request);">{{ request.strName | truncate: 10 }}</span>
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
                                        ng-click="openPickUpOrder(request)">
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


    <div id="crProductSales" class="modal modal-fixed-footer"
         style="width: 50% !important; height: 70% !important; max-height: 100% !important; margin-top: -40px;">
        <form class="col s12 css-form" id="createPSForm" name="createPSForm" novalidate>
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Order
                        <a id="btnCreateExit"
                           class="modal-action modal-close">
                            <i class="small material-icons right grey-text text-darken-4"
                               ng-click="vm.closePSModal()">close</i>
                        </a>
                    </h4>
                    <div class="card red center input-field col s12 white-text z-depth-barts">
                        <span class="white-text"
                              ng-show="createPSForm.strOrderContact.$error.number">
                            Valid contact number is required <br>
                        </span>
                        <span class="white-text"
                              ng-show="createPSForm.strOrderContact.$error.pattern">
                            Contact number should have 11 digits <br>
                        </span>
                        <span class="white-text"
                              ng-show="createPSForm.strOrderName.$error.pattern">
                            Valid name is required <br>
                        </span>
                        <span class="white-text"
                              ng-show="createPSForm.strOrderName.$error.minlength">
                            Name must be at least 5 characters <br>
                        </span>
                        <span class="white-text"
                              ng-show="createPSForm.strOrderStreet.$error.pattern">
                            Valid street is required <br>
                        </span>
                        <span class="white-text"
                              ng-show="createPSForm.strOrderStreet.$error.minlength">
                            Street must be at least 5 characters <br>
                        </span>
                    </div>
                    <div class="row">
                        <div class="stepps well">
                            <div class="container">
                                <div class="input-field col s6">
                                    <select ng-options="order.name for order in orderType track by order.id"
                                            ng-model="details.order"
                                            id="crOrderType">
                                    </select>
                                    <label for="crOrderType"><b>Type</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>

                                </div>
                                <div class="input-field col s6">
                                    <input type="number" name="strOrderContact" id="crOrderContact" placeholder="Ex: 09123456789" class="right-align"
                                           ng-model="details.contact"
                                           ng-required="true"
                                           ng-pattern="/^\d{11}$/"/>
                                    <label for="crOrderContact" class="active">
                                        <b>Contact Number</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" name="strOrderName" id="crOrderName" placeholder="Ex: Juan Luna"
                                           ng-model="details.name"
                                           ng-required="true"
                                           ng-minlength="5"
                                           ng-pattern="/^[A-Za-z \-'`]+$/"/>
                                    <label for="crOrderName" class="active"><b>Name</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s6" id="crDivOrderSt"
                                     ng-if="details.order.id == 2">
                                    <input type="text" name="strOrderStreet" id="crOrderStreet" placeholder="Ex: Sesame Street"
                                           ng-model="details.street"
                                           ng-required="true"
                                           ng-minlength="5"
                                           ng-pattern="/^[a-zA-Z0-9\- `#.,]+$/"/>
                                    <label for="crOrderStreet" class="active"><b>Street</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s6" id="crDivOrderLoc"
                                     ng-if="details.order.id == 2">
                                    <select ng-options="location.strBarangay for location in locationList" material-select watch ng-model="details.location" id="crOrderLoc"
                                    ng-change="locationPrice(details.location);">
                                    </select>
                                    <label for="crOrderLoc"><b>Location</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s12">
                                    <select name="crDiscount" id="crDiscount" material-select watch
                                            ng-model="details.discount"
                                            ng-options="discount.strDiscountName for discount in vm.discountList"
                                            ng-change="selectedDiscount(details.discount)">
                                            <option value="" selected>Choose...</option>
                                    </select>
                                    <label for="crDiscount"><b>Discount</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <p><b>Total Product Price</b>: {{totalAmount | currency: "Php "}} </p>
                                    <div ng-if="details.order.value == 'delivery'">
                                        <p>
                                        <b>Location</b>: {{vm.loc.name}} <br>
                                        <b>Location Price</b>: {{vm.loc.price | currency: "Php "}} <br>
                                        </p>
                                        <div ng-if="vm.seldic != ''">
                                            <div ng-if="vm.seldic.intDiscountType == 1">
                                                <p><b>Discount</b>: {{vm.seldic.strDiscountName}} ({{vm.seldic.dblDiscountAmount}}%) <br>
                                                </p>
                                            </div>
                                            <div ng-if="vm.seldic.intDiscountType == 2">
                                                <p><b>Discount</b>: {{vm.seldic.strDiscountName}} ({{vm.seldic.dblDiscountAmount | currency: "Php "}}) <br>
                                                </p>
                                            </div>
                                        </div>
                                            <div ng-if="vm.x == 0">
                                                <p>
                                                <b>Total</b>: {{totalAmount + vm.loc.price | currency: "Php "}}
                                                </p>
                                            </div>
                                            <div ng-if="vm.x == 1">
                                                <p>
                                                    <b>Total</b>: {{vm.totalAmt | currency: "Php "}}
                                                </p>
                                            </div>
                                    </div>
                                    <div ng-if="details.order.value == 'pickup'">
                                        <div ng-if="vm.seldic != ''">
                                            <div ng-if="vm.seldic.intDiscountType == 1">
                                                <p><b>Discount</b>: {{vm.seldic.strDiscountName}} ({{vm.seldic.dblDiscountAmount}}%) <br>
                                                </p>
                                            </div>
                                            <div ng-if="vm.seldic.intDiscountType == 2">
                                                <p><b>Discount</b>: {{vm.seldic.strDiscountName}} ({{vm.seldic.dblDiscountAmount | currency: "Php "}}) <br>
                                                </p>
                                            </div>
                                        </div>
                                            <div ng-if="vm.x == 0">
                                                <p>
                                                <b>Total</b>: {{totalAmount + vm.loc.price | currency: "Php "}}
                                                </p>
                                            </div>
                                            <div ng-if="vm.x == 1">
                                                <p>
                                                    <b>Total</b>: {{vm.totalAmt | currency: "Php "}}
                                                </p>
                                            </div>
                                    </div>
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
                <button type="submit" value="Submit" id="crCreateOrderBtn"
                        class="actionps submitformps waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px;"
                        ng-if="createPSForm.$valid == true && vm.loadingBubble == 1"
                        ng-click="setProdSalesPayment(details); commaProducts(); commaQuantity()">
                    CREATE
                </button>
                <button type="submit" value="Submit"
                        class="white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px; opacity: 0.5 !important; cursor: not-allowed !important;"
                        ng-if="createPSForm.$valid == false && vm.loadingBubble == 1"
                        ng-disabled="true">
                    CREATE
                </button>
                <button type="submit" value="Submit"
                        class="btn-flat white"
                        style="margin-left:3px; margin-right:3px;"
                        ng-if="vm.loadingBubble == 0"
                        ng-disabled="true">
                    <div style="margin-top: 9px !important;">
                        <div class="bubbles1"></div>
                        <div class="bubbles1"></div>
                        <div class="bubbles1"></div>
                    </div>
                </button>
            </div>
        </form>
    </div>

    <div id="AcceptPickupModal" class="modal modal-fixed-footer"
         style="width: 500px !important; height: 400px !important;">
        <div class="modal-content">
            <div class="container">
                <div class="row">
                    <h4 class="center grey-text text-darken-3">Accept order of<br/>
                        <span class="grey-text text-darken-4"><b>{{pickup.strName | uppercase}}</b></span></h4>

                    <input type="hidden" ng-model="pickup.intSalesID"/>
                    <input type="hidden" ng-model="pickup.index"/>
                    <div class="container">
                        <div class="input-field col s12" style="margin-top: 50px !important;">
                            <select ng-model="pickup.selEmployee" id="acceptPickupEmp"
                                    ng-options="employee.strEmpFirstName for employee in employeeList">
                                <option value="" disabled selected>Choose...</option>
                            </select>
                            <label for="acceptPickupEmp"><b>Employee</b>
                                <i class="material-icons tiny red-text">error_outline</i>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="red-text btn-flat transparent left" disabled
                    style="margin:0px !important; padding:0px !important;"><i
                    class="material-icons red-text">error_outline</i>&nbspRequired field
            </button>
            <button id="submitPickup" class="waves-effect waves-light white-text btn-flat purple"
                    style="margin-left:3px; margin-right:3px;"
                    ng-click="acceptPickupOrder(pickup)">
                PROCEED
            </button>
        </div>
    </div>

    <div id="AcceptDeliveryModal" class="modal modal-fixed-footer"
         style="width: 500px !important; height: 600px !important;">
        <div class="modal-content">
            <div class="container">
                <div class="row">
                    <h4 class="center grey-text text-darken-3">Set delivery date for <br/>
                        <span class="grey-text text-darken-4"><b>{{delivery.strName | uppercase}}</b></span></h4>

                    <input type="hidden" ng-model="delivery.intSalesID"/>
                    <input type="hidden" ng-model="delivery.index"/>
                    <div class="container">
                        <div class="input-field col s12" style="margin-top: 50px !important;">
                            <select ng-model="delivery.selEmployee" id="acceptDelEmp"
                                    ng-options="employee.strEmpFirstName for employee in employeeList">
                                <option value="" disabled selected>Choose...</option>
                            </select>
                            <label for="acceptDelEmp"><b>Employee</b>
                                <i class="material-icons tiny red-text">error_outline</i>
                            </label>
                        </div>
                        <div class="input-field col s12" style="margin-top: 50px !important;">
                            <input type="date" name="delDate" class="datepicker-delivery"
                                   id="deliveryDate" placeholder="August/01/2016" required
                                   ng-model="delivery.deliveryDate"/>
                            <label for="deliveryDate" class="active"><b>Delivery Date</b>
                                <i class="material-icons tiny red-text">error_outline</i></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="red-text btn-flat transparent left" disabled
                    style="margin:0px !important; padding:0px !important;"><i
                    class="material-icons red-text">error_outline</i>&nbspRequired field
            </button>
            <button id="submitDeliveryDate" class="waves-effect waves-light white-text btn-flat purple"
                    style="margin-left:3px; margin-right:3px;"
                    ng-click="sendDeliveryDetails(delivery)">
                PROCEED
            </button>
        </div>
    </div>


    <div id="prodsalesTable" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Product Sales</h4>
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
                    <td class="left-align" ng-if="order.strAddress != ''">{{ order.strAddress }}</td>
                    <td class="left-align" ng-if="order.strAddress == ''">None</td>
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
