
var money;
var scale;
var type;
var city;
$(function () {
    loadRegionData();
    loadPosition()


    $("#positionType").click(function(e) {
        type = $(e.target).attr("data");
        console.log("type======"+type)
        loadPositionByPars(city,scale,money,type);
    });

    $(".city").click(function(e) {
        city = $(e.target).attr("data");
        console.log("city======"+city)
        loadPositionByPars(city,scale,money,type);

    });
    $(".rmcshis").click(function(e) {
        city = $(e.target).attr("data");
        console.log("city======"+city)
        loadPositionByPars(city,scale,money,type);

    });
    $("#money").click(function(e) {
        money = $(e.target).attr("data");
        console.log("money======"+money)
        loadPositionByPars(city,scale,money,type);
    });
    $(".scale").click(function(e) {
        scale = $(e.target).attr("data");
        console.log("scale======"+scale)
        loadPositionByPars(city,scale,money,type);
    });






});

function loadPosition() {
    $.ajax({
        url:"/personal/getPersonalHopeJobClassification",
        type:"POST",
        success : function (res) {
            if(res.success){
                var list =res.data;
                var address =list[0].address;
                if(address == null || address == "") {
                    address = "未获取到位置"
                }
                $('.zcwzuis').append('当前城市：'+address);
                var positionType;
                $.each(list, function (index, item) {
                    positionType =item.positionType;
                    if(positionType == null || positionType == "") {
                        positionType = "您还没有选择分类呦"
                    }
                    $('#positionType').append('<li><a href="#" data="'+positionType+'">'+positionType+'</a></li>');
                })
            }
        }
    })
    
    // var data ={
    //     page :page,
    //     size :size,
    // }
    $.ajax({
        url:"/position/getPosition",
        type: "POST",
        // data:data,
        success :function (res) {
            if (res.success) {
                $('.positionList').empty();
                var list = res.data;
                var id, company_city, position_name,hot,money,experience,age,sex,icon_path,name,company_type,company_scale;
                $.each(list, function (index, item) {
                    id = item.id;
                    company_city = item.company_city;
                    position_name = item.position_name;
                    money = item.money;
                    experience = item.experience;
                    age = item.age;
                    sex = item.sex;
                    name = item.name;
                    company_type = item.company_type;
                    company_scale = item.company_scale;
                    hot =item.hot;
                    if(hot == null || hot == ""){
                        hot ="0";
                    }
                    icon_path =item.icon_path;
                    if(icon_path == null || icon_path == ""){
                        icon_path= "../../person/images/icon_company_default.png"
                    }

                    var positionList =
                        '<div class="reconblock">\n' +
                        '      <a href="#" onclick="loadPositionInfo(\'/transition/transition_position_info?id='+id+'\')">\n' +
                        '      <div class="respise clearfix">\n' +
                        '        <div class="fl reselefs">'+position_name+'</div>\n' +
                        '        <div class="fr reseyous"><span class="yse01">急聘</span> <span class="yse02">热度'+hot+'</span></div>\n' +
                        '      </div>\n' +
                        '      <div class="wagesyears clearfix">\n' +
                        '        <div class="fl wayealfs">'+money+'</div>\n' +
                        '        <div class="fr wayefris">'+experience+' | '+age+' | '+sex+'</div>\n' +
                        '      </div>\n' +
                        '        <div class="corpnames">\n' +
                        '          <div class="corpzuos">\n' +
                        '            <img src="'+icon_path+'" />\n' +
                        '            <div class="yrzs"><img src="../../person/images/biao02.png" /></div>\n' +
                        '          </div>\n' +
                        '          <div class="corpryous">\n' +
                        '            <h1>'+name+'</h1>\n' +
                        '            <p>'+company_type+' | '+company_scale+' | '+company_city+'</p>\n' +
                        '          </div>\n' +
                        '        </div>\n' +
                        '      </a>\n' +
                        '    </div>';

                    $('.positionList').append(positionList);
                });
            }
        },
        error :function (res) {

        }

    })
}

function loadPositionByPars(city,scale,money,type) {
    console.log("scale======"+scale)
    console.log("money======"+money)

    var data ={
        money : money,
        scale : scale,
        type : type,
        city : city,
    }
    $.ajax({
        url:"/position/getPositionByParams",
        type:"POST",
        data:data,
        success : function (res) {
            $('.positionList').empty();
            var list =res.data;
            var id, company_city, position_name,hot,money,experience,age,sex,icon_path,name,company_type,company_scale;
            $.each(list, function (index, item) {
                id = item.id;
                company_city = item.company_city;
                position_name = item.position_name;
                money = item.money;
                experience = item.experience;
                age = item.age;
                sex = item.sex;
                name = item.name;
                company_type = item.company_type;
                company_scale = item.company_scale;
                hot =item.hot;
                if(hot == null || hot == ""){
                    hot ="0";
                }

                icon_path =item.icon_path;
                if(icon_path == null || icon_path == ""){
                    icon_path="../../person/images/icon_company_default.png";
                }
                var positionList = "";
                positionList +=
                    '<div class="reconblock">'+
                    '<a href="#" onclick="loadPositionInfo(\'/transition/transition_position_info?id='+id+'\')">' +
                    '<div class="respise clearfix">' +
                    '<div class="fl reselefs">'+position_name+'</div>' +
                    '<div class="fr reseyous"><span class="yse01">急聘</span> <span class="yse02">'+hot+'</span></div>' +
                    '</div>'+
                    '<div class="wagesyears clearfix">'+
                    '<div class="fl wayealfs">'+money+'</div>'+
                    '<div class="fr wayefris">'+experience+' | '+age+' | '+sex+'</div>'+
                    '</div>'+
                    '<div class="corpnames">'+
                    '<div class="corpzuos">'+
                    '<img src="'+icon_path+'" />'+
                    '<div class="yrzs"><img src="../../person/images/biao02.png" /></div>'+
                    '</div>'+
                    '<div class="corpryous">'+
                    '<h1>'+name+'</h1>'+
                    '<p>'+company_type+' | '+company_scale+' | '+company_city+'</p>'+
                    '</div>'+
                    '</div>'+
                    '</a>'+
                    '</div>';
                $('.positionList').append(positionList);

            })

        }
    })
}

function loadRegionData() {
    $.ajax({
        url:"/region/getRegion",
        type:"POST",
        success : function (res) {
            var list =res.data;
            var name,nameEn;
            $.each(list, function (index, item) {
                name =item.name;
                nameEn =item.nameen;
                var en =nameEn.substring(0,1);
                if(en == "A"){
                    $('.reg1').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "B"){
                    $('.reg2').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "C"){
                    $('.reg3').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "D"){
                    $('.reg4').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "E"){
                    $('.reg5').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "F"){
                    $('.reg6').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "G"){
                    $('.reg7').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "H"){
                    $('.reg8').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "J"){
                    $('.reg9').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "K"){
                    $('.reg10').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "L"){
                    $('.reg11').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "M"){
                    $('.reg12').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "N"){
                    $('.reg13').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "P"){
                    $('.reg14').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "Q"){
                    $('.reg15').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "R"){
                    $('.reg16').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "S"){
                    $('.reg17').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "T"){
                    $('.reg18').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "W"){
                    $('.reg19').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "X"){
                    $('.reg20').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "Y"){
                    $('.reg21').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }else if(en == "Z"){
                    $('.reg22').append('<li><a href="#" data="'+name+'">'+name+'</a></li>');
                }
            })
        }
    })
}

function loadPositionInfo(url) {
    window.location.href=url;
}

function transition_search() {
    window.location.href="/transition/transition_search";
}

function goIndex() {
    window.location.href="/transition/transition_goIndex";
}

function goMySelf() {
    window.location.href="/transition/transition_goMySelf";
}

function citySearch() {
    window.location.href="/transition/city_search";
}

function goJobPosition() {
    window.location.href="/transition/job_position";
}