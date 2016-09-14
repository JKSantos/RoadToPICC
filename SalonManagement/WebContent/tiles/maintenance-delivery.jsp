<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Location"%>
<!-- <div class="container"> -->

<style>
    .dataTables_filter {
        display: none;
    }
</style>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Transportation Charge Maintenance</h3>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createDeliveryModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-viewall btn purple darken-2 left white-text"
               href="#deliveryArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="deliverySearch" placeholder="Search" class="grey-text text-darken-4" type="search" required>
                            <label for="deliverySearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
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
                    <th class="dt-head-left">Barangay</th>
                    <th class="dt-head-left">City</th>
                    <th class="dt-head-right">Base Rate</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Barangay</th>
                    <th class="dt-head-left">City</th>
                    <th class="dt-head-right">Base Rate</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

    <!-- ARCHIVE BEGIN -->
    <div id="deliveryArchive" class="modal modal-fixed-footer">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Archive</h4>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="deliveryArchiveSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search">
                            <label for="deliveryArchiveSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <table id="deliveryArchiveTbl"
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
                    <tr>
                        <td class="dt-body-left"></td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left "></td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-right prodPrice">
                        </td>
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

    <div id="createDeliveryModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createDeliveryForm">
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
                        <div class="input-field col s6 left">
                            <input name="upLocationFree" type="checkbox" class="filled-in"
                                   id="upLocationCrFree" value="on"/>
                            <label for="upLocationCrFree"><b>Free</b></label>
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
                <a class="waves-effect waves-light purple darken-3 white-text btn-flat"
                   id="createDeliveryBtn"
                   onclick="createDelivery()">
                    CREATE
                </a>
            </div>
        </form>
    </div>
    
    <div id="updateDeliveryModal" class="updateDeliveryModal modal modal-fixed-footer">
        <form class="col s12 updateDeliveryForm" id="updateDeliveryForm">
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
                                   name="intLocationID" id="intLocationID">

                            <input type="text" class="validate" id="upLocationBrgy"
                                   name="strBrgy" required placeholder="Baranggay">
                            <label for="upLocationBrgy" class="active"><b>Barangay</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s12" style="margin-top: 25px;">
                            <input type="text" class="validate" id="upLocationCity"
                                   name="strCity" required
                                   placeholder="City">
                            <label for="upLocationCity" class="active"><b>City</b><i
                                    class="material-icons red-text tiny">error_outline</i></label>
                        </div>
                        <div class="input-field col s6 left">
                            <input name="upLocationFree" type="checkbox" class="filled-in"
                                   id="upPromoFree" value="on"/>
                            <label for="upPromoFree"><b>Free</b></label>
                        </div>
                        <div class="input-field col s6 right">
                            <input id="upLocationBRate" name="price"
                                   class="validate upProdItemPrice right-align" required placeholder="Base Rate">
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
                <a class="upLocCancel modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL
                </a>
                <a class="waves-effect waves-light purple darken-3 white-text btn-flat" onclick="updateDelivery()">UPDATE
                </a>
            </div>
        </form>
    </div>




</div>