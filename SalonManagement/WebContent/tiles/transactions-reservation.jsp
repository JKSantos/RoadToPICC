<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="wrapper" ng-controller="reservationTable as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Reservation</h3>
            <a class="crReservation z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createReservationModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#reservationModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">archive</i></a>
             <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#reservationListModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">list</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="reservationSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search"
                                   ng-model="reservationSearch">
                            <label for="reservationSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <div class="row"></div>
            <div>
            	<div id="reservationCalendar"></div>
            </div>


            
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="reservationListModal" class="modal"style = "width: 70% !important; height: 80% !important; border-radius: 10px">
        <div class="modal-content">
         	<a href="#!" class=" modal-action modal-close waves-effect waves-purple btn-flat right"><i
                    class="material-icons red-text" style="font-size: 30px ">highlight_off</i></a>
            <h3 class="purple-text text-darken-3 thin">Reservation List</h3>
            <table id="reservationTable"
                   class="hoverable z-depth-1 cell-border row-border display responsive-table highlight"
                   datatable="ng"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="left-align">Customer Name</th>
                    <th class="left-align">Customer Type</th>
                    <th class="left-align">Reservation Type</th>
                    <th class="left-align">Venue</th>
                    <th class="right-align">Reservation Date</th>
                    <th class="left-align">Status</th>
                    <th align="center-align">Action</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="customer in ::vm.customerList.slice().reverse() | filter: reservationSearch"
                    ng-if="customer.strStatus == 'PENDING' || customer.strStatus == 'REQUEST'">
                    <td class="left-align">{{customer.customer.strName}}</td>
                    <td class="left-align">{{customer.intReservationType }}</td>
                    <td class="left-align" ng-if="customer.intReservationType == 1">HOME SERVICE</td>
                    <td class="left-align" ng-if="customer.intReservationType == 2">EVENT</td>
                    <td class="left-align">{{customer.strVenue}}</td>
                    <td class="right-align">{{customer.datFrom | date: "MMMM/d/yyyy" }}</td>
                    <td class="left-align">{{customer.strStatus}}</td>
                    <td align="center-align">
                        <button class="waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Cancel"
                                ng-if="customer.intReservationType == 1"
                                ng-click="cancelHomeService(customer, $index)">
                            <i class='material-icons'>exit</i>
                        </button>
                        <button class="waves-effect waves-purple btn-flat transparent grey-text text-darken-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Accept"
                                ng-if="customer.intReservationType == 1"
                                ng-click="acceptHomeService(customer, $index)">
                            <i class='material-icons'>done</i>
                        </button>
                        <button class="btn-flat transparent red-text text-lighten-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                ng-if="customer.intReservationType == 2"
                                ng-disabled=true>
                            <i class='material-icons'>exit</i>
                        </button>
                        <button class="btn-flat transparent grey-text text-lighten-3"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                ng-if="customer.intReservationType == 2"
                                ng-disabled=true>
                            <i class='material-icons'>done</i>
                        </button>
                    </td>
                </tr>
                </tbody>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="left-align">Customer Name</th>
                    <th class="left-align">Customer Type</th>
                    <th class="left-align">Reservation Type</th>
                    <th class="left-align">Venue</th>
                    <th class="right-align">Reservation Date</th>
                    <th class="left-align">Status</th>
                    <th align="center-align">Action</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="createReservationModal" class="modal modal-fixed-footer"
         style="width: 70% !important; height: 90% !important; max-height: 100% !important; margin-top: -40px;">
        <form class="col s12" id="createReservationForm" method="post" ng-submit="vm.saveReservation()">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Reservation<a id="btnCrExtraExit" type="reset"
                                                                                    value="Reset"
                                                                                    class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
                    <div class="reservationerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="stepreservation well" style="margin-top: -5px;">
                        <div class="container">
                        <h5><b>Customer Details</b></h5>
                            <div class="row">
                                <div class="input-field col s6">
                                    <select id="rType"
                                            ng-model="vm.details.reservationType"
                                            ng-options="type as type.type for type in vm.reservationType">
                                    </select>
                                    <label for="rType">
                                        <b>Reservation Type</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s6"
                                     ng-if="vm.details.reservationType.id == 2">
                                    <label style="margin-top: -25px;"><b>Customer Type</b></label>
                                    <input type="radio" name="ctype" id="crRCustType1" ng-model="vm.individual"
                                           value="Individual">
                                    <label for="crRCustType1">Individual</label>
                                    <input type="radio" name="ctype" id="crRCustType2" ng-model="vm.individual"
                                           value="Company">
                                    <label for="crRCustType2">Company</label>
                                </div>
                                <div class="input-field col s12">
                                    <div class="input-field col s6">
                                        <input type="text" class="validate" id="crRCustName"
                                               ng-model="vm.details.name" placeholder="Customer Name"/>
                                        <label for="crRCustName" class="active"><b>Customer Name</b>
                                            <i class="material-icons red-text tiny">error_outline</i>
                                        </label>
                                    </div>
                                    <div class="input-field col s6"
                                         ng-if="vm.details.reservationType.id == 2">
                                        <input type="text" class="validate"
                                               id="crRCompanyName" ng-model="vm.details.company"
                                               placeholder="Company Name"
                                               ng-disabled="vm.individual == 'Individual'">
                                        <label for="crRCompanyName" class="active"><b>Company Name</b></label>
                                    </div>
                                </div>
                                <div class="input-field col s12" style="margin-top: 15px;">
                                    <input type="text" id="crRAddress" ng-model="vm.details.address"
                                           placeholder="Address"/>
                                    <label for="crRAddress" class="active"><b>Address</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6">
                                    <input type="text" id="crRContact" ng-model="vm.details.contact"
                                           placeholder="contact"/>
                                    <label for="crRContact" class="active"><b>Contact</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6" style="margin-top: 15px;">
                                    <input type="email" ng-model="vm.details.email" id="crREmail" placeholder="Email"/>
                                    <label for="crREmail" class="active"><b>Email</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="stepreservation well">
                        <div class="container">
                            <div class="row">
                                <h5><b>Reservation Details</b></h5>
                                <div class="input-field col s12"
                                     ng-if="vm.details.reservationType.id == 2">
                                    <input type="text" class="validate" id="crREventVenue"
                                           ng-model="vm.details.venue" placeholder="Event Venue"/>
                                    <label for="crREventVenue" class="active"><b>Event Venue</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6">
                                    <input input-date
                                           type="text"
                                           id="ngDateFrom"
                                           placeholder="January/1/2016"
                                           ng-model="vm.details.datFrom"
                                           months-full="{{ vm.month }}"
                                           disable="disable"
                                           min="{{ vm.minDate }}"
                                           max="{{ vm.maxDate }}"
                                           today="today"
                                           clear="clear"
                                           close="close"
                                           select-years="15"
                                           ng-change="vm.changeDatFrom(vm.details.datFrom)"/>
                                    <label for="ngDateFrom" class="active"><b>Date From</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6" ng-show="vm.details.reservationType.id == 2">
                                    <input input-date
                                           type="text"
                                           placeholder="January/1/2016"
                                           id="ngDateTo"
                                           ng-model="vm.details.datTo"
                                           months-full="{{ vm.month }}"
                                           months-short="{{ vm.monthShort }}"
                                           weekdays-full="{{ vm.weekdaysFull }}"
                                           weekdays-short="{{ vm.weekdaysShort }}"
                                           weekdays-letter="{{ vm.weekdaysLetter }}"
                                           disable="disable"
                                           min="{{vm.details.datFrom}}"
                                           max="{{ vm.maxDate }}"
                                           today="today"
                                           clear="clear"
                                           close="close"
                                           select-years="15"
                                           ng-change="vm.changeDatTo(vm.details.datTo)"/>
                                    <label for="ngDateTo" class="active">
                                        <b>Date To</b>
                                        <i class="material-icons red-text tiny">
                                            error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s6">
                                    <input id="reserveTimeFrom"
                                           class="timepicker"
                                           type="time"
                                           ng-model="vm.details.timeFrom"
                                           placeholder="12:00AM">
                                    <label for="reserveTimeFrom">
                                        <b>From (Time)</b>
                                        <i class="material-icons red-text tiny">
                                            error_outline
                                        </i>
                                    </label>
                                </div>
                                <div class="input-field col s6" ng-show="vm.details.reservationType.id == 2">
                                    <input id="reserveTimeTo"
                                           class="timepicker"
                                           type="time"
                                           ng-model="vm.details.timeTo"
                                           placeholder="12:00AM">
                                    <label for="reserveTimeTo" class="active">
                                        <b>To (Time)</b>
                                        <i class="material-icons red-text tiny">
                                            error_outline
                                        </i>
                                    </label>
                                </div>
                                <div class="input-field col s6">
                                    <input type="text" ng-model="vm.details.headCount" id="crRHeadCount"
                                           placeholder="Headcount"/>
                                    <label for="crRHeadCount"><b>Head Count</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="stepreservation well">
                        <div class="row">
                            <div class="col s4">
                                <select name="" id="discountFilter" ng-model="vm.selected">
                                    <option value="product" selected>Product</option>
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
                                                   type="search" style="border: none !important;"
                                                   ng-model="searchReservationItem">
                                            <label for="crDiscountSearch"><i
                                                    class="material-icons grey-text text-darken-4">search</i></label>
                                        </div>
                                    </form>
                                </div>
                            </nav>

                            <div class="row col s12">

                            </div>

                            <div ng-show="vm.selected == 'product'">
                                <div class="row ">
                                    <div class="col s2" ng-repeat="product in vm.productList | filter: searchReservationItem">
                                        <div class="card small">
                                            <div class="card-image waves-effect waves-block waves-light">
                                                <img class="activator" ng-src="{{product.strPhotoPath}}">
                                            </div>
                                            <div class="card-content">
                                                <a class="activator grey-text text-darken-4 light btn btn-small center"
                                                   style="margin-top: -10px;"><i
                                                        class="material-icons right white-text">add_shopping_cart</i></a>
                                                <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{product.strProductName}}</b>
                                                    <p>{{product.dblProductPrice | currency:"P"}}</p></h5>
                                            </div>
                                            <div class="card-reveal">

                                    <span class="card-title grey-text text-darken-4"><i
                                            class="material-icons right" id="prodClose{{product.intProductID}}">close</i></span>
                                                <h4 style='font-size: 12px; line-height: 15px !important;'>
                                                    <b>{{product.strProductName}}</b><br/>
                                                    <span class="grey-text text-darken-4">{{product.dblProductPrice | currency:"Php "}}</span>
                                                </h4>
                                                <div class="input-field col s12">
                                                    <input type="number" id="crPSQty{{product.intProductID}}"
                                                           ng-model="vm.quantity"/>
                                                    <label for="crPSQty{{product.intProductID}}"><b>Quantity</b></label>
                                                </div>
                                                <h6 class="grey-text text-darken-4">{{product.dblProductPrice *
                                                    vm.quantity | currency:
                                                    "Php "}}</h6>

                                                <a class="waves-effect waves-light btn"
                                                   ng-click="vm.addToCart($index, vm.selected); vm.sumTotal(); vm.closeCard(product.intProductID);">
                                                    <i class="material-icons left"
                                                       style="padding: 0px !important; margin: 0px !important;">
                                                        shopping_basket
                                                    </i>BUY NOW!
                                                </a>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div ng-show="vm.selected == 'service'">
                                <div class="row ">
                                    <div class="col s2" ng-repeat="service in vm.serviceList | filter: searchReservationItem ">
                                        <div class="card small">
                                            <div class="card-image waves-effect waves-block waves-light">
                                                <img class="activator" ng-src="{{service.strPhotoPath}}">
                                            </div>
                                            <div class="card-content">
                                                <a class="activator grey-text text-darken-4 light btn btn-small center"
                                                   style="margin-top: -10px;"><i
                                                        class="material-icons right white-text">add_shopping_cart</i></a>
                                                <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{service.strServiceName}}</b>
                                                    <p>{{service.dblServicePrice | currency:"P"}}</p></h5>
                                            </div>
                                            <div class="card-reveal">

                                    <span class="card-title grey-text text-darken-4"><i
                                            class="material-icons right" id="servClose{{service.intServiceID}}">close</i></span>
                                                <h4 style='font-size: 12px; line-height: 15px !important;'>
                                                    <b>{{service.strServiceName}}</b><br/>
                                                    <span class="grey-text text-darken-4">{{service.dblServicePrice | currency:"Php "}}</span>
                                                </h4>
                                                <div class="input-field col s12">
                                                    <input type="number" id="crPSQty{{service.intServiceID}}"
                                                           ng-model="vm.quantity"/>
                                                    <label for="crPSQty{{service.intServiceID}}"><b>Quantity</b></label>
                                                </div>
                                                <h6 class="grey-text text-darken-4">{{service.dblServicePrice *
                                                    vm.quantity | currency:
                                                    "Php "}}</h6>

                                                <a class="waves-effect waves-light btn"
                                                   ng-click="vm.addToCart($index, vm.selected); vm.sumTotal(); vm.closeService(service.intServiceID);">
                                                    <i class="material-icons left"
                                                       style="padding: 0px !important; margin: 0px !important;">
                                                        shopping_basket</i>GET SERVICE
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div ng-show="vm.selected == 'package'">
                                <div class="row ">
                                    <div class="col s2" ng-repeat="package in vm.packageList | filter: searchReservationItem">
                                        <div class="card small">
                                            <div class="card-image waves-effect waves-block waves-light">
                                                <img class="activator" ng-src="{{service.strPhotoPath}}">
                                            </div>
                                            <div class="card-content">
                                                <a class="activator grey-text text-darken-4 light btn btn-small center"
                                                   style="margin-top: -10px;"><i
                                                        class="material-icons right white-text">add_shopping_cart</i></a>
                                                <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{package.strPackageName}}</b>
                                                    <p>{{package.dblPackagePrice | currency:"P"}}</p></h5>
                                            </div>
                                            <div class="card-reveal">

                                   <span class="card-title grey-text text-darken-4"><i
                                           class="material-icons right" id="packClose{{package.intPackageID}}">close</i></span>
                                                <h4 style='font-size: 12px; line-height: 15px !important;'>
                                                    <b>{{package.strPackageName}}</b><br/>
                                                    <span class="grey-text text-darken-4">{{package.dblPackagePrice | currency:"Php "}}</span>
                                                </h4>
                                                <div class="input-field col s12">
                                                    <input type="number" id="crPSQty{{package.intPackageID}}"
                                                           ng-model="vm.quantity"/>
                                                    <label for="crPSQty{{package.intPackageID}}"><b>Quantity</b></label>
                                                </div>
                                                <h6 class="grey-text text-darken-4">{{package.dblPackagePrice *
                                                    vm.quantity | currency: "Php "}}</h6>

                                                <a class="waves-effect waves-light btn"
                                                   ng-click="vm.addToCart($index, vm.selected); vm.sumTotal(); vm.closePackage(package.intPackageID);">
                                                    <i class="material-icons left"
                                                       style="padding: 0px !important; margin: 0px !important;">
                                                        shopping_basket</i>GET PACKAGE
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div ng-show="vm.selected == 'promo'">
                                <div class="row ">
                                    <div class="col s2" ng-repeat="promo in vm.promoList | filter: searchReservationItem">
                                        <div class="card small">
                                            <div class="card-image waves-effect waves-block waves-light">
                                                <img class="activator" ng-src="{{service.strPhotoPath}}">
                                            </div>
                                            <div class="card-content">
                                                <a class="activator grey-text text-darken-4 light btn btn-small center"
                                                   style="margin-top: -10px;"><i
                                                        class="material-icons right white-text">add_shopping_cart</i></a>
                                                <h5 style='font-size: 12px; line-height: 10px !important;'><b>{{promo.strPromoName}}</b>
                                                    <p>{{promo.dblPromoPrice | currency:"P"}}</p></h5>
                                            </div>
                                            <div class="card-reveal">

                                   <span class="card-title grey-text text-darken-4"><i
                                           class="material-icons right" id="promoClose{{promo.intPromoID}}">close</i></span>
                                                <h4 style='font-size: 12px; line-height: 15px !important;'>
                                                    <b>{{promo.strPromoName}}</b><br/>
                                                    <span class="grey-text text-darken-4">{{promo.dblPromoPrice | currency:"Php "}}</span>
                                                </h4>
                                                <div class="input-field col s12">
                                                    <input type="number" id="crPSQty{{promo.intPromoID}}"
                                                           ng-model="vm.quantity"/>
                                                    <label for="crPSQty{{promo.intPromoID}}"><b>Quantity</b></label>
                                                </div>
                                                <h6 class="grey-text text-darken-4">{{promo.dblPromoPrice * vm.quantity
                                                    | currency:
                                                    "Php "}}</h6>

                                                <a class="waves-effect waves-light btn"
                                                   ng-click="vm.addToCart($index, vm.selected); vm.sumTotal(); vm.closePromo(promo.intPromoID);">
                                                    <i class="material-icons left"
                                                       style="padding: 0px !important; margin: 0px !important;">
                                                        shopping_basket</i>GET PROMO
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col s12 z-depth-barts white" id="">
                                <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                <div class="col s12" id="pslist"
                                     style="margin-top: -13px !important; margin-bottom: 5px !important;">
                                    <div class="chip" ng-repeat="product in vm.productOrder" style="margin: 2px;"
                                                      ng-if="product.productTotal > 0">
                                        <img ng-src="{{product.photo}}" alt="img">
                                        {{product.product | uppercase }} ({{product.productQuantity}} - {{product.productTotal | currency: "Php "}})
                                    </div>
                                    <div class="chip" ng-repeat="service in vm.serviceOrder" style="margin: 2px;"
                                         ng-if="service.serviceTotal > 0">
                                        <img ng-src="{{service.photo}}" alt="img">
                                        {{service.service | uppercase }} ({{service.serviceQuantity}} - {{service.serviceTotal | currency: "Php "}})
                                    </div>
                                    <div class="chip" ng-repeat="package in vm.packageOrder" style="margin: 2px;"
                                         ng-if="package.packageTotal > 0">
                                        {{package.package | uppercase }} ({{package.packageQuantity}} - {{package.packageTotal | currency: "Php "}})
                                    </div>
                                    <div class="chip" ng-repeat="promo in vm.promoOrder" style="margin: 2px;"
                                         ng-if="promo.promoQuantity > 0">
                                        {{promo.promo | uppercase }} ({{promo.promoQuantity}} - {{promo.promoTotal | currency: "Php "}})
                                    </div>
                                </div>
                            </div>
                            <div class="col s4" style="margin-top: 10px;">
                                <div class="input-field col s12">
                                    <select multiple ng-model="vm.extraCharge" id="crROtherCharge"
                                            ng-options="charge.strECName for charge in vm.extraChargeList"></select>

                                    <label for="crROtherCharge"><b>Other Charges</b></label>
                                </div>
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
                                        <h5 class="green-text">Total: {{vm.sum | currency: "Php"}}</h5>
                                    </div>

                                </div>
                            </div>
                            <div class="col s8" style="margin-top: 10px;">
                                <div class="input-field col s12">
                                    <select multiple ng-model="vm.selEmployees" id="cREmp"
                                            ng-options="employee.strEmpFirstName for employee in vm.employeeList">
                                    </select>
                                    <label for="cREmp"><b>Employee</b></label>
                                    <pre>{{vm.selEmployees.intEmpID | json}}</pre>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="stepreservation well" ng-hide="vm.details.reservationType.id == 1">
                        <div class="row" ng-show="vm.details.reservationType.id != 2">
                        </div>
                        <div ng-show="vm.details.reservationType.id == 2">
                            <div class="row">
                                <h3 class="center"><b>Summary</b></h3>
                                <h5><b>Customer Details</b></h5>
                                <div class="input-field col s12" id="crRSumCustName">
                                    <input type="text" readonly ng-model="vm.details.name"/>
                                    <label for="crRSumCustName" class=><b>Customer Name</b></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 15px;">
                                    <input type="text" readonly id="crRSumAddress" ng-model="vm.details.address"/>
                                    <label for="crRSumAddress"><b>Address</b></label>
                                </div>
                                <div class="input-field col s6">
                                    <input type="text" readonly id="crRSumContact" ng-model="vm.details.contact"/>
                                    <label for="crRSumContact"><b>Contact</b></label>
                                </div>
                                <div class="input-field col s6" style="margin-top: 15px;">
                                    <input type="email" readonly id="crRSumEmail" ng-model="vm.details.email"/>
                                    <label for="crRSumEmail" class="active"><b>Email</b></label>
                                </div>
                                <div class="input-field col s6">
                                    <input type="text" name="" id="crRSumCustType"/>
                                    <label for="crRSumCustType"><b>Customer Type</b></label>
                                </div>
                            </div>
                            <div class="row">
                                <h5><b>Reservation Details</b></h5>
                                <div class="input-field col s12">
                                    <input type="text" class="validate" id="crRSumEventVenue"
                                           name=""/>
                                    <label for="crRSumEventVenue"><b>Event Venue</b></label>
                                </div>
                                <div class="input-field col s6" style="margin-top: 15px;">
                                    <input type="text" id="crRSumFromDate" name=""/>
                                    <label for="crRSumFromDate"><b>From (Date)</b></label>
                                </div>
                                <div class="input-field col s6" style="margin-top: 15px;">
                                    <input type="text" id="crRSumToDate" name=""/>
                                    <label for="crRSumToDate" class="active"><b>To (Date)</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <label for="timepicker">Time</label>
                                    <input id="timepicker" class="timepicker" type="time">
                                </div>
                                <div class="input-field col s6" style="margin-top: 15px;">
                                    <input type="text" name="" id="crRSumToTime"/>
                                    <label for="crRSumToTime" class="active"><b>To (Time)</b></label>
                                </div>

                                <div class="input-field col s6">
                                    <input type="text" name="" id="crRSumHeadCount"/>
                                    <label for="crRSumHeadCount"><b>Head Count</b></label>
                                </div>
                            </div>
                            <div class="row">
                                <h5><b>Selected Items</b></h5>
                                <div class="col s12">
                                    <h6 class="grey-text text-darken-3"><b>Services</b></h6>
                                    <table>
                                        <thead>
                                        <tr>
                                            <th class="left-align grey-text text-darken-3">Service Name</th>
                                            <th class="right-align grey-text text-darken-3">Price</th>
                                            <th class="right-align grey-text text-darken-3">Quantity</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div class="col s12">
                                    <h6 class="grey-text text-darken-3"><b>Products</b></h6>
                                    <table>
                                        <thead>
                                        <tr>
                                            <th class="left-align grey-text text-darken-3">Product Name</th>
                                            <th class="right-align grey-text text-darken-3">Price</th>
                                            <th class="right-align grey-text text-darken-3">Quantity</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <h5><b>Other Charges</b></h5>
                                <div class="col s12">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th class="grey-text text-darken-3">Charge Name</th>
                                            <th class="grey-text text-darken-3">Price</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <h5 class="grey-text text-darken-3"><b>Discounts</b></h5>
                                <div class="col s12">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th class="grey-text text-darken-3">Discount Name</th>
                                            <th class="grey-text text-darken-3">Price</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th class="left-align"><b>Payment Type</b></th>
                                            <th class="right-align"><b>Total Price</b></th>
                                        </tr>
                                        </thead>
                                    </table>
                                    <p class="red-text">
                                        Note: First payment should be settled right after the reservation is created.
                                        Second payment will be collected on the day of the event after the program.
                                    </p>

                                    <p>
                                        By clicking create, you AGREE to our terms and agreements stated in the
                                        <a class="blue-text" href="">Reservation Contract</a>
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
                <button type="button" id="nextbtn"
                        class="actionreserve nextformreserve waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button type="submit" value="submit" id="createReservationSubmitForm"
                        class="actionreserve submitformreserve waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px;"
                        ng-click="vm.saveReservation(vm.details)">CREATE
                </button>
                <button type="button" id="backbtn"
                        class="actionreserve backformreserve waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>

            </div>
        </form>


    </div>
</div>
