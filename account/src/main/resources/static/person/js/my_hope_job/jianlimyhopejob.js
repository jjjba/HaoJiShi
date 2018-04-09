// JavaScript Document
var hj =[];
$(document).ready(function() {

    $(".fllfzuos ul li").click(function(){

        $(".fllfzuos ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

    });
    $(".fllfzuos02 ul li").click(function(){

        $(".fllfzuos02 ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

    });
    $(".jishi").click(function(){
        $("#guanli").hide();
        $("#jishi").show();
        $("#houqin").hide();
        $("#peixun").hide();
        $("#qingting").hide();
    });
    $(".guanli").click(function(){
        $("#guanli").show();
        $("#jishi").hide();
        $("#houqin").hide();
        $("#peixun").hide();
        $("#qingting").hide();
    });
    $(".qingting").click(function(){
        $("#qingting").show();
        $("#guanli").hide();
        $("#jishi").hide();
        $("#houqin").hide();
        $("#peixun").hide();
    });
    $(".houqin").click(function(){
        $("#houqin").show();
        $("#qingting").hide();
        $("#guanli").hide();
        $("#jishi").hide();
        $("#peixun").hide();
    });
    $(".peixun").click(function(){
        $("#peixun").show();
        $("#qingting").hide();
        $("#guanli").hide();
        $("#jishi").hide();
        $("#houqin").hide();
    });

});

function addHopeJob(val) {
    var hopeJob =$('#'+val).attr("data");
    if(hopeJob == hj[0] || hopeJob == hj[1] ||hopeJob == hj[2] ||hopeJob == hj[3] ||hopeJob == hj[4] ||hopeJob == hj[5]){
        $(".toolbarframe02").show()
        setTimeout('$(".toolbarframe02").hide()',1000);
    }else {
        if(hj.length > 5){
            $(".toolbarframe03").show()
            setTimeout('$(".toolbarframe03").hide()',1000);
        }else {
            $('.clearfix').append('<li id="'+val+'">'+hopeJob+' <a href="" ' +
                'onclick="deleteJob(&quot;'+val+'&quot;,&quot;'+hopeJob+'&quot;)">' +
                '<img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
            hj.push(hopeJob);
        }
    }

}
function deleteJob(val,hopeJob) {
    $("#"+val).remove();
    hj.splice($.inArray(hopeJob,hj),1);
}
function quedingbaocun() {
    sessionStorage.setItem("hopeJob",hj.join(","));
    window.location.href="/transition/go_wan_shan_xin_xi";
}

function goBack() {
    window.location.href="/transition/go_wan_shan_xin_xi";
}