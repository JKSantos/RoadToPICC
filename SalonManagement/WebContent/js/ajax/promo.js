/**
 * Created by Castillo on 8/6/2016.
 */
window.onload = updatePromoTable();
window.onload = updatePromoCreateProdTable();
window.onload = updatePromoCreateServTable();
window.onload = updatePromoCreatePackageTable();

function updatePromoTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllPromo',
        dataType: 'json',
        async: true,
        beforeSend: function () {
            $('#promoloadingupdate').show();
        },
        success: function (data) {
            var promoList = data.promoList,
                tablepromo = $('#promotbl').DataTable();

            if (promoList != null) {
                tablepromo.clear().draw();
                $.each(promoList, function (i, promo) {
                    var price,
                        guidelines;
                    var addbtn = "<button class='waves-effect waves-purple btn-flat transparent black-text'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + promo.intPromoID + "'" +
                        " onclick='openUpdatePromo(this.value)'>" +
                        "<i class='material-icons'>edit</i></button>" +
                        "<button id='deactivateID" + promo.intPromoID + "' class='waves-effect waves-purple btn-flat transparent red-text text-accent-4'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + promo.intPromoID + "' title='Deactivate'" +
                        " onclick='deactivatePromo(this.value, this.id)'>" +
                        "<i class='material-icons'>delete</i></button>";
                    if (promo.dblPromoPrice == 0.0) {
                        price = 'Free';
                    } else {
                        price = 'Php ' + parseFloat(promo.dblPromoPrice).toFixed(2);
                        price = addCommas(price);
                    }

                    if (promo.strPromoGuidelines == null || promo.strPromoGuidelines == '') {
                        guidelines = 'None';
                    } else {
                        guidelines = promo.strPromoGuidelines;
                    }


                    var crPromoDate,
                        sub = promo.strPromoAvailability.split('-'),
                        availabilityYear = sub[0],
                        availabilityMonth = sub[1],
                        availabilityDay = sub[2]
                    console.log(availabilityYear);
                    if (promo.strPromoAvailability == 'NON-EXPIRY') {
                        crPromoDate = 'NON-EXPIRY';
                    } else {
                        crPromoDate = moment("'" + availabilityMonth + '/' + availabilityDay + '/' + availabilityYear + "'", 'MM/DD/YYYY')
                            .format('LL');
                    }
                    console.log(availabilityYear + '+' + availabilityMonth + '+' + availabilityDay);

                    tablepromo.row.add([
                        promo.strPromoName,
                        guidelines,
                        price,
                        crPromoDate,
                        addbtn
                    ]);
                });
                tablepromo.draw();
            }


        },
        complete: function () {
            $('#promoloadingupdate').fadeOut(300);
        }
    });
}

function updatePromoCreateProdTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllProduct',
        dataType: 'json',
        async: true,
        success: function (data) {
            var productList = data.productList,
                createPromoProdTbl = $('#crpromotblprod').DataTable({
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

            $("#crPromoSearch").bind('keyup search input paste cut', function () {
                createPromoProdTbl.search(this.value).draw();
            });

            if (productList != null) {
                createPromoProdTbl.clear().draw();
                $.each(productList, function (i, product) {
                    var price = parseFloat(product.dblProductPrice).toFixed(2);
                    price = addCommas(price);
                    var checkbox = "<input type='checkbox' name='prodCrPromoSelect' id='promoProdCheck" + product.intProductID + "'" +
                            " class='packcheckbox x" + product.intProductID + "' value='" + product.intProductID + "' onclick='promoProdCompute(this.value)'>" +
                            "<label for='promoProdCheck" + product.intProductID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='crProdPromoQty'" +
                            " id='promoProdQty" + product.intProductID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                    price = "<span class='price'>P " + price + "</span>";

                    createPromoProdTbl.row.add([
                        checkbox,
                        product.strProductName,
                        product.strProductCategory,
                        price,
                        quantity
                    ]);
                });
                createPromoProdTbl.draw();
            }
        }
    });
}

function updatePromoCreateServTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllService',
        dataType: 'json',
        async: true,
        success: function (data) {
            var serviceList = data.serviceList,
                createPromoServTbl = $('#crpromotblserv').DataTable({
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

            $("#crPromoSearch").bind('keyup search input paste cut', function () {
                createPromoServTbl.search(this.value).draw();
            });

            if (serviceList != null) {
                createPromoServTbl.clear().draw();
                $.each(serviceList, function (i, service) {
                    var price = parseFloat(service.dblServicePrice).toFixed(2);
                    price = addCommas(price);
                    var checkbox = "<input type='checkbox' name='servCrPromoSelect' id='crPromoServ" + service.intServiceID + "'" +
                            " class='packcheckbox x" + service.intServiceID + "' value='" + service.intServiceID + "' onclick='promoServCompute(this.value)'>" +
                            "<label for='crPromoServ" + service.intServiceID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='crServPromoQty'" +
                            " id='promoServQty" + service.intServiceID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                    price = "<span class='price'>P " + price + "</span>";

                    createPromoServTbl.row.add([
                        checkbox,
                        service.strServiceName,
                        service.strServiceCategory,
                        price,
                        quantity
                    ]);
                });
                createPromoServTbl.draw();
            }
        }
    });
}

function updatePromoCreatePackageTable() {
    $.ajax({
        type: 'post',
        url: 'api/v1/getAllPackage',
        dataType: 'json',
        async: true,
        success: function (data) {
            var packageList = data.packageList,
                createPromoPackageTbl = $('#crpromotblpackage').DataTable({
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

            $("#crPromoSearch").bind('keyup search input paste cut', function () {
                createPromoPackageTbl.search(this.value).draw();
            });

            if (packageList != null) {
                createPromoPackageTbl.clear().draw();
                $.each(packageList, function (i, packagedata) {
                    var cat = 'Package';
                    var price = parseFloat(packagedata.dblPackagePrice).toFixed(2);
                    price = addCommas(price);
                    var checkbox = "<input type='checkbox' name='packCrPromoSelect' id='crPromoPackage" + packagedata.intPackageID + "'" +
                            " class='packcheckbox x" + packagedata.intPackageID + "' value='" + packagedata.intPackageID + "' onclick='promoPackageCompute(this.value)'>" +
                            "<label for='crPromoPackage" + packagedata.intPackageID + "'></label>",
                        quantity = "<input type='number' class='right-align rowQty' name='crPackPromoQty'" +
                            " id='promoPackageQty" + packagedata.intPackageID + "' disabled style='width: 75px' min='1' max='99' value='1' maxlength='2'>";
                    price = "<td class='dt-body-right'>P " + price + "</td>";

                    createPromoPackageTbl.row.add([
                        checkbox,
                        packagedata.strPackageName,
                        cat,
                        price,
                        quantity
                    ]);
                });
                createPromoPackageTbl.draw();
            }
        }
    });
}

var promoQ = 0, // temporary quantity
    $promoQty = 0, //main quantity
    promoTotal = 0, //total
    promoChk = 0;
function promoProdCompute(id) {
    var promoProductID = $('#promoProdCheck' + id);
    if (promoProductID.is(':checked')) { //if product checkbox is checked
        var $promoProdTR = promoProductID.closest('tr'), // PRODUCT TR
            promoProdPrice = $promoProdTR.find('td:eq(3)').text(),
            $promoProductPrice = parseFloat(promoProdPrice.replace(/[^\d.]/g, '')).toFixed(2), // PRODUCT PRICE (CONVERTED ALREADY)
            $promoProductQtyField = $promoProdTR.find('td #promoProdQty' + id), // quantity field of product
            promoProductQuantity = parseFloat($promoProductQtyField.val()).toFixed(2);

        promoChk = promoChk + 1;
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (promoChk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#promoProdQty' + id).attr('disabled', false);

        $promoProductQtyField.focus(function () {
            promoQ = parseFloat($promoProdTR.find('td #promoProdQty' + id).val()).toFixed(2);
            $promoQty = parseFloat($promoProdTR.find('td #promoProdQty' + id).val()).toFixed(2);
        });

        $promoQty = promoProductQuantity;
        promoTotal += $promoQty * $promoProductPrice;
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));

        promoQ = promoProductQuantity;
        var promoProdShowQty = parseInt($promoQty); // for display quantity in selected items
        $promoProductQtyField.on('input', function () {
            $promoQty = parseFloat($promoProdTR.find('td #promoProdQty' + id).val()).toFixed(2);
            if ($promoQty > promoQ) {
                promoTotal += ($promoQty - promoQ) * $promoProductPrice;
                promoProdShowQty = parseInt($promoQty);
                console.log(promoProdShowQty);
                promoTotal = Math.abs(promoTotal);
                $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#promoProdx' + id).remove();
                $('#promoProdItem' + id + ' .span').append('<span class="grey-text text-darken-3" id="promoProdx' + id + '"> (' + promoProdShowQty + ')</span>');
            } else if (promoQ > $promoQty) {
                promoTotal -= (promoQ - $promoQty) * $promoProductPrice;
                promoTotal = Math.abs(promoTotal);
                promoProdShowQty = parseInt($promoQty);
                $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#promoProdx' + id).remove();
                $('#promoProdItem' + id + ' .span').append('<span class="grey-text text-darken-3" id="promoProdx' + id + '"> (' + promoProdShowQty + ')</span>');
            } else {

            }
            promoQ = $promoQty;
        });

        var promoProdName = $promoProdTR.find('td:eq(1)').text();

        $('#promoList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            ' id="promoProdItem' + id + '"><b>' + promoProdName + '</b><span class="span"><span class="grey-text text-darken-3" id="promoProdx' + id + '">' +
            ' (' + promoProdShowQty + ')</span></span>' + '<i id="promoProdChip' + id + '" class="material-icons" style="margin-right: 5px' +
            '!important">close</i></div>').show();

    } else if (!(promoProductID.is(':checked'))) {
        promoChk = promoChk - 1;
        console.log(promoChk);
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (promoChk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var promoProdIDQty = $('#promoProdQty' + id),
            $unPromoProdTR = promoProductID.closest('tr'),
            unPromoPrice = $unPromoProdTR.find('td:eq(3)').text(),
            $unPromoPrice = parseFloat(unPromoPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $promoQty = parseFloat($unPromoProdTR.find('td #promoProdQty' + id).val()).toFixed(2);
        promoTotal = promoTotal - ($promoQty * $unPromoPrice);
        promoTotal = Math.abs(promoTotal);
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));

        $('#promoProdItem' + id).remove();
        promoProdIDQty.attr('disabled', true);
        promoProdIDQty.val(1);

    }

    $('#promoProdChip' + id).click(function () {
        promoChk = promoChk - 1;
        console.log(promoChk);
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var promoProdChipQty = $('#promoProdQty' + id),
            $uChipPromoProdTr = promoProductID.closest('tr'),
            unPromoChipPrice = $uChipPromoProdTr.find('td:eq(3)').text(),
            $unPromoChipPrice = parseFloat(unPromoChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $promoQty = parseFloat($uChipPromoProdTr.find('td #promoProdQty' + id).val()).toFixed(2);
        promoTotal = promoTotal - ($promoQty * $unPromoChipPrice);
        promoTotal = Math.abs(promoTotal);
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));

        $('#promoProdItem' + id).remove();
        $('#promoProdCheck' + id).prop('checked', false);
        promoProdChipQty.attr('disabled', true);
        promoProdChipQty.val(1);
    });

}

function promoServCompute(id) {

    var promoServiceID = $('#crPromoServ' + id);
    if (promoServiceID.is(':checked')) { //if service checkbox is checked
        $('#promoServQty' + id).attr('disabled', false);
        //
        promoChk = promoChk + 1;
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (promoChk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }
        //
        var $promoServTR = promoServiceID.closest('tr'), // SERVICE TR
            promoServPrice = $promoServTR.find('td:eq(3)').text(),
            $promoServicePrice = parseFloat(promoServPrice.replace(/[^\d.]/g, '')).toFixed(2), //SERVICE PRICE (CONVERTED ALREADY)
            $promoServiceQtyField = $promoServTR.find('td #promoServQty' + id), // quantity field of service
            promoServiceQuantity = parseFloat($promoServiceQtyField.val()).toFixed(2);
        $promoServiceQtyField.focus(function () {
            promoQ = parseFloat($promoServTR.find('td #promoServQty' + id).val()).toFixed(2);
            $promoQty = parseFloat($promoServTR.find('td #promoServQty' + id).val()).toFixed(2);
        });

        $promoQty = promoServiceQuantity;
        promoTotal += $promoQty * $promoServicePrice;
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));
        promoQ = promoServiceQuantity;
        var promoServShowQty = parseInt($promoQty);

        $promoServiceQtyField.on('input', function () {
            $promoQty = parseFloat($promoServTR.find('td #promoServQty' + id).val()).toFixed(2);
            if ($promoQty > promoQ) {
                promoTotal += ($promoQty - promoQ) * $promoServicePrice;
                promoServShowQty = parseInt($promoQty);
                promoTotal = Math.abs(promoTotal);
                $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#promoList #promoServx' + id + '').remove();
                $('#promoList #promoServItem' + id + ' .span').append('<span class="grey-text text-darken-3" id="promoServx' + id + '"> (' + promoServShowQty + ')</span>');
            } else if (promoQ > $promoQty) {
                promoTotal -= (promoQ - $promoQty) * $promoServicePrice;
                promoTotal = Math.abs(promoTotal);
                promoServShowQty = parseInt($promoQty);
                $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#promoList #promoServx' + id + '').remove();
                $('#promoList #promoServItem' + id + ' .span').append('<span class="grey-text text-darken-3" id="promoServx' + id + '"> (' + promoServShowQty + ')</span>');
            } else {

            }
            promoQ = $promoQty;
        });

        var promoServiceName = $promoServTR.find('td:eq(1)').text();

        $('#promoList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            'id="promoServItem' + id + '"><b>' + promoServiceName + '</b><span class="span"><span class="grey-text text-darken-3" id="promoServx' + id + '">' +
            ' (' + promoServShowQty + ')</span></span>' + '<i id="promoServChip' + id + '" class="material-icons" style="margin-right: 5px' +
            '!important">close</i></div>').show();

    } else if (!(promoServiceID.is(':checked'))) {
        promoChk = promoChk - 1;
        console.log(promoChk);
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (promoChk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var promoServIDQty = $('#promoServQty' + id),
            $promoSUtr = promoServiceID.closest('tr'),
            promoSUnChipPrice = $promoSUtr.find('td:eq(3)').text(),
            $promoSUnChipPrice = parseFloat(promoSUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $promoQty = parseFloat($promoSUtr.find('td #promoServQty' + id).val()).toFixed(2);
        promoTotal = promoTotal - ($promoQty * $promoSUnChipPrice);
        promoTotal = Math.abs(promoTotal);
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));

        $('#promoServItem' + id).remove();
        promoServiceID.prop('checked', false);
        promoServIDQty.attr('disabled', true);
        promoServIDQty.val(1);

    }

    $('#promoServChip' + id).click(function () {
        promoChk = promoChk - 1;
        console.log(promoChk);
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (promoChk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var promoServChipQty = $('#promoServQty' + id),
            $promoSUtr = promoServiceID.closest('tr'),
            promoSUnChipPrice = $promoSUtr.find('td:eq(3)').text(),
            $promoSUnchipPrice = parseFloat(promoSUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $promoQty = parseFloat($promoSUtr.find('td #promoServQty' + id).val()).toFixed(2);
        promoTotal = promoTotal - ($promoQty * $promoSUnchipPrice);
        promoTotal = Math.abs(promoTotal);
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));

        $('#promoServItem' + id).remove();
        promoServiceID.prop('checked', false);
        promoServChipQty.attr('disabled', true);
        promoServChipQty.val(1);
    });
}


function promoPackageCompute(id) {

    var promoPackageID = $('#crPromoPackage' + id);
    if (promoPackageID.is(':checked')) { //if service checkbox is checked
        $('#promoPackageQty' + id).attr('disabled', false);
        //
        promoChk = promoChk + 1;
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (promoChk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }
        //
        var $promoPackTR = promoPackageID.closest('tr'), // SERVICE TR
            promoPackPrice = $promoPackTR.find('td:eq(3)').text(),
            $promoPackagePrice = parseFloat(promoPackPrice.replace(/[^\d.]/g, '')).toFixed(2), //SERVICE PRICE (CONVERTED ALREADY)
            $promoPackageQtyField = $promoPackTR.find('td #promoPackageQty' + id), // quantity field of service
            promoPackageQuantity = parseFloat($promoPackageQtyField.val()).toFixed(2);

        $promoPackageQtyField.focus(function () {
            promoQ = parseFloat($promoPackTR.find('td #promoPackageQty' + id).val()).toFixed(2);
            $promoQty = parseFloat($promoPackTR.find('td #promoPackageQty' + id).val()).toFixed(2);
        });

        $promoQty = promoPackageQuantity;
        promoTotal += $promoQty * $promoPackagePrice;
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));
        promoQ = promoPackageQuantity;
        var promoPackShowQty = parseInt($promoQty);

        $promoPackageQtyField.on('input', function () {
            $promoQty = parseFloat($promoPackTR.find('td #promoPackageQty' + id).val()).toFixed(2);
            if ($promoQty > promoQ) {
                promoTotal += ($promoQty - promoQ) * $promoPackagePrice;
                promoPackShowQty = parseInt($promoQty);
                promoTotal = Math.abs(promoTotal);
                $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#promoList #promoPackx' + id + '').remove();
                $('#promoList #promoPackItem' + id + ' .span').append('<span class="grey-text text-darken-3" id="promoPackx' + id + '"> (' + promoPackShowQty + ')</span>');
            } else if (promoQ > $promoQty) {
                promoTotal -= (promoQ - $promoQty) * $promoPackagePrice;
                promoTotal = Math.abs(promoTotal);
                promoPackShowQty = parseInt($promoQty);
                $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));
                $('#promoList #promoPackx' + id + '').remove();
                $('#promoList #promoPackItem' + id + ' .span').append('<span class="grey-text text-darken-3" id="promoPackx' + id + '"> (' + promoPackShowQty + ')</span>');
            } else {

            }
            promoQ = $promoQty;
        });

        var promoPackageName = $promoPackTR.find('td:eq(1)').text();

        $('#promoList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
            ' id="promoPackItem' + id + '"><b>' + promoPackageName + '</b><span class="span"><span class="grey-text text-darken-3" id="promoPackx' + id + '">' +
            ' (' + promoPackShowQty + ')</span></span>' + '<i id="promoPackChip' + id + '" class="material-icons" style="margin-right: 5px' +
            '!important">close</i></div>').show();

    } else if (!(promoPackageID.is(':checked'))) {
        promoChk = promoChk - 1;
        console.log(promoChk);
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (promoChk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var promoPackIDQty = $('#promoPackageQty' + id),
            $promoPUtr = promoPackageID.closest('tr'),
            promoPUnChipPrice = $promoPUtr.find('td:eq(3)').text(),
            $promoPUnChipPrice = parseFloat(promoPUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $promoQty = parseFloat($promoPUtr.find('td #promoPackageQty' + id).val()).toFixed(2);
        promoTotal = promoTotal - ($promoQty * $promoPUnChipPrice);
        promoTotal = Math.abs(promoTotal);
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));

        $('#promoPackItem' + id).remove();
        promoPackageID.prop('checked', false);
        promoPackIDQty.attr('disabled', true);
        promoPackIDQty.val(1);

    }

    $('#promoPackChip' + id).click(function () {
        promoChk = promoChk - 1;
        console.log(promoChk);
        if (promoChk < 1) {
            $('#createPromoSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (promoChk > 0) {
            $('#createPromoSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        var promoPackChipQty = $('#promoPackageQty' + id),
            $promoPUtr = promoPackageID.closest('tr'),
            promoPackUnChipPrice = $promoPUtr.find('td:eq(3)').text(),
            $promoPackUnchipPrice = parseFloat(promoPackUnChipPrice.replace(/[^\d.]/g, '')).toFixed(2);
        $promoQty = parseFloat($promoPUtr.find('td #promoPackageQty' + id).val()).toFixed(2);
        promoTotal = promoTotal - ($promoQty * $promoPackUnchipPrice);
        promoTotal = Math.abs(promoTotal);
        $('#crPromoTotal').val("Php " + parseFloat(promoTotal).toFixed(2));
        $('#crPromoPrice').val("Php " + parseFloat(promoTotal).toFixed(2));

        $('#promoPackItem' + id).remove();
        promoPackageID.prop('checked', false);
        promoPackChipQty.attr('disabled', true);
        promoPackChipQty.val(1);
    });
}

function createPromo() {
    // var job = document.querySelectorAll('select[name=intPackageType]:selected');
    var promoProdSelect = [],
        promoServSelect = [],
        promoPackageSelect = [];

    $.each($("input[name=prodCrPromoSelect]:checked"), function () {
        promoProdSelect.push($(this).val());
    });
    $.each($("input[name=servCrPromoSelect]:checked"), function () {
        promoServSelect.push($(this).val());
    });
    $.each($("input[name=packCrPromoSelect]:checked"), function () {
        promoPackageSelect.push($(this).val());
    });

    var promoProductQty = $('input[name=crProdPromoQty]:enabled').map(function () {
        return this.value;
    }).get(); //get all the quantity enabled in product
    var promoServiceQty = $('input[name=crServPromoQty]:enabled').map(function () {
        return this.value;
    }).get(); //get all the quantity enabled in service
    var promoPackQty = $('input[name=crPackPromoQty]:enabled').map(function () {
        return this.value;
    }).get();

    promoProdSelect = promoProdSelect.join(', ');
    promoServSelect = promoServSelect.join(', ');
    promoPackageSelect = promoPackageSelect.join(', ');
    promoProductQty = promoProductQty.join(', ');
    promoServiceQty = promoServiceQty.join(', ');
    promoPackQty = promoPackQty.join(', ');

    var promodata = {
        "strPromoName": $('#crPromoName').val(),
        "strPromoDesc": $('#crPromoDescription').val(),
        "strPromoGuidelines": $('#crPromoGuidelines').val(),
        "strNonExp": $("#crPromoNonExpiry:checked").val(),
        "strExp": $('#crPromoExpiration').val(),
        "servicePromoSelect": promoServSelect,
        "productPromoSelect": promoProdSelect,
        "packagePromoSelect": promoPackageSelect,
        "servicePromoQty": promoServiceQty,
        "productPromoQty": promoProductQty,
        "packagePromoQty": promoPackQty,
        "strFree": $("input[name=crFree]:checked").val(),
        "dblPromoPrice": $('#crPromoPrice').val().replace(/[^\d.]/g, '')
    };

    swal({
            title: "Create this promo?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'createPromo',
                    type: 'post',
                    data: promodata,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        swal("Successfully created!", ".", "success");
                        updatePromoTable();
                        $('#crPromoModal').closeModal();
                    },
                    error: function () {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}

function deactivatePromo(id, deactivateID) {
    var promodata = {
        "intPromoID": id
    },
        $tr = $('#' + deactivateID).closest('tr'),
        promoname = $tr.find('td:eq(0)').text();

    swal({
            title: "Are you sure you want to deactivate " + promoname + "?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    url: 'deactivatePromo',
                    type: 'post',
                    data: promodata,
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        swal("Successfully deactivated!", ".", "success");
                        $tr.find('td').fadeOut(300, function () {
                            $tr.remove();
                        });
                        updatePromoTable();
                    },
                    error: function (data) {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });
            }, 1000);
        });
}