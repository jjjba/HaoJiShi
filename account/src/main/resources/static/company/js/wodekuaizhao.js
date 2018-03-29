// JavaScript Document

$(document).ready(function() {

        $(".yliuiss li").click(function(){
        
          $(".yliuiss li").eq($(this).index()).addClass("borsbers").siblings().removeClass('borsbers');
          $(".fufeigl").hide().eq($(this).index()).show();


        });


});













