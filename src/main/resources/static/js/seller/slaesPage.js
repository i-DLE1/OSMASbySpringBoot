let currentIndex = 0;
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
        currentIndex = index;
    }
}

showSlide(currentIndex);

setInterval(() => {
    showSlide(currentIndex + 1);
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
    var originalDiv = document.getElementById("optiondiv");
    var container = document.getElementById("optioncontainer");
    var resultParagraph = document.getElementById("optioncheck");

    if (resultParagraph.value !== "") {
        var clonedDiv = originalDiv.cloneNode(true);
        var clonedInput = clonedDiv.querySelector("#optioncheck");
        clonedInput.value = "";
        container.appendChild(clonedDiv);
        option1.value = "";

    }
}
function displaySelectedOptions() {
    var option1 = document.getElementById("selectbox1");
    var option2 = document.getElementById("selectbox2");
    var resultParagraph = document.getElementById("optioncheck");

    if(option1.value !== ""){
        var result = option1.value + " - " + option2.value;
        resultParagraph.value = result;
        option1.value="";
        option2.value="";
    } else {
        alert("옵션1을 선택해주세요.")
        option2.value="";
    }
}

var counter = 1;

function increaseCounter() {
    counter++;
    var counterElement = document.getElementById("optionamount");
    counterElement.value = counter;
}

function decreaseCounter() {
     if(counter>1) {
        counter--;
        var counterElement = document.getElementById("optionamount");
        counterElement.value = counter;
    } else{
         alert("최소 수량은 1개 입니다.");
     }

}