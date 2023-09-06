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
            var imageHandlers = createImageHandlers(post);

            const div = $('<div>').addClass('posts-feed__posts');

            // 유저 이름
            const firstRow = $('<div>').addClass('posts-feed__row').addClass('posts-feed__row--short');
            var col1 = $('<div>').addClass('posts-feed__col');
            col1.append($('<img>').attr('src', '/api/v1/image/' + post.userProfileImageId).addClass('posts-feed__userprofile'));
            var col2 = $('<div>').addClass('posts-feed__col');
            col2.append($('<a>').text(post.username).attr('href', '/user/' + post.username + '/home').addClass('posts-feed__username'));
            var col3 = $('<div>').addClass('posts-feed__col');
            col3.append($('<div>').text(post.createdDate).addClass('posts-feed__created-date'));
            firstRow.append(col1);
            firstRow.append(col2);
            firstRow.append(col3);
            div.append(firstRow);

            //
            var a = $('<a>').attr('href', '/posts/' + post.id);
            const secondRow = $('<div>').addClass('posts-feed__row').addClass('posts-feed__row--long');
            //앨범아트
            var col1 = $('<div>').addClass('posts-feed__col').addClass('posts-feed__col--album-art');
            col1.append($('<img>').attr("src", post.albumArt).addClass('posts-feed__album-art'));
            secondRow.append(col1);
            //아티스트, 앨범, 장르
            var col2 = $('<div>').addClass('posts-feed__col').addClass('posts-feed__col--flex1');
            var row1 = $('<div>').addClass('posts-feed__row');
            row1.append($('<div>').text(post.artist).addClass('posts-feed__artist'));
            var row2 = $('<div>').addClass('posts-feed__row');
            row2.append($('<div>').text(post.album).addClass('posts-feed__album'));
            var row3 = $('<div>').addClass('posts-feed__row');
            row3.append($('<div>').text(post.genre).addClass('posts-feed__genre'));
            col2.append(row1);
            col2.append(row2);
            col2.append(row3);
            secondRow.append(col2);
            //카테고리
            var col3 = $('<div>').addClass('posts-feed__col').addClass('posts-feed__col--short');
            col3.append($('<div>').text(post.category).addClass('posts-feed__category'));
            secondRow.append(col3);
            //상태
            var col4 = $('<div>').addClass('posts-feed__col').addClass('posts-feed__col--short');
            col4.append($('<div>').text(post.status).addClass('posts-feed__status'));
            secondRow.append(col4);

            a.append(secondRow);
            div.append(a);

            // 이미지들
            const thirdRow = $('<div>').addClass('posts-feed__row').addClass('posts-feed__row--img');

            var col1 = $('<div>').addClass('posts-feed__col');

            var temp = $('<div>').addClass('posts-feed__row');
            var imgElement = $('<img>').attr('src', '/api/v1/image/' + post.representativeImageId[0]).addClass('posts-feed__img');
            temp.append(imgElement);
            col1.append(temp);

            var prevLink = $('<a>').addClass('posts-feed__prev');

            prevLink.append($('<i>').addClass('fas fa-arrow-left fa-lg'));

            prevLink.on('click', function() {
                imageHandlers.prevImage(imgElement, post.representativeImageId);
            });

            var nextLink = $('<a>').addClass('posts-feed__next');

            nextLink.append($('<i>').addClass('fas fa-arrow-right fa-lg'));

            nextLink.on('click', function() {
                imageHandlers.nextImage(imgElement, post.representativeImageId);
            });

            col1.append(prevLink);
            col1.append(nextLink);

            thirdRow.append(col1);
            div.append(thirdRow);

            // 텍스트
            const fourthRow = $('<div>').addClass('posts-feed__row').addClass('posts-feed__row--text');
            var col1 = $('<div>').addClass('posts-feed__col');
            col1.append($('<pre>').text(post.text).addClass('posts-feed__text'))

            fourthRow.append(col1);
            div.append(fourthRow);

            $('#follow-posts-list').append(div);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function createImageHandlers(post) {
    let index = 0;

    function updateImage(imageElement, imageId) {
        $(imageElement).attr("src", '/api/v1/image/' + imageId);
    }

    function prevImage(imageElement, representativeImageId) {
        if (index != 0) {
            index--;
            updateImage(imageElement, representativeImageId[index]);
        }
    }

    function nextImage(imageElement, representativeImageId) {
        let maxLength = representativeImageId.length;

        if (index != maxLength - 1) {
            index++;
            updateImage(imageElement, representativeImageId[index]);
        }
    }

    return { prevImage, nextImage };
}