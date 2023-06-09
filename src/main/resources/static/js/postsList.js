var page = 0;
var isEnd = false;
var id;
var category;

$(document).ready(function() {
    var isCategoryClicked = false;

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
            var row = $('<tr>');
            var imageCell = $('<td>');
            var imageLink = $('<img>').attr('src', '/api/v1/image-view/' + post.representativeImageId);

            var titleCell = $('<td>');
            var titleLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title);

            imageCell.append(imageLink);
            titleCell.append(titleLink);
            row.append(imageCell, titleCell);

            $('#postList').append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}