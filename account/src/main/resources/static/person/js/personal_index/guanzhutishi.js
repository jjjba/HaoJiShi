
$(function(){
    console.log("cookie===="+$.cookie('num'))
    if($.cookie('num') == 'null' || $.cookie('num') =='0'){
        $.cookie('num', '1');
        $('.pupuinto').show();
    }

    var date = new Date();
    date.setTime(date.getTime() + 60 * 60 * 1000 + 8 * 60 * 60 * 1000);
    $.cookie('num', '1', {expires: date});
});
