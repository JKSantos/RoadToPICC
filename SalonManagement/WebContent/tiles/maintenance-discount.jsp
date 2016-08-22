<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Discount" %>

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Discount Maintenance</h3>
            <a class="crDiscBtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createDiscountModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#discountModal" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="discountSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search" required>
                            <label for="discountSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="discounttbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Discount Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-left no-sort">Guidelines</th>
                    <th class="dt-head-left">Applicability</th>
                    <th class="dt-head-right">Price</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Discount Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-left no-sort">Guidelines</th>
                    <th class="dt-head-left">Applicability</th>
                    <th class="dt-head-right">Price</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${discountList}" var="discount">
                
                	<c:set var="price" scope="session"
                           value="${(discount.dblDiscountAmount * 9) + discount.dblDiscountAmount}"></c:set>
                    <%
                    Discount discount1 = (Discount)pageContext.getAttribute("discount");
                    String id = String.valueOf(discount1.getIntDiscountID());
                    String percet = null;
                    String fixed = null;

                    if(discount1.getIntDiscountType() == 1){
                    percet = "%";
                    fixed = "";
                    }
                    else{
                    fixed = "Php ";
                    percet = "";
                    }
                    %>
                    <tr>
                        <td style="padding-left: 10px; margin:0;" class="dt-body-left">${discount.strDiscountName}</td>
                        <td style="padding-left: 10px; margin:0;" class="dt-body-left">${discount.strDiscountDesc}
                        </td>
                        <td style="padding-left: 10px; margin:0;" class="dt-body-left">
                            ${discount.strDiscountGuidelines}
                        </td>
                        <td style="padding-left: 10px; margin:0;" class="dt-body-left">${discount.applicability}</td>
                        <td style="padding-right: 10px; margin:0;" class="dt-body-right servPrice">
                            ${price}<%=percet%>
                        </td>
                        <td class="dt-body-center" style="padding:0; margin:0;">
                            <a class="waves-effect waves-purple modal-trigger btn-flat transparent black-text empUpdatebtn"
                               href="#ecupdate${extracharge.intECID}"
                               style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <button class="discountdeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                    style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                    id="${discount.intDiscountID}"
                                    title="Deactivate"><i class="material-icons">delete</i></button>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="createDiscountModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createDiscountForm" method="post" action="createDiscount">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Discount<a id="btnCrExtraExit" type="reset"
                                                                                 value="Reset"
                                                                                 class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
                    <div class="discounterrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="stepdiscount well" style="margin-top: -5px;">
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="text" class="validate" id="crDiscountName"
                                       name="strDiscountName" placeholder="Discount Name">
                                <label for="crDiscountName" class="active"><b>Discount Name</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s12" style="margin-top: 15px;">
                            <textarea type="text" class="materialize-textarea" id="crDiscountDetails"
                                      name="strDiscountDetails" placeholder="Discount"
                                      style="margin-top: -10px !important;"></textarea>
                                <label for="crDiscountDetails" class="active"><b>Details</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6">
                                <label style="margin-top: -25px !important;"><b>Applicability</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                                <input name="strApplicability" class="radiobtn" type="radio" value ="ALL CUSTOMER" id="crDiscountRadio1"/>
                                <label for="crDiscountRadio1">All Customers</label>
                                <input name="strApplicability" class="radiobtn" type="radio" value ="DEPENDING ON THE GUIDELINES" id="crDiscountRadio2"/>
                                <label for="crDiscountRadio2">Depending on the guidelines</label>
                            </div>
                            <div class="input-field col s6" style="margin-top: 15px;">
                            <textarea type="text" class="materialize-textarea" id="crDiscountGuidelines"
                                      name="strDiscountGuidelines" placeholder="Discount"
                                      style="margin-top: -10px !important;"></textarea>
                                <label for="crDiscountGuidelines" class="active"><b>Guidelines</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s3 offset-s6">
                                <select id="crDiscountAmtType" name="strDiscountType">
                                    <option value="1" selected>Percentage</option>
                                    <option value="2">Fixed Amount</option>
                                </select>
                                <label for="crDiscountAmtType"><b>Discount Type</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s3" id="fixed">
                                <input type="text" class="validate right-align prodPrice"
                                       id="crDiscountAmountFixed" name="strDiscountPriceFixed"
                                       placeholder="Discount Amount">
                                <label for="crDiscountAmountFixed"><b>Value</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s3" id="percent">
                                <input type="text" class="validate right-align"
                                       id="crDiscountAmountPercent" name="strDiscountPricePercent"
                                       placeholder="Discount Amount">
                                <label for="crDiscountAmountPercent"><b>Value</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                        </div>
                    </div>
                    <div class="stepdiscount well">
                        <div class="row form-group">
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
                            <div class="col s12">
                                <ul class="collapsible" data-collapsible="accordion">
                                    <li>
                                        <div class="collapsible-header" id="listheadcollapsible"><i class="material-icons">view_list</i>List
                                        </div>
                                        <div class="collapsible-body" id="listcollapsible"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                            <div class="tablewrapper">
                                                <table id="crdiscounttblProduct"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>

                                                    <c:forEach items="${productList}" var="product">
                                                        <tr>
                                                            <td class="dt-body-left">
                                                                <input type="checkbox" name="checkedProducts"
                                                                       id="prodCheck${product.intProductID}" required
                                                                       class="packcheckbox x{product.intProductID} ignore"
                                                                       value="${product.intProductID}"><label
                                                                    for="prodCheck${product.intProductID}"></label>
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                                class="dt-body-left ">${product.strProductName}
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                                Product
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="crdiscounttblService"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    <c:forEach items="${serviceList}" var="service">
                                                        <tr>
                                                            <td class="dt-body-left">
                                                                <input type="checkbox" name="checkedServices"
                                                                       id="myCheckBox${service.intServiceID}" required
                                                                       class="packcheckbox x{service.intServiceID} ignore"
                                                                       value="${service.intServiceID}"><label
                                                                    for="myCheckBox${service.intServiceID}"></label>
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                                class="dt-body-left ">${service.strServiceName}
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                                Service
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="crdiscounttblPackage"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    <c:forEach items="${packageList}" var="pack">
                                                        <tr>
                                                            <td class="dt-body-left">
                                                                <input type="checkbox" name="checkedPackages"
                                                                       id="discountPackage${pack.intPackageID}"
                                                                       class="packcheckbox x{pack.intPackageID}"
                                                                       value="${pack.intPackageID}"><label
                                                                    for="discountPackage${pack.intPackageID}"></label>
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                                class="dt-body-left ">${pack.strPackageName}
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                                Package
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="crdiscounttblPromo"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    <c:forEach items="${promoList}" var="promo">
                                                        <tr>
                                                            <td class="dt-body-left">
                                                                <input type="checkbox" name="checkedPromos"
                                                                       id="discountPromo${promo.intPromoID}"
                                                                       class="packcheckbox x{promo.intPromoID}"
                                                                       value="${promo.intPromoID}"><label
                                                                    for="discountPromo${promo.intPromoID}"></label>
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                                class="dt-body-left ">${promo.strPromoName}
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                                Promo
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="col s9 z-depth-barts white prodservlist" id="prodservContainer">
                                <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                <div class="col s12" id="pslist" style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                            </div>
                            <div class="col s3">
                                <div class="input-field col s12">
                                    <p>Total: <span class="right-align"></span></p>
                                </div>
                                <div class="input-field col s12" style="margin-top: 20px !important;">
                                    <input type="text" class="right-align prodPrice" name="" id="crPackPrice" placeholder="Price"/>
                                    <label for="crPackPrice" class="active"><b>Price</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
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
                </div>
            </div>
            <div class="modal-footer">
                <button class="red-text btn-flat transparent left" disabled
                        style="margin:0px !important; padding:0px !important;"><i
                        class="material-icons">error_outline</i>&nbspRequired field
                </button>
                <button type="button" id="backbtn"
                        class="actiondiscount backformdiscount waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>
                <button type="button" id="nextbtn"
                        class="actiondiscount nextformdiscount waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button type="submit" value="submit" id="createDiscountSubmitForm"
                        class="actiondiscount submitformdiscount waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px;">CREATE
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
