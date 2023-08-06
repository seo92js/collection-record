let page = 0;
let isEnd = false;

window.addEventListener('scroll', function(){
    if ($(window).scrollTop() + $(window).height() == $(document).height() && !isEnd) {
      page = page + 1;
      getFollowPosts(page);
    }
})

getFollowPosts(page);

function getFollowPosts(page){
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

            const createdDateDiv = $('<div>').addClass('home__feed-post-createdDate');
            const createdDate = $('<div>').text(post.createdDate);
            createdDateDiv.append(createdDate);

            const titleDiv = $('<div>').addClass('home__feed-post-title');
            const username = $('<a>').attr('href', '/user/' + post.username + '/home').text(post.username).addClass('home__feed-post-title-username');
            const titleLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title).addClass('home__feed-post-title-title');
            const status = $('<div>').text(post.status).addClass('home__feed-post-title-status');
            titleDiv.append(username);
            titleDiv.append(titleLink);
            titleDiv.append(status);

            const imageDiv = $('<div>').addClass('home__feed-post-image');
            const imageLink = $('<img>').attr('src', '/api/v1/image/' + post.representativeImageId);
            imageDiv.append(imageLink);

            div.append(createdDateDiv);
            div.append(titleDiv);
            div.append(imageDiv);

            $('#follow-posts-list').append(div);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}