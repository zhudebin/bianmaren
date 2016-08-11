/**
 * Created by dengwenbing on 2016-07-21.
 */
initRequirePath();

require(['jquery'],function($){
    require(['common','commonexec'],function(common){
        require(['slick'],function(slick){

            $("#topBox").css("background-image", "url('"+g_base_path+"/assets/img/photos/photo"+parseInt(Math.random()*(1-27+1)+27)+"@2x.jpg')");

            var g_pageSize = 12; //每页多少个
            var g_total = 0; //总共多少记录
            var g_totalPages = 0; //总共多少页
            var g_pageNumber = 1; //当前第几页
            var g_timeTask;

            var $articleBox = $("#articleBox");

            var articleTmpl = '<div class=" post col-md-3 animated fadeIn" data-toggle="appear" data-offset="50" data-class="animated fadeIn">'
                                +'<a class="block block-link-hover2" href="{{url}}" target="_blank">'
                                    +'<div class="block-content block-content-full text-center bg-image" style="background-image: url({{img}}); height: 150px;background-color: #f9f9f9;background-position: center center; -webkit-background-size: cover;background-size: cover;"></div>'
                                    +'<div class="block-content">'
                                         +'<div class="font-s12 push">'
                                                +'<em class="pull-right hidden">{{hits}} 点击</em>'
                                                +'<span class="text-primary">{{author}}</span> 发布于 {{date}}'
                                         +'</div>'
                                         +'<h5 class="push-10" style="height:20px; overflow:hidden">{{title}}</h5>'
                                         +'<p style="min-height: 75px; word-break:break-all; word-wrap:break-word;">{{content}}</p>'
                                    +'</div>'
                                  +'</a>'
                                +'</div>';


            /**
             * 分页获取数据
             * @param pageNumber 获取哪一页的数据
             * @param pageSize 每页多少个
             * @param callback 成功后的回调函数
             */
            function getPageData(pageNumber,pageSize,callback){
                $(".myloading").removeClass("hidden");
                var api = g_base_path + '/article/ajaxLoadArticle.do';
                var data = {
                    "pageNumber":pageNumber,
                    "pageSize":pageSize
                }
                common.postJSON(api,data,function(res){
                    g_total = res.total;
                    g_totalPages = res.totalPages;
                    g_pageNumber = res.pageNumber;
                    showArticle(res.content);
                    callback(res);

                    $(".myloading").addClass("hidden");
                });
            }

            getPageData(g_pageNumber,g_pageSize,function(res){
                if(res.pageNumber >= res.totalPages){
                    $(".mynomore").removeClass("hidden")
                }else{
                    g_timeTask= setTimeout(_cleckReached,100);
                }
            });

            /**
             * 显示文章
             * @param articles
             */
            function showArticle(articles){
                var html = '';
                $(articles).each(function(index,item){

                    var img = g_base_path + '/assets/img/question.jpg';
                    if(null != item.headImg && item.headImg.length >1){
                        img = item.headImg;
                    }

                    var tmp = articleTmpl.replace(/{{title}}/g,common.getString(item.title,25))
                        .replace(/{{content}}/g,common.getString(item.content,45))
                        .replace(/{{author}}/g,common.getString(item.admin.username,20))
                        .replace(/{{hits}}/g,item.hits)
                        .replace(/{{img}}/g,img)
                        .replace(/{{date}}/g,common.jsDateDiff(item.createDate/1000))
                        .replace(/{{url}}/g,g_base_path+item.path);

                        html += tmp;

                });
                $articleBox.append(html);
            }

            var _levelReached = function(){
                var pageHeight = Math.max(document.body.scrollHeight ||
                    document.body.offsetHeight);
                var viewportHeight = window.innerHeight  ||
                    document.documentElement.clientHeight  ||
                    document.body.clientHeight || 0;
                var scrollHeight = window.pageYOffset ||
                    document.documentElement.scrollTop  ||
                    document.body.scrollTop || 0;
                return pageHeight - viewportHeight - scrollHeight < 50;
            };

            /**
             * 检验是否到达了底部
             * @private
             */
            function _cleckReached(){
                if(_levelReached()){
                    clearTimeout(g_timeTask);

                    getPageData(g_pageNumber+1,g_pageSize,function(res){
                        if(res.pageNumber >= res.totalPages){
                            $(".mynomore").removeClass("hidden")
                        }else{
                            g_timeTask= setTimeout(_cleckReached,100);
                        }
                    });
                }else{
                    g_timeTask= setTimeout(_cleckReached,100)
                }
            }

        });

    });
});