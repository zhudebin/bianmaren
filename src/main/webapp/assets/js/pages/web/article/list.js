/**
 * Created by dengwenbing on 2016-07-22.
 */
initRequirePath();

require(['jquery'],function($){
    require(['common','commonexec'],function(common){

        $("#topBox").css("background-image", "url('"+g_base_path+"/assets/img/photos/photo"+parseInt(Math.random()*(1-27+1)+27)+"@2x.jpg')");

    });
});