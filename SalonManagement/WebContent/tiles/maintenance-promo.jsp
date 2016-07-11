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


  <body class="purple lighten-5">
  <div class="wrapper">
           
        <article>
        <!-- <div class="container"> -->
          <div class="wrapper">
<!--                     <div class="aside aside1 z-depth-barts">
                    
                    </div> -->

                    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
                        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                        <h3 class="grey-text text-darken-1">Promo Maintenance</h3>
                        <a class="waves-effect waves-light modal-trigger btn-flat purple darken-2 darken-3 left white-text" href="#create" style="margin-top: 50px; margin-left: 15px;">CREATE</a>
                        <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                              <thead>
                                  <tr>
                                      <th><center>Promo Name</center></th>
                                      <th><center>Price</center></th>
                                      <th><center>Expiration</center></th>
                                      <th>Actions</th>
                                  </tr>
                              </thead>
                              <tbody>
                                <c:forEach items="${promoList}" var="promo">
                                  <tr>
                                      <td style="padding:0; margin:0;"><center>${promo.strPromoName}</center></td>
                                      <td style="padding:0; margin:0;"><center>Php ${promo.dblPromoPrice}</center></td>
                                      <td style="padding:0; margin:0;"><center>${promo.strPromoAvailability}</center></td>
                                      <td class="center" style="padding:0; margin:0;"><button data-target="viewProdSvc${promo.intPromoID}" class="waves-effect waves-purple modal-view btn-flat transparent" style="padding-left: 10px;padding-right:10px; margin: 5px;"><i class="material-icons">visibility</i></button>
                                      <a class="waves-effect waves-purple modal-trigger btn-flat transparent black-text" title="Update" href="#update${promo.intPromoID}" style="padding-left: 10px;padding-right:10px; margin: 5px;"><i class="material-icons">edit</i></a>
                                      <a class="waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4" href="#delete${promo.intPromoID}" title="Deactivate" style="padding-left: 10px;padding-right:10px; margin: 5px;"><i class="material-icons">delete</i></a>
                                      </td>
                                  </tr>
                                </c:forEach>                                  
                              </tbody>
                        </table>

                      </div>

                      <!-- view product and service modal -->
                      	<c:forEach items="${promoList}" var="promo">
                          <div id="viewProdSvc${promo.intPromoID}" class="modal">
                            <div class="modal-content">
                              <!-- <div class="container"> -->
                              <div class="row">
                                <h5 class="grey-text text-darken-1">Services & Products Included</h5>
                                <div class="input-field col s8 offset-s2">
                                  <input type="text" readonly id="promoViewName" name="promoViewName" value="${promo.strPromoName}">
                                  <label for="promoViewName">Name</label>
                                </div>

                                <div class="input-field col s8 offset-s2">
                                  <input type="text" readonly id="promoViewDesc" name="promoViewName" value="${promo.strPromoDescription}">
                                  <label for="promoViewDesc">Description</label>
                                </div>


                                <div class="input-field col s8 offset-s2">
                                  <textarea name="strPromoGuidelines" id="guidelines" class="materialize-textarea noSpace">${promo.strPromoGuidelines}</textarea>
                                  <label for="guidelines" class="active">Guidelines</label>
                                </div>
                                <div class="input-field col s8 offset-s2">
                                  <input type="text" readonly id="promoViewPrice" name="promoViewName" value="${promo.dblPromoPrice}">
                                  <label for="promoViewPrice">Price</label>
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
                                      <c:forEach items="${promo.serviceList}" var="includedService">
                                          <th data-field="viewService">${includedService.service.strServiceName}</th>
                                          <th data-field="viewServiceQty">${includedService.intQuantity}</th>
                                        </c:forEach>
                                    </tr>
                                  </tbody>
                                </table>

                                <table>
                                    <thead>
                                    <tr>
                                      <td>Products Included</td>
                                      <td>Quantity</td>
                                    </tr>
                                  </thead>
                                  <tbody>
                                     <c:forEach items="${promo.productList}"  var="includedProduct">
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
                        <div id="create" class="modal modal-fixed-footer" style="width: 75% !important; height: 80% !important; max-height: 100% !important;">
                        <form class="col s12" action="createPromo" method="get" id="createPromoForm">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                              <div class="wrapper">
                                  <h4 class="grey-text text-darken-1">Create Promo</h4>
                                  <div class="aside aside1 z-depth-barts" style="padding: 10px;">
                                    <div class="row">
                                            <h5 class="grey-text text-darken-1">Promo Information</h5>
                                            <div class="input-field col s12" style="margin-bottom: 30px;">
                                                <label class="red-text"> (*) Indicates required field</label>
                                            </div>
                                            <div class="input-field col s8 offset-s2" style="margin-bottom: 20px;">

                                            <label><b>Availability <span class="red-text">*</span></b></label>
                                            </div>
                                            <div class="input-field col s6">
                                            <p class="center">
                                              <input type="checkbox" class="filled-in nonexpiry filltwo" id="unli" name="strNonExp">
                                              <label for="unli">Non-Expiry</label>
                                            </p>
                                            </div>
                                            <div class="input-field col s6">
                                                 <input name="strExp" type="date" class="datepicker-promo expiration filltwo" id="promoend" placeholder="Expiration"/>
                                            </div>
                                            <div class="input-field col s12">
                                                <input name="strPromoName" type="text" class="validate specialname noSpace" id="promoname" required placeholder="Promo Name">
                                                <label for="promoname" class="active">Promo Name<span class="red-text">*</span></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <textarea name="strPromoDesc" id="promodetails" class="materialize-textarea noSpace" minlength="5" placeholder="Description"></textarea>
                                                <label for="promodetails" class="active">Description</label>
                                            </div>
                                            
                                            <div class="input-field col s12">
                                                <textarea name="strPromoGuidelines" id="guidelines" class="materialize-textarea noSpace" minlength="5" placeholder="*Sample Guideline1
*Sample Guideline 2
*Sample Guideline 3"></textarea>
                                                <label for="guidelines" class="active">Guidelines</label>
                                            </div>
                                            
                                            <div class="input-field col s5">
                                            <p class="center">
                                                <input name="strFree" type="checkbox" class="filled-in free fillone" id="free"/>
                                                <label for="free">Free</label>
                                            </p>
                                            </div>
                                            <div class="input-field col s6 offset-s1">
                                                <input name="dblPromoPrice" type="text" class="validate right-align price amountFormat noSpace fillone" id="promoprice" maxlength="8" placeholder="Amount">
                                                <label for="promoprice" class="active">Price</label>
                                            </div>
                                    </div>
                                  </div>
                                  <div class="aside aside2 z-depth-barts" style="padding: 10px;">
                                      <div class="row">
                                        <h5 class="grey-text text-darken-1">Included Service/s and Product/s</h5>
                                        <ul class="collapsible" data-collapsible="accordion">
                                            <li>
                                              <div class="collapsible-header"><i class="material-icons">list</i>Services</div>
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
                                                        <tr>
                                                          <td><input type="checkbox" name="servicePromoSelect" id="myCheckBox${service.intServiceID}" value="${service.intServiceID}"><label for="myCheckBox${service.intServiceID}"></label></td>
                                                          <td>${service.strServiceName}</td>
                                                          <td>${service.dblServicePrice}</td>
                                                          <td><input type="number" name="servicePromoQty" style="width: 75px" min="1" max="99" value="1"></td>
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
                                                      <thead>
                                                          <th>Select</th>
                                                         <th>Name</th>
                                                          <th>Price</th>
                                                         <th>Quantity</th>
                                                      </thead>
                                                       <c:forEach items="${productList}" var="products"> 
                                                         <tr>
                                                           <td><input type="checkbox" name="productPromoSelect" id="prodCheck${products.intProductID}" value="${products.intProductID}"><label for="prodCheck${products.intProductID}"></label></td>
                                                           <td>${products.strProductName}</td>
                                                           <td>${products.dblProductPrice}</td>
                                                           <td><input type="number" name="productPromoQty" style="width: 75px" min="1" max="99" value="1"></td>
                                                         </tr>
                                                        </c:forEach>
                                                    </table>  
                                                  </div>
                                                </div>
                                            </li>

                                            <li>
                                              <div class="collapsible-header"><i class="material-icons">list</i>Packages</div>
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
                                                         <tr>
                                                           <td><input type="checkbox" name="packagePromoSelect" id="promoPack${pack.intPackageID}" value="${pack.intPackageID}"><label for="promoPack${pack.intPackageID}"></label></td>
                                                           <td>${pack.strPackageName}</td>
                                                           <td>${pack.dblPackagePrice}</td>
                                                           <td><input type="number" name="packagePromoQty" style="width: 75px" min="1" max="99" value="1"></td>
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
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">CREATE</button>
                          </div>
                          </form>
                    </div>

                      <c:forEach items="${promoList}" var="promo">
                        <div id="update${promo.intPromoID}" class="modal modal-fixed-footer" style="width: 75% !important; height: 80% !important; max-height: 100% !important;">
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
                                              <input name="strNonExp" type="checkbox" class="filled-in" id="updateNonExpiry${promo.intPromoID}">
                                              <label for="updateNonExpiry${promo.intPromoID}">Non-Expiry</label>
                                            </p>
                                            </div>
                                            <div class="input-field col s6">
                                                 <input name="strExp" type="date" class="datepicker-promo" id="updatePromoEnd" placeholder="Expiration"/>
                                            </div>
                                            <div class="input-field col s12">
                                                <input name="strPromoName" type="text" class="validate" id="updatePromoName" value="${promo.strPromoName}" required>
                                                <label for="updatePromoName">Promo Name</label>
                                            </div>

                                    <!-- <<<<<<<< DO NOT ALTER >>>>>>>>>-->
                                            <input type="hidden" name="intPromoID" value="${promo.intPromoID}">
                                    <!-- <<<<<<<<< DO NOT ALTER >>>>>>>>>-->

                                            <div class="input-field col s12">
                                                <textarea name="strPromoDesc" id="updatePromoDetails" class="materialize-textarea" length="120">${promo.strPromoDescription}</textarea>
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
                                                <input name="strFree" type="checkbox" class="filled-in" id="updatePromoFree${promo.intPromoID}" <%out.println(free);%>/>
                                                <label for="updatePromoFree${promo.intPromoID}">Free</label>
                                            </p>
                                            </div>
                                            <div class="input-field col s6 offset-s1">
                                                <input name="dblPromoPrice" type="text" class="validate right-align" id="updatePromoPrice" maxlength="8" value="${promo.dblPromoPrice}">
                                                <label for="updatePromoPrice">Promo Price</label>
                                            </div>
                                    </div>
                                  </div>
                                  <div class="aside aside2 z-depth-barts" style="padding: 10px;">
                                      <div class="row">
                                        <h5 class="grey-text text-darken-1">Included Service/s and Product/s</h5>
                                        <ul class="collapsible popout" data-collapsible="accordion">
                                            <li>
                                              <div class="collapsible-header"><i class="material-icons">list</i>Services</div>
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
                                                              Service service = (Service)pageContext.getAttribute("service");
                                                              Promo servicePackage = (Promo)pageContext.getAttribute("promo");
                                                              List<ServicePackage> servicePack = servicePackage.getServiceList();

                                                              int serviceID = service.getIntServiceID();
    
 
                                                               for(int intCtr = 0; intCtr < servicePack.size(); intCtr++){
                                                                
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
                                                          <td><input type="checkbox" name="servicePromoSelect" id="myCheckBox${service.intServiceID}${promo.intPromoID}" value="${service.intServiceID}" <%out.println(servChecked);%>><label for="myCheckBox${service.intServiceID}${promo.intPromoID}"></label></td>
                                                          <td>${service.strServiceName}</td>
                                                          <td>${service.dblServicePrice}</td>
                                                          <td><input type="number" name="servicePromoQty" style="width: 75px" min="1" max="99" value="<%=servQuantity%>"></td>
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
                                                              Promo productPromo = (Promo)pageContext.getAttribute("promo");
                                                              List<ProductPackage> productPack = productPromo.getProductList();             
                                                              
                                                              for(int intCtr = 0; intCtr < productPack.size(); intCtr++){
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
                                                           <td><input type="checkbox" name="productPromoSelect" id="prodCheck${products.intProductID}${promo.intPromoID}" value="${products.intProductID}" <%out.println(prodChecked);%>><label for="prodCheck${products.intProductID}${promo.intPromoID}"></label></td>
                                                           <td>${products.strProductName}</td>
                                                           <td>${products.dblProductPrice}</td>
                                                           <td><input type="number" name="productPromoQty" style="width: 75px" min="1" max="99" value="<%=prodQuantity%>"></td>
                                                         </tr>
                                                        </c:forEach>
                                                    </table>  
                                                  </div>
                                                </div>
                                            </li>

                                            <li>
                                              <div class="collapsible-header"><i class="material-icons">list</i>Packages</div>
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
                                                              Package packages = (Package)pageContext.getAttribute("pack");
                                                              Promo packagePromo = (Promo)pageContext.getAttribute("promo");
                                                              List<PackagePackage> packPack = packagePromo.getPackageList();            
                                                              
                                                              for(int intCtr = 0; intCtr < packPack.size(); intCtr++){
                                                                  if(packPack.get(intCtr).getPack().getIntPackageID() == packages.getIntPackageID()){
                                                                    packChecked = "checked";
                                                                    packQuantity = packPack.get(intCtr).getIntPackageQuantity();
                                                                      break;
                                                                  }
                                                                  else
                                                                    continue;
                                                              }
                                                          %>
                                                         <tr>
                                                           <td><input type="checkbox" name="packagePromoSelect" id="promoPack${pack.intPackageID}${promo.intPromoID}" value="${pack.intPackageID}" <%out.println(packChecked);%>><label for="promoPack${pack.intPackageID}${promo.intPromoID}"></label></td>
                                                           <td>${pack.strPackageName}</td>
                                                           <td>${pack.dblPackagePrice}</td>
                                                           <td><input type="number" name="packagePromoQty" style="width: 75px" min="1" max="99" value="<%=packQuantity%>"></td>
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

                        <c:forEach items="${promoList}" var="promo">
                          <div id="delete${promo.intPromoID}" class="modal" style="width: 30% !important; height: 40% !important">
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
