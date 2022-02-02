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
		<input type="hidden" id="memberId" value="0">

		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="card-box">
					<div class="row">
						<div class="col-sm-6 col-md-6 col-lg-6">

							<div class="row">
								<h2>
									<b> Membership Create</b>
								</h2>
							</div>
							<hr class="mt-1 mb-1">

							<div class="row">

								<div class="col-sm-6">


									<div class="form-group mb-0">
										<label class="mb-0"><span style="font-weight:bold;color:red;font-size:14px;">*</span>Member Name</label> <input id="memberName"
											type="text" class="form-control-sm">

									</div>
								</div>
								<div class="col-sm-6">

									<div class="form-group mb-0">
										<label class="mb-0">Occupation</label> <input id="occupation"
											value=""  type="text" class="form-control-sm">


									</div>
								</div>


							</div>

							<div class="row">

								<div class="col-sm-6">


									<div class="form-group mb-0">
										<label class="mb-0"><span style="font-weight:bold;color:red;font-size:14px;">*</span>Contact</label> 
										<input id="contact"
											type="text" class="form-control-sm">


									</div>
								</div>
								<div class="col-sm-6">

									<div class="form-group mb-0">
										<label class="mb-0">Membership Type</label> <select
											id="membershipType" class="form-control form-control-sm">

											<option id='membershipType' value="0">Select
												Type</option>
											<option id='membershipType' value="Bronze">Bronze</option>
											<option id='membershipType' value="Silver">Silver</option>	
											<option id='membershipType' value="Golden">Golden</option>
											<option id='membershipType' value="Platinum">Platinum</option>
											

										</select>


									</div>
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
									placeholder="Search Member" aria-describedby="findButton"
									id="search" name="search">
								<div class="input-group-append">
									<button class="btn btn-primary" type="button" id="findButton">
										<i class="fa fa-search"></i>
									</button>
									<button onclick="btnPrintEvent()" class="btn btn-success" type="button" id="btnPrint">
										<i class="fa fa-print"></i>Print Member List
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
												<th class="text-left">Member Name</th>
												<th class="text-left">Membership Type</th>
												<th class="text-center">Edit</th>
											</tr>
										</thead>
										<tbody id="dataList">

										<c:forEach items="${memberLList}" var="list"
														varStatus="counter">
											<tr>
												<td>${counter.count}</td>
												<td >${list.memberName}</td>
												<td >${list.membershipType}</td>
												<td><i class="fa fa-edit"  onclick="setData(${list.memberId})"> </i></td>
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
	src="${pageContext.request.contextPath}/assets/js/settings/membership-create.js"></script>


