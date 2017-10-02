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
					[ '3 Records', '5 Records', '10 Records', 'All' ] ],
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
				mRender: function(data, type, row) {
					return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
				}
			},
				
				{
				data : 'name'
			}, {
				data : 'brand'
			}, {
				data : 'unitPrice',
				mRender : function(data, type, row) {
					// HTML Currency Symbols for Armenian dram
					return data + ' &#1423;'
				}
			}, {
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
						
					}else{
						
						str += '<a href="'+window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
					}
					
					
					
					return str;
				}
			}

			]
		});
	}

});
