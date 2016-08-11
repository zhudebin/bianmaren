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
      	${message("admin.role.edit")}
      	<a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
      	<a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${base}/admin/common/index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">${message("admin.role.edit")}</li>
      </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
		<form id="inputForm" action="update.html" method="post">
			<input type="hidden" name="id" value="${role.id}" />
			<table class="input table">
				<tr>
					<th width=140>
						<span class="requiredField">*</span>${message("Role.name")}:
					</th>
					<td>
						<div class="form-inline	">
							<input type="text" name="name" class="text form-control" value="${role.name}" maxlength="200" required />
						</div>
					</td>
				</tr>
				<tr>
					<th>
						${message("Role.description")}:
					</th>
					<td>
						<div class="form-inline	">
							<input type="text" name="description" class="text form-control" value="${role.description}" maxlength="200" required />
						</div>
					</td>
				</tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">商品管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
                                <input type="checkbox" name="authorities" value="admin:factoryProductCirculation" [#if role.authorities?seq_contains("admin:factoryProductCirculation")] checked="checked"[/#if] /> 厂家商品流转列表 &nbsp;
                            </label>
							<label>
                                <input type="checkbox" name="authorities" value="admin:factoryProductCirculationAdd" [#if role.authorities?seq_contains("admin:factoryProductCirculationAdd")] checked="checked"[/#if] /> 添加厂家商品流转 &nbsp;
                            </label>
							<label>
                                <input type="checkbox" name="authorities" value="admin:factoryProductCirculationDel" [#if role.authorities?seq_contains("admin:factoryProductCirculationDel")] checked="checked"[/#if] /> 删除厂家商品流转 &nbsp;
                            </label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productCirculation" [#if role.authorities?seq_contains("admin:productCirculation")] checked="checked"[/#if] /> 仓库商品流转 &nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:shopProductCirculation" [#if role.authorities?seq_contains("admin:shopProductCirculation")] checked="checked"[/#if] /> 零食店商品流转  &nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productList" [#if role.authorities?seq_contains("admin:productList")] checked="checked"[/#if] /> 商品列表 &nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productAdd" [#if role.authorities?seq_contains("admin:productAdd")] checked="checked"[/#if] /> 添加商品 &nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productEdit" [#if role.authorities?seq_contains("admin:productEdit")] checked="checked"[/#if] />  编辑商品 &nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productDelete" [#if role.authorities?seq_contains("admin:productDelete")] checked="checked"[/#if] /> 删除商品 &nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productCategoryList" [#if role.authorities?seq_contains("admin:productCategoryList")] checked="checked"[/#if] /> 商品分类列表&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productCategoryAdd" [#if role.authorities?seq_contains("admin:productCategoryAdd")] checked="checked"[/#if] />  添加商品分类&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productCategoryEdit" [#if role.authorities?seq_contains("admin:productCategoryEdit")] checked="checked"[/#if] />  编辑商品分类&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:productCategoryDelete" [#if role.authorities?seq_contains("admin:productCategoryDelete")] checked="checked"[/#if] />  删除商品分类&nbsp;
							</label>
						</span>
					</td>
				</tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">订单管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="authorities" value="admin:order"  [#if role.authorities?seq_contains("admin:order")] checked="checked"[/#if] /> ${message("admin.role.order")}&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:orderReturn" [#if role.authorities?seq_contains("admin:orderReturn")] checked="checked"[/#if] /> 退货管理&nbsp;
							</label>
						</span>
					</td>
				</tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">店铺管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="authorities" value="admin:shop" [#if role.authorities?seq_contains("admin:shop")] checked="checked"[/#if] /> 店铺管理&nbsp;
							</label>
						</span>
					</td>
				</tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">会员管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="authorities" value="admin:memberList" [#if role.authorities?seq_contains("admin:memberList")] checked="checked"[/#if] /> 会员列表&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:memberAdd" [#if role.authorities?seq_contains("admin:memberAdd")] checked="checked"[/#if] /> 添加会员&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:memberEdit" [#if role.authorities?seq_contains("admin:memberEdit")] checked="checked"[/#if] /> 编辑会员&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:memberDelete" [#if role.authorities?seq_contains("admin:memberDelete")] checked="checked"[/#if] />  删除会员&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:memberRank" [#if role.authorities?seq_contains("admin:memberRank")] checked="checked"[/#if] /> ${message("admin.role.memberRank")}&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:memberTiXian" [#if role.authorities?seq_contains("admin:memberTiXian")] checked="checked"[/#if] />  用户提现管理&nbsp;
							</label>
						</span>
					</td>
				</tr>
                <tr class="authorities">
                    <td>
                        <a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">数据修改申请</a>
                    </td>
                    <td>
						<span class="fieldSet">
							<label>
                                <input type="checkbox" name="authorities" value="admin:DataAlterApplyList" [#if role.authorities?seq_contains("admin:DataAlterApplyList")] checked="checked"[/#if] /> 数据修改申请&nbsp;
                            </label>
							<label>
                                <input type="checkbox" name="authorities" value="admin:DataAlterApplyShenHe" [#if role.authorities?seq_contains("admin:DataAlterApplyShenHe")] checked="checked"[/#if] /> 数据修改审核&nbsp;
                            </label>
							<label>
                                <input type="checkbox" name="authorities" value="admin:DataAlterApplyAdd" [#if role.authorities?seq_contains("admin:DataAlterApplyAdd")] checked="checked"[/#if] /> 数据修改添加&nbsp;
                            </label>
						</span>
                    </td>
                </tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">内容管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="authorities" value="admin:article" [#if role.authorities?seq_contains("admin:article")] checked="checked"[/#if] /> ${message("admin.role.article")}&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:articleCategory" [#if role.authorities?seq_contains("admin:articleCategory")] checked="checked"[/#if] /> ${message("admin.role.articleCategory")}&nbsp;
							</label>
						</span>
					</td>
				</tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">统计管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="authorities" value="admin:statistics" [#if role.authorities?seq_contains("admin:statistics")] checked="checked"[/#if] /> ${message("admin.role.statistics")}&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:sales" [#if role.authorities?seq_contains("admin:sales")] checked="checked"[/#if] /> ${message("admin.role.sales")}&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:salesRanking" [#if role.authorities?seq_contains("admin:salesRanking")] checked="checked"[/#if] /> ${message("admin.role.salesRanking")}&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:purchaseRanking" [#if role.authorities?seq_contains("admin:purchaseRanking")] checked="checked"[/#if] /> ${message("admin.role.purchaseRanking")}&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:deposit" [#if role.authorities?seq_contains("admin:deposit")] checked="checked"[/#if] /> ${message("admin.role.deposit")}&nbsp;
							</label>
						</span>
					</td>
				</tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">运营管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="authorities" value="admin:appfocusimages" [#if role.authorities?seq_contains("admin:appfocusimages")] checked="checked"[/#if] /> APP首页轮播图&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:frienLink" [#if role.authorities?seq_contains("admin:frienLink")] checked="checked"[/#if] /> 友情链接&nbsp;
							</label>
						</span>
					</td>
				</tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">基础数据管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="authorities" value="admin:area" [#if role.authorities?seq_contains("admin:area")] checked="checked"[/#if]  /> 地区管理&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:warehouse" [#if role.authorities?seq_contains("admin:warehouse")] checked="checked"[/#if] /> 仓库管理&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:schoolunits" [#if role.authorities?seq_contains("admin:schoolunits")] checked="checked"[/#if] /> 学校楼栋管理&nbsp;
							</label>
						</span>
					</td>
				</tr>
				<tr class="authorities">
					<td>
						<a href="javascript:;" class="selectAll" title="${message("admin.role.selectAll")}">网站管理</a>
					</td>
					<td>
						<span class="fieldSet">
							<label>
								<input type="checkbox" name="authorities" value="admin:setting" [#if role.authorities?seq_contains("admin:setting")] checked="checked"[/#if]  /> 系统设置&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:adminlist" [#if role.authorities?seq_contains("admin:adminlist")] checked="checked"[/#if] /> 管理员&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:role" [#if role.authorities?seq_contains("admin:role")] checked="checked"[/#if] /> 角色管理&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:sendMessage" [#if role.authorities?seq_contains("admin:sendMessage")] checked="checked"[/#if] /> 发送消息&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:messageList" [#if role.authorities?seq_contains("admin:messageList")] checked="checked"[/#if] /> 消息列表&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:log" [#if role.authorities?seq_contains("admin:log")] checked="checked"[/#if]  /> 日志管理&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:template" [#if role.authorities?seq_contains("admin:template")] checked="checked"[/#if] /> 模板管理&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:static" [#if role.authorities?seq_contains("admin:static")] checked="checked"[/#if] /> 静态化管理&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:cache" [#if role.authorities?seq_contains("admin:cache")] checked="checked"[/#if] /> 缓存管理&nbsp;
							</label>
							<label>
								<input type="checkbox" name="authorities" value="admin:phoneTest" [#if role.authorities?seq_contains("admin:phoneTest")] checked="checked"[/#if] /	> 手机短信测试&nbsp;
							</label>
						</span>
					</td>
				</tr>
				[#if role.isSystem]
					<tr>
						<th>
							&nbsp;
						</th>
						<td>
							<span class="tips">${message("admin.role.editSystemNotAllowed")}</span>
						</td>
					</tr>
				[/#if]
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<input type="submit" class="btn btn-success" value="${message("admin.common.submit")}"[#if role.isSystem] disabled="disabled"[/#if] />
						<input type="button" class="btn btn-default" value="${message("admin.common.back")}" onclick="location.href='list.html'" />
					</td>
				</tr>
			</table>
		</form>

	</section><!-- /.content -->
	[#include "/admin/common/main.footer.ftl"]
	
	<!-- js加载 -->
	[#include "/admin/js/common.ftl" /]
	<script data-main="${base}/resources/admin/js/input.js" src="${base}/resources/plugins/requirejs/require.js" defer="defer" async="async"></script>
</body>
</html>