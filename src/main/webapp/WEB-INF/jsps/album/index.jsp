<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- saved from url=(0041)http://v3.bootcss.com/examples/dashboard/ -->
<html lang="zh-CN">
<head>
<jsp:include page="../part/header.jsp"></jsp:include>
<style type="text/css">
	.placeholder img {
	    display: inline-block;
	    border-radius: inherit;
	}
	.breadcrumb{
		margin-bottom: 10px;
	}
	.btns{
		width: 100%;
		margin-bottom: 10px;
	}
	#upEle{
		display: none;
	}
	#albumCoverImg:hover{
		cursor: pointer;
		border: 1px solid gray;
	}
</style>
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
						<a href="#">相册 <span class="sr-only">(current)</span></a>
					</li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li class="active">相册</li>
				</ol>
				<div class="btns">
					<button type="button" class="btn btn-info" onclick="showCreateAlbumModal();">创建相册</button>
					<button type="button" class="btn btn-info">上传照片</button>
				</div>
				<div class="row placeholders">
					<c:forEach items="${albums }" var="album">
						<div class="col-md-2 clickable" link_url="/album/detail?aid=${album.id }">
							<img src="${imgServer }${album.coverUri}" width="180" height="180" class="img-responsive img-thumbnail"/>
							<h4>${album.name }</h4>
						</div>
					</c:forEach>					
				</div>
			</div>
		</div>
	</div>
	
	<div id="createAlbumModal" data-backdrop="static" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	    <div class="modal-dialog modal-lg" role="document">
	      <div class="modal-content">
	
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	          <h4 class="modal-title" id="myLargeModalLabel">创建相册</h4>
	        </div>
	        <div class="modal-body">
				<form class="form-horizontal">
				  <div class="form-group">
				    <label for="name" class="col-sm-2 control-label">相册名称</label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="name" name="name"/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="albumCoverImg" class="col-sm-2 control-label">相册封面</label>
				    <div class="col-sm-10">
				      <img id="albumCoverImg" title="点击更换" src="http://localhost/images/1.jpg" width="120" height="120" class="img-rounded" onclick="triggerFileEleClick();"/>
				      <input type="hidden" name="coverUri" value=""/>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="desc" class="col-sm-2 control-label">相册描述</label>
				    <div class="col-sm-10">
				      <textarea class="form-control" rows="3" name="desc" id="desc"></textarea>
				    </div>
				  </div>
				</form>	        	
	        </div>
	        <div class="modal-footer">
	        	<input type="file" name="up" id="upEle" onchange="up(this);"/>
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="createAlbum();">创建</button>
		      </div>
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div>		
	<jsp:include page="../part/footer.jsp"></jsp:include>
	<jsp:include page="../part/common.jsp"></jsp:include>

	<script type="text/javascript">
		function showCreateAlbumModal(){
			$("#createAlbumModal").modal();
		}
		function triggerFileEleClick(){
			$("#upEle").trigger("click");
		}
		function createAlbum(){
			var name = $("#createAlbumModal input[name='name']").val();
			var coverUri = $("#createAlbumModal input[name='coverUri']").val();
			name = $.trim(name);
			coverUri = $.trim(coverUri);
			if(name == ''){
				alert("请输入相册名称");
				return false;
			}
			if(name.length>8){
				alert("相册名称限8个字符");
			}
			/* if(coverUri == ''){
				alert("请选择相册封面");
				return false;
			} */
			loading_show();
			$.ajax({
		        url: "/album/create",
		        data: $("#createAlbumModal form").serialize(),
		        type: 'POST',
		        dataType: 'JSON',
		        success: function(res){
		        	loading_hide();
		        	if(res.success){
		        		alert("创建成功");
		        		window.location.reload();
		        	}else{
		            	alert(res.errMsg);
		        	}
	            },
	            error: function(){
		        	loading_hide();
	            }
		    });				
		}
		
		function up(obj){
			var v = $(obj).val();
			if(v && v != ''){
				loading_show();
				$.ajaxFileUpload({
					url : "/up?m=2",
					secureuri : false,
					fileElementId : "upEle",
					dataType: 'json',
					success : function(data, status, e) {
						loading_hide();
						if(data.success){
							var d = data.data[0];
							$("#albumCoverImg").attr("src",data.imgServer+d.coverUri);
							$("#createAlbumModal input[name='coverUri']").val(d.coverUri);
						}else{
							alert(data.errMsg);
						}
					},
					error:function(data,status,e){
						loading_hide();
						console.log("error:" + JSON.stringify(data)+"-" + status+"-" + e);
					}
				});
			}
		}
	</script>
	
</body>
</html>