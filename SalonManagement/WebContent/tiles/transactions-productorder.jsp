<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div ng-controller="prodSalesCtrl">
<div class="wrapper" id="MainWrap" ng-controller = "prodSalesCtrl">
    <div class="aside aside1 z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Product Sales</h3>
            <a class="pscrbtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn left green darken-2 white-text"
               href="#crProductSales" style="margin-top: 30px; margin-left: 15px;">CHECK OUT<i
                    class="material-icons right">shopping_cart</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#prodsalesArchive" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 40%;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="psSearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                   required/>
                            <label for="psSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <!-- <table id="productsalestbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Address</th>
                    <th class="dt-head-right no-sort">Contact</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-left">Status</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Address</th>
                    <th class="dt-head-right no-sort">Contact</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-left">Status</th>
                    <th class="no-sort dt-head-center">Action</th>
                </tr>
                </tfoot>

                <tbody>
                </tbody>
            </table> -->
            </div>

            <div class="row col s12">

            </div>

            <div class="row ">
                <div class="col s3" ng-repeat="product in productList">
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

                    <span class="card-title grey-text text-darken-4"><i class="material-icons right">close</i></span>
                    <h4 style='font-size: 15px; line-height: 15px !important;'>
                        <b>{{product.strProductName}}</b><br/>
                        <span class="grey-text text-darken-4">{{product.dblProductPrice | currency:"Php "}}</span>
                    </h4>
                        <div class="input-field col s12">
                            <input type="number" id="crPSQty{{product.intProductID}}" ng-model = "details.quantity"/>
                            <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                        </div>
                        <h6 class="grey-text text-darken-4">{{product.dblProductPrice * details.quantity | currency: "Php "}}</h6>
                        
                        <button class="waves-effect waves-light btn" ng-click="addToCart($index); calculateTotal()">
                            <i class="material-icons left" style="padding: 0px !important; margin: 0px !important;">
                            shopping_basket</i>BUY NOW!</button>
                    </div>
                </div>
                </div>
                
            </div>
            <ul>
                <li ng-repeat = "orders in orderList">{{orders.product}} - {{orders.total | currency: "Php"}} </li>
            </ul>
            <h4>Total: {{totalAmount | currency:"Php "}} </h4>
        </div>

        <div class="aside aside2 z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
            <div class="row">
                <div class="col s12 transparent">
                    <h5>Request of</h5>
                    <ul class="collection with-header" id="reqList">
                    </ul>
                </div>
            </div>
        </div>
    </div>


    <div id="crProductSales" class="modal modal-fixed-footer"
         style="width: 50% !important; height: 70% !important; max-height: 100% !important; margin-top: -40px;"
         >
        <form class="col s12" id="createPSForm" name="createPSForm" method="post">
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Order
                        <a id="btnCreateExit"
                           class="modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a>
                    </h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
                    <div class="row">
                        <div class="stepps well">    
                            <div class="container">
                                <div class="input-field col s6" >
                                  <p>Order Type</p>
                                  <select class="browser-default" ng-options="order.name for order in orderType track by order.id" ng-model = "details.order"
                                  id = "crOrderType">
                                  </select>

                                </div>

                                <div class="input-field col s6">
                                    <input type="text" name="strOrderContact" id="crOrderContact" ng-model = "details.contact"/>
                                    <label for="crOrderContact"><b>Contact Number</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" name="strOrderName" id="crOrderName" ng-model = "details.name"/>
                                    <label for="crOrderName"><b>Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6" id="crDivOrderSt">
                                    <input type="text" name="strOrderStreet" id="crOrderStreet" ng-model = "details.street"/>
                                    <label for="crOrderStreet"><b>Street</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6" id="crDivOrderLoc">
                                  <select class="browser-default" ng-options="location.strBarangay for location in locationList" 
                                  ng-model = "details.location">
                                  </select>
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
                        style="margin-left:3px; margin-right:3px;" ng-click="setProdSalesPayment(details); commaProducts(); commaQuantity()">CREATE
                </button>
            </div>
        </form>

        <pre>{{details | json}}</pre>
    </div>
</div>
