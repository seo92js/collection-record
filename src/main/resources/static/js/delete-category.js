function deleteCategory(id){
    $.ajax({
        type: 'DELETE',
        url: '/api/v1/category/' + id,
    }).done(function(){
        location.reload(true);
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}
