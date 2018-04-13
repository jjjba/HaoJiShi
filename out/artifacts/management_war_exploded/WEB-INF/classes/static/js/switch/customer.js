/**
 * Created by SongpoLiu on 2017/5/17.
 */

// 默认页码
var page = 1;

// 默认数量
var size = 10;

/** 定义acidNameList */
var acidNamesList;

/** 定义当前acid */
var curAcid;

var uid;
$(function () {
    //初始化日期插件
    $('#time_begin').datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD',
        showClose: true,
        keepOpen: false
    });
    $('#time_end').datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD',
        showClose: true,
        keepOpen: false
    });
    // 加载小程序列表

    var $acidShopList = $('.navbar-form').find('select:eq(0)').empty();
    loadApplets($acidShopList);
    //小程序 acid的值
    curAcid = $('#acidList').val();
    // 绑定切换小程序事件
    // $('.navbar-form').on('change', 'select', function () {
    //     page = 1;
    //     loadData();
    // });

    // 触发切换小程序事件
    $('.navbar-form select').trigger('change');

    // 绑定全选切换事件
    $('table thead tr th input').on('change', function () {
        $('table tbody th input').prop('checked', $(this).prop('checked'));
    });

    // 绑定详情模态框事件
    $('#viewModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the model
        var uid = button.data('uid'); // Extract info from data-* attributes
        // Update the model's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var $modal = $(this);
        var acid = button.data('acid');
        // console.log(acid);
        $.getJSON($('#baseUrl').attr('href') + 'customers/id?id=' + uid + '&acid=' + acid, function (res) {
            if (res.success) {
                var user = res.data;
                $modal.find('[name=nickname]').val(user.nickname);
                $modal.find('[name=role]').val(user.role);
                $modal.find('[name=status]').val(user.status);
                $modal.find('[name=cash]').val(user.cash);
                $modal.find('[name=agent_rate]').val(user.agent_rate);
                // $modal.find('[name=cash2]').val(user.cash2);
                $modal.find('[name=backing_money]').val(user.backing_money);
                $modal.find('[name=backed_money]').val(user.backed_money);
                $modal.find('[name=cash_out]').val(user.cash_out);
                $modal.find('[name=realname]').val(user.realname);
                $modal.find('[name=mobile]').val(user.mobile);
                $modal.find('[name=remark]').val(user.remark);
                /*if(user.role==50){
                 $('#agent_rate_div').show();
                 }else{
                 $('#agent_rate_div').hide();
                 }*/
            }
        });
    });


    // 绑定查询按钮点击事件
    $('.navbar-form .btn-default').click(function () {
        loadData();
    });

    //点击导出按钮，导出Excel
    $('a.btn.btn-info').on('click', function () {
        excelExport();
    });
});

/**
 * 加载数据
 * @param page 页码
 * @param size 大小
 */
function loadData() {
    //var data = {page: page, size: size, acid: $('.navbar-form').find('select').val()};
    var baseUrl = $('#baseUrl').attr('href');
    var data = getColumns(true);
    if (!data.acid || data.acid.length == 0 || data.acid == 0) {
        return;
    }
    // 显示动画
    $.LoadingOverlay("show");

    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "customers",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            var editAble = $("input[name=editAble]").val();
            if (res.success) {
                // 清空表格
                $('tbody').empty();
                var editAble = $("input[name=editAble]").val();
                // 添加数据到表格
                $.each(res.data.list, function (index, item) {
                    var tableHtml = "";

                    tableHtml += '<tr>' +
                        '<td>' + item.uid + '</td>' +
                        '<td><img src="' + item.avatar + '" height="30px;" width="30px;">' + item.nickname + '</td>' +
                        '<td>' + DATA.role[item.role] + '<br>';
                    //状态异常 为红字显示
                    if (item.status == 0) {
                        tableHtml += '<td><p style="color: red">' + DATA.memberstatus[item.status] + '</p></td>';
                    } else {
                        tableHtml += '<td>' + DATA.memberstatus[item.status] + '</td>';
                    }


                    tableHtml += '<td>' + item.cash + '</td>' +
                        '<td>' + item.backed_money + '</td>' +
                        '<td>' + item.backing_money + '</td>' +
                        '<td>' + item.cash_out + '</td>' +
                        '<td>' + item.score + '</td>' +
                        '<td>' + item.realname + '</td>' +
                        '<td>' + item.mobile + '</td>' +
                        '<td>' + $.myTime.UnixToDate(item.create_time, true, 8) + '</td>' +
                        '<td>' + item.remark + '</td>' +
                        '<td>' +
                        '<div class="btn-group">';
                    if (editAble) {
                        if (item.uid != 0)
                            tableHtml +=
                                '<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#agentModal" data-uid="'
                                + item.uid + '" data-role="' + item.role + '">' + (item.role === 50 ? '修改' : '修改') + '</button>';

                        tableHtml +=
                            (('<button class="btn btn-sm btn-primary" data-toggle="modal" ' +
                            'data-target="#passwordModal" data-uid="' + item.uid +
                            '" data-fanid="' + item.fanid +  '" data-remark="' + item.remark+
                        '" data-realname="' + item.realname + '" data-mobile="' + item.mobile + '">' + '设置账号' + '</button>'));
                    }
                    tableHtml +=
                        '<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal" data-uid="' + item.uid + '" data-status="' + item.status + '" data-remark="' + item.remark + '">状态</button>' +
                        '<a class="btn btn-sm btn-primary" style="color: white" href="' + baseUrl + 'orders/custom/' + item.uid + '/' + item.acid + '" target="_blank">订单</a>' +
                        '<a class="btn btn-sm btn-primary" style="color: white" href="' + baseUrl + 'pay/custom/' + item.uid + '/' + item.acid + '" target="_blank">提现记录</a>' +
                        '</div></td>' +
                        '</tr>';
                    $('tbody').append(tableHtml);

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
                        loadData();
                    }
                });

            } else {
                $('tbody').empty();
                $("#pagination").empty();
                $("#pagination").removeClass("well");
            }
        }
    });
}
function getColumns(p) {
    timeBegin = $("#time_begin>input[name='time_begin']").val();
    timeBegin = $.myTime.DateToUnix(timeBegin);
    timeEnd = $("#time_end>input[name='time_end']").val();
    timeEnd = $.myTime.DateToUnix(timeEnd);
    if (timeBegin < 0) timeBegin = null;

    if (timeEnd > 0) timeEnd += 86400;
    else timeEnd = null;


    var data = { join: $('input[name=join]').val(),
        acid: $('.navbar-form').find('[name=acids]').val(),
        role: $('.navbar-form').find('[name=role]').val(),
        nickname: $('.navbar-form').find('[name=nickname]').val(),
        mobile: $('.navbar-form').find('[name=mobile]').val(),
        startTime: timeBegin,
        endTime: timeEnd
    };
    if (p) {
        data.page = page;
        data.size = size;
    }
    return data;

}
function fillelectRegion(pid, type) {
    //市
    if (type == 1) {
        $("select[name='city']").empty();
        $("select[name='city']").append("<option value=''>请选择</option>");
        $.getJSON($('#baseUrl').attr('href') + 'region?pid=' + pid, function (res) {
            if (res.success) {
                // console.info(res.data);

                var options = "";
                $.each(res.data, function (index, item) {
                    options += "<option value='" + item.id + "'>" + item.name + "</option>";
                });
                $("select[name='city']").append(options);
            }
        });
    } else {
        $("select[name='region']").empty();
        $("select[name='region']").append("<option value=''>请选择</option>");
        $.getJSON($('#baseUrl').attr('href') + 'region?pid=' + pid, function (res) {
            if (res.success) {
                //  console.info(res.data);
                var options = "";
                $.each(res.data, function (index, item) {
                    options += "<option value='" + item.id + "'>" + item.name + "</option>";
                });
                $("select[name='region']").append(options);
            }
        });
    }
}


/**
 * 选中当前
 */
function cur_selected(acid) {
    if (!acid) {
        localStorage.acid = $('#acidList').val();
    } else {
        curAcid = acid;
    }
    loadData();
}

//导出Excel
function excelExport() {
    if ($('tbody td').size() == 0) {
        alert("没有数据不能导出！");
        return;
    }
    if ($('tbody td').size() == 0) {
        alert("没有数据不能导出！");
        return;
    }
    var data = getColumns(false);
    window.location.href = $('#baseUrl').attr('href') + "customers/export?" + $.queryStr(data);
}