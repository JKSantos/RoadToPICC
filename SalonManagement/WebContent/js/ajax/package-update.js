/**
 * Created by Castillo on 8/2/2016.
 */
//update package
window.onload = updatePackageTable();
window.onload = updatePackageProductTable();
window.onload = updatePackageServiceTable();

function updatePackageProductTable() {
    $.ajax({
        type: 'get',
        url: 'api/v1/getAllProduct',
        dataType: 'json',
        async: true,
        success: function (data) {
            var updateProductList = data.productList,
                table = $('#uppackageProdtbl').DataTable({
                    "bLengthChange": false,
                    "sPaginationType": "full_numbers",
                    responsive: true,
                    "order": [],
                    "columnDefs": [
                        {"targets": 'no-sort', "orderable": false},
                        {className: "dt-body-left", "targets": [1, 2]},
                        {className: "dt-body-center", "targets": [0]},
                        {className: "dt-head-right", "targets": [3, 4]},
                        {"targets": [4], "width": "100px"}
                    ],
                    "rowHeight": '10px'
                });

            $(".uppackageSearch").bind('keyup search input paste cut', function () {
                table.search(this.value).draw();
            });

            if (updateProductList != null) {
                table.clear().draw();
                $.each(updateProductList, function (i, product) {
                    var price = parseFloat(product.dblProductPrice).toFixed(2);
                    price = addCommas(price);

                    var checkbox = "<input type='checkbox' name='updatePackProdType' id='updateProdCheck" + product.intProductID + "'" +
                            " value='" + product.intProductID + "'><label for='updateProdCheck" + product.intProductID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='updatePackProdQty'" +
                            " id='upProdQty" + product.intProductID + "' style='width: 75px' disabled value='1' maxlength='2'>";
                    price = "<span class='price'>P " + price + "</span>";

                    table.row.add([
                        checkbox,
                        product.strProductName,
                        product.strProductCategory,
                        price,
                        quantity
                    ]);
                });
                table.draw();
            }
        }
    });
}

function updatePackageServiceTable() {
    $.ajax({
        type: 'get',
        url: 'api/v1/getAllService',
        dataType: 'json',
        async: true,
        success: function (data) {
            var updateServiceList = data.serviceList,
                table = $('#uppackageServtbl').DataTable({
                    "bLengthChange": false,
                    "sPaginationType": "full_numbers",
                    responsive: true,
                    "order": [],
                    "columnDefs": [
                        {"targets": 'no-sort', "orderable": false},
                        {className: "dt-body-left", "targets": [1, 2]},
                        {className: "dt-body-center", "targets": [0]},
                        {className: "dt-head-right", "targets": [4]}
                    ],
                    "rowHeight": '10px'
                });
            $(".uppackageSearch").bind('keyup search input paste cut', function () {
                table.search(this.value).draw();
            });

            if (updateServiceList != null) {
                table.clear().draw();
                $.each(updateServiceList, function (i, service) {
                    var price = parseFloat(service.dblServicePrice).toFixed(2);
                    price = addCommas(price);

                    var checkbox = "<input type='checkbox' name='updatePackServType' id='updateServCheckBox" + service.intServiceID + "'" +
                            " value='" + service.intServiceID + "'><label for='updateServCheckBox" + service.intServiceID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='updatePackServQty'" +
                            " id='upServQty" + service.intServiceID + "' style='width: 75px' disabled value='1' maxlength='2'>";
                    price = "<span class='price'>P " + price + "</span>";

                    table.row.add([
                        checkbox,
                        service.strServiceName,
                        service.strServiceCategory,
                        price,
                        quantity
                    ]);
                });
                table.draw();
            }
        }
    });
}

$('#packageloadingupdate').bind('ajaxStart', function () {
   $(this).show();
}).bind('ajaxStop', function () {
    $(this).fadeOut();
});

function openUpdatePackage(id) {
    $('#updatePackageModal').openModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 300, // Transition out duration
    });
    $.ajax({
        type: 'get',
        url: 'api/v1/getAllPackage',
        dataType: 'json',
        async: true,
        beforeSend: function () {
            $('#packageloadingupdate').show();
        },
        success: function (data) {
            for (var i = 0; i < data.packageList.length; i++) {
                var packageid = data.packageList[i].intPackageID,
                    packagetype = data.packageList[i].intPackageType,
                    packageselecttype = $('select[name=intUpdatePackageType] option'),
                    select = $('select');
                if (parseInt(packageid) == parseInt(id)) {
                    console.log(data.packageList[i].intPackageID);
                    $('#upPackageName').val(data.packageList[i].strPackageName);
                    $('#upPackageID').val(data.packageList[i].intPackageID);
                    $('#upPackageDesc').val(data.packageList[i].strPackageDesc);
                    console.log(packagetype);
                    switch (packagetype) {
                        case 1:
                            packageselecttype.each(function () {
                                if($(this).val() == 1) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).attr('selected', 'selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 2:
                            packageselecttype.each(function () {
                                if($(this).val() == 2) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).attr('selected', 'selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 3:
                            packageselecttype.each(function () {
                                if($(this).val() == 3) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).attr('selected', 'selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 4:
                            packageselecttype.each(function () {
                                if($(this).val() == 1 || $(this).val() == 2) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).attr('selected', 'selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 5:
                            packageselecttype.each(function () {
                                if($(this).val() == 1 || $(this).val() == 3) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).attr('selected', 'selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 6:
                            packageselecttype.each(function () {
                                if($(this).val() == 2 || $(this).val() == 3) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).attr('selected', 'selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 7:
                            packageselecttype.each(function () {
                                if($(this).val() == 1 || $(this).val() == 2 || $(this).val() == 3) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).attr('selected', 'selected');
                                    select.material_select();
                                }
                            });
                            break;
                    }
                }
            }
        },
        complete: function () {
            $('#packageloadingupdate').fadeOut(400);
        }
    });
    $('#uppackageProdtbl').parents('div.tablewrapper').first().show();
    $('#uppackageServtbl').parents('div.tablewrapper').first().hide();
}
