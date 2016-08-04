$(function () {
    $('#crPromoNonExpiry').change(function () {
        if ($(this).is(':checked')) {
            $('#crPromoExpiration').attr('disabled', true);
        } else {
            $('#crPromoExpiration').attr('disabled', false);
        }
    });

    $('#strPromoFree').change(function () {
        if ($(this).is(':checked')) {
            $('#strPromoPrice').attr('disabled', true);
        } else {
            $('#strPromoPrice').attr('disabled', false);
        }
    });

    var crpromotblprod = $('#crpromotblprod').DataTable({
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

    var crpromotblserv = $('#crpromotblserv').DataTable({
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

    var crpromotblpackage = $('#crpromotblpackage').DataTable({
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

    $("#crPromoSearch").bind('keyup search input paste cut', function () {
        crpromotblprod.search(this.value).draw();
        crpromotblserv.search(this.value).draw();
        crpromotblpackage.search(this.value).draw();
    });

    $(document).ready(function () {
        $('#crPromoFilter').change(function () {
            var $filter = $(this);
            console.log($filter.val());

            if ($filter.val() == "product") {
                $('#crpromotblprod').parents('div.tablewrapper').first().fadeIn(500);
                $('#crpromotblserv').parents('div.tablewrapper').first().hide();
                $('#crpromotblpackage').parents('div.tablewrapper').first().hide();
            } else if ($filter.val() == "service") {
                $('#crpromotblserv').parents('div.tablewrapper').first().fadeIn(500);
                $('#crpromotblprod').parents('div.tablewrapper').first().hide();
                $('#crpromotblpackage').parents('div.tablewrapper').first().hide();
            } else if ($filter.val() == "package") {
                $('#crpromotblprod').parents('div.tablewrapper').first().hide();
                $('#crpromotblserv').parents('div.tablewrapper').first().hide();
                $('#crpromotblpackage').parents('div.tablewrapper').first().fadeIn(500);
            }
        });
        $('.crPromoBtn').click(function () {
            $('#crpacktblProd').parents('div.tablewrapper').first().show();
            $('#crpromotblserv').parents('div.tablewrapper').first().hide();
            $('#crpromotblpackage').parents('div.tablewrapper').first().hide();
        });

    });

    var total = 0;
    var $qty = 0;
    var q = 0;

    $('.promocheckbox').change(function () {
        var $tr = $(this).closest('tr'),
            qtyfield = $tr.find('td .rowQty');
        if ($(this).is(':checked')) {
            qtyfield.attr('disabled', false);
            var price = $tr.find('td:eq(3)').text(),
                $price = parseFloat(price.replace(/[^\d.]/g, '')).toFixed(2),
                showqty = parseInt($qty);

            qtyfield.focus(function () { //kapag nag focus sa textfield, kung ano nakalagay makukuha
                q = parseFloat($tr.find('td .rowQty').val()).toFixed(2);
                $qty = parseFloat($tr.find('td .rowQty').val()).toFixed(2);
            });
            $qty = parseFloat($tr.find('td .rowQty').val()).toFixed(2);
            total += $qty * $price;
            console.log(total);
            $('#totalPrice').html('P ' + parseFloat(total).toFixed(2));
            $('#crPromoPrice').val('P ' + parseFloat(total).toFixed(2));
            q = parseFloat($tr.find('td .rowQty').val()).toFixed(2);
            console.log(q);
            qtyfield.on('input', function () { //oninput the value will change
                $qty = parseFloat($tr.find('td .rowQty').val()).toFixed(2);
                console.log(q);
                console.log($qty);
                if ($qty > q) {
                    total += ($qty - q) * $price;
                    q = $qty;
                    console.log(q);
                    showqty = parseInt($qty);
                    total = Math.abs(total);
                    $('#totalPrice').html('P ' + parseFloat(total).toFixed(2));
                    $('#crPromoPrice').val('P ' + parseFloat(total).toFixed(2));
                } else if ($qty < q) {
                    total -= (q - $qty) * $price;
                    total = Math.abs(total);
                    $('#totalPrice').html('P ' + parseFloat(total).toFixed(2));
                    $('#crPromoPrice').val('P ' + parseFloat(total).toFixed(2));
                    q = $qty;
                    showqty = parseInt($qty);
                } else {

                }
            });


        } else if (!$(this).is(':checked')) {
            qtyfield.attr('disabled', true);
            var $utr = $(this).closest('tr'),
                unprice = $utr.find('td:eq(3)').text(),
                $unprice = parseFloat(unprice.replace(/[^\d.]/g, '')).toFixed(2);
            $qty = parseFloat($utr.find('td .rowQty').val()).toFixed(2);
            total = total - ($qty * $unprice);
            total = Math.abs(total);
            $('#totalPrice').html('P ' + parseFloat(total).toFixed(2));
            $('#crPromoPrice').val('P ' + parseFloat(total).toFixed(2));
        }
    });


    $('#createPromoSubmitForm').click(function () {
        swal("Successfully created!", "", "success");
        $('#createPromoForm').submit();
    })
});

$(document).ready(function () {
    var emptable = $('#emptbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [1, 2, 3, 4, 5]},
            {className: "dt-head-left", "targets": [1, 2, 3, 4, 5]},
            {className: "dt-body-center", "targets": [0]},
            {"targets": [5], render: $.fn.dataTable.render.ellipsis(20)},
            {"targets": [1], render: $.fn.dataTable.render.ellipsis(15)},
            {"targets": [4], render: $.fn.dataTable.render.ellipsis(20)}
        ],
        "rowHeight": '10px'
    });

    $("#empSearch").bind('keyup search input paste cut', function () {
        emptable.search(this.value).draw();
    });

    var empArchiveTbl = $('#empArchiveTbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [1, 2, 3, 4, 5]},
            {className: "dt-head-left", "targets": [1, 2, 3, 4, 5]},
            {className: "dt-body-center", "targets": [0]},
            {"targets": [5], render: $.fn.dataTable.render.ellipsis(20)},
            {"targets": [1], render: $.fn.dataTable.render.ellipsis(15)},
            {"targets": [4], render: $.fn.dataTable.render.ellipsis(20)}
        ],
        "rowHeight": '10px'
    });

    $("#empArchiveSearch").bind('keyup search input paste cut', function () {
        empArchiveTbl.search(this.value).draw();
    });
});


$(document).ready(function () {
    var inventorytbl = $('#inventorytbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 2]},
            {className: "dt-head-center", "targets": [0, 3]},
            {"targets": [0], "width": "230px"},
            {"targets": [3], "width": "200px"},
            {"targets": [2], "width": "250px"},
            {"targets": [2], render: $.fn.dataTable.render.ellipsis(25)}
        ],
        "rowHeight": '10px'
    });

    $("#inventorySearch").bind('keyup search input paste cut', function () {
        inventorytbl.search(this.value).draw();
    });

    $('.crinventybtn').click(function () {
        $('#defect').fadeIn(500);
        $('#lost').hide();
        $('#expired').hide();
    });

    $('#crInventory').change(function () {
        var select = $('select[name=crInventory]').val();
        if (select == 'defect') {
            $('#defect').fadeIn(500);
            $('#lost').hide();
            $('#expired').hide();
        } else if (select == 'lost') {
            $('#defect').hide();
            $('#lost').fadeIn(500);
            $('#expired').hide();
        } else if (select == 'expired') {
            $('#defect').hide();
            $('#lost').hide();
            $('#expired').fadeIn(500);
        }
    });
    $('#crInventDefSubmit').click(function () {
        $('#defectForm').submit();
    });
    $('#crInventLostSubmit').click(function () {
        $('#lostForm').submit();
    });
    $('#crInventExpiredSubmit').click(function () {
        $('#expiredForm').submit();
    });

    $('.updateAddInvent').click(function () {
        $('.addForm').submit();
    });

    $('.updateMinusInvent').click(function () {
        $('.minusForm').submit();
    });

    $('.addQty').each(function () {
        $(this).on('input', function () {
            var id = $(this).attr('id');
            var addqty = parseInt($('#add' + id).val());
            var val = parseInt($(this).val());
            var total = parseInt(val + addqty);
            $('.totalQty').val(total);
        });
    });

    $('.minusStock').each(function () {
        $(this).on('input', function () {
            var minusid = $(this).attr('id');
            var minusqty = parseInt($('#minus' + minusid).val());
            var minusval = parseInt($(this).val());
            var minustotal = parseInt(minusqty - minusval);
            $('.totalMinusQty').val(minustotal);
        });
    });

});

$(document).ready(function () {
    var productsalestbl = $('#productsalestbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 3, 4]},
            {className: "dt-body-right", "targets": [2]},
            {className: "dt-head-center", "targets": [0, 5]},
            {"targets": [0, 1], "width": "200px"},
            {"targets": [1], render: $.fn.dataTable.render.ellipsis(25)},
            {"targets": [0], render: $.fn.dataTable.render.ellipsis(30)}
        ],
        "rowHeight": '10px'
    });

    $("#psSearch").bind('keyup search input paste cut', function () {
        productsalestbl.search(this.value).draw();
    });
});


$(document).ready(function () {
    var crpstbl = $('#crpstbl').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [1, 2]},
            {className: "dt-body-right", "targets": [3]},
            {className: "dt-head-center", "targets": [0]},
            {"targets": [1], render: $.fn.dataTable.render.ellipsis(25)}
        ],
        "rowHeight": '10px'
    });

    $("#crpsSearch").bind('keyup search input paste cut', function () {
        crpstbl.search(this.value).draw();
    });

});


$(document).ready(function () {
    var discounttbl = $('#discounttbl').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 2, 3]},
            {className: "dt-body-right", "targets": [4]},
            {className: "dt-head-center", "targets": [5]},
            {"targets": [1, 2], render: $.fn.dataTable.render.ellipsis(25)}
        ],
        "rowHeight": '10px'
    });

    $("#discountSearch").bind('keyup search input paste cut', function () {
        discounttbl.search(this.value).draw();
    });

    $('.crDiscBtn').click(function () {
        $('#fixed').hide();
    });

    $('#crDiscountAmtType').change(function () {
        var type = $('select[name=strDiscountType]').val();
        if (type == 1) {
            $('#fixed').hide();
            $('#percent').show();
        } else {
            $('#percent').hide();
            $('#fixed').show();
        }
    });

    var crdiscounttblProduct = $('#crdiscounttblProduct').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 2]}
        ],
        "rowHeight": '10px'
    });

    var crdiscounttblService = $('#crdiscounttblService').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 2]},
        ],
        "rowHeight": '10px'
    });

    var crdiscounttblPackage = $('#crdiscounttblPackage').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 2]}
        ],
        "rowHeight": '10px'
    });

    var crdiscounttblPromo = $('#crdiscounttblPromo').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 2]}
        ],
        "rowHeight": '10px'
    });

    $("#crDiscountSearch").bind('keyup search input paste cut', function () {
        crdiscounttblProduct.search(this.value).draw();
        crdiscounttblService.search(this.value).draw();
        crdiscounttblPackage.search(this.value).draw();
        crdiscounttblPromo.search(this.value).draw();
    });

    $('#discountFilter').change(function () {
        var $filter = $(this);
        console.log($filter.val());

        if ($filter.val() == "product") {
            $('#crdiscounttblProduct').parents('div.tablewrapper').first().fadeIn(500);
            $('#crdiscounttblService').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblPackage').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblPromo').parents('div.tablewrapper').first().hide();
        } else if ($filter.val() == "service") {
            $('#crdiscounttblService').parents('div.tablewrapper').first().fadeIn(500);
            $('#crdiscounttblProduct').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblPackage').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblPromo').parents('div.tablewrapper').first().hide();
        } else if ($filter.val() == "package") {
            $('#crdiscounttblService').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblProduct').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblPackage').parents('div.tablewrapper').first().fadeIn(500);
            $('#crdiscounttblPromo').parents('div.tablewrapper').first().hide();
        } else if ($filter.val() == "promo") {
            $('#crdiscounttblService').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblProduct').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblPackage').parents('div.tablewrapper').first().hide();
            $('#crdiscounttblPromo').parents('div.tablewrapper').first().fadeIn(500);
        }
    });
    $('.crDiscBtn').click(function () {
        $('#crdiscounttblProduct').parents('div.tablewrapper').first().show();
        $('#crdiscounttblService').parents('div.tablewrapper').first().hide();
        $('#crdiscounttblPackage').parents('div.tablewrapper').first().hide();
        $('#crdiscounttblPromo').parents('div.tablewrapper').first().hide();
    });

    $('#createDiscountSubmitForm').click(function () {
        swal("Successfully created!", "", "success");
        $('#createDiscountForm').submit();
    });

    $('.radiobtn').click(function () {
        $('#crDiscountGuidelines').attr('disabled', true);
        if ($('input[name=strApplicability]:checked').val() == 'DEPENDING ON THE GUIDELINES') {
            $('#crDiscountGuidelines').attr('disabled', false);
        }
    });


});

$(function() {
    var packagetbl = $('#packagetbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 2]},
            {className: "dt-head-center", "targets": [0, 3]},
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

    

});


$(document).ready(function () {
    $('#packageFilter').change(function () {
        var $filter = $(this);
        console.log($filter.val());

        if ($filter.val() == "product") {
            $('#crpacktblProd').parents('div.tablewrapper').first().fadeIn(400);
            $('#crpacktblServ').parents('div.tablewrapper').first().hide();
        } else if ($filter.val() == "service") {
            $('#crpacktblServ').parents('div.tablewrapper').first().fadeIn(400);
            $('#crpacktblProd').parents('div.tablewrapper').first().hide();
        }
    });
    $('.createPackbtn').click(function () {
        $('#crpacktblProd').parents('div.tablewrapper').first().show();
        $('#crpacktblServ').parents('div.tablewrapper').first().hide();
    });

    $('#upPackageFilter').change(function () {
        var $filter = $(this);
        console.log($filter.val());

        if ($filter.val() == "product") {
            $('#uppackageProdtbl').parents('div.tablewrapper').first().fadeIn(400);
            $('#uppackageServtbl').parents('div.tablewrapper').first().hide();
        } else if ($filter.val() == "service") {
            $('#uppackageServtbl').parents('div.tablewrapper').first().fadeIn(400);
            $('#uppackageProdtbl').parents('div.tablewrapper').first().hide();
        }
    });

});

$(document).ready(function () {
    var prodtable = $('#prodtbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {"targets": [4], "width": "150px"},
            {"targets": [0], "width": "200px"},
            {"targets": [3], "type": "formatted-num"},
            {"targets": [2], render: $.fn.dataTable.render.ellipsis(30)}
        ],
        "rowHeight": '10px'
    });

    $("#prodSearch").bind('keyup search input paste cut', function () {
        prodtable.search(this.value).draw();
    });


    var prodArchiveTbl = $('#prodArchiveTbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {"targets": [4], "width": "150px"},
            {"targets": [0], "width": "200px"},
            {"targets": [3], "type": "formatted-num"},
            {"targets": [2], render: $.fn.dataTable.render.ellipsis(30)}
        ],
        "rowHeight": '10px'
    });

    $("#prodArchiveSearch").bind('keyup search input paste cut', function () {
        prodArchiveTbl.search(this.value).draw();
    });
});

$(document).ready(function () {
    var servtable = $('#servtbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {"targets": [4], "width": "150px"},
            {"targets": [0], "width": "200px"},
            {"targets": [2], "width": "300px"},
            {"targets": [3], "type": "formatted-num"},
            {"targets": [2], render: $.fn.dataTable.render.ellipsis(40)}
        ],
        "rowHeight": '10px'
    });

    $("#servSearch").bind('keyup search input paste cut', function () {
        servtable.search(this.value).draw();
    });

    var servArchiveTbl = $('#servArchiveTbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {"targets": [4], "width": "150px"},
            {"targets": [0], "width": "200px"},
            {"targets": [2], "width": "300px"},
            {"targets": [3], "type": "formatted-num"},
            {"targets": [2], render: $.fn.dataTable.render.ellipsis(40)}
        ],
        "rowHeight": '10px'
    });

    $("#servArchiveSearch").bind('keyup search input paste cut', function () {
        servArchiveTbl.search(this.value).draw();
    });
});

$(document).ready(function () {
    var deliverytbl = $('#deliverytbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {"targets": [3], "width": "200px"},
            {"targets": [2], "type": "formatted-num"}
        ],
        "rowHeight": '10px'
    });

    $("#deliverySearch").bind('keyup search input paste cut', function () {
        deliverytbl.search(this.value).draw();
    });

    var deliveryArchiveTbl = $('#deliveryArchiveTbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {"targets": [3], "width": "200px"},
            {"targets": [2], "type": "formatted-num"}
        ],
        "rowHeight": '10px'
    });

    $("#deliveryArchiveSearch").bind('keyup search input paste cut', function () {
        deliveryArchiveTbl.search(this.value).draw();
    });
});

$(document).ready(function () {
    
    var extratbl = $('#extratbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1]},
            {className: "dt-body-right", "targets": [2]},
            {className: "dt-head-center", "targets": [3]},
            {"targets": [3], "width": "200px"},
            {"targets": [2], "type": "formatted-num"}
        ],
        "rowHeight": '10px'
    });

    $("#extraSearch").bind('keyup search input paste cut', function () {
        extratbl.search(this.value).draw();
    });
    

    var otherchargeArchiveTbl = $('#otherchargeArchiveTbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {"targets": [3], "width": "200px"},
            {"targets": [2], "type": "formatted-num"}
        ],
        "rowHeight": '10px'
    });

    $("#otherchargeArchiveSearch").bind('keyup search input paste cut', function () {
        otherchargeArchiveTbl.search(this.value).draw();
    });
});

$(document).ready(function () {
    var promotbl = $('#promotbl').DataTable({
        "bLengthChange": false,
        "sPaginationType": "full_numbers",
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {"targets": [0], "width": "150px"},
            {"targets": [1], "width": "100px"},
            {"targets": [2], "width": "200px"},
            {"targets": [3], "width": "100"},
            {"targets": [4], "width": "150"},
            {className: "dt-body-center", "targets": [4]},
            {"targets": [2], render: $.fn.dataTable.render.ellipsis(30)}
        ],
        "rowHeight": '10px'
    });

    $("#promoSearch").bind('keyup search input paste cut', function () {
        promotbl.search(this.value).draw();
    });
});


// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
$('.modal-trigger').leanModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
});

$('.modal-update').leanModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-archive').leanModal({
    dismissible: true,
    opacity: .9,
    in_duration: 400,
    out_duration: 400
});

$('.modal-viewall').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: 0.95, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);


$('.modal-view').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-option').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .2, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-optionUpdate').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .2, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-category').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .2, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-updateCategory').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .2, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('#btnCreateExit').click(function () {
    $("#crSelectedJob .crAddOpt").remove();
    $('#createEmpForm').trigger("reset");
    $('#createOption').trigger("reset");
    $('#backbtn').click();
    $('.errorcontainer').hide();
    $('select').material_select();
});

$('#btnCrLocExit').click(function () {
    $('#createDeliveryForm').trigger("reset");
    $('.deliveryerrorcontainer').hide();
});

$('#btnCrExtraExit').click(function () {
    $('#createExtraForm').trigger("reset");
    $('.extraerrorcontainer').hide();
});

$('.btnUpExtraExit').click(function () {
    $('.updateExtraForm').trigger("reset");
    $('.upextraerrorcontainer').hide();
});

$('.upExtraCancel').click(function () {
    $('.updateExtraForm').trigger("reset");
    $('.upextraerrorcontainer').hide();
});

$('.btnUpExtraExit').click(function () {
    $('.updateExtraForm').trigger("reset");
    $('.upextraerrorcontainer').hide();
});

$('.crExtraCancel').click(function () {
    $('#createExtraForm').trigger("reset");
    $('.extraerrorcontainer').hide();
});

$('.upExtraCancel').click(function () {
    $('.updateExtraForm').trigger("reset");
    $('.upextraerrorcontainer').hide();
});

$('.btnUpLocExit').click(function () {
    $('.updateDeliveryForm').trigger("reset");
    $('.updeliveryerrorcontainer').hide();
});


$('#crLocCancel').click(function () {
    $('#createDeliveryForm').trigger("reset");
    $('.deliveryerrorcontainer').hide();
});

$('.upLocCancel').click(function () {
    $('.updateDeliveryForm').trigger("reset");
    $('.deliveryerrorcontainer').hide();
});

$('#btnProdCrExit').click(function () {
    $('#crItemCategory .crAddCatOpt').remove();
    $('#createProductForm').trigger("reset");
    $('#createAddCatForm').trigger("reset");
    $('.crproderrorcontainer').hide();
    $('select').material_select();
});

$('#btnServCrExit').click(function () {
    $('#crServiceCategory .crServAddCatOpt').remove();
    $('#createServiceForm').trigger("reset");
    $('#createServAddCatForm').trigger("reset");
    $('.crserverrorcontainer').hide();
    $('select').material_select();
});

$('.btnProdUpExit').click(function () {
    $('.upItemCategory .upProdAddCatOpt').remove();
    $('.updateProdForm').trigger("reset");
    $('.upProdAddCateForm').trigger("reset");
    $('.upproderrorcontainer').hide();
    $('select').material_select();
});

$('.btnServUpExit').click(function () {
    $('.updateServCategory .upServAddCatOpt').remove();
    $('.updateservForm').trigger("reset");
    $('.upServAddCatForm').trigger("reset");
    $('.upserverrorcontainer').hide();
    $('select').material_select();
});

$('#crProdCancel').click(function () {
    $('#crItemCategory .crAddCatOpt').remove();
    $('#createProductForm').trigger("reset");
    $('#createAddCatForm').trigger("reset");
    $('.crproderrorcontainer').hide();
    $('select').material_select();
});

$('#crServAddProdCatCancel').click(function () {
    $('#createServAddCatForm').trigger("reset");
    $('.crservcat').hide();
});

$('.upServCancel').click(function () {
    $('.updateServCategory .upServAddCatOpt').remove();
    $('.updateservForm').trigger("reset");
    $('.upServAddCatForm').trigger("reset");
    $('.upserverrorcontainer').hide();
    $('select').material_select();
});

$('.upServAddProdCatCancel').click(function () {
    $('.upServAddCatForm').trigger("reset");
    $('.upservcat').hide();
});

$('#crServCancel').click(function () {
    $('#crServiceCategory .crServAddCatOpt').remove();
    $('#createServiceForm').trigger("reset");
    $('#createServAddCatForm').trigger("reset");
    $('.crserverrorcontainer').hide();
    $('select').material_select();
});

$('#crAddProdCatCancel').click(function () {
    $('#createAddCatForm').trigger("reset");
    $('.crprodcat').hide();
});

$('.upAddProdCatCancel').click(function () {
    $('.upProdAddCatForm').trigger("reset");
    $('.upprodcat').hide();
});

$('.upProdCancel').click(function () {
    $('.upItemCategory .upProdAddCatOpt').remove();
    $('.updateProdForm').trigger("reset");
    $('.upProdAddCateForm').trigger("reset");
    $('.upproderrorcontainer').hide();
    $('select').material_select();
});

$('#crAddOptCancel').click(function () {
    $('#createOption').trigger("reset");
    $('.errorCreateoption').hide();
});

$('.upAddOptCancel').click(function () {
    $('.upProdAddCateForm').trigger("reset");
    $('.errorUpdateoption').hide();
});


$('.upAddOptExit').click(function () {
    $('.updateOptionForm').trigger("reset");
    $('.errorUpdateoption').empty();
});

$('.btnUpdateExit').click(function () {
    $(".upSelectedJob .upAddOpt").remove();
    $('.updateEmpForm').trigger("reset");
    $('.updateOptionForm').trigger("reset");
    $('.updateerror').empty();
    $('select').material_select();

});


// $

$('#createAddPosition').click(function () {
    if ($('#createOption').valid()) {

        $('select').material_select('destroy');
        var addopt = $('#addOptionName').val();
        $('#crSelectedJob').append('<option selected>' + addopt + '</option>').attr('value', addopt);
        $('select').material_select();

        $('#createAddOption').closeModal();
    }
});

$('.updateAddPosition').each(function () {
    $(this).click(function () {
        var upAddOpt = "upAddOpt";
        if ($('#updateOptionForm').valid()) {
            $('select').material_select('destroy');
            var addoptupdate = $('.updateAddOptionName').val();
            $('.upSelectedJob').append('<option selected class="' + upAddOpt + '">' + addoptupdate + '</option>').attr('value', addoptupdate);
            $('select').material_select();

            $('#updateOption').closeModal();
        }
    });
});

$('#createAddCatBtn').click(function () {
    var crAddCatOpt = "crAddCatOpt";
    if ($('#createAddCatForm').valid()) {
        $('select').material_select('destroy');
        var addprodcat = $('#crProdAddCatName').val();
        $('#crItemCategory').append('<option selected class="' + crAddCatOpt + '">' + addprodcat + '</option>').attr('value', addprodcat);
        $('select').material_select();

        $('#crProdAddCate').closeModal();
    }
});


$('.updateProdAddCatBtn').click(function () {
    var upProdAddCatOpt = "upProdAddCatOpt";
    if ($('.upProdAddCatForm').valid()) {
        $('select').material_select('destroy');
        var upprodcat = $('.upProdAddCatName').val();
        $('.upItemCategory').append('<option selected class="' + upProdAddCatOpt + '">' + upprodcat + '</option>').attr('value', upprodcat);
        console.log(upprodcat);
        $('select').material_select();

        $('.upProdAddCatModal').closeModal();
    }
});

$('#createServAddCatBtn').click(function () {
    var crServAddCatOpt = "crServAddCatOpt";
    if ($('#createServAddCatForm').valid()) {
        $('select').material_select('destroy');
        var addservcat = $('#crServAddCatName').val();
        $('#crServiceCategory').append('<option selected class="' + crServAddCatOpt + '">' + addservcat + '</option>').attr('value', addservcat);
        $('select').material_select();

        $('#crServAddCateModal').closeModal();
    }
});

$('.updateServAddCatBtn').click(function () {
    var upServAddCatOpt = "upServAddCatOpt";
    if ($('.upServAddCatForm').valid()) {
        $('select').material_select('destroy');
        var upservcat = $('.upServAddCatName').val();
        $('.updateServCategory').append('<option selected class="' + upServAddCatOpt + '">' + upservcat + '</option>').attr('value', upservcat);
        $('select').material_select();

        $('.upServAddCatModal').closeModal();
    }
});

function createProductSale() {
    var strOrderType = $('select[name=strOrderType]').val(),
        strOrderContact = $('#crOrderContact').val(),
        strOrderName = $('#crOrderName').val(),
        strOrderStreet = $('#crOrderStreet').val(),
        strOrderLocation = $('select[name=strOrderLocation]').val(),
        checkedValues = $('input:checkbox:checked').map(function () {
            return this.value;
        }).get().toString(),
        quantity = $("#createPSForm .psQty").map(function () {
            return $(this).val();
        }).get().toString();

    swal({
            title: "Submit?",
            text: "", showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            setTimeout(function () {
                $.ajax({
                    type: 'post',
                    url: 'createOrder',
                    data: {
                        "orderType": strOrderType,
                        "strContactNo": strOrderContact,
                        "strName": strOrderName,
                        "strStreet": strOrderStreet,
                        "intLocationID": strOrderLocation,
                        "selectedProducts": checkedValues,
                        "productQuantity": quantity
                    },
                    dataType: 'json',
                    async: true,
                    success: function (data) {
                        if (data.status === 'success') {
                            swal("Order was successfully submitted!", "success");
                            updatePSTable();
                            $('#crProductSales').closeModal();
                        } else {
                            sweetAlert("Oops...", "Something went wrong!", "error");
                        }
                    },
                    error: function (data) {
                        sweetAlert("Oops...", "Something went wrong!", "error");
                    }
                });

            }, 1000);
        });

}

// $('#defsubmitbtn').click(function () {
//    if($('#defForm').valid()){
//        $(this).submit();
//    }
// });


$('#addOptionSelect').click(function () {
    $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
});

$('#updateOptionSelect').click(function () {
    $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
});

$('#addCatgorySelect').click(function () {
    $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
});

$('#updateCatgorySelect').click(function () {
    $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
});


$('.tooltipped').tooltip({delay: 30});

$(document).ready(function () {
    $(".button-collapse").sideNav();
    // Initialize collapsible (uncomment the line below if you use the dropdown variation)
    $('.collapsible').collapsible();
    $('select').material_select();


});


// add product / service in table (PROMO)


$('#createBirthday').pickadate({
    selectYears: 40,
    selectMonths: true,
    labelMonthNext: 'Next month',
    labelMonthPrev: 'Previous month',
    labelMonthSelect: 'Select a month',
    labelYearSelect: 'Select a year',
    monthsFull: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
    monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    weekdaysFull: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
    weekdaysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    weekdaysLetter: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
    today: 'Today',
    clear: 'Clear',
    close: 'Close',
    format: 'mmmm/d/yyyy',
    max: 'Today',
    yearRange: "1970:Today",
    onSet: function (arg) {
        if ('select' in arg) { //prevent closing on selecting month/year
            this.close();
        }
        var bdate = $('#createBirthday').val();
        var dob = new Date(bdate);
        var today = new Date();
        var age = today.getTime() - dob.getTime();
        age = Math.floor(age / (1000 * 60 * 60 * 24 * 365.25));
        if (age < 18) {
            $('#createAge').css('color', 'red').val("Not Qualified");
            $('.nextform').attr('disabled', true);
        } else {
            $('#createAge').val(age).css('color', 'black');
            $('.nextform').attr('disabled', false);
        }

    }
});

$('.updateEmpBirthday').each(function () {
    $(this).pickadate({
        selectYears: 40,
        selectMonths: true,
        labelMonthNext: 'Next month',
        labelMonthPrev: 'Previous month',
        labelMonthSelect: 'Select a month',
        labelYearSelect: 'Select a year',
        monthsFull: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        weekdaysFull: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
        weekdaysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        weekdaysLetter: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
        today: 'Today',
        clear: 'Clear',
        close: 'Close',
        format: 'mmmm/d/yyyy',
        max: 'Today',
        yearRange: "1970:Today",
        onSet: function (arg1) {
            if ('select' in arg1) { //prevent closing on selecting month/year
                this.close();
            }
                var bdate1 = $('.updateEmpBirthday').val();
                var dob1 = new Date(bdate1);
                var today1 = new Date();
                var age1 = today1.getTime() - dob1.getTime();
                age1 = Math.floor(age1 / (1000 * 60 * 60 * 24 * 365.25));
                if (age1 < 18) {
                    $('.updateEmpAge').css('color', 'red').val("Not Qualified");
                    $('.updateSubmitForm').attr('disabled', true);
                } else {
                    $('.updateEmpAge').val(age1).css('color', 'black');
                    $('.updateSubmitForm').attr('disabled', false);
                }

        }
    });
});

// bday END


$('.datepicker-promo').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year
    min: 'Today',
    yearRange: "Today:2020"
});

// promo END

$(document).ready(function () {

    $('#createBirthday').change(function () {

        var today = new Date();
        var dd = Number(today.getDate());
        var mm = Number(today.getMonth() + 1);
        var yyyy = Number(today.getFullYear());

        var myBD = $('#createBirthday').val();
        var myBDM = Number(myBD.split("/")[0]);
        var myBDD = Number(myBD.split("/")[1]);
        var myBDY = Number(myBD.split("/")[2]);
        var age = yyyy - myBDY;

        if (mm < myBDM) {
            age = age - 1;

        }
        else if (mm == myBDM && dd < myBDD) {
            age = age - 1;
        }

        if (age > 17) {
            $('#createAge').val(age);
        } else {
            $('#createAge').val("not qualified");
        }


    });

});


$('#updateBirthday').each(function () {

    $(this).change(function () {

        var today = new Date();
        var dd = Number(today.getDate());
        var mm = Number(today.getMonth() + 1);

        var yyyy = Number(today.getFullYear());

        var myBD = $('#updateBirthday').val();
        var myBDM = Number(myBD.split("/")[0])
        var myBDD = Number(myBD.split("/")[1])
        var myBDY = Number(myBD.split("/")[2])
        var age = yyyy - myBDY;

        if (mm < myBDM) {
            age = age - 1;
        }
        else if (mm == myBDM && dd < myBDD) {
            age = age - 1;
        }

        $('#updateAge').val(age);
    });

});

$(document).ready(function () {
    $(".nonexpiry").click(function () {
        if ($(".nonexpiry").is(":checked")) {
            $(".expiration")
                .attr("disabled", "disabled");
        }
        else {
            $(".expiration")
                .removeAttr("disabled");
        }
    });
});

$(document).ready(function () {
    $(".free").click(function () {
        if ($(".free").is(":checked")) {
            $(".price")
                .attr("disabled", "disabled");
        }
        else {
            $(".price")
                .removeAttr("disabled");
        }
    });
});


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('.empprevimg').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

$(".empimgupload").change(function () {
    readURL(this);
});

function updateProdImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('.updateProdImage').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

$(".upProdImg").change(function () {
    updateProdImage(this);
});

function updateServImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('.updateServImage').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

$(".upServImg").change(function () {
    updateServImage(this);
});


$('#emptbl').on('click', '.empdeacbtn', function (e) {
    e.returnValue = false;
    var deacbtn = $(this).attr('id');
    console.log(deacbtn);
    var mydata = {
        'intEmpID': deacbtn
    }
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'deactivateEmployee',
                data: mydata,
                success: function (response) {
                    $tr.find('td').fadeOut(1000, function () {
                        $tr.remove();
                    });
                }
            });
        });


});


$('#prodtbl').on('click', '.proddeacbtn', function (e) {
    e.returnValue = false;
    var proddeacbtn = $(this).attr('id');
    console.log(proddeacbtn);
    var proddata = {
        'intItemID': proddeacbtn
    }
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'deactivateItem',
                data: proddata,
                success: function (response) {
                    $tr.find('td').fadeOut(1000, function () {
                        $tr.remove();
                    });
                }
            });
        });
});


$('#servtbl').on('click', '.servdeacbtn', function (e) {
    e.returnValue = false;
    var servdeacbtn = $(this).attr('id');
    console.log(servdeacbtn);
    var servdata = {
        'intItemID': servdeacbtn
    }
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'deactivateService',
                data: servdata,
                success: function (response) {
                    $tr.find('td').fadeOut(1000, function () {
                        $tr.remove();
                    });
                }
            });
        });
});


$('#deliverytbl').on('click', '.deliverydeacbtn', function (e) {
    e.returnValue = false;
    var deliveryID = $(this).attr('id');
    console.log(deliveryID);
    var deliverydata = {
        'intLocationID': deliveryID
    }
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'deactivateLocation',
                data: deliverydata,
                success: function (response) {
                    $tr.find('td').fadeOut(500, function () {
                        $tr.remove();
                    });
                }
            });
        });
});

$('#promotbl').on('click', '.promodeacbtn', function (e) {
    e.returnValue = false;
    var promoID = $(this).attr('id');
    console.log(promoID);
    var promodata = {
        'intPromoID': promoID
    }
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'deactivatePromo',
                data: promodata,
                success: function (response) {
                    $tr.find('td').fadeOut(200, function () {
                        $tr.remove();
                    });
                }
            });
        });
});



$('#packagetbl').on('click', '.packagedeacbtn', function (e) {
    e.returnValue = false;
    var packageID = $(this).attr('id');
    console.log(packageID);
    var packagedata = {
        'intPackageID': packageID
    }
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'deactivatePackage',
                data: packagedata,
                success: function (response) {
                    $tr.find('td').fadeOut(500, function () {
                        $tr.remove();
                    });
                }
            });
        });
});

$('#inventorytbl').on('click', '.inventdeacbtn', function (e) {
    e.returnValue = false;
    var inventdeac = $(this).attr('id');
    console.log(inventdeac);
    var deactivateItem = {
        'intItemID': inventdeac
    }
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'deactivateItem',
                data: deactivateItem,
                success: function (response) {
                    $tr.find('td').fadeOut(500, function () {
                        $tr.remove();
                    });
                }
            });
        });
});

$('#discounttbl').on('click', '.discountdeacbtn', function (e) {
    e.returnValue = false;
    var discountdeacID = $(this).attr('id');
    var deactivateDiscount = {
        'intDiscountID': discountdeacID
    }
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'deactivateDiscount',
                data: deactivateDiscount,
                success: function (response) {
                    $tr.find('td').fadeOut(500, function () {
                        $tr.remove();
                    });
                }
            });
        });
});


$(document).ready(function () {
    var reservationtbl = $('#reservationtbl').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 3, 2, 4]},
            {className: "dt-body-right", "targets": [3]},
            {className: "dt-head-center", "targets": [5]}
        ],
        "rowHeight": '10px'
    });

    $("#reservationSearch").bind('keyup search input paste cut', function () {
        reservationtbl.search(this.value).draw();
    });

    $('.crReservation').click(function () {
        $('#crdiscounttblProduct').parents('div.tablewrapper').first().show();
        $('#crdiscounttblService').parents('div.tablewrapper').first().hide();
        $('#crdiscounttblPackage').parents('div.tablewrapper').first().hide();
        $('#crdiscounttblPromo').parents('div.tablewrapper').first().hide();
    });
});

$(document).ready(function () {
    var employeeReservationtbl = $('#employeeReservationtbl').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [1]},
            {className: "dt-body-center", "targets": [0]}
        ],
        "rowHeight": '10px'
    });
});


////////////////////////


$(document).ready(function () {
    var walkintbl = $('#walkintbl').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1, 3]},
            {className: "dt-head-center", "targets": [2, 4]}
        ],
        "rowHeight": '10px'
    });

    $("#walkinSearch").bind('keyup search input paste cut', function () {
        walkintbl.search(this.value).draw();
    });

    var crWIServicetbl = $('#crWIServicetbl').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1]},
            {className: "dt-head-center", "targets": [3]},
            {className: "dt-head-right", "targets": [2]}
        ],
        "rowHeight": '10px'
    });

    var crWIProducttbl = $('#crWIProducttbl').DataTable({
        "bLengthChange": false,
        responsive: true,
        "order": [],
        "columnDefs": [
            {"targets": 'no-sort', "orderable": false},
            {className: "dt-body-left", "targets": [0, 1]},
            {className: "dt-head-center", "targets": [3]},
            {className: "dt-head-right", "targets": [2]}
        ],
        "rowHeight": '10px'
    });

});
