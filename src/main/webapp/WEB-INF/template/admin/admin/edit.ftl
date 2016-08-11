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
                编辑管理员
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>网站管理</li>
                <li><a class="link-effect">编辑管理员</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->

<div class="block">
    <div class="block-content table-responsive">
        <form id="inputForm" class="form-horizontal" action="update.html" method="post">
            <input type="hidden" name="id" value="${admin.id}" />
            <div class="form-group">
                <div class="col-xs-12 col-md-4 ">
                    <div class="form-material floating">
                        <label for="username">用户名</label>
                        <input class="form-control" type="text" id="username" name="username" value="${admin.username}" required>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material floating">
                        <input class="form-control" type="text" id="mobile" name="mobile" value="${admin.mobile}"maxlength="20" >
                        <label for="mobile">手机</label>
                    </div>
                </div>

            </div>
            <div class="form-group">

                <div class="col-xs-12 col-md-4">
                    <div class="form-material floating">
                        <input class="form-control" type="text" id="email" name="email" value="${admin.email}" email="true">
                        <label for="email">邮箱</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material floating">
                        <input class="form-control" type="text" id="department" name="department" value="${admin.department}">
                        <label for="department">部门</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-md-4">
                    <div class="form-material input-group">
                        <input class="form-control" type="text" id="weiXinScanCode" name="weiXinScanCode" value="${admin.weiXinScanCode}">
                        <span class="input-group-addon browserButton">选择微信扫码图片</span>
                        <label for="weiXinScanCode">微信扫码</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material input-group">
                        <input class="form-control" type="text" id="zhiFuBaoScanCode" name="zhiFuBaoScanCode" value="${admin.zhiFuBaoScanCode}">
                        <span class="input-group-addon browserButton">选择支付宝扫码图片</span>
                        <label for="zhiFuBaoScanCode">支付宝扫码</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-md-4">
                    <div class="form-material floating">
                        <input class="form-control" type="text" id="name" name="name" value="${admin.name}">
                        <label for="name">姓名</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material input-group">
                        <input class="form-control" type="text" id="headPortrait" name="headPortrait" value="${admin.headPortrait}">
                        <span class="input-group-addon browserButton">选择头像</span>
                        <label for="headPortrait">头像</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-md-8">
                    <div class="form-material floating">
                        <textarea class="form-control" id="introduction" name="introduction" rows="4">${admin.introduction}</textarea>
                        <label for="name">介绍自己</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-12 col-md-8">
                    <label class="css-input switch switch-sm switch-success">
                        <input type="checkbox" id="isEnabled" name="isEnabled"  [#if admin.isEnabled] checked="checked"[/#if]><span></span> 是否启用
                    </label>
                [#list roles as role]
                    <label class="css-input css-radio css-radio-success push-10-r">
                        <input type="checkbox" name="roleIds" [#if admin.roles?seq_contains(role)] checked="checked"[/#if] value="${role.id}"><span></span> ${role.name}
                    </label>
                [/#list]
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-12 col-md-4">
                    <input type="submit" class="btn btn-success" id="submit-btn" value="${message("admin.common.submit")}" />
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


