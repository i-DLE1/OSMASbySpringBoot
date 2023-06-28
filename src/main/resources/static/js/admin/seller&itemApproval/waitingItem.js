document.addEventListener('DOMContentLoaded', function() {

    const goButtons = document.querySelectorAll('.goButton');
    const noButtons = document.querySelectorAll('.noButton');

    goButtons.forEach(button => {
        button.addEventListener('click', () => {
            // 알림 메시지 표시
            alert('판매가 승인되었습니다!');
        });
    });

    noButtons.forEach(button => {
        button.addEventListener('click', () => {
            const product = button.closest('.product');
            const sellerName = product.querySelector('.seller-name').textContent;

            // 알림 폼 생성
            const notificationForm = document.createElement('div');
            notificationForm.classList.add('notification-form');

            // 폼 내용 설정
            notificationForm.innerHTML = `
        <h4>판매 보류 알림</h4>
        <p>판매자: ${sellerName}</p>
        <div class="form-content">
          <input class="reasonText" type="text" placeholder="내용을 입력하세요">
          <div class="button-container">
            <button class="send-button">전송</button>
            <button class="close-button">닫기</button>
          </div>
        </div>
      `;

            // 알림 폼을 페이지에 추가
            document.body.appendChild(notificationForm);

            const sendButton = notificationForm.querySelector('.send-button');
            const closeButton = notificationForm.querySelector('.close-button');

            sendButton.addEventListener('click', () => {
                const reasonText = notificationForm.querySelector('.reasonText').value;
                // 전송 처리
                console.log('전송:', reasonText);
            });

            closeButton.addEventListener('click', () => {
                // 알림 폼 닫기
                notificationForm.remove();
            });
        });
    });
});