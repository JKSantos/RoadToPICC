  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page import="com.gss.model.Package" %>
  <%@ page import="com.gss.model.ProductPackage" %>
  <%@ page import="com.gss.model.ServicePackage" %>
  <%@ page import="com.gss.model.Service" %>
  <%@ page import="com.gss.model.Product" %>
  <%@ page import="java.util.List" %>

  <body class="blue-grey lighten-5">
  <div class="wrapper">
        

        <article>
        <!-- <div class="container"> -->
          <div class="wrapper">
<!--                     <div class="aside aside1 z-depth-barts">
                    
                    </div> -->

                    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
                        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                        <h3 class="grey-text text-darken-1">Package Maintenance</h3>
                        <a class="waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text" href="#create" style="margin-top: 50px; margin-left: 15px;">CREATE</a>
                        <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                <thead>
                                    <tr>
                                        <th><center>ID</center></th>
                                        <th><center>Package Name</center></th>
                                        <th><center>Type</center></th>
                                        <th><center>Description</center></th>
                                        <th><center>Action</center></th>
                                    </tr>
                                </thead>
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
                                        <td style="padding:0; margin: 0;"><center>${pack.intPackageID}</center></td>
                                        <td style="padding:0; margin: 0;"><center>${pack.strPackageName}</center></td>
                                        <td style="padding:0; margin: 0;"><center><%=type%></center></td>
                                        <td style="padding:0; margin: 0;"><center>${pack.strPackageDesc}</center></td>
                                        <td class="center" style="padding:0; margin: 0;">
                                        <button data-target="view<%=packID%>" class="waves-effect waves-purple modal-view btn-flat transparent" style="padding-left: 10px;padding-right:10px; margin: 5px;"><i class="material-icons">visibility</i></button>
                                        <button class="waves-effect waves-purple btn-flat transparent black-text modal-trigger" title="Update" style="padding: 0px;" data-target="update<%=packID%>" data-toggle="modal" style="padding-left: 10px;padding-right:10px; margin: 5px;"><i class="material-icons">edit</i></button>
                                        <a class="waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4" href="#del<%=packID%>" title="Deactivate" style="padding-left: 10px;padding-right:10px; margin: 5px;"><i class="material-icons">delete</i></a>
                                        </td>
                                    </tr>
                                  </c:forEach>
                                </tbody>
                            </table>

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
                                  <input type="text" readonly="true" id="packageViewName" name="packageViewName" value="${pack.strPackageName}">
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
                                     <c:forEach items="${pack.productList}"  var="includedProduct">
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
                        <div id="create" class="modal modal-fixed-footer" style="width: 75% !important; height: 92% !important; margin-top: -23px !important; max-height: 100% !important;">
                        <form class="col s12" id="createPackageForm" method="get" action="createPackage">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                              <div class="wrapper">
                                  <h4 class="grey-text text-darken-1">Create Package</h4>
                                  <div class="aside aside1 z-depth-barts" style="padding: 10px;">
                                    <div class="row">
                                            <div class="input-field col s12" style="margin-bottom: 30px;">
                                        <label class="red-text"> (*) Indicates required field</label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 20px !important;">
                                        <input type="text" name="strDiscountName"
                                               class="validate tooltipped specialname noSpace" id="discountName"
                                               required data-delay="30" data-position="bottom"
                                               data-tooltip="Ex: Student Discount<br/>( At least 5 or more characters )"
                                               pattern="^[a-zA-Z\-'`\s]{5,}$" minlength="5" maxlength="20"
                                               placeholder="Discount Name">
                                        <label for="discountName" class="active">Discount Name<span
                                                class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <textarea id="discountDesc" name="strDiscountDetails"
                                                  class="tooltipped materialize-textarea noSpace" maxlength="30"
                                                  data-delay="30" data-position="bottom"
                                                  data-tooltip="Ex: Valid ID is required<br/>( At least 5 or more characters )"
                                                  minlength="5" placeholder="Description"></textarea>
                                        <label for="discountDesc">Description</label>
                                    </div>
                                    <div class="input-field col s6">
                                        <select class="browser-default" required id="createDiscAmtType"
                                                name="strDiscountType">
                                            <option value="" selected disabled></option>
                                            <option value="1">Percentage</option>
                                            <option value="2">Fixed Amount</option>
                                        </select>
                                        <label for="createDiscAmtType" class="active">Type<span
                                                class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s4 offset-s2">
                                        <input type="text"
                                               class="validate right-align tooltipped specialprice noSpace amountFormat"
                                               id="createDiscPrice" required name="dblDiscountPrice" data-delay="30"
                                               data-position="bottom" data-tooltip="Ex: 99.99<br/>( Numbers only )"
                                               placeholder="Discount Amount" pattern="^[0-9]$" maxlength="10">
                                        <label for="createDiscPrice">Amount<span class="red-text">*</span></label>
                                    </div>
                                    </div>
                                  </div>
                                  <div class="aside aside2 z-depth-barts" style="padding: 10px;">
                                      <div class="row">
                                        <!-- service -->
                                          <h5 class="grey-text text-darken-1">Included Service/s and Product/s</h5>

                                          <ul class="collapsible popout" data-collapsible="accordion">
                                            <li>
                                              <div class="collapsible-header"><i class="material-icons">list</i>Services</div>
                                              <div class="collapsible-body">
                                                <div class="highlight centered responsive-table">
                                                  <table class="centered striped services">
                                                    <thead class="purple lighten-2">
                                                        <th>Select</th>
                                                        <th>Name</th>
                                                        <th>Price</th>
                                                        <th>Quantity</th>
                                                    </thead>
                                                      <c:forEach items="${serviceList}" var="service"> 
                                                        <tr>
                                                          <td><input type="checkbox" name="createPackServType" id="myCheckBox${service.intServiceID}" value="${service.intServiceID}"><label for="myCheckBox${service.intServiceID}"></label></td>
                                                          <td>${service.strServiceName}</td>
                                                          <td>${service.dblServicePrice}</td>
                                                          <td><input type="number" name="createPackServQty" style="width: 75px" min="1" max="99" value="1"></td>
                                                        </tr>
                                                      </c:forEach>
                                                  </table>  
                                                </div>
                                              </div>
                                            </li>
                                            
                                            <li>
                                              <div class="collapsible-header"><i class="material-icons">list</i>Products</div>
                                                <div class="collapsible-body">
                                                  <div class="highlight centered responsive-table">
                                                    <table class="centered striped services">
                                                      <thead class="purple lighten-2">
                                                          <th>Select</th>
                                                         <th>Name</th>
                                                          <th>Price</th>
                                                         <th>Quantity</th>
                                                      </thead>
                                                       <c:forEach items="${productList}" var="products"> 
                                                         <tr>
                                                           <td><input type="checkbox" name="createPackProdType" id="prodCheck${products.intProductID}" value="${products.intProductID}"><label for="prodCheck${products.intProductID}"></label></td>
                                                           <td>${products.strProductName}</td>
                                                           <td>${products.dblProductPrice}</td>
                                                           <td><input type="number" name="createPackProdQty" style="width: 75px" min="1" max="99" value="1"></td>
                                                         </tr>
                                                        </c:forEach>
                                                    </table>  
                                                  </div>
                                                </div>
                                            </li>
                                          </ul>

                                        <!-- end -->

                                        <!-- start table -->
                                          
                                        <!-- end table -->
                                      </div>
                                  </div>
                              </div>
                          </div>
                          <div class="modal-footer">
                              <a href="#!" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</a>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">CREATE</button>
                          </div>
                          </form>
                    </div>

                      <c:forEach items="${packageList}" var="pack">
                      <%
                          Package pg = (Package)pageContext.getAttribute("pack");
                          int id = pg.getIntPackageID();

                      %>
                        <div id="update<%=id%>" class="modal modal-fixed-footer" style="width: 75% !important; height: 92% !important; margin-top: -23px !important; max-height: 100% !important;">
                        <form class="col s12" method="post" action="updatePackage">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                              <div class="wrapper">
                                  <h4 class="grey-text text-darken-1">Update Package</h4>
                                  <div class="aside aside1 z-depth-barts" style="padding: 10px;">
                                    <div class="row">
                                          
                                          <h5 class="grey-text text-darken-1">Package Information</h5>
                                            <div class="input-field col s12" style="margin-bottom: 30px;">
                                                <label class="red-text"> (*) Indicates required field</label>
                                            </div>
                                            <div class="input-field col s12">
                                                <input value="${pack.strPackageName}" type="text" class="validate" id="strPackageName" name="strUpdatePackageName" required>
                                               <input type="hidden" name="intUpdatePackageID" value="${pack.intPackageID}">
                                                <label for="strPackageName">Package Name<span class="red-text">*</span></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <textarea id="updatePackageDesc" name="strUpdatePackageDesc" class="materialize-textarea" length="120">${pack.strPackageDesc}</textarea>
                                                <label for="updatePackageDesc">Description</label>
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
                                            <div class="input-field col s6">
                                              <select id="updatePackageType" name="intUpdatePackageType" autocomplete="off" multiple>
                                                <option disabled>Choose</option>
                                                <option value="1" <%out.println(type1);%>>Event</option>
                                                <option value="2" <%out.println(type2);%>>Home Service</option>
                                                <option valie="3" <%out.println(type3);%>>Walk-In</option>
                                              </select>
                                              <label for="updatePackageType">Type</label>
                                            </div>
                                            <div class="input-field col s6" >
                                                <input type="text" class="validate right-align amountFormat noSpace" id="createPackagePrice" placeholder="99.99" readonly>
                                                <label for="createPackagePrice" class="active">Total Accumulated Price</label>
                                            </div>
                                            <div class="input-field col s3 offset-s9">
                                                <input type="text" value="${pack.dblPackagePrice}" class="validate right-align" id="updatePackagePrice" name="dblUpdatePackagePrice" required>
                                                <label for="updatePackagePrice">Price<span class="red-text">*</span></label>
                                            </div>
                                    </div>
                                  </div>
                                  <div class="aside aside2 z-depth-barts" style="padding: 10px;">
                                      <div class="row">
                                        <!-- service -->
                                          <h5 class="grey-text text-darken-1">Included Service/s and Product/s</h5>
                                            <ul class="collapsible popout" data-collapsible="accordion">
                                            <li>
                                              <div class="collapsible-header"><i class="material-icons">list</i>Services</div>
                                              <div class="collapsible-body">
                                                <div class="highlight centered responsive-table">
                                                  <table class="centered striped services" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;">
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
                                                              Service service = (Service)pageContext.getAttribute("service");
                                                              Package servicePackage = (Package)pageContext.getAttribute("pack");
                                                              List<ServicePackage> servicePack = servicePackage.getServiceList();

                                                              int serviceID = service.getIntServiceID();
    
 
                                                               for(int intCtr = 0; intCtr < servicePack.size(); intCtr++){
                                                            	  System.out.println("Current Services On Package: " + servicePack.get(intCtr).getService().getIntServiceID());
                                                                  if(servicePack.get(intCtr).getService().getIntServiceID() == service.getIntServiceID()){
                                                                	  servChecked = "checked";
                                                                	  servQuantity = servicePack.get(intCtr).getIntQuantity();
                                                                      break;
                                                                  }
                                                                  else
                                                                	  continue;

                                                              }
                                                         %>
                                                        <tr>
                                                          <td><input type="checkbox" name="updatePackServType" id="updateServCheckBox${pack.intPackageID}<%=serviceID%>" value="${service.intServiceID}" <%out.println(servChecked);%>><label for="updateServCheckBox${pack.intPackageID}<%=serviceID%>"></label></td>
                                                          <td>${service.strServiceName}</td>
                                                          <td>${service.dblServicePrice}</td>
                                                          <td><input type="number" name="updatePackServQty" style="width: 75px" min="1" max="99" value="<%=servQuantity%>"></td>
                                                        </tr>
                                                      </c:forEach>
                                                  </table>  
                                                </div>
                                              </div>
                                            </li>
                                            
                                            <li>
                                              <div class="collapsible-header"><i class="material-icons">list</i>Products</div>
                                                <div class="collapsible-body">
                                                  <div class="highlight centered responsive-table">
                                                    <table class="centered striped services" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;">
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
                                                              Product product = (Product)pageContext.getAttribute("products");
                                                              Package productPackage = (Package)pageContext.getAttribute("pack");
                                                              List<ProductPackage> productPack = productPackage.getProductList();             
                                                              
                                                              for(int intCtr = 0; intCtr < productPack.size(); intCtr++){
                                                            	  System.out.println("Current Services On Package: " + productPack.get(intCtr).getProduct().getIntProductID());
                                                                  if(productPack.get(intCtr).getProduct().getIntProductID() == product.getIntProductID()){
                                                                	  prodChecked = "checked";
                                                                	  prodQuantity = productPack.get(intCtr).getIntProductQuantity();
                                                                      break;
                                                                  }
                                                                  else
                                                                	  continue;

                                                              }
                                                         %>
                                                         <tr>
                                                           <td><input type="checkbox" name="updatePackProdType" id="updateProdCheck${pack.intPackageID}${products.intProductID}" value="${products.intProductID}" <%out.println(prodChecked);%>><label for="updateProdCheck${pack.intPackageID}${products.intProductID}"></label></td>
                                                           <td>${products.strProductName}</td>
                                                           <td>${products.dblProductPrice}</td>
                                                           <td><input type="number" name="updatePackProdQty" style="width: 75px" min="1" max="99" value="<%=prodQuantity%>"></td>
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
                              <a href="#!" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</a>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">SAVE</button>
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
                                <a href="#" class="modal-action modal-close waves-effect waves-purple transparent btn-flat black-text">NO</a>
                              </div>
                              </form>
                            </div>
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
