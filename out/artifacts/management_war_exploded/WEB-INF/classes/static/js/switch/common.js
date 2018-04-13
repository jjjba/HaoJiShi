
$(function () {

    /**
     * 高亮显示当前页面所属的菜单
     */
    // 选中导航条左边当前选中菜单
    $('.navbar-fixed-top .navbar-collapse ul li:eq(1)').addClass('active');

    // 选中第一个小程序
    $('.navbar-fixed-side .navbar-collapse ul li').each(function (index, item) {
        // 获取li元素
        var $item = $(item);
        // 获取li下的a元素
        var $a = $($item.find('a'));

        // 如果URL路径中包含当前li的href值，则设置为active样式
        if (location.pathname.indexOf($a.attr('href')) !== -1) {
            $item.addClass('active');
        }
    });
});