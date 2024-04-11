/**
 * 
 */
const cPath = document.body.dataset.contextPath;
$(function(){
	const $modal = $("#exampleModal").on("show.bs.modal", function(event){
		let tr = event.relatedTarget; 
		let memId = $(tr).data("memId");
		let url = `${cPath}/member/memberDetail.do`;
		let method = "get";
		$.ajax({
			url:url
			,method:method
			,dataType:"json"
			,data:{
				who:memId
			}, success:function({member}, status, jqXHR){
				$modal.find("td[id]").each(function(index, td){
					let propName = td.id;
					td.innerHTML = member[propName];
				});
				//$updateBtn.data("who", member.memId);
				
			}, error:function(jqXHR, status, errorText){
				console.log(jqXHR, status, errorText);
			}
		});
	}).on("hidden.bs.modal", function(){
		$modal.find("td[id]").html("");
		//$updateBtn.removeData("who");
	});
	
	$("tr[data-mem-id].active").trigger("click");
	

});































