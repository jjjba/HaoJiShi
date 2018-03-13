/**
 * Created by SongpoLiu on 2017/5/17.
 */

// 默认页码
var page = 1;

// 默认数量
var size = 10;

$(function () {

    // 绑定修改模态框事件
    $('#editModal').on('hidden.bs.modal', function () {
        $(this).find('form').data('formValidation').resetForm(true);// 重置表单校验状态
    }).on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the model
        var id = button.data('id'); // Extract info from data-* attributes
        var $modal = $(this);
        $modal.find('[name=rid]').val(id);
        $.getJSON($('#baseUrl').attr('href') + 'reports/' + id, function (res) {
            if (res.success) {
                var item = res.data;
                // console.log(item);
                var shopname, statu;
                if (item.shopid !== null) {
                    shopname = '店铺';
                } else {
                    shopname = '平台';
                }
                if (item.status == 10) {
                    statu = '未处理';
                } else if (item.status == 20) {
                    statu = '已处理';
                } else {
                    statu = '未知';
                }
                //
                $modal.find('[name=rid]').val(item.rid);
                //投诉状态
                $modal.find('[name=status]').val(statu);
                //投诉类型
                $modal.find('[name=type]').val(shopname);
                //投诉用户
                $modal.find('[name=name]').val(item.name);
                //投诉店铺
                $modal.find('[name=shopName]').val(item.title);
                //联系电话
                $modal.find('[name=phone]').val(item.phone);
                //详情
                $modal.find('[name=body]').val(item.body);
                //处理结果
                $modal.find('[name=remark]').val(item.remark);
            }
        });
    }).on('success.form.fv', function (e) {
        // Prevent form submission
        e.preventDefault();
        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();

        $.ajax({
            url: $form.attr('action'),
            type: 'POST',
            data: $form.serialize()
        }).then(function (res) {
            if (res.success) {
                // 隐藏模态框
                // console.log(res.success);
                $('#editModal').modal('hide');
                // 重新加载数据
                loadData(1, size);
            } else {
                $modal.find('.alert').find('span').text(res.msg);
                $modal.find('.alert').show();
            }
        }, function (res) {
            $modal.find('.alert').find('span').text('请求网络失败，请重试');
            $modal.find('.alert').show();
        });

    }).on('click', '.btn-primary', function (e) {
        // 提交表单
        $(this).parent().prev().find('form').data('formValidation').validate();
    }).find('form').formValidation({
        framework: 'bootstrap',
        excluded: [':disabled'],
        icon: {
            valid: 'fa fa-check',
            invalid: 'fa fa-times',
            validating: 'fa fa-refresh'
        },
        fields: {
            remark: {
                validators: {
                    notEmpty: {
                        message: '处理结果不能为空'
                    }
                }
            }
        }
    });

    // 绑定修改模态框事件
    $('#viewModal').on('hidden.bs.modal', function () {
        $(this).find('form').data('formValidation').resetForm(true);// 重置表单校验状态
    }).on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the model
        var id = button.data('id'); // Extract info from data-* attributes
        var $modal = $(this);
        $modal.find('[name=rid]').val(id);
        $.getJSON($('#baseUrl').attr('href') + 'reports/' + id, function (res) {
            if (res.success) {
                var item = res.data;
                //console.log(item);
                var shopname, statu;
                if (item.shopid !== null) {
                    shopname = '店铺';
                } else {
                    shopname = '平台';
                }
                if (item.status == 10) {
                    statu = '已处理';
                } else if (item.status == 20) {
                    statu = '未处理';
                } else {
                    statu = '未知';
                }
                //
                $modal.find('[name=rid]').val(item.rid);
                //投诉状态
                $modal.find('[name=status]').val(statu);
                //投诉类型
                $modal.find('[name=type]').val(shopname);
                //投诉用户
                $modal.find('[name=name]').val(item.name);
                //投诉店铺()
                $modal.find('[name=shopName]').val(item.title);
                //联系电话
                $modal.find('[name=phone]').val(item.phone);
                //详情
                $modal.find('[name=body]').val(item.body);
                //处理结果
                $modal.find('[name=remark]').val(item.remark);
            }
        });
    }).on('success.form.fv', function (e) {
        // Prevent form submission
        e.preventDefault();
        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();

        $.ajax({
            url: $form.attr('action'),
            type: 'PATCH',
            data: $form.serialize()
        }).then(function (res) {
            if (res.success) {
                // 隐藏模态框
                //console.log(res.success);
                $('#editModal').modal('hide');
                // 重新加载数据
                loadData(1, size);
            } else {
                $modal.find('.alert').find('span').text(res.msg);
                $modal.find('.alert').show();
            }
        }, function (res) {
            $modal.find('.alert').find('span').text('请求网络失败，请重试');
            $modal.find('.alert').show();
        });

    }).on('click', '.btn-primary', function (e) {
        // 提交表单
        $(this).parent().prev().find('form').data('formValidation').validate();
    }).find('form').formValidation({
        framework: 'bootstrap',
        excluded: [':disabled'],
        icon: {
            valid: 'fa fa-check',
            invalid: 'fa fa-times',
            validating: 'fa fa-refresh'
        },
        fields: {
            remark: {
                validators: {
                    notEmpty: {
                        message: '处理结果不能为空'
                    }
                }
            }
        }
    });


    loadData(page, size);
});

/**
 * 加载数据
 * @param page 页码
 * @param size 数量
 */
function loadData(page, size) {

    currentSize = size;
    currentPage = page;

    var data = {page: page, size: size,agent_uid:$('input[name=agent_uid]').val()};

    // 显示动画
    $.LoadingOverlay("show");

    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + 'reports',
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            //console.log(res);
            if (res.success) {

                // 清空表格
                $('tbody').empty();

                // 添加数据到表格
                $.each(res.data.list, function (index, item) {
                    // console.log(item);
                    /* var status = '未知';
                    switch (item.status) {
                        case 10:
                            status = '未处理';
                            break;
                        case 20:
                            status = '已处理';
                            break;
                     }*/

                    $('tbody').append('<tr >' +
                        '<td data-id="' + item.rid + '" data-toggle="modal" data-target="#viewModal">' + item.rid + '</td>' +
                        '<td>' + item.status + '</td>' +
                        '<td>' + item.remark + '</td>' +
                        '<td>' + (item.type != undefined ? item.type : "---" ) + '</td>' +
                        '<td>' + (item.title != undefined ? item.title : "---") + '</td>' +
                        '<td>' + item.name + '</td>' +
                        '<td>' + item.phone + '</td>' +
                        '<td>' + item.body + '</td>' +
                        /*'<td>' + moment(item.createTime).format('YYYY-MM-DD HH:MM:SS') + '</td>' +*/
                        '<td>' + item.createtime + '</td>' +
                        '<td><button class="btn btn-primary" data-toggle="modal" data-target="#editModal" data-id="' + item.rid + '">处理</button></td></tr>'
                    );
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
                console.log('获取投诉列表失败，请重试');
            }
        }
    });
}


/**
 * Excel导出
 */
function excelExport() {
    if ($('tbody td').size() == 0) {
        alert("没有数据不能导出！");
        return;
    }
    var agent_uid = $('input[name=agent_uid]').val();
    if(!agent_uid)
        agent_uid = "";
    window.location.href = $('#baseUrl').attr('href') + 'reports/export?agent_uid='+ agent_uid;
}
