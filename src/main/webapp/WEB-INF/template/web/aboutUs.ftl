<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>${setting.siteName}</title>
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

        <div class="bg-primary-dark">
            <section class="content content-full content-boxed">
                <!-- Section Content -->
                <div class="push-100-t push-50 text-center">
                    <h1 class="h2 text-white push-10 animated fadeInDown" data-toggle="appear" data-class="animated fadeInDown">我们是编码人</h1>
                    <h2 class="h5 text-white-op animated fadeInDown" data-toggle="appear" data-class="animated fadeInDown">我们在这里会分享一些比较好的文章，如果你想加入我们，我们非常欢迎，我们是快乐的程序员，我编程，我快乐</h2>
                </div>
                <!-- END Section Content -->
            </section>
        </div>

        <div class="bg-white">
            <section class="content content-boxed">
                <!-- Section Content -->
                <div class="row items-push push-20-t nice-copy">
                    <div class="col-md-6">
                        <h3 class="h5 font-w600 text-uppercase push-10">关于我们</h3>
                        <p>
                            编码人网站是由邓文兵，邓文武两人创建的，用于分享一些比较好的文章，以及自己的学习心得。我们也非常欢迎其他编码人加入我们的平台。
                        </p>
                        <h3 class="h5 font-w600 text-uppercase push-10">技术服务</h3>
                        <p>
                            我们还提供各种技术服务，主要服务有网站建设，网站制作，APP制作，微信应用开发。我们都是经验丰富的开发者。您有什么需求赶紧联系我们吧 ！
                        </p>
                        <h3 class="h5 font-w600 text-uppercase push-10">联系方式</h3>
                        <p>
                            姓名：邓文兵 <br />
                            联系电话：18770090755 <br />
                            QQ：441889070 <br />
                        </p>
                    </div>
                    <div class="col-md-6">
                        <!-- Company Timeline -->
                        <div class="block block-transparent">
                            <div class="block-content">
                                <ul class="list list-timeline pull-t">
                                    <li class="animated fadeInRight" data-toggle="appear" data-class="animated fadeInRight">
                                        <div class="list-timeline-time">2016-5</div>
                                        <i class="si si-bulb list-timeline-icon bg-warning"></i>
                                        <div class="list-timeline-content">
                                            <p class="font-w600">我们有了这个想法 !</p>
                                            <p class="font-s13">编码人博客平台 !</p>
                                        </div>
                                    </li>
                                    <li class="animated fadeInRight" data-toggle="appear" data-timeout="100" data-class="animated fadeInRight">
                                        <div class="list-timeline-time">2016-6</div>
                                        <i class="si si-speedometer list-timeline-icon bg-city"></i>
                                        <div class="list-timeline-content">
                                            <p class="font-w600">我们开始搭建平台 !</p>
                                            <p class="font-s13">前台采用OneUI框架，后台使用Spring MVC + JPA + EHCACHE !</p>
                                        </div>
                                    </li>
                                    <li class="animated fadeInRight" data-toggle="appear" data-timeout="200" data-class="animated fadeInRight">
                                        <div class="list-timeline-time">2016-7</div>
                                        <i class="si si-briefcase list-timeline-icon bg-smooth"></i>
                                        <div class="list-timeline-content">
                                            <p class="font-w600">平台搭建完成 !</p>
                                            <p class="font-s13">购买阿里云服务，上线 !</p>
                                        </div>
                                    </li>
                                    <li class="animated fadeInRight" data-toggle="appear" data-timeout="400" data-class="animated fadeInRight">
                                        <div class="list-timeline-time">未来</div>
                                        <i class="si si-like list-timeline-icon bg-primary"></i>
                                        <div class="list-timeline-content">
                                            <p class="font-w600">我们会把平台做的更好 !</p>
                                            <p class="font-s13">欢迎大家加入我们 !</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- END Company Timeline -->
                    </div>
                </div>
                <!-- END Section Content -->
            </section>
        </div>

        <div class="bg-gray-lighter">
            <section class="content content-full content-boxed">
                <!-- Section Content -->
                <div class="push-20-t push-20 text-center">
                    <h3 class="h4 push-20 animated fadeIn" data-toggle="appear">觉得平台对您有帮助，打赏一点呗 !</h3>
                    <a class="da-shang btn btn-rounded btn-noborder btn-lg btn-success animated bounceIn" data-img="${base}/assets/img/others/weixin-dengwenbing.png" data-toggle="appear" data-class="animated bounceIn">微&nbsp;&nbsp;信</a>
                    <a class="da-shang btn btn-rounded btn-noborder btn-lg btn-info animated bounceIn" data-img="${base}/assets/img/others/zhifubao-dengwenbing.jpg" data-toggle="appear" data-class="animated bounceIn">支付宝</a>
                </div>
                <!-- Section Content END -->
            </section>
        </div>

        <div class="bg-image" style="background-image: url('${base}/assets/img/photos/photo6@2x.jpg');">
            <div class="bg-primary-dark-op">
                <section class="content content-full content-boxed">
                    <!-- Section Content -->
                    <div class="row items-push-2x push-50-t text-center">
                        <div class="col-sm-4 animated fadeIn" data-toggle="appear" data-offset="-150">
                            <img class="img-avatar img-avatar-thumb" src="${base}/assets/img/others/dengwenbing.jpg" alt="">
                            <div class="h4 text-white-op push-10-t push-5">邓文兵</div>
                            <div class="h6 text-gray">全栈工程师</div>
                        </div>
                        <div class="col-sm-4 animated fadeIn" data-toggle="appear" data-offset="-150" data-timeout="150">
                            <img class="img-avatar img-avatar-thumb" src="${base}/assets/img/others/dengwenwu.jpg" alt="">
                            <div class="h4 text-white-op push-10-t push-5">邓文武</div>
                            <div class="h6 text-gray">全栈工程师</div>
                        </div>
                        <div class="col-sm-4 animated fadeIn" data-toggle="appear" data-offset="-150" data-timeout="150">
                            <img class="img-avatar img-avatar-thumb" src="${base}/assets/img/avatars/avatar14.jpg" alt="">
                            <div class="h4 text-white-op push-10-t push-5">沙发</div>
                            <div class="h6 text-gray">还等什么？！</div>
                        </div>
                    </div>
                    <!-- END Section Content -->
                </section>
            </div>
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
