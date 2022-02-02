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


	<div class="card-box pt-2 pb-1">
		<header class="">
			<h5 class="text-center" style="display: inline;">Balance Sheet</h5>
		</header>
		
		<!-- <div class="row"> -->
		<div style="background-color: #89CFF0" class="card-box pt-1 pb-1 mb-1">
			<div class="row">
			
				<div class="col-sm-2 mr-1">
					<label for="from" class="mb-0">As On Date</label>
					<div class="row">
						<input type="date" id=fromDate class="col-sm-12 form-control-sm" />
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
						data-toggle="modal" onclick="viewBalanceReport()" accesskey="S">
						<i style="color: white" class="fas fa-search"></i> <span style="text-decoration:underline">S</span>earch
					</button>
					<button class="btn btn-sm btn-danger mt-3 border"
						data-toggle="modal" onclick="PreviewBalanceReport()" accesskey="P">
						<i style="color: white" class="fas fa-print"></i>  <span style="text-decoration:underline">P</span>review
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
						<th>Particular Name</th>
						<th>Taka</th>
						<th>Details</th>

					</tr>
				</thead>
				<tbody id="balance_list">
				
				</tbody>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
			</table>

		</div>
	</div>
	
</div>

</div>

<div class="modal fade" id="balList" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
	
	
		<div class="modal-content">
			<div class="modal-header">
			

			
				<h5 class="modal-title" id="exampleModalLabel">Balance Sheet Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row mt-1" style="height: 30px;">
				<p id="groupSyn" style="font-weight:bold;font-size:16px;"><a href="">Balance Sheet</a>>></p>
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
						<tbody id="balDetailsList">
						
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
						<tbody id="balLedgerDetailsList">
						
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
