<!-- Footer -->
<footer id="page-footer" class="bg-gray-lighter">
    <div class="content content-boxed">
        <!-- Footer Navigation -->
        <div class="row push-30-t items-push-2x">
            <div class="col-sm-9">
                <h3 class="h5 font-w600 text-uppercase push-20">友情链接</h3>
                <ul class="list list-simple-mini font-s13">
                    [@friend_link]
                        [#list friendLinks as link]
                            <a class="font-w400 push-10-r" target="_blank" href="${link.url}">${link.name}</a>
                        [/#list]
                    [/@friend_link]

                </ul>
            </div>
            <div class="col-sm-3">
                <h3 class="h5 font-w600 text-uppercase push-20">联系我们</h3>
                <div class="font-s13 push">
                    联系人 ： 邓文兵<br>
                    电话 ： 18770090755<br>
                    QQ ： 441889070<br>
                </div>
                <div class="font-s13">
                    <i class="si si-envelope-open"></i> 441889070@qq.com
                </div>
            </div>
        </div>
        <!-- END Footer Navigation -->

        <!-- Copyright Info -->
        <div class="font-s12 push-20 clearfix">
            <hr class="remove-margin-t">
            <div class="pull-right">
                赣ICP备14010443号
            </div>
            <div class="pull-left">
                <a class="font-w600" href="${setting.siteUrl}" target="_blank">${setting.siteName}</a> &copy; <span class="js-year-copy"></span>
            </div>
        </div>
        <!-- END Copyright Info -->
    </div>
</footer>
<!-- END Footer -->