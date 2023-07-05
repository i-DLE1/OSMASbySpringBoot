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

            var registFile = document.getElementById("registFile").files[0];
            var reportFile = document.getElementById("reportFile").files[0];
            var certificateFile = document.getElementById("certificateFile").files[0];
            var bankBookFile = document.getElementById("bankBookFile").files[0];

            console.log(sellerId);
            console.log(bank);
            console.log(accountNo);
            console.log(name);
            console.log(rprsn);
            console.log(callNumber);
            console.log(address);
            console.log(registNo);
            console.log(reportNo);

            console.log(registFile);
            console.log(reportFile);
            console.log(certificateFile);
            console.log(bankBookFile);


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
            formData.append('registFile', registFile);
            formData.append('reportFile', reportFile);
            formData.append('certificateFile', certificateFile);
            formData.append('bankBookFile', bankBookFile);

            // AJAX 요청
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/admin/sellerApprovalForm/FileUpload', true);
            xhr.onload = function() {
                if (xhr.status >= 200 && xhr.status < 300) {
                    console.log('파일 업로드 성공');
                    alert('파일 업로드가 완료되었습니다!');
                } else {
                    console.error('파일 업로드 실패');
                }
                console.log(xhr.responseText);
            };
            xhr.send(formData);

            // AJAX 요청
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