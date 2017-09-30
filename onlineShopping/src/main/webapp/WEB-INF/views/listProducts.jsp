<!-- This page will be further divided into 2 parts. One to display the category on the side and the other to display the data table -->

<div class="container">

	<div class="row">

		<!-- Would be to display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!-- to display the actual products -->
		<div class="col-md-9">
			<!-- added bread-crumb component -->
			<div class="row">
				<div class="col-lg-12">

					<c:if test="${userClickAllProducts == true}">

						<script>
							// if user is clicking all the products keep it empty
							window.categoryId = '';
						</script>

						<ol class="breadcrumb">

							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>

						</ol>
					</c:if>


					<c:if test="${userClickCategoryProducts == true}">

						<script>
							// if user is clicking on the particular category use the ${category.id}
							// and store it to another variable call the categoryId inside the window
							// global object which is available in JS
							window.categoryId = '${category.id}';
						</script>

						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>
						</ol>
					</c:if>


				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<table id="productListTable"
						class="table table-striped table-borderd">

						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Available Qty.</th>
								<th></th>

							</tr>
						</thead>

						<tfoot>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Available Qty.</th>
								<th></th>
							</tr>
						</tfoot>

					</table>
				</div>
			</div>

		</div>
	</div>
</div>
