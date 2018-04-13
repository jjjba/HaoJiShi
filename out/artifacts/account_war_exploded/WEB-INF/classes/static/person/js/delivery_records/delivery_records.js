

$(function () {
        $.ajax({
            url :"/resume/getAllSubmitResume",
            type:"POST",
            success : function (res) {
                $('.reconblock').empty();
                var list =res.data;
                if(list == null || list == ""  || list == undefined){
                    $('.ksjsis').show();
                }else {
                var positionName,age,experience,money,sex,name,companyCity,companyScale,companyType,iconPath,phone,createDate
                    ,tellPhoneNumber,positionId;
                $.each(list, function (index, item) {
                    positionId =item.positionId;
                    tellPhoneNumber =item.tellPhoneNumber;
                    positionName =item.positionName;
                    age =item.age;
                    experience =item.experience;
                    money =item.money;
                    sex =item.sex;
                    name =item.name;
                    companyCity =item.companyCity;
                    companyScale =item.companyScale;
                    companyType =item.companyType;
                    iconPath =item.iconPath;
                    phone =item.phone;
                    createDate =item.createDate;
                    if(positionName == null || positionName == null){
                        positionName ="未填写"
                    }if(age == null || age == null){
                        age ="未填写"
                    }if(experience == null || experience == null){
                        experience ="未填写"
                    }if(money == null || money == null){
                        money ="未填写"
                    }if(sex == null || sex == null){
                        sex ="未填写"
                    }if(name == null || name == null){
                        name ="未填写"
                    }if(companyCity == null || companyCity == null){
                        companyCity ="未填写"
                    }if(companyScale == null || companyScale == null){
                        companyScale ="未填写"
                    }if(companyType == null || companyType == null){
                        companyType ="未填写"
                    }if(iconPath == null || iconPath == null){
                        iconPath ="../../person/images/icon_company_default.png"
                    }if(phone == null || phone == null){
                        phone ="未填写"
                    }if(tellPhoneNumber == null || tellPhoneNumber == 0 || tellPhoneNumber == ''){
                        tellPhoneNumber ="../../person/images/dianh01.png"
                    }else {
                        tellPhoneNumber ="../../person/images/dianh02.png"
                    }

                    var toudijianli='<div class="reconblock">'+
                        '<a href="#" onclick="loadPositionInfo(\'/transition/transition_position_info?id='+positionId+'\')">'+
                        '<div class="respise clearfix">'+
                        '<div class="fl reselefs">'+positionName+'</div>'+
                        '<div class="fr tdjilus">'+createDate+'投递</div>'+
                        '</div>'+
                        '<div class="wagesyears clearfix">'+
                        '<div class="fl wayealfs">'+money+'</div>'+
                        '<div class="fr wayefris">'+experience+' | '+age+' | '+sex+'</div>'+
                        '</div>'+
                        '<div class="corpnames">'+
                        '<div class="corpzuos">'+
                        '<img src="'+iconPath+'" />'+
                        '<div class="yrzs"><img src="../../person/images/biao02.png" /></div>'+
                        '</div>'+
                        '<div class="corpryous tdljdws">'+
                        '<h1>'+name+'</h1>'+
                        '<p>'+companyType+' | '+companyScale+' | '+companyCity+'</p>'+
                        '<div class="dwphones"><a href="tel:'+phone+'"> <img src="'+tellPhoneNumber+'" /></a></div>'+
                        '</div>'+
                        '</div>'+
                        '</a>'+
                        '</div>';
                    $('.toudijianli').append(toudijianli);
                });
                }

            }
        })
});


function onBack() {
    window.location.href="/transition/transition_goMySelf";
}

function loadPositionInfo(url) {
    window.location.href=url;
}

