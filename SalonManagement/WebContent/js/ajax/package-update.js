/**
 * Created by Castillo on 8/2/2016.
 */
//update package

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
                updatePackageProdTable = $('#uppackageProdtbl').DataTable({
                    "bLengthChange": false,
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
                updatePackageProdTable.search(this.value).draw();
            });

            if (updateProductList != null) {
                updatePackageProdTable.clear().draw();
                $.each(updateProductList, function (i, product) {
                    var price = parseFloat(product.dblProductPrice).toFixed(2);
                    price = addCommas(price);

                    var checkbox = "<input type='checkbox' name='updatePackProdType' id='updateProdCheck" + product.intProductID + "'" +
                            " value='" + product.intProductID + "' onclick='updateComputeProd(this.value)'>" +
                            "<label for='updateProdCheck" + product.intProductID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='updatePackProdQty'" +
                            " id='upProdQty" + product.intProductID + "' style='width: 75px' disabled value='1' min='1' maxlength='2'>";
                    price = "<span class='price'>P " + price + "</span>";

                    updatePackageProdTable.row.add([
                        checkbox,
                        product.strProductName,
                        product.strProductCategory,
                        price,
                        quantity
                    ]);
                });
                updatePackageProdTable.draw();
            }
        }
    });
}

var updatePackageServTable;
var serviceDataData = [];

var upQ = 0, // temporary quantity
    $upQty = 0, //main quantity
    upTotal = 0, //total
    upI = 0, // packagetype == service type na naka check
    upChk = 0,
    serviceTotalPriceChecked = 0; //price total ng naka check na service

function updatePackageServiceTable(packType) {
    $.ajax({
        type: 'get',
        url: 'api/v1/getAllService',
        dataType: 'json',
        async: true,
        success: function (data) {
            var updateServiceList = data.serviceList;
            if (typeof packType === 'undefined') {
                updatePackageServTable = $('#uppackageServtbl').DataTable({
                    "bLengthChange": false,
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
            }
            $(".uppackageSearch").bind('keyup search input paste cut', function () {
                updatePackageServTable.search(this.value).draw();
            });

            if (updateServiceList != null) {

                updatePackageServTable.clear().draw();

                $.each(updateServiceList, function (i, service) {
                    serviceDataData.push(service);

                    var price = parseFloat(service.dblServicePrice).toFixed(2);
                    price = addCommas(price);

                    var checkbox = "<input type='checkbox' name='updatePackServType' id='updateServCheckBox" + service.intServiceID + "'" +
                            " value='" + service.intServiceID + "' onclick='updateComputeService(this.value)'" +
                            " class='chkbox updateServCheckBox" + service.intServiceID + "'>" +
                            "<label for='updateServCheckBox" + service.intServiceID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='updatePackServQty'" +
                            " id='upServQty" + service.intServiceID + "' style='width: 75px' disabled value='1' min='1' maxlength='2'>";
                    price = "<span class='price'>P " + price + "</span>";

                    updatePackageServTable.row.add([
                        checkbox,
                        service.strServiceName,
                        service.strServiceCategory,
                        price,
                        quantity
                    ]);
                });
                updatePackageServTable.draw();

                var promoType = 0;

                $('#upPackageType').on('change', function () {
                    upTotal = upTotal - serviceTotalPriceChecked;
                    $('#upPackTotal').val('Php ' + parseFloat(upTotal).toFixed(2));
                    $('#upPackPrice').val('Php ' + parseFloat(upTotal).toFixed(2));
                    serviceTotalPriceChecked = 0;


                    updatePackageServTable.clear().draw();

                    var type = $(this).val(),
                        walk = 0,
                        home = 0,
                        event = 0;
                    if (type != null) {
                        for (var i = 0; i < type.length; i++) {
                            if (type[i] == 1) {
                                walk = 1;
                            } else if (type[i] == 2) {
                                home = 1;
                            } else if (type[i] == 3) {
                                event = 1;
                            }
                        }
                    }

                    if (walk == 1 && home == 0 && event == 0) {
                        promoType = 1;
                    } else if (walk == 0 && home == 1 && event == 0) {
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
                    } else {
                        promoType = 0;
                    }

                    $.each(updateServiceList, function (i, service) {

                        $('#upServItem' + service.intServiceID).remove();
                        if (promoType == service.serviceType) {
                            var price = parseFloat(service.dblServicePrice).toFixed(2);
                            price = addCommas(price);

                            var checkbox = "<input type='checkbox' name='updatePackServType' id='updateServCheckBox" + service.intServiceID + "'" +
                                    " value='" + service.intServiceID + "' onclick='updateComputeService(this.value)' class='chkbox'>" +
                                    "<label for='updateServCheckBox" + service.intServiceID + "'></label>",
                                quantity = "<input type='number' class='right-align rowQty' name='updatePackServQty'" +
                                    " id='upServQty" + service.intServiceID + "' style='width: 75px' disabled value='1' min='1' maxlength='2'>";
                            price = "<span class='price'>P " + price + "</span>";

                            updatePackageServTable.row.add([
                                checkbox,
                                service.strServiceName,
                                service.strServiceCategory,
                                price,
                                quantity
                            ]);
                        }
                    });
                    updatePackageServTable.draw();
                });
            }
        }
    });
}

$('#btnUpdatePackageExit').on('click', function () {
    $('#updatePackageForm').trigger('reset');
    $('#updatepackageerror').hide();
});

$('#btnUpCancelPackage').on('click', function () {
    $('#updatePackageForm').trigger('reset');
    $('#updatepackageerror').hide();
});

function openUpdatePackage(id) {
    $('#updatePackageModal').openModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 300, // Transition out duration
    });
    $('ul.tabs').tabs('select_tab', 'updateA');
    $('#upPsList .updatePackChip').remove();

    $.ajax({
        type: 'get',
        url: 'api/v1/getAllPackage',
        dataType: 'json',
        async: true,
        beforeSend: function () {
            $('#packageloadingupdate').show();
        },
        success: function (data) {
            var packageType = 0;

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

                    switch (packagetype) {
                        case 1:
                            packageselecttype.each(function () {
                                if ($(this).val() == 1) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).prop('selected', true);
                                    select.material_select();
                                    packageType = 1;
                                } else if ($(this).val() != 1) {
                                    select.material_select('destroy');
                                    $(this).removeAttr('selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 2:
                            packageselecttype.each(function () {
                                if ($(this).val() == 2) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).prop('selected', true);
                                    select.material_select();
                                    packageType = 2;
                                } else if ($(this).val() != 2) {
                                    select.material_select('destroy');
                                    $(this).removeAttr('selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 3:
                            packageselecttype.each(function () {
                                if ($(this).val() == 3) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).prop('selected', true);
                                    select.material_select();
                                    packageType = 3;
                                } else if ($(this).val() != 3) {
                                    select.material_select('destroy');
                                    $(this).removeAttr('selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 4:
                            packageselecttype.each(function () {
                                if ($(this).val() == 4) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).prop('selected', true);
                                    select.material_select();
                                    packageType = 4;
                                } else if ($(this).val() != 4) {
                                    select.material_select('destroy');
                                    $(this).removeAttr('selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 5:
                            packageselecttype.each(function () {
                                if ($(this).val() == 5) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).prop('selected', true);
                                    select.material_select();
                                    packageType = 5;
                                } else if ($(this).val() != 5) {
                                    select.material_select('destroy');
                                    $(this).removeAttr('selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 6:
                            packageselecttype.each(function () {
                                if ($(this).val() == 6) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).prop('selected', true);
                                    select.material_select();
                                    packageType = 6;
                                } else if ($(this).val() != 6) {
                                    select.material_select('destroy');
                                    $(this).removeAttr('selected');
                                    select.material_select();
                                }
                            });
                            break;
                        case 7:
                            packageselecttype.each(function () {
                                if ($(this).val() == 1 || $(this).val() == 2 || $(this).val() == 3) {
                                    select.material_select('destroy');
                                    console.log($(this).text());
                                    $(this).prop('selected', true);
                                    select.material_select();
                                    packageType = 7;
                                }
                            });
                            break;
                    }

                    //start ng pag insert ng mga naka check na product sa table
                    var updatePackProdID = $('input[name=updatePackProdType]');
                    $('input[name=updatePackProdQty]').attr('disabled', true);
                    $('input[name=updatePackservQty]').attr('disabled', true);
                    updatePackProdID.prop('checked', false);
                    upTotal = 0;


                    for (var z = 0; z < data.packageList[i].productList.length; z++) {
                        var productList = data.packageList[i].productList[z];
                        var prodIDAjax = 'updateProdCheck' + productList.product.intProductID;

                        updatePackProdID.each(function () {
                            var updateProdID = $(this).attr('id');
                            if (prodIDAjax == updateProdID) {
                                var ajaxProductID = productList.product.intProductID;
                                upChk = upChk + 1; //para malaman kung ilan or meron bang nakacheck
                                if (upChk < 1) {
                                    $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
                                } else if (upChk > 0) {
                                    $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
                                }

                                $('#' + prodIDAjax).prop('checked', true);
                                $('#upProdQty' + ajaxProductID).attr('disabled', false)
                                    .val(productList.intProductQuantity);

                                $('#upPsList').append('<div style="margin: 3px;" class="updatePackChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                                    ' id="upProdItem' + ajaxProductID + '"><b>' + productList.product.strProductName +
                                    '</b><span class="span"><span class="grey-text text-darken-3" id="upProdx' + ajaxProductID + '">' +
                                    ' (' + productList.intProductQuantity + ')</span></span>' +
                                    '<i id="upProdchip' + ajaxProductID + '" class="material-icons upPackChipExit"' +
                                    ' onclick="upPackChipExit(' + ajaxProductID + ')" style="margin-right: 5px !important">' +
                                    'close</i></span></div>').show();


                                var $upAjaxProdTr = $(this).closest('tr'),
                                    upAjaxProdPrice = $upAjaxProdTr.find('td:eq(3)').text(),
                                    $upAjaxProdQtyField = $upAjaxProdTr.find('td #upProdQty' + ajaxProductID),
                                    $upAjaxProdPrice = parseFloat(upAjaxProdPrice.replace(/[^\d.]/g, '')).toFixed(2),
                                    $upAjaxProdQty = parseFloat(productList.intProductQuantity).toFixed(2);

                                $upAjaxProdQtyField.focus(function () {
                                    upQ = parseFloat($upAjaxProdTr.find('td #upProdQty' + ajaxProductID).val()).toFixed(2);
                                    $upQty = parseFloat($upAjaxProdTr.find('td #upProdQty' + ajaxProductID).val()).toFixed(2);
                                });

                                $upQty = $upAjaxProdQty; //main quantity
                                upQ = $upAjaxProdQty; //temporary quantity

                                upTotal += $upQty * $upAjaxProdPrice;
                                $('#upPackTotal').val('Php ' + parseFloat(upTotal).toFixed(2));
                                $('#upPackPrice').val('Php ' + parseFloat(data.packageList[i].dblPackagePrice).toFixed(2));

                                $upAjaxProdQtyField.on('input', function () {
                                    $upQty = $upAjaxProdQtyField.val();
                                    if ($upQty > upQ) {
                                        upTotal += ($upQty - upQ) * $upAjaxProdPrice;
                                        $('#upPackTotal').val('Php ' + parseFloat(upTotal).toFixed(2));
                                        $('#upPackPrice').val('Php ' + parseFloat(upTotal).toFixed(2));
                                        $('#upProdx' + ajaxProductID + '').remove();
                                        $('#upProdItem' + ajaxProductID + ' .span').append('<span class="grey-text text-darken-3" id="upProdx' + ajaxProductID + '"> (' + $upQty + ')</span>');
                                    } else if ($upQty < upQ) {
                                        upTotal -= (upQ - $upQty) * $upAjaxProdPrice;
                                        $('#upPackTotal').val('Php ' + parseFloat(upTotal).toFixed(2));
                                        $('#upPackPrice').val('Php ' + parseFloat(upTotal).toFixed(2));
                                        $('#upProdx' + ajaxProductID + '').remove();
                                        $('#upProdItem' + ajaxProductID + ' .span').append('<span class="grey-text text-darken-3" id="upProdx' + ajaxProductID + '"> (' + $upQty + ')</span>');
                                    }
                                    upQ = $upQty;
                                });
                            }

                        });
                    }

                    var updatePackServID = $('input[name=updatePackServType]');
                    updatePackServID.prop('checked', false);
                    $('#upPsList .updateServChip').remove();

                    for (var x = 0; x < data.packageList[i].serviceList.length; x++) {

                        var serviceList = data.packageList[i].serviceList[x];
                        var servIDAjax = 'updateServCheckBox' + serviceList.service.intServiceID;

                        //packageType = 1,2,3,4,5,6,7
                        updatePackServID.each(function () {
                            var updateServID = $(this).attr('id');
                            $.each(serviceDataData, function(i, serv) {
                               if(serv.serviceType != packageType) {
                                   var serviceRowHide = $('#updateServCheckBox' + serv.intServiceID).closest('tr');
                                   serviceRowHide.hide();
                               } else if (serv.serviceType == packageType) {
                                   upI += 1;
                               }
                            });

                            if (servIDAjax == updateServID) {
                                console.log(serviceList);
                            }

                            if (serviceList.service.serviceType == packageType) {
                                if (servIDAjax == updateServID) {

                                    var ajaxServiceID = serviceList.service.intServiceID;
                                    upChk = upChk + 1; //para malaman kung ilan or meron bang nakacheck
                                    if (upChk < 1) {
                                        $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
                                    } else if (upChk > 0) {
                                        $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
                                    }
                                    $('#updateServCheckBox' + ajaxServiceID).prop('checked', true);
                                    $('#upServQty' + ajaxServiceID).attr('disabled', false)
                                        .val(serviceList.intQuantity);

                                    $('#upPsList').append('<div style="margin: 3px;" class="updateServChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                                        ' id="upServItem' + ajaxServiceID + '"><b>' + serviceList.service.strServiceName +
                                        '</b><span class="span"><span class="grey-text text-darken-3" id="upServx' + id + '">' +
                                        '(' + serviceList.intQuantity + ')</span></span>' +
                                        '<i id="upServchip' + ajaxServiceID + '" class="material-icons upPackChipExit"' +
                                        ' onclick="upPackServChipExit(' + ajaxServiceID + ')" style="margin-right: 5px !important">' +
                                        'close</i></span></div>').show();


                                    var $upAjaxServTr = $(this).closest('tr'),
                                        upAjaxServPrice = $upAjaxServTr.find('td:eq(3)').text(),
                                        $upAjaxServQtyField = $upAjaxServTr.find('td #upServQty' + ajaxServiceID),
                                        $upAjaxServPrice = parseFloat(upAjaxServPrice.replace(/[^\d.]/g, '')).toFixed(2),
                                        $upAjaxServQty = parseFloat(serviceList.intQuantity).toFixed(2);

                                    $upAjaxServQtyField.focus(function () {
                                        upQ = parseFloat($upAjaxServTr.find('td #upServQty' + ajaxServiceID).val()).toFixed(2);
                                        $upQty = parseFloat($upAjaxServTr.find('td #upServQty' + ajaxServiceID).val()).toFixed(2);
                                    });

                                    $upQty = $upAjaxServQty; //main quantity
                                    upQ = $upAjaxServQty; //temporary quantity

                                    upTotal += $upQty * $upAjaxServPrice;
                                    serviceTotalPriceChecked += $upQty * $upAjaxServPrice;
                                    $('#upPackTotal').val('Php ' + parseFloat(upTotal).toFixed(2));
                                    $('#upPackPrice').val('Php ' + parseFloat(data.packageList[i].dblPackagePrice).toFixed(2));

                                    $upAjaxServQtyField.on('input', function () {
                                        $upQty = $upAjaxServQtyField.val();
                                        if ($upQty > upQ) {
                                            upTotal += ($upQty - upQ) * $upAjaxServPrice;
                                            $('#upPackTotal').val('Php ' + parseFloat(upTotal).toFixed(2));
                                            $('#upPackPrice').val('Php ' + parseFloat(upTotal).toFixed(2));
                                        } else if ($upQty < upQ) {
                                            upTotal -= (upQ - $upQty) * $upAjaxServPrice;
                                            $('#upPackTotal').val('Php ' + parseFloat(upTotal).toFixed(2));
                                            $('#upPackPrice').val('Php ' + parseFloat(upTotal).toFixed(2));
                                        }
                                        upQ = $upQty;
                                    });
                                }
                            }

                        });
                    }
                }

            }

        },
        complete: function () {
            $('#packageloadingupdate').fadeOut(500);
        }
    });
    $('#uppackageProdtbl').parents('div.tablewrapper').first().show();
    $('#uppackageServtbl').parents('div.tablewrapper').first().hide();
}

function updateComputeProd(upProdID) {

    var updateProdID = $('#updateProdCheck' + upProdID);
    if (updateProdID.is(':checked')) { //if product checkbox is checked
        var $upProdTR = updateProdID.closest('tr'), // PRODUCT TR
            upProdPrice = $upProdTR.find('td:eq(3)').text(),
            $upProductPrice = parseFloat(upProdPrice.replace(/[^\d.]/g, '')).toFixed(2), // PRODUCT PRICE (CONVERTED ALREADY)
            $upProductQtyField = $upProdTR.find('td #upProdQty' + upProdID), // quantity field of product
            upProductQuantity = parseFloat($upProductQtyField.val()).toFixed(2);

        upChk = upChk + 1;
        if (upChk < 1) {
            $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upChk > 0) {
            $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#upProdQty' + upProdID).attr('disabled', false);

        $upProductQtyField.focus(function () {
            upQ = parseFloat($upProdTR.find('td #upProdQty' + upProdID).val()).toFixed(2);
            $upQty = parseFloat($upProdTR.find('td #upProdQty' + upProdID).val()).toFixed(2);
        });

        $upQty = upProductQuantity;
        upTotal += $upQty * $upProductPrice;
        $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
        $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));

        upQ = upProductQuantity;
        var upProdShowQty = parseInt($upQty); // for display quantity in selected items
        $upProductQtyField.on('input', function () {
            $upQty = parseFloat($upProdTR.find('td #upProdQty' + upProdID).val()).toFixed(2);
            if ($upQty > upQ) {
                upTotal += ($upQty - upQ) * $upProductPrice;
                upProdShowQty = parseInt($upQty);
                upTotal = Math.abs(upTotal);
                $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
                $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));
                $('#upPsList #upProdx' + upProdID + '').remove();
                $('#upPsList #upProdItem' + upProdID + ' .span').append('<span class="grey-text text-darken-3" id="upProdx' + upProdID + '"> (' + upProdShowQty + ')</span>');
            } else if (upQ > $upQty) {
                upTotal -= (upQ - $upQty) * $upProductPrice;
                upTotal = Math.abs(upTotal);
                upProdShowQty = parseInt($upQty);
                $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
                $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));
                $('#upPsList #upProdx' + upProdID + '').remove();
                $('#upPsList #upProdItem' + upProdID + ' .span').append('<span class="grey-text text-darken-3" id="upProdx' + upProdID + '"> (' + upProdShowQty + ')</span>');
            } else {

            }
            upQ = $upQty;
        });

        var upProdName = $upProdTR.find('td:eq(1)').text();

        $('#upPsList').append('<div style="margin: 3px;" class="updatePackChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            'id="upProdItem' + upProdID + '"><b>' + upProdName + '</b><span class="span"><span class="grey-text text-darken-3" id="upProdx' + upProdID + '">' +
            ' (' + upProdShowQty + ')</span></span><i id="upProdchip' + upProdID + '" class="material-icons upPackChipExit" style="margin-right: 5px' +
            '!important">close</i></div>').show();

    } else if (!(updateProdID.is(':checked'))) {

        upChk = upChk - 1;
        if (upChk < 1) {
            $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upChk > 0) {
            $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
        }
        console.log(upChk);
        var upUnProdIDQty = $('#upProdQty' + upProdID),
            $unProdTR = updateProdID.closest('tr'),
            unPrice = $unProdTR.find('td:eq(3)').text(),
            $unPrice = parseFloat(unPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upQty = parseFloat($unProdTR.find('td #upProdQty' + upProdID).val()).toFixed(2);
        upTotal = upTotal - ($upQty * $unPrice);
        upTotal = Math.abs(upTotal);
        $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
        $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));

        $('#upProdItem' + upProdID).remove();
        upUnProdIDQty.attr('disabled', true);
        upUnProdIDQty.val(1);

    }

    $('#upProdchip' + upProdID).click(function () {
        console.log('update prod cip');
        upChk = upChk - 1;
        console.log(upChk);
        if (upChk < 1) {
            $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upChk > 0) {
            $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        var upProdChipQty = $('#upProdQty' + upProdID),
            $upUntr = updateProdID.closest('tr'),
            upUnChipPrice = $upUntr.find('td:eq(3)').text(),
            $upUnChipPrice = parseFloat(upUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upQty = parseFloat($upUntr.find('td #upProdQty' + upProdID).val()).toFixed(2);
        upTotal = upTotal - ($upQty * $upUnChipPrice);
        upTotal = Math.abs(upTotal);
        $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
        $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));

        $('#upProdItem' + upProdID).remove();
        $('#updateProdCheck' + upProdID).prop('checked', false);
        upProdChipQty.attr('disabled', true);
        upProdChipQty.val(1);
    });
}

function upPackChipExit(chipExitID) {
    upChk = upChk - 1;
    if (upChk < 1) {
        $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
    } else if (upChk > 0) {
        $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
    }

    var upProdChipQty = $('#upProdQty' + chipExitID),
        upProdCheck = $('#updateProdCheck' + chipExitID),
        $upUntr = upProdCheck.closest('tr'),
        upUnChipPrice = $upUntr.find('td:eq(3)').text(),
        $upUnChipPrice = parseFloat(upUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
    $upQty = parseFloat($upUntr.find('td #upProdQty' + chipExitID).val()).toFixed(2);
    upTotal = upTotal - ($upQty * $upUnChipPrice);
    upTotal = Math.abs(upTotal);
    $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
    $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));

    $('#upProdItem' + chipExitID).remove();
    upProdCheck.prop('checked', false);
    upProdChipQty.attr('disabled', true);
    upProdChipQty.val(1);
}


function updateComputeService(upServID) {

    var updateServID = $('#updateServCheckBox' + upServID);
    if (updateServID.is(':checked')) { //if product checkbox is checked
        var $upServTR = updateServID.closest('tr'), // PRODUCT TR
            upServPrice = $upServTR.find('td:eq(3)').text(),
            $upServicePrice = parseFloat(upServPrice.replace(/[^\d.]/g, '')).toFixed(2), // PRODUCT PRICE (CONVERTED ALREADY)
            $upServiceQtyField = $upServTR.find('td #upServQty' + upServID), // quantity field of product
            upServiceQuantity = parseFloat($upServiceQtyField.val()).toFixed(2);

        upChk = upChk + 1;
        if (upChk < 1) {
            $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upChk > 0) {
            $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#upServQty' + upServID).attr('disabled', false);

        $upServiceQtyField.focus(function () {
            upQ = parseFloat($upServTR.find('td #upServQty' + upServID).val()).toFixed(2);
            $upQty = parseFloat($upServTR.find('td #upServQty' + upServID).val()).toFixed(2);
        });

        $upQty = upServiceQuantity;
        upTotal += $upQty * $upServicePrice;
        serviceTotalPriceChecked += $upQty * $upServicePrice;
        $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
        $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));

        upQ = upServiceQuantity;
        var upServShowQty = parseInt($upQty); // for display quantity in selected items
        $upServiceQtyField.on('input', function () {
            $upQty = parseFloat($upServTR.find('td #upServQty' + upServID).val()).toFixed(2);
            if ($upQty > upQ) {
                upTotal += ($upQty - upQ) * $upServicePrice;
                upServShowQty = parseInt($upQty);
                upTotal = Math.abs(upTotal);
                serviceTotalPriceChecked += ($upQty - upQ) * $upServicePrice;
                $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
                $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));
                $('#upPsList #upServx' + upServID + '').remove();
                $('#upPsList #upServItem' + upServID + ' .span').append('<span class="grey-text text-darken-3" id="upServx' + upServID + '"> (' + upServShowQty + ')</span>');
            } else if (upQ > $upQty) {
                upTotal -= (upQ - $upQty) * $upServicePrice;
                upTotal = Math.abs(upTotal);
                upServShowQty = parseInt($upQty);
                serviceTotalPriceChecked -= (upQ - $upQty) * $upServicePrice;
                $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
                $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));
                $('#upPsList #upServx' + upServID + '').remove();
                $('#upPsList #upServItem' + upServID + ' .span').append('<span class="grey-text text-darken-3" id="upServx' + upServID + '"> (' + upServShowQty + ')</span>');
            } else {

            }
            upQ = $upQty;
        });

        var upServName = $upServTR.find('td:eq(1)').text();

        $('#upPsList').append('<div style="margin: 3px;" class="updatePackChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            'id="upServItem' + upServID + '"><b>' + upServName + '</b><span class="span"><span class="grey-text text-darken-3" id="upServx' + upServID + '">' +
            ' (' + upServShowQty + ')</span></span><i id="upServchip' + upServID + '" class="material-icons upPackChipExit" style="margin-right: 5px' +
            '!important">close</i></div>').show();

    } else if (!(updateServID.is(':checked'))) {
        upChk = upChk - 1;
        if (upChk < 1) {
            $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upChk > 0) {
            $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        var upUnServIDQty = $('#upServQty' + upServID),
            $unServTR = updateServID.closest('tr'),
            unPrice = $unServTR.find('td:eq(3)').text(),
            $unPrice = parseFloat(unPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upQty = parseFloat($unServTR.find('td #upServQty' + upServID).val()).toFixed(2);
        upTotal = upTotal - ($upQty * $unPrice);
        upTotal = Math.abs(upTotal);
        serviceTotalPriceChecked = serviceTotalPriceChecked - ($upQty * $unPrice);
        $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
        $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));

        $('#upServItem' + upServID).remove();
        upUnServIDQty.attr('disabled', true);
        upUnServIDQty.val(1);

    }

    $('#upServchip' + upServID).click(function () {
        console.log('update prod cip');
        upChk = upChk - 1;
        if (upChk < 1) {
            $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upChk > 0) {
            $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        var upServChipQty = $('#upServQty' + upServID),
            $upUntr = updateServID.closest('tr'),
            upUnChipPrice = $upUntr.find('td:eq(3)').text(),
            $upUnChipPrice = parseFloat(upUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upQty = parseFloat($upUntr.find('td #upServQty' + upServID).val()).toFixed(2);
        upTotal = upTotal - ($upQty * $upUnChipPrice);
        upTotal = Math.abs(upTotal);
        serviceTotalPriceChecked = serviceTotalPriceChecked - ($upQty * $upUnChipPrice);
        $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
        $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));

        $('#upServItem' + upServID).remove();
        $('#updateServCheckBox' + upServID).prop('checked', false);
        upServChipQty.attr('disabled', true);
        upServChipQty.val(1);
    });
}

function upPackServChipExit(chipExitID) {
    upChk = upChk - 1;
    if (upChk < 1) {
        $('#updatePackSubmitBtn').attr('disabled', true).css('opacity', '0.3');
    } else if (upChk > 0) {
        $('#updatePackSubmitBtn').attr('disabled', false).css('opacity', '1');
    }

    var upServChipQty = $('#upServQty' + chipExitID),
        upServCheck = $('#updateServCheckBox' + chipExitID),
        $upUntr = upServCheck.closest('tr'),
        upUnChipPrice = $upUntr.find('td:eq(3)').text(),
        $upUnChipPrice = parseFloat(upUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
    $upQty = parseFloat($upUntr.find('td #upServQty' + chipExitID).val()).toFixed(2);
    upTotal = upTotal - ($upQty * $upUnChipPrice);
    upTotal = Math.abs(upTotal);
    serviceTotalPriceChecked = serviceTotalPriceChecked - ($upQty * $upUnChipPrice);
    $('#upPackTotal').val("Php " + parseFloat(upTotal).toFixed(2));
    $('#upPackPrice').val("Php " + parseFloat(upTotal).toFixed(2));

    $('#upServItem' + chipExitID).remove();
    upServCheck.prop('checked', false);
    upServChipQty.attr('disabled', true);
    upServChipQty.val(1);
}

function updatePackage() {
    if ($('#updatePackageForm').valid()) {
        // var job = document.querySelectorAll('select[name=intPackageType]:selected');
        var selectedPromoType = [],
            upProdselect = [],
            upServselect = [],
            upPackagetype;
        $.each($("input[name=updatePackProdType]:checked"), function () {
            upProdselect.push($(this).val());
        });
        $.each($("input[name=updatePackServType]:checked"), function () {
            upServselect.push($(this).val());
        });

        var upProductqty = $('input[name=updatePackProdQty]:enabled').map(function () {
            return this.value;
        }).get(); //get all the quantity enabled in product
        var upServiceqty = $('input[name=updatePackServQty]:enabled').map(function () {
            return this.value;
        }).get(); //get all the quantity enabled in service

        selectedPromoType = $('#upPackageType').val();

        var upWalk = 0,
            upHome = 0,
            upEvent = 0,
            upPackageType = [];

        for (var i = 0; i < selectedPromoType.length; i++) {
            if (selectedPromoType[i] == '1') {
                upWalk = 1;
            } else if (selectedPromoType[i] == '2') {
                upHome = 1;
            } else if (selectedPromoType[i] == '3') {
                upEvent = 1;
            }
        }


        if (upWalk == 1 && upHome == 0 && upEvent == 0) {
            upPackageType.push('1');
        } else if (upWalk == 0 && upHome == 1 && upEvent == 0) {
            upPackageType.push('2');
        } else if (upWalk == 0 && upHome == 0 && upEvent == 1) {
            upPackageType.push('3');
        } else if (upWalk == 1 && upHome == 1 && upEvent == 0) {
            upPackageType.push('1');
            upPackageType.push('2');
        } else if (upWalk == 1 && upHome == 0 && upEvent == 1) {
            upPackageType.push('1');
            upPackageType.push('3');
        } else if (upWalk == 0 && upHome == 1 && upEvent == 1) {
            upPackageType.push('2');
            upPackageType.push('3');
        } else if (upWalk == 1 && upHome == 1 && upEvent == 1) {
            upPackageType.push('1');
            upPackageType.push('2');
            upPackageType.push('3');
        }



        upProdselect = upProdselect.join(', ');
        upServselect = upServselect.join(', ');
        upProductqty = upProductqty.join(', ');
        upServiceqty = upServiceqty.join(', ');
        upPackageType = upPackageType.join(',');

        var upPackageData = {
            "intUpdatePackageID": $('#upPackageID').val(),
            "strUpdatePackageName": $('#upPackageName').val(),
            "strUpdatePackageDesc": $('#upPackageDesc').val(),
            "intUpdatePackageType": upPackageType,
            "updatePackServType": upServselect,
            "updatePackProdType": upProdselect,
            "updatePackServQty": upServiceqty,
            "updatePackProdQty": upProductqty,
            "dblUpdatePackagePrice": $('#upPackPrice').val().replace(/[^\d.]/g, '')
        };
        console.log(upPackageData);

        swal({
                title: "Update this package?",
                text: "",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            },
            function () {
                setTimeout(function () {
                    $.ajax({
                        url: 'updatePackage',
                        type: 'post',
                        data: upPackageData,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                            if (data.result == "success") {
                                $('#updatePackageForm').trigger('reset');
                                $('#updatepackageerror').hide();
                                swal("Successfully updated!", ".", "success");
                                updatePackageTable();
                                $('#updatePackageModal').closeModal();
                            } else if (data.result == "existing") {
                                sweetAlert("Oops...", "This package is already existing!", "error");
                            } else if (data.result == "failed") {
                                sweetAlert("Oops...", "Something went wrong!", "error");
                            }
                        },
                        error: function (ts) {
                            sweetAlert("Oops...", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });
    }

}

