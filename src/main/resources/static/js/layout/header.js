document.addEventListener('DOMContentLoaded', function() {

    document.getElementById("loginBtn").addEventListener("click", openModal);
    document.getElementById("loginBtn2").addEventListener("click", openModal);

 
    document.querySelector("#loginModal .modal-content .close").addEventListener("click", closeModal);

    // Handle clicks outside of the modal to close it
    window.onclick = function(event) {
        var modal = document.getElementById("loginModal");
        if (event.target === modal) {
            closeModal();
        }
    };

    document.querySelector('#loginModal .modal-body button').addEventListener('click', function() {
        const email = document.querySelector('#loginModal .modal-body input[type="text"]').value;
        const password = document.querySelector('#loginModal .modal-body input[type="password"]').value;
    
        fetch('/api/public/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email: email, password: password })
        })
        .then(response => response.json()) // 응답을 JSON으로 파싱
        .then(data => {
            if (data.status === 'success') { // 데이터의 status를 확인
                window.location.reload(); // 페이지 새로 고침
                console.log("로그인 성공");
                
            } else {
                alert(`로그인 실패! ${data.message}`); // 실패 시 경고
            }
        })
        .catch(error => console.error('Error:', error));
    });
    
    // Google login redirection
    document.querySelector('#loginModal .modal-footer button:nth-child(1)').addEventListener('click', googleLogin);

    // Member signup redirection
    document.querySelector('#loginModal .modal-footer button:nth-child(2)').addEventListener('click', memberSignup);
});

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
    window.location.href = "/user/public/signup";
}
