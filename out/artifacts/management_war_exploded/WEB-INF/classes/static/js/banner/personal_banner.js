/**
 * @author 梁闯
 * @date 2018/03/14 14.39
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
    $('#editPersonalModal').on('hidden.bs.modal', function () {
        $(this).find(":file").each(function (i, el) {
            $(el).fileinput("clear");
        })
    }).on('show.bs.modal', function (event) {
        initFileInput("#editPersonalModal input[name=image_url][type=file]");
        var id =$("input:checkbox[name='checkPersonal']:checked").val();
        var $modal = $(this);
        $modal.find('[name=id]').val(id);
        $.getJSON($('#baseUrl').attr('href') + 'banner/getBannerById?id=' + id, function (res) {
            if (res.success) {
                var item = res.data;
                var url =item.url;
                if(url == null || url ==""){
                    url ="未设置";
                }
                $modal.find('[name=sorts]').val(item.sort);
                $modal.find('[name=url]').val(url);
                $modal.find('[name=note]').val(item.note);
                if (item.imageUrl != null && item.imageUrl !="") {
                    $modal.find('[name=photoN]').attr("src",item.imageUrl);
                }else{
                    $modal.find('[name=photoN]').css("display","none");
                }
            }
        });
    }).on('success.form.fv', function (e) {
        e.preventDefault();
        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();
        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'banner/updatePersonalBannerById',
            type: 'POST',
            data: formData,
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

    //添加banner
    $('#addPersonalModal').on('hidden.bs.modal', function () {
        $(this).find(":file").each(function (i, el) {
            $(el).fileinput("clear");
        })
    }).on('success.form.fv', function (e) {
        e.preventDefault();
        var $form = $(e.target),
            fv = $form.data('formValidation'),
            $modal = $form.parent().parent().parent().parent();
        var formData = new FormData($form[0]);
        $.ajax({
            url: $('#baseUrl').attr('href') + 'banner/insertPersonalBanner',
            type: 'POST',
            data: formData,
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
    $.LoadingOverlay("show");
    var data = {
        page: page,
        size: size,
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "banner/getAllPersonalBanner",
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
                    var id,sort, imageUrl, url, note;
                    $.each(list, function (index, item) {
                        id =item.id;
                        sort = item.sort;
                        imageUrl = item.imageUrl;
                        url = item.url;
                        if(url == null || url ==""){
                            url ="未设置";
                        }
                        note = item.note;
                        if(note == null || note ==""){
                            note ="暂无";
                        }

                        var tableHtml = "";
                        tableHtml += '<tr>' +
                            '<td><input type="checkbox" name="checkPersonal" value="'+id+'"/></td>'+
                            '<td>' + sort + '</td>' +
                            '<td><img src="' + imageUrl + '" style="height: 150px;width: 200px;" /> </td>' +
                            '<td>' + url + '</td>'+
                            '<td>' + note + '</td>';
                        tableHtml += '</tr>';
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

/**
 * 删除banner
 */
function deletePersonalBanner() {
    var data ={
        id : $("input:checkbox[name='checkPersonal']:checked").val(),
    }
    $.LoadingOverlay("show");
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "banner/deleteBannerById",
        data: data,
        success: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            alert(res.msg);
        },
        error: function (res) {
            // 关闭动画
            $.LoadingOverlay("hide");
            alert(res.msg);
        }
    });
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
            $('#editPersonalModal').find('form')
                .data('formValidation')         // Get the validator instance
                .revalidateField($(e.target));
        });
}
