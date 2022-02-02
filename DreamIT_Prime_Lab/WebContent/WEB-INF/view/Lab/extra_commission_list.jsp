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


<jsp:include page="../include/header.jsp" />

<div class="page-content-wrapper">
<div class="page-content">

	
	
	<div class="card-box pt-2 pb-1">
		<header class="">
			<h5 class="text-center" style="display: inline;">Extra Commision Person List</h5>
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

				
				<div class="col-sm-3">
					<button class="btn btn-sm btn-success mt-3 border"
						data-toggle="modal" onclick="extraCommissionListView()">
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
	src="${pageContext.request.contextPath}/assets/js/lab/lab-sale-statement.js"></script>
