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
		<input type="hidden" id="particularId" value="0">


		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="card-box">
					<div class="row">
						<div class="col-sm-5 col-md-5 col-lg-5">

							<div class="row ">
								<h2>
									<b>Particular Create</b>
								</h2>
							</div>
							<div class="form-group mb-1">
								<label class="mb-0">Test Name</label> 
										<select
											class="selectpicker form-control form-control-sm" id="testId" 	data-live-search="true" data-style="btn-light btn-sm border-secondary form-control-sm" >
											<option id='testId' value="0">Select Test</option>
											<c:forEach items="${mainTestlist}" var="list" varStatus="counter">
												<option id='testId' value="${list.testId}">${list.testName}</option>
											</c:forEach>


										</select>
							</div>
							<div class="form-group mb-1">
								<label class="mb-0">Particular:</label> 
										<select
											class="selectpicker form-control form-control-sm" id="particularRefId" 	data-live-search="true" data-style="btn-light btn-sm border-secondary form-control-sm" >
											<option id='particularRefId' value="0">Select Particular</option>
											<c:forEach items="${paticularNameList}" var="list"
												varStatus="counter">
												<option id='particularRefId' value="${list.particularId}">${list.particularName}</option>
											</c:forEach>


										</select>
							</div>	
	
							
							
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Rate<span style="color: red">*</span></label></span>
									</div>
								<input id="rate" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Qty<span style="color: red">*</span></label></span>
									</div>
								<input id="qty" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
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
									style="overflow: auto; max-height: 380px;">
									<table class="table table-hover table-bordered table-sm">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Test Name</th>
												<th scope="col">Particular Name</th>
												<th scope="col">Qty</th>
												<th scope="col">Rate</th>
												<th scope="col">Edit</th>
												<th scope="col">Del</th>
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
	src="${pageContext.request.contextPath}/assets/js/lab/particular-create.js"></script>
<%-- <script
	src="${pageContext.request.contextPath}/assets/js/register/line-create.js"></script> --%>