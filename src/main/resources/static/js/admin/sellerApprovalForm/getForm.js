document.addEventListener('DOMContentLoaded', function() {
    var changeButtons = document.querySelectorAll('.submit-container #change');
    var goButtons = document.querySelectorAll('.submit-container #go');
    var noButtons = document.querySelectorAll('.submit-container #no');

    goButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            const action = button.value;

            // 알림창 표시
            alert(`${action} 되었습니다!`);

            var sellerId = document.getElementById("sellerId").value;
            var bank = document.getElementById("bank").value;
            var accountNo = document.getElementById("accountNo").value;
            var name = document.getElementById("name").value;
            var rprsn = document.getElementById("rprsn").value;
            var callNumber = document.getElementById("callNumber").value;
            var address = document.getElementById("address").value;
            var registNo = document.getElementById("registNo").value;
            var reportNo = document.getElementById("reportNo").value;


            console.log(sellerId);
            console.log(bank);
            console.log(accountNo);
            console.log(name);
            console.log(rprsn);
            console.log(callNumber);
            console.log(address);
            console.log(registNo);
            console.log(reportNo);

            var formData = new FormData();
            formData.append('sellerId', sellerId);
            formData.append('bank', bank);
            formData.append('accountNo', accountNo);
            formData.append('name', name);
            formData.append('rprsn', rprsn);
            formData.append('callNumber', callNumber);
            formData.append('address', address);
            formData.append('registNo', registNo);
            formData.append('reportNo', reportNo);

                // 파일 업로드 후 서버로의 데이터 전송
                $.ajax({
                    url: "/admin/sellerApprovalForm/sellerInsert",
                    method: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                        console.log('권한 회수 신청이 완료되었습니다.');
                        alert('권한 회수 신청이 완료되었습니다!');
                        location.reload(); // 페이지 새로 고침
                    },
                    error: function(error) {
                        console.error("권한 회수 신청에 실패했습니다.", error);
                    }
                });
        });
    });

    noButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault(); // 기본 동작 방지

            const action = button.value;

            // 알림창 표시
            alert(`${action} 되었습니다!`);

            // 판매자 ID 가져오기
            var sellerName = document.getElementById("sellerName").value;

            console.log(sellerName);

            // AJAX 요청
            $.ajax({
                url: "/admin/sellerApprovalForm/sellerInsertCancel",
                method: 'POST',
                data: {
                    'sellerId': sellerName
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

    changeButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault(); // 기본 동작 방지

            const action = button.value;

            // 알림창 표시
            alert(`${action} 되었습니다!`);

            // 판매자 ID 가져오기
            var sellerName = document.getElementById("sellerName").value;

            console.log(sellerName);

            // AJAX 요청
            $.ajax({
                url: "/admin/sellerApprovalForm/sellerUpdate",
                method: 'POST',
                data: {
                    'sellerId': sellerName
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
});