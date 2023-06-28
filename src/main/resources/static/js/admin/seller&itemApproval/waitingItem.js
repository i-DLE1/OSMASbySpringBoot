document.addEventListener('DOMContentLoaded', function() {

    const buyButtons = document.querySelectorAll('.buy-button');

    buyButtons.forEach(button => {
        button.addEventListener('click', () => {
            // 알림 메시지 표시
            alert('판매가 승인되었습니다!');
        });
    });
});