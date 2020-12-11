$(document).ready(function(){
	$.post("header.do", function(data){
		$("#top").html(data);
	});
});