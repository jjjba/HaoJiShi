var money;
var scale;
var city;
var positionName;
$(function () {
    loadRegionData();
    loadPosition();

    $("#money").click(function(e) {
        money = $(e.target).attr("data");
        console.log("money======"+money)
        loadPositionByPars(city,scale,money,positionName);
    });
    $(".scale").click(function(e) {
        scale = $(e.target).attr("data");
        console.log("scale======"+scale)
        loadPositionByPars(city,scale,money,positionName);
    });
    $('.city').click(function (e) {
        city =$(e.target).attr("data");
        console.log("city======="+city);
        loadPositionByPars(city,scale,money,positionName);
    });
    $('.rmcshis').click(function (e) {
        city =$(e.target).attr("data");
        console.log("city======="+city);
        loadPositionByPars(city,scale,money,positionName);
    });

});
function loadPosition() {
    $.ajax({
        url: "/personal/getPersonalHopeJobClassification",
        type: "POST",
        success: function (res) {
            if (res.success) {
                var list = res.data;
                var address = list[0].address;
                if (address == null || address == "") {
                    address = "未获取到位置"
                }
                $('.zcwzuis').append('当前城市：' + address);
            }
        }
    })
}
function loadPosition() {
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
                $('.personalList1111').empty();
                var list = res.data;
                var id, company_city, position_name,hot,money,experience,age,sex,icon_path,name,company_type,company_scale;
                $.each(list, function (index, item) {
                    id = item.id;
                    company_city =item.company_city;
                    if(company_city == null || company_city == ""){
                        company_city ="未填写";
                    }
                    position_name =item.position_name;
                    if(position_name == null || position_name == ""){
                        position_name ="未填写";
                    }
                    hot =item.hot;
                    if(hot == null || hot == ""){
                        hot ="0";
                    }
                    money =item.money;
                    if(money == null || money == ""){
                        money ="未填写";
                    }
                    experience =item.experience;
                    if(experience == null || experience == ""){
                        experience ="未填写";
                    }
                    age =item.age;
                    if(age == null || age == ""){
                        age ="未填写";
                    }
                    sex =item.sex;
                    if(sex == null || sex == ""){
                        sex ="未填写";
                    }

                    name =item.name;
                    if(name == null || name == ""){
                        name ="未填写";
                    }
                    company_type =item.company_type;
                    if(company_type == null || company_type == ""){
                        company_type ="未填写";
                    }
                    company_scale =item.company_scale;
                    if(company_scale == null || company_scale == ""){
                        company_scale ="未填写";
                    }
                    icon_path =item.icon_path;
                    if(icon_path == null || icon_path == ""){
                        icon_path="../../person/images/icon_company_default.png";
                    }
                    var personalList1111 = "";
                    personalList1111 +=
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
                        '<img src="'+icon_path+'" /> '+
                        '<div class="yrzs"><img src="../../person/images/biao02.png" /></div>'+
                        '</div>'+
                        '<div class="corpryous">'+
                        '<h1>'+name+'</h1>'+
                        '<p>'+company_type+' | '+company_scale+' | '+company_city+'</p>'+
                        '</div>'+
                        '</div>'+
                        '</a>'+
                        '</div>';

                    $('.personalList1111').append(personalList1111);
                });
            }
        },
        error :function (res) {

        }

    })
}
function addHopeJob(val) {
    positionName =$('#'+val).attr("data");
    loadPositionByPars(city,scale,money,positionName);
}
function loadPositionByPars(city,scale,money,positionName) {
    console.log("scale======"+scale)
    console.log("money======"+money)

    var data ={
        money : money,
        scale : scale,
        positionName : positionName,
        city : city,

    }
    $.ajax({
        url:"/position/getPositionByParams02",
        type:"POST",
        data:data,
        success : function (res) {
            $('.personalList1111').empty();
            var list =res.data;
            var id, company_city, position_name,hot,money,experience,age,sex,icon_path,name,company_type,company_scale;
            $.each(list, function (index, item) {
                id = item.id;
                company_city =item.company_city;
                if(company_city == null || company_city == ""){
                    company_city ="未填写";
                }
                position_name =item.position_name;
                if(position_name == null || position_name == ""){
                    position_name ="未填写";
                }
                hot =item.hot;
                if(hot == null || hot == ""){
                    hot ="0";
                }
                money =item.money;
                if(money == null || money == ""){
                    money ="未填写";
                }
                experience =item.experience;
                if(experience == null || experience == ""){
                    experience ="未填写";
                }
                age =item.age;
                if(age == null || age == ""){
                    age ="未填写";
                }
                sex =item.sex;
                if(sex == null || sex == ""){
                    sex ="未填写";
                }
                icon_path =item.icon_path;
                if(icon_path == null || icon_path == ""){
                    icon_path="../../person/images/icon_company_default.png";
                }
                name =item.name;
                if(name == null || name == ""){
                    name ="未填写";
                }
                company_type =item.company_type;
                if(company_type == null || company_type == ""){
                    company_type ="未填写";
                }
                company_scale =item.company_scale;
                if(company_scale == null || company_scale == ""){
                    company_scale ="未填写";
                }
                var personalList1111 = "";
                personalList1111 +=
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
                $('.personalList1111').append(personalList1111);

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