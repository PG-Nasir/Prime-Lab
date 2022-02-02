<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.List"%>

<%
String userId=(String)session.getAttribute("userId");
String bookType=(String) request.getAttribute("bookType");
%>
<jsp:include page="../include/header.jsp" />

<div class="page-content-wrapper">
<div class="page-content">


	<input type="hidden" id="userId" value="<%=userId%>">
	<input type="hidden" id="itemAutoId" value="0">


	<div class="card-box pt-2 pb-1">
		<header class="">
			<h5 class="text-center" style="display: inline;">Day Book Report</h5>
		</header>

		<!-- <div class="row"> -->
		<div style="background-color: #89CFF0" class="card-box pt-1 pb-1 mb-1">
			<div class="row">


				<div class="col-sm-2 mr-1">
					<label for="to" class="mb-0">From Date</label>
					<div class="row">
						<input type="date" id="fromDate" class="col-sm-12 form-control-sm" />
					</div>
				</div>



				<div class="col-sm-2 mr-1">
					<label for="to" class="mb-0">To Date</label>
					<div class="row">
						<input type="date" id="toDate" class="col-sm-12 form-control-sm" />
					</div>
				</div>

				<div class="col-sm-2 mr-1">
					<label for="type" class="mb-0">Voucher Type</label>
					<div class="row">
						<select class="form-control form-control-sm" id="voucherType"
							data-style="btn-light btn-sm border-secondary form-control-sm">
							<option id='voucherType' value="0">All</option>
							<option id='voucherType' value="1">Cash Payment</option>
							<option id='voucherType' value="2">Bank Payment</option>
							<option id='voucherType' value="3">Cash Receipt</option>
							<option id='voucherType' value="4">Bank Receipt</option>
							<option id='voucherType' value="5">Journal Voucher</option>
							<option id='voucherType' value="6">Contra Entry</option>
							<option id='voucherType' value="14">Outdoor Lab Collection</option>
							<option id='voucherType' value="18">Hospital Cash Collection</option>
						</select>
					</div>
				</div>
				
				<div class="col-sm-4">
					<button class="btn btn-sm btn-danger mt-3 border"
						data-toggle="modal" onclick="ViewAllBookReport()">
						<i style="color: white" class="fas fa-search"></i> Search
					</button>
					<button class="btn btn-sm btn-success mt-3 border"
						data-toggle="modal" onclick="PreviewAllBookReport()">
						<i style="color: white" class="fas fa-print"></i> Preview
					</button>
				</div>
				


		</div>

	</div>
	<!-- </div> -->

	<hr class="my-1">
	
	
	<div class="row mt-1">
		<div id="tableList" style="height: 400px;" class="table-responsive">
			<table class="table table-hover table-bordered table-sm">
				<thead>
					<tr>
						<th style="width:60px;">SL#</th>
						<th style="width:140px;">Voucher Type</th>
						<th style="width:100px;">Voucher No</th>
						<th style="width:200px;">Debit Particular</th>
						<th style="width:200px;">Credit Particular</th>
						<th style="width:150px;">Amount</th>

					</tr>
				</thead>
				<tbody id="daybook_list">

				</tbody>
<!-- 					<tr>
						<th style="width:60px;"></th>
						<th>Totalt</th>
						<th></th>
						<th></th>
						<th></th>


					</tr> -->
			</table>

		</div>
	</div>


</div>
</div>


<jsp:include page="../include/footer.jsp" />


<script
	src="${pageContext.request.contextPath}/assets/js/accounts/payment-receipt-voucher.js"></script>
