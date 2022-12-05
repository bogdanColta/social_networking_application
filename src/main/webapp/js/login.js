let username_field = document.getElementById("username");
let password_field = document.getElementById("password");
let invalid_credentials = document.getElementById("invalid-message");

function login() {
    event.preventDefault();
    $.ajax({
        url: `net/account/login`,
        type: "POST",
        async: true,
        contentType: "application/json",
        data: `{"username": "${username_field.value}", 
                "password": "${password_field.value}"}`,
        success: function (id) {
            sessionStorage.setItem("id", id);
            window.location.href = 'index.html';
        },
        error: function (response) {
            if (response.status === 403) {
                handleInvalidCredentials();
            }
        }
    });
}

function logout() {
    $.ajax({
        url: `net/account/logout`,
        type: "GET",
        async: false,
        success: function () {
            sessionStorage.removeItem("id");
            window.location.href = 'login.html';
        }
    });
}

function handleInvalidCredentials() {
    invalid_credentials.innerHTML = "Invalid credentials!";
    username_field.value = "";
    password_field.value = "";
    username_field.classList.add("border-danger");
    password_field.classList.add("border-danger");
}

function clearInvalidCredentials() {
    invalid_credentials.innerHTML = "";
    username_field.classList.remove("border-danger");
    password_field.classList.remove("border-danger");
}
