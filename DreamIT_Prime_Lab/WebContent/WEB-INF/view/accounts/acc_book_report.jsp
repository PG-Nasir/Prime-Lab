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

	<div class="alert alert-success alert-dismissible fade show"
		style="display: none;">
		<p id="successAlert" class="mb-0">
			<strong>Success!</strong> Unit Name Save Successfully..
		</p>
	</div>
	<div class="alert alert-warning alert-dismissible fade show"
		style="display: none;">
		<p id="warningAlert" class="mb-0">
			<strong>Warning!</strong> Unit Name Empty.Please Enter Unit Name...
		</p>
	</div>
	<div class="alert alert-danger alert-dismissible fade show"
		style="display: none;">
		<p id="dangerAlert" class="mb-0">
			<strong>Wrong!</strong> Something Wrong...
		</p>
	</div>
	<input type="hidden" id="userId" value="<%=userId%>">
	<input type="hidden" id="itemAutoId" value="0">
	<input type="hidden" id="bookType" value="<%=bookType%>">


	<div class="card-box pt-2 pb-1">
		<header class="">
			<h5 class="text-center" style="display: inline;"><%=bookType%> Report</h5>
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

				
				<div class="col-sm-4">
					<button class="btn btn-sm btn-danger mt-3 border"
						data-toggle="modal" onclick="viewCashBankBooReport()">
						<i style="color: white" class="fas fa-print"></i> Search
					</button>
					<button class="btn btn-sm btn-success mt-3 border"
						data-toggle="modal" onclick="PreviewBooReport()">
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
						<th style="width:160px;">Voucher No</th>
						<th style="width:400px;">Particular</th>
						<th style="width:150px;">Amount</th>
						<th style="width:150px;">Book Type</th>
						<th style="width:100px;">Date</th>
					</tr>
				</thead>
				<tbody id="cash_bank_book_report_list">

				</tbody>

			</table>

		</div>
	</div>




</div>
</div>


<jsp:include page="../include/footer.jsp" />


<script
	src="${pageContext.request.contextPath}/assets/js/accounts/payment-receipt-voucher.js"></script>
