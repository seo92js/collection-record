document.getElementById("user-update-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userUpdate();
});

const profileForm = document.getElementById("user-profile-form");

function userUpdate(){
    const formData = new FormData(profileForm);

/*    const userUpdateRequestDto = {
        username: formData.get('username'),
        password: formData.get('password'),
        profileImage: null
    };*/

    const id = document.getElementById('userId').value;
    const imageFileInput = document.getElementById('imageFile');

    //이미지 파일 선택 확인
    if (imageFileInput.files.length > 0){
        const imageFile = imageFileInput.files[0];
        //userUpdateRequestDto.profileImage = imageFile;
        formData.append('imageFile', imageFile);
    }

    $.ajax({
        type: 'PUT',
        url: '/api/v1/user-update/' + id,
        //dataType: 'json',
        //contentType: 'application/json; charset=utf-8',
        //contentType: 'multipart/form-data',
        //data: JSON.stringify(userUpdateRequestDto)
        data: formData,
        processData: false,
        contentType: false
    }).done(function(){
        alert('수정완료.');
        window.location.href = '/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}