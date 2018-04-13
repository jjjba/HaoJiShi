
var  hc= [];
$(function() {

    loadRegion();

    $(".flriyous02").click(function(e){
        var hopeCity = $(e.target).attr("data");
        if(hopeCity == hc[0] || hopeCity == hc[1] ||hopeCity == hc[2] ||hopeCity == hc[3] ||hopeCity == hc[4]){
            $(".toolbarframe03").show()
            setTimeout('$(".toolbarframe03").hide()',1000);
        }else {
            if(hc.length > 4){
                $(".toolbarframe02").show()
                setTimeout('$(".toolbarframe02").hide()',1000);
            }else {
                var id = $(e.target).attr("cid");
                $('.clearfix').append('<li id="aaa'+id+'">'+hopeCity+'<a href="#" data="'+hopeCity+'" onclick="deleteCity(&quot;aaa'+id+'&quot;,&quot;'+hopeCity+'&quot;)"><img src="../../person/images/hongscs.png" class="gbanius" /></a></li>')
                hc.push(hopeCity);
            }


        }

    });

    $(".hotPro").click(function(e){
        $('.hotProvince').show();
    });


    $(".fllfzuos ul li").click(function(){
    
        $(".fllfzuos ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');
        });
        $(".fllfzuos02 ul").click(function(){
        
        $(".fllfzuos02 ul").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

        });
});

function deleteCity(cid,hopeCity) {
    $("#"+cid).remove();
    hc.splice($.inArray(hopeCity,hc),1);
    console.log("===="+hc)

}

function loadRegion() {
    $.ajax({
        url:'/region/getProvince',
        type:'POST',
        success:function (res) {
            var list =res.data;
            $.each(list, function (index, item) {
                var name=item.name;
                var id =item.id;
                $('.provincesssss').append('<li><a href="#" onclick="getCityData('+id+')"> '+name+'</a></li>');
            });
        }
    });
}
function getCityData(id) {
    $.ajax({
        url:"/region/getCityBypId",
        type:"POST",
        data:{
            id : id
        },
        success :function (res) {
            $('.flriyous02').empty();
            var city =res.data;
            var name;
            $.each(city, function (index, item) {
                name =item.name;
                var id =item.id;
                $('.flriyous02').append('<span data="'+name+'" cid ="'+id+'"> '+name+'</span>');
            });
        }
    })
}
function quedingbaocun() {
    $.ajax({
        url:"/personal/updatePersonalByPersonalId",
        type:"POST",
        data : {
            hopeCity : hc.join(",")
        },
        success : function (res) {
            window.location.href="/transition/my_resume";
        }
    })
}
function goBack() {
    window.location.href="/transition/my_resume";
}

