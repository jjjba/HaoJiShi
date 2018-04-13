/**
 * @author 梁闯
 * @date 2018/03/12 23.41
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

    //编辑
    $('#editModal').on('hidden.bs.modal', function () {
        $(this).find(":file").each(function (i, el) {
            $(el).fileinput("clear");
        })
    }).on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var id = $("input:checkbox[name='checkPersonal']:checked").val();
        var $modal = $(this);
        $modal.find('[name=id]').val(id);
        $.getJSON($('#baseUrl').attr('href') + 'personal/getPersonalByPersonalId?id=' + id  , function (res) {
            if (res.success) {
                var item = res.data;
                var id, name, sex, age, telephone, monthVisits, openid, hopeJob, hopeCity,
                    address, expectMoney, resumeState, accountState, recordSchool, onceDo,special,
                    myHometown, jobExperience, state, myselfInfo, resumeNumber, createTime, photos;
                id = item.id;
                name = item.name;
                if(name==null || name==""){
                    name="---";
                }
                sex = item.sex;
                if(sex==null || sex==""){
                    sex="---";
                }
                age = item.age;
                if(age==null || age==""){
                    age="---";
                }
                telephone = item.phone;
                if(telephone==null || telephone==""){
                    telephone="---";
                }
                monthVisits =item.monthVisits;
                if(monthVisits==null || monthVisits==""){
                    monthVisits="0";
                }
                openid =item.openid;
                if(openid==null || openid==""){
                    openid="---";
                }
                hopeJob =item.hopeJob;
                if(hopeJob==null || hopeJob==""){
                    hopeJob="---";
                }
                hopeCity =item.hopeCity;
                if(hopeCity==null || hopeCity==""){
                    hopeCity="---";
                }
                address =item.address;
                if(address==null || address==""){
                    address="---";
                }
                expectMoney =item.expectMoney;
                if(expectMoney==null || expectMoney==""){
                    expectMoney="---";
                }
                resumeState =item.resumeState;
                if(resumeState== "1"){
                    resumeState="可见简历";
                }else if(resumeState == "2"){
                    resumeState="隐藏简历";
                }else {
                    resumeState="未知";
                }
                accountState =item.accountState;
                if(accountState== "1"){
                    accountState="账号正常";
                }else if(accountState == "2"){
                    accountState ="账号冻结";
                }else if(accountState == "3"){
                    accountState ="账号删除";
                }else {
                    accountState ="未知";
                }
                recordSchool =item.recordSchool;
                if(recordSchool==null || recordSchool==""){
                    recordSchool="---";
                }
                onceDo =item.onceDo;
                if(onceDo==null || onceDo==""){
                    onceDo="---";
                }
                myHometown =item.myHometown;
                if(myHometown==null || myHometown==""){
                    myHometown="---";
                }
                resumeNumber =item.resumeNumber;
                if(resumeNumber==null || resumeNumber==""){
                    resumeNumber="未投简历";
                }
                jobExperience = item.jobExperience;
                if(jobExperience==null || jobExperience==""){
                    jobExperience="---";
                }
                state = item.state;
                if(state==null || state==""){
                    state="---";
                }
                myselfInfo = item.myselfInfo;
                if(myselfInfo==null || myselfInfo==""){
                    myselfInfo="---";
                }
                createTime = item.createTime;
                photos = item.photos;
                if(photos==null || photos==""){
                    photos="无";
                }else{
                    photos="有";
                }
                $modal.find('[name=id]').val(id);
                $modal.find('[name=name]').val(name);
                $modal.find('[name=sex]').val(sex);
                $modal.find('[name=age]').val(age);
                $modal.find('[name=phone]').val(telephone);
                $modal.find('[name=create_time]').val(createTime);
                $modal.find('[name=month_visits]').val(monthVisits);
                $modal.find('[name=openid]').val(openid);
                $modal.find('[name=hope_job]').val(hopeJob);
                $modal.find('[name=job_experience]').val(jobExperience);
                $modal.find('[name=hope_city]').val(hopeCity);
                $modal.find('[name=address]').val(address);
                $modal.find('[name=expect_money]').val(expectMoney);
                $modal.find('[name=state]').val(state);
                $modal.find('[name=resume_state]').val(resumeState);
                $modal.find('[name=account_state]').val(accountState);
                $modal.find('[name=record_school]').val(recordSchool);
                $modal.find('[name=once_do]').val(onceDo);
                $modal.find('[name=my_hometown]').val(myHometown);
                $modal.find('[name=myself_info]').val(myselfInfo);
                $modal.find('[name=resume_number]').val(resumeNumber);
                $modal.find('[name=photos]').val(photos);

            }
        });
    }).on('success.form.fv', function (e) {
        e.preventDefault();

        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();
        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'personal/updatePersoanlById',
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


function btnExport() {



    //导出当前页
    $('#sorter').tableExport({

        type: 'excel',

        escape: 'false',

        fileName: '导出求职者列表',

        exportDataType: 'all'

    });

}


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
        name: $('.navbar-form').find('[name=name]').val(),
        phone: $('.navbar-form').find('[name=phone]').val(),
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "personal/getAllPersonal",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");

            if (res.success) {
                // 清空表格
                $('tbody').empty();
                // 添加数据到表格
                var list = res.data.list;
                console.log(JSON.stringify(list));
                var id, name, sex, age, phone, month_visits, openid, hope_job, hope_city,
                    address, expect_money, resume_state, account_state, record_school, once_do,
                    my_hometown, job_experience, state, myself_info, resume_number, create_time, photos;
                $.each(list, function (index, item) {
                    console.log("得到的数据：" + item);
                    id = item.id;
                    name = item.name;
                    if(name==null || name==""){
                        name="---";
                    }
                    sex = item.sex;
                    if(sex==null || sex==""){
                        sex="---";
                    }
                    age = item.age;
                    if(age==null || age==""){
                        age="---";
                    }
                    phone = item.phone;
                    if(phone==null || phone==""){
                        phone="---";
                    }
                    month_visits =item.month_visits;
                    if(month_visits==null || month_visits==""){
                        month_visits="0";
                    }
                    openid =item.openid;
                    if(openid==null || openid==""){
                        openid="---";
                    }
                    hope_job =item.hope_job;
                    if(hope_job==null || hope_job==""){
                        hope_job="---";
                    }
                    hope_city =item.hope_city;
                    if(hope_city==null || hope_city==""){
                        hope_city="---";
                    }
                    address =item.address;
                    if(address==null || address==""){
                        address="---";
                    }
                    expect_money =item.expect_money;
                    if(expect_money==null || expect_money==""){
                        expect_money="---";
                    }
                    resume_state =item.resume_state;
                    if(resume_state== "1"){
                        resume_state="可见简历";
                    }else if(resume_state == "2"){
                        resume_state="隐藏简历";
                    }else {
                        resume_state="未知";
                    }
                    account_state =item.account_state;
                    if(account_state== "1"){
                        account_state="账号正常";
                    }else if(account_state == "2"){
                        account_state ="账号冻结";
                    }else if(account_state == "3"){
                        account_state ="账号删除";
                    }else {
                        account_state ="未知";
                    }
                    record_school =item.record_school;
                    if(record_school==null || record_school==""){
                        record_school="---";
                    }
                    once_do =item.once_do;
                    if(once_do==null || once_do==""){
                        once_do="---";
                    }
                    my_hometown =item.my_hometown;
                    if(my_hometown==null || my_hometown==""){
                        my_hometown="---";
                    }

                    resume_number =item.resume_number;
                    if(resume_number==null || resume_number==""){
                        resume_number="未投简历";
                    }

                    job_experience = item.job_experience;
                    if(job_experience==null || job_experience==""){
                        job_experience="---";
                    }
                    state = item.state;
                    if(state==null || state==""){
                        state="---";
                    }
                    myself_info = item.myself_info;
                    if(myself_info==null || myself_info==""){
                        myself_info="---";
                    }
                    create_time = item.create_time;

                    photos = item.photos;
                    if(photos==null || photos==""){
                        photos="无";
                    }else{
                        photos="有";
                    }

                    var tableHtml = "";
                    tableHtml += '<tr>' +
                        '<td><input type="checkbox" name="checkPersonal" value="'+id+'"/></td>'+
                        '<td>' + id + '</td>' +
                        '<td>' + name + '</td>' +
                        '<td>' + sex + '</td>' +
                        '<td>' + age + '</td>' +
                        '<td>' + phone + '</td>' +
                        '<td>' + create_time + '</td>' +
                        '<td>' + month_visits + '</td>' +
                        '<td>' + openid + '</td>' +
                        '<td>' + hope_job + '</td>' +
                        '<td>' + job_experience + '</td>' +
                        '<td>' + hope_city + '</td>' +
                        '<td>' + address + '</td>' +
                        '<td>' + expect_money + '</td>' +
                        '<td>' + state + '</td>' +
                        '<td>' + resume_state + '</td>' +
                        '<td>' + account_state + '</td>' +
                        '<td>' + record_school + '</td>' +
                        '<td>' + once_do + '</td>' +
                        '<td>' + my_hometown + '</td>' +
                        '<td>' + myself_info + '</td>' +
                        '<td>' + resume_number + '</td>' +
                        '<td>' + photos + '</td>';
                    tableHtml += '</div></tr>';
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
    loadData(page,size);
}

/**
 * 冻结求职者账户操作
 */
function frozenAccountExport() {
    // 显示动画
    var personalCheck = $("input:checkbox[name='checkPersonal']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');
    alert("选中的checkbox的值为："+personalCheck);

    $.LoadingOverlay("show");
    var data = {
        personalCheck : personalCheck
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "personal/frozenAccount",
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
 * 删除求职者账户操作
 */
function deleteAccountExport() {
    // 显示动画
    var personalCheck = $("input:checkbox[name='checkPersonal']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');
    alert("选中的checkbox的值为："+personalCheck);

    $.LoadingOverlay("show");
    var data = {
        personalCheck : personalCheck
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "personal/deletePersonal",
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

function lookPhotoExport() {
    window.location.href=$('#baseUrl').attr('href') +"personalPhoto-manage?id="+$("input:checkbox[name='checkPersonal']:checked").val();
}


