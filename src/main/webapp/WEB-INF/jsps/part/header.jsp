<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="http://v3.bootcss.com/favicon.ico">

<title>Family photos</title>

<!-- Bootstrap core CSS -->
<link href="/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<script src="/js/ie-emulation-modes-warning.js"></script>

<style type="text/css">
	#loadingModal{
		padding-top: 10%;
		z-index: 1050;
		//background-color: #202020;
	}
	#loadingModal .modal-content {
	    border: none;
	    -webkit-box-shadow: none;
    	box-shadow: none;
    	background-color: rgba(242, 222, 222, 0);
    	vertical-align: bottom;
    	text-align: center;
	}
	#loadingModal .modal-content span{
		color: white;
		font-size: rgba(255, 255, 255, 0.63);
		font-weight: bold;
	}
	.clickable:hover{
		cursor: pointer;
	}
</style>