document.addEventListener('DOMContentLoaded', function() {
    const fetchPosts = (page = 1, pageSize = 10) => {
        fetch(`/api/public/posts/list?page=${page}&pageSize=${pageSize}`)
            .then(response => response.json())
            .then(data => {
                console.log('Received data:', data);  // 데이터 확인용 로그
                
                const { posts, page, pageSize, totalPages } = data;
                const postList = document.getElementById('post_list');
                postList.innerHTML = ''; // 기존 포스트 리스트 초기화

                // Ensure pt_top remains visible
                const ptTop = document.createElement('div');
                ptTop.classList.add('pt_top');
                ptTop.innerHTML = '<p>게시판</p>';
                postList.appendChild(ptTop);

                if (posts && posts.length > 0) {
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
                } else {
                    postList.innerHTML = '<p>게시물이 없습니다.</p>';
                }

                renderPagination(page, totalPages, pageSize);
            })
            .catch(error => {
                console.error('Error fetching posts:', error);
            });
    };

    const renderPagination = (currentPage, totalPages, pageSize) => {
        const pagination = document.querySelector('.pagenation');
        if (pagination) {
            pagination.innerHTML = '';

            for (let i = 1; i <= totalPages; i++) {
                const pageLink = document.createElement('a');
                pageLink.href = '#';
                pageLink.textContent = i;
                pageLink.classList.add('page-link');
                if (i === currentPage) {
                    pageLink.classList.add('active');
                }
                pageLink.addEventListener('click', function(e) {
                    e.preventDefault();
                    fetchPosts(i, pageSize);
                });
                pagination.appendChild(pageLink);
            }
        } else {
            console.error('Pagination element not found');
        }
    };

    // 초기 로드 시 첫 페이지 호출
    fetchPosts();
});
