<%@page import="pg.model.Login"%>
<%@page import="pg.model.Module"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Base64"%>
<%@page import="pg.model.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String userId = (String) session.getAttribute("userId");
	String userName = (String) session.getAttribute("userName");
	String body = (String) request.getAttribute("body");
	String header = (String) request.getAttribute("header");
	String filename = (String) request.getAttribute("file");
	List<Menu> list = (List<Menu>) session.getAttribute("menulist");
%>




<jsp:include page="include/header.jsp" />



			
			<!--page-content-wrapper-->
			<div class="page-content-wrapper">
				<div class="page-content">
					<div class="row">
						<div class="col-12 col-lg-8">
							<div class="card radius-10">
								<div class="card-header border-bottom-0">
									<div class="d-lg-flex align-items-center">
										<div>
											<h5 class="font-weight-bold mb-2 mb-lg-0">Diagnostic Activities</h5>
										</div>
										<div class="ml-lg-auto mb-2 mb-lg-0">
											<div class="btn-group-round">
												<div class="btn-group">
													<button type="button" class="btn btn-white">Last 1 Year</button>
													<div class="dropdown-menu">	<a class="dropdown-item" href="javaScript:;">Last Month</a>
														<a class="dropdown-item" href="javaScript:;">Last Week</a>
													</div>
													<button type="button" class="btn btn-white dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">	<span class="sr-only">Toggle Dropdown</span>
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div id="chart1"></div>
								</div>
							</div>
						</div>
						<div class="col-12 col-lg-4">
							<div class="card radius-10 bg-red-light">
								<div class="card-body">
									<div class="media align-items-center">
										<img src="assets/images/icons/appointment-book.png" width="45" alt="" />
										<div class="media-body text-right">
											<p class="mb-0 text-white"><i class='bx bxs-arrow-from-bottom'></i> 2.69%</p>
											<p class="mb-0 text-white">Since Last Month</p>
										</div>
									</div>
									<div class="media align-items-center mt-3">
										<div class="media-body">
											<p class="mb-1 text-white">Appointments</p>
											<h4 class="mb-0 text-white font-weight-bold">1879</h4>
										</div>
										<div id="chart2"></div>
									</div>
								</div>
							</div>
							<div class="card radius-10 bg-primary-blue">
								<div class="card-body">
									<div class="media align-items-center">
										<img src="assets/images/icons/surgery.png" width="45" alt="" />
										<div class="media-body text-right">
											<p class="mb-0 text-white"><i class='bx bxs-arrow-from-bottom'></i> 3.56%</p>
											<p class="mb-0 text-white">Since Last Month</p>
										</div>
									</div>
									<div class="media align-items-center mt-3">
										<div class="media-body">
											<p class="mb-1 text-white">Surgery</p>
											<h4 class="mb-0 text-white font-weight-bold">3768</h4>
										</div>
										<div id="chart3"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--end row-->
		
	
					<!--end row-->

				</div>
			</div>
			<!--end page-content-wrapper-->
		</div>
		<!--end page-wrapper-->
		<!--start overlay-->
		<div class="overlay toggle-btn-mobile"></div>
		<!--end overlay-->
		<!--Start Back To Top Button--> <a href="javaScript:;" class="back-to-top"><i class='bx bxs-up-arrow-alt'></i></a>
		<!--End Back To Top Button-->
		<!--footer -->
		<div class="footer">
			<p class="mb-0">Synadmin @2020 | Developed By : <a href="https://themeforest.net/user/codervent" target="_blank">Dream IT</a>
			</p>
		</div>
		<!-- end footer -->
	</div>
	<!-- end wrapper -->
	<!-- JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
	<jsp:include page="include/footer.jsp" />
	



<!-- Mirrored from codervent.com/synadmin/demo/index2.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 21 Oct 2020 18:47:11 GMT -->
</html>