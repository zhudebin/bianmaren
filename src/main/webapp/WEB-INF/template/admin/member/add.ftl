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
      	${message("admin.member.add")}
      	<a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
      	<a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${base}/admin/common/index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">${message("admin.member.add")}</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
		<form id="inputForm" action="save.html" method="post">
			<table class="input tabContent table">
				<tr>
					<th width=100>
						<span class="requiredField">*</span>学校:
					</th>
					<td>
						<div class="form-inline">
							<select name="schoolId" id="schoolId" class="form-control" required>
								<option value="">==请选择学校==</option>
								[#list schoolList as school]
									<option value="${school.id}">${school.name}</option>
								[/#list]
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<th width=100>
						<span class="requiredField">*</span>楼栋:
					</th>
					<td>
						<div class="form-inline">
							<select name="schoolUnitsId" id="schoolUnitsId" class="form-control" required>
								<option value="">==请选择楼栋==</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<th width=100>
						<span class="requiredField">*</span>${message("Member.username")}:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" name="username" class="text form-control" maxlength="20" required />
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>手机:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" id="mobile" name="mobile" class="text form-control" maxlength="11" minlength="11" required />
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>${message("Member.password")}:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" id="password" name="password" class="text form-control" maxlength="20" required />
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>${message("admin.member.rePassword")}:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" name="rePassword" class="text form-control" maxlength="20" required />
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField"></span>${message("Member.email")}:
					</th>
					<td>
						<div class="form-inline">
								<input type="text" name="email" class="text form-control" maxlength="200" email="true" />
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>白条币:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" name="bai_tiao" class="text form-control" value="0" maxlength="16" required number='true' /> <small class="text-danger">*买家用</small>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>余额:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" name="yu_e" class="text form-control" value="0" maxlength="16" required number='true' /> <small class="text-danger">*买家用</small>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>工资币:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" name="balance" class="text form-control" value="0" maxlength="16" required number='true' /> <small class="text-danger">*卖家用</small>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						<span class="requiredField">*</span>章鱼币:
					</th>
					<td>
						<div class="form-inline">
							<input type="text" name="zhang_yu_bi" class="text form-control" value="0" maxlength="16" required number='true' /> <small class="text-danger">*卖家用</small>
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
								<input type="text" id="avator" name="avator" class="text form-control" />
								<input type="button" id="browserButton" class="btn btn-default form-control" value="${message("admin.browser.select")}" />
							</div>
						</span>
					</td>
				</tr>
				<tr>
					<th>
						用户性别:
					</th>
					<td>
						<label>
							<input type="radio" name="genderType" value="0" checked="checked" />&nbsp;男
							<input type="radio" name="genderType" value="1"  />&nbsp;女
						</label>
					</td>
				</tr>
				<tr>
					<th>
						用户角色:
					</th>
					<td>
						<label>
							<input type="checkbox" name="isRoleCommon" value="true" data-role='common' checked="checked" />&nbsp;普通消费者
							<input type="checkbox" name="isRoleShopowner" value="true" data-role='shopowner'  />&nbsp;店长
							<input type="checkbox" name="isRoleDeliveryboy" value="true" data-role='deliveryboy' />&nbsp;三送郎
						</label>
					</td>
				</tr>
				<tr id="trShopName" class="hidden">
					<th>
						店铺名称:
					</th>
					<td>
						<div class="form-inline">
								<input type="text" id="shopName" name="shopName" class="text form-control" required/>
						</div>
					</td>
				</tr>
				<tr>
					<th>
						${message("admin.common.setting")}:
					</th>
					<td>
						<label>
							<input type="checkbox" name="isEnabled" value="true" checked="checked" />&nbsp;${message("Member.isEnabled")}
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