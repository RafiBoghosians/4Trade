<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			Price : <strong> ${product.unitPrice } &#1423;</strong>
		</h4>


		<!-- 2 buttons, one is for add to cart the other is for going back  -->
		<!-- For Displaying The Message -->
		<c:choose>

			<c:when test="${product.quantity < 1}">
				<h6>

					Available Quantity : <span style="color: red">Out of Stock!</span>

				</h6>
			</c:when>
			<c:otherwise>

				<h6>Available Quantity : ${product.quantity}</h6>

			</c:otherwise>
		</c:choose>
		<!-- For Adding to Cart, if the quantity is less than 0, the add to cart button should be disabled -->
		<c:choose>

			<c:when test="${product.quantity < 1}">
				<h6>

					<a href="javascript:void(0)" class="btn btn-success disabled">
					<del><span class="glyphicon glyphicon-shopping-cart"></span>
					Add to Cart </del></a>
							
						

				</h6>
			</c:when>
			<c:otherwise>

				<a href="${contextRoot}/cart/add/${product.id}/product"
					class="btn btn-success"> <span
					class="glyphicon glyphicon-shopping-cart"></span> 
				Add to Cart</a>

			</c:otherwise>
		</c:choose>


		<a href="${contextRoot}/show/all/products" class="btn btn-primary">
			<span class="glyphicon glyphicon-circle-arrow-left"></span> Back
		</a>




	</div>







</div>