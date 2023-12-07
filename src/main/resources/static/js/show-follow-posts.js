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
            var div = $('<a>').attr('href', '/posts/' + post.id).addClass('row mb-4 shadow p-3 bg-body rounded text-decoration-none');

            //유저
            var row1 = $('<div>').addClass('row mb-3 align-items-center');
            row1.append($('<img>').attr('src', '/api/v1/image/' + post.userProfileImageId).addClass('col-sm-1'));
            row1.append($('<a>').text(post.username).attr('href', '/user/' + post.userId + '/home').addClass('col-sm-3 home__username'));
            row1.append($('<div>').text(post.createdDate).addClass('col home__createdDate'));

            //상태
            var row2 = $('<div>').addClass('row mb-1');
            row2.append($('<div>').text(post.status).addClass('col home__status'));
            //카테고리
            var row3 = $('<div>').addClass('row mb-1');
            row3.append($('<div>').text(post.category).addClass('col home__category'));
            //아티스트
            var row4 = $('<div>').addClass('row mb-1');
            row4.append($('<div>').text(post.artist).addClass('col home__artist'));
            //앨범
            var row5 = $('<div>').addClass('row mb-1');
            row5.append($('<div>').text(post.album).addClass('col home__album'))
            //장르
            var row6 = $('<div>').addClass('row mb-4');
            row6.append($('<div>').text(post.genre).addClass('col home__genre'));
            //이미지
            var row7 = $('<div>').addClass('row mb-3');
            row7.append($('<img>').attr('src', '/api/v1/image/' + post.imageIds[0]).addClass('col home__img'));
            //텍스트
            var row8 = $('<div>').addClass('row');
            row8.append($('<pre>').text(post.text).addClass('col home__text'));

            div.append(row1);
            div.append(row2);
            div.append(row3);
            div.append(row4);
            div.append(row5);
            div.append(row6);
            div.append(row7);
            div.append(row8);

            $('#follow-posts-list').append(div);

        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}