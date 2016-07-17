<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Location"%>
<!-- <div class="container"> -->
<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Delivery Charge Maintenance</h3>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createDeliveryModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#empArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="deliverySearch" class="grey-text text-darken-4" type="search" required>
                            <label for="deliverySearch" style="margin-top: -20px !important;"><i
                                    class="material-icons grey-text text-darken-4"
                                    style="margin-top: 20px;">search</i></label>
                            <i class="material-icons grey-text text-darken-4">close</i>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="deliverytbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0" width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Baranggay</th>
                    <th class="dt-head-left">City</th>
                    <th class="dt-head-right">Base Rate</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Baranggay</th>
                    <th class="dt-head-left">City</th>
                    <th class="dt-head-right">Base Rate</th>
                    <th align="dt-head-center" class="no-sort"><center>Action</center></th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${locationList}" var="location">
                <c:set var="price" scope="session" value="${(location.dblLocationPrice * 9) + location.dblLocationPrice}"/>
                <tr>
                    <td class="dt-body-left">${location.strBarangay}</td>
                    <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left ">
                        ${location.strCity}
                    </td>
                    <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-right prodPrice">
                        <c:out value="${price}"/>
                    </td>
                    <td class="center" style="padding:0; margin:0;">
                        <a data-delay="30" data-position="bottom" data-tooltip="View"
                           class="waves-effect waves-purple modal-viewall btn-flat transparent black-text"
                           href="" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                            <i class="material-icons">visibility</i>
                        </a>
                        <a data-delay="30" data-position="bottom" data-tooltip="Update"
                           class="waves-effect waves-purple modal-trigger btn-flat transparent black-text empUpdatebtn"
                           href="#updateDeliveryModal${location.intLocationID}" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                            <i class="material-icons">edit</i>
                        </a>
                        <button class="deliverydeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                id="${location.intLocationID}" title="Deactivate"><i class="material-icons">delete</i></button>
                    </td>
                </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

    <div id="createDeliveryModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createDeliveryForm" method="post" action="createLocation">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Location<a id="btnCrLocExit" type="reset"
                                                                                 value="Reset"
                                                                                 class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="deliveryerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input type="text" class="validate" id="crLocationBrgy"
                                   name="strBrgy" required placeholder="Baranggay">
                            <label for="crLocationBrgy" class="active"><b>Barangay</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s12" style="margin-top: 25px;">
                            <input type="text" class="validate" id="crLocationCity"
                                   name="strCity" required
                                   placeholder="City">
                            <label for="crLocationCity" class="active"><b>City</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s6 right">
                            <input id="crLocationBRate" name="price"
                                   class="validate upProdItemPrice right-align" required placeholder="Base Rate">
                            <label for="crLocationBRate" class="active"><b>Base Rate</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="red-text btn-flat transparent left" disabled
                        style="margin:0px !important; padding:0px !important;"><i
                        class="material-icons">error_outline</i>&nbspRequired field
                </button>
                <button type="reset" value="Reset" id="crLocCancel"
                        class="modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL
                </button>
                <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                        value="Submit">CREATE
                </button>
            </div>
        </form>
    </div>

    <c:forEach items="${locationList}" var="location">
    <c:set var="price" scope="session" value="${(location.dblLocationPrice * 9) + location.dblLocationPrice}"/>
    
    <div id="updateDeliveryModal${location.intLocationID}" class="updateDeliveryModal modal modal-fixed-footer">
        <form class="col s12 updateDeliveryForm" id="updateDeliveryForm" method="post" action="updateLocation">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Update Location<a id="btnUpLocExit" type="reset"
                                                                                 value="Reset"
                                                                                 class="btnUpLocExit modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="updeliveryerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="row">
                        <div class="input-field col s12">

                            <input type="hidden"
                                   name="intLocationID" value="${location.intLocationID}">

                            <input type="text" class="validate" id="upLocationBrgy"
                                   name="strBrgy" required placeholder="Baranggay" value="${location.strBarangay}">
                            <label for="upLocationBrgy" class="active"><b>Barangay</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s12" style="margin-top: 25px;">
                            <input type="text" class="validate" id="upLocationCity"
                                   name="strCity" required
                                   placeholder="City" value="${location.strCity}">
                            <label for="upLocationCity" class="active"><b>City</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s6 right">
                            <input id="upLocationBRate" name="price"
                                   class="validate upProdItemPrice right-align" required placeholder="Base Rate" value="<c:out value='${price}'/>">
                            <label for="upLocationBRate" class="active"><b>Base Rate</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="red-text btn-flat transparent left" disabled
                        style="margin:0px !important; padding:0px !important;"><i
                        class="material-icons">error_outline</i>&nbspRequired field
                </button>
                <button type="reset" value="Reset"
                        class="upLocCancel modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL
                </button>
                <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                        value="Submit">UPDATE
                </button>
            </div>
        </form>
    </div>
    </c:forEach>



</div>