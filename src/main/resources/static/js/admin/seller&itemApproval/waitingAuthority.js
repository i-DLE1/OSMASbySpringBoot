document.addEventListener('DOMContentLoaded', function() {

    const goButtons = document.querySelectorAll('.goButton');
    const noButtons = document.querySelectorAll('.noButton');

    goButtons.forEach(button => {
        button.addEventListener('click', () => {
            // 알림 메시지 표시
            alert('권한이 부여되었습니다!');
        });
    });

    noButtons.forEach(button => {
        button.addEventListener('click', () => {
            const product = button.closest('.product');
            const sellerName = product.querySelector('.seller-name').textContent;
            const saleDescription = product.querySelector('.sale-description').textContent;

            // 알림 폼 생성
            const notificationForm = document.createElement('div');
            notificationForm.classList.add('notification-form');

            // 폼 내용 설정
            notificationForm.innerHTML = `
      <h4>부여 보류 알림</h4>
      <p>판매자: ${sellerName}</p>
      <p>판매 내용: ${saleDescription}</p>
    `;

            // 알림 폼을 페이지에 추가
            document.body.appendChild(notificationForm);
        });
    });
});