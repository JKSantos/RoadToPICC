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
               href="#createDiscountModal" style="margin-top: 30px; margin-left: 15px;"
               id="addDiscountBtn">
                <i class="material-icons">add</i>
            </a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#discountModal" style="margin-top: 30px; margin-left: 15px;">
                <i class="material-icons">archive</i>
            </a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="discountSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search">
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
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-left no-sort">Guidelines</th>
                    <th class="dt-head-left">Applicability</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-right">Amount</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-left no-sort">Guidelines</th>
                    <th class="dt-head-left">Applicability</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-right">Amount</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="createDiscountModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createDiscountForm">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Discount
                        <a id="btnCrDiscountExit" class="modal-action modal-close">
                            <i class="small material-icons right grey-text text-darken-4">close</i>
                        </a>
                    </h4>
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
                                <input type="text" class="validate" id="crDiscountName" required
                                       name="strDiscountName" placeholder="Discount Name">
                                <label for="crDiscountName" class="active">
                                    <b>Discount Name</b>
                                    <i class="material-icons red-text tiny">error_outline</i>
                                </label>
                            </div>
                            <div class="input-field col s12" style="margin-top: 15px;">
                            <textarea type="text" class="materialize-textarea" id="crDiscountDetails"
                                      name="strDiscountDetails" placeholder="Discount"
                                      style="margin-top: -10px !important;"></textarea>
                                <label for="crDiscountDetails" class="active">
                                    <b>Description</b>
                                </label>
                            </div>
                            <div class="input-field col s6">
                                <label style="margin-top: -25px !important;"><b>Applicability</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                                <input name="strApplicability" class="radiobtn" type="radio" value="TOTAL SALES"
                                       id="crDiscountRadio1"/>
                                <label for="crDiscountRadio1">Total Sales</label>
                                <input name="strApplicability" class="radiobtn" type="radio"
                                       value="SELECTED ITEMS" id="crDiscountRadio2"/>
                                <label for="crDiscountRadio2">Selected Items</label>
                            </div>
                            <div class="input-field col s4">
                                <select name="crRequirement" id="crRequirement" multiple="multiple">
                                    <option value="default" disabled selected>Choose...</option>
                                </select>
                                <label for="crRequirement">
                                    <b>Requirement</b>
                                </label>
                            </div>
                            <div class="input-field col s2">
                                <button data-target="crAddNewReq"
                                        class="waves-effect waves-light btn-flat modal-option purple darken-3 white-text">
                                    <i class="material-icons">add</i></button>
                            </div>
                            <div class="input-field col s3 offset-s6" id="crDiscountAmtTypeDiv">
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
                                       placeholder="Discount Amount" required>
                                <label for="crDiscountAmountFixed"><b>Value</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s3" id="percent">
                                <input type="text" class="validate right-align"
                                       id="crDiscountAmountPercent" name="strDiscountPricePercent"
                                       placeholder="Discount Amount" required>
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
                                        <div class="collapsible-header" id="listheadcollapsible"><i
                                                class="material-icons">view_list</i>List
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
                                                        <th class="dt-head-left">Price</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-left">Price</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>

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
                                                        <th class="dt-head-left">Price</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-left">Price</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
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
                                                        <th class="dt-head-left">Price</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-left">Price</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
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
                                                        <th class="dt-head-left">Price</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-left">Price</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="col s12 z-depth-barts white prodservlist" id="prodservContainer">
                                <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                <div class="col s12" id="discountList"
                                     style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="red-text btn-flat transparent left" disabled
                        style="margin:0px !important; padding:0px !important;">
                    <i class="material-icons">error_outline</i>&nbspRequired field
                </button>
                <button id="createDiscountSubmitForm2" onclick="createDiscount()"
                        class="waves-effect waves-light white-text btn-flat purple">CREATE
                </button>
                <button id="createDiscountSubmitForm" onclick="createDiscount()"
                        class="actiondiscount submitformdiscount waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px; opacity: 0.3;" disabled>CREATE
                </button>
                <button type="button" id="backbtn"
                        class="actiondiscount backformdiscount waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>
                <button type="button" id="discnextbtn"
                        class="actiondiscount nextformdiscount waves-effect waves-light white-text btn-flat purple">NEXT
                </button>

            </div>
        </form>
    </div>

    <div id="crAddNewReq" class="modal" style="margin-top: 30px; width: 500px !important;">
        <form id="createRequirementForm">
            <div class="modal-content">
                <h4 class="center">Create Requirement</h4>
                <div class="row">
                    <div class="errorCreateRequirement center input-field col s12 card red white-text z-depth-barts">
                    </div>
                    <div id="addCreateoption" class="center input-field col s12 card red white-text z-depth-barts">
                    </div>
                    <div class="col s12">
                        <div class="input-field col s8 offset-s2">
                            <select id="addCrRequirementSelect" class="browser-default" size="10"
                                    style="height: 150px !important;">
                            </select>
                        </div>
                        <div class="input-field col s6 offset-s2" style="margin-top: 20px;">
                            <input type="text"
                                   placeholder="Ex: Manager" id="addCrRequirementName" name="addCrRequirementName"
                                   required>
                            <label for="addCrRequirementName" class="active"><b>Requirement</b></label>
                        </div>
                        <div class="input-field col s2">
                            <a id="crDeletePosition" onclick="crRemoveNewRequirement();"
                               class="modal-action waves-effect waves-light red darken-3 btn-flat white-text">
                                <i class="material-icons">delete</i>
                            </a>
                        </div>
                        <div class="input-field center col s12" id="requirementExistingDiv">
                            <span class="red-text">Requirement is already existing!</span>
                        </div>
                        <div class="input-field col s8 offset-s2 center">
                            <a id="createAddNewRequirement" onclick="crAddNewRequirement();"
                               style="opacity: 0.3" disabled
                               class="modal-action waves-effect waves-light purple darken-3 btn-flat white-text">
                                SAVE
                            </a>
                            <button type="reset" value="Reset" id="crAddOptCancel"
                                    class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>


    <div id="updateDiscountModal" class="modal modal-fixed-footer">
        <form class="col s12" id="updateDiscountForm">
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">
                        Update Discount
                        <a id="btnUpdateDiscountExit" type="reset" value="Reset"
                           class="btnUpdateDiscountExit modal-action modal-close">
                            <i class="small material-icons right grey-text text-darken-4">close</i>
                        </a>
                    </h4>
                    <div class="updatediscounterror center input-field col s12 card red white-text z-depth-barts"
                         style="margin-top: -10px !important;">

                    </div>
                    <div class="row">
                        <div class="col s12">
                            <ul class="tabs tab-demo-active" style="width: 100%; background-color: #fafafa;">
                                <li class="tab col s6"><a
                                        class="purple-text text-darken-2 waves-effect waves-light"
                                        href="#updateA"><b>Details</b></a></li>
                                <li class="tab col s6">
                                    <a class="purple-text text-darken-2 waves-effect waves-light"
                                       href="#updateB" id="updateBDiscount">
                                        <b>Included Items</b>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div id="updateA" class="ftab col s12" style="margin-top: 20px !important;">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input type="hidden" id="upDiscountID">
                                    <input type="text" class="validate" id="upDiscountName" required
                                           name="strDiscountName" placeholder="Discount Name">
                                    <label for="upDiscountName" class="active">
                                        <b>Discount Name</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 15px;">
                            <textarea type="text" class="materialize-textarea" id="upDiscountDetails"
                                      name="strDiscountDetails" placeholder="Discount"
                                      style="margin-top: -10px !important;"></textarea>
                                    <label for="upDiscountDetails" class="active">
                                        <b>Description</b>
                                    </label>
                                </div>
                                <div class="input-field col s6">
                                    <label style="margin-top: -25px !important;"><b>Applicability</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                    <input name="strUpdateApplicability" class="radiobtn" type="radio"
                                           value="TOTAL SALES"
                                           id="upDiscountRadio1"/>
                                    <label for="upDiscountRadio1">Total Sales</label>
                                    <input name="strUpdateApplicability" class="radiobtn" type="radio"
                                           value="SELECTED ITEM" id="upDiscountRadio2"/>
                                    <label for="upDiscountRadio2">Selected Item</label>
                                </div>
                                <div class="input-field col s4">
                                    <select name="upRequirement" id="upRequirement" multiple="multiple">
                                        <option value="default" disabled selected>Choose...</option>
                                    </select>
                                    <label for="upRequirement">
                                        <b>Requirement</b>
                                    </label>
                                </div>
                                <div class="input-field col s2">
                                    <button data-target="crAddNewReq"
                                            class="waves-effect waves-light btn-flat modal-option purple darken-3 white-text">
                                        <i class="material-icons">add</i></button>
                                </div>
                                <div class="input-field col s3 offset-s6" id="upDiscountAmtTypeDiv">
                                    <select id="upDiscountAmtType" name="strDiscountType">
                                        <option id="percentage" value="1">Percentage</option>
                                        <option id="fixedamount" value="2">Fixed Amount</option>
                                    </select>
                                    <label for="upDiscountAmtType"><b>Discount Type</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s3" id="upFixed">
                                    <input type="text" class="validate right-align prodPrice"
                                           id="upDiscountAmountFixed" name="strDiscountPriceFixed"
                                           placeholder="Discount Amount" required>
                                    <label for="upDiscountAmountFixed"><b>Value</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                                <div class="input-field col s3" id="upPercent">
                                    <input type="text" class="validate right-align"
                                           id="upDiscountAmountPercent" name="strDiscountPricePercent"
                                           placeholder="Discount Amount" required>
                                    <label for="upDiscountAmountPercent"><b>Value</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div id="updateB" class="ftab col s12" style="margin-top: 15px !important;">
                            <div class="row form-group">
                                <div class="col s4">
                                    <select name="" id="discountUpdateFilter">
                                        <option value="product" selected>Product</option>
                                        <option value="service">Service</option>
                                        <option value="package">Package</option>
                                        <option value="promo">Promo</option>
                                    </select>
                                    <label for="discountUpdateFilter"><b>Select</b></label>
                                </div>
                                <nav class="right white z-depth-1" style="width: 300px; margin-right: 20px;">
                                    <div class="nav-wrapper col s12">
                                        <form>
                                            <div class="input-field">
                                                <input id="upDiscountSearch" placeholder="Search"
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
                                            <div class="collapsible-header" id="uplistheadcollapsible"><i
                                                    class="material-icons">view_list</i>List
                                            </div>
                                            <div class="collapsible-body" id="uplistcollapsible"
                                                 style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                                <div class="tablewrapper">
                                                    <table id="updiscounttblProduct"
                                                           class="cell-border row-border display centered responsive-table highlight"
                                                           cellspacing="0" width="100%"
                                                           style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                           rowspan="5">
                                                        <thead>
                                                        <tr>
                                                            <th class="dt-head-center no-sort">Select</th>
                                                            <th class="dt-head-left">Name</th>
                                                            <th class="dt-head-left">Category</th>
                                                            <th class="dt-head-left">Price</th>
                                                        </tr>
                                                        </thead>
                                                        <tfoot style="border: 1px solid #bdbdbd;">
                                                        <tr>
                                                            <th class="dt-head-center no-sort">Select</th>
                                                            <th class="dt-head-left">Name</th>
                                                            <th class="dt-head-left">Category</th>
                                                            <th class="dt-head-left">Price</th>
                                                        </tr>
                                                        </tfoot>

                                                        <tbody>

                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="tablewrapper">
                                                    <table id="updiscounttblService"
                                                           class="cell-border row-border display centered responsive-table highlight"
                                                           cellspacing="0" width="100%"
                                                           style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                           rowspan="5">
                                                        <thead>
                                                        <tr>
                                                            <th class="dt-head-center no-sort">Select</th>
                                                            <th class="dt-head-left">Name</th>
                                                            <th class="dt-head-left">Category</th>
                                                            <th class="dt-head-left">Price</th>
                                                        </tr>
                                                        </thead>
                                                        <tfoot style="border: 1px solid #bdbdbd;">
                                                        <tr>
                                                            <th class="dt-head-center no-sort">Select</th>
                                                            <th class="dt-head-left">Name</th>
                                                            <th class="dt-head-left">Category</th>
                                                            <th class="dt-head-left">Price</th>
                                                        </tr>
                                                        </tfoot>

                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="tablewrapper">
                                                    <table id="updiscounttblPackage"
                                                           class="cell-border row-border display centered responsive-table highlight"
                                                           cellspacing="0" width="100%"
                                                           style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                           rowspan="5">
                                                        <thead>
                                                        <tr>
                                                            <th class="dt-head-center no-sort">Select</th>
                                                            <th class="dt-head-left">Name</th>
                                                            <th class="dt-head-left">Category</th>
                                                            <th class="dt-head-left">Price</th>
                                                        </tr>
                                                        </thead>
                                                        <tfoot style="border: 1px solid #bdbdbd;">
                                                        <tr>
                                                            <th class="dt-head-center no-sort">Select</th>
                                                            <th class="dt-head-left">Name</th>
                                                            <th class="dt-head-left">Category</th>
                                                            <th class="dt-head-left">Price</th>
                                                        </tr>
                                                        </tfoot>

                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="tablewrapper">
                                                    <table id="updiscounttblPromo"
                                                           class="cell-border row-border display centered responsive-table highlight"
                                                           cellspacing="0" width="100%"
                                                           style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                           rowspan="5">
                                                        <thead>
                                                        <tr>
                                                            <th class="dt-head-center no-sort">Select</th>
                                                            <th class="dt-head-left">Name</th>
                                                            <th class="dt-head-left">Category</th>
                                                            <th class="dt-head-left">Price</th>
                                                        </tr>
                                                        </thead>
                                                        <tfoot style="border: 1px solid #bdbdbd;">
                                                        <tr>
                                                            <th class="dt-head-center no-sort">Select</th>
                                                            <th class="dt-head-left">Name</th>
                                                            <th class="dt-head-left">Category</th>
                                                            <th class="dt-head-left">Price</th>
                                                        </tr>
                                                        </tfoot>

                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col s12 z-depth-barts white prodservlist" id="discUpContainer">
                                    <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                    <div class="col s12" id="discountUpdateList"
                                         style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
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
                <a href="#!"
                   class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</a>
                <button class="waves-effect waves-light purple darken-3 white-text btn-flat"
                        onclick="updateDiscount()"
                        id="updateDiscountSubmitBtn">SAVE
                </button>
            </div>
        </form>
    </div>

    <!--<div id="upAddNewReq" class="modal" style="margin-top: 30px; width: 500px !important;">-->
        <!--<form id="updateRequirementForm">-->
            <!--<div class="modal-content">-->
                <!--<h4 class="center">Create Requirement</h4>-->
                <!--<div class="row">-->
                    <!--<div class="errorUpdateRequirement center input-field col s12 card red white-text z-depth-barts">-->
                    <!--</div>-->
                    <!--<div id="addUpdateoption" class="center input-field col s12 card red white-text z-depth-barts">-->
                    <!--</div>-->
                    <!--<div class="col s12">-->
                        <!--<div class="input-field col s8 offset-s2">-->
                            <!--<select id="addUpRequirementSelect" class="browser-default" size="10"-->
                                    <!--style="height: 150px !important;">-->
                            <!--</select>-->
                        <!--</div>-->
                        <!--<div class="input-field col s6 offset-s2" style="margin-top: 20px;">-->
                            <!--<input type="text"-->
                                   <!--placeholder="Ex: Manager" id="addUpRequirementName" name="addCrRequirementName"-->
                                   <!--required>-->
                            <!--<label for="addCrRequirementName" class="active"><b>Requirement</b></label>-->
                        <!--</div>-->
                        <!--<div class="input-field col s2">-->
                            <!--<a id="upDeletePosition" onclick="crRemoveNewRequirement();"-->
                               <!--class="modal-action waves-effect waves-light red darken-3 btn-flat white-text">-->
                                <!--<i class="material-icons">delete</i>-->
                            <!--</a>-->
                        <!--</div>-->
                        <!--<div class="input-field center col s12" id="UpRequirementExistingDiv">-->
                            <!--<span class="red-text">Requirement is already existing!</span>-->
                        <!--</div>-->
                        <!--<div class="input-field col s8 offset-s2 center">-->
                            <!--<a id="updateAddNewRequirement" onclick="crAddNewRequirement();"-->
                               <!--style="opacity: 0.3" disabled-->
                               <!--class="modal-action waves-effect waves-light purple darken-3 btn-flat white-text">-->
                                <!--SAVE-->
                            <!--</a>-->
                            <!--<button type="reset" value="Reset" id="upAddOptCancel"-->
                                    <!--class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL-->
                            <!--</button>-->
                        <!--</div>-->
                    <!--</div>-->
                <!--</div>-->
            <!--</div>-->
        <!--</form>-->
    <!--</div>-->


</div>
