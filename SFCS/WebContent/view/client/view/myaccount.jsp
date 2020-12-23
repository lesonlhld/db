<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<c:url value="/view/client/static" var="url"></c:url>
<meta charset="UTF-8">
<title>Thông tin tài khoản</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
<!--
	$(document).ready(function() {

		var readURL = function(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.avatar').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$(".file-upload").on('change', function() {
			readURL(this);
		});
	});
//-->
</script>

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico">

<!-- Web Fonts -->
<link rel='stylesheet' type='text/css'
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

<!-- CSS Global Compulsory -->
<link rel="stylesheet"
	href="${url}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${url}/css/shop.style.css">

<!-- CSS Header and Footer -->
<link rel="stylesheet" href="${url}/css/headers/header-v5.css">
<link rel="stylesheet" href="${url}/css/footers/footer-v4.css">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet" href="${url}/plugins/animate.css">
<link rel="stylesheet" href="${url}/plugins/line-icons/line-icons.css">
<link rel="stylesheet"
	href="${url}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${url}/plugins/scrollbar/css/jquery.mCustomScrollbar.css">

<!-- CSS Page Style -->
<link rel="stylesheet" href="${url}/css/pages/log-reg-v3.css">

<!-- Style Switcher -->
<link rel="stylesheet" href="${url}/css/plugins/style-switcher.css">

<!-- CSS Theme -->
<link rel="stylesheet" href="${url}/css/theme-colors/default.css"
	id="style_color">

<!-- CSS Customization -->
<link rel="stylesheet" href="${url}/css/custom.css">

<body>
	<body class="header-fixed">
		<div class="wrapper">
			<!--=== Header v5 ===-->
			<div class="header-v5 header-${url}">
				<!-- Topbar v3 -->
				<jsp:include page="topbar.jsp"></jsp:include>
	
				<!-- End Topbar v3 -->
	
				<!-- Navbar -->
				<jsp:include page="navbar.jsp"></jsp:include>
				<!-- End Navbar -->
			</div>
			<!--=== End Header v5 ===-->
	
			<!--=== Breadcrumbs v4 ===-->
			<div class="breadcrumbs-v4">
				<div class="container">
					<span class="page-name">Thông tin tài khoản</span>
					<h1>
						<span class="shop-red">SMART FOOD BÁCH KHOA XIN CHÀO</span>
					</h1>
					<ul class="breadcrumb-v4-in">
						<li><a href="/welcome">Trang chủ</a></li>
						<li class="active">Thông tin tài khoản</li>
					</ul>
				</div>
				<!--/end container-->
			</div>
			<!--=== End Breadcrumbs v4 ===-->
		
			<!--=== Information ===-->
			<div class="log-reg-v4 content-md margin-bottom-30">
				<div class="container bootstrap snippet">
					<form class="form" action="${myaccount}" method="post"
								id="registrationForm" enctype="multipart/form-data">
						<!--left col-->
						<div class="col-sm-3">
							<c:url value="/member/myaccount" var="myaccount"></c:url>
								<input name="role" value="${sessionScope.account.roleId}" hidden="" >
								<input name="id" value="${sessionScope.account.id}" hidden="">
								<div class="text-center">
									<c:url value="/image?fname=${sessionScope.account.avatar}"
										var="imgUrl"></c:url>
									<img src="${imgUrl }" class="avatar img-square img-thumbnail"
										alt="avatar">
									<h6>Thay đổi hình đại diện</h6>
									<input type="file" name="avatar" class="text-center center-block file-upload">
								</div>
							<br>
						</div>
						<!--/col-3-->
						<div class="col-sm-9">
							<div class="tab-content">
								<div class="tab-pane active" id="home">
									<div class="form-group">
									<h4 style="color: red;"> ${alertMsg}</h4>
										<div class="col-xs-6">
											<label for="firstname"><h4>Họ:</h4></label> <input
												type="text" class="form-control" name="firstname" id="firstname"
												value="${sessionScope.account.firstname}"
												title="enter your first name if any.">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-6">
											<label for="lastname"><h4>Tên:</h4></label> <input
												type="text" class="form-control" name="lastname" id="lastname"
												value="${sessionScope.account.lastname}"
												title="enter your first name if any.">
										</div>
									</div>
														
									<div class="form-group">
										<div class="col-xs-6">
											<label for="birthday"><h4>Ngày sinh:</h4></label> <input
												type="date" class="form-control" name="birthday"
												id="birthday" value="${sessionScope.account.birthday}">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-6">
											<label for="gender"><h4>Giới tính:</h4></label>
											<div class="checkbox">
												<c:choose>
													<c:when test="${sessionScope.account.gender == 'M'}">
														<label> <input type="radio" value="M" name="gender" checked="checked" />Nam </label>
														<label> <input type="radio" value="F" name="gender"/>Nữ </label>
													</c:when>
													<c:otherwise>
														<label> <input type="radio" value="M" name="gender" />Nam </label>
														<label> <input type="radio" value="F" name="gender" checked="checked" />Nữ </label>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-6">
											<label for="username"><h4>Tên đăng nhập:</h4></label> <input
												type="text" class="form-control" name="username"
												id="username" value="${sessionScope.account.username}" disabled="disabled">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-6">
											<label for="email"><h4>Email:</h4></label> <input
												type="text" class="form-control" name="email" id="email"
												value="${sessionScope.account.email}"
												title="enter your first name if any.">
										</div>
									</div>
									
									<div class="form-group">
			
										<div class="col-xs-6">
											<label for="phone"><h4>Số điện thoại:</h4></label> <input
												type="text" class="form-control" name="phone" id="phone"
												value="${sessionScope.account.phone}"
												title="enter your first name if any.">
										</div>
									</div>
									
									<div class="form-group">
			
										<div class="col-xs-6">
											<label for="address"><h4>Địa chỉ:</h4></label> <input
												type="text" class="form-control" name="address" id="address"
												value="${sessionScope.account.address }"
												title="enter your first name if any.">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-6">
											<label for="password"><h4>Mật khẩu:</h4></label> <input
												type="password" class="form-control" name="password" id="phone"
												value="${sessionScope.account.password}"
												title="enter your phone number if any.">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-6">
										<label for="passwordConfirm"><h4>Nhập lại mật khẩu:</h4></label> <input
												type="password" class="form-control" name="passwordConfirm" id="phone"
												required="required"
												title="enter your phone number if any.">
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-xs-12">
											<br>
											<button class="btn btn-lg btn-success" type="submit">
												<i class="glyphicon glyphicon-ok-sign"></i> Lưu lại
											</button>
											<button class="btn btn-lg" type="reset">
												<i class="glyphicon glyphicon-repeat"></i> Đặt lại
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<!--/end container-->
			</div>
			<!--=== End Information ===-->
			
			<!--=== Shop Suvbscribe ===-->
			<div class="shop-subscribe">
				<div class="container">
					<div class="row">
						<div class="col-md-8 md-margin-bottom-20">
							<h2>đăng ký để nhận <strong>bản tin hàng tuần</strong> của chúng tôi</h2>
						</div>
						<div class="col-md-4">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="Nhập địa chỉ email..."> <span
									class="input-group-btn">
									<button class="btn" type="button">
										<i class="fa fa-envelope-o"></i>
									</button>
								</span>
							</div>
						</div>
					</div>
				</div>
				<!--/end container-->
			</div>
			<!--=== End Shop Suvbscribe ===-->
	
			<!--=== Footer v4 ===-->
			<jsp:include page="footer.jsp"></jsp:include>
			<!--=== End Footer v4 ===-->
		</div>
		<!--/wrapper-->
	
		<!-- JS Global Compulsory -->
		<script src="${url}/plugins/jquery/jquery.min.js"></script>
		<script src="${url}/plugins/jquery/jquery-migrate.min.js"></script>
		<script src="${url}/plugins/bootstrap/js/bootstrap.min.js"></script>
		<!-- JS Implementing Plugins -->
		<script src="${url}/plugins/back-to-top.js"></script>
		<script src="${url}/plugins/smoothScroll.js"></script>
		<script
			src="${url}/plugins/scrollbar/js/jquery.mCustomScrollbar.concat.min.js"></script>
		<script
			src="${url}/plugins/sky-forms-pro/skyforms/js/jquery.form.min.js"></script>
		<script
			src="${url}/plugins/sky-forms-pro/skyforms/js/jquery.validate.min.js"></script>
		<!-- JS Customization -->
		<script src="${url}/js/custom.js"></script>
		<!-- JS Page Level -->
		<script src="${url}/js/shop.app.js"></script>
		<script src="${url}/js/forms/page_login.js"></script>
		<script src="${url}/js/forms/page_contact_form.js"></script>
		<script>
			jQuery(document).ready(function() {
				App.init();
				Login.initLogin();
				App.initScrollBar();
				PageContactForm.initPageContactForm();
			});
		</script>
		<!--[if lt IE 9]>
	    <script src="${url}/plugins/respond.js"></script>
	    <script src="${url}/plugins/html5shiv.js"></script>
	    <script src="${url}/js/plugins/placeholder-IE-fixes.js"></script>    
	    <script src="${url}/plugins/sky-forms-pro/skyforms/js/sky-forms-ie8.js"></script>
	<![endif]-->
		<!--[if lt IE 10]>
	    <script src="${url}/plugins/sky-forms-pro/skyforms/js/jquery.placeholder.min.js"></script>
	<![endif]-->
	
	</body>
</body>
</html>