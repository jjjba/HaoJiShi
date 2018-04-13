var baseUrl;
$(function () {
    baseUrl = $('#baseUrl').attr('href');
    /**
     * 高亮显示当前页面所属的菜单
     */
    // 选中导航条顶部当前选中菜单
    $('.navbar-collapse .navbar-nav:eq(0)ul li').each(function (index, item) {
        // 获取li元素
        var $item = $(item);
        // 获取li下的a元素
        var $a = $($item.find('a'));

        // 如果URL路径中包含当前li的href值，则设置为active样式
        if (location.pathname.indexOf($a.attr('href')) !== -1) {
            $item.addClass('active');
        }
    });

    //
    if ($('.navbar-collapse .navbar-nav:eq(0)ul li.active').size() === 0) {
        $('.navbar-collapse .navbar-nav:eq(0)ul li:eq(0)').addClass('active');
    }


});

(function ($) {
    $.extend({
        queryStr:
            function queryStr(data) {
                var str = "";
                $.each(data, function (k) {
                    if (str.length > 0)
                        str += '&';
                    str += k +'='+ (data[k]?data[k]:'');
                });
                return str;
            }
    })
})(jQuery);


function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}