<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- saved from url=(0041)http://v3.bootcss.com/examples/dashboard/ -->
<html lang="zh-CN">
<head>
<jsp:include page="../part/header.jsp"></jsp:include>
<style type="text/css">
	#previewPhotoModal .modal-body{
		height: 520px;
		overflow: auto;
	}
	.pname{
		margin-top: 6px;
		width: 100%;
		text-align: center;
	}
	#previewPhotoModal .col-md-2 {
	    margin-top: 10px;
	    padding-left: 4px;
	    padding-right: 4px;
	}
	.main .col-md-2 {
	    margin-top: 10px;
	    margin-bottom: 10px;
	    padding-left: 4px;
	    padding-right: 4px;
	}
	.mpager{
		text-align: center;
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
						<a href="/album">相册 <span class="sr-only">(current)</span></a>
					</li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="btns">
					<input type="file" name="up" id="upEle" multiple="multiple" onchange="up(this);"/>
					<button type="button" class="btn btn-info" onclick="triggerFileEleClick();">上传照片</button>
				</div>				
				
				<div class="row placeholders  gallery auto-loop">
				  <c:forEach items="${photos }" var="photo">
					  <div class="col-md-2 clickable" data-src="${imgServer }${photo.uri}">
						<img src="${imgServer }${photo.coverUri}" width="180" height="180" class="img-responsive img-thumbnail"/>
					  </div>
				  </c:forEach>
				</div>
				<div class="mpager">
					<jsp:include page="../part/pager.jsp">
						<jsp:param value="/photo?aid=${album.id }&" name="url"/>
						<jsp:param value="p" name="pageIdxKeyWord"/>
					</jsp:include>
				</div>
			</div>
		</div>
	</div>
	<div id="previewPhotoModal" data-backdrop="static" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	    <div class="modal-dialog modal-lg" role="document">
	      <div class="modal-content">
	
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	          <h4 class="modal-title" id="myLargeModalLabel">上传预览</h4>
	        </div>
	        <div class="modal-body">
				<div class="row placeholders gallery auto-loop">

				</div>
	        </div>
	        <div class="modal-footer">
	        	<input type="file" name="up" id="upEle" onchange="up(this);"/>
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="triggerFileEleClick();"  data-toggle="tooltip" data-placement="top" title="按住Crtl可选择多个">继续添加</button>
		        <button type="button" class="btn btn-primary" onclick="confirmUp();">确认上传</button>
		      </div>
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div>			
	<jsp:include page="../part/footer.jsp"></jsp:include>
	<jsp:include page="../part/common.jsp"></jsp:include>
	<script type="text/javascript">
		function triggerFileEleClick(){
			$("#upEle").trigger("click");
		}	
		function confirmUp(){
			loading_show();
			var photos = new Array();
			$("#previewPhotoModal .modal-body .row .mphoto").each(function(){
				var uri = $(this).attr("v_uri");
				var coverUri = $(this).attr("v_cover_uri");
				var isVideo = $(this).attr("v_is_video");
				
				var photo = new Object();
				photo.uri = uri;
				photo.coverUri = coverUri;
				photo.isVideo = isVideo;
				photos.push(photo);								
			});
			console.log(JSON.stringify(photos));
			
			$.ajax({
		        url: '/photo/create',
		        data: 'photos='+JSON.stringify(photos) + "&albumId=${album.id}",
		        type: 'POST',
		        dataType: 'JSON',
		        success: function(res){
		        	if(res.success){
		        		alert("上传成功!");
		        		window.location.reload();
		        	}else{
		            	alert(res.errMsg);
		        	}
	            },
	            error: function(){
	            	alert("error");
	            }
		    });		
			loading_hide();
		}
		function up(obj){
			var v = $(obj).val();
			if(v && v != ''){
				$("#previewPhotoModal").modal();
				loading_show();
				$.ajaxFileUpload({
					url : "/up?m=1",
					secureuri : false,
					fileElementId : "upEle",
					dataType: 'json',
					success : function(data, status, e) {
						loading_hide();
						if(data.success){ 
							var html = $("#previewPhotoModal .modal-body .row").html();	// 需要以这种形式重新渲染
							for(var i=0;i<data.data.length;i++){
								var d = data.data[i];
								html += '<div class="col-md-2 mphoto" data-src="'+data.imgServer+d.uri+'" v_cover_uri="'+d.coverUri+'" v_uri="'+d.uri+'" v_is_video="'+d.isVideo+'">';
								html += '	<img src="'+data.imgServer+d.coverUri+'" width="160" height="160" class="img-responsive img-rounded"/>';
								html += '</div>';
							}
							$("#previewPhotoModal .modal-body .row").html(html);	// 需要以这种形式重新渲染
							
							$("#previewPhotoModal .auto-loop").lightGallery({
								loop:true,
								auto:true,
								pause:4000
							});
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
		
		$(".main .auto-loop").lightGallery({
			loop:true,
			auto:true,
			pause:4000
		});
	</script>
</body>
</html>