/**
 * Created by Castillo on 8/1/2016.
 */
window.onload = updatePackageTable();
window.onload = createPackageProductTable();
window.onload = createPackageServiceTable();

function updatePackageTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllPackage',
        dataType: 'json',
        async: true,
        success: function (data) {
            var packageList = data.packageList,
                tablepackage;


            $('#packagetbl').DataTable().destroy();

                tablepackage = $('#packagetbl').DataTable({
                destroy: true,
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                responsive: true,
                "order": [],
                "columnDefs": [
                    {"targets": 'no-sort', "orderable": false},
                    {className: "dt-body-left", "targets": [0, 1, 2]},
                    {className: "dt-body-right", "targets": [3]},
                    {className: "dt-head-right", "targets": [3]},
                    {className: "dt-head-center", "targets": [0]},
                    {"targets": [0], "width": "230px"},
                    {"targets": [3], "width": "200px"},
                    {"targets": [2], "width": "250px"},
                    {"targets": [2], render: $.fn.dataTable.render.ellipsis(25)}
                ],
                "rowHeight": '10px'
            });

            $("#packageSearch").bind('keyup search input paste cut', function () {
                packagetbl.search(this.value).draw();
            });

            if (packageList != null) {
                tablepackage.clear().draw();
                $.each(packageList, function (i, package) {
                    var type;
                    var addbtn = "<button class='waves-effect waves-purple btn-flat transparent black-text'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + package.intPackageID + "'" +
                        " onclick='openViewPackage(this.value)'>" +
                        "<i class='material-icons'>visibility</i></button>" +
                        "<button class='packUpdatebtn waves-effect waves-purple btn-flat transparent black-text'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + package.intPackageID + "'" +
                        " onclick='openUpdatePackage(this.value)'>" +
                        "<i class='material-icons'>edit</i></button>" +
                        "<button id='deactivateID" + package.intPackageID + "' class='waves-effect waves-purple btn-flat transparent red-text text-accent-4'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + package.intPackageID + "' title='Deactivate'" +
                        " onclick='deactivatePackage(this.value, this.id)'>" +
                        "<i class='material-icons'>delete</i></button>";
                    var price = 'Php ' + parseFloat(package.dblPackagePrice).toFixed(2);
                    price = addCommas(price);
                    if (package.intPackageType == 1) {
                        type = 'Event';
                    } else if (package.intPackageType == 2) {
                        type = 'Home Service';
                    } else if (package.intPackageType == 3) {
                        type = 'Walk-In';
                    } else if (package.intPackageType == 4) {
                        type = 'Event, Home Service';
                    } else if (package.intPackageType == 5) {
                        type = 'Event, Walk In';
                    } else if (package.intPackageType == 6) {
                        type = 'Walk In, Home Service';
                    } else {
                        type = 'Event, Home Service, Walk In';
                    }
                    tablepackage.row.add([
                        package.strPackageName,
                        type,
                        package.strPackageDesc,
                        price,
                        addbtn
                    ]);
                });
                tablepackage.draw();
            }
        }
    });
}


function createPackageProductTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllProduct',
        dataType: 'json',
        async: true,
        success: function (data) {
            var productList = data.productList,
                createPackageProdTable = $('#crpacktblProd').DataTable({
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

            $("#crpackageSearch").bind('keyup search input paste cut', function () {
                createPackageProdTable.search(this.value).draw();
            });

            if (productList != null) {
                createPackageProdTable.clear().draw();
                $.each(productList, function (i, product) {
                    var price = parseFloat(product.dblProductPrice).toFixed(2);
                    price = addCommas(price);
                    var checkbox = "<input type='checkbox' name='createPackProdType' id='prodCheck" + product.intProductID + "' required" +
                            " class='packcheckbox x" + product.intProductID + "' value='" + product.intProductID + "' onclick='compute(this.value)'>" +
                            "<label for='prodCheck" + product.intProductID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='createPackProdQty'" +
                            " id='prodqty" + product.intProductID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                    price = "<span class='price'>P " + price + "</span>";

                    createPackageProdTable.row.add([
                        checkbox,
                        product.strProductName,
                        product.strProductCategory,
                        price,
                        quantity
                    ]);
                });
                createPackageProdTable.draw();
            }
        }
    });
}

function createPackageServiceTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllService',
        dataType: 'json',
        async: true,
        success: function (data) {
            var serviceList = data.serviceList,
                createPackageServTable = $('#crpacktblServ').DataTable({
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

            $("#crpackageSearch").bind('keyup search input paste cut', function () {
                createPackageServTable.search(this.value).draw();
            });

            if (serviceList != null) {

                var promoType = 0;

                $('#crPackageType').on('change', function() {
                    createPackageServTable.clear().draw();
                    var type = $(this).val(),
                        walk = 0,
                        home = 0,
                        event = 0;
                    for (var i = 0; i < type.length; i++) {
                        if (type[i] == 3) {
                            walk = 1;
                        } else if (type[i] == 2) {
                            home = 1;
                        } else if (type[i] == 1) {
                            event = 1;
                        }
                    }

                    if (walk == 1 && home == 0 && event == 0) {
                        promoType = 1;
                    } else if (walk == 0 && home > 0 && event == 0) {
                        promoType = 2;
                    } else if (walk == 0 && home == 0 && event == 1) {
                        promoType = 3;
                    } else if (walk == 1 && home == 1 && event == 0) {
                        promoType = 4;
                    } else if (walk == 1 && home == 0 && event == 1) {
                        promoType = 5;
                    } else if (walk == 0 && home == 1 && event == 1) {
                        promoType = 6;
                    } else if (walk == 1 && home == 1 && event == 1) {
                        promoType = 7;
                    }

                    $.each(serviceList, function (i, service) {
                        if(promoType == service.serviceType) {
                            var price = parseFloat(service.dblServicePrice).toFixed(2);
                            price = addCommas(price);
                            var checkbox = "<input type='checkbox' name='createPackServType' id='myCheckBox" + service.intServiceID + "' required" +
                                    " class='packcheckbox x" + service.intServiceID + "' value='" + service.intServiceID + "' onclick='serviceCompute(this.value)'>" +
                                    "<label for='myCheckBox" + service.intServiceID + "'></label>",
                                quantity = "<input type='number' class='right-align rowQty' name='createPackServQty'" +
                                    " id='svc" + service.intServiceID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                            price = "<span class='price'>P " + price + "</span>";

                            createPackageServTable.row.add([
                                checkbox,
                                service.strServiceName,
                                service.strServiceCategory,
                                price,
                                quantity
                            ]);
                        }
                    });
                    createPackageServTable.draw();
                });


            }
        }
    });
}
//CREATE PACKAGE
var q = 0, // temporary quantity
    $qty = 0, //main quantity
    total = 0, //total
    i = 0,
    chk = 0;
function compute(id) {
    var productID = $('#prodCheck' + id);
    if (productID.is(':checked')) { //if product checkbox is checked
        var $prodTR = productID.closest('tr'), // PRODUCT TR
            prodPrice = $prodTR.find('td:eq(3)').text(),
            $productPrice = parseFloat(prodPrice.replace(/[^\d.]/g, '')).toFixed(2), // PRODUCT PRICE (CONVERTED ALREADY)
            $productQtyField = $prodTR.find('td #prodqty' + id), // quantity field of product
            productquantity = parseFloat($productQtyField.val()).toFixed(2);

        chk = chk + 1;
        console.log(chk);
        if(chk < 1) {
            $('#createSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if(chk > 0) {
            $('#createSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#prodqty' + id).attr('disabled', false);

        $productQtyField.focus(function () {
            q = parseFloat($prodTR.find('td #prodqty' + id).val()).toFixed(2);
            $qty = parseFloat($prodTR.find('td #prodqty' + id).val()).toFixed(2);
        });

        $qty = productquantity;
        total += $qty * $productPrice;
        $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
        $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));

        q = productquantity;
        var prodshowqty = parseInt($qty); // for display quantity in selected items
        $productQtyField.on('input', function () {
            $qty = parseFloat($prodTR.find('td #prodqty' + id).val()).toFixed(2);
            if ($qty > q) {
                total += ($qty - q) * $productPrice;
                prodshowqty = parseInt($qty);
                total = Math.abs(total);
                $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
                $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));
                $('#pslist #prodx' + id + '').remove();
                $('#pslist #proditem' + id + ' .span').append('<span class="grey-text text-darken-3" id="prodx' + id + '"> (' + prodshowqty + ')</span>');
            } else if (q > $qty) {
                total -= (q - $qty) * $productPrice;
                total = Math.abs(total);
                prodshowqty = parseInt($qty);
                $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
                $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));
                $('#pslist #prodx' + id + '').remove();
                $('#pslist #proditem' + id + ' .span').append('<span class="grey-text text-darken-3" id="prodx' + id + '"> (' + prodshowqty + ')</span>');
            } else {

            }
            q = $qty;
        });

        var prodName = $prodTR.find('td:eq(1)').text();

        $('#pslist').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            ' id="proditem' + id + '"><b>' + prodName + '</b><span class="span"><span class="grey-text text-darken-3" id="prodx' + id + '">' +
            ' (' + prodshowqty + ')</span></span>' + '<i id="prodchip' + id + '" class="material-icons" style="margin-right: 5px' +
            '!important">close</i></div>').show();

    } else if (!(productID.is(':checked'))) {
        chk = chk - 1;
        console.log(chk);
        if(chk < 1) {
            $('#createSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if(chk > 0) {
            $('#createSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var prodidqty = $('#prodqty' + id),
            $unprodTR = productID.closest('tr'),
            unprice = $unprodTR.find('td:eq(3)').text(),
            $unprice = parseFloat(unprice.replace(/[^\d.]/g, '')).toFixed(2);
        $qty = parseFloat($unprodTR.find('td #prodqty' + id).val()).toFixed(2);
        total = total - ($qty * $unprice);
        total = Math.abs(total);
        $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
        $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));

        $('#proditem' + id).remove();
        prodidqty.attr('disabled', true);
        prodidqty.val(1);

    }

    $('#prodchip' + id).click(function () {
        chk = chk - 1;
        console.log(chk);
        if(chk < 1) {
            $('#createSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if(chk > 0) {
            $('#createSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var prodchipqty = $('#prodqty' + id),
            $utr = productID.closest('tr'),
            unchipprice = $utr.find('td:eq(3)').text(),
            $unchipprice = parseFloat(unchipprice.replace(/[^\d.]/g, '')).toFixed(2);
        $qty = parseFloat($utr.find('td #prodqty' + id).val()).toFixed(2);
        total = total - ($qty * $unchipprice);
        total = Math.abs(total);
        $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
        $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));

        $('#proditem' + id).remove();
        $('#prodCheck' + id).prop('checked', false);
        prodchipqty.attr('disabled', true);
        prodchipqty.val(1);
    });


}

function serviceCompute(id) {

    var serviceID = $('#myCheckBox' + id);
    if (serviceID.is(':checked')) { //if service checkbox is checked
        $('#svc' + id).attr('disabled', false);
        //
        chk = chk + 1;
        console.log(chk);
        if(chk < 1) {
            $('#createSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if(chk > 0) {
            $('#createSubmitForm').attr('disabled', false).css('opacity', '1');
        }
        //
        var $servTR = serviceID.closest('tr'), // SERVICE TR
            servPrice = $servTR.find('td:eq(3)').text(),
            $servicePrice = parseFloat(servPrice.replace(/[^\d.]/g, '')).toFixed(2), //SERVICE PRICE (CONVERTED ALREADY)
            $serviceQtyField = $servTR.find('td #svc' + id), // quantity field of service
            servicequantity = parseFloat($serviceQtyField.val()).toFixed(2);
        $serviceQtyField.focus(function () {
            q = parseFloat($servTR.find('td #svc' + id).val()).toFixed(2);
            $qty = parseFloat($servTR.find('td #svc' + id).val()).toFixed(2);
        });

        $qty = servicequantity;
        total += $qty * $servicePrice;
        $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
        $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));
        q = servicequantity;
        var servshowqty = parseInt($qty);

        $serviceQtyField.on('input', function () {
            $qty = parseFloat($servTR.find('td #svc' + id).val()).toFixed(2);
            if ($qty > q) {
                total += ($qty - q) * $servicePrice;
                servshowqty = parseInt($qty);
                total = Math.abs(total);
                $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
                $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));
                $('#pslist #servx' + id + '').remove();
                $('#pslist #servitem' + id + ' .span').append('<span class="grey-text text-darken-3" id="servx' + id + '"> (' + servshowqty + ')</span>');
            } else if (q > $qty) {
                total -= (q - $qty) * $productPrice;
                total = Math.abs(total);
                servshowqty = parseInt($qty);
                $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
                $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));
                $('#pslist #servx' + id + '').remove();
                $('#pslist #servitem' + id + ' .span').append('<span class="grey-text text-darken-3" id="servx' + id + '"> (' + servshowqty + ')</span>');
            } else {

            }
            q = $qty;
        });

        var serviceName = $servTR.find('td:eq(1)').text();

        $('#pslist').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            'id="servitem' + id + '"><b>' + serviceName + '</b><span class="span"><span class="grey-text text-darken-3" id="servx' + id + '">' +
            ' (' + servshowqty + ')</span></span>' + '<i id="servchip' + id + '" class="material-icons" style="margin-right: 5px' +
            '!important">close</i></div>').show();

    } else if (!(serviceID.is(':checked'))) {
        chk = chk - 1;
        console.log(chk);
        if(chk < 1) {
            $('#createSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if(chk > 0) {
            $('#createSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var servidqty = $('#svc' + id),
            $sutr = serviceID.closest('tr'),
            sunchipprice = $sutr.find('td:eq(3)').text(),
            $sunchipprice = parseFloat(sunchipprice.replace(/[^\d.]/g, '')).toFixed(2);
        $qty = parseFloat($sutr.find('td #svc' + id).val()).toFixed(2);
        total = total - ($qty * $sunchipprice);
        total = Math.abs(total);
        $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
        $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));

        $('#servitem' + id).remove();
        serviceID.prop('checked', false);
        servidqty.attr('disabled', true);
        servidqty.val(1);

    }

    $('#servchip' + id).click(function () {
        chk = chk - 1;
        console.log(chk);
        if(chk < 1) {
            $('#createSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if(chk > 0) {
            $('#createSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var servchipqty = $('#svc' + id),
            $sutr = serviceID.closest('tr'),
            sunchipprice = $sutr.find('td:eq(3)').text(),
            $sunchipprice = parseFloat(sunchipprice.replace(/[^\d.]/g, '')).toFixed(2);
        $qty = parseFloat($sutr.find('td #svc' + id).val()).toFixed(2);
        total = total - ($qty * $sunchipprice);
        total = Math.abs(total);
        $('#crPackTotal').val("Php " + parseFloat(total).toFixed(2));
        $('#crPackPrice').val("Php " + parseFloat(total).toFixed(2));

        $('#servitem' + id).remove();
        serviceID.prop('checked', false);
        servchipqty.attr('disabled', true);
        servchipqty.val(1);
    });
}


function createPackage() {
    if($('#createPackageForm').valid()) {
        if($('#createPackageForm').valid()) {
            // var job = document.querySelectorAll('select[name=intPackageType]:selected');
            var type = [],
                prodselect = [],
                servselect = [],
                packagetype;
            $.each($("#crPackageType option:selected"), function(){
                type.push($(this).val());
            });
            $.each($("input[name=createPackProdType]:checked"), function(){
                prodselect.push($(this).val());
            });
            $.each($("input[name=createPackServType]:checked"), function(){
                servselect.push($(this).val());
            });

            var productqty = $('input[name=createPackProdQty]:enabled').map(function () {
                return this.value;
            }).get(); //get all the quantity enabled in product
            var serviceqty = $('input[name=createPackServQty]:enabled').map(function () {
                return this.value;
            }).get(); //get all the quantity enabled in service

            type = type.join(', ');
            if (type == '1, 2, 3') {
                packagetype = '7';
            } else if (type == '2, 3' || type == '3, 2') {
                packagetype = '6';
            } else if (type == '1, 3' || type == '3, 1') {
                packagetype = '5';
            } else if (type == '1, 2' || type == '2, 1') {
                packagetype = '4';
            } else {
                packagetype = type;
            }
            prodselect = prodselect.join(', ');
            servselect = servselect.join(', ');
            productqty = productqty.join(', ');
            serviceqty = serviceqty.join(', ');
            console.log(type);
            var packagedata = {
                "strPackageName": $('#crPackageName').val(),
                "strPackageDesc": $('#crPackageDesc').val(),
                "intPackageType": packagetype,
                "createPackServType": servselect,
                "createPackProdType": prodselect,
                "createPackServQty": serviceqty,
                "createPackProdQty": productqty,
                "dblPackagePrice": $('#crPackPrice').val().replace(/[^\d.]/g, '')
            };

            swal({
                    title: "Create this package?",
                    text: "",
                    type: "info",
                    showCancelButton: true,
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    setTimeout(function () {
                        $.ajax({
                            url: 'createPackage',
                            type: 'post',
                            data: packagedata,
                            dataType: 'json',
                            async: true,
                            success: function (data) {
                                if(data.result == "success") {
                                    swal("Successfully created!", ".", "success");
                                    updatePackageTable();
                                    $('#createPackageModal').closeModal();
                                } else if (data.result == "existing") {
                                    sweetAlert("Oops...", "This package is already existing!", "error");
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
    }
}


//CREATE PACKAGE END