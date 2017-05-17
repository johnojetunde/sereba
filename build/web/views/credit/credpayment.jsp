<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	 <%@ include file="../../includes/header.jsp" %> 
         <link rel="stylesheet" href="../assets/css/bootstrap-datepicker3.min.css" />
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
								<a href="#">Credit</a>
							</li>
							<li class="active">Credit Payment</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1>
								Credit
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Credit Payment
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                 <div id="ajaxResponse">${returnMessage}</div>
                                                                <!-- PAGE CONTENT BEGINS -->
                                                                 <c:if test="${display}"> 
								<div class="row">
									<div class="col-sm-11">
                                                                           
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#make_payment">
														<i class="green ace-icon fa fa-file-text bigger-120"></i>
														Credit Payment
													</a>
												</li>

												

												
											</ul>

											<div class="tab-content">
												<div id="single_credit" class="tab-pane fade in active">
													
								<form role="form" name=""  id="makepayment" action="savepayment.html" method="post" class="form-horizontal" >
									
								
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="amount"> Amount being Payed</label>
                                                                                <input type="hidden"  name="credId" value="${credId}" required/>
										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4"  name="amount" step=".01" required id="amount" placeholder="Amount" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
<!--											<div class="help-inline" id="input-size-slider">ghc</div>-->
										</div>
									</div>
                                                                    
									
                                                                    

									



									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-success" type="submit" >
												<i class="ace-icon fa fa-check bigger-110"></i>
												Make Payment
											</button>

											
										</div>
									</div>

								</form>
												</div>

												

												
											</div>
										</div>
									</div><!-- /.col -->

								
								</div><!-- /.row -->
                                                                 </c:if>

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
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="../assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="../assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="../assets/js/jquery-ui.custom.min.js"></script>
		<script src="../assets/js/jquery.ui.touch-punch.min.js"></script>
		
                <script src="../assets/js/autosize.min.js"></script>
                <script src="../assets/js/bootstrap-datepicker.min.js"></script>
		<!-- ace scripts -->
		<script src="../assets/js/ace-elements.min.js"></script>
		<script src="../assets/js/ace.min.js"></script>
                <script src="../functions/functions.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
				
			
				$('#tasks').disableSelection();
				$('#tasks input:checkbox').removeAttr('checked').on('click', function(){
					if(this.checked) $(this).closest('li').addClass('selected');
					else $(this).closest('li').removeClass('selected');
				});
                                autosize($('textarea[class*=autosize]'));
			
				//show the dropdowns on top or bottom depending on window height and menu position
				$('#task-tab .dropdown-hover').on('mouseenter', function(e) {
					var offset = $(this).offset();
			
					var $w = $(window)
					if (offset.top > $w.scrollTop() + $w.innerHeight() - 100) 
						$(this).addClass('dropup');
					else $(this).removeClass('dropup');
				});
			
                        $('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true,
                                        dateFormat: 'yyyy-mm-dd'
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
                                
			})
		</script>
	</body>
</html>
