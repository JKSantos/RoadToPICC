
window.onload = updateProductTable();

function updateProductTable(){
	
	$.ajax({
		type : "POST",
		url : "getAllProduct",
		dataType : "json",
		async : "true",
		success : function(data){
			Materialize.toast('Success.', 3000, 'rounded');
//			if (data.productList != null){
//				var table = $('#prodsaleCRTable').DataTable();
//				var checkbox = "<input type='checkbox' class='filled-in center' name='jjj' id='check1' style='margin-left: 10px !important;'/><label style='margin: 0px !important; padding: 0px !important;' for='check1'></label>";
//        		var quantity = "<input type='number' name='createPackProdQty' style='width: 75px' min='1' max='99' value='1'>"; 
//				table.clear().draw();
//				$.each(data.productList, function(i, product){
//					table.row.add( [
//    	        		            checkbox,
//    	        		            product.strProductName,
//    	        		            product.dblProductPrice,
//    	        		            quantity
//    	        		            ]);
//				});
//				table.draw();
//				
//			}
		},
		error : function(data){
			Materialize.toast('Error occured.', 3000, 'rounded');
		}
	});
	
}