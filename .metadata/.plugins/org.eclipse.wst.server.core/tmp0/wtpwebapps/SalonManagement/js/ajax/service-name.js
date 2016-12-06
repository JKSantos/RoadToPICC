/**
 * Created by Castillo on 9/16/2016.
 */
/**
 * Created by Castillo on 9/16/2016.
 */
var servname = [];

$('#crServiceNameError').hide();

$.ajax({
    type: 'get',
    url: 'getServiceNames',
    dataType: 'json',
    async: true,
    success: function (data) {
        var servNames = data.names;
        for (var i = 0; i < servNames.length; i++) {
            servname.push(servNames[i]);
        }
    },
    error: function (data) {

    }
});

$('#crServiceName').on('input', function () {
    var $this = $(this).val(),
        count = 0;
    for (var i = 0; i < servname.length; i++) {
        if ($this.toLowerCase() == servname[i].toLowerCase()) {
            count += 1;
        }
    }

    if (count > 0) {
        $('#crServiceNameError').show();
        $('#crServiceBtn').prop('disabled', true).css('opacity', '0.3');
    } else {
        $('#crServiceNameError').hide();
        $('#crServiceBtn').prop('disabled', false).css('opacity', '1');
    }
});

function checkServiceNameAvailability(id) {
    var count = 0,
        n = [],
        na = [];
    $.ajax({
        type: 'get',
        url: 'getServiceNamesWithID',
        data: {
            'id': id
        },
        dataType: 'json',
        async: true,
        success: function (data) {
            var names = data.names;
            for(var i = 0; i < names.length; i ++) {
                na.push(names[i]);
            }
            n = na;
            na = [];
        },
        error: function (data) {
        }
    });

    $('#upServName' + id).on('input', function() {
        count = 0;
        $('#upServiceNameError' + id).hide();
        for(var i = 0; i < n.length; i ++) {
            if($('#upServName' + id).val().toLowerCase() == n[i].toLowerCase()){
                count += 1;
            }
        }

        if (count > 0) {
            $('#upServiceNameError' + id).show();
            $('.upServSubmitBtn').prop('disabled', true).css('opacity', '0.3');
        } else {
            $('#upServiceNameError' + id).hide();
            $('.upServSubmitBtn').prop('disabled', false).css('opacity', '1');
        }
    });
}