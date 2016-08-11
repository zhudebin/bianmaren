<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/oneui.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/nprogress-master/nprogress.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/layer-v2.1/skin/layer.css">
</head>
<body>
	[#include "/admin/include/template.ftl" /]
	<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
      	会员列表<small>${message("admin.page.total", page.total)}</small>
      	<a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
      	<a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${base}/admin/common/index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">会员列表</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
		<form id="listForm" action="${base}/admin/member/list.html" method="post">

			<!-- 列表页面通用操作 -->
    		<div id="pageListConsole">
    			<div class="pull-left">
    				<a href="add.html" class="btn btn-success">
						<i class="fa fa-plus"></i> ${message("admin.common.add")}
					</a>
					<!--
					<a href="javascript:;" id="deleteButton" class="btn btn-danger disabled">
						<i class="fa fa-trash"></i> ${message("admin.common.delete")}
					</a>
					-->
					<span class="dropdown">
						  <button class="btn btn-default dropdown-toggle" type="button" id="pageSizeSelect" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
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
    			</div><!-- /.pull-left -->
    			<div class="pull-right">
    				<div class="form-inline">
						<div class="input-group">
							<div class="input-group-btn" id="searchPropertySelect">
								<select class="form-control" style="margin-right:-1px;" id="searchPropertyOption">
									 <option [#if page.searchProperty == "mobile"]selected[/#if] value="mobile">手机</option>
									 <option [#if page.searchProperty == "username"]selected[/#if] value="username">${message("Member.username")}</option>
									 <option [#if page.searchProperty == "email"]selected[/#if] value="email">${message("Member.email")}</option>

								</select>
							</div>
					      	<input type="text" class="form-control" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					      	<span class="input-group-btn">
					        	<button class="btn btn-default" type="submit"><i class="fa fa-search"></i> 搜索</button>
					      	</span>
					    </div><!-- /input-group -->
    				</div><!-- /.form-inline -->
    			</div><!-- /.pull-right -->
    			<div class="clearfix"></div>
    		</div><!-- /#pageListConsole -->

			<table id="listTable" class="list my-table">
				<tr>
					<th class="check">
						<input type="checkbox" id="selectAll" />
					</th>
					<th>
						<a href="javascript:;" class="sort" name="username">用户昵称<i class="fa"></i></a>
					</th>
                    <th>
                        <a href="javascript:;" class="sort" name="email">邮箱<i class="fa"></i></a>
                    </th>
					<th>
						<a href="javascript:;" class="sort" name="amount">总募捐额<i class="fa"></i></a>
					</th>
                    <th>
                        <a href="javascript:;" class="" name="">微信用户<i class="fa"></i></a>
                    </th>
                    <th>
                        <a href="javascript:;" class="sort" name="authenticationType">认证类型<i class="fa"></i></a>
                    </th>
                    <th>
                        <a href="javascript:;" class="sort" name="loginDate">上次登录时间<i class="fa"></i></a>
                    </th>
					<th>
						<a href="javascript:;" class="sort" name="createDate">${message("admin.common.createDate")}<i class="fa"></i></a>
					</th>
					<th>
						<span>${message("admin.member.status")}</span>
					</th>
					<th>
						<span>${message("admin.common.handle")}</span>
					</th>
				</tr>
				[#list page.content as member]
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${member.id}" />
						</td>
						<td>
							${member.username}
						</td>
                        <td>
							${member.email}
                        </td>
						<td>
							${member.amount}
						</td>
                        <td>
							[#if (member.unionid?length>1)]是[#else]否[/#if]
                        </td>
                        <td>
							${member.authenticationType!'无'}
                        </td>
							<td>
							[#if null != member.loginDate]
								<span title="${member.loginDate?string("yyyy-MM-dd HH:mm:ss")}">${member.loginDate?string("yyyy-MM-dd HH:mm:ss")}</span>
							[#else]
								无
							[/#if]
						</td>
						<td>
							<span title="${member.createDate?string("yyyy-MM-dd HH:mm:ss")}">${member.createDate?string("yyyy-MM-dd HH:mm:ss")}</span>
						</td>
						<td>
							[#if !member.isEnabled]
								<b class="text-danger">${message("admin.member.disabled")}</b>
							[#elseif member.isLocked]
								<b class="text-danger"> ${message("admin.member.locked")} </b>
							[#else]
								<b class="text-success">${message("admin.member.normal")}</b>
							[/#if]
						</td>
						<td>
							<a href="view.html?id=${member.id}" class="btn btn-primary btn-xs">${message("admin.common.view")}</a>
							<a href="edit.html?id=${member.id}" class="btn btn-info btn-xs">${message("admin.common.edit")}</a>
						</td>
					</tr>
				[/#list]
			</table>

			[@pagesTemaplte page /]

		</form>

	 </section><!-- /.content -->

	[#include "/admin/common/main.footer.ftl"]

	<!-- js加载 -->
	[#include "/admin/js/common.ftl" /]
	<script data-main="${base}/resources/admin/js/list.js" src="${base}/resources/plugins/requirejs/require.js" defer="defer" async="async"></script>
</body>
</html>