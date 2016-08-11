<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>在线留言 - ${setting.siteName}</title>
    <meta name="description" content="">
    <meta name="author" content="pixelcave">
    <meta name="robots" content="noindex, nofollow">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Stylesheets -->
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/slick/slick.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/slick/slick-theme.min.css">
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/oneui.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/pages/web/common.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/sweetalert/sweetalert.min.css">
</head>
<body>
<!-- Page Container -->
<div id="page-container" class="header-navbar-fixed header-navbar-transparent">

[#include "/web/header.ftl" /]

    <!-- Main Container -->
    <main id="main-container">

        <div class="content bg-image overflow-hidden" style="background-image: url('${base}/assets/img/photos/photo3@2x.jpg');">
            <div class="push-50-t push-50 text-center">
                <h1 class="h2 text-white animated zoomIn push-10">在线留言</h1>
                <h2 class="h5 text-white-op animated zoomIn">在这里留下你宝贵的意见</h2>
            </div>
        </div>

        <div class="bg-white">
            <section class="content content-boxed">
                <!-- UY BEGIN -->
                <div id="uyan_frame"></div>
                <script type="text/javascript" src="http://v2.uyan.cc/code/uyan.js?uid=1613778"></script>
                <!-- UY END -->
            </section>
        </div>

    </main>
    <!-- END Main Container -->

[#include "/web/footer.ftl" /]
</div>
<!-- END Page Container -->

[#include "/web/include/js/common.ftl" /]
<!-- Page JS Code -->
<script data-main="${base}/assets/js/pages/web/aboutUs.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>
