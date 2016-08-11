/**
 * Created by dengwenwu on 2016-06-20.
 */

//初始化依赖包路径
initRequirePath();
require(['jquery','nprogress'],function($,NProgress){
    NProgress.start();
    require(['bootstrap','slimscroll','scrollLock','appear','placeholder','cookie'],function(bootstrap,slimscroll,scrollLock,appear,placeholder,cookie){
        require(['app','validate','bootstrap_notify','sweetalert'],function(app,validate,bootstrap_notify,sweetalert){
            require(['common','admin_commonexec'],function(common,admin_commonexec){
                var $type = $("#type");
                var $logo = $("#logo");
                $type.change(function(){
                    if ($(this).val() == "text") {
                        $("#logoBox").addClass('hidden');
                    } else {
                        $("#logoBox").removeClass('hidden');
                    }
                });

                /*var $inputForm = $("#inputForm");
                   $inputForm.validate({  //异步校验重名
                        rules: {
                            name: {
                                required: true,
                                 remote: {
                                            type: "post",
                                            url: g_base_path+"/admin/friend_link/checkName.html",
                                            data: {
                                                         name: function() {
                                                        return $("#name").val();
                                                    }
                                                 },
                                            dataType: "json",  //接受数据格式
                                          },
                                },
                    }
                });*/

                NProgress.done();
            });
        });
    })
});