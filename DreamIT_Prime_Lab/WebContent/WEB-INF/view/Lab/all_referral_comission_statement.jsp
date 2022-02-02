<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.List"%>
<jsp:include page="../include/header.jsp" />

<%
String userId=(String)session.getAttribute("userId");
String userName=(String)session.getAttribute("userName");
	String bookType=(String) request.getAttribute("bookType");
%>
<div class="page-content-wrapper">

	<input type="hidden" id="userId" value="<%=userId%>">
	<input type="hidden" id="itemAutoId" value="0">


	<div class="card-box pt-2 pb-1">
		<header class="">
			<h5 class="text-center" style="display: inline;">All Referral Commission Statement Summary</h5>
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
					<label for="type" class="mb-0">Doctor Name</label>
					<div class="row">
							<select
											class="selectpicker form-control form-control-sm" id="doctorId" 	data-live-search="true" data-style="btn-light btn-sm border-secondary form-control-sm" >
											<option id='doctorId' value="0">Select Referral</option>
											<c:forEach items="${doctorlist}" var="list"
												varStatus="counter">
												<option id='doctorId' value="${list.doctorId}">${list.doctorName}</option>
											</c:forEach>

							</select>
					</div>
				</div>
				
				<div class="col-sm-3">
					<button class="btn btn-sm btn-success mt-3 border"
						data-toggle="modal" onclick="AllReferralCommissionStatementPreview()">
						<i style="color: white" class="fas fa-print"></i> Preview
					</button>
				</div>
				


		</div>

	</div>
	<!-- </div> -->




</div>
</div>


<jsp:include page="../include/footer.jsp" />


<script
	src="${pageContext.request.contextPath}/assets/js/lab/lab-sale-statement.js"></script>
