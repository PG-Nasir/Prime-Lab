<%@page import="java.util.ArrayList"%>
<%@page import="pg.model.Login"%>
<%@page import="pg.model.Menu"%>
<%@page import="pg.model.Module"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Base64"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">


<!-- Mirrored from codervent.com/synadmin/demo/index2.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 21 Oct 2020 18:47:10 GMT -->
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<title>To be successful, you have to have your heart in your business, and your business in your heart.</title>

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
</head>
<%
	String userId = (String) session.getAttribute("userId");
	String userName = (String) session.getAttribute("userName");
	List<Menu> list = (List<Menu>) session.getAttribute("menulist");
	List<Module> modulelist = (List<Module>) session.getAttribute("modulelist");
	byte[] decodedBytesUsername = Base64.getDecoder().decode(userName);
	userName = new String(decodedBytesUsername);
	
%>
<body>
	<!-- wrapper -->
	<div class="wrapper">
		<!--header-->
		<header class="top-header">
			<nav class="navbar navbar-expand">
				<div class="left-topbar d-flex align-items-center">
					<a href="javaScript:;" class="toggle-btn">	<i class="bx bx-menu"></i>
					</a>
					<div class="">
						
					</div>
				</div>
				<div class="flex-grow-1 search-bar">
					<div class="input-group">
						<div class="input-group-prepend search-arrow-back">
							
						</div>
						
						<div class="input-group-append">
							<p>You are using Hospital ERP Software!!</P>
						</div>
					</div>
				</div>
				<div class="right-topbar ml-auto">
					<ul class="navbar-nav">
						<li class="nav-item search-btn-mobile">
							<a class="nav-link position-relative" href="javaScript:;">	<i class="bx bx-search vertical-align-middle"></i>
							</a>
						</li>
						<li class="nav-item dropdown dropdown-lg">
							<a class="nav-link dropdown-toggle dropdown-toggle-nocaret position-relative" href="javaScript:;" data-toggle="dropdown">	<span class="msg-count">6</span>
								<i class="bx bx-comment-detail vertical-align-middle"></i>
							</a>
							<div class="dropdown-menu dropdown-menu-right">
								<a href="javaScript:;">
									<div class="msg-header">
										<h6 class="msg-header-title">6 New</h6>
										<p class="msg-header-subtitle">Application Messages</p>
									</div>
								</a>
								<div class="header-message-list">
									<a class="dropdown-item" href="javaScript:;">
										<div class="media align-items-center">
											<div class="user-online">
												<img src="assets/images/avatars/avatar-1.png" class="msg-avatar" alt="user avatar">
											</div>
											<div class="media-body">
												<h6 class="msg-name">Daisy Anderson <span class="msg-time float-right">5 sec
													ago</span></h6>
												<p class="msg-info">The standard chunk of lorem</p>
											</div>
										</div>
									</a>
										



								</div>
								
							</div>
						</li>
						<li class="nav-item dropdown dropdown-lg">
							<a class="nav-link dropdown-toggle dropdown-toggle-nocaret position-relative" href="javaScript:;" data-toggle="dropdown">	<i class="bx bx-bell vertical-align-middle"></i>
								<span class="msg-count">8</span>
							</a>
							<div class="dropdown-menu dropdown-menu-right">
								<a href="javaScript:;">
									<div class="msg-header">
										<h6 class="msg-header-title">8 New</h6>
										<p class="msg-header-subtitle">Application Notifications</p>
									</div>
								</a>
								<div class="header-notifications-list">
									<a class="dropdown-item" href="javaScript:;">
										<div class="media align-items-center">
											<div class="notify bg-light-primary text-primary"><i class="bx bx-group"></i>
											</div>
											<div class="media-body">
												<h6 class="msg-name">New Customers<span class="msg-time float-right">14 Sec
													ago</span></h6>
												<p class="msg-info">5 new user registered</p>
											</div>
										</div>
									</a>


								</div>
								<a href="javaScript:;">
									<div class="text-center msg-footer">View All Notifications</div>
								</a>
							</div>
						</li>
						<li class="nav-item dropdown dropdown-user-profile">
							<a class="nav-link dropdown-toggle dropdown-toggle-nocaret" href="javaScript:;" data-toggle="dropdown">
								<div class="media user-box align-items-center">
									<div class="media-body user-info">
										<p class="user-name mb-0"><%=userName%></p>
										<p class="designattion mb-0">Software Engineer</p>
									</div>
									<i class="bx bx-user"></i><span></span>
									<!-- <img src="assets/images/developer/dev.jpg" class="user-img" alt="user avatar"> -->
								</div>
							</a>
							<div class="dropdown-menu dropdown-menu-right">	<a class="dropdown-item" href="javaScript:;"><i
										class="bx bx-user"></i><span>Profile</span></a>
								<a class="dropdown-item" href="javaScript:;"><i
										class="bx bx-cog"></i><span>Change Password</span></a>
								<a class="dropdown-item" href="javaScript:;"><i
										class="bx bx-cog"></i><span>Settings</span></a>
								<a class="dropdown-item" href="javaScript:;"><i
										class="bx bx-tachometer"></i><span>Dashboard</span></a>

								<div class="dropdown-divider mb-0"></div>	
								<s:url var="url_form" value="/loginout" />
								<a class="dropdown-item" href="${url_form}"><i
										class="bx bx-power-off"></i><span>Logout</span></a>
							</div>
						</li>
						
						
						<li class="nav-item dropdown dropdown-user-profile">
							<a class="nav-link dropdown-toggle dropdown-toggle-nocaret" href="javaScript:;" data-toggle="dropdown">
								<div class="media user-box align-items-center">
									<div class="media-body user-info">
										<p class="user-name mb-0">Permitted Module <i class="bx bx-cog"></i></p>
										<p class="designattion mb-0">..</p>
										
									</div>
									
								</div>
							</a>
							<div class="dropdown-menu dropdown-menu-right">	
							
							<%
							//List<menu> list = (List<menu>) session.getAttribute("menulist");
							String moduleName="";
							int moduleId=0;
							for (int a = 0; a < modulelist.size(); a++) {
								moduleName = modulelist.get(a).getModulename().toString();
								moduleId= modulelist.get(a).getId();
							%>
							 
							<button class="dropdown-item" onclick="modulewisemenu(<%=moduleId%>)"><i
										class="bx bx-tachometer"></i><span><%=moduleName%></span></button>
							<%
							}
							%>
<%-- 							   <a class="dropdown-item" href="javaScript:;"><i
										class="bx bx-user"></i><span>Profile</span></a>
								<a class="dropdown-item" href="javaScript:;"><i
										class="bx bx-cog"></i><span>Change Password</span></a>
								<a class="dropdown-item" href="javaScript:;"><i
										class="bx bx-cog"></i><span>Settings</span></a>
								<a class="dropdown-item" href="javaScript:;"><i
										class="bx bx-tachometer"></i><span>Dashboard</span></a>
 --%>
								
							</div>
						</li>
						
						

					</ul>
				</div>
			</nav>
		</header>
		
		<!--end header-->
		<!--page-wrapper-->
		<div class="page-wrapper">
			<!--sidebar-wrapper-->
			<div class="sidebar-wrapper" data-simplebar="true">
				<div class="sidebar-header">
					<a href="javaScript:;" class="toggle-btn"> <i class="bx bx-menu"></i>
					</a>
					<div class="">
						<img src="assets/images/logo-img-2.png" class="logo-icon-2" alt="" />
					</div>
				</div>
				<!--navigation-->
				<ul class="metismenu" id="menu">
					<li>
						<a class="has-arrow" href="javaScript:;">
							<div class="parent-icon"><i class="bx bx-home-alt"></i>
							</div>
							<div class="menu-title">Dashboard</div>
						</a>

					</li>


					<li class="menu-label">UI Elements</li>
					
					
						<%
							//List<menu> list = (List<menu>) session.getAttribute("menulist");
							int i = 0;
							String head = "";

							for (int a = 0; a < list.size(); a++) {
								head = list.get(a).getName().toString();
						%>
						<li class="menu-title">
						
						<a class="has-arrow" href="javaScript:;">
							<div class="parent-icon"><i class="bx bx-spa"></i></div>
							<div class="menu-title"><%=head%></div>
						</a>
						
							<ul>
								<%
									if (head.equals(list.get(a).getName().toString())) {
											for (int b = a; b < list.size(); b++) {
												if (!list.get(b).getName().toString().equals(head)) {
													a--;
													break;
												}
								%>
								<s:url var="url_form" value="<%=list.get(a).getLinks()%>" />
								<li> <a href="${url_form}"><i class="bx bx-right-arrow-alt"></i><%=list.get(a).getSub()%></a></li>
								<%
									a++;
											}
										}
								%>
							</ul></li>

						<%
							}
						%>
					


				</ul>
				<!--end navigation-->
			</div>
			<!--end sidebar-wrapper-->