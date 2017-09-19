$(function() {
	// solving the active menu problem
	switch(menu){
	
	case 'About Us':
		$('#about').addClass('active');
		breake;
	case 'Contact Us':
		$('#contact').addClass('active');
		breake;
	default:
		$('#home').addClass('active');
		breake;
	
	}
});