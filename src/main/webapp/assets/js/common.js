/**
 * 公共函数定义
 * Created by dengwenbing on 2016-06-16.
 */

initRequirePath();


define('common',['jquery','jQueryForm','layer','table2excel','bootstrap_notify','cookie'],function($,jQueryForm,layer,table2excel,bootstrap_notify,Cookies){

    /**
     *
     * @Title: loadCss
     * @Description: 动态加载css
     * @Author:dengwenbing
     * @param url
     * @param callback
     */
    function loadCss(url,callback){
        var css = document.createElement ("link")
        css.type = "text        /css";
        css.rel="stylesheet";
        css.rev = "stylesheet";
        css.media = "screen";
        if (css.readyState){ //IE
            css.onreadystatechange = function(){
                if (css.readyState == "loaded" || css.readyState == "complete"){
                    css.onreadystatechange = null;
                    callback();
                }
            };
        } else { //Others
            css.onload = function(){
                callback();
            };
        }
        css.href=url;
        document.getElementsByTagName("head")[0].appendChild(css);
    }

    /** 美化时间显示*/
    function jsDateDiff(publishTime){
        var d_minutes,d_hours,d_days;
        var timeNow = parseInt(new Date().getTime()/1000);
        var d;
        d = timeNow - publishTime;
        d_days = parseInt(d/86400);
        d_hours = parseInt(d/3600);
        d_minutes = parseInt(d/60);
        if(d_days>0 && d_days<4){
            return d_days+"天前";
        }else if(d_days<=0 && d_hours>0){
            return d_hours+"小时前";
        }else if(d_hours<=0 && d_minutes>0){
            return d_minutes+"分钟前";
        }else if(d_days<=0 && d_hours<=0 && d_minutes<=0){
            return parseInt(d,10)+"秒前";
        }else{
            var s = new Date(publishTime*1000);
            return s.getFullYear()+"年"+(s.getMonth()+1)+"月"+s.getDate()+"日";
        }
    }

    /**
     将数值四舍五入后格式化.
     @param num 数值(Number或者String)
     @param cent 要保留的小数位(Number)
     @param isThousand 是否需要千分位 0:不需要,1:需要(数值类型);
     @return 格式的字符串,如'1,234,567.45'
     @type String
     */
    function formatNumber(num,cent,isThousand){
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))//检查传入数值为数值类型.
            num = "0";
        if(isNaN(cent))//确保传入小数位为数值型数值.
            cent = 0;
        cent = parseInt(cent);
        cent = Math.abs(cent);//求出小数位数,确保为正整数.
        if(isNaN(isThousand))//确保传入是否需要千分位为数值类型.
            isThousand = 0;
        isThousand = parseInt(isThousand);
        if(isThousand < 0)
            isThousand = 0;
        if(isThousand >=1) //确保传入的数值只为0或1
            isThousand = 1;
        sign = (num == (num = Math.abs(num)));//获取符号(正/负数)
        //Math.floor:返回小于等于其数值参数的最大整数
        num = Math.floor(num*Math.pow(10,cent)+0.50000000001);//把指定的小数位先转换成整数.多余的小数位四舍五入.
        cents = num%Math.pow(10,cent); //求出小数位数值.
        num = Math.floor(num/Math.pow(10,cent)).toString();//求出整数位数值.
        cents = cents.toString();//把小数位转换成字符串,以便求小数位长度.
        while(cents.length<cent){//补足小数位到指定的位数.
            cents = "0" + cents;
        }
        if(isThousand == 0) //不需要千分位符.
            return (((sign)?'':'-') + num + '.' + cents);
        //对整数部分进行千分位格式化.
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+'’'+
                num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + num + '.' + cents);
    }

    /**
     * 千分位格式化
     * @param num
     * @returns {String}
     */
    function toThousands(num) {
        var num = (num || 0).toString(), result = '';
        while (num.length > 3) {
            result = ',' + num.slice(-3) + result;
            num = num.slice(0, num.length - 3);
        }
        if (num) { result = num + result; }
        return result;
    }

    //提示对话框
    function alert(title,content){
        layer.open({
            title: title,
            content: content
        });
    }

    //确认对话框
    function confirm(content,ok_function){
        layer.confirm(content, function(index){
            ok_function();
            layer.close(index);
        });
    }

    /**
     * 类似于getJSON、但基于POST请求的获取JSON数据的AJAX方法
     */
    function postJSON(url, data, callback){
        if ($.isFunction(data)) {
            callback = data;
            data = undefined;
        }
        return $.ajax({
            url : url,
            type : "POST",
            data : data,
            success : callback,
            dataType : "json",
            error:function(jqXHR, textStatus, errorMsg){
            }
        });
    }

    function getJSON(url, data, callback){
        if ($.isFunction(data)) {
            callback = data;
            data = undefined;
        }
        return $.ajax({
            url : url,
            type : "GET",
            data : data,
            success : callback,
            dataType : "json",
            error:function(jqXHR, textStatus, errorMsg){
            }
        });
    }

    /** 多语言*/
    function message(code) {
        var messages = {};
        if("undefined" != typeof g_messages){
            messages = g_messages;
        }
        if (code != null) {
            var content = messages[code] != null ? messages[code] : code;
            if (arguments.length == 1) {
                return content;
            } else {
                if ($.isArray(arguments[1])) {
                    $.each(arguments[1], function(i, n) {
                        content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
                    });
                    return content;
                } else {
                    $.each(Array.prototype.slice.apply(arguments).slice(1), function(i, n) {
                        content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
                    });
                    return content;
                }
            }
        }
    }

    /** 文件浏览拓展组件*/
    var fileBrowerFunctionObject = {
        browser: function(options) {
            var settings = {
                type: "image",
                title: "图片浏览",
                isUpload: true,
                browserUrl:  g_base_path + "/admin/file/browser.do",
                uploadUrl:  g_base_path + "/admin/file/upload.do",
                callback: null,
                allowImgFileSize:1024*1024 //KB
            };
            var global_setting = {};

            if("undefined" != typeof g_setting){
                global_setting = g_setting;
            }else{
                tips_error("g_setting 全局设置变量不存在");
            }

            $.extend(settings, options);

            var token = Cookies.get("token");
            var cache = {};
            return this.each(function() {
                var browserFrameId = "browserFrame" + (new Date()).valueOf() + Math.floor(Math.random() * 1000000);
                var fileInputId = "file"+ (new Date()).valueOf() + Math.floor(Math.random() * 1000000);
                var $browserButton = $(this);
                $browserButton.click(function() {
                    var $browser = $('<div class="browserBox"><\/div>');
                    var $browserBar = $('<div class="browserBar"><\/div>').appendTo($browser);
                    var $browserFrame,$browserForm,$browserUploadButton,$browserUploadInput,$browserParentButton,$browserOrderType,$browserLoadingIcon,$browserList;
                    if (settings.isUpload) {
                        $browserFrame = $('<iframe id="' + browserFrameId + '" name="' + browserFrameId + '" style="display: none;"><\/iframe>').appendTo($browserBar);
                        $browserForm = $('<form action="' + settings.uploadUrl + '" method="post" encType="multipart/form-data" target="' + browserFrameId + '" style="display:inline;"><input type="hidden" name="token" value="' + token + '" \/><input type="hidden" name="fileType" value="' + settings.type + '" \/><\/form>').appendTo($browserBar);
                        $browserUploadButton = $('<label href="javascript:;" class="browserUploadButton btn btn-default btn-sm" for="'+fileInputId+'">上传<\/label>').appendTo($browserForm);
                        $browserUploadInput = $('<input type="file" name="file" id="'+fileInputId+'" style="position:absolute;left:-99999px"\/>').appendTo($browserForm);
                    }
                    $browserParentButton = $('<a href="javascript:;" class="btn btn-default btn-sm browserParentButton">上级目录<\/a>').appendTo($browserBar);
                    //$browserBar.append("&nbsp;&nbsp;&nbsp;排序: ");
                    $browserOrderType = $('<span class="form-inline hidden"><select name="orderType" class="browserOrderType form-control input-sm"><option value="name">名称<\/option><option value="size">大小<\/option><option value="type">类型<\/option><\/select></span>').appendTo($browserBar);
                    $browserLoadingIcon = $('<span class="loadingIcon" style="display: none;"><i class="fa-li fa fa-spinner fa-spin"></i><\/span>').appendTo($browserBar);
                    $browserList = $('<div class="browserList"><\/div>').appendTo($browser);


                    $browserOrderType = $(".browserOrderType");

                    var $dialog = layer.open({
                        area: '60%',
                        title: settings.title,
                        content: $browser.html()
                    });

                    browserList("/");

                    function browserList(path) {
                        var key = settings.type + "_" + path + "_" + $browserOrderType.val();
                        if (cache[key] == null) {
                            $.ajax({
                                url: settings.browserUrl,
                                type: "GET",
                                data: {fileType: settings.type, orderType: $browserOrderType.val(), path: path},
                                dataType: "json",
                                cache: false,
                                beforeSend: function() {
                                    $browserLoadingIcon.show();
                                },
                                success: function(data) {
                                    createBrowserList(path, data);
                                    cache[key] = data;
                                },
                                complete: function() {
                                    $browserLoadingIcon.hide();
                                }
                            });
                        } else {
                            createBrowserList(path, cache[key]);
                        }
                    }

                    function createBrowserList(path, data) {
                        var browserListHtml = "";
                        $.each(data, function(i, fileInfo) {
                            var iconUrl;
                            var title;
                            if (fileInfo.isDirectory) {
                                iconUrl = g_base_path + "/assets/img/folder.png";
                                title = fileInfo.name;
                            } else if (new RegExp("^\\S.*\\.(jpg|jpeg|bmp|gif|png)$", "i").test(fileInfo.name)) {
                                iconUrl = fileInfo.url;
                                title = fileInfo.name + " (" + Math.ceil(fileInfo.size / 1024) + "KB, " + new Date(fileInfo.lastModified).toLocaleString() + ")";
                            } else {
                                iconUrl = g_base_path + "/resources/admin/images/file_icon.gif";
                                title = fileInfo.name + " (" + Math.ceil(fileInfo.size / 1024) + "KB, " + new Date(fileInfo.lastModified).toLocaleString() + ")";
                            }
                            browserListHtml += '<div class="browserItem"><img src="' + iconUrl + '" title="' + title + '" url="' + fileInfo.url + '" isDirectory="' + fileInfo.isDirectory + '" \/><div>' + fileInfo.name + '<\/div><\/div>';
                        });


                        $(".browserList").html(browserListHtml+"<div class='clearfix'></div>");

                        $(".browserList").find("img").bind("click", function() {
                            var $this = $(this);
                            var isDirectory = $this.attr("isDirectory");
                            if (isDirectory == "true") {
                                var name = $this.next().text();
                                browserList(path + name + "/");
                            } else {
                                var url = $this.attr("url");
                                if (settings.input != null) {
                                    settings.input.val(url);
                                } else {
                                    $browserButton.parent().find('input[type="text"]').val(url);
                                    $browserButton.parent("div").find("#imgPreView").attr("href",url);
                                    $browserButton.parent("div").find("#imgPreView img").attr("src",url);
                                    $browserButton.parent("div").find(".imgPreView").attr("href",url);
                                    $browserButton.parent("div").find(".imgPreView img").attr("src",url);
                                }
                                if (settings.callback != null && typeof settings.callback == "function") {
                                    settings.callback(url);
                                }
                                layer.close($dialog);
                            }
                        });

                        $browserParentButton = $(".browserParentButton");
                        if (path == "/") {
                            $browserParentButton.unbind("click");
                        } else {
                            var parentPath = path.substr(0, path.replace(/\/$/, "").lastIndexOf("/") + 1);
                            $browserParentButton.unbind("click").bind("click", function() {
                                browserList(parentPath);
                            });
                        }
                        $browserOrderType.unbind("change").bind("change", function() {
                            alert("aaa")
                            browserList(path);
                        });
                    }

                    $(".browserBar input[name='file']").change(function() {

                        $this = $(this);

                        var allowedUploadExtensions;
                        if (settings.type == "flash") {
                            allowedUploadExtensions = "swf,flv";
                        } else if (settings.type == "media") {
                            allowedUploadExtensions = "swf,flv,mp3,wav,avi,rm,rmvb";
                        } else if (settings.type == "file") {
                            allowedUploadExtensions = "zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx";
                        } else {
                            allowedUploadExtensions = "jpg,jpeg,bmp,gif,png";
                        }
                        if ($.trim(allowedUploadExtensions) == "" || !new RegExp("^\\S.*\\.(" + allowedUploadExtensions.replace(/,/g, "|") + ")$", "i").test($this.val().toLowerCase())) {
                            layer.msg("文件类型错误")
                            return false;
                        }

                        //图片大小验证
                        var ele = $(this).get(0);
                        var byteSize  = (ele.files[0].size / 1024).toFixed(2);
                        if(byteSize > settings.allowImgFileSize){
                            layer.msg("图片大小超过限制，最大"+settings.allowImgFileSize+"KB")
                            return false;
                        }

                        $browserLoadingIcon.show();

                        //异步提交表单
                        $this.parent("form").ajaxSubmit(function(text) {
                            if ($.trim(text) != "") {
                                var data = $.parseJSON(text);
                                if (data.message.type == "success") {
                                    if (settings.input != null) {
                                        settings.input.val(data.url);
                                    } else {
                                        $browserButton.parent().find('input[type="text"]').val(data.url);
                                        $browserButton.parent("div").find("#imgPreView").attr("href",data.url);
                                        $browserButton.parent("div").find("#imgPreView img").attr("src",data.url);
                                        $browserButton.parent("div").find(".imgPreView").attr("href",data.url);
                                        $browserButton.parent("div").find(".imgPreView img").attr("src",data.url);
                                    }
                                    if (settings.callback != null && typeof settings.callback == "function") {
                                        settings.callback(data.url);
                                    }
                                    cache = {};
                                } else {
                                    showTipsMessage(data.message);
                                }
                                $browserLoadingIcon.hide();
                                layer.close($dialog);
                            }
                        });

                    });





                });

            });
        }
    }

    /**
     * 列表页面通用操作
     */
    function listPageTemplateFunction(){

        var $listForm = $("#listForm");
        var $filterSelect = $("#listForm .filterSelect");
        var $pageTotal = $("#pageTotal");
        var $deleteButton = $("#deleteButton");
        var $pageSizeSelect = $("#pageSizeSelect");
        var $pageSizeOption = $("#pageSizeOption a");
        var $moreOperation = $("#moreOperation");
        var $searchPropertySelect = $("#searchPropertySelect #searchPropertyOption");
        var $searchPropertyOption = $("#searchPropertyOption option");
        var $searchValue = $("#searchValue");
        var $listTable = $("#listTable");
        var $selectAll = $("#selectAll");
        var $ids = $("#listTable input[name='ids']");
        var $contentRow = $("#listTable tr:gt(0)");
        var $sort = $("#listTable .sort");
        var $pageSize = $("#pageSize");
        var $searchProperty = $("#searchProperty");
        var $orderProperty = $("#orderProperty");
        var $orderDirection = $("#orderDirection");
        var $pageNumber = $("#pageNumber");
        var $delete = $("#listTable a.delete");
        var $exportExcelButton = $("#exportExcelButton");//Excel导出按钮


        //过滤select变动后自动提交表单
        $filterSelect.change(function(){
            $listForm.submit();
        })

        // 删除
        $delete.click(function() {
            var $this = $(this);

            confirm("是否确定删除",function(){
                $.ajax({
                    url: "delete.do",
                    type: "POST",
                    data: {id: $this.attr("val")},
                    dataType: "json",
                    cache: false,
                    success: function(message) {
                        showTipsMessage(message);
                        if (message.type == "success") {
                            $this.parents("tr").remove();
                        }
                    }
                });
            });

        });

        // 删除
        $deleteButton.click( function() {

            var $this = $(this);
            if ($this.hasClass("disabled")) {
                return false;
            }
            var $checkedIds = $listTable.find("input[name='ids']:enabled:checked");

            confirm("是否确定删除",function(){
                $.ajax({
                    url: "delete.do",
                    type: "POST",
                    data: $checkedIds.serialize(),
                    dataType: "json",
                    cache: false,
                    success: function(message) {
                        showTipsMessage(message);
                        if (message.type == "success") {
                            $pageTotal.text(parseInt($pageTotal.text()) - $checkedIds.size());
                            $checkedIds.closest("tr").remove();
                            if ($listTable.find("tr").size() <= 1) {
                                setTimeout(function() {
                                    location.reload(true);
                                }, 3000);
                            }
                        }
                        $deleteButton.addClass("disabled");
                        $selectAll.prop("checked", false);
                        $checkedIds.prop("checked", false);
                    }
                });
            });


        });

        // 全选
        $selectAll.click( function() {
            var $this = $(this);
            var $enabledIds = $listTable.find("input[name='ids']:enabled");
            if ($this.prop("checked")) {
                $enabledIds.prop("checked", true);
                if ($enabledIds.filter(":checked").size() > 0) {
                    $deleteButton.removeClass("disabled");
                    $contentRow.addClass("selected");
                } else {
                    $deleteButton.addClass("disabled");
                }
            } else {
                $enabledIds.prop("checked", false);
                $deleteButton.addClass("disabled");
                $contentRow.removeClass("selected");
            }
        });

        // 选择
        $ids.click( function() {
            var $this = $(this);
            if ($this.prop("checked")) {
                $this.closest("tr").addClass("selected");
                $deleteButton.removeClass("disabled");
            } else {
                $this.closest("tr").removeClass("selected");
                if ($listTable.find("input[name='ids']:enabled:checked").size() > 0) {
                    $deleteButton.removeClass("disabled");
                } else {
                    $deleteButton.addClass("disabled");
                }
            }
        });

        // 排序
        $sort.click( function() {
            var orderProperty = $(this).attr("name");
            if ($orderProperty.val() == orderProperty) {
                if ($orderDirection.val() == "asc") {
                    $orderDirection.val("desc")
                } else {
                    $orderDirection.val("asc");
                }
            } else {
                $orderProperty.val(orderProperty);
                $orderDirection.val("asc");
            }
            $pageNumber.val("1");
            $listForm.submit();
            return false;
        });

        // 排序图标
        if ($orderProperty.val() != "") {
            $sort = $listTable.find(".sort[name='" + $orderProperty.val() + "']");
            if ($orderDirection.val() == "asc") {
                $sort.removeClass("desc").addClass("asc");
                $sort.find("i").removeClass("fa-caret-down").addClass("fa-caret-up");
            } else {
                $sort.removeClass("asc").addClass("desc");
                $sort.find("i").removeClass("fa-caret-up").addClass("fa-caret-down");
            }
        }

        // 列表查询
        if (location.search != "") {
            Cookies.set("listQuery", location.search);
        } else {
            Cookies.remove("listQuery");
        }

        // 每页记录数
        $pageSizeOption.click( function() {
            var $this = $(this);
            $pageSize.val($this.attr("val"));
            $pageNumber.val("1");
            $listForm.submit();
            return false;
        });

        // 页码跳转
        $.pageSkip = function(pageNumber) {
            $pageNumber.val(pageNumber);
            $listForm.submit();
            return false;
        }

        // 表单提交
        $listForm.submit(function() {
            $searchProperty.val($searchPropertySelect.val());

            if (!/^\d*[1-9]\d*$/.test($pageNumber.val())) {
                $pageNumber.val("1");
            }

            if ($searchValue.size() > 0 && $searchValue.val() != "" && $searchProperty.val() == "") {
                alert($searchPropertyOption.eq(0).attr("val") + " &&")
                $searchProperty.val($searchPropertyOption.eq(0).attr("val"));
            }
        });

        //表格导出Excel
        $exportExcelButton.click(function(){

            var listTable = $("#listTable").clone();
            listTable.find(".noExl").remove();
            listTable.find("th a").removeAttr("href");

            var myDate = new Date();
            var year = myDate.getFullYear();
            var month = myDate.getMonth()+1;
            var date = myDate.getDate();

            var fileExcelName = $("#listTable").attr("data-excel-name");
            listTable.table2excel({
                exclude: ".noExl",
                filename: fileExcelName+"."+year+"-"+month+"-"+date
            });

        });

    }


    /**
     * 显示消息  ，
     * 格式  message = {
				'type':'消息类型',
				'content':'消息内容'
		   };
     */
    function showTipsMessage(message){
        if(null != message && undefined != message){
            var type = message.type;
            var content = message.content;
            if(null != content && content.length>0){

                if('warn' == type){
                    type = 'warning';
                }
                if('error' == type){
                    type = 'warning';
                }
                $.notify({
                    message: message.content
                },{
                    type: type
                });

            }
        }
    }

    function getString(s,n){
        s =  delHtmlTag(s);  //html替换
        if(s.length > n){
            return s.substring(0,n);
        }
        return s;
    }

    function delHtmlTag(str)
    {
        return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
    }



    return {
        "loadCss":loadCss,
        "jsDateDiff":jsDateDiff,
        "formatNumber":formatNumber,
        "toThousands":toThousands,

        /** 提示信息*/
        'alert':alert,
        'confirm':confirm,
        'showTipsMessage':showTipsMessage,

        /** Json操作工具*/
        "postJSON":postJSON,
        "getJSON":getJSON,
        "message":message,

        /** 文件浏览组件*/
        "fileBrowerFunctionObject":fileBrowerFunctionObject,

        /** 列表类型页面通用操作*/
        "listPageTemplateFunction":listPageTemplateFunction,

        'getString':getString,
        'delHtmlTag':delHtmlTag,

        'layer':layer
    };

});

