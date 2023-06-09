var page = 0;
var isEnd = false;

window.addEventListener('scroll', function(){
    if ($(window).scrollTop() + $(window).height() == $(document).height() && !isEnd) {
      page = page + 1;
      searchList(page, $('#search-text').val());
    }
})

document.getElementById("search-btn").addEventListener('click', function(event){
    isEnd = false;

    page = 0;

    $('#user-search-list').empty();
    $('#posts-search-list').empty();

    if ($('#search-text').val() != ""){
        event.preventDefault();
        searchList(page, $('#search-text').val());
    }
})

function searchList(page, text){

    $.ajax({
        type: 'GET',
        url: '/api/v1/search/' + text + '/' + page,
    }).done(function(response){

        if (response.userSearchList.length === 0 && response.postsSearchList.length === 0){
            isEnd = true;
            return;
        }

        response.userSearchList.forEach(function(user){
            var userRow = $('<tr>');
            var userImageCell = $('<td>');
            var userImageLink = $('<img>').attr('src', '/api/v1/image-view/' + user.profileImageId);

            var userCell = $('<td>');
            var userLink = $('<a>').attr('href', '/user/' + user.username + '/home').text(user.username);
            userImageCell.append(userImageLink);
            userCell.append(userLink);
            userRow.append(userImageCell, userCell);
            $('#user-search-list').append(userRow);
        });

       response.postsSearchList.forEach(function(post){
            var postRow = $('<tr>');
            var postImageCell = $('<td>').text(post.image);
            var postImageLink = $('<img>').attr('src', '/api/v1/image-view/' + post.representativeImageId);

            var postCell = $('<td>');
            var postLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title);

            postImageCell.append(postImageLink);
            postCell.append(postLink)
            postRow.append(postImageCell, postCell);
            $('#posts-search-list').append(postRow);
        });

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}