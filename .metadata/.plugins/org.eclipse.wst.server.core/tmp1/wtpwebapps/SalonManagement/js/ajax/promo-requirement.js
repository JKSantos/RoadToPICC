/**
 * Created by Castillo on 9/16/2016.
 */

var requirementName = [];

$.ajax({
    type: 'get',
    url: 'getRequirements',
    dataType: 'json',
    async: true,
    success: function (data) {
        var requirements = data.requirements;

        $.each(requirements, function(i, req) {
            requirementName.push(req.strRequirementName);
            $('#crPromoRequirement').append('<option value="' + req.intRequirementID + '">' + req.strRequirementName + '</option>');
            $('#addCrPromoRequirementSelect').append('<option value="' + req.intRequirementID + '">' + req.strRequirementName + '</option>');
            $('#upPromoRequirement').append('<option value="' + req.intRequirementID + '">' + req.strRequirementName + '</option>');
            //$('#addUpRequirementSelect').append('<option value="' + req.intRequirementID + '">' + req.strRequirementName + '</option>');
        });
        console.log(requirementName);
    },
    error: function (data) {

    }
});

$('#requirementPromoExistingDiv').hide();
$('#requirementPromoFailedDiv').hide();
$('.errorCreatePromoRequirement').hide();
$('#addCreatePromoOption').hide();

function crAddNewPromoRequirement() {
    if($('#createPromoRequirementForm').valid()) {
        var reqname = $('#addCrPromoRequirementName').val();

        $.ajax({
            type: 'post',
            url: 'createRequirement',
            data: {
                'strRequirementName': reqname
            },
            dataType: 'json',
            async: true,
            success: function(data) {
                var id = data.id;
                if(id > 0) {
                    console.log(data);
                    requirementName.push(reqname);
                    console.log(requirementName);
                    $('select').material_select('destroy');
                    $('#crPromoRequirement').append('<option value="' + id + '" selected>' + reqname.toUpperCase() + '</option>');
                    $('#addCrPromoRequirementSelect').append('<option value="' + id + '" selected>' + reqname.toUpperCase() + '</option>');
                    $('#upPromoRequirement').append('<option value="' + id + '" selected>' + reqname.toUpperCase() + '</option>');
                    $('select').material_select();
                    $('#crAddPromoNewReq').closeModal();
                    $('#createPromoRequirementForm').trigger('reset');
                    $('#requirementPromoExistingDiv').hide();
                    $('#requirementPromoFailedDiv').hide();
                    $('.errorCreatePromoRequirement').hide();
                    $('#addCreatePromoOption').hide();
                } else if (id == -1) {
                    $('#requirementPromoExistingDiv').show();
                } else {
                    $('#requirementPromoFailedDiv').show();
                }
            },
            error: function(data) {

            }
        });
    }
}


function crRemoveNewPromoRequirement() {
    var reqname = $('#addCrPromoRequirementSelect').val(),
        req = $('#addCrPromoRequirementSelect option[value=' + reqname +']').text();

    $.ajax({
        type: 'post',
        url: 'removeRequirement',
        data: {
            'intRequirementID': reqname
        },
        dataType: 'json',
        async: true,
        success: function(data) {
            //updateDiscountTable();
            for(var i=0; i<requirementName.length; i++) {
                console.log(requirementName);
                if(req.toLowerCase() == requirementName[i].toLowerCase()) {
                    var index = requirementName.indexOf(requirementName[i]);
                    console.log(index);
                    if(index > -1) {
                        requirementName.splice(index, 1);
                    }
                }
            }

            $('#addCrPromoRequirementSelect option').each(function () {
                if($(this).val() == reqname) {
                    $(this).remove();
                }
            });

            $('#crPromoRequirement option').each(function () {
                if($(this).val() == reqname) {
                    $('select').material_select('destroy');
                    $(this).remove();
                    $('select').material_select();
                }
            });

            $('#upPromoRequirement option').each(function () {
                if($(this).val() == reqname) {
                    $('select').material_select('destroy');
                    $(this).remove();
                    $('select').material_select();
                }
            });
        },
        error: function(data) {

        }
    });
}