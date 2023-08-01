document.getElementById("category-add-btn").addEventListener('click', function(event) {
    event.preventDefault();
    categoryAdd();
});

const joinForm = document.getElementById("category-add-form");

function categoryAdd(){
    const formData = new FormData(joinForm);

    const categoryAddRequestDto = {
        userId: formData.get('userId'),
        name: formData.get('name')
    };

    if(!checkRequiredValue(categoryAddRequestDto))
        return;

    $.ajax({
        type: 'POST',
        url: '/api/v1/category-add',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(categoryAddRequestDto)
    }).done(function(){
        alert('카테고리 추가 완료.');
        window.location.href = '/category';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function categoryChildAdd(userId, parentCategoryId){
    const name = document.getElementById("category-child-add-input").value;

    const categoryChildAddRequestDto = {
        userId: userId,
        parentCategoryId: parentCategoryId,
        name: name
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/category-child-add',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(categoryChildAddRequestDto)
    }).done(function(){
        alert('카테고리 추가 완료.');
        window.location.href = '/category';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function checkRequiredValue(value){
    if (!value.name) {
        alert('필수 값을 입력하시오');
        return false;
    }else{
        return true;
    }
}