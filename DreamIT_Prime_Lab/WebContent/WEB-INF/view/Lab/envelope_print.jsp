<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>

<%

String linkName=(String)request.getAttribute("linkName");
String modueMenu=(String)request.getAttribute("modueMenu");
System.out.println("modueMenu "+modueMenu);

%>


<body onload="checkMenuId('<%=modueMenu%>')">

<jsp:include page="../include/header.jsp" />

<div class="page-wrapper">
	<div class="content container-fluid">
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



	<div class="card-box pt-2 pb-1">
		<header class="">
			<h5 class="text-center" style="display: inline;">Referral Wise Envelope</h5>
		</header>

		<!-- <div class="row"> -->
		<div style="background-color: #89CFF0" class="card-box pt-1 pb-1 mb-1">
			<div class="row">




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
						data-toggle="modal" onclick="PrintEnvelope()">
						<i style="color: white" class="fas fa-print"></i> Preview
					</button>
				</div>
				


		</div>

	</div>
	<!-- </div> -->




</div>
</div>

</div>
<jsp:include page="../include/footer.jsp" />


<script
	src="${pageContext.request.contextPath}/assets/js/lab/envelope.js"></script>
