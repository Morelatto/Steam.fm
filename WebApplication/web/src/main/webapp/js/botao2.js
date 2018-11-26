$(document).ready(function () {

    $('#cta-buttons-wrapper').css('position', 'fixed');
    var docked = false;

    $(window).scroll(function () {
        //console.log('footer offset='+$('#footer').offset().top);
        //console.log('window bottom='+($(window).scrollTop() + $(window).height()));
        if ($(window).scrollTop() + $(window).height() >= $('#footer').offset().top) {
            if (!docked) {
                $('#cta-buttons-wrapper').css('position', 'absolute');
                docked = true;
            }
        } else {
            if (docked) {
                $('#cta-buttons-wrapper').css('position', 'fixed');
                docked = false;
            }
        }
    });

});