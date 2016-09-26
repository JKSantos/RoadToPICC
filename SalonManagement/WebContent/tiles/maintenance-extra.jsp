<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.ExtraCharge"%>

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Fee Maintenance</h3>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createExtraChargeModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <!--<a class="z-depth-1 hoverable waves-effect waves-light modal-viewall btn purple darken-2 left white-text"-->
               <!--href="#otherchargeArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>-->
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="extraSearch" placeholder="Search" class="grey-text text-darken-4" type="search">
                            <label for="extraSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
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
                    <th class="dt-head-left">Fee Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-right">Price</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Fee Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-right">Price</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody class="extratblbody">
                </tbody>
            </table>
        </div>
    </div>

    <!-- ARCHIVE BEGIN -->
    <div id="otherchargeArchive" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Archive</h4>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="otherchargeArchiveSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search">
                            <label for="otherchargeArchiveSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <table id="otherchargeArchiveTbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0" width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Fee Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-right">Price</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Fee Name</th>
                    <th class="dt-head-left no-sort">Description</th>
                    <th class="dt-head-right">Price</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                    <tr>
                        <td style="padding-left: 10px; margin:0;" class="dt-body-left"></td>
                        <td style="padding-left: 10px; margin:0;" class="dt-body-left"></td>
                        <td style="padding-right: 10px; margin:0;" class="dt-body-right servPrice"></td>
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
    <div id="createExtraChargeModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createExtraForm">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Fee<a id="btnCrExtraExit" type="reset"
                                                                                 value="Reset"
                                                                                 class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="extraerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input type="text" class="validate" id="crECName"
                                   name="strECName" required placeholder="Charge Name">
                            <label for="crECName" class="active"><b>Fee Name</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s12" style="margin-top: 25px;">
                            <input type="text" class="validate" id="crECDetails"
                                   name="strECDetails"
                                   placeholder="Details">
                            <label for="crECDetails" class="active"><b>Details</b></label>
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
                <a class="waves-effect waves-light purple darken-3 white-text btn-flat" onclick="createExtra()">CREATE
                </a>
            </div>
        </form>
    </div>

        <div id="updateExtraModal" class="updateExtraModal modal modal-fixed-footer">
            <form class="col s12 updateExtraForm" id="updateExtraForm">
                <div class="modal-content">
                    <!-- <div class="container"> -->
                    <div class="wrapper">
                        <h4 class="center grey-text text-darken-1">Update Fee<a id="btnUpExtraExit" type="reset"
                                                                                   value="Reset"
                                                                                   class="btnUpExtraExit modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                        <div class="upextraerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                           	 	<input type="hidden" id="upECID" name="intECID">
                                <input type="text" class="validate" id="upECName"
                                       name="strECName" required placeholder="Charge Name">
                                <label for="upECName" class="active"><b>Fee Name</b><i
                                        class="material-icons red-text tiny">error_outline</i></label>
                            </div>
                            <div class="input-field col s12" style="margin-top: 25px;">
                                <input type="text" class="validate" id="upECDetails"
                                       name="strECDetails" placeholder="Details">
                                <label for="upECDetails" class="active">
                                    <b>Details</b>
                                </label>
                            </div>
                            <div class="input-field col s6 right">
                                <input id="upECPrice" name="price"
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
                    <a class="waves-effect waves-light purple darken-3 white-text btn-flat" onclick="extraUpdate()">UPDATE
                    </a>
                </div>
            </form>
        </div>


</div>

