document.getElementById("login-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userLogin();
});

const joinForm = document.getElementById("login-form");

function userLogin(){
    const formData = new FormData(joinForm);

    const loginRequestDto = {
        username: formData.get('username'),
        password: formData.get('password'),
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/login',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(loginRequestDto)
    }).done(function(){
        var socket = new WebSocket('ws://localhost:8080/notification');

        alert('로그인이 완료되었습니다.');
        window.location.href = '/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}