$(function() {
	// solving the active menu problem
	switch(menu){
	
	case 'About Us':
		$('#about').addClass('active');
		breake;
	case 'Contact Us':
		$('#contact').addClass('active');
		breake;
	case 'All Products':
		$('#listProducts').addClass('active');
		breake;
	default:
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
		 
	
	}
});