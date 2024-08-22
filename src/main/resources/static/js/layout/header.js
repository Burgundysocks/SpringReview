// header.js

function openModal() {
    document.getElementById("loginModal").style.display = "block";
}

function closeModal() {
    document.getElementById("loginModal").style.display = "none";
}

function googleLogin() {
    window.location.href = "/oauth2/authorization/google";
}

function memberSignup() {
    window.location.href = `/user/public/signup`;
}


window.onclick = function(event) {
    var modal = document.getElementById("loginModal");
    if (event.target == modal) {
        closeModal();
    }
}
