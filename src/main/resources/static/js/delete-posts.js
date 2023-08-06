function deletePosts(id){
    var username=$('input[name=loginUsername]').val();

    $.ajax({
        type: 'DELETE',
        url: '/api/v1/posts/' + id,
    }).done(function(){
        alert('게시물 삭제 완료.');
        window.location.href = '/user/' + username + '/home';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}