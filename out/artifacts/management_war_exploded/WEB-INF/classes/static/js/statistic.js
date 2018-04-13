/**
 * Created by SongpoLiu on 2017/5/18.
 */
var page = 1;
var size = 10;

$(function () {
    //初始化日期插件
    $('#end_time').datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD',
        showClose: true,
        keepOpen: false
    });
    //列表头
    init_thead();

    // 绑定VIP版提示
    $('table thead span:eq(0)').popover({
        content: '<p>全自动微信收付款，一个店铺一个待奖励对列。</p>' +
        '<p>商户号属于商家，代理商为平台，代理费提现到平台，由平台财务结算。</p>' +
        '<p>单笔营业额2w以上，每日超20w增加小程序。</p>',
        html: true,
        placement: 'bottom',
        trigger: 'hover'
    });

    // 绑定多店版（含连锁店、加盟店）提示
    $('table thead span:eq(2)').popover({
        content: '<p>全自动微信收付款，连锁店：多个店铺一个待奖励队列，加盟店：多个店铺多个待奖励队列。</p>' +
        '<p>商户号属于商家，代理商为平台，代理费提现到平台，由平台财务结算。</p>',
        html: true,
        placement: 'bottom',
        trigger: 'hover'
    });

    // 绑定平台版提示
    $('table thead span:eq(4)').popover({
        content: '<p>全自动微信收付款，连锁店：多个店铺一个待奖励队列，加盟店：多个店铺多个待奖励队列。</p>' +
        '<p>商户号属于平台。</p>',
        html: true,
        placement: 'bottom',
        trigger: 'hover'
    });

    //search
    $('#search').on('click', function () {
        var time = $("#end_time>input[name='end_time']").val();
        loadData(time);
    });

    $('#end_time').data("DateTimePicker").date(moment().format('YYYY-MM-DD'));
    $('#search').trigger('click');
    $('#end_time').on('dp.change', function (e) {
        $('#search').trigger('click');
    });
    loadDateData();
});

function loadDateData() {
    var data = {"page": page, "size": size};
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "statistic/date-sys",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");

            if (res.success) {
                // 清空表格
                var lastTbody = $('tbody').last();
                lastTbody.empty();
                // 添加数据到表格
                $.each(res.data.list, function (index, item) {
                    var tr = '<tr><th scope="row">' + item.date + '</th>' +
                        '<td>' + item.total_fee + '</td>' +
                        '<td>' + item.shop_money + '</td>' +
                        '<td>' + item.agent_money + '</td>' +
                        '<td>' + item.servicer_money + '</td>' +
                        '<td>' + item.partner_money + '</td>' +
                        '<td>' + item.pool_money + '</td>' +
                        '<td>' + item.com_money + '</td>';

                    lastTbody.append(tr);
                });

                // 初始化分页控件
                $("#pagination").bs_pagination({
                    currentPage: res.data.pageNum,
                    totalRows: res.data.total,
                    rowsPerPage: res.data.pageSize,
                    totalPages: res.data.pages,
                    onChangePage: function (event, data) {
                        page = data.currentPage;
                        size = data.rowsPerPage;
                        loadDateData();
                    }
                });
            } else {
                $('tbody').empty();
                $("#pagination").empty();
                $("#pagination").removeClass("well");
                console.log('获取统计列表失败，请重试');
            }
        }
    });
}

/**
 * 加载数据
 *
 */
function loadData(time) {

    // 显示动画
    $.LoadingOverlay("show");
    var data = {"time": time};
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "statistic",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");

            if (res.success) {
                // 清空表格
                var firstTbody = $('tbody').first();
                //  var lastTbody = $('tbody').last();
                firstTbody.empty();
                // lastTbody.empty();
                // 添加数据到表格
                $.each(res.data, function (index, item) {
                    if (index.indexOf('t1_') != -1) {
                        if (index == 't1_aList') {
                            fillValue(firstTbody, item, '使用个数', 'gx_wechats_count');
                        } else if (index == 't1_uList') {
                            fillUserCount(firstTbody, item);
                        } else if (index == 't1_orderTotalList') {
                            fillValue(firstTbody, item, '总营业额', 'sales');
                        } else if (index == 't1_orderCurTimeList') {
                            fillValue(firstTbody, item, '日最高营业额', 'top_fee');

                        }
                    }
                    /*else {
                                           $.each(item, function (k, v) {
                                               if (k == 0) {
                                                   lastTbody.append('<tr>' + '<th scope="row">' + v + '</th>');
                                               } else {
                                                   lastTbody.find("tr").last().append('<td>' + v + '</td>')
                                               }
                                           });
                                       }*/
                });
            } else {
                $('tbody').empty();
                $("#pagination").empty();
                $("#pagination").removeClass("well");
                console.log('获取统计列表失败，请重试');
            }
        }
    });
}

function init_thead() {
    $thead = $('thead').eq(0);
    var tr = "<tr>";
    tr += "<th>统计项目\\发布版本</th>";
    $.each(DATA.applet_type, function (k) {
        tr += ('<th>' + this + '</th>')
    });

    tr += "</tr>";
    $thead.append(tr)
}

function fillValue(firstTbody, item, title, key) {
    firstTbody.append('<tr>' + '<th scope="row">' + title + '</th>');
    $.each(DATA.applet_type, function (k) {
        firstTbody.find("tr").last().append('<td>' + getValue(k, item, key) + '</td>')

    });
    firstTbody.append('</tr>');
}

function getValue(type, v, key) {
    for (var i = 0; i < v.length; i++) {
        var it = v[i];
        if (it.type == type)
            return it[key];

    }
    return 0;
}

function fillUserCount(firstTbody, item) {
    firstTbody.append('<tr>' + '<th scope="row">' + '总用户数' + '</th>');

    $.each(DATA.applet_type, function (k) {
        firstTbody.find("tr").last().append('<td>' + getUserCount(k, item) + '</td>')

    });
    firstTbody.append('</tr>');

    fillRoleCount(firstTbody, item, '消费者个数', 10);
    fillRoleCount(firstTbody, item, '商户个数', 20);
    fillRoleCount(firstTbody, item, '合伙人个数', 30);
    fillRoleCount(firstTbody, item, '服务商个数', 40);
    fillRoleCount(firstTbody, item, '代理商个数', 50);
}

function getUserCount(type, v) {
    var sum = 0;
    for (var i = 0; i < v.length; i++) {
        var it = v[i];
        if (it.type == type)
            sum += it.users_count;
    }
    return sum;
}

function fillRoleCount(firstTbody, item, title, role) {
    firstTbody.append('<tr>' + '<th scope="row">' + title + '</th>');
    $.each(DATA.applet_type, function (k) {
        firstTbody.find("tr").last().append('<td>' + getRoleCount(k, item, role) + '</td>')

    });
    firstTbody.append('</tr>');
}

function getRoleCount(type, v, role) {
    for (var i = 0; i < v.length; i++) {
        var it = v[i];
        if (it.type == type && it.role == role)
            return it.users_count;

    }
    return 0;
}