function showCreateChildForm(parentCategoryId) {
    $('#create-child-category-name-'  + parentCategoryId).attr("required");
    $('#create-child-category-form-'  + parentCategoryId).removeClass('category__add--hidden');
}

function hiddenCreateChildForm(parentCategoryId){
    $('#create-child-category-name-'  + parentCategoryId).removeAttr("required");
    $('#create-child-category-form-'  + parentCategoryId).addClass('category__add--hidden');
}

function createChildCategory(userId, parentCategoryId){
    const name = document.getElementById("create-child-category-name-" + parentCategoryId).value;

    const createChildCategoryRequestDto = {
        userId: userId,
        parentCategoryId: parentCategoryId,
        name: name
    };

    if(!createChildCategoryRequestDto.name)
        return;

    $.ajax({
        type: 'POST',
        url: '/api/v1/child-category',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(createChildCategoryRequestDto)
    }).done(function(){
        alert('카테고리 추가 완료');
        location.reload(true);
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}