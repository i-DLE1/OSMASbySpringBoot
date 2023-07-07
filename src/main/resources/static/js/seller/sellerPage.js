let projectListCount = 1;

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
    window.open(`./projectDetail/qaAnswer?no=${e.id}`,"popup",`${screenCenterPopup(500,700)}, resizable=no, name=qa_answer`);
}

function popupCancel(id) {
    window.open(`./projectDetail/cancel?no=${id}`,"popup",`${screenCenterPopup(450, 650)}, resizable=no, name=projectCancel, location=no`);
}

function popupStatics(id){
    window.open(`./projectDetail/projectDetail?no=${id}`,"popup",`${screenCenterPopup(1600,1000)}, resizable=no, name=projectStatics, location=no`);
}

function projectModify(id){
    window.open(`/seller/regist/project1?no=${id}`,"popup");
}
function popupRetry(no){
    window.open(`./projectDetail/retry?no=${no}`,"popup",`${screenCenterPopup(450,600)}, resizable=no, name=projectRetry, location=no`);
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

        case '/seller/projectList?listType=rejected' :
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
            // $titleSpan.text('전체조회');
            break;

        case '/seller/orderList?listType=receipt' :
            $("#orderReceiptMenu").addClass('active-page') ;
            $("#orderReceipt").addClass('active-page') ;
            // $titleSpan.text('주문접수');
            $tdSpan.text('배송처리');
            break;

        case '/seller/orderList?listType=delivery' :
            $("#orderDeliveryMenu").addClass('active-page') ;
            $("#orderDelivery").addClass('active-page') ;
            // $titleSpan.text('배송처리');
            $tdSpan.text('운송장번호');
            break;

        case '/seller/orderList?listType=refund' :
            $("#orderRefundMenu").addClass('active-page') ;
            $("#orderRefund").addClass('active-page') ;
            // $titleSpan.text('교환환불');
            $tdSpan.text('상태변경');
            break;

        case '/seller/orderList?listType=cancel' :
            $("#orderCancelMenu").addClass('active-page') ;
            $("#orderCancel").addClass('active-page') ;
            // $titleSpan.text('취소내역');
            $tdSpan.text('취소일자');
            break;

        case '/seller/orderList?listType=calculate' :
            $("#orderCalculateMenu").addClass('active-page') ;
            $("#orderCalculate").addClass('active-page') ;
            // $titleSpan.text('주문정보');
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
    let no = new URLSearchParams(location.search).get('no');

    $.ajax({
        url : `/seller/projectDetail/cancel?no=${no}`,
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

function movePage(pageNo) {
    const moveURL = new URLSearchParams(location.search);
    moveURL.set("pageNo", pageNo)
    location.href = `?${moveURL}`;
}

function moveSale(no) {
    window.open(`/seller/sales/detail?no=${no}`);

}