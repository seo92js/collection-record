function categoryDelete(id){
    $.ajax({
        type: 'DELETE',
        url: '/api/v1/category-delete/' + id,
    }).done(function(){
        alert('카테고리 삭제 완료.');
        window.location.href = '/category';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}
