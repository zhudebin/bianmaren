<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>列表</title>

    <meta name="author" content="bianmaren">
    <meta name="robots" content="noindex, nofollow">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Common CSS -->
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/oneui.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/nprogress-master/nprogress.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/layer-v2.1/skin/layer.css">

</head>
<body>
[#include "/admin/include/template.ftl" /]
<!-- Page Header -->
<div class="content-mini bg-gray-lighter">
    <div class="row items-push">
        <div class="col-sm-7">
            <h1 class="page-heading">
                添加角色
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>网站管理</li>
                <li><a class="link-effect">添加角色</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->
<div class="block">
    <div class="block-content table-responsive">
        <form id="inputForm" class="form-horizontal" action="save.html" method="post">
            <div class="form-group">
                <div class="col-xs-12 col-md-4 ">
                    <div class="form-material floating">
                        <input class="form-control" type="text" id="name" name="name" required>
                        <label for="name">${message("Role.name")}</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material floating">
                        <input class="form-control" type="text" id="description" name="mobile">
                        <label for="description">${message("Role.description")}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">

                <div class="col-xs-12 col-md-4">
                    <label class="css-input switch switch-sm switch-success">
                        是否启用     <input type="checkbox" id="isEnabled" name="isEnabled" checked="checked"><span></span>
					</label>
                </div>
                <div class="col-xs-12 col-md-4">
                    <label class="css-input switch switch-sm switch-success">
                        是否启用    <input type="checkbox" id="isEnabled" name="isEnabled" checked="checked"><span></span>
                    </label>
                </div>
                <div class="col-xs-12 col-md-4">
                    <label class="css-input switch switch-sm switch-success">
                        是否启用   <input type="checkbox" id="isEnabled" name="isEnabled" checked="checked"><span></span>
					</label>
                </div>
                <div class="col-xs-12 col-md-4">
                    <label class="css-input switch switch-sm switch-success">
                        是否启用    <input type="checkbox" id="isEnabled" name="isEnabled" checked="checked"><span></span>
                    </label>
                </div>
                <div class="col-xs-12 col-md-4">
                    <label class="css-input switch switch-sm switch-success">
                        是否启用    <input type="checkbox" id="isEnabled" name="isEnabled" checked="checked"><span></span>
                    </label>
                </div>
                <div class="col-xs-12 col-md-4">
                    <label class="css-input switch switch-sm switch-success">
                        是否启用    <input type="checkbox" id="isEnabled" name="isEnabled" checked="checked"><span></span>
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-md-4">
                    <input type="submit" class="btn btn-success" id="submit-btn" value="${message("admin.common.submit")}" />
                    <input type="button" class="btn btn-square" id="submit-btn" value="${message("admin.common.back")}" onclick="location.href='list.html'"/>
                </div>
            </div>
        </form>
    </div>
</div><!-- /.block -->
	 
	<!-- Page JS Code -->
	[#include "/admin/include/js/common.ftl" /]
	<script data-main="${base}/assets/js/pages/admin/list.page.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
	</body>
</html>
