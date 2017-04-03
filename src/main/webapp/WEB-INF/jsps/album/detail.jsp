<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- saved from url=(0041)http://v3.bootcss.com/examples/dashboard/ -->
<html lang="zh-CN">
<head>
<jsp:include page="../part/header.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="../part/top.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li>
						<a href="/">首页 </a>
					</li>
					<li class="active">
						<a href="/album">相册 <span class="sr-only">(current)</span></a>
					</li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="/album">相册</a></li>
				  <li class="active">相册1</li>
				</ol>
				<div class="row placeholders">
				  <div class="col-md-2">
						<img src="http://localhost/images/1.jpg" width="180" height="180" class="img-responsive img-thumbnail"/>
						<h4>图片1</h4>					  	
				  </div>
				  <div class="col-md-2">
						<img src="http://localhost/images/1.jpg" width="180" height="180" class="img-responsive img-thumbnail"/>
						<h4>图片1</h4>					  	
				  </div>
				  <div class="col-md-2">
						<img src="http://localhost/images/1.jpg" width="180" height="180" class="img-responsive img-thumbnail"/>
						<h4>图片1</h4>					  	
				  </div>
				  <div class="col-md-2">
						<img src="http://localhost/images/1.jpg" width="180" height="180" class="img-responsive img-thumbnail"/>
						<h4>图片1</h4>					  	
				  </div>
				  <div class="col-md-2">
						<img src="http://localhost/images/1.jpg" width="180" height="180" class="img-responsive img-thumbnail"/>
						<h4>图片1</h4>					  	
				  </div>
				  <div class="col-md-2">
						<img src="http://localhost/images/1.jpg" width="180" height="180" class="img-responsive img-thumbnail"/>
						<h4>图片1</h4>					  	
				  </div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../part/footer.jsp"></jsp:include>
	<jsp:include page="../part/common.jsp"></jsp:include>
</body>
</html>