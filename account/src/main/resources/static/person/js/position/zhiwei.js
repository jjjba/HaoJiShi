// JavaScript Document

$(document).ready(function() {

        $(".fllfzuos ul li").click(function(){
        
           $(".fllfzuos ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

        });
		
		
	$(".xzdpgms p a").click(function(){
	$(this).addClass("cuiysy").siblings().removeClass("cuiysy");
	}).hover(function(){
	$(this).addClass("yszdsids2");
	},function () {
	$(this).removeClass("yszdsids2");
	});	

   
   $(".dqkouss").hide();
   $(".zwciys01").hide();
   $(".zwciys02").hide();

   $(".dhxlfs03").click(function(){
	   
	   $(".zwciys01").hide();
	   $(".zwciys02").hide();
	   
	   
	   if($(".dqkouss").is(":hidden")){
		   $(".dqkouss").show();
		   $(".dhxlfs03").addClass("curts");
		   }else{
			 
			$(".dqkouss").hide(); 
			   $(".dhxlfs03").removeClass("curts");
			   }
			   
			$(".dhxlfs01").removeClass("curts");
			$(".dhxlfs02").removeClass("curts");
	   
	   });
	   


 $(".flriyous span").click(function(){
	 $(".zwciys01").hide();
	 $(".dhxlfs01").removeClass("curts");
	 });


   $(".dhxlfs01").click(function(){
	   
	   $(".zwciys02").hide();
	   $(".dqkouss").hide();
	   
	   if($(".zwciys01").is(":hidden")){
		   $(".zwciys01").show();
		   $(".dhxlfs01").addClass("curts");
		   }else{
			 
			$(".zwciys01").hide(); 
			   $(".dhxlfs01").removeClass("curts");
			   }
			
			$(".dhxlfs02").removeClass("curts");
	         $(".dhxlfs03").removeClass("curts");
	   });


   $(".qdius").click(function(){
	   
	   $(".zwciys02").hide();
	   
	   $(".dhxlfs02").removeClass("curts");
	   
	   });

   $(".dhxlfs02").click(function(){
	   
	   $(".zwciys01").hide();
	   $(".dqkouss").hide();
	   
	   if($(".zwciys02").is(":hidden")){
		   $(".zwciys02").show();
		   $(".dhxlfs02").addClass("curts");
		   }else{
			 
			$(".zwciys02").hide(); 
			   $(".dhxlfs02").removeClass("curts");
			   }
			
			$(".dhxlfs01").removeClass("curts");
	         $(".dhxlfs03").removeClass("curts");
	   });

  $(".fenlisse ul li,.fenlisse h2,#positionType").click(function(){
	  
	  $(".zwciys01").hide();
	  $(".dhxlfs01").removeClass("curts");
	  
	  });

    $(".rmcshis p a,.city-list p,.city-list ul li,.container").click(function(){
        $("#region111111111111").hide();

        $(".dhxlfs03").removeClass("curts");

    });

  



});











