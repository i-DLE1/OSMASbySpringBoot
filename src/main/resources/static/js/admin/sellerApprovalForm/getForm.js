document.addEventListener('DOMContentLoaded', function() {
    const submitButtons = document.querySelectorAll('.submit-container input[type="submit"]');

    submitButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault(); // 기본 동작인 폼 제출을 막기 --> 나중에 지워줘야함

            const action = button.value;

            // 알림창 표시
            alert(`${action} 되었습니다!`);
        });
    });
});