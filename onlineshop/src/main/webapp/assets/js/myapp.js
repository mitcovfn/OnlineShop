/**
 * Solving active menu problem
 */
$(function() {

	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;

	case 'Contact Us':
		$('#contact').addClass('active');
		break;
		
	case 'All Products':
		$('#listallproducts').addClass('active');
		break;

	default:
		$('#a_'+menu).addClass('active');
		break;
	}
});