function previewProfile(input){
  $('#preview').empty();

  if (input.files) {
    for (var i = 0; i < input.files.length; i++){
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#preview').attr("src", e.target.result).addClass('image-profile');
        };

        reader.readAsDataURL(input.files[i]);
    }
  } else {
    $('#preview').empty();
  }
}

function previewUpload(input){
  $('#preview').empty();

  if (input.files) {
    for (var i = 0; i < input.files.length; i++){
        var reader = new FileReader();
        reader.onload = function(e) {
            var div = $('<div>').addClass('image-upload')
            var imgElement = $('<img>').attr('src', e.target.result).addClass('img-thumbnail');
            div.append(imgElement);
            //$('#preview').append(imgElement);
            $('#preview').append(div);
        };

        reader.readAsDataURL(input.files[i]);
    }
  } else {
    $('#preview').empty();
  }
}