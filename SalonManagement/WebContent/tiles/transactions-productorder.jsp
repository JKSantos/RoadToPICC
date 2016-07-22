<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Product Sales</h3>
            <a class="pscrbtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#crProductSales" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#prodsalesArchive" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="psSearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                   required/>
                            <label for="psSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="productsalestbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Address</th>
                    <th class="dt-head-right no-sort">Contact</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-left">Status</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Address</th>
                    <th class="dt-head-right no-sort">Contact</th>
                    <th class="dt-head-left">Type</th>
                    <th class="dt-head-left">Status</th>
                    <th class="no-sort dt-head-center">Action</th>
                </tr>
                </tfoot>

                <tbody>
                </tbody>
            </table>
        </div>
    </div>


    <div id="crProductSales" class="modal modal-fixed-footer"
         style="width: 50% !important; height: 70% !important; max-height: 100% !important; margin-top: -40px;">
        <form class="col s12" id="createPSForm" name="createPSForm" method="post">
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Order
                        <a id="btnCreateExit"
                           class="modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a>
                    </h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
                    <div class="row">
                        <div class="stepps well" style="margin-top: 20px;">
                            <div class="container">
                                <div class="input-field col s6">
                                    <select name="strOrderType" id="crOrderType">
                                        <option value="default" disabled selected>Choose...</option>
                                        <option value="pickup">Pick Up</option>
                                        <option value="delivery">Delivery</option>
                                    </select>
                                    <label for="crOrderType"><b>Order Type</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>

                                <div class="input-field col s6">
                                    <input type="text" name="strOrderContact" id="crOrderContact"/>
                                    <label for="crOrderContact"><b>Contact Number</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" name="strOrderName" id="crOrderName"/>
                                    <label for="crOrderName"><b>Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6" id="crDivOrderSt">
                                    <input type="text" name="strOrderStreet" id="crOrderStreet"/>
                                    <label for="crOrderStreet"><b>Street</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6" id="crDivOrderLoc">
                                    <select name="strOrderLocation" id="crOrderLocation">
                                    	<option value="default">Choose...</option>
                                    	<c:forEach items="${locationList}" var="location">
                                        	<option value="${location.intLocationID}">${location.strBarangay}, ${location.strCity},</option>
                                        </c:forEach>
                                    </select>
                                    <label for="crOrderLocation"><b>Location</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>
                        </div>
                        <div class="stepps well">

                                <nav class="right white z-depth-1 col s6 offset-s6 hoverable" style="width: 300px; margin-right: 20px;">
                                    <div class="nav-wrapper col s12">
                                        <form>
                                            <div class="input-field">
                                                <input id="crpsSearch" placeholder="Search"
                                                       class=" grey-text text-darken-4"
                                                       type="search" style="border: none !important;">
                                                <label for="crpsSearch"><i
                                                        class="material-icons grey-text text-darken-4">search</i></label>
                                            </div>
                                        </form>
                                    </div>
                                </nav>
                            <div class="col s12">
                                <ul class="collapsible" data-collapsible="accordion">
                                    <li>
                                        <div class="collapsible-header" id="listheadcollapsible"><i
                                                class="material-icons">view_list</i>List
                                        </div>
                                        <div class="collapsible-body" id="listcollapsible"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                            <table id="crpstbl"
                                                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                                                   cellspacing="0"
                                                   width="100%"
                                                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;"
                                                   rowspan="10">
                                                <thead>
                                                <tr>
                                                    <th class="no-sort"></th>
                                                    <th class="dt-head-left">Name</th>
                                                    <th class="dt-head-left">Description</th>
                                                    <th class="dt-head-right">Price</th>
                                                    <th class="dt-head-right">Quantity</th>
                                                </tr>
                                                </thead>
                                                <tfoot style="border: 1px solid #bdbdbd;">
                                                <tr>
                                                    <th class="no-sort"></th>
                                                    <th class="dt-head-left">Name</th>
                                                    <th class="dt-head-left">Description</th>
                                                    <th class="dt-head-right">Price</th>
                                                    <th class="dt-head-right">Quantity</th>
                                                </tr>
                                                </tfoot>

                                                <tbody>
                                                <tr>
                                                    <td class="no-sort"></td>
                                                    <td class="dt-body-left">Name</td>
                                                    <td class="dt-body-left">Description</td>
                                                    <td class="dt-body-right">Price</td>
                                                    <th class="dt-body-right">Quantity</th>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </li>
                                </ul>
                                <div class="col s9 z-depth-barts white prodservlist" id="prodservContainer">
                                    <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                    <div class="col s12" id="pslist"
                                         style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                                </div>
                                <div class="col s3">
                                    <div class="input-field col s12" style="margin-top: 20px !important;">
                                        <input type="text" class="right-align prodPrice" name="" id="crPackPrice" disabled
                                               placeholder="Price" style="color: #424242 !important; border: none !important;"/>
                                        <label for="crPackPrice" style="color: #424242 !important;" class="active"><b>Price</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="red-text btn-flat transparent left" disabled
                        style="margin:0px !important; padding:0px !important;"><i
                        class="material-icons">error_outline</i>&nbspRequired field
                </button>
                <button type="button" class="actionps backformps waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>
                <button type="button" id="nextbtn"
                        class="actionps nextformps waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button type="submit" value="Submit" id="crCreateOrderBtn" onclick="createProductSale()"
                        class="actionps submitformps waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px;">CREATE
                </button>
            </div>
        </form>
    </div>


</div>

