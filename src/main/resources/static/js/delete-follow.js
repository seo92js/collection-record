function deleteFollow(id){
    $.ajax({
        type: 'DELETE',
        url: '/api/v1/follow/' + id,
    }).done(function(){
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}