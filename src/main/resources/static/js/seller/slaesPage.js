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

