let page = 0;
let isEnd = false;

window.addEventListener('scroll', function(){
    if ($(window).scrollTop() + $(window).height() == $(document).height() && !isEnd) {
      page = page + 1;
      loadFollowPostsList(page);
    }
})

loadFollowPostsList(page);

function loadFollowPostsList(page){
    $.ajax({
        type: 'GET',
        url: '/api/v1/home/' + page,
    }).done(function(response){
        if (response.length === 0){
            isEnd = true;
            return;
        }

        response.forEach(function(post){
            const div = $('<div>').addClass('home__feed-post');
            const username = $('<a>').attr('href', '/user/' + post.username + '/home').text(post.username).addClass('home__feed-post-username');
            const imageLink = $('<img>').attr('src', '/api/v1/image-view/' + post.representativeImageId).addClass('home__feed-post-image');
            const titleLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title).addClass('home__feed-post-title');
            div.append(username);
            div.append(imageLink);
            div.append(titleLink);

            $('#follow-posts-list').append(div);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}