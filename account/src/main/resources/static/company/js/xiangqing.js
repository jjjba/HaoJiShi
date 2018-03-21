// JavaScript Document


  $(document).ready(function() {
        
  $('.d-btn').click(function(){
				if($(this).text()=="收藏"){
					$(this).html("<i></i>已收藏").addClass("curs");
				}else{
					$(this).html("<i></i>收藏").removeClass("curs");
				}
			});

		 
      });


var lanrenzhijia = {
	message:function(text,time){
		if(!text || !time){
			return false;
		}
		var str = $("<div class='popMessage'><div class='mobileMessage'>"+text+"</div></div>");
		str.appendTo("body");
		$('.popMessage').css({
			position:'fixed',
			width:'100%',
			height:'100%',
			left:'0',
			top:'0',
			'z-index':'50'
		})
		$('.mobileMessage').css({
			width:'5rem',
			height:'auto',
			position:'absolute',
			top:'50%',
			left:'50%',
			padding:'0.8rem 0.1rem 0.8rem 0.1rem',
			'border-radius':'0.3rem',
			background:'0.1rem rgba(0,0,0,0.7)',
			
			overflow:'hidden',
			'text-align':'center',
			color:'#fff',
			'font-size':'0.16rem',
			'z-index':'9999',
			'-webkit-transform': 'translate(-50%,-50%)',
			'-moz-transform': 'translate(-50%,-50%)',
			'transform': 'translate(-50%,-50%)'
		});
		var offMessage = function remove(){
			$('.popMessage').remove();
		}
		setTimeout(offMessage,time);
	}
}




