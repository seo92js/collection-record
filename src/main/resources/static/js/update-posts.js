function updatePosts(id){
    const updatePostsForm = document.getElementById("update-posts-form");

    const formData = new FormData(updatePostsForm);

    const updatePostsRequestDto = {
        category: formData.get('category'),
        status: formData.get('status'),
        artist: formData.get('artist'),
        album: formData.get('album'),
        genre: formData.get('genre'),
        albumArt: formData.get('albumArt'),
        text: formData.get('text')
    };

    if(!updatePostsRequestDto.artist)
        return;

    event.preventDefault();  // 이벤트의 기본 동작 중단

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

