$(document).ready(function() {
    $('.contentsmiddle').click(function() {
        // 모든 li 요소의 배경색을 흰색으로 변경
        $('.contentsmiddle').not(this).css('background-color', 'transparent');
        $('.contentsmiddle').not(this).css('font-weight', 'normal');

        // 클릭된 li 요소의 배경색을 채워진 색상으로 변경
        $(this).css('background-color', '#C49A6C');
        $(this).css('font-weight', 'bold');
    });
});

$(document).ready(function() {
    $('#updateContent1').click(function() {
        var productId = $(this).attr('data-sales-no');
        $.ajax({
            url: '/seller/sales/detail?no=' + productId,  // 데이터를 가져올 URL
            method: 'GET',  // 요청 메소드 (GET, POST 등)
            success: function(data) {
                // 데이터를 성공적으로 가져왔을 때 실행할 코드
                $('#dynamicContent').text('성공');
            },
            error: function() {
                // 데이터 가져오기 실패 시 실행할 코드
                console.log('데이터 가져오기 실패');
            }
        });
    });
});

$(document).ready(function() {
    $('#updateContent2').click(function() {
        var productId = $(this).attr('data-sales-no');
        $.ajax({
            url: '/seller/sales/prjInfo?no=' + productId,  // 데이터를 가져올 URL
            method: 'GET',  // 요청 메소드 (GET, POST 등)
            success: function(data) {
                // 데이터를 성공적으로 가져왔을 때 실행할 코드
                $('#dynamicContent').html(data);
            },
            error: function() {
                // 데이터 가져오기 실패 시 실행할 코드
                console.log('데이터 가져오기 실패');
            }
        });
    });
});

$(document).ready(function() {
    $('#updateContent3').click(function() {
        var productId = $(this).attr('data-sales-no');
        $.ajax({
            url: '/seller/sales/prjQna?no=' + productId,  // 데이터를 가져올 URL
            method: 'GET',  // 요청 메소드 (GET, POST 등)
            success: function(data) {
                // 데이터를 성공적으로 가져왔을 때 실행할 코드
                $('#dynamicContent').html(data);
            },
            error: function() {
                // 데이터 가져오기 실패 시 실행할 코드
                console.log('데이터 가져오기 실패');
            }
        });
    });
});

$(document).ready(function() {
    $('#updateContent4').click(function() {
        var productId = $(this).attr('data-sales-no');
        $.ajax({
            url: '/seller/sales/prjFaq?no=' + productId,  // 데이터를 가져올 URL
            method: 'GET',  // 요청 메소드 (GET, POST 등)
            success: function(data) {
                // 데이터를 성공적으로 가져왔을 때 실행할 코드
                $('#dynamicContent').html(data);
            },
            error: function() {
                // 데이터 가져오기 실패 시 실행할 코드
                console.log('데이터 가져오기 실패');
            }
        });
    });
});

$(document).ready(function() {
    $('#updateContent5').click(function() {
        var productId = $(this).attr('data-sales-no');
        $.ajax({
            url: '/seller/sales/prjNewInfo?no=' + productId,  // 데이터를 가져올 URL
            method: 'GET',  // 요청 메소드 (GET, POST 등)
            success: function(data) {
                // 데이터를 성공적으로 가져왔을 때 실행할 코드
                $('#dynamicContent').html(data);
            },
            error: function() {
                // 데이터 가져오기 실패 시 실행할 코드
                console.log('데이터 가져오기 실패');
            }
        });
    });
});

$(document).ready(function() {
    $('#updateContent6').click(function() {
        var productId = $(this).attr('data-sales-no');
        $.ajax({
            url: '/seller/sales/sellerInfo?no=' + productId,  // 데이터를 가져올 URL
            method: 'GET',  // 요청 메소드 (GET, POST 등)
            success: function(data) {
                // 데이터를 성공적으로 가져왔을 때 실행할 코드
                $('#dynamicContent').html(data);
            },
            error: function() {
                // 데이터 가져오기 실패 시 실행할 코드
                console.log('데이터 가져오기 실패');
            }
        });
    });
});

