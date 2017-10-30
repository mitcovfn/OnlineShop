<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../layout/flows-header.jsp"%>



<div class="container">

	<div class="row">

		<div class="col-md-offset-3 col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign Up - Personal</h4>
				</div>

				<div class="panel-body">
					<!-- FORM ELEMENTS -->
					<sf:form class="form-horizontal" id="registerForm" modelAttribute="user"
						method="POST">

						<div class="form-group">
							<label class="control-label col-md-4" for="firstName">First Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="firstName" id="firstName"
									placeholder="First Name" class="form-control" />
								<sf:errors path="firstName" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="lastName">Last Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="lastName" id="lastName" placeholder="Last Name"
									class="form-control" />
								<sf:errors path="lastName" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="email">Email: </label>
							<div class="col-md-8">
								<sf:input type="text" path="email" id="email" placeholder="email@mail.com"
									class="form-control" />
								<sf:errors path="email" class="help-block" element="em" />
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4" for="contactNumber">Contact
								Number: </label>
							<div class="col-md-8">
								<sf:input type="number" path="contactNumber" id="contactNumber"
									placeholder="065896655" class="form-control" />
								<sf:errors path="contactNumber" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="password">Password: </label>
							<div class="col-md-8">
								<sf:input type="password" path="password" id="password"
									placeholder="Password" class="form-control" />
								<sf:errors path="password" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="confirmPassword">Confirm
								Password: </label>
							<div class="col-md-8">
								<sf:input type="password" path="confirmPassword" id="confirmPassword"
									placeholder="Re-enter Password" class="form-control" />
								<sf:errors path="confirmPassword" class="help-block" element="em" />
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Role </label>
							<div class="col-md-8">
								<label class="radio-inline">
									<sf:radiobutton path="role" value="USER" checked="checked" />
									User
								</label>
								<label class="radio-inline">
									<sf:radiobutton path="role" value="SUPPLIER" />
									Supplier
								</label>
							</div>
						</div>



						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" name="_eventId_address" class="btn btn-primary">
									Next - Billing
									<span class="glyphicon glyphicon-chevron-right"></span>
								</button>
							</div>
						</div>
					</sf:form>

				</div>

			</div>

		</div>

	</div>

</div>



<%@include file="../layout/flows-footer.jsp"%>