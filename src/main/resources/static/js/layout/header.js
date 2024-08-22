// header.js

function openModal() {
    document.getElementById("loginModal").style.display = "block";
}

function closeModal() {
    document.getElementById("loginModal").style.display = "none";
}

function redirectToGoogle() {
    window.location.href = "/oauth2/authorization/google";
}

function redirectToSignup() {
    console.log("Signup button clicked");
    window.location.href = `/user/public/signup`;
}

// Close modal when clicking outside of it
window.onclick = function(event) {
    var modal = document.getElementById("loginModal");
    if (event.target == modal) {
        closeModal();
    }
}
