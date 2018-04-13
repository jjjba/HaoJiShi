$(function () {
    $(".companyName").focus(function () {
        $(".companyName").keyup(function(){
            var length=this.value.length;
            if(length > 0){
                $('.cancel').hide();
                $('.wancheng').show();
            }else {
                $('.cancel').show();
                $('.wancheng').hide();
            }
        });
    });

});
function wancheng() {
    var name =$('.companyName').val();
    $.ajax({
        url:"/position/getPositionByName",
        type:"POST",
        data:{
            name : name,
        },
        success :function (res) {
            $('.zhiweiliebiao').empty();
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
                    icon_path= "../../person/images/icon_company_default.png"
                }
                var zhiweiliebiao =
                    '    <div class="reconblock">\n' +
                    '      <a href="#" onclick="loadPositionInfomation(\'/transition/transition_position_info?id='+id+'\')">\n' +
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
                    '    </div>\n' ;
                $('.zhiweiliebiao').append(zhiweiliebiao);
                $('.zhiweiliebiao').show();
                $('.lishijilu').hide();
            });
        }
    })
}
function loadPositionInfomation(url) {
    window.location.href=url;
}
function cancel() {
    window.location.href="/transition/transition_all_position";
}
function qingchu() {
    $('.companyName').val("");
}