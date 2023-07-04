const addForm = document.getElementById("comment-add-form");

function commentAdd(){
    const formData = new FormData(addForm);

    const commentAddRequestDto = {
        userId: formData.get('userId'),
        postsId: formData.get('postsId'),
        text: formData.get('text')
    };

    if(!checkRequiredValue(commentAddRequestDto))
        return;

    $.ajax({
        type: 'POST',
        url: '/api/v1/comment-add',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(commentAddRequestDto)
    }).done(function(){
        alert('댓글 작성 완료.');
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function checkRequiredValue(value){
    if (!value.text) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}