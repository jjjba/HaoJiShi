
$(function () {

});

function loadPosition() {
    var data ={}
    $.ajax({
        url:"",
        type: "POST",
        data:data,
        success :function (res) {

        },
        error :function (res) {

        }

    })
}

function transition_search() {
    window.location.href="/transition/transition_search";
}

function goIndex() {
    window.location.href="/transition/transition_goIndex";
}

function goMySelf() {
    window.location.href="/transition/transition_goMySelf";
}