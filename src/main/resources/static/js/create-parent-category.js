function createParentCategory(userId){
    const createParentCategoryForm = document.getElementById("create-parent-category-form");

    const formData = new FormData(createParentCategoryForm);

    const createParentCategoryRequestDto = {
        userId: userId,
        name: formData.get('name')
    };

    if (!createParentCategoryRequestDto.name)
        return;

    event.preventDefault();  // 이벤트의 기본 동작 중단

    $.ajax({
        type: 'POST',
        url: '/api/v1/parent-category',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(createParentCategoryRequestDto)
    }).done(function(event){
        alert('카테고리 추가 완료');
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}
