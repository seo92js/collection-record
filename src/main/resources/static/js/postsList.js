$(document).ready(function() {
    // 카테고리 클릭 이벤트 처리
    $('.category-link').on('click', function(event) {
        var id = $(this).data('id');
        var category = $(this).data('category');
        event.preventDefault();
        postList(id, category);
    });

    var id = $('.category-link[data-category="all"]').data('id');
    $('.category-link[data-id="' + id + '"][data-category="all"]').click();
});

function postList(id, category) {
    $.ajax({
        type: 'GET',
        url: '/api/v1/posts/' + id + '/' + category,
    }).done(function(response){
        var postList = $('#postList');
        postList.empty();

        response.forEach(function(post) {
            var row = $('<tr>');
            var imageCell = $('<td>').text(post.image);
            var titleCell = $('<td>');
            var titleLink = $('<a>').attr('href', '/posts/' + post.id).text(post.title);
            titleCell.append(titleLink);
            row.append(imageCell, titleCell);
            postList.append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}