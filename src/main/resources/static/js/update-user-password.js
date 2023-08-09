function updateUserPassword(id){
    const passwordForm = document.getElementById("user-password-form");

    const formData = new FormData(passwordForm);

    const updateUserPasswordRequestDto = {
        oldPassword: formData.get('oldPassword'),
        newPassword: formData.get('newPassword')
    };

    if(!updateUserPasswordRequestDto.oldPassword || !updateUserPasswordRequestDto.newPassword)
        return;

    event.preventDefault();  // 이벤트의 기본 동작 중단

    $.ajax({
        type: 'PUT',
        url: '/api/v1/user-password/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(updateUserPasswordRequestDto)
    }).done(function(){
        alert('비밀번호 변경 완료.');
        window.location.href = '/user/profile';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}