// src/main/resources/static/js/main.js

document.addEventListener("DOMContentLoaded", function() {
    console.log("DOM fully loaded and parsed");
    fetch('/api/list')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("Data fetched successfully:", data);
            const postList = document.getElementById('post-list');
            data.forEach(post => {
                const postElement = document.createElement('div');
                postElement.classList.add('post');

                const titleElement = document.createElement('h2');
                titleElement.textContent = post.postTitle;

                const authorElement = document.createElement('p');
                authorElement.textContent = `By ${post.author}`;

                const contentElement = document.createElement('p');
                contentElement.textContent = post.postContent;

                postElement.appendChild(titleElement);
                postElement.appendChild(authorElement);
                postElement.appendChild(contentElement);

                postList.appendChild(postElement);
            });
        })
        .catch(error => console.error('Error fetching post data:', error));
});
