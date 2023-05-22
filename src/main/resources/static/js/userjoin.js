document.getElementById("user-join-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userJoin();
});

const joinForm = document.getElementById("user-join-form");

function userJoin(){
    const formData = new FormData(joinForm);

    const userJoinRequestDto = {
        username: formData.get('username'),
        password: formData.get('password'),
    };

    if(!checkRequiredValue(userJoinRequestDto))
        return;

    $.ajax({
        type: 'POST',
        url: '/api/v1/user-join',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userJoinRequestDto)
    }).done(function(){
        alert('회원가입이 완료되었습니다.');
        window.location.href = '/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function checkRequiredValue(value){
    if (!value.username || !value.password) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}