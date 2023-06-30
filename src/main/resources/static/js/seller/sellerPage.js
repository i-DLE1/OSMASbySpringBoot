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

function projectOrderList(filter){
    $("#projectOrderList").html("");

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
        $tr.append($("<th>").text(index+1)); // 인덱스 번호
        $tr.append($("<td>").text(item.title)); // 주문자
        $tr.append($("<td>").text(item.address)); // 배송지
        $tr.append($("<td>").text(item.comment)); // 요청사항
        $tr.append($("<td>").text(item.call)); // 연락처
        $tr.append($("<td>").text(item.option)); // 구매옵션
        $tr.append($("<td>").text(item.money)); // 결제금액
        $tr.append($("<td>").text(item.payment)); // 결제수단
        $tr.append($("<td>").text(item.date)); // 주문일자
        $tr.append($("<td>").text(item.state)); // 배송상태
        $tr.append($td);

        $("#projectOrderList").append($tr);
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

// 화면 위치 시작
const screenCenterPopup = (popupWidth, popupHeight) =>{
    const screenWidth = window.screen.width
    const screenHeight = window.screen.height

    const popupX = (screenWidth /2) - (popupWidth /2);
    const popupY = (screenHeight /2) - (popupHeight /2);

    return `width=${popupWidth} , height=${popupHeight}, left=${popupX}, top=${popupY}`;
}
// 화면 위치 끝

function qaPopup(e){
    window.open(`./projectDetail/qaAnswer?id=${e.id}`,"popup",`${screenCenterPopup(500,700)}, resizable=no, name=qa_answer`);
}

function popupCancel(id) {
    window.open(`./projectDetail/cancel?id=${id}`,"popup",`${screenCenterPopup(450, 650)}, resizable=no, name=projectCancel, location=no`);
}

function popupStatics(id){
    window.open(`./projectDetail/projectDetail?id=${id}`,"popup",`${screenCenterPopup(1600,1000)}, resizable=no, name=projectStatics, location=no`);
}

function projectModify(id){
    window.open(`../createProject/index?id=${id}`,"popup");
}
function popupRetry(id){
    window.open(`./projectDetail/retry?id=${id}`,"popup",`${screenCenterPopup(500,600)}, resizable=no, name=projectRetry, location=no`);
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


function activePage() {
    let pathname = location.pathname;
    let queryStr = new URLSearchParams(location.search).get('listType');
    let $titleSpan = $(".h1-title").children('span')
    let $tdSpan = $(".td-title").children('span')
    switch (pathname+'?listType='+queryStr) {
        case '/seller/projectList' :
        case '/seller/projectList?listType=all' :
            $("#cusAllMenu").addClass('active-page') ;
            $("#cusAll").addClass('active-page') ;
            // $titleSpan.text('전체조회');
            break;

        case '/seller/projectList?listType=screening' :
            $("#cusScreenMenu").addClass('active-page') ;
            $("#cusScreen").addClass('active-page') ;
            // $titleSpan.text('심사중');
            break;

        case '/seller/projectList?listType=processing' :
            $("#cusProcessMenu").addClass('active-page') ;
            $("#cusProcess").addClass('active-page') ;
            // $titleSpan.text('진행중');
            break;

        case '/seller/projectList?listType=refuse' :
            $("#cusRefuseMenu").addClass('active-page') ;
            $("#cusRefuse").addClass('active-page') ;
            // $titleSpan.text('반려');
            break;

        case '/seller/projectList?listType=cancel' :
            $("#cusCancelMenu").addClass('active-page') ;
            $("#cusCancel").addClass('active-page') ;
            // $titleSpan.text('취소');
            break;

        case '/seller/projectQnAList' :
        case '/seller/projectQnAList?listType=all' :
            $("#qaAllMenu").addClass('active-page') ;
            $("#qaAll").addClass('active-page') ;
            // $titleSpan.text('전체조회');
            break;

        case '/seller/projectQnAList?listType=wait' :
            $("#qaWaitMenu").addClass('active-page') ;
            $("#qaWait").addClass('active-page') ;
            // $titleSpan.text('답변 대기중');
            break;

        case '/seller/projectQnAList?listType=complete' :
            $("#qaCompleteMenu").addClass('active-page') ;
            $("#qaComplete").addClass('active-page') ;
            // $titleSpan.text('완료');
            break;

        case '/seller/orderList?listType=all' :
            $("#orderAllMenu").addClass('active-page') ;
            $("#orderAll").addClass('active-page') ;
            $titleSpan.text('전체조회');
            break;

        case '/seller/orderList?listType=receipt' :
            $("#orderReceiptMenu").addClass('active-page') ;
            $("#orderReceipt").addClass('active-page') ;
            $titleSpan.text('주문접수');
            $tdSpan.text('배송처리');
            break;

        case '/seller/orderList?listType=delivery' :
            $("#orderDeliveryMenu").addClass('active-page') ;
            $("#orderDelivery").addClass('active-page') ;
            $titleSpan.text('배송처리');
            $tdSpan.text('운송장번호');
            break;

        case '/seller/orderList?listType=refund' :
            $("#orderRefundMenu").addClass('active-page') ;
            $("#orderRefund").addClass('active-page') ;
            $titleSpan.text('교환환불');
            $tdSpan.text('상태변경');
            break;

        case '/seller/orderList?listType=cancel' :
            $("#orderCancelMenu").addClass('active-page') ;
            $("#orderCancel").addClass('active-page') ;
            $titleSpan.text('취소내역');
            $tdSpan.text('취소일자');
            break;

        case '/seller/orderList?listType=calculate' :
            $("#orderCalculateMenu").addClass('active-page') ;
            $("#orderCalculate").addClass('active-page') ;
            $titleSpan.text('주문정보');
            break;

    }
}
function projectSearch() {
    const searchType = $("#searchType").val()
    const path = location.pathname
    const search = $("#search").val()
    const listType = new URLSearchParams(location.search).get('listType')
    location.replace(`${path}?listType=${listType}${searchType === undefined ? '' : '&searchType='+searchType}&search=${search}`)
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

function deleteProject(){
    let deleteConfirm = confirm("프로젝트를 삭제하시겠습니까?")
    if(deleteConfirm){
        let deleteConfirmRetry = confirm("삭제할 경우 프로젝트 복구는 불가능합니다.\n정말 삭제하시겠습니까?")
    }else {

    }
}


function cancelSubmit() {
    let content = $("#cancel-content").val();
    let id = new URLSearchParams(location.search).get('id');
    console.log(id)

    $.ajax({
        url : `/seller/projectDetail/cancel?id=${id}`,
        type : "post",
        contentType : "application/json; charset=utf-8;",
        data : content,
        success : function (success){
            location.reload()
        },
        error : function (error) {
            console.log(error)
        }
    })
}