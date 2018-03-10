// window.onload = function() {
    // $("#close").click(function(){
    //     $(".guanggao").hide();
    // })
    // $(".fenlei span").click(function(){
    //     $(this).addClass("active");
    //     $(this).siblings().removeClass("active");
    // });

// };

// 默认页码
var page = 1;

// 默认数量
var size = 10;


$(function () {
    //加载动画
    setTimeout("loading()",5000);
    //读取数据
    loadData();
    //banner初始化设置时长
    $('#myCarousel').carousel({interval:3000});
});

    // $(".main_visual").hover(function(){
    //     $("#btn_prev,#btn_next").fadeIn()
    // },function(){
    //     $("#btn_prev,#btn_next").fadeOut()
    // });
    //
    // $dragBln = false;
    //
    // $(".main_image").touchSlider({
    //     flexible : true,
    //     speed : 200,
    //     btn_prev : $("#btn_prev"),
    //     btn_next : $("#btn_next"),
    //     paging : $(".flicking_con span"),
    //     counter : function (e){
    //         $(".flicking_con span").removeClass("on").eq(e.current-1).addClass("on");
    //     }
    // });
    //
    // $(".main_image").bind("mousedown", function() {
    //     $dragBln = false;
    // });
    //
    // $(".main_image").bind("dragstart", function() {
    //     $dragBln = true;
    // });
    //
    // $(".main_image a").click(function(){
    //     if($dragBln) {
    //         return false;
    //     }
    // });
    //
    // timer = setInterval(function(){
    //     $("#btn_next").click();
    // }, 3000);
    //
    // $(".main_visual").hover(function(){
    //     clearInterval(timer);
    // },function(){
    //     timer = setInterval(function(){
    //         $("#btn_next").click();
    //     },5000);
    // });
    //
    //
    // $(".main_image").bind("touchstart",function(){
    //     clearInterval(timer);
    // }).bind("touchend", function(){
    //     timer = setInterval(function(){
    //         $("#btn_next").click();
    //     }, 5000);



    function loadData() {

        var data = {
        page: page,
        size: size,
        // name: $('.navbar-form').find('[name=name]').val(),
        // phone: $('.navbar-form').find('[name=phone]').val(),
        };
        $.ajax({
            type: "POST",
            url: "personal/getAllPersonal",
            data: data,
            success: function (res) {
                alert("成功了")
                if (res.success) {
                    // 清空表格
                    $('tbody').empty();
                    // 添加数据到表格
                    var list = res.data.list;
                    console.log("======"+res.data);
                    var id, name, sex, age, phone, job_experience, state, address, hope_job, expect_money, avatar;
                    $.each(list, function (index, item) {
                        console.log("得到的数据：" + item);
                        id = item.id;
                        phone = item.phone;
                        name =item.name;
                        sex =item.sex;
                        age =item.age;
                        job_experience =item.job_experience;
                        state =item.state;
                        address =item.address;
                        hope_job =item.hope_job;
                        expect_money =item.expect_money;
                        avatar =item.avatar;
                        if (phone == null || phone == "") {
                            phone = "未填写";
                        }
                        if (name == null || name == "") {
                            name = "未填写";
                        }
                        if (sex == null || sex == "") {
                            sex = "未填写";
                        }
                        if (age == null || age == "") {
                            age = "未填写";
                        }
                        if (job_experience == null || job_experience == "") {
                            job_experience = "未填写";
                        }
                        if (state == null || state == "") {
                            state = "未填写";
                        }
                        if (address == null || address == "") {
                            address = "未填写";
                        }
                        if (hope_job == null || hope_job == "") {
                            hope_job = "未填写";
                        }
                        if (expect_money == null || expect_money == "") {
                            expect_money = "未填写";
                        }
                        if (avatar == null || avatar == "") {
                            avatar = "未上传";
                        }

                        var tableHtml = "";
                        tableHtml +=
                            '<tr>' +
                            '<td rowspan="4"><img src="'+avatar+'" style="width: 60px;height: 60px;"/></td>' +
                            '<td>' + name + '</td>' +
                            '<td>' + sex + '</td>' +
                            '<td>' + expect_money + '</td>' +
                            '</tr>'+
                            '<tr>'+
                            '<td>' + job_experience + '</td>' +
                            '<td>' + state + '</td>' +
                            '<td>' + address + '</td>' +
                            '</tr>'+
                            '<tr>'+
                            '<td>意向工作:</td>' +
                            '<td>' + hope_job + '</td>' +
                            '</tr>'+
                            '<tr>'+
                            '<td colspan="4"><a href="tel:'+phone+'" >电话联系</a></td>'+
                            '</tr>';
                        $('tbody').append(tableHtml);
                    });

                    // 初始化分页控件
                    // $("#pagination").bs_pagination({
                    //     currentPage: res.data.pageNum,
                    //     totalRows: res.data.total,
                    //     rowsPerPage: res.data.pageSize,
                    //     totalPages: res.data.pages,
                    //     onChangePage: function (event, data) {
                    //         page = data.currentPage;
                    //         size = data.rowsPerPage;
                    //
                    //         loadData(data.currentPage, data.rowsPerPage);
                    //     }
                    // });

                } else {
                    $('tbody').empty();
                    // $("#pagination").empty();
                    // $("#pagination").removeClass("well");
                }
            }
        });
    }
    
    function loadBanner() {
        
    }

function loading(){
    var box=document.getElementById("loading");
    box.style.display="none";
}

function tell() {
    alert("电话联系")
}



// window.onload =function () {
//     $('#loading').remove();
// }

