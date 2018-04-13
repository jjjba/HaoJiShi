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




    
    function loadBanner() {
        
    }



function tell() {
    alert("电话联系")
}



// window.onload =function () {
//     $('#loading').remove();
// }

