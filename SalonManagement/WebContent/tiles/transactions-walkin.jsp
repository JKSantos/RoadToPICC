<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Walk-In</h3>
            <a class="crWalkin z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createWalkinModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#walkinModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="walkinSearch" placeholder="Search" class="grey-text text-darken-4"
                                   type="search" required>
                            <label for="walkinSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="walkintbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Guest Type</th>
                    <th class="dt-head-center no-sort">Date</th>
                    <th class="dt-head-left">Status</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left">Guest Type</th>
                    <th class="dt-head-center no-sort">Date</th>
                    <th class="dt-head-left">Status</th>
                    <th align="dt-head-center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal Structure -->
    <div id="createWalkinModal" class="modal modal-fixed-footer">
        <form class="col s12" id="createWalkinForm" method="post" action="">
            <div class="modal-content">
                <!-- <div class="container"> -->
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Walk-In<a id="btnCrExtraExit" type="reset"
                                                                                value="Reset"
                                                                                class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
                    <div class="walkinerrorcontainer card red center input-field col s12 white-text z-depth-barts">

                    </div>
                    <div class="stepwalkin well" style="margin-top: -5px;">
                        <div class="row">
                            <div class="container">
                                <div class="input-field col s12">
                                    <input type="text" class="validate" id="crWIName"
                                           name="" placeholder="Name"/>
                                    <label for="crWIName" class="active"><b>Name</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="text" id="crWIContact" name="" placeholder="contact"/>
                                    <label for="crWIContact" class="active"><b>Contact</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s12" style="margin-top: 15px;">
                                    <input type="email" name="" id="crWIEmail" placeholder="Email"/>
                                    <label for="crWIEmail" class="active"><b>Email</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="stepwalkin well">
                        <div class="row">
                            <nav class="right white z-depth-1" style="width: 300px; margin-right: 20px;">
                                <div class="nav-wrapper col s12">
                                    <form>
                                        <div class="input-field">
                                            <input id="crDiscountSearch" placeholder="Search"
                                                   class="grey-text text-darken-4"
                                                   type="search" style="border: none !important;">
                                            <label for="crDiscountSearch"><i
                                                    class="material-icons grey-text text-darken-4">search</i></label>
                                        </div>
                                    </form>
                                </div>
                            </nav>
                            <div class="col s12">
                                <ul class="collapsible" data-collapsible="accordion">
                                    <li>
                                        <div class="collapsible-header" id="listheadcollapsible"><i
                                                class="material-icons">view_list</i>Products
                                        </div>
                                        <div class="collapsible-body" id="listcollapsible"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                            <div class="tablewrapper">
                                                <table id="crWIProducttbl"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th class="dt-head-center">Quantity</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th class="dt-head-center">Quantity</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                    <c:forEach items="${productList}" var="product">
                                                        <tr>
                                                             <td><input type="checkbox" name="" id="crWIProductCheckbox${product.intProductID}">
                                                            <label for="crWIProductCheckbox${product.intProductID}"></label></td>
                                                        <td>Php ${product.strProductName}</td>
                                                        <td>Php ${product.dblProductPrice}</td>
                                                        <td>
                                                            <input type="number" name="" id="crWIProductQty"/>
                                                            <label for="crWIProductQty"></label>
                                                        </td>
                                                        </tr>
                                                    </c:forEach>
                                                    
                                                    
                                                   
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="collapsible-header" id="listheadcollapsible1"><i
                                                class="material-icons">view_list</i>Services
                                        </div>
                                        <div class="collapsible-body" id="listcollapsible1"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                            <div class="">
                                                <table id="crWIServicetbl"
                                                       class="cell-border row-border display centered responsive-table highlight"
                                                       cellspacing="0" width="100%"
                                                       style="border: 1px solid #bdbdbd; margin-top: -30px !important;"
                                                       rowspan="5">
                                                    <thead>
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th class="dt-head-center">Employee</th>
                                                    </tr>
                                                    </thead>
                                                    <tfoot style="border: 1px solid #bdbdbd;">
                                                    <tr>
                                                        <th class="dt-head-center no-sort">Select</th>
                                                        <th class="dt-head-left">Name</th>
                                                        <th class="dt-head-right">Price</th>
                                                        <th class="dt-head-center">Employee</th>
                                                    </tr>
                                                    </tfoot>

                                                    <tbody>
                                                      <c:forEach items="${serviceList}" var="service">
                                                    <tr>
                                                        <td><input type="checkbox" name="" id="crWIServiceCheckbox${service.intServiceID}">
                                                            <label for="crWIServiceCheckbox${service.intServiceID}"></label></td>
                                                        <td>${service.strServiceName}</td>
                                                        <td>${service.dblServicePrice}</td>
                                                        <td>
                                                            <select name="" id="crWIServiceEmpSelect">
                                                                <option value="default" selected disabled>Employee
                                                                </option>
                                                                <c:forEach items="${employeeList}" var="emp">
                                                  										<option value="${emp.intEmpID}">${emp.strEmpFirstName} ${emp.strEmpMiddleName} ${emp.strEmpLastName}</option>
                                                									</c:forEach>
                                                            </select>
                                                            <label for="crWIServiceEmpSelect"></label>
                                                        </td>
                                                    </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="collapsible-header" id="listheadcollapsible2"><i
                                                class="material-icons">view_list</i>Packages
                                        </div>
                                        
                                        
                                        <div class="collapsible-body" id="listcollapsible2"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                           
                                                <ul class="collapsible" data-collapsible="accordion">
                                                    <c:forEach items="${packageList}" var="packagee">
                                                    <li>
                                                        <div class="collapsible-header">${packagee.strPackageName}</div>
                                                        <div class="collapsible-body">
                                                            <div class="row">
                                                                <div class="input-field col s12">
                                                                    <input type="checkbox" name=""
                                                                           id="crWIPackageCheckbox1"/>
                                                                    <label for="crWIPackageCheckbox1"><b>Select ${packagee.strPackageName}</b></label><p>
                                                                    <table>
                                                                        <thead>
                                                                        <tr>
                                                                            <th>Service Name</th>
                                                                            <th>Employee Assigned</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <c:forEach items="${packagee.serviceList}" var="servicee">
						                                                    <tr>
						                                                        <td>${servicee.service.strServiceName}</td>
						                                                        <td>
						                                                            <select name="" id="crWIServiceEmpSelect">
						                                                                <option value="default" selected disabled>Employee
						                                                                </option>
						                                                                <c:forEach items="${employeeList}" var="emp">
						                                                  										<option value="${emp.intEmpID}">${emp.strEmpFirstName} ${emp.strEmpMiddleName} ${emp.strEmpLastName}</option>
						                                                									</c:forEach>
						                                                            </select>
						                                                            <label for="crWIServiceEmpSelect"></label>
						                                                        </td>
						                                                    </tr>
						                                                    </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                    <table>
                                                                        <thead>
                                                                        <tr>
                                                                            <th>Product Name</th>
                                                                            <th>Quantity</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <c:forEach items="${packagee.productList}" var="products">
						                                                    <tr>
						                                                        <td>${products.product.strProductName}</td>
						                                                        <td>
						                                                            ${products.intProductQuantity}
						                                                        </td>
						                                                    </tr>
						                                                    </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    </c:forEach>
                                                </ul>
                                           
                                        </div>
                                        
                                    </li>
                                    <li>
                                        <div class="collapsible-header" id="listheadcollapsible3"><i
                                                class="material-icons">view_list</i>Promos
                                        </div>
                                        <div class="collapsible-body" id="listcollapsible3"
                                             style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                                            <ul class="collapsible" data-collapsible="accordion">
                                                
                                                <c:forEach items="${promoList}" var="promo">
                                                <li>
                                                    <div class="collapsible-header">
                                                        ${promo.strPromoName}
                                                    </div>
                                                    <div class="collapsible-body">
                                                        <ul class="collapsible" data-collapsible="accordion">
                                                            <li>
                                                                <div class="collapsible-header">Products Included</div>
                                                                <div class="collapsible-body">
                                                                	<table>
                                                                        <thead>
	                                                                        <tr>
	                                                                            <th>Product Name</th>
	                                                                            <th>Quantity</th>
	                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        	<c:forEach items="${promo.productList}" var="products">
							                                                    <tr>
							                                                        <td>${products.product.strProductName}</td>
							                                                        <td>
							                                                            ${products.intProductQuantity}
							                                                        </td>
							                                                    </tr>
						                                                    </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                             </li>
                                                             <li>
                                                                <div class="collapsible-header">Services Included</div>
                                                                <div class="collapsible-body">
                                                                	<table>
                                                                        <thead>
	                                                                        <tr>
	                                                                            <th>Service Name</th>
	                                                                            <th>Assigned Employee</th>
	                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        	<c:forEach items="${promo.serviceList}" var="services">
							                                                    <tr>
							                                                        <td>${services.service.strServiceName}</td>
							                                                        <td>
							                                                             <select name="" id="crWIServiceEmpSelect">
						                                                                	<option value="default" selected disabled>Employee
						                                                                	</option>
						                                                                	<c:forEach items="${employeeList}" var="emp">
						                                                  						<option value="${emp.intEmpID}">${emp.strEmpFirstName} ${emp.strEmpMiddleName} ${emp.strEmpLastName}</option>
						                                                					</c:forEach>
						                                                            	</select>
							                                                        </td>
							                                                    </tr>
						                                                    </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                             </li>
                                                            <li>
                                                            	
                                                                <div class="collapsible-header">Packages Included</div>
                                                                <div class="collapsible-body">
                                                                	<ul class="collapsible" data-collapsible="accordion">
                                                                        	<c:forEach items="${promo.packageList}" var="packagee">
						                                                    <li>
						                                                        <div class="collapsible-header">${packagee.strPackageName}</div>
						                                                        <div class="collapsible-body">
						                                                            <div class="row">
						                                                                <div class="input-field col s12">
						                                                                    <input type="checkbox" name=""
						                                                                           id="crWIPackageCheckbox1"/>
						                                                                    <label for="crWIPackageCheckbox1"><b>Select ${packagee.strPackageName}</b></label><p>
						                                                                    <table>
						                                                                        <thead>
						                                                                        <tr>
						                                                                            <th>Service Name</th>
						                                                                            <th>Employee Assigned</th>
						                                                                        </tr>
						                                                                        </thead>
						                                                                        <tbody>
						                                                                        <c:forEach items="${packagee.serviceList}" var="servicee">
												                                                    <tr>
												                                                        <td>${servicee.service.strServiceName}</td>
												                                                        <td>
												                                                            <select name="" id="crWIServiceEmpSelect">
												                                                                <option value="default" selected disabled>Employee
												                                                                </option>
												                                                                <c:forEach items="${employeeList}" var="emp">
												                                                  										<option value="${emp.intEmpID}">${emp.strEmpFirstName} ${emp.strEmpMiddleName} ${emp.strEmpLastName}</option>
												                                                									</c:forEach>
												                                                            </select>
												                                                            <label for="crWIServiceEmpSelect"></label>
												                                                        </td>
												                                                    </tr>
												                                                    </c:forEach>
						                                                                        </tbody>
						                                                                    </table>
						                                                                    <table>
						                                                                        <thead>
						                                                                        <tr>
						                                                                            <th>Product Name</th>
						                                                                            <th>Quantity</th>
						                                                                        </tr>
						                                                                        </thead>
						                                                                        <tbody>
						                                                                        <c:forEach items="${packagee.productList}" var="products">
												                                                    <tr>
												                                                        <td>${products.product.strProductName}</td>
												                                                        <td>
												                                                            ${products.intProductQuantity}
												                                                        </td>
												                                                    </tr>
												                                                    </c:forEach>
						                                                                        </tbody>
						                                                                    </table>
						                                                                </div>
						                                                            </div>
						                                                        </div>
						                                                    </li>
						                                                    </c:forEach>
                                                                        </ul>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </li>
                                </ul>
                            </div>

                            <div class="col s12 z-depth-barts white" id="">
                                <h6 class="center" style="padding-top: -2px !important;"><b>Selected Items</b></h6>
                                <div class="col s12" id="pslist"
                                     style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>
                            </div>
                            <div class="col s4 offset-s4" style="margin-top: 10px;">
                                <div class="input-field col s12">
                                    <select multiple id="crRDiscount">
                                        <option value="" disabled selected>Choose your option</option>
                                        <c:forEach items="${ discountList }" var="discount">
                                        	<option value="${discount.intDiscountID}">${discount.strDiscountName}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="crRDiscount"><b>Discounts</b></label>
                                </div>
                            </div>
                            <div class="col s4 z-depth-barts white" style="margin-top: 10px;">
                                <div class="col s12">
                                    <div class="input-field col s12">
                                        <p>Total: <span class="right-align"></span></p>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 20px !important;">
                                        <input type="text" class="right-align prodPrice" name="" id="crPackPrice"
                                               placeholder="Price"/>
                                        <label for="crPackPrice" class="active"><b>Price</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                </div>
                            </div>
                            <!--<div class="col s12 z-depth-barts white prodservlist" style="margin-top: 5px;"-->
                            <!--id="servContainer">-->
                            <!--<h6 class="center" style="padding-top: -2px !important;"><b>Service List</b></h6>-->
                            <!--<div class="col s6" id="servList"-->
                            <!--style="margin-top: -13px !important; margin-bottom: 5px !important;"></div>-->
                            <!--</div>-->
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button class="red-text btn-flat transparent left" disabled
                        style="margin:0px !important; padding:0px !important;"><i
                        class="material-icons">error_outline</i>&nbspRequired field
                </button>
                <button type="button" id="nextbtn"
                        class="actionwalkin nextformwalkin waves-effect waves-light white-text btn-flat purple"
                        style="margin-left: 3px; margin-right:3px;">NEXT
                </button>
                <button type="submit" value="submit" id="createReservationSubmitForm"
                        class="actionwalkin submitformwalkin waves-effect waves-light white-text btn-flat purple"
                        style="margin-left:3px; margin-right:3px;">CREATE
                </button>
                <button type="button" id="backbtn"
                        class="actionwalkin backformwalkin waves-effect waves-purple transparent btn-flat"
                        style="margin-left: 3px;margin-right:3px;">BACK
                </button>

            </div>
        </form>
    </div>


</div>