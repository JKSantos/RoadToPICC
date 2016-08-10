/**
 * Created by Castillo on 8/10/2016.
 */
window.onload = updatePOTable();

function updatePOTable() {
    $.ajax({
        type: 'post',
        url: 'orders',
        dataType: 'json',
        async: true,
        success: function (data) {
            var orderList = data.orderList,
                ajaxPSTable = $('#productsalestbl').DataTable();

            if (orderList != null) {
                ajaxPSTable.clear().draw();
                $.each(orderList, function (i, order) {
                    if(order.strStatus != "REQUEST") {
                        var type,
                            status;
                        var addbtn = "<button class='waves-effect waves-purple btn-flat transparent black-text'" +
                            " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + order.intSalesID + "'" +
                            " onclick='openUpdateOrder(this.value)'>" +
                            "<i class='material-icons'>edit</i></button>" +
                            "<button id='deactivateID" + order.intSalesID + "' class='waves-effect waves-purple btn-flat transparent red-text text-accent-4'" +
                            " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + order.intSalesID + "' title='Deactivate'" +
                            " onclick='cancelOrder(this.value, this.id)'>" +
                            "<i class='material-icons'>delete</i></button>";
                        if (order.intType == 1) {
                            type = 'Pick-up';
                        } else if (order.intType == 2) {
                            type = 'Delivery';
                        }

                        if(order.strStatus == "PENDING" || order.strStatus == "COMPLETE") {
                            status = order.strStatus;
                        }
                        ajaxPSTable.row.add([
                            order.strName,
                            order.strAddress,
                            order.strContactNo,
                            type,
                            status,
                            addbtn
                        ]);

                        ajaxPSTable.draw();
                    } else {
                        
                    }
                });
            }
        }
    });
}

function cancelOrder(id, deactivateid) {
    var psdata = {
            'intOrderID': id
        },
        $pstr = $('#' + deactivateid).closest('tr'),
        psname = $pstr.find('td:eq(0)').text();
    swal({
            title: "Are you sure you want to cancel the order of " + psname + "?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'deactivateOrder',
                    type: 'post',
                    data: psdata,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        swal("Successfully cancelled!", ".", "success");
                        $pstr.find('td').fadeOut(300, function () {
                            $pstr.remove();
                        });
                        updatePackageTable();
                    },
                    error: function (data) {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}