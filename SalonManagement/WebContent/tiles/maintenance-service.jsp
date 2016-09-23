<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Product" %>
<%@ page import="com.gss.model.Service" %>

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Service Maintenance</h3>
            <a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"
               href="#createService" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>
            <a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-viewall btn-flat purple darken-2 left white-text"
               href="#serviceArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="servSearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                   required>
                            <label for="servSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <table id="servtbl"
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
                <tfoot style="border: 0.5px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Category</th>
                    <th class="dt-head-left">Detail</th>
                    <th class="dt-head-right">Price</th>
                    <th class="dt-head-center">Actions</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${serviceList}" var="service">

                    <c:set var="price" scope="session"
                           value="${(service.dblServicePrice * 0) + service.dblServicePrice}"/>
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
                            ${service.stringPrice}
                        </td>
                        <td style="padding:0; margin: 0;" class="dt-body-center">
                            <a class="waves-effect waves-purple modal-trigger btn-flat transparent black-text"
                               href="#view${service.intServiceID}"
                               style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">visibility</i>
                            </a>
                            <a class="waves-effect waves-purple modal-trigger btn-flat transparent black-text editbtn"
                               href="#serv${service.intServiceID}"
                               onclick="showPhpFormat(${service.intServiceID}); checkServiceNameAvailability(${service.intServiceID});"
                               style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <button class="servdeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                    id="${service.intServiceID}"
                                    style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">delete</i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>

        <!-- ARCHIVE BEGIN -->
        <div id="serviceArchive" class="modal modal-fixed-footer">
            <div class="modal-content">
                <h4 class="grey-text center text-darken-1">Archive</h4>
                <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                    <div class="nav-wrapper col s4">
                        <form>
                            <div class="input-field">
                                <input id="servArchiveSearch" placeholder="Search" class="grey-text text-darken-4"
                                       type="search">
                                <label for="servArchiveSearch"><i
                                        class="material-icons grey-text text-darken-4">search</i></label>
                            </div>
                        </form>
                    </div>
                </nav>
                <table id="servArchiveTbl"
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
                    <tfoot style="border: 0.5px solid #bdbdbd;">
                    <tr>
                        <th class="dt-head-left">Name</th>
                        <th class="dt-head-left">Category</th>
                        <th class="dt-head-left">Detail</th>
                        <th class="dt-head-right">Price</th>
                        <th class="dt-head-center">Actions</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <tr>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left"></td>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left"></td>
                        <td style="padding-left: 10px; margin: 0;" class="dt-body-left"></td>
                        <td style="padding-right: 10px; margin: 0;" class="dt-body-right servPrice"></td>
                        <td style="padding:0; margin: 0;" class="dt-body-center">
                            <button class="waves-effect waves-light btn-flat green darken-3 white-text"
                                    id="deliveryArchiveBtn" style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                    title="Restore">
                                <i class="material-icons">restore</i>
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Close</a>
            </div>
        </div>
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
                        <div class="crserverrorcontainer center input-field col s12 card red white-text z-depth-barts"
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
                                           value="Service"/>
                                    <input type="text" name="strItemName" id="crServiceName" required
                                           placeholder="Service Name"/>
                                    <label for="crServiceName" class="active"><b>Name</b>
                                        <i class="material-icons red-text tiny">error_outline</i>
                                        <span id="crServiceNameError" class="red-text">Already existing!</span>
                                    </label>
                                </div>
                                <div class="input-field col s12">
                                    <textarea id="crServiceDetails" name="strItemDetails"
                                              class="materialize-textarea" placeholder="Details"
                                              maxlength="25" style="margin-top: -10px !important;"></textarea>
                                    <label for="crServiceDetails" class="active">
                                        <b>Details</b>
                                    </label>
                                </div>
                                <div class="input-field col s12">
                                    <select id="crServiceType" name="intServiceType" class="required"
                                            required style="margin-bottom: -15px !important;" multiple>
                                        <option value="default" disabled selected>Choose...</option>
                                        <option value="1">Walk In</option>
                                        <option value="2">Home Service</option>
                                        <option value="3">Event</option>
                                    </select>
                                    <label for="crServiceType"><b>Type</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s8">
                                    <select id="crServiceCategory" name="strItemCategory" class="required"
                                            required style="margin-bottom: -15px !important;">
                                        <option value="default" disabled selected>Choose...</option>
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
                                           id="crServicePrice" name="price" required placeholder="P9.99"/>
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
                            style="opacity: 0.3"
                            disabled
                            id="crServiceBtn"
                            value="Submit">CREATE
                    </button>
                </div>
            </form>
        </div>

        <!-- Modal Structure -->

        <!-- add category BEGIN -->
        <div id="crServAddCateModal" class="modal" style="margin-top: 30px;">
            <form id="createServAddCatForm">
                <div class="modal-content">
                    <h4>Add Another Category</h4>
                    <div class="row">
                        <div class="col s12">
                            <div class="crservcat center input-field col s12 card red white-text z-depth-barts">
                            </div>
                            <div id="addCreateServiceCategory"
                                 class="center input-field col s12 card red white-text z-depth-barts">
                            </div>
                            <div class="input-field col s8 offset-s2">
                                <select id="createServAddCategorySelect" class="browser-default" size="10"
                                        style="height: 120px !important; border-bottom: none !important;">
                                </select>
                            </div>
                            <div class="input-field col s6 offset-s2" style="margin-top: 20px;">
                                <input type="text" class="validate"
                                       id="crServAddCatName" name="crServAddCatName"
                                       placeholder="New Category" required/>
                                <label for="crServAddCatName" class="active"><b>Category</b></label>
                            </div>
                            <div class="input-field col s2">
                                <a id="crDeletePosition" onclick="removeCreateServiceCategory();"
                                   class="modal-action waves-effect waves-light red darken-3 btn-flat white-text">
                                    <i class="material-icons">delete</i>
                                </a>
                            </div>
                            <div class="input-field col s12 center">
                                <a id="createServAddCatBtn" onclick="addServiceCategory();"
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
            <c:set var="price" scope="session" value="${service.stringPrice}"/>
            <div id="serv${service.intServiceID}" class="servUpdateModal modal modal-fixed-footer">
                <form class="col s12 updateservForm" method="post" id="updateservForm" action="updateItem"
                      enctype="multipart/form-data">
                    <div class="modal-content">
                        <!-- <div class="container"> -->
                        <div class="wrapper">
                            <div class="input-field col s12" style="margin-top: -5px !important;">
                                <h4 class="grey-text center text-darken-1">Update Service<a id="btnServUpExit"
                                                                                            type="reset" value="Reset"
                                                                                            class="btnServUpExit modal-action modal-close"><i
                                        class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                            </div>
                            <div class="upserverrorcontainer center input-field col s12 card red white-text z-depth-barts"
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
                                        <input type="hidden" name="strItemCate" value="service">
                                        <input value="${service.strServiceName}" type="text" name="strItemName"
                                               id="upServName${service.intServiceID}" required
                                               placeholder="Product Name"/>
                                        <label for="upServName${service.intServiceID}" class="active"><b>Name</b>
                                            <i class="material-icons red-text tiny">error_outline</i>
                                            <span id="upServiceNameError${service.intServiceID}"
                                                  style="display: none;"
                                                  class="red-text">Already existing!</span>
                                        </label>
                                    </div>
                                    <div class="input-field col s12">
                                    <textarea id="upServDetails" name="strItemDetails"
                                              class="materialize-textarea" placeholder="Details"
                                              style="margin-top: -10px !important;">${service.strServiceDesc}</textarea>
                                        <label for="upServDetails" class="active">
                                            <b>Details</b>
                                        </label>
                                    </div>
                                    <div class="input-field col s12">
                                    <select id="crServiceType" name="intServiceType" class="required"
                                            required style="margin-bottom: -15px !important;" multiple>
										
										<c:set var="walkin" scope="session" value=""/>
										<c:set var="home" scope="session" value=""/>
										<c:set var="event" scope="session" value=""/>
										
										<c:if test="${service.serviceType == 1}">
											<c:set var="walkin" scope="session" value="selected"/>
										</c:if>
										<c:if test="${service.serviceType == 2}">
											<c:set var="home" scope="session" value="selected"/>
										</c:if>
										<c:if test="${service.serviceType == 3}">
											<c:set var="event" scope="session" value="selected"/>
										</c:if>
										<c:if test="${service.serviceType == 4}">
											<c:set var="walkin" scope="session" value="selected"/>
											<c:set var="home" scope="session" value="selected"/>
										</c:if>
										<c:if test="${service.serviceType == 5}">
											<c:set var="walkin" scope="session" value="selected"/>
											<c:set var="event" scope="session" value="selected"/>
										</c:if>
										<c:if test="${service.serviceType == 6}">
											<c:set var="home" scope="session" value="selected"/>
											<c:set var="event" scope="session" value="selected"/>
										</c:if>
										<c:if test="${service.serviceType == 7}">
											<c:set var="walkin" scope="session" value="selected"/>
											<c:set var="home" scope="session" value="selected"/>
											<c:set var="event" scope="session" value="selected"/>
										</c:if>
										  
                                        <option value="default" disabled selected>Choose...</option>
                                        <option value="1" <c:out value="${walkin}"/>>Walk In</option>
                                        <option value="2" <c:out value="${home}"/>>Home Service</option>
                                        <option value="3" <c:out value="${event}"/>>Event</option>
                                    </select>
                                    <label for="crServiceType"><b>Type</b><i
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
                                                <option value="${service}"
                                                <%out.println(selectedService);%>>${service}</option>
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
                                    	<c:set var="price" scope="session" value="${price}"/>
                                        <input type="text" value="<c:out value='${price}'/>"
                                               class="validate right-align servPrice priceField"
                                               id="upServPrice${service.intServiceID}" name="price" required
                                               placeholder="P9.99"/>
                                        <label for="upServPrice${service.intServiceID}" class="active"><b>Price</b><i
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
                                id="upServSubmitBtn${service.intServiceID}"
                                type="submit"
                                value="Submit">UPDATE
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
                            <div class="upservcat center input-field col s12 card red white-text z-depth-barts">
                            </div>
                            <div id="addUpdateServiceCategory"
                                 class="addUpdateServiceCategory center input-field col s12 card red white-text z-depth-barts">
                            </div>
                            <div class="input-field col s8 offset-s2">
                                <select id="upServAddCategorySelect" class="browser-default upServAddCategorySelect"
                                        size="10"
                                        style="height: 120px !important; border-bottom: none !important;">
                                </select>
                            </div>
                            <div class="input-field col s6 offset-s2" style="margin-top: 20px;">
                                <input type="text" class="validate upServAddCatName"
                                       id="upServAddCatName" name="upServAddCatName"
                                       placeholder="New Category" required/>
                                <label for="upServAddCatName" class="active"><b>Category</b></label>
                            </div>
                            <div class="input-field col s2">
                                <a id="upDeletePosition" onclick="removeUpdateServiceCategory();"
                                   class="modal-action waves-effect waves-light red darken-3 btn-flat white-text">
                                    <i class="material-icons">delete</i>
                                </a>
                            </div>
                            <div class="input-field col s12 center">
                                <a id="updateServAddCatBtn" onclick="addUpdateServiceCategory();"
                                   class="updateServAddCatBtn waves-effect waves-light purple darken-3 btn-flat white-text">SAVE
                                </a>
                                <button type="reset" value="Reset" id="upServAddProdCatCancel"
                                        class="upServAddProdCatCancel modal-close waves-effect waves-purple transparent btn-flat white">
                                    CANCEL
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <c:forEach items="${serviceList}" var="service">
            <%! String viewServiceID = null; %>
            <% Service servID = (Service)pageContext.getAttribute("service");
            viewServiceID = String.valueOf(servID.getIntServiceID());
            String serviceCate = servID.getStrServiceCategory();
            double price = servID.getDblServicePrice();
            String selectedService = "";
            %>
            <c:set var="price" scope="session" value="${(service.dblServicePrice * 0) + service.dblServicePrice}"/>
            <div id="view${service.intServiceID}"
                 class="transparent servUpdateModal z-depth-0 modal modal-fixed-footer">
                <form class="col s12" method="post" id="viewServForm"
                      enctype="multipart/form-data">
                    <div class="modal-content">
                        <!-- <div class="container"> -->
                        <div class="wrapper">
                            <div class="input-field col s12" style="margin-top: -5px !important;">
                                <h3 class="center grey-text text-lighten-2">View Service</h3>
                            </div>
                            <div class="aside aside1 z-depth-0">
                                <div class="row">
                                    <!--<div class="input-field col s12">-->
                                    <!--<img name="image" id="employeeimg" class="circle"-->
                                    <!--style="width: 200px; height: 200px;" src="./img/anon.jpg" alt=""/>-->
                                    <!--</div>-->
                                    <div class="col s12">

                                        <img name="prodsvcCreate" id="viewServImage"
                                             style="width: 200px; height: 200px; margin-top: 20px;"
                                             src="<s:url action='getImage'><s:param name='ImageID'><%=viewServiceID%></s:param><s:param name='type'>service</s:param></s:url>"
                                             alt="${service.strServiceName}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="aside aside2 z-depth-0">
                                <div class="row">
                                    <div class="input-field col s12" style="margin-top: 25px !important;">
                                        <input type="hidden" name="intItemID" value="${service.intServiceID}">
                                        <input type="hidden" name="strItemCate" value="service">
                                        <input value="${service.strServiceName}" type="text" name="strItemName"
                                               id="viewServName" placeholder="Product Name" disabled
                                               class="white-text"/>
                                        <label for="viewServName" class="active purple-text text-lighten-2"><b>Name</b></label>
                                    </div>
                                    <div class="input-field col s8">
                                           <c:if test="${service.serviceType == 1}">
                                           		<c:set var="type" scope="session" value="Walk In"/>
                                           </c:if>
                                           <c:if test="${service.serviceType == 1}">
                                           		<c:set var="type" scope="session" value="Home Service"/>
                                           </c:if>
                                           <c:if test="${service.serviceType == 2}">
                                           		<c:set var="type" scope="session" value="Event"/>
                                           </c:if>
                                           <c:if test="${service.serviceType == 3}">
                                           		<c:set var="type" scope="session" value="Walk In, Home Service"/>
                                           </c:if>
                                           <c:if test="${service.serviceType == 4}">
                                           		<c:set var="type" scope="session" value="Walk In, Event"/>
                                           </c:if>
                                           <c:if test="${service.serviceType == 5}">
                                           		<c:set var="type" scope="session" value="Home Service, Event"/>
                                           </c:if>
                                           <c:if test="${service.serviceType == 6}">
                                           		<c:set var="type" scope="session" value="Walk In, Home Service, Event"/>
                                           </c:if>
                                        <input value="${type}" type="text" name="strItemName"
                                               id="viewServCategory" disabled
                                               class="white-text"/>
                                        <label for="viewServCategory" class="purple-text text-lighten-2"><b>Type</b></label>
                                    </div>
                                    <div class="input-field col s8">
                                        <c:forEach items="${serviceCategory}" var="service">
                                            <%
                                            String cate3 = (String)pageContext.getAttribute("service");
                                           
                                            if(serviceCate.equals(cate3)){
                                            	
                                            	selectedService+= cate3;
                                            }
                                            %>
                                        </c:forEach>
                                        <input value="<%=selectedService%>" type="text" name="strItemName"
                                               id="viewServCategory" disabled
                                               class="white-text"/>
                                        <label for="viewServCategory" class="purple-text text-lighten-2"><b>Category</b></label>
                                    </div>
                                    <div class="input-field col s6" style="margin-bottom: -15px !important;">
                                        <input type="text" value="<c:out value='${price}'/>"
                                               class="white-text servPrice" disabled
                                               id="viewServPrice" name="price" placeholder="P9.99"/>
                                        <label for="viewServPrice"
                                               class="active purple-text text-lighten-2"><b>Price</b></label>
                                    </div>
                                    <div class="input-field col s12">
                                    <textarea id="viewServDetails" name="strItemDetails" disabled
                                              class="materialize-textarea white-text" placeholder="Details"
                                              style="margin-top: -10px !important;">${service.strServiceDesc}</textarea>
                                        <label for="viewServDetails" class="active purple-text text-lighten-2"><b>Details</b></label>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="modal-footer transparent">
                        <a class="modal-action modal-close waves-effect waves-purple btn-flat purple-text text-lighten-1"
                           style="border: 3px solid #ab47bc !important;">
                            <b>CLOSE</b>
                        </a>
                    </div>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
<!-- </div> -->