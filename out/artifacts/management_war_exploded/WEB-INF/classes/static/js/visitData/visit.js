/**
 * @author 梁闯
 * @date 2018/03/15 19.43
 */

// 默认页码
var page = 1;

// 默认数量
var size = 10;

$(function () {

    $('#dateTimeRange').daterangepicker({
        applyClass : 'btn-sm btn-success',
        cancelClass : 'btn-sm btn-default',
        locale: {
            applyLabel: '确认',
            cancelLabel: '取消',
            fromLabel : '起始时间',
            toLabel : '结束时间',
            customRangeLabel : '自定义',
            firstDay : 1
        },
        ranges : {
            '最近1小时': [moment().subtract('hours',1), moment()],
            '今日': [moment().startOf('day'), moment()],
            '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
            '最近7日': [moment().subtract('days', 6), moment()],
            '最近30日': [moment().subtract('days', 29), moment()],
            '本月': [moment().startOf("month"),moment().endOf("month")],
            '上个月': [moment().subtract(1,"month").startOf("month"),moment().subtract(1,"month").endOf("month")]
        },
        opens : 'right',	// 日期选择框的弹出位置
        separator : ' 至 ',
        showWeekNumbers : true,		// 是否显示第几周
        //timePicker: true,
        //timePickerIncrement : 10,	// 时间的增量，单位为分钟
        //timePicker12Hour : false,	// 是否使用12小时制来显示时间
        //maxDate : moment(),			// 最大时间
        format: 'YYYY-MM-DD'
    }, function(start, end, label) { // 格式化日期显示框
        $('#beginTime').val(start.format('YYYY-MM-DD'));
        $('#endTime').val(end.format('YYYY-MM-DD'));
    }).next().on('click', function(){
        $(this).prev().focus();
    });

    // 加载数据
    loadData(page, size);




});//初始化




/**
 * 加载数据
 * @param page 页码
 * @param size 大小
 */
function loadData() {
    $.LoadingOverlay("show");
    var data = {

        beginTime: $('#beginTime').val(),
        endTime: $('#endTime').val(),
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "visits/getVisitsDate",
        data: data,
        success: function (res) {
            $.LoadingOverlay("hide");
            if (res.success) {
                $('thead').empty();
                if (res.data != null) {
                    var list = res.data;

                    var ptfwcs,ptfwrs,ykfwcs,ykfwrs,qyfwcs,qyfwrs,grfwcs,grfwrs,fxcs,fxrs;

                    ptfwcs = list.ptfwcs;
                    if(ptfwcs == null || ptfwcs ==""){
                        ptfwcs ="--"
                    }
                    ptfwrs = list.ptfwrs;
                    console.log(ptfwcs);
                    if(ptfwrs == null || ptfwrs ==""){
                        ptfwrs ="--"
                    }

                    ykfwcs = list.ykfwcs;
                    if(ykfwcs == null || ykfwcs ==""){
                        ykfwcs ="--"
                    }
                    ykfwrs = list.ykfwrs;
                    console.log(ptfwcs);
                    if(ykfwrs == null || ykfwrs ==""){
                        ykfwrs ="--"
                    }

                    qyfwcs = list.qyfwcs;
                    if(qyfwcs == null || qyfwcs ==""){
                        qyfwcs ="--"
                    }
                    qyfwrs = list.qyfwrs;
                    console.log(ptfwcs);
                    if(qyfwrs == null || qyfwrs ==""){
                        qyfwrs ="--"
                    }

                    grfwcs = list.grfwcs;
                    if(grfwcs == null || grfwcs ==""){
                        grfwcs ="--"
                    }
                    grfwrs = list.grfwrs;
                    console.log(grfwrs);
                    if(grfwrs == null || grfwrs ==""){
                        grfwrs ="--"
                    }

                    fxcs = list.fxcs;
                    if(fxcs == null || fxcs ==""){
                        fxcs ="--"
                    }
                    fxrs = list.fxrs;
                    console.log(fxrs);
                    if(fxrs == null || fxrs ==""){
                        fxrs ="--"
                    }

                    var tableHtml = "";
                    tableHtml += '<tr>' +
                        '<td>平台访问人数</td>'+
                        '<td>' + ptfwrs + '</td></tr>' +
                        '<tr><td>平台访问次数</td>' +
                        '<td>' + ptfwcs + '</td></tr>' +
                        '<tr><td>游客访问人数</td>' +
                        '<td>' + ykfwrs + '</td></tr>' +
                        '<tr><td>游客访问次数</td>' +
                        '<td>' + ykfwcs + '</td></tr>' +
                        '<tr><td>企业访问人数</td>' +
                        '<td>' + qyfwrs + '</td></tr>' +
                        '<tr><td>企业访问次数</td>' +
                        '<td>' + qyfwcs + '</td></tr>' +
                        '<tr><td>个人访问人数</td>' +
                        '<td>' + grfwrs + '</td></tr>' +
                        '<tr><td>个人访问次数</td>' +
                        '<td>' + grfwcs + '</td></tr>' +
                        '<tr><td>分享人数</td>' +
                        '<td>' + fxrs + '</td></tr>' +
                        '<tr><td>分享次数</td>' +
                        '<td>' + fxcs + '</td></tr>' ;

                    $('thead').append(tableHtml);
                }

            } else {
                $('thead').empty();
                $("#pagination").empty();
                $("#pagination").removeClass("well");
            }
        }
    });
}

function search() {
    loadData();
}

/**
 * 清除时间
 */
function begin_end_time_clear() {
    $('#dateTimeRange').val('');
    $('#beginTime').val('');
    $('#endTime').val('');
}
