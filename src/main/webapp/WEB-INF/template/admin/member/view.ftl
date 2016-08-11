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
      	${message("admin.member.view")}
      	<a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
      	<a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${base}/admin/common/index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">${message("admin.member.view")}</li>
      </ol>
    </section>
    <!-- Main content -->
    <section class="content">
		<table class="input table">
			<tr>
				<th width=120>
					头像:
				</th>
				<td>
					[#if null != member.avator && member.avator?length>0]
						<a href="${member.avator}" target="_bank"><img src="${member.avator}" title="${member.username}" height=50 /></a>
					[#else]
						<a href="${base}/resources/admin/images/user-default.jpg" target="_bank"><img src="${base}/resources/admin/images/user-default.jpg" title="${member.username}" height=50 /></a>
					[/#if]
				</td>
			</tr>
			<tr>
				<th width=120>
					${message("Member.username")}:
				</th>
				<td>
					${member.username}
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.email")}:
				</th>
				<td>
					${member.email}
				</td>
			</tr>
            <tr>
                <th>
                    手机:
                </th>
                <td>
				${member.mobile}
                </td>
            </tr>
			<tr>
				<th>
					总募捐额:
				</th>
				<td>
					${member.amount}
				</td>
			</tr>
			<tr>
				<th>状态:</th>
				<td>
					[#if !member.isEnabled]
						<b class="text-danger">${message("admin.member.disabled")}</b>
					[#elseif member.isLocked]
						<b class="text-danger"> ${message("admin.member.locked")} </b>
					[#else]
						<b class="text-success">${message("admin.member.normal")}</b>
					[/#if]
				</td>
			</tr>	
			<tr>
				<th>
					${message("admin.common.createDate")}:
				</th>
				<td>
					${member.createDate?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.loginDate")}:
				</th>
				<td>
					${(member.loginDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.registerIp")}:
				</th>
				<td>
					${member.registerIp}
				</td>
			</tr>
			<tr>
				<th>
					${message("Member.loginIp")}:
				</th>
				<td>
					${(member.loginIp)!"-"}
				</td>
			</tr>
		</table>
	
    </section><!-- /.content -->
	
	[#include "/admin/common/main.footer.ftl"]
	
	<!-- js加载 -->
	[#include "/admin/js/common.ftl" /]
	<script data-main="${base}/resources/admin/js/shop/list.js" src="${base}/resources/plugins/requirejs/require.js" defer="defer" async="async"></script>
</body>
</html>