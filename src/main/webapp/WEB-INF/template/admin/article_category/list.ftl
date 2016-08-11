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
                文章分类列表 <small>${message("admin.page.total", articleCategoryTree?size)}</small>
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>内容管理</li>
                <li><a class="link-effect">文章分类列表</a></li>
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
                <a href="javascript:;" id="deleteButton" class="btn btn-sm btn-danger disabled hidden">
                    <i class="fa fa-trash"></i> ${message("admin.common.delete")}
                </a>
					<span class="dropdown hidden">
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
            <div class="pull-right hidden">
                <div class="form-inline">
                    <div class="input-group input-group-sm">
                        <div class="input-group-btn" id="searchPropertySelect">
                            <select class="form-control input-sm" style="margin-right: -1px;" id="searchPropertyOption">
                                <option [#if page.searchProperty == "name"]selected[/#if] value="name">分类名称</option>
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
                    <th>分类名称</th>
                    <th>排序</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
				[#list articleCategoryTree as articleCategory]
					<tr>
						<td>
							<span style="margin-left: ${articleCategory.grade * 20}px;[#if articleCategory.grade == 0] color: #000000;[/#if]">
							${articleCategory.name}
							</span>
						</td>
						<td>
						${articleCategory.order}
						</td>
						<td>
                            <div class="btn-group">
                                <a href="${base}${articleCategory.path}" target="_blank" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" data-original-title="查看"><i class="fa fa-eye"></i></a>
                                <a href="edit.html?id=${articleCategory.id}" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" data-original-title="编辑"><i class="fa fa-pencil"></i></a>
                                <a href="javascript:;" class="delete btn btn-default btn-xs" val="${articleCategory.id}" data-toggle="tooltip" data-original-title="删除"><i class="fa fa-times"></i></a>
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
<!-- js加载 -->
[#include "/admin/include/js/common.ftl" /]
<script data-main="${base}/assets/js/pages/admin/list.page.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>