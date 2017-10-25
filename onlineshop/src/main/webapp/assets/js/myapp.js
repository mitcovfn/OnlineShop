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
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for jquery dataTable

	var $table = $('#productListTable');

	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table.DataTable({

			lengthMenu : [ [ 5, 10, 15, 25, -1 ],
					[ '5', '10', '15', '25', 'All' ] ],
			pageLength : 10,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns: [
				{
					data: 'code',
					mRender: function(data, type, row){
						
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="productimg">';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'unitPrice',
					mRender: function(data, type, row){
						return '&#36;' + data
					}
				},
				{
					data: 'quantity',
					mRender: function(data, type, row){
						if(data < 1 ){
							return '<span style="color:red"> Out of Stock!</span>';
						}
						return data;
					}
				},
				{
					data: 'id',
					dSortable: false,
					mRender: function(data, type, row){
						var str ='';
						str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
						
						if(row.quantity < 1){
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}else{
							str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						
						return str;
					}
				},
			]
		
		
		});
	}

});