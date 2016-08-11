<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>控制台</title>

    <meta name="description" content="控制台">
    <meta name="author" content="bianmaren">
    <meta name="robots" content="noindex, nofollow">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Common CSS -->
    [#include "/admin/include/css/common.ftl" /]

    <!-- Page JS Plugins CSS -->
    <link rel="stylesheet" href="${base}/assets/js/plugins/slick/slick.min.css">
    <link rel="stylesheet" href="${base}/assets/js/plugins/slick/slick-theme.min.css">

</head>
<body>
<div id="page-loader"></div>
<div id="page-container" class="sidebar-l sidebar-o side-scroll header-navbar-fixed">
    <!-- Side Overlay-->
    [#include "/admin/common/main.side.overlay.ftl" /]

    <!-- Sidebar -->
    [#include "/admin/common/main.left.nav.ftl" /]

    <!-- Header -->
    [#include "/admin/common/main.header.nav.ftl" /]

    <!-- Main Container -->
    <main id="main-container">
        <iframe id="main-container-iframe" name="main-container-iframe" src="index.html" frameborder="0"
                style="display: block;  width: 100%; overflow-x:hidden"></iframe>
    </main>
    <!-- END Main Container -->

    [#include "/admin/common/main.footer.ftl" /]
</div>
<!-- END Page Container -->


<!-- Page JS Code -->
[#include "/admin/include/js/common.ftl" /]
<script data-main="${base}/assets/js/pages/admin/main.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>