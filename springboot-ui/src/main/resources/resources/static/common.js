function fillOwner() {
    $("[name='owner']").val($.cookie('_user_name'));
}

function checkOwner(owner) {
    var user = $.cookie("_user_name");
    if (user !== owner) {
        // error box
        $.gritter.add({
            // (string | mandatory) the heading of the notification
            title: '只有owner才能操作!',
            class_name: 'gritter-error'
        });
        return false;
    }
    return true;
}