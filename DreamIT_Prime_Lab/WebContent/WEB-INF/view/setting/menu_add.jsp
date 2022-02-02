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

				<h2 >New Menu</h2>

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
											class="my-0" for="name">Module Name<span style="color: red">*</span></label></span>
									</div>
									
										
										<select class="form-select" id="moduleId" style="width:240px;">
										<option id="moduleId" value="0">None</option>
										<c:forEach items="${ModuleList}" var="v" varStatus="counter">
											 <option id="moduleId" value="${v.moduleId}">${v.moduleName}</option>
										</c:forEach> 

										</select>	
										
										<button type="button"  style="width:40px;" id="btnNewModule"
										class="btn btn-info btn-sm" onclick="newModuleEvent()"
										accesskey="N">
										<span style="text-decoration: underline;">+</span>
										</button>

								</div>
								
								<div class="input-group input-group-sm mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text" id="inputGroup-sizing-sm"><label
											class="my-0" for="menuName">Menu Name<span style="color: red">*</span></label></span>
									</div>
									<input id="menuName" type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-sm" >
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
										<strong> Menu List </strong>
									</div>
									<div class="card-body py-1">
										<div class="row">
											<div class="col-sm-12 col-md-12 col-lg-12 p-0 m-0"
												style="overflow: auto; max-height: 350px;">
												<table class="table table-hover table-bordered table-sm">
													<thead>
														<tr>
															<th class="text-left">#SL</th>
															<th class="text-left" style="width:160px;">Module</th>
															<th class="text-left" style="width:300px;">Menu</th>
															<th class="text-center" style="width:160px;">Action </th>
														</tr>
													</thead>
													<tbody id="menuList">
									 				<c:forEach items="${menuList}" var="v" varStatus="counter">
									 					<tr>
															<td class="text-left">${counter.count}</td>
															<td class="text-left" style="width:160px;">${v.moduleName}</td>
															<td class="text-left" style="width:300px;">${v.menuName}</td>
															<td class="row-index text-center" style="width:160px;">   <button id='' onclick='' class='' ><i class='fa fa-edit'></i></button>  <button id='' onclick='' class='' ><i class='fa fa-trash'></i></button></td>
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


</div>
</div>
<jsp:include page="../include/footer.jsp" />
<script
	src="${pageContext.request.contextPath}/assets/js/custom/link.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/settings/menuadd.js"></script>




