document.addEventListener("DOMContentLoaded", function() {
    var reasonSelect = document.getElementById("reason");
    var otherReasonDiv = document.getElementById("otherReasonDiv");
    var otherReasonInput = document.getElementById("otherReasonInput");
    var goButtons = document.querySelectorAll('.submit-container #go');

    // 페이지 로드 시 이전에 선택한 값 복원
    var savedReason = localStorage.getItem("selectedReason");
    if (savedReason) {
        reasonSelect.value = savedReason;
        handleReasonSelection();
    }

    // 이벤트 리스너 등록
    reasonSelect.addEventListener("change", function() {
        var selectedReason = reasonSelect.value;

        // 선택한 사유 저장
        localStorage.setItem("selectedReason", selectedReason);

        handleReasonSelection();
    });

    // submit 버튼에 이벤트 리스너 등록
    goButtons.forEach(button => {
        button.addEventListener('click', (event) => {

            const action = button.value;

            // 알림창 표시
            alert(`${action} 되었습니다!`);

            // 판매자 ID 가져오기
            var sellerId = document.getElementById("sellerId").value;

            console.log(sellerId);
            console.log(reasonSelect.value);
            console.log(otherReasonInput.value);

            // AJAX 요청
            $.ajax({
                url: "/admin/sellerApprovalForm/sellerOut",
                method: 'POST',
                data: {
                    'reasonSelect': reasonSelect.value,
                    'otherReasonInput': otherReasonInput.value,
                    'sellerId': sellerId
                },
                success: function(response) {
                    console.log('권한 회수 신청이 완료되었습니다.');
                    alert('권한 회수 신청이 완료되었습니다!');
                    location.href = "goSuccess";
                },
                error: function(error) {
                    console.error("권한 회수 신청에 실패했습니다.", error);
                }
            });
        });
    });

    function handleReasonSelection() {
        var selectedReason = reasonSelect.value;

        if (selectedReason === "기타") {
            otherReasonDiv.style.display = "block";
        } else {
            otherReasonDiv.style.display = "none";
        }
    }

    // 페이지 로드 시 사유 선택 초기화
    resetReasonSelection();

    function resetReasonSelection() {
        reasonSelect.selectedIndex = 0;
        handleReasonSelection(); // 초기 선택에 따라 기타 사유 입력창 표시 여부 갱신
        otherReasonInput.value = "";
    }
});