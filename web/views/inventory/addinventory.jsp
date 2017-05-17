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
								<a href="#">Inventory</a>
							</li>
							<li class="active">Add Income/Expenditure</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1>
								Inventory
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Add Income/Expenditure
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                <div id="ajaxResponse"></div>
                                                                <!-- PAGE CONTENT BEGINS -->
                                                                <c:if test="${acl.view_inventory=='view_inventory'}">
                                                                                <a href="../inventory/viewinventory.html" class="btn btn-primary"><i class="fa fa-table"></i> View Income/Expenditure</a>
                                                                 </c:if>
								<div class="row">
									<div class="col-sm-11">
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#single_inventory">
														<i class="green ace-icon fa fa-file-text bigger-120"></i>
														Single  Income/Expenditure
													</a>
												</li>

												<li>
													<a data-toggle="tab" href="#multiple_inventory">
                                                                                                            <i class="blue ace-icon fa fa-file-excel-o bigger-120"></i> 
														Multiple Income/Expenditure
														
													</a>
												</li>
                                                                                                <li>
													<a data-toggle="tab" href="#download_template">
                                                                                                            <i class="red ace-icon fa fa-download bigger-120"></i> 
														Download Template
														
													</a>
												</li>

												
											</ul>

											<div class="tab-content">
												<div id="single_inventory" class="tab-pane fade in active">
													
								<form class="form-horizontal" role="form" name="" action="" id="addinventory" onSubmit="submitForm('addinventory', 'saveinventory.html');return false;" method="post">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="title"> Title </label>

										<div class="col-sm-9">
											<input type="text" id="title" placeholder="Title" name="title" required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="trans_type"> Transaction Type </label>

										<div class="col-sm-9">
                                                                                    <select name="trans_type" class="col-xs-10 col-sm-5" required >
                                                                                        <option value="debit">Debit / Expenditure</option>
                                                                                        <option value="credit">Credit / Income</option>
                                                                                    </select>
											
										</div>
									</div>
                                                                
								
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="amount"> Amount</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4"  name="amount" step=".01" id="amount" placeholder="Amount" required/>
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
<!--											<div class="help-inline" id="input-size-slider">ghc</div>-->
										</div>
									</div>
                                                                      
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit" onSubmit="submitForm('addinventory', 'saveinventory.html');return false;">
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

												<div id="multiple_inventory" class="tab-pane fade">
													
                                                                            <form class="form-horizontal" role="form" name="" action="" method="post">
							
                                                                    
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="document"> Upload File (xlsx)</label>

										<div class="col-sm-9">
											<input type="file" id="document" placeholder="Document" name="document" class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                        




									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button">
												<i class="ace-icon fa fa-upload bigger-110"></i>
												Upload
											</button>

											
										</div>
									</div>

								</form>
												</div>
                                                                                            
                                                                                            <div id="download_template" class="tab-pane fade">
													
								
                                                                        




									
											<button class="btn btn-danger" type="button">
												<i class="ace-icon fa fa-download bigger-110"></i>
												Download Excel Template
											</button>

										

								
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
                
                <script src="../assets/js/autosize.min.js"></script>
                <script src="../assets/js/bootstrap-datepicker.min.js"></script>
		<!-- ace scripts -->
		<script src="../assets/js/ace-elements.min.js"></script>
		<script src="../assets/js/ace.min.js"></script>

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
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
                                
			})
		</script>
	</body>
</html>
