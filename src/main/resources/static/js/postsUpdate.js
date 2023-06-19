document.getElementById("posts-update-btn").addEventListener('click', function(event) {
    event.preventDefault();
});

function postsUpdate(id){
    const updateForm = document.getElementById("posts-update-form");

    const formData = new FormData(updateForm);
    const imageFileInput = document.getElementById('posts-rep-update-img');

    const checkValue = {
        categoryName: formData.get('categoryName'),
        title: formData.get('title'),
        text: formData.get('text'),
        hashtags: formData.get('hashtags')
    };

    //이미지 파일 선택 확인
    if (imageFileInput.files.length > 0){
        const imageFile = imageFileInput.files[0];
        formData.append('imageFile', imageFile);
    } else {
        alert('이미지를 선택하세요');
        return;
    }

    if(!checkRequiredValue(checkValue))
        return;

    $.ajax({
        type: 'PUT',
        url: '/api/v1/posts-update/' + id,
        data: formData,
        processData: false,
        contentType: false
    }).done(function(){
        alert('게시물 수정 완료.');
        window.location.href = '/posts/' + id;
        //location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function checkRequiredValue(value){
    if (!value.categoryName || !value.title || !value.text || !value.hashtags) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}