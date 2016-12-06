/**
 * Created by Castillo on 9/11/2016.
 */
/**
 * Created by Castillo on 9/10/2016.
 */

window.onload = createCategoryPosition();
$('#addCreateCategory').hide();
$('.addUpdateCategory').hide();
$("#crexistingCat").remove();
$("#crfailedCat").remove();
$(".upexistingCat").remove();
$(".upfailedCat").remove();

$('#crProdAddCatName').on('input', function () {
    $('#addCreateCategory').hide();
    $("#crexistingCat").remove();
    $("#crfailedCat").remove();
});

$('.upProdAddCatName').on('input', function () {
    $('#addUpdateoption').hide();
    $(".upexistingCat").remove();
    $(".upfailedCat").remove();
});

function createCategoryPosition() {
    $.ajax({
        type: 'get',
        url: 'getCategory',
        dataType: 'json',
        async: true,
        success: function (data) {
            var categories = data.categories;
            if (categories != null) {
                $.each(categories, function (i, cat) {
                    $('select').material_select('destroy');
                    $('#crItemCategory').append('<option value="' + cat.strCategory + '">' + cat.strCategory + "</option>");
                    $('#createAddCategorySelect').append('<option value="' + cat.strCategory + '">' + cat.strCategory + "</option>");
                    $('.updateAddCategorySelect').append('<option value="' + cat.strCategory + '">' + cat.strCategory + "</option>");
                    $('select').material_select();
                });
            }
        }
    });
}

function addCategory() {
    if ($('#createAddCatForm').valid()) {
        var pos = $('#crProdAddCatName').val();
        pos = pos.toUpperCase();
        $.ajax({
            type: 'post',
            url: 'addCategory',
            dataType: 'json',
            data: {
                categoryName: pos,
                type: 1
            },
            async: true,
            success: function (data) {
                if (data.result == "success") {
                    $('#addCreateCategory').hide();
                    $('select').material_select('destroy');
                    $('#crItemCategory').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('#createAddCategorySelect').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('.upItemCategory').append('<option>' + pos + '</option>').attr('value', pos);
                    $('.updateAddCategorySelect').append('<option>' + pos + '</option>').attr('value', pos);
                    $('select').material_select();
                } else if (data.result == "existing") {
                    $("#crexistingCat").remove();
                    $("#crfailedCat").remove();
                    $('#addCreateCategory').show();
                    $('#addCreateCategory').append('<span id="crexistingCat" class="crexistingCat white-text"><b>Category</b> is already existing</span>');
                    $('#addCreateCategory .upfailedCat').remove();
                } else if (data.result == "failed") {
                    $("#crexistingCat").remove();
                    $("#crfailedCat").remove();
                    $('#addCreateCategory').show();
                    $('#addCreateCategory').append('<span id="crfailedCat" class="crfailedCat white-text"><b>Category</b> failed</span>');
                    $('#addCreateCategory .crexistingCat').remove();
                }
            }
        });
    }
}

function addUpdateCategory() {
    if ($('.upProdAddCatName').valid()) {
        var pos = $('.upProdAddCatName').val();
        pos = pos.toUpperCase();
        $.ajax({
            type: 'post',
            url: 'addCategory',
            dataType: 'json',
            data: {
                categoryName: pos,
                type: 1
            },
            async: true,
            success: function (data) {
                if (data.result == "success") {
                    $('.addUpdateCategory').hide();
                    $('select').material_select('destroy');
                    $('#crItemCategory').append('<option>' + pos + '</option>').attr('value', pos);
                    $('#createAddCategorySelect').append('<option>' + pos + '</option>').attr('value', pos);
                    $('.upItemCategory').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('.updateAddCategorySelect').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('select').material_select();
                } else if (data.result == "existing") {
                    $(".upexistingCat").remove();
                    $(".upfailedCat").remove();
                    $('.addUpdateCategory').show();
                    $('.addUpdateCategory').append('<span id="upexistingCat" class="upexistingCat white-text"><b>Category</b> is already existing</span>');
                    $('.addUpdateCategory .upfailedCat').remove();
                } else if (data.result == "failed") {
                    $(".upexistingCat").remove();
                    $(".upfailedCat").remove();
                    $('.addUpdateCategory').show();
                    $('.addUpdateCategory').append('<span id="upfailedCat" class="upfailedCat white-text"><b>Category</b> failed</span>');
                    $('.addUpdateCategory .upexistingCat').remove();
                }
            }
        });
    }
}

function removeCreateCategory() {
    var createAddCategorySelect = $('#createAddCategorySelect');
    var pos = createAddCategorySelect.val();
    $.ajax({
        type: 'post',
        url: 'removeCategory',
        dataType: 'json',
        data: {
            categoryName: pos,
            type: 2
        },
        async: true,
        success: function (data) {
            if (data.result == "success") {
                $('#createAddCategorySelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('#crItemCategory option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
                $('.updateAddCategorySelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('.upItemCategory option').each(function () {
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

function removeUpdateCategory() {
    var updateAddCategorySelect = $('.updateAddCategorySelect');
    var pos = updateAddCategorySelect.val();
    $.ajax({
        type: 'post',
        url: 'removeCategory',
        dataType: 'json',
        data: {
            categoryName: pos,
            type: 2
        },
        async: true,
        success: function (data) {
            if (data.result == "success") {
                $('#createAddCategorySelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('#crItemCategory option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
                $('.updateAddCategorySelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('.upItemCategory option').each(function () {
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