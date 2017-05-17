<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	 <%@ include file="../../includes/header.jsp" %> 
         <style type="text/css">
    #wrapper { width: auto; height: 100px; position: relative;}
#wrapper img {position: absolute; height: 100%; width: auto;}
</style>

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
							<li class="active">Edit Product</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

						<div class="page-header">
							<h1>
								Product
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Edit Product
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
                                                                <div id="ajaxResponse">${returnMessage}</div>
                                                                <!-- PAGE CONTENT BEGINS -->
                                                                 <c:if test="${acl.view_product=='view_product'}">
                                                                <a href="../product/viewproduct.html" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Product List</a>
                                                                </c:if>
                                                                 <c:if test="${display}"> 
                   
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

												

												
											</ul>

											<div class="tab-content">
												<div id="single_product" class="tab-pane fade in active">
													
								<form class="form-horizontal" role="form" name="addProduct" method="POST" action="editproduct.html" enctype="multipart/form-data" id="productForm">
								<input type="hidden" name="id" value="${product.prodId}" />	
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_name"> Product Name </label>

										<div class="col-sm-9">
											<input type="text" id="prod_name" placeholder="Product Name" name="prod_name"  value="${product.prodName}" required class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                    
                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_name"> Product Image </label>

										<div class="col-sm-9">
											<figure id="wrapper"><img src="${pageContext.request.contextPath}/images/uploads/products/${product.prodImagePath}" class="img-thumbnail img-responsive"/></figure>
                                                                                        <input type="file" id="prod_image"  name="prod_image" class="col-xs-10 col-sm-5" />
										</div>
									</div>
                                                                        

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_cost_price">Product Cost Price</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4" name="prod_cost_price" min="0"  required value="${product.prodCostPrice}" step=".01" id="prod_cost_price" placeholder="Product Cost price" />
											
                                                                                        <span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_selling_price">Product Selling Price</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4" step=".01" min="0" name="prod_selling_price" value="${product.prodCostPrice}" required id="prod_selling_price" placeholder="Product Selling price" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
										</div>
									</div>
                                                                        <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="discount">Discount Amount</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4"  name="discount" step=".01" min="0" id="discount" value="${product.discount}" placeholder="Discount Amount" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>GHC</b></span>
											</span>
<!--											<div class="help-inline" id="input-size-slider">ghc</div>-->
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_measurement"> Product Measurement unit </label>

										<div class="col-sm-9">
											<input type="text" id="prod_measurement" placeholder="Product Measurement Unit" value="${product.prodMeasurement}" required name="prod_measurement" class="col-xs-10 col-sm-5" />
										</div>
									</div>
<!--                                                                    <div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="prod_qty">Quantity</label>

										<div class="col-sm-9">
											<input type="number" class="col-xs-6 col-sm-4"  id="prod_qty" name="prod_qty" min="0" required placeholder="Quanity" value="${product.prodQty}" />
											<span class="help-inline col-xs-12 col-sm-7">
                                                                                            <span class="middle"><b>per unit</b></span>
											</span>
											<div class="help-inline" id="input-size-slider">ghc</div>
										</div>
									</div>-->
                                                                    

									



									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="submit">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Update
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
				$('.easy-pie-chart.percentage').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
					var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
					var size = parseInt($(this).data('size')) || 50;
					$(this).easyPieChart({
						barColor: barColor,
						trackColor: trackColor,
						scaleColor: false,
						lineCap: 'butt',
						lineWidth: parseInt(size/10),
						animate: ace.vars['old_ie'] ? false : 1000,
						size: size
					});
				})
			
				$('.sparkline').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
					$(this).sparkline('html',
									 {
										tagValuesAttribute:'data-values',
										type: 'bar',
										barColor: barColor ,
										chartRangeMin:$(this).data('min') || 0
									 });
				});
			
			
			  //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
			  //but sometimes it brings up errors with normal resize event handlers
			  $.resize.throttleWindow = false;
			
			  var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
			  var data = [
				{ label: "social networks",  data: 38.7, color: "#68BC31"},
				{ label: "search engines",  data: 24.5, color: "#2091CF"},
				{ label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
				{ label: "direct traffic",  data: 18.6, color: "#DA5430"},
				{ label: "other",  data: 10, color: "#FEE074"}
			  ]
			  function drawPieChart(placeholder, data, position) {
			 	  $.plot(placeholder, data, {
					series: {
						pie: {
							show: true,
							tilt:0.8,
							highlight: {
								opacity: 0.25
							},
							stroke: {
								color: '#fff',
								width: 2
							},
							startAngle: 2
						}
					},
					legend: {
						show: true,
						position: position || "ne", 
						labelBoxBorderColor: null,
						margin:[-30,15]
					}
					,
					grid: {
						hoverable: true,
						clickable: true
					}
				 })
			 }
			 drawPieChart(placeholder, data);
			
			 /**
			 we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			 so that's not needed actually.
			 */
			 placeholder.data('chart', data);
			 placeholder.data('draw', drawPieChart);
			
			
			  //pie chart tooltip example
			  var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
			  var previousPoint = null;
			
			  placeholder.on('plothover', function (event, pos, item) {
				if(item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : " + item.series['percent']+'%';
						$tooltip.show().children(0).text(tip);
					}
					$tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
				} else {
					$tooltip.hide();
					previousPoint = null;
				}
				
			 });
			
				/////////////////////////////////////
				$(document).one('ajaxloadstart.page', function(e) {
					$tooltip.remove();
				});
			
			
			
			
				var d1 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.5) {
					d1.push([i, Math.sin(i)]);
				}
			
				var d2 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.5) {
					d2.push([i, Math.cos(i)]);
				}
			
				var d3 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.2) {
					d3.push([i, Math.tan(i)]);
				}
				
			
				var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
				$.plot("#sales-charts", [
					{ label: "Domains", data: d1 },
					{ label: "Hosting", data: d2 },
					{ label: "Services", data: d3 }
				], {
					hoverable: true,
					shadowSize: 0,
					series: {
						lines: { show: true },
						points: { show: true }
					},
					xaxis: {
						tickLength: 0
					},
					yaxis: {
						ticks: 10,
						min: -2,
						max: 2,
						tickDecimals: 3
					},
					grid: {
						backgroundColor: { colors: [ "#fff", "#fff" ] },
						borderWidth: 1,
						borderColor:'#555'
					}
				});
			
			
				$('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('.tab-content')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			
			
				$('.dialogs,.comments').ace_scroll({
					size: 300
			    });
				
				
				//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
				//so disable dragging when clicking on label
				var agent = navigator.userAgent.toLowerCase();
				if(ace.vars['touch'] && ace.vars['android']) {
				  $('#tasks').on('touchstart', function(e){
					var li = $(e.target).closest('#tasks li');
					if(li.length == 0)return;
					var label = li.find('label.inline').get(0);
					if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
				  });
				}
			
				$('#tasks').sortable({
					opacity:0.8,
					revert:true,
					forceHelperSize:true,
					placeholder: 'draggable-placeholder',
					forcePlaceholderSize:true,
					tolerance:'pointer',
					stop: function( event, ui ) {
						//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
						$(ui.item).css('z-index', 'auto');
					}
					}
				);
				$('#tasks').disableSelection();
				$('#tasks input:checkbox').removeAttr('checked').on('click', function(){
					if(this.checked) $(this).closest('li').addClass('selected');
					else $(this).closest('li').removeClass('selected');
				});
			
			
				//show the dropdowns on top or bottom depending on window height and menu position
				$('#task-tab .dropdown-hover').on('mouseenter', function(e) {
					var offset = $(this).offset();
			
					var $w = $(window)
					if (offset.top > $w.scrollTop() + $w.innerHeight() - 100) 
						$(this).addClass('dropup');
					else $(this).removeClass('dropup');
				});
			
			})
		</script>
	</body>
</html>
