/**
 * Created by snhua on 2017/7/15.
 */
$(function () {
    $('#end_time').datetimepicker({
        locale: 'zh-cn',
        format: 'YYYY-MM-DD',
        showClose: true,
        keepOpen: false
    });

    $('#end_time').data("DateTimePicker").date(moment().days(1).format('YYYY-MM-DD'));

    // 绑定设置代理商事件
    $('#agentModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var uid = button.data('uid');
        var role = button.data('role');

        var agentModal = $(this);

        $.ajaxSettings.async = false;
        //清空输入框值
        $("#agentModal").find("input,select").each(function () {
            $(this).val('');
        });
        $("#agentModal").find("select").each(function () {
            if($(this).attr("name")!="role")
                $(this).empty();
        });
        var $industryList = agentModal.find("[name=industry]");
        loadIndustry($industryList);

        agentModal.find("[name=uid]").val(uid);
        /*agentModal.find("[name=agent_rate]").val(agent_rate);
         agentModal.find("[name=open_rate]").val(open_rate);*/

        //初始化省份
        $.getJSON($('#baseUrl').attr('href') + 'region?pid=1', function (res) {
            if (res.success) {
                //  console.info(res.data);
                var options = "";
                $.each(res.data, function (index, item) {
                    options += "<option value='" + item.id + "'>" + item.name + "</option>";
                });
                $("select[name='province']").append(options);

                $.getJSON($('#baseUrl').attr('href') + 'customers/agent-info?uid=' + uid + "&acid=" + $('.navbar-form').find('select').val(), function (res) {
                    if (res.success) {
                        // $("select[name=role]").val(50);
                        agentModal.find("[name=num]").val(res.data.num);
                        agentModal.find("[name=parent_uid]").val(res.data.parent_uid);
                        agentModal.find("[name=agent_rate]").val(res.data.agent_rate);
                        agentModal.find("[name=open_rate]").val(res.data.open_rate);
                        agentModal.find("[name=remark]").val(res.data.remark);
                        $industryList.val(res.data.industry);
                        /*agentModal.find("[name=agent_rate]").val(res.data.agent_rate);*/
                        agentModal.find("[name=opening_limit]").val(res.data.opening_limit);

                        // var unixTimestamp = new Date(res.data.end_time * 1000);
                        var date = new Date(res.data.end_time * 1000);
                        var year = date.getFullYear();
                        var month = (date.getMonth() + 1) >= 10 ? (date.getMonth() + 1) : ("0" + (date.getMonth() + 1));
                        var day = (date.getDate() ) < 10 ? ("0" + (date.getDate() )) : (date.getDate() );
                        var time = year + "-" + month + "-" + day;

                        // agentModal.find("[name=end_time]").val(unixTimestamp.toLocaleString().substr(0, 10));
                        agentModal.find("[name=end_time]").val(time);
                        $("#agentModal").find("[name=province]").val('');
                        $.getJSON($('#baseUrl').attr('href') + 'region/region-id?id=' + res.data.city, function (response) {
                            if (response.success) {
                                if (null !== response.data && null !== response.data.parent_id) {
                                    $("#agentModal").find("[name=province]").val(response.data.parent_id);
                                    fillelectRegion(response.data.parent_id, 1);
                                }

                                $("#agentModal").find("[name=city]").val(res.data.city);
                                fillelectRegion(res.data.city, 2);
                                $("#agentModal").find("[name=region]").val(res.data.region);
                            }
                        });
                    }
                });
            }
        });
    });


    //设置、修改、设置为代理商
    $("#saveAgentBun").click(function () {
        var agentModal = $("#agentModal");
        var num = agentModal.find("[name=num]").val();
        var agent_rate = agentModal.find("[name=agent_rate]").val();
        var open_rate = agentModal.find("[name=open_rate]").val();
        var opening_limit = agentModal.find("[name=opening_limit]").val();
        var end_timeStr = agentModal.find("[name=end_time]").val();
        var province = agentModal.find("[name=province]").val();
        var city = agentModal.find("[name=city]").val();
        var region = agentModal.find("[name=region]").val();
        var roleid = agentModal.find("[name=role]").val();
        var industry = agentModal.find("[name=industry]").val();
        var parent_uid = agentModal.find("[name=parent_uid]").val();
        var remark = agentModal.find("[name=remark]").val();
        $.ajax({
            url: $('#baseUrl').attr('href') + 'customers/change-to-agent',
            type: 'PATCH',
            data: {
                uid: agentModal.find('[name=uid]').val(),
                num: num,
                agent_rate: agent_rate,
                open_rate: open_rate,
                opening_limit: opening_limit,
                end_timeStr: end_timeStr,
                province: province,
                city: city,
                region: region,
                acid: $('.navbar-form').find('select').val(),
                roleid: roleid,
                industry:industry,
                parent_uid: parent_uid,
                remark: remark
            }
        }).then(function (res) {
            if (res.success) {
                // 设置全选按钮为空
                $('thead :checkbox').prop('checked', false);
                // 重新加载数据
                loadData(1, size, curAcid);
            }
        });
        // 隐藏模态框
        $(this).modal('hide');
    });

});
