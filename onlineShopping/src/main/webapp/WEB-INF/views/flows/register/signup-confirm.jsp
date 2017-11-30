<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp" %>

<div class="container">
	<div class="row">
		<!-- columns for Display The personal details -->
		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					
					<!-- Confirmation page has 2 column(Personal Details and Billing Address) -->
					
					<h4>Display the Personal Details</h4>

				</div>

				<div class="panel-body">
				<!--  
			  		<div class="text-center">
						<h4>${registerModel.user.firstName }-
							${registerModel.user.lastName }</h4>
						<h4>email : ${registerModel.user.email }</h4>
						<h4>mobile : ${registerModel.user.contactNumber }</h4>
						<h4>role : ${registerModel.user.role }</h4>

				</div> -->
				</div>
				<!-- User can go to personal page and edit the information -->
				<div class="panel-footer">
		 
					<a href="${flowExecutionUrl }&_eventId_personal" class="btn btn-primary">Edit</a>
						
				</div>
			</div>
		</div>

		<!-- column for Display the address -->
		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">

					<h4>Display the Billing Address</h4>

				</div>

				<div class="panel-body">
		<!--  
					<div class="text-center">
						<h4>${registerModel.billing.addressLineOne }</h4>
						<h4>${registerModel.billing.addressLineTwo }</h4>
						<h4>${registerModel.billing.city } -
							${registerModel.billing.postalCode }</h4>
						<h4>${registerModel.billing.state } -
							${registerModel.billing.country }</h4>
				</div> -->	
				</div>
				<!-- User can go to address page and edit the information -->
				<div class="panel-footer">
				
		   			<a href="${flowExecutionUrl }&_eventId_billing" class="btn btn-primary">Edit</a>
				
				</div>
			</div>
		</div>
	</div>
	
	<!-- Confirm button after displaying the details -->
	<div class="row">
		<div class="col-sm-4 col-sm-offset-4">
			<div class="text-center">
			
				<a href="${flowExecutionUrl}&_eventId_success" class="btn btn-primary">Confirm</a>
					
			</div>
		</div>
	</div>
</div>

			
<%@include file="../shared/flows-footer.jsp" %>
		