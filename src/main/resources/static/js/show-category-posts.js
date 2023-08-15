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

       let num = 0;

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

            if (num === 3 || index === response.length - 1) {
                $('#category-posts-list').append(container); // 3개마다 또는 마지막 게시물일 때 컨테이너를 추가
                container = $('<div>').addClass('user-home__row-wrapper'); // 다음 컨테이너 생성
                num = 0;
            }

/*            $('#category-posts-list').append(div);*/

        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

