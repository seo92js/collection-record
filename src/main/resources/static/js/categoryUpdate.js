function categoryUpdate(id){

    const name = document.getElementById('category-name-input-' + id).value;

    const categoryUpdateRequestDto = {
        name: name
    };

    if(!checkRequiredValue(categoryUpdateRequestDto))
        return;

    $.ajax({
        type: 'PUT',
        url: '/api/v1/category-update/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(categoryUpdateRequestDto)
    }).done(function(){
        alert('카테고리 수정 완료.');
        window.location.href = '/category';
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function categoryChildUpdate(id){

    const name = document.getElementById('category-child-name-input-' + id).value;

    const categoryUpdateRequestDto = {
        name: name
    };

    if(!checkRequiredValue(categoryUpdateRequestDto))
        return;

    $.ajax({
        type: 'PUT',
        url: '/api/v1/category-update/' + id,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(categoryUpdateRequestDto)
    }).done(function(){
        alert('카테고리 수정 완료.');
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