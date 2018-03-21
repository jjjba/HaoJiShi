
$(function () {
    loadCollectPosition();
});


function loadCollectPosition() {
    $.ajax({
        type:'POST',
        url:'/collection/selectCollectPosition',
        success :function(res){
            var list =res.data;
            var id,name,company_type,company_city,company_scale,icon_path,position_name,hot,money,sex,age,experience;
            $.each(list, function (index, item) {
                id =item.id;
                name =item.name;
                if(name == "" || name == null){
                    name ="未填写";
                }
                company_type =item.company_type;
                if(company_type == "" || company_type == null){
                    company_type ="未填写";
                }
                company_city =item.company_city;
                if(company_city == "" || company_city == null){
                    company_city ="未填写";
                }
                company_scale =item.company_scale;
                if(company_scale == "" || company_scale == null){
                    company_scale ="未填写";
                }
                position_name =item.position_name;
                if(position_name == "" || position_name == null){
                    position_name ="未填写";
                }
                hot =item.hot;
                if(hot == "" || hot == null){
                    hot ="未填写";
                }
                money =item.money;
                if(money == "" || money == null){
                    money ="未填写";
                }
                sex =item.sex;
                if(sex == "" || sex == null){
                    sex ="未填写";
                }
                age =item.age;
                if(age == "" || age == null){
                    age ="未填写";
                }
                experience =item.experience;
                if(experience == "" || experience == null){
                    experience ="未填写";
                }

                var reconblock ='<a href="#">'+
                    '<div class="respise clearfix">'+
                    '<div class="fl reselefs">'+position_name+'</div>'+
                    '<div class="fr reseyous"><span class="yse01">急聘</span> <span class="yse02">'+hot+'</span></div>'+
                    '</div>'+
                    '<div class="wagesyears clearfix">'+
                    '<div class="fl wayealfs">'+money+'</div>'+
                    '<div class="fr wayefris">'+experience+' | '+age+' | '+sex+'</div>'+
                    '</div>'+
                    '<div class="corpnames">'+
                    '<div class="corpzuos">'+
                    '<div class="icon"></div>'+
                    '<div class="yrzs"><img src="../../person/images/biao02.png" /></div>'+
                    '</div>'+
                    '<div class="corpryous">'+
                    '<h1>'+name+'</h1>'+
                    '<p>'+company_type+' | '+company_scale+' | '+company_city+'</p>'+
                    '</div>'+
                    '</div>'+
                    '</a>';
                $('.reconblock').append(reconblock);

                icon_path =item.icon_path;
                if(icon_path == null || icon_path == ""){
                    $('.icon').append('<img src="../../person/images/icon_company_default.png" />');
                }else {
                    $('.icon').append('<img src="'+icon_path+'" />');
                }

            })
        }
    })
}