document.addEventListener('DOMContentLoaded', function() {

    const apiUrl = `/api/public/user/userinfo/${userId}`;

    // 사용자 정보를 가져오기 위한 함수
    function getUserInfo() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                // 입력 필드에 값을 설정
                document.querySelector('.nickname input').value = data.nickname;
                document.querySelector('.email input').value = data.email;

               
                document.querySelector('.password input').value = data.password || '';

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