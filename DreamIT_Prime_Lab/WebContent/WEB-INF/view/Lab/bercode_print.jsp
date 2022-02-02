<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="pg.services.SettingServiceImpl"%>
<%@page import="java.util.List"%>


<%
	String userId=(String)session.getAttribute("userId");
	String userName=(String)session.getAttribute("userName");
	String fiscalYear = (String) request.getAttribute("fiscalYear");
	String labId = (String) request.getAttribute("labId");
	String linkName=(String)request.getAttribute("linkName");
	String modueMenu=(String)request.getAttribute("modueMenu");
	
	System.out.println("modueMenu "+modueMenu);
%>

<jsp:include page="../include/header.jsp" />

<body onload="checkMenuId('<%=modueMenu%>')">
<div class="page-wrapper">
	<div class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card-box pt-1 pl-1 pb-1">
					<input type="hidden" id="user_hidden"
						value="<%=userId%>"> <input type="hidden"
						id="labbill" value="0"> <input type="hidden"
						id="labfiscalyear" value=""> <input type="hidden"
						id="find" value="0"> <input type="hidden" id="regNo"
						value=""> <input type="hidden" id="ledger" value="">
					<input type="hidden" id="isbank" value=">">
					
									<input type="hidden" id="patientFiscalYear" value=""/>
									<input type="hidden" id="period" value=""/>


					<!-- <div class="tab-pane active" id="main_menu_tab"> -->
						<!-- <div class="main-menu-form"> -->
							<%-- <form onsubmit="return false" id="add_menu_form"> --%>


								<div class="row">


									<div class="col-sm-6">
										<div class="row">

											<div class="col-sm-12">
										<button data-toggle="modal" data-target="#labBillList"
													class="btn btn-info btn-sm" style="height: 30px;">Lab List</button>
											</div>

										</div>
									</div>

								</div>


								<div class="row mt-1">

									<div class="col-sm-6">
										<div class="row">
											<label class="col-sm-3">Patient Name</label>
											<div class="col-sm-9">

												<input id="patientname" readonly type="text" class="form-control-sm">

											</div>
										</div>
									</div>

									<div class="col-sm-3">
										<div class="row">

											<label class="col-sm-4">Mobile</label>
											<div class="col-sm-8">

												<input id="mobile" readonly type="text" class="form-control-sm">

											</div>

										</div>
									</div>


								</div>


								<div class="row">
									<div class="col-sm-6">
										<div class="row">


											<label class="col-sm-3">Age</label>
											<div class="col-sm-9">

												<div class="row">
													<div class="col-sm-3">
														<label class="mb-0">Year</label> <input id="age"
															type="text" readonly class="form-control-sm">
													</div>
													<div class="col-sm-3">
														<label class="mb-0">Month</label> <input id="month"
															type="text" readonly class="form-control-sm">
													</div>
													<div class="col-sm-3">
														<label class="mb-0">Day</label> <input id="day"
															type="text" readonly class="form-control-sm">
													</div>
													<div class="col-sm-3">
														<label class="mb-0">Gender</label> <select id="sex"
															class="form-control form-control-sm">
															<option id='sex' value="Male">Male</option>
															<option id='sex' value="Female">Female</option>
															<option id='sex' value="Other">Other</option>

														</select>
													</div>
												</div>

											</div>

										</div>
									</div>

									<div class="col-sm-3">
										<div class="row">

											<label class="col-sm-4 mt-3">Lab No</label>
											<div class="col-sm-8 mt-3">

												<input id="labNo" readonly type="text" class="form-control-sm">

											</div>

										</div>
									</div>




								</div>

	

	


						<%-- 	</form> --%>
						<!-- </div> -->



						<div class="row mt-1">
								<div class="col-sm-12 col-md-12 col-lg-12"
									style="overflow: auto; max-height: 400px;">
									<table class="display table table-stripped table-sm">
										<thead>
											<tr>
												<th class="text-left">Sl.</th>
												<th class="text-left">Group Name</th>
												<th class="text-left">Test Name</th>
												<th class="text-left"><input class='form-check-input' type='checkbox' id='checkAll' onlick="" >Select Test</th>

											</tr>
										</thead>
										<tbody id="labbill_table">


										</tbody>
									</table>
								</div>
						</div>
						
						<div class="row mt-1">

							

							<div class="col-sm-12">

								<div class="row">

									<div class="col-sm-7">
										<button style="height: 30px; background: black; color: white;"
											class="btn btn-info btn-sm" onclick='PrintBercodeLab()'>Print Bercode</button>
									</div>


								</div>

							</div>

						</div>
				<!-- 	</div> -->
				</div>

			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="indoorPaitentList" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Indoor Paitent
					List</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-sm-12 col-md-12 col-lg-12"
					style="overflow: auto; max-height: 200px;">
					<table class="display table table-stripped table-sm">
						<thead>
							<tr>
								<th class="text-left">Reg. No</th>
								<th class="text-left">Paitent Name</th>
								<th class="text-left">Admission Date/Time</th>
								<th class="text-center">Bed/Cabin</th>
								<th class="text-center">View</th>
							</tr>
						</thead>
						<tbody id="labbill_table">
											<c:forEach items="${indoorRuningPatientList}" var="list" varStatus="counter">
												<tr>
													<td>${list.regNo}</td>
													<td >${list.patientname}</td>
													<td >${list.admissiond_t}</td>
													<td >${list.seatname}</td>
													<td><i class="fa fa-edit"
														onclick="setData(${list.patientId})">
													</i></td>
												</tr>
											</c:forEach>

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


<div class="modal fade" id="labBillList" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Lab Bill List</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-sm-12 col-md-12 col-lg-12"
					style="overflow: auto; max-height: 200px;">
					<table class="display table table-stripped table-sm">
						<thead>
							<tr>
								<th class="text-left">SL#</th>
								<th class="text-left">Lab No</th>
								<th class="text-left">Patient Name</th>
								<th class="text-center">Mobile</th>
								<th class="text-center">Referral</th>
								<th class="text-center">Bill Date</th>
								<th class="text-center">View</th>
								<th class="text-center">Print</th>
							</tr>
						</thead>
						<tbody id="labbill_table">
											<c:forEach items="${billlist}" var="list" varStatus="counter">
												<tr>
													<td>${counter.count}</td>
													<td>${list.labId}</td>
													<td >${list.patientname}</td>
													<td >${list.mobile}</td>
													<td >${list.referralcdegree}</td>
													<td id='dateTime${list.labId}'>${list.billdate}</td>
													<td><i class="fa fa-edit"
														onclick="setLabBillData(${list.labId},${list.fiscalyear})">
													</i></td>
													<td><i class="fa fa-print"
														onclick="labPrint(${list.labId},${list.fiscalyear})">
													</i></td>													
												</tr>
											</c:forEach>

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
	src="${pageContext.request.contextPath}/assets/js/lab/bercode_print.js"></script>


