<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div ng-controller="prodSalesCtrl as vm">
    <div class="wrapper" id="MainWrap">
        <div class="aside asideAside1 z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
            <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                <h3 class="grey-text text-darken-1">Product Sales</h3>
                <a class="pscrbtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn left green darken-2 white-text"
                   href="#crProductSales" style="margin-top: 30px; margin-left: 15px;">CHECK OUT<i
                        class="material-icons right">shopping_cart</i></a>
                <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
                   href="#prodsalesTable" style="margin-top: 30px; margin-left: 15px;"><i
                        class="material-icons">border_all</i></a>
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

                            <span class="card-title grey-text text-darken-4"><i
                                    class="material-icons right">close</i></span>
                            <h4 style='font-size: 15px; line-height: 15px !important;'>
                                <b>{{product.strProductName}}</b><br/>
                                <span class="grey-text text-darken-4">{{product.dblProductPrice | currency:"Php "}}</span>
                            </h4>
                            <div class="input-field col s12">
                                <input type="number" id="crPSQty{{product.intProductID}}" ng-model="details.quantity"/>
                                <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                            </div>
                            <h6 class="grey-text text-darken-4">{{product.dblProductPrice * details.quantity | currency:
                                "Php "}}</h6>

                            <button class="waves-effect waves-light btn" ng-click="addToCart($index); calculateTotal()">
                                <i class="material-icons left" style="padding: 0px !important; margin: 0px !important;">
                                    shopping_basket</i>BUY NOW!
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div id="editItem" class="modal modal-fixed-footer">
            <div class="modal-content">
                <h4 class="center">{{orderToBeEdit.product}}<br/>
                    <img class="circle" ng-src="{{orderToBeEdit.strPhotoPath}}" height="150" width="150"></h4>
                <h6 class="center">Price: {{orderToBeEdit.price | currency: "Php "}}</h6>
                <div class="container">
                    <div class="row">
                        <div class="input-field col s4 offset-s4" style="margin-top: -20px !important;">
                            <input type="number" class="center-align" ng-model="orderToBeEdit.quantity">
                        </div>
                        <div class="input-field col s12" style="margin-top: -10px !important;">
                            <h5 class="center">Total: {{ (orderToBeEdit.price * orderToBeEdit.quantity) | currency: "Php "}}</h5>
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
                                <span style="padding-left: 5px !important;" title="{{orders.product}} - {{orders.total | currency: 'Php '}}"
                                      ng-click="openEditItem($index, orders)">
                                    {{orders.product | truncate: 17}}
                                </span>
                                <button name="" title="Decline" class="secondary-content red-text transparent"
                                        style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;"
                                        ng-click="removeToCart($index, orders)">
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
                                <span title="{{ request.strName }}">{{ request.strName | truncate: 12 }}</span>
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


    <div id="crProductSales" class="modal modal-fixed-footer"
         style="width: 50% !important; height: 70% !important; max-height: 100% !important; margin-top: -40px;">
        <form class="col s12" id="createPSForm" name="createPSForm" method="post">
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Order
                        <a id="btnCreateExit"
                           class="modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a>
                    </h4>
                    <div class="row">
                        <div class="stepps well">
                            <div class="container">
                                <div class="input-field col s6">
                                    <select ng-options="order.name for order in orderType track by order.id"
                                            ng-model="details.order"
                                            id="crOrderType">
                                    </select>
                                    <label for="crOrderType"><b>Select</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>

                                </div>
                                <div class="input-field col s6">
                                    <input type="text" name="strOrderContact" id="crOrderContact"
                                           ng-model="details.contact"/>
                                    <label for="crOrderContact"><b>Contact Number</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" name="strOrderName" id="crOrderName" ng-model="details.name"/>
                                    <label for="crOrderName"><b>Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6" id="crDivOrderSt"
                                     ng-if="details.order.id == 2">
                                    <input type="text" name="strOrderStreet" id="crOrderStreet"
                                           ng-model="details.street"/>
                                    <label for="crOrderStreet"><b>Street</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6" id="crDivOrderLoc"
                                     ng-if="details.order.id == 2">
                                    <select ng-options="location.strBarangay for location in locationList"
                                            ng-model="details.location" id="crOrderLoc">
                                    </select>
                                    <label for="crOrderLoc"><b>Location</b><i
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
                <button type="button" class="actionps backformps waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>
                <button type="button" id="nextbtn"
                        class="actionps nextformps waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button type="submit" value="Submit" id="crCreateOrderBtn"
                        class="actionps submitformps waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px;"
                        ng-click="setProdSalesPayment(details); commaProducts(); commaQuantity()">CREATE
                </button>
            </div>
        </form>
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
                            <input type="date" name="delDate" class="datepicker-delivery"
                                   id="deliveryDate" placeholder="August/01/2016" required
                                   ng-model="delivery.deliveryDate"/>
                            <label for="deliveryDate" class="active"><b>Delivery Date</b><i
                                    class="material-icons tiny red-text">error_outline</i></label>
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
