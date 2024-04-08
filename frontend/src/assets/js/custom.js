function test()
{

    $(".regular").slick({
        // dots: true,
         infinite: true,
         slidesToShow: 4,
         slidesToScroll: 1,
     variableWidth: true
       });
     $(".regular-1").slick({
        // dots: true,
         infinite: true,
         slidesToShow: 4,
         slidesToScroll: 1,
     variableWidth: true
       });
     $(".blog-home1").slick({
        // dots: true,
         infinite: true,
         slidesToShow: 2,
         slidesToScroll: 1,
     variableWidth: true
       });
     
     $(".buy").slick({
        // dots: true,
         infinite: true,
     
         variableWidth: true
       });
       $('#showmenu').click(function() {
        $('.menu').slideToggle("fast");
});
 $('#showmenu1').click(function() {
        $('.menu1').slideToggle("fast");
});
 $('#showmenu2').click(function() {
        $('.menu2').slideToggle("fast");
});
 $('#showmenu3').click(function() {
        $('.menu3').slideToggle("fast");
});
 $('#showmenu4').click(function() {
        $('.menu4').slideToggle("fast");
});
 $('#showmenu5').click(function() {
        $('.menu5').slideToggle("fast");
});
 $('#showmenu6').click(function() {
        $('.menu6').slideToggle("fast");
});
 $('#showmenu7').click(function() {
        $('.menu7').slideToggle("fast");
});
$('#showmenu8').click(function() {
        $('.menu8').slideToggle("fast");
});
$('#showmenu9').click(function() {
        $('.menu9').slideToggle("fast");
});
$('#showmenu10').click(function() {
        $('.menu10').slideToggle("fast");
});
$(".progress").each(function() {
      
    var value = $(this).attr('data-value');
    var left = $(this).find('.progress-left .progress-bar');
    var right = $(this).find('.progress-right .progress-bar');

    if (value > 0) {
      if (value <= 55) {
        right.css('transform', 'rotate(' + percentageToDegrees(value) + 'deg)')
      } else {
        right.css('transform', 'rotate(180deg)')
        left.css('transform', 'rotate(' + percentageToDegrees(value - 55) + 'deg)')
      }
    }

  })
}
function percentageToDegrees(percentage) {
      
    return percentage / 100 * 360

  }