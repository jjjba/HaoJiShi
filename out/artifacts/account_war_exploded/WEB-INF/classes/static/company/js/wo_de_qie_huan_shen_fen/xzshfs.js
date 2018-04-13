// JavaScript Document

$(document).ready(function() {
   
           $(".zhijiss li").click(function(){
        
          $(".zhijiss li").eq($(this).index()).addClass("cuyuis").siblings().removeClass('cuyuis');


        });

   
    
});