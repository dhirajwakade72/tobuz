//scroll
$( window ).scroll(function() {

		var height = $(window).scrollTop();

		if(height >= 50) {
			$('.fix-navbar').addClass('fixed-menu');
		} else {
			$('.fix-navbar').removeClass('fixed-menu');
		}

	});

	
	