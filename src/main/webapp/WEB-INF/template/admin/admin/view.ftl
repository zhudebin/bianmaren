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
                管理员详情
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>网站管理</li>
                <li><a class="link-effect">管理员详情</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->

<div class="block">
    <div class="block-content table-responsive form-horizontal">
        <div class="form-group">
            <div class="col-xs-3">
                <div class="form-material">
                    <input class="form-control" type="text" id="username" name="username" placeholder="登入名" value="${admin.username}" disabled="disabled">
                    <label for="username">登入名</label>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-material">
                    <input class="form-control" type="text" id="headPortrait" name="headPortrait" placeholder="头像" value="${admin.headPortrait}" disabled="disabled">
                    <label for="headPortrait">头像</label>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-material">
                    <input class="form-control" type="text" id="name" name="name" placeholder="真实姓名" value="${admin.name}" disabled="disabled">
                    <label for="name">真实姓名</label>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-material">
                    <input class="form-control" type="text" id="createDate" name="createDate" placeholder="创建时间" value="${admin.createDate?string("yyyy-MM-dd HH:mm:ss")}"disabled="disabled">
                    <label for="createDate">创建时间</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12 col-md-4">
                <div class="form-material">
                    <input class="form-control" type="text" id="weiXinScanCode" name="weiXinScanCode" placeholder="微信扫码" value="${admin.weiXinScanCode!'暂无'}"disabled="disabled">
                    <label for="weiXinScanCode">微信扫码</label>
                </div>
            </div>
            <div class="col-xs-12 col-md-4">
                <div class="form-material">
                    <input class="form-control" type="text" id="zhiFuBaoScanCode" name="zhiFuBaoScanCode" placeholder="支付宝扫码" value="${admin.zhiFuBaoScanCode!'暂无'}"disabled="disabled">
                    <label for="zhiFuBaoScanCode">支付宝扫码</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <div class="form-material">
                    <textarea class="form-control" id="introduction" name="introduction" rows="4" disabled="disabled" >${admin.introduction}</textarea>
                    <label for="introduction">介绍自己</label>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
	</div><!-- /.block-content -->
</div><!-- /.block -->


<!-- Page JS Code -->
[#include "/admin/include/js/common.ftl" /]
<script data-main="${base}/assets/js/pages/admin/list.page.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>


