document.getElementById("user-update-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userUpdate();
});

const profileForm = document.getElementById("user-profile-form");

function userUpdate(){
    const formData = new FormData(profileForm);

    const userProfileDto = {
        username: formData.get('username'),
        password: formData.get('password'),
        image: formData.get('image')
    };

    $.ajax({
        type: 'PUT',
        url: '/api/v1/user-update',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userProfileDto)
    }).done(function(){
        alert('수정완료.');
        window.location.href = '/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}