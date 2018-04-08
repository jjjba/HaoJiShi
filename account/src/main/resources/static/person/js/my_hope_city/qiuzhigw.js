// JavaScript Document

$(document).ready(function() {

    loadHopeJob();
        $(".fllfzuos ul li").click(function(){
    
        $(".fllfzuos ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

        });
        $(".fllfzuos02 ul li").click(function(){
        
        $(".fllfzuos02 ul li").eq($(this).index()).addClass("houvers").siblings().removeClass('houvers');

        });
		
		
		
		
	
});


function loadHopeJob() {
    $.ajax({
        url:'/personal/getPersonalInfo',
        type:'POST',
        success:function (res) {
            var job =res.data.hope_job;
            console.log(job)
            if(job == null || job == ""){
                job ="";
            }
        }
    })
}