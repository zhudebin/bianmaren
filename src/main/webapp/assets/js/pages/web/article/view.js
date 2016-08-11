initRequirePath();

require(['jquery'],function($){
	require(['common','commonexec','sweetalert','highlightjs'],function(common,commonexec,sweetalert,highlightjs){

		//随机设置背景
		var picnum = parseInt(Math.random()*(1-27+1)+27);
		$("#topBox").css("background-image", "url('"+g_base_path+"/assets/img/photos/photo"+picnum+"@2x.jpg')");
		$("#userBox").css("background-image", "url('"+g_base_path+"/assets/img/photos/photo"+picnum+".jpg')");


		var $hits = $("#hits");

		var articleId = $("#articleId").val()
		// 查看点击数
		$.ajax({
			url: g_base_path+"/article/hits/"+articleId+".do",
			type: "GET",
			success: function(data) {
				$hits.text(data);
			}
		});
		
		//图片自适应
		$(".articleContent img").css({"max-width":"100%"});

		//打赏
		$(".da-shang").click(function(){
			var img = $(this).attr("data-img");
			swal({   title: "<img src='"+img+"'  style='max-width: 150px' />",   text: "谢谢土豪! 有你们的支持，我们才能做的更好",  html: true });
		});


		//代码高亮
		$("pre").each(function(index,item){
			hljs.highlightBlock(item);
		});

	});
});