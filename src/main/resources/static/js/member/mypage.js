// MypageAccount
function toggleDropdown(event) {
    var dropdown = document.getElementById("dropdownContent");
    dropdown.classList.toggle("slide-open");
}

window.addEventListener("click", function (event) {
    var dropdown = document.getElementById("dropdownContent");
    var targetElement = event.target;

    if (!targetElement.closest(".gugu") && !targetElement.closest(".slide-content")) {
        dropdown.classList.remove("slide-open");
    }
});

function redirectToMypageAccountDelete() {
    window.location.href = "MypageAccountDelete.html";
}

function showMessageAccount() {
    const messageContainer = document.querySelector('.message-container');
    const saveButton = document.querySelector('.btn_save');

    messageContainer.textContent = '저장되었습니다!';
    messageContainer.classList.add('show');

    saveButton.disabled = true;

    setTimeout(function () {
        messageContainer.classList.remove('show');
        saveButton.disabled = false;
    }, 1000);
}

// MypageAccountDelete
function redirectToMypageAccountDeleteNext() {
    window.location.href = "MypageAccountDeleteNext.html";
}

// MypageAccountDeleteNext
function showMessageDelete() {
    const messageContainer = document.querySelector('.message-container');
    const saveButton = document.querySelector('.btn_save');

    messageContainer.textContent = '완료되었습니다!';
    messageContainer.classList.add('show');

    saveButton.disabled = true;

    setTimeout(function () {
        messageContainer.classList.remove('show');
        saveButton.disabled = false;
    }, 1000);
}

// MypageMessage
function showMessageMessage() {
    const messageContainer = document.querySelector('.message-container');
    const saveButton = document.querySelector('.btn_save');

    messageContainer.textContent = '문의완료!';
    messageContainer.classList.add('show');

    saveButton.disabled = true;

    setTimeout(function () {
        messageContainer.classList.remove('show');
        saveButton.disabled = false;
    }, 1000);
}

// MypageProfile
function showMessageProfile() {
    const messageContainer = document.querySelector('.message-container');
    const saveButton = document.querySelector('.btn_save');

    messageContainer.textContent = '저장되었습니다!';
    messageContainer.classList.add('show');

    saveButton.disabled = true;

    setTimeout(function () {
        messageContainer.classList.remove('show');
        saveButton.disabled = false;
    }, 1000);
}

// MypageShippingAd
function showMessageShippingAd() {
    const messageContainer = document.querySelector('.message-container');
    const saveButton = document.querySelector('.btn_save');

    messageContainer.textContent = '저장되었습니다!';
    messageContainer.classList.add('show');

    saveButton.disabled = true;

    setTimeout(function () {
        messageContainer.classList.remove('show');
        saveButton.disabled = false;
    }, 1000);
}
