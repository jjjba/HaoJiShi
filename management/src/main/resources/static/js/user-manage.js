/**
 * Created by SongpoLiu on 2017/5/17.
 */

// 默认页码
var page = 1;

// 默认数量
var size = 10;

$(function () {
    // 添加模态框事件
    $('#addModal').on('hidden.bs.modal', function () {
        // 重置表单校验状态
        $(this).find('form').data('formValidation').resetForm(true);
    }).on('success.form.fv', function (e) {
        // Prevent form submission
        e.preventDefault();

        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();

        $.ajax({
            url: $form.attr('action'),
            type: 'PUT',
            data: $form.serialize()
        }).then(function (res) {
            if (res.success) {
                // 隐藏模态框
                //$modal.modal('hide');
                $('#addModal').modal('hide');
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
    }).on('click', '.btn-primary', function () {
        // 提交表单
        $(this).parent().prev().find('form').data('formValidation').validate();
    }).find('form').formValidation({
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'fa fa-check',
            invalid: 'fa fa-times',
            validating: 'fa fa-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '帐号不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '确认密码与密码不相同'
                    }
                }
            },
            role: {
                validators: {
                    notEmpty: {
                        message: '角色不能为空'
                    }
                }
            }
        }
    });

    // 修改模态框事件
    $('#editModal').on('hidden.bs.modal', function () {
        // 重置表单校验状态
        $(this).find('form').data('formValidation').resetForm(true);
    }).on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the model
        var id = button.data('id'); // Extract info from data-* attributes
        console.log(id);
        // Update the model's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var $modal = $(this);
        $modal.find('[name=mid]').val(id);

        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        $.getJSON($('#baseUrl').attr('href') + 'members/' + id, function (res) {
            if (res.success) {
                var user = res.data;
                $modal.find('[name=username]').val(user.username);
                $modal.find('[name=id]').val(id);
                $modal.find('[name=password]').val('');
                $modal.find('[name=confirmPassword]').val('');
                $modal.find('[name=role]').prop('disabled', user.username === 'admin').val(res.data.role);
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
                $modal.modal('hide');
                // 重新加载数据
                loadData(page, size);
            } else {
                $modal.find('.alert').find('span').text(res.msg);
                $modal.find('.alert').show();
            }
        }, function (res) {
            $modal.find('.alert').find('span').text('请求网络失败，请重试');
            $modal.find('.alert').show();
        });
    }).on('click', '.btn-primary', function () {
        // 提交表单
        $(this).parent().prev().find('form').data('formValidation').validate();
    }).find('form').formValidation({
        framework: 'bootstrap',
        excluded: ':disabled',
        icon: {
            valid: 'fa fa-check',
            invalid: 'fa fa-times',
            validating: 'fa fa-refresh'
        },
        fields: {
            confirmPassword: {
                validators: {
                    identical: {
                        field: 'password',
                        message: '确认密码与密码不相同'
                    }
                }
            },
            role: {
                validators: {
                    notEmpty: {
                        message: '角色不能为空'
                    }
                }
            }
        }
    });

    // 删除模态框事件
    $('#deleteModal').on('hidden.bs.modal', function () {
        // 重置表单校验状态
        /* $(this).find('form').data('formValidation').resetForm(true);*/
    }).on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the model
        var id = button.data('id'); // Extract info from data-* attributes
        var username = button.data('username'); // Extract info from data-* attributes

        // Update the model's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var $modal = $(this);
        $modal.find('[name=id]').val(id);
        $modal.find('p').find('span').text(username);
    }).on('click', '.btn-primary', function () {
        var $modal = $('#deleteModal');
        var $form = $modal.find('form');
        // 删除
        $.ajax({
            url: $form.attr('action') + '/' + $form.find('[name=id]').val(),
            type: 'DELETE'
        }).then(function (res) {
            if (res.success) {
                // 隐藏模态框
                $modal.modal('hide');
                // 重新加载数据
                loadData(page, size);
            } else {
                $modal.find('.alert').find('span').text(res.msg);
                $modal.find('.alert').show();
            }
        }, function (res) {
            $modal.find('.alert').find('span').text('请求网络失败，请重试');
            $modal.find('.alert').show();
        });
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

    var data = {page: page, size: size};

    // 显示动画
    $.LoadingOverlay("show");

    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + 'members',
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");

            if (res.success) {
                // 清空表格
                $('tbody').empty();

                // 添加数据到表格
                $.each(res.data.list, function (index, item) {
                    var roleName = '';
                    switch (item.role) {
                        case 1:
                            roleName = '管理员';
                            break;
                        case 2:
                            roleName = '运维人员';
                            break;
                        case 3:
                            roleName = '财务人员';
                            break;
                        case 4:
                            roleName = '客服人员';
                            break;
                        case 5:
                            roleName = '代理商';
                            break;
                        case 6:
                            roleName = '金牌管理员';
                            break;
                        case 7:
                            roleName = '战略合伙人';
                            break;
                    }

                    if (item.username === 'admin') {
                        $('tbody').append('<tr>' +
                            '<td style="text-align: center;">' + (res.data.startRow + index) + '</td>' +
                            '<td style="text-align: center;">' + (item.username || '') + '</td>' +
                            '<td style="text-align: center;">' + roleName + '</td>' +
                            '<td style="text-align: center;">' +
                            '<div class="btn-group">' +
                            '<button class="btn btn-primary editShow" data-toggle="modal" data-target="#editModal" data-id="' + item.id + '"><i class="fa fa-edit"></i></button>' +
                            '</div>' +
                            '</td>' +
                            '</tr>');
                    } else {
                        $('tbody').append('<tr>' +
                            '<td style="text-align: center;">' + (res.data.startRow + index) + '</td>' +
                            '<td style="text-align: center;">' + (item.username || '') + '</td>' +
                            '<td style="text-align: center;">' + roleName + '</td>' +
                            '<td style="text-align: center;">' +
                            '<div class="btn-group">' +
                            '<button class="btn btn-primary" data-toggle="modal" data-target="#editModal" data-id="' + item.id + '"><i class="fa fa-edit"></i></button>' +
                            '<button class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" data-id="' + item.id + '" data-username="' + item.username + '"><i class="fa fa-trash"></i></button>' +
                            '</div>' +
                            '</td>' +
                            '</tr>');
                    }
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
                console.log('获取货位列表失败，请重试');
            }
        }
    });

}
