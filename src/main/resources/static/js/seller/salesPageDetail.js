$(document).ready(function() {
    $('#updateContent').click(function() {
        $.ajax({
            url: '/seller/sales/detail',  // 데이터를 가져올 URL
            method: 'GET',  // 요청 메소드 (GET, POST 등)
            success: function(data) {
                // 데이터를 성공적으로 가져왔을 때 실행할 코드
                $('#dynamicContent').text(data);
            },
            error: function() {
                // 데이터 가져오기 실패 시 실행할 코드
                console.log('데이터 가져오기 실패');
            }
        });
    });
});