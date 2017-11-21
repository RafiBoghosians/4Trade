<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<div class="row">
	
	
	<c:if test="${not empty message}">
	
		<div class="col-xs-12">
			
			<div class="alert alert-success alert-dismissible">
			
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				
				${message}
			
			</div>
	
		</div>
	
	</c:if>
		
		
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>
				<div class="panel-body">
					<!-- The elements of FORM -->
					<!-- The name of modelAttribute is based of ManagementController class, product attributeName-->
					<!-- modelAttribute convert usual form in to Spring Form -->
					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products"
						method="POST"
						enctype="multipart/form-data"
						>
						
						<!-- name -->	
							<div class="form-group">
								<label class="control-label col-md-4" for="name">Enter Product Name: </label>
								<div class="col-md-8">
									<!-- path="name" should belong to the field shoppingBackend/dto/Product.java -->
									<sf:input type="text" path="name" id="name" placeholder="Product Name" class="form-control" />
									<!-- <em class="help-block">Please Enter Product Name!</em> -->
									<sf:errors path="name" cssClass="help-block" element="em"/>
								</div>
							</div>
							
						<!-- brand -->
							<div class="form-group">
								<label class="control-label col-md-4" for="brand">Enter Product Brand: </label>
								<div class="col-md-8">
									<sf:input type="text" path="brand" id="brand" placeholder="Brand Name" class="form-control" />
									<!-- <em class="help-block">Please Enter Product Brand!</em> -->
									<sf:errors path="brand" cssClass="help-block" element="em"/>
								</div>
							</div>
							
						<!-- Product Description -->
							<div class="form-group">
								<label class="control-label col-md-4" for="description">Product Description: </label>
								<div class="col-md-8">
									<sf:textarea path="description" id="description" rows="4" placeholder="Write a description" class="form-control" />	
									<!-- <em class="help-block">Please write Product Description!</em> -->
									<sf:errors path="description" cssClass="help-block" element="em"/>
								</div>
							</div>
						
						<!-- Product Price -->
							<div class="form-group">
								<label class="control-label col-md-4" for="unitPrice">Enter Unit Price: </label>
								<div class="col-md-8">
									<sf:input type="number" path="unitPrice" id="unitPrice"  placeholder="Unit Price" class="form-control" />
									<sf:errors path="unitPrice" cssClass="help-block" element="em"/>
								</div>
							</div>
							
						<!-- Product Quantity -->
							<div class="form-group">
								<label class="control-label col-md-4" for="quantity">Enter Available Quantity: </label>
								<div class="col-md-8">
									<sf:input type="number" path="quantity" id="quantity"  placeholder="Available Quantity" class="form-control" />
								</div>
							</div>
							
						<!-- File element for image upload -->
						<div class="form-group">
							<label class="control-label col-md-4" for="file">Select an Image: </label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>
							
						<!-- Category Selection -->
							<div class="form-group">
								<label class="control-label col-md-4" for="categoryId">Select Category: </label>
								<div class="col-md-8">
								
										<!-- items="${categories}" -->
										<!-- the same as ManagementController => @ModelAttribute("categories") -->
										<!-- items are coming from controller @link ManagementController -->
										
										<!-- itemLable="name" -->
										<!-- itemLable is text what we want to display from Category which is name of the category in our case -->
										
										<!-- itemValue="id" -->
										<!-- we want to store the id of that particular category -->
										<!-- and this "id" will link with path="categoryId" -->
										<!-- itemValue is the value which value i want to use on select -->
									<sf:select class="form-control" id="categoryId" path="categoryId"
									 items="${categories}" 
									 itemLabel="name"
									 itemValue="id">
									</sf:select>
									
									<!-- Add Category button -->	
									<c:if test="${product.id == 0}">
										<div class="text-right">
											<br/>
											<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
										</div>
									</c:if>
								
								</div>
							</div>
							
						<!-- submission button  -->
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary" />
								
								<!-- Hidden fields for products  -->
								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="active" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />
							
							</div>
						</div>

					</sf:form>

				</div>

			</div>

		</div>

	</div>








	<div class="row">
		
		<div class="col-xs-12">
			<h3>Available Products</h3>
			<hr/>		
		</div>	
		
		<div class="col-xs-12">
		
			<div style="overflow: auto;">
			
	<!-- Products table for admin -->
	<table id="adminProductsTable" class="table table-striped table-bordered">		
				
	<thead>
		<tr>
			<th>Id</th>
			<th>&#160;</th>
			<th>Name</th>
			<th>Brand</th>
			<th>Quantity</th>
			<th>Unit Price</th>
			<th>Active</th>
			<th>Edit</th>
		</tr>
	</thead>
				
	
				
	<tfoot>
		<tr>
			<th>Id</th>
			<th>&#160;</th>
			<th>Name</th>
			<th>Brand</th>
			<th>Quantity</th>
			<th>Unit Price</th>
			<th>Active</th>
			<th>Edit</th>
		</tr>
	</tfoot>
</table>
				
			</div>
			
		</div>
		
	
	</div>


	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex=-1>
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title">Add new Category</h4>
				</div>
				<div class="modal-body">
					<!-- Category Form  -->
					<sf:form id="categoryForm" modelAttribute="category" action="${contextRoot }/manage/category" 
					 method="POST" class="form-horizontal">
						
						<div class="form-group">
							<label for="category_name" class="control-label col-md-4">Category Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name" class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							<label for="category_description" class="control-label col-md-4">Category Description</label>
							<div class="col-md-8">
								<sf:textarea cols="" rows="5" path="description" id="category_description" class="form-control" />
							</div>
						</div>
						
						<div class="form-group">
							
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category" class="btn btn-primary"/>
							</div>
						</div>
						
					
					</sf:form>
				</div>
			</div>
		</div>
	</div>



</div>
