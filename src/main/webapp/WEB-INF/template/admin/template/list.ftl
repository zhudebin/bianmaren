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
                模板列表 <small>共${page.total}调记录</small>
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>网站管理</li>
                <li><a class="link-effect">模板列表</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->

<div class="block">
    <form id="listForm" action="list.html" method="post">
        <div class="block-header">
            <div class="pull-left">
					<span class="dropdown">
						  <button class="btn btn-sm btn-default dropdown-toggle" type="button" id="pageSizeSelect" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                          ${message("admin.page.pageSize")}
                              <span class="caret"></span>
                          </button>
						  <ul class="dropdown-menu" aria-labelledby="pageSizeSelect" id="pageSizeOption">
                              <li [#if page.pageSize == 10] class="active"[/#if] >
                                  <a href="javascript:;" val="10">10</a>
                              </li>
                              <li [#if page.pageSize == 20] class="active"[/#if] >
                                  <a href="javascript:;" val="20">20</a>
                              </li>
                              <li [#if page.pageSize == 50]class="active"[/#if] >
                                  <a href="javascript:;" val="50">50</a>
                              </li>
                              <li [#if page.pageSize == 100] class="active"[/#if]>
                                  <a href="javascript:;"  val="100">100</a>
                              </li>
                          </ul>
					</span>
            </div>
            <div class="pull-right">
                <div class="form-inline">
                    <div class="input-group input-group-sm">
                        <div class="input-group-btn" id="searchPropertySelect">
                            <select class="form-control input-sm" style="margin-right: -1px;" id="searchPropertyOption">
                                <option [#if page.searchProperty == "name"]selected[/#if] value="name">模板名称</option>
                            </select>
                        </div>
                        <input type="text" class="form-control" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit"><i class="fa fa-search"></i> 搜索</button>
							</span>
                    </div><!-- /input-group -->
                </div><!-- /.form-inline -->
            </div>
            <div class="clearfix"></div>
        </div><!-- /.block-header -->
        <div class="block-content table-responsive remove-padding-t">
            <table class="table table-bordered" id="listTable">
                <thead>
                <tr>
                    <th class="sort" name="name">模板名称 <i class="fa"></i></th>
                    <th class="sort" name="value">模板路径 <i class="fa"></i></th>
                    <th class="sort" name="createDate">创建日期 <i class="fa"></i></th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
				[#list page.content as template]
					<tr>
						<td>
							${template.name}
						</td>
						<td>
							${template.value}
						</td>
						<td>
							<span data-toggle="tooltip" data-original-title="${template.createDate?string("yyyy-MM-dd HH:mm:ss")}">${template.createDate}</span>
						</td>
						<td>
							<div class="btn-group">
								<a href="edit.html?id=${template.id}" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" data-original-title="编辑"><i class="fa fa-pencil"></i></a>
							</div>
						</td>
					</tr>
				[/#list]
                </tbody>
            </table>
            <div class="text-center">[@pagesTemaplte page /]</div>
        </div><!-- /.block-content -->
    </form>
</div><!-- /.block -->


<!-- Page JS Code -->
[#include "/admin/include/js/common.ftl" /]
<script data-main="${base}/assets/js/pages/admin/list.page.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>


