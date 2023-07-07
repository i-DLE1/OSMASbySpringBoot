

// 서버로부터 데이터를 가져와 화면에 표시하는 함수
async function displayBoardData(boardId) {
    try {
        const response = await axios.get(`/board/${boardId}`);
        const boardData = response.data;

        // 데이터를 가져와 화면에 표시하는 로직을 추가합니다.
        const titleElement = document.getElementById('board-title');
        const dateElement = document.getElementById('board-date');

        titleElement.textContent = boardData.title;
        dateElement.textContent = boardData.regist_date;
    } catch (error) {
        console.error(error);
    }
}

// 페이지 로드 시 실행
document.addEventListener('DOMContentLoaded', () => {
    const boardId = 123; // 실제로 사용할 게시물의 ID를 가져오는 로직이 필요합니다.
    displayBoardData(boardId);
});

// 공지사항 등록 페이지의 취소 버튼
function cancel() {
    window.history.back();
}

// 공지사항 등록 페이지의 저장 버튼
function save() {

    let category = $('#category').val();
    let title =  $('#title').val();
    let content=  $('#content').val();
    let refmember = refmemberno;
    var param = {"title":title, "content":content,"category":category, "refmember":refmember};

    console.log(param);

    $.ajax({
        url : "/admin/admin_notice/notice_registration",
        type : "POST",
        data : JSON.stringify(param),
        contentType: "application/json",

        success : function (data){
            alert("등록되었습니다.");
            location.href = "/admin/user_notice/notice_view/A";
        },
        error : function (){
            alert("등록에 실패했습니다.");
        }
    });

}

