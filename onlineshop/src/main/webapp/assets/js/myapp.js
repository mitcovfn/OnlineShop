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

	case 'Manage Products':
		$('#manageProducts').addClass('active');
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
			jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
		}

		$table
				.DataTable({

					lengthMenu : [ [ 5, 10, 15, 25, -1 ], [ '5', '10', '15', '25', 'All' ] ],
					pageLength : 10,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot + '/resources/images/' + data
											+ '.jpg" class="productimg">';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#36;' + data
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red"> Out of Stock!</span>';
									}
									return data;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									} else {
										str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									}

									return str;
								}
							}, ]

				});
	}

	// dismissing the alert after 3 seconds
	var $alert = $('.alert');

	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
	}

	// data table for ADMIN--
	var $adminProductsTable = $('#adminProductTable');

	if ($adminProductsTable.length) {

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProductsTable.DataTable({

			lengthMenu : [ [ 10, 30, 50, -1 ], [ '10', '30', '50', 'All' ] ],
			pageLength : 30,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [
					{
						data : 'id'
					},
					{
						data : 'code',
						mRender : function(data, type, row) {

							return '<img src="' + window.contextRoot + '/resources/images/' + data
									+ '.jpg" class="adminDataTableImg">';
						}
					},
					{
						data : 'name'
					},
					{
						data : 'brand'
					},
					{
						data : 'quantity',
						mRender : function(data, type, row) {
							if (data < 1) {
								return '<span style="color:red"> Out of Stock!</span>';
							}
							return data;
						}
					},
					{
						data : 'unitPrice',
						mRender : function(data, type, row) {
							return '&#36;' + data
						}
					},
					{
						data : 'active',
						bSortable : false,
						mRender : function(data, type, row) {

							var str = '';
							str += '<label class="switch">';
							if (data) {
								str += '<input type="checkbox" checked="checked" value="' + row.id + '">';
							} else {
								str += '<input type="checkbox" value="' + row.id + '">';
							}

							str += '<div class="slider"></div>';
							str += '</label>';
							return str;
						}
					},
					{
						data : 'id',
						bSortable : false,
						mRender : function(data, type, row) {
							var url = '';
							url += '<a href="' + window.contextRoot + '/manage/' + data
									+ '/product" class="btn btn-warning">';
							url += '<span class="glyphicon glyphicon-pencil"></span></a>';
							return url;
						}
					} ],

			// for activating and diactivating product
			initComplete : function() {

				var api = this.api();
				api.$('.switch input[type="checkbox"]').on('change', function() {

					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked) ? 'You want to activate the product?' : 'You want to deactivate the product?';

					var value = checkbox.prop('value');

					bootbox.confirm({
						size : 'medium',
						title : 'Product activation & deactivation',
						message : dMsg,
						callback : function(confirmed) {

							if (confirmed) {

								var activationUrl = window.contextRoot + '/manage/product/' + value + '/activation';
								$.post(activationUrl, function(data) {
									bootbox.alert({
										size : 'medium',
										title : 'Information',
										message : data
									});
								});

							} else {
								checkbox.prop('checked', !checked);
							}
						}
					});
				});

			}

		});
	}

});