// $("#idCheck").click(function () {
//     const id = $("#id").val();
//     $.ajax({
//         url: "/member/idDupCheck",
//         type: "post",
//         data: {id: id},
//         success: function (data) {
//             alert(data)
//             if (data == '중복된 아이디가 있습니다') {
//                 $('#id').val('');
//             }
//         },
//         error: function (error) {
//             alert(error)
//         }
//     });
// });
//
// $("#nicknameCheck").click(function () {
//     const nickname = $("#nickname").val();
//     $.ajax({
//         url: "/member/nickDupCheck",
//         type: "post",
//         data: {nickname: nickname},
//         success: function (data) {
//             alert(data)
//             if (data == '중복된 닉네임 있습니다') {
//                 $('#id').val('');
//             }
//         },
//         error: function (error) {
//             alert(error)
//         }
//     });
// });
