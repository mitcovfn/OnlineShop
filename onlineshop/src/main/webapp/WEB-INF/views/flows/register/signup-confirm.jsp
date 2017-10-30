<%@include file="../layout/flows-header.jsp"%>



<div class="row text-center">
	<div class="col-sm-6"> 
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Personal Details</h4>
			</div>
			<div class="panel-body">
				<!-- person details -->
				<div class="text-center">
					<h4>${registerModel.user.firstName}${registerModel.user.lastName}</h4>
					<h5>Email: -  ${registerModel.user.email}</h5>
					<h5>Contact Number: - ${registerModel.user.contactNumber}</h5>
					<h5>Role: - ${registerModel.user.role}</h5>
				</div>
			</div>
			<div class="panel-footer">
				<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
			</div>
		</div>
	</div>

	<div class="col-sm-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Billing Address</h4>
			</div>
			<div class="panel-body">
				<!-- person details -->
				<div class="text-center">
					<h4>${registerModel.address.addressLineOne}</h4>
					<h4>${registerModel.address.addressLineTwo}</h4>
					<h4>${registerModel.address.city}- ${registerModel.address.postalCode}</h4>
					<h4>${registerModel.address.state}- ${registerModel.address.country}</h4>

				</div>
			</div>
			<div class="panel-footer">
				<a href="${flowExecutionUrl}&_eventId_address" class="btn btn-primary">Edit</a>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<div class="text-center">
			<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-primary">Confirm</a>
		</div>
	</div>
</div>





<%@include file="../layout/flows-footer.jsp"%>