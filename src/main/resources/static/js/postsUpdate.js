function postsUpdate(id){
    const updateForm = document.getElementById("posts-update-form");

    const formData = new FormData(updateForm);

    const postsUpdateRequestDto = {
        categoryName: formData.get('categoryName'),
        title: formData.get('title'),
        image: formData.get('image'),
        text: formData.get('text')
    };

    if(!checkRequiredValue(postsUpdateRequestDto))
        return;

    $.ajax({
        type: 'PUT',
        url: '/api/v1/posts-update/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(postsUpdateRequestDto)
    }).done(function(){
        alert('게시물 수정 완료.');
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