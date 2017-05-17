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
								<a href="#">Staff</a>
							</li>
							<li class="active">Add Staff</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1>
								Store Staff
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Add Staff
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                <div id="ajaxResponse">${returnMessage}</div>
                                                                <!-- PAGE CONTENT BEGINS -->
                                                                <c:if test="${userType=='user'}">
                                                                  <a href="${pageContext.request.contextPath}/staff/viewstaff.html" class="btn btn-primary"><i class="fa fa-atable"></i> Staff List</a>
                                                                  </c:if>
								<div class="row">
									<div class="col-sm-11">
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#staff">
														<i class="green ace-icon fa fa-user-plus bigger-120"></i>
														New Staff
													</a>
												</li>


												
											</ul>

											<div class="tab-content">
												<div id="staff" class="tab-pane fade in active">
													
								<form class="form-horizontal" role="form" name="addStaff" method="POST" action="addstaff.html"  id="addstaff">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="fullname"> Fullname </label>

										<div class="col-sm-9">
											<input type="text" id="fullname" placeholder="Fullname" name="fullname"  value="${param.fullname}" required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                    
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="email"> Email </label>

										<div class="col-sm-9">
											<input type="email" id="email" placeholder="Email" required name="email" value="${param.email}" class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                        

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="phone_no">Phone Number</label>

										<div class="col-sm-9">
											<input type="tel" id="phone_no" placeholder="Phone Number" name="phone_no"  value="${param.phone_no}" required class="col-xs-10 col-sm-5" />
										
										</div>
									</div>
                                                                         <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="password">Password</label>

										<div class="col-sm-9">
											<input type="password" id="password" placeholder="Password" name="password"   required class="col-xs-10 col-sm-5" />
										
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="confirm_password">Confirm Password</label>

										<div class="col-sm-9">
											<input type="password" id="confirm_password" placeholder="Confirm Password" name="confirm_password"   required class="col-xs-10 col-sm-5" />
										
										</div>
									</div>
                                                                                        <hr/>
                                                                                       
                                                                       <div class="col-md-9 col-lg-push-2">
										<label class="col-sm-3 control-label bolder blue no-padding-right">Access Levels (ACL)</label>
                                                                               <br/>
                                                                               <div class="row">
                                                                                   <div class="col-md-9">
                                                                                       <div class="row">
                                                                                   <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="add_product" class="ace"  <c:if test="${not empty acl.add_product}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> Add Product</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   <div class="col-md-3">
                                                                                <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="view_product" class="ace" <c:if test="${not empty acl.view_product}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> View Product</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="edit_product" class="ace" <c:if test="${not empty acl.edit_product}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> Edit Product</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                       <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="delete_product" class="ace" <c:if test="${not empty acl.delete_product}">
                                                                                           checked ="true"
                                                                                        </c:if> />
                                                                                        <span class="lbl"> Delete Product</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                       </div>
                                                                                       <div class="row">
                                                                                   <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="add_inventory" class="ace" <c:if test="${not empty acl.add_inventory}">
                                                                                           checked ="true"
                                                                                        </c:if> />
                                                                                        <span class="lbl"> Add Inventory</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   <div class="col-md-3">
                                                                                <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="view_inventory" class="ace" <c:if test="${not empty acl.view_inventory}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> View Inventory</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                 
                                                                                     
                                                                                       </div>
                                                                                        <div class="row">
                                                                                   <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="add_sale" class="ace" <c:if test="${not empty acl.add_sale}">
                                                                                           checked ="true"
                                                                                        </c:if> />
                                                                                        <span class="lbl"> Add Sale</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   <div class="col-md-3">
                                                                                <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="view_sale" class="ace" <c:if test="${not empty acl.view_sale}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> View Sales</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                 
                                                                                     
                                                                                       </div>
                                                                                       
                                                                                       
                                                                                        <div class="row">
                                                                                   <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="add_credit" class="ace" <c:if test="${not empty acl.add_credit}">
                                                                                           checked ="true"
                                                                                        </c:if> />
                                                                                        <span class="lbl"> Add Credit</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   <div class="col-md-3">
                                                                                <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="view_credit" class="ace" <c:if test="${not empty acl.view_credit}">
                                                                                           checked ="true"
                                                                                        </c:if> />
                                                                                        <span class="lbl"> View Credit</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   
                                                                                       <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="delete_credit" class="ace" <c:if test="${not empty acl.delete_credit}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> Delete Credit</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                       </div>
                                                                                       
                                                                                        <div class="row">
                                                                                   <div class="col-md-6">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="add_credit_payment" class="ace" <c:if test="${not empty acl.add_credit_payment}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> Make Credit Payment</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   <div class="col-md-6">
                                                                                <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="view_credit_payment" class="ace" <c:if test="${not empty acl.view_credit_payment}">
                                                                                           checked ="true"
                                                                                        </c:if> />
                                                                                        <span class="lbl"> View Credit's Payment History</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                
                                                                                      
                                                                                       </div>
                                                                                       
                                                                                       
                                                                                        <div class="row">
                                                                                   <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="add_income_expense" class="ace" <c:if test="${not empty acl.add_income_expense}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> Add Income/Expense</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   <div class="col-md-3">
                                                                                <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="view_income_expense" class="ace" <c:if test="${not empty acl.view_income_expense}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> View Income/Expense</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   
                                                                                       <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="delete_income_expense" class="ace" <c:if test="${not empty acl.delete_income_expense}">
                                                                                           checked ="true"
                                                                                        </c:if> />
                                                                                        <span class="lbl"> Delete Income/Expense</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                       </div>
                                                                                       
                                                                                       <div class="row">
                                                                                   <div class="col-md-3">
                                                                               <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="reports" class="ace" <c:if test="${not empty acl.reports}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> Query reports</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   <div class="col-md-3">
                                                                                <div class="checkbox">
                                                                                    <label>
                                                                                        <input name="acl" type="checkbox" value="charts" class="ace" <c:if test="${not empty acl.charts}">
                                                                                           checked ="true"
                                                                                        </c:if>/>
                                                                                        <span class="lbl"> View Charts</span>
                                                                                    </label>
                                                                                </div>
                                                                                   </div>
                                                                                   
                                                                                      
                                                                                       </div>
                                                                                       
                                                                                      
                                                                                   </div>
                                                                               </div>
									</div>
                                                                       

                                                                    
                                                                                        <div class="clearfix"></div>
									
<hr/>


									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Submit
											</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												Reset
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
