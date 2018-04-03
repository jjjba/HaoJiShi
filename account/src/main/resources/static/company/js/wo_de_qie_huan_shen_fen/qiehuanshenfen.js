$(document).ready(function() {
    $.ajax({
        type:"POST",
        url:"/company/getUser",
        success:function (msg) {
            if(msg.data.type == 1){
                $("#num2").removeClass("cuyuis");
                $("#num1").addClass("cuyuis");
                updateDisplyIdValues();
                $("#displyIds").val("1");
            }
            if(msg.data.type == 2){
                $("#num1").removeClass("cuyuis");
                $("#num2").addClass("cuyuis");
                updateDisplyIdValue();
                $("#displyIds").val("2");
            }
        }
    })
})


function fanhui() {
    window.location.href="/transition/go_wo_de";
}
function updateDisplyIdValue() {
    $("#displyId").val("1");
}
function updateDisplyIdValues() {
   $("#displyId").val("2");
}
function qiehuan() {
    var DisplyIdValue = $("#displyId").val();
    var DisplyIdValues = $("#displyIds").val();
    if(DisplyIdValue == DisplyIdValues){
        $('.shenfen').show();
        setTimeout('$(".shenfen").hide()',1000);
    }else{
        $.ajax({
            type:"Post",
            url:"/company/updateShenfen",
            data:{shenfen:DisplyIdValue},
            success:function (msg) {
                if(msg.data == 1){
                    $('.shenfenYes').show();
                    setTimeout('$(".shenfenYes").hide()',1000);
                }else{
                    $('.shenfenNo').show();
                    setTimeout('$(".shenfenNo").hide()',1000);
                }
            }
        })
    }
   
}