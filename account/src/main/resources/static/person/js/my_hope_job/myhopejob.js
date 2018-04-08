// JavaScript Document
var hj =[];
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
            var job =res.data.hope_job;
            if(job == null || job == ""){
                var j =job.split(",");
                for(var i = 0;i < j.length;i++){
                    hj.push(j[i]);

                    $('.clearfix').append('<li id="'+val+'">'+j[i]+' <a href="" ' +
                        'onclick="deleteJob(&quot;'+val+'&quot;,&quot;'+j[i]+'&quot;)">' +
                        '<img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');                }
            }
        }
    })
}
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