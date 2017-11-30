<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp" %>
	<div class="container">

	<div class="row">

		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">

					<h4>Sign Up - Address</h4>

				</div>

				<div class="panel-body">

					<!-- FORM Elements -->

					<sf:form
							method="POST"
							class="form-horizontal" 
							id="billingForm" 
							modelAttribute="billing"
					>

						<!-- Address Line One -->
						<div class="form-group">
							<label class="control-label col-md-4">Address Line One: </label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineOne" placeholder="Enter Address Line One" class="form-control" />		
								<sf:errors path="addressLineOne" cssClass="help-block" element="em" />	
							</div>
						</div>
						
						<!-- Address Line Two -->
						<div class="form-group">
							<label class="control-label col-md-4"> Address Line Two: </label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineTwo" placeholder="Enter Address Line Two" class="form-control" />
								<sf:errors path="addressLineTwo" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<!-- City -->
						<div class="form-group">
							<label class="control-label col-md-4">City: </label>
							<div class="col-md-8">
								<sf:input type="text" path="city" placeholder="Enter city name" class="form-control" />
								<sf:errors path="city" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<!-- Postal Code -->
						<div class="form-group">
							<label class="control-label col-md-4">Postal Code: </label>
							<div class="col-md-8">
								<sf:input type="text" path="postalCode" placeholder="Enter Postal code" class="form-control" />
							</div>
						</div>
						
						<!-- State -->
						<div class="form-group">
							<label class="control-label col-md-4">State: </label>
							<div class="col-md-8">
								<sf:input type="text" path="state" placeholder="Enter your state name" class="form-control" />
								<sf:errors path="state" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<!-- Country -->
						<div class="form-group">
							<label class="control-label col-md-4">Country: </label>
							<div class="col-md-8">
								<sf:input type="text" path="country" placeholder="Enter your country name" class="form-control" />	
							</div>
						</div>
						
						<!-- Submit -->
						<div class="form-group">

							<div class="col-md-offset-4 col-md-8">

								<!-- This "name" is important RAFI :) -->
								<!-- 2 submit button one for go back and the other for go forward -->
								<button type="submit" name="_eventId_personal" class="btn btn-primary btn-block">
			
									<span class="glyphicon glyphicon-chevron-left"></span> Previous	- Personal
								
								</button>


								<button type="submit" name="_eventId_confirm" class="btn btn-primary btn-block">
									
									Next - Confirm <span class="glyphicon glyphicon-chevron-right"></span>
								
								</button>
							</div>

						</div>

					</sf:form>

				</div>
			</div>
		</div>

	</div>
</div>
			 
			
<%@include file="../shared/flows-footer.jsp" %>
		