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
    <link rel="stylesheet" href="${base}/assets/js/plugins/bootstrap-datepicker/bootstrap-datepicker3.min.css">

</head>
<body>
[#include "/admin/include/template.ftl" /]
<!-- Page Header -->
<div class="content-mini bg-gray-lighter">
    <div class="row items-push">
        <div class="col-sm-7">
            <h1 class="page-heading">
                静态化管理
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>网站管理</li>
                <li><a class="link-effect">静态化管理</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->

<div class="block">
    <div class="block-content table-responsive">
        <form id="inputForm" action="build.html" method="post" class="form-inline">

            <table class="table">
                <tr>
                    <th width="150">
					${message("admin.static.buildType")}:
                    </th>
                    <td>
                        <select id="buildType" name="buildType" class="form-control">
						[#list buildTypes as buildType]
							[#if buildType='index' || buildType='article']
                                <option value="${buildType}">${message("admin.static." + buildType)}</option>
							[/#if]
						[/#list]
                        </select>
                    </td>
                </tr>
                <tr id="articleCategoryTr" class="hidden">
                    <th>
					${message("Article.articleCategory")}:
                    </th>
                    <td>
                        <select id="articleCategoryId" name="articleCategoryId" class="form-control">
                            <option value="">${message("admin.static.emptyOption")}</option>
						[#list articleCategoryTree as articleCategory]
                            <option value="${articleCategory.id}">
								[#if articleCategory.grade != 0]
									[#list 1..articleCategory.grade as i]
                                        &nbsp;&nbsp;
									[/#list]
								[/#if]
							${articleCategory.name}
                            </option>
						[/#list]
                        </select>
                    </td>
                </tr>
                <tr id="productCategoryTr" class="hidden">
                    <th>
					${message("Product.productCategory")}:
                    </th>
                    <td>
                        <select id="productCategoryId" name="productCategoryId" class="form-control">
                            <option value="">${message("admin.static.emptyOption")}</option>
						[#list productCategoryTree as productCategory]
                            <option value="${productCategory.id}">
								[#if productCategory.grade != 0]
									[#list 1..productCategory.grade as i]
                                        &nbsp;&nbsp;
									[/#list]
								[/#if]
							${productCategory.name}
                            </option>
						[/#list]
                        </select>
                    </td>
                </tr>
                <tr id="beginDateTr" class="hidden">
                    <th>
					${message("admin.static.beginDate")}:
                    </th>
                    <td>
                        <input type="text" id="beginDate" name="beginDate" class="text Wdate form-control" value="${defaultBeginDate?string("yyyy-MM-dd")}" title="${message("admin.static.beginDateTitle")}" />
                    </td>
                </tr>
                <tr id="endDateTr" class="hidden">
                    <th>
					${message("admin.static.endDate")}:
                    </th>
                    <td>
                        <input type="text" id="endDate" name="endDate" class="text Wdate form-control" value="${defaultEndDate?string("yyyy-MM-dd")}" title="${message("admin.static.endDateTitle")}"/>
                    </td>
                </tr>
                <tr id="countTr" class="hidden">
                    <th>
                        <span class="requiredField">*</span>${message("admin.static.count")}:
                    </th>
                    <td>
                        <input type="text" id="count" name="count"  class="form-control" value="50" maxlength="9" />
                    </td>
                </tr>
                <tr id="statusTr" class="hidden">
                    <th>
                        &nbsp;
                    </th>
                    <td>
						<div class=" col-xs-4">
                            <div class="loadingBar progress active">
                                <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                            </div>
                            <div id="status"></div>
						</div>

                    </td>
                </tr>
                <tr>
                    <th>
                        &nbsp;
                    </th>
                    <td>
                        <input type="submit" class="btn btn-success" value="${message("admin.common.submit")}" />
                        <input type="button" class="btn" value="${message("admin.common.back")}" onclick="location.href='../common/index.html'" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div><!-- /.block -->


<!-- Page JS Code -->
[#include "/admin/include/js/common.ftl" /]
<script data-main="${base}/assets/js/pages/admin/static/build.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>


