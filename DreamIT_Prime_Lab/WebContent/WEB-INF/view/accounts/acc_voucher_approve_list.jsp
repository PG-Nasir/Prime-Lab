<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>

<%
String userId=(String)session.getAttribute("userId");
	
%>

<jsp:include page="../include/header.jsp" />

<div class="page-content-wrapper">
<div class="page-content">
	
	<input type="hidden" id="userId" value="<%=userId%>">
	<input type="hidden" id="itemAutoId" value="0">


	<div class="card-box pt-2 pb-1">
		<header class="">
			<h5 class="text-center" style="display: inline;">Voucher Approval List</h5>
		</header>

		<!-- <div class="row"> -->
		<div style="background-color: #89CFF0" class="card-box pt-1 pb-1 mb-1">
			<div class="row">

				<div class="col-sm-2 mr-1">
					<label for="voucherNoSearch" class="mb-0">Voucher No</label>
					<div class="row">
						<input type="text" id="voucherNoSearch" class="form-control-sm" />
					</div>
				</div>

				<div class="col-sm-2 mr-1">
					<label for="from" class="mb-0">From</label>
					<div class="row">
						<input type="date" id="fromDate" class="col-sm-12 form-control-sm" />
					</div>
				</div>

				<div class="col-sm-2 mr-1">
					<label for="to" class="mb-0">To</label>
					<div class="row">
						<input type="date" id="toDate" class="col-sm-12 form-control-sm" />
					</div>
				</div>
				<div class="col-sm-2 mr-1">
					<label for="type" class="mb-0">Type</label>
					<div class="row">
						<select class="form-control form-control-sm" id="voucherType"
							data-style="btn-light btn-sm border-secondary form-control-sm">
							<option id='voucherType' value="0">All</option>
							<option id='voucherType' value="1">Cash Payment</option>
							<option id='voucherType' value="2">Bank Payment</option>
							<option id='voucherType' value="3">Cash Receipt</option>
							<option id='voucherType' value="4">Bank Receipt</option>
							<option id='voucherType' value="5">Journal Voucher</option>
							<option id='voucherType' value="6">Contra Voucher</option>
							<option id='voucherType' value="10">Medicine Purchase</option>
							<option id='voucherType' value="12">Pathology Voucher</option>
							<option id='voucherType' value="13">Hospital Billing Voucher (Indoor)</option>
							<option id='voucherType' value="14">Hospital Billing Voucher (Outdoor)</option>
						</select>
					</div>
				</div>
				
				<div class="col-sm-1 mr-1">
					<label for="type" class="mb-0">Status</label>
					<div class="row">
						<select class="form-control form-control-sm" id="approveType"
							data-style="btn-light btn-sm border-secondary form-control-sm">
							<option id='approveType' value="0">Pending</option>
							<option id='approveType' value="1">Approve</option>
						</select>
					</div>
				</div>
				
				<div class="col-sm-2">
					<button class="btn btn-sm btn-danger mt-3 border"
						data-toggle="modal" onclick="PaymentVoucherList()">
						<i style="color: white" class="fas fa-search"></i> Search
					</button>
				</div>

		</div>

	</div>
	<!-- </div> -->

	<hr class="my-1">



	<div class="row mt-1">
		<div id="tableList" style="height: 350px;" class="table-responsive">
			<table class="table table-hover table-bordered table-sm">
				<thead>
					<tr>
						<th>SL#</th>
						<th>Voucher No</th>
						<th>Type</th>
						<th>Date</th>
						<th><input type='checkbox' id='allcheck' />Status</th>
						<th>Approve By</th>
						<th>Approve At</th>
						<th>Print</th>

					</tr>
				</thead>
				<tbody id="payment_voucher_list">

				</tbody>
			</table>

		</div>
			
	</div>
	

	<div class="card-box pt-2 pb-1">
			<div class="row d-flex justify-content-center ">
			<button class="btn btn-sm btn-success mt-3 border"
						data-toggle="modal" onclick="approveVoucher()">
						<i style="color: white" class="fas fa-check"></i> Approve
			</button>
			</div>
	</div>

</div>


</div>

</div>

<jsp:include page="../include/footer.jsp" />


<script
	src="${pageContext.request.contextPath}/assets/js/accounts/voucher_approval.js"></script>
