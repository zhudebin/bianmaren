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
	<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
      	编辑会员
      	<a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
      	<a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${base}/admin/common/index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">编辑会员</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
		<form id="inputForm" action="update.html" method="post">
			<table class="input tabContent table">
				<input type='hidden' name="id" id="id" value="${member.id}" />
                <tr>
                    <th>
                        <span class="requiredField"></span>${message("Member.email")}:
                    </th>
                    <td>
                        <div class="form-inline">
                            <input type="text" name="email" class="text form-control disabled" maxlength="200" value="${member.email}" />
                        </div>
                    </td>
                </tr>
				<tr>
					<th width=100>
						<span class="requiredField">*</span>${message("Member.username")}:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" name="username" class="text form-control" maxlength="20" required value="${member.username}"/>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						用户头像:
					</th>
					<td>
						<span class="fieldSet">
							<div class="form-inline">
								<input type="text" id="avator" name="avator" class="text form-control" value="${member.avator}" />
								<input type="button" id="browserButton" class="btn btn-default form-control" value="${message("admin.browser.select")}" />
							</div>
						</span>
					</td>
				</tr>
				<tr class="hidden">
					<th>
						用户性别:
					</th>
					<td>
						<label>
							<input type="radio" name="genderType" value="0" [#if member.gender == "male"]checked="checked"[/#if]  />&nbsp;男 
							<input type="radio" name="genderType" value="1" [#if member.gender == "female"]checked="checked"[/#if] />&nbsp;女
						</label>
					</td>
				</tr>
				<tr>
					<th>
						${message("admin.common.setting")}:
					</th>
					<td>
						<label>
							<input type="checkbox" name="isEnabled" value="true" [#if member.isEnabled] checked="checked"[/#if] />&nbsp;${message("Member.isEnabled")}
						</label>
					</td>
				</tr>
			</table>
			<table class="input">
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<input type="submit" class="btn btn-success" value="${message("admin.common.submit")}" />
						<input type="button" class="btn btn-default" value="${message("admin.common.back")}" onclick="location.href='list.html'" />
					</td>
				</tr>
			</table>
		</form>
		
	</section><!-- /.content -->
	[#include "/admin/common/main.footer.ftl"]
	
	<!-- js加载 -->
	[#include "/admin/js/common.ftl" /]
	<script data-main="${base}/resources/admin/js/member/add.js" src="${base}/resources/plugins/requirejs/require.js" defer="defer" async="async"></script>
</body>
</html>