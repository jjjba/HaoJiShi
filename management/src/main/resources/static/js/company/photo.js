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

var id=0;

$(function () {
    id = GetQueryString("id");
    $.getJSON($('#baseUrl').attr('href') + 'company/findCompanyInfoById?id=' + id  , function (res) {
        if (res.success) {
            var item = res.data;
            id=item.company_id;
        }
    });
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
        initFileInput("#editModal input[name=image_url][type=file]");
        var button = $(event.relatedTarget);
        var $modal = $(this);
        $modal.find('[name=id]').val(id);
        var photo = button.data('photo');
        $modal.find('[name=photo]').val(photo);
        $modal.find('[name=photoN]').attr("src",photo);
        console.log("photo"+photo);
        console.log("id"+id);
    }).on('success.form.fv', function (e) {
        e.preventDefault();

        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();
        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'company/updatePhoto',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false
        }).then(function (res) {
            if (res.success) {
                // 隐藏模态框
                $modal.modal('hide');
                // 重新加载数据
                window.location.reload();
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
        initFileInput("#editModal input[name=image_url][type=file]");
        var button = $(event.relatedTarget);
        var $modal = $(this);
        $modal.find('[name=id]').val(id);
    }).on('success.form.fv', function (e) {
        e.preventDefault();

        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();
        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'company/insertPhoto',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false
        }).then(function (res) {
            if (res.success) {
                // 隐藏模态框
                $modal.modal('hide');
                // 重新加载数据
                window.location.reload();
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
    };
    $.getJSON($('#baseUrl').attr('href') + 'company/findCompanyInfoById?id=' + id  , function (res) {
        if (res.success) {
            $.LoadingOverlay("hide");
            var item = res.data;
            var photoArr=item.company_photo.split(',');
            if(photoArr.length>=5){
                $("#btnAdd").css("display","none");
            }
            for(var i=0;i<photoArr.length;i++){
                var tableHtml = "";
                tableHtml += '<tr>' +
                    '<td><img src="' + photoArr[i] + '" width="80px" height="50px" /> </td>';
                tableHtml += '<td> <div class="btn-group">';
                tableHtml +='<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal" data-photo="' + photoArr[i] + '" data-id="' + id + '" >修改</button>';
                tableHtml += '</div></td></tr>';
                $('tbody').append(tableHtml);
            }
        }
    });
}
function search() {
    loadData(page, size);
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

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
