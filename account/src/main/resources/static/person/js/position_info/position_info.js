// JavaScript Document

var phone;

var type;

var resumeNumber;

var id;

  $(function() {
    loadPositionInfoById();
	
	$(".toolbarframe").hide();
	$(".toolbarframe02").hide();
	$(".toolbarframe03").hide();


      $('.d-btn').click(function(){
      if(type == "3"){
          $('.tacne01').show();
      }else {
          if($(this).text()=="收藏"){
              $.ajax({
                  type: "POST",
                  url:"/collection/collectPosition",
                  success: function (res) {
                      id =res.data.id
                      $('.sctcws01').html("<i></i>已收藏").addClass("curs");
                      $(".toolbarframe").show();
                      $(".toolbarframe02").hide();
                      $(".toolbarframe03").hide();
                      setTimeout('$(".toolbarframe").hide()',900);
                  },
                  error : function () {
                      $('.sctcws01').html("<i></i>收藏").removeClass("curs");
                      $(".toolbarframe").hide();
                      $(".toolbarframe02").hide();
                      $(".toolbarframe03").show();
                      setTimeout('$(".toolbarframe").hide()',900);
                  }
              })

          }else{
              $.ajax({
                  type: "POST",
                  url:"/collection/cancelCollectPosition?id="+id,
                  success: function (res) {
                      console.log("====-------"+JSON.stringify(res.data))
                      $('.sctcws01').html("<i></i>收藏").removeClass("curs");
                      $(".toolbarframe").hide();
                      $(".toolbarframe02").show();
                      $(".toolbarframe03").hide();
                      setTimeout('$(".toolbarframe02").hide()',900);
                  },
                  error : function () {
                      $('.sctcws01').html("<i></i>已收藏").addClass("curs");
                      $(".toolbarframe").hide();
                      $(".toolbarframe02").hide();
                      $(".toolbarframe03").show();
                      setTimeout('$(".toolbarframe").hide()',900);
                  }
              })

          }
      }
			});

      $('.sctcws02').click(function(){
          if(type == "3"){
              $('.tacne01').show();
          }else {
              if(resumeNumber == "0"){
                  $(".tacne04").show();
              }else {
                  window.location.href="tel:"+phone;
              }
          }
      });

		 
      });


function loadPositionInfoById() {
    $.ajax({
        type: "POST",
        url: "/position/getPositionById",
        success: function (res) {
            if (res.success) {
                var list = res.data;
                var company_addr,name,id,position_info,company_city,position_name,hot,
					money,experience,age,sex,icon_path,name,company_type,company_scale,collectNumber;
                $.each(list, function (index, item) {
                    id = item.id;
                    resumeNumber =item.resumeNumber;
                    if(resumeNumber == null || resumeNumber ==""){
                        resumeNumber ="0";
                    }
                    collectNumber =item.collectNumber;
                    if(collectNumber == null || collectNumber ==""){
                        collectNumber ="0"
                    }
                    type =item.type;
                    company_addr = item.company_addr;
                    if (company_addr == null || company_addr == "") {
                        company_addr = "未填写"
                    }
                    position_info = item.position_info;
                    if (position_info == null || position_info == "") {
                        position_info = "还没有填写呦"
                    }
                    company_city = item.company_city;
                    if (company_city == null || company_city == "") {
                        company_city = "未填写";
                    }
                    position_name = item.position_name;
                    if (position_name == null || position_name == "") {
                        position_name = "未填写";
                    }
                    hot = item.hot;
                    if (hot == null || hot == "") {
                        hot = "0";
                    }
                    money = item.money;
                    if (money == null || money == "") {
                        money = "未填写";
                    }
                    experience = item.experience;
                    if (experience == null || experience == "") {
                        experience = "未填写";
                    }
                    age = item.age;
                    if (age == null || age == "") {
                        age = "未填写";
                    }
                    sex = item.sex;
                    if (sex == null || sex == "") {
                        sex = "未填写";
                    }

                    name = item.name;
                    if (name == null || name == "") {
                        name = "未填写";
                    }
                    company_type = item.company_type;
                    if (company_type == null || company_type == "") {
                        company_type = "未填写";
                    }
                    company_scale = item.company_scale;
                    if (company_scale == null || company_scale == "") {
                        company_scale = "未填写";
                    }
                    var sbnecont = "";
                    sbnecont +=
                        '<div class="sbontshs">' +
                        '<div class="respise clearfix">' +
                        '<div class="fl reselefs">'+position_name+'</div>' +
                        '<div class="fr reseyous"><span class="yse01">急聘</span> <span class="yse02">'+hot+'</span></div>' +
                        '</div>' +
                        '<div class="gonzis">'+money+'</div>' +
                        '<div class="yaoqius">' +
                        '</div>' +
                        '<div class="daiyus">' +
                        '<span>包吃包住</span>' +
                        '</div>' +
                        '</div>' +
                        '<div class="corpnames">' +
                        '<div class="corpzuos">' +
                        '<div class="icon"></div> ' +
                        '<div class="yrzs"><img src="../../person/images/biao02.png" /></div>' +
                        '</div>' +
                        '<div class="corpryous">' +
                        '<h1>'+name+'</h1>' +
                        '<p>'+company_type+' | '+company_scale+' | '+company_city+'</p>' +
                        '<img src="../../person/images/yjts.png" class="jtois" />' +
                        '</div>' +
                        '</div>' ;
                    icon_path = item.icon_path;
                    if (icon_path == null || icon_path == "") {
                        $('.icon').append('<img src="../../person/images/icon_company_default.png" />');
                    } else {
                        $('.icon').append('<img src="' + icon_path + '" />');
                    }
                    $('.sbnecont').append(sbnecont);
                    $('.zwqxconts').append(position_info);
                    $('.dzlefs').append(company_addr);
                    if(type != "2"){
                        $('.footres').show()
                    }
                    if(collectNumber != 0) {
                        $('.sctcws01').html("<i></i>已收藏").addClass("curs");
                    }
                });

            }
        }
    });
}

// function shoucang() {
//
// }







