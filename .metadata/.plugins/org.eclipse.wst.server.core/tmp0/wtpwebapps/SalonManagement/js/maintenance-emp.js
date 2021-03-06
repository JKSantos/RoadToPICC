$(function () {
    $('#crPromoExpiration').attr('disabled', false);
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

    $(document).ready(function () {
        $('#crPromoFilter').change(function () {
            var $filter = $(this);

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


    $(document).ready(function () {
        $('#upPromoFilter').change(function () {
            var $filter = $(this);

            if ($filter.val() == "product") {
                $('#upPromoTblProd').parents('div.tablewrapper').first().fadeIn(500);
                $('#upPromoTblServ').parents('div.tablewrapper').first().hide();
                $('#upPromoTblPackage').parents('div.tablewrapper').first().hide();
            } else if ($filter.val() == "service") {
                $('#upPromoTblServ').parents('div.tablewrapper').first().fadeIn(500);
                $('#upPromoTblProd').parents('div.tablewrapper').first().hide();
                $('#upPromoTblPackage').parents('div.tablewrapper').first().hide();
            } else if ($filter.val() == "package") {
                $('#upPromoTblProd').parents('div.tablewrapper').first().hide();
                $('#upPromoTblServ').parents('div.tablewrapper').first().hide();
                $('#upPromoTblPackage').parents('div.tablewrapper').first().fadeIn(500);
            }
        });

    });

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
    var productsalestbl = $('#productsalestbl').dataTable({
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
    // var discounttbl = $('#discounttbl').DataTable({
    //     "bLengthChange": false,
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {className: "dt-body-left", "targets": [0, 1, 2, 3]},
    //         {className: "dt-body-right", "targets": [4]},
    //         {className: "dt-head-center", "targets": [5]},
    //         {"targets": [1, 2], render: $.fn.dataTable.render.ellipsis(25)}
    //     ],
    //     "rowHeight": '10px'
    // });

    // $("#discountSearch").bind('keyup search input paste cut', function () {
    //     discounttbl.search(this.value).draw();
    // });

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

    // var crdiscounttblProduct = $('#crdiscounttblProduct').DataTable({
    //     "bLengthChange": false,
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {className: "dt-body-left", "targets": [0, 1, 2]}
    //     ],
    //     "rowHeight": '10px'
    // });

    // var crdiscounttblService = $('#crdiscounttblService').DataTable({
    //     "bLengthChange": false,
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {className: "dt-body-left", "targets": [0, 1, 2]},
    //     ],
    //     "rowHeight": '10px'
    // });

    // var crdiscounttblPackage = $('#crdiscounttblPackage').DataTable({
    //     "bLengthChange": false,
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {className: "dt-body-left", "targets": [0, 1, 2]}
    //     ],
    //     "rowHeight": '10px'
    // });

    // var crdiscounttblPromo = $('#crdiscounttblPromo').DataTable({
    //     "bLengthChange": false,
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {className: "dt-body-left", "targets": [0, 1, 2]}
    //     ],
    //     "rowHeight": '10px'
    // });

    // $("#crDiscountSearch").bind('keyup search input paste cut', function () {
    //     crdiscounttblProduct.search(this.value).draw();
    //     crdiscounttblService.search(this.value).draw();
    //     crdiscounttblPackage.search(this.value).draw();
    //     crdiscounttblPromo.search(this.value).draw();
    // });

    $('#discountFilter').change(function () {
        var $filter = $(this);

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

    // discounttbl

    $('.radiobtn').click(function () {
        $('#crDiscountGuidelines').attr('disabled', true);
        if ($('input[name=strApplicability]:checked').val() == 'DEPENDING ON THE GUIDELINES') {
            $('#crDiscountGuidelines').attr('disabled', false);
        }
    });


});
$(function () {
    // var packagetbl = $('#packagetbl').dataTable({
    //     destroy: true,
    //     "bLengthChange": false,
    //     "sPaginationType": "full_numbers",
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {className: "dt-body-left", "targets": [0, 1, 2]},
    //         {className: "dt-head-center", "targets": [0, 3]},
    //         {"targets": [0], "width": "230px"},
    //         {"targets": [3], "width": "200px"},
    //         {"targets": [2], "width": "250px"},
    //         {"targets": [2], render: $.fn.dataTable.render.ellipsis(25)}
    //     ],
    //     "rowHeight": '10px'
    // });
    //
    // $("#packageSearch").bind('keyup search input paste cut', function () {
    //     packagetbl.search(this.value).draw();
    // });

    var archivePackageTbl = $('#archivePackageTbl').dataTable({
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

    $("#archivePackageSearch").bind('keyup search input paste cut', function () {
        archivePackageTbl.search(this.value).draw();
    });


});


$(document).ready(function () {
    $('#packageFilter').change(function () {
        var $filter = $(this);

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
            {"targets": [5], "width": "250px"},
            {"targets": [0], "width": "200px"},
            {"targets": [1], "width": "200px"},
            {"targets": [2], "width": "150px"},
            {"targets": [4], "type": "formatted-num"},
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
    // var deliverytbl = $('#deliverytbl').DataTable({
    //     "bLengthChange": false,
    //     "sPaginationType": "full_numbers",
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {"targets": [3], "width": "200px"},
    //         {"targets": [2], "type": "formatted-num"}
    //     ],
    //     "rowHeight": '10px'
    // });
    //
    // $("#deliverySearch").bind('keyup search input paste cut', function () {
    //     deliverytbl.search(this.value).draw();
    // });

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

    // var extratbl = $('#extratbl').dataTable({
    //     "bLengthChange": false,
    //     "sPaginationType": "full_numbers",
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {className: "dt-body-left", "targets": [0, 1]},
    //         {className: "dt-body-right", "targets": [2]},
    //         {className: "dt-head-center", "targets": [3]},
    //         {"targets": [3], "width": "200px"},
    //         {"targets": [2], "type": "formatted-num"}
    //     ],
    //     "rowHeight": '10px'
    // });
    //
    // $("#extraSearch").bind('keyup search input paste cut', function () {
    //     extratbl.search(this.value).draw();
    // });


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
    // var promotbl = $('#promotbl').dataTable({
    //     "bLengthChange": false,
    //     "sPaginationType": "full_numbers",
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {"targets": [0], "width": "150px"},
    //         {"targets": [2], "width": "100px"},
    //         {"targets": [1], "width": "200px"},
    //         {"targets": [3], "width": "100"},
    //         {"targets": [4], "width": "150"},
    //         {className: "dt-body-center", "targets": [4]},
    //         {className: "dt-body-left", "targets": [0, 1]},
    //         {className: "dt-body-right", "targets": [2, 3]}
    //     ],
    //     "rowHeight": '10px'
    // });
    //
    // $("#promoSearch").bind('keyup search input paste cut', function () {
    //     promotbl.search(this.value).draw();
    // });
});

$(document).ready(function () {
    // var discounttbl = $('#discounttbl').DataTable({
    //     "bLengthChange": false,
    //     responsive: true,
    //     "order": [],
    //     "columnDefs": [
    //         {"targets": 'no-sort', "orderable": false},
    //         {className: "dt-body-left", "targets": [0, 1, 2, 3, 4]},
    //         {className: "dt-body-right", "targets": [5]},
    //         {className: "dt-head-center", "targets": [6]},
    //         {"targets": [1, 2], render: $.fn.dataTable.render.ellipsis(25)}
    //     ],
    //     "rowHeight": '10px'
    // });
    //
    // $("#discountSearch").bind('keyup search input paste cut', function () {
    //     discounttbl.search(this.value).draw();
    // });
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
   // $('select').material_select();
});

$('#btnCrLocExit').click(function () {
    $('#createDeliveryForm').trigger("reset");
    $('.deliveryerrorcontainer').hide();
    $('#crLocationBRate').prop('disabled', false);
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
    $('#upLocationBRate').prop('disabled', false);
});


$('#crLocCancel').click(function () {
    $('#createDeliveryForm').trigger("reset");
    $('.deliveryerrorcontainer').hide();
    $('#crLocationBRate').prop('disabled', false);
});

$('.upLocCancel').click(function () {
    $('.updateDeliveryForm').trigger("reset");
    $('.updeliveryerrorcontainer').hide();
    $('#upLocationBRate').prop('disabled', false);
});

$('#btnProdCrExit').click(function () {
    $('#crItemCategory .crAddCatOpt').remove();
    $('#createProductForm').trigger("reset");
    $('#createAddCatForm').trigger("reset");
    $('.crproderrorcontainer').hide();
    //$('select').material_select();
});

$('#btnServCrExit').click(function () {
    $('#crServiceCategory .crServAddCatOpt').remove();
    $('#createServiceForm').trigger("reset");
    $('#createServAddCatForm').trigger("reset");
    $('.crserverrorcontainer').hide();
    //$('select').material_select();
});

$('.btnProdUpExit').click(function () {
    $('.upItemCategory .upProdAddCatOpt').remove();
    $('.updateProdForm').trigger("reset");
    $('.upProdAddCateForm').trigger("reset");
    $('.upproderrorcontainer').hide();
    //$('select').material_select();
});

$('.btnServUpExit').click(function () {
    $('.updateServCategory .upServAddCatOpt').remove();
    $('.updateservForm').trigger("reset");
    $('.upServAddCatForm').trigger("reset");
    $('.upserverrorcontainer').hide();
    //$('select').material_select();
    $('.upServSubmitBtn').prop('disabled', false).css('opacity', '1');
});

$('#crProdCancel').click(function () {
    $('#crItemCategory .crAddCatOpt').remove();
    $('#createProductForm').trigger("reset");
    $('#createAddCatForm').trigger("reset");
    $('.crproderrorcontainer').hide();
    //$('select').material_select();
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
    //$('select').material_select();
    $('.upServSubmitBtn').prop('disabled', false).css('opacity', '1');
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
    //$('select').material_select();
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
   // $('select').material_select();
});

$('#crAddOptCancel').click(function () {
    $('#createOption').trigger("reset");
    $('.errorCreateoption').hide();
    $('#addCreateoption').hide();
});

$('.upAddOptCancel').click(function () {
    $('.upProdAddCateForm').trigger("reset");
    $('.errorUpdateoption').hide();
    $('.addUpdateoption').hide();
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
    //$('select').material_select();

});


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
    //$('select').material_select();


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
    selectYears: 15,
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
    min: "Today",
    yearRange: "Today:2030",
    onSet: function (arg1) {
        if ('select' in arg1) { //prevent closing on selecting month/year
            this.close();
        }
    }
});


$('.datepicker-delivery').pickadate({
    selectYears: 15,
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
    min: "Today",
    yearRange: "Today:2030",
    onSet: function (arg1) {
        if ('select' in arg1) { //prevent closing on selecting month/year
            this.close();
        }
    }
});

$('.datepicker-promoUpdate').pickadate({
    selectYears: 15,
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
    min: "Today",
    yearRange: "Today:2030",
    onSet: function (arg1) {
        if ('select' in arg1) { //prevent closing on selecting month/year
            this.close();
        }
    }
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


function addCommas(nStr) {
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}

function pricesample() {
}