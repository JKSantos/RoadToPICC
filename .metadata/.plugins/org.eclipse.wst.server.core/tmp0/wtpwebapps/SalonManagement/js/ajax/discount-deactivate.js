/**
 * Created by Castillo on 9/8/2016.
 */
function deactivateDiscount(valueID, ID) {
    var discountData = {
            'intDiscountID': valueID
        },
        $discounttr = $('#' + ID).closest('tr'),
        discountName = $discounttr.find('td:eq(0)').text();
    swal({
            title: "Are you sure you want to deactivate " + discountName + "?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'deactivateDiscount',
                    type: 'post',
                    data: discountData,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        swal("Successfully deactivated!", ".", "success");
                        $discounttr.find('td').fadeOut(300, function () {
                            $discounttr.remove();
                        });
                        updateDiscountTable();
                    },
                    error: function (data) {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}