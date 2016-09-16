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
            $('#crRequirement').append('<option value="' + req.intRequirementID + '">' + req.strRequirementName + '</option>');
            $('#addCrRequirementSelect').append('<option value="' + req.intRequirementID + '">' + req.strRequirementName + '</option>');
        });
        console.log(requirementName);
    },
    error: function (data) {
        
    }
});

$('#requirementExistingDiv').hide();

$('#addCrRequirementName').on('input', function() {
    var count = 0;
    if(requirementName.length > 0) {
        for(var i = 0; i < requirementName.length; i++) {
            if($('#addCrRequirementName').val().toLowerCase() == requirementName[i].toLowerCase()) {
                count += 1;
                console.log(requirementName[i]);
            }
        }

        if(count > 0) {
            $('#requirementExistingDiv').show();
            $('#createAddNewRequirement').css('opacity', '0.3').prop('disabled', true);
        } else {
            $('#requirementExistingDiv').hide();
            $('#createAddNewRequirement').css('opacity', '1').prop('disabled', false);
        }
    } else {

    }
});

function crAddNewRequirement() {
    if($('#createRequirementForm').valid()) {
        var reqname = $('#addCrRequirementName').val();

        $.ajax({
           type: 'post',
            url: 'createRequirement',
            data: {
                'strRequirementName': reqname
            },
            dataType: 'json',
            async: true,
            success: function(data) {
                $('select').material_select('destroy');
                $('#crRequirement').append('<option value="' + reqname.toUpperCase() + '" selected>' + reqname.toUpperCase() + '</option>');
                $('#addCrRequirementSelect').append('<option value="' + reqname.toUpperCase() + '" selected>' + reqname.toUpperCase() + '</option>');
                $('#crAddNewReq').closeModal();
                $('select').material_select();
            },
            error: function(data) {

            }
        });
    }
}

function crRemoveNewRequirement() {
    var reqname = $('#addCrRequirementSelect').val();

    $.ajax({
       type: 'post',
        url: 'removeRequirement',
        data: {

        }
    });
}