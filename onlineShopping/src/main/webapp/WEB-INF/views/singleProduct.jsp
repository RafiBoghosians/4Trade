
<div class="container">

	<!-- For Designing we should use to functionality one for BreadCrumb the other for loading product detail-->

	<!-- Breadcrumb  -->
	<div class="row">
		<div class="col-xs-12">

			<ol class="breadcrumb">
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<!-- Using our EL expression we will get our "product" from our PageController  -->
				<li class="active">${product.name}</li>
			</ol>


		</div>
	</div>

	<!-- Display the product  -->
	<div class="row">

		<!-- Image of the Product  -->
		<div class="col-xs-12 col-sm-4">

			<div class="thumbnail">
				<img src="${images}/${product.code}.jpg" class="img img-responsive">
			</div>

		</div>

		<!-- Description of the Product  -->
		<div class="col-xs-12 col-sm-4"></div>

		<h3>${product.name }</h3>
		<hr />

		<p>${product.description }</p>
		<hr />

		<h4>
			Price : <strong> ${product.unitPrice }  &#1423;</strong>
		</h4>


		<h6>Available Quantity : ${product.quantity}</h6>

		<!-- 2 buttons, one is for add to cart the other is for going back  -->

		<a href="${contextRoot }/cart/add/${product.id}/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span> Add to Cart</a>
		
		<a href="${contextRoot }/show/all/products" class="btn btn-primary"><span class="glyphicon glyphicon-circle-arrow-left"></span> Back</a>




	</div>







</div>