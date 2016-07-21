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