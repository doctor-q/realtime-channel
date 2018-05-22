<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>侧栏</title>
</head>
<body>
<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div><!-- #sidebar-shortcuts -->

    <ul class="nav nav-list">
        <li>
            <a href="/reader/list">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> 输入列表 </span>
            </a>
        </li>
        <li>
            <a href="/filter/list">
                <i class="icon-laptop"></i>
                <span class="menu-text"> 过滤器列表 </span>
            </a>
        </li>
        <li>
            <a href="/trigger/list">
                <i class="icon-folder-open"></i>
                <span class="menu-text"> 输出列表 </span>
            </a>
        </li>

        <li>
            <a href="/about">
                <i class="icon-external-link"></i>
                <span class="menu-text"> 关于-帮助 </span>
            </a>
        </li>

    </ul><!-- /.nav-list -->

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>
</body>
</html>