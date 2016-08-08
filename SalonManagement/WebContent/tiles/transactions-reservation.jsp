<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Discount" %>
<div class="wrapper" ng-controller= "reservationCtrl">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Reservation</h3>
            <a class="crReservation z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createReservationModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#reservationModal" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="reservationSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search" required>
                            <label for="reservationSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="reservationtbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Customer Name</th>
                    <th class="dt-head-left">Customer Type</th>
                    <th class="dt-head-left">Reservation Type</th>
                    <th class="dt-head-left no-sort">Venue</th>
                    <th class="dt-head-right">Reservation Date</th>
                    <th class="dt-head-left">Status</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tbody>
                	<tr>
	                	<td class="dt-head-left">HAN AINAN ONGSIP</td>
	                    <td class="dt-head-left">INDIVIDUAL</td>
	                    <td class="dt-head-left">EVENT</td>
	                    <td class="dt-head-left no-sort">TANGHALANG PASIGUENO</td>
	                    <td class="dt-head-right">2016-08-30</td>
	                    <td class="dt-head-left">REQUEST</td>
	                    <td align="dt-head-center" class="no-sort"><center><button class='waves-effect waves-light modal-trigger btn-flat transparent black-text'
	                                title='Accept' onclick='acceptOrder(this.value)' id='acc' style='padding: 0px;'>
	                                <i class='material-icons light-green-text text-darken-3'>check_circle</i></button><button class='prodsalesdeacbtn waves-effect waves-light btn-flat transparent
	                                red-text text-accent-4' title='Decline' onclick='declineOrder(this.value)'>
	                                <i class='material-icons'>cancel</i></button></center></td>
	                </tr>
	                <tr>
	                	<td class="dt-head-left">JEFFREY SANTOS</td>
	                    <td class="dt-head-left">INDIVIDUAL</td>
	                    <td class="dt-head-left">EVENT</td>
	                    <td class="dt-head-left no-sort">MOA ARENA</td>
	                    <td class="dt-head-right">2016-08-15</td>
	                    <td class="dt-head-left">PENDING</td>
	                    <td align="dt-head-center" class="no-sort"><center><button class='waves-effect waves-light modal-trigger btn-flat transparent black-text'
                        title='Update' onclick='openUpdate(this.value)' id='submitbtn' style='padding: 0px;'>
                        <i class='material-icons'>edit</i></button><button class='prodsalesdeacbtn waves-effect waves-light btn-flat transparent
                        red-text text-accent-4' title='Deactivate'  onclick='cancelOrder(this.value)'><i class='material-icons'>delete</i></button></center></td>
	                </tr>
                </tbody>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Customer Name</th>
                    <th class="dt-head-left">Customer Type</th>
                    <th class="dt-head-left">Reservation Type</th>
                    <th class="dt-head-left no-sort">Venue</th>
                    <th class="dt-head-right">Date of event</th>
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
    <div id="createReservationModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createReservationForm" method="post" action="">
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
                        <div class="row">
                            <h5><b>Customer Details</b></h5>
                            <div class="input-field col s12">
                                <input type="text" class="validate" id="crRCustName"
                                       name="" placeholder="Customer Name"/>
                                <label for="crRCustName" class="active"><b>Customer Name</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s12" style="margin-top: 15px;">
                                <input type="text" id="crRAddress" name="" placeholder="Address"/>
                                <label for="crRAddress" class="active"><b>Address</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" id="crRContact" name="" placeholder="contact"/>
                                <label for="crRContact" class="active"><b>Contact</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6" style="margin-top: 15px;">
                                <input type="email" name="" id="crREmail" placeholder="Email"/>
                                <label for="crREmail" class="active"><b>Email</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6">
                                <label style="margin-top: -25px;"><b>Customer Type</b></label>
                                <input type="radio" name="ctype" id="crRCustType1">
                                <label for="crRCustType1">Individual</label>
                                <input type="radio" name="ctype" id="crRCustType2">
                                <label for="crRCustType2">Company</label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" class="validate"
                                       id="crRCompanyName" name=""
                                       placeholder="Company Name">
                                <label for="crRCompanyName"><b>Company Name</b></label>
                            </div>
                        </div>
                    </div>
                    <div class="stepreservation well">
                        <div class="row">
                            <h5><b>Reservation Details</b></h5>
                            <div class="input-field col s12">
                                <input type="text" class="validate" id="crREventVenue"
                                       name="" placeholder="Event Venue"/>
                                <label for="crREventVenue" class="active"><b>Event Venue</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6" style="margin-top: 15px;">
                                <input type="text" id="crRFromDate" name="" placeholder="From (Date)"/>
                                <label for="crRFromDate" class="active"><b>From (Date)</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6" style="margin-top: 15px;">
                                <input type="text" id="crRToDate" name="" placeholder="To (Date)"/>
                                <label for="crRToDate" class="active"><b>To (Date)</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" id="crRFromTime" name="" placeholder="From (Time)"/>
                                <label for="crRFromTime" class="active"><b>From (Time)</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6" style="margin-top: 15px;">
                                <input type="text" name="" id="crRToTime" placeholder="crRToTime"/>
                                <label for="crRToTime" class="active"><b>To (Time)</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" name="" id="crRHeadCount" placeholder="Headcount"/>
                                <label for="crRHeadCount"><b>Head Count</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                        </div>
                    </div>
                    <div class="stepreservation well">
                        <div class="row">
                            <div class="col s4">
                                <select name="" id="discountFilter">
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
                                                   type="search" style="border: none !important;">
                                            <label for="crDiscountSearch"><i
                                                    class="material-icons grey-text text-darken-4">search</i></label>
                                        </div>
                                    </form>
                                </div>
                            </nav>

                            <div class="row">
                                <h4>Product</h4>
                                <div class="input-field col s6" id="crDivOrderLoc">
                                      <select class="browser-default" ng-options="product.strProductName for product in productList" 
                                      ng-model = "details.selectedProduct">
                                      </select>
                                </div>

                                <div class="input-field col s6">
                                    <input type="text" id="crOrderContact" ng-model = "details.productQuantity"/>
                                    <label for="crOrderContact"><b>Quantity</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>

                            <div class="row">
                                <h4>Service</h4>
                                <div class="input-field col s6" id="crDivOrderLoc">
                                      <select class="browser-default" ng-options="service.strServiceName for service in serviceList" 
                                      ng-model = "details.selectedService">
                                      </select>
                                </div>

                                <div class="input-field col s6">
                                    <input type="text" id="crOrderContact" ng-model = "details.serviceQuantity"/>
                                    <label for="crOrderContact"><b>Quantity</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>

                            <div class="row">
                                <h4>Package</h4>
                                <div class="input-field col s6" id="crDivOrderLoc">
                                      <select class="browser-default" ng-options="package.strPackageName for package in packageList" 
                                      ng-model = "details.selectedPackage">
                                      </select>
                                </div>

                                <div class="input-field col s6">
                                    <input type="text" id="crOrderContact" ng-model = "details.packageQuantity"/>
                                    <label for="crOrderContact"><b>Quantity</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>

                            <div class="row">
                                <h4>Promos</h4>
                                <div class="input-field col s6" id="crDivOrderLoc">
                                      <select class="browser-default" ng-options="promo.strPromoName for promo in promoList" 
                                      ng-model = "details.selectedPackage">
                                      </select>
                                </div>

                                <div class="input-field col s6">
                                    <input type="text" id="crOrderContact" ng-model = "details.promoQuantity"/>
                                    <label for="crOrderContact"><b>Quantity</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>

                            <div class="row">
                                <h4>Discount</h4>
                                <div class="input-field col s6" id="crDivOrderLoc">
                                      <select class="browser-default" ng-options="promo.strPromoName for promo in promoList" 
                                      ng-model = "details.selectedPackage">
                                      </select>
                                </div>

                                <div class="input-field col s6">
                                    <input type="text" id="crOrderContact" ng-model = "details.promoQuantity"/>
                                    <label for="crOrderContact"><b>Quantity</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>

                            

                            <div class="col s12 z-depth-barts white" id="">
                                <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                <div class="col s12" id="pslist" style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                            </div>
                            <div class="col s4" style="margin-top: 10px;">
                                <div class="input-field col s12">
                                    <select multiple id="crROtherCharge">
                                        <option value="" disabled selected>Choose your option</option>
                                        <c:forEach items="${ extraChargeList }" var="ec">
                                        	<option value="${ec.intECID}">${ec.strECName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="crROtherCharge"><b>Other Charges</b></label>
                                </div>
                            </div>
                            <div class="col s4" style="margin-top: 10px;">
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
                                        <input type="text" class="right-align prodPrice" name="" id="crPackPrice" placeholder="Price"/>
                                        <label for="crPackPrice" class="active"><b>Price</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                </div>
                            </div>
                            <!--<div class="col s12 z-depth-barts white prodservlist" style="margin-top: 5px;"-->
                            <!--id="servContainer">-->
                            <!--<h6 class="center" style="padding-top: -2px !important;"><b>Service List</b></h6>-->
                            <!--<div class="col s6" id="servList"-->
                            <!--style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>-->
                            <!--</div>-->
                        </div>
                    </div>
                    <div class="stepreservation well">
                        <div class="row">
                            <h3 class="center"><b>Summary</b></h3>
                            <h5><b>Customer Details</b></h5>
                            <div class="input-field col s12" id="crRSumCustName">
                                <input type="text" class="validate"/>
                                <label for="crRSumCustName" class=><b>Customer Name</b></label>
                            </div>
                            <div class="input-field col s12" style="margin-top: 15px;">
                                <input type="text" id="crRSumAddress" name="" placeholder=""/>
                                <label for="crRSumAddress"><b>Address</b></label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" id="crRSumContact" name="" />
                                <label for="crRSumContact"><b>Contact</b></label>
                            </div>
                            <div class="input-field col s6" style="margin-top: 15px;">
                                <input type="email" name="" id="crRSumEmail"/>
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
                            <div class="input-field col s6">
                                <input type="text" id="crRSumFromTime" name="" />
                                <label for="crRSumFromTime" class="active"><b>From (Time)</b></label>
                            </div>
                            <div class="input-field col s6" style="margin-top: 15px;">
                                <input type="text" name="" id="crRSumToTime" />
                                <label for="crRSumToTime" class="active"><b>To (Time)</b></label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" name="" id="crRSumHeadCount" />
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
                        style="margin-left:3px; margin-right:3px;">CREATE
                </button>
                <button type="button" id="backbtn"
                        class="actionreserve backformreserve waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>

            </div>
        </form>
    </div>

    <c:forEach items="${ecList}" var="extracharge">
        <div id="ecupdate${extracharge.intECID}" class="updateExtraModal modal modal-fixed-footer">
            <form class="col s12 updateExtraForm" id="updateExtraForm" method="post" action="updateExtraCharge">
                <div class="modal-content">
                    <!-- <div class="container"> -->
                    <div class="wrapper">
                        <h4 class="center grey-text text-darken-1">Update Charge<a id="btnUpExtraExit" type="reset"
                                                                                   value="Reset"
                                                                                   class="btnUpExtraExit modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                        <div class="upextraerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="hidden" value="${extracharge.intECID}"
                                       name="intECID">
                                <input type="text" class="validate" id="upECName" value="${extracharge.strECName}"
                                       name="strECName" required placeholder="Charge Name">
                                <label for="upECName" class="active"><b>Charge Name</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s12" style="margin-top: 25px;">
                                <input type="text" class="validate" id="upECDetails"
                                       name="strECDetails" required value="${extracharge.strECDetails}"
                                       placeholder="City">
                                <label for="upECDetails" class="active"><b>Details</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6 right">
                                <input id="upECPrice" name="price" value="${extracharge.dblECPrice}"
                                       class="upECPrice validate upProdItemPrice right-align" required
                                       placeholder="Price">
                                <label for="upECPrice" class="active"><b>Price</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="red-text btn-flat transparent left" disabled
                            style="margin:0px !important; padding:0px !important;"><i
                            class="material-icons">error_outline</i>&nbspRequired field
                    </button>
                    <button type="reset" value="Reset" id="upExtraCancel"
                            class="upExtraCancel modal-action modal-close waves-effect waves-purple transparent btn-flat">
                        CANCEL
                    </button>
                    <button class="upExtraSubmitBtn waves-effect waves-light purple darken-3 white-text btn-flat"
                            type="submit"
                            value="Submit">UPDATE
                    </button>
                </div>
            </form>
        </div>
    </c:forEach>


</div>
