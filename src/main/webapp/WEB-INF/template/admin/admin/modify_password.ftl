<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>修改密码</title>

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
                修改密码
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>网站管理</li>
                <li><a class="link-effect">修改密码</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->

<div class="block">
    <div class="block-content table-responsive">
        <form id="inputForm" class="form-horizontal" action="update_password.html" method="post">
            <input type="hidden" name="id" value="${admin.id}" />
            <div class="form-group">
                <div class="col-xs-12">
                    <div class="form-material col-md-4" >
                        <input class="form-control js-validation-password beforepassword" type="password" id="beforepassword" name="beforepassword" required  minlength="5">
                        <label for="beforepassword">旧密码</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12">
                    <div class="form-material col-md-4">
                        <input class="form-control js-validation-password newPassword" type="password" id="newPassword" name="newPassword" required minlength="5">
                        <label for="newPassword">新密码</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 ">
                    <div class="form-material col-md-4">
                        <input class="form-control js-validation-password ConfirmPassword" type="password" id="ConfirmPassword" name="ConfirmPassword" required minlength="5">
                        <label for="ConfirmPassword">确认密码</label>
                    </div>
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
<script data-main="${base}/assets/js/pages/admin/admin/admin.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>


