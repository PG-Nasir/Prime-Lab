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

				<ul class="nav nav-tabs" id="mytabs" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						id="hematologyTab" data-toggle="tab" href="#assetItemWise_tab"
						role="tab" aria-controls="assetItemWise" aria-selected="true">Add
							Asset (Item Wise)</a></li>
					<li class="nav-item"><a class="nav-link"
						id="#locationWise_tab" data-toggle="tab" href="#locationWise_tab"
						role="tab" aria-controls="assetLocationWise" aria-selected="false">Add
							Asset(Location Wise)</a></li>
					<li class="nav-item"><a class="nav-link" id="serologyTab"
						data-toggle="tab" href="#serology_tab" role="tab"
						aria-controls="serology" aria-selected="false">Service</a></li>

				</ul>



			</div>
		</div>

		<input type="hidden" id="userId" value="<%=userId%>"> <input
			type="hidden" id="employeeAutoId" value="0"> <input
			type="hidden" id="userAutoId" value="0">

		<!-- Item Wise Asset Start -->
		<div class="tab-content pt-1">
			<div id="assetItemWise_tab" class="tab-pane active row mt-2">
				<div class="col-lg-12">
					<div class="card">

						<div class="card-body">
							<div class="row">
								<div class="col-sm-5">

									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="assetName">Asset Name<span
													style="color: red">*</span></label></span>
										</div>
										<input id="assetName" type="text" class="form-control"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">
									</div>

									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Asset Type<span
													style="color: red">*</span></label></span>
										</div>

										<select class="form-select" id="assetType"
											style="width: 300px;">
											<option id="assetType" value="0">None</option>
											<option id="assetType" value="1">Electronics</option>
											<option id="assetType" value="2">Equipment</option>
											<option id="assetType" value="3">Furniture</option>
											<option id="assetType" value="4">Hardware</option>
											<option id="assetType" value="5">Machinery</option>
											<option id="assetType" value="6">Software</option>
											<option id="assetType" value="7">Tools</option>
										</select>

									</div>

									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Location<span
													style="color: red">*</span></label></span>
										</div>
										<input id="location" type="text" class="form-control"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">
									</div>

									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Brand</label></span>
										</div>
										<input id="brand" type="text" class="form-control col-md-3"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">

										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Model</label></span>
										</div>
										<input id="model" type="text" class="form-control"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">
									</div>


									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Serial No<span
													style="color: red">*</span></label></span>
										</div>
										<input id="serialNo" type="text" class="form-control col-md-4"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">

										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Status<span
													style="color: red">*</span></label></span>
										</div>
										<select class="form-select" id="status">
											<option id="status" selected value="0"></option>
											<option id="status" value="In Use">In Use</option>
											<option id="status" value="In Storage">In Storage</option>
											<option id="status" value="Loaned Out">Loaned Out</option>
											<option id="status" value="Out For Repair">Out For
												Repair</option>
										</select>
									</div>



									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Condition<span
													style="color: red">*</span></label></span>
										</div>
										<select class="form-select" id="Condition">
											<option id="Condition" selected value="0"></option>
											<option id="Condition" value="New">New</option>
											<option id="Condition" value="Good">Good</option>
											<option id="Condition" value="Fair">Fair</option>
											<option id="Condition" value="Poor">Poor</option>
										</select>

										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Note</label></span>
										</div>
										<input id="note" type="text" class="form-control"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">
									</div>

									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Vendor<span
													style="color: red">*</span></label></span>
										</div>

										<select id="vendor" class="form-control selectpicker"
											aria-label="Sizing example input" data-size="5"
											data-selected-text-format="count>2" data-actions-box="true"
											aria-describedby="inputGroup-sizing-sm"
											data-live-search="true"
											data-style="btn-light btn-sm border-secondary">
											<option value="0">Select Vendor</option>
											<c:forEach items="${vendorList}" var="v">
												<option id="moduleName" value="${v.vendorId}">${v.vendorName}</option>
											</c:forEach>
										</select>
									</div>


									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">PO Number<span
													style="color: red">*</span></label></span>
										</div>
										<input id="poNumber" type="text" class="form-control col-md-3"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">

										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Date<span style="color: red">*</span></label></span>
										</div>

										<input id="purchaseDate" type="date"
											class="form-control customDate" placeholder="dd/mm/yyyy"
											data-date-format="DD MMM YYYY"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">


									</div>

									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Purchase Value<span
													style="color: red">*</span></label></span>
										</div>
										<input id="purchaseValue" type="text"
											class="form-control col-md-3"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">

										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Qty<span style="color: red">*</span></label></span>
										</div>
										<input id="qty" type="text" class="form-control"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">
									</div>


									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Market Value<span
													style="color: red">*</span></label></span>
										</div>
										<input id="marketValue" type="text"
											class="form-control col-md-3"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">

										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Scrap Value</label></span>
										</div>
										<input id="scrapValue" type="text" class="form-control"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">
									</div>

									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Depreciation Method<span
													style="color: red">*</span></label></span>
										</div>
										<select class="form-select" id="depreciationMethod"
											style="width: 240px;">
											<option id="depreciationMethod" value="0">None</option>
											<option id="depreciationMethod" value="1">Straight-Line</option>
											<option id="depreciationMethod" value="2">Declining
												Balance</option>
											<option id="depreciationMethod" value="3">Reducing
												balance</option>
											<option id="depreciationMethod" value="4">Sum-of-the-Years
												Digits</option>
										</select>

									</div>

									<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="name">Estimate Life<span
													style="color: red">*</span></label></span>
										</div>
										<select class="form-select" id="estimateLife">
											<option id="estimateLife" value="0">None</option>
											<option id="estimateLife" value="1 Year">1 Year</option>
											<option id="estimateLife" value="2 Year">2 Year</option>
											<option id="estimateLife" value="3 Year">3 Year</option>
											<option id="estimateLife" value="4 Year">4 Year</option>
											<option id="estimateLife" value="5 Year">5 Year</option>
											<option id="estimateLife" value="6 Year">6 Year</option>
											<option id="estimateLife" value="7 Year">7 Year</option>
											<option id="estimateLife" value="8 Year">8 Year</option>
											<option id="estimateLife" value="9 Year">9 Year</option>
											<option id="estimateLife" value="10 Year">10 Year</option>
										</select>

									</div>

									<div class="input-group input-group-sm mb-1">
										<div class="col-sm-12 col-md-12 text-right">
											<button type="button" id="btnSave"
												class="btn btn-primary btn-sm" onclick="saveAction()"
												accesskey="S">
												<span style="text-decoration: underline;"> Save</span>
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
										<div class="card-header">
											<strong> Asset List </strong>
										</div>
										<div class="card-body py-1">
											<div class="row">
												<div class="col-sm-12 col-md-12 col-lg-12 p-0 m-0"
													style="overflow: auto; max-height: 350px;">
													<table class="table table-hover table-bordered table-sm">
														<thead>
															<tr>
																<th class="text-left">#SL</th>
																<th class="text-left">Asset Type</th>
																<th class="text-left">Asset Name</th>
																<th class="text-center">Action</th>
															</tr>
														</thead>
														<tbody id="assetList">
															<c:forEach items="${assetList}" var="v"
																varStatus="counter">
																<tr>
																	<td class="text-left">${counter.count}</td>
																	<td class="text-left">${v.categoryName}</td>
																	<td class="text-left">${v.assetName}</td>
																	<td class="row-index text-center">
																		<button id=''
																			onclick='printAssetDetails(${v.assetId})' class=''>
																			<i class='fa fa-print'></i>
																		</button>
																		<button id='' onclick='testDelete()' class=''>
																			<i class='fa fa-edit'></i>
																		</button>
																		<button id='' onclick='testDelete()' class=''>
																			<i class='fa fa-trash'></i>
																		</button>
																	</td>
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

			<div class="tab-pane" id="locationWise_tab">
				<div class="card">

					<div class="card-body py-1">
						<div class="card-header">
								<div class="input-group input-group-sm mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="inputGroup-sizing-sm"><label
												class="my-0" for="assetName">Location:<span
													style="color: red">*</span></label></span>
										</div>
										<input id="locationName" type="text" class="form-control"
											aria-label="Sizing example input"
											aria-describedby="inputGroup-sizing-sm">
								</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-md-12 col-lg-12 p-0 m-0"
								style="overflow: auto; height: 370px;">
								<table class="table table-hover table-bordered table-sm">
									<thead>
										<tr>
											<th class="text-left">#SL</th>
											<th class="text-left">Asset Type</th>
											<th class="text-left">Asset Name</th>
											<th class="text-left">Brand</th>
											<th class="text-left">Model</th>
											<th class="text-left">Serial No</th>
											<th class="text-left">Status</th>
											<th class="text-left">Condition</th>
											<th class="text-left">Note</th>
											<th class="text-left">Vendor</th>
											<th class="text-left">Purchase No</th>
											<th class="text-left">Purchase Date</th>
											<th class="text-left">Purchase Value</th>
											<th class="text-left">Purchase Qty</th>
											<th class="text-left">Market Value</th>
											<th class="text-left">Scrap Value</th>
											<th class="text-left">Depreciation Method</th>
											<th class="text-left">Estimate Life</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tbody id="locationWiseAssetList">

									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
			</div>

		</div>
		<!-- Item Wise Asset End -->
		<!-- Location Wise Asset Start -->



		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<div class="input-group">
							<input id="search" type="text" class="form-control"
								placeholder="Search User" aria-label="Recipient's username"
								aria-describedby="basic-addon2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fa fa-search"></i></span>
							</div>
						</div>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<table class="table table-hover table-bordered table-sm mb-0">
							<thead>
								<tr>
									<th>SL#</th>
									<th>Employee Name</th>
									<th>User Name</th>
									<th>Department</th>
									<th>Designation</th>
									<th>Active Status</th>
									<th><span><i class="fa fa-search"></i></span></th>
								</tr>
							</thead>
							<tbody id="poList">
								<c:forEach items="${userList}" var="user" varStatus="counter">
									<tr>
										<td>${counter.count}</td>
										<td>${user.fullName}</td>
										<td>${user.username}</td>
										<td>${user.departmentName}</td>
										<td>${user.designationName}</td>
										<td>${user.activeStatus}</td>
										<td><i class="fa fa-search" style="cursor: pointer;"
											onclick="searchUser('${user.id}')"> </i></td>

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
<jsp:include page="../include/footer.jsp" />
<script
	src="${pageContext.request.contextPath}/assets/js/custom/link.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/asset/asset_process.js"></script>




