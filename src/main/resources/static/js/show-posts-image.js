let index = 0;

const imageRows = document.querySelectorAll("#posts-img");
const images = document.querySelectorAll(".posts__img");

const maxLength = images.length;

showImage(index);

function showImage(index) {
    for (i = 0; i < maxLength; i++){
        if (i == index){
            imageRows[i].style.display= "flex";
            images[i].style.display = "block";
        }
        else{
            imageRows[i].style.display= "none";
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