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

function increaseCounter() {
    const boxes = document.querySelectorAll('.plusbutton');
    boxes.forEach(box => {
        box.addEventListener('click', function increaseCounter() {
            let count = box.previousSibling.previousSibling.value;
            console.log(cont);
            box.previousSibling.previousSibling.value = parseInt(count) + 1;
        });
    });
}
const boxes2 = document.querySelectorAll('.minusbutton');

boxes2.forEach(box => {
    box.addEventListener('click', function handleClick() {

        let count = box.previousSibling.previousSibling.previousSibling.value;
        if(count>1) {
            box.previousSibling.previousSibling.previousSibling.value = parseInt(count) - 1;
        }else{
            alert("최소 수량은 1개 입니다.");
        }
    });
});