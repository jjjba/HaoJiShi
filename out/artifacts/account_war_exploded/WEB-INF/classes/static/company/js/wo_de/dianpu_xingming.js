function baocun() {
    var name = $("#Name").val();
    if(name != "" && name !='' && name != undefined){
        sessionStorage.setItem("Name",name);
        window.location.href="/transition/go_zhu_ce_tian_xie_xin_xi";
    }else{
        $(".NameBwk").show();
        setTimeout('$(".NameBwk").hide()',600);
    }

}
function Xmfanhui() {
    window.location.href="/transition/go_zhu_ce_tian_xie_xin_xi";
}