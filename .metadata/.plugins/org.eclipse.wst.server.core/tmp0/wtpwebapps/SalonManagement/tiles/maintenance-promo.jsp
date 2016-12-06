<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Product"%>
<%@ page import="com.gss.model.Service"%>
<%@ page import="com.gss.model.Package"%>
<%@ page import="java.util.List" %>
<%@ page import="com.gss.model.ProductPackage"%>
<%@ page import="com.gss.model.ServicePackage"%>
<%@ page import="com.gss.model.PackagePackage"%>
<%@ page import="com.gss.model.Promo"%>

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Promo Maintenance</h3>
            <a class="crPromoBtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#crPromoModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <!--<a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"-->
               <!--href="#promoArchive" style="margin-top: 30px; margin-left: 15px;"><i-->
                    <!--class="material-icons">archive</i></a>-->
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="promoSearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                   required>
                            <label for="promoSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="promotbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0" width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Promo Name</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-left">Requirement</th>
                    <th class="dt-head-right">Price</th>
                    <th class="dt-head-right">Expiration</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Promo Name</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-left">Requirement</th>
                    <th class="dt-head-right">Price</th>
                    <th class="dt-head-right">Expiration</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </tfoot>

                <tbody>
                </tbody>
            </table>
        </div>
    </div>


    <!-- Modal Structure -->
    <div id="crPromoModal" class="modal modal-fixed-footer">
        <form id="createPromoForm" class="col s12 form" enctype="multipart/form-data">
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Promo<a id="btnCrPromoExit"
                                                                              class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
                    <div class="crpromoerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="steppromo well" style="margin-top: -10px !important;">
                        <div class="container">
                            <div class="wrapper">
                                <div class="row form-group">
                                    <div class="input-field col s2">
                                        <label><b>Availability</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s5">
                                        <p class="center">
                                            <input type="checkbox" class="filled-in" id="crPromoNonExpiry"
                                                   name="crNonExp" value="on">
                                            <label for="crPromoNonExpiry"><b>Non-Expiry</b></label>
                                        </p>
                                    </div>
                                    <div class="input-field col s5">
                                        <input name="crExp" type="date" class="datepicker-promo"
                                               id="crPromoExpiration" placeholder="Expiration" required/>
                                        <label for="crPromoExpiration" class="active"><b>Expiration</b></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input name="crPromoName" type="text" class="validate"
                                               id="crPromoName" placeholder="Promo Name" required>
                                        <label for="crPromoName" class="active"><b>Promo Name</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s12">
                                    <textarea name="crPromoDesc" id="crPromoDescription"
                                              style="margin-top: -10px !important;"
                                              class="materialize-textarea" minlength="5"
                                              placeholder="Description"></textarea>
                                        <label for="crPromoDescription" class="active"><b>Description</b></label>
                                    </div>
                                    <div class="input-field col s8">
                                        <select name="crPromoRequirement" id="crPromoRequirement" multiple="multiple">
                                            <option value="default" disabled selected>Choose...</option>
                                        </select>
                                        <label for="crPromoRequirement">
                                            <b>Requirement</b>
                                        </label>
                                    </div>
                                    <div class="input-field col s4">
                                        <button data-target="crAddPromoNewReq"
                                                class="waves-effect waves-light btn-flat modal-option purple darken-3 white-text">
                                            <i class="material-icons">add</i></button>
                                    </div>
                                    <div class="input-field col s8">
                                        <select name="crPromoType" id="crPromoType" multiple="multiple" required>
                                            <option value="default" disabled selected>Choose...</option>
                                            <option value="walkin">WALK-IN</option>
                                            <option value="homeservice">HOME SERVICE</option>
                                            <option value="event">EVENT</option>
                                        </select>
                                        <label for="crPromoType">
                                            <b>Type</b><i class="material-icons red-text tiny">error_outline</i>
                                        </label>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="steppromo well">
                        <div class="row form-group">
                            <div class="col s4">
                                <select name="" id="crPromoFilter">
                                    <option value="product" selected>Products</option>
                                    <option value="service">Services</option>
                                    <option value="package">Packages</option>
                                </select>
                                <label for="crPromoFilter"><b>Select</b></label>
                            </div>
                            <nav class="right white z-depth-1" style="width: 300px; margin-right: 20px;">
                                <div class="nav-wrapper col s12">
                                    <form>
                                        <div class="input-field">
                                            <input id="crPromoSearch" placeholder="Search"
                                                   class="grey-text text-darken-4"
                                                   type="search" style="border: none !important;">
                                            <label for="crPromoSearch"><i
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
                                                <table id="crpromotblprod"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="crpromotblserv"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="crpromotblpackage"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
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
                            <div class="col s9 z-depth-barts white prodservlist" id="prodservContainer">
                                <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                <div class="col s12" id="promoList"
                                     style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                            </div>
                            <div class="col s3">
                                <div class="input-field col s12">
                                    <input type="text" class="right-align grey-text text-darken-3" disabled name=""
                                           id="crPromoTotal" placeholder="Price"/>
                                    <label for="crPromoTotal"
                                           class="active grey-text text-darken-3"><b>Total</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" class="right-align prodPrice" name="crPromoPrice"
                                           id="crPromoPrice"
                                           placeholder="Promo Price" required/>
                                    <label for="crPromoPrice" class="active"><b>Promo Price</b>
                                        <i class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: -15px !important;">
                                    <input name="crFree" type="checkbox" class="filled-in"
                                           id="crPromoFree" value="on"/>
                                    <label for="crPromoFree"><b>Free</b></label>
                                </div>
                            </div>
                            <!--<div class="col s12 z-depth-barts white prodservlist" style="margin-top: 5px;"-->
                            <!--id="servContainer">-->
                            <!--<h6 class="center" style="padding-top: -2px !important;"><b>Service List</b></h6>-->
                            <!--<div class="col s6" id="servList"-->
                            <!--style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>-->
                            <!--</div>-->

                            <!--<input type="submit" value="create"/>-->

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="red-text btn-flat transparent left" disabled
                        style="margin:0px !important; padding:0px !important;"><i
                        class="material-icons">error_outline</i>&nbspRequired field
                </button>
                <button type="button" id="crPromoBackBtn"
                        class="actionpromo backformpromo waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>
                <button type="button" id="nextbtn"
                        class="actionpromo nextformpromo waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button id="createPromoSubmitForm" onclick="createPromo();"
                        class="actionpromo submitformpromo waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px; opacity: 0.3" disabled>CREATE
                </button>

            </div>
        </form>
    </div>

    <div id="crAddPromoNewReq" class="modal" style="margin-top: 30px; width: 500px !important;">
        <form id="createPromoRequirementForm">
            <div class="modal-content">
                <h4 class="center">Create Requirement</h4>
                <div class="row">
                    <div class="errorCreatePromoRequirement center input-field col s12 card red white-text z-depth-barts">
                    </div>
                    <div id="addCreatePromoOption" class="center input-field col s12 card red white-text z-depth-barts">
                    </div>
                    <div class="col s12">
                        <div class="input-field col s8 offset-s2">
                            <select id="addCrPromoRequirementSelect" class="browser-default" size="10"
                                    style="height: 150px !important;">
                            </select>
                        </div>
                        <div class="input-field col s6 offset-s2" style="margin-top: 20px;">
                            <input type="text"
                                   placeholder="Ex: Manager" id="addCrPromoRequirementName" name="addCrPromoRequirementName"
                                   required>
                            <label for="addCrPromoRequirementName" class="active"><b>Requirement</b></label>
                        </div>
                        <div class="input-field col s2">
                            <a id="crDeletePromoRequirement" onclick="crRemoveNewPromoRequirement();"
                               class="modal-action waves-effect waves-light red darken-3 btn-flat white-text">
                                <i class="material-icons">delete</i>
                            </a>
                        </div>
                        <div class="input-field center col s12" id="requirementPromoExistingDiv">
                            <span class="red-text">Requirement is already existing!</span>
                        </div>
                        <div class="input-field center col s12" id="requirementPromoFailedDiv">
                            <span class="red-text">Failed!</span>
                        </div>
                        <div class="input-field col s8 offset-s2 center">
                            <a id="createAddNewPromoRequirementBtn" onclick="crAddNewPromoRequirement();"
                               class="modal-action waves-effect waves-light purple darken-3 btn-flat white-text">
                                SAVE
                            </a>
                            <button type="reset" value="Reset" id="crAddOptPromoCancel"
                                    class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div id="upPromoModal" class="modal modal-fixed-footer">
        <form id="updatePromoForm" name="updatePromoForm" class="col s12">
            <div class="modal-content">
                <div class="wrapper">

                    <div class="row">
                    <h4 class="center grey-text text-darken-1">
                        Update Promo
                        <a id="btnUpPromoExit" class="modal-action modal-close">
                            <i class="small material-icons right grey-text text-darken-4">close</i>
                        </a>
                    </h4>
                    <div style="margin-bottom: 40px !important;"
                         class="uppromoerrorcontainer card red center input-field col s12 white-text z-depth-barts">
                         
                    </div>
                        <div class="col s12">
                            <ul class="tabs tab-demo-active" style="width: 100%; background-color: #fafafa; margin-top: -20px !important;">
                                <li class="tab col s6">
                                <a class="purple-text text-darken-2 waves-effect waves-light"
                                        href="#promoUpdateA"><b>Details</b>
                                        </a>
                                        </li>
                                <li class="tab col s6">
                                <a class="purple-text text-darken-2 waves-effect waves-light"
                                        href="#promoUpdateB">
                                        <b>Included Items</b></a>
                                        </li>
                            </ul>
                        </div>
                        <div id="promoUpdateA" class="ftab col s12" style="margin-top: 20px !important;">
                            <div class="container">
                                <div class="input-field col s4">
                                    <label><b>Availability</b>
                                        <i class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s4">
                                    <p class="center">
                                        <input type="checkbox" class="filled-in" id="upPromoNonExpiry"
                                               name="upNonExp" value="on">
                                        <label for="upPromoNonExpiry"><b>Non-Expiry</b></label>
                                    </p>
                                </div>
                                <div class="input-field col s4">
                                    <input name="upExp" type="date" class="datepicker-promoUpdate"
                                           id="upPromoExpiration" placeholder="Expiration" required/>
                                    <label for="upPromoExpiration" class="active"><b>Expiration</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <input name="intPromoID" type="hidden" id="upPromoID">
                                    <input name="upPromoName" type="text" class="validate"
                                           id="upPromoName" placeholder="Promo Name" required>
                                    <label for="upPromoName" class="active"><b>Promo Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <textarea name="upPromoDesc" id="upPromoDescription"
                                              style="margin-top: -10px !important;"
                                              class="materialize-textarea" minlength="5"
                                              placeholder="Description"></textarea>
                                    <label for="upPromoDescription" class="active"><b>Description</b></label>
                                </div>
                                <div class="input-field col s8">
                                    <select name="upPromoRequirement" id="upPromoRequirement" multiple="multiple">
                                        <option value="default" disabled selected>Choose...</option>
                                    </select>
                                    <label for="upPromoRequirement">
                                        <b>Requirement</b>
                                    </label>
                                </div>
                                <div class="input-field col s4">
                                    <button data-target="crAddPromoNewReq"
                                            class="waves-effect waves-light btn-flat modal-option purple darken-3 white-text">
                                        <i class="material-icons">add</i></button>
                                </div>
                                <div class="input-field col s8">
                                    <select name="upPromoType" id="upPromoType" multiple="multiple" required>
                                        <option value="default" disabled selected>Choose...</option>
                                        <option value="walkin">WALK-IN</option>
                                        <option value="homeservice">HOME SERVICE</option>
                                        <option value="event">EVENT</option>
                                    </select>
                                    <label for="upPromoType">
                                        <b>Type</b><i class="material-icons red-text tiny">error_outline</i>
                                    </label>
                                </div>

                            </div>
                        </div>
                        <div id="promoUpdateB" class="ftab col s12" style="margin-top: 20px !important;">
                            <div class="col s4">
                                <select name="" id="upPromoFilter">
                                    <option value="product" selected>Products</option>
                                    <option value="service">Services</option>
                                    <option value="package">Packages</option>
                                </select>
                                <label for="upPromoFilter"><b>Select</b></label>
                            </div>
                            <nav class="right white z-depth-1" style="width: 300px; margin-right: 20px;">
                                <div class="nav-wrapper col s12">
                                    <form>
                                        <div class="input-field">
                                            <input id="upPromoSearch" placeholder="Search"
                                                   class="grey-text text-darken-4"
                                                   type="search" style="border: none !important;">
                                            <label for="upPromoSearch"><i
                                                    class="material-icons grey-text text-darken-4">search</i></label>
                                        </div>
                                    </form>
                                </div>
                            </nav>
                            <div class="col s12">
                                <ul class="collapsible" data-collapsible="accordion">
                                    <li>
                                        <div class="collapsible-header"><i
                                                class="material-icons">view_list</i>List
                                        </div>
                                        <div class="collapsible-body"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                            <div class="tablewrapper">
                                                <table id="upPromoTblProd"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="upPromoTblServ"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="upPromoTblPackage"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-left">Category</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th align="dt-head-right" class="no-sort">Quantity</th>
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
                            <div class="col s9 z-depth-barts white prodservlist" id="updateProdservContainer">
                                <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                <div class="col s12" id="updatePromoList"
                                     style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                            </div>
                            <div class="col s3">
                                <div class="input-field col s12">
                                    <input type="text" class="right-align grey-text text-darken-3" disabled name=""
                                           id="upPromoTotal" placeholder="Price"/>
                                    <label for="upPromoTotal"
                                           class="active grey-text text-darken-3"><b>Total</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" class="right-align prodPrice" name="upPromoPrice"
                                           id="upPromoPrice"
                                           placeholder="Promo Price"/>
                                    <label for="upPromoPrice" class="active"><b>Promo Price</b>
                                        <i class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: -15px !important;">
                                    <input name="upFree" type="checkbox" class="filled-in"
                                           id="upPromoFree" value="on"/>
                                    <label for="upPromoFree"><b>Free</b></label>
                                </div>
                                <div class="input-field col s12" id="errorPriceFree">
                                    <span class="red-text">Invalid Price</span>
                                </div>
                            </div>
                            <!--<div class="col s12 z-depth-barts white prodservlist" style="margin-top: 5px;"-->
                            <!--id="servContainer">-->
                            <!--<h6 class="center" style="padding-top: -2px !important;"><b>Service List</b></h6>-->
                            <!--<div class="col s6" id="servList"-->
                            <!--style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>-->
                            <!--</div>-->

                            <!--<input type="submit" value="create"/>-->

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
                <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                        onclick="updatePromo()"
                        id="updatePromoSubmitBtn">SAVE
                </button>

            </div>
        </form>
    </div>
</div>