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
								<a href="#">Product</a>
							</li>
							<li class="active">Add Product</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1>
								Product
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Add Product
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                <div id="ajaxResponse">${returnMessage}</div>
                                                                <!-- PAGE CONTENT BEGINS -->
                                                                 <c:if test="${acl.view_product=='view_product'}">
                                                                <a href="../product/viewproduct.html" class="btn btn-primary"><i class="fa fa-table"></i> Product List</a>
                                                                </c:if>
								<div class="row">
									<div class="col-sm-11">
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#single_product">
														<i class="green ace-icon fa fa-file-text bigger-120"></i>
														Single Product
													</a>
												</li>

												<li>
													<a data-toggle="tab" href="#multiple_product">
                                                                                                            <i class="blue ace-icon fa fa-file-excel-o bigger-120"></i> 
														Multiple Products
														
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
												<div id="single_product" class="tab-pane fade in active">
													
								<form class="form-horizontal" role="form" name="addProduct" method="POST" action="addproduct.html" enctype="multipart/form-data" id="productForm">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_name"> Product Name </label>

										<div class="col-sm-9">
											<input type="text" id="prod_name" placeholder="Product Name" name="prod_name"  value="${param.prod_name}" required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                    
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_name"> Product Image </label>

										<div class="col-sm-9">
											<input type="file" id="prod_image" placeholder="Product Image" required name="prod_image" class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                        

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_cost_price">Product Cost Price</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4" name="prod_cost_price" min="0"  required value="${param.prod_cost_price}" step=".01" id="prod_cost_price" placeholder="Product Cost price" />
											
                                                                                        <span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_selling_price">Product Selling Price</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4" step=".01" min="0" name="prod_selling_price" value="${param.prod_selling_price}" required id="prod_selling_price" placeholder="Product Selling price" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="discount">Discount Amount</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4"  name="discount" step=".01" min="0" id="discount" value="${param.discount}" placeholder="Discount Amount" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
<!--											<div class="help-inline" id="input-size-slider">ghc</div>-->
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_measurement"> Product Measurement unit </label>

										<div class="col-sm-9">
											<input type="text" id="prod_measurement" placeholder="Product Measurement Unit" required value="${param.prod_measurement}" name="prod_measurement" class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_qty">Quantity</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4"  id="prod_qty" name="prod_qty" min="0" required placeholder="Quanity" value="${param.prod_qty}" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>per unit</b></span>
											</span>
<!--											<div class="help-inline" id="input-size-slider">ghc</div>-->
										</div>
									</div>
                                                                    

									



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

												<div id="multiple_product" class="tab-pane fade">
													
                                                                            <form class="form-horizontal" role="form" name="" action="" method="post">
							
                                                                    
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_name"> Upload File (xlsx)</label>

										<div class="col-sm-9">
											<input type="file" id="prod_image" placeholder="Product Image" name="prod_image" class="col-xs-10 col-sm-5" />
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
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="../assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="../assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="../assets/js/jquery-ui.custom.min.js"></script>
		<script src="../assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="../assets/js/jquery.easypiechart.min.js"></script>
		<script src="../assets/js/jquery.sparkline.index.min.js"></script>
		<script src="../assets/js/jquery.flot.min.js"></script>
		<script src="../assets/js/jquery.flot.pie.min.js"></script>
		<script src="../assets/js/jquery.flot.resize.min.js"></script>

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
