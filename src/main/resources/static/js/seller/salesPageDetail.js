$(document).ready(function() {
    $('.contentsmiddle').click(function() {
        // 모든 li 요소의 배경색을 흰색으로 변경
        $('.contentsmiddle').not(this).css('background-color', 'transparent');
        $('.contentsmiddle').not(this).css('font-weight', 'normal');

        // 클릭된 li 요소의 배경색을 채워진 색상으로 변경
        $(this).css('background-color', '#C49A6C');
        $(this).css('font-weight', 'bold');
        $(this).data('loading', false);
    });
});

$(document).ready(function() {
    var cachedData = {}; // 데이터 캐시를 위한 객체

    $('#updateContent1').click(function() {
        var productId = $(this).attr('data-sales-no');

        if (cachedData[productId]) {
            // 캐시된 데이터가 있는 경우, 데이터를 사용하여 동적 내용을 변경
            $('#dynamicContent').html(cachedData[productId]);
        } else {
            $.ajax({
                url: '/seller/sales/detail?no=' + productId,  // 데이터를 가져올 URL
                method: 'GET',  // 요청 메소드 (GET, POST 등)
                success: function (data) {
                    // 데이터를 성공적으로 가져왔을 때 실행할 코드
                    $('#dynamicContent').text('성공');
                    cachedData[productId] = data;
                    $('#updateContent1').data('loading', false);
                },
                error: function () {
                    // 데이터 가져오기 실패 시 실행할 코드
                    $('#dynamicContent').text('정보가 없습니다.');
                    console.log('데이터 가져오기 실패');
                }
            });
        }
    });
});

$(document).ready(function() {
    var cachedData = {}; // 데이터 캐시를 위한 객체

    $('#updateContent2').click(function() {
        var productId = $(this).attr('data-sales-no');

        if (cachedData[productId]) {
            // 캐시된 데이터가 있는 경우, 데이터를 사용하여 동적 내용을 변경
            $('#dynamicContent').html(cachedData[productId]);
        } else {
            $.ajax({
                url: '/seller/sales/prjInfo?no=' + productId,  // 데이터를 가져올 URL
                method: 'GET',  // 요청 메소드 (GET, POST 등)
                success: function (data) {
                    // 데이터를 성공적으로 가져왔을 때 실행할 코드
                    $('#dynamicContent').html(data);
                    cachedData[productId] = data; // 데이터를 캐시에 저장
                    $('#updateContent2').data('loading', false);
                },
                error: function () {
                    // 데이터 가져오기 실패 시 실행할 코드
                    $('#dynamicContent').text('정보가 없습니다.');
                    console.log('데이터 가져오기 실패');
                }
            });
        }
    });
});

$(document).ready(function() {
    var cachedData = {}; // 데이터 캐시를 위한 객체
    $('#updateContent3').click(function() {
        var productId = $(this).attr('data-sales-no');

        if (cachedData[productId]) {
            // 캐시된 데이터가 있는 경우, 데이터를 사용하여 동적 내용을 변경
            $('#dynamicContent').html(cachedData[productId]);
        } else {
            $.ajax({
                url: '/seller/sales/prjQna?no=' + productId,  // 데이터를 가져올 URL
                method: 'GET',  // 요청 메소드 (GET, POST 등)
                success: function (data) {
                    // 데이터를 성공적으로 가져왔을 때 실행할 코드
                    $('#dynamicContent').html(data);
                    cachedData[productId] = data; // 데이터를 캐시에 저장
                    $('#updateContent3').data('loading', false);
                },
                error: function () {
                    // 데이터 가져오기 실패 시 실행할 코드
                    $('#dynamicContent').text('정보가 없습니다.');
                    console.log('데이터 가져오기 실패');
                }
            });
        }
    });
});

$(document).ready(function() {
    var cachedData = {}; // 데이터 캐시를 위한 객체

    $('#updateContent4').click(function() {
        var productId = $(this).attr('data-sales-no');

        if (cachedData[productId]) {
            // 캐시된 데이터가 있는 경우, 데이터를 사용하여 동적 내용을 변경
            $('#dynamicContent').html(cachedData[productId]);
        } else {
            $.ajax({
                url: '/seller/sales/prjFaq?no=' + productId,  // 데이터를 가져올 URL
                method: 'GET',  // 요청 메소드 (GET, POST 등)
                success: function (data) {
                    // 데이터를 성공적으로 가져왔을 때 실행할 코드
                    $('#dynamicContent').html(data);
                    cachedData[productId] = data; // 데이터를 캐시에 저장
                    $('#updateContent4').data('loading', false);
                },
                error: function () {
                    // 데이터 가져오기 실패 시 실행할 코드
                    $('#dynamicContent').text('정보가 없습니다.');
                    console.log('데이터 가져오기 실패');
                }
            });
        }
    });
});

$(document).ready(function() {
    var cachedData = {}; // 데이터 캐시를 위한 객체
    $('#updateContent5').click(function() {
        var productId = $(this).attr('data-sales-no');
        if (cachedData[productId]) {
            // 캐시된 데이터가 있는 경우, 데이터를 사용하여 동적 내용을 변경
            $('#dynamicContent').html(cachedData[productId]);
        } else {
            $.ajax({
                url: '/seller/sales/prjNewInfo?no=' + productId,  // 데이터를 가져올 URL
                method: 'GET',  // 요청 메소드 (GET, POST 등)
                success: function (data) {
                    // 데이터를 성공적으로 가져왔을 때 실행할 코드
                    $('#dynamicContent').html(data);
                    cachedData[productId] = data; // 데이터를 캐시에 저장
                    $('#updateContent5').data('loading', false);
                },
                error: function () {
                    // 데이터 가져오기 실패 시 실행할 코드
                    $('#dynamicContent').text('정보가 없습니다.');
                    console.log('데이터 가져오기 실패');
                }
            });
        }
    });
});

$(document).ready(function() {
    var cachedData = {}; // 데이터 캐시를 위한 객체
    $('#updateContent6').click(function() {
        var productId = $(this).attr('data-sales-no');

        if (cachedData[productId]) {
            // 캐시된 데이터가 있는 경우, 데이터를 사용하여 동적 내용을 변경
            $('#dynamicContent').html(cachedData[productId]);
        } else {
            $.ajax({
                url: '/seller/sales/sellerInfo?no=' + productId,  // 데이터를 가져올 URL
                method: 'GET',  // 요청 메소드 (GET, POST 등)
                success: function (data) {
                    // 데이터를 성공적으로 가져왔을 때 실행할 코드
                    $('#dynamicContent').html(data);
                    cachedData[productId] = data; // 데이터를 캐시에 저장
                    $('#updateContent6').data('loading', false);
                },
                error: function () {
                    // 데이터 가져오기 실패 시 실행할 코드
                    $('#dynamicContent').text('정보가 없습니다.');
                    console.log('데이터 가져오기 실패');
                }
            });
        }
    });
});

$(document).ready(function() {
    $('.show-details').click(function() {
        $(this).closest('tr').next('.hidden-row').toggle();
        $(this).data('loading', false);
    });
});

$(document).ajaxStart(function() {
    $('#loading-icon').show();
});

$(document).ajaxStop(function() {
    $('#loading-icon').hide();
});
$(document).ready(function() {
    $('.content-wrapper').each(function() {
        var contentWrapper = $(this);
        var content = contentWrapper.find('.newInfoContent');
        var moreButton = contentWrapper.find('.more-button');

        if (content[0].scrollHeight > contentWrapper.innerHeight()) {
            moreButton.show();
        }
    });
});
$(document).ready(function() {
    $('.more-button').click(function() {
        $(this).closest('.content-wrapper').toggleClass('open');
        if ($(this).text() === "더 보기" ){
            $(this).text('접기');
        } else {
            $(this).text('더 보기');
        }
        $(this).data('loading', false);
    });
});

