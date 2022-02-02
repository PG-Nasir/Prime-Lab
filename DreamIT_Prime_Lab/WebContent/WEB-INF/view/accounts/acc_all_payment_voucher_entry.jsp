<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>

<%
String userId=(String)session.getAttribute("userId");
String userName=(String)session.getAttribute("userName");


	String fiscalYear = (String) request.getAttribute("fiscalYear");
	String maxVoucher = (String) request.getAttribute("maxVoucherNo");

%>

<jsp:include page="../include/header.jsp" />

<div class="page-content-wrapper">
<div class="page-content">


	<input type="hidden" id="userId" value="<%=userId%>">
	<input type="hidden" id="itemAutoId" value="0">


		<!-- <div class="row"> -->
		<h5 class="text-left" style="margin-top:0px;">Voucher Entry</h5>
			<div class="row">

			
<%-- 			<button type="button" accesskey="A" class="btn btn-outline-success"
				onclick="addRow()">
				<i class="fa fa-search"></i><span style="text-decoration:underline">A</span>dd
			</button> --%>

				<div class="col-sm-2 mr-1">
					<label for="voucherNoSearch" class="mb-0">Voucher No</label>
					<div class="row">
						<input type="number" id="voucherNoSearch" value='0' class="form-control-sm" />
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
						<select class="form-control form-control-sm" id="voucherTypeS"
							data-style="btn-light btn-sm border-secondary form-control-sm">
							<option id='voucherTypeS' value="0">All</option>
							<option id='voucherTypeS' value="1">Cash Payment Voucher</option>
							<option id='voucherTypeS' value="3">Cash Received Voucher</option>
							<option id='voucherTypeS' value="2">Bank Payment Voucher</option>
							<option id='voucherTypeS' value="4">Bank Received Voucher</option>
							<option id='voucherTypeS' value="6">Contra Voucher</option>
							<option id='voucherTypeS' value="5">Journal Voucher</option>
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
						data-toggle="modal" data-target="#installFormModal" onclick="PaymentVoucherList()">
						<i style="color: white" class="fas fa-download" ></i> Voucher List
					</button>
				</div>

			</div>

		<!-- </div> -->

		<hr class="my-1">

		<div class="row" style="padding-left:20px;">
		
				<div class="col-sm-2 mr-1">
					<label for="type" class="mb-0">Type</label>
					<div class="row">
						<select class="form-control form-control-sm" id="voucherType"
							data-style="btn-light btn-sm border-secondary form-control-sm" onclick="TypeWiseVoucherNo()">
							<option id='voucherType' value="1">Cash Payment Voucher</option>
							<option id='voucherType' value="3">Cash Received Voucher</option>
							<option id='voucherType' value="2">Bank Payment Voucher</option>
							<option id='voucherType' value="4">Bank Received Voucher</option>
							<option id='voucherType' value="6">Contra Voucher</option>
							<option id='voucherType' value="5">Journal Voucher</option>
						</select>
					</div>
				</div>
				
			<div class="col-sm-2 mr-1">
				<label for="sample" class="mb-0">Voucher No</label>
				<div class="row">
					<input type="text" readonly value="<%=maxVoucher%>"  id="voucherNo" class="form-control-sm" />
				</div>
			</div>


			<div class="col-sm-2 mr-1">
				<label for="buyerName" class="mb-0">Date</label>
				<div class="row">
					<input type="date" id="date" class="col-md-12 form-control-sm" />
				</div>
			</div>

			<div class="col-sm-3 mr-1">
				<label for="sample" class="mb-0">Cost Center</label>
				<div class="row">
					<select class="selectpicker form-control form-control-sm"
						id="costCenterId" data-live-search="true"
						data-style="btn-light btn-sm border-secondary form-control-sm">
						<option id='costCenterId' value="0">Select Cost Center</option>
						<c:forEach items="${costCenterlist}" var="list" varStatus="counter">
							<option id='costCenterId' value="${list.costCenterId}">${list.name}</option>
						</c:forEach>

					</select>
				</div>
			</div>
			
						<div class="col-sm-2">

				<label for="sample" class="mb-0 mt-2">Stand By <input
					type="checkbox" id="standBy" class="mt-2" />
				</label>
			</div>

		</div>
	
		<div style="padding-left:20px;" class="row mt-1">
			<div id="debittableList" style="height: 230px;" class="table-responsive">
<!-- 			<input type="checkbox" class="hidedebitcol" value="Cheque No" id="col_3" />Cheque No
			<input type="checkbox" class="hidedebitcol" value="Cheque Date" id="col_4" />Cheque Date -->
			<label for="sample" class="mb-0 mt-2"><span style="font-weight:bold;font-size:20px;">Debit</span></label>
				<table id="debit_table" class="table table-hover table-bordered table-sm">
				
					<thead>
						<tr>
							<th>SL#</th>
							<th style='width:280px'>Details Account</th>
							<th>Cheque No</th>
							<th>Cheque Date</th>
							<th>Amount</th>
							<th>Description</th>
							<th>Add</th>
							<th>Delete</th>
							<th>Edit</th>
							

						</tr>
					</thead>
					<tbody id="debit_trlist">

					</tbody>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th><input id="txtTotalDebit" style='background:yellow;font-weight:bold;font-size:20px;width:170px;height:30px;' readonly class='form-control-sm'  value=''/></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>

						</tr>
				</table>

			</div>
			
			<div id="credittableList" style="height: 180px;" class="table-responsive">
<!-- 			<input type="checkbox" class="hidecreditcol" value="Cheque No" id="col_3" />Cheque No
			<input type="checkbox" class="hidecreditcol" value="Cheque Date" id="col_4" />Cheque Date -->
			<label for="sample" class="mb-0 mt-2"><span style="font-weight:bold;font-size:20px;">Credit</span></label>
				<table id="credit_table" class="table table-hover table-bordered table-sm">
					<thead>
						<tr>
							<th>SL#</th>
							<th style='width:280px'>Details Account</th>
							<th>Cheque No</th>
							<th>Cheque Date</th>
							<th>Amount</th>
							<th>Description</th>
							<th>Add</th>
							<th>Delete</th>
							<th>Edit</th>
							
							

						</tr>
					</thead>
					<tbody id="credit_trlist">

					</tbody>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th><input id="txtTotalCredit" style='background:yellow;font-weight:bold;font-size:20px;width:170px;height:30px;' readonly class='form-control-sm'  value=''/></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
				</table>

			</div>
		</div>
		
		
		<div class="row mt-1 mb-0">
			<div class="col-md-12 pl-0">
				<button id="btnPOSubmit" type="button" accesskey="S"
					class="btn btn-outline-success" onclick="saveAction()">
					<i class="fas fa-save"></i> <span style="text-decoration:underline">S</span>ave
				</button>
				
				<button id="btnRefresh" type="button" accesskey="R"
					class="btn btn-outline-dark" onclick="refreshAction()">
					<i class="fas fa-refresy"></i> <span style="text-decoration:underline">R</span>efresh
				</button>
	

			</div>
		</div>

</div>
<!-- Modal -->
<div  class="modal fade" id="installFormModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div  class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Voucher List</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
	<div class="row mt-1">
		<div id="tableList" style="height: 350px;" class="table-responsive">
			<table class="table table-hover table-bordered table-sm">
				<thead>
					<tr>
						<th>SL#</th>
						<th>Voucher No</th>
						<th>Type</th>
						<th>Date</th>
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
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-sm btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>
</div>
<jsp:include page="../include/footer.jsp" />


<script
	src="${pageContext.request.contextPath}/assets/js/accounts/voucher.js"></script>
