/**
 * Created by Castillo on 8/9/2016.
 */
window.onload = updatePORequest();

var request = [];
function updatePORequest() {
    var reqList = $('#reqList');
    $.ajax({
        url: 'getAllProductRequest',
        type: 'post',
        dataType: 'json',
        async: true,
        success: function (data) {
            var productSalesList = data.productSalesList;
            if (productSalesList != null) {
                request.length = 0;
                reqList.empty();
                $.each(productSalesList, function (i, orderList) {
                    var strname,
                        orderTypeIcon,
                        orderAcceptIcon,
                        orderDeclineIcon;
                    if (orderList.strName.length > 11) {
                        strname = orderList.strName.substring(0, 11 - 3) + '...';
                    } else {
                        strname = orderList.strName;
                    }

                    if (orderList.intType == 1) {
                        orderTypeIcon = '<i class="material-icons left" style="margin-right:10px !important; margin-top: -3px !important;">local_shipping</i>';
                        orderAcceptIcon = '<button name="' + orderList.strName + '" onclick="acceptDeliveryRequest(' + orderList.intSalesID + ', this.name)" ' +
                            'title="Accept" class="secondary-content black-text transparent" id="poList' + orderList.intSalesID + '"' +
                            'style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border: 0px !important;">' +
                            '<i class="material-icons" style="padding-top: 7px !important;">done</i>' +
                            '</button>';
                        orderDeclineIcon = '<button name="' + orderList.strName + '" onclick="declineRequest(' + orderList.intSalesID + ', this.name)" ' +
                            'title="Decline" class="secondary-content red-text transparent"' +
                            'style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;">' +
                            '<i class="material-icons" style="padding-top: 7px !important;">clear</i>' +
                            '</button>';
                    } else {
                        orderTypeIcon = '<i class="material-icons left" style="margin-right:10px !important; margin-top: -3px !important;">shopping_basket</i>';
                        orderAcceptIcon = '<button name="' + orderList.strName + '" onclick="acceptPickupRequest(' + orderList.intSalesID + ', this.name)" ' +
                            'title="Accept" class="secondary-content black-text transparent" id="poList' + orderList.intSalesID + '"' +
                            'style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border: 0px !important;">' +
                            '<i class="material-icons" style="padding-top: 7px !important;">done</i>' +
                            '</button>';
                        orderDeclineIcon = '<button name="' + orderList.strName + '" onclick="declineRequest(' + orderList.intSalesID + ', this.name)" ' +
                            'title="Decline" class="secondary-content red-text transparent"' +
                            'style="padding: 0px !important; margin-top: -10px !important; margin-bottom: 0 !important; border:0px !important;">' +
                            '<i class="material-icons" style="padding-top: 7px !important;">clear</i>' +
                            '</button>';
                    }

                    request.unshift('<li class="collection-item left-align" style="margin-left:0px !important; padding-left: 10px !important;">' +
                        orderTypeIcon +
                        '<span title="' + orderList.strName + '" style="padding-top: 0px !important; margin-top: 13px !important;">' +
                        strname +
                        '</span>' + orderDeclineIcon + orderAcceptIcon +
                        '</li>');
                });
                reqList.append(request.join(''));
            }
        }
    });

}

function acceptDeliveryRequest(id, name) {
    $('#AcceptDeliveryModal').openModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .7, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    });

    $('#datDeliveryID').val(id);
    $('#requestName').append(name);
}

function submitDeliveryDate() {
    var id = $('#datDeliveryID').val(),
        date = $('#deliveryDate').val(),
        name = $('#requestName').text();

    var data = {
        "intOrderID": id,
        "datDeliveryDate": date
    };

    var poList = $('#poList' + id);

    swal({
            title: "Are you sure you want to set this as delivery date for " + name + "?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'acceptOrder',
                    type: 'post',
                    data: data,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        if (data.result == "success") {
                            swal("Order request was successfully accepted!", ".", "success");
                            poList.parent().remove();
                            $('#AcceptDeliveryModal').closeModal();
                        } else {
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

function acceptPickupRequest(id, name) {

    var poList = $('#poList' + id);

    swal({
            title: "Accept order of " + name + "?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'acceptOrder',
                    type: 'post',
                    data: {
                        "intOrderID": id
                    },
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        if (data.result == "success") {
                            swal("Order request was successfully accepted!", ".", "success");
                            poList.parent().remove();
                        } else {
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

function declineRequest(id, name) {

    var poList = $('#poList' + id);

    swal({
            title: "Decline order of " + name + "?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'declineOrder',
                    type: 'post',
                    data: {
                        "intOrderID": id
                    },
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        swal("Order request was successfully declined!", ".", "success");
                        poList.parent().remove();
                    },
                    error: function () {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}