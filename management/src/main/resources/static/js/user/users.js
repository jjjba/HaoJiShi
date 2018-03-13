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
        var button = $(event.relatedTarget);
        var id = button.data('id');
        ids = id;
        var $modal = $(this);
        $modal.find('[name=id]').val(id);
        $.getJSON($('#baseUrl').attr('href') + 'users/findById?id=' + id  , function (res) {
            if (res.success) {
                var item = res.data;
                $modal.find('[name=pid]').val(item.pid);
                //姓名
                $modal.find('[name=name]').val(item.name);
                //性别
                var sex=item.sex;
                $modal.find('[name=sex]').val(sex);
                $modal.find('[name=age]').val(item.age);
                $modal.find('[name=phone]').val(item.phone);
                $modal.find('[name=state]').val(item.state);
                $modal.find('[name=info]').val(item.info);
                $modal.find('[name=pid]').val(item.pid);
            }
        });
    }).on('success.form.fv', function (e) {
        e.preventDefault();

        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();
        // var name = $modal.find('[name=name]').val();
        // var sex = $modal.find('[name=sex]').val();
        // var state = $modal.find('[name=state]').val();
        // var age = $modal.find('[name=age]').val();
        // var phone = $modal.find('[name=phone]').val();
        // var info = $modal.find('[name=info]').val();
        // var pid = $modal.find('[name=pid]').val();
        // var id = $modal.find('[name=id]').val();
        // alert(name);
        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'users/updateUserById',
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
        url: $('#baseUrl').attr('href') + "users/list",
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
                var id, name, sex, age, phone, job_experience, state, info, create_time;
                $.each(list, function (index, item) {
                    console.log("得到的数据：" + item);
                    id = item.id;
                    name = item.name;
                    if(name==null || name==""){
                        name="";
                    }
                    sex = item.sex;
                    if(sex=="1"){
                        sex="男";
                    }else if(sex=="2"){
                        sex="女";
                    }else{
                        sex="";
                    }
                    age = item.age;
                    if(age==null || age==""){
                        age="";
                    }
                    phone = item.phone;
                    if(phone==null || phone==""){
                        phone="";
                    }
                    job_experience = item.job_experience;
                    if(job_experience==null || job_experience==""){
                        job_experience="";
                    }
                    state = item.state;
                    if(state==null || state==""){
                        state="";
                    }
                    info = item.info;
                    if(info==null || info==""){
                        info="";
                    }
                    create_time = item.create_time;

                    var tableHtml = "";
                    tableHtml += '<tr>' +
                        '<td>' + id + '</td>' +
                        '<td>' + name + '</td>' +
                        '<td>' + sex + '</td>' +
                        '<td>' + age + '</td>' +
                        '<td>' + phone + '</td>' +
                        '<td>' + job_experience + '</td>' +
                        '<td>' + state + '</td>' +
                        '<td>' + info + '</td>' +
                        '<td>' + create_time + '</td>';
                    tableHtml += '<td> <div class="btn-group">';
                    tableHtml +='<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal" data-id="' + item.id + '" >修改</button>';
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
