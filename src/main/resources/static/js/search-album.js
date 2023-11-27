const searchText = $("#artist-input");
const artistSelect = $("#artist-select");
const artist = $("#artist");
const album = $("#album");
const genre = $("#genre");
const albumArt = $("#albumArt");
const albumArtImg = $("#album-art-img");

$("#artist-select").on("change", function (){
    var selectedOption = artistSelect.find(":selected");

    artist.attr("value", selectedOption.attr("artist"));
    album.attr("value", selectedOption.attr("album"));
    genre.attr("value", selectedOption.attr("genre"));
    albumArt.attr("value", selectedOption.attr("albumArt"));
    albumArtImg.attr("src", selectedOption.attr("albumArt"));
})

function searchAlbum(){
    $.ajax({
        type: 'GET',
        url: "https://itunes.apple.com/search?media=music&entity=album&country=kr&term=" + searchText.val(),
              dataType: "json",
    }).done(function(response){
        artistSelect.empty();
        artist.attr("value", "");
        album.attr("value", "");
        genre.attr("value", "");
        albumArt.attr("value", "");
        albumArtImg.attr("src", "");

        for (var i = 0; i < response.results.length; i++){

            const artist = response.results[i].artistName;
            const album = response.results[i].collectionName;
            const genre = response.results[i].primaryGenreName;
            const albumArt = response.results[i].artworkUrl100;

            var option = $("<option>").text(artist + " - " + album)
            .attr("artist", artist)
            .attr("album", album)
            .attr("albumArt", albumArt)
            .attr("genre", genre);

            artistSelect.append(option);
        }

        $("#artist-select option:eq(0)").prop("selected", true).trigger('change');
    });
}

