<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Package" %>
<%@ page import="com.gss.model.ProductPackage" %>
<%@ page import="com.gss.model.ServicePackage" %>
<%@ page import="com.gss.model.Service" %>
<%@ page import="com.gss.model.Product" %>
<%@ page import="java.util.List" %>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Package Maintenance</h3>
            <a class="createPackbtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createPackageModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#packageArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="packageSearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                   required>
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
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Package Name</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-left">Description</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </tfoot>

                <tbody>
                </tbody>
            </table>
        </div>
    </div>


    <c:forEach items="${packageList}" var="pack">
        <%
        Package list = (Package)pageContext.getAttribute("pack");
        int id = list.getIntPackageID();
        %>
        <!-- view product and service modal -->
        <div id="view<%=id%>" class="modal">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="row">
                    <h5 class="grey-text text-darken-1">Services & Products Included</h5>
                    <div class="input-field col s8 offset-s2">
                        <input type="text" readonly="true" id="packageViewName" name="packageViewName"
                               value="${pack.strPackageName}">
                        <label for="packageViewName">Package Name</label>
                    </div>

                    <table class="centered responsive-table">
                        <thead>
                        <tr>
                            <td>Services Included</td>
                            <td>Quantity</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach items="${pack.serviceList}" var="includedService">
                                <th data-field="viewService">${includedService.service.strServiceName}</th>
                                <th data-field="viewServiceQty">${includedService.intQuantity}</th>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>

                    <table class="centered responsive-table">
                        <thead>
                        <tr>
                            <td>Products Included</td>
                            <td>Quantity</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pack.productList}" var="includedProduct">
                            <tr>
                                <th data-field="viewProduct">${includedProduct.product.strProductName}</th>
                                <th data-field="viewProductQty">${includedProduct.intProductQuantity}</th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:forEach>

    <!-- end view product and service modal -->

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
                    <!--<div id="card-alert" class="card red z-depth-barts">-->
                    <!--<div class="card-content center white-text errorcontainer">-->
                    <!--</div>-->
                    <!--<button type="button" class="close red-text" data-dismiss="alert" aria-label="Close">-->
                    <!--<span aria-hidden="true"><i class="material-icons">close</i></span>-->
                    <!--</button>-->
                    <!--</div>-->
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
                                        <label for="crPackageDesc" class="active"><b>Description</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <select name="intPackageType" id="crPackageType" multiple required>
                                            <option disabled value="default">Choose your option</option>
                                            <option value="1">Event</option>
                                            <option value="2">Home Service</option>
                                            <option value="3">Walk-In</option>
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
                                    <input type="text" class="right-align prodPrice" name="" id="crPackPrice"
                                           placeholder="Price"/>
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
                <button type="submit" value="submit" id="createSubmitForm" onclick="createPackage()" disabled
                        class="actionpack submitformpack waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px; opacity: 0.3 !important;">CREATE
                </button>

            </div>
        </form>
    </div>


    <div id="updatePackageModal" class="updatePackageModal modal modal-fixed-footer">
        <div class="loadingbarts" id="packageloadingupdate"> <!-- mystyle.css -->
            <span class="centerer"></span>
            <img class="salonlogo" src="./img/logo.png" height="200"/>
        </div>

        <form class="col s12">
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
                                        class="firsttab purple-text text-darken-2 active waves-effect waves-light"
                                        href="#updateA"><b>INFO 1</b></a></li>
                                <li class="tab col s6"><a
                                        class="secondtab purple-text text-darken-2 waves-effect waves-light"
                                        href="#updateB"><b>INFO 2</b></a></li>
                            </ul>
                        </div>
                        <div id="updateA" class="ftab col s12" style="margin-top: 20px !important;">
                            <div class="container">
                                <div class="input-field col s12">
                                    <input type="hidden" name="intUpdatePackageID" id="upPackageID"
                                           style="margin-top: -10px !important;">
                                    <input type="text" name="strUpdatePackageName" id="upPackageName" placeholder="Package Name"/>
                                    <label for="upPackageName" class="active"><b>Package Name</b></label>
                                </div>
                                <div class="input-field col s12">
                                            <textarea name="strUpdatePackageDesc" class="materialize-textarea" placeholder="Description"
                                                      id="upPackageDesc" length="80" style="margin-top: -10px !important;"></textarea>
                                    <label for="upPackageDesc" class="active"><b>Description</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <select name="intUpdatePackageType" id="upPackageType" multiple
                                            autocomplete="off">
                                        <option value="default" disabled>Choose...</option>
                                        <option value="1">Event</option>
                                        <option value="2">Home Service</option>
                                        <option valie="3">Walk-In</option>
                                    </select>
                                    <label for="upPackageType"><b>Type</b></label>
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
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">ID</th>
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
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">ID</th>
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
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="#!"
                   class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</a>
                <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                        value="Submit">SAVE
                </button>
            </div>
        </form>
    </div>

</div>
