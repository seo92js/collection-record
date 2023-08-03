document.getElementById("user-update-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userUpdate();
});

function userUpdate(){
    const profileForm = document.getElementById("user-profile-form");

    const formData = new FormData(profileForm);

    const id = document.getElementById('userId').value;

    const imageFileInput = document.getElementById('user-profile-img');

    const userUpdateRequestDto = {
        username: formData.get('username'),
        profileText: formData.get('profileText')
    }

    formData.append('userUpdateRequestDto', new Blob([JSON.stringify(userUpdateRequestDto)] , {type: "application/json"}));

    if(!checkRequiredValue(userUpdateRequestDto))
        return;

    //이미지 파일 선택 확인
    if (imageFileInput.files.length > 0){
        const imageFile = imageFileInput.files[0];
        formData.append('imageFile', imageFile);
    }

    $.ajax({
        type: 'PUT',
        url: '/api/v1/user-update/' + id,
        data: formData,
        processData: false,
        contentType: false
    }).done(function(){
        alert('수정완료.');
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function checkRequiredValue(value){
    if (!value.username) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}