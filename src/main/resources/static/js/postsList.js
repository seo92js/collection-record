var page = 0;
var isEnd = false;

window.addEventListener('scroll', function(){
        if ($(window).scrollTop() + $(window).height() == $(document).height() && !isEnd) {
          page = page + 1;
          var id = $('.category-link').data('id');
          var category = $('.category-link').data('category');
          postList(id, category, page);
        }
    }
)

$(document).ready(function() {
    // 카테고리 클릭 이벤트 처리
    $('.category-link').on('click', function(event) {
        var id = $(this).data('id');
        var category = $(this).data('category');
        var post = $('#postList');
        post.empty();
        event.preventDefault();
        page = 0;
        postList(id, category, page);
    });

    var id = $('.category-link[data-category="all"]').data('id');
    $('.category-link[data-id="' + id + '"][data-category="all"]').click();
});

function postList(id, category, page) {
    $.ajax({
        type: 'GET',
        url: '/api/v1/posts/' + id + '/' + category + '/' + page,
    }).done(function(response){
        var postList = $('#postList');
        //postList.empty();

        response.forEach(function(post) {
            var row = $('<tr>');
            var imageCell = $('<td>');
            var imageLink = $('<img>').attr('src', '/api/v1/image-view/' + post.representativeImageId);

            var titleCell = $('<td>');
            var titleLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title);

            imageCell.append(imageLink);
            titleCell.append(titleLink);
            row.append(imageCell, titleCell);
            postList.append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}