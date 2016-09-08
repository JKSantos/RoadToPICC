/**
 * Created by Castillo on 9/9/2016.
 */

$('#discountUpdateFilter').change(function () {
    var sel = $(this).val();
    if (sel == 'product') {

        $('#updiscounttblProduct').parents('div.tablewrapper').first().show();
        $('#updiscounttblService').parents('div.tablewrapper').first().hide();
        $('#updiscounttblPackage').parents('div.tablewrapper').first().hide();
        $('#updiscounttblPromo').parents('div.tablewrapper').first().hide();
    } else if (sel == 'service') {

        $('#updiscounttblProduct').parents('div.tablewrapper').first().hide();
        $('#updiscounttblService').parents('div.tablewrapper').first().show();
        $('#updiscounttblPackage').parents('div.tablewrapper').first().hide();
        $('#updiscounttblPromo').parents('div.tablewrapper').first().hide();
    } else if (sel == 'package') {
        $('#updiscounttblProduct').parents('div.tablewrapper').first().hide();
        $('#updiscounttblService').parents('div.tablewrapper').first().hide();
        $('#updiscounttblPackage').parents('div.tablewrapper').first().show();
        $('#updiscounttblPromo').parents('div.tablewrapper').first().hide();
    } else if (sel == 'promo') {
        $('#updiscounttblProduct').parents('div.tablewrapper').first().hide();
        $('#updiscounttblService').parents('div.tablewrapper').first().hide();
        $('#updiscounttblPackage').parents('div.tablewrapper').first().hide();
        $('#updiscounttblPromo').parents('div.tablewrapper').first().show();
    }
});

$('input[name=strUpdateApplicability]').click(function () {
    var appli = $(this).val();
    if (appli == 'ALL CUSTOMER') {
        $('#updateDiscountGuidelinesDiv').hide();
        $('#upDiscountAmtTypeDiv').css('margin-top', '-50px');
        $('#upPercent').css('margin-top', '-50px');
        $('#upFixed').css('margin-top', '-50px');
    } else if (appli == 'DEPENDING ON THE GUIDELINES') {
        $('#updateDiscountGuidelinesDiv').show();
        $('#upDiscountAmtTypeDiv').css('margin-top', '10px');
        $('#upPercent').css('margin-top', '10px');
        $('#upFixed').css('margin-top', '10px');
    }
});

$('#upDiscountAmtType').change(function () {
    var type = $(this).val();
    if (type == 1) {
        $('#upPercent').show();
        $('#upFixed').hide();
    } else {
        $('#upPercent').hide();
        $('#upFixed').show();
    }
});

$('input[name=strUpdateApplicability]').click(function () {
    if ($(this).val() == 'DEPENDING ON THE GUIDELINES' && $('#upDiscountAmtType').val() == 1) {
        $('#upDiscountGuidelines, #upDiscountAmountPercent').on('input', function () {
            if ($('#upDiscountGuidelines').val() == '' || $('#upDiscountAmountPercent').val() == '') {
                $('#updateDiscountSubmitBtn').prop('disabled', true)
                    .css('opacity', '0.3');
            } else {
                $('#updateDiscountSubmitBtn').prop('disabled', false)
                    .css('opacity', '1');
            }
        });
    } else if ($(this).val() == 'DEPENDING ON THE GUIDELINES' && $('#upDiscountAmtType').val() == 2) {
        $('#upDiscountAmountFixed, #upDiscountGuidelines').on('input', function () {
            if ($('#upDiscountGuidelines').val() == '' || $('#upDiscountAmountFixed').val() == '' || $('#upDiscountAmountFixed').val() == 'Php 0.00') {
                $('#updateDiscountSubmitBtn').prop('disabled', true)
                    .css('opacity', '0.3');
            } else {
                $('#updateDiscountSubmitBtn').prop('disabled', false)
                    .css('opacity', '1');
            }
        });
    } else if ($(this).val() == 'ALL CUSTOMER' && $('#upDiscountAmtType').val() == 1) {
        $('#upDiscountAmountPercent').on('input', function () {
            if ($('#upDiscountAmountPercent').val() == '') {
                $('#updateDiscountSubmitBtn').prop('disabled', true)
                    .css('opacity', '0.3');
            } else {
                $('#updateDiscountSubmitBtn').prop('disabled', false)
                    .css('opacity', '1');
            }
        });
    } else if ($(this).val() == 'ALL CUSTOMER' && $('#upDiscountAmtType').val() == 2) {
        $('#upDiscountAmountFixed').on('input', function () {
            if ($('#upDiscountAmountFixed').val() == '' || $('#upDiscountAmountFixed').val() == 'Php 0.00') {
                $('#upDiscountAmountPercent').prop('disabled', true)
                    .css('opacity', '0.3');
            } else {
                $('#upDiscountAmountPercent').prop('disabled', false)
                    .css('opacity', '1');
            }
        });
    }
});


var chkupdate = 0;
function openUpdateDiscount(id) {
    $('#discountUpdateFilter option[value=product]').prop('selected', true);
    $('#updiscounttblProduct').parents('div.tablewrapper').first().show();
    $('#updiscounttblService').parents('div.tablewrapper').first().hide();
    $('#updiscounttblPackage').parents('div.tablewrapper').first().hide();
    $('#updiscounttblPromo').parents('div.tablewrapper').first().hide();

    $('#updateDiscountModal').openModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 300, // Transition out duration
    });
    $('ul.tabs').tabs('select_tab', 'updateA');

    var discountData = {
        intDiscountID: id
    };

    $.ajax({
        type: 'post',
        url: 'getDiscountByID',
        data: discountData,
        dataType: 'json',
        async: true,
        success: function (data) {
            var discount = data.discount;
            $('.updateDiscountChip').remove();
            $('.updateDiscountCheckbox').prop('checked', false);
            console.log(discount);
            $('#upDiscountID').val(discount.intDiscountID);
            $('#upDiscountName').val(discount.strDiscountName);
            $('#upDiscountDetails').val(discount.strDiscountDesc);

            if (discount.applicability == 'ALL CUSTOMER') {
                $('#upDiscountRadio1').prop('checked', true);
                $('#upDiscountRadio2').prop('checked', false);

                $('#updateDiscountGuidelinesDiv').hide();
                $('#upDiscountAmtTypeDiv').css('margin-top', '-50px');
                $('#upPercent').css('margin-top', '-50px');
                $('#upFixed').css('margin-top', '-50px');

            } else {
                $('#upDiscountRadio1').prop('checked', false);
                $('#upDiscountRadio2').prop('checked', true);

                $('#updateDiscountGuidelinesDiv').show();
                $('#upDiscountGuidelines').val(discount.strDiscountGuidelines);
                $('#upDiscountAmtTypeDiv').css('margin-top', '10px');
                $('#upPercent').css('margin-top', '10px');
                $('#upFixed').css('margin-top', '10px');
            }

            if (discount.intDiscountType == 1) { // percentage
                $('#upPercent').show();
                $('#upFixed').hide();

                $('select').material_select('destroy');
                $('#upDiscountAmtType option[value=1]').prop('selected', true);
                $('select').material_select();

                $('#upDiscountAmountPercent').val(discount.dblDiscountAmount);

            } else if (discount.intDiscountType == 2) { //fixed amt
                $('#upPercent').hide();
                $('#upFixed').show();

                $('select').material_select('destroy');
                $('#upDiscountAmtType option[value=2]').prop('selected', true);
                $('select').material_select();

                $('#upDiscountAmountFixed').val('Php ' + parseFloat(discount.dblDiscountAmount).toFixed(2));
            }

            $.each(discount.productList, function (i, product) {
                var upProdCheck = $('#discUpProdCheck' + product.intProductID),
                    discnt, typeamt;
               if(product.intProductID == upProdCheck.val()){
                   chkupdate = chkupdate + 1;
                   upProdCheck.prop('checked', true);
                   if (discount.intDiscountType == 1) { //percentage
                       discnt = parseFloat(product.dblProductPrice - ( (product.dblProductPrice * discount.dblDiscountAmount) / 100)).toFixed(2);
                       typeamt = discount.dblDiscountAmount + '%';
                   } else if (discount.intDiscountType == 2) {
                       discnt = parseFloat(product.dblProductPrice - discount.dblDiscountAmount).toFixed(2);
                       typeamt = 'P' + parseFloat(discount.dblDiscountAmount).toFixed(2);
                   }

                   $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountChip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                       ' id="discUpProdItem' + product.intProductID + '"><b>' + product.strProductName + '</b>' +
                       ' P' + parseFloat(product.dblProductPrice).toFixed(2) + ' - (Discount: ' + typeamt + ') = (Total: P' + discnt + ')' +
                       '<i id="discUpProdChip' + product.intProductID + '" class="material-icons" style="margin-right: 5px' +
                       '!important">close</i></div>').show();
               }
            });

            $.each(discount.serviceList, function (i, service) {
                var upServCheck = $('#discUpServCheck' + service.intServiceID),
                    discntService, typeamtService;
                if(service.intServiceID == upServCheck.val()){
                    chkupdate = chkupdate + 1;
                    upServCheck.prop('checked', true);
                    if (discount.intDiscountType == 1) { //percentage
                        discntService = parseFloat(service.dblServicePrice - ( (service.dblServicePrice * discount.dblDiscountAmount) / 100)).toFixed(2);
                        typeamtService = discount.dblDiscountAmount + '%';
                    } else if (discount.intDiscountType == 2) {
                        discntService = parseFloat(service.dblServicePrice - discount.dblDiscountAmount).toFixed(2);
                        typeamtService = 'P' + parseFloat(discount.dblDiscountAmount).toFixed(2);
                    }

                    $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountChip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                        ' id="discUpServItem' + service.intServiceID + '"><b>' + service.strServiceName + '</b>' +
                        ' P' + parseFloat(service.dblServicePrice).toFixed(2) + ' - (Discount: ' + typeamtService + ') = (Total: P' + discntService + ')' +
                        '<i id="discUpServChip' + service.intServiceID + '" class="material-icons" style="margin-right: 5px' +
                        '!important">close</i></div>').show();
                }
            });

            $.each(discount.packageList, function (i, package) {
                var upPackCheck = $('#discUpPackCheck' + package.intPackageID),
                    discntPackage, typeamtPackage;
                if(package.intPackageID == upPackCheck.val()){
                    upPackCheck.prop('checked', true);
                    chkupdate = chkupdate + 1;
                    if (discount.intDiscountType == 1) { //percentage
                        discntPackage = parseFloat(package.dblPackagePrice - ( (package.dblPackagePrice * discount.dblDiscountAmount) / 100)).toFixed(2);
                        typeamtPackage = discount.dblDiscountAmount + '%';
                    } else if (discount.intDiscountType == 2) {
                        discntPackage = parseFloat(package.dblPackagePrice - discount.dblDiscountAmount).toFixed(2);
                        typeamtPackage = 'P' + parseFloat(discount.dblDiscountAmount).toFixed(2);
                    }

                    $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountChip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                        ' id="discUpPackItem' + package.intPackageID + '"><b>' + package.strPackageName + '</b>' +
                        ' P' + parseFloat(package.dblPackagePrice).toFixed(2) + ' - (Discount: ' + typeamtPackage + ') = (Total: P' + discntPackage + ')' +
                        '<i id="discUpPackChip' + package.intPackageID + '" class="material-icons" style="margin-right: 5px' +
                        '!important">close</i></div>').show();
                }
            });

            $.each(discount.promoList, function (i, promo) {
                var upPromoCheck = $('#discUpPromoCheck' + promo.intPromoID),
                    discntPromo, typeamtPromo;
                if(promo.intPromoID == upPromoCheck.val()){
                    upPromoCheck.prop('checked', true);
                    chkupdate = chkupdate + 1;
                    if (discount.intDiscountType == 1) { //percentage
                        discntPromo = parseFloat(promo.dblPromoPrice - ( (promo.dblPromoPrice * discount.dblDiscountAmount) / 100)).toFixed(2);
                        typeamtPromo = discount.dblDiscountAmount + '%';
                    } else if (discount.intDiscountType == 2) {
                        discntPromo = parseFloat(promo.dblPromoPrice - discount.dblDiscountAmount).toFixed(2);
                        typeamtPromo = 'P' + parseFloat(discount.dblDiscountAmount).toFixed(2);
                    }

                    $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountChip z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                        ' id="discUpPromoItem' + promo.intPromoID + '"><b>' + promo.strPromoName + '</b>' +
                        ' P' + parseFloat(promo.dblPromoPrice).toFixed(2) + ' - (Discount: ' + typeamtPromo + ') = (Total: P' + discntPromo + ')' +
                        '<i id="discUpPromoChip' + promo.intPromoID + '" class="material-icons" style="margin-right: 5px' +
                        '!important">close</i></div>').show();
                }
            });

        },
        error: function (data) {
            console.log(data);
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
            updateDiscountProdTable = $('#updiscounttblProduct').DataTable({
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

        $("#upDiscountSearch").bind('keyup search input paste cut', function () {
            updateDiscountProdTable.search(this.value).draw();
        });

        if (productList != null) {
            updateDiscountProdTable.clear().draw();
            $.each(productList, function (i, product) {
                var price = parseFloat(product.dblProductPrice).toFixed(2);
                price = addCommas(price);
                var checkbox = "<input type='checkbox' name='updateDiscountProduct' id='discUpProdCheck" + product.intProductID + "' required" +
                    " class='updateDiscountCheckbox x" + product.intProductID + "' value='" + product.intProductID + "' onclick='updateProductDiscount(this.value)'>" +
                    "<label for='discUpProdCheck" + product.intProductID + "'></label>";
                price = "<span class='price'>P " + price + "</span>";

                updateDiscountProdTable.row.add([
                    checkbox,
                    product.strProductName,
                    product.strProductCategory,
                    price
                ]);
            });
            updateDiscountProdTable.draw();
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
            updateDiscountServTable = $('#updiscounttblService').DataTable({
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

        $("#upDiscountSearch").bind('keyup search input paste cut', function () {
            updateDiscountServTable.search(this.value).draw();
        });

        if (serviceList != null) {
            updateDiscountServTable.clear().draw();
            $.each(serviceList, function (i, service) {
                var price = parseFloat(service.dblServicePrice).toFixed(2);
                price = addCommas(price);
                var checkbox = "<input type='checkbox' name='updateDiscountService' id='discUpServCheck" + service.intServiceID + "' required" +
                    " class='updateDiscountCheckbox x" + service.intServiceID + "' value='" + service.intServiceID + "' onclick='updateServiceDiscount(this.value)'>" +
                    "<label for='discUpServCheck" + service.intServiceID + "'></label>";
                price = "<span class='price'>P " + price + "</span>";

                updateDiscountServTable.row.add([
                    checkbox,
                    service.strServiceName,
                    service.strServiceCategory,
                    price
                ]);
            });
            updateDiscountServTable.draw();
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
            updateDiscountPackageTbl = $('#updiscounttblPackage').DataTable({
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

        $("#upDiscountSearch").bind('keyup search input paste cut', function () {
            updateDiscountPackageTbl.search(this.value).draw();
        });

        if (packageList != null) {
            updateDiscountPackageTbl.clear().draw();
            $.each(packageList, function (i, packagedata) {
                var cat = 'Package';
                var price = parseFloat(packagedata.dblPackagePrice).toFixed(2);
                price = addCommas(price);
                var checkbox = "<input type='checkbox' name='updateDiscountPackage' id='discUpPackCheck" + packagedata.intPackageID + "'" +
                    " class='updateDiscountCheckbox x" + packagedata.intPackageID + "' value='" + packagedata.intPackageID + "' onclick='updatePackageDiscount(this.value)'>" +
                    "<label for='discUpPackCheck" + packagedata.intPackageID + "'></label>";
                price = "<td class='dt-body-right'>P " + price + "</td>";

                updateDiscountPackageTbl.row.add([
                    checkbox,
                    packagedata.strPackageName,
                    cat,
                    price
                ]);
            });
            updateDiscountPackageTbl.draw();
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
            updateDiscountPromoTbl = $('#updiscounttblPromo').DataTable({
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

        $("#upDiscountSearch").bind('keyup search input paste cut', function () {
            updateDiscountPromoTbl.search(this.value).draw();
        });

        if (promoList != null) {
            updateDiscountPromoTbl.clear().draw();
            $.each(promoList, function (i, promodata) {
                var cat = 'Promo';
                if (promodata.dblPromoPrice != 0.0) {
                    var price = parseFloat(promodata.dblPromoPrice).toFixed(2);
                    price = addCommas(price);
                    var checkbox = "<input type='checkbox' name='updateDiscountPromo' id='discUpPromoCheck" + promodata.intPromoID + "'" +
                        " class='updateDiscountCheckbox x" + promodata.intPromoID + "' value='" + promodata.intPromoID + "' onclick='updatePromoDiscount(this.value)'>" +
                        "<label for='discUpPromoCheck" + promodata.intPromoID + "'></label>";
                    price = "<td class='dt-body-right'>P " + price + "</td>";

                    updateDiscountPromoTbl.row.add([
                        checkbox,
                        promodata.strPromoName,
                        cat,
                        price
                    ]);
                }
            });
            updateDiscountPromoTbl.draw();
        }
    }
});


function updateProductDiscount(id) {
    var productID = $('#discUpProdCheck' + id);
    if (productID.is(':checked')) {
        var $prodTR = productID.closest('tr'), // PRODUCT TR
            prodPrice = $prodTR.find('td:eq(3)').text(),
            $productPrice = parseFloat(prodPrice.replace(/[^\d.]/g, '')).toFixed(2); // PRODUCT PRICE (CONVERTED ALREADY)
        var prodName = $prodTR.find('td:eq(1)').text(),
            discountedPrice,
            discountType,
            upDiscountAmountPercent = $('#upDiscountAmountPercent').val(),
            upDiscountAmountFixed = parseFloat($('#upDiscountAmountFixed').val().replace(/[^\d.]/g, '')).toFixed(2),
            upDiscountAmtType = $('#upDiscountAmtType').val();

        chkupdate = chkupdate + 1;
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        if (upDiscountAmtType == 1) { //percentage
            discountedPrice = parseFloat($productPrice - ( ($productPrice * upDiscountAmountPercent) / 100)).toFixed(2);
            discountType = upDiscountAmountPercent + '%';
        } else if (upDiscountAmtType == 2) {
            discountedPrice = parseFloat($productPrice - upDiscountAmountFixed).toFixed(2);
            discountType = 'P' + upDiscountAmountFixed;
        }

        if (discountedPrice > 0) {
            $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountCheckbox z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discUpProdItem' + id + '"><b>' + prodName + '</b>' +
                ' P' + $productPrice + ' - (Discount: ' + discountType + ') = (Total: P' + discountedPrice + ')' +
                '<i id="discUpProdChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        } else {
            $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountCheckbox z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discUpProdItem' + id + '"><b>' + prodName + '</b>' +
                ' P' + $productPrice + ' - (Discount: ' + discountType + ') = P0.00' +
                '<i id="discUpProdChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        }

    } else if (!productID.is(':checked')) {
        chkupdate = chkupdate - 1;
        console.log(chkupdate);
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#discUpProdItem' + id).remove();
        productID.prop('checked', false);
    }

    $('#discUpProdChip' + id).click(function () {
        chkupdate = chkupdate - 1;
        console.log(chkupdate);
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#discUpProdItem' + id).remove();
        $('#discUpProdCheck' + id).prop('checked', false);

    });
}

function updateServiceDiscount (id) {
    var serviceID = $('#discUpServCheck' + id);
    if (serviceID.is(':checked')) {
        var $servTR = serviceID.closest('tr'), // PRODUCT TR
            servPrice = $servTR.find('td:eq(3)').text(),
            $servicePrice = parseFloat(servPrice.replace(/[^\d.]/g, '')).toFixed(2); // PRODUCT PRICE (CONVERTED ALREADY)
        var servName = $servTR.find('td:eq(1)').text(),
            discountedPrice,
            discountType,
            upDiscountAmountPercent = $('#upDiscountAmountPercent').val(),
            upDiscountAmountFixed = parseFloat($('#upDiscountAmountFixed').val().replace(/[^\d.]/g, '')).toFixed(2),
            upDiscountAmtType = $('#upDiscountAmtType').val();

        chkupdate = chkupdate + 1;
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        if (upDiscountAmtType == 1) { //percentage
            discountedPrice = parseFloat($servicePrice - ( ($servicePrice * upDiscountAmountPercent) / 100)).toFixed(2);
            discountType = upDiscountAmountPercent + '%';
        } else if (upDiscountAmtType == 2) {
            discountedPrice = parseFloat($servicePrice - upDiscountAmountFixed).toFixed(2);
            discountType = 'P' + upDiscountAmountFixed;
        }

        if (discountedPrice > 0) {
            $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountCheckbox z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discUpServItem' + id + '"><b>' + servName + '</b>' +
                ' P' + $servicePrice + ' - (Discount: ' + discountType + ') = (Total: P' + discountedPrice + ')' +
                '<i id="discUpServChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        } else {
            $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountCheckbox z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discUpServItem' + id + '"><b>' + servName + '</b>' +
                ' P' + $servicePrice + ' - (Discount: ' + discountType + ') = P0.00' +
                '<i id="discUpServChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        }

    } else if (!serviceID.is(':checked')) {
        chkupdate = chkupdate - 1;
        console.log(chkupdate);
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#discUpServItem' + id).remove();
        serviceID.prop('checked', false);
    }

    $('#discUpServChip' + id).click(function () {
        chkupdate = chkupdate - 1;
        console.log(chkupdate);
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#discUpServItem' + id).remove();
        $('#discUpServCheck' + id).prop('checked', false);

    });
}

function updatePackageDiscount (id) {
    var packageID = $('#discUpPackCheck' + id);
    if (packageID.is(':checked')) {
        var $packTR = packageID.closest('tr'), // PRODUCT TR
            packPrice = $packTR.find('td:eq(3)').text(),
            $packagePrice = parseFloat(packPrice.replace(/[^\d.]/g, '')).toFixed(2); // PRODUCT PRICE (CONVERTED ALREADY)
        var packName = $packTR.find('td:eq(1)').text(),
            discountedPrice,
            discountType,
            upDiscountAmountPercent = $('#upDiscountAmountPercent').val(),
            upDiscountAmountFixed = parseFloat($('#upDiscountAmountFixed').val().replace(/[^\d.]/g, '')).toFixed(2),
            upDiscountAmtType = $('#upDiscountAmtType').val();

        chkupdate = chkupdate + 1;
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        if (upDiscountAmtType == 1) { //percentage
            discountedPrice = parseFloat($packagePrice - ( ($packagePrice * upDiscountAmountPercent) / 100)).toFixed(2);
            discountType = upDiscountAmountPercent + '%';
        } else if (upDiscountAmtType == 2) {
            discountedPrice = parseFloat($packagePrice - upDiscountAmountFixed).toFixed(2);
            discountType = 'P' + upDiscountAmountFixed;
        }

        if (discountedPrice > 0) {
            $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountCheckbox z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discUpPackItem' + id + '"><b>' + packName + '</b>' +
                ' P' + $packagePrice + ' - (Discount: ' + discountType + ') = (Total: P' + discountedPrice + ')' +
                '<i id="discUpPackChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        } else {
            $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountCheckbox z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discUpPackItem' + id + '"><b>' + packName + '</b>' +
                ' P' + $packagePrice + ' - (Discount: ' + discountType + ') = P0.00' +
                '<i id="discUpPackChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        }

    } else if (!packageID.is(':checked')) {
        chkupdate = chkupdate - 1;
        console.log(chkupdate);
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#discUpPackItem' + id).remove();
        packageID.prop('checked', false);
    }

    $('#discUpPackChip' + id).click(function () {
        chkupdate = chkupdate - 1;
        console.log(chkupdate);
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#discUpPackItem' + id).remove();
        $('#discUpPackCheck' + id).prop('checked', false);

    });
}

function updatePromoDiscount (id) {
    var promoID = $('#discUpPromoCheck' + id);
    if (promoID.is(':checked')) {
        var $promoTR = promoID.closest('tr'), // PRODUCT TR
            promoPrice = $promoTR.find('td:eq(3)').text(),
            $promoPrice = parseFloat(promoPrice.replace(/[^\d.]/g, '')).toFixed(2); // PRODUCT PRICE (CONVERTED ALREADY)
        var promoName = $promoTR.find('td:eq(1)').text(),
            discountedPrice,
            discountType,
            upDiscountAmountPercent = $('#upDiscountAmountPercent').val(),
            upDiscountAmountFixed = parseFloat($('#upDiscountAmountFixed').val().replace(/[^\d.]/g, '')).toFixed(2),
            upDiscountAmtType = $('#upDiscountAmtType').val();

        chkupdate = chkupdate + 1;
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        if (upDiscountAmtType == 1) { //percentage
            discountedPrice = parseFloat($promoPrice - ( ($promoPrice * upDiscountAmountPercent) / 100)).toFixed(2);
            discountType = upDiscountAmountPercent + '%';
        } else if (upDiscountAmtType == 2) {
            discountedPrice = parseFloat($promoPrice - upDiscountAmountFixed).toFixed(2);
            discountType = 'P' + upDiscountAmountFixed;
        }

        if (discountedPrice > 0) {
            $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountCheckbox z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discUpPromoItem' + id + '"><b>' + promoName + '</b>' +
                ' P' + $promoPrice + ' - (Discount: ' + discountType + ') = (Total: P' + discountedPrice + ')' +
                '<i id="discUpPromoChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        } else {
            $('#discountUpdateList').append('<div style="margin: 3px;" class="chip updateDiscountCheckbox z-depth-1 grey lighten-3 grey-text text-darken-4"' +
                ' id="discPromoItem' + id + '"><b>' + promoName + '</b>' +
                ' P' + $promoPrice + ' - (Discount: ' + discountType + ') = P0.00' +
                '<i id="discUpPromoChip' + id + '" class="material-icons" style="margin-right: 5px' +
                '!important">close</i></div>').show();
        }

    } else if (!promoID.is(':checked')) {
        chkupdate = chkupdate - 1;
        console.log(chkupdate);
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#discUpPromoItem' + id).remove();
        promoID.prop('checked', false);
    }

    $('#discUpPromoChip' + id).click(function () {
        chkupdate = chkupdate - 1;
        console.log(chkupdate);
        if (chkupdate < 1) {
            $('#updateDiscountSubmitBtn').attr('disabled', true).css('opacity', '0.3');
        } else if (chkupdate > 0) {
            $('#updateDiscountSubmitBtn').attr('disabled', false).css('opacity', '1');
        }

        $('#discUpPromoItem' + id).remove();
        $('#discUpPromoCheck' + id).prop('checked', false);

    });
}


function updateDiscount() {
    if($('#updateDiscountForm').valid()){
        var discountProdSelect = [],
            discountServSelect = [],
            discountPackSelect = [],
            discountPromoSelect = [],
            applicability = '';

        $.each($("input[name=updateDiscountProduct]:checked"), function () {
            discountProdSelect.push($(this).val());
        });
        $.each($("input[name=updateDiscountService]:checked"), function () {
            discountServSelect.push($(this).val());
        });
        $.each($("input[name=updateDiscountPackage]:checked"), function () {
            discountPackSelect.push($(this).val());
        });
        $.each($("input[name=updateDiscountPromo]:checked"), function () {
            discountPromoSelect.push($(this).val());
        });

        discountProdSelect = discountProdSelect.join(',');
        discountServSelect = discountServSelect.join(',');
        discountPackSelect = discountPackSelect.join(',');
        discountPromoSelect = discountPromoSelect.join(',');


        var discountname = $('#upDiscountName').val();
        var discountData = {
            "intDiscountID": $('#upDiscountID').val(),
            "strApplicability": $('input[name=strUpdateApplicability]:checked').val(),
            "strDiscountName": discountname,
            "strDiscountDetails": $('#upDiscountDetails').val(),
            "strDiscountGuidelines": $("#upDiscountGuidelines").val(),
            "strDiscountType": $('#upDiscountAmtType').val(),
            "strDiscountPriceFixed": $('#upDiscountAmountFixed').val().replace(/[^\d.]/g, ''),
            "strDiscountPricePercent": parseInt($('#upDiscountAmountPercent').val()),
            "checkedServices": discountServSelect,
            "checkedProducts": discountProdSelect,
            "checkedPackages": discountPackSelect,
            "checkedPromos": discountPromoSelect
        };

        console.log(discountData);

        swal({
                title: "Are you sure you want to update " + discountname + "?",
                text: "",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            },
            function () {
                setTimeout(function () {
                    $.ajax({
                        url: 'updateDiscount',
                        type: 'post',
                        data: discountData,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                            swal("Successfully created!", ".", "success");
                            updateDiscountTable();
                            $('#updateDiscountModal').closeModal();
                        },
                        error: function () {
                            sweetAlert("Oops...", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });
    }
}