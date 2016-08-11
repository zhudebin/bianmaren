<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

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
                编辑文章
                <a class="content-header-btn" href="javascript:window.location.reload()"><i class="fa fa-refresh"></i> 刷新</a>
                <a class="content-header-btn" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回上一页</a>
            </h1>
        </div>
        <div class="col-sm-5 text-right hidden-xs">
            <ol class="breadcrumb push-10-t">
                <li>内容管理</li>
                <li><a class="link-effect">编辑文章</a></li>
            </ol>
        </div>
    </div>
</div>
<!-- END Page Header -->

<div class="block">
    <div class="block-content table-responsive">
        <form id="inputForm" class="form-horizontal" action="update.html" method="post">
            <input type="hidden" name="id" value="${article.id}" />
            <div class="form-group">
                <div class="col-xs-12 col-md-10">
                    <div class="form-material">
                        <input class="form-control" type="text" id="title" name="title" required value="${article.title}">
                        <label for="title">文章标题</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-md-4">
                    <div class="form-material">
                        <select class="form-control" name="articleCategoryId" >
						[#list articleCategoryTree as articleCategory]
                            <option value="${articleCategory.id}"[#if articleCategory == article.articleCategory] selected="selected"[/#if]>
								[#if articleCategory.grade != 0]
									[#list 1..articleCategory.grade as i]
                                        &nbsp;&nbsp;
									[/#list]
								[/#if]
							${articleCategory.name}
                            </option>
						[/#list]
                        </select>
                        <label for="articleCategoryId">文章分类</label>
                    </div>
                </div>
                <div class="col-xs-12  col-md-4">
                    <div class="form-material floating">
                        <input class="form-control" type="text" id="reprintedUrl" name="reprintedUrl" value="${article.reprintedUrl}">
                        <label for="reprintedUrl">转载地址</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div>
                        <label>
                            <label class="css-input css-checkbox css-checkbox-success">
                                <input type="checkbox" name="isPublication" value="true" [#if article.isPublication] checked[/#if]><span></span> ${message("Article.isPublication")}
                            </label>
                        </label>
                        <label>
                            <label class="css-input css-checkbox css-checkbox-success">
                                <input type="checkbox" name="isTop" value="true" [#if article.isTop] checked[/#if]/><span></span> ${message("Article.isTop")}
                            </label>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group hidden">
                <div class="col-xs-12 col-md-4">
                    <div class="form-material">
                        <input class="form-control" type="text" id="seoTitle" name="seoTitle" value="${article.seoTitle}">
                        <label for="seoTitle">${message("Article.seoTitle")}</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material">
                        <input class="form-control" type="text" id="seoKeywords" name="seoKeywords" value="${article.seoKeywords}">
                        <label for="seoKeywords">${message("Article.seoKeywords")}</label>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <div class="form-material">
                        <input class="form-control" type="text" id="seoDescription" name="seoDescription" value="${article.seoDescription}">
                        <label for="seoDescription">${message("Article.seoDescription")}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-md-4">
                    <div>
                        文章标签 :
					[#list tags as tag]
                        <label class="css-input css-checkbox css-checkbox-success">
                            <input type="checkbox" name="tagIds" value="${tag.id}" [#if article.tags?seq_contains(tag)] checked[/#if] /><span></span> ${tag.name}
                        </label>
					[/#list]
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 col-md-10">
                    <textarea id="editor" name="content" class="editor hidden">${article.content?html}</textarea>
                    <script id="editorUe" type="text/plain" style="width:100%;min-height:500px;">${article.content}</script>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-12 col-md-4">
                    <input type="submit" class="btn btn-success" id="submit-btn" value="${message("admin.common.submit")}" />
                    <input type="button" class="btn btn-square" id="submit-btn" value="${message("admin.common.back")}" onclick="location.href='list.html'"/>
                </div>
            </div>
        </form>
    </div>
</div><!-- /.block -->


<!-- Page JS Code -->
[#include "/admin/include/js/common.ftl" /]

<script type="text/javascript" charset="utf-8" src="${base}/assets/js/core/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/assets/js/plugins/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/assets/js/plugins/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${base}/assets/js/plugins/ueditor1_4_3_3-utf8-jsp/utf8-jsp/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    var ue = UE.getEditor('editorUe');
    ue.on('contentchange', function () {
        $("#editor").html(ue.getContent());
    });
</script>


</body>
</html>


