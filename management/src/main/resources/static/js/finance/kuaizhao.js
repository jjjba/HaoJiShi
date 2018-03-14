/**
 * @author 梁闯
 * @date 2018/03/14 20.09
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
        url: $('#baseUrl').attr('href') + "finance/getAllKuaiZhao",
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
                var  comId, comName,phone,startDate,money,type,name,endDate,surplusNumber,phoneNumber,createDate;
                $.each(list, function (index, item) {
                    console.log("得到的数据：" + item);
                    comId = item.comId;
                    comName = item.comName;
                    if(comName==null || comName==""){
                        comName="未填写";
                    }
                    phone = item.phone;
                    if(phone==null || phone==""){
                        phone="未填写";
                    }
                    name = item.name;
                    if(name==null || name==""){
                        name="未填写";
                    }
                    startDate = item.startDate;
                    if(startDate == null || startDate == ""){
                        startDate ="---";
                    }
                    endDate = item.endDate;
                    if(endDate == null || endDate == ""){
                        endDate ="---";
                    }
                    surplusNumber = item.surplusNumber;
                    if(surplusNumber == null || surplusNumber == ""){
                        surplusNumber="---";
                    }
                    phoneNumber =item.phoneNumber;
                    if(phoneNumber == null || phoneNumber == ""){
                        phoneNumber ="---";
                    }
                    createDate =item.createDate;
                    type = item.type;
                    if(type == "1"){
                        type ="按时间计算";
                    }else if(type == "2"){
                        type ="按次数计算";
                    }else {
                        type ="未知";
                    }
                    money = item.money;
                    if(money == null || money ==""){
                        money ="0";
                    }

                    var tableHtml = "";
                    tableHtml += '<tr>' +
                        '<td><input type="checkbox" name="checkPersonal" value="'+comId+'"/></td>'+
                        '<td>' + comId + '</td>' +
                        '<td>' + comName + '</td>' +
                        '<td>' + phone + '</td>' +
                        '<td>' + createDate + '</td>' +
                        '<td>' + money + '</td>' +
                        '<td>' + type + '</td>' +
                        '<td>' + name + '</td>' +
                        '<td>' + startDate + '</td>' +
                        '<td>' + endDate + '</td>' +
                        '<td>' + phoneNumber + '</td>' +
                        '<td>' + surplusNumber + '</td>';
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

        fileName: '导出快招付费列表',

        exportDataType: 'all'

    });

}