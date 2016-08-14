<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="walkinCtrl">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Walk-In</h3>
            <a class="crWalkin z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createWalkinModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#walkinModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="walkinSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search" required>
                            <label for="walkinSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="walkintbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Guest Type</th>
                    <th class="dt-head-center no-sort">Date</th>
                    <th class="dt-head-left">Status</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Guest Type</th>
                    <th class="dt-head-center no-sort">Date</th>
                    <th class="dt-head-left">Status</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>
            </table>
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
                                           name="" placeholder="Name"/>
                                    <label for="crWIName" class="active"><b>Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" id="crWIContact" name="" placeholder="contact"/>
                                    <label for="crWIContact" class="active"><b>Contact</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 15px;">
                                    <input type="email" name="" id="crWIEmail" placeholder="Email"/>
                                    <label for="crWIEmail" class="active"><b>Email</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="stepwalkin well" ng-controller="walkinCtrl">
                        <div class="row">
                            <div class="col s4">
                                <select id="discountFilter" ng-model="vm.selected">
                                    <option value="product">Product</option>
                                    <option value="service">Service</option>
                                    <option value="package">Package</option>
                                    <option value="promo">Promo</option>
                                </select>
                                <label for="discountFilter"><b>Select</b></label>
                            </div>

                            <nav class="right white z-depth-1" style="width: 300px; margin-right: 20px;">
                                <div class="nav-wrapper col s12">
                                    <form>
                                        <div class="input-field">
                                            <input id="crDiscountSearch" placeholder="Search"
                                                   class="grey-text text-darken-4"
                                                   type="search" style="border: none !important;">
                                            <label for="crDiscountSearch"><i
                                                    class="material-icons grey-text text-darken-4">search</i></label>
                                        </div>
                                    </form>
                                </div>
                            </nav>
                            
                            <div class="row col s12">

                            </div>

                             <div >
                                <div class="row ">
                                    <div class="col s2" ng-repeat="product in vm.productList">
                                        <div class="card small">
                                            <div class="card-image waves-effect waves-block waves-light">
                                                <img class="activator" ng-src="{{product.strPhotoPath}}">
                                            </div>
                                            <div class="card-content">
                                                <a class="activator grey-text text-darken-4 light btn btn-small center" style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
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
                                                    <input type="number" id="crPSQty{{product.intProductID}}" ng-model="vm.quantity"/>
                                                    <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                                                </div>
                                                <h6 class="grey-text text-darken-4">{{product.dblProductPrice * vm.quantity | currency:
                                                    "Php "}}</h6>

                                                <a class="waves-effect waves-light btn" ng-click="vm.addToCart($index, vm.selected); vm.sumTotal()">
                                                    <i class="material-icons left" style="padding: 0px !important; margin: 0px !important;">
                                                        shopping_basket</i>BUY NOW!
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                             </div>

                            <div ng-show="vm.selected == 'service'">
                                <div class="row ">
                                    <div class="col s2" ng-repeat="service in vm.serviceList">
                                        <div class="card small">
                                            <div class="card-image waves-effect waves-block waves-light">
                                                <img class="activator" ng-src="{{service.strPhotoPath}}">
                                            </div>
                                            <div class="card-content">
                                                <a class="activator grey-text text-darken-4 light btn btn-small center" style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
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
                                                    <input type="number" id="crPSQty{{product.intProductID}}" ng-model="vm.quantity"/>
                                                    <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                                                </div>
                                                <div class="input-field col s12">
                                                   <select multiple ng-model="vm.selEmployees" id="cREmp" ng-options="employee.strEmpFirstName for employee in vm.employeeList"></select>
                                                    <label for="cREmp"><b>Employees</b></label>
                                                </div>
                                                <h6 class="grey-text text-darken-4">{{service.dblServicePrice * vm.quantity | currency:
                                                    "Php "}}</h6>

                                                 <a class="waves-effect waves-light btn" ng-click="vm.addToCart($index, vm.selected); vm.sumTotal()">
                                                    <i class="material-icons left" style="padding: 0px !important; margin: 0px !important;">
                                                        shopping_basket</i>BUY NOW!
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div ng-show="vm.selected == 'package'">
                               <div class="row ">
                                   <div class="col s2" ng-repeat="package in vm.packageList">
                                       <div class="card small">
                                           <div class="card-image waves-effect waves-block waves-light">
                                               <img class="activator" ng-src="{{service.strPhotoPath}}">
                                           </div>
                                           <div class="card-content">
                                               <a class="activator grey-text text-darken-4 light btn btn-small center" style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
                                               <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{package.strPackageName}}</b>
                                                   <p>{{package.dblPackagePrice | currency:"P"}}</p></h5>
                                           </div>
                                           <div class="card-reveal">

                                               <span class="card-title grey-text text-darken-4"><i
                                                       class="material-icons right">close</i></span>
                                               <h4 style='font-size: 12px; line-height: 15px !important;'>
                                                   <b>{{package.strPackageName}}</b><br/>
                                                   <span class="grey-text text-darken-4">{{package.dblPackagePrice | currency:"Php "}}</span>
                                               </h4>
                                               <div class="input-field col s12">
                                                   <input type="number" id="crPSQty{{product.intProductID}}" ng-model="vm.quantity"/>
                                                   <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                                               </div>
                                               <h6 class="grey-text text-darken-4">{{package.dblPackagePrice * vm.quantity | currency: "Php "}}</h6>

                                               <a class="waves-effect waves-light btn" ng-click="vm.addToCart($index, vm.selected); vm.sumTotal()">
                                                    <i class="material-icons left" style="padding: 0px !important; margin: 0px !important;">
                                                        shopping_basket</i>BUY NOW!
                                                </a>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                            </div>

                            <div ng-show="vm.selected == 'promo'">
                                <div class="row ">
                                   <div class="col s2" ng-repeat="promo in vm.promoList">
                                       <div class="card small">
                                           <div class="card-image waves-effect waves-block waves-light">
                                               <img class="activator" ng-src="{{service.strPhotoPath}}">
                                           </div>
                                           <div class="card-content">
                                               <a class="activator grey-text text-darken-4 light btn btn-small center" style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
                                               <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{promo.strPromoName}}</b>
                                                   <p>{{promo.dblPromoPrice | currency:"P"}}</p></h5>
                                           </div>
                                           <div class="card-reveal">

                                               <span class="card-title grey-text text-darken-4"><i
                                                       class="material-icons right">close</i></span>
                                               <h4 style='font-size: 12px; line-height: 15px !important;'>
                                                   <b>{{promo.strPromoName}}</b><br/>
                                                   <span class="grey-text text-darken-4">{{promo.dblPromoPrice | currency:"Php "}}</span>
                                               </h4>
                                               <div class="input-field col s12">
                                                   <input type="number" id="crPSQty{{product.intProductID}}" ng-model="vm.quantity"/>
                                                   <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                                               </div>
                                               <h6 class="grey-text text-darken-4">{{promo.dblPromoPrice * vm.quantity | currency:
                                                   "Php "}}</h6>

                                               <a class="waves-effect waves-light btn" ng-click="vm.addToCart($index, vm.selected); vm.sumTotal()">
                                                    <i class="material-icons left" style="padding: 0px !important; margin: 0px !important;">
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
                            <div class="col s4 offset-s4" style="margin-top: 10px;">
                                <div class="input-field col s12">
                                    <select multiple id="crRDiscount">
                                        <option value="" disabled selected>Choose your option</option>
                                        <c:forEach items="${ discountList }" var="discount">
                                        	<option value="${discount.intDiscountID}">${discount.strDiscountName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="crRDiscount"><b>Discounts</b></label>
                                </div>
                            </div>
                            <div class="col s4 z-depth-barts white" style="margin-top: 10px;">
                                <div class="col s12">
                                    <div class="input-field col s12">
                                        <p>Total: <span class="right-align"></span></p>
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
                        style="margin-left:3px; margin-right:3px;">CREATE
                </button>
                <button type="button" id="backbtn"
                        class="actionwalkin backformwalkin waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>

            </div>
        </form>
    </div>
</div>
