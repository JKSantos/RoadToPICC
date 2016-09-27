$(document).ready(function(){
    $("#servicebtn").click(function(){
        $("#label").append('<b>appended text</b>');
    });

    $("#btn2").click(function(){
        $("ol").append("<li>Appended item</li>");
    });
});
