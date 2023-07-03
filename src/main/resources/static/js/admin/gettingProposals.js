document.addEventListener('DOMContentLoaded', function() {
    const buttonAlarms = document.querySelectorAll('.buttonAlarm');

    buttonAlarms.forEach(button => {
        button.addEventListener('click', () => {
            const product = button.closest('.product');
            const sellerName = product.querySelector('.seller-name').textContent;
            const sellerID = product.querySelector('.apply-content').textContent;

            const notificationForm = document.createElement('div');
            notificationForm.classList.add('notification-form');

            const defaultReason = '요청주신 제안을 확인했습니다.';

            notificationForm.innerHTML = `
                <h4>제안 확인 메시지</h4>
                <p> ${sellerID}: ${sellerName}</p>
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
                const proposalNo = button.getAttribute('data-proposal-no');

                console.log('제안 번호: ' + proposalNo);
                console.log('제안 알람 확인: ' + reasonTextValue);

                // AJAX 요청
                $.ajax({
                    url: "/admin/AdminCategory/sendingProposals",
                    method: 'POST',
                    data: {
                        'proposalNo': proposalNo,
                        'reasonText': reasonTextValue
                    },
                    success: function(response) {
                        console.log('확인 메세지가 전송되었습니다.');
                        location.reload(); // 페이지 새로 고침
                    },
                    error: function(error) {
                        console.error('확인 메세지 전송에 실패했습니다.', error);
                    }
                });
            });

            closeButton.addEventListener('click', () => {
                notificationForm.remove();
            });
        });
    });
});