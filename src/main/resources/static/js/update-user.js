function updateUser(id){
    const profileForm = document.getElementById("user-profile-form");

    const formData = new FormData(profileForm);

    const updateUserRequestDto = {
        username: formData.get('username'),
        profileText: formData.get('profileText')
    };

    if(!updateUserRequestDto.username)
        return;

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
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}