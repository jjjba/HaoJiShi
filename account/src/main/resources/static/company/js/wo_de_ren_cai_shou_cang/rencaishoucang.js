$(document).ready(function() {
        $.ajax({
            type:"post",
            url:"/company/getRenCaishoucang",
            success:function (msg) {

            }
        })
});

function renfan() {
    window.location.href="/transition/go_wo_de";
}