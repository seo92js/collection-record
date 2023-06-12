document.getElementById("search-btn").addEventListener('click', function(event) {
    event.preventDefault();
    searchList();
});

function searchList(){

    const text = document.getElementById('search-text').value;

    if(!checkRequiredValue(text))
        return;

    $.ajax({
        type: 'GET',
        url: '/api/v1/search/' + text,
    }).done(function(response){
        var userSearchList = $('#user-search-list');
        userSearchList.empty();

        response.userSearchList.forEach(function(user){
            var userRow = $('<tr>');
            var userImageCell = $('<td>').text(user.image);
            var userCell = $('<td>');
            var userLink = $('<a>').attr('href', '/user/' + user.username + '/home').text(user.username);
            userCell.append(userLink);
            userRow.append(userImageCell, userCell);
            userSearchList.append(userRow);
        });

       var postsSearchList = $('#posts-search-list');
       postsSearchList.empty();

       response.postsSearchList.forEach(function(post){
            var postRow = $('<tr>');
            var postImageCell = $('<td>').text(post.image);
            var postCell = $('<td>');
            var postLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title);

            postCell.append(postLink)
            postRow.append(postImageCell, postCell);
            postsSearchList.append(postRow);
        });

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function checkRequiredValue(value){
    if (!value) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}