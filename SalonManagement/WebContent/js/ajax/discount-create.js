/**
 * Created by Castillo on 9/8/2016.
 */
function createDiscount() {
    var discountProdSelect = [],
        discountServSelect = [],
        discountPackSelect = [],
        discountPromoSelect = [],
        applicability = '',
        requirement = [];

    $.each($("input[name=createDiscountProduct]:checked"), function () {
        discountProdSelect.push($(this).val());
    });
    $.each($("input[name=createDiscountService]:checked"), function () {
        discountServSelect.push($(this).val());
    });
    $.each($("input[name=createDiscountPackage]:checked"), function () {
        discountPackSelect.push($(this).val());
    });
    $.each($("input[name=createDiscountPromo]:checked"), function () {
        discountPromoSelect.push($(this).val());
    });

    discountProdSelect = discountProdSelect.join(',');
    discountServSelect = discountServSelect.join(',');
    discountPackSelect = discountPackSelect.join(',');
    discountPromoSelect = discountPromoSelect.join(',');

        var req = $('#crRequirement').val();

        requirement = req.join(',');

    var discountname = $('#crDiscountName').val();
    var discountData = {
        "strApplicability": $('input[name=strApplicability]:checked').val(),
        "strDiscountName": discountname,
        "strDiscountDetails": $('#crDiscountDetails').val(),
        "strDiscountType": $('#crDiscountAmtType').val(),
        "strDiscountPriceFixed": $('#crDiscountAmountFixed').val().replace(/[^\d.]/g, ''),
        "strDiscountPricePercent": parseInt($('#crDiscountAmountPercent').val()),
        "checkedServices": discountServSelect,
        "checkedProducts": discountProdSelect,
        "checkedPackages": discountPackSelect,
        "checkedPromos": discountPromoSelect,
        "requirements": requirement
    };

    if($('input[name=strApplicability]:checked').val() == 'TOTAL SALES') {
        if($('#createDiscountForm').valid()) {
            swal({
                    title: "Are you sure you want to create " + discountname + "?",
                    text: "",
                    type: "info",
                    showCancelButton: true,
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    setTimeout(function () {
                        $.ajax({
                            url: 'createDiscount',
                            type: 'post',
                            data: discountData,
                            dataType: 'json',
                            async: true,
                            success: function (data) {
                                swal("Successfully created!", ".", "success");
                                updateDiscountTable();
                                $('#createDiscountModal').closeModal();
                                $('#backbtn').click();
                                $('#createDiscountForm').trigger("reset");
                            },
                            error: function () {
                                sweetAlert("Oops...", "Something went wrong!", "error");
                            }
                        });
                    }, 1000);
                });
        }
    } else if ($('input[name=strApplicability]:checked').val() == 'SELECTED ITEMS') {
        swal({
                title: "Are you sure you want to create " + discountname + "?",
                text: "",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            },
            function () {
                setTimeout(function () {
                    $.ajax({
                        url: 'createDiscount',
                        type: 'post',
                        data: discountData,
                        dataType: 'json',
                        async: true,
                        success: function (data) {
                            swal("Successfully created!", ".", "success");
                            updateDiscountTable();
                            $('#createDiscountModal').closeModal();
                            $('#backbtn').click();
                            $('#createDiscountForm').trigger("reset");
                        },
                        error: function () {
                            sweetAlert("Oops...", "Something went wrong!", "error");
                        }
                    });
                }, 1000);
            });
    }
}