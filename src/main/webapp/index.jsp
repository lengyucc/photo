<!DOCTYPE html>
<!-- saved from url=(0041)http://v3.bootcss.com/examples/dashboard/ -->
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="http://v3.bootcss.com/favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<script src="/js/ie-emulation-modes-warning.js"></script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Family photos</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Dashboard</a></li>
					<li><a href="javascript:void(0);" onclick="setHomeLinks();">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">Help</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active">
						<a href="#">Home <span class="sr-only">(current)</span></a>
					</li>
					<li><a href="#">Albums</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Home</h1>

				<div class="row placeholders">
					<div class="col-xs-6 col-sm-3 placeholder">
						<img
							src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
							width="200" height="200" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img
							src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
							width="200" height="200" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="setHomeLinksModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	    <div class="modal-dialog modal-lg" role="document">
	      <div class="modal-content">
	
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	          <h4 class="modal-title" id="myLargeModalLabel">首页设置</h4>
	        </div>
	        <div class="modal-body">
	          <table class="table table-bordered">
				  <thead>
				  	<tr>
				  		<th>#</th>
				  		<th>封面</th>
				  		<th>名称</th>
				  		<th>短描述</th>
				  		<th>链接到</th>
				  		<th>排序数值</th>
				  		<th>描述</th>
				  		<th>操作</th>
				  	</tr>
				  </thead>
				</table>
	        </div>
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div>	

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/js/jquery-3.2.0.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="/js/jquery-3.2.0.min.js"><\/script>')
	</script>
	<script src="/lib/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/js/ie10-viewport-bug-workaround.js"></script>

	<script type="text/javascript">
		function setHomeLinks(){
			$("#setHomeLinksModal").modal();
		}
	</script>
</body>
</html>