
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



            <div class="aside aside2 z-depth-barts">
                <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                    <h3 class="grey-text text-darken-1">Product Sales</h3>

                    <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text"
                       href="#CreateProdSaleModal" style="margin-top: 50px; margin-bottom: 20px; margin-left: 15px;"
                       data-delay="30"
                       data-position="bottom" data-tooltip="Create"><i class="material-icons">add</i></a>
                    <table id="prodsaleTable" class="display centered responsive-table highlight" cellspacing="0"
                           width="100%"
                           style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Contact Number</th>
                            <th>Type</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>John Angelo Barot</td>
                            <td>Sto. Niño, Bulacan</td>
                            <td>09999999999</td>
                            <td>Delivery</td>
                            <td>PENDING</td>
                            <td class="center" style="padding:0; margin:0;">
                            <a data-delay="30" data-position="bottom" data-tooltip="View Details"
                               class="waves-effect waves-purple modal-viewall btn-flat transparent black-text tooltipped"
                               href="#view" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">visibility</i>
                            </a>
                            <a data-delay="30" data-position="bottom" data-tooltip="Update Order"
                               class="waves-effect waves-purple modal-trigger btn-flat transparent black-text tooltipped"
                               href="#update" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <a data-delay="30" data-position="bottom" data-tooltip="Cancel Order"
                               class="waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4 tooltipped"
                               href="#cancel" style="padding-left: 10px; padding-right:10px; margin: 5px;"
                               title="Deactivate"><i class="material-icons">cancel</i></a>
                        </td>	
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>

            <div id="CreateProdSaleModal" class="modal modal-fixed-footer"
                 style="width: 50% !important; height: 70% !important; max-height: 100% !important; margin-top: -40px;">
                <form class="col s12" id="crProdSaleForm" name="createWalkinForm">
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
                                <div class="step well" style="margin-top: 20px;">
                                    <div class="container">
                                        <div class="input-field col s12">
                                            <select name="strOrderType" id="crOrderType" required>
                                                <option value="default" disabled selected>Choose...</option>
                                                <option value="pickup">Pick Up</option>
                                                <option value="delivery">Delivery</option>
                                            </select>
                                            <label for="crOrderType"><b>Order Type</b><i
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
                                                	<option value="${location.intLocationID}">${location.strBarangay}, ${location.strCity}</option>
                                                </c:forEach>
                                            </select>
                                            <label for="crOrderLocation"><b>Location</b><i
                                                    class="material-icons red-text tiny">error_outline</i></label>
                                        </div>
                                        <div class="input-field col s6">
                                            <input type="text" name="strOrderContact" id="crOrderContact"/>
                                            <label for="crOrderContact"><b>Contact Number</b><i
                                                    class="material-icons red-text tiny">error_outline</i></label>
                                        </div>
                                        <div class="input-field col s6" id="crDivPickDate">
                                            <input type="date" name="strOrderDate" id="crOrderPickupDate"
                                                   class="datepicker"/>
                                            <label for="crOrderPickupDate"><b>Pickup Date</b><i
                                                    class="material-icons red-text tiny">error_outline</i></label>
                                        </div>
                                        <div class="input-field col s6" id="crDivDelivDate">
                                            <input type="date" name="strOrderDate" id="crOrderDeliveryDate"
                                                   class="datepicker"/>
                                            <label for="crOrderDeliveryDate"><b>Delivery Date</b><i
                                                    class="material-icons red-text tiny">error_outline</i></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="step well">
                                    <div class="col s12">
                                        <table id="prodsaleCRTable" class="display centered responsive-table highlight"
                                               cellspacing="0" width="100%"
                                               style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                            <thead>
                                            <tr>
                                                <th></th>
                                                <th>Name</th>
                                                <th>Description</th>
                                                <th>Price</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <td><input type="checkbox" class="filled-in center" name="" id="check1"
                                                           style="margin-left: 10px !important;"/><label
                                                        style="margin: 0px !important; padding: 0px !important;"
                                                        for="check1"></label></td>
                                                <td></td>
                                                <td>BARTS</td>
                                                <td>1999</td>
                                            </tr>
                                            </tbody>
                                        </table>
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
                        <button type="button" class="action backform waves-effect waves-purple transparent btn-flat"
                                style="margin-left: 3px;margin-right:3px;">BACK
                        </button>
                        <button type="button" id="nextbtn"
                                class="action nextform waves-effect waves-light white-text btn-flat purple"
                                style="margin-left: 3px; margin-right:3px;">NEXT
                        </button>
                        <button type="submit" value="Submit" id="crCreateOrderBtn"
                                class="action submitform waves-effect waves-light white-text btn-flat purple"
                                style="margin-left:3px; margin-right:3px;" onclick="createOrder()">CREATE
                        </button>
                    </div>
                </form>
            </div>


            <!-- UPDATE Product Sales -->
            <div id="updateps" class="modal modal-fixed-footer"
                 style="width: 60% !important; height: 93% !important; max-height: 100% !important; margin-top: -30px;">
                <form class="col s12" name="createWalkinForm" method="post" action="createTag">
                    <div class="modal-content">
                        <div class="wrapper">
                            <h4 class="grey-text text-darken-1">
                                <center>Update Order</center>
                            </h4>
                            <div class="aside asideA z-depth-0">
                                <div class="row">
                                    <div class="input-field col s4">
                                        <select class="multiple" id="upprodsaleot">
                                            <option selected disabled>Order Type...</option>
                                        </select>
                                        <label for="upprodsaleot">Order Type</label>
                                    </div>
                                    <div class="input-field col s4 offset-s4">
                                        <input type="text" id="upprodsalepud">
                                        <label class="active" for="upprodsalepud">Delivery/Pick-Up Date</label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 4px;">
                                        <input type="text" id="upprodsalename">
                                        <label for="upprodsalename" class="active">Name</label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 4px;">
                                        <input type="text" id="upprodsaleaddress">
                                        <label for="upprodsaleaddress" class="active">Address</label>
                                    </div>
                                    <div class="input-field col s6" style="margin-top: 4px;">
                                        <select class="multiple" id="upprodsalepnp">
                                            <option selected disabled class="black-text">Product
                                                Name-----------------------------Price
                                            </option>
                                        </select>
                                        <label for="upprodsalepnp">Product Name</label>
                                    </div>
                                    <div class="input-field col s3" style="margin-top: 4px;">
                                        <input type="number" min="0" id="upprodsaleqty">
                                        <label for="upprodsaleqty" class="active">Quantity</label>
                                    </div>
                                    <div class="input-field col s2" style="margin-top: 4px">
                                        <button class="waves-effect waves-light purple white-text btn-flat"><i
                                                class="material-icons">add</i></button>
                                    </div>
                                    <table>
                                        <thead>
                                        <tr>
                                            <th>Product Name</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                    </table>
                                    <div class="input-field col s4" style="margin-top: 90px !important;">
                                        <input type="number" disabled class="black-text" id="upprodsaletp">
                                        <label for="upprodsaletp" class="active black-text">Total Price</label>
                                    </div>
                                    <div class="input-field col s4" style="margin-top: 90px !important;">
                                        <input type="number" min="0" id="upprodsalepa">
                                        <label for="upprodsalepa" class="active">Payment Amount</label>
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
            <!-- END -->



<style type="text/css">
    .wrapper > * {
        flex: 1 100%;
    }

    .wrapper {
        display: -webkit-box;
        display: -moz-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        flex-direction: row;

        -webkit-flex-flow: row wrap;
        flex-flow: row wrap;
        -moz-flex-flow: row wrap;
        -o-flex-flow: row wrap;
    }

    .asideA {
        background: transparent;
        border-radius: 1px;
        margin: 10px;
        text-align: center;
        width: 25px;
        height: 30%;
    }

    @media all and (min-width: 500px) {
        .aside {
            flex: 1 auto;
        }

    }

    .add + input[type="text"] {
        width: 10px !important;
    }


</style>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="./js/productsales.js"></script>
<script type="text/javascript">

window.onload = updateProductTable();
window.onload = updateOrderTable();

function createOrder(){
	
	var name = $('#crOrderName').val();
	var street = $('#crOrderStreet').val();
	var location = $('#crOrderLocation').val();
	var contact = $('#crOrderContact').val();
	var type = $('#crOrderType').val();
	var products = $("input[name='products']:checked").map(function() {
		return this.value;}).get().toString();
	var quantity = $("input[name='createPackProdQty']").map(function(){
		return this.value;}).get().toString();
	
	alert(location);
	
	$.ajax({
		type : "POST",
		url : "createOrder",
		dataType : "json",
		async : "true",
		data : {
			"strName" : name,
			"strStreet" : street,
			"intLocationID" : location,
			"strContactNo" : contact,
			"orderType" : type,
			"selectedProducts" : products,
			"productQuantity" : quantity
		},
		success: function(data){
			
			if(data.status==="success"){
				Materialize.toast('Order Successfully Created!', 3000, 'rounded');
		   		
			}
			else if(data.status==="failed"){
				Materialize.toast('An error was occured while saving the order!', 3000, 'rounded');
		   		
			}
		},
		error: function(data){
			Materialize.toast('Fatal Error was occurred, please contact your system administrator to fix this.', 3000, 'rounded');
	   		
		}
		
	});
}

function updateProductTable(){
	
	$.ajax({ 
		type : "POST",
		url : "getAllProduct",
		dataType : "json",
		async : "true",
		success : function(data){
			
			if (data.productList != null){
				var table = $('#prodsaleCRTable').DataTable();
				table.clear().draw();
				
				$.each(data.productList, function(i, product){
					
					var checkbox = "<input type='checkbox' class='filled-in center' name='products' id='" + product.intProductID + "' value='" + product.intProductID + "' style='margin-left: 10px !important;'/><label style='margin: 0px !important; padding: 0px !important;' for='" + product.intProductID + "'></label>";
	        		var quantity = "<input type='number' name='createPackProdQty' style='width: 75px' min='1' max='99'>"; 
					
					table.row.add( [
    	        		            checkbox,
    	        		            product.strProductName,
    	        		            product.dblProductPrice,
    	        		            quantity
    	        		            ]);
				});
				table.draw();
				
			}
		},
		error : function(data){
			Materialize.toast('Error occured.', 3000, 'rounded');
		}
	});
	
}

function updateOrderTable(){
	
	$.ajax({ 
		type : "POST",
		url : "orders",
		dataType : "json",
		async : "true",
		success : function(data){
			
			if (data.orderList != null){
				var table = $('#prodsaleTable').DataTable();
				table.clear().draw();
				
				$.each(data.orderList, function(i, order){
					
					var a = "<a data-delay='30' data-position='bottom' data-tooltip='View Details' class='waves-effect waves-purple modal-viewall btn-flat transparent black-text tooltipped' href='#view' style='padding-left: 10px;padding-right:10px; margin: 5px;'><i class='material-icons'>visibility</i></a>";
                    var b = "<a data-delay='30' data-position='bottom' data-tooltip='Update Order'";
                    var c = "class='waves-effect waves-purple modal-trigger btn-flat transparent black-text tooltipped'";
                    var d = "href='#update' style='padding-left: 10px;padding-right:10px; margin: 5px;'><i class='material-icons'>edit</i></a>'";
                    var e = "<a data-delay='30' data-position='bottom' data-tooltip='Cancel Order'";
                    var f = "class='waves-effect waves-purple modal-trigger btn-flat transparent red-text tooltipped'";
                    var g = "href='#cancel' style='padding-left: 10px;padding-right:10px; margin: 5px;' title='Deactivate'><i class='material-icons'>cancel</i></a>'";   
                    
                    var buttons = a + b + c + d + e + f + g;
                    
					var type = order.intType;
					var strType = "";
					var status = order.intStatus;
					var strStatus = "";
					
					if(type == 1)
						strType = "DELIVERY";
					else
						strType = "PICK-UP";

					table.row.add( [
    	        		            order.strName,
    	        		            order.strAddress,
    	        		            order.strContactNo,
    	        		            strType,
    	        		            strStatus,
    	        		            buttons
    	        		            ]);
				});
				table.draw();
				updateOrderTable();
				
			}
		},
		error : function(data){
			Materialize.toast('Error occured.', 3000, 'rounded');
		}
	});
	
}

</script>

<script type="text/javascript">
    $(document).ready(function () {
        $('ul.tabs').tabs('select_tab', 'add');
    });

</script>
