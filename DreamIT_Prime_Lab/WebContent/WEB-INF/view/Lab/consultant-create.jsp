<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>

<%
String userId=(String)session.getAttribute("userId");
String userName=(String)session.getAttribute("userName");
	
	String Id = (String) request.getAttribute("consultantId");
%>

<jsp:include page="../include/header.jsp" />

<body onload="allConsultantName()">


<div class="page-content-wrapper">
<div class="page-content">

		<input type="hidden" id="userId" value="<%=userId%>">

		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="card-box">
					<div class="row">
						<div class="col-sm-6 col-md-6 col-lg-6">
							<div class="row">
								<h2>
									<b>Consultant Create</b>
								</h2>
							</div>
							<hr class="mt-1 mb-1">
							
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Id<span style="color: red">*</span></label></span>
									</div>
								<input id="id" readonly type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Consultant Name<span style="color: red">*</span></label></span>
									</div>
								<input id="consultantName" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Designation<span style="color: red">*</span></label></span>
									</div>
										<select
											id="designation" class="form-control form-control-sm">
											<option id='doctorCategory' value="Lab Incharge">Lab
												Incharge</option>
											<option id='doctorCategory' value="Consultant">Consultant</option>
										</select>
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">1st Line<span style="color: red">*</span></label></span>
									</div>
								<input id="line1" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">2nd Line<span style="color: red">*</span></label></span>
									</div>
								<input id="line2" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">3rd Line<span style="color: red">*</span></label></span>
									</div>
								<input id="line3" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">4th Line<span style="color: red">*</span></label></span>
									</div>
								<input id="line4" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">5th Line<span style="color: red">*</span></label></span>
									</div>
								<input id="line5" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
							<div class="row mt-1">
								<div class="col-sm-12 col-md-12 col-lg-12">
									<button type="button" id="btnSave"
										class="btn btn-primary btn-sm" onclick="saveAction()">Save</button>
									<button type="button" id="btnEdit"
										class="btn btn-primary btn-sm" onclick="editAction()" disabled>Edit</button>
									<button type="button" id="btnRefresh"
										class="btn btn-primary btn-sm" onclick="refreshAction()">Refresh</button>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-md-6 col-lg-6 shadow ">
							<div class="input-group my-2">
								<input type="text" class="form-control"
									placeholder="Search Consultant" aria-describedby="findButton"
									id="search" name="search">
								<div class="input-group-append">
									<button class="btn btn-primary" type="button" id="findButton">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-12"
									style="overflow: auto; max-height: 600px;">
									<table class="table table-hover table-bordered table-sm">
										<thead>
											<tr>
												<th class="text-center">ID</th>
												<th class="text-left">Consultant Name</th>
												<th class="text-center">Edit</th>
											</tr>
										</thead>
										<tbody id="dataList">

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../include/footer.jsp" />
<script>
	$('.bsdatepicker').datepicker({

	});
</script>
<script
	src="${pageContext.request.contextPath}/assets/js/custom/link.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/lab/consultant-create.js"></script>