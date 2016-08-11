<script type="text/javascript">

    //全局变量
    var g_base_path = "${base}";
    var g_locale = "${locale}";

    //全局设置
    var g_setting = {
    };

    //JS依赖路径设置
    function initRequirePath(){
        if("undefined" != typeof require){
            require.config({
                baseUrl: g_base_path +'/assets',
                paths: {
                    //核心依赖
                    jquery:'js/core/jquery.min',
                    bootstrap:'js/core/bootstrap.min',
                    slimscroll:'js/core/jquery.slimscroll.min',
                    scrollLock:'js/core/jquery.scrollLock.min',
                    appear:'js/core/jquery.appear.min',
                    countTo:'js/core/jquery.countTo.min',
                    placeholder:'js/core/jquery.placeholder.min',
                    cookie:'js/core/js.cookie.min',
                    app:'js/app',
                    oneui:'js/oneui.min',

                    //插件
                    validate:'js/plugins/jquery-validation/jquery.validate.min',
                    bootstrap_notify:'js/plugins/bootstrap-notify/bootstrap-notify.min',
                    sweetalert:'js/plugins/sweetalert/sweetalert.min',
                    jQueryForm:'js/plugins/jQueryForm/jquery.form',
                    layer:'js/plugins/layer-v2.1/layer',
                    table2excel: 'js/plugins/table2excel/jquery.table2excel.min',
                    nprogress: 'js/plugins/nprogress-master/nprogress',
                    slick: 'js/plugins/slick/slick.min',
                    ias: 'js/plugins/ias/jquery-ias.min',
                    highlightjs: 'js/plugins/highlightjs/highlight.min',

                    //通用模块
                    common:'js/common',
                    commonexec:'js/pages/web/commonexec',

                    //加密相关依赖
                    base64:'js/plugins/rsa/base64',
                    jsbn:'js/plugins/rsa/jsbn',
                    prng4:'js/plugins/rsa/prng4',
                    rng:'js/plugins/rsa/rng',
                    rsa:'js/plugins/rsa/rsa',
                }
            });
        }
    }

</script>