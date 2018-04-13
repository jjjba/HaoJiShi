/**
 * Created by snhua on 2017/7/15.
 */
$(function () {
    // 绑定设置代理商事件
    $('#passwordModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var uid = button.data('uid');
        var fanid = button.data('fanid');
        var mobile = button.data('mobile');
        var realname = button.data('realname');
        var remark = button.data('remark');

        var agentModal = $(this);

        $.ajaxSettings.async = false;
        //清空输入框值
        agentModal.find("input").each(function () {
            $(this).val('');
        });

        agentModal.find("[name=mobile]").val(mobile);
        agentModal.find("[name=uid]").val(uid);
        agentModal.find("[name=fanid]").val(fanid);
        agentModal.find("[name=realname]").val(realname);
        agentModal.find("[name=remark]").val(remark);

    });


    //修改用户信息
    $("#setPasswordBun").click(function () {
        var agentModal = $("#passwordModal");
        var mobile = agentModal.find("[name=mobile]").val();
        var realname = agentModal.find("[name=realname]").val();
        var password = agentModal.find("[name=password]").val();
        var remark = agentModal.find("[name=remark]").val();
        var fanid = agentModal.find('[name=fanid]').val();
        fanid = fanid=="undefined"?"":fanid;
        $.ajax({
            url: $('#baseUrl').attr('href') + 'customers/set-user',
            type: 'PATCH',
            data: {
                fanId: fanid?fanid:"",
                uid: agentModal.find('[name=uid]').val(),
                mobile: mobile,
                realname: realname,
                password: password,
                remark: remark
            }
        }).then(function (res) {
            if (res.success) {
                // 设置全选按钮为空
                $('thead :checkbox').prop('checked', false);
                agentModal.modal('hide');
                // 重新加载数据
                loadData();
            }else{
                alert(res.msg);
            }
        });
        // 隐藏模态框


    });

});
