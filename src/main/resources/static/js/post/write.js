$(document).ready(function() {
    // 썸머노트 초기화
    $('#summernote').summernote({
        height: '100%',
        minHeight: 850,
        maxHeight: 850,
        focus: true,
        lang: "ko-KR",
        placeholder: '최대 2048자까지 쓸 수 있습니다',
        toolbar: [
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

    // 등록 버튼 클릭 시
    $('#submitPost').on('click', function() {
        const postTitle = $('#postTitle').val();
        const contentEditableDiv = document.querySelector('.note-editable');
        const postContent = contentEditableDiv.innerHTML;

        const userIdElement = document.getElementById('userId');
        const userId = userIdElement.textContent.trim();

        const postDTO = {
            postTitle: postTitle,
            postContent: postContent,
            userId: userId
        };

        console.log('Sending data:', postDTO); // 요청 데이터를 로그로 출력

        $.ajax({
            url: '/api/user/posts',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(postDTO),
            success: function(response) {
                const postId = response; // 서버 응답에서 postId 추출
                console.log(postId);
                alert('게시글이 성공적으로 저장되었습니다.');
                window.location.href = `/board/public/recent/${postDTO.userId}`;
            },
            error: function(xhr, status, error) {
                console.error('게시글 저장 중 오류:', error);
                console.error('Error details:', xhr.responseText); // 서버 응답 메시지 로그 출력
                alert('게시글 저장 중 오류가 발생했습니다.');
            }
        });
    });

    $('#back').on('click', function() {
        window.location.href = '/board/public/list';
    });
});
