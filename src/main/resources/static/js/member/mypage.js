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
    window.location.href = "/member/mypage/MypageAccountDelete";
}

function showMessageAccount(param) {
    const messageContainer = document.querySelector('.message-container');
    const saveButton = document.querySelector('.btn_save');

    const inputE = $(param).siblings('input');
    const inputVal = inputE.val();
    const inputId = inputE.attr('id')

    const text = memberInfoSubmit(inputId, inputVal);


    messageContainer.textContent = text;
    messageContainer.classList.add('show');

    saveButton.disabled = true;

    setTimeout(function () {
        messageContainer.classList.remove('show');
        saveButton.disabled = false;
    }, 1000);
}

// MypageAccountDelete
function redirectToMypageAccountDeleteNext() {
    window.location.href = "/member/mypage/MypageAccountDeleteNext";
}

function selectReason(e) {
    const reason = $(e).children("a")[0].text
    $('#reason').val(reason)
}


// MypageAccountDeleteNext
function showMessageDelete() {
    const messageContainer = document.querySelector('.message-container');
    const saveButton = document.querySelector('.btn_save');

    const reason = $('#reason').val()

    $.ajax({
        url: '/member/mypage/MypageAccountDeleteNext',
        type: 'post',
        data: {reason},
        success: function (success){
            if (success == "success") {
                messageContainer.textContent = '완료되었습니다!';
                messageContainer.classList.add('show');
                setTimeout(() => {
                    location.href = "/"
                },1000)

            } else {
                messageContainer.textContent = '실패했습니다!';
                messageContainer.classList.add('show');
            }
        },
        error : function(e) {
            messageContainer.textContent = '실패했습니다!';
            messageContainer.classList.add('show');
        }
    })
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

function saveNickName() {
    const nickname = $("#nickname").val();
    console.log(nickname)
}

async function memberInfoSubmit(inputId, inputVal) {
    let result ='';
    await $.ajax({
        url : '/member/mypage/MypageProfile',
        type : 'post',
        data : {inputId, inputVal},
        success : function (success) {
            console.log(success)
            if (success == "success") {
                result = '저장되었습니다!'
            } else {
                result  = '실패하였습니다!'
            }
        },
        error : function (e) {
            console.log(e)
            result = '실패하였습니다!'
        }
    })
return result;
}

// MypageProfile
async function showMessageProfile(param) {
    const messageContainer = document.querySelector('.message-container');
    const saveButton = document.querySelector('.btn_save');

    const inputE = $(param).siblings('input');
    const inputVal = inputE.val();
    const inputId = inputE.attr('id')

    // $.ajax({
    //     url : '/member/mypage/MypageProfile',
    //     type : 'post',
    //     data : {inputId, inputVal},
    //     success : function (success) {
    //     //         console.log(success)
    //     //         if (success == "success") {
    //     //             messageContainer.textContent = '저장되었습니다!'
    //     //         } else {
    //     //             messageContainer.textContent = '실패하였습니다!'
    //         }
    //     },
    //     error : function (e) {
    //         console.log(e)
    //         messageContainer.textContent = '실패하였습니다!'
    //     }
    // })

    let text = await memberInfoSubmit(inputId, inputVal);
    console.log(text)
    messageContainer.textContent = text
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

    const joinName = $('#joinName').val();
    const postalCode = $('#postalCode').val();
    const general = $('#general').val();
    const joinDetail = $('#joinDetail').val();
    const joinPhone = $('#joinPhone').val();

    console.log(joinName, postalCode, general, joinDetail, joinPhone)


    $.ajax({
        url : '/member/mypage/MypageShippingAd',
        type : 'post',
        data : {joinName, postalCode, general, joinDetail, joinPhone},
        success : function (success) {
                if (success == "success") {
                    messageContainer.textContent = '저장되었습니다!'
                    messageContainer.classList.add('show');
                } else {
                    messageContainer.textContent = '실패하였습니다!'
                    messageContainer.classList.add('show');
            }
        },
        error : function (e) {
            console.log(e)
            messageContainer.textContent = '실패하였습니다!'
        }
    })

    // messageContainer.textContent = '저장되었습니다!';
    messageContainer.classList.add('show');

    saveButton.disabled = true;

    setTimeout(function () {
        messageContainer.classList.remove('show');
        saveButton.disabled = false;
    }, 1000);
}
