function commentDelete(id){
    $.ajax({
        type: 'DELETE',
        url: '/api/v1/comment-delete/' + id,
    }).done(function(){
        alert('댓글 삭제 완료.');
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}
