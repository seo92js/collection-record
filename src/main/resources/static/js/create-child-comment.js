function switchParentForm(){
    $('#create-parent-comment-form').removeClass('hidden');
    $('#create-child-comment-form').addClass('hidden');
}

function createChildComment(userId, postsId){
    const createChildCommentForm = document.getElementById("create-child-comment-form");

    const formData = new FormData(createChildCommentForm);

    const createChildCommentRequestDto = {
        userId: userId,
        postsId: postsId,
        parentCommentId: formData.get('parentCommentId'),
        text: formData.get('text')
    };

    if(!createChildCommentRequestDto.text)
        return;

    $.ajax({
        type: 'POST',
        url: '/api/v1/child-comment',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(createChildCommentRequestDto)
    }).done(function(){
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}