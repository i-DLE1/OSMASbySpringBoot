document.addEventListener('DOMContentLoaded', function() {
    var changeButtons = document.querySelectorAll('.submit-container #change');
    var noButtons = document.querySelectorAll('.submit-container #no');

    noButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault(); // 기본 동작 방지

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
                    location.href = "noSuccess";
                },
                error: function(error) {
                    console.error("신청 취소에 실패했습니다.", error);
                }
            });
        });
    });

    changeButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault(); // 기본 동작 방지

            const action = button.value;

            // 알림창 표시
            alert(`${action} 되었습니다!`);

            // 폼 데이터 가져오기
            var form = document.getElementById('sellerGetForm');
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
                    console.log('신청이 수정 후 제출 되었습니다.');
                    alert('신청이 수정 후 제출 되었습니다!');
                    location.href = "changeSuccess";
                },
                error: function(error) {
                    console.error("신청 수정 후 제출에 실패했습니다.", error);
                }
            });
        });
    });
});