$("#retry-body").on("input",function (){
    $(this).siblings("label").children("span").text(this.value.length)
});

$("#qa-answer").on("input",function (){
    console.log($(this).siblings("label")[0])
    $(this).siblings("label").children("span").text(this.value.length)
})


function qaAnswerSubmit(submitType) {
    const content = $("#qa-answer").val();
    const queryStr = new URLSearchParams(location.search);
    $.ajax({
        url : `/seller/projectDetail/qaAnswer?id=${queryStr.get('id')}&submitType=${submitType}`,
        type : "post",
        contentType : "application/json; charset=utf-8;",
        data : content,
        success : function (success) {
            window.close()
        },
        error : function (error) {
            console.log(error)
        }
    })
}

function projectRetrySubmit(){
    const content = $("#retry-body").val();
    const no = new URLSearchParams(location.search).get('no');
    $.ajax({
        url : `seller/projectDetail/retry?no=${no}`,
        type : "post",
        data : content,
        contentType: "application/json; charset=utf-8",
        success : function (success){
            console.log(success);
        },
        error : function (error){
            console.log(error);
        }
    })
}


