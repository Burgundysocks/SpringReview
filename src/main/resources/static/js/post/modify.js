$(document).ready(function() {
    // 썸머노트 초기화
    $('#summernote').summernote({
        height: '100%',              // 에디터 높이를 100%로 설정
        minHeight: 850,             // 최소 높이 설정 해제
        maxHeight: 850,             // 최대 높이 설정 해제
        focus: true,               // 에디터 로딩 후 포커스를 맞출지 여부
        lang: "ko-KR",             // 한글 설정
        placeholder: '최대 2048자까지 쓸 수 있습니다', // placeholder 설정
        toolbar: [                 // Summernote의 툴바 설정
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
            ['color', ['forecolor', 'color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert', ['picture', 'link', 'video']],
            ['view', ['fullscreen', 'help']]
        ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체'],
        fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
        callbacks: {
            onImageUpload: function(files) {
                for (let i = 0; i < files.length; i++) {
                    imageUploader(files[i], this);
                }
            }
        }
    });    
    const urlPath = window.location.pathname;
    const postId = urlPath.split("/").pop(); // URL 경로에서 postId 추출
    
    if (!postId) {
        console.error('아이디 없음');
        alert('게시물 조회 실패');
        return;
    }

    // Fetch post data from API
    fetch(`/api/public/posts/${postId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
       
            document.getElementById('postTitle').value = data.postTitle; 
           
            const noteEditableElements = document.getElementsByClassName('note-editable');

            // 만약 요소가 하나만 있다면, 첫 번째 요소를 선택하여 값을 설정합니다.
            if (noteEditableElements.length > 0) {
                noteEditableElements[0].innerHTML = data.postContent;
            }
        })
        .catch(error => console.error('Error:', error));
     
    // 이미지 업로드 함수
    function imageUploader(file, editor) {
        let data = new FormData();
        data.append('file', file);

        $.ajax({
            data: data,
            type: 'POST',
            url: '/upload', // 서버에서 이미지를 처리하는 API URL
            cache: false,
            contentType: false,
            processData: false,
            success: function(url) {
                $(editor).summernote('insertImage', url);
            },
            error: function(xhr, status, error) {
                console.error('이미지 업로드 중 오류:', error);
            }
        });
    }

    // 수정 버튼 클릭 시
    $('#submitPost').on('click', function() {
        const postTitle = $('#postTitle').val(); // 제목 입력값
        const contentEditableDiv = document.querySelector('.note-editable');
        const postContent = contentEditableDiv.innerHTML;

        console.log('Post Title:', postTitle); // 제목 확인
        console.log('Post Content:', postContent); // 썸머노트 내용 확인

        // 사용자 ID는 실제 사용자 ID로 대체
        const userIdElement = document.getElementById('userId');
        const userId = userIdElement.textContent.trim();

        console.log('User ID:', userId);

        const postDTO = {
            postId : postId,
            postTitle: postTitle,
            postContent: postContent,
            userId: userId
        };
        


        console.log('Sending data:', postDTO); // 데이터 확인용 로그

        $.ajax({
            url: `/api/user/posts/${postId}`,
            type: 'PUT',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json', // JSON 형식으로 응답을 처리
            data: JSON.stringify(postDTO),
            success: function(response) {
                alert(response.message); // JSON 응답 처리
                window.location.href = `/board/public/get/${postId}`; 
            },
            error: function(xhr, status, error) {
                console.error('게시글 저장 중 오류:', xhr.status, xhr.statusText, xhr.responseText);
                alert('게시글 저장 중 오류가 발생했습니다.');
            }
        });
    });
    $('#back').on('click', function() {
        window.location.href = '/board/public/list';
    });
});
