// JavaScript Document

$(document).ready(function() {
    
	      $(".fllfzuos ul li").click(function(){
        
           $(".fllfzuos ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

        });
		
	        $(".fllfzuos02 ul li").click(function(){
        
           $(".fllfzuos02 ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

        });
		
		
		
		
	
});