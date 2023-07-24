let page = 0;
let isEnd = false;
let id;
let category;

$(document).ready(function() {
    let isCategoryClicked = false;

    // 카테고리 클릭 이벤트 처리
    $('.category-link').on('click', function(event) {
        id = $(this).data('id');
        category = $(this).data('category');

        $('#postList').empty();
        page = 0;

        isCategoryClicked = true;

        event.preventDefault();

        loadPostList(id, category, page);
        isEnd = false;
    });

    $(window).on('scroll', function(){
        if ($(window).scrollTop() + $(window).height() == $(document).height() && !isEnd &&!isCategoryClicked) {
              page = page + 1;
              loadPostList(id, category, page);
        }
        isCategoryClicked = false;
    })

    id = $('.category-link[data-category="all"]').data('id');
    $('.category-link[data-id="' + id + '"][data-category="all"]').click();
});


function loadPostList(id, category, page) {
    $.ajax({
        type: 'GET',
        url: '/api/v1/posts/' + id + '/' + category + '/' + page,
    }).done(function(response){
        if (response.length === 0){
            isEnd = true;
            return;
        }

        response.forEach(function(post) {
            const div = $('<div>').addClass('user-home__post-post');
            const titleLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title).addClass('user-home__post-title');
            const imageLink = $('<img>').attr('src', '/api/v1/image-view/' + post.representativeImageId).addClass('user-home__post-image');
            div.append(titleLink);
            div.append(imageLink);

            $('#postList').append(div);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}