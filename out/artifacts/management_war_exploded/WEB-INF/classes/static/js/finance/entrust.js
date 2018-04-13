/**
 * @author 梁闯
 * @date 2018/03/15 20.09
 */


// 默认页码
var page = 1;

// 默认数量
var size = 10;


$(function () {


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

    $("#checkAll").click(function () {
        $("input[name='checkPersonal']:checkbox").prop("checked", this.checked);
    });
});//初始化


/**
 * 加载数据
 * @param page 页码
 * @param size 大小
 */
function loadData(page, size) {

    // 显示动画
    $.LoadingOverlay("show");
    TableExport.init();
    var data = {
        page: page,
        size: size,
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "finance/getAllEntrust",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            if (res.success) {
                // 清空表格
                $('tbody').empty();
                // 添加数据到表格
                var list = res.data.list;
                console.log(list);
                var  company_id, comName,phone,money,type,end_date,create_date;
                $.each(list, function (index, item) {
                    console.log("得到的数据：" + item);
                    company_id = item.company_id;
                    comName = item.comName;
                    if(comName==null || comName==""){
                        comName="---";
                    }
                    phone = item.phone;
                    if(phone==null || phone==""){
                        phone="---";
                    }
                    end_date = item.end_date;
                    if(end_date == null || end_date == ""){
                        end_date ="---";
                    }
                    create_date =item.create_date;
                    type = item.type;
                    if(type == "1"){
                        type ="半年版";
                    }else if(type == "2"){
                        type ="一年版";
                    }else {
                        type ="未知";
                    }
                    money = item.money;
                    if(money == null || money ==""){
                        money ="0";
                    }

                    var tableHtml = "";
                    tableHtml += '<tr>' +
                        '<td><input type="checkbox" name="checkPersonal" value="'+company_id+'"/></td>'+
                        '<td>' + company_id + '</td>' +
                        '<td>' + comName + '</td>' +
                        '<td>' + phone + '</td>' +
                        '<td>' + create_date + '</td>' +
                        '<td>' + money + '</td>' +
                        '<td>' + type + '</td>' +
                        '<td>' + end_date + '</td>' ;
                    tableHtml += '</tr>';
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

            } else {
                $('tbody').empty();
                $("#pagination").empty();
                $("#pagination").removeClass("well");
            }
        }
    });
}

/**
 * 导出Excule
 */
function btnExport() {

    //导出当前页
    $('#sorter').tableExport({

        type:'excel',

        escape:'false',

        fileName: '导出委托招聘付费列表',

        exportDataType: 'all'

    });

}