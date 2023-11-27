function preview(input){
  $('#preview').empty();

  if (input.files) {
    for (var i = 0; i < input.files.length; i++){
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#preview').attr("src", e.target.result).addClass('posts-add__preview');
        };

        reader.readAsDataURL(input.files[i]);
    }
  } else {
    $('#preview').empty();
  }
}