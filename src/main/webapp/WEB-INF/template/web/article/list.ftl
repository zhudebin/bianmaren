<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

    <title>文章列表</title>

    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Stylesheets -->
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/slick/slick.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/slick/slick-theme.min.css">
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/oneui.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/pages/web/common.css">

</head>
<body>
	<!-- Page Container -->
	<div id="page-container" class="header-navbar-fixed header-navbar-transparent">

		[#include "/web/header.ftl" /]
		[#include "/web/include/template.ftl" /]
        <!-- Main Container -->
        <main id="main-container">

            <div class="bg-image" id="topBox" >
                <div class="bg-primary-dark-op">
                    <section class="content content-full content-boxed overflow-hidden">
                        <!-- Section Content -->
                        <div class="push-50-t push-50 text-center">
                            <h1 class="h2 text-white push-10 animated fadeInDown" data-toggle="appear" data-class="animated fadeInDown">${articleCategory.name}</h1>
                            <h2 class="h5 text-white-op animated fadeInDown" data-toggle="appear" data-class="animated fadeInDown">编码人 , Just Coding !!</h2>
                        </div>
                        <!-- END Section Content -->
                    </section>
                </div>
            </div>

            <div class="bg-white">
                <section class="content content-mini content-mini-full content-boxed overflow-hidden">
                    <ol class="breadcrumb">
                        <li><a class="text-primary-dark" href="${base}/">首页</a></li>
						[@article_category_parent_list articleCategoryId = articleCategory.id]
							[#list articleCategories as articleCategory]
                                <li><a class="text-primary-dark" href="${base}${articleCategory.path}">${articleCategory.name}</a></li>
							[/#list]
						[/@article_category_parent_list]
                        <li><a href="javascript:0" class="text-muted">${articleCategory.name}</a></li>
                    </ol>
                </section>
            </div>

            <div class="">
				<section class="content content-boxed">

                    <div class="push-20">
                        <div class="row">
                            <div class="col-md-8">
                                <form id="listForm" action="${base}${articleCategory.path}" method="get">
                                    <input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
									[#if page.content?has_content]
										[#list page.content as article]
											<!-- Story -->
											<div class="block">
												<div class="block-content">
													<div class="row items-push">
														<div class="col-md-4">
															<a href="#">
																[#assign headImg = article.headImg /]
																[#if !(headImg??) || (headImg?length <1) ]
																	[#assign headImg = '${base}/assets/img/question.jpg' /]
																[/#if]
																<img class="img-responsive" src="${headImg}" alt="">
															</a>
														</div>
														<div class="col-md-8">
															<div class="font-s12 push-10">
																<span class="pull-right"><i class="fa fa-eye"></i> ${article.hits}</span>
																${article.admin.username} 发表于
																${article.createDate?string("yyyy-MM-dd HH:mm:ss")}
															</div>
															<h4 class="text-uppercase push-10"><a class="text-primary-dark" href="${base}${article.path}">${abbreviate(article.title, 40, "...")}</a></h4>
															<p style="overflow: hidden">${abbreviate(article.text, 200, "...")}</p>
														</div>
													</div>
												</div>
											</div>
											<!-- END Story -->
										[/#list]
									[#else]
										<div class="block">
											<div class="block-content">
												<div class="text-center push-20">
													<i class="fa fa-warning text-warning fa-5x"></i>
												</div>
												<p class="text-center">暂无数据</p>
											</div><!-- /.block-content -->
										</div><!-- /.block -->
									[/#if]
                                </form>

								<div class="text-center">
									[@pagesTemaplte page /]
								</div>

                            </div>
                            <div class="col-md-4">
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



	[#include "/web/include/js/common.ftl" /]
    <!-- Page JS Code -->
    <!-- Page JS Code -->
    <script data-main="${base}/assets/js/pages/web/article/list.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>