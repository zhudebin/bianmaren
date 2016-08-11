/*
 * 	author 		  :  dengwenbing
 * 	describe: 	  :  公共要执行的模块
 */

initRequirePath();

require(['jquery'],function($){
	require(['common','validate'],function(common,validate){
		
		/**
		 * 解决ie下数组indexOf 的问题
		 */
		if(!Array.prototype.indexOf){
		  Array.prototype.indexOf = function(elt /*, from*/){
		    var len = this.length >>> 0;
		    var from = Number(arguments[1]) || 0;
		    from = (from < 0)
		         ? Math.ceil(from)
		         : Math.floor(from);
		    if (from < 0)
		      from += len;
		    for (; from < len; from++){
		      if (from in this &&
		          this[from] === elt)
		        return from;
		    }
		    return -1;
		  };
		}
;

		//页面消息不为空就直接显示出来
		if("undefined" != typeof pageMessage){
		 	common.showTipsMessage(pageMessage);
		}

		//初始化列表通用组件
		common.listPageTemplateFunction();

		//初始化输入页面
		var $inputForm = $("#inputForm");
		var $browserButtonByClass = $(".browserButton");

		$.fn.extend(common.fileBrowerFunctionObject);
		$browserButtonByClass.browser();

		$.validator.setDefaults({
			errorPlacement:function(error,element) {
				$(element).parent().parent().addClass('has-error');
				$(element).parent().find(".help-block").remove();
				var e = $(error).get(0);
				var message = '<div class="help-block text-right animated fadeInDown">'+e.innerHTML+'</div>';
				$(element).after($(message));
			},
			success:function(error,element) {
				$(element).parent().parent().removeClass('has-error');
				$(element).parent().find(".help-block").remove();
			}
		});

		//验证表单
		var validator = $inputForm.validate();
		$.extend($.validator.messages, {
			required: "这是必填字段",
			remote: "请修正此字段",
			email: "请输入有效的电子邮件地址",
			url: "请输入有效的网址",
			date: "请输入有效的日期",
			dateISO: "请输入有效的日期 (YYYY-MM-DD)",
			number: "请输入有效的数字",
			digits: "只能输入数字",
			creditcard: "请输入有效的信用卡号码",
			equalTo: "你的输入不相同",
			extension: "请输入有效的后缀",
			maxlength: $.validator.format("最多可以输入 {0} 个字符"),
			minlength: $.validator.format("最少要输入 {0} 个字符"),
			rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
			range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
			max: $.validator.format("请输入不大于 {0} 的数值"),
			min: $.validator.format("请输入不小于 {0} 的数值")
		});



	});
	
});