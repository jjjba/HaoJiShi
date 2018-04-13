// JavaScript Document
var od =[];
$(document).ready(function() {

    loadHopeJob();
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
function loadHopeJob() {
    $.ajax({
        url:'/personal/getPersonalInfo',
        type:'POST',
        success:function (res) {
            var onceDo =res.data.onceDo;
            if(onceDo == null || onceDo == ""){
                var j =onceDo.split(",");
                for(var i = 0;i < j.length;i++){
                    od.push(j[i]);

                    $('.clearfix').append('<li id="'+val+'">'+j[i]+' <a href="" ' +
                        'onclick="deleteJob(&quot;'+val+'&quot;,&quot;'+j[i]+'&quot;)">' +
                        '<img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');                }
            }
        }
    })
}
function addHopeJob(val) {
    var hopeJob =$('#'+val).attr("data");
    if(hopeJob == od[0] || hopeJob == od[1] ||hopeJob == od[2] ||hopeJob == od[3] ||hopeJob == od[4] ||hopeJob == od[5]){
        $(".toolbarframe02").show()
        setTimeout('$(".toolbarframe02").hide()',1000);
    }else {
        if(od.length > 5){
            $(".toolbarframe03").show()
            setTimeout('$(".toolbarframe03").hide()',1000);
        }else {
            console.log("val====="+val)
            $('.clearfix').append('<li class="'+val+'">'+hopeJob+' <a href="" ' +
                'onclick="deleteJob(&quot;'+val+'&quot;,&quot;'+hopeJob+'&quot;)">' +
                '<img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
            od.push(hopeJob);
        }
    }

}
function deleteJob(val,hopeJob) {
    $("."+val).hide();
    od.splice($.inArray(hopeJob,od),1);
}
function quedingbaocun() {
    $.ajax({
        url:"/personal/updatePersonalOnceDo",
        type:"POST",
        data : {
            onceDo : od.toString()
        },
        success : function () {
            window.location.href="/transition/my_resume";
        }
    })
}

function goBack() {
    window.location.href="/transition/my_resume";
}