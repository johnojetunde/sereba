<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	 <%@ include file="../../includes/header.jsp" %> 
<style type="text/css">
    #wrapper { width: auto; height: 50px; position: relative;}
#wrapper img {position: absolute; height: 100%; width: auto;}
</style>
<link rel="stylesheet" href="../assets/css/select2.min.css" />
<link rel="stylesheet" href="../assets/css/bootstrap-datepicker3.min.css" />
<link rel="stylesheet" href="../assets/css/bootstrap-timepicker.min.css" />
<link rel="stylesheet" href="../assets/css/daterangepicker.min.css" />



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
								<a href="#">Reports</a>
							</li>
							<li class="active">Average Sales </li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1>
								Reports
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Average Sales 
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                 <div id="ajaxResponse"></div>
                                                                 <input type="hidden" name="baseUrl" id="baseUrl" value="${pageContext.request.contextPath}" />
                                                                <!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-sm-11">
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#average_sales">
														<i class="green ace-icon fa fa-file-text bigger-120"></i>
														Average Sales
													</a>
												</li>

												
											</ul>

											<div class="tab-content">
												<div id="average_sales" class="tab-pane fade in active">
													
								<form  role="form" name=""  id="averagesales" action="" method="post" onSubmit="submitFormSales('averagesales', 'querysales.html');return false;" class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_name"> Select Product</label>

										<div class="col-sm-9">
                                                                                  
                                                                                 <select multiple="true" id="prodId" name="prodId" required class="select2" data-placeholder="Click to Choose...">
												 <c:forEach items="${products}" var="product">
                                                                                                     <option value="${product[0].prodId}"> <c:out value="${product[0].prodName}" /></option>
                                                                                    </c:forEach>
									         </select>


                                                                              
                                                                                </div>
									</div>
                                                                            <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="date_range">Date Range</label>

										
										<div class="col-xs-5 col-sm-6">
										<div class="input-daterange input-group">
										<input type="text" class="input-sm form-control" name="start_date" required/>
										<span class="input-group-addon">
										<i class="fa fa-exchange"></i>
										</span>
                                                                                <input type="text" class="input-sm form-control" name="end_date" required/>
										</div>
										</div>
												
									</div>
                                                                       <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="period">Period</label>

										<div class="col-sm-9">
										<div class="radio">
										<label>
										<input name="period" type="radio" class="ace" value="monthly" checked required/>
										<span class="lbl"> Monthly</span>
										</label>
										</div>
                                                                                    
                                                                                <div class="radio">
										<label>
										<input name="period" type="radio" class="ace" value="3month" required/>
										<span class="lbl"> Last 3 Months</span>
										</label>
										</div>
                                                                                 
                                                                                <div class="radio">
										<label>
										<input name="period" type="radio" class="ace" value="6month" required/>
										<span class="lbl"> Last 6  Months</span>
										</label>
										</div>
                                                                                    
                                                                                <div class="radio">
										<label>
										<input name="period" type="radio" class="ace" value="annual" required/>
										<span class="lbl"> Annual</span>
										</label>
										</div>
                                                                              
                                                                                    
                                                                                <div class="radio">
										<label>
										<input name="period" type="radio" class="ace" value="aggregate" required/>
										<span class="lbl"> Aggregate</span>
										</label>
										</div>
										</div>
									</div>
                                                                          
                                                                      
                                                                    
                                                                     </div>

									

                                                                                   <hr/>

									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit" onSubmit="submitFormSales('averagesales', 'querysales.html');return false;">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Fetch Data
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
                                                                    
                                                                    <div id="tableResponse"></div>
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
	
	
               
                <script src="../assets/js/bootstrap-datepicker.min.js"></script>
                <script src="../assets/js/moment.min.js"></script>
		<script src="../assets/js/daterangepicker.min.js"></script>
		
                <script src="../btselect/js/bootstrap-select.js"></script>
              
                <script src="../assets/js/jquery.dataTables.min.js"></script>
		<script src="../assets/js/jquery.dataTables.bootstrap.min.js"></script>
		<script src="../assets/js/dataTables.buttons.min.js"></script>
                <script src="../assets/js/buttons.flash.min.js"></script>
		<script src="../assets/js/buttons.html5.min.js"></script>
		<script src="../assets/js/buttons.print.min.js"></script>
		<script src="../assets/js/buttons.colVis.min.js"></script>
		

		<!-- ace scripts -->
		<script src="../assets/js/ace-elements.min.js"></script>
		<script src="../assets/js/ace.min.js"></script>
                
		
                <script src="../assets/js/select2.min.js"></script>
                 <script src="../functions/functions.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			
			$('.select2').css('width','200px').select2({allowClear:true})
				$('#select2-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('.select2').addClass('tag-input-style');
					 else $('.select2').removeClass('tag-input-style');
				});
				//or change it into a date range picker
				$('.input-daterange').datepicker({autoclose:true,dateFormat: 'yyyy-mm-dd',format: 'yyyy-mm-dd'});
                                
                                //to translate the daterange picker, please copy the "examples/daterange-fr.js" contents here before initialization
				$('input[name=date-range-picker]').daterangepicker({
                                         dateFormat: 'yyyy-mm-dd',
					'applyClass' : 'btn-sm btn-success',
					'cancelClass' : 'btn-sm btn-default',
					locale: {
						applyLabel: 'Apply',
						cancelLabel: 'Cancel',
					}
				})
				.prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
                                
                                
				//////////////////
			
			
		
			
			
			})
		</script>
                         <div class="modal fade" id="loadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" class="text-xs-center" style="vertical-align: middle; height:90px;">
        <div class="modal-dialog" class="text-xs-center" style="vertical-align: middle">
          <div class="modal-content" class="text-xs-center" style="vertical-align: middle">
            
            <div class="modal-body" class="text-xs-center" style="vertical-align: middle">
                <p align="center"><i class="fa fa-spinner fa-2x fa-spin" style="color: #008C00"></i></p>
            </div>
            
          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div><!-- /.modal -->
     
	
	</body>
</html>
