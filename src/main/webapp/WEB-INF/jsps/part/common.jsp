<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="loadingModal" data-backdrop="static" class="modal fade bs-example-modal-sm loading" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <img alt="" src="/images/loading.gif" class="img-circle"/>&nbsp;&nbsp;
    </div>
  </div>		
</div>
<div id="viewPhotoModal" data-backdrop="static" class="modal fade bs-example-modal-lg loading" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <img alt="" src="http://localhost/1.jpg"/>&nbsp;&nbsp;
    </div>
  </div>		
</div>
<div id="setHomeLinksModal" data-backdrop="static" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
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
			  <tbody>
			  	<tr>
			  		<td>1</td>
			  		<td>
			  			<img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="50" height="50" class="img-circle"/>
			  		</td>
			  		<td>
			  			<input type="text" value="" name="name"/>
			  		</td>
			  		<td>
			  			<input type="text" value="" name="shortDesc"/>
			  		</td>
			  		<td>
			  			
			  		</td>
			  		<td>沐夏专属</td>
			  		<td>沐夏专属</td>
			  		<td>沐夏专属</td>
			  	</tr>
			  </tbody>
			</table>
        </div>
        <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>	
<script>

</script>