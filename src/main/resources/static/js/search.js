let page = 0;
let isEnd = false;

window.addEventListener('scroll', function(){
    if ($(window).scrollTop() + $(window).height() == $(document).height() && !isEnd) {
      page = page + 1;
      searchList(page, $('#search-text').val());
    }
})

function search(){
    isEnd = false;

    page = 0;

    $('#user-search-list').empty();
    $('#posts-search-list').empty();

    if ($('#search-text').val() != ""){
        event.preventDefault();
        searchList(page, $('#search-text').val());
    }
}

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

            const div = $('<div>').addClass('search__list-user-col');
            const title = $('<div>유저</div>');
            const userImageLink = $('<img>').attr('src', '/api/v1/image/' + user.profileImageId).addClass('search__list-user-col-image');
            const userLink = $('<a>').attr('href', '/user/' + user.username + '/home').text(user.username).addClass('search__list-user-col-username');
            div.append(title);
            div.append(userImageLink);
            div.append(userLink);

            $('#user-search-list').append(div);
        });

       response.postsSearchList.forEach(function(post){

            const div = $('<div>').addClass('search__list-posts-col');
            const title = $('<div>게시물</div>');
            const postImageLink = $('<img>').attr('src', '/api/v1/image/' + post.representativeImageId).addClass('search__list-posts-col-image');;
            const postLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title).addClass('search__list-posts-col-title');;
            div.append(title);
            div.append(postImageLink);
            div.append(postLink);

            $('#posts-search-list').append(div);
        });

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}