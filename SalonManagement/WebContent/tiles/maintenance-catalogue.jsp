  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page import="com.gss.model.Catalogue"%>
  <%@ page import="com.gss.model.Service"%>


  <body class="purple lighten-5">
  <div class="wrapper">

        <article>
        <!-- <div class="container"> -->
          <div class="wrapper">
<!--                     <div class="aside aside1 z-depth-barts">
                    
                    </div> -->

                    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
                        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                        <h3 class="grey-text text-darken-1">Catalogue Maintenance</h3>
                        <a class="waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text tooltipped" href="#create" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Create"><i class="material-icons">add</i></a>
                        <a class="waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text tooltipped" href="#catArchive" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Archive"><i class="material-icons">archive</i></a>
                        <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                <thead>
                                    <tr>
                                        <th><center>Name</center></th>
                                        <th><center>Type</center></th>
                                        <th><center>Price</center></th>
                                        <th><center>Status</center></th>
                                        <th><center>Action</center></th>
                                    </tr>
                                </thead>
                                <tbody>
                                   <!--  <tr>
                                        <td>1</td>
                                        <td>Mohawk</td>
                                        <td>Senior Cut</td>
                                        <td>desciption</td>
                                        <td>99.00</td>
                                        <td>01/01/01</td>
                                        <td><a class="waves-effect waves-light modal-trigger btn-flat transparent black-text" title="Update" href="#update" style="padding: 0px;"><i class="material-icons">edit</i></a>
                                        <a class="waves-effect waves-light modal-trigger btn-flat transparent red-text text-accent-4" href="#delete" title="Deactivate"><i class="material-icons">delete</i></a>
                                        </td>
                                    </tr> -->
                                  <c:forEach items="${catList}" var="cat">
                                    <%
                                        Catalogue cat1 = (Catalogue)pageContext.getAttribute("cat");
                                        String cat1ID = String.valueOf(cat1.getIntCatalogueID());
                                    %>
                                    <tr>
                                        <td style="padding:0; margin:0;"><center>${cat.strCatalogueName}</center></td>
                                        <td style="padding:0; margin:0;"><center>${cat.strCatalogueType}</center></td>
                                        <td style="padding:0; margin:0;"><center>Php ${cat.dblCataloguePrice}</center></td>
                                        <td style="padding:0; margin:0;"><center>${cat.intCatalogueStatus}</center></td>
                                        <td style="padding:0; margin:0;" class="center">
                                        <a data-delay="30" data-position="bottom" data-tooltip="View" class="tooltipped waves-effect waves-purple modal-viewall btn-flat transparent black-text" href="#viewCat<%=cat1ID%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                        <i class="material-icons">visibility</i>
                                        </a>
                                        <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" href="#cata<%=cat1ID%>" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Update"><i class="material-icons">edit</i></a>
                                        <a data-delay="30" data-position="bottom" data-tooltip="Deactive" class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent red-text text-accent-4" href="#dec<%=cat1ID%>" style="padding-left: 10px;padding-right:10px; margin: 5px;"><i class="material-icons">delete</i></a>
                                        </td>
                                    </tr> 
                                   </c:forEach>
                                </tbody>
                            </table>

                      </div>

                      <!-- ARCHIVE CATALOGUE BEGIN -->
                      <div id="catArchive" class="modal modal-fixed-footer" style="width: 80% !important; height: 86% !important; max-height: 100% !important;">
                          <div class="modal-content">
                            <div class="col s12">
                                <h4 class="grey-text text-darken-1">Archive</h4>
                                <table id="carArchivetable" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                        <thead>
                                            <tr>
                                                <th><center>Name</center></th>
                                                <th><center>Type</center></th>
                                                <th><center>Price</center></th>
                                                <th><center>Status</center></th>
                                                <th><center>Action</center></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <!--  <tr>
                                                <td>1</td>
                                                <td>Mohawk</td>
                                                <td>Senior Cut</td>
                                                <td>desciption</td>
                                                <td>99.00</td>
                                                <td>01/01/01</td>
                                                <td><a class="waves-effect waves-light modal-trigger btn-flat transparent black-text" title="Update" href="#update" style="padding: 0px;"><i class="material-icons">edit</i></a>
                                                <a class="waves-effect waves-light modal-trigger btn-flat transparent red-text text-accent-4" href="#delete" title="Deactivate"><i class="material-icons">delete</i></a>
                                                </td>
                                            </tr> -->
                                          <c:forEach items="${catList}" var="cat">
                                            <%
                                                Catalogue cat1 = (Catalogue)pageContext.getAttribute("cat");
                                                String cat1ID = String.valueOf(cat1.getIntCatalogueID());
                                            %>
                                            <tr>
                                                <td style="padding:0; margin:0;"><center>${cat.strCatalogueName}</center></td>
                                                <td style="padding:0; margin:0;"><center>${cat.strCatalogueType}</center></td>
                                                <td style="padding:0; margin:0;"><center>Php ${cat.dblCataloguePrice}</center></td>
                                                <td style="padding:0; margin:0;"><center>${cat.intCatalogueStatus}</center></td>
                                                <td style="padding:0; margin:0;" class="center">
                                                <a class="waves-effect waves-light modal-trigger btn-flat transparent green-text text-accent-4 tooltipped" href="#dec<%=cat1ID%>" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Activate"><i class="material-icons">check</i></a>
                                                </td>
                                            </tr> 
                                           </c:forEach>
                                        </tbody>
                                    </table>
                            </div>
                          </div>
                          <div class="modal-footer">
                            <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">BACK</a>
                          </div>
                        </div>
                      <!-- ARCHIVE CATALOGUE END -->

                      <!-- Modal Structure -->
                        <div id="create" class="modal modal-fixed-footer">
                        <form class="col s12" method="post" action="createCatalogue" enctype="multipart/form-data" id="createCatalogueForm">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                            <div class="row">
                                <div class="input-field col s12">
                                  <h3 class="grey-text text-darken-1">Create Catalogue</h3>
                                </div>
                                <div class="col s12" style="margin-top: -10px !important;">
                                     <label class="red-text" style="font-size: 15px;">(*) Indicates required field</label>
                                </div>
                                <div class="col s12">
                                    <img name="createCatalogue" id="createCatalogue" style="width: 150px; height: 150px; margin-top: 20px;" src="./img/product.png" alt=""/>
                                </div>
                                <div class="input-field col s5 offset-s4">
                                    <div class="file-field">
                                          <div class="btn purple">
                                            <span class=""><i class="material-icons">add_a_photo</i></span>
                                            <input name="upload" type="file" accept="image/.jpg, image/.png" onchange="loadCreateCatalogue(event)">
                                          </div>
                                          <div class="file-path-wrapper">
                                            <input name="strCataloguePath" value="image" class="file-path validate" type="text">
                                          </div>
                                      </div>
                                </div>
                                <div class="input-field col s10 offset-s1">
                                  <select class="browser-default" required name="strCatalogueCategoryID" id="createCataCateg">
                                    <option value="" disabled="disabled" selected></option>
                                    <c:forEach items="${serviceList}" var="service">
                                      <option value="${service.strServiceName}">${service.strServiceName}</option>
                                    </c:forEach>
                                  </select>
                                  <label for="createCataCateg" class="active">Type<span class="red-text">*</span></label>
                                </div>
                                <div class="input-field col s10 offset-s1" style="margin-top: 25px !important;">
                                  <input type="text" class="validate tooltipped specialname noSpace" required id="prodsvcName" name="strCatalogueName" data-delay="30" data-position="bottom" placeholder="Catalogue Name" data-tooltip="Ex: Under Cut<br/>( At least 5 or more characters )" pattern="^[a-zA-Z\-'`\s]{5,15}$" minlength="5" maxlength="15">
                                  <label for="prodsvcName" class="active">Name<span class="red-text">*</span></label>
                                </div> 
                              
                            <!-- </div> -->
                            </div>
                          </div>
                          <div class="modal-footer">
                              <button type="reset" value="Reset" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</button>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">SAVE</button>
                          </div>
                          </form>
                    </div>

                    <!-- VIEW CATALOGUE -->
                        <c:forEach items="${catList}" var="category">
                          <%
                              Catalogue cat3 = (Catalogue)pageContext.getAttribute("category");
                              String catID2 = String.valueOf(cat3.getIntCatalogueID());
                              String cateType = cat3.getStrCatalogueType();
                          %>
                          <div id="viewCat<%=catID2%>" class="modal modal-fixed-footer" style="width: 38% !important; height: 89% !important; max-height: 100% !important;">
                          <form class="col s12" enctype="multipart/form-data">
                            <div class="modal-content">
                              <!-- <div class="container"> -->
                              <div class="row">
                                
                                  <div class="input-field col s12">
                                    <h3 class="grey-text text-darken-1">View Catalogue</h3>
                                  </div>
                                  <div class="col s12" style="margin-top: -10px !important;">
                                      <img name="updateCatalogue" id="updateCatalogue" style="width: 150px; height: 150px; margin-top: 20px;" src="<s:url action='getImage'><s:param name='ImageID'><%=catID2%></s:param><s:param name='type'>catalogue</s:param></s:url>" alt=""/>
                                  </div>
                                  <div class="input-field col s5 offset-s4">
                                      <div class="file-field">
                                            <input type="hidden" name="intCatalogueID"value="${category.intCatalogueID}">
                                            <div class="btn purple">
                                              <span class=""><i class="material-icons">add_a_photo</i></span>
                                              <input disabled="disabled" name="upload" type="file" accept="image/.jpg, image/.png" onchange="loadCreateCatalogue(event)">
                                            </div>
                                            <div class="file-path-wrapper">
                                              <input disabled="disabled" name="strCataloguePath" value="image" class="file-path" type="text">
                                            </div>
                                        </div>
                                  </div>
                                  <div class="input-field col s10 offset-s1">
                                    <select class="browser-default" name="strCatalogueCategoryID" id="viewCatalogue" disabled>
                                      <option value="" disabled selected></option>
                                      <c:forEach items="${serviceList}" var="service">
                                        <%
                                            Service cat2 = (Service)pageContext.getAttribute("service");
                                            String type = "";
                                            if(cat2.getStrServiceName().equals(cateType)){
                                                type = "selected";
                                            }
                                        %>
                                        <option value="${service.strServiceName}" <%out.print(type);%>>${service.strServiceName}</option>
                                      </c:forEach>
                                    </select>
                                    <label for="viewCatalogue" class="active">Type</label>
                                  </div>
                                  <div class="input-field col s10 offset-s1"  style="margin-top: 25px !important;">
                                    <input readonly type="text" class="tooltipped" id="prodsvcName" name="strCatalogueName" value="${category.strCatalogueName}" data-delay="30" data-position="bottom" data-tooltip="${category.strCatalogueName}">
                                    <label for="prodsvcName" class="active">Name</label>
                                  </div>
                      
                                
                              <!-- </div> -->
                              </div>
                            </div>
                            <div class="modal-footer">
                                <button class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">BACK</button>

                            </div>
                            </form>
                      </div>
                    </c:forEach>
                    <!-- VIEW CATALOGUE END -->

                      <c:forEach items="${catList}" var="category">
                        <%
                            Catalogue cat3 = (Catalogue)pageContext.getAttribute("category");
                            String catID2 = String.valueOf(cat3.getIntCatalogueID());
                            String cateType = cat3.getStrCatalogueType();
                        %>
                        <div id="cata<%=catID2%>" class="modal modal-fixed-footer" style="margin-top:-10px !important; width: 38% !important; height: 89% !important; max-height: 100% !important;">
                        <form class="col s12 updateCataForm" id="updateCataForm" method="post" action="updateCatalogue" enctype="multipart/form-data">
                    <div class="modal-content">
                                                        <!-- <div class="container"> -->
                        <div class="row">
                          <div class="input-field col s12">
                              <h3 class="grey-text text-darken-1">Update Catalogue</h3>
                              </div>
                                <div class="col s12"  style="margin-top: -10px !important;">
                                    <img name="updateCatalogue" id="updateCatalogue" style="width: 150px; height: 150px; margin-top: 20px;" src="<s:url action='getImage'><s:param name='ImageID'><%=catID2%></s:param><s:param name='type'>catalogue</s:param></s:url>" alt="${servoce.strCatalogueName}"/>
                               </div>
                                <div class="input-field col s5 offset-s4">
                                    <div class="file-field">
                                         <input type="hidden" name="intCatalogueID"value="${category.intCatalogueID}">
                                         <div class="btn purple">
                                            <span class=""><i class="material-icons">add_a_photo</i></span>
                                            <input name="upload" type="file" accept="image/.jpg, image/.png" onchange="loadCreateCatalogue(event)">
                                          </div>
                                          <div class="file-path-wrapper">
                                            <input name="strCataloguePath" value="image" class="file-path validate" type="text">
                                          </div>
                                     </div>
                               </div>
                                <div class="input-field col s10 offset-s1">
                                 <select class="browser-default" name="strCatalogueCategoryID" id="createCataCateg">
                                    <option value="" disabled="disabled" selected></option>
                                       <c:forEach items="${serviceList}" var="service">
                                         <%
                                             Service cat2 = (Service)pageContext.getAttribute("service");
                                             String type = "";
                                             if(cat2.getStrServiceName().equals(cateType)){
                                                 type = "selected";
                                             }
                                         %>
                                         <option value="${service.strServiceName}" <%out.print(type);%>>${service.strServiceName}</option>
                                       </c:forEach>
                                 </select>
                                 <label for="createCataCateg" class="active">Type</label>
                                </div>
                                <div class="input-field col s10 offset-s1" style="margin-top: 25px !important;">
                                 <input type="text" class="validate tooltipped specialname noSpace" id="updateProdSvcName" name="strCatalogueName" data-delay="30" data-position="bottom" placeholder="Catalogue Name" data-tooltip="Ex: Under Cut<br/>( At least 5 or more characters )" pattern="^[a-zA-Z\-'`\s]{5,15}$" minlength="5" maxlength="15" value="${category.strCatalogueName}">
                                 <label for="updateProdSvcName" class="active">Name</label>
                                </div> 
                                                          
                                                        <!-- </div> -->
                           </div>
                         </div>
                         <div class="modal-footer">
                             <button type="reset" value="Reset" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</button>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">UPDATE</button>
                         </div>
                          </form>
                        </div>
                      </c:forEach>

                    <c:forEach items="${catList}" var="category">
                      <%
                          Catalogue cat5 = (Catalogue)pageContext.getAttribute("category");
                          String catID4 = String.valueOf(cat5.getIntCatalogueID());
                      %>
                      <div id="dec<%=catID4%>" class="modal" style="width: 30% !important;">
                        <form method="get" action="deactivateCatalogue">      
                          <div class="container">
                            <div class="modal-content">
                              <input type="hidden" name="intCatalogueID" value="${category.intCatalogueID}">
                              <div class="row">
                                <h5 class="red-text">Warning!</h5>
                                <p class="center">Are you sure you want to deactivate <b>${category.strCatalogueName}</b>?</p>
                              </div>
                            </div>
                              <div class="col s12 center" style="margin-bottom: 30px;">
                                <button class="waves-effect waves-light orange btn-flat white-text">YES</button>
                                <a href="#" class="modal-action modal-close waves-effect waves-light transparent btn-flat black-text">NO</a>
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