document.getElementById("user-login-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userLogin();
});

const joinForm = document.getElementById("user-login-form");

function userLogin(){
    const formData = new FormData(joinForm);

    const userLoginRequestDto = {
        username: formData.get('username'),
        password: formData.get('password'),
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/user-login',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userLoginRequestDto)
    }).done(function(){
        alert('로그인이 완료되었습니다.');
        window.location.href = '/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}