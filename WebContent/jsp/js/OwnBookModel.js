function submit(){
	$("#modal-form").modal("hide");
	$.ajax({
		url:"/BookstoreSSH/ModifyBookAction",
		data:{"id":bookId,
			"name":$("#name").val(),
			"author":$("#author").val(),
			"publisher":$("#publisher").val(),
			"price":$("#price").val(),
			"stock":$("#stock").val()
			},
		success: function(data){		
			var arr = $.evalJSON(data);
			showInfo(arr["message"]);
			var table = $("#book"+bookId);
			table.find(".name").text($("#name").val());
			table.find(".author").text($("#author").val());
			table.find(".publisher").text($("#publisher").val());
			table.find(".price").text($("#price").val());
			table.find(".stock").text($("#stock").val());
		}
	});
}
$(function(){
	$(".button-change").on("click", function(){
		bookId = $(this).parents("table").find(".id").text();
		$("#name").val($(this).parents("table").find(".name").text());
		$("#author").val($(this).parents("table").find(".author").text());
		$("#publisher").val($(this).parents("table").find(".publisher").text());
		$("#price").val($(this).parents("table").find(".price").text());
		$("#stock").val($(this).parents("table").find(".stock").text());
		$("#modal-form").modal("show");
	});
	$(".button-remove").on("click", function(){
		var that = this;
		$.ajax({
			url: "/BookstoreSSH/RemoveBookAction",
			data: {"bookId": $(this).parents("table").find(".id").text()},
			success:function(data){
					var arr = $.evalJSON(data);
					$(that).parents(".book-div").css("display", "none");
					showInfo(arr["message"]);	
				} 
			});
	});
});