<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../layout/flows-header.jsp"%>



<div class="container">

	<div class="row">

		<div class="col-md-offset-3 col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign Up - Address</h4>
				</div>

				<div class="panel-body">
					<!-- 					FORM ELEMENTS -->
					<sf:form class="form-horizontal" id="addressForm" modelAttribute="address"
						method="POST">

						<div class="form-group">
							<label class="control-label col-md-4" for="addressLineOne">Address
								Line One: </label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineOne" id="addressLineOne"
									placeholder="Address Line One" class="form-control" />
								<sf:errors path="addressLineOne" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="addressLineTwo">Address
								Line Two: </label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineTwo" id="addressLineTwo"
									placeholder="Address Line Two" class="form-control" />
								<sf:errors path="addressLineTwo" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="city">City: </label>
							<div class="col-md-8">
								<sf:input type="text" path="city" id="city" placeholder="City"
									class="form-control" />
								<sf:errors path="city" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="state">State: </label>
							<div class="col-md-8">
								<sf:input type="text" path="state" id="state" placeholder="State"
									class="form-control" />
								<sf:errors path="state" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="country">Country: </label>
							<div class="col-md-8">
								<sf:input type="text" path="country" id="country" placeholder="Country"
									class="form-control" />
								<sf:errors path="country" class="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="postalCode">Postal Code: </label>
							<div class="col-md-8">
								<sf:input type="text" path="postalCode" id="postalCode"
									placeholder="Postal Code" class="form-control" />
								<sf:errors path="postalCode" class="help-block" element="em" />
							</div>
						</div>


						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<button type="submit" name="_eventId_personal" class="btn btn-primary">
									<span class="glyphicon glyphicon-chevron-left"></span>
									Previous
								</button>
								<button type="submit" name="_eventId_confirm" class="btn btn-primary">
									Next
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