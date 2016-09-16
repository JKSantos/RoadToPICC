/**
 * Created by Castillo on 9/8/2016.
 */


$('#btnCrDiscountExit').click(function () {
    $('#createDiscountForm').trigger("reset");
    $('.discounterrorcontainer').hide();
});

$('#createDiscountSubmitForm2').hide();

$('#addDiscountBtn').on('click', function() {
    $('#createDiscountSubmitForm2').hide();
    $('#createDiscountForm').trigger("reset");
    $('#createRequirementForm').trigger("reset");
});

$('input[name=strApplicability]').on('click', function() {
    if($(this).val() == 'TOTAL SALES') {
        $("#createDiscountForm .determinate")
            .css("width","100%")
            .html("100%").css("font-size", "15px");
        $('#createDiscountSubmitForm2').show();
        $('#discnextbtn').hide();
    } else if ($(this).val() == 'SELECTED ITEMS') {
        $("#createDiscountForm .determinate")
            .css("width","50%")
            .html("50%").css("font-size", "15px");
        $('#createDiscountSubmitForm2').hide();
        $('#discnextbtn').show();
    }
});

window.onload = updateDiscountTable();

function updateDiscountTable() {
    $.ajax({
        type: 'post',
        url: 'getAllDiscountNoDetails',
        dataType: 'json',
        async: true,
        success: function (data) {
            var discountList = data.discountList,
                tablediscount;

            $('#discounttbl').DataTable().destroy();

            tablediscount = $('#discounttbl').DataTable({
                destroy: true,
                "bLengthChange": false,
                responsive: true,
                "order": [],
                "columnDefs": [
                    {"targets": 'no-sort', "orderable": false},
                    {className: "dt-body-left", "targets": [0, 1, 2, 3, 4]},
                    {className: "dt-body-right", "targets": [5]},
                    {className: "dt-head-center", "targets": [6]},
                    {"targets": [1, 2], render: $.fn.dataTable.render.ellipsis(25)}
                ],
                "rowHeight": '10px'
            });

            $("#discountSearch").bind('keyup search input paste cut', function () {
                tablediscount.search(this.value).draw();
            });

            if (discountList != null) {
                tablediscount.clear().draw();
                $.each(discountList, function (i, discount) {
                    var price,
                        guidelines,
                        description,
                        type;
                    var addbtn = "<button class=' waves-effect waves-purple btn-flat transparent black-text'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + discount.intDiscountID + "'" +
                        " onclick='openUpdateDiscount(this.value)'>" +
                        "<i class='material-icons'>edit</i></button>" +
                        "<button id='deactivateID" + discount.intDiscountID + "' class='waves-effect waves-purple btn-flat transparent red-text text-accent-4'" +
                        " style='padding-left: 10px;padding-right:10px; margin: 5px;' value='" + discount.intDiscountID + "' title='Deactivate'" +
                        " onclick='deactivateDiscount(this.value, this.id)'>" +
                        "<i class='material-icons'>delete</i></button>";

                    if (discount.intDiscountType == 1) { //Percentage
                        price = discount.dblDiscountAmount + '%';
                        type = 'Percentage';
                    } else if (discount.intDiscountType == 2) { //Fixed
                        price = 'P' + parseFloat(discount.dblDiscountAmount).toFixed(2);
                        price = addCommas(price);
                        type = 'Fixed Amount'
                    }

                    if (discount.strDiscountGuidelines == null || discount.strDiscountGuidelines == '') {
                        guidelines = 'None';
                    } else {
                        guidelines = discount.strDiscountGuidelines;
                    }

                    if (discount.strDiscountDesc == null || discount.strDiscountDesc == '') {
                        description = 'None';
                    } else {
                        description = discount.strDiscountDesc;
                    }

                    tablediscount.row.add([
                        discount.strDiscountName,
                        description,
                        guidelines,
                        discount.applicability,
                        type,
                        price,
                        addbtn
                    ]);
                });
                tablediscount.draw();
            }


        }
    });
}

$.ajax({
    type: 'post',
    url: 'api/v1/getAllProduct',
    dataType: 'json',
    async: true,
    success: function (data) {
        var productList = data.productList,
            createDiscountProdTable = $('#crdiscounttblProduct').DataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                responsive: true,
                "order": [],
                "columnDefs": [
                    {"targets": 'no-sort', "orderable": false},
                    {className: "dt-body-left", "targets": [1, 2]},
                    {className: "dt-body-center", "targets": [0]},
                    {className: "dt-head-right", "targets": [3]}
                ],
                "rowHeight": '10px'
            });

        $("#crDiscountSearch").bind('keyup search input paste cut', function () {
            createDiscountProdTable.search(this.value).draw();
        });

        if (productList != null) {
            createDiscountProdTable.clear().draw();
            $.each(productList, function (i, product) {
                var price = parseFloat(product.dblProductPrice).toFixed(2);
                price = addCommas(price);
                var checkbox = "<input type='checkbox' name='createDiscountProduct' id='discProdCheck" + product.intProductID + "' required" +
                    " class='packcheckbox x" + product.intProductID + "' value='" + product.intProductID + "' onclick='computeProductDiscount(this.value)'>" +
                    "<label for='discProdCheck" + product.intProductID + "'></label>";
                price = "<span class='price'>P " + price + "</span>";

                createDiscountProdTable.row.add([
                    checkbox,
                    product.strProductName,
                    product.strProductCategory,
                    price
                ]);
            });
            createDiscountProdTable.draw();
        }
    }
});

$.ajax({
    type: 'post',
    url: 'api/v1/getAllService',
    dataType: 'json',
    async: true,
    success: function (data) {
        var serviceList = data.serviceList,
            createDiscountServTable = $('#crdiscounttblService').DataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                responsive: true,
                "order": [],
                "columnDefs": [
                    {"targets": 'no-sort', "orderable": false},
                    {className: "dt-body-left", "targets": [1, 2]},
                    {className: "dt-body-center", "targets": [0]},
                    {className: "dt-head-right", "targets": [3]}
                ],
                "rowHeight": '10px'
            });

        $("#crDiscountSearch").bind('keyup search input paste cut', function () {
            createDiscountServTable.search(this.value).draw();
        });

        if (serviceList != null) {
            createDiscountServTable.clear().draw();
            $.each(serviceList, function (i, service) {
                var price = parseFloat(service.dblServicePrice).toFixed(2);
                price = addCommas(price);
                var checkbox = "<input type='checkbox' name='createDiscountService' id='discServCheck" + service.intServiceID + "' required" +
                    " class='packcheckbox x" + service.intServiceID + "' value='" + service.intServiceID + "' onclick='computeServiceDiscount(this.value)'>" +
                    "<label for='discServCheck" + service.intServiceID + "'></label>";
                price = "<span class='price'>P " + price + "</span>";

                createDiscountServTable.row.add([
                    checkbox,
                    service.strServiceName,
                    service.strServiceCategory,
                    price
                ]);
            });
            createDiscountServTable.draw();
        }
    }
});

$.ajax({
    type: 'post',
    url: 'api/v1/getAllPackage',
    dataType: 'json',
    async: true,
    success: function (data) {
        var packageList = data.packageList,
            createDiscountPackageTbl = $('#crdiscounttblPackage').DataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                responsive: true,
                "order": [],
                "columnDefs": [
                    {"targets": 'no-sort', "orderable": false},
                    {className: "dt-body-left", "targets": [1, 2]},
                    {className: "dt-body-center", "targets": [0]},
                    {className: "dt-head-right", "targets": [3]}
                ],
                "rowHeight": '10px'
            });

        $("#crDiscountSearch").bind('keyup search input paste cut', function () {
            createDiscountPackageTbl.search(this.value).draw();
        });

        if (packageList != null) {
            createDiscountPackageTbl.clear().draw();
            $.each(packageList, function (i, packagedata) {
                var cat = 'Package';
                var price = parseFloat(packagedata.dblPackagePrice).toFixed(2);
                price = addCommas(price);
                var checkbox = "<input type='checkbox' name='createDiscountPackage' id='discPackCheck" + packagedata.intPackageID + "'" +
                    " class='packcheckbox x" + packagedata.intPackageID + "' value='" + packagedata.intPackageID + "' onclick='computePackageDiscount(this.value)'>" +
                    "<label for='discPackCheck" + packagedata.intPackageID + "'></label>";
                price = "<td class='dt-body-right'>P " + price + "</td>";

                createDiscountPackageTbl.row.add([
                    checkbox,
                    packagedata.strPackageName,
                    cat,
                    price
                ]);
            });
            createDiscountPackageTbl.draw();
        }
    }
});

$.ajax({
    type: 'post',
    url: 'getAllPromoNoDetails',
    dataType: 'json',
    async: true,
    success: function (data) {
        var promoList = data.promoList,
            createDiscountPromoTbl = $('#crdiscounttblPromo').DataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                responsive: true,
                "order": [],
                "columnDefs": [
                    {"targets": 'no-sort', "orderable": false},
                    {className: "dt-body-left", "targets": [1, 2]},
                    {className: "dt-body-center", "targets": [0]},
                    {className: "dt-head-right", "targets": [3]}
                ],
                "rowHeight": '10px'
            });

        $("#crDiscountSearch").bind('keyup search input paste cut', function () {
            createDiscountPromoTbl.search(this.value).draw();
        });

        if (promoList != null) {
            createDiscountPromoTbl.clear().draw();
            $.each(promoList, function (i, promodata) {
                var cat = 'Promo';
                if (promodata.dblPromoPrice != 0.0) {
                    var price = parseFloat(promodata.dblPromoPrice).toFixed(2);
                    price = addCommas(price);
                    var checkbox = "<input type='checkbox' name='createDiscountPromo' id='discPromoCheck" + promodata.intPromoID + "'" +
                        " class='packcheckbox x" + promodata.intPromoID + "' value='" + promodata.intPromoID + "' onclick='computePromoDiscount(this.value)'>" +
                        "<label for='discPromoCheck" + promodata.intPromoID + "'></label>";
                    price = "<td class='dt-body-right'>P " + price + "</td>";

                    createDiscountPromoTbl.row.add([
                        checkbox,
                        promodata.strPromoName,
                        cat,
                        price
                    ]);
                }
            });
            createDiscountPromoTbl.draw();
        }
    }
});


var chk = 0;

function computeProductDiscount(id) {
    var productID = $('#discProdCheck' + id);
    if (productID.is(':checked')) {
        var $prodTR = productID.closest('tr'), // PRODUCT TR
            prodPrice = $prodTR.find('td:eq(3)').text(),
            $productPrice = parseFloat(prodPrice.replace(/[^\d.]/g, '')).toFixed(2); // PRODUCT PRICE (CONVERTED ALREADY)
        var prodName = $prodTR.find('td:eq(1)').text(),
            discountedPrice,
            discountType,
            crDiscountAmountPercent = $('#crDiscountAmountPercent').val(),
            crDiscountAmountFixed = parseFloat($('#crDiscountAmountFixed').val().replace(/[^\d.]/g, '')).toFixed(2),
            crDiscountAmtType = $('#crDiscountAmtType').val();

        chk = chk + 1;
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        if (crDiscountAmtType == 1) { //percentage
            discountedPrice = parseFloat($productPrice - ( ($productPrice * crDiscountAmountPercent) / 100)).toFixed(2);
            discountType = crDiscountAmountPercent + '%';
        } else if (crDiscountAmtType == 2) {
            discountedPrice = parseFloat($productPrice - crDiscountAmountFixed).toFixed(2);
            discountType = 'P' + crDiscountAmountFixed;
        }

        if (discountedPrice > 0) {
            $('#discountList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discProdItem' + id + '"><b>' + prodName + '</b>' +
                ' P' + $productPrice + ' - (Discount: ' + discountType + ') = (Total: P' + discountedPrice + ')' +
                '<i id="discProdChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        } else {
            $('#discountList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discProdItem' + id + '"><b>' + prodName + '</b>' +
                ' P' + $productPrice + ' - (Discount: ' + discountType + ') = P0.00' +
                '<i id="discProdChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        }

    } else if (!productID.is(':checked')) {
        chk = chk - 1;
        console.log(chk);
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#discProdItem' + id).remove();
        productID.prop('checked', false);
    }

    $('#discProdChip' + id).click(function () {
        chk = chk - 1;
        console.log(chk);
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#discProdItem' + id).remove();
        $('#discProdCheck' + id).prop('checked', false);

    });

}

function computeServiceDiscount(id) {
    var serviceID = $('#discServCheck' + id);
    if (serviceID.is(':checked')) {
        var $servTR = serviceID.closest('tr'), // PRODUCT TR
            servPrice = $servTR.find('td:eq(3)').text(),
            $servicePrice = parseFloat(servPrice.replace(/[^\d.]/g, '')).toFixed(2); // PRODUCT PRICE (CONVERTED ALREADY)
        var servName = $servTR.find('td:eq(1)').text(),
            discountedPrice,
            discountType,
            crDiscountAmountPercent = $('#crDiscountAmountPercent').val(),
            crDiscountAmountFixed = parseFloat($('#crDiscountAmountFixed').val().replace(/[^\d.]/g, '')).toFixed(2),
            crDiscountAmtType = $('#crDiscountAmtType').val();

        chk = chk + 1;
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        if (crDiscountAmtType == 1) { //percentage
            discountedPrice = parseFloat($servicePrice - ( ($servicePrice * crDiscountAmountPercent) / 100)).toFixed(2);
            discountType = crDiscountAmountPercent + '%';
        } else if (crDiscountAmtType == 2) {
            discountedPrice = parseFloat($servicePrice - crDiscountAmountFixed).toFixed(2);
            discountType = 'P' + crDiscountAmountFixed;
        }

        if (discountedPrice > 0) {
            $('#discountList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discServItem' + id + '"><b>' + servName + '</b>' +
                ' P' + $servicePrice + ' - (Discount: ' + discountType + ') = (Total: P' + discountedPrice + ')' +
                '<i id="discServChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        } else {
            $('#discountList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discServItem' + id + '"><b>' + servName + '</b>' +
                ' P' + $servicePrice + ' - (Discount: ' + discountType + ') = P0.00' +
                '<i id="discServChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        }

    } else if (!serviceID.is(':checked')) {
        chk = chk - 1;
        console.log(chk);
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#discServItem' + id).remove();
        serviceID.prop('checked', false);
    }

    $('#discServChip' + id).click(function () {
        chk = chk - 1;
        console.log(chk);
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#discServItem' + id).remove();
        $('#discServCheck' + id).prop('checked', false);

    });

}

function computePackageDiscount(id) {
    var packageID = $('#discPackCheck' + id);
    if (packageID.is(':checked')) {
        var $packTR = packageID.closest('tr'), // PRODUCT TR
            packPrice = $packTR.find('td:eq(3)').text(),
            $packagePrice = parseFloat(packPrice.replace(/[^\d.]/g, '')).toFixed(2); // PRODUCT PRICE (CONVERTED ALREADY)
        var packName = $packTR.find('td:eq(1)').text(),
            discountedPrice,
            discountType,
            crDiscountAmountPercent = $('#crDiscountAmountPercent').val(),
            crDiscountAmountFixed = parseFloat($('#crDiscountAmountFixed').val().replace(/[^\d.]/g, '')).toFixed(2),
            crDiscountAmtType = $('#crDiscountAmtType').val();

        chk = chk + 1;
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        if (crDiscountAmtType == 1) { //percentage
            discountedPrice = parseFloat($packagePrice - ( ($packagePrice * crDiscountAmountPercent) / 100)).toFixed(2);
            discountType = crDiscountAmountPercent + '%';
        } else if (crDiscountAmtType == 2) {
            discountedPrice = parseFloat($packagePrice - crDiscountAmountFixed).toFixed(2);
            discountType = 'P' + crDiscountAmountFixed;
        }

        if (discountedPrice > 0) {
            $('#discountList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discPackItem' + id + '"><b>' + packName + '</b>' +
                ' P' + $packagePrice + ' - (Discount: ' + discountType + ') = (Total: P' + discountedPrice + ')' +
                '<i id="discPackChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        } else {
            $('#discountList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discPackItem' + id + '"><b>' + packName + '</b>' +
                ' P' + $packagePrice + ' - (Discount: ' + discountType + ') = P0.00' +
                '<i id="discPackChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        }

    } else if (!packageID.is(':checked')) {
        chk = chk - 1;
        console.log(chk);
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#discPackItem' + id).remove();
        packageID.prop('checked', false);
    }

    $('#discPackChip' + id).click(function () {
        chk = chk - 1;
        console.log(chk);
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#discPackItem' + id).remove();
        $('#discPackCheck' + id).prop('checked', false);

    });

}

function computePromoDiscount(id) {
    var promoID = $('#discPromoCheck' + id);
    if (promoID.is(':checked')) {
        var $promoTR = promoID.closest('tr'), // PRODUCT TR
            promoPrice = $promoTR.find('td:eq(3)').text(),
            $promoPrice = parseFloat(promoPrice.replace(/[^\d.]/g, '')).toFixed(2); // PRODUCT PRICE (CONVERTED ALREADY)
        var promoName = $promoTR.find('td:eq(1)').text(),
            discountedPrice,
            discountType,
            crDiscountAmountPercent = $('#crDiscountAmountPercent').val(),
            crDiscountAmountFixed = parseFloat($('#crDiscountAmountFixed').val().replace(/[^\d.]/g, '')).toFixed(2),
            crDiscountAmtType = $('#crDiscountAmtType').val();

        chk = chk + 1;
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        if (crDiscountAmtType == 1) { //percentage
            discountedPrice = parseFloat($promoPrice - ( ($promoPrice * crDiscountAmountPercent) / 100)).toFixed(2);
            discountType = crDiscountAmountPercent + '%';
        } else if (crDiscountAmtType == 2) {
            discountedPrice = parseFloat($promoPrice - crDiscountAmountFixed).toFixed(2);
            discountType = 'P' + crDiscountAmountFixed;
        }

        if (discountedPrice > 0) {
            $('#discountList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discPromoItem' + id + '"><b>' + promoName + '</b>' +
                ' P' + $promoPrice + ' - (Discount: ' + discountType + ') = (Total: P' + discountedPrice + ')' +
                '<i id="discPromoChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        } else {
            $('#discountList').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discPromoItem' + id + '"><b>' + promoName + '</b>' +
                ' P' + $promoPrice + ' - (Discount: ' + discountType + ') = P0.00' +
                '<i id="discPromoChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        }

    } else if (!promoID.is(':checked')) {
        chk = chk - 1;
        console.log(chk);
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#discPromoItem' + id).remove();
        promoID.prop('checked', false);
    }

    $('#discPromoChip' + id).click(function () {
        chk = chk - 1;
        console.log(chk);
        if (chk < 1) {
            $('#createDiscountSubmitForm').attr('disabled', true).css('opacity', '0.3');
        } else if (chk > 0) {
            $('#createDiscountSubmitForm').attr('disabled', false).css('opacity', '1');
        }

        $('#discPromoItem' + id).remove();
        $('#discPromoCheck' + id).prop('checked', false);

    });

}