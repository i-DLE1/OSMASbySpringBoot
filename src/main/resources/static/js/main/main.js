$(document).ready(function() {
    $('.product-image').click(function() {
        var photoId = 20;

        $.ajax({
            url: '/seller/sales/detail',
            method: 'GET',
            data: { no: photoId }, // 전달할 데이터
            success: function(response) {
                console.log('요청이 성공적으로 처리되었습니다.');
                window.location.href = '/seller/sales/detail?no=' + photoId;

            },
            error: function() {
                console.log('요청 처리 중 오류가 발생하였습니다.');
            }
        });
    });
});