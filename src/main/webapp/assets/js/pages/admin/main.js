
//初始化依赖包路径
initRequirePath();

require(['jquery','nprogress'],function($,NProgress){
    NProgress.start();
    require(['bootstrap','slimscroll','scrollLock','appear','placeholder','cookie'],function(bootstrap,slimscroll,scrollLock,appear,placeholder,cookie){
        require(['app','validate','bootstrap_notify','sweetalert'],function(app,validate,bootstrap_notify,sweetalert){
            require(['common','admin_commonexec'],function(common,admin_commonexec){

                App.initHelpers('slick');

                NProgress.done();
            });
        });
    })
});



