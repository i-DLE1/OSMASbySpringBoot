// 버튼 요소 선택
const buyButtons = document.querySelectorAll('.buy-button');

// 각 버튼에 이벤트 처리 추가
buyButtons.forEach(button => {
    button.addEventListener('click', () => {
        // 알림 메시지 표시
        alert('권한이 부여되었습니다!');
    });
});