
const modal = document.getElementById("modal");
const closeModalBtn = document.getElementById("close-modal");
closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
    document.body.style.overflow = "auto"; // 스크롤바 보이기
    $('.button_modify').text('수정');
    $('#content').attr('readonly', true);
    $('#title').attr('readonly', true);
});

const modal2 = document.getElementById("modal2");
const openModalBtn = document.getElementById("open-modal");
const closeModalBtn2 = document.getElementById("close-modal2");
openModalBtn.addEventListener("click", () => {
    modal2.style.display = "block";
    document.body.style.overflow = "hidden"; // 스크롤바 제거
});
closeModalBtn2.addEventListener("click", () => {
    modal2.style.display = "none";
    document.body.style.overflow = "auto";
});

$('.button_write').click(function (){
    let title = $('#title2').val();
    let content = $('#content2').val();
    let name = $('#category').val();
    if (title.trim().length == 0 || content.trim().length == 0) {
        alert("제목과 내용을 빈공간을 채워주세요")
    }else{
        $.ajax({
            url: "/member/suggest/write",
            type: "post",
            data:{title,content,name},
            success: function (data){
                alert(data)
                modal2.style.display = "none";
                document.body.style.overflow = "auto";
                location.reload();
            }, error: function (error){
                alert(error)
                modal2.style.display = "none";
                document.body.style.overflow = "auto";
            }
        });
    }
});

if (document.querySelectorAll(".suggest_title")) {
    const $suggest = document.querySelectorAll(".suggest_title");
    for (let i = 0; i < $suggest.length; i++) {
        $suggest[i].onclick = function () {
            let no = this.parentNode.children[0].innerHTML;
            let title = this.parentNode.children[2].innerHTML;
            let nickname = this.parentNode.children[4].innerHTML;
            $.ajax({
                url: "/member/suggest/suggest",
                type: "post",
                data: {no, no},
                success: function (data) {
                    modal.style.display = "block";
                    document.body.style.overflow = "hidden";
                    $('#title').val(title);
                    $('#content').val(data);
                    $('#no').val(no);
                    $('#nickname').val(nickname);
                },
                error: function (error) {
                    alert(error)
                }
            });
        }
    }
}

$('.button_remove').click(function () {
    let no = $('#no').val();
    let nickname = $('#nickname').val();
    $.ajax({
        url: "/member/suggest/remove",
        type: "post",
        data: {no, nickname},
        success: function (data) {
            location.reload();
            alert(data);
        },
        error: function (error) {
            alert(error)
        }
    });
});

$('.button_modify').click(function () {
    if ($('.button_modify').text() == '등록') {
        let content = $('#content').val();
        let title = $('#title').val();
        let no = $('#no').val();
        let nickname = $('#nickname').val();
        $.ajax({
            url: "/member/suggest/modify",
            type: "post",
            data: {content, title, no, nickname},
            success: function (data) {
                $('.button_modify').text('수정');
                $('#content').attr('readonly', true);
                $('#title').attr('readonly', true);
                location.reload();
                alert(data);
            }, error: function (error) {
                $('.button_modify').text('수정');
                $('#content').attr('readonly', true);
                $('#title').attr('readonly', true);
                alert(error);
            }
        });
    }
    $('.button_modify').text('등록');
    $('#content').removeAttr('readonly');
    $('#title').removeAttr('readonly');
});
