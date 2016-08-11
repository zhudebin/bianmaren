<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>添加设置</title>

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
                编辑设置
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>网站管理</li>
                <li><a class="link-effect">编辑设置</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->

<div class="block">
    <div class="block-content table-responsive">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h3 class="font-w300 push-15">注意</h3>
            <p>
            <ul class="list-unstyled">
                <li><b>模板设置 : </b>设置名称填写页面名称，设置值填写页面模板的路径，备注填写静态化路径</li>
                <li><b>日志设置 : </b>设置名称填写操作名称，设置值填写改操作的匹配路径</li>
            </ul>
            </p>
        </div>
        <br/>
        <form id="inputForm" class="form-horizontal" action="save.html" method="post">
            <div class="form-group">
                <div class="col-xs-12 col-md-4 ">
                    <div class="form-material">
                        <select class="form-control" id="settingType" name="settingType" required>
                        [#list settingTypeList as type]
                            <option value="${type}" [#if sysSetting.settingType == type]selected[/#if]>
                                [#if 'setting'== type]系统设置[/#if]
                                [#if 'log'== type]日志设置[/#if]
                                [#if 'templ'== type]模板设置[/#if]
                            </option>
                        [/#list]
                        </select>
                        <label for="username">类型</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material floating">
                        [#if sysSetting.is_system]
                            <input class="form-control" type="hidden" id="name" name="name" value="${sysSetting.name}" required>
                            <input class="form-control" type="text" id="name" name="name" value="${sysSetting.name}" required disabled>
                        [#else ]
                            <input class="form-control" type="text" id="name" name="name" value="${sysSetting.name}" required>
                        [/#if]

                        <label for="name">设置名称</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material floating">
                        <input class="form-control" type="text" id="value" name="value" value="${sysSetting.value}">
                        <label for="value">设置值</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-md-12">
                    <div class="form-material floating">
                        <textarea class="form-control" id="remark" name="remark" rows="5">${sysSetting.remark}</textarea>
                        <label for="remark">备注</label>
                    </div>
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


