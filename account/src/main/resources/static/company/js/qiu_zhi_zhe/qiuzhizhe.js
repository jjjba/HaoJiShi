$(function () {
    loadPersonalData();
});


function loadPersonalData() {
    // var data ={
    //
    // }
    $.ajax({
        url:"/personal/getPersonal",
        type:"POST",
        // data:data,
        success :function (res) {
            if (res.success) {
                $('.positionList').empty();
                var list = res.data;
                var avatar, sex;
                $.each(list, function (index, item) {
                    avatar =item.avatar;
                    if(avatar == null || avatar == ""){
                        if(item.sex == "男"){

                            avatar ="../../company/images/tupian01.png"
                        }else {
                            avatar ="../../company/images/tupian02.png"
                        }
                    }
                    sex =item.sex;
                    if(sex == "男"){
                        sex ="../../company/images/biao05.png"
                    }else {
                        sex ="../../company/images/biao06.png"
                    }
                    var personalList ='<a href="#" onclick="loadPersonalInfoById(\'/transition/go_qiu_zhi_zhe_xiang_qing?id='+item.id+'\')" >'+
                        '<div class="rcxinxis">'+
                        '<div class="rcktops">'+
                        '<div class="reclefs">'+
                        '<img src="'+avatar+'" />'+
                        '</div>'+
                        '<div class="recryous">'+
                        '<div class="clearfix rcvsise">'+
                        '<div class="fl rminsii">'+item.name+' <span><img src="'+sex+'">'+item.age+'</span></div>'+
                        '<div class="fr ciiuss">'+item.expectMoney+'</div>'+
                        '</div>'+
                        '<div class="xnxyusie"> '+item.jobExperience+' | '+item.state+' | '+item.address+' </div>'+
                        '</div>'+
                        '</div>'+
                        '<div class="pxbiuss">'+
                        '<div class="pxlefts">'+item.hopeJob+'</div>'+
                        '<div class="pxyouls"><a href="#" onclick="tellPhone()"><img src="./../company/images/biao07.png" /></div></a>'+
                        '</div>'+
                        '</div>'+
                        '</a>';
                    $('.personalList').append(personalList);
                })
            }
        }
    })    
}
function loadPersonalByParams() {
    
}























function goIndex() {
    window.location.href="/transition/go_company_index"
}

function gowode() {
    window.location.href="/transition/go_wo_de";
}

