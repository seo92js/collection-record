function switchChildForm(commentId){

    $('#create-parent-comment-form').addClass('hidden');
    $('#create-child-comment-form').removeClass('hidden');

    document.getElementById("parentCommentId").value = commentId;
}

function switchParentForm(){
    $('#create-parent-comment-form').removeClass('hidden');
    $('#create-child-comment-form').addClass('hidden');
}
