<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<p class="lead">Categories</p>
<div class="list-group">
	

	
	<c:forEach items="${categories}" var="category">
								<!-- show category 1 products
								show category 2 products ... -->
		<a href="${contextRoot }/show/category/${category.id}/products" class="list-group-item" id=a_${category.name} >${category.name }</a> 
	
	</c:forEach>
	
</div>