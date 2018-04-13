$(document).ready(function() {
  $.ajax({
      url:"/company/getZhiWeiALL",
      type:"POST",
      success:function (msg) {
          console.log(msg);
          var company = msg.data;
          var position = msg.dataOne;
          var htm = "";
          if(position != null ){
              for(var i = 0;i<position.length;i++){
                  var idd= position[i].id;
                  htm+="<div class='ykuissws'><div class='sbxxuys'><div class='dybuzs01 clearfix'><div class='fl sbtus01' onclick='go_zhiweixiangqing(&quot;";
                  htm+=idd;
                  htm+="&quot;)'>";
                  htm+=position[i].positionName;
                  htm+="</div>";
                  if(position[i].state ==4){
                      htm+="<div class='fr sbtus02'>";
                      htm+="招聘中";
                  }
                  if(position[i].state == 1){
                      htm+="<div class='fr sbtus03'>";
                      htm+="等待上线";
                  }
                  if(position[i].state == 2){
                      htm+="<div class='fr sbtus03'>";
                      htm+="已关闭";
                  }
                  if(position[i].state == 3){
                      htm+="<div class='fr sbtus04'>";
                      htm+="审核失败";
                  }
                  htm+="</div></div> <div class='dybuzs02'><div class='dypui001'>";
                  htm+=position[i].money;
                  htm+="/月</div><div class='dypui002'>";
                  if(position[i].experience !=null){
                      htm+=position[i].experience;
                      htm+="|";
                  }
                  if(position[i].age != null){
                      htm+=position[i].age;
                      htm+="|";
                  }
                  if(position[i].sex !=null){
                      htm+=position[i].sex;
                  }
                  htm+="</div></div><div class='dybuzs03'><ul><li><span>曝光量</span> <b>";
                  htm+=position[i].exposureNumber;
                  htm+="</b></li><li><span>访问量</span> <b>";
                  htm+=position[i].seeNumber;
                  htm+="</b></li><li><span>分享访问量</span> <b>";
                  htm+=position[i].shareNumber;
                  htm+="</b></li><li><span>投递简历</span> <b>";
                  htm+=position[i].resumeNumber;
                  htm+="</b></li></ul></div></div><div class='xkasius'>";
                  if(position[i].state ==4){
                      htm+="<a href='#'><img src='../../company/images/iconn002.png' /> 分享</a>";
                      htm+="<a href='#'><img src='../../company/images/iconn003.png' /> 下线</a>";
                      htm+="<a href='#' onclick='go_zhiweixiugai(&quot;";
                      htm+=idd;
                      htm+="&quot;);'><img src='../../company/images/iconn004.png' /> 编辑</a>";
                  }
                  if(position[i].state == 3){
                      htm+="<a href='#'><img src='../../company/images/iconn006.png' /> 删除</a>"
                  }
                  if(position[i].state == 1 || position[i].state == 2){
                      htm+="<a href='#' onclick='$(&quot;.popupus&quot;).show();'><img src='../../company/images/iconn005.png' /> 上线</a>";
                      htm+="<a href='#' onclick='go_zhiweixiugai(&quot;";
                      htm+=idd;
                      htm+="&quot;);'><img src='../../company/images/iconn004.png' /> 编辑</a>";

                  }
                  htm+="</div></div>";

              }
          }
          if(company.matstate == 1 || company.matstate == 3){
              $(".popupus").show();

              $(".zwlfius02").text("职位上线后才可以被求职者看到，您需要完成店铺资质认证后，才可以上线职位！");
          }
          if(company.matstate == 2 || company.matstate == 4){
              $(".popupus").hide();

              $(".zwlfius02").text("分享职位到朋友圈可以获得更多的曝光，提升职位热度和职位排名！招聘效果提升50%以上！");
          }
          $("#Duzi").html(htm);

      }
  })  
})
function TccYc() {
    $(".popupus").hide();
}
function Qrz() {
    $(".popupus").hide();
    sessionStorage.setItem("RENZHENG","RENZHNEG");
    window.location.href="/transition/RenZheng";
}
function addZw() {
    window.location.href="/transition/bianji_zhiwei";
}
function go_zhiweixiangqing(id) {
    sessionStorage.setItem("position_id",id);
    sessionStorage.setItem("YuLanOrXiangQing","xiangqing");
    window.location.href="/transition/zhiwei_xiangqing";
}
function go_zhiweixiugai(id) {
    sessionStorage.setItem("position_id",id);
    $.ajax({
        url:"/company/getSelectPosition",
        data:{position_id:id},
        type:"POST",
        success:function (msg) {
            console.log(msg);
            sessionStorage.setItem("zwlx",msg.data.positionType);
            sessionStorage.setItem("zwmc",msg.data.positionName);
            sessionStorage.setItem("yx",msg.data.money);
            sessionStorage.setItem("jyyq",msg.data.experience);
            sessionStorage.setItem("xbyq",msg.data.sex);
            sessionStorage.setItem("nlyq",msg.data.age);
            sessionStorage.setItem("LLLLzwfl",msg.data.welfare);
            sessionStorage.setItem("zwms",msg.data.positionInfo);
            window.location.href="/transition/zhiweiguanlirenzhneg_bj";
        }
    })

}