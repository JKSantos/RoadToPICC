<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.ExtraCharge"%>
<!-- <div class="container"> -->
<div class="wrapper">
    <!--                     <div class="aside aside1 z-depth-barts">

                        </div> -->

    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Other Charge Maintenance</h3>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createExtraChargeModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#empArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="extraSearch" class="grey-text text-darken-4" type="search" required>
                            <label for="extraSearch" style="margin-top: -20px !important;"><i
                                    class="material-icons grey-text text-darken-4"
                                    style="margin-top: 20px;">search</i></label>
                            <i class="material-icons grey-text text-darken-4">close</i>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="extratbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Charge Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-right">Price</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Charge Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-right">Price</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${ecList}" var="extracharge">
                    <tr>
                        <td style="padding-left: 10px; margin:0;" class="dt-body-left">${extracharge.strECName}</td>
                        <td style="padding-left: 10px; margin:0;" class="dt-body-left">${extracharge.strECDetails}</td>
                        <td style="padding-right: 10px; margin:0;" class="dt-body-right servPrice">${extracharge.dblECPrice}</td>
                        <td class="dt-body-center" style="padding:0; margin:0;">
                            <a class="waves-effect waves-purple modal-trigger btn-flat transparent black-text empUpdatebtn"
                               href="#ecupdate${extracharge.intECID}" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <button class="extradeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                    style="padding-left: 10px;padding-right:10px; margin: 5px;" id="${extracharge.intECID}"
                                    title="Deactivate" formaction="deactivateEmployee"><i class="material-icons">delete</i></button>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="createExtraChargeModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createExtraForm" method="post" action="createExtraCharge">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Charge<a id="btnCrExtraExit" type="reset"
                                                                                 value="Reset"
                                                                                 class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="extraerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input type="text" class="validate" id="crECName"
                                   name="strECName" required placeholder="Charge Name">
                            <label for="crECName" class="active"><b>Charge Name</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s12" style="margin-top: 25px;">
                            <input type="text" class="validate" id="crECDetails"
                                   name="strECDetails" required
                                   placeholder="City">
                            <label for="crECDetails" class="active"><b>Details</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s6 right">
                            <input id="crECPrice" name="price"
                                   class="validate upProdItemPrice right-align" required placeholder="Price">
                            <label for="crECPrice" class="active"><b>Price</b><i
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
                <button type="reset" value="Reset" id="crExtraCancel"
                        class="crExtraCancel modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL
                </button>
                <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                        value="Submit">CREATE
                </button>
            </div>
        </form>
    </div>

    <c:forEach items="${ecList}" var="extracharge">
        <div id="ecupdate${extracharge.intECID}" class="updateExtraModal modal modal-fixed-footer">
            <form class="col s12 updateExtraForm" id="updateExtraForm" method="post" action="updateExtraCharge">
                <div class="modal-content">
                    <!-- <div class="container"> -->
                    <div class="wrapper">
                        <h4 class="center grey-text text-darken-1">Update Charge<a id="btnUpExtraExit" type="reset"
                                                                                   value="Reset"
                                                                                   class="btnUpExtraExit modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                        <div class="upextraerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                           	 	<input type="hidden" value="${extracharge.intECID}"
                                       name="intECID">
                                <input type="text" class="validate" id="upECName" value="${extracharge.strECName}"
                                       name="strECName" required placeholder="Charge Name">
                                <label for="upECName" class="active"><b>Charge Name</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s12" style="margin-top: 25px;">
                                <input type="text" class="validate" id="upECDetails"
                                       name="strECDetails" required value="${extracharge.strECDetails}"
                                       placeholder="City">
                                <label for="upECDetails" class="active"><b>Details</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s6 right">
                                <input id="upECPrice" name="price" value="${extracharge.dblECPrice}"
                                       class="upECPrice validate upProdItemPrice right-align" required placeholder="Price">
                                <label for="upECPrice" class="active"><b>Price</b><i
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
                    <button type="reset" value="Reset" id="upExtraCancel"
                            class="upExtraCancel modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL
                    </button>
                    <button class="upExtraSubmitBtn waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                            value="Submit">UPDATE
                    </button>
                </div>
            </form>
        </div>
    </c:forEach>


</div>

