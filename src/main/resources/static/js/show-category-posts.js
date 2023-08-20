let page = 0;
let isEnd = false;
let id;
let category;
let artist;

$('#category-all-btn').on('click', function() {
    page = 0;
    $('#category-posts-list').empty();
    isEnd = false;
});

$('.user-home__link').on('click', function() {
    page = 0;
    $('#category-posts-list').empty();
    isEnd = false;
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
            var a = $('<a>').attr('href', '/posts/' + post.id).addClass('posts-main__posts');

            const div = $('<div>').addClass('posts-main__posts');

            const firstRow = $('<div>').addClass('posts-main__row');

            //col 1 날짜, 인덱스
            var col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--short');
            var row1 = $('<div>').addClass('posts-main__row').addClass('posts-main__row--short');
            row1.append($('<div>').text(post.createdDate).addClass('posts-main__created-date'));
            var row2 = $('<div>').addClass('posts-main__row');
            row2.append($('<div>').text("#" + (response.length - index)).addClass('posts-main__index'))
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
            a.append(div)
            $('#category-posts-list').append(a);
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

            var a = $('<a>').attr('href', '/posts/' + post.id).addClass('posts-main__posts');

            const div = $('<div>').addClass('posts-main__posts');

            const firstRow = $('<div>').addClass('posts-main__row');

            //col 1 날짜, 인덱스
            var col = $('<div>').addClass('posts-main__col').addClass('posts-main__col--short');
            var row1 = $('<div>').addClass('posts-main__row').addClass('posts-main__row--short');
            row1.append($('<div>').text(post.createdDate).addClass('posts-main__created-date'));
            var row2 = $('<div>').addClass('posts-main__row');
            row2.append($('<div>').text("#" + (response.length - index)).addClass('posts-main__index'))
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

            a.append(div)

            $('#category-posts-list').append(a);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}
