<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</style>
<div id="loadingModal" data-backdrop="static" class="modal fade bs-example-modal-sm loading" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <img alt="" src="/images/loading.gif" class="img-circle"/>&nbsp;&nbsp;
      <!-- <span>正在处理，请稍后……</span> -->
    </div>
  </div>		
</div>
