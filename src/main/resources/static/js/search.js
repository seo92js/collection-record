document.getElementById("search-btn").addEventListener('click', function(event) {
    event.preventDefault();
    searchList();
});

function searchList(){

    const text = document.getElementById('search-text').value;

    $.ajax({
        type: 'GET',
        url: '/api/v1/search/' + text,
    }).done(function(response){
        var searchList = $('#searchList');
        searchList.empty();

        response.forEach(function(search){
            var row = $('<tr>');
            var userCell = $('<td>').text(search.username);
            row.append(userCell);
            searchList.append(row);
        });
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}
