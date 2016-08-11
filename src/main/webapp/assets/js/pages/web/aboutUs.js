/**
 * Created by dengwenbing on 2016-07-23.
 */
initRequirePath();

require(['jquery'],function($){
    require(['common','commonexec','sweetalert'],function(common,commonexec,sweetalert){

        $(".da-shang").click(function(){
            var img = $(this).attr("data-img");
            swal({   title: "<img src='"+img+"'  style='max-width: 150px' />",   text: "谢谢土豪! 有你们的支持，我们才能做的更好",  html: true });
        });

    });
});