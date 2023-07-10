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

showSlide(currentIdx);

setInterval(() => {
    showSlide(currentIdx + 1);

}, 1000);


// 옵션선택
function addNewDiv() {
    var option = document.getElementById("selectbox");
    var originalDiv =  document.getElementsByClassName('optionmiddle')[2];
    var container = document.getElementById("optioncontainer");

    var clonedDiv = originalDiv.cloneNode(true);
    // clonedDiv.style.display = 'none';
    container.appendChild(clonedDiv);

    // plus버튼 이벤트추가
    const plusButton = clonedDiv.querySelector('.plusbutton');
    plusButton.addEventListener('click', function() {
        let count = plusButton.previousSibling.previousSibling.value;
        let money = parseInt(plusButton.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value);
        let price = money/count;
        plusButton.previousSibling.previousSibling.value = parseInt(count) + 1;
        plusButton.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value = parseInt(plusButton.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value) + price;
        calcMoney();

    });
    // minus버튼 이벤트추가
    const minusButton = clonedDiv.querySelector('.minusbutton');
    minusButton.addEventListener('click', function() {
        let count = minusButton.previousSibling.previousSibling.previousSibling.previousSibling.value;
        let money = parseInt(minusButton.nextSibling.nextSibling.nextSibling.nextSibling.value);
        let price = money/count;
        if (count > 1) {
            minusButton.previousSibling.previousSibling.previousSibling.previousSibling.value = parseInt(count) - 1;
            minusButton.nextSibling.nextSibling.nextSibling.nextSibling.value = parseInt(minusButton.nextSibling.nextSibling.nextSibling.nextSibling.value) - price;
            calcMoney();            } else {
            alert("최소 수량은 1개 입니다.");
        }
    });
        // delete 버튼 이벤트추가
        const deleteButton = clonedDiv.querySelector('.deletebutton');
        deleteButton.addEventListener('click', function() {
            clonedDiv.remove();
            calcMoney();
        });

    displaySelectedOptions();
}


function displaySelectedOptions() {
    var option = document.getElementById("selectbox");
    const inputbox =  document.querySelectorAll('.optioncheck');
    // 가격 박스 클래스 요소 가져오기!!!!!!!!
    const optionmoneybox =  document.querySelectorAll('.optionmoney');
    // 옵션번호 박스 클래스 요소 가져오기!!!!!!!! (옵션 수량의 클래스는 class="optionamount" 입니다)
    const optionNobox =  document.querySelectorAll('.optionNumber');
    var resultParagraph = document.getElementsByClassName("optioncheck");

    // 가격 가져오기!!!!!!!!!!
    var selectedOptionIndex = selectbox.selectedIndex;
    var selectedOption = selectbox.options[selectedOptionIndex];
    var dataOptionPrice = selectedOption.dataset.optionPrice;
    // 옵션번호 가져오기!!!!!!!!!!
    var dataOptionNo = selectedOption.dataset.optionNo;
    console.log(dataOptionNo);

    var result = option.value;
    inputbox[inputbox.length-1].value = result;

    var parentDiv = inputbox[inputbox.length - 1].parentNode;

    var duplicateValues = checkDuplicates(resultParagraph);
    if(duplicateValues.length !== 0 ){
        alert("이미 선택된 옵션입니다.");
        parentDiv.remove();
        option.value="";
    } else{
        // 가격 넣기!!!!!!!!
        optionmoneybox[optionmoneybox.length-1].value = dataOptionPrice;
        // 옵션번호 넣기!!!!!!!!
        optionNobox[optionNobox.length-1].value = dataOptionNo;
        parentDiv.style.display = 'flex';
        option.value="";
        calcMoney();
    }

}

// 현재금액/목표금액 퍼센트구하기
function calcPercent(){
    var currentAmount = parseInt(document.getElementById("currentAmount").textContent);
    var currentAmounttext = document.getElementById("currentAmount");
    var targetAmount = parseInt(document.getElementById("targetAmount").textContent);
    var targetAmounttext = document.getElementById("targetAmount");
    var calcAmount = document.getElementById("calcAmount");
    var gauge1 = document.getElementById("progress-gauge1");

    var calcResult = (currentAmount/targetAmount)*100;
    calcAmount.textContent = calcResult.toFixed(1);
    gauge1.style.width = calcResult + "%";

    currentAmounttext.textContent = currentAmount.toLocaleString();
    targetAmounttext.textContent = targetAmount.toLocaleString();
}

// 종료일자-시작일자 일자구하기
function calcBetweenDate() {
    var startDate = new Date(document.getElementById("startDate").textContent);
    var currentDate = new Date();
    var endDate = new Date(document.getElementById("endDate").textContent);
    var calcDate = document.getElementById("calcDate");
    var calcDatetext = document.getElementById("calcDatetext");
    var gauge2 = document.getElementById("progress-gauge2");

    currentDate.setHours(0, 0, 0, 0); // 현재시간 정보를 00:00:00으로 초기화

    var timeDiff = Math.abs(endDate.getTime() - currentDate.getTime()); // 남은날짜
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

    var timeDiff2 = Math.abs(endDate.getTime() - startDate.getTime()); // 총진행기간
    var diffDays2 = Math.ceil(timeDiff2 / (1000 * 3600 * 24));

//     if(diffDays >= 0 && currentDate.getTime() <= endDate.getTime() && currentDate.getTime() >= startDate.getTime()) {
//         calcDate.textContent = diffDays;
//         gauge2.style.width = 100 -((diffDays / diffDays2)* 100) + "%";
//         if(diffDays==1){
//             calcDate.textContent = "오늘 종료!";
//             calcDatetext.textContent = "";
//         }
//     } else if(currentDate.getTime() > endDate.getTime()) {
//         calcDate.textContent = "종료되었습니다";
//         calcDatetext.textContent = "";
//         gauge2.style.width = 100 + "%";
//     } else {
//         calcDate.textContent = "진행예정입니다";
//         calcDatetext.textContent = "";
//         gauge2.style.width = 0 + "%";
//     }
//
// }

    if (diffDays >= 0 && currentDate.getTime() <= endDate.getTime() && currentDate.getTime() >= startDate.getTime()) {
        if (currentDate.getTime() === startDate.getTime()) {
            calcDate.textContent = diffDays;
            gauge2.style.width = 100 - ((diffDays / diffDays2) * 100) + "%";
        } else {
            calcDate.textContent = diffDays;
            gauge2.style.width = 100 - ((diffDays / diffDays2) * 100) + "%";
            if (diffDays === 1) {
                calcDate.textContent = "오늘 종료!";
                calcDatetext.textContent = "";
            }
        }
    } else if (currentDate.getTime() > endDate.getTime()) {
        calcDate.textContent = "종료되었습니다";
        calcDatetext.textContent = "";
        gauge2.style.width = 100 + "%";
    } else {
        calcDate.textContent = diffDays;
        gauge2.style.width = 100 - ((diffDays / diffDays2) * 100) + "%";
        if (diffDays === 1) {
            calcDate.textContent = "오늘 종료!";
            calcDatetext.textContent = "";
        }
    }
}


// 금액합산기능
function calcMoney() {
    var totalamount = document.getElementById("totalamount");
    const inputbox2 =  document.querySelectorAll('.optionmoney');
    var donationck = document.getElementById("donationck");
    var donation = document.getElementById("donation");

    if (donationck.checked) {
        var  sum = parseInt(donation.value);
    } else {
        var  sum = 0;
    }

    for (var  i=1; i< inputbox2.length ; i++){
        sum += parseInt(inputbox2[i].value);
    }


    totalamount.textContent = sum.toLocaleString();

}


// 옵션 중복체크
function checkDuplicates(arr) {
    var seen = {};
    var duplicates = [];
    for (var i = 0; i < arr.length; i++) {
        var currentItem = arr[i].value;
        // 이전에 이미 등장한 값인 경우 중복으로 처리합니다.
        if (seen[currentItem]) {
            duplicates.push(currentItem);
        } else {
            // 현재 값을 저장하여 이후 중복 여부를 확인할 때 사용합니다.
            seen[currentItem] = true;
        }
    }
    return duplicates;
}

// 후원금 텍스트박스 1000으로 설정
function clearText() {
    var donation = document.getElementById("donation");

    if(donation.readOnly === true){
        alert("체크를 해제해주세요!")
    } else {
        donation.value =1000;
    }

}

// 숫자만,천원 이상만 입력하도록 조정
function checkNumericInput(inputElement) {
    var inputValue = inputElement.value;
    var hasShownAlert = false; // alert 창이 이미 표시되었는지 추적하는 변수

    if (/^\d+$/.test(inputValue)) {
        // 입력값이 숫자로만 구성되어 있는 경우
        // 추가적인 처리를 수행할 수 있습니다.
        if (0 < inputValue && inputValue < 1000) {
            // 입력값이 조건에 맞는 경우
                alert("후원은 천원부터 가능합니다.");
                clearText();
                hasShownAlert = true; // alert 창이 표시되었음을 표시
            }
        } else {
        // 입력값에 다른 글자가 포함되어 있는 경우
        if (!hasShownAlert) {
            alert("숫자로만 입력해주세요.");
            clearText();
            hasShownAlert = true; // alert 창이 표시되었음을 표시
        }

    }
}


// 후원옵션박스 체크하면 후원금 추가, 해제하면 후원금 삭제
function donationplus(){
    var donation = document.getElementById("donation");
    if (donation.value === "1000+"){
        donation.value = 1000;
    }
    var totalamount = document.getElementById("totalamount");
    var parsedNumber = parseFloat(totalamount.textContent.replace(/,/g, ''));
    totalamount.textContent = (parseInt(parsedNumber)+parseInt(donation.value)).toLocaleString();
}

function donationminus(){
    var donation = document.getElementById("donation");
    var totalamount = document.getElementById("totalamount");
    var parsedNumber = parseFloat(totalamount.textContent.replace(/,/g, ''));
    totalamount.textContent = (parseInt(parsedNumber) - parseInt(donation.value)).toLocaleString();
}

document.addEventListener('DOMContentLoaded', function() {
    // increaseCounter 함수 등록
    const plusButtons = document.querySelectorAll('.plusbutton');
    plusButtons.forEach(plusButton => {
        plusButton.addEventListener('click', function() {
            let count = plusButton.previousSibling.previousSibling.value;
            let money = parseInt(plusButton.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value);
            let price = money/count;
            plusButton.previousSibling.previousSibling.value = parseInt(count) + 1;
            plusButton.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value = parseInt(plusButton.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value) + price;
            calcMoney();
        });
    });

    // decreaseCounter 함수 등록
    const minusButtons = document.querySelectorAll('.minusbutton');
    minusButtons.forEach(minusButton => {
        minusButton.addEventListener('click', function() {
            let count = minusButton.previousSibling.previousSibling.previousSibling.previousSibling.value;
            let money = parseInt(minusButton.nextSibling.nextSibling.nextSibling.nextSibling.value);
            let price = money/count;
            if (count > 1) {
                minusButton.previousSibling.previousSibling.previousSibling.previousSibling.value = parseInt(count) - 1;
                minusButton.nextSibling.nextSibling.nextSibling.nextSibling.value = parseInt(minusButton.nextSibling.nextSibling.nextSibling.nextSibling.value) - price;
                calcMoney();
            } else {
                alert("최소 수량은 1개 입니다.");
            }
        });
    });


    var donationck = document.getElementById("donationck");
    var donation = document.getElementById("donation");
    donationck.addEventListener("change", function() {
        if (donationck.checked) {
            // 체크박스가 선택된 경우
            donationplus();
            donation.readOnly = true;
        } else {
            donationminus();
            donation.readOnly = false;
            // 체크박스가 선택되지 않은 경우
            // 필요한 처리를 수행할 수 있습니다.
        }
    });

});

function optionList() {
    // 옵션번호 박스 클래스 요소 가져오기
    const optionNobox = document.querySelectorAll('.optionNumber');
    // 옵션수량 박스 클래스 요소 가져오기
    const optionAmountbox = document.querySelectorAll('.optionamount');

    // 값을 담을 배열 생성
    const optionListArray = [];

    // optionNobox와 optionAmountbox의 값을 배열에 담기
    for (let i = 1; i < optionNobox.length; i++) {
        const optionNoboxValue = optionNobox[i].value;
        const optionAmountboxValue = optionAmountbox[i].value;
        optionListArray.push({
            optionNumber: optionNoboxValue,
            optionAmount: optionAmountboxValue
        });
    }

    return optionListArray;
}


