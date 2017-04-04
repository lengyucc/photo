$(function(){
	$(document).on("click",".clickable",function(){
		var href = $(this).attr("link_url");
		if(href){
			window.location.href = $(this).attr("link_url");
		}
	});
});
function loading_show(){
	$("#loadingModal").modal();
}
function loading_hide(){
	$("#loadingModal").modal("hide");
}
function setHomeLinks(){
	$("#setHomeLinksModal").modal();
}
function viewPhoto(imgSrc,imgAlt){
	$("#viewPhotoModal").modal();
}
