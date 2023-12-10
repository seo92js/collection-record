let page = 0;

window.addEventListener('scroll', function(){
    if ($(window).scrollTop() + $(window).height() == $(document).height()) {
      page = page + 1;
      searchList(page, $('#search-text').val());
    }
})

function search(){

    page = 0;

    $('#search-list').empty();

    var type = $('input[name=type]:checked').val();

    if ($('#search-text').val() != ""){
        event.preventDefault();

        if (type == 'user'){
            searchUser(page, $('#search-text').val());
        } else if (type == 'artist'){
            searchArtistPosts(page, $('#search-text').val());
        } else if (type == 'album') {
            searchAlbumPosts(page, $('#search-text').val());
        }
    }
}

function searchUser(page, text){
 $.ajax({
    type: 'GET',
    url: '/api/v1/search/user/' + text + '/' + page,
    }).done(function(response){

        if (response.length === 0){
            return;
        }

        response.forEach(function(user){

            var row = $('<a>').attr('href', '/user/' + user.id + '/home').addClass('row mb-3 mx-auto align-items-center shadow p-3 bg-body rounded user-home__posts');
            var col1 = $('<div>').addClass('col-sm-3 text-center');
            col1.append($('<img>').attr('src', '/api/v1/image/' + user.profileImageId).addClass('image-profile'));
            var col2 = $('<div>').addClass('col-sm-3 text-center');
            col2.append($('<div>').text(user.username).addClass('search__username'));

            row.append(col1);
            row.append(col2);

            $('#search-list').append(row);

        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function searchArtistPosts(page, text){
 $.ajax({
    type: 'GET',
    url: '/api/v1/search/artist/' + text + '/' + page,
    }).done(function(response){

        if (response.length === 0){
            return;
        }

        response.forEach(function(post){

            var row = $('<a>').attr('href', '/posts/' + post.id).addClass('row mb-3 mx-auto align-items-center shadow p-3 bg-body rounded user-home__posts');
            //날짜, 인덱스
            var col1 = $('<div>').addClass('col-sm-2');

            col1.append($('<div>').text(post.createdDate).addClass('row mb-3 justify-content-center user-home__date'));
            col1.append($('<div>').text(post.username).addClass('row justify-content-center user-home__index'))

            //앨범 아트
            var col2 = $('<div>').addClass('col-sm-2 text-center');

            col2.append($('<img>').attr("src", post.albumArt).addClass('user-home__albumArt'));

            //아티스트, 앨범, 장르
            var col3 = $('<div>').addClass('col-sm-6');

            col3.append($('<div>').text(post.artist).addClass('row mb-4 justify-content-center user-home__artist'));
            col3.append($('<div>').text(post.album).addClass('row mb-4 justify-content-center user-home__album'));
            col3.append($('<div>').text(post.genre).addClass('row justify-content-center user-home__genre'));

            //카테고리
            var col4 = $('<div>').addClass('col-sm-1');

            col4.append($('<div>').text(post.category).addClass('row justify-content-center user-home__category'));

            //상태
            var col5 = $('<div>').addClass('col-sm-1');

            col5.append($('<div>').text(post.status).addClass('row justify-content-center user-home__status'));

            row.append(col1);
            row.append(col2);
            row.append(col3);
            row.append(col4);
            row.append(col5);

            $('#search-list').append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function searchAlbumPosts(page, text){
 $.ajax({
    type: 'GET',
    url: '/api/v1/search/album/' + text + '/' + page,
    }).done(function(response){

        if (response.length === 0){
            return;
        }

        response.forEach(function(post){
            var row = $('<a>').attr('href', '/posts/' + post.id).addClass('row mb-3 mx-auto align-items-center shadow p-3 bg-body rounded user-home__posts');
            //날짜, 인덱스
            var col1 = $('<div>').addClass('col-sm-2');

            col1.append($('<div>').text(post.createdDate).addClass('row mb-3 justify-content-center user-home__date'));
            col1.append($('<div>').text(post.username).addClass('row justify-content-center user-home__index'))

            //앨범 아트
            var col2 = $('<div>').addClass('col-sm-2 text-center');

            col2.append($('<img>').attr("src", post.albumArt).addClass('user-home__albumArt'));

            //아티스트, 앨범, 장르
            var col3 = $('<div>').addClass('col-sm-6');

            col3.append($('<div>').text(post.artist).addClass('row mb-4 justify-content-center user-home__artist'));
            col3.append($('<div>').text(post.album).addClass('row mb-4 justify-content-center user-home__album'));
            col3.append($('<div>').text(post.genre).addClass('row justify-content-center user-home__genre'));

            //카테고리
            var col4 = $('<div>').addClass('col-sm-1');

            col4.append($('<div>').text(post.category).addClass('row justify-content-center user-home__category'));

            //상태
            var col5 = $('<div>').addClass('col-sm-1');

            col5.append($('<div>').text(post.status).addClass('row justify-content-center user-home__status'));

            row.append(col1);
            row.append(col2);
            row.append(col3);
            row.append(col4);
            row.append(col5);

            $('#search-list').append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}