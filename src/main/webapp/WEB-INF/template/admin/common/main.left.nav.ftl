<!-- Sidebar -->
<nav id="sidebar">
    <!-- Sidebar Scroll Container -->
    <div id="sidebar-scroll">
        <!-- Sidebar Content -->
        <!-- Adding .sidebar-mini-hide to an element will hide it when the sidebar is in mini mode -->
        <div class="sidebar-content">
            <!-- Side Header -->
            <div class="side-header side-content bg-white-op">
                <!-- Layout API, functionality initialized in App() -> uiLayoutApi() -->
                <button class="btn btn-link text-gray pull-right hidden-md hidden-lg" type="button" data-toggle="layout" data-action="sidebar_close">
                    <i class="fa fa-times"></i>
                </button>
                <a class="h5 text-white" href="main.html"><span class="h4 font-w600 sidebar-mini-hide">${setting.siteName}</span></a>
            </div>
            <!-- END Side Header -->

            <!-- Side Content -->
            <div class="side-content">
                <ul class="nav-main">
                    <li>
                        <a class="active" href="index.html"><i class="si si-speedometer"></i><span class="sidebar-mini-hide">控制面板</span></a>
                    </li>
                    <li class="nav-main-heading"><span class="sidebar-mini-hide">运营</span></li>
                    <li>
                        <a class="nav-submenu" data-toggle="nav-submenu" href="#"><i class="si si-book-open"></i><span class="sidebar-mini-hide">内容管理</span></a>
                        <ul>
                            <li>
                                <a href="../article/list.html" target="main-container-iframe">文章管理</a>
                                <a href="../article_category/list.html" target="main-container-iframe">分类管理</a>
                                <a href="../tag/list.html" target="main-container-iframe">标签管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a class="nav-submenu" data-toggle="nav-submenu" href="#"><i class="si si-badge"></i><span class="sidebar-mini-hide">运营管理</span></a>
                        <ul>
                            <li>
                                <a href="../navigation/list.html" target="main-container-iframe">导航管理</a>
                                <a href="../carousel_images/list.html" target="main-container-iframe">轮播图</a>
                                <a href="../friend_link/list.html" target="main-container-iframe">友情链接</a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-main-heading"><span class="sidebar-mini-hide">系统</span></li>
                    <li>
                        <a class="nav-submenu" data-toggle="nav-submenu" href="#"><i class="si si-wrench"></i><span class="sidebar-mini-hide">网站管理</span></a>
                        <ul>
                            <li><a href="../sys_setting/list.html" target="main-container-iframe">系统设置</a></li>
                            <li><a href="base_comp_images.html" target="main-container-iframe">消息管理</a></li>
                            <li><a href="../admin/list.html" target="main-container-iframe">管理员</a></li>
                            <li><a href="../role/list.html" target="main-container-iframe">角色管理</a></li>
                            <li><a href="../log/list.html" target="main-container-iframe">日志管理</a></li>
                            <li><a href="../template/list.html" target="main-container-iframe">模板管理</a></li>
                            <li><a href="../static/build.html" target="main-container-iframe">静态化管理</a></li>
                            <li><a href="../cache/clear.html" target="main-container-iframe">缓存管理</a></li>
                            <li><a href="${base}/druid/index.html" target="main-container-iframe">数据库监测</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- END Side Content -->
        </div>
        <!-- Sidebar Content -->
    </div>
    <!-- END Sidebar Scroll Container -->
</nav>
<!-- END Sidebar -->