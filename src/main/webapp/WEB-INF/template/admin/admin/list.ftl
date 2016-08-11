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
					管理员列表 <small>${message("admin.page.total", page.total)}</small>
                    <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                    <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
				</h1>
			</div>
			<div class="col-sm-5 text-right hidden-xs">
				<ol class="breadcrumb push-10-t">
					<li>网站管理</li>
					<li><a class="link-effect">管理员列表</a></li>
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
									<option [#if page.searchProperty == "username"]selected[/#if] value="username">${message("Admin.username")}</option>
									<option [#if page.searchProperty == "email"]selected[/#if] value="email">${message("Admin.email")}</option>
									<option [#if page.searchProperty == "name"]selected[/#if] value="name">${message("Admin.name")}</option>
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
						<th class="sort" name="username">昵称 <i class="fa"></i></th>
						<th class="sort" name="mobile">手机号 <i class="fa"></i></th>
						<th class="sort" name="email">邮箱 <i class="fa"></i></th>
						<th class="sort" name="name">真实姓名 <i class="fa"></i></th>
						<th class="sort" name="department">部门 <i class="fa"></i></th>
						<th class="sort" name="loginDate">上次登录日期 <i class="fa"></i></th>
						<th class="sort" name="loginIp">登录IP <i class="fa"></i></th>
						<th>状态</th>
						<th class="sort" name="createDate">创建日期 <i class="fa"></i></th>
						<th><span>${message("admin.common.handle")}</span></th>
					</tr>
					</thead>
					<tbody>
						[#list page.content as admin]
							<tr>
								<td>
                                    <label class="css-input css-checkbox css-checkbox-primary remove-padding remove-margin">
										<input type="checkbox" name="ids" value="${admin.id}" /><span></span>
                                    </label>
								</td>
								<td>
								${admin.username}
								</td>
								<td>
								${admin.mobile}
								</td>
								<td>
								${admin.email!'-'}
								</td>
								<td>
								${admin.name!'-'}
								</td>
								<td>
								${admin.department!'-'}
								</td>
								<td>
									[#if admin.loginDate??]
                                    <span title="${admin.loginDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.loginDate}</span>
								[#else]
                                    -
								[/#if]
								</td>
								<td>
								${(admin.loginIp)!"-"}
								</td>
								<td>
									[#if !admin.isEnabled]
										<span class="red">${message("admin.admin.disabled")}</span>
									[#elseif admin.isLocked]
										<span class="red"> ${message("admin.admin.locked")} </span>
									[#else]
										<span class="green">${message("admin.admin.normal")}</span>
									[/#if]
								</td>
								<td>
									<span title="${admin.createDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.createDate}</span>
								</td>
								<td>
									<div class="btn-group">
										<a href="edit.html?id=${admin.id}" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" title="" data-original-title="编辑"><i class="fa fa-pencil"></i></a>
									</div>
                                    <div class="btn-group">
                                        <a href="view.html?id=${admin.id}" class="btn btn-xs btn-default" type="button" data-toggle="tooltip" title="" data-original-title="详情"><i class="fa fa-eye"></i></a>
                                    </div>
                                    <div class="btn-group">
                                        <a href="modify_password.html?id=${admin.id}"> <button class="btn btn-xs btn-default" type="button"><i class="fa fa-pencil"></i>修改密码</button></a>
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


