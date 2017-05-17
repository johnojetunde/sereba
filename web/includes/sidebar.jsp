<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
=======
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
<div id="sidebar" class="sidebar                  responsive                    ace-save-state">
				<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
				</script>
<<<<<<< HEAD
<c:set var="acl" value='<%= session.getAttribute("aclArray") %>' scope="application" /> 
<c:set var="userType" value='<%= session.getAttribute("userType") %>' scope="application" />


				<ul class="nav nav-list">
					<li class="active">
						<a href="#">
=======

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="ace-icon fa fa-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="ace-icon fa fa-users"></i>
						</button>

						<button class="btn btn-danger">
							<i class="ace-icon fa fa-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->

				<ul class="nav nav-list">
					<li class="active">
						<a href="index.html">
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text"> Dashboard </span>
						</a>

						<b class="arrow"></b>
					</li>

                                        <li class="">
<<<<<<< HEAD
						<a href="${pageContext.request.contextPath}/user/profile.html">
=======
						<a href="calendar.html">
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							<i class="menu-icon fa fa-user"></i>

							<span class="menu-text">
								Profile

								
							</span>
						</a>

						<b class="arrow"></b>
					</li>
                                        
					<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-file-o"></i>

							<span class="menu-text">
								Products

								<span class="badge badge-primary">5</span>
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>
<<<<<<< HEAD
                                        
                                               
                                            
                                            
    
						<ul class="submenu">
                                                   <c:if test="${acl.add_product=='add_product'}">
							<li class="">
								<a href="${pageContext.request.contextPath}/product/addproduct.html">
=======

						<ul class="submenu">
							<li class="">
								<a href="../product/addproduct.html">
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
									<i class="menu-icon fa fa-caret-right"></i>
									Add Product
								</a>

								<b class="arrow"></b>
							</li>
<<<<<<< HEAD
                                                   </c:if>
                                                        <c:if test="${acl.view_product=='view_product'}">
							<li class="">
								<a href="${pageContext.request.contextPath}/product/viewproduct.html">
									<i class="menu-icon fa fa-caret-right"></i>
									View Product
								</a>

								<b class="arrow"></b>
							</li>
                                                        </c:if>
                                                         <c:if test="${acl.add_inventory=='add_inventory'}">
							<li class="">
								<a href="${pageContext.request.contextPath}/product/addinventory.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Stock Product
=======

							<li class="">
								<a href="../product/viewproduct.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Manage Product
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
								</a>

								<b class="arrow"></b>
							</li>
<<<<<<< HEAD
                                                        </c:if>
                                                         <c:if test="${acl.view_inventory=='view_inventory'}">
<!--							<li class="">
								<a href="${pageContext.request.contextPath}/product/viewinventory.html">
									<i class="menu-icon fa fa-caret-right"></i>
									View Stocking History
								</a>

								<b class="arrow"></b>
							</li>-->
                                                        </c:if>
=======

>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							
						</ul>
					</li>
                                        
                                          
					<li class="">
						<a href="#" class="dropdown-toggle">
<<<<<<< HEAD
							<i class="menu-icon fa fa-credit-card"></i>
=======
							<i class="menu-icon fa fa-cart-plus"></i>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1

							<span class="menu-text">
								Sales

<<<<<<< HEAD
								
=======
								<span class="badge badge-primary">5</span>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
<<<<<<< HEAD
                                                     <c:if test="${acl.add_sale=='add_sale'}">
							<li class="">
								<a href="${pageContext.request.contextPath}/sale/addsale.html">
=======
							<li class="">
								<a href="#">
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
									<i class="menu-icon fa fa-caret-right"></i>
									Add Sales
								</a>

								<b class="arrow"></b>
							</li>
<<<<<<< HEAD
                                                        </c:if>
                                                         <c:if test="${acl.view_sale=='view_sale'}">
							<li class="">
								<a href="${pageContext.request.contextPath}/sale/viewsale.html">
									<i class="menu-icon fa fa-caret-right"></i>
									View Sales
=======

							<li class="">
								<a href="error-404.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Manage Sales
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
								</a>

								<b class="arrow"></b>
							</li>
<<<<<<< HEAD
                                                         </c:if>
=======
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1

							
						</ul>
					</li>
                                        
                                        <li class="">
						<a href="#" class="dropdown-toggle">
<<<<<<< HEAD
							<i class="menu-icon fa  fa-book"></i>
=======
							<i class="menu-icon fa fa-cart-plus"></i>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1

							<span class="menu-text">
								Credit

<<<<<<< HEAD
								
=======
								<span class="badge badge-primary">5</span>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
<<<<<<< HEAD
                                                     <c:if test="${acl.add_credit=='add_credit'}">
							<li class="">
								<a href="${pageContext.request.contextPath}/credit/addcredit.html">
=======
							<li class="">
								<a href="#">
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
									<i class="menu-icon fa fa-caret-right"></i>
									Add Credit Plan
								</a>

								<b class="arrow"></b>
							</li>
<<<<<<< HEAD
                                                     </c:if>
                                                         <c:if test="${acl.view_credit=='view_credit'}">
                                                          
							<li class="">
								<a href="${pageContext.request.contextPath}/credit/viewcredit.html">
=======

							<li class="">
								<a href="error-404.html">
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
									<i class="menu-icon fa fa-caret-right"></i>
									Manage Credit
								</a>

								<b class="arrow"></b>
							</li>
<<<<<<< HEAD
                                                         </c:if>
=======
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1

							
						</ul>
					</li>
                                        
                                        <li class="">
						<a href="#" class="dropdown-toggle">
<<<<<<< HEAD
							<i class="menu-icon fa fa-pencil-square-o"></i>

							<span class="menu-text">
								Income/Expenditure

								
=======
							<i class="menu-icon fa fa-cart-plus"></i>

							<span class="menu-text">
								Inventory

								<span class="badge badge-primary">5</span>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
<<<<<<< HEAD
                                                     <c:if test="${acl.add_income_expense=='add_income_expense'}">
							<li class="">
								<a href="${pageContext.request.contextPath}/inventory/addincomeexpense.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Add Income/Expenditure 
=======
							<li class="">
								<a href="#">
									<i class="menu-icon fa fa-caret-right"></i>
									Add Inventory 
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
								</a>

								<b class="arrow"></b>
							</li>
<<<<<<< HEAD
                                                     </c:if>
                                                         <c:if test="${acl.view_income_expense=='view_income_expense'}">
							<li class="">
								<a href="${pageContext.request.contextPath}/inventory/viewincomeexpense.html">
									<i class="menu-icon fa fa-caret-right"></i>
									View Income/Expenditure
=======

							<li class="">
								<a href="error-404.html">
									<i class="menu-icon fa fa-caret-right"></i>
									View Inventory
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
								</a>

								<b class="arrow"></b>
							</li>
<<<<<<< HEAD
                                                         </c:if>
=======
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1

							
						</ul>
					</li>
<<<<<<< HEAD
                                         <c:if test="${acl.reports=='reports'}">
                                        <li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-table"></i>
=======
                                        
                                        <li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-cart-plus"></i>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1

							<span class="menu-text">
								Tabular Reports

<<<<<<< HEAD
								
=======
								<span class="badge badge-primary">5</span>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
<<<<<<< HEAD
                                                    
							<li class="">
								<a href="${pageContext.request.contextPath}/report/averagesales.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Average Sales
								</a>

								<b class="arrow"></b>
							</li>
                                                        <li class="">
								<a href="${pageContext.request.contextPath}/report/salesfreq.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Sales Frequency
								</a>

								<b class="arrow"></b>
							</li>
                                                       
=======
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							<li class="">
								<a href="#">
									<i class="menu-icon fa fa-caret-right"></i>
									Profile & Loss 
								</a>

								<b class="arrow"></b>
							</li>

							<li class="">
								<a href="error-404.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Balance Sheets
								</a>

								<b class="arrow"></b>
							</li>

							
						</ul>
					</li>
<<<<<<< HEAD
                                         </c:if>
                                        
                                        <c:if test="${acl.charts=='charts'}">
                                         <li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-bar-chart-o"></i>
=======
                                        
                                         <li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-cart-plus"></i>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1

							<span class="menu-text">
								Charts

<<<<<<< HEAD
								
=======
								<span class="badge badge-primary">5</span>
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="#">
									<i class="menu-icon fa fa-caret-right"></i>
									Pie Chart
								</a>

								<b class="arrow"></b>
							</li>

							<li class="">
								<a href="error-404.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Bar Chart
								</a>

								<b class="arrow"></b>
							</li>

							
						</ul>
					</li>
<<<<<<< HEAD
                                        </c:if>
                                        <c:if test="${userType=='user'}">
                                        <li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-users"></i>

							<span class="menu-text">
								Staffs

								
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
                                                     
							<li class="">
								<a href="${pageContext.request.contextPath}/staff/addstaff.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Add Staff
								</a>

								<b class="arrow"></b>
							</li>
                                                    
                                                        

							<li class="">
								<a href="${pageContext.request.contextPath}/staff/viewstaff.html">
									<i class="menu-icon fa fa-caret-right"></i>
									Manage Staffs
								</a>

								<b class="arrow"></b>
							</li>
                                                        

							
						</ul>
					</li>
                                        </c:if>
                                         <li class="">
						<a href="#" class="dropdown-toggle">
							<i class="menu-icon fa fa-money"></i>

							<span class="menu-text">
								Subscription

								
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>

						<b class="arrow"></b>

						<ul class="submenu">
							<li class="">
								<a href="#">
									<i class="menu-icon fa fa-caret-right"></i>
									Subscribe
								</a>

								<b class="arrow"></b>
							</li>

							<li class="">
								<a href="error-404.html">
									<i class="menu-icon fa fa-caret-right"></i>
									View Subscriptions
								</a>

								<b class="arrow"></b>
							</li>

							
						</ul>
					</li>
=======
>>>>>>> 722dac123665b8505952013f6722c2935e6991f1

					

					

					
				</ul><!-- /.nav-list -->

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>
