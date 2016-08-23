function createBlock(){
		
		var  = document.getElementById("floorIdBlockToCreate").value;
		
		if (blockName == null || blockName == "" || blockName == " " ||
				unitType == null || unitType == "" || unitType == " "){
			
		}else{
			$.ajax({
				type: "POST",
				url: "create",
				data: {
					"block.floorId" : floorId,
					"block.strBlockName" : blockName,
					"block.strUnitType" : unitType,
					"block.intLevelNo" : levelNo,
					"block.intColumnNo" : columnNo
				},
				dataType: "json",
				async: true,
				success: function(data){
					if (data.status === "success"){
						Materialize.toast('Block is successfully created.', 3000, 'rounded');
						$('#modalCreateBlock').closeModal();
						updateTable();
					}else if (data.status === "failed-existing"){
						Materialize.toast('Block already exists.', 3000, 'rounded');
					}else if (data.status === "failed-database"){
						Materialize.toast('Please check your connection.', 3000, 'rounded');
					}else if (data.status === "input"){
						Materialize.toast('Please check all your inputs.', 3000, 'rounded');
					}
				},
				error: function(data){
					Materialize.toast('Error occured.', 3000, 'rounded');
				}
			});
			
		}
		
	}