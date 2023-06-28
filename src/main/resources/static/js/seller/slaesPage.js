let currentIdx= 0;
const slides = document.getElementsByClassName("image");

function showSlide(index) {
    if (index < 0) {
        index = slides.length - 1;
    } else if (index >= slides.length) {
        index = 0;
    }

    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }

    if (slides[index]) {
        slides[index].style.display = "block";
        currentIdx = index;
    }
}

//showSlide(currentIdx);

setInterval(() => {
    showSlide(currentIdx + 1);

}, 1000);

function setOptionValues() {
    var option1 = document.getElementById("selectbox1").value;
    var option2 = document.getElementById("selectbox2").value;
    var resultInput = document.getElementById("optioncheck");

    var result = option1 + " - " + option2;
    resultInput.value = result;
}

function addNewDiv() {
    var option1 = document.getElementById("selectbox1");
    var option2 = document.getElementById("selectbox2");
    var originalDiv =  document.getElementsByClassName('optionmiddle')[3];
    var container = document.getElementById("optioncontainer");
    var resultParagraph = document.getElementsByClassName("optioncheck")[0];

    if (resultParagraph.value !== "") {
        var clonedDiv = originalDiv.cloneNode(true);
        container.appendChild(clonedDiv);

        const plusButton = clonedDiv.querySelector('.plusbutton');
        plusButton.addEventListener('click', function() {
            let count = plusButton.previousSibling.previousSibling.value;
            plusButton.previousSibling.previousSibling.value = parseInt(count) + 1;
        });

        const minusButton = clonedDiv.querySelector('.minusbutton');
        minusButton.addEventListener('click', function() {
            let count = minusButton.previousSibling.previousSibling.previousSibling.previousSibling.value;
            if (count > 1) {
                minusButton.previousSibling.previousSibling.previousSibling.previousSibling.value = parseInt(count) - 1;
            } else {
                alert("최소 수량은 1개 입니다.");
            }
        });

        const optioncheck =  document.querySelectorAll('.optioncheck');
        optioncheck[optioncheck.length-1].value = "";
        const optionamount =  document.querySelectorAll('.optionamount');
        optionamount[optionamount.length-1].value = 1;
    }
}


function displaySelectedOptions() {

    var option1 = document.getElementById("selectbox1");
    var option2 = document.getElementById("selectbox2");
    const inputbox =  document.querySelectorAll('.optioncheck');

    if(option1.value !== ""){
        var result = option1.value + " - " + option2.value;
        inputbox[inputbox.length-1].value = result;
        option1.value="";
        option2.value="";
    } else {
        alert("옵션1을 선택해주세요.")
        option2.value="";
    }
}

document.addEventListener('DOMContentLoaded', function() {
    // increaseCounter 함수 등록
    const plusButtons = document.querySelectorAll('.plusbutton');
    plusButtons.forEach(plusButton => {
        plusButton.addEventListener('click', function() {
            let count = plusButton.previousSibling.previousSibling.value;
            plusButton.previousSibling.previousSibling.value = parseInt(count) + 1;
        });
    });

    // decreaseCounter 함수 등록
    const minusButtons = document.querySelectorAll('.minusbutton');
    minusButtons.forEach(minusButton => {
        minusButton.addEventListener('click', function() {
            let count = minusButton.previousSibling.previousSibling.previousSibling.previousSibling.value;
            if (count > 1) {
                minusButton.previousSibling.previousSibling.previousSibling.previousSibling.value = parseInt(count) - 1;
            } else {
                alert("최소 수량은 1개 입니다.");
            }
        });
    });
});



// 현재금액/목표금액 퍼센트구하기
function calcPercent(){
    var currentAmount = parseInt(document.getElementById("currentAmount").textContent) ;
    var targetAmount = parseInt(document.getElementById("targetAmount").textContent);
    var calcAmount = document.getElementById("calcAmount");
    var gauge1 = document.getElementById("progress-gauge1");

    var calcResult = (currentAmount/targetAmount)*100;
    calcAmount.textContent = calcResult;
    gauge1.style.width = calcResult + "%";
}

// 종료일자-시작일자 일자구하기
function calcBetweenDate(){
    var startDate = new Date(document.getElementById("startDate").textContent);
    var currentDate = new Date();
    var endDate = new Date(document.getElementById("endDate").textContent);
    var calcDate = document.getElementById("calcDate");
    var gauge2 = document.getElementById("progress-gauge2");

    var timeDiff = Math.abs(endDate.getTime() - currentDate.getTime());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

    var timeDiff2 = Math.abs(endDate.getTime() - startDate.getTime());
    var diffDays2 = Math.ceil(timeDiff2 / (1000 * 3600 * 24));

    calcDate.textContent = diffDays;
    gauge2.style.width = 100 -((diffDays / diffDays2)* 100) + "%";
}

function clearText() {
    var donation = document.getElementById("donation");

    donation.value ="";

}