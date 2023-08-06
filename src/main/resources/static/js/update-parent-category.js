function updateParentCategory(id){

    const name = document.getElementById('update-parent-category-name-' + id).value;

    const updateCategoryRequestDto = {
        name: name
    };

    if(!updateCategoryRequestDto.name)
        return;

    $.ajax({
        type: 'PUT',
        url: '/api/v1/category/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(updateCategoryRequestDto)
    }).done(function(){
        location.reload(true);
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

