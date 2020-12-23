<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url value="/view/admin/static" var="url"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Add User</title>
<!-- BOOTSTRAP STYLES-->
<link href="${url}/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLES-->
<link href="${url}/css/font-awesome.css" rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="${url}/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="wrapper">
		<jsp:include page="/view/admin/view/nav-bar.jsp"></jsp:include>
		<!-- /. NAV TOP  -->
		<jsp:include page="/view/admin/view/slide-bar.jsp"></jsp:include>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<h2>Edit User</h2>
						<h5>You can edit info user in here</h5>
					</div>
				</div>
				<!-- /. ROW  -->
				<hr />
				<div class="row">
					<div class="col-md-12">
						<!-- Form Elements -->
						<div class="panel panel-default">
							<div class="panel-heading">Add User</div>
							<h3 style="color: red;"> ${errMsg}</h3>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-6">
										<h3>User:</h3>										
										<form role="form" action="add"  method="post" enctype="multipart/form-data">
											<div class="form-group">
												<label>User Name:</label> <input class="form-control"
													placeholder="Please enter username" name="username" />
											</div>
											<div class="form-group">
												<label>Password:</label> <input class="form-control"
													placeholder="Please enter password" type="password"
													name="password" />
											</div>									
											<div class="form-group">
												<label>First Name:</label> <input class="form-control"
													placeholder="Please enter firstname" name="firstname" />
											</div>
											<div class="form-group">
												<label>Last Name:</label> <input class="form-control"
													placeholder="Please enter lastname" name="lastname" />
											</div>
											<div class="form-group">
												<label>Birth Day:</label> <input class="form-control"
													type="date" name="birthday" />
											</div>
											<div class="form-group">
												<label>Gender:</label>
												<div class="checkbox">
													<label> <input type="radio" value="M" name="gender"/>Male</label> 
													<br> <label> <input type="radio" value="F" name="gender"/>Female</label>
												</div>
											</div>
											<div class="form-group">
												<label>Email:</label> <input class="form-control"
													placeholder="Please enter email" name="email" />
											</div>
											<div class="form-group">
												<label>Phone:</label> <input class="form-control"
													placeholder="Please enter phone number" name="phone" />
											</div>
											<div class="form-group">
												<label>Address:</label> <input class="form-control"
													placeholder="Please enter address" name="address" />
											</div>									
											<div class="form-group">
												<label>Role:</label>
												<div class="checkbox">
													<c:forEach items="${roleList }" var="r">
														<label> <input type="radio" value=${r.id } name="role" />${r.name } </label><br>
													</c:forEach>
												</div>								
											</div>
											<div class="form-group">
												<label>Avatar</label> <input type="file" name="avatar" />
											</div>
											<button type="submit" class="btn btn-default">Add</button>
											<button type="reset" class="btn btn-primary">Reset</button>
										</form>


									</div>
								</div>
							</div>
						</div>
						<!-- End Form Elements -->
					</div>
				</div>
				<!-- /. ROW  -->
				<div class="row">
					<div class="col-md-12"></div>
				</div>
				<!-- /. ROW  -->
			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
	<!-- JQUERY SCRIPTS -->
	<script src="${url}/js/jquery-1.10.2.js"></script>
	<!-- BOOTSTRAP SCRIPTS -->
	<script src="${url}/js/bootstrap.min.js"></script>
	<!-- METISMENU SCRIPTS -->
	<script src="${url}/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="${url}/js/custom.js"></script>

</body>
</html>