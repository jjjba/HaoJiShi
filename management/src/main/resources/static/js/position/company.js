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

    //编辑
    $('#editModal').on('hidden.bs.modal', function () {
        $(this).find(":file").each(function (i, el) {
            $(el).fileinput("clear");
        })
    }).on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        ids = id;
        var $modal = $(this);
        $modal.find('[name=id]').val(id);
        $.getJSON($('#baseUrl').attr('href') + 'position/findPositionById?id=' + id, function (res) {
            if (res.success) {
                var item = res.data;
                console.log(item);
                $modal.find('[name=user_id]').val(item.userId);
                $modal.find('[name=company_id]').val(item.companyId);
                $modal.find('[name=position_name]').val(item.positionName);
                $modal.find('[name=position_type]').val(item.positionType);
                $modal.find('[name=sex]').val(item.sex);
                $modal.find('[name=age]').val(item.age);
                $modal.find('[name=money]').val(item.money);
                $modal.find('[name=experience]').val(item.experience);
                $modal.find('[name=position_info]').val(item.positionInfo);
                if(item.isReward==1){
                    $modal.find('[name=is_reward]').val(1);
                    $modal.find('[name=is_reward_check]').prop("checked",true);
                }else{
                    $modal.find('[name=is_reward]').val(2);
                    $modal.find('[name=is_reward_check]').removeAttr("checked");
                }
                $modal.find('[name=reward_money]').val(item.rewardMoney);
                $modal.find('[name=reward_detail]').val(item.rewardDetail);
            }
        });
    }).on('success.form.fv', function (e) {
        e.preventDefault();

        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();
            if($modal.find('[name=is_reward_check]').is(':checked')){
                $modal.find('[name=is_reward]').val(1)
            }else{
                $modal.find('[name=is_reward]').val(2)
            }

        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'position/updatePositionById',
            type: 'POST',
            data: formData,
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

    //添加
    $('#addModal').on('hidden.bs.modal', function () {
        $(this).find(":file").each(function (i, el) {
            $(el).fileinput("clear");
        })
    }).on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = button.data('id');
        ids = id;
        var $modal = $(this);
        $modal.find('[name=user_id]').val(user_id);
    }).on('success.form.fv', function (e) {
        e.preventDefault();
        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();

        if($modal.find('[name=is_reward_check]').is(':checked')){
            $modal.find('[name=is_reward]').val(1)
        }else{
            $modal.find('[name=is_reward]').val(2)
        }
        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'position/insertPosition',
            type: 'POST',
            data: formData,
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
        url: $('#baseUrl').attr('href') + "position/companyList",
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
                        tableHtml += '<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal" data-id="' + item.id + '" >修改</button>';
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