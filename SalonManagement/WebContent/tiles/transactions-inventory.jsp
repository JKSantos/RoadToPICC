  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <body class="blue-grey lighten-5">
  <div class="wrapper">
       
        <article>
          <div class="wrapper">
                              
                      <div class="aside aside2 z-depth-barts">
                             <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                             <h3 class="grey-text text-darken-1">Inventory</h3>
                             <div class="row">
                               <div class="input-field col s4">
                               <h4 class="thin">Filter by:</h4>
                                 <select name="categoty" required>
                                   <option value="" disabled selected>Inventory category</option>  
                                     <option value="}">Expired</option>
                                     <option value="}">Lost</option>
                                     <option value="}">Defect</option>
                                 </select>
                               </div>
                             </div>
                             
                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text" href="#defectiveitem" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Defective Item"><i class="material-icons">broken_image</i></a>
                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text" href="#lostitem" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Lost Item"><i class="material-icons">search</i></a>
                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text" href="#expireditem" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Expired Item"><i class="material-icons">event_busy</i></a>
                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text" href="#archive" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Expired Item"><i class="material-icons">system_update_alt</i></a>

                             <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                     <thead>
                                         <tr>
                                             <th><center>Name</center></th>
                                             <th><center>Selling Price</center></th>
                                             <th><center>Stock</center></th>
                                             <th><center>Date/Time</center></th>
                                             <th><center>Actions</center></th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                        <c:forEach items="${productList}" var="product">
                                         <tr>
                                             <td style="padding:0; margin:0;"><center>${product.strProductName}</center></td>
                                             <td style="padding:0; margin:0;"><center>${product.dblProductPrice}</center></td>
                                             <td style="padding:0; margin:0;"><center>${product.intProductQuantity}</center></td>
                                             <td style="padding:0; margin:0;"><center>${product.intProductQuantity}</center></td>
                                             <td class="center" style="padding:0; margin:0;"><center>
                                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" title="Update" href="#inventoryedit${product.intProductID}" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Update"><i class="material-icons">edit</i></a></center>
                                             </td>
                                         </tr>
                                        </c:forEach>
                                     </tbody>
                                 </table>
                           </div>                    
                        </div>

                        <div id="archive" class="modal modal-fixed-footer" style="width: 90% !important; margin-bottom: 0px !important; height: 50% !important; max-height: 100% !important;">
                          <form class="col s12" name="DamagedForm" method="post" action="createTag">
                              <div class="modal-content">
                                <div class="wrapper">
                                  <h4 class="grey-text text-darken-1"><center>Archive</center></h4>
                                  <div class="row">
                                      <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                              <thead>
                                                  <tr>
                                                      <th><center>Name</center></th>
                                                      <th><center>Selling Price</center></th>
                                                      <th><center>Quantity</center></th>
                                                      <th><center>Date/Time</center></th>
                                                      <th><center>Actions</center></th>
                                                  </tr>
                                              </thead>
                                              <tbody>
                                               
                                                  <tr>
                                                      <td style="padding:0; margin:0;"><center>Wax</center></td>
                                                      <td style="padding:0; margin:0;"><center>P200/center></td>
                                                      <td style="padding:0; margin:0;"><center>35</center></td>
                                                      <td style="padding:0; margin:0;"><center>12/23/17</center></td>
                                                      <td class="center" style="padding:0; margin:0;"><center>
                                                      <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" title="Update" href="#inventoryedit${product.intProductID}" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Update"><i class="material-icons">edit</i></a></center>
                                                      </td>
                                                  </tr>
                                                
                                              </tbody>
                                          </table>
                                  </div>
                              </div>
                              <div class="modal-footer">
                                <button type="submit" class="waves-effect waves-white btn-flat purple white-text">SAVE</button>
                                <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                              </div>
                          </form>
                        </div>

                        <div id="defectiveitem" class="modal modal-fixed-footer" style="width: 30% !important; margin-bottom: 0px !important; height: 50% !important; max-height: 100% !important;">
                          <form class="col s12" name="DamagedForm" method="post" action="createTag">
                              <div class="modal-content">
                                <div class="wrapper">
                                  <h4 class="grey-text text-darken-1"><center>Defective Item</center></h4>
                                  <div class="row">
                                    <div class="input-field col s12" style="margin-top: 10%;">
                                      <select name="intProductID" required>
                                        <option value="" disabled selected>Item Name</option>  
                                        <c:forEach items="${productList}" var="product"> 
                                          <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                      </select>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 5%;">
                                      <input type="number" name="intQuantity" id="Quantity" maxlength="3" placeholder="1" pattern="^[0-9]$" min="1" required></input>
                                      <label for="Quantity">Quantity</label>
                                    </div>

<!-- ///// IMPORTANT CODES /////////  DO NOT EDIT /////////////////////////////////////////////////////////-->
                                    <div class="input-field col s12" style="margin-top: 5%;">
                                      <input type="hidden" name="intTagType" id="tagType" value="1"></input>
                                    </div>
<!-- /////////////////////////////////////////////////////////////////////////////////////////////////-->
                                  </div>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="submit" class="waves-effect waves-white btn-flat purple white-text">SAVE</button>
                                <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                              </div>
                          </form>
                        </div>

                        <div id="lostitem" class="modal modal-fixed-footer" style="width: 30% !important; height: 50% !important; max-height: 100% !important;">
                          <form class="col s12" name="LostForm" method="post" action="createTag">
                              <div class="modal-content">
                                <div class="wrapper">
                                  <h4 class="grey-text text-darken-1"><center>Lost Item</center></h4>
                                  <div class="row">
                                    <div class="input-field col s12" style="margin-top: 10%;">
                                      <select name="intProductID" required>
                                        <option value="" disabled selected>Item Name</option>  
                                        <c:forEach items="${productList}" var="product"> 
                                          <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                      </select>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 5%;">
                                      <input type="number" name="intQuantity" id="Quantity" maxlength="3" placeholder="1" pattern="^[0-9]$" min="1" required></input>
                                      <label for="Quantity">Quantity</label>
                                    </div>

<!-- ///// IMPORTANT CODES /////////  DO NOT EDIT /////////////////////////////////////////////////////////-->
                                    <div class="input-field col s12" style="margin-top: 5%;">
                                      <input type="hidden" name="intTagType" id="tagType" value="2"></input>
                                    </div>
<!-- /////////////////////////////////////////////////////////////////////////////////////////////-->
                                  </div>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="submit" class="waves-effect waves-white btn-flat purple white-text">SAVE</button>
                                <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                              </div>
                          </form>
                        </div>

                        <div id="expireditem" class="modal modal-fixed-footer" style="width: 30% !important; height: 50% !important; max-height: 100% !important;">
                          <form class="col s12" name="ExpiredForm" method="post" action="createTag">
                              <div class="modal-content">
                                <div class="wrapper">
                                  <h4 class="grey-text text-darken-1"><center>Expired Item</center></h4>
                                  <div class="row">
                                    <div class="input-field col s12" style="margin-top: 10%;">
                                      <select name="intProductID" required>
                                        <option value="" disabled selected>Item Name</option>  
                                        <c:forEach items="${productList}" var="product"> 
                                          <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                      </select>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 5%;">
                                      <input type="number" name="intQuantity" id="Quantity" maxlength="3" placeholder="1" pattern="^[0-9]$" min="1" required></input>
                                      <label for="Quantity">Quantity</label>
                                    </div>
<!-- ///// IMPORTANT CODES /////////  DO NOT EDIT /////////////////////////////////////////////////////////-->
                                    <div class="input-field col s12" style="margin-top: 5%;">
                                      <input type="hidden" name="intTagType" id="tagType" value="3"></input>
                                    </div>
<!-- //////////////////////////////////////////////////////////////////////////////////////////////////////-->                                    
                                  </div>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="submit" class="waves-effect waves-white btn-flat purple white-text">SAVE</button>
                                <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                              </div>
                          </form>
                        </div>

                      <c:forEach items="${productList}" var="product">
                        <div id="inventoryedit${product.intProductID}" class="modal modal-fixed-footer" style="width: 30% !important; height: 65% !important; max-height: 100% !important;">
                              <div class="col s12">
                                <ul class="tabs">
                                  <li class="tab col s6"><a href="#add${product.intProductID}">Increase Stock</a></li>
                                  <li class="tab col s6"><a  class="active" href="#subtract${product.intProductID}">Decrease Stock</a></li>
                                </ul>
                              </div>
                              <div id="add${product.intProductID}" class="col s12">
                                <form class="col s12" name="addForm" method="post" action="updateStock">
                                <div class="modal-content">
                                  <div class="row">

<!-- ///// IMPORTANT CODES /////////  DO NOT EDIT /////////////////////////////////////////////////////////-->
                                      <input type="hidden" name="intProductID" value="${product.intProductID}">
                                      <input type="hidden" name="intType" value="1">
<!--///////////////////////////////////////////////////////////////////////////////////////////////////////-->
                                    <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                      <input type="text" id="prodname" value="${product.strProductName}" disabled style="color: black !important;">
                                      <label for="prodname" style="color: black !important;">Product Name</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                      <input type="number" id="addcurrentstock" value="${product.intProductQuantity}" min="0" max="3" disabled style="color: black;">
                                      <label for="addcurrentstock" class="active black-text">Current Stock</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                      <input name="intQuantity" type="number" id="addstocknumber" placeholder="1" min="0">
                                      <label for="addstocknumber" class="active">Stock (<b>+</b>)</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                      <input class="black-text" type="number" id="addtotalstock" value="${product.intProductQuantity}" min="0" disabled>
                                      <label for="addtotalstock" class="active black-text">Total Stock</label>
                                    </div>
                                  </div>
                                </div>
                                <div class="modal-footer">
                                  <button type="submit" class="waves-effect waves-white btn-flat purple white-text">SAVE</button>
                                  <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                                </div>
                                </form>
                              </div>

                              <div id="subtract${product.intProductID}" class="col s12">
                                <form class="col s12" name="subForm" action="updateStock" method="post">
                                <div class="modal-content">
                                  <div class="row">

<!-- ///// IMPORTANT CODES /////////  DO NOT EDIT /////////////////////////////////////////////////////////-->
                                      <input type="hidden" name="intProductID" value="${product.intProductID}">
                                      <input type="hidden" name="intType" value="2"> 
<!--///////////////////////////////////////////////////////////////////////////////////////////////////////-->                                    

                                    <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                      <input type="text" id="subprodname" value="${product.strProductName}" disabled style="color: black !important;">
                                      <label for="subprodname" style="color: black !important;">Product Name</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                      <input type="number" id="subcurrentstock" value="${product.intProductQuantity}" min="0" max="3" disabled style="color: black;">
                                      <label for="subcurrentstock" class="active black-text">Current Stock</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                      <input name="intQuantity" type="number" id="substocknumber" placeholder="1" min="0">
                                      <label for="substocknumber" class="active">Stock (<b>-</b>)</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                      <input class="black-text" type="number" id="subtotalstock" value="${product.intProductQuantity}" min="0" disabled>
                                      <label for="subtotalstock" class="active black-text">Total Stock</label>
                                    </div>
                                  </div>
                                </div>
                                <div class="modal-footer">
                                  <button type="submit" class="waves-effect waves-white btn-flat purple white-text">SAVE</button>
                                  <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                                </div>
                                </form>
                              </div>
                        </div>
                      </c:forEach>

        </article>

  </div>
                
  </body>