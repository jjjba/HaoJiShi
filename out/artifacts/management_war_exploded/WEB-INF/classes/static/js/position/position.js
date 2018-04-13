/**
 * @author 梁闯
 * @date 2018/03/15 19.43
 */

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
    $.LoadingOverlay("show");
    var data = {
        page: page,
        size: size,
        name: $('.navbar-form').find('[name=name]').val(),
        phone: $('.navbar-form').find('[name=phone]').val(),
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "position/getAllPosition",
        data: data,
        success: function (res) {
            $.LoadingOverlay("hide");
            if (res.success) {
                $('tbody').empty();
                if (res.data != null) {
                    var list = res.data.list;
                    console.log(list);
                    var id, position_name,position_type,name, sex, age, phone, position_info,
                        money,experience,create_time,hot,exposure_number,see_number,update_time,
                        share_number,resume_number, welfare,company_addr;
                    $.each(list, function (index, item) {
                        id = item.id;
                        position_name = item.position_name;
                        if(position_name == null || position_name ==""){
                            position_name ="--"
                        }
                        position_type = item.position_type;
                        if(position_type == null || position_type ==""){
                            position_type ="--"
                        }
                        name = item.name;
                        if(name == null || name ==""){
                            name ="--"
                        }
                        sex = item.sex;
                        if(sex == null || sex ==""){
                            sex ="--"
                        }
                        age = item.age;
                        if(age == null || age ==""){
                            age ="--"
                        }
                        phone = item.phone;
                        if(phone == null || phone ==""){
                            phone ="--"
                        }
                        position_info = item.position_info;
                        if(position_info == null || position_info ==""){
                            position_info ="--"
                        }
                        money = item.money;
                        if(money == null || money ==""){
                            money ="--"
                        }
                        experience = item.experience;
                        if(experience == null || experience ==""){
                            experience ="--"
                        }
                        create_time = item.create_time;

                        hot = item.hot;
                        if(hot == null || hot ==""){
                            hot ="0"
                        }
                        exposure_number = item.exposure_number;
                        if(exposure_number == null || exposure_number ==""){
                            exposure_number ="0"
                        }
                        see_number = item.see_number;
                        if(see_number == null || see_number ==""){
                            see_number ="0"
                        }
                        update_time = item.update_time;
                        if(update_time == null || update_time ==""){
                            update_time ="--"
                        }
                        share_number = item.share_number;
                        if(share_number == null || share_number ==""){
                            share_number ="0"
                        }
                        resume_number = item.resume_number;
                        if(resume_number == null || resume_number ==""){
                            resume_number ="0"
                        }
                        welfare = item.welfare;
                        if(welfare == null || welfare ==""){
                            welfare ="--"
                        }

                        company_addr = item.company_addr;
                        if(company_addr == null || company_addr ==""){
                            company_addr ="--"
                        }

                        var tableHtml = "";
                        tableHtml += '<tr>' +
                            '<td><input type="checkbox" name="checkPersonal" value="'+id+'"/></td>'+
                            '<td>' + id + '</td>' +
                            '<td>' + name + '</td>' +
                            '<td>' + phone + '</td>' +
                            '<td>' + position_type + '</td>' +
                            '<td>' + position_name + '</td>' +
                            '<td>' + create_time + '</td>' +
                            '<td>' + update_time + '</td>' +
                            '<td>' + hot + '</td>' +
                            '<td>' + exposure_number + '</td>' +
                            '<td>' + see_number + '</td>' +
                            '<td>' + share_number + '</td>' +
                            '<td>' + resume_number + '</td>' +
                            '<td>' + money + '</td>' +
                            '<td>' + age + '</td>' +
                            '<td>' + sex + '</td>' +
                            '<td>' + experience + '</td>' +
                            '<td>' + welfare + '</td>' +
                            '<td>' + company_addr + '</td>'+
                            '<td>' + position_info + '</td>';
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

function btnExport() {

    //导出当前页
    $('#sorter').tableExport({

        type: 'excel',

        escape: 'false',

        fileName: '导出职位列表',

        exportDataType: 'all'

    });
}

/**
 * 冻结职位操作
 */
function frozenExport() {
    // 显示动画
    var positionCheck = $("input:checkbox[name='checkPersonal']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');

    $.LoadingOverlay("show");
    var data = {
        positionCheck : positionCheck
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "position/frozenPosition",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            alert(res.msg)

        },
        error: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            alert(res.msg)
        }
    });
}

/**
 * 关闭职位操作
 */
function shutDownExport() {
    // 显示动画
    var positionCheck = $("input:checkbox[name='checkPersonal']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');
    $.LoadingOverlay("show");
    var data = {
        positionCheck : positionCheck
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "position/shutDownPosition",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            alert(res.msg)

        },
        error: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            alert(res.msg)
        }

    });
}

//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}