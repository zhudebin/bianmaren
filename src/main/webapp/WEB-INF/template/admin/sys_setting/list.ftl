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
					设置列表 <small>共${page.total}调记录</small>
                    <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                    <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
				</h1>
			</div>
			<div class="col-sm-5 text-right hidden-xs">
				<ol class="breadcrumb push-10-t">
					<li>网站管理</li>
					<li><a class="link-effect">设置列表</a></li>
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
					<span class="form-inline">
						<select class="form-control input-sm filterSelect"  name="settingType" id="settingType" >
							[#list settingTypeList as type]
                                <option value="${type}" [#if type==settingType]selected[/#if] >
									[#if 'setting'== type]系统设置[/#if]
									[#if 'log'== type]日志设置</span>[/#if]
									[#if 'templ'== type]模板设置[/#if]
								</option>
							[/#list]
                        </select>
					</span>
				</div>
				<div class="pull-right">
					<div class="form-inline">
						<div class="input-group input-group-sm">
							<div class="input-group-btn" id="searchPropertySelect">
								<select class="form-control input-sm" style="margin-right: -1px;" id="searchPropertyOption">
									<option [#if page.searchProperty == "name"]selected[/#if] value="name">名称</option>
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
						<th class="sort" name="settingType">类型 <i class="fa"></i></th>
						[#if 'setting'== settingType]
                            <th class="sort" name="name">名称 <i class="fa"></i></th>
                            <th class="sort" name="value">值 <i class="fa"></i></th>
						[/#if]
						[#if 'log'== settingType]
                            <th class="sort" name="name">操作 <i class="fa"></i></th>
                            <th class="sort" name="value">匹配模式 <i class="fa"></i></th>
						[/#if]
						[#if 'templ'== settingType]
                            <th class="sort" name="name">页面 <i class="fa"></i></th>
                            <th class="sort" name="value">路径 <i class="fa"></i></th>
						[/#if]
						<th class="sort" name="remark">备注 <i class="fa"></i></th>
						<th class="sort" name="is_system">系统内置 <i class="fa"></i></th>
						<th class="sort" name="createDate">创建日期 <i class="fa"></i></th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>
						[#list page.content as data]
							<tr>
								<td>
									[#if 'setting'== data.settingType]<span class="label label-success">系统设置</span>[/#if]
									[#if 'log'== data.settingType]<span class="label label-primary">日志设置</span>[/#if]
									[#if 'templ'== data.settingType]<span class="label label-info">模板设置</span>[/#if]
								</td>
								<td>
								${data.name}
								</td>
								<td>
								${data.value}
								</td>
								<td>
								${data.remark}
								</td>
								<td>
									[#if data.is_system]
                                        <i class="si si-check text-success"></i>
									[#else]
                                        <i class="si si-close text-danger"></i>
									[/#if]
								</td>
								<td>
									<span data-toggle="tooltip" title="" data-original-title="${data.createDate?string("yyyy-MM-dd HH:mm:ss")}">${data.createDate}</span>
								</td>
								<td>
									<div class="btn-group">
										<a href="edit.html?id=${data.id}" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" title="" data-original-title="编辑"><i class="fa fa-pencil"></i></a>
										[#if !data.is_system]
											<a href="delete.html?id=${data.id}" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" title="" data-original-title="删除"><i class="fa fa-times"></i></a>
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


