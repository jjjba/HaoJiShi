var num = 0;
function ZwMc() {
    window.location.href="/transition/zwmingCheng";
}
function zhiweifuli() {
    window.location.href="/transition/zhiwei_fuli";
}
function zhiweimiaoshu() {
    window.location.href="/transition/zhiwei_miaoshu";
}
function xuanzezhiwei() {
    window.location.href="/transition/dianpu_zhiweixuanze";
}
$(document).ready(function() {
    var zwlx = sessionStorage.getItem("zwlx");
    var zwmc = sessionStorage.getItem("zwmc");
    var yx = sessionStorage.getItem("yx");
    var jyyq = sessionStorage.getItem("jyyq");
    var xbyq = sessionStorage.getItem("xbyq");
    var nlyq = sessionStorage.getItem("nlyq");
    var zwfl = sessionStorage.getItem("LLLLzwfl");
    var zwms = sessionStorage.getItem("zwms");
    if(zwlx !="" && zwlx !='' && zwlx !=undefined){
        $("#trigger1").html(zwlx);
        num++;
    }else{
        $("#trigger1").html("请选择");
    }
    if(zwmc !="" && zwmc !='' && zwmc !=undefined){
        $("#zwmc").html(zwmc+"可选择");
        num++;
    }else {
        if(zwlx !="" && zwlx !='' && zwlx !=undefined && (zwmc =="" || zwmc =='' || zwmc ==undefined)){
            $("#zwmc").html(zwlx+"(可编辑)");
        }else {
            $("#zwmc").html("请填写&nbsp;");
        }

    }
    if(yx !="" && yx !='' && yx !=undefined){
        $("#trigger2").html(yx);
        num++;
    }else {
        $("#trigger2").html("请选择");
    }
    if(jyyq !="" && jyyq !='' && jyyq !=undefined){
        $("#trigger3").html(jyyq);
        num++;
    }else {
        $("#trigger3").html("请选择");
    }
    if(xbyq !="" && xbyq !='' && xbyq !=undefined){
        $("#trigger4").html(xbyq);
        num++;
    }else {
        $("#trigger4").html("请选择");
    }
    if(nlyq !="" && nlyq !='' && nlyq !=undefined){
        $("#trigger5").html(nlyq);
        num++;
    }else {
        $("#trigger5").html("请选择");
    }
    if(zwfl !="" && zwfl !='' && zwfl !=undefined){
        var fl = zwfl.substring(0,13)+"...";
        $("#zwfl").html(fl);
        num++;
    }else {
        $("#zwfl").html("请选择&nbsp;");
    }
    if(zwms !="" && zwms !='' && zwms !=undefined){
        var ms = zwms.substring(0,13)+"...";
        $("#zwms").html(ms);
        num++;
    }else {
        $("#zwms").html("请填写&nbsp;");
    }
})
function yulan() {
    if(num ==8){
        sessionStorage.setItem("YuLanOrXiangQing","yulan");
        window.location.href="/transition/zhiwei_xiangqing";
    }else {
        $(".yulanBt").show();
        setTimeout('$(".yulanBt").hide()',1000);
    }

}
function fabuZhiwei() {
    var zwlx = sessionStorage.getItem("zwlx");
    var zwmc = sessionStorage.getItem("zwmc");
    var yx = sessionStorage.getItem("yx");
    var jyyq = sessionStorage.getItem("jyyq");
    var xbyq = sessionStorage.getItem("xbyq");
    var nlyq = sessionStorage.getItem("nlyq");
    var zwfl = sessionStorage.getItem("LLLLzwfl");
    var zwms = sessionStorage.getItem("zwms");
   if((zwlx !="" && zwlx !='' && zwlx !=undefined)&&(zwmc !="" && zwmc !='' && zwmc !=undefined)&&(yx !="" && yx !='' && yx !=undefined)
   &&(jyyq !="" && jyyq !='' && jyyq !=undefined)&&(xbyq !="" && xbyq !='' && xbyq !=undefined)&&(nlyq !="" && nlyq !='' && nlyq !=undefined)&&
       (zwfl !="" && zwfl !='' && zwfl !=undefined)&&(zwms !="" && zwms !='' && zwms !=undefined)){
       $.ajax({
           url:"/company/AddZhiwei",
           type:"POST",
           data:{zwlx:zwlx,zwmc:zwmc,yx:yx,jyyq:jyyq,xbyq:xbyq,nlyq:nlyq,zwfl:zwfl,zwms:zwms},
           success:function (msg) {
               console.log(msg);
               window.location.href="/transition/go_wo_de";
           }
       })
    }else{
       $(".quanbubitian").show();
       setTimeout('$(".quanbubitian").hide()',1000);
   }


}

