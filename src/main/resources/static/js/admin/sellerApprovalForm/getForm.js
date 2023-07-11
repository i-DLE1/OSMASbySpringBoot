document.addEventListener('DOMContentLoaded', function() {
    var goButton = document.getElementById('go');

    if (goButton) {
        goButton.addEventListener('click', function(event) {
            // 알림창 표시
            alert('제출되었습니다!');

            // 폼 데이터 가져오기
            var form = document.getElementById('sellerGotForm');
            var formData = new FormData(form);


            // AJAX 요청
            $.ajax({
                url: form.getAttribute('action'),
                method: 'POST',
                data: formData,
                enctype: "multipart/form-data",
                processData: false,
                contentType: false,
                success: function(response) {
                    console.log('권한 신청 되었습니다.');
                    alert('권한 신청 되었습니다!');
                    location.reload(); // 페이지 새로 고침
                },
                error: function(error) {
                    console.error('제출에 실패했습니다.', error);
                }
            });
        });
    }

    var noButton = document.querySelectorAll('.submit-container #no');
    noButton.forEach(button => {
        button.addEventListener('click', (event) => {
            const action = button.value;

            // 알림창 표시
            alert(`${action} 되었습니다!`);

            // 판매자 ID 가져오기
            var sellerId = document.getElementById("sellerId").value;

            console.log(sellerId);

            // AJAX 요청
            $.ajax({
                url: "/admin/sellerApprovalForm/sellerInsertCancel",
                method: 'POST',
                data: {
                    'sellerId': sellerId
                },
                success: function(response) {
                    console.log('신청이 취소되었습니다.');
                    alert('신청이 취소되었습니다!');
                    location.reload(); // 페이지 새로 고침
                },
                error: function(error) {
                    console.error("신청 취소에 실패했습니다.", error);
                }
            });
        });
    });

    var changeButton = document.querySelectorAll('.submit-container #change');
    changeButton.forEach(button => {
        button.addEventListener('click', (event) => {
            const action = button.value;

            // 알림창 표시
            alert(`${action} 되었습니다!`);

            // 폼 데이터 가져오기
            const form = document.getElementById('sellerGotForm');
            const formData = new FormData(form);

            // AJAX 요청
            $.ajax({
                url: form.getAttribute('action'),
                method: 'POST',
                data: formData,
                success: function(response) {
                    console.log('신청이 수정 후 제출 되었습니다.');
                    alert('신청이 수정 후 제출 되었습니다!');
                    location.reload(); // 페이지 새로 고침
                },
                error: function(error) {
                    console.error("신청 수정 후 제출에 실패했습니다.", error);
                }
            });
        });
    });
});