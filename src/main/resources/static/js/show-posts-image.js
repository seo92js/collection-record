let index = 0;

const images = document.querySelectorAll(".posts__img");

const maxLength = images.length;

showImage(index);

function showImage(index) {
    console.log("구국수");

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