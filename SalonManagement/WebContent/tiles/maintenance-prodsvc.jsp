  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page import="com.gss.model.Product" %>
  <%@ page import="com.gss.model.Service" %>
  
  <body class="purple lighten-5">
  <div class="wrapper">
            
        <article>
        <!-- <div class="container"> -->
          <div class="wrapper">
<!--                     <div class="aside aside1 z-depth-barts">
                    
                    </div> -->

                    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
                        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                        <h3 class="grey-text text-darken-1">Product & Service Maintenance</h3>
                        <a class="waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text tooltipped" href="#create" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Create"><i class="material-icons">add</i></a>
                        <a class="waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text tooltipped" href="#empArchive" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Archive"><i class="material-icons">archive</i></a>

                        <h4>Services</h4>
                        <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                <thead>
                                    <tr>
                                        <th><center>Name</center></th>
                                        <th><center>Category</center></th>
                                        <th><center>Price</center></th>
                                        <th><center>Actions</center></th>
                                    </tr>
                                </thead>
                                <tbody>
                                  <c:forEach items="${productList}" var="product">
                                    <%! String string = null; %>
                                    <% Product prod = (Product)pageContext.getAttribute("product");
                                       string = String.valueOf(prod.getIntProductID());
                                    %>
                                    <tr>
                                        <td style="padding:0; margin: 0;"><center>${product.strProductName}</center></td>
                                        <td style="padding:0; margin: 0;"><center>Product</center></td>
                                        <td style="padding:0; margin: 0;"><center>Php ${product.dblProductPrice}</center></td>
                                        <td style="padding:0; margin: 0;">
                                        <a data-delay="30" data-position="bottom" data-tooltip="View" class="tooltipped waves-effect waves-purple modal-viewall btn-flat transparent black-text" href="#viewprod<%=string%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                        <i class="material-icons">visibility</i>
                                        </a>
                                        <a class="waves-effect waves-purple modal-trigger btn-flat transparent black-text tooltipped" href="#prod<%=string%>" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Update">
                                          <i class="material-icons">edit</i>
                                        </a>
                                        <a class="waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4 tooltipped" href="#del<%=string%>" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Deactivate">
                                          <i class="material-icons">delete</i>
                                        </a>
                                        </td>
                                    </tr>
                                  </c:forEach>
                                  


                                    
                                </tbody>
                            </table>

                            <a class="waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text tooltipped" href="#createProduct" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Create"><i class="material-icons">add</i></a>
                            <a class="waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text tooltipped" href="#empArchive" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Archive"><i class="material-icons">archive</i></a>

                            <h4>Products</h4>

                            <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                              <thead>
                                  <tr>
                                      <th><center>Name</center></th>
                                      <th><center>Category</center></th>
                                      <th><center>Price</center></th>
                                      <th><center>Actions</center></th>
                                  </tr>
                              </thead>

                              <tbody>
                                  <c:forEach items="${serviceList}" var="service">
                                    <%! String strService =null; %>
                                    <% Service serv = (Service)pageContext.getAttribute("service");
                                       strService = String.valueOf(serv.getIntServiceID());
                                    %>
                                    <tr>
                                        <td style="padding:0; margin: 0;"><center>${service.strServiceName}</center></td>
                                        <td style="padding:0; margin: 0;"><center>Service</center></td>
                                        <td style="padding:0; margin: 0;"><center>Php ${service.dblServicePrice}</center></td>
                                        <td style="padding:0; margin: 0;">
                                        <a data-delay="30" data-position="bottom" data-tooltip="View" class="tooltipped waves-effect waves-purple modal-viewall btn-flat transparent black-text" href="#viewserv<%=strService%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                        <i class="material-icons">visibility</i>
                                        </a>
                                        <a class="waves-effect waves-light modal-trigger btn-flat transparent black-text tooltipped" href="#serv<%=strService%>" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Update">
                                          <i class="material-icons">edit</i>
                                        </a>
                                        <a class="waves-effect waves-light modal-trigger btn-flat transparent red-text text-accent-4 tooltipped" href="#del<%=strService%>" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Deactivate">
                                          <i class="material-icons">delete</i>
                                        </a>
                                        </td>
                                    </tr>
                                  </c:forEach>
                              </tbody>
                            </table>

                      </div>

                      <!-- ARCHIVE BEGIN -->
                      <div id="empArchive" class="modal modal-fixed-footer" style="width: 80% !important; height: 86% !important; max-height: 100% !important;">
                          <div class="modal-content">
                            <div class="col s12">
                                  <h4 class="grey-text text-darken-1">Archive</h4>
                                  <table id="prodsvcArchive" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                                                  <thead>
                                                                      <tr>
                                                                          <th><center>Name</center></th>
                                                                          <th><center>Category</center></th>
                                                                          <th><center>Price</center></th>
                                                                          <th><center>Action</center></th>
                                                                      </tr>
                                                                  </thead>
                                                                  <tbody>
                                                                    <c:forEach items="${productList}" var="product">
                                                                      <%! String stringArch = null; %>
                                                                      <% Product prod = (Product)pageContext.getAttribute("product");
                                                                         stringArch = String.valueOf(prod.getIntProductID());
                                                                      %>
                                                                      <tr>
                                                                          <td style="padding:0; margin: 0;"><center>${product.strProductName}</center></td>
                                                                          <td style="padding:0; margin: 0;"><center>Product</center></td>
                                                                          <td style="padding:0; margin: 0;"><center>Php ${product.dblProductPrice}</center></td>
                                                                          <td style="padding:0; margin: 0;">
                                                                          <a class="waves-effect waves-purple modal-trigger btn-flat transparent green-text text-accent-4 tooltipped" href="#del<%=stringArch%>" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Activate">
                                                                            <i class="material-icons">check</i>
                                                                          </a>
                                                                          </td>
                                                                      </tr>
                                                                    </c:forEach>
                                                                    <c:forEach items="${serviceList}" var="service">
                                                                      <%! String strServiceArch =null; %>
                                                                      <% Service serv = (Service)pageContext.getAttribute("service");
                                                                         strServiceArch = String.valueOf(serv.getIntServiceID());
                                                                      %>
                                                                      <tr>
                                                                          <td style="padding:0; margin: 0;"><center>${service.strServiceName}</center></td>
                                                                          <td style="padding:0; margin: 0;"><center>Service</center></td>
                                                                          <td style="padding:0; margin: 0;"><center>Php ${service.dblServicePrice}</center></td>
                                                                          <td style="padding:0; margin: 0;">
                                                                          <a class="waves-effect waves-light modal-trigger btn-flat transparent green-text text-accent-4 tooltipped" href="#del<%=strServiceArch%>" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Activate">
                                                                            <i class="material-icons">check</i>
                                                                          </a>
                                                                          </td>
                                                                      </tr>
                                                                    </c:forEach>


                                                                      
                                                                  </tbody>
                                                              </table>
                            </div>
                          </div>
                          <div class="modal-footer">
                            <a href="#!" class=" modal-action modal-close waves-effect waves-purple btn-flat">BACK</a>
                          </div>
                        </div>
                      <!-- ARCHIVE END -->

                      <!-- Modal Structure -->
                        <div id="create" class="modal modal-fixed-footer">
                        <form class="col s12" method="post" id="createProdSvcForm" action="createProdServ" enctype="multipart/form-data">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                            <div class="wrapper">
                              <div class="input-field col s12">
                                  <h4 class="grey-text text-darken-1">Create Service</h4>
                              </div>

                              <div class="aside aside1 z-depth-0">
                                <div class="row">
                              <!-- 1st aside -->
                                  <div class="col s12">
                                      <img name="prodsvcCreate" id="prodsvcCreate" style="width: 200px; height: 200px; margin-top: 20px;" src="./img/packIcon.png" alt=""/>
                                  </div>
                                  <div class="input-field col s12">
                                      <div class="file-field">
                                            <div class="btn purple darken-3">
                                              <span class=""><i class="material-icons">add_a_photo</i></span>
                                              <input name="upload" type="file" accept="image/.jpg, image/.png" onchange="loadProdSvc(event)">
                                            </div>
                                            <div class="file-path-wrapper">
                                              <input value="image" class="file-path validate" type="text">
                                            </div>
                                        </div>
                                  </div>
                                </div>
                              </div>

                              <div class="aside aside2 z-depth-0">
                                <div class="row">
                              <!-- 2nd aside -->
                                    <div class="col s12">
                                         <label class="red-text left">(*) Indicates required field</label>
                                    </div>
                                    
                                    <div class="input-field col s12" style="margin-top: 28px !important;">
                                      <input type="text" class="validate tooltipped specialprodsvc noSpace" required id="prodsvcName" name="strItemName" placeholder="Product/Service Name" data-position="bottom" data-delay="30" data-tooltip="Ex: Item 1<br/> ( At least 3 or more characters )" pattern="^[a-zA-Z0-9\-\s]{3,}$" maxlength="15">
                                      <label for="prodsvcName">Name<span class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s12">
                                      <textarea id="prodsvcDetail" name="strItemDetails" class="materialize-textarea tooltipped noSpace" placeholder="Details" data-position="bottom" data-delay="30" data-tooltip="Ex: This item is affordable.<br/> ( At least 5 or more characters )" pattern="^[a-zA-Z0-9.,`-\-\s]{5,}$" maxlength="25"></textarea>
                                      <label for="prodsvcDetail" class="active">Details</label>
                                    </div>
                                    <div class="input-field col s8">
                                        <select class="browser-default" id="createPSCategory" name="strItemCategory" required>
                                            <option value="" disabled selected> </option>
                                            <c:forEach items="${productCategory}" var="product">
                                              <option value="${product}">${product}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="createPSCategory" class="active">Category<span class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s4">
                                      <button data-target="addCategory" class="waves-effect waves-light btn-flat modal-category purple lighten-1"><i class="material-icons white-text">add</i></button>
                                    </div>
                                    <div class="input-field col s6" style="margin-top: 28px !important;">
                                        <input type="text" class="validate right-align tooltipped amountFormat" id="prodsvcPrice" name="dblItemPrice" required placeholder="999.99" data-position="bottom" data-delay="30" data-tooltip="Ex: 99.99<br/>( Numbers only )">
                                        <label for="prodsvcPrice" class="active">Price<span class="red-text">*</span></label>
                                    </div>
                                </div>
                              </div>
                            </div>


                            
                          </div>
                          <div class="modal-footer">
                              <button type="reset" value="Reset" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</button>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">CREATE</button>
                          </div>
                          </form>
                    </div>

                     <!-- Modal Structure -->
                        <div id="createProduct" class="modal modal-fixed-footer">
                        <form class="col s12" method="post" id="createProdSvcForm" action="createProdServ" enctype="multipart/form-data">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                            <div class="wrapper">
                              <div class="input-field col s12">
                                  <h4 class="grey-text text-darken-1">Create Product</h4>
                              </div>

                              <div class="aside aside1 z-depth-0">
                                <div class="row">
                              <!-- 1st aside -->
                                  <div class="col s12">
                                      <img name="prodsvcCreate" id="prodsvcCreate" style="width: 200px; height: 200px; margin-top: 20px;" src="./img/packIcon.png" alt=""/>
                                  </div>
                                  <div class="input-field col s12">
                                      <div class="file-field">
                                            <div class="btn purple darken-3">
                                              <span class=""><i class="material-icons">add_a_photo</i></span>
                                              <input name="upload" type="file" accept="image/.jpg, image/.png" onchange="loadProdSvc(event)">
                                            </div>
                                            <div class="file-path-wrapper">
                                              <input value="image" class="file-path validate" type="text">
                                            </div>
                                        </div>
                                  </div>
                                </div>
                              </div>

                              <div class="aside aside2 z-depth-0">
                                <div class="row">
                              <!-- 2nd aside -->
                                    <div class="col s12">
                                         <label class="red-text left">(*) Indicates required field</label>
                                    </div>
                                    <div class="input-field col s12">
                                      <select name="strItemCate" class="browser-default" required id="strItemCate">
                                        <option value="" disabled="disabled" selected></option>
                                        <option value="service">For sale</option>
                                        <option value="product">Consumables</option>
                                      </select>
                                      <label for="strItemCate" class="active">Availablity<span class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 28px !important;">
                                      <input type="text" class="validate tooltipped specialprodsvc noSpace" required id="prodsvcName" name="strItemName" placeholder="Product/Service Name" data-position="bottom" data-delay="30" data-tooltip="Ex: Item 1<br/> ( At least 3 or more characters )" pattern="^[a-zA-Z0-9\-\s]{3,}$" maxlength="15">
                                      <label for="prodsvcName">Name<span class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s12">
                                      <textarea id="prodsvcDetail" name="strItemDetails" class="materialize-textarea tooltipped noSpace" placeholder="Details" data-position="bottom" data-delay="30" data-tooltip="Ex: This item is affordable.<br/> ( At least 5 or more characters )" pattern="^[a-zA-Z0-9.,`-\-\s]{5,}$" maxlength="25"></textarea>
                                      <label for="prodsvcDetail" class="active">Details</label>
                                    </div>
                                    <div class="input-field col s8">
                                        <select class="browser-default" id="createPSCategory" name="strItemCategory" required>
                                            <option value="" disabled selected> </option>
                                            <c:forEach items="${productCategory}" var="product">
                                              <option value="${product}">${product}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="createPSCategory" class="active">Category<span class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s4">
                                      <button data-target="addCategory" class="waves-effect waves-light btn-flat modal-category purple lighten-1"><i class="material-icons white-text">add</i></button>
                                    </div>
                                    <div class="input-field col s6" style="margin-top: 28px !important;">
                                        <input type="text" class="validate right-align tooltipped amountFormat" id="prodsvcPrice" name="dblItemPrice" required placeholder="999.99" data-position="bottom" data-delay="30" data-tooltip="Ex: 99.99<br/>( Numbers only )">
                                        <label for="prodsvcPrice" class="active">Price<span class="red-text">*</span></label>
                                    </div>
                                </div>
                              </div>
                            </div>


                            
                          </div>
                          <div class="modal-footer">
                              <button type="reset" value="Reset" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</button>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">CREATE</button>
                          </div>
                          </form>
                    </div>

                    <!-- add category BEGIN -->
                          <div id="addCategory" class="modal" style="margin-top: 30px;">
                            <form id="createAddCat">
                              <div class="modal-content">
                                <h4>Add Another Category</h4>
                                <div class="row">
                                  <div class="col s12">
                                    <div class="input-field col s8 offset-s2">
                                      <select id="createAddCategorySelect" class="browser-default" size="10">
                                        <c:forEach items="${productCategory}" var="product">
                                          <option value="${product}">${product}</option>
                                        </c:forEach>
                                      </select>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                                      <input type="text" class="validate tooltipped noSpace specialoption" id="createAddCategoryName" name="createAddCategoryName" placeholder="New Category" data-position="bottom" data-delay="30" data-tooltip="Ex: Hair Color<br/>( At least 3 or more characters )" pattern="^[a-zA-Z0-9-\-\s]{3,}$" required/>
                                      <label for="createAddCategoryName" class="active">Category</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2 center">
                                      <button id="createAddCatBtn" class="waves-effect waves-light purple darken-3 btn-flat white-text">SAVE</button>
                                      <button type="reset" value="Reset" class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL</button>
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
                        <div id="prod<%=strProdID%>" class="modal modal-fixed-footer" style="width: 60% !important; height: 80% !important; max-height: 100% !important;">
                        <form class="col s12 updateProdForm" id="updateProdForm" method="get" action="updateItem" enctype="multipart/form-data">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                            <div class="wrapper">
                              <div class="input-field col s12">
                                  <h4 class="grey-text text-darken-1">Update Product</h4>
                              </div>

                              <div class="aside aside1 z-depth-0">
                                <div class="row">
                              <!-- 1st aside -->
                                  <div class="col s12">
                                  
                                      <img name="updateProdImage" id="updateProdImage" style="width: 200px; height: 200px; margin-top: 20px;" src="<s:url action='getImage'><s:param name='ImageID'><%=strProdID%></s:param><s:param name='type'>product</s:param></s:url>" alt="${product.strProductName}"/>
                                  </div>
                                  <div class="input-field col s12">
                                      <div class="file-field">
                                            <div class="btn purple darken-3">
                                              <span class=""><i class="material-icons">add_a_photo</i></span>
                                              <input name="upload" type="file" accept="image/.jpg, image/.png" onchange="updateProdImg(event)">
                                            </div>
                                            <div class="file-path-wrapper">
                                              <input value="image" name="imageName" class="file-path validate" type="text">
                                            </div>
                                        </div>
                                  </div>
                                </div>
                              </div>

                              <div class="aside aside2 z-depth-0">
                                <div class="row">
                              <!-- 2nd aside -->
                                    <div class="input-field col s12">
                                    	<input type="hidden" name="intItemID" value="<%=strProdID%>">
                                      <select name="strItemCate" class="browser-default" id="strItemCate">
                                        <option value="" disabled="disabled" selected></option>
                                        <option value="service">Service</option>
                                        <option value="product" selected>Product</option>
                                      </select>
                                      <label for="strItemCate" class="active">Type</label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 28px !important;">
                                      <input value="${product.strProductName}" type="text" class="validate tooltipped specialprodsvc noSpace" id="prodsvcName" name="strItemName" placeholder="Product/Service Name" data-position="bottom" data-delay="30" data-tooltip="${product.strProductName}" pattern="^[a-zA-Z0-9\-\s]{3,}$" maxlength="15">
                                      <label for="prodsvcName">Name</label>
                                    </div>
                                    <div class="input-field col s12">
                                      <textarea id="prodsvcDetail" name="strItemDetails" class="materialize-textarea tooltipped noSpace" placeholder="Details" data-position="bottom" data-delay="30" data-tooltip="${product.strProductDesc}" pattern="^[a-zA-Z0-9.,`-\-\s]{5,}$" maxlength="25" style="margin-top: -10px;">${product.strProductDesc}</textarea>
                                      <label for="prodsvcDetail" class="active">Details</label>
                                    </div>
                                    <div class="input-field col s8">
                                        <select class="browser-default updateProdCategory" id="updateProdCategory" name="strItemCategory" required>
                                            <option value="" disabled selected> </option>
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
                                              <option value="${cate}" <%out.println(selectedProduct);%>>${cate}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="createPSCategory" class="active">Category</label>
                                    </div>
                                    <div class="input-field col s4">
                                      <button data-target="addUpdateProdCategory" class="waves-effect waves-light btn-flat modal-updateCategory purple lighten-1"><i class="material-icons white-text">add</i></button>
                                    </div>
                                    <div class="input-field col s6" style="margin-top: 28px !important;">
                                        <input value="${product.dblProductPrice}" type="text" class="validate right-align tooltipped specialprice noSpace amountFormat" id="updateprodPrice" name="dblItemPrice" placeholder="999.99" data-position="bottom" data-delay="30" data-tooltip="${product.dblProductPrice}">
                                        <label for="updateprodPrice" class="active">Price</label>
                                    </div>
                                </div>
                              </div>
                            </div>


                            
                          </div>
                          <div class="modal-footer">
                              <button type="reset" value="Reset" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</button>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">UPDATE</button>
                          </div>
                          </form>
                    </div>
                  </c:forEach>
                    <!--  START VIEW PRODUCT -->
                      <c:forEach items="${productList}" var="product">
                        <%! String viewstrProdID = null; %>
                        <% Product prodID = (Product)pageContext.getAttribute("product");
                           viewstrProdID = String.valueOf(prodID.getIntProductID());
                           String productCate = prodID.getStrProductCategory();
                        %>
                        <div id="viewprod<%=viewstrProdID%>" class="modal modal-fixed-footer" style="width: 60% !important; height: 80% !important; max-height: 100% !important;">
                        <form class="col s12" enctype="multipart/form-data">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                            <div class="wrapper">
                              <div class="input-field col s12">
                                  <h4 class="grey-text text-darken-1">View Product</h4>
                              </div>

                              <div class="aside aside1 z-depth-0">
                                <div class="row">
                              <!-- 1st aside -->
                                  <div class="col s12">
                                      <img name="prodsvcCreate" id="prodsvcCreate" style="width: 200px; height: 200px; margin-top: 20px;" src="<s:url action='getImage'><s:param name='ImageID'><%=viewstrProdID%></s:param><s:param name='type'>product</s:param></s:url>" alt="${product.strProductName}"/>

                                  </div>
                                </div>
                              </div>

                              <div class="aside aside2 z-depth-0">
                                <div class="row">
                              <!-- 2nd aside -->
                                    <div class="input-field col s12">
                                      <select name="strItemCate" class="browser-default" id="strItemCate" disabled="disabled">
                                        <option value="" disabled="disabled" selected></option>
                                        <option value="service">Service</option>
                                        <option value="product" selected>Product</option>
                                      </select>
                                      <label for="strItemCate" class="active">Type</label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 28px !important;">
                                     
                                      <input value="${product.strProductName}" type="text" class="tooltipped" id="prodsvcName" name="strItemName" placeholder="Product/Service Name" data-position="bottom" data-delay="30" data-tooltip="${product.strProductName}" readonly>
                                      <label for="prodsvcName">Name</label>
                                    </div>
                                    <div class="input-field col s12">
                                      <textarea id="prodsvcDetail" name="strItemDetails" class="materialize-textarea tooltipped" placeholder="Details" data-position="bottom" data-delay="30" data-tooltip="${product.strProductDesc}" readonly style="margin-top: -10px;">${product.strProductDesc}</textarea>
                                      <label for="prodsvcDetail" class="active">Details</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <select class="browser-default updateProdCategory" id="updateProdCategory" name="strItemCategory" disabled="disabled">
                                            <option value="" disabled selected> </option>
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
                                              <option value="${cate}" <%out.println(selectedProduct);%>>${cate}</option>
                                            </c:forEach>
                                        </select>
                                        <label for="createPSCategory" class="active">Category</label>
                                    </div>
                                    <div class="input-field col s6" style="margin-top: 28px !important;">
                                        <input value="${product.dblProductPrice}" type="text" class="right-align tooltipped" id="updateprodPrice" name="dblItemPrice" required placeholder="999.99" data-position="bottom" data-delay="30" data-tooltip="${product.dblProductPrice}" readonly>
                                        <label for="updateprodPrice" class="active">Price</label>
                                    </div>
                                </div>
                              </div>
                            </div>


                            
                          </div>
                          <div class="modal-footer">
                              <button class="modal-action modal-close waves-effect waves-purple transparent btn-flat">BACK</button>
                          </div>
                          </form>
                    </div>
                  </c:forEach>
                  <!-- end view product -->

                  <!-- add category BEGIN -->
                          <div id="addUpdateProdCategory" class="modal" style="margin-top: 30px;">
                            <form id="prodAddCatForm" class="prodAddCatForm">
                              <div class="modal-content">
                                <h4>Add Another Category</h4>
                                <div class="row">
                                  <div class="col s12">
                                    <div class="input-field col s8 offset-s2">
                                      <select id="updateAddCatProdSelect" class="browser-default" size="10">
                                        <c:forEach items="${productCategory}" var="product">
                                          <option value="${product}">${product}</option>
                                        </c:forEach>
                                      </select>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                                      <input type="text" class="validate tooltipped specialoption noSpace" id="updateAddCatProdName" name="updateAddCatProdName" placeholder="New Category" data-position="bottom" data-delay="30" data-tooltip="Ex: Hair Color<br/>( At least 3 or more characters )" pattern="^[a-zA-Z0-9-\-\s]{3,}$" required>
                                      <label for="updateAddCatProdName">Category</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2 center">
                                      <button id="updateAddProdCatBtn" class="waves-effect waves-light purple darken-3 btn-flat white-text">SAVE</button>
                                      <button type="reset" value="Reset" class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL</button>
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
                        <div id="serv<%=serviceID%>" class="modal modal-fixed-footer" style="width: 60% !important; height: 80% !important; max-height: 100% !important;">
                        <form class="col s12 updateServForm" method="get" id="updateServForm" action="updateItem" enctype="multipart/form-data">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                            <div class="wrapper">
                              <div class="input-field col s12">
                                  <h4 class="grey-text text-darken-1">Update Service</h4>
                              </div>

                              <div class="aside aside1 z-depth-0">
                                <div class="row">
                              <!-- 1st aside -->
                                  <div class="col s12">

                                      <img name="prodsvcCreate" id="prodsvcCreate" style="width: 200px; height: 200px; margin-top: 20px;" src="<s:url action='getImage'><s:param name='ImageID'><%=serviceID%></s:param><s:param name='type'>service</s:param></s:url>" alt="${service.strServiceName}"/>

                                  <input type="hidden" name="intItemID" value="${service.intServiceID}">
                                  <!-- <img name="updateSvcimg" id="updateSvcimg" style="width: 200px; height: 200px; margin-top: 20px;" src="<s:url action='getImage'><s:param name='ImageID'><%=serviceID%></s:param><s:param name='type'>service</s:param></s:url>" alt="${service.strServiceName}"/> -->

<!-- >>>>>>> 769546141be516a2f7b753e00de41d578b9fe3f6 -->
                                  </div>
                                  <div class="input-field col s12">
                                      <div class="file-field">
                                            <div class="btn purple darken-3">
                                              <span class=""><i class="material-icons">add_a_photo</i></span>
                                              <input name="upload" type="file" accept="image/.jpg, image/.png" onchange="LoadSvcImg(event)"/>
                                            </div>
                                            <div class="file-path-wrapper">
                                              <input value="image" name="imageName" class="file-path validate" type="text">
                                            </div>
                                        </div>
                                  </div>
                                </div>
                              </div>

                              <div class="aside aside2 z-depth-0">
                                <div class="row">
                              <!-- 2nd aside -->
                                    <div class="input-field col s12">
                                        
                                      <select name="strItemCate" class="browser-default" id="strItemCate">
                                        <option value="" disabled="disabled" selected></option>
                                        <option value="service" selected>Service</option>
                                        <option value="product">Product</option>
                                      </select>
                                      <label for="strItemCate" class="active">Type</label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 28px !important;">
                                      <input value="${service.strServiceName}" type="text" class="validate tooltipped specialprodsvc noSpace" id="prodsvcName" name="strItemName" placeholder="Product/Service Name" data-position="bottom" data-delay="30" data-tooltip="${service.strServiceName}" pattern="^[a-zA-Z0-9\-\s]{3,}$" maxlength="15">
                                      <label for="prodsvcName">Name</label>
                                    </div>
                                    <div class="input-field col s12">
                                      <textarea id="prodsvcDetail" name="strItemDetails" class="materialize-textarea tooltipped noSpace" placeholder="Details" data-position="bottom" data-delay="30" data-tooltip="${service.strServiceDesc}" pattern="^[a-zA-Z0-9.,`-\-\s]{5,}$" maxlength="25" style="margin-top: -10px;">${service.strServiceDesc}</textarea>
                                      <label for="prodsvcDetail" class="active">Details</label>
                                    </div>
                                    <div class="input-field col s8">
                                        <select class="browser-default updateServCategory" id="updateServCategory" name="strItemCategory">
                                            <option value="" disabled selected> </option>
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
                                        <label for="updateServCategory" class="active">Category</label>
                                    </div>
                                    <div class="input-field col s4">
                                      <button data-target="addUpdateServCategory" class="waves-effect waves-light btn-flat modal-updateCategory purple lighten-1"><i class="material-icons white-text">add</i></button>
                                    </div>
                                    <div class="input-field col s6" style="margin-top: 28px !important;">
                                        <input type="text" value="<%=price%>" class="validate right-align tooltipped specialprice noSpace amountFormat" id="updateservPrice" name="dblItemPrice" placeholder="999.99" data-position="bottom" data-delay="30" data-tooltip="${service.dblServicePrice}">
                                        <label for="updateservPrice" class="active">Price</label>
                                    </div>
                                </div>
                              </div>
                            </div>


                            
                          </div>
                          <div class="modal-footer">
                              <button type="reset" value="Reset" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</button>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">UPDATE</button>
                          </div>
                          </form>
                    </div>
                  </c:forEach>

                  <!-- VIEW SERVICE START -->
                  <c:forEach items="${serviceList}" var="service">
                         <%! String viewserviceID = null; %>
                         <% Service servID = (Service)pageContext.getAttribute("service");
                            viewserviceID = String.valueOf(servID.getIntServiceID());
                            String serviceCate = servID.getStrServiceCategory();
                         %>
                         <div id="viewserv<%=viewserviceID%>" class="modal modal-fixed-footer" style="width: 60% !important; height: 80% !important; max-height: 100% !important;">
                         <form class="col s12" enctype="multipart/form-data">
                           <div class="modal-content">
                             <!-- <div class="container"> -->
                             <div class="wrapper">
                               <div class="input-field col s12">
                                   <h4 class="grey-text text-darken-1">View Product/Service</h4>
                               </div>

                               <div class="aside aside1 z-depth-0">
                                 <div class="row">
                               <!-- 1st aside -->
                                   <div class="col s12">
                                       <img name="prodsvcCreate" id="prodsvcCreate" style="width: 200px; height: 200px; margin-top: 20px;" src="<s:url action='getImage'><s:param name='ImageID'><%=serviceID%></s:param><s:param name='type'>service</s:param></s:url>" alt="${product.strProductName}"/>
                                   </div>
                                 </div>
                               </div>

                               <div class="aside aside2 z-depth-0">
                                 <div class="row">
                               <!-- 2nd aside -->
                                     <div class="input-field col s12">
                                       <select name="strItemCate" class="browser-default" id="strItemCate" disabled="disabled">
                                         <option value="" disabled="disabled" selected></option>
                                         <option value="service" selected>Service</option>
                                         <option value="product">Product</option>
                                       </select>
                                       <label for="strItemCate" class="active">Type</label>
                                     </div>
                                     <div class="input-field col s12" style="margin-top: 28px !important;">
                                       <input value="${service.strServiceName}" type="text" class="tooltipped" id="prodsvcName" name="strItemName" placeholder="Product/Service Name" data-position="bottom" data-delay="30" data-tooltip="${service.strServiceName}" readonly>
                                       <label for="prodsvcName">Name</label>
                                     </div>
                                     <div class="input-field col s12">
                                       <textarea id="prodsvcDetail" name="strItemDetails" class="materialize-textarea tooltipped" placeholder="Details" data-position="bottom" data-delay="30" data-tooltip="${service.strServiceDesc}" readonly style="margin-top: -10px;">${service.strServiceDesc}</textarea>
                                       <label for="prodsvcDetail" class="active">Details</label>
                                     </div>
                                     <div class="input-field col s12">
                                         <select class="browser-default updateServCategory" id="updateServCategory" name="strItemCategory" disabled="disabled">
                                             <option value="" disabled selected> </option>
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
                                         <label for="updateServCategory" class="active">Category</label>
                                     </div>
                                     <div class="input-field col s6" style="margin-top: 28px !important;">
                                         <input value="${service.dblServicePrice}" type="text" class="right-align tooltipped amountFormat" id="updateservPrice" name="dblItemPrice" required placeholder="999.99" data-position="bottom" data-delay="30" data-tooltip="${service.dblServicePrice}" readonly>
                                         <label for="updateservPrice" class="active">Price</label>
                                     </div>
                                 </div>
                               </div>
                             </div>


                             
                           </div>
                           <div class="modal-footer">
                               <button class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">BACK</button>
                           </div>
                           </form>
                     </div>
                   </c:forEach>
                  <!-- VIEW SERVICE END -->

                  <!-- add category BEGIN -->
                          <div id="addUpdateServCategory" class="modal" style="margin-top: 30px;">
                            <form class="updateAddCatServ">
                              <div class="modal-content">
                                <h4>Add Another Category</h4>
                                <div class="row">
                                  <div class="col s12">
                                    <div class="input-field col s8 offset-s2">
                                      <select id="updateAddCatServSelect" class="browser-default" size="10">
                                        <c:forEach items="${serviceCategory}" var="service">
                                          <option value="${service}">${service}</option>
                                        </c:forEach>
                                      </select>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                                      <input type="text" class="validate tooltipped specialoption noSpace" id="updateAddCatServName" name="updateAddCatServName" placeholder="New Category" data-position="bottom" data-delay="30" data-tooltip="Ex: Hair Color<br/>( At least 3 or more characters )" pattern="^[a-zA-Z0-9-\-\s]{3,}$" required>
                                      <label for="updateAddCatServName" class="active">Position Name</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2 center">
                                      <button id="updateAddServCatBtn" class="waves-effect waves-light purple darken-3 btn-flat white-text">SAVE</button>
                                      <button type="reset" value="Reset" class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL</button>
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
                                <a href="#" class="modal-action modal-close waves-effect waves-purple transparent btn-flat black-text">NO</a>
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
                                <a href="#" class="modal-action modal-close waves-effect waves-purple transparent btn-flat black-text">NO</a>
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
        </article>

        

  </div>

  </body>