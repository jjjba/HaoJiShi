$(function () {
    $(".companyName").focus(function () {
        $(".companyName").keyup(function(){
            var length=this.value.length;
            if(length > 0){
                $('.cancel').hide();
                $('.wancheng').show();
            }else {
                $('.cancel').show();
                $('.wancheng').hide();
            }
        });
    });
//     $(".companyName").blur(function () {
//         var name =$('.companyName').val();
//         console.log(name+"============")
//         if(name != null && name != ""){
//             $('.cancel').hide();
//             $('.wancheng').show();
//         }else {
//             $('.cancel').show();
//             $('.wancheng').hide();
//         }
//     });
});
function wancheng() {
    var name =$('.companyName').val();
    $.ajax({
        url:"/position/getPositionByName",
        type:"POST",
        data:{
            name : name,
        },
        success :function (res) {

        }
    })
}
function cancel() {
    window.location.href="/transition/transition_all_position";
}
function qingchu() {
    $('.companyName').val("");
}