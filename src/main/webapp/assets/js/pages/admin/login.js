//初始化依赖包路径
initRequirePath();

require(['jquery','nprogress'],function($,NProgress){
    NProgress.start();
    require(['bootstrap','slimscroll','scrollLock','appear','placeholder','cookie'],function(bootstrap,slimscroll,scrollLock,appear,placeholder,cookie){
        require(['app','validate','bootstrap_notify','sweetalert'],function(app,validate,bootstrap_notify,sweetalert){
            require(['base64'],function(base64){
                require(['jsbn'],function(jsbn){
                    require(['prng4'],function(prng4){
                        require(['rng'],function(rng){
                            require(['rsa'],function(rsa){
                                require(['common','admin_commonexec'],function(common,admin_commonexec){
                                    var $loginForm = $("#loginForm");
                                    var $submit = $("#submit");
                                    var $img_captcha = $("#img_captcha");
                                    var $enPassword = $("#enPassword");

                                    //输入校验
                                    jQuery('.js-validation-login').validate({
                                        errorClass: 'help-block text-right animated fadeInDown',
                                        errorElement: 'div',
                                        errorPlacement: function(error, e) {
                                            jQuery(e).parents('.form-group > div').append(error);
                                        },
                                        highlight: function(e) {
                                            jQuery(e).closest('.form-group').removeClass('has-error').addClass('has-error');
                                            jQuery(e).closest('.help-block').remove();
                                        },
                                        success: function(e) {
                                            jQuery(e).closest('.form-group').removeClass('has-error');
                                            jQuery(e).closest('.help-block').remove();
                                        },
                                        rules: {
                                            'username': {
                                                required: true,
                                                minlength: 3
                                            },
                                            'enPassword': {
                                                required: true,
                                                minlength: 5
                                            },
                                            'captcha': {
                                                required: true
                                            }
                                        },
                                        messages: {
                                            'username': {
                                                required: '请输入用户名',
                                                minlength: '最少长度是3'
                                            },
                                            'enPassword': {
                                                required: '请输入密码',
                                                minlength: '最少长度是5'
                                            },
                                            'captcha': {
                                                required: '请输入验证码'
                                            }
                                        }
                                    });


                                    //绑定点击换验证码事件
                                    $img_captcha.click(function(){
                                        var timestamp = new Date().getTime();
                                        $img_captcha.attr("src", g_base_path+"/kaptcha/captcha.jpg?captchaId=<%=captchaId%>&timestamp=" + timestamp);
                                    })

                                    // 登录之前将密码加密
                                    $submit.click(function(){
                                        var $password = $enPassword;
                                        var rsaKey = new RSAKey();
                                        rsaKey.setPublic(b64tohex(ras_modulus), b64tohex(ras_exponent));
                                        var enPassword = hex2b64(rsaKey.encrypt($password.val()));
                                        $password.val(enPassword);
                                        $loginForm.submit();
                                    })
                                    /**
                                     * 显示登录错误信心
                                     */
                                    common.showTipsMessage(loginMessage);

                                    $submit.removeClass("hidden");

                                    NProgress.done();
                                 });
                               });
                            });
                        });
                    });
                 });
            });
     })
});



