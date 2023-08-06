function deleteComment(id){
    $.ajax({
        type: 'DELETE',
        url: '/api/v1/comment/' + id,
    }).done(function(){
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}
