function switchChildForm(commentId){

    $('#create-parent-comment-form').addClass('hidden');
    $('#create-child-comment-form').removeClass('hidden');

    document.getElementById("parentCommentId").value = commentId;
}

function createParentComment(userId, postsId){
    const createParentCommentForm = document.getElementById("create-parent-comment-form");

    const formData = new FormData(createParentCommentForm);

    const createParentCommentRequestDto = {
        userId: userId,
        postsId: postsId,
        text: formData.get('text')
    };

    if(!createParentCommentRequestDto.text)
        return;

    event.preventDefault();  // 이벤트의 기본 동작 중단

    $.ajax({
        type: 'POST',
        url: '/api/v1/parent-comment',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(createParentCommentRequestDto)
    }).done(function(){
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}