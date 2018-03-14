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



    //编辑
    $('#editModal').on('hidden.bs.modal', function () {
        $(this).find(":file").each(function (i, el) {
            $(el).fileinput("clear");
        })
    }).on('show.bs.modal', function (event) {
        initFileInput("#editModal input[name=icon][type=file]");
        initFileInput("#editModal input[name=company_photo][type=file]");
        var button = $(event.relatedTarget);
        var id = button.data('id');
        ids = id;
        var $modal = $(this);
        $modal.find('[name=id]').val(id);
        //读取省市县
        $.getJSON($('#baseUrl').attr('href') + 'company/regionList?pid=1' , function (res) {
            if (res.success) {
                console.log(res.data);
                $.each(res.data, function (index, item) {
                    $modal.find('[name=province]').append('<option value="'+item.id+'">'+item.name+'</option>');
                });
            }
        });
        $.getJSON($('#baseUrl').attr('href') + 'company/findCompanyInfoById?id=' + id  , function (res) {
            if (res.success) {
                var item = res.data;
                console.log(item);
                $modal.find('[name=pid]').val(item.pid);
                $modal.find('[name=company_city]').val(item.company_city);
                $modal.find('[name=company_special_str]').val(item.company_special);
                $modal.find('[name=company_name]').val(item.company_name);
                $modal.find('[name=company_scale]').val(item.company_scale);
                $modal.find('[name=province]').val(item.province_id);
                $modal.find('[name=company_info]').val(item.company_info);
                $modal.find('[name=company_type]').val(item.company_type);
                $modal.find('[name=company_addrx]').val(item.company_addrx);
                $modal.find('[name=company_addry]').val(item.company_addry);
                $modal.find('[name=company_addr]').val(item.company_addr);

                if (item.icon != null && item.icon !="") {
                    $modal.find('[name=iconN]').attr("src",item.icon);
                }else{
                    $modal.find('[name=iconN]').css("display","none");
                }
                // if (item.company_photo != null && item.company_photo !="") {
                //     $modal.find('[name=photoN]').attr("src",item.company_photo);
                // }else{
                //     $modal.find('[name=photoN]').css("display","none");
                // }
                //绑定市
                if(item.province_id !=null && item.province_id !=""){
                    $.getJSON($('#baseUrl').attr('href') + 'company/regionList?pid='+item.province_id , function (res) {
                        if (res.success) {
                            $.each(res.data, function (index, item) {
                                $modal.find('[name=city]').append('<option value="'+item.id+'">'+item.name+'</option>');
                            });
                            $modal.find('[name=city]').val(item.city_id);
                        }
                    });
                }
                //绑定区
                if(item.city_id !=null && item.city_id !="") {
                    $.getJSON($('#baseUrl').attr('href') + 'company/regionList?pid=' + item.city_id, function (res) {
                        if (res.success) {
                            $.each(res.data, function (index, item) {
                                $modal.find('[name=area]').append('<option value="' + item.id + '">' + item.name + '</option>');
                            });
                            $modal.find('[name=area]').val(item.area_id);
                        }
                    });
                }
                //绑定标签
                var company_special_str=","+item.company_special+",";
                $('input[name="company_special"]').each(function(){
                    if(company_special_str.replace(","+$(this).val()+",","")!=company_special_str){
                        $(this).attr("checked", true);
                    }
                });
            }
        });
    }).on('success.form.fv', function (e) {
        e.preventDefault();

        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();

        $modal.find('[name=company_city]').val($modal.find('[name=city]').find("option:selected").text()+"-"+$modal.find('[name=area]').find("option:selected").text());
        var company_special_str="";
        $('input[name="company_special"]:checked').each(function(){
            if(company_special_str==null || company_special_str==""){
                company_special_str=$(this).val();
            }else{
                company_special_str=company_special_str+","+$(this).val();
            }
        });
        $modal.find('[name=company_special_str]').val(company_special_str);
        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'company/updateCompanyById',
            type: 'POST',
            data:formData,
            // data: {id: id,name:name,sex:sex,state:state,age:age,phone:phone,info:info,pid:pid},
            processData: false,
            contentType: false
        }).then(function (res) {
            if (res.success) {
                // 隐藏模态框
                $modal.modal('hide');
                // 重新加载数据
                loadData();
            } else {
                $modal.find('.alert').find('span').text(res.msg);
                $modal.find('.alert').show();
            }
        }, function (res) {
            $modal.find('.alert').find('span').text('请求网络失败，请重试');
            $modal.find('.alert').show();
        });

    }).on('click', '.btn-primary2', function () {
        // 提交表单
        $(this).parent().prev().find('form').data('formValidation').validate();
    }).find('form').formValidation();



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
    //   alert($('.navbar-form').find('[name=start_time]').val());
    $.LoadingOverlay("show");
    TableExport.init();
    var data = {
        page: page,
        size: size,
        name: $('.navbar-form').find('[name=name]').val(),
        phone: $('.navbar-form').find('[name=phone]').val(),
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "company/getAllCompany",
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
                var  id, company_special, name, phone, job_experience, create_time, month_visits, user_name,
                zhi_wu, openid, matState, company_addr, account_state, company_info, company_photo, position_exposure_number,
                    position_see_number, position_number, share_number, position_count, company_city, company_scale, company_type
                ;
                $.each(list, function (index, item) {
                    console.log("得到的数据：" + item);
                    id = item.id;

                    user_name = item.user_name;
                    if(user_name==null || user_name==""){
                        user_name="未填写";
                    }

                    zhi_wu = item.zhi_wu;
                    if(zhi_wu==null || zhi_wu==""){
                        zhi_wu="未填写";
                    }
                    phone = item.phone;
                    if(phone==null || phone==""){
                        phone="未填写";
                    }
                    name = item.name;
                    if(name==null || name==""){
                        name="未填写";
                    }
                    company_scale = item.company_scale;
                    if(company_scale==null || company_scale==""){
                        company_scale="未填写";
                    }
                    company_type = item.company_type;
                    if(company_type==null || company_type==""){
                        company_type="未填写";
                    }
                    company_city = item.company_city;
                    if(company_city==null || company_city==""){
                        company_city="未填写";
                    }
                    position_count = item.position_count;
                    if(position_count==null || position_count==""){
                        position_count="0";
                    }

                    openid = item.openid;
                    if(openid==null || openid==""){
                        openid="未获取到";
                    }
                    matState = item.matState;
                    if(matState=="1"){
                        matState="审核通过";
                    }else if(matState == "2"){
                        matState="审核未通过";
                    }else {
                        matState="未知";
                    }
                    company_addr = item.company_addr;
                    if(company_addr==null || company_addr==""){
                        state="未填写";
                    }
                    account_state = item.account_state;
                    if(account_state== "1"){
                        account_state="正常";
                    }else if(account_state== "2"){
                        account_state="冻结";
                    }else if(account_state== "3"){
                        account_state="删除";
                    }else {
                        account_state="未知";
                    }
                    company_info = item.company_info;
                    if(company_info==null || company_info==""){
                        company_info="未填写";
                    }
                    company_photo = item.company_photo;
                    if(company_photo==null || company_photo==""){
                        company_photo="无";
                    }else {
                        company_photo="有";
                    }
                    position_exposure_number = item.position_exposure_number;
                    if(position_exposure_number==null || position_exposure_number==""){
                        position_exposure_number="0";
                    }
                    position_see_number = item.position_see_number;
                    if(position_see_number==null || position_see_number==""){
                        position_see_number="0";
                    }
                    position_number = item.position_number;
                    if(position_number==null || position_number==""){
                        position_number="0";
                    }
                    share_number = item.share_number;
                    if(share_number==null || share_number==""){
                        share_number="0";
                    }

                    month_visits = item.month_visits;
                    if(month_visits==null || month_visits==""){
                        month_visits="0";
                    }

                    company_special = item.company_special;
                    if(company_special==null || company_special==""){
                        company_special="暂无";
                    }
                    create_time = item.create_time;

                    var tableHtml = "";
                    tableHtml += '<tr>' +
                        '<td><input type="checkbox" name="checkPersonal" value="'+id+'"/></td>'+
                        '<td>' + id + '</td>' +
                        '<td>' + name + '</td>' +
                        '<td>' + create_time + '</td>' +
                        '<td>' + month_visits + '</td>' +
                        '<td>' + user_name + '</td>' +
                        '<td>' + zhi_wu + '</td>' +
                        '<td>' + phone + '</td>' +
                        '<td>' + openid + '</td>' +
                        '<td>' + company_type + '</td>' +
                        '<td>' + company_scale + '</td>' +
                        '<td>' + company_city + '</td>' +
                        '<td>'+ position_count+ '</td>' +
                        '<td>' + share_number + '</td>' +
                        '<td>' + position_number + '</td>' +
                        '<td>' + position_exposure_number + '</td>' +
                        '<td>' + position_see_number + '</td>' +
                        '<td>' + company_addr + '</td>' +
                        '<td>' + company_special + '</td>' +
                        '<td>' + matState + '</td>'+
                        '<td>' + account_state + '</td>' +
                        '<td>' + company_info + '</td>' +
                        '<td>' + company_photo + '</td>';
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
 * 冻结企业账户操作
 */
function frozenAccountExport() {
    // 显示动画
    var companyCheck = $("input:checkbox[name='checkPersonal']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');
    alert("选中的checkbox的值为："+companyCheck);

    $.LoadingOverlay("show");
    var data = {
        companyCheck : companyCheck
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "company/frozenAccount",
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
 * 删除企业账户操作
 */
function deleteAccountExport() {
    // 显示动画
    var companyCheck = $("input:checkbox[name='checkPersonal']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');
    $.LoadingOverlay("show");
    var data = {
        companyCheck : companyCheck
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "company/deleteAccount",
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



function btnExport() {

    //导出当前页
    $('#sorter').tableExport({

        type:'excel',

        escape:'false',

        fileName: '导出企业列表',

        exportDataType: 'all'

    });


//bootstrap插件导出Excel
    //初始化表格
    // $("#table").bootstrapTable({
    //     striped: true,
    //     columns: [{
    //         field: "id",
    //         title: "id",
    //         align: "center"
    //     },{
    //         field: "手机号",
    //         title: "手机号",
    //         align: "center"
    //     },{
    //         field: "姓名",
    //         title: "姓名",
    //         align: "center"
    //     },{
    //         field: "职位",
    //         title: "职位",
    //         align: "center"
    //     },{
    //         field: "添加时间",
    //         title: "添加时间",
    //         align: "center"
    //     },{
    //         field: "收到的简历数",
    //         title: "收到的简历数",
    //         align: "center"
    //     },{
    //         field: "访问量",
    //         title: "访问量",
    //         align: "center"
    //     },{
    //         field: "店铺名称",
    //         title: "店铺名称",
    //         align: "center"
    //     },{
    //         field: "支付金额",
    //         title: "支付金额",
    //         align: "center"
    //     },{
    //         field: "支付时间",
    //         title: "支付时间",
    //         align: "center"
    //     },{
    //         field: "店铺规模",
    //         title: "店铺规模",
    //         align: "center"
    //     },{
    //         field: "店铺类型",
    //         title: "店铺类型",
    //         align: "center"
    //     },{
    //         field: "所在城市",
    //         title: "所在城市",
    //         align: "center"
    //     }],
    //     cache: false,
    //     formatLoadingMessage: function () {
    //         return "";
    //     },
    //     formatNoMatches: function () {  //没有匹配的结果
    //         return '无符合条件的记录';
    //     }
    // });

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
