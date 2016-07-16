  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page import="com.gss.model.ExtraCharge"%>

  <body class="purple lighten-5">
  <div class="wrapper">

        <article>
        <!-- <div class="container"> -->
          <div class="wrapper">
<!--                     <div class="aside aside1 z-depth-barts">
                    
                    </div> -->

                    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
                        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                        <h3 class="grey-text text-darken-1">Other Charge Maintenance</h3>
                        <a data-delay="30" data-position="bottom" data-tooltip="Create" class="waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text tooltipped" href="#create" style="margin-top: 50px; margin-left: 15px;"><i class="material-icons">add</i></a>
                        <a data-delay="30" data-position="bottom" data-tooltip="Archive" class="waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text tooltipped" href="#archive" style="margin-top: 50px; margin-left: 15px;"><i class="material-icons">archive</i></a>
                        <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                <thead>
                                    <tr>
                                        <th><center>Charge Name</center></th>
                                        <th><center>Description</center></th>
                                        <th><center>Price</center></th>
                                        <th><center>Action</center></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${ecList}" var="extracharge">
                                      <tr>
                                      <%
                                          ExtraCharge ext = (ExtraCharge)pageContext.getAttribute("extracharge");
                                          String exID = String.valueOf(ext.getIntECID());
                                      %>

                                        <td style="padding:0; margin:0;"><center>${extracharge.strECName}</center></td>
                                        <td style="padding:0; margin:0;"><center>${extracharge.strECDetails}</center></td>
                                        <td style="padding:0; margin:0;"><center>${extracharge.dblECPrice}</center></td>
                                        <td class="center" style="padding:0; margin:0;"><center>
                                        <a data-delay="30" data-position="bottom" data-tooltip="View" class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" href="#ec${extracharge.intECID}" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                        <i class="material-icons">visibility</i></a>
                                        <a data-delay="30" data-position="bottom" data-tooltip="Update" class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" href="#ec${extracharge.intECID}" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                        <i class="material-icons">edit</i></a>
                                        <a data-delay="30" data-position="bottom" data-tooltip="Deactivate" class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent red-text text-accent-4" href="#del<%=exID%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                        <i class="material-icons">delete</i></a></center>
                                        </td>
                                      </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                      </div>

                      <!-- Modal Structure -->
                        <div id="create" class="modal modal-fixed-footer">
                        <form class="col s12" id="createExtraForm" method="post" action="createExtraCharge">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                              <div class="wrapper">
                                  <h4 class="grey-text text-darken-1" style="margin-bottom: 40px;">Create Charge</h4>
                                    <div class="row">
                                            <div class="input-field col s12" style="margin-bottom: 30px;">
                                                <label class="red-text"> (*) Indicates required field</label>
                                            </div>
                                            <div class="input-field col s12" style="margin-top: 25px;">
                                                <input type="text" class="validate tooltipped specialname noSpace" id="createEChargeName" name="strECName" required pattern="^[a-zA-Z\-'`\s]{5,}$" minlength="5" maxlength="15" data-delay="30" data-position="bottom" data-tooltip="Ex: Missing Equipments<br/>( At least 5 or more characters )" placeholder="Charge Name">
                                                <label for="createEChargeName" class="active">Charge Name<span class="red-text">*</span></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <textarea id="createEChargeDesc" name="strECDetails" class="materialize-textarea tooltipped noSpace" maxlength="30" minlength="5" data-delay="30" data-position="bottom" data-tooltip="Description" placeholder="Description"></textarea>
                                                <label for="createEChargeDesc" class="active">Description<span class="red-text">*</span></label>
                                            </div>
                                            <div class="input-field col s12">
                                               	<input type="text" class="validate tooltipped specialname noSpace" id="createECPrice" name="dblECPrice" required data-tooltip="Price" placeholder="99.99">
                                                <label for="createECPrice" class="active">Price<span class="red-text">*</span></label>
                                            </div>
                                    </div>
                              </div>
                          </div>
                          <div class="modal-footer">
                              <button type="reset" value="Reset" class="modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</button>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">CREATE</button>
                          </div>
                          </form>
                    </div>

                      <c:forEach items="${ecList}" var="extra">
                        <div id="ec${extra.intECID}" class="modal modal-fixed-footer" style="width: 30% !important; height: 70% !important; max-height: 100% !important;">
                        <form class="col s12 updateExtraForm" method="get" id="updateExtraForm" action="updateExtraCharge">
                          <div class="modal-content">
                            <!-- <div class="container"> -->
                              <div class="wrapper">
                                  <h4 class="grey-text text-darken-1" style="margin-bottom: 40px;">Update Charge</h4>
                                    <div class="row">
                                            <div class="input-field col s12">
                                                <input type="hidden" name="intECID" value="${extra.intECID}">

                                                <input id="updateEChargeName" name="strECName" type="text" value="${extra.strECName}" class="validate tooltipped specialname noSpace" pattern="^[a-zA-Z\-'`\s]{5,}$" placeholder="Charge Name" data-delay="30" data-position="bottom" data-tooltip="Ex: Missing Equipments<br/>( At least 5 or more characters )" minlength="5" maxlength="15">
                                                <label for="updateEChargeName" class="active">Charge Name</label>
                                            </div>
                                            <div class="input-field col s12">
                                                <textarea id="updateEChargeDesc" name="strECDetails" class="materialize-textarea tooltipped noSpace" data-delay="30" data-position="bottom" data-tooltip="Description<br/>( At least 5 or more characters )" maxlength="30" minlength="5">${extra.strECDetails}</textarea>
                                                <label for="updateEChargeDesc" class="active">Description</label>
                                            </div>
                                    </div>
                              </div>
                          </div>
                          <div class="modal-footer">
                              <button type="reset" value="Reset" class="modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</button>
                              <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">UPDATE</button>
                          </div>
                          </form>
                    </div>
                  </c:forEach>

                        <c:forEach items="${ecList}" var="extra">
                          <%
                                        
                              ExtraCharge ext2 = (ExtraCharge)pageContext.getAttribute("extra");
                              String exID2 = String.valueOf(ext2.getIntECID());
                         %>


                          <div id="del<%=exID2%>" class="modal" style="width: 30% !important;">
                          <form action="deactivateExtraCharge" method="get">
                          <div class="container">
                            <input type="hidden" name="intECID" value="${extra.intECID}">
                            <div class="modal-content">
                              <div class="row">
                                <h5 class="red-text">Warning!</h5>
                                <p class="center">Are you sure?</p>
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
