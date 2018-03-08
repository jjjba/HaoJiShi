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
    // setTimeout("loading()",5000);
    changeImg();

    // loadData();
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



    // function loadData() {
    //     //显示动画
    //     $("#loading").show();
    //     // var data = {
    //     // page: page,
    //     // size: size,
    //     // name: $('.navbar-form').find('[name=name]').val(),
    //     // phone: $('.navbar-form').find('[name=phone]').val(),
    //     // };
    //     $.ajax({
    //         type: "POST",
    //         url: "personal/getAllPersonal",
    //         // data: data,
    //         success: function (res) {
    //             // 关闭动画
    //             $("#loading").hide();
    //             alert("成功了")
    //             if (res.success) {
    //                 // 清空表格
    //                 $('tbody').empty();
    //                 // 添加数据到表格
    //                 var list = res.data.list;
    //                 console.log(list);
    //                 var id, name, sex, age, phone, job_experience, state, info, create_time;
    //                 $.each(list, function (index, item) {
    //                     console.log("得到的数据：" + item);
    //                     id = item.id;
    //                     company_fu_ze_ren = item.company_fu_ze_ren;
    //                     if (company_fu_ze_ren == null || company_fu_ze_ren == "") {
    //                         company_fu_ze_ren = "";
    //                     }
    //
    //                     zhi_wu = item.zhi_wu;
    //                     if (zhi_wu == null || zhi_wu == "") {
    //                         zhi_wu = "";
    //                     }
    //                     phone = item.phone;
    //                     if (phone == null || phone == "") {
    //                         phone = "";
    //                     }
    //                     see_count = item.see_count;
    //                     if (see_count == null || see_count == "") {
    //                         see_count = "0";
    //                     }
    //                     company_name = item.company_name;
    //                     if (company_name == null || company_name == "") {
    //                         company_name = "";
    //                     }
    //                     company_scale = item.company_scale;
    //                     if (company_scale == null || company_scale == "") {
    //                         company_scale = "";
    //                     }
    //                     company_type = item.company_type;
    //                     if (company_type == null || company_type == "") {
    //                         company_type = "";
    //                     }
    //                     company_city = item.company_city;
    //                     if (company_city == null || company_city == "") {
    //                         company_city = "";
    //                     }
    //                     position_count = item.position_count;
    //                     if (position_count == null || position_count == "") {
    //                         position_count = "0";
    //                     }
    //
    //                     kuaizhao_money = item.kuaizhao_money;
    //                     if (kuaizhao_money == null || kuaizhao_money == "") {
    //                         kuaizhao_money = "0";
    //                     }
    //
    //                     kuaizhao_date = item.kuaizhao_date;
    //                     if (kuaizhao_date == null || kuaizhao_date == "") {
    //                         kuaizhao_date = "0";
    //                     }
    //                     create_time = item.create_time;
    //
    //                     var tableHtml = "";
    //                     tableHtml += '<tr>' +
    //                         '<td>' + id + '</td>' +
    //                         '<td>' + phone + '</td>' +
    //                         '<td>' + company_fu_ze_ren + '</td>' +
    //                         '<td>' + zhi_wu + '</td>' +
    //                         '<td>' + create_time + '</td>' +
    //                         '<td>' + position_count + '</td>' +
    //                         '<td>' + see_count + '</td>' +
    //                         '<td>' + company_name + '</td>' +
    //                         '<td>' + kuaizhao_money + '</td>' +
    //                         '<td>' + kuaizhao_date + '</td>' +
    //                         '<td>' + company_scale + '</td>' +
    //                         '<td>' + company_type + '</td>' +
    //                         '<td>' + company_city + '</td>';
    //                     tableHtml += '<td> <div class="btn-group">';
    //                     tableHtml += '<button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#editModal" data-id="' + item.id + '" >修改</button>';
    //                     tableHtml += '<button class="btn btn-sm btn-primary" onclick="trunUrl(\'' + $('#baseUrl').attr('href') + 'companyPhoto-manage?id=' + item.id + '\')">照片管理</button>';
    //                     tableHtml += '<button class="btn btn-sm btn-primary" onclick="trunUrl(\'' + $('#baseUrl').attr('href') + 'position-company?user_id=' + item.id + '\')">职位管理</button>';
    //                     tableHtml += '</div></td></tr>';
    //                     $('tbody').append(tableHtml);
    //                 });
    //
    //                 // 初始化分页控件
    //                 $("#pagination").bs_pagination({
    //                     currentPage: res.data.pageNum,
    //                     totalRows: res.data.total,
    //                     rowsPerPage: res.data.pageSize,
    //                     totalPages: res.data.pages,
    //                     onChangePage: function (event, data) {
    //                         page = data.currentPage;
    //                         size = data.rowsPerPage;
    //
    //                         loadData(data.currentPage, data.rowsPerPage);
    //                     }
    //                 });
    //
    //             } else {
    //                 $('tbody').empty();
    //                 $("#pagination").empty();
    //                 $("#pagination").removeClass("well");
    //             }
    //         }
    //     });
    // }

function loading(){
    var box=document.getElementById("loading");
    box.style.display="none";
}


//首页轮播banner循环js
function changeImg(){
    /*获取图片和索引的数组*/
    var $imgs=$("#ad_img li");
    var $nums=$("#ad_num li");

    var isStop=false;
    var index=0;

    $nums.eq(index).addClass("numsover");
    $nums.eq(index).siblings().removeClass("numsover");
    $imgs.eq(index).show();

    /*鼠标悬停在数字上的事件*/
    $nums.mouseover(function(){
        isStop=true;
        /*先把数字的背景改了*/
        $(this).addClass("numsover").siblings().removeClass("numsover");

        /*图片的索引和数字的索引是对应的，所以获取当前的数字的索引就可以获得图片，从而对图片进行操作*/
        index=$nums.index(this);
        $imgs.eq(index).show("slow");
        $imgs.eq(index).siblings().hide("slow");
    }).mouseout(function(){isStop=false});
    /*设置循环*/
    setInterval(function(){
        if(isStop) return;
        if(index>=5) index=-1;
        index++;

        $nums.eq(index).addClass("numsover").siblings().removeClass("numsover");

        $imgs.eq(index).show("slow");
        $imgs.eq(index).siblings().hide("slow");

    },5000);
}



// window.onload =function () {
//     $('#loading').remove();
// }

