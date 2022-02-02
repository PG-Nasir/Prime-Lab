<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>

<%
String userId=(String)session.getAttribute("userId");
String userName=(String)session.getAttribute("userName");

%>

<%-- <body onload="checkMenuId('<%=modueMenu%>')"> --%>
<jsp:include page="../include/header.jsp" />


<div class="page-content-wrapper">
<div class="page-content">

		<input type="hidden" id="userId" value="<%=userId%>">
		<input type="hidden" id="lineId" value="0">
		<input type="hidden" id="subTestId" value="0">
	
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="card-box">
					<div class="row">
						<div class="col-sm-5 col-md-5 col-lg-5">

							<div class="row ">
								<h2>
									<b>Sub Test Create</b>
								</h2>
							</div>
							<div class="form-group mb-0">
								<label class="mb-0">Sub Test Name</label>
								
								<textarea class="form-control" id="subTestName" rows="3"></textarea>
								

							</div>


							<div class="row">
								<div class="col-sm-4">
									<div class="form-group mb-0">
										<label class="mb-0">Calculate</label> <input type="text"
											class="form-control-sm" id="calculate" name="text">
									</div>
								</div>
								<div class="col-sm-8">
									<div class="form-group  mb-0">
										<label class="mb-0">Parent Test</label>
										<select
											class="selectpicker form-control form-control-sm" id="testId" 	data-live-search="true" data-style="btn-light btn-sm border-secondary form-control-sm" >
											<option id='testId' value="0">Select Group</option>
											<c:forEach items="${mainTestlist}" var="list"
												varStatus="counter">
												<option id='testId' value="${list.testId}">${list.testName}</option>
											</c:forEach>


										</select>
									</div>
								</div>

							</div>


							<div class="row">

								<div class="col-sm-4">
									<div class="form-group mb-1">
										<label for="unit" class="mb-0">Unit</label>
										<input type="text" class="form-control-sm" id="unit"
											name="text">
									</div>
								</div>


								<div class="col-sm-8">
									<div class="form-group mb-1">
										<label for="unit" class="mb-0">Normal
											Range</label>
											 <textarea class="form-control" id="normalRange" rows="3"></textarea>
			
									</div>
								</div>
							</div>
							<div class="row">

								<div class="col-sm-4">
									<div class="form-group mb-1">
										<label for="sorting" class="mb-0">Sorting</label>
										<input type="text" class="form-control-sm" id="sorting"
											name="text">
									</div>
								</div>
								
							</div>
							<button type="button" id="btnSave" class="btn btn-primary btn-sm"
								onclick="saveAction()">Save</button>

							<button type="button" id="btnEdit" class="btn btn-primary btn-sm"
								onclick="editAction()" disabled>Edit</button>
							<button type="button" id="btnRefresh"
								class="btn btn-primary btn-sm" onclick="refreshAction()">Refresh</button>

						</div>
						<div class="col-sm-7 col-md-7 col-lg-7 shadow ">
							<div class="input-group my-2">
								<input type="text" class="form-control"
									placeholder="Search Test Name" aria-describedby="findButton"
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
												<th scope="col">#</th>
												<th scope="col">Test Name</th>
												<th scope="col">Sub Test Name</th>
												<th scope="col">Unit</th>
												<th scope="col">Sorting</th>
												<th scope="col">edit</th>
												<th scope="col">Delete</th>
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
<script
	src="${pageContext.request.contextPath}/assets/js/lab/subtest-create.js"></script>
<%-- <script
	src="${pageContext.request.contextPath}/assets/js/register/line-create.js"></script> --%>