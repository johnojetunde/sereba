<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	 <%@ include file="../../includes/header.jsp" %> 
<link rel="stylesheet" href="../btselect/css/bootstrap-select.css">

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
								<a href="#">Sales</a>
							</li>
							<li class="active">Add Sale</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1>
								Sales
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Add Sale
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                 <div id="ajaxResponse"></div>
                                                                <!-- PAGE CONTENT BEGINS -->
								<div class="row">
									<div class="col-sm-11">
										<div class="tabbable">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active">
													<a data-toggle="tab" href="#single_sale">
														<i class="green ace-icon fa fa-file-text bigger-120"></i>
														Add Sales
													</a>
												</li>

												
											</ul>

											<div class="tab-content">
												<div id="single_sale" class="tab-pane fade in active">
													
								<form  role="form" name=""  id="addsale" action="" method="post" onSubmit="submitForm('addsale', 'savesale.html');return false;" class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_name"> Select Product</label>

										<div class="col-sm-9">
                                                                                  
                                                                                 
                                                                                 <select id="productList" name="prodId" class="selectpicker" onChange="loadProductData('${pageContext.request.contextPath}/product/productdata.html',this.value);" data-live-search="true" title="Please select a product ..." required>
                                                                                   <c:forEach items="${products}" var="product">
                                                                                                     <option value="${product[0].prodId}"> <c:out value="${product[0].prodName}" /></option>
                                                                                    </c:forEach>
                                                                                  </select>
                                                                              
                                                                                </div>
									</div>
                                                                    
                                                                   
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="selling_price">Selling Price</label>

										<div class="col-sm-9">
											<input type="number" readonly class="col-xs-6 col-sm-4" name="selling_price" readonly id="selling_price" placeholder="Selling price" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC per <span id="prodMeasurement"></span></b></span>
											</span>
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="discount">Discount Amount</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4" min="0" step=".01" name="discount" id="discount" placeholder="Discount Amount" onkeyup="calculateTotalCost()" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
<!--											<div class="help-inline" id="input-size-slider">ghc</div>-->
										</div>
									</div>
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="qty_sold">Quantity</label>

										<div class="col-sm-9">
                                                                                    <input type="number" class="col-xs-6 col-sm-4"  id="qty_sold" min="1" name="qty_sold" placeholder="Quanity"  onkeyup="calculateTotalCost()"/>
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle" id="prodMeasurement"><b>packs</b></span>
											</span>
<!--											<div class="help-inline" id="input-size-slider">ghc</div>-->
										</div>
									</div>
                                                                     <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="total_amount">Total Cost</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4" step=".01" readonly id="total_amount" name="amount" placeholder="Total Cost" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
<!--											<div class="help-inline" id="input-size-slider">ghc</div>-->
										</div>
									</div>
									

									



									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit" onSubmit="submitForm('addsale', 'savesale.html');return false;">
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
                <script src="../btselect/js/bootstrap-select.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			
			
		
			
			
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
