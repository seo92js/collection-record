document.getElementById("user-update-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userUpdate();
});

const profileForm = document.getElementById("user-profile-form");

function userUpdate(){
    const formData = new FormData(profileForm);

    const userUpdateRequestDto = {
        username: formData.get('username'),
        password: formData.get('password'),
        image: formData.get('image')
    };

    const id = document.getElementById('userId').value;

    $.ajax({
        type: 'PUT',
        url: '/api/v1/user-update/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userUpdateRequestDto)
    }).done(function(){
        alert('수정완료.');
        window.location.href = '/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}