/**
 * @author 梁闯
 * @date 2018/03/15 15.47
 */

// 默认页码
var page = 1;

// 默认数量
var size = 10;

var id=0;

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

    $("#checkAll").click(function () {
        $("input[name='checkPersonal']:checkbox").prop("checked", this.checked);
    });

});//初始化


/**
 * 加载数据
 * @param page 页码
 * @param size 大小
 */
function loadData(page, size) {
    // 显示动画
    id = GetQueryString("id");
    $.LoadingOverlay("show");
    $.getJSON($('#baseUrl').attr('href') + 'company/getCompanyPhotoById?id=' + id  , function (res) {
        if (res.success) {
            $.LoadingOverlay("hide");
            var item = res.data;
            var photoArr=item.companyPhoto.split(',');
            if(photoArr.length>=5){
                $("#btnAdd").css("display","none");
            }
            for(var i=0;i<photoArr.length;i++){
                var tableHtml = "";
                tableHtml += '<tr>' +
                    '<td><input type="checkbox" name="checkPersonal" value="'+photoArr[i]+'&&id='+id+'"/></td>'+
                    '<td><img src="' + photoArr[i] + '" style="width:200px;height:150px" /> </td>';
                tableHtml += '</tr>';
                $('tbody').append(tableHtml);
            }
        }
    });
}

function deletePhoto() {
    var photoCheck = $("input:checkbox[name='checkPersonal']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');
    var id =photoCheck.substring(photoCheck.indexOf("=")+1);
    var photoUrl =photoCheck.substring(0, photoCheck.indexOf("&"));
    $.LoadingOverlay("show");
    var data = {
        photoUrl : photoUrl,
        id : id
    };
    $.ajax({
        type: "GET",
        url: $('#baseUrl').attr('href') + "company/deleteCompanyPhoto",
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
