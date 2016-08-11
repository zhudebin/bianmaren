<!DOCTYPE html>
<!--[if IE 9]>         <html class="ie9 no-focus"> <![endif]-->
<!--[if gt IE 9]><!--> <html class="no-focus"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>主页</title>

    <meta name="author" content="bianmaren">
    <meta name="robots" content="noindex, nofollow">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">

    <!-- Stylesheets -->
    <!-- Bootstrap and OneUI CSS framework -->
    <link rel="stylesheet" href="${base}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/css/oneui.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/nprogress-master/nprogress.css">
    <link rel="stylesheet" id="css-main" href="${base}/assets/js/plugins/layer-v2.1/skin/layer.css">

</head>
<body>
    <!-- Page Header -->
    <div class="content bg-image overflow-hidden" style="background-image: url('${base}/assets/img/photos/photo3@2x.jpg');">
        <div class="push-50-t push-15">
            <h1 class="h2 text-white animated zoomIn">控制面板</h1>
            <h2 class="h5 text-white-op animated zoomIn">欢迎
                [${current_admin_role()}][@shiro.user][@shiro.principal/][/@shiro.user]
            </h2>
        </div>
    </div>
    <!-- END Page Header -->

    <div class="block">
        <div class="block-content">
            <table class="table table-striped table-borderless ">
                <tbody>
                    <tr>
                        <td>系统名称</td>
                        <td>${systemName}</td>
                    </tr>
                    <tr>
                        <td>官方网站</td>
                        <td><a href="${officialSite }" target="_blank">${officialSite }</a></td>
                    </tr>
                    <tr>
                        <td>JAVA版本</td>
                        <td>${javaVersion}</td>
                    </tr>
                    <tr>
                        <td>JAVA路径</td>
                        <td>${javaHome}</td>
                    </tr>
                    <tr>
                        <td>操作系统名称</td>
                        <td>${osName}</td>
                    </tr>
                    <tr>
                        <td>操作系统构架</td>
                        <td>${osArch}</td>
                    </tr>
                    <tr>
                        <td>Servlet信息</td>
                        <td>${serverInfo}</td>
                    </tr>
                    <tr>
                        <td>Servlet版本</td>
                        <td>${servletVersion}</td>
                    </tr>
                    <tr>
                        <td>JVM可以使用的总内存</td>
                        <td>${JVMTotalMemory/1024/1024} MB</td>
                    </tr>
                    <tr>
                        <td>JVM可以使用的剩余内存</td>
                        <td>${JVMFreeMemory/1024/1024} MB</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>


    [#include "/admin/include/js/common.ftl" /]
</body>
</html>


