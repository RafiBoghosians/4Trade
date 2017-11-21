$(function() {
	// solving the active menu problem
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	default:
		if (menu === "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for JQuery dataTable

	var $table = $('#productListTable');

	//	This code only will be executed if we have the table inside our page

	if ($table.length) {
		//console.log('Inside the table!');

		var jsonUrl = '';
		// if it is empty it means user is asking for all the products
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else { // this Id is dynamic that we are added in the listProduct.jsp
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table.DataTable({
			// the first array indicates how many records we want to display[3,5,10,-1]
			//-1 indicates we want to display all the records otherwise display 3,5,10
			// the second array indicates the String that we want to display
			lengthMenu : [ [ 3, 5, 10, -1 ],
					[ '3 Rec', '5 Rec', '10 Rec', 'All' ] ],
			//pageLength indicates how many records it should be displayed by defaults
			pageLength : 5,
			ajax : {
				url : jsonUrl,
				//this is a collection of object without any name, that is why we keep this empty
				dataSrc : ''
			},
			columns : [
			//content of data: ... should be the same as the RESTClient object variable names
						{
							// we use this code and store images with .jpg extension
							data: 'code',
							bSortable: false,
							mRender: function(data, type, row) {
								return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
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
								// HTML Currency Symbols for Armenian dram
								return data + ' &#1423;'
						}
					},
					{
							data : 'quantity',
							mRender: function(data, type, row) {
					
								if(data < 1) {
									return '<span style="color:red;">Out of Stock! </span>'; 
								} 
					
								return data;
					
							}
					},
					{
						data : 'id',
						bSortable: false,	
						mRender: function(data, type, row) {
					
							var str = '';
							str += '<a href="'+window.contextRoot+ '/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
					
							if(row.quantity < 1) {	
								str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}
							else{
						
								str += '<a href="'+window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}
					
					
							return str;
						}
					}
					]
		});
	}

	//dismissing the alert after 3 seconds
	var $alert = $('.alert');
	
	if($alert.length) {
		
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
		
	}
	
	// -----------------------------------
	
	$('.switch input[type="checkbox"]').on('change', function() {

				// alert('entering function');
				var checkbox = $(this);
				// check if the checkbox is checked returns true or false
				var checked = checkbox.prop('checked');
				var dMsg = (checked) ? 'Do you want to activate the product?'
						: 'Do you want to deactivate the product?';
				var value = checkbox.prop('value');

				bootbox.confirm({
							size : 'medium',
							title : 'Product Activation & Deactivation',
							message : dMsg,

							callback : function(confirmed) {
								if (confirmed) {

									bootbox
											.alert({
												size : 'medium',
												title : 'Information',
												message : 'You are going to perform operation on product'
														+ value
											});

								} else {
									checkbox.prop('checked', !checked);
								}
							}
						});
			});
	
	// --------------------
	// data table for admin
	// --------------------

	var $adminProductsTable = $('#adminProductsTable');

	// execute the below code only where we have this table
	if ($adminProductsTable.length) {
		// console.log('Inside the table !');

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		
	$adminProductsTable.DataTable({

					lengthMenu : [
							[ 10, 30, 50, -1 ],['10 Records', '30 Records', '50 Records', 'ALL']],
					pageLength : 30,
					
					// data: products
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
								bSortable: false,
								mRender : function(data, type, row) {
									// css class customDataTableImg is not working in chrome, so we added custom css property class = "customDataTableImg"
									var cssProp = 'style="border: 1px solid #ddd; border-radius: 50%; padding: 5px;	box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);	width: 100px; height: 100px;"';
									return '<img class="adminDataTableImg" src="'+ window.contextRoot+ '/resources/images/'+ data+ '.jpg" />';		
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
										return '<span style="color:red">Out of Stock</span>';
									}
									return data;
								}

							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return ' &#1423; ' + data;  
								}

							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<label class="switch">';
									if (data)// if active

										str += '<input type="checkbox" checked="checked" value="'+ row.id + '" />';
												
									else
										str += '<input type="checkbox" value="'+ row.id + '" />';
									
									str += '<div class="slider" ></div>	</label>';

									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';

									str += '<a href="'+ window.contextRoot+ '/manage/'+ data + '/product" class="btn btn-warning" >';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';

									return str;
								}
							} ],
					initComplete: function() {

						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change', function() {
											// alert('entering function');
											var checkbox = $(this);
											// check if the checkbox is checked returns true or false
											var checked = checkbox.prop('checked');
											var dMsg = (checked) ? 'Do you want to activate the product?' :
																	'Do you want to deactivate the product?';
											var value = checkbox.prop('value');
											bootbox.confirm({
														size : 'medium',
														title : 'Product Activation & Deactivation',
														message : dMsg,
														//blub
														callback : function(confirmed) {
															if (confirmed) {
																console.log(value);
																var activationUrl = window.contextRoot + '/manage/product/'	+ value + '/activation';
																$.post(activationUrl, function(data) {
																bootbox.alert({
																			size : 'medium',
																			title : 'Information',
																			message : data
																		});
																	});
															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													});

										});
					}

				});

	}

	// --------------------------
	//validation code for Category(Manage Products)

	var $categoryForm = $('#categoryForm');

	if ($categoryForm.length) {
		$categoryForm.validate({

			rules : {
				name : {
					required : true,
					minlength : 2
				},
				description : {
					required : true
				}
			},
			messages : {
				name : {
					required : 'Please add the category name',
					minlength : 'The category name should not be less than 2 characters!'
				},
				description : {
					required : 'Please add a description for this category!'
				}

			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				//add the class of help-block
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}

		});
	}

	// ------------------------------
	
	
	
	
});
