$(document).ready(function(){
    var current = 1;

    empwidget      = $("#createEmpForm .step");
    empbtnback     = $("#createEmpForm .backform");
    empbtnnext     = $("#createEmpForm .nextform");
    empbtnsubmit   = $("#createEmpForm .submitform");

    // Init buttons and UI
    empwidget.not(':eq(0)').hide();
    hideButtons(current);
    setProgress(current);

    // Next button click action
    empbtnnext.click(function(){

        if($('#createEmpForm').valid()){
            if(current < empwidget.length){
                empwidget.show();
                empwidget.not(':eq('+(current++)+')').hide();
                setProgress(current);
            }
            hideButtons(current);
        }
    });
    // Back button click action
    empbtnback.click(function(){
        if(current > 1){
            current = current - 2;
            if(current < empwidget.length){
                empwidget.show();
                empwidget.not(':eq('+(current++)+')').hide();
                setProgress(current);
            }
        }
        hideButtons(current);
    });

    // empbtnsubmit.submit(function(){
    //    $('#createEmpForm').submit(function(){
    //       alert("submitted");
    //    });
    // });

});

setProgress = function(currstep){
    var percent = parseFloat(100 / empwidget.length) * currstep;
    percent = percent.toFixed();
    $("#createEmpForm .determinate")
        .css("width",percent+"%")
        .html(percent+"%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtons = function(current){
    var limit = parseInt(empwidget.length);

    $("#createEmpForm .action").hide();

    if(current < limit) empbtnnext.show();
    if (current == limit) { empbtnnext.hide(); empbtnsubmit.show(); }
    if(current > 1) empbtnback.show();
}
// Change progress bar action


//package
$(document).ready(function(){
    var currentPack = 1;

    packwidget      = $(".steppack");
    packbtnback     = $(".backformpack");
    packbtnnext     = $(".nextformpack");
    packbtnsubmit   = $(".submitformpack");

    // Init buttons and UI
    packwidget.not(':eq(0)').hide();
    hideButtonsPack(currentPack);
    setProgressPack(currentPack);

    // Next button click action
    packbtnnext.click(function(){

        if($('#createPackageForm').valid()){
            if(currentPack < packwidget.length){
                packwidget.show();
                packwidget.not(':eq('+(currentPack++)+')').hide();
                setProgressPack(currentPack);
            }
            hideButtonsPack(currentPack);
        }
    });
    // Back button click action
    packbtnback.click(function(){
        if(currentPack > 1){
            currentPack = currentPack - 2;
            if(currentPack < packwidget.length){
                packwidget.show();
                packwidget.not(':eq('+(currentPack++)+')').hide();
                setProgressPack(currentPack);
            }
        }
        hideButtonsPack(currentPack);
    });

    // packbtnsubmit.submit(function(){
    //    $('#createPackageForm').submit(function(){
    //       alert("submitted");
    //    });
    // });

});

setProgressPack = function(currstepPack){
    var percent = parseFloat(100 / packwidget.length) * currstepPack;
    percent = percent.toFixed();
    $("#createPackageForm .determinate")
        .css("width",percent+"%")
        .html(percent+"%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtonsPack = function(currentPack){
    var limit = parseInt(packwidget.length);

    $(".actionpack").hide();

    if(currentPack < limit) packbtnnext.show();
    if (currentPack == limit) { packbtnnext.hide(); packbtnsubmit.show(); }
    if(currentPack > 1) packbtnback.show();
}

//package end


//product sale
$(document).ready(function(){
    var currentPS = 1;

    pswidget      = $(".stepps");
    psbtnback     = $(".backformps");
    psbtnnext     = $(".nextformps");
    psbtnsubmit   = $(".submitformps");

    // Init buttons and UI
    pswidget.not(':eq(0)').hide();
    hideButtonsPS(currentPS);
    setProgressPS(currentPS);

    // Next button click action
    psbtnnext.click(function(){

        if($('#createPSForm').valid()){
            if(currentPS < pswidget.length){
                pswidget.show();
                pswidget.not(':eq('+(currentPS++)+')').hide();
                setProgressPS(currentPS);
            }
            hideButtonsPS(currentPS);
        }
    });
    // Back button click action
    psbtnback.click(function(){
        if(currentPS > 1){
            currentPS = currentPS - 2;
            if(currentPS < pswidget.length){
                pswidget.show();
                pswidget.not(':eq('+(currentPS++)+')').hide();
                setProgressPS(currentPS);
            }
        }
        hideButtonsPS(currentPS);
    });

    // packbtnsubmit.submit(function(){
    //    $('#createPackageForm').submit(function(){
    //       alert("submitted");
    //    });
    // });

});

setProgressPS = function(currstepPS){
    var percent = parseFloat(100 / pswidget.length) * currstepPS;
    percent = percent.toFixed();
    $("#createPSForm .determinate")
        .css("width",percent+"%")
        .html(percent+"%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtonsPS = function(currentPS){
    var limit = parseInt(pswidget.length);

    $(".actionps").hide();

    if(currentPS < limit) psbtnnext.show();
    if (currentPS == limit) { psbtnnext.hide(); psbtnsubmit.show(); }
    if(currentPS > 1) psbtnback.show();
}
//product sale

//promo
$(document).ready(function(){
    var currentPromo = 1;

    promowidget      = $(".steppromo");
    promobtnback     = $(".backformpromo");
    promobtnnext     = $(".nextformpromo");
    promobtnsubmit   = $(".submitformpromo");

    // Init buttons and UI
    promowidget.not(':eq(0)').hide();
    hideButtonsPromo(currentPromo);
    setProgressPromo(currentPromo);

    // Next button click action
    promobtnnext.click(function(){

        if($('#createPromoForm').valid()){
            if(currentPromo < promowidget.length){
                promowidget.show();
                promowidget.not(':eq('+(currentPromo++)+')').hide();
                setProgressPromo(currentPromo);
            }
            hideButtonsPromo(currentPromo);
        }
    });
    // Back button click action
    promobtnback.click(function(){
        if(currentPromo > 1){
            currentPromo = currentPromo - 2;
            if(currentPromo < promowidget.length){
                promowidget.show();
                promowidget.not(':eq('+(currentPromo++)+')').hide();
                setProgressPromo(currentPromo);
            }
        }
        hideButtonsPromo(currentPromo);
    });

    // promobtnsubmit.submit(function(){
    //    $('#createPromoForm').submit(function(){
    //       alert("submitted");
    //    });
    // });

});

setProgressPromo = function(currstepPromo){
    var percent = parseFloat(100 / promowidget.length) * currstepPromo;
    percent = percent.toFixed();
    $("#createPromoForm .determinate")
        .css("width",percent+"%")
        .html(percent+"%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtonsPromo = function(currentPromo){
    var limit = parseInt(promowidget.length);

    $(".actionpromo").hide();

    if(currentPromo < limit) promobtnnext.show();
    if (currentPromo == limit) { promobtnnext.hide(); promobtnsubmit.show(); }
    if(currentPromo > 1) promobtnback.show();
}
//promo

//discount
$(document).ready(function(){
    var currentDisc = 1;

    discwidget      = $(".stepdiscount");
    discbtnback     = $(".backformdiscount");
    discbtnnext     = $(".nextformdiscount");
    discbtnsubmit   = $(".submitformdiscount");

    // Init buttons and UI
    discwidget.not(':eq(0)').hide();
    hideButtonsDisc(currentDisc);
    setProgressDisc(currentDisc);

    // Next button click action
    discbtnnext.click(function(){

        if($('#createDiscountForm').valid()){
            if(currentDisc < discwidget.length){
                discwidget.show();
                discwidget.not(':eq('+(currentDisc++)+')').hide();
                setProgressDisc(currentDisc);
            }
            hideButtonsDisc(currentDisc);
        }
    });
    // Back button click action
    discbtnback.click(function(){
        if(currentDisc > 1){
            currentDisc = currentDisc - 2;
            if(currentDisc < discwidget.length){
                discwidget.show();
                discwidget.not(':eq('+(currentDisc++)+')').hide();
                setProgressDisc(currentDisc);
            }
        }
        hideButtonsDisc(currentDisc);
    });

    // discbtnsubmit.submit(function(){
    //    $('#createPromoForm').submit(function(){
    //       alert("submitted");
    //    });
    // });

});

setProgressDisc = function(currstepDisc){
    var percent = parseFloat(100 / discwidget.length) * currstepDisc;
    percent = percent.toFixed();
    $("#createDiscountForm .determinate")
        .css("width",percent+"%")
        .html(percent+"%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtonsDisc = function(currentDisc){
    var limit = parseInt(discwidget.length);

    $(".actiondiscount").hide();

    if(currentDisc < limit) discbtnnext.show();
    if (currentDisc == limit) { discbtnnext.hide(); discbtnsubmit.show(); }
    if(currentDisc > 1) discbtnback.show();
}
//discount

//reservation
$(document).ready(function(){
    var currentReserve = 1;

    reservewidget      = $(".stepreservation");
    reservebtnback     = $(".backformreserve");
    reservebtnnext     = $(".nextformreserve");
    reservebtnsubmit   = $(".submitformreserve");

    // Init buttons and UI
    reservewidget.not(':eq(0)').hide();
    hideButtonsReserve(currentReserve);
    setProgressReserve(currentReserve);

    // Next button click action
    reservebtnnext.click(function(){

        if($('#createReservationForm').valid()){
            if(currentReserve < reservewidget.length){
                reservewidget.show();
                reservewidget.not(':eq('+(currentReserve++)+')').hide();
                setProgressReserve(currentReserve);
            }
            hideButtonsReserve(currentReserve);
        }
    });
    // Back button click action
    reservebtnback.click(function(){
        if(currentReserve > 1){
            currentReserve = currentReserve - 2;
            if(currentReserve < reservewidget.length){
                reservewidget.show();
                reservewidget.not(':eq('+(currentReserve++)+')').hide();
                setProgressReserve(currentReserve);
            }
        }
        hideButtonsReserve(currentReserve);
    });

    // reservebtnsubmit.submit(function(){
    //    $('#createPromoForm').submit(function(){
    //       alert("submitted");
    //    });
    // });

});

setProgressReserve = function(currstepReserve){
    var percent = parseFloat(100 / reservewidget.length) * currstepReserve;
    percent = percent.toFixed();
    $("#createReservationForm .determinate")
        .css("width",percent+"%")
        .html(percent+"%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtonsReserve = function(currentReserve){
    var limit = parseInt(reservewidget.length);

    $(".actionreserve").hide();

    if(currentReserve < limit) reservebtnnext.show();
    if (currentReserve == limit) { reservebtnnext.hide(); reservebtnsubmit.show(); }
    if(currentReserve > 1) reservebtnback.show();
}
//end reservation


//walkin
$(document).ready(function(){
    var currentWalkin = 1;

    walkinwidget      = $(".stepwalkin");
    walkinbtnback     = $(".backformwalkin");
    walkinbtnnext     = $(".nextformwalkin");
    walkinbtnsubmit   = $(".submitformwalkin");

    // Init buttons and UI
    walkinwidget.not(':eq(0)').hide();
    hideButtonsWalkin(currentWalkin);
    setProgressWalkin(currentWalkin);

    // Next button click action
    walkinbtnnext.click(function(){

        if($('#createWalkinForm').valid()){
            if(currentWalkin < walkinwidget.length){
                walkinwidget.show();
                walkinwidget.not(':eq('+(currentWalkin++)+')').hide();
                setProgressWalkin(currentWalkin);
            }
            hideButtonsWalkin(currentWalkin);
        }
    });
    // Back button click action
    walkinbtnback.click(function(){
        if(currentWalkin > 1){
            currentWalkin = currentWalkin - 2;
            if(currentWalkin < walkinwidget.length){
                walkinwidget.show();
                walkinwidget.not(':eq('+(currentWalkin++)+')').hide();
                setProgressWalkin(currentWalkin);
            }
        }
        hideButtonsWalkin(currentWalkin);
    });

    // reservebtnsubmit.submit(function(){
    //    $('#createPromoForm').submit(function(){
    //       alert("submitted");
    //    });
    // });

});

setProgressWalkin = function(currstepWalkin){
    var percent = parseFloat(100 / walkinwidget.length) * currstepWalkin;
    percent = percent.toFixed();
    $("#createWalkinForm .determinate")
        .css("width",percent+"%")
        .html(percent+"%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtonsWalkin = function(currentWalkin){
    var limit = parseInt(walkinwidget.length);

    $(".actionwalkin").hide();

    if(currentWalkin < limit) walkinbtnnext.show();
    if (currentWalkin == limit) { walkinbtnnext.hide(); walkinbtnsubmit.show(); }
    if(currentWalkin > 1) walkinbtnback.show();
}
//end walkin