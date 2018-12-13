$(function () {
    let sidebar = $('#sidebar')
    let offset = $(".sidebar").offset();
    let topPadding = 120;
    // stopPosition = $('#morejob').offset().top - $(".sidebar").outerHeight();
    $(window).scroll(function () {
        // console.log(stopPosition);
        if ($(window).scrollTop() > offset.top) {
            $(".sidebar").stop().animate({
                marginTop: $(window).scrollTop() - offset.top + topPadding
            });

        }
        else if ($(window).scrollTop() > stopPosition) {
            $(".sidebar").stop().animate({
                marginTop: 50
            })
        }
    })
})

