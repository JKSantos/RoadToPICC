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
            $('#upRequirement').append('<option value="' + req.intRequirementID + '">' + req.strRequirementName + '</option>');
            $('#addUpRequirementSelect').append('<option value="' + req.intRequirementID + '">' + req.strRequirementName + '</option>');
        });
    },
    error: function (data) {
        
    }
});

$('#requirementExistingDiv').hide();
$('#requirementFailedDiv').hide();
$('.errorCreateRequirement').hide();
$('#addCreateoption').hide();

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
                var id = data.id;
                if(id > 0) {
                    requirementName.push(reqname);
                    $('select').material_select('destroy');
                    $('#crRequirement').append('<option value="' + id + '" selected>' + reqname.toUpperCase() + '</option>');
                    $('#addCrRequirementSelect').append('<option value="' + id + '" selected>' + reqname.toUpperCase() + '</option>');
                    $('#upRequirement').append('<option value="' + id + '" selected>' + reqname.toUpperCase() + '</option>');
                    $('select').material_select();
                    $('#crAddNewReq').closeModal();
                    $('#createRequirementForm').trigger('reset');
                    $('#requirementExistingDiv').hide();
                    $('#requirementFailedDiv').hide();
                    $('.errorCreateRequirement').hide();
                    $('#addCreateoption').hide();
                } else if (id == -1) {
                    $('#requirementExistingDiv').show();
                } else {
                    $('#requirementFailedDiv').show();
                }
            },
            error: function(data) {

            }
        });
    }
}


function crRemoveNewRequirement() {
    var reqname = $('#addCrRequirementSelect').val(),
        req = $('#addCrRequirementSelect option[value=' + reqname +']').text();

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
                if(req.toLowerCase() == requirementName[i].toLowerCase()) {
                    var index = requirementName.indexOf(requirementName[i]);
                    if(index > -1) {
                        requirementName.splice(index, 1);
                    }
                }
            }

            $('#addCrRequirementSelect option').each(function () {
               if($(this).val() == reqname) {
                   $(this).remove();
               }
            });
            
            $('#crRequirement option').each(function () {
                if($(this).val() == reqname) {
                    $('select').material_select('destroy');
                    $(this).remove();
                    $('select').material_select();
                }
            });

            $('#upRequirement option').each(function () {
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