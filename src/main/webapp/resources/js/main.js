$(document).ready(function() {
	$('a#nav-trigger').sidr({
		name: 'nav-panel'
	});

	$('a#search-trigger').sidr({
		name: 'search-panel',
		side: 'right'
	});


	$('nav#nav-panel > ul > li > a').click(function(event) {
		$(this).parent().toggleClass('current').siblings().removeClass('current');
	});
});



