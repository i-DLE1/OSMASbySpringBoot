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

function displaySelectedOptions() {

    var selectedOptionIndex = projectSelectBox.selectedIndex;
    var selectedOption = projectSelectBox.options[selectedOptionIndex];
    var projectNo = selectedOption.dataset.projectNo;
    console.log(projectNo);

    $.ajax({
        url: '/seller/orderList?listType=all&no=180',
        method: 'GET',
        data: { no: projectNo }, // 전달할 데이터
        success: function(response) {
            console.log('요청이 성공적으로 처리되었습니다.');
            window.location.href = '/seller/orderList?listType=all&no='+ projectNo;

        },
        error: function() {
            console.log('요청 처리 중 오류가 발생하였습니다.');
        }
    });

}