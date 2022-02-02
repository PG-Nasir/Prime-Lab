<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="pg.services.SettingServiceImpl"%>
<%@page import="java.util.List"%>
<jsp:include page="../include/header.jsp" />



<div class="page-wrapper">
	<div class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card-box">
	



				<div class="panel-body">	
				<div class="row">
					

						<table align="center">
							<tr>

								<td><label for="text">Bill Type </label>	</td>
								<td>
									<select  id="billType"
												class="form-control">
												<option id='billType' value="2">Outdoor</option>
												<option id='billType' value="1">Indoor</option>

									</select>					
								</td>

								<td><div class="feild">Start Date</div></td>
								<td>
								<div class='input-group date bsdatepicker'>
										<input id='startdate' class="form-control" /> <span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
								</div>								
								</td>

								<td><div class="feild">End Date</div></td>
								<td>
								<div  class='input-group date bsdatepicker'>
										<input id='enddate' class="form-control" /> <span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
								</div>
								</td>
								<td>  <button type="submit" onclick="labsalescashstatementreport()" class="btn btn-info">Submit</button></div></td>
							</tr>

							</table>
						
					</div>
					
					  <div class="row pt-25">
							<div class="col-lg-12">
								<div class="card-box">
									<div class="card-block">
										
										<div class="card-header text-center">
							           
									        <div  class="row">
										         <div style="width:30px;" class="col-lg-1 col-sm-1">
										 			  <img style="width:80px;hieght:80px;" src="assets/img/cursor.png" alt="logo" class="float-left">
										        </div>
										         <div class="col-lg-11 col-sm-11">
										             <h5>SURGISCOPE HOSPITAL LTD. (Unit-2)</h5>
								           			      445/466, Katalgonj, Chawkbazar <br>
								           				  031 652201, 031 652203, 2555101-5
										        </div>
									        </div>
									        
									        <h1 class="card-title text-bold">Lab Sales Cash Statement For <span id='startdatep'>2020-05-15</span> To <span id='enddatep'>2020-05-15</span></h1>
							
							           </div>
							           
										<table class="table table-stripped">
											<thead>
												<tr>
													<th class="text-center">Sl.</th>
													<th class="text-left">Date/Time</th>
													<th class="text-left">Bill No</th>
													<th class="text-left">Bill Date</th>
													<th class="text-left">Patient Name</th>
													<th class="text-center">Username</th>
													<th class="text-right">Amount</th>
												</tr>
											</thead>
											<tbody id="labsalescashstatement_table">
												
											</tbody>
										</table>
									</div>
									
									<div class="card-block">
									<button type="submit" id="btnprint" style="display:none;" onclick="Print()" class="btn btn-info">Print</button>
									</div>
								</div>
							</div>
						</div>
					
					
				</div>

					</div>
				</div>
			</div>
		</div>


			<script>
				$('.bsdatepicker').datepicker({

				});
			</script>
			<jsp:include page="../include/footer.jsp" />
			
			<script
	src="${pageContext.request.contextPath}/assets/js/hospital/labstatement.js"></script>


				