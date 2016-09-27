/**
 * Created by Castillo on 7/8/2016.
 */
/**
 * Created by Castillo on 7/7/2016.
 */
window.onload = updateTable;


function createEmployee() {

    if($('#createEmpForm').valid()){

        var file = document.getElementById('createImage').value;
        var file1 = file.replace(/C:\\fakepath\\/i, '');
        console.log(file1);
        var imgext = file1.split('.')[1];
        var imgname = file1.split('.')[0];
        console.log(imgext);
        console.log(imgname);
        // var upload = $('#createImage').val().replace(/C:\\fakepath\\/i, '');
        // var upload = document.getElementById('#createImage').value.replace(/C:\\fakepath\\/i, '');
        // var imgext = upload.split('.')[1];
        // var imgname = upload.split('.')[0];
        var fname = $('#strCrEmpFname').val();
        var mname = $('#strCrEmpMname').val();
        var lname = $('#strCrEmpLname').val();
        var bday = $('#createBirthday').val();
        var gender = $('#createGender').val();
        var contact = $('#createContact').val();
        var email = $('#createEmail').val();
        var address = $('#createAddress').val();
        var job = document.querySelectorAll('input[name=selectedJob]:checked').value;
        console.log(job);
        // var job = $('#crSelectedJob :selected').map(function(i,el){
        //     return $(el).val();
        // });
        var check = document.querySelectorAll('input[name=chkGrantAccess]:checked').value;

        $.ajax({

            type: 'POST',
            url: 'createEmployee',
            data: {
                "file": file1,
                // "contentType": imgext,
                // "filename": imgname,
                "strEmpFirstName": fname,
                "strEmpMiddleName": mname,
                "strEmpLastName": lname,
                "strBirthdate": bday,
                "strEmpGender": gender,
                "strEmpContactNo": contact,
                "strEmpEmail": email,
                "strEmpAddress": address,
                "selectedJob": job,
                "chkGrantAccess": check
            },
            dataType: 'json',
            async: true,
            success: function (data) {
                if(data.result==='success'){
                    console.log(data);
                }

            },
            error: function(data) {
                console.log('error');
            }
        });
    }


}



function updateTable() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SalonManagement/api/v1/getAllEmployee',
        dataType: 'json',
        async: true,
        success: function (data) {
            var employeeList = data.employeeList;
            if (employeeList != null) {
                var table = $('#employeeTable').DataTable();
                table.clear().draw();

                $.each(employeeList, function (i, employee) {
                    var btn = "<center><button class='waves-effect waves-purple btn-flat transparent black-text'" +
                        " style='padding: 0px 8px 0px 8px; '><i class='material-icons'>visibility</i></button>" +
                        "<button class='waves-effect waves-purple btn-flat transparent black-text' onclick='openEmpUpdate(this.value)'" +
                        " value='" + employee.intEmpID + "' style='padding: 0px 8px 0px 8px;'><i class='material-icons'>edit</i></button>" +
                        "<a class='waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4' href='#de<%=de%>'" +
                        " style='padding: 0px 8px 0px 8px;' title='Deactivate'><i class='material-icons'>delete</i></a></center>";

                    table.row.add([
                        employee.strEmpFirstName,
                        employee.strEmpMiddleName,
                        employee.strEmpLastName,
                        btn
                    ]);

                });
                table.draw();
            }
        },
        error: function (data) {
            alert('Error');
        }
    });
}

function openEmpUpdate(intEmpID) {

    $('#empModalUpdate').openModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    });
    $.ajax({
        type: 'POST',
        url: 'getEmployeeByID',
        data: {
            "intEmpID": intEmpID
        },
        async: true,
        dataType: 'json',
        success: function (data) {
            console.log(data.employee.intEmpID);
            $('#intEmpID').val(data.employee.intEmpID);
            $('#updateEmpFname').val(data.employee.strEmpFirstName);
        },
        error: function () {
            alert('error');
        }
    });
}


function clickclick () {
	var data = {
			'students': [{
				'intStudID': 1,
				'strFName': 'John Angelo',
				'strLName': 'Castillo',
				'strMName': 'Castillo',
				'strAddess': 'Meycauayan, Bulacan',
				'strContactNo': '12313131'
			}, {
				'intStudID': 2,
				'strFName': 'Jeff',
				'strLName': 'Santos',
				'strMName': 'Santos',
				'strAddess': 'Pasig',
				'strContactNo': '12313131'
			}]
	}

	$.ajax({
		url: 'createStudents',
		method: 'post',
		data: data,
		dataType: 'json',
		async: true,
		success: function(data) {
			console.log(data);
		},
		error: function(data) {
			console.log(data);
		}
		
	});
}

