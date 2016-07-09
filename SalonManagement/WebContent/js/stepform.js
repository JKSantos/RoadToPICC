$(document).ready(function(){
    var current = 1;

    widget      = $(".step");
    btnback     = $(".backform");
    btnnext     = $(".nextform");
    btnsubmit   = $(".submitform");

    // Init buttons and UI
    widget.not(':eq(0)').hide();
    hideButtons(current);
    setProgress(current);

    // Next button click action
    btnnext.click(function(){

        if($('#createEmpForm').valid()){
            if(current < widget.length){
                widget.show();
                widget.not(':eq('+(current++)+')').hide();
                setProgress(current);
            }
            hideButtons(current);
        }
    });
    // Back button click action
    btnback.click(function(){
        if(current > 1){
            current = current - 2;
            if(current < widget.length){
                widget.show();
                widget.not(':eq('+(current++)+')').hide();
                setProgress(current);
            }
        }
        hideButtons(current);
    });

    // btnsubmit.submit(function(){
    //    $('#createEmpForm').submit(function(){
    //       alert("submitted");
    //    });
    // });
});

// Change progress bar action
setProgress = function(currstep){
    var percent = parseFloat(100 / widget.length) * currstep;
    percent = percent.toFixed();
    $(".determinate")
        .css("width",percent+"%")
        .html(percent+"%").css("font-size", "15px");
}

// Hide buttons according to the current step
hideButtons = function(current){
    var limit = parseInt(widget.length);

    $(".action").hide();

    if(current < limit) btnnext.show();
    if (current == limit) { btnnext.hide(); btnsubmit.show(); }
    if(current > 1) btnback.show();
}