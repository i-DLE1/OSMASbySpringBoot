const screenCenterPopup = (popupWidth, popupHeight) =>{
    const screenWidth = window.screen.width
    const screenHeight = window.screen.height

    const popupX = (screenWidth /2) - (popupWidth /2);
    const popupY = (screenHeight /2) - (popupHeight /2);

    return `width=${popupWidth} , height=${popupHeight}, left=${popupX}, top=${popupY}`;
}

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

        case '/seller/orderList?listType=cancelList' :
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



function deleteProject(){
    let deleteConfirm = confirm("프로젝트를 삭제하시겠습니까?")
    if(deleteConfirm){
        let deleteConfirmRetry = confirm("삭제할 경우 프로젝트 복구는 불가능합니다.\n정말 삭제하시겠습니까?")
        if(deleteConfirmRetry) deleteTempProject(false);
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


function confirmTempProject(){
    $.ajax({
        url : '/seller/regist/tempProjectConfirm',
        type: 'get',
        success : function (success) {
            if(success.result === 'isExist'){
                let temp = confirm("이미 등록을 진행중인 프로젝트가 있습니다.\n새로운 프로젝트는 시작하면 임시저장된 프로젝트는 삭제됩니다.\n임시저장된 프로젝트를 불러오겠습ㄴ니까?")
                if(temp){
                    location.href = `/seller/regist/project1?no=${success.no}`;
                }else {
                    deleteTempProject(true);
                }
            }else {
                location.href = `/seller/regist/project1`;
            }
        },
        error : e => {
            console.log(e)
        }
    })
}

function deleteTempProject(newProject){
    let isDelete = confirm("정말로 임시저장된 프로젝트를 삭제하시겠니까?")
    if(!isDelete) return;
    $.ajax({
        url : `/seller/regist/deleteTempProject`,
        success : function (success) {
            if(success === 'succcess'){
                alert('임시저장 중이 프로젝트가 삭제되었습니다.')
            }
            if(newProject) location.href = `/seller/regist/project1`
        },
        error : e => {
            console.log(e)
        }
    })
}

// 페이지 리스트타입을 받아서 화면이동
function displaySelectedOptions() {
    var selectedOptionIndex = projectSelectBox.selectedIndex;
    var selectedOption = projectSelectBox.options[selectedOptionIndex];
    var projectNo2 = selectedOption.dataset.projectNo;
    var projectName = selectedOption.dataset.projectTitle;
    var listText = selectedOption.dataset.listType;
    var listType = "all";
    switch (listText){
        case "전체조회" :
            listType = "all";
            break;
        case "주문접수" :
            listType = "receipt";
            break;
        case "배송처리" :
            listType = "delivery";
            break;
        case "교환환불" :
            listType = "refund";
            break;
        case "취소내역" :
            listType = "cancelList";
            break;
        case "주문정보" :
            listType = "calculate";
            break;
        default:
            listType = "all";
    }

    var currentUrl = window.location.href;
    var newUrl = currentUrl.split('?')[0] + '?listType=' + listType+ '&projectNo2=' + projectNo2;
    window.location.href = newUrl;
}

// 페이지 로드 시 번호를 할당하여 출력
window.addEventListener('DOMContentLoaded', function() {
    var rowNumbers = document.getElementsByClassName('orderrow-number');
    var j = 1;
    for (var i = rowNumbers.length; i > 0; i--) {
        rowNumbers[i-1].textContent = j++;
    }
});

// 주문상품 목록 맨 뒤에 컴마 지우기
function removeLastComma() {
    var tds = document.getElementsByClassName('remove-last-character');
    for (var i = 0; i < tds.length; i++) {
        var text = tds[i].textContent.trim();
        text = text.slice(0, -1); // 마지막 한글자 제거
        tds[i].textContent = text;
    }
}

// 버튼 숨기기
function toggleButtons() {
    var label = document.getElementById('#listLabel');
    var listType =  label.innerText;
    console.log(listType);
    console.log('하이');

    var orderConfirmationBtn = document.getElementById('orderConfirmationBtn');
    var orderExchangeBtn = document.getElementById('orderExchangeBtn');
    var orderRefundBtn = document.getElementById('orderRefundBtn');

    if (listType === '배송처리') {
        orderConfirmationBtn.style.display = 'inline-block';
    } else if (listType === '교환처리') {
        orderExchangeBtn.style.display = 'inline-block';
    } else if (listType === '환불처리') {
        orderRefundBtn.style.display = 'inline-block';
    }
}

function deliveryCheck(){
    const query = 'input[class="deliveryChk"]:checked';
    const selectedEls = document.querySelectorAll(query);
    // 선택된 목록에서 value 찾기
    let result = '';
    selectedEls.forEach((el) => {
        result += el.value + ',';
    });
    result = result.slice(0, -1);
    // 출력
    console.log(result);
    return result;

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