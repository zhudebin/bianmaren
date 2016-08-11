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

                var $beforepassword = $(".beforepassword");
                var $newPassword = $(".newPassword");
                var $ConfirmPassword = $(".ConfirmPassword");

                $newPassword.blur(function(){
                   var beforepassword =   $beforepassword.val();
                    var newPassword =   $newPassword.val();
                    if(beforepassword==newPassword){
                        common.layer.msg("原密码和新密码不能一致");
                        return;
                   }
                  /*  var api = g_diange.base + '/admin/orderlog/ajaxDeleteLogInfo.do';
                    var data_param = {
                        "id":id,
                    };
                    common.confirm("是否删除系统日志？",function(){
                        $parent.remove();
                        common.postJSON(api,data_param,function(msg){
                            var index = common.layer.load();
                            if(msg.type != 'success'){
                                common.layer.msg(msg.content);
                            }else{
                                common.layer.msg("删除成功");
                            }
                            layer.close(index);
                        });
                    })
*/
                });
                $ConfirmPassword.blur(function(){
                    var ConfirmPassword =   $ConfirmPassword.val();
                    var newPassword =   $newPassword.val();
                    if(ConfirmPassword!=newPassword){
                        common.layer.msg("新密码和确认密码要一致");
                        return;
                    }
                });
                NProgress.done();
            });
        });
    })
});