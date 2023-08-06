function updateNotification(id){
    $.ajax({
        type: 'PUT',
        url: '/api/v1/notification/' + id,
    }).done(function(){
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}