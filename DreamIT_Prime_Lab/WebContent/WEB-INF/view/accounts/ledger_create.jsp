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
	
		<div class="alert alert-danger alert-dismissible fade show"
			style="display: none;">
			<p id="dangerAlert" class="mb-0">
				<strong>Wrong!</strong> Something Wrong...
			</p>
		</div>
		<input type="hidden" id="userId" value="<%=userId%>">
		<input type="hidden" id="lineId" value="0">
		<input type="hidden" id="ledgerId" value="0">

		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="card-box">
					<div class="row">
						<div class="col-sm-5 col-md-5 col-lg-5">

							<div class="row ">
								<h2>
									<b>Accounts Ledger Create</b>
								</h2>
							</div>
							
							<div class="form-group mb-0">
								<label class="mb-0"lineName">Head Name</label> 
								<select
											class="selectpicker form-control form-control-sm" id="headId" 	data-live-search="true" data-style="btn-light btn-sm border-secondary form-control-sm" >
											<option id='headId' value="0">Select Head</option>
											<c:forEach items="${acclist}" var="list"
												varStatus="counter">
												<option id='headId' value="${list.parentId}">${list.headName}</option>
											</c:forEach>

							 </select>
							</div>
							
							<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Ledger Name<span style="color: red">*</span></label></span>
									</div>
								<input id="ledgerName" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
							</div>
							
							<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Opening Balance<span style="color: red">*</span></label></span>
									</div>
								<input id="openingBalance" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
							</div>
							
							<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Remark<span style="color: red">*</span></label></span>
									</div>
								<input id="remark" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
							</div>
							
					
							<button type="button" accesskey="S" id="btnSave" class="btn btn-primary btn-sm"
								onclick="saveAction()"><span style="text-decoration:underline">S</span>ave</button>

							<button type="button" accesskey="U" id="btnEdit" class="btn btn-primary btn-sm"
								onclick="editAction()" disabled><span style="text-decoration:underline">U</span>pdate</button>
							<button type="button" id="btnRefresh"
								class="btn btn-primary btn-sm" accesskey="R" onclick="refreshAction()"> <span style="text-decoration:underline">R</span>efresh</button>
								

						</div>
						<div class="col-sm-7 col-md-7 col-lg-7 shadow ">
							<div class="input-group my-2">
								<input type="text" class="form-control"
									placeholder="Search Ledger Name"
									aria-describedby="findButton" id="search" name="search">
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
												<th scope="col">Group Name</th>
												<th scope="col">Ledger Name</th>
												<th scope="col">edit</th>
											</tr>
										</thead>
										<tbody id="dataList">
  											<c:forEach items="${ledgerlist}" var="list" varStatus="counter">
												<tr>
													<td>${counter.count}</td>
													<td >${list.headName}</td>
													<td >${list.ledgerName}</td>
													<td>
													<input type='hidden' id='headId${list.ledgerId}' value='${list.headId}'/>
													<input type='hidden' id='ledgerId${list.ledgerId}' value='${list.ledgerId}'/>
													<input type='hidden' id='ledgerName${list.ledgerId}' value='${list.ledgerName}'/>
													<input type='hidden' id='openingBalance${list.ledgerId}' value='${list.openingBalance}'/>
													<input type='hidden' id='remark${list.ledgerId}' value='${list.remark}'/>
													<i class="fa fa-edit"
														onclick="setData(${list.ledgerId})">
													</i></td>
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
	src="${pageContext.request.contextPath}/assets/js/accounts/ledger-create.js"></script>