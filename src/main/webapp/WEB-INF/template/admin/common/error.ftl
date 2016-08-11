<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>出错了</title>

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

<div class="content bg-white text-center pulldown overflow-hidden">
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <!-- Error Titles -->
            <h1 class="font-s36 font-w300 text-amethyst animated fadeInDown">程序开了个小差</h1>
			<br/>
            <h2 class="h3 font-w300 push-20 animated fadeInUp">
				[#if content??]${content}[/#if]
			</h2>
			[#if constraintViolations?has_content]
				[#list constraintViolations as constraintViolation]
                    <h4 class="h4 font-w300 animated fadeInUp">
						${constraintViolation.propertyPath}:${constraintViolation.message}
                    </h4>
				[/#list]
			[/#if]
			<div class="push-50"></div>
        </div>
    </div>
</div>

<!-- Error Footer -->
<div class="content pulldown text-muted text-center">
    <a class="link-effect" onclick="window.history.back(); return false;" >返回上一页</a>
</div>
<!-- END Error Footer -->


<!-- Page JS Code -->
[#include "/admin/include/js/common.ftl" /]
<script data-main="${base}/assets/js/pages/admin/list.page.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>


