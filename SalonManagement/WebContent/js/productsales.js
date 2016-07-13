/**
 * Created by Castillo on 7/10/2016.
 */
$(function () {
    $('#crDivOrderSt').hide();
    $('#crDivOrderLoc').hide();
    $('#crDivDelivDate').hide();
    $('#crDivPickDate').show();
    $('#crOrderType').change(function () {
        var ordertype = $(this).val();

        if (ordertype == 'delivery') {
            console.log(ordertype);
            $('#crDivOrderSt').show();
            $('#crDivOrderLoc').show();
            $('#crDivDelivDate').show();
            $('#crDivPickDate').hide();
        } else {
            console.log(ordertype);
            $('#crDivOrderSt').hide();
            $('#crDivOrderLoc').hide();
            $('#crDivDelivDate').hide();
            $('#crDivPickDate').show();
        }
    });
});

$(document).ready(function () {
    $('#prodsaleCRTable').DataTable({
        "bLengthChange": false,
        responsive: true,
        columnDefs: [
            {
                targets: [0, 1, 2],
                className: 'mdl-data-table__cell--non-numeric'
            }
        ],
        "rowHeight": '10px'
    });

    $('#prodsaleTable_length').attr('hidden',true);
});



$(document).ready(function () {
    $('#prodsaleTable').DataTable({
        "bLengthChange": false,
        responsive: true,
        columnDefs: [
            {
                targets: [0, 1, 2],
                className: 'mdl-data-table__cell--non-numeric'
            }
        ],
        "rowHeight": '10px'
    });
});

// $(document).ready(function () {
//     $(".dropdown-content.select-dropdown li").on("click", function () {
//         var that = this;
//         setTimeout(function () {
//             if ($(that).parent().hasClass('active')) {
//                 $(that).parent().removeClass('active');
//                 $(that).parent().hide();
//             }
//         }, 100);
//     });
// });


$(function () {
    $('#crProdSaleSelectall').click(function () {
        $('.case').attr('checked', this.checked);
    });

    $('.case').click(function () {
        if ($('.case').length == $('.case:checked').length) {
            $('#crProdSaleSelectall').attr('checked', 'checked');
        } else {
            $('#crProdSaleSelectall').removeAttr('checked');
        }
    });
});


//STEP FORM
$(document).ready(function () {
    var current = 1;

    widget = $(".step");
    btnback = $(".backform");
    btnnext = $(".nextform");
    btnsubmit = $(".submitform");

    // Init buttons and UI
    widget.not(':eq(0)').hide();
    hideButtons(current);
    setProgress(current);

    // Next button click action
    btnnext.click(function () {

        // if($('#crProdSaleForm').valid()){
        if (current < widget.length) {
            widget.show();
            widget.not(':eq(' + (current++) + ')').hide();
            setProgress(current);
            $('#CreateProdSaleModal').css('height', '90%');
        }
        hideButtons(current);
        // }
    });
    // Back button click action
    btnback.click(function () {
        if (current > 1) {
            current = current - 2;
            if (current < widget.length) {
                widget.show();
                widget.not(':eq(' + (current++) + ')').hide();
                setProgress(current);
                $('#CreateProdSaleModal').css('height', '70%');
            }
        }
        hideButtons(current);
    });

    // btnsubmit.submit(function(){
    //    $('#createEmpForm').submit(function(){
    //       alert("submitted");
    //    });
    // });
});

// Change progress bar action
setProgress = function (currstep) {
    var percent = parseFloat(100 / widget.length) * currstep;
    percent = percent.toFixed();
    $(".determinate")
        .css("width", percent + "%")
        .html(percent + "%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtons = function (current) {
    var limit = parseInt(widget.length);

    $(".action").hide();

    if (current < limit) btnnext.show();
    if (current == limit) {
        btnnext.hide();
        btnsubmit.show();
    }
    if (current > 1) btnback.show();
}

//STEP FORM END