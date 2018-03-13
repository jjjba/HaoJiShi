/**
 * Created by xzcy-01 on 2017/12/8.
 */

/**
 * 定义下类型排序规则变量
 */
var typeSort;
/**
 * 定义总销售额排序规则变量
 */
var totalSalesAmountSort;

/**
 * 定义今日订单数排序规则变量
 */
var orderCountSort;


// 默认页码
var page = 1;

// 默认数量
var size = 10;

var user_id = 0;

$(function () {
    user_id = GetQueryString("user_id");
    $("#user_id").val(user_id);
//初始化日期插件
    $('#start_time').datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD',
        showClose: true,
        keepOpen: false
    });
    $('#end_time').datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD',
        showClose: true,
        keepOpen: false
    });
    // 加载数据
    loadData(page, size);

});//初始化


/**
 * 加载数据
 * @param page 页码
 * @param size 大小
 */
function loadData(page, size) {
    // 显示动画
    //   alert($('.navbar-form').find('[name=start_time]').val());
    $.LoadingOverlay("show");
    var data = {
        page: page,
        size: size,
        name: $('.navbar-form').find('[name=position_name]').val(),
        user_id: $('#user_id').val(),
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "position/list",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");

            if (res.success) {
                // 清空表格
                $('tbody').empty();
                // 添加数据到表格
                if (res.data != null) {
                    var list = res.data.list;
                    console.log(list);
                    var id, name, sex, age, phone, job_experience, state, info, create_time;
                    $.each(list, function (index, item) {
                        id = item.id;

                        var tableHtml = "";
                        tableHtml += '<tr>' +
                            '<td>' + item.positionName + '</td>' +
                            '<td>' + item.positionType + '</td>' +
                            '<td>' + item.sex + '</td>' +
                            '<td>' + item.age + '</td>' +
                            '<td>' + item.money + '</td>' +
                            '<td>' + item.experience + '</td>' +
                            '<td>' + item.positionInfo + '</td>' +
                            '<td>' + getMyDate(item.createTime) + '</td>';
                        tableHtml += '<td> <div class="btn-group">';
                        tableHtml += '</div></td></tr>';
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

                            loadData(data.currentPage, data.rowsPerPage);
                        }
                    });
                }

            } else {
                $('tbody').empty();
                $("#pagination").empty();
                $("#pagination").removeClass("well");
            }
        }
    });
}
function search() {
    loadData(page, size);
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}