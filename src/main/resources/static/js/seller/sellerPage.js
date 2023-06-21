let projectListCount = 1;

function customerProjectList(filter){
    $("#customerProjectList").html("");
    // 더미 데이터
    let projectList = [
        {id: 1, title : "title1",money:"100,100,000", currentMoney:"900,900",startDate:"2023-01-01",endDate:"2023-10-10",state:"진행중"},
        {id: 2, title : "title2",money:"100,100,000", currentMoney:"900,900",startDate:"2023-01-01",endDate:"2023-10-10",state:"진행중"},
        {id: 10, title : "title2",money:"100,100,000", currentMoney:"900,900",startDate:"2023-01-01",endDate:"2023-10-10",state:"진행중"},
        {id: 15, title : "title3",money:"100,100,000", currentMoney:"900,900",startDate:"2023-01-01",endDate:"2023-10-10",state:"진행중"},
    ];

    projectList.forEach((item ,index)=>{
        let $tr = $("<tr>").attr("id",item.id);
        let $td = $("<td>");

        let $inputStatics = $("<input>").addClass("cus-button")
                                        .attr("value","통계")
                                        .attr("type","button")
                                        .attr("onclick",`popupStatics(${item.id})`);
        $td.append($inputStatics);

        let $inputModify = $("<input>").addClass("cus-button")
                                        .attr("value","수정")
                                        .attr("type","button")
                                        .attr("onclick",`projectModify(${item.id})`);
        $td.append($inputModify);

        let $inputRetry = $("<input>").addClass("cus-button")
                                        .attr("value","재심사")
                                        .attr("type","button")
                                        .attr("onclick",`popupRetry(${item.id})`);
        $td.append($inputRetry);

        let $inputCancel = $("<input>").addClass("cus-button")
                                        .attr("value","취소")
                                        .attr("type","button")
                                        .attr("onclick",`popupCancel(${item.id})`);
        $td.append($inputCancel);

        $tr.append($("<th>").text(index+1));
        $tr.append($("<td>").text(item.title));
        $tr.append($("<td>").text(item.money));
        $tr.append($("<td>").text(item.currentMoney));
        $tr.append($("<td>").text(`${item.startDate} ~ ${item.endDate}`));
        $tr.append($("<td>").text(item.state));
        $tr.append($td);

        $("#customerProjectList").append($tr);
    })
}

function proejctQAList(filter){
    $("#customerQAList").html("");

    // 더미 데이터
    let projectList = [
        {id : 1,  title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
        {id : 8, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
        {id : 12, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
        {id : 20, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
        {id : 100, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
        {id : 12, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
    ];
    projectList.forEach((item,index)=>{
        let $tr = $("<tr>").attr("onclick","qaPopup(this)").attr("id",`${item.id}`);
        $tr.append($("<th>").text(index+1)); // 인덱스 번호
        $tr.append($("<td>").text(item.title)); // 프로젝트명
        $tr.append($("<td>").text(item.accountID)); // 작성자 아이디
        $tr.append($("<td>").text(item.content)); // 내용 일부
        $tr.append($("<td>").text(item.phone)); // 연락처
        $tr.append($("<td>").text(item.option)); // 상품옵션
        $tr.append($("<td>").text(item.money)); // 결제금액
        $tr.append($("<td>").text(item.payment)); // 결제수단
        $tr.append($("<td>").text(item.createDate)); // 생성날짜
        $tr.append($("<td>").text(item.state)); // 상태
        $("#customerQAList").append($tr);
    })
}

function qaPopup(e){
    window.open(`./qa_answer.html?id=${e.id}`,"popup","height=650px, width=500px, resizable=no, name=qa_answer");
}

function activeElement(element){
    let selectEl = $(element);
    let selectId = selectEl.prop("id");

    $(`#${selectId}Menu`).siblings("li").removeClass("active-page");
    $(`#${selectId}Menu`).addClass("active-page");

    selectEl.prop("id");
    selectEl.siblings("td").removeClass("active-page");
    //$(`${}Menu`).addClass("active-page")
    selectEl.addClass("active-page");
    return selectId;
}
$("#cusMenu td").click(function (){
    let selectId = activeElement(this);
    customerProjectList(selectId);
})

$("#qaMenu td").click(function (){
    let selectId = activeElement(this);
    proejctQAList(selectId);
})

function popupCancel(id) {
    window.open(`./cancel.html?id=${id}`,"popup","height=900px, width=600px, resizable=no, name=projectCancel, location=no");
}

function popupStatics(id){
    window.open(`./projectDetail.html?id=${id}`,"popup","height=1000px, width=1500px, resizable=no, name=projectStatics, location=no");
}

function projectModify(id){
    window.open(`../createProject/index.html?id=${id}`,"popup");
}
function popupRetry(id){
    window.open(`./retry.html?id=${id}`,"popup","height=600px, width=500px, resizable=no, name=projectRetry, location=no");
}
function cancelTableDummyData(){
    let data =
        {title : "프로젝트명", startDate : "2023-01-01", endDate : "2023-10-10",
        money : "90,000,000",currentMoney : "9,000,000",accomplieRate : "10%",   cancelFee : "2%"
            , estimate:"180,000", state : "진행중" };
    cancelTable(data);
}

function cancelTable(data) {

    let $tableTr = $("#cancel-table tr");
    $($tableTr[0]).children("td").text(data.title); // 프로젝트 명
    $($tableTr[1]).children("td").text(data.startDate); // 시작일
    $($tableTr[2]).children("td").text(data.endDate); // 종료일
    $($tableTr[3]).children("td").text(data.money); // 목표금액
    $($tableTr[4]).children("td").text(data.currentMoney); // 모금액
    $($tableTr[5]).children("td").text(data.accomplieRate); // 달성률
    $($tableTr[6]).children("td").text(data.cancelFee); // 취소 수수료율
    $($tableTr[7]).children("td").text(data.estimate); // 취소 예상금액
    $($tableTr[8]).children("td").text(data.state); // 상태
}

$("#cancel-content").on("input",function (){
    $(this).siblings("label").children("span").text(this.value.length)
})
