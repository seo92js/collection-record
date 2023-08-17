let page = 0;
let isEnd = false;
let id;
let category;

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
          getCategoryPosts(this.id, this.category);
    }
})

function getCategoryPosts(id, category) {
    this.id = id;
    this.category = category;

    $.ajax({
        type: 'GET',
        url: '/api/v1/posts/' + id + '/' + category + '/' + page,
    }).done(function(response){
        if (response.length === 0){
            isEnd = true;
            return;
        }

        response.forEach(function(post, index) {
            const div = $('<div>').addClass('user-home__posts');

            const firstRow = $('<div>').addClass('user-home__row');

            //카테고리
            const col0 = $('<div>').addClass('user-home__col');
            col0.append($('<div>').text("#" + index).addClass('user-home__posts-index'));
            firstRow.append(col0);

            const col1 = $('<div>').addClass('user-home__col');
            col1.append($('<div>').text(post.category).addClass('user-home__posts-category'));
            firstRow.append(col1);
            //게시물 상태
            const col2 = $('<div>').addClass('user-home__col');
            col2.append($('<div>').text(post.status).addClass('user-home__posts-status'));
            firstRow.append(col2);

            //아티스트
            const col3 = $('<div>').addClass('user-home__col');
            col3.append($('<div>').text(post.artist).addClass('user-home__posts-artist'));
            firstRow.append(col3);

            //앨범
            const col4 = $('<div>').addClass('user-home__col');
            col4.append($('<div>').text(post.album).addClass('user-home__posts-album'));
            firstRow.append(col4);

            //장르
            const col5 = $('<div>').addClass('user-home__col');
            col5.append($('<div>').text(post.genre).addClass('user-home__posts-genre'));
            firstRow.append(col5);

            //앨범아트
            const col6 = $('<div>').addClass('user-home__col');

            a = $('<a>').attr('href', '/posts/' + post.id).addClass('user-home__link');
            a.append($('<img>').attr("src", post.albumArt));
            col6.append(a);
            firstRow.append(col6);

            div.append(firstRow);
            $('#category-posts-list').append(div)
        });

      /* let num = 0;

       let container = $('<div>').addClass('user-home__row-wrapper');

        response.forEach(function(post, index) {
            const div = $('<div>').addClass('user-home__posts');

            //첫줄
            const firstRow = $('<div>').addClass('user-home__row');
            let col = ($('<div>').addClass('user-home__col').addClass('user-home__col--left'));
            col.append($('<div>').text(post.createdDate).addClass('user-home__created-date'));
            firstRow.append(col);

            //두번째 줄
            const secondRow = $('<div>').addClass('user-home__row');
            col = $('<div>').addClass('user-home__col').addClass('user-home__col--left');
            col.append($('<a>').attr('href', '/posts/' + post.id).text(post.title).addClass('user-home__title'));
            secondRow.append(col);
            col = $('<div>').addClass('user-home__col').addClass('user-home__col--right');
            col.append($('<div>').text(post.status).addClass('user-home__status'));
            secondRow.append(col);

            //세번째 줄
            const thirdRow = $('<div>').addClass('user-home__row').addClass('user-home__row--long');
            col = $('<div>').addClass('user-home__col');
            a = $('<a>').attr('href', '/posts/' + post.id).addClass('user-home__link');
            a.append($('<img>').attr('src', '/api/v1/image/' + post.representativeImageId).addClass('user-home__img'));
            col.append(a);
            thirdRow.append(col);

            div.append(firstRow);
            div.append(secondRow);
            div.append(thirdRow);

            container.append(div);

            num++;

            console.log(num);

            if (num === 3 || index === response.length - 1) {
*//*            if (num === 3) {*//*
                $('#category-posts-list').append(container); // 3개마다 또는 마지막 게시물일 때 컨테이너를 추가
                container = $('<div>').addClass('user-home__row-wrapper'); // 다음 컨테이너 생성
                num = 0;
            }
        });*/

    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

