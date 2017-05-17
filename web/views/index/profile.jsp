<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
	 <%@ include file="../../includes/header.jsp" %> 

	<body class="no-skin">
             <%@ include file="../../includes/lid.jsp" %>

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
                    <%@ include file="../../includes/sidebar.jsp" %> 
			

			
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">User</a>
							</li>
							<li class="active">Profile</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1>
								User
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Profile
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                <div id="ajaxResponse">${returnMessage}</div>
                                                                  
                                                                
                                                                <!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-sm-11">
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#staff">
														<i class="green ace-icon fa fa-user bigger-120"></i>
														Account Details
													</a>
												</li>
                                                                                                <li>
													<a data-toggle="tab" href="#password">
														<i class="green ace-icon fa fa-lock bigger-120"></i>
														Change Password
													</a>
												</li>


												
											</ul>

											<div class="tab-content">
												<div id="staff" class="tab-pane fade in active">
                                                            <c:if test="${userType=='staff'}">	
								<form class="form-horizontal" role="form" name="staffaccount" method="POST" action=""  onSubmit="submitForm('staffaccount', 'saveaccountstaff.html');return false;" id="staffaccount">
                                                                    <input type="hidden" name="id" value="${staff.staffId}" required/>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="fullname"> Fullname </label>

										<div class="col-sm-9">
											<input type="text" id="fullname" placeholder="Fullname" name="fullname"  value="${staff.fullname}" required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                    
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="email"> Email </label>

										<div class="col-sm-9">
											<input type="email" id="email" placeholder="Email" readonly required name="email" value="${staff.email}" class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                        

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="phone_no">Phone Number</label>

										<div class="col-sm-9">
											<input type="tel" id="phone_no" placeholder="Phone Number" name="phone_no"  value="${staff.phoneNumber}" required class="col-xs-10 col-sm-5" />
										
										</div>
									</div>
                                                                         
                                                                                        <hr/>
                                                                         

                                                                    
                                                                                        <div class="clearfix"></div>
									
<hr/>


									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit" onSubmit="submitForm('staffaccount', 'saveaccountstaff.html');return false;">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Update Account
											</button>

											
										</div>
									</div>

								</form>
                                                             </c:if>
                                                                                                     <c:if test="${userType=='user'}">		
								<form class="form-horizontal" role="form" name="useraccount" method="POST" action=""  onSubmit="submitForm('useraccount', 'saveaccountuser.html');return false;" id="useraccount">
                                                                    <input type="hidden" name="id" value="${staff.staffId}" required/>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="fullname"> Fullname </label>

										<div class="col-sm-9">
											<input type="text" id="fullname" placeholder="Fullname" name="fullname"  value="${user.fullname}" required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                    
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="email"> Email </label>

										<div class="col-sm-9">
											<input type="email" id="email" placeholder="Email" readonly required name="email" value="${user.email}" class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="store_name">Store Name</label>

										<div class="col-sm-9">
											<input type="text" id="store_name" placeholder="Store name" name="store_name"  value="${user.storeName}" required class="col-xs-10 col-sm-5" />
										
										</div>
									</div>
                                                                                        

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="phone_no">Phone Number</label>

										<div class="col-sm-9">
											<input type="tel" id="phone_no" placeholder="Phone Number" name="phone_no"  value="${user.phoneNumber}" required class="col-xs-10 col-sm-5" />
										
										</div>
									</div>
                                                                                        
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="contact_address">Contact Address</label>

										<div class="col-sm-9">
                                                                                    <textarea id="contact_address" placeholder="Contact Address" name="contact_address" required class="col-xs-10 col-sm-5">${user.contactAddress}</textarea>
										
										</div>
									</div>
                                                                       
                                                                                        <hr/>
                                                                         

                                                                    
                                                                                        <div class="clearfix"></div>
									
<hr/>


									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit" onSubmit="submitForm('useraccount', 'saveaccountuser.html');return false;">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Update Account
											</button>

											
										</div>
									</div>

								</form>
                                                                                                    </c:if>
												</div>

                                                                                            <div id="password" class="tab-pane fade in">
                                                             <form class="form-horizontal" role="form" name="passwordform" method="POST" action="" onSubmit="submitForm('passwordform', 'savepassword.html');return false;" id="passwordform">
                                                                    <input type="hidden" name="id" value="${staff.staffId}" required/>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="current_password"> Current Password </label>

										<div class="col-sm-9">
											<input type="password" id="current_password" placeholder="Current Password" name="current_password"   required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                    
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="new_password"> New Password </label>

										<div class="col-sm-9">
											<input type="password" id="new_password" placeholder="New Password" name="new_password" required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="confirm_password"> Confirm New Password </label>

										<div class="col-sm-9">
											<input type="password" id="confirm_password" placeholder="Confirm New Password" name="confirm_password" required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                       
                                                                                        <hr/>
                                                                         

                                                                    
                                                                                        <div class="clearfix"></div>
									
<hr/>


									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit" onSubmit="submitForm('passwordform', 'savepassword.html');return false;">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Change Password
											</button>

											
										</div>
									</div>

								</form>
                                                                                            </div>
												
											</div>
										</div>
									</div><!-- /.col -->

								
								</div><!-- /.row -->
                                                               

							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->


			 <%@ include file="../../includes/footer.jsp" %>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="../assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="../assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="../assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="../assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="../assets/js/jquery-ui.custom.min.js"></script>
		<script src="../assets/js/jquery.ui.touch-punch.min.js"></script>
		

                <script src="../functions/functions.js"></script>
		<!-- ace scripts -->
		<script src="../assets/js/ace-elements.min.js"></script>
		<script src="../assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
		
			
			
			
			})
		</script>
	</body>
</html>
