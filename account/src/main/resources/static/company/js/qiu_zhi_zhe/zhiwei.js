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

  $(".fenlisse ul li,.fenlisse h2").click(function(){
	  
	  $(".zwciys01").hide();
	  $(".dhxlfs01").removeClass("curts");
	  
	  });

  
  $(".rmcshis p a,.city-list p").click(function(){
	  
	  $(".dqkouss").hide();
	  
	  $(".dhxlfs03").removeClass("curts");
	  
	  });


$(".popupus").hide();
$(".pxyouls").click(function(){
	
	$(".popupus").show();
	
	});
$(".wzkljgz01,.wzkljgz02").click(function(){
	
	$(".popupus").hide();
	
	});


});

function jishilei() {
    $('#jishi').show();
    $('#guanli').hide();
    $('#qianting').hide();
    $('#houqin').hide();
    $('#peixun').hide();
}
function guanlilei() {
    $('#jishi').hide();
    $('#guanli').show();
    $('#qianting').hide();
    $('#houqin').hide();
    $('#peixun').hide();
}
function qiantinglei() {
    $('#jishi').hide();
    $('#guanli').hide();
    $('#qianting').show();
    $('#houqin').hide();
    $('#peixun').hide();
}
function houqinlei() {
    $('#jishi').hide();
    $('#guanli').hide();
    $('#qianting').hide();
    $('#houqin').show();
    $('#peixun').hide();
}
function peixunlei() {
    $('#jishi').hide();
    $('#guanli').hide();
    $('#qianting').hide();
    $('#houqin').hide();
    $('#peixun').show();
}









