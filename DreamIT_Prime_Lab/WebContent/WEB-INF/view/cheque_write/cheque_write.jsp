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

				<h2 >Prepared Your Cheque </h2>

			</div>
		</div>

		<input type="hidden" id="userId" value="<%=userId%>">
			<input type="hidden" id="menuId" value="0">
		<div class="row mt-2">
			<div class="col-lg-12">
				<div class="card">

					<div class="card-body">
						<div class="row">
							<div class="col-sm-5">

								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0" for="name">Bank Name<span style="color: red">*</span></label></span>
									</div>
									
										
										<select class="form-select" id="bankId" style="width:240px;">
										<option id="bankId" value="0">None</option>
										<c:forEach items="${bankList}" var="v" varStatus="counter">
											 <option id="bankId" value="${v.bankId}">${v.bankName}</option>
										</c:forEach> 

										</select>	
										
								
										<button type="button"  style="width:55px;" id="btnNewModule"
										class="btn btn-info btn-sm" 
										accesskey="N" data-toggle="modal" data-target="#bankAdd">
										<span style="text-decoration: underline;">+Add</span>
										</button>

								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Pay To<span style="color: red">*</span></label></span>
									</div>
									<input id="payTo" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Amount<span style="color: red">*</span></label></span>
									</div>
									<input id="amount" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Date<span style="color: red">*</span></label></span>
									</div>
								<input id="chequeWriteDate" type="date" class="form-control customDate" placeholder="dd/mm/yyyy"  data-date-format="DD MMM YYYY"
								aria-label="Sizing example input"
								aria-describedby="inputGroup-sizing-sm">
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Type<span style="color: red">*</span></label></span>
									</div>
										<select class="form-select" id="printCategory" style="width:290px;">
										<option id="printCategory" value="0">None</option>
										<option id="printCategory" value="1">Cross A/C Payee + Or Bearer</option>
										<option id="printCategory" value="2">A/C PAYEE ONLY </option>

										</select>	
								</div>

								
								<div class="input-group input-group-sm mb-1">
							<div class="col-sm-12 col-md-12 text-right">
								<button type="button" id="btnSave"
									class="btn btn-primary btn-sm" onclick="saveAction()"
									accesskey="S">
									<span style="text-decoration: underline;"> Save</span>
								</button>
								
								<button type="button" id="btnSave"
									class="btn btn-info btn-sm" onclick="previewAction()"
									accesskey="P">
									<span style="text-decoration: underline;"> Preview</span>
								</button>

								<button type="button" id="btnEdit"
									class="btn btn-success btn-sm" onclick="editAction()"
									accesskey="E" style="display: none;">
									<span style="text-decoration: underline;"> Edit</span>
								</button>
								<button type="button" id="btnRefresh"
									class="btn btn-secondary btn-sm" onclick="refreshAction()">Refresh</button>
							</div>							

								</div>
								


							</div>

							<div class="col-sm-7">
								<div class="card">
									<div class="card-header bg-info">
										<strong > Cheque List </strong>
									</div>
									<div class="card-body py-1">
										<div class="row">
											<div class="col-sm-12 col-md-12 col-lg-12 p-0 m-0"
												style="overflow: auto; max-height: 350px;">
												<table class="table table-hover table-bordered table-sm">
													<thead>
														<tr>
															<th class="text-left bg-info">#SL</th>
															<th class="text-left bg-info" style="width:200px;">Bank</th>
															<th class="text-left bg-info" style="width:140px;">Pay To</th>
															<th class="text-left bg-info" style="width:100px;">Serverd By</th>
															<th class="text-center bg-info" style="width:120px;">Action </th>
														</tr>
													</thead>
													<tbody id="chequeList">
									 				<c:forEach items="${chequeWriterList}" var="v" varStatus="counter">
									 					<tr>
															<td class="text-left">${counter.count}</td>
															<td class="text-left"  style="width:200px;">${v.bankName}</td>
															<td class="text-left" style="width:140px;">${v.payTo}</td>
															<td class="text-left" style="width:100px;">${v.userName}</td>
															<td class="row-index text-center" style="width:120px;">   <button id='' onclick='printSaveCheque(${v.writerId})' class='' ><i class='fa fa-print'></i></button>  <button id='' onclick='viewData(${v.writerId})' class='' ><i class='fa fa-edit'></i></button></td>
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
										
								
								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											style="width:80px;" class="my-0 text-left" for="menuName">Report Name<span style="color: red">*</span></label></span>
									</div>
									<input id="reportName" type="text" class="form-control col-md-6"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm"  >
										
														<button type="button" id="btnSave"
									class="btn btn-primary btn-sm" onclick="bankSaveAction()"
									accesskey="A">
									<span style="text-decoration: underline;"> Add</span>
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
	src="${pageContext.request.contextPath}/assets/js/chequewriter/chequewriter.js"></script>




