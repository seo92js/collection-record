
function unfollowing(id){
    $.ajax({
        type: 'PUT',
        url: '/api/v1/unfollowing/' + id,
    }).done(function(){
        alert('팔로우 취소.');
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}