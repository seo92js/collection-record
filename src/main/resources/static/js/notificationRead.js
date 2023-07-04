function notificationRead(id){
    $.ajax({
        type: 'PUT',
        url: '/api/v1/notification-read/' + id,
    }).done(function(){
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}