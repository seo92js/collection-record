
function commentAdd(){
    const addForm = document.getElementById("comment-add-form");
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

function commentChildAdd(){
    const addForm = document.getElementById("comment-child-add-form");
        const formData = new FormData(addForm);

        const commentChildAddRequestDto = {
            userId: formData.get('userId'),
            postsId: formData.get('postsId'),
            parentCommentId: formData.get('parentCommentId'),
            text: formData.get('text')
        };

        if(!checkRequiredValue(commentChildAddRequestDto))
            return;

        $.ajax({
            type: 'POST',
            url: '/api/v1/comment-child-add',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(commentChildAddRequestDto)
        }).done(function(){
            alert('댓글 작성 완료.');
            location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
}

function switchChildForm(commentId){

    $('#comment-add-form').addClass('hidden');
    $('#comment-child-add-form').removeClass('hidden');

    document.getElementById("parentCommentId").value = commentId;
}

function switchParentForm(commentId){

    $('#comment-add-form').removeClass('hidden');
    $('#comment-child-add-form').addClass('hidden');
}

function checkRequiredValue(value){
    if (!value.text) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}