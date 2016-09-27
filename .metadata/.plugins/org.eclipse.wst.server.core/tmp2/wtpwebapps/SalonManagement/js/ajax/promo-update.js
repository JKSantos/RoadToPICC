/**
 * Created by Castillo on 8/7/2016.
 */
window.onload = updatePromoProdTable();
window.onload = updatePromoServTable();
window.onload = updatePromoPackageTable();

function updatePromoProdTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllProduct',
        dataType: 'json',
        async: true,
        success: function (data) {
            var productList = data.productList,
                updatePromoProdTbl = $('#upPromoTblProd').DataTable({
                    "bLengthChange": false,
                    "sPaginationType": "full_numbers",
                    responsive: true,
                    "order": [],
                    "columnDefs": [
                        {"targets": 'no-sort', "orderable": false},
                        {className: "dt-body-left", "targets": [1, 2]},
                        {className: "dt-body-center", "targets": [0]},
                        {className: "dt-body-right", "targets": [3, 4]},
                        {"targets": [4], "width": "100px"}
                    ],
                    "rowHeight": '10px'
                });

            $("#upPromoSearch").bind('keyup search input paste cut', function () {
                updatePromoProdTbl.search(this.value).draw();
            });

            if (productList != null) {
                updatePromoProdTbl.clear().draw();
                $.each(productList, function (i, product) {
                    var price = parseFloat(product.dblProductPrice).toFixed(2);
                    price = addCommas(price);
                    var checkbox = "<input type='checkbox' name='prodUpPromoSelect' id='promoUpProdCheck" + product.intProductID + "'" +
                            " class='packcheckbox x" + product.intProductID + "' value='" + product.intProductID + "' onclick='promoUpProdCompute(this.value)'>" +
                            "<label for='promoUpProdCheck" + product.intProductID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='prodUpdatePromoQty'" +
                            " id='promoUpProdQty" + product.intProductID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                    price = "<span class='price' id='upProdPrice" + product.intProductID + "'>P " + price + "</span>";

                    updatePromoProdTbl.row.add([
                        checkbox,
                        product.strProductName,
                        product.strProductCategory,
                        price,
                        quantity
                    ]);
                });
                updatePromoProdTbl.draw();
            }
        }
    });
}

var serviceDataData = [];
var packageDataData = [];
var upPromoQ = 0, // temporary quantity
    $upPromoQty = 0, //main quantity
    upPromoTotal = 0, //total
    upPromoChk = 0;
var serviceTotalPriceChecked = 0;
var packageTotalPriceChecked = 0;

function updatePromoServTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllService',
        dataType: 'json',
        async: true,
        success: function (data) {
            var serviceList = data.serviceList,
                updatePromoServTbl = $('#upPromoTblServ').DataTable({
                    "bLengthChange": false,
                    "sPaginationType": "full_numbers",
                    responsive: true,
                    "order": [],
                    "columnDefs": [
                        {"targets": 'no-sort', "orderable": false},
                        {className: "dt-body-left", "targets": [1, 2]},
                        {className: "dt-body-center", "targets": [0]},
                        {className: "dt-body-right", "targets": [3, 4]}
                    ],
                    "rowHeight": '10px'
                });

            $("#upPromoSearch").bind('keyup search input paste cut', function () {
                updatePromoServTbl.search(this.value).draw();
            });

            console.log(serviceList);

            if (serviceList != null) {

                updatePromoServTbl.clear().draw();

                $.each(serviceList, function (i, service) {
                    serviceDataData.push(service);
                    var price = parseFloat(service.dblServicePrice).toFixed(2);
                    price = addCommas(price);
                    var checkbox = "<input type='checkbox' name='servUpPromoSelect' id='promoUpServCheck" + service.intServiceID + "'" +
                            " class='packcheckbox x" + service.intServiceID + "' value='" + service.intServiceID + "' onclick='promoUpServCompute(this.value)'>" +
                            "<label for='promoUpServCheck" + service.intServiceID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='servUpdatePromoQty'" +
                            " id='promoUpServQty" + service.intServiceID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                    price = "<span class='price'>P " + price + "</span>";

                    updatePromoServTbl.row.add([
                        checkbox,
                        service.strServiceName,
                        service.strServiceCategory,
                        price,
                        quantity
                    ]);
                });

                updatePromoServTbl.draw();

                var promoType = 0;

                $('#upPromoType').on('change', function () {
                    upPromoTotal = upPromoTotal - serviceTotalPriceChecked;
                    $('#upPromoTotal').val('Php ' + parseFloat(upTotal).toFixed(2));
                    $('#upPromoPrice').val('Php ' + parseFloat(upTotal).toFixed(2));
                    serviceTotalPriceChecked = 0;
                    updatePromoServTbl.clear().draw();

                    var type = $(this).val(),
                        walk = 0,
                        home = 0,
                        event = 0;
                    for (var i = 0; i < type.length; i++) {
                        if (type[i] == 'walkin') {
                            walk = 1;
                            console.log('walkin');
                        } else if (type[i] == 'homeservice') {
                            home = 1;
                            console.log('homeservice');
                        } else if (type[i] == 'event') {
                            event = 1;
                            console.log('event');
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
                        $('#upPromoServItem' + service.intServiceID).remove();
                        if (promoType == service.serviceType) {
                            var price = parseFloat(service.dblServicePrice).toFixed(2);
                            price = addCommas(price);
                            var checkbox = "<input type='checkbox' name='servUpPromoSelect' id='promoUpServCheck" + service.intServiceID + "'" +
                                    " class='packcheckbox x" + service.intServiceID + "' value='" + service.intServiceID + "' onclick='promoUpServCompute(this.value)'>" +
                                    "<label for='promoUpServCheck" + service.intServiceID + "'></label>",
                                quantity = "<input type='number' class='right-align rowQty' name='servUpdatePromoQty'" +
                                    " id='promoUpServQty" + service.intServiceID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                            price = "<span class='price'>P " + price + "</span>";

                            updatePromoServTbl.row.add([
                                checkbox,
                                service.strServiceName,
                                service.strServiceCategory,
                                price,
                                quantity
                            ]);
                        }
                    });

                    updatePromoServTbl.draw();

                });
            }
        }
    });
}

function updatePromoPackageTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllPackage',
        dataType: 'json',
        async: true,
        success: function (data) {
            var packageList = data.packageList,
                updatePromoPackageTbl = $('#upPromoTblPackage').DataTable({
                    "bLengthChange": false,
                    "sPaginationType": "full_numbers",
                    responsive: true,
                    "order": [],
                    "columnDefs": [
                        {"targets": 'no-sort', "orderable": false},
                        {className: "dt-body-left", "targets": [1, 2]},
                        {className: "dt-body-center", "targets": [0]},
                        {className: "dt-body-right", "targets": [3, 4]}
                    ],
                    "rowHeight": '10px'
                });

            $("#upPromoSearch").bind('keyup search input paste cut', function () {
                updatePromoPackageTbl.search(this.value).draw();
            });

            if (packageList != null) {

                updatePromoPackageTbl.clear().draw();
                $.each(packageList, function (i, packagedata) {
                    packageDataData.push(packagedata);
                        var cat = 'Package';
                        var price = parseFloat(packagedata.dblPackagePrice).toFixed(2);
                        price = addCommas(price);
                        var checkbox = "<input type='checkbox' name='packUpPromoSelect' id='promoUpPackage" + packagedata.intPackageID + "'" +
                                " class='packcheckbox x" + packagedata.intPackageID + "' value='" + packagedata.intPackageID + "' onclick='promoUpPackageCompute(this.value)'>" +
                                "<label for='promoUpPackage" + packagedata.intPackageID + "'></label>",
                            quantity = "<input type='number' class='right-align rowQty' name='packUpdatePromoQty'" +
                                " id='promoUpPackageQty" + packagedata.intPackageID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                        price = "<td class='dt-body-right'>P " + price + "</td>";

                        updatePromoPackageTbl.row.add([
                            checkbox,
                            packagedata.strPackageName,
                            cat,
                            price,
                            quantity
                        ]);
                });
                updatePromoPackageTbl.draw();

                var promoType = 0;

                $('#upPromoType').on('change', function () {
                    upPromoTotal = upPromoTotal - packageTotalPriceChecked;
                    $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                    $('#upPromoPrice').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                    packageTotalPriceChecked = 0;
                    updatePromoPackageTbl.clear().draw();

                    var type = $(this).val(),
                        walk = 0,
                        home = 0,
                        event = 0;
                    for (var i = 0; i < type.length; i++) {
                        if (type[i] == 'walkin') {
                            walk = 1;
                            console.log('walkin');
                        } else if (type[i] == 'homeservice') {
                            home = 1;
                            console.log('homeservice');
                        } else if (type[i] == 'event') {
                            event = 1;
                            console.log('event');
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

                    $.each(packageList, function (i, packagedata) {

                        $('#upPromoPackageItem' + packagedata.intPackageID).remove();
                        if (promoType == packagedata.intPackageType) {
                            var cat = 'Package';
                            var price = parseFloat(packagedata.dblPackagePrice).toFixed(2);
                            price = addCommas(price);
                            var checkbox = "<input type='checkbox' name='packUpPromoSelect' id='promoUpPackage" + packagedata.intPackageID + "'" +
                                    " class='packcheckbox x" + packagedata.intPackageID + "' value='" + packagedata.intPackageID + "' onclick='promoUpPackageCompute(this.value)'>" +
                                    "<label for='promoUpPackage" + packagedata.intPackageID + "'></label>",
                                quantity = "<input type='number' class='right-align rowQty' name='packUpdatePromoQty'" +
                                    " id='promoUpPackageQty" + packagedata.intPackageID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                            price = "<td class='dt-body-right'>P " + price + "</td>";

                            updatePromoPackageTbl.row.add([
                                checkbox,
                                packagedata.strPackageName,
                                cat,
                                price,
                                quantity
                            ]);
                        }
                    });
                    updatePromoPackageTbl.draw();
                });
            }
        }
    });
}


function openUpdatePromo(id) {
    $('#upPromoModal').openModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 300, // Transition out duration
    });
    $('select').material_select('destroy');
    $('#upPromoFilter option[value="product"]').prop('selected', true);
    $('select').material_select();
    $('#upPromoTblProd').parents('div.tablewrapper').first().show();
    $('#upPromoTblServ').parents('div.tablewrapper').first().hide();
    $('#upPromoTblPackage').parents('div.tablewrapper').first().hide();
    $('ul.tabs').tabs('select_tab', 'promoUpdateA');

    var upPromoExpDate;


    $.ajax({
        type: 'get',
        url: 'getPromoByID',
        dataType: 'json',
        async: true,
        data: {
            "intPromoID": id
        },
        success: function (data) {
            var promoType = 0;

            if (data.result == "success") {
                console.log(data.promo);
                var pty = [],
                    preq = [];
                if (data.promo.promoType == 1) {
                    pty.push('walkin');
                    promoType = 1;
                } else if (data.promo.promoType == 2) {
                    pty.push('homeservice');
                    promoType = 2;
                } else if (data.promo.promoType == 3) {
                    pty.push('event');
                    promoType = 3;
                } else if (data.promo.promoType == 4) {
                    pty.push('walkin');
                    pty.push('homeservice');
                    promoType = 4;
                } else if (data.promo.promoType == 5) {
                    pty.push('walkin');
                    pty.push('event');
                    promoType = 5;
                } else if (data.promo.promoType == 6) {
                    pty.push('homeservice');
                    pty.push('event');
                    promoType = 6;
                } else if (data.promo.promoType == 7) {
                    pty.push('walkin');
                    pty.push('homeservice');
                    pty.push('event');
                    promoType = 7;
                }

                $('select').material_select('destroy');
                $('#upPromoType').val(pty);
                $('select').material_select();

                $.each(data.promo.requirements, function (i, req) {
                    preq.push(req.intRequirementID);
                });

                $('select').material_select('destroy');
                $('#upPromoRequirement').val(preq);
                $('select').material_select();

                if (data.promo.dblPrice == "0.0") {
                    $('input[name=upFree]').prop('checked', false);
                } else {
                    $('input[name=upFree]').prop('checked', true);
                }

                if (data.promo.strPromoAvailability == "NON-EXPIRY") {
                    $('#upPromoNonExpiry').prop('checked', true);
                    $('#upPromoExpiration').prop('disabled', 'disabled').val('');
                } else {
                    upPromoExpDate = data.promo.strPromoAvailability;
                    var split = upPromoExpDate.split('-'),
                        m;
                    if (split[1] == 1) {
                        m = "January";
                    } else if (split[1] == 2) {
                        m = "February";
                    } else if (split[1] == 3) {
                        m = "March";
                    } else if (split[1] == 4) {
                        m = "April";
                    } else if (split[1] == 5) {
                        m = "May";
                    } else if (split[1] == 6) {
                        m = "June";
                    } else if (split[1] == 7) {
                        m = "July";
                    } else if (split[1] == 8) {
                        m = "August";
                    } else if (split[1] == 9) {
                        m = "September";
                    } else if (split[1] == 10) {
                        m = "October";
                    } else if (split[1] == 11) {
                        m = "November";
                    } else if (split[1] == 12) {
                        m = "December";
                    }
                    upPromoExpDate = m + '/' + split[2] + '/' + split[0];

                    $('#upPromoNonExpiry').prop('checked', false);
                    $('#upPromoExpiration').prop('disabled', false).val(upPromoExpDate);
                }

                $('#upPromoNonExpiry').change(function () {
                    if ($(this).is(':checked')) {
                        $('#upPromoExpiration').prop('disabled', true).val('');
                    } else {
                        $('#upPromoExpiration').prop('disabled', false).val(upPromoExpDate);
                    }
                });


                $('#upPromoID').val(data.promo.intPromoID);
                $('#upPromoName').val(data.promo.strPromoName);
                $('#upPromoDescription').val(data.promo.strPromoDescription);
                $('#upPromoGuidelines').val(data.promo.strPromoGuidelines);

                var upPromoProdName = $('input[name=prodUpPromoSelect]');
                upPromoProdName.prop('checked', false);
                $('input[name=upProdPromoQty]').prop('disabled', true);
                $('.updatePromoChip').remove();
                upPromoTotal = 0;

                console.log(data.promo);

                for (var ii = 0; ii < data.promo.productList.length; ii++) {
                    var prodIDAjax = 'promoUpProdCheck' + data.promo.productList[ii].product.intProductID;

                    upPromoProdName.each(function () {
                        console.log(data.promo.productList[ii]);
                        var updatePromoProdID = $(this).attr('id');
                        if (prodIDAjax == updatePromoProdID) {
                            console.log(prodIDAjax + ' equal ' + updatePromoProdID);
                            var ajaxProductID = data.promo.productList[ii].product.intProductID;
                            upPromoChk = upPromoChk + 1; //para malaman kung ilan or meron bang nakacheck
                            if (upPromoChk < 1) {
                                $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
                            } else if (upPromoChk > 0) {
                                $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
                            }

                            $('#' + prodIDAjax).prop('checked', true);
                            $('#promoUpProdQty' + ajaxProductID).attr('disabled', false).val(data.promo.productList[ii].intProductQuantity);

                            $('#updatePromoList').append('<div style="margin: 3px;" class="updatePromoChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                                ' id="upPromoProdItem' + ajaxProductID + '"><b>' + data.promo.productList[ii].product.strProductName +
                                '</b><span class="span"><span class="grey-text text-darken-3" id="upPromoProdx' + ajaxProductID + '">' +
                                ' (' + data.promo.productList[ii].intProductQuantity + ')</span></span>' +
                                '<i id="upPromoProdchip' + ajaxProductID + '" class="material-icons upPromoChipExit"' +
                                ' onclick="upPromoChipExit(' + ajaxProductID + ')" style="margin-right: 5px !important">' +
                                'close</i></span></div>').show();


                            var $upAjaxProdTr = $(this).closest('tr'),
                                upAjaxProdPrice = $upAjaxProdTr.find('td:eq(3)').text(),
                                $upAjaxProdQtyField = $upAjaxProdTr.find('td #promoUpProdQty' + ajaxProductID),
                                $upAjaxProdPrice = parseFloat(upAjaxProdPrice.replace(/[^\d.]/g, '')).toFixed(2),
                                $upAjaxProdQty = parseFloat(data.promo.productList[ii].intProductQuantity).toFixed(2);

                            $upAjaxProdQtyField.focus(function () {
                                upPromoQ = parseFloat($upAjaxProdTr.find('td #promoUpProdQty' + ajaxProductID).val()).toFixed(2);
                                $upPromoQty = parseFloat($upAjaxProdTr.find('td #promoUpProdQty' + ajaxProductID).val()).toFixed(2);
                            });

                            $upPromoQty = $upAjaxProdQty; //main quantity
                            upPromoQ = $upAjaxProdQty; //temporary quantity

                            upPromoTotal += $upPromoQty * $upAjaxProdPrice;
                            $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                            $('#upPromoPrice').val('Php ' + parseFloat(data.promo.dblPromoPrice).toFixed(2));

                            $upAjaxProdQtyField.on('input', function () {
                                $upPromoQty = $upAjaxProdQtyField.val();
                                if ($upPromoQty > upPromoQ) {
                                    upPromoTotal += ($upPromoQty - upPromoQ) * $upAjaxProdPrice;
                                    $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                    $('#upPromoPrice').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                    $('#upPromoProdx' + ajaxProductID + '').remove();
                                    $('#upPromoProdItem' + ajaxProductID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoProdx' + ajaxProductID + '"> (' + $upPromoQty + ')</span>');
                                } else if ($upPromoQty < upPromoQ) {
                                    upPromoTotal -= (upPromoQ - $upPromoQty) * $upAjaxProdPrice;
                                    $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                    $('#upPromoPrice').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                    $('#upPromoProdx' + ajaxProductID + '').remove();
                                    $('#upPromoProdItem' + ajaxProductID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoProdx' + ajaxProductID + '"> (' + $upPromoQty + ')</span>');
                                }
                                upPromoQ = $upPromoQty;
                            });

                        }

                    });
                }

                var upPromoServName = $('input[name=servUpPromoSelect]');
                upPromoServName.prop('checked', false);
                $('input[name=upServPromoQty]').prop('disabled', true);

                $.each(serviceDataData, function (i, serv) {
                    if (serv.serviceType != promoType) {
                        var serviceRowHide = $('#promoUpServCheck' + serv.intServiceID).closest('tr');
                        serviceRowHide.hide();
                    } else if (serv.serviceType == promoType) {
                        upI += 1;
                    }
                });

                //for (var i = 0; i < data.promo.productList.length; i++) {
                for (var xx = 0; xx < data.promo.serviceList.length; xx++) {
                    var serviceListPromo = data.promo.serviceList[xx];
                    var servIDAjax = 'promoUpServCheck' + data.promo.serviceList[xx].service.intServiceID;

                    upPromoServName.each(function () {
                        var updatePromoServID = $(this).attr('id');
                        $.each(serviceDataData, function (i, serv) {
                            if (serv.serviceType != promoType) {
                                var serviceRowHide = $('#promoUpServCheck' + serv.intServiceID).closest('tr');
                                serviceRowHide.hide();
                            } else if (serv.serviceType == promoType) {
                                upI += 1;
                            }
                        });

                        if(serviceListPromo.service.serviceType == promoType) {
                            if (servIDAjax == updatePromoServID) {
                                var ajaxServiceID = data.promo.serviceList[xx].service.intServiceID;
                                upPromoChk = upPromoChk + 1; //para malaman kung ilan or meron bang nakacheck
                                if (upPromoChk < 1) {
                                    $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
                                } else if (upPromoChk > 0) {
                                    $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
                                }

                                $('#' + servIDAjax).prop('checked', true);
                                $('#promoUpServQty' + ajaxServiceID).attr('disabled', false)
                                    .val(data.promo.serviceList[xx].intQuantity);

                                $('#updatePromoList').append('<div style="margin: 3px;" class="updatePromoChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                                    ' id="upPromoServItem' + ajaxServiceID + '"><b>' + data.promo.serviceList[xx].service.strServiceName +
                                    '</b><span class="span"><span class="grey-text text-darken-3" id="upPromoServx' + ajaxServiceID + '">' +
                                    ' (' + data.promo.serviceList[xx].intQuantity + ')</span></span>' +
                                    '<i id="upPromoServchip' + ajaxServiceID + '" class="material-icons upPromoServChipExit"' +
                                    ' onclick="upPromoServChipExit(' + ajaxServiceID + ')" style="margin-right: 5px !important">' +
                                    'close</i></span></div>').show();


                                var $upAjaxServTr = $(this).closest('tr'),
                                    upAjaxServPrice = $upAjaxServTr.find('td:eq(3)').text(),
                                    $upAjaxServQtyField = $upAjaxServTr.find('td #promoUpServQty' + ajaxServiceID),
                                    $upAjaxServPrice = parseFloat(upAjaxServPrice.replace(/[^\d.]/g, '')).toFixed(2),
                                    $upAjaxServQty = parseFloat(data.promo.serviceList[xx].intQuantity).toFixed(2);

                                $upAjaxServQtyField.focus(function () {
                                    upPromoQ = parseFloat($upAjaxServTr.find('td #promoUpServQty' + ajaxServiceID).val()).toFixed(2);
                                    $upPromoQty = parseFloat($upAjaxServTr.find('td #promoUpServQty' + ajaxServiceID).val()).toFixed(2);
                                });

                                $upPromoQty = $upAjaxServQty; //main quantity
                                upPromoQ = $upAjaxServQty; //temporary quantity

                                upPromoTotal += $upPromoQty * $upAjaxServPrice;
                                serviceTotalPriceChecked += $upPromoQty * $upAjaxServPrice;
                                $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                $('#upPromoPrice').val('Php ' + parseFloat(data.promo.dblPromoPrice).toFixed(2));

                                $upAjaxServQtyField.on('input', function () {
                                    $upPromoQty = $upAjaxServQtyField.val();
                                    if ($upPromoQty > upPromoQ) {
                                        upPromoTotal += ($upPromoQty - upPromoQ) * $upAjaxServPrice;
                                        $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                        $('#upPromoPrice').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                        $('#upPromoServx' + ajaxServiceID + '').remove();
                                        $('#upPromoServItem' + ajaxServiceID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoServx' + ajaxServiceID + '"> (' + $upPromoQty + ')</span>');
                                    } else if ($upPromoQty < upPromoQ) {
                                        upPromoTotal -= (upPromoQ - $upPromoQty) * $upAjaxServPrice;
                                        $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                        $('#upPromoPrice').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                        $('#upPromoServx' + ajaxServiceID + '').remove();
                                        $('#upPromoServItem' + ajaxServiceID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoServx' + ajaxServiceID + '"> (' + $upPromoQty + ')</span>');
                                    }
                                    upPromoQ = $upPromoQty;
                                });
                            }
                        }

                    });
                }

                var upPromoPackName = $('input[name=packUpPromoSelect]');
                upPromoPackName.prop('checked', false);
                $('input[name=promoUpPackageQty]').prop('disabled', true);

                $.each(packageDataData, function(i, packe) {
                    if(packe.intPackageType != promoType) {
                        var packageRowHide = $('#promoUpPackage' + packe.intPackageID).closest('tr');
                        packageRowHide.hide();
                    } else if (packe.intPackageType != promoType) {
                        //counter
                    }
                });

                //for (var i = 0; i < data.promo.productList.length; i++) {
                for (var a = 0; a < data.promo.packageList.length; a++) {
                    var packageListPromo = data.promo.packageList[a].pack;
                    var packageIDAjax = 'promoUpPackage' + data.promo.packageList[a].pack.intPackageID;

                    upPromoPackName.each(function () {
                        var updatePromoPackageID = $(this).attr('id');

                        $.each(packageDataData, function(i, packe) {
                            if(packe.intPackageType != promoType) {
                                var packageRowHide = $('#promoUpPackage' + packe.intPackageID).closest('tr');
                                packageRowHide.hide();
                            } else if (packe.intPackageType != promoType) {
                                //counter
                            }
                        });

                        if(packageListPromo.intPackageType == promoType) {
                            if (packageIDAjax == updatePromoPackageID) {
                                var ajaxPackageID = data.promo.packageList[a].pack.intPackageID;
                                upPromoChk = upPromoChk + 1; //para malaman kung ilan or meron bang nakacheck
                                if (upPromoChk < 1) {
                                    $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
                                } else if (upPromoChk > 0) {
                                    $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
                                }

                                $('#' + packageIDAjax).prop('checked', true);
                                $('#promoUpPackageQty' + ajaxPackageID).attr('disabled', false)
                                    .val(data.promo.packageList[a].intPackageQuantity);

                                $('#updatePromoList').append('<div style="margin: 3px;" class="updatePromoChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                                    ' id="upPromoPackageItem' + ajaxPackageID + '"><b>' + data.promo.packageList[a].pack.strPackageName +
                                    '</b><span class="span"><span class="grey-text text-darken-3" id="upPromoPackx' + ajaxPackageID + '">' +
                                    ' (' + data.promo.packageList[a].intPackageQuantity + ')</span></span>' +
                                    '<i id="upPromoPackagechip' + ajaxPackageID + '" class="material-icons upPromoPackageChipExit"' +
                                    ' onclick="upPromoPackageChipExit(' + ajaxPackageID + ')" style="margin-right: 5px !important">' +
                                    'close</i></span></div>').show();


                                var $upAjaxPackageTr = $(this).closest('tr'),
                                    upAjaxPackagePrice = $upAjaxPackageTr.find('td:eq(3)').text(),
                                    $upAjaxPackageQtyField = $upAjaxPackageTr.find('td #promoUpPackageQty' + ajaxPackageID),
                                    $upAjaxPackagePrice = parseFloat(upAjaxPackagePrice.replace(/[^\d.]/g, '')).toFixed(2),
                                    $upAjaxPackageQty = parseFloat(data.promo.packageList[a].intPackageQuantity).toFixed(2);

                                $upAjaxPackageQtyField.focus(function () {
                                    upPromoQ = parseFloat($upAjaxPackageTr.find('td #promoUpPackageQty' + ajaxPackageID).val()).toFixed(2);
                                    $upPromoQty = parseFloat($upAjaxPackageTr.find('td #promoUpPackageQty' + ajaxPackageID).val()).toFixed(2);
                                });

                                $upPromoQty = $upAjaxPackageQty; //main quantity
                                upPromoQ = $upAjaxPackageQty; //temporary quantity

                                upPromoTotal += $upPromoQty * $upAjaxPackagePrice;
                                packageTotalPriceChecked += $upPromoQty * $upAjaxPackagePrice;
                                $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                $('#upPromoPrice').val('Php ' + parseFloat(data.promo.dblPromoPrice).toFixed(2));

                                $upAjaxPackageQtyField.on('input', function () {
                                    $upPromoQty = $upAjaxPackageQtyField.val();
                                    if ($upPromoQty > upPromoQ) {
                                        upPromoTotal += ($upPromoQty - upPromoQ) * $upAjaxPackagePrice;
                                        $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                        $('#upPromoPrice').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                        $('#upPromoPackx' + ajaxPackageID + '').remove();
                                        $('#upPromoPackageItem' + ajaxPackageID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoPackx' + ajaxPackageID + '"> (' + $upPromoQty + ')</span>');
                                    } else if ($upPromoQty < upPromoQ) {
                                        upPromoTotal -= (upPromoQ - $upPromoQty) * $upAjaxPackagePrice;
                                        $('#upPromoTotal').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                        $('#upPromoPrice').val('Php ' + parseFloat(upPromoTotal).toFixed(2));
                                        $('#upPromoPackx' + ajaxPackageID + '').remove();
                                        $('#upPromoPackageItem' + ajaxPackageID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoPackx' + ajaxPackageID + '"> (' + $upPromoQty + ')</span>');
                                    }
                                    upPromoQ = $upPromoQty;
                                });
                            }
                        }

                    });
                }


            } else {
                console.log('error');
            }
        }

    });
}

function promoUpProdCompute(upProdID) {
    var updateProdID = $('#promoUpProdCheck' + upProdID);
    if (updateProdID.is(':checked')) { //if product checkbox is checked
        var $upProdTR = updateProdID.closest('tr'), // PRODUCT TR
            upProdPrice = $upProdTR.find('td:eq(3)').text(),
            $upProductPrice = parseFloat(upProdPrice.replace(/[^\d.]/g, '')).toFixed(2), // PRODUCT PRICE (CONVERTED ALREADY)
            $upProductQtyField = $upProdTR.find('td #promoUpProdQty' + upProdID), // quantity field of product
            upProductQuantity = parseFloat($upProductQtyField.val()).toFixed(2);

        upPromoChk = upPromoChk + 1;
        if (upPromoChk < 1) {
            $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upPromoChk > 0) {
            $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#promoUpProdQty' + upProdID).attr('disabled', false);

        $upProductQtyField.focus(function () {
            upPromoQ = parseFloat($upProdTR.find('td #promoUpProdQty' + upProdID).val()).toFixed(2);
            $upPromoQty = parseFloat($upProdTR.find('td #promoUpProdQty' + upProdID).val()).toFixed(2);
        });

        $upPromoQty = upProductQuantity;
        upPromoTotal += $upPromoQty * $upProductPrice;
        $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
        $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

        upPromoQ = upProductQuantity;
        var upProdShowQty = parseInt($upPromoQty); // for display quantity in selected items
        $upProductQtyField.on('input', function () {
            $upPromoQty = parseFloat($upProdTR.find('td #promoUpProdQty' + upProdID).val()).toFixed(2);
            if ($upPromoQty > upPromoQ) {
                upPromoTotal += ($upPromoQty - upPromoQ) * $upProductPrice;
                console.log('qty ' + $upPromoQty);
                console.log('subqty ' + upPromoQ);
                console.log('price ' + $upProductPrice);
                upProdShowQty = parseInt($upPromoQty);
                upPromoTotal = Math.abs(upPromoTotal);
                $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoProdx' + upProdID + '').remove();
                $('#upPromoProdItem' + upProdID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoProdx' + upProdID + '"> (' + upProdShowQty + ')</span>');
            } else if (upPromoQ > $upPromoQty) {
                upPromoTotal -= (upPromoQ - $upPromoQty) * $upProductPrice;
                upPromoTotal = Math.abs(upPromoTotal);
                upProdShowQty = parseInt($upPromoQty);
                $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoProdx' + upProdID + '').remove();
                $('#upPromoProdItem' + upProdID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoProdx' + upProdID + '"> (' + upProdShowQty + ')</span>');
            } else {

            }
            upPromoQ = $upPromoQty;
        });

        var upProdName = $upProdTR.find('td:eq(1)').text();

        $('#updatePromoList').append('<div style="margin: 3px;" class="updatePromoChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            'id="upPromoProdItem' + upProdID + '"><b>' + upProdName + '</b><span class="span"><span class="grey-text text-darken-3" id="upPromoProdx' + upProdID + '">' +
            ' (' + upProdShowQty + ')</span></span><i id="upPromoProdchip' + upProdID + '" class="material-icons upPackChipExit" style="margin-right: 5px' +
            '!important" onclick="upPromoChipExit(' + upProdID + ')">close</i></div>').show();

    } else if (!(updateProdID.is(':checked'))) {

        upPromoChk = upPromoChk - 1;
        if (upPromoChk < 1) {
            $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upPromoChk > 0) {
            $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
        }
        var upUnProdIDQty = $('#promoUpProdQty' + upProdID),
            $unProdTR = updateProdID.closest('tr'),
            unPrice = $unProdTR.find('td:eq(3)').text(),
            $unPrice = parseFloat(unPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upPromoQty = parseFloat($unProdTR.find('td #promoUpProdQty' + upProdID).val()).toFixed(2);
        upPromoTotal = upPromoTotal - ($upPromoQty * $unPrice);
        upPromoTotal = Math.abs(upPromoTotal);
        $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
        $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

        $('#upPromoProdItem' + upProdID).remove();
        upUnProdIDQty.attr('disabled', true);
        upUnProdIDQty.val(1);

    }

    $('#upPromoProdchip' + upProdID).click(function () {
        upPromoChk = upPromoChk - 1;
        console.log(upPromoChk);
        if (upPromoChk < 1) {
            $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upPromoChk > 0) {
            $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        var upProdChipQty = $('#promoUpProdQty' + upProdID),
            $upUntr = updateProdID.closest('tr'),
            upUnChipPrice = $upUntr.find('td:eq(3)').text(),
            $upUnChipPrice = parseFloat(upUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upPromoQty = parseFloat($upUntr.find('td #promoUpProdQty' + upProdID).val()).toFixed(2);
        upPromoTotal = upPromoTotal - ($upPromoQty * $upUnChipPrice);
        upPromoTotal = Math.abs(upPromoTotal);
        $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
        $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

        $('#upPromoProdItem' + upProdID).remove();
        $('#promoUpProdCheck' + upProdID).prop('checked', false);
        upProdChipQty.attr('disabled', true);
        upProdChipQty.val(1);
    });
}

function upPromoChipExit(chipExitID) {
    upPromoChk = upPromoChk - 1;
    if (upPromoChk < 1) {
        $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
    } else if (upPromoChk > 0) {
        $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
    }

    var upProdChipQty = $('#promoUpProdQty' + chipExitID),
        upProdCheck = $('#promoUpProdCheck' + chipExitID),
        $upUntr = upProdCheck.closest('tr'),
        upUnChipPrice = $upUntr.find('td:eq(3)').text(),
        $upUnChipPrice = parseFloat(upUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
    $upPromoQty = parseFloat($upUntr.find('td #promoUpProdQty' + chipExitID).val()).toFixed(2);
    upPromoTotal = upPromoTotal - ($upPromoQty * $upUnChipPrice);
    upPromoTotal = Math.abs(upPromoTotal);
    $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
    $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

    $('#upPromoProdItem' + chipExitID).remove();
    upProdCheck.prop('checked', false);
    upProdChipQty.attr('disabled', true);
    upProdChipQty.val(1);
}

function promoUpServCompute(upServID) {

    var updateServID = $('#promoUpServCheck' + upServID);
    if (updateServID.is(':checked')) { //if product checkbox is checked
        var $upServTR = updateServID.closest('tr'), // PRODUCT TR
            upServPrice = $upServTR.find('td:eq(3)').text(),
            $upServicePrice = parseFloat(upServPrice.replace(/[^\d.]/g, '')).toFixed(2), // PRODUCT PRICE (CONVERTED ALREADY)
            $upServiceQtyField = $upServTR.find('td #promoUpServQty' + upServID), // quantity field of product
            upServiceQuantity = parseFloat($upServiceQtyField.val()).toFixed(2);

        upPromoChk = upPromoChk + 1;
        if (upPromoChk < 1) {
            $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upPromoChk > 0) {
            $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#promoUpServQty' + upServID).attr('disabled', false);

        $upServiceQtyField.focus(function () {
            upPromoQ = parseFloat($upServTR.find('td #promoUpServQty' + upServID).val()).toFixed(2);
            $upPromoQty = parseFloat($upServTR.find('td #promoUpServQty' + upServID).val()).toFixed(2);
        });

        $upPromoQty = upServiceQuantity;
        upPromoTotal += $upPromoQty * $upServicePrice;
        serviceTotalPriceChecked += $upPromoQty * $upServicePrice;
        $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
        $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

        upPromoQ = upServiceQuantity;
        var upServShowQty = parseInt($upPromoQty); // for display quantity in selected items
        $upServiceQtyField.on('input', function () {
            $upQty = parseFloat($upServTR.find('td #promoUpServQty' + upServID).val()).toFixed(2);
            if ($upPromoQty > upPromoQ) {
                upPromoTotal += ($upPromoQty - upPromoQ) * $upServicePrice;
                upServShowQty = parseInt($upPromoQty);
                upPromoTotal = Math.abs(upPromoTotal);
                serviceTotalPriceChecked -= (upPromoQ - $upPromoQty) * $upServicePrice;
                $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoServx' + upServID + '').remove();
                $('#upPromoServItem' + upServID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoServx' + upServID + '"> (' + upServShowQty + ')</span>');
            } else if (upPromoQ > $upPromoQty) {
                upPromoTotal -= (upPromoQ - $upPromoQty) * $upServicePrice;
                upPromoTotal = Math.abs(upPromoTotal);
                upServShowQty = parseInt($upPromoQty);
                serviceTotalPriceChecked -= (upPromoQ - $upPromoQty) * $upServicePrice;
                $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoServx' + upServID + '').remove();
                $('#upPromoServItem' + upServID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoServx' + upServID + '"> (' + upServShowQty + ')</span>');
            } else {

            }
            upPromoQ = $upPromoQty;
        });

        var upServName = $upServTR.find('td:eq(1)').text();

        $('#updatePromoList').append('<div style="margin: 3px;" class="updatePromoChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            ' id="upPromoServItem' + upServID + '"><b>' + upServName + '</b><span class="span"><span class="grey-text text-darken-3" id="upPromoServx' + upServID + '">' +
            ' (' + upServShowQty + ')</span></span><i id="upPromoServchip' + upServID + '" class="material-icons upPromoChipExit" style="margin-right: 5px' +
            '!important" onclick="upPromoServChipExit(' + upServID + ')">close</i></div>').show();

    } else if (!(updateServID.is(':checked'))) {
        upPromoChk = upPromoChk - 1;
        if (upPromoChk < 1) {
            $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upPromoChk > 0) {
            $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        var upUnServIDQty = $('#promoUpServQty' + upServID),
            $unServTR = updateServID.closest('tr'),
            unPrice = $unServTR.find('td:eq(3)').text(),
            $unPrice = parseFloat(unPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upPromoQty = parseFloat($unServTR.find('td #promoUpServQty' + upServID).val()).toFixed(2);
        upPromoTotal = upPromoTotal - ($upPromoQty * $unPrice);
        upPromoTotal = Math.abs(upPromoTotal);
        serviceTotalPriceChecked = serviceTotalPriceChecked - ($upPromoQty * $unPrice);
        $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
        $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

        $('#upPromoServItem' + upServID).remove();
        upUnServIDQty.attr('disabled', true);
        upUnServIDQty.val(1);

    }

    $('#upPromoServchip' + upServID).click(function () {
        upPromoChk = upPromoChk - 1;
        if (upPromoChk < 1) {
            $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upPromoChk > 0) {
            $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        var upServChipQty = $('#promoUpServQty' + upServID),
            $upUntr = updateServID.closest('tr'),
            upUnChipPrice = $upUntr.find('td:eq(3)').text(),
            $upUnChipPrice = parseFloat(upUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upPromoQty = parseFloat($upUntr.find('td #promoUpServQty' + upServID).val()).toFixed(2);
        upPromoTotal = upPromoTotal - ($upPromoQty * $upUnChipPrice);
        upPromoTotal = Math.abs(upPromoTotal);
        serviceTotalPriceChecked = serviceTotalPriceChecked - ($upPromoQty * $upUnChipPrice);
        $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
        $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

        $('#upPromoServItem' + upServID).remove();
        $('#promoUpServCheck' + upServID).prop('checked', false);
        upServChipQty.attr('disabled', true);
        upServChipQty.val(1);
    });
}

function upPromoServChipExit(chipExitID) {
    upPromoChk = upPromoChk - 1;
    if (upPromoChk < 1) {
        $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
    } else if (upPromoChk > 0) {
        $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
    }

    var upServChipQty = $('#promoUpServQty' + chipExitID),
        upServCheck = $('#promoUpServCheck' + chipExitID),
        $upUntr = upServCheck.closest('tr'),
        upUnChipPrice = $upUntr.find('td:eq(3)').text(),
        $upUnChipPrice = parseFloat(upUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
    $upPromoQty = parseFloat($upUntr.find('td #promoUpServQty' + chipExitID).val()).toFixed(2);
    upPromoTotal = upPromoTotal - ($upPromoQty * $upUnChipPrice);
    upPromoTotal = Math.abs(upPromoTotal);
    serviceTotalPriceChecked = serviceTotalPriceChecked - ($upPromoQty * $upUnChipPrice);
    $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
    $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

    $('#upPromoServItem' + chipExitID).remove();
    upServCheck.prop('checked', false);
    upServChipQty.attr('disabled', true);
    upServChipQty.val(1);
}

function promoUpPackageCompute(packageID) {


    var updatePackageID = $('#promoUpPackage' + packageID);
    if (updatePackageID.is(':checked')) { //if product checkbox is checked
        var $upPackageTR = updatePackageID.closest('tr'), // PRODUCT TR
            upPackagePrice = $upPackageTR.find('td:eq(3)').text(),
            $upPackagePrice = parseFloat(upPackagePrice.replace(/[^\d.]/g, '')).toFixed(2), // PRODUCT PRICE (CONVERTED ALREADY)
            $upPackageQtyField = $upPackageTR.find('td #promoUpPackageQty' + packageID), // quantity field of product
            upPackageQuantity = parseFloat($upPackageQtyField.val()).toFixed(2);

        upPromoChk = upPromoChk + 1;
        if (upPromoChk < 1) {
            $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upPromoChk > 0) {
            $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#promoUpPackageQty' + packageID).attr('disabled', false);

        $upPackageQtyField.focus(function () {
            upPromoQ = parseFloat($upPackageTR.find('td #promoUpPackageQty' + packageID).val()).toFixed(2);
            $upPromoQty = parseFloat($upPackageTR.find('td #promoUpPackageQty' + packageID).val()).toFixed(2);
        });

        $upPromoQty = upPackageQuantity;
        upPromoTotal += $upPromoQty * $upPackagePrice;
        packageTotalPriceChecked += $upPromoQty * $upPackagePrice;
        $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
        $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

        upPromoQ = upPackageQuantity;
        var upPackageShowQty = parseInt($upPromoQty); // for display quantity in selected items
        $upPackageQtyField.on('input', function () {
            $upQty = parseFloat($upPackageTR.find('td #promoUpPackageQty' + packageID).val()).toFixed(2);
            if ($upPromoQty > upPromoQ) {
                upPromoTotal += ($upPromoQty - upPromoQ) * $upPackagePrice;
                upPackageShowQty = parseInt($upPromoQty);
                upPromoTotal = Math.abs(upPromoTotal);
                packageTotalPriceChecked += ($upPromoQty - upPromoQ) * $upPackagePrice;
                $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoPackx' + packageID + '').remove();
                $('#upPromoPackageItem' + packageID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoPackx' + packageID + '"> (' + upPackageShowQty + ')</span>');
            } else if (upPromoQ > $upPromoQty) {
                upPromoTotal -= (upPromoQ - $upPromoQty) * $upPackagePrice;
                upPromoTotal = Math.abs(upPromoTotal);
                upPackageShowQty = parseInt($upPromoQty);
                packageTotalPriceChecked -= (upPromoQ - $upPromoQty) * $upPackagePrice;
                $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));
                $('#upPromoPackx' + packageID + '').remove();
                $('#upPromoPackageItem' + packageID + ' .span').append('<span class="grey-text text-darken-3" id="upPromoServx' + packageID + '"> (' + upPackageShowQty + ')</span>');
            } else {

            }
            upPromoQ = $upPromoQty;
        });

        var upPackageName = $upPackageTR.find('td:eq(1)').text();

        $('#updatePromoList').append('<div style="margin: 3px;" class="updatePromoChip chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            ' id="upPromoPackageItem' + packageID + '"><b>' + upPackageName + '</b><span class="span"><span class="grey-text text-darken-3" id="upPromoPackx' + packageID + '">' +
            ' (' + upPackageShowQty + ')</span></span><i id="upPromoPackagechip' + packageID + '" class="material-icons upPromoChipExit" style="margin-right: 5px' +
            '!important" onclick="upPromoPackageChipExit(' + packageID + ')">close</i></div>').show();

    } else if (!(updatePackageID.is(':checked'))) {
        upPromoChk = upPromoChk - 1;
        if (upPromoChk < 1) {
            $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (upPromoChk > 0) {
            $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        var upUnPackIDQty = $('#promoUpPackageQty' + packageID),
            $unPackTR = updatePackageID.closest('tr'),
            unPackPrice = $unPackTR.find('td:eq(3)').text(),
            $unPackPrice = parseFloat(unPackPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $upPromoQty = parseFloat($unPackTR.find('td #promoUpPackageQty' + packageID).val()).toFixed(2);
        upPromoTotal = upPromoTotal - ($upPromoQty * $unPackPrice);
        upPromoTotal = Math.abs(upPromoTotal);
        packageTotalPriceChecked = packageTotalPriceChecked - ($upPromoQty * $unPackPrice);
        $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
        $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

        $('#upPromoPackageItem' + packageID).remove();
        upUnPackIDQty.attr('disabled', true);
        upUnPackIDQty.val(1);

    }


}

function upPromoPackageChipExit(packChipID) {
    upPromoChk = upPromoChk - 1;
    if (upPromoChk < 1) {
        $('#updatePromoSubmitBtn').attr('disabled', true).css('opacity', '0.3');
    } else if (upPromoChk > 0) {
        $('#updatePromoSubmitBtn').attr('disabled', false).css('opacity', '1');
    }

    var upPackageChipQty = $('#promoUpPackageQty' + packChipID),
        upPackageCheck = $('#promoUpPackage' + packChipID),
        $upUnPackageTr = upPackageCheck.closest('tr'),
        upUnPackChipPrice = $upUnPackageTr.find('td:eq(3)').text(),
        $upUnPackChipPrice = parseFloat(upUnPackChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
    $upPromoQty = parseFloat($upUnPackageTr.find('td #promoUpPackageQty' + packChipID).val()).toFixed(2);
    upPromoTotal = upPromoTotal - ($upPromoQty * $upUnPackChipPrice);
    packageTotalPriceChecked = packageTotalPriceChecked - ($upPromoQty * $upUnPackChipPrice);
    upPromoTotal = Math.abs(upPromoTotal);
    $('#upPromoTotal').val("Php " + parseFloat(upPromoTotal).toFixed(2));
    $('#upPromoPrice').val("Php " + parseFloat(upPromoTotal).toFixed(2));

    $('#upPromoPackageItem' + packChipID).remove();
    upPackageCheck.prop('checked', false);
    upPackageChipQty.attr('disabled', true);
    upPackageChipQty.val(1);
}

$('#errorPriceFree').hide();

function updatePromo() {
    var p = $('#upPromoPrice').val().replace(/[^\d.]/g, '');
    if(p == '0.00' && !$('#upPromoFree').is(':checked')) {
        $('#errorPriceFree').show();
    } else if(p > '0.00' && !$('#upPromoFree').is(':checked')) {
        $('#errorPriceFree').hide();
        updateActionPromo();
    } else if($('#upPromoFree').is(':checked')) {
        $('#errorPriceFree').hide();
        updateActionPromo();
    }
}

function updateActionPromo() {
    if ($('#updatePromoForm').valid()) {
        var promoUpdateProdSelect = [],
            promoUpdateServSelect = [],
            promoUpdatePackageSelect = [],
            selectedPromoType = [],
            requirement = [];

        $.each($("input[name=prodUpPromoSelect]:checked"), function () {
            promoUpdateProdSelect.push($(this).val());
        });
        $.each($("input[name=servUpPromoSelect]:checked"), function () {
            promoUpdateServSelect.push($(this).val());
        });
        $.each($("input[name=packUpPromoSelect]:checked"), function () {
            promoUpdatePackageSelect.push($(this).val());
        });

        var promoUpdateProductQty = $('input[name=prodUpdatePromoQty]:enabled').map(function () {
            return this.value;
        }).get(); //get all the quantity enabled in product
        var promoUpdateServiceQty = $('input[name=servUpdatePromoQty]:enabled').map(function () {
            return this.value;
        }).get(); //get all the quantity enabled in service
        var promoUpdatePackQty = $('input[name=packUpdatePromoQty]:enabled').map(function () {
            return this.value;
        }).get();

        promoUpdateProdSelect = promoUpdateProdSelect.join(', ');
        promoUpdateServSelect = promoUpdateServSelect.join(', ');
        promoUpdatePackageSelect = promoUpdatePackageSelect.join(', ');
        var prodQty = promoUpdateProductQty.join(', '),
            servQty = promoUpdateServiceQty.join(', '),
            packQty = promoUpdatePackQty.join(', ');
        var promoname = $('#upPromoName').val();

        var req = $('#upPromoRequirement').val();
        if(req != null) {
            requirement = req.join(',');
        }

        selectedPromoType = $('#upPromoType').val();

        var upWalk = 0,
            upHome = 0,
            upEvent = 0,
            upPromoType = [];

        for (var i = 0; i < selectedPromoType.length; i++) {
            if (selectedPromoType[i] == 'walkin') {
                upWalk = 1;
            } else if (selectedPromoType[i] == 'homeservice') {
                upHome = 1;
            } else if (selectedPromoType[i] == 'event') {
                upEvent = 1;
            }
        }

        if (upWalk == 1 && upHome == 0 && upEvent == 0) {
            upPromoType.push('1');
        } else if (upWalk == 0 && upHome == 1 && upEvent == 0) {
            upPromoType.push('2');
        } else if (upWalk == 0 && upHome == 0 && upEvent == 1) {
            upPromoType.push('3');
        } else if (upWalk == 1 && upHome == 1 && upEvent == 0) {
            upPromoType.push('1');
            upPromoType.push('2');
        } else if (upWalk == 1 && upHome == 0 && upEvent == 1) {
            upPromoType.push('1');
            upPromoType.push('3');
        } else if (upWalk == 0 && upHome == 1 && upEvent == 1) {
            upPromoType.push('2');
            upPromoType.push('3');
        } else if (upWalk == 1 && upHome == 1 && upEvent == 1) {
            upPromoType.push('1');
            upPromoType.push('2');
            upPromoType.push('3');
        }


        upPromoType = upPromoType.join(',');


        console.log(promoUpdateProdSelect);

        swal({
                title: "Are you sure you want to update " + promoname + "?",
                text: "",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            },
            function () {
                setTimeout(function () {
                    $.ajax({
                        url: 'updatePromo',
                        type: 'post',
                        data: {
                            "intPromoID": $('#upPromoID').val(),
                            "strPromoName": promoname,
                            "strPromoDesc": $('#upPromoDescription').val(),
                            "strPromoGuidelines": $('#upPromoGuidelines').val(),
                            "strNonExp": $("#upPromoNonExpiry:checked").val(),
                            "strExp": $('#upPromoExpiration').val(),
                            "servicePromoSelect": promoUpdateServSelect,
                            "productPromoSelect": promoUpdateProdSelect,
                            "packagePromoSelect": promoUpdatePackageSelect,
                            "servicePromoQty": servQty,
                            "productPromoQty": prodQty,
                            "packagePromoQty": packQty,
                            "strFree": $("input[name=upFree]:checked").val(),
                            "dblPromoPrice": $('#upPromoPrice').val().replace(/[^\d.]/g, ''),
                            "requirement": requirement,
                            "type": upPromoType
                        },
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                            console.log(data);
                            swal("Successfully created!", ".", "success");
                            updatePromoTable();
                            $('#upPromoModal').closeModal();
                        },
                        error: function () {
                            sweetAlert("Oops...", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });
    }
}