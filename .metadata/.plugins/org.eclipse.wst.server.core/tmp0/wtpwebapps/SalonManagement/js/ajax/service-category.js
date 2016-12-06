/**
 * Created by Castillo on 9/11/2016.
 */
/**
 * Created by Castillo on 9/11/2016.
 */
/**
 * Created by Castillo on 9/10/2016.
 */

window.onload = createServiceCategoryPosition();
$('#addCreateServiceCategory').hide();
$('.addUpdateServiceCategory').hide();
$("#crexistingServCat").remove();
$("#crfailedServCat").remove();
$(".upexistingServCat").remove();
$(".upfailedServCat").remove();

function showPhpFormat(id) {
    $('#upServPrice' + id).addClass('servPrice');
}

$('#crServAddCatName').on('input', function () {
    $('#addCreateServiceCategory').hide();
    $("#crexistingServCat").remove();
    $("#crfailedServCat").remove();
});

$('.upServAddCatName').on('input', function () {
    $('.addUpdateServiceCategory').hide();
    $(".upexistingServCat").remove();
    $(".upfailedServCat").remove();
});

function createServiceCategoryPosition() {
    $.ajax({
        type: 'get',
        url: 'getServiceCategory',
        dataType: 'json',
        async: true,
        success: function (data) {
            var categoryList = data.categoryList;
            if (categoryList != null) {
                $.each(categoryList, function (i, cat) {
                    $('select').material_select('destroy');
                    var catName = cat.categoryName.toUpperCase();
                    $('#crServiceCategory').append('<option value="' + catName + '">' + catName + "</option>");
                    $('#createServAddCategorySelect').append('<option value="' + catName + '">' + catName + "</option>");
                    $('.upServAddCategorySelect').append('<option value="' + catName + '">' + catName + "</option>");
                    $('select').material_select();
                });
            }
        }
    });
}

function addServiceCategory() {
    if ($('#createServAddCatForm').valid()) {
        var pos = $('#crServAddCatName').val();
        pos = pos.toUpperCase();
        $.ajax({
            type: 'post',
            url: 'addServiceCategory',
            dataType: 'json',
            data: {
                categoryName: pos,
                type: 1
            },
            async: true,
            success: function (data) {
                if (data.result == "success") {
                    $('#addCreateServiceCategory').hide();
                    $('select').material_select('destroy');
                    $('#crServiceCategory').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('#createServAddCategorySelect').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('.updateServCategory').append('<option>' + pos + '</option>').attr('value', pos);
                    $('.upServAddCategorySelect').append('<option>' + pos + '</option>').attr('value', pos);
                    $('select').material_select();
                } else if (data.result == "existing") {
                    $("#crexistingServCat").remove();
                    $("#crfailedServCat").remove();
                    $('#addCreateServiceCategory').show();
                    $('#addCreateServiceCategory').append('<span id="crexistingServCat" class="crexistingServCat white-text"><b>Category</b> is already existing</span>');
                    $('#addCreateServiceCategory .crfailedServCat').remove();
                } else if (data.result == "failed") {
                    $("#crexistingServCat").remove();
                    $("#crfailedServCat").remove();
                    $('#addCreateServiceCategory').show();
                    $('#addCreateServiceCategory').append('<span id="crfailedServCat" class="crfailedServCat white-text"><b>Category</b> failed</span>');
                    $('#addCreateServiceCategory .crexistingServCat').remove();
                }
            }
        });
    }
}

function addUpdateServiceCategory() {
    if ($('.upServAddCatName').valid()) {
        var pos = $('.upServAddCatName').val();
        pos = pos.toUpperCase();
        $.ajax({
            type: 'post',
            url: 'addServiceCategory',
            dataType: 'json',
            data: {
                categoryName: pos,
                type: 1
            },
            async: true,
            success: function (data) {
                if (data.result == "success") {
                    $('.addUpdateServiceCategory').hide();
                    $('select').material_select('destroy');
                    $('#crServiceCategory').append('<option>' + pos + '</option>').attr('value', pos);
                    $('#createServAddCategorySelect').append('<option>' + pos + '</option>').attr('value', pos);
                    $('.updateServCategory').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('.upServAddCategorySelect').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('select').material_select();
                } else if (data.result == "existing") {
                    $("#upexistingServCat").remove();
                    $("#upfailedServCat").remove();
                    $('.addUpdateServiceCategory').show();
                    $('.addUpdateServiceCategory').append('<span id="upexistingServCat" class="upexistingServCat white-text"><b>Category</b> is already existing</span>');
                    $('.addUpdateServiceCategory .upfailedServCat').remove();
                } else if (data.result == "failed") {
                    $("#upexistingServCat").remove();
                    $("#upfailedServCat").remove();
                    $('.addUpdateServiceCategory').show();
                    $('.addUpdateServiceCategory').append('<span id="upfailedServCat" class="upfailedServCat white-text"><b>Category</b> failed</span>');
                    $('.addUpdateServiceCategory .upexistingServCat').remove();
                }
            }
        });
    }
}

function removeCreateServiceCategory() {
    var createServAddCategorySelect = $('#createServAddCategorySelect');
    var pos = createServAddCategorySelect.val();
    $.ajax({
        type: 'post',
        url: 'removeServiceCategory',
        dataType: 'json',
        data: {
            categoryName: pos,
            type: 2
        },
        async: true,
        success: function (data) {
            if (data.result == "success") {
                $('#createServAddCategorySelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('#crServiceCategory option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
                // $('.updateAddCategorySelect option').each(function () {
                //     if ($(this).val() == pos) {
                //         $(this).remove();
                //     }
                //});
                // $('.upItemCategory option').each(function () {
                //     if ($(this).val() == pos) {
                //         $('select').material_select('destroy');
                //         $(this).remove();
                //         $('select').material_select();
                //     }
                // });
            } else if (data.result == "failed") {
            }
        }
    });
}

function removeUpdateServiceCategory() {
    var upServAddCategorySelect = $('.upServAddCategorySelect');
    var pos = upServAddCategorySelect.val();
    $.ajax({
        type: 'post',
        url: 'removeServiceCategory',
        dataType: 'json',
        data: {
            categoryName: pos,
            type: 2
        },
        async: true,
        success: function (data) {
            if (data.result == "success") {
                $('#createServAddCategorySelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('#crServiceCategory option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
                $('.upServAddCategorySelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('.updateServCategory option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
            } else if (data.result == "failed") {
            }
        }
    });
}