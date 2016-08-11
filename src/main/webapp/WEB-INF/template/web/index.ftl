<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>${setting.siteName}</title>
    <meta name="keywords" content="编码人,编码人博客,程序员,编程,IT,技术,博客" />
    <meta name="description" content="一个程序员博客的平台,可以分享自己的学习心得" />

    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">
    <meta name="baidu-site-verification" content="GiLi7A3t5W" />

    <!-- Stylesheets -->
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/slick/slick.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/slick/slick-theme.min.css">
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/oneui.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/pages/web/common.css">

    <!-- 百度统计 -->
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?a36a89df54e63fee49efb7c16e3733aa";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>
<body>
    <!-- Page Container -->
    <div id="page-container" class="header-navbar-fixed header-navbar-transparent">

        [#include "/web/header.ftl" /]

        <!-- Main Container -->
        <main id="main-container">

            <div class="bg-image" id="topBox" style="background-image:url('${base}/assets/img/photos/photo12@2x.jpg')" >
                <div>
                    <section class="content content-full content-boxed overflow-hidden">
                        <!-- Section Content -->
                        <div class="push-50-t push-50 text-center">
                            <h1 class="h2 text-white push-10 animated fadeInDown" data-toggle="appear" data-class="animated fadeInDown">今日名言</h1>
                            <h2 class="h5 text-white-op animated fadeInDown" data-toggle="appear" data-class="animated fadeInDown">
                                [@famous_aphorism]
                                    ${famousAphorism.mrname} ：${famousAphorism.content}
                                [/@famous_aphorism]
                            </h2>
                        </div>
                        <!-- END Section Content -->
                    </section>
                </div>
            </div>

            <!-- END Hero Content -->
            <!-- Content -->
            <section class="content content-boxed">
                <div class="push-30-t">
                    <div class="row"  id="articleBox"></div>
                </div>
                <div class="myloading text-center font-w300 push-30" style="font-size: 20px;"><i class="fa fa-spinner fa-pulse fa-fw "></i> 马不停蹄加载中...</div>
                <div class="mynomore text-center font-w300 push-30 hidden" style="font-size: 20px;">哥 , 没货了</div>
            </section>
            <!-- END Content -->
        </main>
        <!-- END Main Container -->

        [#include "/web/footer.ftl" /]
    </div>
    <!-- END Page Container -->

    [#include "/web/include/js/common.ftl" /]
    <!-- Page JS Code -->
    <script data-main="${base}/assets/js/pages/web/index.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>
