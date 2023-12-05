let page = 0;
let isEnd = false;
let id;
let category;
let artist;

$('#category-all-btn').on('click', function() {
    page = 0;
    $('#category-posts-list').empty();
    isEnd = false;

    $('.user-home__category-list').removeClass('focus');
    $(this).addClass('focus');
});

$('.user-home__category-list').on('click', function() {
    page = 0;
    $('#category-posts-list').empty();
    isEnd = false;

    $('#category-all-btn').removeClass('focus');
    $('.user-home__category-list').removeClass('focus');
    $(this).addClass('focus');
});

$('#category-all-btn').click();

$(window).on('scroll', function(){
    if ($(window).scrollTop() + $(window).height() == $(document).height() && !isEnd) {
          page = page + 1;

          event.preventDefault();

          if (this.category != "")
            getCategoryPosts(this.id, this.category);
          else
            getArtistPosts(this.id, this.artist);
    }
})

function getCategoryPosts(id, category) {
    this.id = id;
    this.category = category;
    this.artist = "";

    $.ajax({
        type: 'GET',
        url: '/api/v1/posts/category/' + id + '/' + category + '/' + page,
    }).done(function(response){
        if (response.length === 0){
            isEnd = true;
            return;
        }

        response.forEach(function(post, index) {
            var row = $('<a>').attr('href', '/posts/' + post.id).addClass('row mb-3 mx-auto align-items-center user-home__posts');
            //날짜, 인덱스
            var col1 = $('<div>').addClass('col-sm-2');

            col1.append($('<div>').text(post.createdDate).addClass('row mb-3 justify-content-center user-home__date'));
            col1.append($('<div>').text("#" + (response.length - index)).addClass('row mb-3 justify-content-center user-home__index'))

            //앨범 아트
            var col2 = $('<div>').addClass('col-sm-2 text-center');

            col2.append($('<img>').attr("src", post.albumArt).addClass('user-home__albumArt'));

            //아티스트, 앨범, 장르
            var col3 = $('<div>').addClass('col-sm-6');

            col3.append($('<div>').text(post.artist).addClass('row mb-3 justify-content-center user-home__artist'));
            col3.append($('<div>').text(post.album).addClass('row mb-3 justify-content-center user-home__album'));
            col3.append($('<div>').text(post.genre).addClass('row mb-3 justify-content-center user-home__genre'));

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

            $('#category-posts-list').append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function getArtistPosts(id, artist) {
    this.id = id;
    this.artist = artist;
    this.category = "";

    $.ajax({
        type: 'GET',
        url: '/api/v1/posts/artist/' + id + '/' + artist + '/' + page,
    }).done(function(response){
        if (response.length === 0){
            isEnd = true;
            return;
        }

        response.forEach(function(post, index) {
            var row = $('<a>').attr('href', '/posts/' + post.id).addClass('row mb-3 mx-auto align-items-center user-home__posts');
            //날짜, 인덱스
            var col1 = $('<div>').addClass('col-sm-2');

            col1.append($('<div>').text(post.createdDate).addClass('row mb-3 justify-content-center user-home__date'));
            col1.append($('<div>').text("#" + (response.length - index)).addClass('row mb-3 justify-content-center user-home__index'))

            //앨범 아트
            var col2 = $('<div>').addClass('col-sm-2 text-center');

            col2.append($('<img>').attr("src", post.albumArt).addClass('user-home__albumArt'));

            //아티스트, 앨범, 장르
            var col3 = $('<div>').addClass('col-sm-6');

            col3.append($('<div>').text(post.artist).addClass('row mb-3 justify-content-center user-home__artist'));
            col3.append($('<div>').text(post.album).addClass('row mb-3 justify-content-center user-home__album'));
            col3.append($('<div>').text(post.genre).addClass('row mb-3 justify-content-center user-home__genre'));

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

            $('#category-posts-list').append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}