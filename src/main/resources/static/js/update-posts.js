function updatePosts(id){
    const updatePostsForm = document.getElementById("update-posts-form");

    const formData = new FormData(updatePostsForm);

    const updatePostsRequestDto = {
        categoryId: formData.get('categoryId'),
        title: formData.get('title'),
        text: formData.get('text'),
        hashtags: formData.get('hashtags'),
        status: formData.get('status')
    };

    if(!updatePostsRequestDto.title)
        return;

    $.ajax({
        type: 'PUT',
        url: '/api/v1/posts/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(updatePostsRequestDto)
    }).done(function(){
        alert('게시물 수정 완료.');
        window.location.href = '/posts/' + id;
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

