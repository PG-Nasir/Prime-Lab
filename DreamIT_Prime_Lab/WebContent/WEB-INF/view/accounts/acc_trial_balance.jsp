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
			<h5 class="text-center" style="display: inline;">Trial Balance Statement</h5>
		</header>
		
		<!-- <div class="row"> -->
		<div style="background-color: #89CFF0" class="card-box pt-1 pb-1 mb-1">
			<div class="row">
			

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
					<label for="to" class="mb-0">Type</label>
					<div class="row">
						<select class="form-control form-control-sm" id="transactionType"
							data-style="btn-light btn-sm border-secondary form-control-sm">
							<option id='transactionType' value="0">All</option>
							<option id='transactionType' value="1">Transaction Head</option>
						</select>
					</div>
				</div>

				<div class="col-sm-4">
							<button class="btn btn-sm btn-info mt-3 border"
						data-toggle="modal" onclick="ViewTrialBalanceReport()" accesskey="S">
						<i style="color: white" class="fas fa-search"></i> <span style="text-decoration:underline">S</span>earch
					</button>
					<button class="btn btn-sm btn-danger mt-3 border"
						data-toggle="modal" onclick="PreviewTrialBalanceReport()" accesskey="P">
						<i style="color: white" class="fas fa-print"></i> <span style="text-decoration:underline">P</span>rint
					</button>
					
				</div>
				
				

		</div>

	</div>
	<!-- </div> -->

	<hr class="my-1">
	
	<div class="row mt-1" style="height: 30px;">
		<p id="groupSyn" style="font-weight:bold;font-size:16px;"><a href="">Trial Balance</a>>></p>
	</div>
	
	<div class="row mt-1">
		<div id="tableList" style="height: 400px;" class="table-responsive">
			<table class="table table-hover table-bordered table-sm">
				<thead>
					<tr>
						<th>SL#</th>
						<th>Accounts No</th>
						<th>Before Debit</th>
						<th>Before Credit</th>
						<th>Transaction Debit</th>
						<th>Transaction Credit</th>
						<th>Balance Debit</th>
						<th>Balance Credit</th>
						<th>Details</th>

					</tr>
				</thead>
				<tbody id="tiralbalance_list">

				</tbody>
					<tr>
						<th></th>
						<th>Total</th>
						<th><input type="text" id="beforeDebit" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
						<th><input type="text" id="beforeCredit" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
						<th><input type="text" id="transactionDebit" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
						<th><input type="text" id="transactionCredit" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
						<th><input type="text" id="balanceDebit" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
						<th><input type="text" id="balanceCredit" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
						<th></th>

					</tr>
			</table>

		</div>
	</div>
	
</div>

</div>


<jsp:include page="../include/footer.jsp" />

<script
	src="${pageContext.request.contextPath}/assets/js/accounts/account-report.js"></script>
