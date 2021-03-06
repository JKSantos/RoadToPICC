<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Package" %>
<%@ page import="com.gss.model.ProductPackage" %>
<%@ page import="com.gss.model.ServicePackage" %>
<%@ page import="com.gss.model.Service" %>
<%@ page import="com.gss.model.Product" %>
<%@ page import="java.util.List" %>

<style>
    .dataTables_filter {
        display: none;
    }
</style>



<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Package Maintenance</h3>
            <a class="createPackbtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createPackageModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <!--<a class="z-depth-1 hoverable waves-effect waves-light modal-viewall btn purple darken-2 left white-text"-->
               <!--href="#archivePackageModal" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>-->
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="packageSearch" placeholder="Search" class="grey-text text-darken-4" type="search">
                            <label for="packageSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="packagetbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0" width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Package Name</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-left">Description</th>
                    <th class="dt-head-right">Price</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <!--<tfoot style="border: 1px solid #bdbdbd;">-->
                <!--<tr>-->
                    <!--<th class="dt-head-left">Package Name</th>-->
                    <!--<th class="dt-head-left">Type</th>-->
                    <!--<th class="dt-head-left">Description</th>-->
                    <!--<th class="dt-head-right">Price</th>-->
                    <!--<th align="center" class="no-sort"><center>Action</center></th>-->
                <!--</tr>-->
                <!--</tfoot>-->

                <tbody>
                </tbody>
            </table>
        </div>
    </div>


    <div id="archivePackageModal" class="modal modal-fixed-footer">
        <div class="modal-content">
            <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                <h4 class="grey-text text-darken-1 center">Archive</h4>
                <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                    <div class="nav-wrapper col s4">
                        <form>
                            <div class="input-field">
                                <input id="archivePackageSearch" placeholder="Search" class="grey-text text-darken-4" type="search">
                                <label for="archivePackageSearch"><i
                                        class="material-icons grey-text text-darken-4">search</i></label>
                            </div>
                        </form>
                    </div>
                </nav>

                <table id="archivePackageTbl"
                       class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                       cellspacing="0" width="100%"
                       style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                    <thead>
                    <tr>
                        <th class="dt-head-left">Package Name</th>
                        <th class="dt-head-left">Type</th>
                        <th class="dt-head-left">Description</th>
                        <th class="dt-head-right">Price</th>
                        <th align="center" class="no-sort">Action</th>
                    </tr>
                    </thead>
                    <!--<tfoot style="border: 1px solid #bdbdbd;">-->
                    <!--<tr>-->
                        <!--<th class="dt-head-left">Package Name</th>-->
                        <!--<th class="dt-head-left">Type</th>-->
                        <!--<th class="dt-head-left">Description</th>-->
                        <!--<th class="dt-head-right">Price</th>-->
                        <!--<th align="center" class="no-sort">Action</th>-->
                    <!--</tr>-->
                    <!--</tfoot>-->

                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="modal-footer">
            <a class="modal-action modal-close waves-effect waves-purple btn-flat transparent" >CLOSE</a>
        </div>
    </div>

    <!-- end view product and service modal -->

    <div id="viewModalPackage" class="modal">
        <div class="modal-content">
            <h4 class="center grey-text text-darken-1">View Package</h4>
            <div class="wrapper">
                <div class="row">
                    <div id="viewContainer">

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="createPackageModal" class="modal modal-fixed-footer">
        <form id="createPackageForm" class="col s12 form" method="post" action="createPackage"
              enctype="multipart/form-data">
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Package<a id="btnCrPackageExit" type="reset"
                                                                                value="Reset"
                                                                                class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
                    <div class="crpackageerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="steppack well" style="margin-top: 20px;">
                        <div class="wrapper">
                            <div class="row form-group">
                                <div class="container">
                                    <div class="input-field col s12">
                                        <input name="strPackageName" id="crPackageName" type="text"
                                               class="validate" placeholder="Package Name"/>
                                        <label for="crPackageName" class="active" id="label1"><b>Package Name</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 10px !important;">
                                    <textarea name="strPackageDesc" id="crPackageDesc" type="text"
                                              class="materialize-textarea" length="80"
                                              placeholder="Description"
                                              style="margin-top: -10px !important;"></textarea>
                                        <label for="crPackageDesc" class="active">
                                            <b>Description</b></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <select name="intPackageType" id="crPackageType" multiple required>
                                            <option disabled value="default">Choose your option</option>
                                            <option value="1">Walk-In</option>
                                            <option value="2">Home Service</option>
                                            <option value="3">Event</option>
                                        </select>
                                        <label for="crPackageType"><b>Type</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="steppack well">
                        <div class="row form-group">
                            <div class="col s4">
                                <select name="" id="packageFilter">
                                    <option value="product" selected>Product</option>
                                    <option value="service">Service</option>
                                </select>
                                <label for="packageFilter"><b>Select</b></label>
                            </div>
                            <nav class="right white z-depth-1" style="width: 300px; margin-right: 20px;">
                                <div class="nav-wrapper col s12">
                                    <form>
                                        <div class="input-field">
                                            <input id="crpackageSearch" placeholder="Search"
                                                   class="grey-text text-darken-4"
                                                   type="search" style="border: none !important;">
                                            <label for="crpackageSearch"><i
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
                                                <table id="crpacktblProd"
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
                                                    <!--<tfoot style="border: 1px solid #bdbdbd;">-->
                                                    <!--<tr>-->
                                                        <!--<th class="dt-head-center no-sort">Select</th>-->
                                                        <!--<th class="dt-head-left">Name</th>-->
                                                        <!--<th class="dt-head-left">Category</th>-->
                                                        <!--<th class="dt-head-right">Price</th>-->
                                                        <!--<th align="dt-head-right" class="no-sort">Quantity</th>-->
                                                    <!--</tr>-->
                                                    <!--</tfoot>-->

                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="crpacktblServ"
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
                                                    <!--<tfoot style="border: 1px solid #bdbdbd;">-->
                                                    <!--<tr>-->
                                                        <!--<th class="dt-head-center no-sort">Select</th>-->
                                                        <!--<th class="dt-head-left">Name</th>-->
                                                        <!--<th class="dt-head-left">Category</th>-->
                                                        <!--<th class="dt-head-right">Price</th>-->
                                                        <!--<th align="dt-head-right" class="no-sort">Quantity</th>-->
                                                    <!--</tr>-->
                                                    <!--</tfoot>-->

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
                                <div class="col s12" id="pslist"
                                     style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                            </div>
                            <div class="col s3">
                                <div class="input-field col s12">
                                    <input type="text" class="right-align grey-text text-darken-3" disabled name=""
                                           id="crPackTotal" placeholder="Price"/>
                                    <label for="crPackTotal" class="active grey-text text-darken-3"><b>Total</b></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 20px !important;">
                                    <input type="text" class="right-align prodPrice" name="createPackagePrice" id="crPackPrice"
                                           placeholder="Package Price" required/>
                                    <label for="crPackPrice"
                                           style="font-size: 13px !important;"
                                           class="active"><b>Package Price</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
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
                <button type="button" id="backbtn"
                        class="actionpack backformpack waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>
                <button type="button" id="nextbtn"
                        class="actionpack nextformpack waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button type="submit" value="submit" id="createSubmitForm" disabled onclick="createPackage()"
                        class="actionpack submitformpack waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px; opacity: 0.3 !important;">CREATE
                </button>

            </div>
        </form>
    </div>


    <div id="updatePackageModal" class="updatePackageModal modal modal-fixed-footer">
        <div class="loadingbarts" id="packageloadingupdate"> <!-- mystyle.css -->
            <span class="centerer"></span>
            <img class="salonlogo circle" src="./img/logo.png" height="200"/>
        </div>

        <form class="col s12" id="updatePackageForm">
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Update Package<a id="btnUpdatePackageExit"
                                                                                type="reset"
                                                                                value="Reset"
                                                                                class="btnUpdatePackageExit modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="updatepackageerror center input-field col s12 card red white-text z-depth-barts"
                         style="margin-top: -10px !important;">

                    </div>
                    <div class="row">
                        <div class="col s12">
                            <ul class="tabs tab-demo-active" style="width: 100%; background-color: #fafafa;">
                                <li class="tab col s6"><a
                                        class="purple-text text-darken-2 waves-effect waves-light"
                                        href="#updateA"><b>Details</b></a></li>
                                <li class="tab col s6"><a
                                        class="purple-text text-darken-2 waves-effect waves-light"
                                        href="#updateB"><b>Included Items</b></a></li>
                            </ul>
                        </div>
                        <div id="updateA" class="ftab col s12" style="margin-top: 20px !important;">
                            <div class="container">
                                <div class="input-field col s12">
                                    <input type="hidden" name="intUpdatePackageID" id="upPackageID">
                                    <input type="text" name="strUpdatePackageName" id="upPackageName"
                                           placeholder="Package Name" required/>
                                    <label for="upPackageName" class="active"><b>Package Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                            <textarea name="strUpdatePackageDesc" class="materialize-textarea"
                                                      placeholder="Description"
                                                      id="upPackageDesc" length="80"
                                                      style="margin-top: -10px !important;"></textarea>
                                    <label for="upPackageDesc" class="active"><b>Description</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <select name="intUpdatePackageType" id="upPackageType" multiple required>
                                        <option value="default" disabled>Choose...</option>
                                        <option value="3">Event</option>
                                        <option value="2">Home Service</option>
                                        <option value="1">Walk-In</option>
                                    </select>
                                    <label for="upPackageType"><b>Type</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>
                        </div>
                        <div id="updateB" class="ftab col s12" style="margin-top: 15px !important;">
                            <div class="col s4">
                                <select name="" id="upPackageFilter">
                                    <option value="product" selected>Product</option>
                                    <option value="service">Service</option>
                                </select>
                                <label for="upPackageFilter"><b>Select</b></label>
                            </div>
                            <nav class="right white z-depth-1" style="width: 300px; margin-right: 20px;">
                                <div class="nav-wrapper col s12">
                                    <form>
                                        <div class="input-field">
                                            <input id="uppackageSearch" placeholder="Search"
                                                   class="uppackageSearch grey-text text-darken-4"
                                                   type="search" style="border: none !important;">
                                            <label for="uppackageSearch"><i
                                                    class="material-icons grey-text text-darken-4">search</i></label>
                                        </div>
                                    </form>
                                </div>
                            </nav>
                            <div class="col s12">
                                <ul class="collapsible" data-collapsible="accordion">
                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">view_list</i>List
                                        </div>
                                        <div class="collapsible-body"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                            <div class="tablewrapper">
                                                <table id="uppackageProdtbl"
                                                       class="uppackagetbl cell-border row-border display centered responsive-table highlight"
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
                                                    <!--<tfoot style="border: 1px solid #bdbdbd;">-->
                                                    <!--<tr>-->
                                                        <!--<th class="dt-head-center no-sort">ID</th>-->
                                                        <!--<th class="dt-head-left">Name</th>-->
                                                        <!--<th class="dt-head-left">Category</th>-->
                                                        <!--<th class="dt-head-right">Price</th>-->
                                                        <!--<th align="dt-head-right" class="no-sort">Quantity</th>-->
                                                    <!--</tr>-->
                                                    <!--</tfoot>-->

                                                    <tbody>

                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tablewrapper">
                                                <table id="uppackageServtbl"
                                                       class="uppackagetbl cell-border row-border display centered responsive-table"
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
                                                    <!--<tfoot style="border: 1px solid #bdbdbd;">-->
                                                    <!--<tr>-->
                                                        <!--<th class="dt-head-center no-sort">ID</th>-->
                                                        <!--<th class="dt-head-left">Name</th>-->
                                                        <!--<th class="dt-head-left">Category</th>-->
                                                        <!--<th class="dt-head-right">Price</th>-->
                                                        <!--<th align="dt-head-right" class="no-sort">Quantity</th>-->
                                                    <!--</tr>-->
                                                    <!--</tfoot>-->

                                                    <tbody>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="col s9 z-depth-barts white">
                                <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                <div class="col s12" id="upPsList"
                                     style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                            </div>
                            <div class="col s3">
                                <div class="input-field col s12">
                                    <input type="text" class="right-align grey-text text-darken-3" disabled name=""
                                           id="upPackTotal" placeholder="Price"/>
                                    <label for="upPackTotal" class="active grey-text text-darken-3"><b>Total</b></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 20px !important;">
                                    <input type="text" class="right-align prodPrice" name="updatePackagePrice" id="upPackPrice"
                                           placeholder="Package Price" required/>
                                    <label for="upPackPrice"
                                           style="font-size: 13px !important;"
                                           class="active"><b>Package Price</b><i
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
                <a id="btnUpCancelPackage"
                   class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</a>
                <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" onclick="updatePackage()"
                        id="updatePackSubmitBtn">SAVE
                </button>
            </div>
        </form>
    </div>

</div>
