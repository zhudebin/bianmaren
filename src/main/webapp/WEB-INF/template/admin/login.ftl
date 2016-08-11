<!DOCTYPE html>
<!--[if IE 9]><html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>管理员登录</title>
    <meta name="description" content="DGCMS">
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
<!-- Login Content -->
<div class="bg-white pulldown">
    <div class="content content-boxed overflow-hidden">
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
                <div class="push-30-t push-50 animated fadeIn">
                    <!-- Login Title -->
                    <div class="text-center">
                        <p class="text-muted push-15-t">管理员登录</p>
                    </div>
                    <!-- END Login Title -->

                    <!-- Login Form -->
                    <form class="js-validation-login form-horizontal push-30-t" action="${base}/admin/common/login.html" method="post" id="loginForm">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="form-material form-material-primary">
                                    <input class="form-control" type="text" id="username" name="username" placeholder="请输入用户名">
                                    <label for="login-username">用户名</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="form-material form-material-primary">
                                    <input class="form-control valid" type="password" id="enPassword" name="enPassword" aria-required="true" aria-invalid="false" aria-describedby="login-password-error" placeholder="请输入密码">
                                    <label for="login-password">密码</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-9">
                                <div class="form-material form-material-primary">
                                    <input class="form-control" type="text" id="captcha" name="captcha" placeholder="请输入验证码">
                                    <label for="login-password">验证码</label>
                                </div>
                            </div>
                            <div class="col-xs-3">
                                <div class="row" style="margin-right: 0px;">
                                    <img style="width: 100%;" alt="验证码" src="${base}/kaptcha/captcha.jpg" id="img_captcha" data-toggle="tooltip" data-placement="right" title="点击更换" />
								</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-6">
                                <label class="css-input switch switch-sm switch-primary">
                                    <input type="checkbox" id="rememberMe" name="rememberMe"><span></span> 记住我 ?
                                </label>
                            </div>
                            <div class="col-xs-6">
                                <div class="font-s13 text-right push-5-t">
                                    <a href="#">忘记密码 ?</a>
                                </div>
                            </div>
                        </div>
                        <div class="form-group push-30-t">
                            <div class="col-xs-12 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                                <a class="btn btn-sm btn-block btn-primary hidden"  id="submit" name="submit">登录</a>
                            </div>
                        </div>
                    </form>
                    <!-- END Login Form -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END Login Content -->

<!-- Login Footer -->
<div class="pulldown push-30-t text-center animated fadeInUp">
    <small class="text-muted"><span class="js-year-copy"></span> &copy; ${setting.siteName}</small>
</div>
<!-- END Login Footer -->


<!-- Page JS Code -->
[#include "/admin/include/js/common.ftl" /]
<script>
    var ras_modulus = '${ras_modulus}';
    var ras_exponent = '${ras_exponent}';
    var loginMessage = {
        'type':'${loginMessage.type!""}',
        'content':'${loginMessage.content!""}'
    };
</script>

<script data-main="${base}/assets/js/pages/admin/login.js" src="${base}/assets/js/core/require.js" defer="defer" async="async"></script>
</body>
</html>