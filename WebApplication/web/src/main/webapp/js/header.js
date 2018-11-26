$(function () {
    var header = $("header"),
        yOffset = 0,
        triggerPoint = 150;

    $(window).scroll(function () {
        yOffset = $(window).scrollTop();

        if (yOffset >= triggerPoint) {
            header.addClass("minimized");
            $('.board').css("marginTop", "-25px");
            $('#error').css("marginLeft", "0px");
            $('#code').css("marginLeft", "250px");
            $('.board').css("font", "60px/95px Monoton, cursive");
            $('.menu-section.on').css("marginTop", "-50px");
        } else {
            header.removeClass("minimized");
            $('.board').css("marginTop", "0px");
            $('#code').css("marginLeft", "400px");
            $('.board').css("font", "90px/95px Monoton, cursive");
            $('.menu-section.on').css("marginTop", "0px");
        }

    });
});