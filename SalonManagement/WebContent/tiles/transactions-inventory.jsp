<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper">

    <div class="main z-depth-barts">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Inventory</h3>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#deflostexModal" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#packageArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="inventorySearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search">
                            <label for="inventorySearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="inventorytbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0" width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-right">Selling Price</th>
                    <th class="dt-head-right">Stock</th>
                    <th class="dt-head-right">Date/Time</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-right">Selling Price</th>
                    <th class="dt-head-right">Stock</th>
                    <th class="dt-head-right">Date/Time</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${productList}" var="product">
                    <tr>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left">
                            ${product.strProductName}
                        </td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-right prodPrice">
                            ${product.dblProductPrice}
                        </td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-right">
                            ${product.intProductQuantity}
                        </td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-right">date/time
                        </td>
                        <td class="center" style="padding:0; margin:0;">
                            <a data-delay="30" data-position="bottom" data-tooltip="View"
                               class="waves-effect waves-purple modal-viewall btn-flat transparent black-text"
                               style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">visibility</i>
                            </a>
                            <a id="${product.intProductID}" href="#inventoryedit${product.intProductID}"
                               class=" btnEdit modal-trigger waves-effect waves-purple btn-flat transparent black-text empUpdatebtn"
                               style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <button class="packagedeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                    style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                    id="${product.intProductID}" title="Deactivate"><i class="material-icons">delete</i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


    <div id="deflostexModal" class="modal modal-fixed-footer">
        <form class="col s12" id="deflostexpForm" name="deflostexpForm" method="post" action="createTag">
            <div class="modal-content">
                <div class="wrapper">
                    <div class="row">
                        <div class="col s12">
                            <div class="inventoryerror center input-field col s12 card red white-text z-depth-barts"></div>
                            <ul class="tabs tab-demo-active" style="width: 100%; background-color: #fafafa;">
                                <li class="tab col s6"><a
                                        class="firsttab purple-text text-darken-2 active waves-effect waves-light"
                                        href="#defectiveitem"><b>Defective Item</b></a></li>
                                <li class="tab col s6"><a
                                        class="secondtab purple-text text-darken-2 waves-effect waves-light"
                                        href="#lostitem"><b>Lost Item</b></a></li>
                                <li class="tab col s6"><a
                                        class="thirdtab purple-text text-darken-2 waves-effect waves-light"
                                        href="#expitem"><b>Expired Item</b></a></li>
                            </ul>
                        </div>
                        <div id="defectiveitem" class="col s12" style="margin-top: 40px;">
                            <div class="container">
                                <div class="input-field col s12">
                                    <select name="intProductID" id="slctDefItem" required>
                                        <option value="default" disabled selected>Choose...</option>
                                        <c:forEach items="${productList}" var="product">
                                            <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="slctDefItem"><b>Item Name</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <input class="right-align" type="text" name="intQuantity" id="defQty"
                                           maxlength="3" placeholder="1" required>
                                    <label for="defQty"><b>Quantity</b></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 5%;">
                                    <input type="hidden" name="intTagType" id="tagTypeDef" value="1"/>
                                </div>
                            </div>
                        </div>
                        <div id="lostitem" class="col s12" style="margin-top: 40px;">
                            <div class="container">
                                <div class="input-field col s12">
                                    <select name="intProductID" id="slctLostItem" required>
                                        <option value="default" disabled selected>Choose...</option>
                                        <c:forEach items="${productList}" var="product">
                                            <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="slctLostItem"><b>Item Name</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <input class="right-align" type="text" name="intQuantity" id="lostQty"
                                           maxlength="3" placeholder="1" required>
                                    <label for="lostQty"><b>Quantity</b></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 5%;">
                                    <input type="hidden" name="intTagType" id="tagTypeLost" value="2"/>
                                </div>
                            </div>
                        </div>
                        <div id="expitem" class="col s12" style="margin-top: 40px;">
                            <div class="container">
                                <div class="input-field col s12">
                                    <select name="intProductID" id="slctExpItem" required>
                                        <option value="default" disabled selected>Choose...</option>
                                        <c:forEach items="${productList}" var="product">
                                            <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="slctExpItem"><b>Item Name</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <input class="right-align" type="text" name="intQuantity" id="expQty"
                                           maxlength="3" placeholder="1" required>
                                    <label for="expQty"><b>Quantity</b></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 5%;">
                                    <input type="hidden" name="intTagType" id="tagTypeExp" value="3"/>
                                </div>
                            </div>
                        </div>
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
        <div id="inventoryedit${product.intProductID}" class="modal modal-fixed-footer"
             style="width: 30% !important; height: 65% !important; max-height: 100% !important; position: absolute !important;">
            <div class="col s12">
                <ul class="tabs">
                    <li class="tab col s6"><a href="#add${product.intProductID}">Increase Stock</a></li>
                    <li class="tab col s6"><a class="active" href="#subtract${product.intProductID}">Decrease Stock</a>
                    </li>
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
                                <input type="text" id="prodname" value="${product.strProductName}" disabled
                                       style="color: black !important;">
                                <label for="prodname" style="color: black !important;">Product Name</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input type="number" id="addcurrentstock" value="${product.intProductQuantity}" min="0"
                                       max="3" disabled style="color: black;">
                                <label for="addcurrentstock" class="active black-text">Current Stock</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input name="intQuantity" type="number" id="addstocknumber" placeholder="1" min="0">
                                <label for="addstocknumber" class="active">Stock (<b>+</b>)</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input class="black-text" type="number" id="addtotalstock"
                                       value="${product.intProductQuantity}" min="0" disabled>
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
                                <input type="text" id="subprodname" value="${product.strProductName}" disabled
                                       style="color: black !important;">
                                <label for="subprodname" style="color: black !important;">Product Name</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input type="number" id="subcurrentstock" value="${product.intProductQuantity}" min="0"
                                       max="3" disabled style="color: black;">
                                <label for="subcurrentstock" class="active black-text">Current Stock</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input name="intQuantity" type="number" id="substocknumber" placeholder="1" min="0">
                                <label for="substocknumber" class="active">Stock (<b>-</b>)</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input class="black-text" type="number" id="subtotalstock"
                                       value="${product.intProductQuantity}" min="0" disabled>
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
</div>