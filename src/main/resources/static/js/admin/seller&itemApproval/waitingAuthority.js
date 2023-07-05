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
        button.addEventListener('click', (event) => {

            const product = button.closest('.product');
            const sellerName = product.querySelector('.seller-name').textContent;
            const sellerId = button.getAttribute('data-seller-id'); // 판매자 아이디 가져오기
            const sellerReqNoString = document.querySelector('p[data-seller-req]').getAttribute('data-seller-req');
            const sellerReqNo = parseInt(sellerReqNoString, 10); // 10은 진수를 나타내는 옵션입니다 (10진수)

            const notificationForm = document.createElement('div');
            notificationForm.classList.add('notification-form');

            notificationForm.innerHTML = `
                <h4>권한 부여 보류 알림</h4>
                <p>신청자: ${sellerName}</p>
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

                    const currentCursorPosition = reasonText.selectionStart;
                    reasonText.value = reasonText.value.slice(0, currentCursorPosition) + '\n' + reasonText.value.slice(currentCursorPosition);
                }
            });

            console.log(sellerId); // 값 출력
            console.log(sellerReqNo); // 판매자 신청 번호 값
            console.log(typeof sellerReqNo);


            sendButton.addEventListener('click', () => {
                const reason = reasonText.value;
                // 전송 처리
                console.log('전송값:', reason);

                // 권한 부여 보류 Ajax 요청
                $.ajax({
                    url: "/admin/sellerApproval/holdingPermission",
                    method: "POST",
                    data: {
                        "sellerId": sellerId,
                        "reason": reason,
                        "sellerReq": sellerReqNo
                    },
                    success: function(response) {
                        console.log("권한이 보류되었습니다.");
                        // 알림 메시지 표시
                        alert('권한이 보류되었습니다!');
                        closeButton.click(); // 알림창 닫기
                        location.reload(); // 페이지 새로 고침
                    },
                    error: function(error) {
                        console.error("권한 보류에 실패했습니다.", error);
                    }
                });
            });

            closeButton.addEventListener('click', () => {
                notificationForm.remove();
            });
        });
    });
});