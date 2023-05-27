document.getElementById("posts-add-btn").addEventListener('click', function(event) {
    event.preventDefault();
    postsAdd();
});

const addForm = document.getElementById("posts-add-form");

function postsAdd(){
    const formData = new FormData(addForm);

    const postsAddRequestDto = {
        categoryName: formData.get('categoryName'),
        title: formData.get('title'),
        image: formData.get('image'),
        text: formData.get('text')
    };

    if(!checkRequiredValue(postsAddRequestDto))
        return;

    $.ajax({
        type: 'POST',
        url: '/api/v1/posts',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postsAddRequestDto)
    }).done(function(){
        alert('게시물 추가 완료.');
        window.location.href = '/user/home';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function checkRequiredValue(value){
    if (!value.categoryName || !value.title || !value.image || !value.text) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}