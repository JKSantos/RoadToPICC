/**
 * Created by Castillo on 8/4/2016.
 */
function deactivatePackage(id, deactivateid) {
    var packagedata = {
        'intPackageID': id
    },
        $packtr = $('#' + deactivateid).closest('tr'),
        packagename = $packtr.find('td:eq(0)').text();
    swal({
            title: "Are you sure you want to deactivate " + packagename + "?",
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
                        $packtr.find('td').fadeOut(300, function () {
                            $packtr.remove();
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