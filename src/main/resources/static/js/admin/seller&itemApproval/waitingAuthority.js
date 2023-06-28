document.addEventListener('DOMContentLoaded', function() {
    const goButtons = document.querySelectorAll('.goButton');
    const noButtons = document.querySelectorAll('.noButton');

    goButtons.forEach(button => {
        button.addEventListener('click', () => {
            // 알림 메시지 표시
            alert('권한이 부여 되었습니다!');
        });
    });

    noButtons.forEach(button => {
        button.addEventListener('click', () => {
            const product = button.closest('.product');
            const sellerName = product.querySelector('.seller-name').textContent;

            const notificationForm = document.createElement('div');
            notificationForm.classList.add('notification-form');

            notificationForm.innerHTML = `
                <h4>권한 부여 보류 알림</h4>
                <p>판매자: ${sellerName}</p>
                <div class="form-content">
                    <textarea class="reasonText" placeholder="내용을 입력하세요"></textarea>
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
                    const currentCursorPosition = reasonText.selectionStart;
                    reasonText.value = reasonText.value.slice(0, currentCursorPosition) + '\n' + reasonText.value.slice(currentCursorPosition);
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