document.addEventListener('DOMContentLoaded', function() {
    loadPosts();

    function loadPosts() {
        fetch('/api/list')
            .then(response => response.json())
            .then(data => {
                const postList = document.getElementById('post-list');
                postList.innerHTML = '';
                data.forEach(post => {

                    const row = document.createElement('tr');

                    row.innerHTML = `
                        <td>${post.postId}</td>
                        <td>${post.postTitle}</td>
                        <td>${post.author}</td>
                        <td>${post.createAt}</td>
                        <td><a href="#" onclick="ModifyForm(${post.postId})">수정</a></td>
                        <td><a href="#" onclick="deletePost(${post.postId})">삭제</a></td>
                    `;

                    postList.appendChild(row);
                });
            });
    }

    window.CreateForm = function() {
        const formContainer = document.getElementById('form-container');
        formContainer.innerHTML = `
            <h2>게시물 작성</h2>
            <form id="create-form">
                <label for="postTitle">제목:</label>
                <input type="text" id="postTitle" name="postTitle"><br>
                <label for="author">작성자:</label>
                <input type="text" id="author" name="author"><br>
                <label for="postContent">내용:</label>
                <textarea id="postContent" name="postContent"></textarea><br>
                <button type="submit">작성</button>
            </form>
        `;

        document.getElementById('create-form').addEventListener('submit', function(event) {
            event.preventDefault();
            const postData = {
                postTitle: document.getElementById('postTitle').value,
                author: document.getElementById('author').value,
                postContent: document.getElementById('postContent').value
            };

            fetch('/api/posts', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(postData)
            })
                .then(() => {
                    loadPosts();
                    formContainer.innerHTML = '';
                });
        });
    };

    window.ModifyForm = function(postId) {
        fetch(`/api/${postId}`)
            .then(response => response.json())
            .then(post => {
                const formContainer = document.getElementById('form-container');
                formContainer.innerHTML = `
                    <h2>게시물 수정</h2>
                    <form id="modify-form">
                        <input type="hidden" id="postId" name="postId" value="${post.postId}">
                        <label for="postTitle">제목:</label>
                        <input type="text" id="postTitle" name="postTitle" value="${post.postTitle}"><br>
                        <label for="author">작성자:</label>
                        <input type="text" id="author" name="author" value="${post.author}"><br>
                        <label for="postContent">내용:</label>
                        <textarea id="postContent" name="postContent">${post.postContent}</textarea><br>
                        <button type="submit">수정</button>
                    </form>
                `;

                document.getElementById('modify-form').addEventListener('submit', function(event) {
                    event.preventDefault();
                    const postData = {
                        postId: document.getElementById('postId').value,
                        postTitle: document.getElementById('postTitle').value,
                        author: document.getElementById('author').value,
                        postContent: document.getElementById('postContent').value
                    };

                    fetch(`/api/${postId}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(postData)
                    })
                        .then(() => {
                            loadPosts();
                            formContainer.innerHTML = '';
                        });
                });
            });
    };

    window.deletePost = function(postId) {
        fetch(`/api/${postId}`, {
            method: 'DELETE'
        })
            .then(() => {
                loadPosts();
            });
    };
});
