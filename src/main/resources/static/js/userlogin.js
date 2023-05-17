document.getElementById("user-login-btn").addEventListener('click', userLogin);

const joinForm = document.getElementById("user-login-form");

function userLogin(){
    const formData = new FormData(joinForm);

    const userLoginDto = {
        email: formData.get('email'),
        password: formData.get('password'),
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/user-login',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userLoginDto)
    }).done(function(){
        alert('로그인이 완료되었습니다.');
        window.location.href = '/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}