document.getElementById("logout-btn").addEventListener('click', function(event) {
    event.preventDefault();
    userLogout();
});

function userLogout(){
    $.ajax({
        type: 'POST',
        url: '/api/v1/logout',
    }).done(function(){
        alert('로그아웃.');
        window.location.href = '/';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}