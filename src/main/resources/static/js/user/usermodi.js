document.addEventListener('DOMContentLoaded', function() {
    const apiUrl = `/api/public/user/userinfo/${userId}`;
    const updateUrl = `/api/user/usermodi/${userId}`;

    // 사용자 정보를 가져오기 위한 함수
    function getUserInfo() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                // 입력 필드에 값을 설정
                document.querySelector('.nickname').value = data.nickname;
                document.querySelector('.email').value = data.email;

                // 비밀번호가 null이 아닌 경우에만 필드를 표시하고 이메일 필드도 수정 가능하게 설정
                if (data.password) {
                    document.querySelector('.password').value = data.password;
                    document.querySelector('.password_row').style.display = 'table-row';
                } else {
                    // 비밀번호가 null인 경우 이메일 필드를 readonly로 설정
                    document.querySelector('.email').readOnly = true;
                }

                // 수정 버튼을 보여줄 조건
                if (data.id == userId) {
                    document.querySelector('.btn_zone').style.display = 'block';
                }
            })
            .catch(error => console.error('Error fetching user data:', error));
    }

    // 사용자 정보를 업데이트하기 위한 함수
    function updateUserInfo() {
        const updatedUser = {
            nickname: document.querySelector('.nickname').value,
            email: document.querySelector('.email').value,
            password: document.querySelector('.password').value || null  // password가 null인 경우도 고려
        };

        fetch(updateUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedUser)
        })
        .then(response => {
            return response.json().then(data => ({ status: response.ok, data }));
        })
        .then(result => {
            alert(result.data.message);  // 서버에서 반환된 메시지를 alert로 표시
            if (result.status) {
                window.location.href = "/user/public/info"; 
            }
        })
        .catch(error => console.error('Error updating user data:', error));
    }

    // 페이지 로드 시 사용자 정보 가져오기
    getUserInfo();

    // 수정 버튼 클릭 이벤트 리스너 추가
    document.getElementById('modify').addEventListener('click', function() {
        updateUserInfo();
    });
});