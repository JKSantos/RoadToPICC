/**
 * Created by Castillo on 9/14/2016.
 */
window.onload = deliveryTable();

$('#upLocationCrFree').click(function () {
    if ($(this).is(':checked')) {
        var p = 0.00;
        $('#crLocationBRate').prop('disabled', true);
        $('#crLocationBRate').val(parseFloat(p).toFixed(2));
    } else if (!$(this).is(':checked')) {
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
                    if (location.dblLocationPrice > 0) {
                        price = 'Php ' + parseFloat(location.dblLocationPrice).toFixed(2);
                    } else {
                        price = 'Free';
                    }

                    var addbtn = "<button class=' waves-effect waves-purple btn-flat transparent black-text'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + location.intLocationID + "'" +
                        " onclick='openUpdateDelivery(this.value)'>" +
                        "<i class='material-icons'>edit</i></button>" +
                        "<button id='deactivateID" + location.intLocationID + "' class='waves-effect waves-purple btn-flat transparent red-text text-accent-4'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + location.intLocationID + "' title='Deactivate'" +
                        " onclick='deleteDelivery(this.value, this.id)'>" +
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
    if ($('#createDeliveryForm').valid()) {
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

$('#upPromoFree').click(function () {
    if ($(this).is(':checked')) {
        var p = 0.0;
        $('#upLocationBRate').prop('disabled', true).val(parseFloat(p).toFixed(2));
    } else if (!$(this).is(':checked')) {
        $('#upLocationBRate').prop('disabled', false).val('');
    }
});

function openUpdateDelivery(id) {
    $('#updateDeliveryModal').openModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 300, // Transition out duration
    });

    $.ajax({
        type: 'post',
        url: 'getLocationByID',
        data: {
            'id': id
        },
        success: function (data) {
            var loc = data.location;

            $('#intLocationID').val(loc.intLocationID);
            $('#upLocationBrgy').val(loc.strBarangay);
            $('#upLocationCity').val(loc.strCity);
            if (loc.dblLocationPrice > 0) {
                $('#upLocationBRate').prop('disabled', false).val('Php ' + parseFloat(loc.dblLocationPrice).toFixed(2));
                $('#upPromoFree').prop('checked', false);
            } else {
                $('#upLocationBRate').prop('disabled', true).val('0.0');
                $('#upPromoFree').prop('checked', true);
            }
        },
        error: function (data) {
        }
    });
}

function updateDelivery() {
    if($('#updateDeliveryForm').valid()) {
        var data = {
            'intLocationID': $('#intLocationID').val(),
            'strBrgy': $('#upLocationBrgy').val(),
            'strCity': $('#upLocationCity').val(),
            'price': $('#upLocationBRate').val().replace(/[^\d.]/g, '')
        };

        $.ajax({
            type: 'post',
            url: 'updateNewLocation',
            data: data,
            dataType: 'json',
            async: true,
            success: function(data) {
                if (data.result == 'success') {
                    swal("Successfully created!", ".", "success");
                    deliveryTable();
                    $('#updateDeliveryModal').closeModal();
                } else if (data.result == 'failed') {
                    sweetAlert("Oops...", "Something went wrong!", "error");
                } else if (data.result == 'existing') {
                    sweetAlert("Oops...", "This charge is already existing!", "error");
                }
            },
            error: function(data) {
                sweetAlert("Oops...", "Something went wrong!", "error");
            }
        });
    }
}

function deleteDelivery(id, idid) {
    var data = {
            'intLocationID': id
        },
        $deltr;
    $deltr = $('#' + idid).closest('tr'),
    swal({
            title: "Are you sure you want this to deactivate?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'deactivateNewLocation',
                    type: 'post',
                    data: data,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        swal("Successfully deactivated!", ".", "success");
                        $deltr.find('td').fadeOut(300, function () {
                            $deltr.remove();
                        });
                        deliveryTable();
                    },
                    error: function (data) {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}