var page = 0;
var isEnd = false;

window.addEventListener('scroll', function(){
        if ($(window).scrollTop() + $(window).height() == $(document).height() && !isEnd) {
          page = page + 1;
          loadFollowPostsList(page);
        }
    }
)

loadFollowPostsList(page);

function loadFollowPostsList(page){
    $.ajax({
        type: 'GET',
        url: '/api/v1/home/' + page,
    }).done(function(response){
        var followPostsList = $('#follow-posts-list');

        if (response.length === 0){
            isEnd = true;
            return;
        }

        response.forEach(function(post){
            var row = $('<tr>');
            var usernameCell = $('<td>');
            var username = $('<a>').attr('href', '/user/' + post.username + '/home').text(post.username);
            var imageCell = $('<td>');
            var imageLink = $('<img>').attr('src', '/api/v1/image-view/' + post.representativeImageId);
            var titleCell = $('<td>');
            var titleLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title);

            usernameCell.append(username);
            imageCell.append(imageLink);
            titleCell.append(titleLink);

            row.append(usernameCell, imageCell, titleCell);

            followPostsList.append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}