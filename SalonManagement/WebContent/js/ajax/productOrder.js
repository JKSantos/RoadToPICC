/**
 * Created by Castillo on 8/9/2016.
 */
window.onload = updatePORequest();


function updatePORequest() {
    var request = [],
        reqList = $('#reqList');
    $.ajax({
        url: 'orders',
        type: 'post',
        dataType: 'json',
        async: true,
        success: function (data) {
            var orderList = data.orderList;
            if (orderList != null) {
                request.length = 0;
                reqList.empty();
                $.each(orderList, function (i, order) {
                    if (order.strStatus == 'REQUEST') {
                        var strname;
                        if (order.strName.length > 15) {
                            strname = order.strName.substring(0, 15-3) + '...';
                        } else {
                            strname = order.strName;
                        }
                        request.unshift('<li class="collection-item left-align" style="margin-left: 0px !important; padding-left: 10px !important;">' +
                            '<div><i class="material-icons" style="margin-right: 10px !important;">local_shipping</i>' +
                            '<span title="' + order.strName + '" style="margin-top: -10px !important;">' + strname +
                            '</span><a onclick="declineRequest(' + order.intSalesID + ')" title="Accept" class="secondary-content red-text">' +
                            '<i class="material-icons">clear</i>' +
                            '</a>' +
                            '<a href="#" name="' + order.strName + '" onclick="acceptRequest(' + order.intSalesID + ', this.name)" title="Decline" class="secondary-content black-text">' +
                            '<i class="material-icons">done</i>' +
                            '</a></div></li>');
                    }
                });
                reqList.append(request.join(''));
            }
        }
    });

}

function acceptRequest(id, name) {
    console.log(id);
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

                    },
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        swal("Successfully created!", ".", "success");
                        updatePackageTable();
                        $('#createPackageModal').closeModal();
                    },
                    error: function () {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}