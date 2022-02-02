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
%>



<jsp:include page="../include/header.jsp" />

<div class="page-content-wrapper">
<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="card-box p-1">
					<div class="card-box pt-1 pb-1 mb-1">
						<input type="hidden" id="user_hidden" value="<%=userId%>">
						<input type="hidden" id="labbill" value=""> <input
							type="hidden" id="labfiscalyear" value=""> <input
							type="hidden" id="department" value=""> <input
							type="hidden" id="testid" value=""> <input type="hidden"
							id="testName" value="">
							<input type="hidden" id="cmonth" value="">
						<div class="row">
							<div class="col-sm-6 col-md-6 col-lg-6">
								<div class="row" >
								
								<label style="width:60px;" class="mb-0">P.Name</label> <input
												id="patientname" style="width:260px;border:0.3px solid #32CD32;" readonly
												type="text" class="theight-25">
												
								<label class="mb-0">Mobile</label> <input id="mobile"
														readonly style="width:125px;border:0.3px solid #32CD32;" type="text"
														class="theight-25">
												
					
<!-- 									<div class="col-sm-6 pl-0">
										<div class="form-group mb-0">
											<label class="mb-0">Patient Name</label> <input
												id="patientname" style="background: #8FBC8F;" readonly
												type="text" class="theight-25">
										</div>
									</div>
									<div class="col-sm-6 pl-0">
										<div class="row">
											<div class="col-sm-7">
												<div class="form-group mb-0">
													<label class="mb-0">Mobile</label> <input id="mobile"
														readonly style="background: #8FBC8F;" type="text"
														class="theight-25">
												</div>
											</div>
											<div class="col-sm-5 pl-0 pr-0">
												<button data-toggle="modal" data-target="#labBillList"
													class="btn btn-info btn-sm pt-1"
													style="height: 25px; margin-top: 19px;"
													onclick='SubtestSearch()'>Lab Bill List</button>
											</div>
										</div>
									</div> -->
								</div>
								<div class="row mt-1" >
									<label class="theight-25" style="width:60px;">Age</label>				
									<label style="margin-left:5px;" class="mb-0 mt-1 pr-1 pl-1">Y</label> <input id="age" style="border:0.3px solid #32CD32;width:55px;height:28px;" type="text"
												class="form-control-sm">
												
									<label class="mb-0 mt-1 pr-1 pl-1">M</label> <input id="month" style="border:0.3px solid #32CD32;width:55px;height:28px;" type="text" class="form-control-sm">	
												
									<label class="mb-0 mt-1 pr-1 pl-1" >D</label> <input id="day" type="text" style="border:0.3px solid #32CD32;width:50px;height:28px;" class="form-control-sm">
									
									<label class="mb-0 mt-1 pr-1 pl-1">Gender</label> 
								
									<select id="sex"
												style="width:80px;height:28px;">
												<option id='sex' value="Male">Male</option>
												<option id='sex' value="Female">Female</option>
												<option id='sex' value="Other">Other</option>

									</select>
									
									<button data-toggle="modal" data-target="#labBillList"
													class="btn btn-info btn-sm pt-1"
													style="margin-left:8px;height: 25px;"
													onclick='SubtestSearch()'>Bill List</button>
												
			<%-- 										<div class="col-sm-3">
														<label class="mb-0">Year</label> <input id="age"
															type="text" style="background: #8FBC8F;" readonly
															class="theight-25">
													</div>
													<div class="col-sm-3">
														<label class="mb-0">Month</label> <input id="month"
															type="text" style="background: #8FBC8F;" readonly
															class="theight-25">
													</div>
													<div class="col-sm-3">
														<label class="mb-0">Day</label> <input id="day"
															type="text" style="background: #8FBC8F;" readonly
															class="theight-25">
													</div>
													<div class="col-sm-3">
														<label class="mb-0">Gender</label> <select
															style="background: #8FBC8F;" id="sex"
															class="form-control theight-25 pt-0">
															<option id='sex' value="1">Male</option>
															<option id='sex' value="2">Female</option>
															<option id='sex' value="3">Other</option>
														</select>
													</div> --%>

								</div>
								<div class="row mt-1" >
											
											<label style="width:60px;" class="mb-0">Referral</label> 
											<input readonly
												id="referral_search" style="width:296px;border:0.3px solid #32CD32;"
												onkeyup="doctor_search(this,1)"
												style="url('${pageContext.request.contextPath}/assets/img/search.png') left center no-repeat;padding-left:35px"
												class="theight-25" type="text">
												
											<label class="mb-0">Fiscal Year</label> <input
												id="fiscalyear" style="width:60px;background: #8FBC8F;" readonly
												type="text" value="<%=fiscalYear%>" class="theight-25">
												
			<%-- 						<div class="col-sm-3">
										<div class="form-group mb-0">
											<label class="mb-0">Fiscal Year</label> <input
												id="fiscalyear" style="background: #8FBC8F;" readonly
												type="text" value="<%=fiscalYear%>" class="theight-25">
										</div>
									</div> --%>
	<!-- 								<div class="col-sm-3">
										<div class="form-group mb-0">
											<label class="mb-0">Lab No</label> <input id="labfind"
												onclick="LabNoBlank()" type="text"
												placeholder='Enter Lab No'
												style="background: yellow; font-weight: bold; text-size: 16px; margin-left: 2px; width: 110px;"
												class="form-control-sm">
										</div>
									</div> -->
								</div>

								 <div class="row mt-1" >
											<label style="width:60px;" class="mb-0">Machine</label> 
											<select style="width:435px;background:yellow;height: 30px;" id="machineId"
															class="form-control theight-25 pt-0">
												<option id='machineId' value="0">Select Machine</option>
											<c:forEach items="${machineList}" var="list"
												varStatus="counter">
												<option id='machineId' value="${list.machineId}">${list.machineName}</option>
											</c:forEach>

											</select>
											
								</div> 
								 <div class="row mt-1">
								<label style="width:60px;" class="mb-0">Title</label> 
											<select style="width:435px;background:yellow;height: 30px;" id="titleId"
															class="form-control theight-25 pt-0">
											<option id='titleId' value="0">Select Title</option>
											<c:forEach items="${reportTitleList}" var="list"
												varStatus="counter">
												<option id='titleId' value="${list.titleId}">${list.titleName}</option>
											</c:forEach>

											</select>
								</div>

							</div>
							<div class="col-sm-6 col-md-6 col-lg-6 table-responsive"
								style="overflow: auto; height: 190px;">
								
							<div class="row" style="height: 43px;">

							<label class="mt-1 pl-1 pr-3" style="width:40px;">Year</label>				<select 
										id="sFiscalYear" data-live-search="true" style="width:60px;height:30px;"
										>
										<option id='sFiscalYear' value="2021">2021</option>
										<option id='sFiscalYear' value="2022">2022</option>
										<option id='sFiscalYear' value="2023">2023</option>
										<option id='sFiscalYear' value="2024">2024</option>
										<option id='sFiscalYear' value="2025">2025</option>
										<option id='sFiscalYear' value="2026">2026</option>
										<option id='sFiscalYear' value="2027">2027</option>
										<option id='sFiscalYear' value="2028">2028</option>
										<option id='sFiscalYear' value="2029">2029</option>
										<option id='sFiscalYear' value="2030">2030</option>
										<option id='sFiscalYear' value="2031">2031</option>
										<option id='sFiscalYear' value="2032">2032</option>
										<option id='sFiscalYear' value="2033">2033</option>
										<option id='sFiscalYear' value="2034">2034</option>
										<option id='sFiscalYear' value="2035">2035</option>
					

									</select>
				
									<label class="mt-1 pl-1 pr-1">Month</label>				
									<select 
										id="sMonth" data-live-search="true" style="width:100px;height:30px;"
										>
										<option id='sMonth' value="January">January</option>
										<option id='sMonth' value="February">February</option>
										<option id='sMonth' value="March">March</option>
										<option id='sMonth' value="April">April</option>
										<option id='sMonth' value="May">May</option>
										<option id='sMonth' value="June">June</option>
										<option id='sMonth' value="July">July</option>
										<option id='sMonth' value="August">August</option>
										<option id='sMonth' value="September">September</option>
										<option id='sMonth' value="October">October</option>
										<option id='sMonth' value="November">November</option>
										<option id='sMonth' value="December">December</option>
		
					

									</select>
									<label class="mb-0 mt-1">Bill No</label>
										<input id="sLabId"
										style="width:90px;background: yellow; color: black; text-size: 18px; font-weight: bold;"
										type="number" value="<%=labId%>"
										class="form-control-sm">
										
										
										 <input id="labfind"
												onclick="LabNoBlank()" type="text"
												placeholder='Enter Bill No'
												style="background: yellow; font-weight: bold; text-size: 16px; margin-left: 2px; width: 110px;"
												class="form-control-sm">
						
								</div>
								
								<table class="table table-hover table-bordered table-sm">
									<thead>
										<tr>
											<th>SL#</th>
											<th>Test Name</th>
											<th>Group Name</th>
											<th>View</th>
											<th>Result Status</th>
										</tr>
									</thead>
									<tbody id="labbill_invetable">
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" id="mytabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							id="hematologyTab" data-toggle="tab" href="#hematology_tab"
							role="tab" aria-controls="hematology" aria-selected="true">Heamatology</a></li>
						<li class="nav-item"><a class="nav-link" id="biochemestryTab"
							data-toggle="tab" href="#biochemestry_tab" role="tab"
							aria-controls="biochemestry" aria-selected="false">Bio-Chemestry</a></li>
						<li class="nav-item"><a class="nav-link" id="serologyTab"
							data-toggle="tab" href="#serology_tab" role="tab"
							aria-controls="serology" aria-selected="false">Serology</a></li>
						<li class="nav-item"><a class="nav-link" id="hormoneTab"
							data-toggle="tab" href="#hormone_tab" role="tab"
							aria-controls="hormone" aria-selected="false">Hormone</a></li>
						<li class="nav-item"><a class="nav-link" id="urineTab"
							data-toggle="tab" href="#urine_tab" role="tab"
							aria-controls="urine" aria-selected="false">Urine R/E</a></li>
						<li class="nav-item"><a class="nav-link" id="stoolTab"
							data-toggle="tab" href="#stool_tab" role="tab"
							aria-controls="stool" aria-selected="false">Stool R/E</a></li>
						<li class="nav-item"><a class="nav-link" id="microbiologyTab"
							data-toggle="tab" href="#microbiology_tab" role="tab"
							aria-controls="microbiology" aria-selected="false">Microbiology</a></li>
						<li class="nav-item"><a class="nav-link" id="immunologyTab"
							data-toggle="tab" href="#immunology_tab" role="tab"
							aria-controls="echochardio" aria-selected="false">Immunology</a></li>
						<li class="nav-item"><a class="nav-link" id="pathologyTab"
							data-toggle="tab" href="#pathology_tab" role="tab"
							aria-controls="pathology" aria-selected="false">Pathology</a></li>

					</ul>

						<div class="tab-content pt-1">

							<div class="tab-pane active" id="hematology_tab">
								<!-- <div class="card-body"> -->
								<div class="tab-content pt-0">
									<div class="tab-pane active" id="HEAMATOLOGY_tab">
										<div class="HEAMATOLOGY-form">
											<form id="add_menu_form">
												<div class="row">
													<div class="col-md-12">
														<div class="row theight-25">
															<label class="col-sm-2 theight-25">HGB</label>
															<div class="col-sm-2">
																<input id="hgb" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">PLT</label>
															<div class="col-sm-2">
																<input id="plt" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-2 theight-25">Sample</label>
															<div class="col-sm-2">
																<select id="sample" class="form-control theight-25 pt-0">
																	<option id='blood' value="1">Blood</option>
																	<option id='urine' value="2">Urine</option>
																</select>
															</div>
														</div>
														<div class="row mt-1 theight-25">
															<label class="col-sm-2 theight-25">ESR</label>
															<div class="col-sm-2">
																<input id="esr" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">MPV</label>
															<div class="col-sm-2">
																<input id="mpv" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25"></label> <label
																class="col-sm-1 theight-25">BT</label>
															<div class="col-sm-2">
																<input id="min" value=" Min  Sec"
																	style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>

														</div>

														<div class="row mt-1 theight-25">
															<label class="col-sm-2 theight-25">Total Count</label>
															<div class="col-sm-2">
																<input id="totalCount" style="background: #8FBC8F;"
																	type="text" class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">PDW</label>
															<div class="col-sm-2">
																<input id="pdw" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25"></label> <label
																class="col-sm-1 theight-25">CT</label>
															<div class="col-sm-2">
																<input id="ctmin" value=" Min  Sec"
																	style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>

														</div>

														<div class="row mt-1 theight-25">
															<label class="col-sm-2">Neutrophlis</label>
															<div class="col-sm-2">
																<input id="neutrophlis" onkeyup="setTotalDC()"
																	style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">PCT</label>
															<div class="col-sm-2">
																<input id="pct" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25"></label> <label
																class="col-sm-1 theight-25">MP</label>
															<div class="col-sm-1">
																<input id="mp" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
														</div>


														<div class="row mt-1 theight-25">
															<label class="col-sm-2 theight-25">Lymphocytes</label>
															<div class="col-sm-2">
																<input id="lymphocytes" onkeyup="setTotalDC()"
																	style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">RBC</label>
															<div class="col-sm-2">
																<input id="rbc" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25"></label> <label
																class="col-sm-1 theight-25">MPC</label>
															<div class="col-sm-1">
																<input id="mpc" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
														</div>

														<div class="row mt-1 theight-25">
															<label class="col-sm-2 theight-25">Monocytes</label>
															<div class="col-sm-2">
																<input id="monocytes" onkeyup="setTotalDC()"
																	style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">PCV</label>
															<div class="col-sm-2">
																<input id="pcv" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
														</div>

														<div class="row mt-1 theight-25">
															<label class="col-sm-2 theight-25">Eosinophils</label>
															<div class="col-sm-2">
																<input id="eosinophils" onkeyup="setTotalDC()"
																	style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">MCV</label>
															<div class="col-sm-2">
																<input id="mcv" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
														</div>

														<div class="row mt-1 theight-25">
															<label class="col-sm-2 theight-25">Basophils</label>
															<div class="col-sm-2">
																<input id="basophils" onkeyup="setTotalDC()"
																	style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">MCH</label>
															<div class="col-sm-2">
																<input id="mch" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
														</div>

														<div class="row mt-1 theight-25">
															<label class="col-sm-2 theight-25">Others</label>
															<div class="col-sm-2">
																<input id="other" onkeyup="setTotalDC()"
																	style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">MCHC</label>
															<div class="col-sm-2">
																<input id="mchc" style="background: #8FBC8F;"
																	type="text" class="theight-25">
															</div>
														</div>
														<div class="row mt-1 theight-25">
															<label
																style="font-weight: bold; font-size: 16px; color: green;"
																class="col-sm-2 theight-25">Total DC</label>
															<div class="col-sm-2">
																<input id="totaldc" readonly
																	style="background: yellow; font-weight: bold; font-size: 16px; color: red;"
																	type="text" class="theight-25">
															</div>
															<label class="col-sm-1 theight-25">CE COUNT</label>
															<div class="col-sm-2">
																<input id="cec" style="background: #8FBC8F;" type="text"
																	class="theight-25">
															</div>
														</div>
													</div>
												</div>
										</div>
									</div>
									</form>
								</div>
								<!-- </div> -->
							</div>


							<div class="tab-pane" id="biochemestry_tab">
								<div class="row">
									<div class="col-md-12">
										<div class="row theight-25">
											<label class="col-sm-1 theight-25">Sample</label>
											<div class="col-sm-3">
												<select id="sample" class="form-control theight-25 pt-0">
													<option id='sample' value="Blood">Blood</option>
													<option id='sample' value="Urine">Urine</option>
													<option id='sample' value="Stool">Stool</option>
													<option id='sample' value="Pus">Pus</option>
													<option id='sample' value="Sputam">Sputam</option>
													<option id='sample' value="Semen">Semen</option>
												</select>
											</div>
											<div class="theight-25 form-check-inline">
												<input id="single-test" style="margin-left: 5px"
													type="radio" class="form-check-input" value="1"> <label
													style="margin-left: 5px" class="form-check-label">Single
													Test</label> <input id="group-test" style="margin-left: 5px"
													type="radio" class="form-check-input" value="2"> <label
													style="margin-left: 5px" style="margin-left:5px"
													class="form-check-label">Group Test</label> <input
													id="diabetes-test" style="margin-left: 5px" type="radio"
													class="form-check-input" value="3"> <label
													style="margin-left: 5px" class="form-check-label">Diabetes
													Test </label>
											</div>
										</div>
										<div class="card-box mt-1 mb-1 p-1">
											<div class="col-sm-12 col-md-12 col-lg-12 p-0"
												style="overflow: auto; max-height: 200px;">
												<table class="table table-hover table-bordered table-sm">
													<thead>
														<tr>
															<th class="text-center">T.P.ID</th>
															<th class="text-left">Test Perticulars</th>
															<th class="text-left">Test Result</th>
															<th class="text-center">GFR Calculate</th>
															<th class="text-center">Flag/Sugar</th>
															<th class="text-center">Normal Range</th>
															<th class="text-center">Uom</th>
															<th class="text-center">Sorting</th>
															<th class="text-center">Test Name</th>

														</tr>
													</thead>
													<tbody id="biologytable">

													</tbody>
												</table>
											</div>
										</div>

										<div class="row mt-1">
											<label class="col-sm-1 theight-25">Note</label>
											<div class="col-sm-8">
												<input id="bio-note" type="text" class="theight-25">
											</div>
										</div>
									</div>
								</div>
							</div>


							<div class="tab-pane" id="serology_tab">
								<div class="row">
									<div class="col-md-12">
										<div class="row theight-25">
											<label class="col-sm-1 theight-25">Sample</label>
											<div class="col-sm-3">
												<select id="sample" class="form-control theight-25 pt-0">
													<option id='sample' value="Blood">Blood</option>
													<option id='sample' value="Urine">Urine</option>
													<option id='sample' value="Stool">Stool</option>
													<option id='sample' value="Pus">Pus</option>
													<option id='sample' value="Sputam">Sputam</option>
													<option id='sample' value="Semen">Semen</option>
												</select>
											</div>
											<div class="theight-25 form-check-inline">
												<input id="single-test" style="margin-left: 5px"
													type="radio" class="form-check-input" value="1"> <label
													style="margin-left: 5px" class="form-check-label">Single
													Test</label> <input id="grou-test" style="margin-left: 5px"
													type="radio" class="form-check-input" value="2"> <label
													style="margin-left: 5px" style="margin-left:5px"
													class="form-check-label">Group Test</label> </label>
											</div>
										</div>
										<div class="card-box mt-1 mb-1 p-1">
											<div class="col-sm-12 col-md-12 col-lg-12 p-0"
												style="overflow: auto; max-height: 200px;">
												<table class="table table-hover table-bordered table-sm">
													<thead>
														<tr>
															<th class="text-center">T.P.ID</th>
															<th class="text-left">Test Perticulars</th>
															<th class="text-left">Test Result</th>
															<th class="text-center">Flag</th>
															<th class="text-center">Normal Range</th>
															<th class="text-center">Uom</th>
															<th class="text-center">Sorting</th>
															<th class="text-center">Test Name</th>

														</tr>
													</thead>
													<tbody id="serologytable">

													</tbody>
												</table>
											</div>
										</div>

										<div class="row mt-1">
											<label class="col-sm-1">Note</label>
											<div class="col-sm-8">
												<input id="ser-note" type="text" class="theight-25">
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="tab-pane" id="hormone_tab">
								<div class="row">
									<div class="col-md-12">
										<div class="row theight-25">
											<label class="col-sm-1">Sample</label>
											<div class="col-sm-3">
												<select id="sample" class="form-control theight-25 pt-0">
													<option id='sample' value="Blood">Blood</option>
													<option id='sample' value="Urine">Urine</option>
													<option id='sample' value="Stool">Stool</option>
													<option id='sample' value="Pus">Pus</option>
													<option id='sample' value="Sputam">Sputam</option>
													<option id='sample' value="Semen">Semen</option>
												</select>
											</div>
											<div class="theight-25 form-check-inline">

												<select id="hormoneReportType"
													class="form-control theight-25 pt-0">
													<option id='hormoneReportType' value="0">General
														Report</option>
													<option id='hormoneReportType' value="1">Confirmatorty
														Report</option>
												</select>


											</div>
										</div>

										<hr class="mt-1 mb-1">

										<div class="row">
											<div class="col-sm-9">
												<div class="col-sm-12 col-md-12 col-lg-12 p-0"
													style="overflow: auto; max-height: 200px;">
													<table class="table table-hover table-bordered table-sm">
														<thead>
															<tr>
																<th class="text-center">T.P.ID</th>
																<th class="text-left">Test Perticulars</th>
																<th class="text-left">Test Result</th>
																<th class="text-center">Flag</th>
																<th class="text-center">Normal Range</th>
																<th class="text-center">Uom</th>
																<th class="text-center">Sorting</th>
																<th class="text-center">Test Name</th>
															</tr>
														</thead>
														<tbody id="hormonetable">

														</tbody>
													</table>
												</div>
												<div class="row mt-1">
													<label class="col-sm-1">Note</label>
													<div class="col-sm-8">
														<input id="hor-note" type="text" class="theight-25">
													</div>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="card-box pb-1 pt-1">
													<div class="row">
														<label class="mb-0 theight-25">Cut Of Value</label> <input
															id="hor-cut-value" type="text" class="theight-25">

													</div>
													<div class="row mt-1">
														<label class="theight-25 mb-0">Patient Sample
															Count</label> <input id="hor-patient-sample-count" type="text"
															class="theight-25">

													</div>

													<div class="row mt-1">
														<label class="theight-25 mb-0">Impression</label> <input
															id="hor-impression" type="text" class="theight-25">
													</div>

												</div>
											</div>
										</div>

									</div>
								</div>
							</div>

							<div class="tab-pane" id="urine_tab">

								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-sm-4">
												<div class="row theight-25">
													<label class="col-sm-12" style="color: red">Physical
														Examication</label>
												</div>

												<hr class="mb-1 mt-0">

												<div class="row theight-25">
													<label class="col-sm-4">Sample</label>
													<div class="col-sm-8">
														<select id="sample" class="form-control theight-25 pt-0">
															<option id='sample' value="Urine">Urine</option>
															<option id='sample' value="Blood">Blood</option>
															<option id='sample' value="Stool">Stool</option>
															<option id='sample' value="Pus">Pus</option>
															<option id='sample' value="Sputam">Sputam</option>
															<option id='sample' value="Semen">Semen</option>
														</select>
													</div>
												</div>

												<div class="row mt-1 theight-25">
													<label class="col-sm-4 theight">Quantity</label>
													<div class="col-sm-8">
														<input id="quantity" style="background: #8FBC8F;"
															value="ml" type="text" class="theight-25">
													</div>
												</div>

												<div class="row mt-1 theight-25">
													<label class="col-sm-4 theight-25">Color</label>
													<div class="col-sm-8">
														<input id="color" value="Straw"
															style="background: #8FBC8F;" type="text"
															class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-4 theight-25">Appearance</label>
													<div class="col-sm-8">
														<input id="appearance" style="background: #8FBC8F;"
															value="Clear" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-4 theight-25">Sediment</label>
													<div class="col-sm-8">
														<input id="sediment" style="background: #8FBC8F;"
															value="Nil" type="text" class="theight-25">

													</div>
												</div>
												<div class="row theight-25">
													<label style="color: red;" class="col-sm-12">Casts
														Examication</label>
												</div>
												<hr class="mb-1 mt-0">

												<div class="row theight-25">
													<label class="col-sm-5 theight-25 pt-0">Hyaline
														Cast</label>
													<div class="col-sm-7">
														<input id="hyalinecash" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row mt-1 theight-25">
													<label style="text-align: left;" class="col-sm-5">Granular
														Cells</label>
													<div class="col-sm-7">
														<input id="granularcells" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row mt-1 theight-25">
													<label class="col-sm-5">Fatty</label>
													<div class="col-sm-7">
														<input id="fatty" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-5">WBC Casts</label>
													<div class="col-sm-7">
														<input id="wbccasts" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-5">RBC Casts</label>
													<div class="col-sm-7">

														<input id="rbccasts" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

											</div>
											<div class="col-sm-4">
												<div class="row theight-25">
													<label style="color: red;" class="col-sm-12">Chemical
														Examication</label>
												</div>

												<hr class="mb-1 mt-0">

												<div class="row theight-25">
													<label class="col-sm-6 theight-25">Sp. Gravity</label>
													<div class="col-sm-6">
														<input id="gravity" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">PH</label>
													<div class="col-sm-6">
														<input id="ph" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Sugar</label>
													<div class="col-sm-6">
														<input id="sugar" style="background: #8FBC8F;" value="Nil"
															type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Albumin</label>
													<div class="col-sm-6">
														<input id="albumin" style="background: #8FBC8F;"
															value="Nil" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Ketone Bodies</label>
													<div class="col-sm-6">
														<input id="ketonebodies" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Blood</label>
													<div class="col-sm-6">
														<input id="urineBlood" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Bilirubin</label>
													<div class="col-sm-6">
														<input id="bilirubin" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Uribiliogen</label>
													<div class="col-sm-6">
														<input id="uribiliogen" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Nitrites</label>
													<div class="col-sm-6">
														<input id="nitrites" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Bile Pigment</label>
													<div class="col-sm-6">
														<input id="bilepigment" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Bile Salt</label>
													<div class="col-sm-6">
														<input id="bilesalth" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Exs. Of
														Phosphate</label>
													<div class="col-sm-6">
														<input id="phosphate" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">

													</div>
												</div>

											</div>

											<div class="col-sm-4">
												<div class="row theight-25">
													<label style="color: red;" class="col-sm-12 theight-25">Microscopic
														Examication</label>
												</div>

												<hr class="mb-1 mt-0">

												<div class="row theight-25">
													<label class="col-sm-6 theight-25">RBC</label>
													<div class="col-sm-6">
														<input id="urinRbc" style="background: #8FBC8F;"
															value="Nil/HPF" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Pus Cells</label>
													<div class="col-sm-6">
														<input id="puscells" style="background: #8FBC8F;"
															value="Nil/HPF" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Epithelial Cells</label>
													<div class="col-sm-6">
														<input id="epithelialcells" style="background: #8FBC8F;"
															value="Nil/HPF" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Trichomonas
														Vaginalis</label>
													<div class="col-sm-6">
														<input id="trichomonasVaginalis"
															style="background: #8FBC8F;" value="Not Seen" type="text"
															class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Supermatozoa(Dead)</label>
													<div class="col-sm-6">
														<input id="supermatozoa" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Micro Organism</label>
													<div class="col-sm-6">
														<input id="microorganism" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Fungus</label>
													<div class="col-sm-6">
														<input id="fungus" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25">
													<label style="color: red;" class="col-sm-12 theight-25">Crystals
														Examication</label>
												</div>

												<hr class="mt-0 mb-1">

												<div class="row theight-25">
													<label class="col-sm-6 theight-25">Calcium Oxalate</label>
													<div class="col-sm-6">
														<input id="calciumoxalate" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Uric Acid
														Crystals</label>
													<div class="col-sm-6">
														<input id="uricacidcrystals" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Triple Phosphate</label>
													<div class="col-sm-6">
														<input id="triplephosphate" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Amorph Phosphate</label>
													<div class="col-sm-6">
														<input id="amorphphosphate" style="background: #8FBC8F;"
															value="Nil" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Urate</label>
													<div class="col-sm-6">
														<input id="urate" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">

													</div>
												</div>

											</div>

										</div>
									</div>
								</div>

							</div>

							<div class="tab-pane" id="stool_tab">

								<div class="row">
									<div class="col-sm-12">
										<div class="row">
											<div class="col-sm-4">
												<div class="row theight-25">
													<label style="color: red;" class="col-sm-12 theight-25">Physical
														Examication</label>
												</div>
												<div class="row theight-25">
													<label class="col-sm-6 theight-25">Sample</label>
													<div class="col-sm-6">
														<select id="sample" class="form-control theight-25 pt-0">
															<option id='sample' value="Stool">Stool</option>
															<option id='sample' value="Blood">Blood</option>
															<option id='sample' value="Urine">Urine</option>
															<option id='sample' value="Pus">Pus</option>
															<option id='sample' value="Sputum">Sputum</option>
															<option id='sample' value="Semen">Semen</option>
														</select>
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Consistency</label>
													<div class="col-sm-6">
														<input id="consistency" style="background: #8FBC8F;"
															value="Soft" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Color</label>
													<div class="col-sm-6">
														<input id="stoolColor" style="background: #8FBC8F;"
															value="Brownish" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Odour</label>
													<div class="col-sm-6">
														<input id="odour" style="background: #8FBC8F;"
															value="Offensive" type="text" class="theight-25">
													</div>
												</div>


												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Mucus</label>
													<div class="col-sm-6">
														<input id="stoolMucus" style="background: #8FBC8F;"
															value="(+)" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Blood</label>
													<div class="col-sm-6">
														<input id="stoolBlood" style="background: #8FBC8F;"
															value="Nil" type="text" class="theight-25">

													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Helminths</label>
													<div class="col-sm-6">
														<input id="helminths" style="background: #8FBC8F;"
															value="Nil" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25">
													<label style="color: red;" class="col-sm-12 theight-25">Chemical
														Examication</label>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Reaction</label>
													<div class="col-sm-6">
														<input id="reaction" style="background: #8FBC8F;"
															value="Acidic" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Reaction
														Substance</label>
													<div class="col-sm-6">
														<input id="reducingSubstance" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Occult Blood
														Test</label>
													<div class="col-sm-6">
														<input id="occultBloodTest" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">
													</div>
												</div>


											</div>

											<div class="col-sm-4">


												<hr class="mt-1 mb-1">
												<div class="row theight-25">
													<label style="color: red;" class="col-sm-12 theight-25">Microscopic
														Examication</label>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Protozoa</label>
													<div class="col-sm-6">
														<input id="protozoa" style="background: #8FBC8F;"
															value="Not Done" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Cysts Of E.H</label>
													<div class="col-sm-6">
														<input id="cystseh" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Cysts Of E.Colt</label>
													<div class="col-sm-6">
														<input id="cystsColt" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Cysts Of Giardia</label>
													<div class="col-sm-6">
														<input id="cystsGiardia" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Ova Of (Round
														Worm) AL</label>
													<div class="col-sm-6">
														<input id="ovaoFroundWorm" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Ova Of (Hook
														Worm) A.D</label>
													<div class="col-sm-6">
														<input id="ovaOfHookWorm" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Ova Of (Whip
														Worm) T.T</label>
													<div class="col-sm-6">
														<input id="ovaOfWhipWorm" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Larva Of S.S</label>
													<div class="col-sm-6">
														<input id="larvaOfSs" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Macrophage</label>
													<div class="col-sm-6">
														<input id="macrophage" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

											</div>

											<div class="col-sm-4">
												<div class="row theight-25">
													<label style="color: red;" class="col-sm-12 theight-25">Others
														Examication</label>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Strach</label>
													<div class="col-sm-6">
														<input id="strach" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Vagetiable Cells</label>
													<div class="col-sm-6">
														<input id="vagetiableCells" style="background: #8FBC8F;"
															value="(+)" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Pus Cells</label>
													<div class="col-sm-6">
														<input id="stoolPuscells" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Eithelial</label>
													<div class="col-sm-6">
														<input id="eithelial" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Fat Droplets</label>
													<div class="col-sm-6">
														<input id="fatDroplets" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Fungi</label>
													<div class="col-sm-6">
														<input id="fungi" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">W.B.C</label>
													<div class="col-sm-6">
														<input id="stoolWbc" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">RBC</label>
													<div class="col-sm-6">
														<input id="stoolRbc" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Muscle Fibers</label>
													<div class="col-sm-6">
														<input id="muscleFibers" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Charot Leyden
														Crystals</label>
													<div class="col-sm-6">
														<input id="charotLeyden" style="background: #8FBC8F;"
															value="Not Seen" type="text" class="theight-25">
													</div>
												</div>


											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="tab-pane" id="microbiology_tab">
								<div class="row">
									<div class="row">
										<label style="margin-left: 250px;">Specimen</label> <select
											id="specimen">
											<option id='specimen' value="Blood">Blood</option>
											<option id='specimen' value="Urine">Urine</option>
											<option id='specimen' value="Stool">Stool</option>
											<option id='specimen' value="Pus">Pus</option>
											<option id='specimen' value="Sputum">Sputum</option>
											<option id='specimen' value="Semen">Semen</option>
										</select> <label>Report Category</label> <select
											id="microreportcategory">
											<option id='microreportcategory' value="0">Growth
												Report</option>
											<option id='microreportcategory' value="1">Non-Growth
												Report</option>
											<option id='microreportcategory' value="3">General
												Report</option>
											<option id='microreportcategory' value="4">Confirmatorty
												Report</option>

										</select>

									</div>

									<hr class="mt-1 mb-1">
									<div class=col-sm-8>
										<div class="row">
											<div class="col-sm-6">
												<div class="row">
													<div class="col-sm-8">
														Antibiotic
													</div>
													<div class="col-sm-4">
														Sensitivity
													</div>
												</div>

												<div class="row theight-25">
													<label class="col-sm-8 theight-25">Amoxycillin +
														Clavulan Acid</label>
													<div class="col-sm-2">
														<input id="amoxycillinClavulanAcid" style="background: #8FBC8F;"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>
												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Gentamycin</label>
													<div class="col-sm-4">
														<input id="gentamycin" style="background: #8FBC8F;"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Azithromycin</label>
													<div class="col-sm-4">
														<input id="azithromycin" style="background: #8FBC8F;"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row mt-1 theight-25">
													<label class="col-sm-8 theight-25">Ciprofloxacin</label>
													<div class="col-sm-4">
														<input id="ciprofloxacin" style="background: #8FBC8F;"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Meropenem</label>
													<div class="col-sm-4">
														<input id="meropenem" style="background: #8FBC8F;"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Ceftriaxone</label>
													<div class="col-sm-4">
														<input id="ceftriaxone" style="background: #8FBC8F;"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Amikacin</label>
													<div class="col-sm-4">
														<input id="amikacin" style="background: #8FBC8F;"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Piperacillin +
														Tazobactam</label>
													<div class="col-sm-4">
														<input id="piperacillinTazobactam" style="background: #8FBC8F;"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Tigecycline</label>
													<div class="col-sm-4">
														<input id="tigecycline" style="background: #8FBC8F;"
															type="text" class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Ertapenem</label>
													<div class="col-sm-4">
														<input id="ertapenem" style="background: #8FBC8F;"
															type="text" class="theight-25">
													</div>
												</div>

												<!-- <div class="row theight-25 mt-1">
												<label class="col-sm-6 theight-25">Netilimyin</label>
												<div class="col-sm-6">
													<input id="netilimyin" style="background: #8FBC8F;"
														type="text" class="theight-25">
												</div>
											</div>

											<div class="row theight-25 mt-1">
												<label class="col-sm-6 theight-25">Azithromycin</label>
												<div class="col-sm-6">
													<input id="azithromycin" style="background: #8FBC8F;"
														type="text" class="theight-25">
												</div>
											</div> -->

											</div>

											<div class="col-sm-6">
												<div class="row">
													<div class="col-sm-8">
														Antibiotic
													</div>
													<div class="col-sm-4">
														Sensitivity
													</div>
												</div>
												<div class="row theight-25">
													<label class="col-sm-8 theight-25">Levofloxacin</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="levofloxacin" type="text"
															class="theight-25 text-center" value="">
													</div>
												</div>
												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Imipenem</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="imipenem" type="text"
															class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Moxafloxacin</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="moxafloxacin" type="text"
															class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Nalidixic Acid</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="nalidixicAcid" type="text"
															class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Cotrimexazole</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="cotrimexazole" type="text"
															class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Cefuroxime</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="cefuroxime"
															type="text" class="theight-25 text-center" value="">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Ceftazidime</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="ceftazidime" type="text"
															class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Nitrofurantoin</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="nitrofurantoin" type="text"
															class="theight-25 text-center" value="">
													</div>
												</div>


												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Vancomycin</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="vancomycin" type="text"
															class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-8 theight-25">Cefixime</label>
													<div class="col-sm-4">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="cefixime"
															type="text" class="theight-25">
													</div>
												</div>

												<!-- <div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Amicacin(Amibac)</label>
													<div class="col-sm-6">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="amicacin" type="text"
															class="theight-25">
													</div>
												</div>

												<div class="row theight-25 mt-1">
													<label class="col-sm-6 theight-25">Levofloxacin(Levobac)</label>
													<div class="col-sm-6">
														<input style="background: #8FBC8F;"
															style="margin-left: 25px;" id="levofloxacin" type="text"
															class="theight-25">
													</div>
												</div> -->

											</div>
										</div>
									</div>

									<div class=col-sm-4>
										<div class="row ">
											<label style="color: red;" class="col-sm-12">Growth</label>
										</div>
										<div class="row theight-25">
											<label class="col-sm-6 theight-25">Organism A</label>
											<div class="col-sm-6">


												<input style="background: #8FBC8F;" id="organism_a"
													value="Incubation Temperature:37 c#Incubation time:48 hours#Bacteria isolated:E.coli#Colony count:1X10^5CFU/ml"
													type="text" class="theight-25">

											</div>
										</div>

										<div class="row theight-25 mt-1">
											<label class="col-sm-6 theight-25">Organism B</label>
											<div class="col-sm-6">
												<input style="background: #8FBC8F;" id="organism_b" value=""
													type="text" class="theight-25">
											</div>
										</div>

										<div class="row ">
											<label style="color: red;" class="col-sm-12">Non-Growth</label>
										</div>

										<div class="row theight-25 mt-1">

											<div class="col-sm-6">
												<input style="background: #8FBC8F;" id="micro-nongrowth"
													value="Culture shows No Growth of bacteria after 48Hours of incubation at 37 &deg; c in aerobic condition"
													type="text" class="theight-25">
											</div>
										</div>

										<div class="row ">
											<label style="color: red;" class="col-sm-12 theight-25">Confirmatory</label>
										</div>
										<div class="row theight-25">
											<label class="col-sm-6 theight-25">Cut Of Value</label>
											<div class="col-sm-6">
												<input style="background: #8FBC8F;" id="micro-cut-value"
													type="text" class="theight-25">
											</div>
										</div>

										<div class="row theight-25 mt-1">
											<label class="col-sm-6 theight-25">Patient Sample</label>
											<div class="col-sm-6">
												<input style="background: #8FBC8F;"
													id="micro-patient-sample" type="text" class="theight-25">
											</div>
										</div>

										<div class="row theight-25 mt-1">
											<label class="col-sm-6">Count_Impression</label>
											<div class="col-sm-6">
												<input style="background: #8FBC8F;"
													id="micro-count-impression" type="text" class="theight-25">
											</div>
										</div>

										<div class="row">
											<label style="color: red;" class="col-sm-12">General</label>
										</div>
										<div class="row theight-25">
											<label class="col-sm-6 theight-25">Puscells</label>
											<div class="col-sm-6">
												<input style="background: #8FBC8F;" id="micro-puscells"
													type="text" class="theight-25">

											</div>
										</div>

										<div class="row theight-25 mt-1">
											<label class="col-sm-6">Epithelles_Cells</label>
											<div class="col-sm-6">
												<input style="background: #8FBC8F;" id="micro-ephithelles"
													type="text" class="theight-25">
											</div>
										</div>

										<div class="row theight-25 mt-1">
											<label class="col-sm-6 theight-25">Result</label>
											<div class="col-sm-6">
												<input style="background: #8FBC8F;" id="micro-result"
													type="text" class="theight-25">
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="tab-pane" id="immunology_tab">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<label style="text-align: left; margin-left: 150px;"
												class="col-sm-1">Sample</label>
											<div class="col-sm-3">
												<select id="immu_sample" class="form-control">
													<option id='immu_sample' value="Blood">Blood</option>
													<option id='immu_sample' value="Stool">Stool</option>
													<option id='immu_sample' value="Urine">Urine</option>
													<option id='immu_sample' value="Pus">Pus</option>
													<option id='immu_sample' value="Sputum">Sputum</option>
													<option id='immu_sample' value="Semen">Semen</option>
												</select>
											</div>
											<div class="form-check-inline">
												<select id="immReportType"
													class="form-control theight-25 pt-0">
													<option id='immReportType' value="0">General
														Report</option>
													<option id='immReportType' value="1">Confirmatorty
														Report</option>
												</select>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-9">
												<div class="col-sm-12 col-md-12 col-lg-12 p-0"
													style="overflow: auto; max-height: 200px;">
													<table class="table table-hover table-bordered table-sm">
														<thead>
															<tr>
																<th class="text-center">T.P.ID</th>
																<th class="text-left">Test Perticulars</th>
																<th class="text-left">Test Result</th>
																<th class="text-center">Flag</th>
																<th class="text-center">Normal Range</th>
																<th class="text-center">Uom</th>
																<th class="text-center">Sorting</th>
																<th class="text-center">Test Name</th>
															</tr>
														</thead>
														<tbody id="immunologytable">

														</tbody>
													</table>
												</div>
												<div class="row mt-1">
													<label class="col-sm-1">Note</label>
													<div class="col-sm-8">
														<input id="img-note" type="text" class="theight-25">
													</div>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="card-box">
													<div class="row">
														<label style="text-align: center" class="col-sm-12">Cut
															Of Value</label>
														<div class="col-sm-12">
															<input id="imm-cut-value" type="text"
																class="form-control">
														</div>
													</div>
													<div style="margin-top: 5px">
														<label style="text-align: center" class="col-sm-12">Patient
															Sample Count</label>
														<div class="row r_padding">
															<div class="col-sm-12">
																<input id="imm-patient-sample-count" type="text"
																	class="form-control">
															</div>
														</div>
													</div>

													<div style="margin-top: 5px">
														<label style="text-align: center" class="col-sm-12">Impression</label>
														<div class="row r_padding">
															<div class="col-sm-12">
																<input id="imm-impression" type="text"
																	class="form-control">
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

					<div class="row mt-1">
						<div class="col-sm-7">
							<label>Print Choice : </label>
							<div class="form-check form-check-inline mr-0">
								<input class="form-check-input" type="radio"
									name="exampleRadios" id="exampleRadios1" value="option1"
									checked> <label class="form-check-label"
									for="inlineCheckbox1">Top</label>
							</div>
							<div class="form-check form-check-inline mr-0">
								<input class="form-check-input" type="radio"
									name="exampleRadios" id="exampleRadios1" value="option1">
								<label class="form-check-label" for="inlineCheckbox2">TestName</label>
							</div>
							<div class="form-check form-check-inline mr-0">
								<input class="form-check-input" type="radio"
									name="exampleRadios" id="exampleRadios1" value="option1">
								<label class="form-check-label" for="inlineCheckbox3">Result</label>
							</div>
							<div class="form-check form-check-inline mr-0">
								<input class="form-check-input" type="radio"
									name="exampleRadios" id="exampleRadios1" value="option1">
								<label class="form-check-label" for="inlineCheckbox3">Footer</label>
							</div>

							<button style="height: 30px;" onclick="resultsave()"
								class="btn btn-success btn-sm px-1">Save</button>
							<button style="height: 30px;" onclick="printEvent()"
								class="btn btn-info btn-sm px-1">Print</button>
							<button style="height: 30px;" onclick="printTopEvent()"
								class="btn btn-danger btn-sm px-1">Top</button>
							<button style="height: 30px;" onclick="refreshEvent()"
								class="btn btn-primary btn-sm px-1">Refresh</button>

						</div>

						<div class="col-sm-5">
							<div style="height: 42px;" class="row">
								<div class="col-sm-4 pr-0 pl-1">
									<div class="form-group">
										<label style="font-size: 15px;" class="mb-0">Lab
											Incharge</label> <select
											class="selectpicker form-control form-control-sm"
											id="inchargeId" data-live-search="true"
											data-style="btn-light btn-sm border-secondary form-control-sm">
											<option id='inchargeId' value="0">Select Incharge</option>
											<c:forEach items="${consultantlist}" var="list"
												varStatus="counter">
												<option id='inchargeId' value="${list.doctorId}">${list.doctorName}</option>
											</c:forEach>

										</select>
									</div>
								</div>
								<div class="col-sm-4 pr-0 pl-1">
									<div class="form-group">
										<label style="font-size: 15px;" class="mb-0">Doctor 1</label>
										<select class="selectpicker form-control form-control-sm"
											id="doctor1" data-live-search="true"
											data-style="btn-light btn-sm border-secondary form-control-sm">
											<option id='doctor1' value="0">Select Consultant</option>
											<c:forEach items="${consultantlist}" var="list"
												varStatus="counter">
												<option id='doctor1' value="${list.doctorId}">${list.doctorName}</option>
											</c:forEach>

										</select>
									</div>
								</div>
								<div class="col-sm-4 pl-1">
									<div class="form-group">
										<label style="font-size: 15px;" class="mb-0">Doctor 2</label>
										<select class="selectpicker form-control form-control-sm"
											id="doctor2" data-live-search="true"
											data-style="btn-light btn-sm border-secondary form-control-sm">
											<option id='doctor2' value="0">Select Consultant</option>
											<c:forEach items="${consultantlist}" var="list"
												varStatus="counter">
												<option id='doctor2' value="${list.doctorId}">${list.doctorName}</option>
											</c:forEach>

										</select>
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

<div class="modal fade" id="labBillList" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
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
					style="overflow: auto; max-height: 400px;">
					<div class="input-group my-2">
						<input type="text" class="form-control" placeholder="Search Bill"
							aria-describedby="findButton" id="search" name="search">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button" id="findButton">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					<hr>
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
							</tr>
						</thead>
						<tbody id="labbill_table">
							<c:forEach items="${billlist}" var="list" varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${list.labId}</td>
									<td>${list.patientname}</td>
									<td>${list.mobile}</td>
									<td>${list.referralcdegree}</td>
									<td id='dateTime${list.labId}'>${list.billdate}</td>
									<td><i class="fa fa-edit"
										onclick="setLabBillData(${list.labId},${list.fiscalyear},'${list.cMonth}')">
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/bootstrap-datetimepicker.js"></script>



<script
	src="${pageContext.request.contextPath}/assets/js/custom/link.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/hospital/setting.js"></script>

<script
	src="${pageContext.request.contextPath}/assets/js/lab/lab-investigation.js"></script>