/**
 * Created by Castillo on 9/14/2016.
 */
window.onload = deliveryTable();

$('#upLocationCrFree').click(function () {
    if($(this).is(':checked')) {
        var p = 0.00;
        $('#crLocationBRate').prop('disabled', true);
        $('#crLocationBRate').val(p);
    } else if(!$(this).is(':checked')) {
        $('#crLocationBRate').prop('disabled', false);
    }
});


function deliveryTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllLocation',
        dataType: 'json',
        async: true,
        success: function (data) {
            var locationList = data.locationList,
                tableDelivery;

            $('#deliverytbl').DataTable().destroy();

            tableDelivery = $('#deliverytbl').DataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                responsive: true,
                "order": [],
                "columnDefs": [
                    {"targets": 'no-sort', "orderable": false},
                    {className: "dt-body-left", "targets": [0, 1]},
                    {className: "dt-body-right", "targets": [2]},
                    {className: "dt-head-center", "targets": [3]},
                    {"targets": [3], "width": "200px"},
                    {"targets": [2], "type": "formatted-num"}
                ],
                "rowHeight": '10px'
            });

            $("#deliverySearch").bind('keyup search input paste cut', function () {
                tableDelivery.search(this.value).draw();
            });

            if (locationList != null) {
                tableDelivery.clear().draw();
                $.each(locationList, function (i, location) {
                    var price;

                    price = 'Php ' + parseFloat(location.dblLocationPrice).toFixed(2);
                    var addbtn = "<button class=' waves-effect waves-purple btn-flat transparent black-text'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + location.intLocationID + "'" +
                        " onclick='openUpdateDelivery(this.value)'>" +
                        "<i class='material-icons'>edit</i></button>" +
                        "<button id='deactivateID" + location.intLocationID + "' class='waves-effect waves-purple btn-flat transparent red-text text-accent-4'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + location.intLocationID + "' title='Deactivate'" +
                        " onclick='deactivateDiscount(this.value, this.id)'>" +
                        "<i class='material-icons'>delete</i></button>";

                    tableDelivery.row.add([
                        location.strBarangay,
                        location.strCity,
                        price,
                        addbtn
                    ]);
                });
                tableDelivery.draw();
            }

        },
        error: function (data) {

        }
    });
}

function createDelivery() {
    if($('#createDeliveryForm').valid()) {
        var data = {
            'strBrgy': $('#crLocationBrgy').val(),
            'strCity': $('#crLocationCity').val(),
            'price': $('#crLocationBRate').val().replace(/[^\d.]/g, '')
        };

        $.ajax({
            type: 'post',
            url: 'createNewLocation',
            data: data,
            dataType: 'json',
            async: true,
            success: function (data) {
                if (data.result == 'success') {
                    swal("Successfully created!", ".", "success");
                    deliveryTable();
                    $('#createDeliveryModal').closeModal();
                } else if (data.result == 'failed') {
                    sweetAlert("Oops...", "Something went wrong!", "error");
                } else if (data.result == 'existing') {
                    sweetAlert("Oops...", "This charge is already existing!", "error");
                }
            },
            error: function (data) {
                sweetAlert("Oops...", "Something went wrong!", "error");
            }
        });
    }
}

function openUpdateDelivery(id) {
    $('#updateDiscountModal').openModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 300, // Transition out duration
    });

    $.ajax({
       type: 'post',
        url: ''
    });
}