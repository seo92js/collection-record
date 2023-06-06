document.getElementById("user-following-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userFollowing();
});

const followingForm = document.getElementById("user-following-form");

function userFollowing(){
    const formData = new FormData(followingForm);

    const userFollowingRequestDto = {
        userId: formData.get('userId'),
        followingUserId: formData.get('followingUserId'),
    };

    $.ajax({
        type: 'PUT',
        url: '/api/v1/user-following/' + userFollowingRequestDto.followingUserId,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userFollowingRequestDto)
    }).done(function(){
        alert('팔로잉.');
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}