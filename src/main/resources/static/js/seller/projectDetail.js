function projectStaticListDummyData() {
    let data = [
        {title: "블라블라", money:"25000", count: 1000, totalMoney : "25,000,000"},
        {title: "블라블라1", money:"251000", count: 100, totalMoney : "25,000,000"},
        {title: "블라블라2", money:"225000", count: 1200, totalMoney : "25,000,000"},
        {title: "블라블라3", money:"235000", count: 1400, totalMoney : "25,000,000"},
    ];
    projectStaticsListLoad(data);
}

function projectStaticsListLoad(data){
    data.forEach((item,index)=>{
        let $tr = $("<tr>");
        $tr.append($("<th>").text(index+1));
        $tr.append($("<td>").text(item.title));
        $tr.append($("<td>").text(item.money));
        $tr.append($("<td>").text(item.count));
        $tr.append($("<td>").text(item.totalMoney));
        $("#product-statics-list").append($tr);
    })
}

function projectDetailDummyData(){
    let data =
        {title : "프로젝트명1", startDate:"2023-01-12",
            endDate:"2023-10-12", remainPeriod : "200",
            money : "100,000,000", currentMoney : "25,000,000"
            ,user : "1000"};
    projectDetailLoad(data);
}

function projectDetailLoad(data) {
    let $projectDetailTableTr = $("#project-detail-table>tbody>tr");

    $($projectDetailTableTr[0]).children("td").text(data.title); // 프로젝틈명
    $($projectDetailTableTr[1]).children("td").text(data.startDate); // 시작일
    $($projectDetailTableTr[2]).children("td").text(data.endDate); // 종료일
    $($projectDetailTableTr[3]).children("td").text(data.remainPeriod); // 남은기간
    $($projectDetailTableTr[4]).children("td").text(data.money); // 목표금액
    $($projectDetailTableTr[5]).children("td").text(data.currentMoney); // 모금액
    $($projectDetailTableTr[6]).children("td").text(data.user); // 참여수

}
$("#retry-body").on("input",function (){
    $(this).siblings("label").children("span").text(this.value.length)
});

$("#qa-answer").on("input",function (){
    console.log($(this).siblings("label")[0])
    $(this).siblings("label").children("span").text(this.value.length)
})


function qaAnswerSubmit(submitType) {
    const content = $("#qa-answer").val();
    console.log(content)
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
