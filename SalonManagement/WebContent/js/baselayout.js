/**
 * Created by Castillo on 8/2/2016.
 */


function setBirthDate(id, date) {

    var monthNames = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ];

    var from = date.split("-");
    var dat = new Date(Date.parse(date));
    var d = monthNames[dat.getMonth()] + "/" + from[2] + "/" + from[0];
    $(id).val(d);

    var dob2 = new Date(d);
    var today2 = new Date();
    var age2 = today2.getTime() - dob2.getTime();
    age2 = Math.floor(age2 / (1000 * 60 * 60 * 24 * 365.25));

}

$(document).ready(function () {
    $(".button-collapse").sideNav();
    $(".dropdown-button").dropdown();

//        $('#emppics').removeClass('sor');
//        $('#emppics').css('background-image','none');
});

$(document).ready(function () {
    $('select').material_select();
    $("select[required]").css({display: "inline", height: 0, padding: 0, width: 0});
});

$('.dropify').dropify({
    messages: {
        'error': 'Oops, something wrong appended.'
    }
});

$('#createContact').formatter({
    'pattern': '09{{99}}-{{999}}-{{9999}}',
    'persistent': true
});

$('.updateContact').each(function () {
    $(this).formatter({
        'pattern': '09{{99}}-{{999}}-{{9999}}',
        'persistent': true
    });
});

$('ul.tabs').tabs();

$(document).ready(function () {
    $('.tooltipped').tooltip({delay: 5});
});

$(document).ready(function () {
    setTimeout(function () {
        $('body').addClass('loaded');
    }, 800);
});

$('#crItemPrice').priceFormat({
    prefix: 'Php ',
    centsSeparator: '.',
    thousandsSeparator: ',',
    limit: 9
});

$('#ItemPrice').priceFormat({
    prefix: 'Php ',
    centsSeparator: '.',
    thousandsSeparator: ',',
    limit: 9
});


$('.upProdItemPrice').each(function () {
    $(this).priceFormat({
        prefix: 'Php ',
        centsSeparator: '.',
        thousandsSeparator: ',',
        limit: 9
    });
});


$('.prodPrice').priceFormat({
    prefix: 'Php ',
    centsLimit: 2,
    centsSeparator: '.',
    thousandsSeparator: ',',
    limit: 9
});

$('.servPrice').priceFormat({
    prefix: 'Php ',
    centsSeparator: '.',
    thousandsSeparator: ',',
    limit: 9
});

$('#crPackPrice').priceFormat({
    prefix: 'Php ',
    centsSeparator: '.',
    thousandsSeparator: ',',
    limit: 9
});

$('#crServicePrice').priceFormat({
    prefix: 'Php ',
    centsSeparator: '.',
    thousandsSeparator: ',',
    limit: 9
});

$('.timepicker').pickatime({
    autoclose: true,
    twelvehour: true
});

