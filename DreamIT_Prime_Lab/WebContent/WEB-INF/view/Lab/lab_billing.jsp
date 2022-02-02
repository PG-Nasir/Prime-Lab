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
String edit = (String) request.getAttribute("edit");
String delete = (String) request.getAttribute("delete");
String refund = (String) request.getAttribute("refund");
String discount = (String) request.getAttribute("discount");
	
%>


<jsp:include page="../include/header.jsp" />


<div class="page-content-wrapper">
<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="card-box pt-1 pl-1 pb-1">
					<input type="hidden" id="user_hidden"
						value="<%=userId%>"> 
						<input type="hidden" id="labbill" value="0"> 
						<input type="hidden" id="labfiscalyear" value=""> 
						<input type="hidden" id="actualTestAmount" value=""> 
						<input type="hidden" id="find" value="0"> 
						<input type="hidden" id="regNo" value=""
						value=""> <input type="hidden" id="ledger" value="">
					<input type="hidden" id="isbank" value=">"> <input
						type="hidden" id="patientFiscalYear" value="" /> <input
						type="hidden" id="period" value="" />
					<input type="hidden" id="labcMonth" />
					
					<input type="hidden" id="editStatus" value=<%=edit%> />
					<input type="hidden" id="deleteStatus" value=<%=delete%> />
					<input type="hidden" id="refundStatus" value=<%=refund%> />
					<input type="hidden" id="discountStatus" value=<%=discount%> />
					

					<!-- <div class="tab-pane active" id="main_menu_tab"> -->
					<!-- <div class="main-menu-form"> -->
					<%-- <form onsubmit="return false" id="add_menu_form"> --%>


					<div class="row">

						<div class="col-sm-6">

							<div class="row">

								<%-- <label class="col-sm-3">Bill Type</label>
								<div class="col-sm-9">

									<select onfocusout="billtype(this)" id="billType"
										class="form-control form-control-sm">
										<option id='billType' value="2">Outdoor</option>
										<option id='billType' value="1">Indoor</option>

									</select>
								</div> --%>
								
								
								<div class="col-sm-3">
									<label style="margin-left:5px;width:50px;">Bill No</label>
									<input id="labId"
										style="width:40px;background: yellow; color: black; text-size: 18px; font-weight: bold;"
										readonly type="text" value="<%=labId%>"
										class="form-control-sm">

								</div>
								
								<div class="col-md-9 col-lg-9 col-sm-9">
										<label style="margin-left:15px;width:30px;">Year</label>				<select 
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
									
									<label >Month</label>				
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
									
									<input id="sLabId"
										style="width:90px;background: yellow; color: black; text-size: 18px; font-weight: bold;"
										type="number" value="<%=labId%>"
										class="form-control-sm">
									
								<!-- 	<button data-toggle="modal" data-target="#indoorPaitentList"
										class="btn btn-info btn-sm btn-block" style="height: 30px;">Indoor
										Paitent List</button> -->
								</div>
								
							</div>

						</div>

						<!-- <div class="col-sm-2">
							<div class="row">
								<div class="col-sm-12">
									<button data-toggle="modal" data-target="#indoorPaitentList"
										class="btn btn-info btn-sm" style="height: 30px;">Indoor
										Paitent List</button>
								</div>
							</div>
						</div> -->
						<div class="col-sm-6">
							<div class="row">

								<div class="col-sm-12">
									<button style="height: 30px; background: green;"
										class="btn btn-info btn-sm" onfocusout="counterValue(this)"
										id="btncounter1" value="1"
										onclick="counterWisePendingTestWithPatientInfo(this)">C1</button>
									<button style="height: 30px; background: green;"
										class="btn btn-info btn-sm" onfocusout="counterValue(this)"
										id="btncounter2" value="2"
										onclick="counterWisePendingTestWithPatientInfo(this)">C2</button>
									<button style="height: 30px; background: green;"
										class="btn btn-info btn-sm" onfocusout="counterValue(this)"
										id="btncounter3" value="3"
										onclick="counterWisePendingTestWithPatientInfo(this)">C3</button>
									<button style="height: 30px; background: green;"
										class="btn btn-info btn-sm" onfocusout="counterValue(this)"
										id="btncounter4" value="4"
										onclick="counterWisePendingTestWithPatientInfo(this)">C4</button>
									<button style="height: 30px; background: green;"
										class="btn btn-info btn-sm" onfocusout="counterValue(this)"
										id="btncounter5" value="5"
										onclick="counterWisePendingTestWithPatientInfo(this)">C5</button>
									<button style="height: 30px; background: green;"
										class="btn btn-info btn-sm" onfocusout="counterValue(this)"
										id="btncounter6" value="6"
										onclick="counterWisePendingTestWithPatientInfo(this)">C6</button>

									
									<label class="mb-0">D. Date</label> <input
										id="deliverydatetime" style="width:130px;" type="date" class="form-control-sm">
									
					
										<select 
										id="dtime" data-live-search="true" style="width:60px;height:30px;"
										>
										<option id='dtime' value="8 PM">8 PM</option>
										<option id='dtime' value="1 AM">1 AM</option>
										<option id='dtime' value="2 AM">2 AM</option>
										<option id='dtime' value="3 AM">3 AM</option>
										<option id='dtime' value="4 AM">4 AM</option>
										<option id='dtime' value="5 AM">5 AM</option>
										<option id='dtime' value="6 AM">6 AM</option>
										<option id='dtime' value="7 AM">7 AM</option>
										<option id='dtime' value="8 AM">8 AM</option>
										<option id='dtime' value="9 AM">9 AM</option>
										<option id='dtime' value="10 AM">10 AM</option>
										<option id='dtime' value="11 AM">11 AM</option>
										<option id='dtime' value="12 AM">12 AM</option>
										<option id='dtime' value="1 PM">1 PM</option>
										<option id='dtime' value="2 PM">2 PM</option>
										<option id='dtime' value="3 PM">3 PM</option>
										<option id='dtime' value="4 PM">4 PM</option>
										<option id='dtime' value="5 PM">5 PM</option>
										<option id='dtime' value="6 PM">6 PM</option>										
										<option id='dtime' value="7 PM">7 PM</option>
										<option id='dtime' value="9 PM">9 PM</option>
										<option id='dtime' value="10 PM">10 PM</option>
										<option id='dtime' value="11 PM">11 PM</option>
										<option id='dtime' value="12 PM">12 PM</option>


									</select>
									
										
									
				<%-- 					<select  id="findFiscalYear"
										class="form-control form-control-sm">
										<option id='findFiscalYear' value="2020">2020</option>
										<option id='findFiscalYear' value="2021">2021</option>
										<option id='findFiscalYear' value="2022">2022</option>
										<option id='findFiscalYear' value="2023">2023</option>
										<option id='findFiscalYear' value="2024">2024</option>
										<option id='findFiscalYear' value="2025">2025</option>
										<option id='findFiscalYear' value="2026">2026</option>

									</select> --%>
								</div>

							</div>
						</div>

					</div>


					<div class="row">

						<div class="col-sm-6">
							<div class="row">
								<label class="col-sm-3">Patient Name</label>
								<div class="col-sm-9">

									<input id="patientname" type="text" class="form-control-sm">

								</div>
							</div>
						</div>

						<div class="col-sm-3">
							<div class="row">
					
								<label class="col-sm-4">Address</label>
								<div class="col-sm-8">

									<input id="address" type="text" class="form-control-sm">

								</div>

							</div>
						</div>
						<div class="col-sm-3">
							<div class="row">

								<button data-toggle="modal" data-target="#labBillList"
									class="btn btn-info btn-sm" style="height: 30px;">Lab
									List</button>

								<input id="labfind" onclick="LabNoBlank()"  type="number" placeholder='Enter Bill No'
									style="background: yellow; font-weight: bold; text-size: 16px; margin-left: 2px; width: 160px;"
									class="form-control-sm">
							</div>
						</div>

					</div>


					<div class="row mt-1">
						<div class="col-sm-6">
							<div class="row">


								<label class="col-sm-3">Age</label>
								<div class="col-sm-9">


									
									<label style="margin-left:5px;" class="mb-0">Y</label> <input id="age" style="width:55px;" type="text"
												class="form-control-sm">
												
									<label class="mb-0">M</label> <input id="month" style="width:55px;" type="text" class="form-control-sm">	
												
									<label class="mb-0">D</label> <input id="day" type="text" style="width:50px;" class="form-control-sm">
									
									<label class="mb-0">Gender</label> 
								
									<select id="sex"
												style="width:80px;height:30px;">
												<option id='sex' value="Male">Male</option>
												<option id='sex' value="Female">Female</option>
												<option id='sex' value="Other">Other</option>

									</select>
	
		<%-- 								<div class="col-sm-3">
											<label class="mb-0">Year</label> <input id="age" type="text"
												class="form-control-sm">
										</div>
										<div class="col-sm-3">
											<label class="mb-0">Month</label> <input id="month"
												type="text" class="form-control-sm">
										</div>
										<div class="col-sm-3">
											<label class="mb-0">Day</label> <input id="day" type="text"
												class="form-control-sm">
										</div>
										<div class="col-sm-3">
											<label class="mb-0">Gender</label> <select id="sex"
												class="form-control form-control-sm">
												<option id='sex' value="Male">Male</option>
												<option id='sex' value="Female">Female</option>
												<option id='sex' value="Other">Other</option>

											</select>
										</div> --%>
										


								</div>

							</div>
						</div>

						<div class="col-sm-3">
							<div class="row">


								<label class="col-sm-4 ">Mobile</label>
								<div class="col-sm-8 ">

									<input id="mobile" type="text" class="form-control-sm">

								</div>
								
								
			

							</div>
						</div>

						<div class="col-sm-3">

							<div class="row">

								<label class="col-sm-7">Bed/Cabin</label>
								<div class="col-sm-5">

									<input id="bedcabin" type="text" class="form-control-sm">

								</div>

							</div>


						</div>


					</div>

					<div class="row mt-1">

						<div class="col-sm-6">
							<div class="row">

								<label class="col-sm-3">Referral</label>
								<div class="col-sm-9 eventInsForm_Ledger">

										<input type='text' style="height:30px;width:315px;border:0.3px solid #32CD32;"  placeholder='Search Doctor' aria-describedby='findButton' id='referral' >
										<button style="height: 30px; background: yellow; color: black;"
								class="btn btn-info btn-sm" onclick='addRerralDoctor()' ">+</button>
								
								
<%-- 									<select class="selectpicker form-control form-control-sm"
										id="referral" data-live-search="true"
										data-style="btn-light btn-sm border-secondary form-control-sm">
										<option id='referral' value="0">Select Referral</option>
										<c:forEach items="${doctorlist}" var="list"
											varStatus="counter">
											<option value="${list.doctorId}">${list.doctorName}</option>
										</c:forEach>

									</select> --%>

								</div>
							</div>

						</div>

						<div class="col-sm-3">
							<div class="row">

								<label class="col-sm-4">Degree</label>
								<div class="col-sm-8">

									<input id="referraldegree"  type="text"
										class="form-control-sm">

								</div>

							</div>

						</div>

						<div class="col-sm-3">
							<div class="row">

								<label class="col-sm-7">Fiscal Year</label>
								<div class="col-sm-5">

									<input id="fiscalyear" readonly type="text"
										value="<%=fiscalYear%>" class="form-control-sm">

								</div>

							</div>

						</div>

					</div>

					<div class="row mt-1">


						<div class="col-sm-6">
							<div class="row">

								<label class="col-sm-3">C Referral</label>
								<div class="col-sm-9 eventInsForm_Ledger">
								
								<input type='text' style="height:30px;width:315px;border:0.3px solid #32CD32;" placeholder='Search Doctor' aria-describedby='findButton' id='cReferral' >
								<button style="height: 30px; background: yellow; color: black;"
								class="btn btn-info btn-sm" onclick='addCRerralDoctor()' >+</button>
								
<%-- 									<select class="selectpicker form-control form-control-sm"
										id="cReferral" data-live-search="true"
										data-style="btn-light btn-sm border-secondary form-control-sm">
										<option id='cReferral' value="0">Select Comission
											Referral</option>
										<c:forEach items="${doctorlist}" var="list"
											varStatus="counter">
											<option id='cReferral' value="${list.doctorId}">${list.doctorName}</option>
										</c:forEach>

									</select> --%>

								</div>

							</div>
						</div>

						<div class="col-sm-3">
							<div class="row">

								<label class="col-sm-4">Degree</label>
								<div class="col-sm-8">

									<input id="referralcomissiondegree"  type="text"
										class="form-control-sm">

								</div>

							</div>
						</div>

						<div class="col-sm-3">
							<div class="row">

								<%-- <label class="col-sm-7">Lab No</label>
								<div class="col-sm-5">

									<input id="labId"
										style="background: yellow; color: black; text-size: 18px; font-weight: bold;"
										readonly type="text" value="<%=labId%>"
										class="form-control-sm">

								</div> --%>
								
								<label class="col-sm-7">Bill Type</label>
								<div class="col-sm-5">

									<select onfocusout="billtype(this)" id="billType"
										class="form-control form-control-sm">
										<option id='billType' value="2">Outdoor</option>
										<option id='billType' value="1">Indoor</option>

									</select>
								</div>

							</div>
						</div>

					</div>
					
					
										<div class="row mt-1">


						<div class="col-sm-6">
							<div class="row">

								<label class="col-sm-3">Extra Com.</label>
								<div class="col-sm-9 eventInsForm_Ledger">
								
								<input type='text' style="height:30px;width:315px;border:0.3px solid #32CD32;" placeholder='Search Doctor' aria-describedby='findButton' id='extraCommission' >
								<button style="height: 30px; background: yellow; color: black;"
								class="btn btn-info btn-sm" onclick='addCRerralDoctor()' >+</button>
								


								</div>

							</div>
						</div>

						<div class="col-sm-3">
							<div class="row">

								<label class="col-sm-4">Degree</label>
								<div class="col-sm-8">

									<input id="referralcomissiondegree"  type="text"
										class="form-control-sm">

								</div>

							</div>
						</div>

						<div class="col-sm-3">
							<div class="row">

								<%-- <label class="col-sm-7">Lab No</label>
								<div class="col-sm-5">

									<input id="labId"
										style="background: yellow; color: black; text-size: 18px; font-weight: bold;"
										readonly type="text" value="<%=labId%>"
										class="form-control-sm">

								</div> --%>
								
								<label class="col-sm-7"></label>
								<div class="col-sm-5">


								</div>

							</div>
						</div>

					</div>

					<div class="row mt-1">

						<div class="col-sm-6">
							<div class="row">
								<label class="col-sm-3">Test Name</label>
								<div class="col-sm-9 eventInsForm_maintest">

									<input type='text' style="height:30px;width:380px;border:0.3px solid #32CD32;"  placeholder='Search Test' aria-describedby='findButton' id='testId' >

<%-- 									<select class="selectpicker form-control form-control-sm"
										id="testId" data-live-search="true"
										data-style="btn-light btn-sm border-secondary form-control-sm">
										<option id='testId' value="0">Select Test</option>
										<c:forEach items="${mainTestlist}" var="list"
											varStatus="counter">
											<option id='testId' value="${list.testId}">${list.testName}</option>
										</c:forEach>

									</select> --%>

								</div>
							</div>
						</div>

						<button style="height: 30px; background: yellow; color: black;"
								class="btn btn-info btn-sm" onclick='AddPatientInforWithTest()' accesskey="A"><span style="text-decoration: underline;">A</span>dd</button>
						
						</div>



					<%-- 	</form> --%>
					<!-- </div> -->



					<div style="height: 180px;overflow:auto;" class="row mt-1">
						<div class="col-sm-12 col-md-12 col-lg-12"
							>
							<table  class="table tableFixHead table-stripped table-sm">
								<thead>
									<tr>
										<th class="text-left">Sl.</th>
										<th class="text-left">Test Name</th>
										<th class="text-left">Qty</th>
										<th class="text-left">Rate</th>
										<th class="text-left">Discount</th>
										<th class="text-left">Amount</th>
										<th class="text-left">Delete</th>
										<th class="text-left">Refund</th>
										<th class="text-left">Top</th>
									</tr>
								</thead>
								<tbody id="labbill_table">


								</tbody>
							</table>
						</div>
					</div>

					<div class="row mt-1">

						<div class="col-sm-12">

							<div class="row">
									<label style="margin-left:10px;" class="mb-0">Total</label> <input id="totalamount"
										style="margin-left:5px;margin-right:10px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;"
										readonly type="text" class="form-control-sm">
										
										<label class="mb-0">Discount%</label> 
									<input
										id="percentdiscount" style="width:100px;margin-right:10px;background: black; color: white; text-size: 18px; font-weight: bold;" onkeyup="setPayable()" type="text"
										class="form-control-sm">
										
													<label class="mb-0">M.Discount</label> <input
										id="manualdiscount" style="margin-left:6px;margin-right:10px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;" onkeyup="setPayable()" type="text"
										class="form-control-sm">
										
										<label class="mb-0">Paid</label> <input id="paid" type="text"
										onkeyup="setDuesValue(this)" style="margin-left:35px;margin-right:10px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;" class="form-control-sm">
							
										<label class="mb-0">Due</label><input id="dues"
										style="margin-left:27px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;"
										readonly type="text" class="form-control-sm">
										
																			<label style="margin-left:5px;" class="mb-0">Advance</label> <input id="advance"
										style="margin-left:5px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;" readonly type="text" class="form-control-sm">
										
<!-- 								<div class="col-sm-2">
									<label class="mb-0">Total</label> <input id="totalamount"
										style="width:90px;background: black; color: white; text-size: 18px; font-weight: bold;"
										readonly type="text" class="form-control-sm">
								</div>
								<div class="col-sm-3">
									<label class="mb-0">Discount%</label> 
									<input
										id="percentdiscount" style="width:90px;background: black; color: white; text-size: 18px; font-weight: bold;" onkeyup="setPayable()" type="text"
										class="form-control-sm">
								</div>
								<div class="col-sm-3">
									<label class="mb-0">M.Discount</label> <input
										id="manualdiscount" style="width:90px;background: black; color: white; text-size: 18px; font-weight: bold;" onkeyup="setPayable()" type="text"
										class="form-control-sm">
								</div>
								<div class="col-sm-2">
									<label class="mb-0">Paid</label> <input id="paid" type="text"
										onkeyup="setDuesValue(this)" style="width:90px;background: black; color: white; text-size: 18px; font-weight: bold;" class="form-control-sm">
								</div>
								<div class="col-sm-2">
									<label class="mb-0">Dues</label> 
									<input id="dues"
										style="width:90px;background: black; color: white; text-size: 18px; font-weight: bold;"
										readonly type="text" class="form-control-sm">
								</div>
								<div class="col-sm-2">
									<label class="mb-0">Advance</label> <input id="advance"
										readonly type="text" class="form-control-sm">
								</div> -->
							</div>

						</div>

						<div class="col-sm-12">

							<div class="row">
							
							<label style="margin-left:168px;" class="mb-0">In_Taka</label> <input id="perdiscount_taka"
										style="margin-left:5px;margin-right:10px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;"
										readonly type="text" class="form-control-sm">
										
										
							<label class="mb-0">In_Taka</label> 
							<input id="mdiscount_tata"
										readonly type="text" style="margin-left:30px;margin-right:10px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;" class="form-control-sm"/>
							
							<label class="mb-0">Payable</label> <input id="totalpayable"
										readonly type="text" style="margin-left:10px;margin-right:10px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;" class="form-control-sm">
						
						
							<label class="mb-0">Refund</label> <input id="refund"  style="margin-left:5px;margin-right:10px;width:100px;background: black; color: white; text-size: 18px; font-weight: bold;" readonly
										type="text" class="form-control-sm">
										
		
						
		<!-- 						<div class="col-sm-2">
									<label class="mb-0">Grand</label>
								</div>
								<div class="col-sm-2">
									<label class="mb-0">In_Taka</label> <input
										id="perdiscount_taka" readonly type="text"
										class="form-control-sm">
								</div>
								<div class="col-sm-2">
									<label class="mb-0">In_Taka</label> <input id="mdiscount_tata"
										readonly type="text" style="background: black; color: white; text-size: 18px; font-weight: bold;" class="form-control-sm">
							
								</div>
								<div class="col-sm-2">
									<label class="mb-0">Payable</label> <input id="totalpayable"
										readonly type="text" style="background: black; color: white; text-size: 18px; font-weight: bold;" class="form-control-sm">
						
								</div>
								<div class="col-sm-2">
									<label class="mb-0">Refund</label> <input id="refund" readonly
										type="text" class="form-control-sm">
								</div> -->
			
							</div>

						</div>
						<div class="col-sm-12">

							<div class="row">

								<div class="col-sm-12">
								
									<label style="margin-left:0px;width:30px;">Type</label>				<select 
										id="reporType" data-live-search="true" style="width:130px;height:30px;"
										>
										<option id='reporType' value="Money Receipt">Money Receipt</option>
										<option id='reporType' value="Lab Slip">Lab Slip</option>
										<option id='reporType' value="All">All</option>
										
										
									</select>
									
									<button style="height: 30px; background: yellow; color: black;"
										class="btn btn-info btn-sm" accesskey="P" onclick='BillPost()'><span style="text-decoration: underline;">P</span>ost</button>
									<button style="height: 30px; background: green; color: white;"
										class="btn btn-info btn-sm"accesskey="U"  onclick='EditPostedBill()'><span style="text-decoration: underline;">U</span>pdate</button>
									<button style="height: 30px; background: black; color: white;"
										class="btn btn-info btn-sm" accesskey="L" onclick='LabBillPrint()'>
										<span style="text-decoration: underline;">L</span>ab Bill Print</button>
									<button style="height: 30px;" class="btn btn-info btn-sm"
										accesskey="R" onclick='btnRefundEvent()'><span style="text-decoration: underline;">R</span>efund</button>
									<button style="height: 30px; background: white; color: black"
										accesskey="C" class="btn btn-info btn-sm" onclick='btnclearevent()'><span style="text-decoration: underline;">C</span>lear</button>
									<button style="height: 30px; background: red; color: black"
										accesskey="D" class="btn btn-info btn-sm" onclick='CounterInfoDelete()'><span style="text-decoration: underline;">D</span>elete
										Counter</button>
									<button style="height: 30px; background: blue; color: white"
										accesskey="T" class="btn btn-info btn-sm" onclick='printTop()'><span style="text-decoration: underline;">T</span>op
										</button>
										
											<label  style="margin-left:4px;margin-right:5px;" class="mb-0">Remark</label> <input id="remark"  style="width:280px;text-size: 18px; font-weight: bold;"
										type="text" class="form-control-sm">
								</div>


							</div>

						</div>

					</div>
					<!-- 	</div> -->
				</div>

			</div>
		</div>
	</div>
</div>

<%-- <div class="modal fade" id="indoorPaitentList" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Indoor Paitent
					List</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-sm-12 col-md-12 col-lg-12"
					style="overflow: auto; max-height: 200px;">
					<table class="display table table-stripped table-sm">
						<thead>
							<tr>
								<th class="text-left">Reg. No</th>
								<th class="text-left">Paitent Name</th>
								<th class="text-left">Admission Date/Time</th>
								<th class="text-center">Bed/Cabin</th>
								<th class="text-center">View</th>
							</tr>
						</thead>
						<tbody id="indoorpatient_table">
							<c:forEach items="${indoorRuningPatientList}" var="list"
								varStatus="counter">
								<tr>
									<td>${list.regNo}</td>
									<td>${list.patientname}</td>
									<td>${list.admissiond_t}</td>
									<td>${list.seatname}</td>
									<td><i class="fa fa-edit"
										onclick="setData(${list.patientId})"> </i></td>
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
</div> --%>


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
								<input type="text" class="form-control"
									placeholder="Search Bill" aria-describedby="findButton"
									id="labsearch" name="search">
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
								<th style="width:100px;" >Patient Name</th>
								<th class="text-center">Mobile</th>
								<th class="text-center">Referral</th>
								<th class="text-center">Bill Date</th>
								<th class="text-center">View</th>
								<th class="text-center">Print</th>
							</tr>
						</thead>
						<tbody id="labbilllist_table">
							<c:forEach items="${billlist}" var="list" varStatus="counter">
								<tr>
									<td>${counter.count}</td>
									<td>${list.labId}</td>
									<td style="width:100px;">${list.patientname}</td>
									<td>${list.mobile}</td>
									<td>${list.referralcdegree}</td>
									<td ><input type="hidden" id='dateTimeValue${list.labId}' value="${list.billdate}"/>${list.billdate}</td>
									<td><i class="fa fa-edit"
										onclick="setLabBillData(${list.labId},${list.fiscalyear},'${list.cMonth}')">
									</i></td>
									<td><i class="fa fa-print"
										onclick="labPrint(${list.labId},${list.fiscalyear},'${list.cMonth}')"> </i></td>
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

<script src="${pageContext.request.contextPath}/assets/js/lab/lab.js"></script>


