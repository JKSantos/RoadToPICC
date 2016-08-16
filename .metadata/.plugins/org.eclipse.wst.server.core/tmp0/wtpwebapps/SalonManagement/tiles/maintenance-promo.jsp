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
<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Promo Maintenance</h3>
            <a class="crPromoBtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#crPromoModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#promoArchive" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">archive</i></a>
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
                    <th class="dt-head-left">Guidelines</th>
                    <th class="dt-head-right">Price</th>
                    <th class="dt-head-right">Expiration</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Promo Name</th>
                    <th class="dt-head-left">Guidelines</th>
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
                                    <div class="input-field col s12">
                                    <textarea name="crPromoGuide" id="crPromoGuidelines" placeholder="Guidelines"
                                              class="materialize-textarea noSpace"
                                              style="margin-top: -10px !important;"></textarea>
                                        <label for="crPromoGuidelines" class="active"><b>Guidelines</b></label>
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
                                    <input name="dblPromoPrice" type="text"
                                           class="validate right-align"
                                           id="crPromoPrice" maxlength="8" placeholder="Amount">
                                    <label for="crPromoPrice" class="active"><b>Price</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
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
                <button id="createPromoSubmitForm" onclick="createPromo()"
                        class="actionpromo submitformpromo waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px; opacity: 0.3" disabled>CREATE
                </button>

            </div>
        </form>
    </div>

    <c:forEach items="${promoList}" var="promo">
        <div id="update${promo.intPromoID}" class="modal modal-fixed-footer"
             style="width: 75% !important; height: 80% !important; max-height: 100% !important;">
            <form class="col s12" method="post" action="updatePromo">
                <div class="modal-content">
                    <!-- <div class="container"> -->
                    <div class="wrapper">
                        <h4 class="grey-text text-darken-1">Update Promo</h4>
                        <div class="aside aside1 z-depth-barts" style="padding: 10px;">
                            <div class="row">
                                <h5 class="grey-text text-darken-1">Promo Information</h5>
                                <div class="input-field col s8 offset-s2" style="margin-bottom: 20px;">
                                    <label><b>Availability</b></label>
                                </div>
                                <div class="input-field col s6">
                                    <p class="center">
                                        <input name="strNonExp" type="checkbox" class="filled-in"
                                               id="updateNonExpiry${promo.intPromoID}">
                                        <label for="updateNonExpiry${promo.intPromoID}">Non-Expiry</label>
                                    </p>
                                </div>
                                <div class="input-field col s6">
                                    <input name="strExp" type="date" class="datepicker-promo"
                                           id="updatePromoEnd" placeholder="Expiration"/>
                                </div>
                                <div class="input-field col s12">
                                    <input name="strPromoName" type="text" class="validate"
                                           id="updatePromoName" value="${promo.strPromoName}" required>
                                    <label for="updatePromoName">Promo Name</label>
                                </div>

                                <!-- <<<<<<<< DO NOT ALTER >>>>>>>>>-->
                                <input type="hidden" name="intPromoID" value="${promo.intPromoID}">
                                <!-- <<<<<<<<< DO NOT ALTER >>>>>>>>>-->

                                <div class="input-field col s12">
                                                <textarea name="strPromoDesc" id="updatePromoDetails"
                                                          class="materialize-textarea" length="120">${promo.strPromoDescription}</textarea>
                                    <label for="updatePromoDetails">Description</label>
                                </div>
                                <%
                                Promo promo = (Promo)pageContext.getAttribute("promo");
                                double promoPrice = promo.getDblPromoPrice();
                                String free = "";
                                String price = "";

                                if(promo.getDblPromoPrice() == 0){
                                free = "checked";
                                }

                                %>
                                <div class="input-field col s5">
                                    <p class="center">
                                        <input name="strFree" type="checkbox" class="filled-in"
                                               id="updatePromoFree${promo.intPromoID}"
                                        <%out.println(free);%>/>
                                        <label for="updatePromoFree${promo.intPromoID}">Free</label>
                                    </p>
                                </div>
                                <div class="input-field col s6 offset-s1">
                                    <input name="dblPromoPrice" type="text" class="validate right-align"
                                           id="updatePromoPrice" maxlength="8"
                                           value="${promo.dblPromoPrice}">
                                    <label for="updatePromoPrice">Promo Price</label>
                                </div>
                            </div>
                        </div>
                        <div class="aside aside2 z-depth-barts" style="padding: 10px;">
                            <div class="row">
                                <h5 class="grey-text text-darken-1">Included Service/s and Product/s</h5>
                                <ul class="collapsible popout" data-collapsible="accordion">
                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">list</i>Services
                                        </div>
                                        <div class="collapsible-body">
                                            <div class="highlight centered responsive-table">
                                                <table class="centered striped services">
                                                    <thead>
                                                    <th>Select</th>
                                                    <th>Name</th>
                                                    <th>Price</th>
                                                    <th>Quantity</th>
                                                    </thead>
                                                    <c:forEach items="${serviceList}" var="service">
                                                        <%
                                                        String servChecked = "";

                                                        int servQuantity = 1;
                                                        Service service =
                                                        (Service)pageContext.getAttribute("service");
                                                        Promo servicePackage =
                                                        (Promo)pageContext.getAttribute("promo");
                                                        List
                                                        <ServicePackage> servicePack =
                                                            servicePackage.getServiceList();

                                                            int serviceID = service.getIntServiceID();


                                                            for(int intCtr = 0; intCtr < servicePack.size();
                                                            intCtr++){

                                                            if(servicePack.get(intCtr).getService().getIntServiceID()
                                                            == service.getIntServiceID()){
                                                            servChecked = "checked";
                                                            servQuantity =
                                                            servicePack.get(intCtr).getIntQuantity();
                                                            break;
                                                            }
                                                            else
                                                            continue;

                                                            }
                                                            %>
                                                            <tr>
                                                                <td><input type="checkbox"
                                                                           name="servicePromoSelect"
                                                                           id="myCheckBox${service.intServiceID}${promo.intPromoID}"
                                                                           value="${service.intServiceID}"
                                                                    <%out.println(servChecked);%>><label
                                                                            for="myCheckBox${service.intServiceID}${promo.intPromoID}"></label>
                                                                </td>
                                                                <td>${service.strServiceName}</td>
                                                                <td>${service.dblServicePrice}</td>
                                                                <td><input type="number"
                                                                           name="servicePromoQty"
                                                                           style="width: 75px" min="1"
                                                                           max="99"
                                                                           value="<%=servQuantity%>"></td>
                                                            </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">list</i>Products
                                        </div>
                                        <div class="collapsible-body">
                                            <div class="highlight centered responsive-table">
                                                <table class="centered striped services">
                                                    <thead>
                                                    <th>Select</th>
                                                    <th>Name</th>
                                                    <th>Price</th>
                                                    <th>Quantity</th>
                                                    </thead>
                                                    <c:forEach items="${productList}" var="products">
                                                        <%
                                                        String prodChecked = "";
                                                        int prodQuantity = 1;
                                                        Product product =
                                                        (Product)pageContext.getAttribute("products");
                                                        Promo productPromo =
                                                        (Promo)pageContext.getAttribute("promo");
                                                        List
                                                        <ProductPackage> productPack =
                                                            productPromo.getProductList();

                                                            for(int intCtr = 0; intCtr < productPack.size();
                                                            intCtr++){
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
                                                                <td><input type="checkbox"
                                                                           name="productPromoSelect"
                                                                           id="prodCheck${products.intProductID}${promo.intPromoID}"
                                                                           value="${products.intProductID}"
                                                                    <%out.println(prodChecked);%>><label
                                                                            for="prodCheck${products.intProductID}${promo.intPromoID}"></label>
                                                                </td>
                                                                <td>${products.strProductName}</td>
                                                                <td>${products.dblProductPrice}</td>
                                                                <td><input type="number"
                                                                           name="productPromoQty"
                                                                           style="width: 75px" min="1"
                                                                           max="99"
                                                                           value="<%=prodQuantity%>"></td>
                                                            </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>
                                    </li>

                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">list</i>Packages
                                        </div>
                                        <div class="collapsible-body">
                                            <div class="highlight centered responsive-table">
                                                <table class="centered striped services">
                                                    <thead>
                                                    <th>Select</th>
                                                    <th>Name</th>
                                                    <th>Price</th>
                                                    <th>Quantity</th>
                                                    </thead>
                                                    <c:forEach items="${packageList}" var="pack">
                                                        <%
                                                        String packChecked = "";
                                                        int packQuantity = 1;
                                                        Package packages =
                                                        (Package)pageContext.getAttribute("pack");
                                                        Promo packagePromo =
                                                        (Promo)pageContext.getAttribute("promo");
                                                        List
                                                        <PackagePackage> packPack =
                                                            packagePromo.getPackageList();

                                                            for(int intCtr = 0; intCtr < packPack.size();
                                                            intCtr++){
                                                            if(packPack.get(intCtr).getPack().getIntPackageID()
                                                            == packages.getIntPackageID()){
                                                            packChecked = "checked";
                                                            packQuantity =
                                                            packPack.get(intCtr).getIntPackageQuantity();
                                                            break;
                                                            }
                                                            else
                                                            continue;
                                                            }
                                                            %>
                                                            <tr>
                                                                <td><input type="checkbox"
                                                                           name="packagePromoSelect"
                                                                           id="promoPack${pack.intPackageID}${promo.intPromoID}"
                                                                           value="${pack.intPackageID}"
                                                                    <%out.println(packChecked);%>><label
                                                                            for="promoPack${pack.intPackageID}${promo.intPromoID}"></label>
                                                                </td>
                                                                <td>${pack.strPackageName}</td>
                                                                <td>${pack.dblPackagePrice}</td>
                                                                <td><input type="number"
                                                                           name="packagePromoQty"
                                                                           style="width: 75px" min="1"
                                                                           max="99"
                                                                           value="<%=packQuantity%>"></td>
                                                            </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="#!"
                       class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</a>
                    <button class="waves-effect waves-light purple darken-3 white-text btn-flat"
                            type="submit" value="Submit">SAVE
                    </button>
                </div>
            </form>
        </div>
    </c:forEach>

    <c:forEach items="${promoList}" var="promo">
        <div id="delete${promo.intPromoID}" class="modal"
             style="width: 30% !important; height: 40% !important">
            <form method="post" action="deactivatePromo">
                <div class="container">
                    <div class="modal-content">
                        <div class="row">
                            <input type="hidden" name="intPromoID" value="${promo.intPromoID}">
                            <h5 class="red-text">Warning!</h5>
                            <p>Are you sure you want to deactivate</p>
                            <p>${promo.strPromoName}</p>
                        </div>
                    </div>
                    <div class="col s12 center" style="margin-bottom: 30px;">
                        <button class="waves-effect waves-light purple btn-flat white-text">YES</button>
                        <a href="#"
                           class="modal-action modal-close waves-effect waves-light transparent btn-flat black-text">NO</a>
                    </div>
                </div>
            </form>
        </div>
    </c:forEach>
</div>