document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/public/posts/list')
        .then(response => response.json())
        .then(posts => {
            const postList = document.getElementById('post_list');
            posts.forEach(post => {
                const postDiv = document.createElement('div');
                postDiv.classList.add('post');
                postDiv.innerHTML = `
                    <a href="/board/public/get/${post.postId}">
                        <div class="pt_number">
                            <h3>${post.postId}</h3>
                        </div>
                        <div class="pt_title">
                            <h3>${post.postTitle}</h3>
                        </div>
                        <div class="writer">
                            <h3>${post.nickname}</h3>
                        </div>
                        <div class="create_time">
                            <h4>${post.createAt}</h4>
                        </div>
                        <div class="pt_recommand">
                            <p>추천수 : ${post.likeCnt}</p>
                        </div>
                        <div class="view_cnt">
                            <p>조회수 : ${post.viewCnt}</p>
                        </div>
                    </a>
                `;
                postList.appendChild(postDiv);
            });
        });
});