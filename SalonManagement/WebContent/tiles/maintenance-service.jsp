<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Product" %>
<%@ page import="com.gss.model.Service" %>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Service Maintenance</h3>
            <a class="z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"
               href="#createService" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>
            <a class="z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"
               href="#empArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="servSearch" class="grey-text text-darken-4" type="search" required>
                            <label for="servSearch" style="margin-top: -20px !important;"><i
                                    class="material-icons grey-text text-darken-4"
                                    style="margin-top: 20px;">search</i></label>
                            <i class="material-icons grey-text text-darken-4">close</i>
                        </div>
                    </form>
                </div>
            </nav>
            <table id="servtbl" class="z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0" width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Category</th>
                    <th class="dt-head-left">Detail</th>
                    <th class="dt-head-right">Price</th>
                    <th class="dt-head-center">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${serviceList}" var="service">
                    <%! String strService =null; %>
                    <% Service serv = (Service)pageContext.getAttribute("service");
                    strService = String.valueOf(serv.getIntServiceID());
                    %>
                    <tr>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left">${service.strServiceName}</td>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left">${service.strServiceCategory}
                        </td>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left">${service.strServiceDesc}</td>
                        <td style="padding-right: 10px; margin: 0;" class="dt-body-right servPrice">
                            ${service.dblServicePrice}
                        </td>
                        <td style="padding:0; margin: 0;" class="dt-body-center">
                            <a data-delay="30" data-position="bottom" data-tooltip="View"
                               class="tooltipped waves-effect waves-purple modal-viewall btn-flat transparent black-text"
                               href=""
                               style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">visibility</i>
                            </a>
                            <a class="waves-effect waves-purple modal-trigger btn-flat transparent black-text"
                               href="#serv<%=serviceID%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <a class="waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4"
                               href="#del<%=strService%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">delete</i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>

        <!-- ARCHIVE BEGIN -->

        <!-- ARCHIVE END -->

        <!-- Modal Structure -->
        <div id="createService" class="modal modal-fixed-footer">
            <form class="col s12" method="post" id="createServiceForm" action="createProdServ"
                  enctype="multipart/form-data">
                <div class="modal-content">
                    <!-- <div class="container"> -->
                    <div class="wrapper">
                        <div class="input-field col s12" style="margin-top: -5px !important;">
                            <h4 class="grey-text center text-darken-1">Create Service<a id="btnServCrExit" type="reset"
                                                                                        value="Reset"
                                                                                        class="modal-action modal-close"><i
                                    class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                        </div>
                        <div class="crserverrorcontainer center input-field col s12 red darken-4 white-text z-depth-barts"
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
                                    <input type="text" name="strItemName" id="crServiceName" required
                                           placeholder="Service Name"/>
                                    <label for="crServiceName" class="active"><b>Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <textarea id="crServiceDetails" name="strItemDetails"
                                              class="materialize-textarea" placeholder="Details"
                                              maxlength="25" style="margin-top: -10px !important;"></textarea>
                                    <label for="crServiceDetails" class="active"><b>Details</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s8">
                                    <select id="crServiceCategory" name="strItemCategory" class="required"
                                            required style="margin-bottom: -15px !important;">
                                        <option value="default" disabled selected>Choose...</option>
                                        <c:forEach items="${serviceCategory}" var="service">
                                            <option value="${service}">${service}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="crServiceCategory"><b>Category</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s4">
                                    <a href="#crServAddCateModal"
                                       class="waves-effect waves-light btn-flat modal-category purple white-text"><i
                                            class="material-icons">add</i></a>
                                </div>
                                <div class="input-field col s6 offset-s6" style="margin-bottom: -15px !important;">
                                    <input type="text" class="validate right-align"
                                           id="crServicePrice" name="dblItemPrice" required placeholder="P9.99"/>
                                    <label for="crServicePrice" class="active"><b>Price</b><i
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
                    <button type="reset" value="Reset" id="crServCancel"
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
        <div id="crServAddCateModal" class="modal" style="margin-top: 30px;">
            <form id="createServAddCatForm">
                <div class="modal-content">
                    <h4>Add Another Category</h4>
                    <div class="row">
                        <div class="col s12">
                            <div class="crservcat center input-field col s12 red darken-4 white-text z-depth-barts">

                            </div>
                            <div class="input-field col s8 offset-s2">

                                <select id="createServAddCategorySelect" class="browser-default" size="10"
                                        style="height: 120px !important; border-bottom: none !important;">
                                    <c:forEach items="${serviceCategory}" var="service">
                                        <option value="${service}">${product}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                                <input type="text" class="validate"
                                       id="crServAddCatName" name="crServAddCatName"
                                       placeholder="New Category" required/>
                                <label for="crServAddCatName" class="active"><b>Category</b></label>
                            </div>
                            <div class="input-field col s8 offset-s2 center">
                                <a id="createServAddCatBtn"
                                   class="waves-effect waves-light purple darken-3 btn-flat white-text">SAVE
                                </a>
                                <button type="reset" value="Reset" id="crServAddProdCatCancel"
                                        class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- add category END -->


        <c:forEach items="${serviceList}" var="service">
            <%! String serviceID = null; %>
            <% Service servID = (Service)pageContext.getAttribute("service");
            serviceID = String.valueOf(servID.getIntServiceID());
            String serviceCate = servID.getStrServiceCategory();
            double price = servID.getDblServicePrice();
            %>
            <div id="serv<%=serviceID%>" class="servUpdateModal modal modal-fixed-footer">
                <form class="col s12 updateservForm" method="post" id="updateservForm" action="updateItem"
                      enctype="multipart/form-data">
                    <div class="modal-content">
                        <!-- <div class="container"> -->
                        <div class="wrapper">
                            <div class="input-field col s12" style="margin-top: -5px !important;">
                                <h4 class="grey-text center text-darken-1">Update Product<a id="btnServUpExit"
                                                                                            type="reset" value="Reset"
                                                                                            class="btnServUpExit modal-action modal-close"><i
                                        class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                            </div>
                            <div class="upserverrorcontainer center input-field col s12 red darken-4 white-text z-depth-barts"
                                 style="margin-bottom: 15px !important; margin-top: 5px !important;">

                            </div>
                            <div class="aside aside1 z-depth-0">
                                <div class="row">
                                    <!--<div class="input-field col s12">-->
                                    <!--<img name="image" id="employeeimg" class="circle"-->
                                    <!--style="width: 200px; height: 200px;" src="./img/anon.jpg" alt=""/>-->
                                    <!--</div>-->
                                    <div class="col s12">

                                        <img name="prodsvcCreate" id="updateServImage" class="updateServImage"
                                             style="width: 200px; height: 200px; margin-top: 20px;"
                                             src="<s:url action='getImage'><s:param name='ImageID'><%=serviceID%></s:param><s:param name='type'>service</s:param></s:url>"
                                             alt="${service.strServiceName}"/>
                                    </div>
                                    <div class="input-field col s12">
                                        <div class="file-field">
                                            <div class="btn purple darken-3">
                                                <span class=""><i class="material-icons">add_a_photo</i></span>
                                                <input name="upload" type="file" accept="image/.jpg, image/.png"
                                                       class="upServImg">
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
                                        <input type="hidden" name="intItemID" value="${service.intServiceID}">
                                        <input value="${service.strServiceName}" type="text" name="strItemName"
                                               id="upServName" required
                                               placeholder="Product Name"/>
                                        <label for="upServName" class="active"><b>Name</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s12">
                                    <textarea id="upServDetails" name="strItemDetails"
                                              class="materialize-textarea" placeholder="Details"
                                              style="margin-top: -10px !important;">
                                        ${service.strServiceDesc}
                                    </textarea>
                                        <label for="upServDetails" class="active"><b>Details</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s8">
                                        <select id="updateServCategory" name="strItemCategory"
                                                class="required updateServCategory"
                                                required style="margin-bottom: -15px !important;">
                                            <option value="default" disabled>Choose...</option>
                                            <c:forEach items="${serviceCategory}" var="service">
                                                <%
                                                String cate3 = (String)pageContext.getAttribute("service");
                                                String selectedService = null;
                                                if(serviceCate.equals(cate3)){
                                                selectedService = "selected";
                                                }
                                                else {
                                                selectedService = "";
                                                }
                                                %>
                                                <option value="${service}" <%out.println(selectedService);%>>${service}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="updateServCategory"><b>Category</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s4">
                                        <a href="#upServAddCatModal"
                                           class="waves-effect waves-light btn-flat modal-category purple white-text"><i
                                                class="material-icons">add</i></a>
                                    </div>
                                    <div class="input-field col s6 offset-s6" style="margin-bottom: -15px !important;">
                                        <input value="${service.dblServicePrice}" type="text"
                                               class="validate right-align servPrice"
                                               id="upServPrice" name="dblItemPrice" required placeholder="P9.99"/>
                                        <label for="upServPrice" class="active"><b>Price</b><i
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
                        <button type="reset" value="Reset" id="upServCancel"
                                class="upServCancel modal-action modal-close waves-effect waves-purple transparent btn-flat">
                            CANCEL
                        </button>
                        <button class="waves-effect waves-light purple darken-3 white-text btn-flat upServSubmitBtn"
                                type="submit"
                                value="Submit">CREATE
                        </button>
                    </div>
                </form>
            </div>
        </c:forEach>
        <!--  START VIEW PRODUCT -->

        <div id="upServAddCatModal" class="modal upServAddCatModal" style="margin-top: 30px;">
            <form id="upServAddCatForm" class="upServAddCatForm">
                <div class="modal-content">
                    <h4>Add Another Category</h4>
                    <div class="row">
                        <div class="col s12">
                            <div class="upservcat center input-field col s12 red darken-4 white-text z-depth-barts">

                            </div>
                            <div class="input-field col s8 offset-s2">

                                <select id="upServAddCategorySelect" class="browser-default" size="10"
                                        style="height: 120px !important; border-bottom: none !important;">
                                    <c:forEach items="${serviceCategory}" var="service">
                                        <option value="${service}">${product}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                                <input type="text" class="validate upServAddCatName"
                                       id="upServAddCatName" name="upServAddCatName"
                                       placeholder="New Category" required/>
                                <label for="upServAddCatName" class="active"><b>Category</b></label>
                            </div>
                            <div class="input-field col s8 offset-s2 center">
                                <a id="updateServAddCatBtn"
                                   class="waves-effect waves-light purple darken-3 btn-flat white-text">SAVE
                                </a>
                                <button type="reset" value="Reset" id="upServAddProdCatCancel"
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
            <%
            Product prod = (Product)pageContext.getAttribute("product");
            String prodID = String.valueOf(prod.getIntProductID());
            %>
            <div id="del<%=prodID%>" class="modal" style="width: 30% !important;">
                <form action="deactivateItem" method="get">
                    <div class="container">
                        <div class="modal-content">
                            <div class="row">
                                <h5 class="red-text">Warning!</h5>
                                <p class="center">Are you sure?</p>
                            </div>
                        </div>
                        <div class="col s12 center" style="margin-bottom: 30px;">
                            <input type="hidden" name="intItemID" value="${product.intProductID}">
                            <button class="waves-effect waves-light purple btn-flat white-text">YES</button>
                            <a href="#"
                               class="modal-action modal-close waves-effect waves-purple transparent btn-flat black-text">NO</a>
                        </div>
                    </div>
                </form>
            </div>
        </c:forEach>

        <c:forEach items="${serviceList}" var="service">
            <%
            Service serv = (Service)pageContext.getAttribute("service");
            String servID = String.valueOf(serv.getIntServiceID());
            %>
            <div id="del<%=servID%>" class="modal" style="width: 30% !important;">
                <form method="get" action="deactivateService">
                    <div class="container">
                        <div class="modal-content">
                            <div class="row">
                                <h5 class="red-text">Warning!</h5>
                                <p class="center">Are you sure?</p>
                            </div>
                        </div>
                        <div class="col s12 center" style="margin-bottom: 30px;">
                            <input type="hidden" name="intItemID" value="${service.intServiceID}">
                            <button class="waves-effect waves-light purple btn-flat white-text">YES</button>
                            <a href="#"
                               class="modal-action modal-close waves-effect waves-purple transparent btn-flat black-text">NO</a>
                        </div>
                    </div>
                </form>
            </div>
        </c:forEach>
        <!--                     <div class="aside aside2 z-depth-barts">

        </div> -->
    </div>
</div>
<!-- </div> -->