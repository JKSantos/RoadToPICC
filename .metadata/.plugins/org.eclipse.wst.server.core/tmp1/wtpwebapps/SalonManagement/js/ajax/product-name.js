/**
 * Created by Castillo on 9/16/2016.
 */
var prodname = [];

$('#crItemNameError').hide();

$.ajax({
    type: 'get',
    url: 'getProductNames',
    dataType: 'json',
    async: true,
    success: function (data) {
        var prodNames = data.names;
        for (var i = 0; i < prodNames.length; i++) {
            prodname.push(prodNames[i]);
        }
    },
    error: function (data) {

    }
});

$('#crItemName').on('input', function () {
    var $this = $(this).val(),
        count = 0;
    for (var i = 0; i < prodname.length; i++) {
        if ($this.toLowerCase() == prodname[i].toLowerCase()) {
            count += 1;
        }
    }

    if (count > 0) {
        $('#crItemNameError').show();
        $('#btnCreateProduct').prop('disabled', true).css('opacity', '0.3');
    } else {
        $('#crItemNameError').hide();
        $('#btnCreateProduct').prop('disabled', false).css('opacity', '1');
    }
});

function checkNameAvailability(id) {
    var count = 0,
        n = [],
        na = [];
    $.ajax({
        type: 'get',
        url: 'getProductNamesWithID',
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
            console.log(data);
        }
    });

    $('#upItemName' + id).on('input', function() {
        count = 0;
       for(var i = 0; i < n.length; i ++) {
           if($('#upItemName' + id).val().toLowerCase() == n[i].toLowerCase()){
               count += 1;
           }
       }

        console.log(count);

        if (count > 0) {
            $('#upItemNameError' + id).show();
            $('#btnUpdateID' + id).prop('disabled', true).css('opacity', '0.3');
        } else {
            $('#upItemNameError' + id).hide();
            $('#btnUpdateID' + id).prop('disabled', false).css('opacity', '1');
        }
    });
}