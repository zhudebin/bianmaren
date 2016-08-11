/**
 * Created by dengwenbing on 2016-07-02.
 */

//初始化依赖包路径
initRequirePath();
require(['jquery','nprogress'],function($,NProgress){
    NProgress.start();
    require(['bootstrap','slimscroll','scrollLock','appear','placeholder','cookie'],function(bootstrap,slimscroll,scrollLock,appear,placeholder,cookie){
        require(['app','validate','bootstrap_notify','sweetalert'],function(app,validate,bootstrap_notify,sweetalert){
            require(['common','admin_commonexec','datepicker'],function(common,admin_commonexec,datepicker){

                var $inputForm = $("#inputForm");
                var $buildType = $("#buildType");
                var $articleCategoryTr = $("#articleCategoryTr");
                var $articleCategoryId = $("#articleCategoryId");
                var $productCategoryTr = $("#productCategoryTr");
                var $productCategoryId = $("#productCategoryId");
                var $beginDateTr = $("#beginDateTr");
                var $beginDate = $("#beginDate");
                var $endDateTr = $("#endDateTr");
                var $endDate = $("#endDate");
                var $count = $("#count");
                var $statusTr = $("#statusTr");
                var $countTr = $("#countTr");
                var $status = $("#status");
                var $submit = $(":submit");

                var first;
                var buildCount;
                var buildTime;
                var buildType;
                var articleCategoryId;
                var productCategoryId;
                var beginDate;
                var endDate;
                var count;

                $buildType.change(function() {
                    var $this = $(this);

                    if($this.val() == 'index'){
                        $countTr.addClass('hidden');
                    }else{
                        $countTr.removeClass('hidden');
                    }

                    if ($this.val() == "article") {
                        $articleCategoryTr.removeClass('hidden');
                        $productCategoryTr.addClass('hidden');
                        $beginDateTr.removeClass('hidden');
                        $endDateTr.removeClass('hidden');
                    } else if ($this.val() == "product") {
                        $articleCategoryTr.addClass('hidden');
                        $productCategoryTr.removeClass('hidden');
                        $beginDateTr.removeClass('hidden');
                        $endDateTr.removeClass('hidden');
                    } else {
                        $articleCategoryTr.addClass('hidden');
                        $productCategoryTr.addClass('hidden');
                        $beginDateTr.addClass('hidden');
                        $endDateTr.addClass('hidden');
                    }
                });


                /**
                 * 异步请求生成静态
                 */
                function build() {
                    var data = {buildType: buildType, articleCategoryId: articleCategoryId, productCategoryId: productCategoryId, beginDate: beginDate, endDate: endDate, first: first, count: count};
                    var url = "build.do";

                    //异步请求回调函数
                    var callback =  function(data) {
                        buildCount += data.buildCount;
                        buildTime += data.buildTime;
                        if (!data.isCompleted) {
                            if (buildType == "article" || buildType == "product") {
                                first = data.first;
                                $status.text("正在生成静态"+" [" + first + " - " + (first + count) + "]");
                            } else {
                                $status.text("正在生成静态");
                            }
                            build();
                        } else {
                            $buildType.prop("disabled", false);
                            $articleCategoryId.prop("disabled", false);
                            $productCategoryId.prop("disabled", false);
                            $beginDate.prop("disabled", false);
                            $endDate.prop("disabled", false);
                            $count.prop("disabled", false);
                            $submit.prop("disabled", false);
                            $statusTr.addClass('hidden');
                            $status.empty();
                            var time;
                            if (buildTime < 60000) {
                                time = (buildTime / 1000).toFixed(2) + '秒';
                            } else {
                                time = (buildTime / 60000).toFixed(2) + '分';
                            }
                            common.layer.msg("静态化成功 <div>数目: " + buildCount +"</div><div>静态化时间：" + time + "</div>");
                        }
                    }

                    //发生异步请求
                    common.postJSON(url,data,callback);

                }

                //提交
                $inputForm.submit( function() {

                    if(null == $count.val() || $count.val().length<1){
                        common.tips_warning("每次生成数字不能为空");
                        return false;
                    }

                    first = 0;
                    buildCount = 0;
                    buildTime = 0;
                    buildType = $buildType.val();
                    articleCategoryId = $articleCategoryId.val();
                    productCategoryId = $productCategoryId.val();
                    beginDate = $beginDate.val();
                    endDate = $endDate.val();
                    count = parseInt($count.val());
                    $buildType.prop("disabled", true);
                    $articleCategoryId.prop("disabled", true);
                    $productCategoryId.prop("disabled", true);
                    $beginDate.prop("disabled", true);
                    $endDate.prop("disabled", true);
                    $count.prop("disabled", true);
                    $submit.prop("disabled", true);
                    $statusTr.removeClass('hidden');

                    build();

                    return false;
                });

                NProgress.done();
            });
        });
    })
});