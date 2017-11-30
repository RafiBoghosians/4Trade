<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp" %>

<div class="container">				
 	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">

					<h4>Sign Up - Personal Info.</h4>

				</div>

				<div class="panel-body">

					<sf:form 
								method="POST" 
								class="form-horizontal"
								id="registerForm"
								modelAttribute="user"
					>
					
					
					
					<!-- FORM Elements -->
						<!-- First Name -->
						<div class="form-group">
							<label class="control-label col-md-4" for="name">First
								Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="firstName" placeholder="First Name"
									class="form-control" />
								<sf:errors path="firstName" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<!-- Last Name -->
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Last
								Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="lastName" placeholder="Last Name"
									class="form-control" />
								<sf:errors path="lastName" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<!-- Email -->
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Email: </label>
							<div class="col-md-8">
								<sf:input type="text" path="email" placeholder="john@gmail.com"
									class="form-control" />
								<sf:errors path="email" cssClass="help-block errHighlight" element="em" />
							</div>
						</div>
						
						<!-- Contact Number-->
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Contact
								Number: </label>
							<div class="col-md-8">
								<sf:input type="text" path="contactNumber"
									placeholder="XXXXXXXXXXXX" class="form-control" />
								<sf:errors path="contactNumber" cssClass="help-block"
									element="em" />
							</div>
						</div>
						
						<!-- Password -->
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Password:
							</label>
							<div class="col-md-8">
								<sf:input type="password" path="password" placeholder="Password"
									class="form-control" />
								<sf:errors path="password" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<!-- Password Confirmation -->
				<!-- 		<div class="form-group">
							<label class="control-label col-md-4" for="name">Confirm
								Password: </label>
							<div class="col-md-8">
								<sf:input type="password" path="confirmPassword"
									placeholder="Re-enter Password" class="form-control" />
								<sf:errors path="confirmPassword" cssClass="help-block errHighlight"
									element="em" />
							</div>
						</div>
					-->	
						<!-- Role(User/Supplier)-->
						<!-- By using bootstrap class of radio-inline we create a radio button -->
						<div class="form-group">
							<div class="control-label col-md-4">Select Role:</div>
							<div class="col-md-8">
								<label class="radio-inline"> <sf:radiobutton path="role"
										value="USER" checked="checked" />User
								</label> <label class="radio-inline"> <sf:radiobutton
										path="role" value="SUPPLIER" />Supplier
								</label>
							</div>
						</div>

						<!-- Submit Button -->
						<div class="form-group">

							<div class="col-md-offset-4 col-md-8">

								<!-- This name is important -->
								<button type="submit" class="btn btn-primary btn-block" name="_eventId_billing" >
									
									Next - Billing <span class="glyphicon glyphicon-chevron-right"></span>
								
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
		