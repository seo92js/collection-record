let index = 0;

const images = document.querySelectorAll(".posts__left-image-content");

const maxLength = images.length;

showImage(index);

function showImage(index) {
    for (i = 0; i < maxLength; i++){
        if (i == index){
            images[i].style.display = "block";
        }
        else{
            images[i].style.display = "none";
        }
    }
}

function showNextImage(){
    if (index != maxLength - 1){
        index++;
    }

    showImage(index);
}

function showPrevImage(){
    if (index != 0){
        index--;
    }

    showImage(index);
}