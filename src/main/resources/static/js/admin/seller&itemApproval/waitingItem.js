document.addEventListener('DOMContentLoaded', function() {
    const goButtons = document.querySelectorAll('.goButton');
    const noButtons = document.querySelectorAll('.noButton');

    goButtons.forEach(button => {
        button.addEventListener('click', () => {
            // 알림 메시지 표시
            alert('상품 판매가 승인되었습니다!');
        });
    });

    noButtons.forEach(noButton => {
        noButton.addEventListener('click', () => {
            const product = noButton.closest('.product');
            const sellerId = noButton.getAttribute('data-seller-id');
            const projectNoString = noButton.getAttribute('data-project-no');
            const projectNo = parseInt(projectNoString, 10);


            const notificationForm = document.createElement('div');
            notificationForm.classList.add('notification-form');

            notificationForm.innerHTML = `
                <h4>상품 판매 보류 알림</h4>
                <p>판매자: ${sellerId}</p>
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

            console.log(sellerId);
            console.log(typeof projectNo);
            console.log(projectNo);
            console.log(reasonText);

            sendButton.addEventListener('click', () => {
                const reasonTextValue = reasonText.value;
                // 전송 처리
                console.log('전송:', reasonTextValue);

                // 권한 부여 보류 Ajax 요청
                $.ajax({
                    url: "/admin/itemApproval/noProgress",
                    method: "POST",
                    data: {
                        "sellerId": sellerId,
                        "projectNo": projectNo,
                        "reasonText": reasonTextValue
                    },
                    success: function(response) {
                        console.log("상품 판매 승인이 보류되었습니다.");
                        // 알림 메시지 표시
                        alert('상품 판매 승인이 보류되었습니다!');
                        closeButton.click(); // 알림창 닫기
                        location.reload(); // 페이지 새로 고침
                    },
                    error: function(error) {
                        console.error("승인 보류에 실패했습니다.", error);
                    }
                });
            });

            closeButton.addEventListener('click', () => {
                notificationForm.remove();
            });
        });
    });
});