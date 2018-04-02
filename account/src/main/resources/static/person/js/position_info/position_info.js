// JavaScript Document

var phone;
var type;
var id;
var isRegist;
var isDelivery;
var isCollect;
  $(function() {

      loadPositionInfoById();

      $('.d-btn').click(function(){
      if(isRegist == "3") {
          $('.tacne01').show();
          $('.zaikandenglushoucang').click(function () {
              $('.tacne01').hide();
          });
          $('.lijidenglushoucang').click(function () {
              $('.tacne01').hide();
              window.location.href = "/transition/go_zhu_ce1";
          });
      }else if(isRegist == "2"){
          $('.tacne02').show();
          $('.zaikanwanshanshoucang').click(function () {
              $('.tacne02').hide();
          });
          $('.lijiwanshanshoucang').click(function () {
              $('.tacne02').hide();
              window.location.href = "/transition/go_wan_shan_xin_xi";
          });
      }else {
          if($(this).text()=="收藏"){
              $.ajax({
                  type: "POST",
                  url:"/collection/collectPosition",
                  success: function (res) {
                      id =res.data.id
                      $('.sctcws01').html("<i></i>已收藏").addClass("curs");
                      $(".toolbarframe").show();
                      setTimeout('$(".toolbarframe").hide()',900);
                  },
                  error : function () {
                      $('.sctcws01').html("<i></i>收藏").removeClass("curs");
                      $(".toolbarframe03").show();
                      setTimeout('$(".toolbarframe03").hide()',900);
                  }
              })

          }else if($(this).text()=="已收藏"){
              $.ajax({
                  type: "POST",
                  url:"/collection/cancelCollectPosition",
                  success: function (res) {
                      console.log("====-------"+JSON.stringify(res.data))
                      $('.sctcws01').html("<i></i>收藏").removeClass("curs");
                      $(".toolbarframe02").show();
                      setTimeout('$(".toolbarframe02").hide()',900);
                  },
                  error : function () {
                      $('.sctcws01').html("<i></i>已收藏").addClass("curs");
                      $(".toolbarframe03").show();
                      setTimeout('$(".toolbarframe03").hide()',900);
                  }
              })

          }
      }
			});

      $('.sctcws02').click(function(){
          if(isRegist == "3") {
              $('.tacne03').show();
              $('.zaikandengludianhua').click(function () {
                  $('.tacne03').hide();
              });
              $('.lijidengludianhua').click(function () {
                  $('.tacne03').hide();
                  window.location.href = "/transition/go_zhu_ce1";
              });
          }else if(isRegist == "2") {
              $('.tacne04').show();
              $('.zaikanwanshandianhua').click(function () {
                  $('.tacne04').hide();
              });
              $('.lijiwanshandianhua').click(function () {
                  $('.tacne04').hide();
                  window.location.href = "/transition/go_wan_shan_xin_xi";
              });
          }else {
              if(isDelivery == "1"){
                  $.ajax({
                      url:"/resume/addResumeTellPhoneNum",
                      type:"POST",
                      data :id,
                      success :function () {
                          window.location.href="tel:"+phone;
                      },
                      error : function () {
                          $('.toolbarframe06').show();
                          setTimeout('$(".toolbarframe06").hide()',900);

                      }
                  });

              }else {
                  $(".tacne05").show();
                  $('.zaikantoudi').click(function () {
                      $('.tacne05').hide();
                  });
                  $('.lijitoudi').click(function () {
                      $('.tacne05').hide();
                      yingpin();
                  });
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
                $('.sbnecont').empty();
                var list = res.data;
                var company_addr,name,position_info,company_city,position_name,hot,
					money,experience,age,sex,icon_path,name,company_type,company_scale;
                $.each(list, function (index, item) {
                    id = item.id;
                    isDelivery =item.isDelivery;
                    isCollect =item.isCollect;
                    isRegist =item.isRegist;
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
                    icon_path =item.icon_path;
                    if(icon_path == null || icon_path == ""){
                        icon_path = "../../person/images/icon_company_default.png";
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
                    sbnecont +=''+
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
                        '<img src="'+icon_path+'" />' +
                        '<div class="yrzs"><img src="../../person/images/biao02.png" /></div>' +
                        '</div>' +
                        '<a href="#" onclick="loadCompanyInfo(\'/transition/company_info?cid='+item.cid+ '\')"> '+
                        '<div class="corpryous">' +
                        '<h1>'+name+'</h1>' +
                        '<p>'+company_type+' | '+company_scale+' | '+company_city+'</p>' +
                        '<img src="../../person/images/yjts.png" class="jtois" />' +
                        '</div>' +
                        '</a>'+
                        '</div>';

                    $('.sbnecont').append(sbnecont);
                    $('.zwqxconts').append(position_info);
                    $('.dzlefs').append(company_addr);
                    if(isDelivery == "1"){
                        $('#yiyingpin').show()
                    }else {
                        $('#yingpin').show();
                    }
                    if(isCollect == "1") {
                        $('.sctcws01').html("<i></i>已收藏").addClass("curs");
                    }
                });

            }
        }
    });
}

function yingpin() {
    if(isRegist == "2") {
        $('.tacne07').show();
        $('.zaikanwanshantoudi').click(function(){
            $('.tacne07').hide();
        });
        $('.lijiwanshantoudi').click(function(){
            $('.tacne07').hide();
            window.location.href="/transition/go_wan_shan_xin_xi";
        });
    }else if(isRegist == "3"){
        $('.tacne06').show();
        $('.zaikandenglutoudi').click(function(){
            $('.tacne06').hide();
        });
        $('.lijidenglutoudi').click(function(){
            $('.tacne06').hide();
            window.location.href="/transition/go_zhu_ce1";
        });
    }else {
        $.ajax({
            url:"/resume/submitResume",
            type:"POST",
            success : function (res) {
                $(".toolbarframe04").show();
                setTimeout('$(".toolbarframe04").hide()',900);
            },

            error : function (res) {
                $(".toolbarframe05").show();
                setTimeout('$(".toolbarframe05").hide()',900);
            }
        })
    }
}

function loadCompanyInfo(url) {
    window.location.href=url;
}




