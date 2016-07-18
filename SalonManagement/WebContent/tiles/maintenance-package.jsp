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
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
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
                <c:forEach items="${packageList}" var="pack">
                    <%
                    Package packge = (Package)pageContext.getAttribute("pack");
                    String type = "";
                    String packID = String.valueOf(packge.getIntPackageID());

                    if(packge.getIntPackageType() == 1){
                    type = "Event";
                    }
                    else if(packge.getIntPackageType() == 2){
                    type = "Home Service";
                    }
                    else{
                    type = "Walk-In";
                    }
                    %>
                    <tr>
                        <td class="dt-body-left">${pack.strPackageName}</td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left ">
                            <%=type%>
                        </td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left">
                            ${pack.strPackageDesc}
                        </td>
                        <td class="center" style="padding:0; margin:0;">
                            <a data-delay="30" data-position="bottom" data-tooltip="View"
                               class="waves-effect waves-purple modal-viewall btn-flat transparent black-text"
                               href="#view<%=packID%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">visibility</i>
                            </a>
                            <a data-delay="30" data-position="bottom" data-tooltip="Update"
                               class="waves-effect waves-purple modal-trigger btn-flat transparent black-text empUpdatebtn"
                               href="#update<%=packID%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <button class="packagedeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                    style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                    id="${pack.intPackageID}" title="Deactivate"><i class="material-icons">delete</i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>

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
                    <div class="steppack well">
                        <div class="wrapper">
                            <div class="row form-group">
                                <div class="container">
                                    <div class="input-field col s12">
                                        <input name="strPackageName" id="crPackageName" type="text"
                                               class="validate" placeholder="Package Name"/>
                                        <label for="crPackageName" class="active" id="label1"><b>Package Name</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s12">
                                    <textarea name="strPackageDesc" id="crPackageDesc" type="text"
                                              class="materialize-textarea" length="80"
                                              placeholder="Description"></textarea>
                                        <label for="crPackageDesc" class="active"><b>Description</b></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <select name="intPackageType" id="crPackageType" multiple>
                                            <option disabled selected value="default">Choose your option</option>
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
                                        <div class="collapsible-header"><i class="material-icons">view_list</i>List
                                        </div>
                                        <div class="collapsible-body"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                            <table id="crpackagetbl"
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

                                                <c:forEach items="${productList}" var="product">
                                                    <tr>
                                                        <td class="dt-body-left">
                                                            <input type="checkbox" name="createPackProdType"
                                                                   id="prodCheck${product.intProductID}"
                                                                   class="packcheckbox"
                                                                   value="${product.intProductID}"><label
                                                                for="prodCheck${product.intProductID}"></label>
                                                        </td>
                                                        <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                            class="dt-body-left ">${product.strProductName}
                                                        </td>
                                                        <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                            Product
                                                        </td>
                                                        <td style="padding-right: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                            class="dt-body-right prodPrice">
                                                            <input type="text" name="crPackProdPrice"
                                                                   id="crPackProdPrice"
                                                                   value="${product.dblProductPrice}" disabled
                                                                   class="black-text rowPrice"/>
                                                        </td>
                                                        <td class="dt-body-right"
                                                            style="padding-right: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                            <input type="number" class="right-align rowQty"
                                                                   name="createPackProdQty"
                                                                   id="p${product.intProductID}"
                                                                   style="width: 75px" disabled
                                                                   min="1" max="99" value="1" maxlength="2">
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                                <c:forEach items="${serviceList}" var="service">
                                                    <tr>
                                                        <td class="dt-body-left">
                                                            <input type="checkbox" name="createPackProdType"
                                                                   id="myCheckBox${service.intServiceID}"
                                                                   class="packcheckbox"
                                                                   value="${service.intServiceID}"><label
                                                                for="myCheckBox${service.intServiceID}"></label>
                                                        </td>
                                                        <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                            class="dt-body-left ">${service.strServiceName}
                                                        </td>
                                                        <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                            Service
                                                        </td>
                                                        <td style="padding-right: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                            class="dt-body-right prodPrice">
                                                            <input type="text" name="crPackProdPrice"
                                                                   id="crPackServPrice"
                                                                   value="${service.dblServicePrice}" disabled
                                                                   class="black-text rowPrice"/>
                                                        </td>
                                                        <td class="dt-body-right"
                                                            style="padding-right: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                            <input type="number" class="right-align rowQty"
                                                                   name="createPackProdQty"
                                                                   id="s${service.intServiceID}"
                                                                   style="width: 75px" disabled value="1" maxlength="2">
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                                </tbody>
                                            </table>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="col s12 z-depth-barts white">
                                <h6 class="center"><b>Selected Item</b></h6>
                                <div class="col s12" id="list"
                                     style="margin-top: -10px !important; margin-bottom: 5px !important;">
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
                <button type="button" id="backbtn"
                        class="actionpack backformpack waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>
                <button type="button" id="nextbtn"
                        class="actionpack nextformpack waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button type="submit" value="Submit" id="createSubmitForm"
                        class="actionpack submitformpack waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px;">CREATE
                </button>
            </div>
        </form>
    </div>

    <c:forEach items="${packageList}" var="pack">
        <%
        Package pg = (Package)pageContext.getAttribute("pack");
        int id = pg.getIntPackageID();

        %>
        <div id="update<%=id%>" class="updatePackageModal modal modal-fixed-footer">
            <form class="col s12" method="post" action="updatePackage">
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
                                            href="#update<%=id%>A"><b>INFO 1</b></a></li>
                                    <li class="tab col s6"><a
                                            class="secondtab purple-text text-darken-2 waves-effect waves-light"
                                            href="#update<%=id%>B"><b>INFO 2</b></a></li>
                                </ul>
                            </div>
                            <div id="update<%=id%>A" class="ftab col s12" style="margin-top: 20px !important;">
                                <div class="wrapper">
                                    <div class="container">
                                        <div class="input-field col s12">
                                            <input type="text" name="strUpdatePackageName" id="upPackageName"
                                                   value="${pack.strPackageName}"/>
                                            <input type="hidden" name="intUpdatePackageID" value="${pack.intPackageID}"
                                                   style="margin-top: -10px !important;">
                                            <label for="upPackageName" class="active"><b>Package Name</b></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <textarea name="strUpdatePackageDesc" class="materialize-textarea"
                                                      id="upPackageDesc" length="80">${pack.strPackageDesc}</textarea>
                                            <label for="upPackageDesc" class="active"><b>Description</b></label>
                                        </div>
                                        <%
                                        Package packge = (Package)pageContext.getAttribute("pack");

                                        String type1 = "";
                                        String type2 = "";
                                        String type3 = "";

                                        if(packge.getIntPackageType() == 1){
                                        type1 = "selected";
                                        }
                                        else if(packge.getIntPackageType() == 2){
                                        type2 = "selected";
                                        }
                                        else if(packge.getIntPackageType() == 3){
                                        type3 = "selected";
                                        }
                                        else if(packge.getIntPackageType() == 4){
                                        type1 = "selected";
                                        type2 = "selected";
                                        }
                                        else if(packge.getIntPackageType() == 5){
                                        type1 = "selected";
                                        type3 = "selected";
                                        }
                                        else if(packge.getIntPackageType() == 6){
                                        type2 = "selected";
                                        type3 = "selected";
                                        }
                                        else if(packge.getIntPackageType() == 7){
                                        type1 = "selected";
                                        type2 = "selected";
                                        type3 = "selected";
                                        }
                                        %>
                                        <div class="input-field col s12">
                                            <select name="intUpdatePackageType" id="upPackageType" multiple
                                                    autocomplete="off">
                                                <option value="default" disabled>Choose...</option>
                                                <option value="1"
                                                <%out.println(type1);%>>Event</option>
                                                <option value="2"
                                                <%out.println(type2);%>>Home Service</option>
                                                <option valie="3"
                                                <%out.println(type3);%>>Walk-In</option>
                                            </select>
                                            <label for="upPackageType"><b>Type</b></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="update<%=id%>B" class="ftab col s12" style="margin-top: 15px !important;">
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
                                                <table id="uppackagetbl"
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

                                                    <c:forEach items="${productList}" var="products">
                                                        <%
                                                        String prodChecked = "";
                                                        int prodQuantity = 1;
                                                        Product product =
                                                        (Product)pageContext.getAttribute("products");
                                                        Package productPackage =
                                                        (Package)pageContext.getAttribute("pack");
                                                        List
                                                        <ProductPackage> productPack =
                                                            productPackage.getProductList();

                                                            for(int intCtr = 0; intCtr < productPack.size();
                                                            intCtr++){
                                                            System.out.println("Current Services On Package: " +
                                                            productPack.get(intCtr).getProduct().getIntProductID());
                                                            if(productPack.get(intCtr).getProduct().getIntProductID()
                                                            == product.getIntProductID()){
                                                            prodChecked = "checked";
                                                            prodQuantity =
                                                            productPack.get(intCtr).getIntProductQuantity();
                                                            break;
                                                            }
                                                            else
                                                            continue;

                                                            }
                                                            %>
                                                            <tr>
                                                                <td class="dt-body-left">
                                                                    <input type="checkbox" name="updatePackProdType"
                                                                           id="updateProdCheck${pack.intPackageID}${products.intProductID}"
                                                                           value="${products.intProductID}"
                                                                    <%out.println(prodChecked);%>><label
                                                                        for="updateProdCheck${pack.intPackageID}${products.intProductID}"></label>
                                                                </td>
                                                                <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                                    class="dt-body-left ">${products.strProductName}
                                                                </td>
                                                                <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                                    Service
                                                                </td>
                                                                <td style="padding-right: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                                    class="dt-body-right prodPrice">
                                                                    <input type="text" name="crPackProdPrice"
                                                                           id="upPackProdPrice"
                                                                           value="${products.dblProductPrice}" disabled
                                                                           class="black-text rowPrice"/>
                                                                </td>
                                                                <td class="dt-body-right"
                                                                    style="padding-right: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                                    <input type="number" class="right-align rowQty"
                                                                           name="updatePackProdQty" value="<%=prodQuantity%>"
                                                                           id="upP${product.intProductID}"
                                                                           style="width: 75px" disabled value="1"
                                                                           maxlength="2">
                                                                </td>
                                                            </tr>
                                                    </c:forEach>

                                                    <c:forEach items="${serviceList}" var="service">
                                                        <%
                                                        String servChecked = "";

                                                        int servQuantity = 1;
                                                        Service service =
                                                        (Service)pageContext.getAttribute("service");
                                                        Package servicePackage =
                                                        (Package)pageContext.getAttribute("pack");
                                                        List
                                                        <ServicePackage> servicePack =
                                                            servicePackage.getServiceList();

                                                            int serviceID = service.getIntServiceID();


                                                            for(int intCtr = 0; intCtr < servicePack.size();
                                                            intCtr++){
                                                            System.out.println("Current Services On Package: " +
                                                            servicePack.get(intCtr).getService().getIntServiceID());
                                                            if(servicePack.get(intCtr).getService().getIntServiceID()
                                                            == service.getIntServiceID()){
                                                            servChecked = "checked";
                                                            servQuantity = servicePack.get(intCtr).getIntQuantity();
                                                            break;
                                                            }
                                                            else
                                                            continue;

                                                            }
                                                            %>
                                                        <tr>
                                                            <td class="dt-body-left">
                                                                <input type="checkbox" name="updatePackServType"
                                                                       id="updateServCheckBox${pack.intPackageID}<%=serviceID%>"
                                                                       value="${service.intServiceID}"
                                                                <%out.println(servChecked);%>><label
                                                                    for="updateServCheckBox${pack.intPackageID}<%=serviceID%>"></label>
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                                class="dt-body-left ">${service.strServiceName}
                                                            </td>
                                                            <td style="padding-left: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                                Service
                                                            </td>
                                                            <td style="padding-right: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;"
                                                                class="dt-body-right prodPrice">
                                                                <input type="text" name="crPackProdPrice"
                                                                       id="upPackServPrice"
                                                                       value="${service.dblServicePrice}" disabled
                                                                       class="black-text rowPrice"/>
                                                            </td>
                                                            <td class="dt-body-right"
                                                                style="padding-right: 10px !important; margin: 0px !important; padding-top: 0px !important; padding-bottom: 0px !important;">
                                                                <input type="number" class="right-align rowQty"
                                                                       name="updatePackServQty"
                                                                       id="upS${service.intServiceID}"
                                                                       style="width: 75px" disabled value="<%=servQuantity%>"
                                                                       maxlength="2">
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

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
    </c:forEach>

    <c:forEach items="${packageList}" var="pack">
        <%
        Package packss = (Package)pageContext.getAttribute("pack");
        int id = packss.getIntPackageID();
        %>
        <div id="del<%=id%>" class="modal">
            <div class="container">
                <form method="post" action="deactivatePackage">
                    <div class="modal-content">
                        <div class="row">
                            <h5 class="red-text">Warning!</h5>
                            <p>Are you sure you want to deactivate</p>
                            <p>${pack.strPackageName}</p>
                            <input type="hidden" name="intPackageID" value="<%=id%>">
                        </div>
                    </div>
                    <div class="col s12 center" style="margin-bottom: 30px;">
                        <button class="waves-effect waves-light purple btn-flat white-text">YES</button>
                        <a href="#"
                           class="modal-action modal-close waves-effect waves-purple transparent btn-flat black-text">NO</a>
                    </div>
                </form>
            </div>
        </div>
    </c:forEach>
    <!--                     <div class="aside aside2 z-depth-barts">

                        </div> -->

</div>
