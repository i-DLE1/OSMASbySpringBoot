document.addEventListener('DOMContentLoaded', function() {
    const buttonAlarms = document.querySelectorAll('.buttonAlarm');

    buttonAlarms.forEach(button => {
        button.addEventListener('click', () => {
            const product = button.closest('.product');
            const sellerName = product.querySelector('.seller-name').textContent;

            const notificationForm = document.createElement('div');
            notificationForm.classList.add('notification-form');

            const defaultReason = '요청주신 제안을 확인했습니다.';

            notificationForm.innerHTML = `
                <h4>제안 확인 메시지</h4>
                <p>판매자: ${sellerName}</p>
                <div class="form-content">
                    <textarea class="reasonText" placeholder="내용을 입력하세요">${defaultReason}</textarea>
                    <div class="button-container">
                        <button class="send-button">전송</button>
                        <button class="close-button">닫기</button>
                    </div>
                </div>
            `;

            document.body.appendChild(notificationForm);

            const sendButton = notificationForm.querySelector('.send-button');
            const closeButton = notificationForm.querySelector('.close-button');
            const reasonText = notificationForm.querySelector('.reasonText');

            reasonText.addEventListener('keydown', (event) => {
                if (event.key === 'Enter' && !event.shiftKey) {
                    event.preventDefault();
                    reasonText.value += '\n';
                }
            });

            sendButton.addEventListener('click', () => {
                const reasonTextValue = reasonText.value;
                // 전송 처리
                console.log('전송:', reasonTextValue);
            });

            closeButton.addEventListener('click', () => {
                notificationForm.remove();
            });
        });
    });
});