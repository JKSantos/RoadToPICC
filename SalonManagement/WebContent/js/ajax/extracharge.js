/**
 * Created by Castillo on 8/1/2016.
 */
window.onload = updateExtraTable();

function updateExtraTable() {

    $.ajax({
        type: 'POST',
        url: 'api/v1/getAllOtherCharge',
        dataType: 'json',
        async: true,
        success: function (data) {
            var extraCharge = data.extraChargeList;
            var servPrice = "servPrice";
            var extraTable;

            $('#extratbl').DataTable().destroy();

            extraTable = $('#extratbl').DataTable({
                destroy: true,
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

            $("#extraSearch").bind('keyup search input paste cut', function () {
                extratbl.search(this.value).draw();
            });

            if (extraCharge != null) {
                extraTable.clear().draw();
                $.each(extraCharge, function (i, extra) {


                    var price = parseFloat(extra.dblECPrice).toFixed(2);
                    price = addCommas(price);
                    var addbtn = "<button class='waves-effect waves-purple modal-trigger btn-flat transparent black-text' " +
                        "onclick='extraOpenUpdate(this.value)' value='" + extra.intECID + "' style='padding-left: 10px;padding-right:10px; margin: 5px;'> " +
                        "<i class='material-icons'>edit</i></button> " +
                        "<button class='extradeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4'" +
                        "style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + extra.intECID + "'" +
                        "title='Deactivate' onclick='extraDeactivate(this.value)'><i class='material-icons'>delete</i></button>";
                    var chargename = "<td>" + extra.strECName + "</td>",
                        details,
                        price = "<td><span class='price'>Php " + price + "</span></td>",
                        btn = "<td>" + addbtn + "</td>";
                    if (extra.strECDetails.length > 0) {
                        details = "<td>" + extra.strECDetails + "</td>"
                    } else {
                        details = "<td>None</td>"
                    }

                    extraTable.row.add([
                        chargename,
                        details,
                        price,
                        btn
                    ]);
                });
                extraTable.draw();
            }

        }

    });
}

function addCommas(nStr) {
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}

function createExtra() {
    $('#createExtraForm').trigger("reset");
    var extraName = $('#crECName').val(),
        extraDetails = $('#crECDetails').val(),
        extraPrice = $('#crECPrice').val().replace(/[^\d.]/g, '');


    var extradata = {
        "strECName": extraName,
        "strECDetails": extraDetails,
        "price": extraPrice
    };
    swal({
            title: "Create this charge?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'createExtraCharge',
                    type: 'post',
                    data: extradata,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        if (data.result == "success") {
                            swal("Successfully updated!", ".", "success");
                            updateExtraTable();
                            $('#createExtraChargeModal').closeModal();
                        } else if (data.result == "existing") {
                            sweetAlert("Oops...", "This charge is already existing!", "error");
                        } else if (data.result == "failed") {
                            sweetAlert("Oops...", "Something went wrong!", "error");
                        }
                    },
                    error: function () {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}


function extraOpenUpdate(id) {
    $.ajax({
        type: 'get',
        url: 'api/v1/getAllOtherCharge',
        dataType: 'json',
        async: true,
        success: function (data) {
            for (var i = 0; i < data.extraChargeList.length; i++) {
                var intecid = parseInt(data.extraChargeList[i].intECID);
                if (intecid === parseInt(id)) {
                    var price = parseFloat(data.extraChargeList[i].dblECPrice).toFixed(2);
                    price = addCommas(price);
                    $('#updateExtraModal').openModal({
                        dismissible: false, // Modal can be dismissed by clicking outside of the modal
                        opacity: .9, // Opacity of modal background
                        in_duration: 200, // Transition in duration
                        out_duration: 200, // Transition out duration
                    });
                    $('#upECID').val(id)
                    $('#upECName').val(data.extraChargeList[i].strECName);
                    $('#upECDetails').val(data.extraChargeList[i].strECDetails);
                    $('#upECPrice').val('Php ' + price);
                }
            }


        }
    });
}

function extraUpdate() {
    if($('#updateExtraForm').valid()) {
        var extradata = {
            'intECID': $('#upECID').val(),
            'strECName': $('#upECName').val(),
            'strECDetails': $('#upECDetails').val(),
            'price': $('#upECPrice').val()
        }
        swal({
                title: "Update this charge?",
                text: "",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            },
            function () {
                setTimeout(function () {
                    $.ajax({
                        url: 'updateExtraCharge',
                        type: 'post',
                        data: extradata,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                            if(data.result == "success") {
                                swal("Successfully updated!", ".", "success");
                                updateExtraTable();
                                $('#updateExtraModal').closeModal();
                            } else if (data.result == "existing") {
                                sweetAlert("Oops...", "This charge is already existing!", "error");
                            } else if (data.result == "failed") {
                                sweetAlert("Oops...", "This charge is already existing!", "error");
                            }
                        },
                        error: function () {
                            sweetAlert("Oops...", "This charge is already existing!", "error");
                        }
                    });
                }, 1000);
            });
    }
}

function extraDeactivate(id) {
    var extradata = {
        'intECID': id
    }
    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            $.ajax({
                url: 'deactivateExtraCharge',
                type: 'post',
                data: extradata,
                dataType: 'json',
                async: true,
                success: function (data) {
                    swal("Successfully deactivated!", ".", "success");
                    updateExtraTable();
                },
                error: function (data) {
                    sweetAlert("Oops...", "Something went wrong!", "error");
                }
            });
        });
}