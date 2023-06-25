function toggleDropdown(event) {
    // event.stopPropagation(); // 클릭 이벤트의 전파를 중지합니다.
    var dropdown = document.getElementById("dropdownContent");
    dropdown.classList.toggle("slide-open");
}

// 클릭 이벤트가 발생한 영역 밖을 클릭하면 슬라이드가 닫히도록 설정
window.addEventListener("click", function (event) {
    var dropdown = document.getElementById("dropdownContent");
    var targetElement = event.target;

    if (!targetElement.closest(".gugu") && !targetElement.closest(".slide-content")) {
        dropdown.classList.remove("slide-open");
    }
});