<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="pg.services.SettingServiceImpl"%>
<%@page import="pg.model.WareInfo"%>
<%@page import="pg.model.Module"%>
<%@page import="pg.model.Login"%>
<%@page import="java.util.List"%>

<%
	String userId = (String) session.getAttribute("userId");
	String userName = (String) session.getAttribute("userName");
%>

<jsp:include page="../include/header.jsp" />

<script type="text/javascript"> var contexPath = "<%=request.getContextPath()%>";
</script>

<div class="page-content-wrapper">
<div class="page-content">

		<div class="row mt-0">
			<div class="col-lg-12 d-flex justify-content-between">

				<h2 >Add New Test Information </h2>

			</div>
		</div>

<input type="hidden" id="userId" value="<%=userId%>">
		<input type="hidden" id="lineId" value="0">
		<input type="hidden" id="testId" value="0">
		
		<div class="row mt-2">
			<div class="col-lg-12">
				<div class="card">

					<div class="card-body">
						<div class="row">
							<div class="col-sm-5">

								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0" for="name">Test Name<span style="color: red">*</span></label></span>
									</div>
									
									<input id="testName" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
					

								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Discount?<span style="color: red">*</span></label></span>
									</div>
										<select
											class="form-control form-control-sm" id="discountAllow" 	data-live-search="true" data-style="btn-light btn-sm border-secondary form-control-sm" >
											<option id='discountAllow' value="1">Yes</option>
											<option id='discountAllow' value="0">No</option>
	

										</select>
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Group Name<span style="color: red">*</span></label></span>
									</div>
										<select
											class="selectpicker form-control form-control-sm" id="groupId" 	data-live-search="true" data-style="btn-light btn-sm border-secondary form-control-sm" >
											<option id='groupId' value="0">Select Group</option>
											<c:forEach items="${grouplist}" var="list"
												varStatus="counter">
												<option id='groupId' value="${list.groupId}">${list.groupName}</option>
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
											style="width:80px;" class="my-0 text-left" for="menuName">Doctor Com.<span style="color: red">*</span></label></span>
									</div>
								<input id="doctorCommission" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Unit<span style="color: red">*</span></label></span>
									</div>
								<input id="unit" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Normal Range<span style="color: red">*</span></label></span>
									</div>
								<input id="normalRange" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								

								
								<div class="input-group input-group-sm mb-1">
			<button type="button" id="btnSave" class="btn btn-primary btn-sm"
								onclick="saveAction()">Save</button>

							<button type="button" id="btnEdit" class="btn btn-success btn-sm"
								onclick="editAction()" disabled>Edit</button>
							<button type="button" id="btnRefresh"
								class="btn btn-default btn-sm" onclick="refreshAction()">Refresh</button>						

								</div>
								


							</div>

							<div class="col-sm-7">
								<div class="card">
									
							<div class="row ">
							
								
								<div class="col-sm-6">
									<div class="form-group mb-0">
										<label class="mb-0">Group Name</label> 
										<select
											class="selectpicker form-control form-control-sm" id="findGroupId" 	data-live-search="true" data-style="btn-light btn-sm border-secondary form-control-sm" >
											<option id='findGroupId' value="0">Select Group</option>
											<c:forEach items="${grouplist}" var="list"
												varStatus="counter">
												<option id='findGroupId' value="${list.groupId}">${list.groupName}</option>
											</c:forEach>


										</select>
									</div>
								</div>
								
								<div class="col-sm-3" style="margin-top:15px;">
								<button type="button" id="btnTestView" class="btn btn-success btn-sm"
									onclick="btnTestView()">View</button>
	
								<button type="button" id="btnTestPrint" class="btn btn-danger btn-sm"
									onclick="btnTestPrint()" >Print</button>								
								</div>
								
		
							
							</div>
							
							<div class="input-group input-group-sm mb-1">
							<input type="text" class="form-control"
									placeholder="Search Group/Test Name"
									aria-describedby="findButton" id="search" name="search">
								<div class="input-group-append">
									<button class="btn btn-primary" type="button" id="findButton">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
								

								
									<div class="card-body py-1">
										<div class="row">
											<div class="col-sm-12 col-md-12 col-lg-12 p-0 m-0"
												style="overflow: auto; max-height: 350px;">
									<table class="table table-hover table-bordered table-sm">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Group Name</th>
												<th scope="col">Test Name</th>
												<th scope="col">Rate</th>
												<th scope="col">edit</th>
											</tr>
										</thead>
										<tbody id="dataList">
 											<c:forEach items="${testlist}" var="list" varStatus="counter">
												<tr>
													<td>${counter.count}</td>
													<td >${list.headName}</td>
													<td >${list.testName}</td>
													<td  >${list.rate}</td>
													<td>
													<input type='hidden' id='headId${list.testId}' value='${list.headId}'/>
													<input type='hidden' id='testName${list.testId}' value='${list.testName}'/>
													<input type='hidden' id='rate${list.testId}' value='${list.rate}'/>
													<input type='hidden' id='normalRange${list.testId}' value='${list.normalRange}'/>
													<input type='hidden' id='unit${list.testId}' value='${list.unit}'/>
													<input type='hidden' id='doctorCommission${list.testId}' value='${list.doctorCommission}'/>
													<input type='hidden' id='discountAllow${list.testId}' value='${list.discountAllow}'/>
													<i class="fa fa-edit"
														onclick="setData(${list.testId})">
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
</div>

<!-- Modal -->
<div class="modal fade" id="bankAdd" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Add New Bank Name</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
							
							<div class="col-sm-12 col-md-12 col-lg-12 p-0">
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Bank Name<span style="color: red">*</span></label></span>
									</div>
									<input id="newBankName" type="text" class="form-control col-md-6"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm"  >
										
														<button type="button" id="btnSave"
									class="btn btn-primary btn-sm" onclick="bankSaveAction()"
									accesskey="A">
									<span style="text-decoration: underline;"> Add</span>
								</button>
								

								<button type="button" id="btnEdit"
									class="btn btn-success btn-sm" onclick="bankEditAction()"
									accesskey="U" style="display: none;">
									<span style="text-decoration: underline;"> Update</span>
								</button>
								
								</div>
								</div>
							
				<div class="col-sm-12 col-md-12 col-lg-12 p-0"
					style="overflow: auto; max-height: 200px;">
					<table class="display table table-bordered table-sm">
						<thead>
							<tr>
								<th style='width:80px;' class="text-left">SL#</th>
								<th style='width:300px;' class="text-left">Bank Name</th>
								<th style='width:100px;' class="text-left">Edit</th>
							</tr>
						</thead>
						<tbody id="bankList">

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

</div>
</div>
<jsp:include page="../include/footer.jsp" />
<script
	src="${pageContext.request.contextPath}/assets/js/custom/link.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/lab/test-create.js"></script>




