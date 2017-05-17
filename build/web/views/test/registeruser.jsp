<%-- 
    Document   : registeruser
    Created on : May 13, 2017, 8:04:10 AM
    Author     : John
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register User Unit Test</title>
    </head>
    <body>
        <div id="formdata"></div>
        <div id="ajaxResponse"></div>

											<form method="post" onSubmit="submitFormTest('registerform', '../api/registerUser.html');return false;" id="registerform" action="#">
												<fieldset>
                                                                                                    <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="fullname" required class="form-control" placeholder="Fullname" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" required name="email" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>
                                                                                                        <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="store_name" required class="form-control" placeholder="Store name" />
															<i class="ace-icon fa fa-cart-arrow-down"></i>
														</span>
													</label>
                                                                                                     <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="tel" name="phone_no" required class="form-control" placeholder="Phone Number" />
															<i class="ace-icon fa fa-phone"></i>
														</span>
													</label>
													

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" required class="form-control" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="confirm_password" required class="form-control" placeholder="Repeat password" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
													</label>
                                                                                                   
                                                                                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<textarea id="form-field-11" class="autosize-transition form-control" required name="contact_address" placeholder="Contact Address"></textarea>
															<i class="ace-icon fa fa-bank"></i>
														</span>
                                                                                                              </label>
													

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">Reset</span>
														</button>

														<button onclick="submitFormTest('registerform', '../api/registerUser.html');return false;" type="submit" class="width-65 pull-right btn btn-sm btn-success" onSubmit="submitForm('registerform', 'index/adduser.html');return false;">
															<span class="bigger-110">Register</span>

															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
        <script src="../assets/js/jquery-2.1.4.min.js"></script>
                <script src="../assets/js/autosize.min.js"></script>
                 <script src="../functions/functions.js"></script>
    </body>
</html>
