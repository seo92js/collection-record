function updateUser(id){
    const profileForm = document.getElementById("user-profile-form");

    const formData = new FormData(profileForm);

    const updateUserRequestDto = {
        username: formData.get('username'),
        profileText: formData.get('profileText')
    };

    if(!updateUserRequestDto.username)
        return;

    event.preventDefault();  // 이벤트의 기본 동작 중단

    formData.append('updateUserRequestDto', new Blob([JSON.stringify(updateUserRequestDto)] , {type: "application/json"}));

    const imageFileInput = document.getElementById('user-profile-img');

    //이미지 파일 선택 확인
    if (imageFileInput.files.length > 0){
        const imageFile = imageFileInput.files[0];
        formData.append('imageFile', imageFile);
    }

    $.ajax({
        type: 'PUT',
        url: '/api/v1/user/' + id,
        data: formData,
        processData: false,
        contentType: false
    }).done(function(){
        alert('수정 완료');
        window.location.href = '/user/' + updateUserRequestDto.username + '/home';
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
            $('#preview').attr("src", e.target.result).addClass('posts-add__preview');
        };

        reader.readAsDataURL(input.files[i]);
    }
  } else {
    $('#preview').empty();
  }
}