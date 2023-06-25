document.addEventListener("DOMContentLoaded", function() {
    var reasonSelect = document.getElementById("reason");
    var otherReasonDiv = document.getElementById("otherReasonDiv");

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
});

function resetReasonSelection() {
    var reasonSelect = document.getElementById("reason");
    var otherReasonDiv = document.getElementById("otherReasonDiv");
    var otherReasonInput = document.getElementById("otherReasonInput");

    reasonSelect.selectedIndex = 0;
    otherReasonDiv.style.display = "none";
    otherReasonInput.value = "";
}