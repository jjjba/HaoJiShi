/**
 * Created by SongpoLiu on 2017/5/17.
 */

// 默认页码
var page = 1;

// 默认数量
var size = 10;

$(function () {

    $("#xgmm").click(function () {
        var id = $(this).attr("user-id");
        $("#editModal").modal("toggle");
        $(".modal-backdrop").remove();//删除class值为modal-backdrop的标签，可去除阴影

        var $modal = $("#editModal");
        $modal.find('[name=mid]').val(id);
        $.getJSON($('#baseUrl').attr('href') + 'members/' + id, function (res) {
            if (res.success) {
                var user = res.data;
                $modal.find('[name=username]').val(user.username);
                $modal.find('[name=id]').val(id);
                $modal.find('[name=password]').val('');
                $modal.find('[name=confirmPassword]').val('');
            }
        });
    });



    // 修改模态框事件
    $('#editModal').on('hidden.bs.modal', function () {
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
            type: 'PATCH',
            data: $form.serialize()
        }).then(function (res) {
            if (res.success) {
                // 隐藏模态框
                $modal.modal('hide');
                alert("修改密码成功");
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

});


