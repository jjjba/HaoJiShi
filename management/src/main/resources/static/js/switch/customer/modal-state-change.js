/**
 *
 * Created by snhua on 2017/7/15.
 */
$(function(){
    // 绑定切换状态事件
    $('#editModal').on('hidden.bs.modal', function () {
        $(this).find('form').data('formValidation').resetForm(true);// 重置表单校验状态
    }).on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the model
        var uid = button.data('uid'); // Extract info from data-* attributes
        // console.log(uid);
        var status = button.data('status'); // Extract info from data-* attributes
        // console.log(status);
        var remark = button.data('remark');
        // console.log(remark);
        var $modal = $(this);
        $modal.find('[name=uid]').val(uid);
        $modal.find('[name=status]').val(status);
        $modal.find('[name=remark]').val(remark);

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
            states: {
                validators: {
                    notEmpty: {
                        message: '状态不能为空'
                    }
                }
            }/*,
             remark: {
             validators: {
             notEmpty: {
             message: '备注信息不能为空'
             }
             }
             }*/
        }
    });
});
