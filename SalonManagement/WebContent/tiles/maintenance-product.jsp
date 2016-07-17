<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Product" %>
<%@ page import="com.gss.model.Service" %>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Product Maintenance</h3>
            <a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"
               href="#createProduct" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>
            <a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"
               href="#empArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="prodSearch" class="grey-text text-darken-4" type="search" required>
                            <label for="prodSearch" style="margin-top: -20px !important;"><i
                                    class="material-icons grey-text text-darken-4"
                                    style="margin-top: 20px;">search</i></label>
                            <i class="material-icons grey-text text-darken-4">close</i>
                        </div>
                    </form>
                </div>
            </nav>
            <table id="prodtbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0" width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Category</th>
                    <th class="dt-head-left no-sort">Detail</th>
                    <th class="dt-head-right">Price</th>
                    <th class="dt-head-center no-sort">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${productList}" var="product">
                    <c:set var="price" scope="session"
                           value="${(product.dblProductPrice * 9) + product.dblProductPrice}"></c:set>
                    <%! String string = null; %>
                    <% Product prod = (Product)pageContext.getAttribute("product");
                    string = String.valueOf(prod.getIntProductID());
                    %>
                    <tr>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left">${product.strProductName}</td>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left">${product.strProductCategory}
                        </td>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left">${product.strProductDesc}</td>
                        <td style="padding-right: 10px; margin: 0;" class="dt-body-right prodPrice">
                            <c:out value="${price}"/>
                        </td>
                        <td style="padding:0; margin: 0;" class="dt-body-center">
                            <a data-delay="30" data-position="bottom" data-tooltip="View"
                               class="tooltipped waves-effect waves-purple modal-viewall btn-flat transparent black-text"
                               href="#viewprod<%=string%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">visibility</i>
                            </a>
                            <a class="waves-effect waves-purple modal-trigger btn-flat transparent black-text"
                               href="#prod<%=string%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <button class="proddeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                    id="${product.intProductID}"
                                    style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">delete</i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Category</th>
                    <th class="dt-head-left">Detail</th>
                    <th class="dt-head-right">Price</th>
                    <th class="dt-head-center">Actions</th>
                </tr>
                </tfoot>
            </table>
        </div>

    </div>

    <!-- ARCHIVE BEGIN -->

    <!-- ARCHIVE END -->

    <!-- Modal Structure -->
    <div id="createProduct" class="modal modal-fixed-footer">
        <form class="col s12" method="post" id="createProductForm" action="createProdServ"
              enctype="multipart/form-data">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <div class="input-field col s12" style="margin-top: -5px !important;">
                        <h4 class="grey-text center text-darken-1">Create Product<a id="btnProdCrExit" type="reset"
                                                                                    value="Reset"
                                                                                    class="modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    </div>
                    <div class="crproderrorcontainer center input-field col s12 card red white-text z-depth-barts"
                         style="margin-bottom: 15px !important; margin-top: 5px !important;">

                    </div>
                    <div class="aside aside1 z-depth-0">
                        <div class="row">
                            <!--<div class="input-field col s12">-->
                            <!--<img name="image" id="employeeimg" class="circle"-->
                            <!--style="width: 200px; height: 200px;" src="./img/anon.jpg" alt=""/>-->
                            <!--</div>-->
                            <div class="input-field col s12">
                                <div class="file-field">
                                    <input name="upload" class="dropify z-depth-3" type="file"
                                           data-allowed-file-extensions="png jpg jpeg"
                                           data-default-file="./img/packIcon.png" data-height="220"
                                           data-file-max-size="3M" data-width="150"
                                           data-show-errors="true"/>
                                    <!--<div class="file-path-wrapper">-->
                                    <!--<input name="path" value="image" class="file-path validate"-->
                                    <!--type="text">-->
                                    <!--</div>-->
                                </div>
                            </div>

                        </div>

                    </div>
                    <div class="aside aside2 z-depth-0">
                        <div class="row">
                            <div class="input-field col s12" style="margin-top: 25px !important;">
                                <input type="hidden" name="strItemCate"
                                       value="Product"/>
                                <input type="text" name="strItemName" id="crItemName" required
                                       placeholder="Product Name"/>
                                <label for="crItemName" class="active"><b>Name</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s12">
                                    <textarea id="crItemDetails" name="strItemDetails"
                                              class="materialize-textarea" placeholder="Details"
                                              maxlength="25" style="margin-top: -10px !important;"></textarea>
                                <label for="crItemDetails" class="active"><b>Details</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s8">
                                <select id="crItemCategory" name="strItemCategory" class="required"
                                        required style="margin-bottom: -15px !important;">
                                    <option value="default" disabled selected>Choose...</option>
                                    <c:forEach items="${productCategory}" var="product">
                                        <option value="${product}">${product}</option>
                                    </c:forEach>
                                </select>
                                <label for="crItemCategory"><b>Category</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s4">
                                <a href="#crProdAddCate"
                                   class="waves-effect waves-light btn-flat modal-category purple white-text"><i
                                        class="material-icons">add</i></a>
                            </div>
                            <div class="input-field col s6 offset-s6" style="margin-bottom: -15px !important;">
                                <input value="${product.dblProductPrice}" type="text"
                                       class="validate right-align upProdItemPrice"
                                       id="ItemPrice" name="price" required placeholder="P9.99"/>
                                <label for="ItemPrice" class="active"><b>Price</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
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
                <button type="reset" value="Reset" id="crProdCancel"
                        class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL
                </button>
                <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                        value="Submit">CREATE
                </button>
            </div>
        </form>
    </div>

    <!-- Modal Structure -->
    <

    <!-- add category BEGIN -->
    <div id="crProdAddCate" class="modal" style="margin-top: 30px;">
        <form id="createAddCatForm">
            <div class="modal-content">
                <h4>Add Another Category</h4>
                <div class="row">
                    <div class="col s12">
                        <div class="crprodcat center input-field col s12 card red white-text z-depth-barts">

                        </div>
                        <div class="input-field col s8 offset-s2">

                            <select id="createAddCategorySelect" class="browser-default" size="10"
                                    style="height: 120px !important; border-bottom: none !important;">
                                <c:forEach items="${productCategory}" var="product">
                                    <option value="${product}">${product}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                            <input type="text" class="validate"
                                   id="crProdAddCatName" name="crProdAddCatName"
                                   placeholder="New Category" required/>
                            <label for="crProdAddCatName" class="active"><b>Category</b></label>
                        </div>
                        <div class="input-field col s12 center">
                            <a id="createAddCatBtn"
                               class="waves-effect waves-light purple darken-3 btn-flat white-text">SAVE
                            </a>
                            <button type="reset" value="Reset" id="crAddProdCatCancel"
                                    class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- add category END -->


    <c:forEach items="${productList}" var="product">
        <%! String strProdID = null; %>
        <% Product prodID = (Product)pageContext.getAttribute("product");
        strProdID = String.valueOf(prodID.getIntProductID());
        String productCate = prodID.getStrProductCategory();
        %>
        <c:set var="price" scope="session" value="${(product.dblProductPrice * 9) + product.dblProductPrice}"></c:set>
        <div id="prod<%=strProdID%>" class="prodUpdateModal modal modal-fixed-footer">
            <form class="col s12 updateProdForm" method="post" id="updateProdForm" action="updateItem"
                  enctype="multipart/form-data">
                <div class="modal-content">
                    <!-- <div class="container"> -->
                    <div class="wrapper">
                        <div class="input-field col s12" style="margin-top: -5px !important;">
                            <h4 class="grey-text center text-darken-1">Update Product<a id="btnProdUpExit"
                                                                                        type="reset" value="Reset"
                                                                                        class="btnProdUpExit modal-action modal-close"><i
                                    class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                        </div>
                        <div class="upproderrorcontainer center input-field col s12 card red white-text z-depth-barts"
                             style="margin-bottom: 15px !important; margin-top: 5px !important;">

                        </div>
                        <div class="aside aside1 z-depth-0">
                            <div class="row">
                                <!--<div class="input-field col s12">-->
                                <!--<img name="image" id="employeeimg" class="circle"-->
                                <!--style="width: 200px; height: 200px;" src="./img/anon.jpg" alt=""/>-->
                                <!--</div>-->
                                <div class="col s12">

                                    <img name="prodsvcCreate" id="updateProdImage" class="updateProdImage"
                                         style="width: 200px; height: 200px; margin-top: 20px;"
                                         src="<s:url action='getImage'><s:param name='ImageID'><%=strProdID%></s:param><s:param name='type'>product</s:param></s:url>"
                                         alt="${product.strProductName}"/>
                                </div>
                                <div class="input-field col s12">
                                    <div class="file-field">
                                        <div class="btn purple darken-3">
                                            <span class=""><i class="material-icons">add_a_photo</i></span>
                                            <input name="upload" type="file" accept="image/.jpg, image/.png"
                                                   class="upProdImg">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input value="image" name="imageName" class="file-path validate"
                                                   type="text">
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>
                        <div class="aside aside2 z-depth-0">
                            <div class="row">
                                <div class="input-field col s12" style="margin-top: 25px !important;">
                                    <input type="hidden" name="intItemID" value="<%=strProdID%>">
                                    <input type="hidden" name="strItemCate" value="Product">
                                    <input value="${product.strProductName}" type="text" name="strItemName"
                                           id="upItemName" required
                                           placeholder="Product Name"/>
                                    <label for="upItemName" class="active"><b>Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <textarea id="upItemDetails" name="strItemDetails"
                                              class="materialize-textarea" placeholder="Details"
                                              style="margin-top: -10px !important;">${product.strProductDesc}</textarea>
                                    <label for="upItemDetails" class="active"><b>Details</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s8">
                                    <select id="upItemCategory" name="strItemCategory"
                                            class="required upItemCategory"
                                            required style="margin-bottom: -15px !important;">
                                        <option value="default" disabled selected>Choose...</option>
                                        <c:forEach items="${productCategory}" var="cate">
                                            <%
                                            String cate = (String)pageContext.getAttribute("cate");
                                            String selectedProduct = null;
                                            if(productCate.equals(cate)){
                                            selectedProduct = "selected";
                                            }
                                            else {
                                            selectedProduct = "";
                                            }
                                            %>
                                            <option value="${cate}"
                                            <%out.println(selectedProduct);%>>${cate}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="upItemCategory"><b>Category</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s4">
                                    <a href="#upProdAddCatModal"
                                       class="waves-effect waves-light btn-flat modal-category purple white-text"><i
                                            class="material-icons">add</i></a>
                                </div>
                                <div class="input-field col s6 offset-s6" style="margin-bottom: -15px !important;">
                                    <input type="text"
                                           class="validate right-align upProdItemPrice"
                                           id="upItemPrice" name="price" required placeholder="P9.99"
                                           value="<c:out value='${price}'/>"/>
                                    <label for="upItemPrice" class="active"><b>Price</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
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
                    <button type="reset" value="Reset" id="upProdCancel"
                            class="upProdCancel modal-action modal-close waves-effect waves-purple transparent btn-flat">
                        CANCEL
                    </button>
                    <button class="waves-effect waves-light purple darken-3 white-text btn-flat upProdSubmitBtn"
                            type="submit"
                            value="Submit">UPDATE
                    </button>
                </div>
            </form>
        </div>
    </c:forEach>

    <div id="upProdAddCatModal" class="upProdAddCatModal modal" style="margin-top: 30px;">
        <form id="upProdAddCatForm" class="upProdAddCatForm">
            <div class="modal-content">
                <h4>Add Another Category</h4>
                <div class="row">
                    <div class="col s12">
                        <div class="upprodcat center input-field col s12 card red white-text z-depth-barts">

                        </div>
                        <div class="input-field col s8 offset-s2">

                            <select id="updateAddCategorySelect" class="browser-default" size="10"
                                    style="height: 120px !important; border-bottom: none !important;">
                                <c:forEach items="${productCategory}" var="product">
                                    <option value="${product}">${product}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                            <input type="text" class="validate upProdAddCatName"
                                   id="upProdAddCatName" name="upProdAddCatName"
                                   placeholder="New Category" required/>
                            <label for="upProdAddCatName" class="active"><b>Category</b></label>
                        </div>
                        <div class="input-field col s12 center">
                            <a id="updateProdAddCatBtn"
                               class="updateProdAddCatBtn waves-effect waves-light purple darken-3 btn-flat white-text">SAVE
                            </a>
                            <button type="reset" value="Reset" id="upAddProdCatCancel"
                                    class="upAddProdCatCancel modal-close waves-effect waves-purple transparent btn-flat white">
                                CANCEL
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!--  START VIEW PRODUCT -->

    <!-- add category END -->


    <!--                     <div class="aside aside2 z-depth-barts">

    </div> -->
</div>
<!-- </div> -->