<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>

<%
String userId=(String)session.getAttribute("userId");
String userName=(String)session.getAttribute("userName");


%>
<jsp:include page="../include/header.jsp" />
<%
	String doctorId=(String) request.getAttribute("doctorId");
%>

<div class="page-content-wrapper">
<div class="page-content">
		<div class="alert alert-success alert-dismissible fade show"
			style="display: none;">
			<p id="successAlert" class="mb-0">
				<strong>Success!</strong> Doctor Name Save Successfully..
			</p>
		</div>
		<div class="alert alert-warning alert-dismissible fade show"
			style="display: none;">
			<p id="warningAlert" class="mb-0">
				<strong>Warning!</strong> Doctor Name Save Successfully.. Name
				Empty.Please Enter Doctor Name...
			</p>
		</div>
		<div class="alert alert-danger alert-dismissible fade show"
			style="display: none;">
			<p id="dangerAlert" class="mb-0">
				<strong>Wrong!</strong> Something Wrong...
			</p>
		</div>
		<input type="hidden" id="userId" value="<%=userId%>">
		<input type="hidden" id="brandId" value="0">
		<input type="hidden" id="doctorId" value="0">

		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="card-box">
					<div class="row">
						<div class="col-sm-6 col-md-6 col-lg-6">

							<div class="row">
								<h2>
									<b> Doctor Create</b>
								</h2>
							</div>
							<hr class="mt-1 mb-1">

							<div class="row">

								<div class="col-sm-6">


									<div class="form-group mb-0">
										<label class="mb-0"><span style="font-weight:bold;color:red;font-size:14px;">*</span>Doctor Name</label> <input id="doctorName"
											type="text" class="form-control-sm">

									</div>
								</div>
								<div class="col-sm-6">

									<div class="form-group mb-0">
										<label class="mb-0">Doctor Code</label> <input id="doctorCode"
											value="<%=doctorId%>"  type="text" class="form-control-sm">


									</div>
								</div>


							</div>

							<div class="row">

								<div class="col-sm-6">


									<div class="form-group mb-0">
										<label class="mb-0"><span style="font-weight:bold;color:red;font-size:14px;">*</span>Degree</label> <input id="degree"
											type="text" class="form-control-sm">


									</div>
								</div>
								<div class="col-sm-6">

									<div class="form-group mb-0">
										<label class="mb-0">Category</label> <select
											id="doctorCategory" class="form-control form-control-sm">

											<option id='doctorCategory' value="0">Select
												Category</option>
											<option id='doctorCategory' value="Referral">Referral</option>
											<option id='doctorCategory' value="Non Referral">Non Referral</option>
											<option id='doctorCategory' value="Extra Referral">Extra Referral</option>	
											<option id='doctorCategory' value="Consultant">Consultant</option>
											<option id='doctorCategory' value="Doctor">Doctor</option>
											<option id='doctorCategory' value="Surgeon">Surgeon</option>
											<option id='doctorCategory' value="Assistant">Assistant</option>
											<option id='doctorCategory' value="Pharmacy">Pharmacy</option>

										</select>


									</div>
								</div>


							</div>


							<div class="row">

								<div class="col-sm-6">


									<div class="form-group mb-0">
										<label class="mb-0">Department</label> <select id="departmentId"
											class="form-control form-control-sm">

											<option id='departmentId' value="0">Select Department</option>
											<option id='departmentId' value="1">Medicine</option>
											<option id='departmentId' value="2">Nero Medicine</option>
											<option id='departmentId' value="3">Nuro Surgery</option>
											<option id='departmentId' value="4">Nurlogist</option>
											<option id='departmentId' value="5">Urologist</option>
											<option id='departmentId' value="6">Conlogist</option>
											<option id='departmentId' value="7">Cardiologist</option>
											<option id='departmentId' value="8">Gynelogist</option>
											<option id='departmentId' value="9">Orthopaedic</option>
											<option id='departmentId' value="10">Surgery</option>
											<option id='departmentId' value="11">Anesthetics</option>
											<option id='departmentId' value="13">Pedretic</option>
											<option id='departmentId' value="14">HEPATOLOGY</option>
											<option id='departmentId' value="15">GASTROLOGY</option>
											<option id='departmentId' value="16">Diabetology</option>
											<option id='departmentId' value="17">Endrocinology</option>
											<option id='departmentId' value="18">Oncology</option>
											<option id='departmentId' value="19">Medicine & Chiled</option>
											<option id='departmentId' value="20">Nephrology</option>

										</select>

									</div>
								</div>
								<div class="col-sm-6">

									<div class="form-group mb-0">
										<label class="mb-0">Address</label> <input id="address"
											type="text" class="form-control-sm">



									</div>
								</div>


							</div>

							<div class="row">

								<div class="col-sm-6">


									<div class="form-group mb-0">
										<label class="mb-0">Religion</label> <select id="religion"
											class="form-control form-control-sm">

											<option id='religion' value="Islam">Islam</option>
											<option id='religion' value="Hinduism">Hinduism</option>
											<option id='religion' value="Buddhism">Buddhism</option>
											<option id='religion' value="Other">Other</option>


										</select>

									</div>
								</div>
								<div class="col-sm-6">

									<div class="form-group mb-0">

										<label class="mb-0">Sex</label> <select id="sex"
											class="form-control form-control-sm">

											<option id='sex' value="Male">Male</option>
											<option id='sex' value="Female">Female</option>
											<option id='sex' value="Other">Other</option>


										</select>


									</div>
								</div>


							</div>


							<div class="row">

								<div class="col-sm-6">


									<div class="form-group mb-0">
										<label class="mb-0">Mobile</label> <input id="mobile"
											type="text" class="form-control-sm">

									</div>
								</div>
								<div class="col-sm-6">

									<div class="form-group mb-0">

										<label class="mb-0 mt-4">Is Active</label> <input
											name="doctorActive" value="1" type="radio"> Yes <input
											name="doctorActive" value="0" type="radio" checked>No


									</div>
								</div>


							</div>
							
							<div class="row">
							
								<div class="col-sm-6">
									<label class="mb-0">Visit Fee</label>
									<input type="text" class="form-control-sm" id="visitFee">
								</div>
								<div class="col-sm-6">
									<label class="mb-0">Room No</label>
									<input type="text" class="form-control-sm" id="roomNo">
								</div>
							
							</div>
							
							<div class="row mt-1">
								<div class="col-sm-12 col-md-12 col-lg-12"
									style="overflow: auto; height: 250px;">
									<table class="table table-hover table-bordered table-sm">
										<thead>
											<tr>
												<th style="width:350px;" class="text-left">Group Name</th>
												<th style="width:100px;" class="text-left">Commission</th>
												<th style="width:100px;" class="text-left">After Commission Deduction</th>
												<th style="width:60px;" class="text-left">Check <input type="checkbox" id='allCheck' onclick="setCheck()" /></th>
											</tr>
										</thead>
										
										<tbody id="doctorCommissionTable">
										<c:forEach items="${labGroupList}" var="list"
														varStatus="counter">
											<tr  class="lab-group-row row-height-30" data-id='${list.groupId}'>
												<td width='240px;'>${list.groupName}<input type="hidden" id='groupName${list.groupId}' class='form-control-sm' value="${list.groupName}"/></td>
												<td width='80px;'><input type="text" id='doctorComission${list.groupId}' class='form-control-sm' value="${list.doctorComission}"/><input type="hidden" id='parentId${list.groupId}' class='form-control-sm' value="${list.parentId}"/></td>
												<td width='80px;'><input type="text" id='doctorComissionDeduction${list.groupId}' class='form-control-sm' value="${list.doctorComission}"/><input type="hidden" id='parentId${list.groupId}' class='form-control-sm' value="${list.parentId}"/></td>
												<td width='60px;'><input class='check'  type="checkbox" id='req-${list.groupId}' /></td>
											</tr>
										</c:forEach>
										</tbody>
										
									</table>
								</div>

							</div>
							<div class="row mt-1">
								<div class="col-sm-12 col-md-12 col-lg-12">
									<button type="button"  id="btnSave" accesskey="S" class="btn btn-primary btn-sm"
								onclick="saveAction()"><span style="text-decoration: underline;">S</span>ave</button>
									<button type="button" id="btnEdit" class="btn btn-primary btn-sm"
								onclick="editAction()" accesskey="U" disabled><span style="text-decoration: underline;">U</span>pdate</button>
									<button type="button" id="btnRefresh"
								class="btn btn-primary btn-sm" accesskey="R" onclick="refreshAction()"><span style="text-decoration: underline;">R</span>efresh</button></div>
							</div>
						</div>
						<div class="col-sm-6 col-md-6 col-lg-6 shadow ">
							<div class="input-group my-2">
								<input type="text" class="form-control"
									placeholder="Search Doctor" aria-describedby="findButton"
									id="search" name="search">
								<div class="input-group-append">
									<button class="btn btn-primary" type="button" id="findButton">
										<i class="fa fa-search"></i>
									</button>
									<button onclick="btnPrintEvent()" class="btn btn-success" type="button" id="btnPrint">
										<i class="fa fa-print"></i>Print Doctor List
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
												<th class="text-center">Sl.</th>
												<th class="text-left">Doctor Name</th>
												<th class="text-left">Degree</th>
												<th class="text-center">Edit</th>
											</tr>
										</thead>
										<tbody id="dataList">

										<c:forEach items="${doctorList}" var="list"
														varStatus="counter">
											<tr>
												<td>${counter.count}</td>
												<td >${list.doctorName}</td>
												<td >${list.degree}</td>
												<td><i class="fa fa-edit"  onclick="setData(${list.doctorId})"> </i></td>
											</tr>
										</c:forEach>
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
<script
	src="${pageContext.request.contextPath}/assets/js/custom/link.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/settings/doctor-create.js"></script>


