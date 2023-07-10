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
        // var url = '/seller/sales/prjInfo?no=' + productId;

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
                    // $('#updateContent2').data('loading', false);
                },
                error: function () {
                    // 데이터 가져오기 실패 시 실행할 코드
                    $('#dynamicContent').text('정보가 없습니다.');
                    console.log('데이터 가져오기 실패');
                    // history.pushState(null, null, url);
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

    // QnA 입력기능
    $('#inputQna').click(function (){

        let content =  $('#question').val();
        let refMemberNo = memberNo;
        let refPrjNo = $(this).attr('data-sales-no');

        var param = {"content":content, "refMemberNo":refMemberNo, "refPrjNo":refPrjNo}

        $.ajax({
            url : "/seller/sales/prjQna",
            type : "POST",
            data : JSON.stringify(param),
            contentType: "application/json",

            success : function (data){
            alert("등록되었습니다.");
            // $('#dynamicContent').load(location.href + ' #dynamicContent');
            $('#updateContent3').click();

            },
            error : function (){
                alert("등록에 실패했습니다. 관리자에게 문의세요.");
            }
        });
    });
});



// 페이징처리
var $setRows = $('#setRows');

$setRows.submit(function (e) {
    e.preventDefault();
    var rowPerPage = 10;

//    console.log(typeof rowPerPage);

    var zeroWarning = 'Sorry, but we cat\'t display "0" rows page. + \nPlease try again.'
    if (!rowPerPage) {
        alert(zeroWarning);
        return;
    }
    $('#nav-paging').remove();
    var $products = $('#products');

    $products.after('<div id="nav-paging">');


    var $tr = $($products).find('tbody tr');
    var rowTotals = $tr.length;
//  console.log(rowTotals);

    var pageTotal = Math.ceil(rowTotals/ rowPerPage);
    var i = 0;

    for (; i < pageTotal; i++) {
        $('<a href="#"></a>')
            .attr('rel', i)
            .html(i + 1)
            .appendTo('#nav-paging');
    }

    $tr.addClass('off-screen')
        .slice(0, rowPerPage)
        .removeClass('off-screen');

    var $pagingLink = $('#nav-paging a');
    $pagingLink.on('click', function (evt) {
        evt.preventDefault();
        var $this = $(this);
        if ($this.hasClass('active')) {
            return;
        }
        $pagingLink.removeClass('active');
        $this.addClass('active');

        // 0 => 0(0*4), 4(0*4+4)
        // 1 => 4(1*4), 8(1*4+4)
        // 2 => 8(2*4), 12(2*4+4)
        // 시작 행 = 페이지 번호 * 페이지당 행수
        // 끝 행 = 시작 행 + 페이지당 행수

        var currPage = $this.attr('rel');
        var startItem = currPage * rowPerPage;
        var endItem = startItem + rowPerPage;

        $tr.css('opacity', '0.0')
            .addClass('off-screen')
            .slice(startItem, endItem)
            .removeClass('off-screen')
            .animate({opacity: 1}, 300);

    });

    $pagingLink.filter(':first').addClass('active');

});
$setRows.submit();



// 페이징처리의 하단 숫자 페이징처리
var visiblePages = 5; // 한 번에 표시할 수 있는 최대 페이지 수

// ...

var $pagingLink = $('#nav-paging a');
$pagingLink.on('click', function (evt) {
    // ...

    var currPage = $this.attr('rel');
    var startItem = currPage * rowPerPage;
    var endItem = startItem + rowPerPage;

    $tr.css('opacity', '0.0')
        .addClass('off-screen')
        .slice(startItem, endItem)
        .removeClass('off-screen')
        .animate({opacity: 1}, 300);

    updatePageLinks(currPage);
});

// ...

function updatePageLinks(currPage) {
    var startPage = Math.floor(currPage / visiblePages) * visiblePages + 1;
    var endPage = startPage + visiblePages - 1;
    endPage = Math.min(endPage, pageTotal);

    $('#nav-paging').empty();

    if (startPage > 1) {
        $('<a href="#" rel="' + (startPage - 1) + '">Previous</a>').appendTo('#nav-paging');
    }

    for (var i = startPage; i <= endPage; i++) {
        $('<a href="#" rel="' + (i - 1) + '">' + i + '</a>').appendTo('#nav-paging');
    }

    if (endPage < pageTotal) {
        $('<a href="#" rel="' + endPage + '">Next</a>').appendTo('#nav-paging');
    }

    $pagingLink = $('#nav-paging a');
    $pagingLink.on('click', function (evt) {
        evt.preventDefault();
        var $this = $(this);
        if ($this.hasClass('active')) {
            return;
        }
        $pagingLink.removeClass('active');
        $this.addClass('active');

        // ...
    });

    $pagingLink.filter('[rel="' + currPage + '"]').addClass('active');
}

// ...

$setRows.submit();










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

