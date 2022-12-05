function updateUser() {
    $.ajax({
        url: `net/account`,
        type: "PUT",
        async: false,
        contentType: "application/json",
        data: `{ "username": "${document.getElementById("update-username").value}", 
                "password": "${document.getElementById("update-password").value}"}`,
        success: function () {
            $('#update-user-modal').modal('hide');
            $('#update-user-notification-modal').modal('show');
        }
    });
}

function deleteUser() {
    $.ajax({
        url: `net/account`,
        type: "DELETE",
        async: false,
        success: function () {
            $('#delete-user-modal').modal('hide');
            $('#delete-user-notification-modal').modal('show');
        }
    });
}

