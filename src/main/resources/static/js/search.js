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
            isEnd = true;
            return;
        }

        response.forEach(function(user){
            var a = $('<a>').attr('href', '/user/' + user.username + '/home').addClass('search__link');

            const div = $('<div>').addClass('search__user');

            const firstRow = $('<div>').addClass('search__row');

            //col 1 유저 프로필 사진
            var col1 = $('<div>').addClass('search__col');
            col1.append($('<img>').attr('src', '/api/v1/image/' + user.profileImageId).addClass('search__img'))
            firstRow.append(col1);

            //col 2 유저 이름
            var col2 = $('<div>').addClass('search__col');
            col2.append($('<div>').text(user.username).addClass('search__username'));
            firstRow.append(col2);

            div.append(firstRow);
            a.append(div);

            $('#search-list').append(a);
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
            isEnd = true;
            return;
        }

        response.forEach(function(post){

            var a = $('<a>').attr('href', '/posts/' + post.id).addClass('posts-main__posts');

            const div = $('<div>').addClass('posts-main__posts');

            const firstRow = $('<div>').addClass('posts-main__row');

            //col 1 날짜, 인덱스
            var col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--short');
            var row1 = $('<div>').addClass('posts-main__row').addClass('posts-main__row--short');
            row1.append($('<div>').text(post.createdDate).addClass('posts-main__created-date'));
            var row2 = $('<div>').addClass('posts-main__row');
            row2.append($('<div>').text(post.username).addClass('posts-main__username'));
            col.append(row1);
            col.append(row2);
            firstRow.append(col);

            //col 2 앨범아트
            col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--album-art');
            col.append($('<img>').attr("src", post.albumArt).addClass('posts-main__album-art'));
            firstRow.append(col);

            //col 3 아티스트, 앨범, 장르
            col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--flex1');
            row1 = $('<div>').addClass('posts-main__row');
            row2 = $('<div>').addClass('posts-main__row');
            var row3 = $('<div>').addClass('posts-main__row');
            row1.append($('<div>').text(post.artist).addClass('posts-main__artist'));
            row2.append($('<div>').text(post.album).addClass('posts-main__album'));
            row3.append($('<div>').text(post.genre).addClass('posts-main__genre'));
            col.append(row1);
            col.append(row2);
            col.append(row3);
            firstRow.append(col);

            //col 4 카테고리
            col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--short');
            col.append($('<div>').text(post.category).addClass('posts-main__category'));
            firstRow.append(col);

            //col 5 상태
            col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--short');
            col.append($('<div>').text(post.status).addClass('posts-main__status'));
            firstRow.append(col);

            div.append(firstRow);

            a.append(firstRow);

            $('#search-list').append(a);
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
            isEnd = true;
            return;
        }

        response.forEach(function(post){

            var a = $('<a>').attr('href', '/posts/' + post.id).addClass('posts-main__posts');

            const div = $('<div>').addClass('posts-main__posts');

            const firstRow = $('<div>').addClass('posts-main__row');

            //col 1 날짜, 인덱스
            var col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--short');
            var row1 = $('<div>').addClass('posts-main__row').addClass('posts-main__row--short');
            row1.append($('<div>').text(post.createdDate).addClass('posts-main__created-date'));
            var row2 = $('<div>').addClass('posts-main__row');
            row2.append($('<div>').text(post.username).addClass('posts-main__username'));
            col.append(row1);
            col.append(row2);
            firstRow.append(col);

            //col 2 앨범아트
            col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--album-art');
            col.append($('<img>').attr("src", post.albumArt).addClass('posts-main__album-art'));
            firstRow.append(col);

            //col 3 아티스트, 앨범, 장르
            col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--flex1');
            row1 = $('<div>').addClass('posts-main__row');
            row2 = $('<div>').addClass('posts-main__row');
            var row3 = $('<div>').addClass('posts-main__row');
            row1.append($('<div>').text(post.artist).addClass('posts-main__artist'));
            row2.append($('<div>').text(post.album).addClass('posts-main__album'));
            row3.append($('<div>').text(post.genre).addClass('posts-main__genre'));
            col.append(row1);
            col.append(row2);
            col.append(row3);
            firstRow.append(col);

            //col 4 카테고리
            col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--short');
            col.append($('<div>').text(post.category).addClass('posts-main__category'));
            firstRow.append(col);

            //col 5 상태
            col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--short');
            col.append($('<div>').text(post.status).addClass('posts-main__status'));
            firstRow.append(col);

            div.append(firstRow);

            a.append(firstRow);

            $('#search-list').append(a);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}