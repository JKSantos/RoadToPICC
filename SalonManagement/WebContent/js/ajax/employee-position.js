/**
 * Created by Castillo on 9/10/2016.
 */

window.onload = createJobPosition();
$('#addCreateoption').hide();
$('.addUpdateoption').hide();
$("#crexisting").remove();
$("#crfailed").remove();

$('#addOptionName').on('input', function () {
    $('#addCreateoption').hide();
    $("#crexisting").remove();
    $("#crfailed").remove();
});

$('.updateAddOptionName').on('input', function () {
    $('#addUpdateoption').hide();
    $(".upexisting").remove();
    $(".upfailed").remove();
});

function createJobPosition() {
    $.ajax({
        type: 'get',
        url: 'getJob',
        dataType: 'json',
        async: true,
        success: function (data) {
            var jobList = data.jobList;
            console.log(data);
            console.log(jobList);
            if (jobList != null) {
                $.each(jobList, function (i, job) {
                    $('select').material_select('destroy');
                    $('#crSelectedJob').append('<option value="' + job.strJobDesc + '">' + job.strJobDesc + "</option>");
                    $('#addOptionSelect').append('<option value="' + job.strJobDesc + '">' + job.strJobDesc + "</option>");
                    $('#updateAddOptionSelect').append('<option value="' + job.strJobDesc + '">' + job.strJobDesc + "</option>");
                    $('select').material_select();
                });
            }
        }
    });
}

function addPosition() {
    if ($('#createOption').valid()) {
        var pos = $('#addOptionName').val();
        pos = pos.toUpperCase();
        $.ajax({
            type: 'post',
            url: 'addPosition',
            dataType: 'json',
            data: {
                positionName: pos,
                type: 1
            },
            async: true,
            success: function (data) {
                if (data.result == "success") {
                    $('#addCreateoption').hide();
                    $('select').material_select('destroy');
                    $('#crSelectedJob').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('#addOptionSelect').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('.upSelectedJob').append('<option>' + pos + '</option>').attr('value', pos);
                    $('.updateAddOptionSelect').append('<option>' + pos + '</option>').attr('value', pos);
                    $('select').material_select();
                } else if (data.result == "existing") {
                    $("#crexisting").remove();
                    $("#crfailed").remove();
                    console.log(data);
                    $('#addCreateoption').show();
                    $('#addCreateoption').append('<span id="crexisting" class="existing white-text"><b>Position</b> is already existing</span>');
                    $('#addCreateOption .failed').remove();
                } else if (data.result == "failed") {
                    $("#crexisting").remove();
                    $("#crfailed").remove();
                    $('#addCreateoption').show();
                    $('#addCreateoption').append('<span id="crfailed" class="failed white-text"><b>Position</b> failed</span>');
                    $('#addCreateOption .existing').remove();
                }
            }
        });
    }
}

function addUpdatePosition() {
    if ($('#updateAddOptionName').valid()) {

        var pos = $('.updateAddOptionName').val();
        $.ajax({
            type: 'post',
            url: 'addPosition',
            dataType: 'json',
            data: {
                positionName: pos,
                type: 1
            },
            async: true,
            success: function (data) {
                if (data.result == "success") {
                    $('#addUpdateoption').hide();
                    $('select').material_select('destroy');
                    $('#crSelectedJob').append('<option>' + pos + '</option>').attr('value', pos);
                    $('#addOptionSelect').append('<option>' + pos + '</option>').attr('value', pos);
                    $('.upSelectedJob').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('#updateAddOptionSelect').append('<option selected>' + pos + '</option>').attr('value', pos);
                    $('select').material_select();
                } else if (data.result == "existing") {
                    $(".upexisting").remove();
                    $(".upfailed").remove();
                    console.log(data);
                    $('#addUpdateoption').show();
                    $('#addUpdateoption').append('<span id="upexisting" class="upexisting white-text"><b>Position</b> is already existing</span>');
                    $('#addUpdateoption .upfailed').remove();
                } else if (data.result == "failed") {
                    $(".upfailed").remove();
                    $(".upfailed").remove();
                    $('#addUpdateoption').show();
                    $('#addUpdateoption').append('<span id="upfailed" class="upfailed white-text"><b>Position</b> failed</span>');
                    $('#addUpdateoption .upexisting').remove();
                }
            }
        });
    }
}

function removePosition() {
    var addOptionSelect = $('#addOptionSelect');
    var pos = addOptionSelect.val();
    $.ajax({
        type: 'post',
        url: 'removePosition',
        dataType: 'json',
        data: {
            positionName: pos,
            type: 2
        },
        async: true,
        success: function (data) {
            if (data.result == "success") {
                $('#addOptionSelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('#crSelectedJob option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
                $('#updateAddOptionSelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('#upSelectedJob option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
                console.log(data.result);
            } else if (data.result == "failed") {
                console.log('failed');
            }
        }
    });
}

function removeUpdatePosition() {
    var addOptionSelect = $('#updateAddOptionSelect');
    var pos = addOptionSelect.val();
    $.ajax({
        type: 'post',
        url: 'removePosition',
        dataType: 'json',
        data: {
            positionName: pos,
            type: 2
        },
        async: true,
        success: function (data) {
            if (data.result == "success") {
                $('#addOptionSelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('#crSelectedJob option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
                $('#updateAddOptionSelect option').each(function () {
                    if ($(this).val() == pos) {
                        $(this).remove();
                    }
                });
                $('#upSelectedJob option').each(function () {
                    if ($(this).val() == pos) {
                        $('select').material_select('destroy');
                        $(this).remove();
                        $('select').material_select();
                    }
                });
                console.log(data.result);
            } else if (data.result == "failed") {
                console.log('failed');
            }
        }
    });
}