<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/cursor.png">
    <title>Admin</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/jquery-ui.css">
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/font-awesome-4.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/icofont.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/fullcalendar.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap-datetimepicker.min.css">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap-select.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap-table-expandable.css">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/datepicker.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/style.css">

	<!--favicon-->
	<link rel="icon" href="assets/images/favicon-32x32.png" type="image/png" />
	<!--plugins-->
	<link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
	<link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
	<link href="assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />
	<!-- loader-->
	<link href="assets/css/pace.min.css" rel="stylesheet" />
	<script src="assets/js/pace.min.js"></script>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
	<!-- Icons CSS -->
	<link rel="stylesheet" href="assets/css/icons.css" />
	<!-- App CSS -->
	<link rel="stylesheet" href="assets/css/app.css" />

    <!--[if lt IE 9]>
		<script src="assets/js/html5shiv.min.js"></script>
		<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

  <body>
    <!-- wrapper -->
    <div class="wrapper">
     

	   <div class="container-fluid">
	    <div class="card mb-0">
	     <div class="card-body p-0">
		  <div class="row no-gutters">
			<div class="col-12 col-lg-5 col-xl-4 d-flex align-items-stretch">
			  <div class="card mb-0 shadow-none bg-transparent w-100 login-card rounded-0">
			     <div class="card-body p-md-1">
				  <img src="assets/images/dream.png" width="120" height="80" alt=""/>
					 <h4 class="mt-5"><strong>Welcome To Dream IT</strong></h4>
					 <p>We belived that,The customer’s perception is our reality.</p>
					 <%-- <s:url var="url_login" value="/login" /> --%>
					 
					  <s:url var="url_login" value="/login" />
					 <f:form action="${url_login}" class="login100-form validate-form">
					 
					 <div class="form-group mt-4">
					   <label>Username</label>
					   <input type="hidden" class="form-control"  id="softwarePaymentStatus" name="softwarePaymentStatus"  />
					   <input type="text" class="form-control"  id="name" name="name" value="" placeholder="Enter your Username"/> 
					 </div>
					 <div class="form-group">
					   <label>Password</label>
					   <input type="password" class="form-control" id="password" name="password" value="" placeholder="Enter your password"/>
					 </div>
					 <div class="form-row">
					   <div class="form-group col text-right">
					     <label ><span id="paymentText" style="font-weight:bold;color:red;font-size:20px;"></span></label>
					   </div>
					 </div>
					 			<div class="container-login100-form-btn">
						<button class="btn btn-primary btn-block mt-3" >
							<b>Login</b>
						</button>
					</div>
					</f:form>
					 <!-- <button type="button" class="btn btn-primary btn-block mt-3"><i class='bx bxs-lock mr-1'></i>Login</button> -->

				 </div>
			  </div>
			</div>
			<div class="col-12 col-lg-7 col-xl-8 d-flex align-items-stretch" >
			  <div class="card mb-0 shadow-none bg-transparent w-100 rounded-0">
				  <div class="card-body p-md-1">
				    <div class="text-center" style="margin-top:40px;margin-bottom:20px;"><img src="assets/images/LabManagement.png"  class="img-fluid" alt=""/></div>
					<h5 class="card-title">About Lab Management Software:</h5>
					<p class="card-text">A Laboratory Information Management System (LIMS) is software that allows you to effectively manage samples and associated data. By using a LIMS, your lab can automate workflows, integrate instruments, and manage samples and associated information</p>
				  </div>
			  </div>
			 </div>
		    </div>
		    </div>
			<div class="card-footer bg-transparent px-md-5">
			 <div class="d-flex align-items-center justify-content-between flex-wrap">
			  <ul class="list-inline mb-0">
 				<p class="mb-0">Developed By : <a href="https://themeforest.net/user/codervent" target="_blank">Dream IT</a> </p>
			   </ul>
				

			   </div>
			 </div>
		   </div>
		 </div>


      
    </div>
    <!-- end wrapper -->

  
	<script type="text/javascript" src="assets/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="assets/js/popper.min.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
   	<script type="text/javascript" src="assets/js/Chart.bundle.min.js"></script>
    <script type="text/javascript" src="assets/js/Chart.min.js"></script>
    <script type="text/javascript" src="assets/js/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="assets/js/jquery-ui-1.12.1.js"></script>
    <script type="text/javascript" src="assets/js/jquery.dataTables.min.js"></script> 
   	<script type="text/javascript" src="assets/js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="assets/js/moment.min.js"></script>
    <script type="text/javascript" src="assets/js/select2.min.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap-table-expandable.js"></script>
   	<script type="text/javascript" src="assets/js/app.js"></script>
   	<script type="text/javascript" src="assets/js/common.js"></script>
   	

	<script src="assets/plugins/simplebar/js/simplebar.min.js"></script>
	<script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
	<script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
	<script src="assets/plugins/apexcharts-bundle/js/apexcharts.min.js"></script>
	<script src="assets/js/index2.js"></script>
	<script src="assets/js/app.js"></script> 
	<script type="text/javascript" src="assets/js/login.js"></script>
  </body>
</html>