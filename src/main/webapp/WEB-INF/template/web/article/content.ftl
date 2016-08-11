<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

	<title>${article.seoTitle}</title>
    <meta name="keywords" content="${article.seoKeywords}" />
    <meta name="description" content="${article.seoDescription}" />

    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Stylesheets -->
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/slick/slick.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/slick/slick-theme.min.css">
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/oneui.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/sweetalert/sweetalert.min.css">
    <link rel="stylesheet" href="${base}/assets/js/plugins/highlightjs/default.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/pages/web/common.css">
</head>
<body>
<!-- Page Container -->
<div id="page-container" class="header-navbar-fixed header-navbar-transparent"">

[#include "/web/header.ftl" /]
[#include "/web/include/template.ftl" /]
    <!-- Main Container -->
    <main id="main-container">

        <div class="bg-image" id="topBox" >
            <div class="bg-primary-dark-op">
                <section class="content content-full content-boxed overflow-hidden">
                    <!-- Section Content -->
                    <div class="push-50-t push-50 text-center">
                        <h1 class="h2 text-white push-10 animated fadeInDown" data-toggle="appear" data-class="animated fadeInDown">${article.title}</h1>
                        <h2 class="h5 text-white-op animated fadeInDown" data-toggle="appear" data-class="animated fadeInDown">
                            ${article.admin.username} 发布于 ${article.createDate?string("yyyy-MM-dd HH:mm:ss")}
                            <span class="font-s13">&nbsp;&nbsp;<b id="hits"><i class="fa fa-spinner fa-pulse fa-fw"></i></b> 访问</span>
                        </h2>
                        <p class="text-white push-20-t">
                            标签 ：
                            [#list article.tags as tag]
                                <span class="label label-default">${tag.name}</span>
                            [/#list]
                        </p>
                    </div>
                    <!-- END Section Content -->
                </section>
            </div>
        </div>

        <div class="">
            <section class="content content-boxed">

                <div class="push-20">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="block block-rounded">
                                <div class="block-content">
									<div class="articleContent table-responsive">
                                        [#--[#escape x as x?html]--]
										    ${article.content}
                                        [#--[/#escape]--]
									</div><!-- /.articleContent -->

                                    [#if article.reprintedUrl??]
                                        [#if null != article.reprintedUrl && article.reprintedUrl?length >5]
                                            <blockquote class="push-10-t font-s13">
                                                <p>本文转载至：${article.reprintedUrl}</p>
                                            </blockquote>
                                        [/#if]
                                    [/#if]

                                    [@pagination pageNumber = article.pageNumber totalPages = article.totalPages pattern = "{pageNumber}.htm"]
                                        [#include "/web/include/pagination.ftl"]
                                    [/@pagination]

                                    <!-- UY BEGIN -->
                                    <div id="uyan_frame"></div>
                                    <script type="text/javascript" src="http://v2.uyan.cc/code/uyan.js?uid=1613778"></script>
                                    <!-- UY END -->

								</div>
                            </div>
                        </div><!-- /.col-md-8 -->
                        <div class="col-md-4">

                            <div class="block">
                                <div class="block-content block-content-full text-center bg-image" id="userBox">
                                    [#assign avatars = article.admin.headPortrait /]
                                    [#if !(avatars??) || (avatars?length <1) ]
                                        [#assign avatars = '${base}/assets/img/avatars/avatar12.jpg' /]
                                    [/#if]
                                    <img class="img-avatar img-avatar96 img-avatar-thumb" src="${avatars}" alt="">
                                    <h4 class="text-white push-10-t">${article.admin.username}</h4>
                                </div>
                                <div class="block-content">
                                    <div class="row items-push text-center">
                                        <div class="col-xs-6 da-shang"  data-img="${article.admin.weiXinScanCode}">
                                            <div class="push-5 text-center"><img style="max-width: 40px" src="${base}/assets/img/weixin.png" alt=""/></div>
                                            <div class="h5 font-w300 text-muted">微信打赏</div>
                                        </div>
                                        <div class="col-xs-6 da-shang"  data-img="${article.admin.zhiFuBaoScanCode}">
                                            <div class="push-5 text-center"><img style="max-width: 40px" src="${base}/assets/img/zhifubao.png" alt=""/></div>
                                            <div class="h5 font-w300 text-muted">支付宝打赏</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="block-content remove-margin-t remove-padding-t">
                                    <p>${article.admin.introduction!'这个人很懒，没有留下任何自我介绍...'}</p>
                                </div>
                            </div>

                            <div class="block block-rounded">
                                <div class="block-header bg-gray-lighter text-center">
                                    <h3 class="block-title">热门文章</h3>
                                </div>
                                <div class="block-content">
                                    <table class="table table-borderless table-condensed">
                                        <tbody>
										[@article_list articleCategoryId = articleCategory.id count = 10 orderBy="hits desc"]
											[#list articles as article]
                                            <tr class="border-b">
                                                <td>
                                                    <a href="${base}${article.path}" class="text-city-dark" title="${article.title}">${abbreviate(article.title, 30)}</a>
                                                </td>
                                            </tr>
											[/#list]
										[/@article_list]
                                        </tbody>
                                    </table>
                                </div>
                            </div><!-- /.block -->

                        </div><!-- /.col-md-4 -->
                    </div>
                </div>

            </section>
        </div>

    </main><!-- /#main-container -->

[#include "/web/footer.ftl" /]


</div><!-- /#page-container -->


	<input type="hidden" id="articleId" name="articleId" value="${article.id}"/>

	[#include "/web/include/js/common.ftl" /]
	<!-- Page JS Code -->
	<script data-main="${base}/assets/js/pages/web/article/view.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>