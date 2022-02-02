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
			<h5 class="text-center" style="display: inline;">Daily Statement</h5>
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
					<label for="type" class="mb-0">Type</label>
					<div class="row">
						<select class="form-control form-control-sm" id="statementType"
							data-style="btn-light btn-sm border-secondary form-control-sm">
							<option id='statementType' value="0">All</option>
							<option id='statementType' value="1">IPD Income</option>
							<option id='statementType' value="2">OPD Income</option>
							<option id='statementType' value="3">Lab Income</option>
							<option id='statementType' value="4">Other Income</option>
							<option id='statementType' value="5">Expense</option>
							
						</select>
					</div>
				</div>
				
				<div class="col-sm-4">
					<button class="btn btn-sm btn-danger mt-3 border"
						data-toggle="modal" onclick="StatementViewList()" accesskey="S">
						<i style="color: white" class="fas fa-search"></i> <span style="text-decoration:underline">S</span>earch
					</button>
					<button class="btn btn-sm btn-info mt-3 border"
						data-toggle="modal" onclick="PreviewDailyStatement()" accesskey="P">
						<i style="color: white" class="fas fa-print"></i> <span style="text-decoration:underline">P</span>rint
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
						<th>Type</th>
						<th class='text-right'>Sales</th>
						<th class='text-right'>Discount</th>
						<th class='text-right'>Collection</th>
						<th class='text-right'>Due</th>
						<th class='text-right'>Details</th>

					</tr>
				</thead>
				<tbody id="statement_details_list">

				</tbody>
			</table>

		</div>
			
	</div>
	


</div>


</div>


<div class="modal fade" id="incomeDetailsList" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
	
	
		<div class="modal-content">
			<div class="modal-header">
			

			
				<h5 class="modal-title" id="exampleModalLabel">Income Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row mt-1" style="height: 30px;">
				<p id="groupSyn" style="font-weight:bold;font-size:16px;"><a href=""></a>>></p>
				</div>
	
				<div class="col-sm-12 col-md-12 col-lg-12 p-0"
					style="overflow: auto; max-height: 400px;">
					<p id="groupSyn" style="background:#800000;color:#FFFFFF;font-weight:bold;font-size:16px;">Sales>></p>
					<table id="income_sale" class="display table table-bordered table-sm">
						<thead>
							<tr>
								<th style='width:80px' class="text-left">#SL</th>
								<th style='width:280px' class="text-left">Particular </th>
								<th style='width:120px' class="text-right">Amount</th>
								<th style='width:120px' class="text-left">Details</th>
							</tr>
						</thead>
						<tbody id="stIncomeDetailsList">
						
						</tbody>
					</table>
					<p id="groupSyn" style="background:#800000;color:#FFFFFF;font-weight:bold;font-size:16px;">Discount>></p>
						<table id="income_discount" class="display table table-bordered table-sm">
						<thead>
							<tr>
								<th style='width:80px' class="text-left">#SL</th>
								<th style='width:280px' class="text-left">Particular </th>
								<th style='width:120px' class="text-right">Amount</th>
								<th style='width:120px' class="text-left">Details</th>
							</tr>
						</thead>
						<tbody id="stDiscountDetailsList">
						
						</tbody>
					</table>
					<p id="groupSyn" style="background:#800000;color:#FFFFFF;font-weight:bold;font-size:16px;">Cash>></p>
					<table id="income_cash" class="display table table-bordered table-sm">
						<thead>
							<tr>
								<th style='width:80px' class="text-left">#SL</th>
								<th style='width:280px' class="text-left">Particular </th>
								<th style='width:120px' class="text-right">Amount</th>
								<th style='width:120px' class="text-left">Details</th>
							</tr>
						</thead>
						<tbody id="stCashDetailsList">
						
						</tbody>
					</table>
					
					<p id="" style="background:#800000;color:#FFFFFF;font-weight:bold;font-size:16px;">Due:>>>>>>><span id="DueAmount"></span></p>	
					
				</div>
				
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-secondary"
					data-dismiss="modal">Close</button>

			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="expenseDetailsList" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
	
	
		<div class="modal-content">
			<div class="modal-header">
			

			
				<h5 class="modal-title" id="exampleModalLabel">Expense Details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<div class="col-sm-12 col-md-12 col-lg-12 p-0"
					style="overflow: auto; max-height: 400px;">
					<p id="groupSyn" style="background:#800000;color:#FFFFFF;font-weight:bold;font-size:16px;">Expense List>></p>
					<table id="expense_sale" class="display table table-bordered table-sm">
						<thead>
							<tr>
								<th style='width:80px' class="text-left">#SL</th>
								<th style='width:280px' class="text-left">Particular </th>
								<th style='width:120px' class="text-right">Amount</th>
								<th style='width:120px' class="text-left">Details</th>
							</tr>
						</thead>
						<tbody id="stExpenseDetailsList">
						
						</tbody>
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
	src="${pageContext.request.contextPath}/assets/js/accounts/payment-receipt-voucher.js"></script>
