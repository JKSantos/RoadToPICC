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
                           style="margin-left: 15px;" ng-click="vm.createWalkinOpen();"
                           ng-if="vm.productOrder.length > 0 || vm.serviceOrder.length > 0 || vm.packageOrder.length > 0 || vm.promoOrder.length > 0">
                            <i class="material-icons">add</i>
                        </a>
                        <a class="crWalkin z-depth-1 hoverable btn purple darken-2 left white-text"
                           style="margin-left: 15px; opacity: 0.3; cursor: not-allowed !important;" ng-disabled="true"
                           ng-if="vm.productOrder.length == 0 && vm.serviceOrder.length == 0 && vm.packageOrder.length == 0 && vm.promoOrder.length == 0">
                            <i class="material-icons">add</i>
                        </a>
                        <a class="z-depth-1 hoverable waves-effect waves-light btn purple darken-2 left white-text"
                           href="transWalkinTable" style="margin-left: 15px;">
                            <i class="material-icons">border_all</i>
                        </a>
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
                                    <img class="activator" ng-src="{{product.strPhotoPath}}"
                                         ng-click="vm.closeNotSelectedInProd($index);">
                                </div>
                                <div class="card-content">
                                    <a class="activator grey-text text-darken-4 light btn btn-small center"
                                       ng-click="vm.closeNotSelectedInProd($index);"
                                       style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i></a>
                                    <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{product.strProductName}}</b>
                                        <p>{{product.dblProductPrice | currency:"P"}}</p></h5>
                                </div>
                                <div class="card-reveal">
                                    <span class="card-title grey-text text-darken-4 prodClose123">
                                        <i class="material-icons right prodClose{{$index}}"
                                           id="prodClose{{product.intProductID}}">close</i>
                                    </span>
                                    <h4 style='font-size: 12px; line-height: 15px !important;'>
                                        <b>{{product.strProductName}}</b><br/>
                                        <span class="grey-text text-darken-4">{{product.dblProductPrice | currency:"Php "}}</span>
                                    </h4>
                                    <div class="input-field col s12">
                                        <input type="number" id="crPSQty{{product.intProductID}}"
                                               ng-model="vm.quantity" min="1"/>
                                        <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                                    </div>
                                    <h6 class="grey-text text-darken-4">
                                        {{product.dblProductPrice * vm.quantity | currency: "Php "}}
                                    </h6>
                                    <a class="waves-effect waves-light btn"
                                       ng-if="vm.quantity > 0"
                                       ng-click="vm.addToCart($index, vm.selected);
                                                 vm.sumTotal();
                                                 vm.closeCard(product.intProductID, vm.selected)">
                                        <i class="material-icons left"
                                           style="padding: 0px !important; margin: 0px !important;">
                                            shopping_basket</i>BUY NOW!
                                    </a>
                                    <a class="btn"
                                       ng-if="vm.quantity < 1 || vm.quantity == ''"
                                       style=" opacity: 0.3; cursor: not-allowed !important;">
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
                             ng-repeat="service in vm.serviceList | toArray: false | filter: vm.walkinSearch"
                             ng-if="service.intServiceStatus == 1">
                            <div class="card small">
                                <div class="card-image waves-effect waves-block waves-light">
                                    <img class="activator" ng-src="{{service.strPhotoPath}}"
                                         ng-click="vm.filterEmployee($index);">
                                </div>
                                <div class="card-content">
                                    <a class="activator grey-text text-darken-4 light btn btn-small center"
                                       ng-click="vm.filterEmployee($index, service.intServiceID);"
                                       style="margin-top: -10px;"><i class="material-icons right white-text">add_shopping_cart</i>
                                    </a>
                                    <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{service.strServiceName}}</b>
                                        <p>{{service.dblServicePrice | currency:"P"}}</p></h5>
                                </div>
                                <div class="card-reveal">
                                    <span class="card-title grey-text text-darken-4 servReveal">
                                        <i class="material-icons right servClose{{$index}}"
                                           id="servClose{{service.intServiceID}}">close</i>
                                    </span>
                                    <h4 style='font-size: 12px; line-height: 15px !important;'>
                                        <b>{{service.strServiceName}}</b><br/>
                                        <span class="grey-text text-darken-4">{{service.dblServicePrice | currency:"Php "}}</span>
                                    </h4>
                                    <div input-field class="col s12 ddd">
                                        <select id="crEmpServSelEmp"
                                                ng-model="vm.selEmployee" material-select watch
                                                ng-options="emp.strEmpFirstName for emp in vm.filteredEmpForService">
                                            <option value="" selected>Choose...</option>
                                        </select>
                                        <label for="crEmpServSelEmp"><b>Employee</b></label>
                                    </div>

                                    <a class="waves-effect waves-light btn"
                                       ng-click="vm.addToCart($index, vm.selected);
                                                 vm.sumTotal();
                                                 vm.closeCard(service.intServiceID, vm.selected);"
                                       ng-if="vm.selEmployee != null">
                                        <i class="material-icons left"
                                           style="padding: 0px !important; margin: 0px !important;">
                                            shopping_basket</i>GET SERVICE!
                                    </a>
                                    <a class=" btn"
                                       ng-disabled="true"
                                       style="opacity: 0.3; cursor: not-allowed !important;"
                                       ng-if="vm.selEmployee == null">
                                        <i class="material-icons left"
                                           style="padding: 0px !important; margin: 0px !important;">
                                            shopping_basket</i>GET SERVICE!
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col s12">
                </div>

                <div ng-show="vm.selected == 'package'" style="margin-top: -50px !important;">
                    <div class="row ">
                        <div class="col s3"
                             ng-repeat="package in vm.packageList | toArray: false | filter: vm.walkinSearch"
                             ng-if="package.intPackageStatus > 0">
                            <div class="card small">
                                <div class="card-image waves-effect waves-block waves-light">
                                    <img class="activator" ng-src="{{service.strPhotoPath}}">
                                </div>
                                <div class="card-content">
                                    <a class="activator grey-text text-darken-4 light btn btn-small center"
                                       style="margin-top: -10px;">
                                        <i class="material-icons right white-text">add_shopping_cart</i>
                                    </a>
                                    <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{package.strPackageName}}</b>
                                        <p>{{package.dblPackagePrice | currency:"P"}}</p></h5>
                                </div>
                                <div class="card-reveal">
                                               <span class="card-title grey-text text-darken-4"><i
                                                       class="material-icons right">close</i></span>
                                    <h4 style='font-size: 12px; line-height: 15px !important;'>
                                        <b>{{package.strPackageName}}</b><br/>
                                        <span class="grey-text tex 	t-darken-4"></span>
                                    </h4>

                                    <a class="waves-effect waves-light btn"
                                       ng-click="vm.openPackageModal($index, package)">
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
                                       ng-click="vm.openPromoModal($index, promo)">
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
        <form class="col s12 css-form" name="createWalkinForm" id="createWalkinForm" novalidate>
            <div class="modal-content">

                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">
                        Create Walk-In
                        <a id="btnCrExtraExit" type="reset" value="Reset" class="modal-action modal-close">
                            <i class="small material-icons right grey-text text-darken-4">close</i>
                        </a>
                    </h4>
                    <!--<div class="walkinerrorcontainer card red center input-field col s12 white-text z-depth-barts">-->

                    <!--</div>-->
                    <div class="card red center input-field col s12 white-text z-depth-barts">
                        <span class="white-text"
                              ng-show="createWalkinForm.crWIName.$error.pattern">
                            Valid name is required <br>
                        </span>
                        <span class="white-text"
                              ng-show="createWalkinForm.crWIName.$error.minlength">
                            Name must be at least 5 characters <br>
                        </span>
                    </div>
                    <div class="stepwalkin well" style="margin-top: -5px;">
                        <div class="row">
                            <div class="container">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" id="crWIName" name="crWIName"
                                           ng-model="vm.details.name"
                                           ng-required="true"
                                           ng-minlength="5"
                                           ng-pattern="/^[A-Za-z \-'`]+$/" placeholder="Name"/>
                                    <label for="crWIName" class="active">
                                        <b>Name</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" id="crWIContact" ng-model="vm.details.contact"
                                           placeholder="contact"/>
                                    <label for="crWIContact" class="active">
                                        <b>Contact Number</b>
                                    </label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 15px;">
                                    <input type="email" ng-model="vm.details.email" id="crWIEmail" placeholder="Email"/>
                                    <label for="crWIEmail" class="active">
                                        <b>Email</b>
                                    </label>
                                </div>
                                <div class="input-field col s12">
                                    <select ng-model="vm.selDiscounts" id="crRDiscount" material-select watch
                                            ng-change="vm.selDiscountDetails(vm.selDiscounts);"
                                            ng-options="discount.strDiscountName for discount in vm.discountList">
                                        <option id="discOpt" value="" selected disabled>Choose...</option>
                                    </select>
                                    <label for="crRDiscount"><b>Discounts</b></label>
                                </div>
                                <div class="input-field col s12" ng-if="vm.count > 0">
                                    <p>
                                        <b>Note:</b> <br>
                                            <span ng-if="vm.discountDetail.strDiscountDesc.length > 0" class="col s12">
                                                <b>-</b><i>{{vm.discountDetail.strDiscountName}}</i>: {{vm.discountDetail.strDiscountDesc}}
                                            </span>
                                            <span ng-if="vm.discountDetail.strDiscountDesc.length < 1"
                                                  class="col s12">
                                                <b>-</b>There's no description for <i>"{{vm.discountDetail.strDiscountName}}"</i>
                                            </span>
                                        <br>
                                        <b>Discount Value:</b>
                                        <span ng-if="vm.discountDetail.intDiscountType == 1">{{vm.discountDetail.stringPrice | number:0}}%</span>
                                        <span ng-if="vm.discountDetail.intDiscountType == 2">{{vm.discountDetail.stringPrice | currency: "Php "}}</span>
                                        <br>
                                        <b class="grey-text text-darken-1">Total (w/o discount):</b>
                                        <span class="grey-text text-darken-1">{{vm.sum | currency: "Php "}}</span> <br>
                                        <b class="grey-text text-darken-1">Total (w/ discount):</b>
                                        <span class="grey-text text-darken-1">{{vm.sumWithDiscount | currency: "Php "}}</span>
                                        <br>
                                        <b>Requirement/s:</b> <br>
                                    <div class="chip" ng-repeat="req in vm.requirement" style="margin: 2px;">
                                        {{req}}
                                    </div>
                                    <br>
                                    <b class="grey-text text-darken-1">Item/s included:</b> <br>
                                    <div class="chip" style="margin: 2px;" ng-if="vm.discountApplicability.length > 0">
                                        {{vm.discountApplicability}}
                                    </div>
                                    <div class="chip"
                                         ng-if="vm.discountApplicability.length < 1"
                                         ng-repeat="prod in vm.prodInDiscount" style="margin: 2px;">
                                        {{prod.strProductName}}
                                    </div>
                                    <div class="chip"
                                         ng-if="vm.discountApplicability.length < 1"
                                         ng-repeat="serv in vm.servInDiscount" style="margin: 2px;">
                                        {{serv.strServiceName}}
                                    </div>
                                    <div class="chip"
                                         ng-if="vm.discountApplicability.length < 1"
                                         ng-repeat="pack in vm.packInDiscount" style="margin: 2px;">
                                        {{pack.strPackageName}}
                                    </div>
                                    <div class="chip"
                                         ng-if="vm.discountApplicability.length < 1"
                                         ng-repeat="promo in vm.promoInDiscount" style="margin: 2px;">
                                        {{promo.strPromoName}}
                                    </div>
                                    </p>
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
                   ng-if="createWalkinForm.$valid == true && vm.loadingBubble == 1"
                   ng-click="vm.saveWalkin(details)"
                   style="margin-left:3px; margin-right:3px;">CREATE
                </a>
                <a class="white-text btn-flat purple"
                   ng-disabled="true"
                   ng-if="createWalkinForm.$valid == false && vm.loadingBubble == 1"
                   style="margin-left:3px; margin-right:3px; opacity: 0.5 !important; cursor: not-allowed !important;">CREATE
                </a>
                <a class="white btn-flat"
                   ng-if="vm.loadingBubble == 0"
                   ng-disabled="true"
                   style="margin-left:3px; margin-right:3px;">
                    <div style="margin-top: 9px !important;">
                        <div class="bubbles1"></div>
                        <div class="bubbles1"></div>
                        <div class="bubbles1"></div>
                    </div>
                </a>


            </div>
        </form>
    </div>

    <div id="packageListModal" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Package Contains</h4>

            <div class="chip" ng-repeat="products in vm.packageContainProduct" style="margin: 5px !important;">
                <img ng-src="{{products.product.strPhotoPath}}">
                {{ products.product.strProductName }} (<b>{{products.intProductQuantity}}</b>)
            </div>
            <table>
                <tr ng-repeat="services in vm.packageContainService" style="margin: 0px !important;">
                    <td>{{services.service.strServiceName}}</td>
                    <td>
                        <div class="input-field col s12">
                            <select ng-model="vm.selEmployeePerService" id="cREmp3"
                                    ng-options="employee.strEmpFirstName for employee in vm.employeeList"></select>
                            <label for="cREmp3"><b>Employee</b></label>
                        </div>
                    </td>
                    <td>
                        <button class="waves-effect waves-light white-text btn-flat purple"
                                ng-click="vm.assignEmployeePackage($index)">
                            ASSIGN
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
            <button type="submit" value="Submit" id="paymentDetails.submit1"
                    class="waves-effect waves-light white-text btn-flat purple"
                    style="margin-left:3px; margin-right:3px;"
                    ng-click="vm.addToCart(vm.packageIndex, vm.selected); vm.sumTotal()">DONE
            </button>
        </div>
    </div>

    <div id="promoListModal" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Promo Contains</h4>
            <h5 class="purple-text">Products</h5>
                <div class="chip" ng-repeat="products in vm.promoContainsProduct">
                    {{products.product.strProductName}} ({{products.intProductQuantity}})
                  </div>
            <h5 class="purple-text">Services</h5>
            <div>
                <ul ng-repeat="services in vm.promoContainsService">
                    <li>
                      <h6> {{services.service.strServiceName}}</h6>
                    </li>
                    <li>
                      <div class="input-field col s6">
                            <ul ng-repeat="emp in vm.employeelist">
                                <li>
                                    {{emp.strEmpFirstName}}
                                </li>
                            </ul>
                          <select ng-model="vm.selEmployeePerService" id="cREmp1" material-select watch
                                  ng-options="employee.strEmpFirstName for employee in vm.employeeList">
                                      <option value="" disabled selected>Choose</option>
                                  </select>
                          <label for="cREmp1"><b>Employee</b></label>
                      </div>
                      <button id="btnAssign"
                              class="waves-effect waves-light white-text btn-flat purple"
                              ng-click="vm.assignEmployeePromo($index)">ASSIGN
                      </button>
                    </li>
                </ul>
            </div>
            <!-- <table>
                <tr >
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
                <tr ng-repeat="packageService in vm.promoContainsPackage">
                    <td>{{packageService.service.strServiceName}}</td>
                    <td>
                        <div class="input-field col s12">
                            <select ng-model="vm.selEmployeePerService" id="cREmp4"
                                    ng-options="employee.strEmpFirstName for employee in vm.employeeList"></select>
                            <label for="cREmp4"><b>Employee</b></label>
                        </div>
                    </td>
                    <td>
                        <button id="btnAssign1"
                                class="waves-effect waves-light white-text btn-flat purple"
                                ng-click="vm.assignEmployeePromo($index)">ASSIGN
                        </button>
                    </td>
                </tr>
            </table> -->
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
                    ng-click="vm.addToCart(vm.promoIndex, vm.selected); vm.sumTotal()">DONE
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
                            ng-repeat="order in vm.productOrder">
                            <img ng-src="{{order.strPhotoPath}}" class="circle" height="30" width="30">
                                <span style="padding-left: 5px !important; cursor: pointer !important;"
                                      title="{{order.product}} - {{order.productTotal | currency: 'Php '}}"
                                      ng-click="vm.openEditItem($index, order);">
                                    {{order.product | truncate: 15}}
                                </span>
                            <button name="" title="Remove" class="secondary-content red-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;"
                                    ng-click="vm.removeToCartProduct($index, order)">
                                <i class="material-icons" style="padding-top: 7px !important;">clear</i>
                            </button>
                        </li>
                        <li class="collection-item left-align"
                            style="margin-left:0px !important; padding-left: 5px !important;"
                            ng-repeat="service in vm.serviceOrder">
                            <img ng-src="{{service.strPhotoPath}}" class="circle" height="30" width="30">
                                <span style="padding-left: 5px !important; cursor: pointer !important;"
                                      title="{{service.service}} - {{service.serviceTotal | currency: 'Php '}}"
                                      ng-click="vm.openEditItem($index, service); vm.filterEmployee(service.idx); vm.selEmpServ(service);">
                                    {{service.service | truncate: 15}}
                                </span>
                            <button name="" title="Remove" class="secondary-content red-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;"
                                    ng-click="vm.removeToCartService($index, service)">
                                <i class="material-icons" style="padding-top: 7px !important;">clear</i>
                            </button>
                        </li>
                        <li class="collection-item left-align"
                            style="margin-left:0px !important; padding-left: 5px !important;"
                            ng-repeat="package in vm.packageOrder">
                            <img ng-src="img/package.png" class="circle" height="30" width="30">
                                <span style="padding-left: 5px !important; cursor: pointer !important;"
                                      title="{{package.package}} - {{package.packageTotal | currency: 'Php '}}"
                                      ng-click="vm.openEditItem($index, package)">
                                    {{package.package | truncate: 11}}
                                </span>
                            <button name="" title="Remove" class="secondary-content red-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;"
                                    ng-click="vm.removeToCartPackage($index, package)">
                                <i class="material-icons" style="padding-top: 7px !important;">clear</i>
                            </button>
                        </li>
                        <li class="collection-item left-align"
                            style="margin-left:0px !important; padding-left: 5px !important;"
                            ng-repeat="promo in vm.promoOrder">
                            <img ng-src="img/package.png" class="circle" height="30" width="30">
                                <span style="padding-left: 5px !important; cursor: pointer !important;"
                                      title="{{promo.promo}} - {{promo.promoTotal | currency: 'Php '}}"
                                      ng-click="vm.openEditItem($index, promo)">
                                    {{promo.promo | truncate: 11}}
                                </span>
                            <button name="" title="Remove" class="secondary-content red-text transparent"
                                    style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;"
                                    ng-click="vm.removeToCartPromo($index, promo)">
                                <i class="material-icons" style="padding-top: 7px !important;">clear</i>
                            </button>
                        </li>
                    </ul>
                    <h5>Total: {{ vm.sum | currency: "Php "}}</h5>
                </div>
            </div>
        </div>
    </div>



    <div id="editItem" class="modal modal-fixed-footer">
        <div class="modal-content" ng-if="vm.orderToBeEdit.type == 'product'">
            <h4 class="center">{{vm.orderToBeEdit.product}}<br/>
                <img class="circle" ng-src="{{vm.orderToBeEdit.strPhotoPath}}" height="150" width="150"></h4>
            <h6 class="center">Price: {{vm.orderToBeEdit.productPrice | currency: "Php "}}</h6>
            <div class="container">
                <form name="changeQtyProd">
                    <div class="row">
                        <div class="input-field col s4 offset-s4" style="margin-top: -20px !important;">
                            <input type="text" class="center-align" min="1"
                                   numbers-only
                                   name="prodQtyName"
                                   ng-model="vm.orderToBeEdit.productQuantity">
                        </div>
                        <div class="input-field col s12" style="margin-top: -10px !important;">
                            <h5 class="center">Total: {{ (vm.orderToBeEdit.productPrice *
                                vm.orderToBeEdit.productQuantity) | currency: "Php "}}</h5>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="modal-content" ng-if="vm.orderToBeEdit.type == 'service'">
            <h4 class="center">{{vm.orderToBeEdit.service}}<br/>
                <img class="circle" ng-src="{{vm.orderToBeEdit.strPhotoPath}}" height="150" width="150"></h4>
            <h6 class="center">Price: {{vm.orderToBeEdit.servicePrice | currency: "Php "}}</h6>
            <div class="container">
                <div class="row">
                    <div class="input-field col s8 offset-s2" style="margin-top: -10px !important;">
                        <select ng-model="vm.selEmployeeEdit" id="cREmp2" material-select watch
                                ng-options="employee.strEmpFirstName for employee in vm.filteredEmpForService">
                            <option value="">Choose...</option>
                        </select>
                        <label for="cREmp2"><b>Employee</b></label>
                    </div>
                    <div class="input-field col s12" style="margin-top: -10px !important;">
                        <h5 class="center">Total: {{ vm.orderToBeEdit.servicePrice | currency: "Php "}}</h5>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button class="waves-effect waves-light btn-flat purple white-text"
                    ng-if="vm.orderToBeEdit.productQuantity > 0 || vm.selEmployeeEdit != ''"
                    ng-click="vm.editInCart(vm.orderToBeEdit)">
                SAVE
            </button>
            <a class="btn-flat purple white-text"
               ng-if="vm.orderToBeEdit.productQuantity < 0"
               ng-disabled="true"
               style="opacity: 0.3 !important; cursor: not-allowed !important;">
                SAVE
            </a>
        </div>
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
                <tr ng-repeat="walkin in vm.walkinList">
                    <td class="left-align">{{walkin.strName | uppercase}}</td>
                    <td class="right-align">{{walkin.strContactNo}}</td>
                    <td class="left-align">{{walkin.strWalkInType | uppercase}}</td>
                    <td class="left-align">{{walkin.strWalkInStatus | uppercase}}</td>
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

<script type="text/javascript">
    (function() {
        $('select').material_select('destroy');
    })();
</script>
