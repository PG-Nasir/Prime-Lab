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
			<h5 class="text-center" style="display: inline;">Profit And Loss</h5>
		</header>
		
		<!-- <div class="row"> -->
		<div style="background-color: #89CFF0" class="card-box pt-1 pb-1 mb-1">
			<div class="row">
			

				<div class="col-sm-2 mr-1">
					<label for="from" class="mb-0">From Date</label>
					<div class="row">
						<input type="date" id="fromDate" class="col-sm-12 form-control-sm" />
					</div>
				</div>

				<div class="col-sm-2 mr-1">
					<label for="from" class="mb-0">To Date</label>
					<div class="row">
						<input type="date" id="toDate" class="col-sm-12 form-control-sm" />
					</div>
				</div>
				
				<div class="col-sm-4">
					<button class="btn btn-sm btn-info mt-3 border"
						data-toggle="modal" onclick="ViewProfitAndLossReport()">
						<i style="color: white" class="fas fa-search"></i> Search
					</button>
					<button class="btn btn-sm btn-danger mt-3 border"
						data-toggle="modal" onclick="PreviewPALReport()">
						<i style="color: white" class="fas fa-print"></i> Preview
					</button>
				</div>

		</div>

	</div>
	<!-- </div> -->

	<hr class="my-1">
	
		
	<div class="row mt-1" style="height: 30px;">
		<p id="groupSyn" style="font-weight:bold;font-size:16px;"><a href="">Profit And Loss</a>>></p>
	</div>
	
	<div class="row mt-1">
		<div id="tableList" style="height: 400px;" class="table-responsive">
			<table class="table table-hover table-bordered table-sm">
				<thead>
					<tr>
						<th style="width:60px;">SL#</th>
						<th style="width:200px;">Particular</th>
						<th style="width:400px;">Note</th>
						<th style="width:150px;">Taka</th>
						<th style="width:80px;">Details</th>

					</tr>
				</thead>
				<tbody id="profitandloss_list">

				</tbody>
					<tr>
						<th style="width:60px;"></th>
						<th>Net Profit</th>
						<th></th>
						<th style="width:150px;"><input type="text" id="netProfit" style="background:yellow;font-weight:bold;width:150px;height:30px;"/></th>
						<th style="width:80px;"></th>
						

					</tr>
			</table>

		</div>
	</div>
	
</div>

</div>


<div class="modal fade" id="palList" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Profit And Loss Accounts Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row mt-1" style="height: 30px;">
				<p id="groupSyn" style="font-weight:bold;font-size:16px;"><a href="">Profit And Loss</a>>></p>
				</div>
	
				<div class="col-sm-12 col-md-12 col-lg-12 p-0"
					style="overflow: auto; max-height: 200px;">
					<table class="display table table-bordered table-sm">
						<thead>
							<tr>
								<th class="text-left">#SL</th>
								<th class="text-left">Account Name/No</th>
								<th class="text-left">Opening Balance</th>
								<th class="text-left">Debit</th>
								<th class="text-center">Credit</th>
								<th class="text-center">Closing</th>
								<th class="text-center">Details</th>
							</tr>
						</thead>
						<tbody id="palDetailsList">
						
						</tbody>
					</table>
				</div>
				
				<div class="row mt-1" style="height: 30px;">
				<p id="groupSyn" style="font-weight:bold;font-size:16px;"><a href="">Ledger Details</a>>></p>
				</div>
				
					<div class="col-sm-12 col-md-12 col-lg-12 p-0"
					style="overflow: auto; max-height: 200px;">
					<table class="display table table-bordered table-sm">
						<thead>
							<tr>
								<th style="width:40px;" class="text-left">#SL</th>
								<th style="width:350px;" class="text-left">Ledger Account</th>
								<th style="width:120px;" class="text-left">Opening Balance</th>
								<th style="width:120px;" class="text-left">Debit</th>
								<th style="width:120px;" class="text-center">Credit</th>
								<th style="width:120px;" class="text-center">Closing</th>
								<th style="width:50px;" class="text-center">Details</th>
							</tr>
						</thead>
						<tbody id="palLedgerDetailsList">
						
						</tbody>
						<tr>
							<th style="width:40px;"></th>
							<th style="width:350px;">Total</th>
							<th style="width:120px;"><input type="text" id="tOb" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
							<th style="width:120px;"><input type="text" id="tDebit" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
							<th style="width:120px;"><input type="text" id="tCredit" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
							<th style="width:120px;"><input type="text" id="tClose" style="background:yellow;font-weight:bold;width:110px;height:30px;"/></th>
							<th style="width:120px;"></th>
							
	
						</tr>
					</table>
				</div>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-secondary"
					data-dismiss="modal">Close</button>

			</div>
		</div>
	</div>
</div>


<jsp:include page="../include/footer.jsp" />

<script
	src="${pageContext.request.contextPath}/assets/js/accounts/account-report.js"></script>
