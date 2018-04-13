// JavaScript Document
var ColorId;
var num = 0;
$(document).ready(function() {
	      $(".fllfzuos ul li").click(function(){
        
           $(".fllfzuos ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

        });
		
	        $(".fllfzuos02 ul li").click(function(){
        
           $(".fllfzuos02 ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

        });
		
		
		
		
	
});
function xuanze(lx) {
    if(lx == "js") {
        $(".flriyous").empty();
        var htm1 = " <a href='#' onclick='ColorAndClick(&quot;lls&quot;)' ><span id='lls'>理疗师</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;zls&quot;)' ><span id='zls'>足疗师</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;ams&quot;)'><span id='ams'>按摩师</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;zjs&quot;)'><span id='zjs'>针灸师</span></a>\n" +
            "                <a href='#'onclick='ColorAndClick(&quot;tns&quot;)'><span id='tns'>推拿师</span></a>\n" +
            "                <a href='#'onclick='ColorAndClick(&quot;xjs&quot;)'><span id='xjs'>修脚师</span></a>\n" +
            "                <a href='#'onclick='ColorAndClick(&quot;cys&quot;)'><span id='cys'>推油师</span></a>\n" +
            "                <a href='#'onclick='ColorAndClick(&quot;crs&quot;)'><span id='crs'>采耳师</span></a>\n" +
            "                <a href='#'onclick='ColorAndClick(&quot;zys&quot;)'><span id='zys'>指压师</span></a>\n" +
            "                <a href='#'onclick='ColorAndClick(&quot;spss&quot;)'><span id='spss'>SPA师</span></a>\n" +
            "                <a href='#'onclick='ColorAndClick(&quot;qt&quot;)'><span id='qt'>其他</span></a>\n" +
            "                <a href='#'onclick='ColorAndClick(&quot;mrams&quot;)'><span id='mrams'>盲人按摩师</span></a>";
        $(".flriyous").html(htm1);
    }
    if(lx == "gl") {
        $(".flriyous").empty();
        var htm2 = " <a href='#' onclick='ColorAndClick(&quot;fzjl&quot;)'><span id='fzjl'>副总经理</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;yyjl&quot;)'><span id='yyjl'>营运经理</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;dzzl&quot;)'><span id='dzzl'>店长助理</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;jlzl&quot;)'><span id='jlzl'>经理助理</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;qyjl&quot;)'><span id='qyjl'>区域经理</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;yxzj&quot;)'><span id='yxzj'>营销总监</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;zjl&quot;)'><span id='zjl'>总经理</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;dz&quot;)'><span id='dz'>店长</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;qtt&quot;)'><span id='qtt'>其他</span></a>";
        $(".flriyous").append(htm2);
    }

    if(lx == "qt") {
        $(".flriyous").empty();
        var htm3 = "<a href='#' onclick='ColorAndClick(&quot;syby&quot;)'><span id='syby'>收银/吧员</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;zkyk&quot;)'><span id='zkyk'>咨客/客服</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;ybjd&quot;)'><span id='ybjd'>迎宾/接待</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;khjl&quot;)'><span id='khjl'>客户经理</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;dtjl&quot;)'><span id='dtjl'>大堂经理/领班</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;xs&quot;)'><span id='xs'>销售</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;bz&quot;)'><span id='bz'>部长</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;qttt&quot;)'><span id='qttt'>其他</span></a>";
        $(".flriyous").append(htm3);
    }

    if(lx == "hq") {
        $(".flriyous").empty();
        var htm4 ="<a href='#' onclick='ColorAndClick(&quot;wyy&quot;)'><span id='wyy'>文员</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;cgg&quot;)'><span id='cgg'>采购</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;bjj&quot;)'><span id='bjj'>保洁</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;css&quot;)'><span id='css'>厨师</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;ba&quot;)'><span id='ba'>保安</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;sj&quot;)'><span id='sj'>司机</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;fwy&quot;)'><span id='fwy'>服务员</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;cbgb&quot;)'><span id='cbgb'>储备干部</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;pxmrs&quot;)'><span id='pxmrs'>皮鞋美容师</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;qtttt&quot;)'><span id='qtttt'>其他</span></a>\n";
        $(".flriyous").append(htm4);
    }

    if(lx == "px") {
        $(".flriyous").empty();
        var htm5 =" <a href='#' onclick='ColorAndClick(&quot;jszj&quot;)'><span id='jszj'>技术总监</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;jsls&quot;)'><span id='jsls'>技术老师</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;lyds&quot;)'><span id='lyds'>礼仪导师</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;pxjs&quot;)'><span id='pxjs'>培训讲师</span></a>\n" +
            "                <a href='#' onclick='ColorAndClick(&quot;qttttt&quot;)'><span id='qttttt'>其他</span></a>";
        $(".flriyous").append(htm5);
    }
}

function ColorAndClick(mc) {
    console.log(mc);
    if(num != 0){
        $("#"+ColorId).css("background-color","#f7f7f7");
        $("#"+ColorId).css("color","#404040");
    }
    $("#"+mc).css("background-color","#FFF0E9");
    $("#"+mc).css("color","#FF6118");
    ColorId = mc;
    sessionStorage.setItem("zwlx",$("#"+mc).text());
    num+=1;

    fhbb();
}
function fhbb() {
    var id= sessionStorage.getItem("positionid");
    if(id == null || id == undefined || id=="" || id == ''){
        window.location.href="/transition/bianji_zhiwei";
    }else {

        window.location.href="/transition/zhiweiguanlirenzhneg_bj";
    }
}