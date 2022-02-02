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
			<h5 class="text-center" style="display: inline;">Report By Ledger</h5>
		</header>

		<!-- <div class="row"> -->
		<div style="background-color: #89CFF0" class="card-box">
			<div class="row">
			

				<div class="col-sm-2">
					<label for="from" class="mb-0">From</label>
					<div class="row">
						<input type="date" id="fromDate" class="col-sm-12 form-control-sm" />
					</div>
				</div>

				<div class="col-sm-2">
					<label for="to" class="mb-0">To</label>
					<div class="row">
						<input type="date" id="toDate" class="col-sm-12 form-control-sm" />
					</div>
				</div>
				<div class="col-sm-2 ">
					<label for="to" class="mb-0">Ledger</label>
					<div class="row">
						<select class="selectpicker form-control form-control-sm"
						id="reference" data-live-search="true"
						data-style="btn-light btn-sm border-secondary form-control-sm">
						<option id='reference' value="0">Select Ledger</option>
						<c:forEach items="${ledgerlist}" var="list"
							varStatus="counter">
							<option id='reference' value="${list.reference}">${list.ledgerName}</option>
						</c:forEach>

						</select>
					</div>

				</div>
					
				
				<div class="col-sm-2">
					<button class="btn btn-sm btn-danger mt-3 border"
						data-toggle="modal" onclick="PreviewReport()">
						<i style="color: white" class="fas fa-print"></i> Preview
					</button>
				</div>

		</div>

	</div>
	<!-- </div> -->

	<hr class="my-1">
	
</div>


</div>



<jsp:include page="../include/footer.jsp" />


<script
	src="${pageContext.request.contextPath}/assets/js/accounts/account-report.js"></script>
