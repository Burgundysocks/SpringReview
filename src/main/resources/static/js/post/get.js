document.addEventListener("DOMContentLoaded", function() {
    const urlPath = window.location.pathname;
    const postId = urlPath.split("/").pop(); // URL 경로에서 postId 추출

    if (!postId) {
        console.error('아이디 없음');
        alert('게시물 조회 실패');
        return;
    }

    // 게시물 정보 가져오기
    fetch(`/api/public/posts/${postId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('nicknameText').textContent = data.nickname;
            document.getElementById('postTitle').textContent = data.postTitle;
            document.getElementById('createAt').textContent = data.createAt;
            document.getElementById('postContent').innerHTML = data.postContent;

            document.getElementById('modify').addEventListener('click', function() {
                window.location.href = `/board/user/modify/${postId}`;
            });

            document.getElementById('delete').addEventListener('click', function() {
                fetch(`/api/user/posts/${postId}`, {
                    method: 'DELETE',
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('삭제 실패');
                        }
                        return response.text();
                    })
                    .then(result => {
                        alert('삭제 성공');
                        window.location.href = '/board/public/list';
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('삭제 실패');
                    });
            });

        })
        .catch(error => console.error('Error:', error));
});