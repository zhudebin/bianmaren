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
                文章列表 <small>${message("admin.page.total", page.total)}</small>
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>内容管理</li>
                <li><a class="link-effect">文章列表</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->

<div class="block">
    <form id="listForm" action="list.html" method="post">
        <div class="block-header">
            <div class="pull-left">
                <a href="add.html" class="btn btn-sm btn-success">
                    <i class="fa fa-plus"></i> ${message("admin.common.add")}
                </a>
                <a href="javascript:;" id="deleteButton" class="btn btn-sm btn-danger disabled">
                    <i class="fa fa-trash"></i> ${message("admin.common.delete")}
                </a>
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
                                <option [#if page.searchProperty == "title"]selected[/#if] value="title">标题</option>
                            </select>
                        </div>
                        <input type="text" class="form-control" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
							<span class="input-group-btn">
								<button class="btn btn-success" type="submit"><i class="fa fa-search"></i></button>
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
                    <th class="remove-padding-t remove-padding-b">
                        <label class="css-input css-checkbox css-checkbox-primary">
                            <input type="checkbox" id="selectAll"><span></span>
                        </label>
                    </th>
                    <th class="sort" name="title">标题 <i class="fa"></i></th>
                    <th class="sort" name="articleCategory">文章分类 <i class="fa"></i></th>
                    <th class="sort" name="admin">作者 <i class="fa"></i></th>
                    <th class="sort" name="isPublication">是否发布 <i class="fa"></i></th>
                    <th class="sort" name="isTop">是否置顶 <i class="fa"></i></th>
                    <th class="sort" name="createDate">创建日期 <i class="fa"></i></th>
                    <th><span>${message("admin.common.handle")}</span></th>
                </tr>
                </thead>
                <tbody>
				[#list page.content as article]
					<tr>
						<td>
                            <label class="css-input css-checkbox css-checkbox-primary remove-padding remove-margin">
							    <input type="checkbox" name="ids" value="${article.id}" /><span></span>
                            </label>
						</td>
						<td>
							<span title="${article.title}">${abbreviate(article.title, 50, "...")}</span>
						</td>
						<td>${article.articleCategory.name}</td>
						<td>${article.admin.username}</td>
						<td>
                            [#if article.isPublication]
                                <i class="si si-check text-success"></i>
                            [#else]
                                <i class="si si-close text-danger"></i>
                            [/#if]
						</td>
						<td>
                            [#if article.isTop]
                                <i class="si si-check text-success"></i>
                            [#else]
                                <i class="si si-close text-danger"></i>
                            [/#if]
						</td>
						<td>
							<span title="${article.createDate?string("yyyy-MM-dd HH:mm:ss")}">${article.createDate}</span>
						</td>
						<td>
                            <div class="btn-group">
                                <a href="edit.html?id=${article.id}" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" data-original-title="编辑"><i class="fa fa-pencil"></i></a>
                                [#if article.isPublication]
                                    <a href="${base}${article.path}" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" data-original-title="查看" target="_blank"><i class="fa fa-eye"></i></a>
                                [/#if]
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


