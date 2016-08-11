<!-- Header -->
<header id="header-navbar" class="content-mini content-mini-full">
    <div class="content-boxed">
        <!-- Header Navigation Right -->
        <ul class="nav-header pull-right">

            <li class="hidden-md hidden-lg">
                <!-- Toggle class helper (for main header navigation below in small screens), functionality initialized in App() -> uiToggleClass() -->
                <button class="btn-toggle btn btn-link pull-right" data-toggle="class-toggle" data-target=".js-nav-main-header" data-class="nav-main-header-o" type="button">
                    <i class="fa fa-navicon"></i>
                </button>
            </li>
        </ul>
        <!-- END Header Navigation Right -->

        <!-- Main Header Navigation -->
        <ul class="js-nav-main-header nav-main-header pull-right">
            <li class="text-right hidden-md hidden-lg">
                <!-- Toggle class helper (for main header navigation in small screens), functionality initialized in App() -> uiToggleClass() -->
                <button class="btn btn-link text-white" data-toggle="class-toggle" data-target=".js-nav-main-header" data-class="nav-main-header-o" type="button">
                    <i class="fa fa-times"></i>
                </button>
            </li>
            [@navigations]
                [#list navigations as nav]
                    [#if nav.navigationChildList?size>0]
                        <li>
                            <a class="nav-submenu text-white" href="javascript:void(0)">${nav.name}</a>
                            <ul>
                                [#list nav.navigationChildList as navChild]
                                    <li><a href="${navChild.url}">${navChild.name}</a></li>
                                [/#list]
                            </ul>
                        </li>
                    [#else]
                        <li><a class="text-white" href="${nav.url}">${nav.name}</a></li>
                    [/#if]
                [/#list]
            [/@navigations]
        </ul>
        <!-- END Main Header Navigation -->

        <!-- Header Navigation Left -->
        <ul class="nav-header pull-left">
            <li class="header-content">
                <a class="h5" href="${base}/">
                    ${setting.siteName}
                </a>
            </li>
        </ul>
        <!-- END Header Navigation Left -->
    </div>
</header>
<!-- END Header -->