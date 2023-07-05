// const modal = document.getElementById("modal");
// const closeModalBtn = document.getElementById("close-modal");
// closeModalBtn.addEventListener("click", () => {
//     modal.style.display = "none";
//     document.body.style.overflow = "auto"; // 스크롤바 보이기
//     $('.button_modify').text('수정');
//     $('#content').attr('readonly', true);
//     $('#title').attr('readonly', true);
// });
// // 리뷰 선택시 리뷰내용 보여줌
// if (document.querySelectorAll(".review")) {
//     const $review = document.querySelectorAll(".review");
//     for (let i = 0; i < $review.length; i++) {
//         $review[i].onclick = function () {
//             let no = this.children[0].innerText;
//             let title = this.children[2].innerText;
//             let nickname = this.children[3].children[1].innerText;
//             $.ajax({
//                 url: "/member/review/review",
//                 type: "post",
//                 data: {no, no},
//                 success: function (data) {
//                     modal.style.display = "block";
//                     document.body.style.overflow = "hidden"; // 스크롤바 제거
//                     $('#title').val(title);
//                     $('#content').val(data);
//                     $('#no').val(no);
//                     $('#nickname').val(nickname);
//                 },
//                 error: function (error) {
//                     alert(error)
//                 }
//             });
//         }
//     }
// }
//
// function modify() {
//     if ($('.button_modify').text() == '등록') {
//         let content = $('#content').val();
//         let title = $('#title').val();
//         let no = $('#no').val();
//         let nickname = $('#nickname').val();
//         $.ajax({
//             url: "/member/review/modify",
//             type: "post",
//             data: {content, title, no, nickname},
//             success: function (data) {
//                 $('.button_modify').text('수정');
//                 $('#content').attr('readonly', true);
//                 $('#title').attr('readonly', true);
//                 alert(data);
//             }, error: function (error) {
//                 $('.button_modify').text('수정');
//                 $('#content').attr('readonly', true);
//                 $('#title').attr('readonly', true);
//                 alert(error);
//             }
//         });
//     }
//     $('.button_modify').text('등록');
//     $('#content').removeAttr('readonly');
//     $('#title').removeAttr('readonly');
// }
//
// $('.button_remove').click(function () {
//     let no = $('#no').val();
//     let nickname = $('#nickname').val();
//     $.ajax({
//         url: "/member/review/remove",
//         type: "post",
//         data: {no, nickname},
//         success: function (data) {
//             modal.style.display = "none";
//             document.body.style.overflow = "auto"; // 스크롤바 보이기
//             location.reload();
//             alert(data)
//         }, error: function (error) {
//             alert(error)
//         }
//     });
// })