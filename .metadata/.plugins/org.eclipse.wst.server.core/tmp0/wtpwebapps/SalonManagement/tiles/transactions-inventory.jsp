<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper">

    <div class="main z-depth-barts">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Product Inventory</h3>
            <a class="crinventybtn z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#deflostexModal" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#archive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
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
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-right">Selling Price</th>
                    <th class="dt-head-right">Stock</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </tfoot>

                <tbody>
                <c:forEach items="${productList}" var="product">
                <c:set var="price" scope="session"
                           value="${product.dblProductPrice}"></c:set>
                    <tr>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left">
                            ${product.strProductName}
                        </td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-right prodPrice">
                            ${price}
                        </td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-right">
                            ${product.intProductQuantity}
                        </td>
                        <td class="center" style="padding:0; margin:0;">
                            <a id="update${product.intProductID}" href="#inventoryedit${product.intProductID}"
                               class=" btnEdit modal-trigger waves-effect waves-purple btn-flat transparent black-text empUpdatebtn"
                               style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


    <div id="deflostexModal" class="modal">
        <div class="modal-content">
            <div class="input-field col s8 offset-s2">
                <select name="crInventory" id="crInventory">
                    <option value="defect" selected>Defective Item</option>
                    <option value="lost">Lost Item</option>
                    <option value="expired">Expired Item</option>
                </select>
                <label for="crInventory"><b>Select</b><i
                        class="material-icons red-text tiny">error_outline</i></label>
            </div>
            <div id="defect">
                <form action="createTag" id="defectForm" name="defectForm">
                    <div class="wrapper">
                        <div class="container">
                            <div class="row">
                                <div class="input-field col s12">
                                    <select name="intProductID" id="slctDefItem" required>
                                        <option value="default" disabled selected>Choose...</option>
                                        <c:forEach items="${productList}" var="product">
                                            <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="slctDefItem"><b>Item Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input class="right-align" type="text" name="intQuantity" id="defQty"
                                           maxlength="3" placeholder="1" required>
                                    <label for="defQty"><b>Quantity</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="hidden" name="employees" value="${sessionScope.id}">
                                </div>
                                <div class="input-field col s12" style="margin-top: 5%;">
                                    <input type="hidden" name="intTagType" id="tagTypeDef" value="1"/>
                                </div>
                                <div class="input-field col s12">
                                    <button type="submit" id="crInventDefSubmit"
                                            class="waves-effect waves-white btn-flat purple white-text">SAVE
                                    </button>
                                    <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="lost">
                <form action="createTag" id="lostForm" name="lostForm">
                    <div class="wrapper">
                        <div class="container">
                            <div class="row">
                                <div class="input-field col s12">
                                    <select name="intProductID" id="slctLostItem" required>
                                        <option value="default" disabled selected>Choose...</option>
                                        <c:forEach items="${productList}" var="product">
                                            <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="slctLostItem"><b>Item Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input class="right-align" type="text" name="intQuantity" id="lostQty"
                                           maxlength="3" placeholder="1" required>
                                    <label for="lostQty"><b>Quantity</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                
                                <div class="input-field col s12">
                                    <input type="hidden" name="employees" value="${sessionScope.id}">
                                </div>
                                <div class="input-field col s12" style="margin-top: 5%;">
                                    <input type="hidden" name="intTagType" id="tagTypeLost" value="2"/>
                                </div>
                                <div class="input-field col s12">
                                    <button type="submit" id="crInventLostSubmit"
                                            class="waves-effect waves-white btn-flat purple white-text">SAVE
                                    </button>
                                    <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div id="expired">
                <form action="createTag" id="expiredForm" name="expiredForm">
                    <div class="wrapper">
                        <div class="container">
                            <div class="row">
                                <div class="input-field col s12">
                                    <select name="intProductID" id="slctexpiredItem" required>
                                        <option value="default" disabled selected>Choose...</option>
                                        <c:forEach items="${productList}" var="product">
                                            <option value="${product.intProductID}">${product.strProductName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="slctexpiredItem"><b>Item Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input class="right-align" type="text" name="intQuantity" id="expiredQty"
                                           maxlength="3" placeholder="1" required>
                                    <label for="expiredQty"><b>Quantity</b><i class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="hidden" name="employees" value="${sessionScope.id}">
                                </div>
                                <div class="input-field col s12" style="margin-top: 5%;">
                                    <input type="hidden" name="intTagType" id="tagTypeExpired" value="3"/>
                                </div>
                                <div class="input-field col s12">
                                    <button type="submit" id="crInventExpiredSubmit"
                                            class="waves-effect waves-white btn-flat purple white-text">SAVE
                                    </button>
                                    <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <c:forEach items="${productList}" var="product">
        <div id="inventoryedit${product.intProductID}" class="modal modal-fixed-footer"
             style="width: 30% !important; height: 65% !important; max-height: 100% !important; position: absolute !important;">
            <div class="col s12">
                <ul class="tabs">
                    <li class="tab col s6"><a href="#add${product.intProductID}" class="active purple-text text-darken-2"><b>Add Stock</b></a></li>
                    <li class="tab col s6"><a class="purple-text text-darken-2" href="#subtract${product.intProductID}"><b>Consume Stock</b></a>
                    </li>
                </ul>
            </div>
            <div id="add${product.intProductID}" class="col s12">
                <form class="col s12 addForm" name="addForm" method="post" action="updateStock">
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
                                <input type="number" id="add${product.intProductQuantity}" value="${product.intProductQuantity}" min="0"
                                       max="3" disabled style="color: black;" class="intaddQty">
                                <label for="add${product.intProductQuantity}" class="active black-text">Current Stock</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input name="intQuantity" type="number" id="${product.intProductQuantity}" class="addQty" placeholder="1" min="1">
                                <label for="${product.intProductQuantity}" class="active">Stock (<b>+</b>)</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input class="black-text totalQty" type="number" id="addtotalstock"
                                       value="${product.intProductQuantity}" min="1" disabled>
                                <label for="addtotalstock" class="active black-text">Total Stock</label>
                            </div>
                            
                            <div class="input-field col s12">
                                    <select name="employees" id="empList4">
                                    	<c:forEach items="${ employeeList }" var="emp">
                                    		<option value="${ emp.intEmpID }">${ emp.strEmpFirstName } ${ emp.strEmpLastName }</option>
                                    	</c:forEach>
                                    </select>
                                    
                                    <label for="empList4"><b>Tagged by</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="updateAddInvent waves-effect waves-white btn-flat purple white-text">SAVE</button>
                        <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                    </div>
                </form>
            </div>

            <div id="subtract${product.intProductID}" class="col s12">
                <form class="col s12 minusForm" name="subForm" action="updateStock" method="post">
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
                                <input type="number" id="minus${product.intProductQuantity}" value="${product.intProductQuantity}" min="0"
                                       max="3" class="intminusQty" disabled style="color: black;">
                                <label for="minus${product.intProductQuantity}" class="active black-text">Current Stock</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input name="intQuantity" type="number" class="minusStock" id="${product.intProductQuantity}" placeholder="1" min="1">
                                <label for="${product.intProductQuantity}" class="active">Stock (<b>-</b>)</label>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 5%;">
                                <input class="black-text totalMinusQty" type="number" id="minustotalstock"
                                       value="${product.intProductQuantity}" min="1" disabled>
                                <label for="minustotalstock" class="active black-text">Total Stock</label>
                            </div>
                            
                            <div class="input-field col s12">
                                    <select name="employees" id="empList5">
                                    	<c:forEach items="${ employeeList }" var="emp">
                                    		<option value="${ emp.intEmpID }">${ emp.strEmpFirstName } ${ emp.strEmpLastName }</option>
                                    	</c:forEach>
                                    </select>
                                    
                                    <label for="empList5"><b>Tagged by</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="updateMinusInvent waves-effect waves-white btn-flat purple white-text">SAVE</button>
                        <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                    </div>
                </form>
            </div>
        </div>
    </c:forEach>
    
    
    <div id="archive" class="modal" style="width:80% !important">
        <div class="modal-content">
            <h4 class="grey-text center text-darken-1">Archive</h4>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="empArchiveSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search"/>
                            <label for="empArchiveSearch">
                                <i class="material-icons grey-text text-darken-4">search</i>
                            </label>
                        </div>
                    </form>
                </div>
            </nav>
            <table id="empArchiveTbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;">
                <thead>
                <tr>
                    <th class="no-sort">Tag ID</th>
                    <th class="dt-head-left">Tag Date</th>
                    <th class="dt-head-left left-align" align="left">Product Name</th>
                    <th class="dt-head-lef">Tag Type</th>
                    <th class="dt-head-left">Quantity</th>
                    <th class="dt-head-left">Tagged By</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="no-sort">Tag ID</th>
                    <th class="dt-head-left">Tag Date</th>
                    <th class="dt-head-left left-align" align="left">Product Name</th>
                    <th class="dt-head-lef">Tag Type</th>
                    <th class="dt-head-left">Quantity</th>
                    <th class="dt-head-left">Tagged By</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                	<c:forEach items="${tagList}" var="tag">
	                    <tr>
	                    	<c:set var="tagType" scope="session" value="${ tag.intTagType }"/>
	                    	<c:if test="${tagType == 1}"><c:set var="tagName" value="DEFECTIVE"/></c:if>
	                    	<c:if test="${tagType == 2}"><c:set var="tagName" value="LOST"/></c:if>
	                    	<c:if test="${tagType == 3}"><c:set var="tagName" value="EXPIRED"/></c:if>
	                    	<c:if test="${tagType == 4}"><c:set var="tagName" value="CONSUMED"/></c:if>
	                        <td style="padding:0; margin:0;" class="dt-body-center">
	                            <center>${tag.intTagID}</center>
	                        </td>
	                        <td class="dt-body-left">${tag.dateTagged}</td>
	                        <td style="padding-left: 10px !important; margin-left: 0px;" class="dt-body-left">${tag.product.strProductName}</td>
	                        <td style="padding-right: 10px !important; margin-right: 0px;" class="dt-body-left ">${tagName}</td>
	                        
	                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left ">${tag.intQuantity}</td>
	                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left ">${tag.tagBy.strEmpFirstName} ${tag.tagBy.strEmpLastName}</td>
	                        <td class="center" style="padding:0; margin:0;">
	                        
	                        <form method="post" action="restoreTag">
	                        	<input type="hidden" name="intTagID" value="${tag.intTagID}"/>
	                            <button class="waves-effect waves-light btn-flat green darken-3 white-text"
	                                    id="empArchiveBtn" style="padding-left: 10px;padding-right:10px; margin: 5px;"
	                                    title="Restore">
	                                <i class="material-icons">restore</i>
	                            </button>
	                        </form>
	                        </td>
	                    </tr>
					</c:forEach>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Close</a>
        </div>
    </div>
</div>