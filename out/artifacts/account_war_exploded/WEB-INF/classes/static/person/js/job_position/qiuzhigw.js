// JavaScript Document
var jp =[];
$(document).ready(function() {

loadPosition();
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
    var pos =$('#'+val).attr("data");
    if(pos == jp[0] || pos == jp[1] ||pos == jp[2] ||pos == jp[3] ||pos == jp[4] ||pos == jp[5]){
        $(".toolbarframe02").show()
        setTimeout('$(".toolbarframe02").hide()',1000);
    }else {
        if(jp.length > 5){
            $(".toolbarframe03").show()
            setTimeout('$(".toolbarframe03").hide()',1000);
        }else {
            $('.clearfix').append('<li id="'+val+'">'+pos+' <a href="" ' +
                'onclick="deleteJob(&quot;'+val+'&quot;,&quot;'+pos+'&quot;)">' +
                '<img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
            jp.push(pos);
        }
    }

}
function deleteJob(val,pos) {
    $("#"+val).remove();
    jp.splice($.inArray(pos,jp),1);
    console.log("===="+jp)
}

function loadPosition(){
    $.ajax({
        url:'/personal/getPersonalHopePosition',
        type:'POST',
        success:function (res) {
            var job =res.data.positions;
            if(job == null || job == ""){
                var j =job.split(",");
                for(var i = 0;i < j.length;i++){
                    jp.push(j[i]);
                    console.log("j[i]============"+j[i]);
                    $('.clearfix').append('<li id="'+val+'">'+j[i]+' <a href="" ' +
                        'onclick="deleteJob(&quot;'+val+'&quot;,&quot;'+j[i]+'&quot;)">' +
                        '<img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
                }
            }
        }
    })
}

function quedingbaocun() {
    $.ajax({
        url:"/personal/updatePersonalByPersonalId",
        type:"POST",
        data : {
            hopeJob : jp.join(",")
        },
        success : function () {
            window.location.href="/transition/transition_all_position";
        }
    })
}

function goBack() {
    window.location.href="/transition/transition_all_position";
}