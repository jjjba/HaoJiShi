
var sp = [];
var special;
$(function () {

    $(".nrxbyuus").click(function(e){
        var special = $(e.target).attr("data");
        if(special == sp[0] || special == sp[1] || special == sp[2] || special == sp[3] || special == sp[4]
            || special == sp[5] ) {
            $(".toolbarframe02").show()
            setTimeout('$(".toolbarframe02").hide()',1000);
        }else {
            if(sp.length > 5){
                $(".toolbarframe03").show()
                setTimeout('$(".toolbarframe03").hide()',1000);
            }else {
                var val = $(e.target).attr("val");
                $('#neirong').append('<li id="'+val+'">'+special+'<a href="#" onclick="deleteSpecial(&quot;'+val+'&quot;,&quot;'+special+'&quot;)"> ' +
                    '<img src="../../person/images/hongscs.png" class="gbanius" /></a></li>');
                sp.push(special);
            }
        }
    });
});

function deleteSpecial(val,special) {
    $("#"+val).remove();
    sp.splice($.inArray(special,sp),1);
}
function quedingbaocun() {
    sessionStorage.setItem("special",sp.join(","));
    window.location.href="/transition/go_wan_shan_xin_xi";
}

function fanhui() {
    window.location.href="/transition/go_wan_shan_xin_xi";
}