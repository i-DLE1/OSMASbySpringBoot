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
                    location.href = "goSuccess";
                },
                error: function(error) {
                    console.error('제출에 실패했습니다.', error);
                }
            });
        });
    }
});