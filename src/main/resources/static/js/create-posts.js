function createPosts(userId){
    const createPostsForm = document.getElementById("create-posts-form");

    const formData = new FormData(createPostsForm);

    const imageFileInput = document.getElementById('posts-img-input');

    const createPostsRequestDto = {
        userId: userId,
        category: formData.get('category'),
        artist: formData.get('artist'),
        album: formData.get('album'),
        genre: formData.get('genre'),
        albumArt: formData.get('albumArt'),
        text: formData.get('text'),
        status: formData.get('status')
    };

    if(!createPostsRequestDto.artist)
        return;

    event.preventDefault();  // 이벤트의 기본 동작 중단

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

function preview(input){
  $('#preview').empty();

  if (input.files) {
    for (var i = 0; i < input.files.length; i++){
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#preview').append($('<img>').attr("src", e.target.result).addClass('posts-add__preview'));
        };

        reader.readAsDataURL(input.files[i]);
    }
  } else {
    $('#preview').empty();
  }
}