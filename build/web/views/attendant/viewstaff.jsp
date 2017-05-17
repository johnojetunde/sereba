<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
	 <%@ include file="../../includes/header.jsp" %> 
<style type="text/css">
    #wrapper { width: auto; height: 50px; position: relative;}
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
								<a href="#">Staff</a>
							</li>
							<li class="active">View Staff</li>
						</ul><!-- /.breadcrumb -->

						
					</div>

					<div class="page-content">
						

<!--						<div class="page-header">
							<h1>
								Tables
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									Product &amp;
								</small>
							</h1>
						</div> /.page-header -->
                                 
                                  
                                    
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
                                                                <div id="ajaxResponse"></div>
                                                                <input type="hidden" name="baseUrl" id="baseUrl" value="${pageContext.request.contextPath}" />
								<div class="row">
									<div class="col-xs-12">
										<h3 class="header smaller lighter blue">Staff Table</h3>
                                                                                <c:if test="${acl.add_staff=='add_staff'}">
                                                                                <a href="../staff/addstaff.html" class="btn btn-primary"><i class="fa fa-plus"></i> Add Staff</a>
                                                                                </c:if>
										<div class="clearfix">
											<div class="pull-right tableTools-container"></div>
										</div>
										<div class="table-header">
											Results for "Staffs"
										</div>

										<!-- div.table-responsive -->

										<!-- div.dataTables_borderWrap -->
										<div>
											<table id="dynamic-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
                                                                                                            <th>ID</th>
                                                                                                            <th>Fullname</th>
                                                                                                            <th>Email</th>
													    <th>Phone Number</th>
                                                                                                            <th style="max-width:250px !important;word-wrap:break-word !important;">acl</th>
                                                                                                           
                                                                                                            <th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i> Time Created</th>
                                                                                                            <th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i> Time Modified</th>
                                                                                                            <th>Created By</th>
                                                                                                            <th>Store Name</th>
                                                                                                            <th></th>
													</tr>
												</thead>

												<tbody>
                                                                                                    <c:forEach items="${staffs}" var="staff">
                                                                                                        <c:if test="${staff[4].email!=staff[0].email}">
                                                                                    <tr id="row<c:out value="${staff[0].staffId}" />">
                                                                                    
                                                                                    <td>
                                                                                        <c:out value="${staff[0].staffId}" />
                                                                                    </td>
                                                                                        <td><c:out value="${staff[0].fullname}" /></td>
                                                                                        <td><c:out value="${staff[0].email}" /></td>
                                                                                        <td><c:out value="${staff[0].phoneNumber}" /></td>
                                                                                        <td style="max-width:250px !important;word-wrap:break-word !important;"><c:out value="${staff[0].acl}" /></td>
                                                                                        
                                                                                        <td><c:out value="${staff[0].timeCreated}" /></td>
                                                                                        <td><c:out value="${staff[0].timeModified}" /></td>
                                                                                        <td><c:out value="${staff[1]}" /></td>
                                                                                        <td><c:out value="${staff[3]}" />
                                                                                       
                                                                                     
                                                                                        </td>
                                                                                        <td>
															<div class="hidden-sm hidden-xs action-buttons">
																<c:if test="${userType=='user'}">

																<a class="green" href="${pageContext.request.contextPath}/staff/editstaff.html?id=${staff[0].staffId}">
																	<i class="ace-icon fa fa-pencil bigger-130"></i>
																</a>
                                                                                                                                </c:if>
                                                                                                                            <c:if test="${userType=='user'}">
                                                                                                                               <c:if test="${staff[4].email!=staff[0].email}">
																<a class="red" href="#" onclick="showDeleteModal('${pageContext.request.contextPath}/staff/delete.html','${staff[0].staffId}');">
																	<i class="ace-icon fa fa-trash-o bigger-130"></i>
																</a>
                                                                                                                                 </c:if>
                                                                                                                            </c:if>
															</div>

															<div class="hidden-md hidden-lg">
																<div class="inline pos-rel">
																	<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">
																		<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
																	</button>

																	<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																	<c:if test="${userType=='user'}">
                                                                                                                                              
                                                                                                                                                    <c:if test="${staff[4].email!=staff[0].email}">
																		<li>
																			<a href="${pageContext.request.contextPath}/staff/editstaff.html?id=${staff[0].staffId}" class="tooltip-success" data-rel="tooltip" title="Edit">
																				<span class="green">
																					<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																				</span>
																			</a>
																		</li>
                                                                                                                                                    </c:if>
                                                                                                                                        </c:if>
                                                                                                                                                <c:if test="${userType=='user'}">
                                                                                                                                                
																		<li>
																			<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete" onclick="showDeleteModal('${pageContext.request.contextPath}/product/delete.html','${product[0].prodId}');">
																				<span class="red">
																					<i class="ace-icon fa fa-trash-o bigger-120"></i>
																				</span>
																			</a>
																		</li>
                                                                                                                                                    </c:if>
                                                                                                                                               
																	</ul>
																</div>
															</div>
														</td>
                                                                                    </tr>
                                                                                                </c:if>
                                                                                                      </c:forEach>
				
													
												</tbody>
											</table>
										</div>
									</div>
								</div>

								

								<!-- PAGE CONTENT ENDS -->
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

		

	
		<!-- page specific plugin scripts -->

		<script src="../assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="../assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
		<script src="../assets/js/jquery.dataTables.min.js"></script>
		<script src="../assets/js/jquery.dataTables.bootstrap.min.js"></script>
		<script src="../assets/js/dataTables.buttons.min.js"></script>
		<script src="../assets/js/buttons.flash.min.js"></script>
		<script src="../assets/js/buttons.html5.min.js"></script>
		<script src="../assets/js/buttons.print.min.js"></script>
		<script src="../assets/js/buttons.colVis.min.js"></script>
		<script src="../assets/js/dataTables.select.min.js"></script>

                <script src="../functions/functions.js"></script>
		<!-- ace scripts -->
		<script src="../assets/js/ace-elements.min.js"></script>
		<script src="../assets/js/ace.min.js"></script>
                <script type="text/javascript">
			jQuery(function($) {
				//initiate dataTables plugin
				var myTable = 
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.DataTable( {
					bAutoWidth: false,
					"aoColumns": [null,
					 
					  null, null,null, null, null,null,null,null,
					  { "bSortable": false }
					],
					"aaSorting": [],
				
			    } );
			
				
				
				$.fn.dataTable.Buttons.defaults.dom.container.className = 'dt-buttons btn-overlap btn-group btn-overlap';
				
				new $.fn.dataTable.Buttons( myTable, {
					buttons: [
					  {
						"extend": "colvis",
						"text": "<i class='fa fa-search bigger-110 blue'></i> <span class='hidden'>Show/hide columns</span>",
						"className": "btn btn-white btn-primary btn-bold",
						columns: ':not(:first):not(:last)'
					  },
					  {
						"extend": "copy",
						"text": "<i class='fa fa-copy bigger-110 pink'></i> <span class='hidden'>Copy to clipboard</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "csv",
						"text": "<i class='fa fa-database bigger-110 orange'></i> <span class='hidden'>Export to CSV</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "excel",
						"text": "<i class='fa fa-file-excel-o bigger-110 green'></i> <span class='hidden'>Export to Excel</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "pdf",
						"text": "<i class='fa fa-file-pdf-o bigger-110 red'></i> <span class='hidden'>Export to PDF</span>",
						"className": "btn btn-white btn-primary btn-bold"
					  },
					  {
						"extend": "print",
						"text": "<i class='fa fa-print bigger-110 grey'></i> <span class='hidden'>Print</span>",
						"className": "btn btn-white btn-primary btn-bold",
						autoPrint: false,
						message: 'This print was produced using the Print button for DataTables'
					  }		  
					]
				} );
				myTable.buttons().container().appendTo( $('.tableTools-container') );
				
				//style the message box
				var defaultCopyAction = myTable.button(1).action();
				myTable.button(1).action(function (e, dt, button, config) {
					defaultCopyAction(e, dt, button, config);
					$('.dt-button-info').addClass('gritter-item-wrapper gritter-info gritter-center white');
				});
				
				
				var defaultColvisAction = myTable.button(0).action();
				myTable.button(0).action(function (e, dt, button, config) {
					
					defaultColvisAction(e, dt, button, config);
					
					
					if($('.dt-button-collection > .dropdown-menu').length == 0) {
						$('.dt-button-collection')
						.wrapInner('<ul class="dropdown-menu dropdown-light dropdown-caret dropdown-caret" />')
						.find('a').attr('href', '#').wrap("<li />")
					}
					$('.dt-button-collection').appendTo('.tableTools-container .dt-buttons')
				});
			
				////
			
				setTimeout(function() {
					$($('.tableTools-container')).find('a.dt-button').each(function() {
						var div = $(this).find(' > div').first();
						if(div.length == 1) div.tooltip({container: 'body', title: div.parent().text()});
						else $(this).tooltip({container: 'body', title: $(this).text()});
					});
				}, 500);
				
				
			
			
				$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
					e.stopImmediatePropagation();
					e.stopPropagation();
					e.preventDefault();
				});
				
				
				
				
				
			
				/********************************/
				//add tooltip for small view action buttons in dropdown menu
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				
				//tooltip placement on right or left
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
				
				
				
				
				/***************/
				$('.show-details-btn').on('click', function(e) {
					e.preventDefault();
					$(this).closest('tr').next().toggleClass('open');
					$(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
				});
				/***************/
				
				
			
			})
		</script>
                <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title">Sereba Accounting</h4>
            </div>
            <div class="modal-body">
              <p>Are you sure you want to delete this staff's record?</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default pull-left" data-dismiss="modal">No</button>
              <button id="ok" type="button" onclick="" class="btn btn-primary">Yes</button>
            </div>
          </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
      </div><!-- /.modal -->
	</body>
</html>
