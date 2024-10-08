document.addEventListener('DOMContentLoaded', function() {

    const apiUrl = `/api/public/user/userinfo/${userinfoId}`;

    // 사용자 정보를 가져오기 위한 함수
    function getUserInfo() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                document.querySelector('.nickname').textContent = '닉네임: ' + data.nickname;
                document.querySelector('.email').textContent = '이메일: ' + data.email;

                // 현재 로그인된 사용자와 가져온 사용자 정보가 동일할 경우 수정 버튼 표시
                if (data.id == userId) {
                    document.querySelector('.modi_btn').style.display = 'block';
                }
            })
            .catch(error => console.error('Error fetching user data:', error));
    }

    // 페이지 로드 시 사용자 정보 가져오기
    getUserInfo();
});