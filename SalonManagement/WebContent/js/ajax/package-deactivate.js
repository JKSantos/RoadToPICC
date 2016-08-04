/**
 * Created by Castillo on 8/4/2016.
 */
function deactivatePackage(id) {
    var packagedata = {
        'intPackageID': id
    };
    swal({
            title: "Deactivate this package?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'deactivatePackage',
                    type: 'post',
                    data: packagedata,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        swal("Successfully deactivated!", ".", "success");
                        updatePackageTable();
                    },
                    error: function (data) {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}