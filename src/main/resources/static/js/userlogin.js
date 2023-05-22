document.getElementById("user-login-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userLogin();
});

const joinForm = document.getElementById("user-login-form");

function userLogin(){
    const formData = new FormData(joinForm);

    const userLoginRequestDto = {
        email: formData.get('email'),
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