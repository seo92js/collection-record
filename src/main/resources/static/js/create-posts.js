function createPosts(userId){
    const createPostsForm = document.getElementById("create-posts-form");

    const formData = new FormData(createPostsForm);

    const imageFileInput = document.getElementById('posts-img-input');

    const createPostsRequestDto = {
        userId: userId,
        categoryId: formData.get('categoryId'),
        title: formData.get('title'),
        text: formData.get('text'),
        hashtags: formData.get('hashtags'),
        status: formData.get('status')
    };

    if(!createPostsRequestDto.title)
        return;

    formData.append('createPostsRequestDto', new Blob([JSON.stringify(createPostsRequestDto)] , {type: "application/json"}));

    //이미지 파일 선택 확인
    if (imageFileInput.files.length > 0){
        for (const file of imageFileInput.files) {
            formData.append('imageFile', file);
        }
    } else{
        alert('이미지를 선택하세요.');
        return;
    }

    const username=$('input[name=loginUsername]').val();

    $.ajax({
        type: 'POST',
        url: '/api/v1/posts',
        data: formData,
        processData: false,
        contentType: false
    }).done(function(){
        alert('게시물 등록 완료.');
        window.location.href = '/user/' + username + '/home';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}