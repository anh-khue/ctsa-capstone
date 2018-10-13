$(function () {
    var offset = $("#sidebar").offset();
    var topPadding = 50;
    stopPosition = $('#morejob').offset().top - $("#sidebar").outerHeight();
    $(window).scroll(function () {
        console.log(stopPosition);
        if ($(window).scrollTop() < stopPosition) {
            $("#sidebar").stop().animate({
                marginTop: $(window).scrollTop() - offset.top + topPadding
            });
            
        } 
        else if($(window).scrollTop() > stopPosition){
            $("#sidebar").stop().animate({
                marginTop: 50
            });
        };
    });
});

