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
                日志详情
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>网站管理</li>
                <li><a class="link-effect">日志详情</a></li>
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
                    <input class="form-control" type="text" id="operation" name="operation" placeholder="操作" value="${log.operation}" disabled="disabled">
                    <label for="operation">操作</label>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-material">
                    <input class="form-control" type="text" id="operator" name="operator" placeholder="操作员" value="${log.operator}" disabled="disabled">
                    <label for="operator">操作员</label>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-material">
                    <input class="form-control" type="text" id="ip" name="ip" placeholder="IP" value="${log.ip}" disabled="disabled">
                    <label for="ip">IP</label>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="form-material">
                    <input class="form-control" type="text" id="createDate" name="createDate" placeholder="创建时间" value="${log.createDate?string("yyyy-MM-dd HH:mm:ss")}"disabled="disabled">
                    <label for="createDate">创建时间</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <div class="form-material">
                    <input class="form-control" type="text" id="content" name="content" placeholder="内容" value="${log.content!'暂无'}"disabled="disabled">
                    <label for="content">内容</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-12">
                <div class="form-material">
                    <textarea class="form-control" id="parameter" name="parameter" rows="9" disabled="disabled" >${log.parameter?html}</textarea>
                    <label for="parameter">请求参数</label>
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


