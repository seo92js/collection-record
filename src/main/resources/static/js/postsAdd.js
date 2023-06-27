document.getElementById("posts-add-btn").addEventListener('click', function(event) {
    event.preventDefault();
    postsAdd();
});


function postsAdd(){
    const addForm = document.getElementById("posts-add-form");

    const formData = new FormData(addForm);

    const imageFileInput = document.getElementById('posts-rep-add-img')

    const postsAddRequestDto = {
        userId: formData.get('userId'),
        categoryName: formData.get('categoryName'),
        title: formData.get('title'),
        text: formData.get('text'),
        hashtags: formData.get('hashtags'),
        status: formData.get('status')
    };

    formData.append('postsAddRequestDto', new Blob([JSON.stringify(postsAddRequestDto)] , {type: "application/json"}));

    if(!checkRequiredValue(postsAddRequestDto))
        return;

    //이미지 파일 선택 확인
    if (imageFileInput.files.length > 0){
        const imageFile = imageFileInput.files[0];
        formData.append('imageFile', imageFile);
    } else{
        alert('대표이미지를 선택하세요.');
        return;
    }

    var username=$('input[name=loginUsername]').val();

    $.ajax({
        type: 'POST',
        url: '/api/v1/posts',
        data: formData,
        processData: false,
        contentType: false
    }).done(function(){
        alert('게시물 추가 완료.');
        window.location.href = '/user/' + username + '/home';

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function checkRequiredValue(value){
    if (!value.categoryName || !value.title || !value.text || !value.status) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}