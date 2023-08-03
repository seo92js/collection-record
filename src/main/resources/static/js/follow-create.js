
function following(id){
    $.ajax({
        type: 'PUT',
        url: '/api/v1/following/' + id,
    }).done(function(){
        alert('팔로잉.');
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}