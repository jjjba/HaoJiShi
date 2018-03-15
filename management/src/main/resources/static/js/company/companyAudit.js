/**
 *
 * @author 梁闯
 * @date 2018/03/13 22.54
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
    var data = {
        page: page,
        size: size,
        name: $('.navbar-form').find('[name=name]').val(),
        phone: $('.navbar-form').find('[name=phone]').val(),
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "company/getAllCompanyState",
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
                var id, name, phone, create_time, modify_time, user_name,
                    license_path,matState;
                $.each(list, function (index, item) {
                    console.log("得到的数据：" + item);
                    id = item.id;
                    user_name = item.user_name;
                    if(user_name==null || user_name==""){
                        user_name="---";
                    }

                    phone = item.phone;
                    if(phone==null || phone==""){
                        phone="---";
                    }

                    name = item.name;
                    if(name==null || name==""){
                        name="---";
                    }

                    matState = item.matState;
                    if(matState=="1"){
                        matState="审核通过";
                    }else if(matState == "2"){
                        matState="审核未通过";
                    }else {
                        matState="审核中";
                    }

                    license_path = item.license_path;
                    if(license_path==null || license_path==""){
                        license_path="未上传营业执照";
                    }else {
                        license_path ='<img src="'+license_path+'" style="width: 200px;height: 200px">';
                    }

                    create_time = item.create_time;

                    modify_time = item.modify_time;

                    var tableHtml = "";
                    tableHtml += '<tr>' +
                        '<td><input type="checkbox" name="checkPersonal" value="'+id+'"/></td>'+
                        '<td>' + id + '</td>' +
                        '<td>' + name + '</td>' +
                        '<td>' + create_time + '</td>' +
                        '<td>' + user_name + '</td>' +
                        '<td>' + phone + '</td>' +
                        '<td>' + license_path + '</td>' +
                        '<td>' + modify_time + '</td>' +
                        '<td>' + matState + '</td>';
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
function search() {
    loadData(page, size);
}

/**
 * 设置企业营业执照审核通过
 */
function approvedExport() {
    // 显示动画
    $.LoadingOverlay("show");
    var data = {
        id : $("input:checkbox[name='checkPersonal']:checked").val(),
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "company/approve",
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
 * 设置企业营业执照审核未通过
 */
function auditFailedExport() {
    // 显示动画
    $.LoadingOverlay("show");
    var data = {
        id : $("input:checkbox[name='checkPersonal']:checked").val(),
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "company/auditFailed",
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


function initFileInput(id) {
//初始化
    $(id).fileinput({
        language: "zh", // 设置语言为中文
        showUpload: false, // 是否显示上传按钮
        previewFileType: 'any', // 预览文件类型
        layoutTemplates: {//去除预览图
            actionZoom: '',
            actionDrag: '',
            close: '',
        }
    })
        .on('filecleared', function (e) {
            $('#editModal').find('form')
                .data('formValidation')         // Get the validator instance
                .revalidateField($(e.target));
        });
}
//切换市、区
function loadRegion(e,rtype) {
    //读取省市县
    $.getJSON($('#baseUrl').attr('href') + 'company/regionList?pid='+$(e).val() , function (res) {
        if (res.success) {
            if(rtype=="city"){
                $('#'+rtype).html('<option value="">市</option>');
            }else{
                $('#'+rtype).html('<option value="">区</option>');
            }

            $.each(res.data, function (index, item) {
                $('#'+rtype).append('<option value="'+item.id+'">'+item.name+'</option>');
            });
        }
    });
}

function trunUrl(url) {
    window.location.href=url;
}
