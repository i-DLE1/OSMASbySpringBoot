let projectListCount = 1;

// function proejctQAList(filter){
//     $("#customerQAList").html("");

//     // 더미 데이터
//     let projectList = [
//         {id : 1,  title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
//         {id : 8, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
//         {id : 12, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
//         {id : 20, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
//         {id : 100, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
//         {id : 12, title : "title2",  accountID : "홍길동", content : "내내용ㅇ요ㅐ요ㅐ애ㅛ애ㅛ내요내", phone:"010-1111-2222",option:"옵션1",money:"40,000",payment:"카드", createDate:"2023-01-23",state:"완료"},
//     ];
//     projectList.forEach((item,index)=>{
//         let $tr = $("<tr>").attr("onclick","qaPopup(this)").attr("id",`${item.id}`);
//         $tr.append($("<th>").text(index+1)); // 인덱스 번호
//         $tr.append($("<td>").text(item.title)); // 프로젝트명
//         $tr.append($("<td>").text(item.accountID)); // 작성자 아이디
//         $tr.append($("<td>").text(item.content)); // 내용 일부
//         $tr.append($("<td>").text(item.phone)); // 연락처
//         $tr.append($("<td>").text(item.option)); // 상품옵션
//         $tr.append($("<td>").text(item.money)); // 결제금액
//         $tr.append($("<td>").text(item.payment)); // 결제수단
//         $tr.append($("<td>").text(item.createDate)); // 생성날짜
//         $tr.append($("<td>").text(item.state)); // 상태
//         $("#customerQAList").append($tr);
//     })
// }

// function qaPopup(e){
//     window.open(`./qa_answer.html?id=${e.id}`,"popup","height=650px, width=500px, resizable=no, name=qa_answer");
// }

// function activeElement(element){
//     let selectEl = $(element);
//     let selectId = selectEl.prop("id");

//     $(`#${selectId}Menu`).siblings("li").removeClass("active-page");
//     $(`#${selectId}Menu`).addClass("active-page");

//     selectEl.prop("id");
//     selectEl.siblings("td").removeClass("active-page");
//     //$(`${}Menu`).addClass("active-page")
//     selectEl.addClass("active-page");
//     return selectId;
// }
// $("#cusMenu td").click(function (){
//     let selectId = activeElement(this);
//     customerProjectList(selectId);
// })

// $("#qaMenu td").click(function (){
//     let selectId = activeElement(this);
//     proejctQAList(selectId);
// })

// function popupCancel(id) {
//     window.open(`./cancel.html?id=${id}`,"popup","height=900px, width=600px, resizable=no, name=projectCancel, location=no");
// }

// function popupStatics(id){
//     window.open(`./projectDetail.html?id=${id}`,"popup","height=1000px, width=1500px, resizable=no, name=projectStatics, location=no");
// }

// function projectModify(id){
//     window.open(`../createProject/index.html?id=${id}`,"popup");
// }
// function popupRetry(id){
//     window.open(`./retry.html?id=${id}`,"popup","height=600px, width=500px, resizable=no, name=projectRetry, location=no");
// }
// function cancelTableDummyData(){
//     let data =
//         {title : "프로젝트명", startDate : "2023-01-01", endDate : "2023-10-10",
//         money : "90,000,000",currentMoney : "9,000,000",accomplieRate : "10%",   cancelFee : "2%"
//             , estimate:"180,000", state : "진행중" };
//     cancelTable(data);
// }

// function ccustomerOrderList(data) {

//     let $tableTr = $("#cancel-table tr");
//     $($tableTr[0]).children("td").text(data.title); // 프로젝트 명
//     $($tableTr[1]).children("td").text(data.startDate); // 시작일
//     $($tableTr[2]).children("td").text(data.endDate); // 종료일
//     $($tableTr[3]).children("td").text(data.money); // 목표금액
//     $($tableTr[4]).children("td").text(data.currentMoney); // 모금액
//     $($tableTr[5]).children("td").text(data.accomplieRate); // 달성률
//     $($tableTr[6]).children("td").text(data.cancelFee); // 취소 수수료율
//     $($tableTr[7]).children("td").text(data.estimate); // 취소 예상금액
//     $($tableTr[8]).children("td").text(data.state); // 상태
// }

// $("#cancel-content").on("input",function (){
//     $(this).siblings("label").children("span").text(this.value.length)
// })


function customerOrderList(filter){
    $("#customerOrderList").html("");
    // 더미 데이터
    let projectList = [
        {id: 1, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",state:"주문접수"},
        {id: 2, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",state:"주문접수"},
        {id: 3, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",state:"주문접수"},
        {id: 4, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",state:"주문접수"},

    ];

    projectList.forEach((item ,index)=>{
        let $tr = $("<tr>").attr("id",item.id);
        let $td = $("<td>");
        $tr.append($("<th>").text(index+1));
        $tr.append($("<td>").text(item.title));
        $tr.append($("<td>").text(item.address));
        $tr.append($("<td>").text(item.comment));
        $tr.append($("<td>").text(item.call));
        $tr.append($("<td>").text(item.option));
        $tr.append($("<td>").text(item.money));
        $tr.append($("<td>").text(item.payment));
        $tr.append($("<td>").text(item.date));
        $tr.append($("<td>").text(item.state));
        $tr.append($td);

        $("#customerOrderList").append($tr);
    })
}

function customerOrderCheck(filter){
    $("#customerOrderCheck").html("");
    // 더미 데이터
    let projectList = [
        {id: 1, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01"},
        {id: 2, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01"},
        {id: 3, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01"},
        {id: 4, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01"},

    ];

    projectList.forEach((item ,index)=>{
        let $tr = $("<tr>").attr("id",item.id);
        let $td = $("<td>");

        let $ordercheck = $("<input>").addClass("checkoption")
            .attr("type","checkbox")
        $td.append($ordercheck);


        $tr.append($("<th>").text(index+1));
        $tr.append($("<td>").text(item.title));
        $tr.append($("<td>").text(item.address));
        $tr.append($("<td>").text(item.comment));
        $tr.append($("<td>").text(item.call));
        $tr.append($("<td>").text(item.option));
        $tr.append($("<td>").text(item.money));
        $tr.append($("<td>").text(item.payment));
        $tr.append($("<td>").text(item.date));
        $tr.append($td);

        $("#customerOrderCheck").append($tr);
    })
}


function customerOrderConfirmList(filter){
    $("#customerOrderConfirmList").html("");
    // 더미 데이터
    let projectList = [
        {id: 1, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",tracking:"202306010101"},
        {id: 2, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",tracking:"202306010101"},
        {id: 3, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",tracking:"202306010101"},
        {id: 4, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",tracking:"202306010101"},

    ];

    projectList.forEach((item ,index)=>{
        let $tr = $("<tr>").attr("id",item.id);
        let $td = $("<td>");
        $tr.append($("<th>").text(index+1));
        $tr.append($("<td>").text(item.title));
        $tr.append($("<td>").text(item.address));
        $tr.append($("<td>").text(item.comment));
        $tr.append($("<td>").text(item.call));
        $tr.append($("<td>").text(item.option));
        $tr.append($("<td>").text(item.money));
        $tr.append($("<td>").text(item.payment));
        $tr.append($("<td>").text(item.date));
        $tr.append($("<td>").text(item.tracking));
        $tr.append($td);

        $("#customerOrderConfirmList").append($tr);
    })
}

function customerOrderRefundList(filter){
    $("#customerOrderRefundList").html("");
    // 더미 데이터
    let projectList = [
        {id: 1, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",refunddate:"2023.06.05"},
        {id: 2, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",refunddate:"2023.06.05"},
        {id: 3, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",refunddate:"2023.06.05"},
        {id: 4, title : "홍길동",address:"서울시00구", comment:"문앞에놔주세요",call:"010-1234-5678",option:"블랙,90(S),1",money:"46,000",payment:"카카오페이",date:"2023.06.01",refunddate:"2023.06.05"},

    ];

    projectList.forEach((item ,index)=>{
        let $tr = $("<tr>").attr("id",item.id);
        let $td = $("<td>");
        $tr.append($("<th>").text(index+1));
        $tr.append($("<td>").text(item.title));
        $tr.append($("<td>").text(item.address));
        $tr.append($("<td>").text(item.comment));
        $tr.append($("<td>").text(item.call));
        $tr.append($("<td>").text(item.option));
        $tr.append($("<td>").text(item.money));
        $tr.append($("<td>").text(item.payment));
        $tr.append($("<td>").text(item.date));
        $tr.append($("<td>").text(item.refunddate));
        $tr.append($td);

        $("#customerOrderRefundList").append($tr);
    })
}

function customerOrdercalculate(filter){
    $("#customerOrdercalculate").html("");
    // 더미 데이터
    let projectList = [
        {id: 1, title : "장애인-비장애인 누구나 입는 '유니버셜' 의류", income:"600,000", commission:"60,000", total:"540,000"},
        {id: 2, title : "장애인-비장애인 누구나 입는 '유니버셜' 의류", income:"600,000", commission:"60,000", total:"540,000"},
        {id: 3, title : "장애인-비장애인 누구나 입는 '유니버셜' 의류", income:"600,000", commission:"60,000", total:"540,000"},

    ];

    projectList.forEach((item ,index)=>{
        let $tr = $("<tr>").attr("id",item.id);
        let $td = $("<td>");
        $tr.append($("<th>").text(index+1));
        $tr.append($("<td>").text(item.title));
        $tr.append($("<td>").text(item.income));
        $tr.append($("<td>").text(item.commission));
        $tr.append($("<td>").text(item.total));
        $tr.append($td);

        $("#customerOrdercalculate").append($tr);
    })
}

function orderConfirmation() {
    var result = confirm('배송처리 하시겠습니까?');
    if (result) {
        alert('배송처리 되었습니다.');
    } else {
        alert('취소하셨습니다.');
    }
}

function orderExchange() {
    var result = confirm('교환처리 하시겠습니까?');
    if (result) {
        alert('교환처리 되었습니다.');
    } else {
        alert('취소하셨습니다.');
    }
}

function orderRefund() {
    var result = confirm('환불처리 하시겠습니까?');
    if (result) {
        alert('환불처리 되었습니다.');
    } else {
        alert('취소하셨습니다.');
    }
}