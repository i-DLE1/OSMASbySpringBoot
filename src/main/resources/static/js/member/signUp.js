function eamilOk(){
    const emailNum = $('#emailNum').val();
    const emailCheck = $('#emailCheck').val();
    if(emailNum == emailCheck){
        $('#emailNum-error').text('인증에 성공했습니다');
        $('#signupBtn').removeAttr('disabled')
    }else{
        $('#emailNum-error').text('인증에 실패했습니다');
        $('#emailNum').val('');
        $('#emailNum').focus();
    }
}
// id중복 및 유효성 검사
function idReg() {
    const id = $("#id").val();
    let idCheck = /^[a-zA-Z0-9]{5,20}$/;
    if (!idCheck.test(id)) {
        $('#id-error').text("아이디는 영문또는 숫자로 5자~20자로 입력해주세요.");
        // alert("아이디는 영문또는 숫자로 5자~20자로 입력해주세요.")
        $("#id").val('');
        $("#id").focus();
    } else {
        $.ajax({
            url: "/member/idDupCheck",
            type: "post",
            data: {id: id},
            success: function (data) {
                $('#id-error').text(data);
            },
            error: function (error) {
                $('#id-error').text(error.responseJSON.message);
                $('#id').val('');
                $("#id").focus();
            }
        });
    }
}
function emailReg(){
    const email = $('#email').val();
    let emailCheck =  /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    if(!emailCheck.test(email)){
        $('#email-error').text('다시 입력해주세요');
        $('#email').val('');
        $('#email').focus();
    }else{
        $.ajax({
            url: "/member/emailDupCheck",
            type: "post",
            data: {email: email},
            success: function (data) {
                $('#email-error').text(data);
            },
            error: function (error) {
                $('#email-error').text(error.responseJSON.message);
                $('#email').val('');
                $("#email").focus();
            }
        });
    }

}
function nickReg() {
    const nickname = $("#nickname").val();
    let nickCheck = /^[가-힣]{2,8}$/;
    if (!nickCheck.test(nickname)) {
        $('#nickname-error').text("닉네임은 한글로 2~8자로 입력해주세요.");
        $("#nickname").val('');
        $("#nickname").focus();
    }else{
        $.ajax({
            url: "/member/nickDupCheck",
            type: "post",
            data: {nickname: nickname},
            success: function (data) {
                $('#nickname-error').text(data);
            },
            error: function (error) {
                $('#nickname-error').text(error.responseJSON.message);
                $('#nickname').val('');
                $("#nickname").focus();
            }
        });
    }
}
function  pwdReg(){
    const pwd = $('#pwd').val();
    let pwdCheck = /^[0-9a-zA-Z]{6,15}$/;
    if(!pwdCheck.test(pwd)){
        $('#pwd-error').text('비밀번호는 영문,숫자 혼합하여 8자~15자로 입력해주세요.');
        $('#pwd').val('');
        $('#pwd').focus();
    }else{
        $('#pwd-error').text('');
    }
}
function nameReg(){
    const name = $('#name').val();
    let nameCheck = /^[가-힣]{2,4}$/;
    if(!nameCheck.test(name)){
        $('#name-error').text('이름을 다시 입력해주세요.');
        $('#name').val('');
        $('#name').focus();
    }else{
        $('#name-error').text('');
    }
}
function phoneReg(){
    const phone = $('#phone').val();
    let phoneCheck = /^\d{2,3}-\d{3,4}-\d{4}$/;
    if(!phoneCheck.test(phone)){
        $('#phone-error').text('전화번호를 다시 입력해주세요.');
        $('#phone').val('');
        $('#phone').focus();
    }else{
        $('#phone-error').text('');
    }
}
function birthReg(){
    const birth = $('#birth').val();
    let birthCheck = /^(19[0-9][0-9]|20\d{2})(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
    if(!birthCheck.test(birth)){
        $('#birth-error').text('생년월일은 8자리 숫자로 입력해주세요.');
        $('#birth').val('');
        $('#birth').focus();
    }else{
        $('#birth-error').text('');
    }
}

function emailSend(){
    const email = $('#email').val();
    $.ajax({
        url:"/member/email",
        type: "post",
        data:{email : email},
        success : function (data){
            $('#email-error').text('이메일에 인증번호를 보냈습니다');
            $('#emailCheck').val(data);
        },error : function (error){
            $('#email-error').text('이메일을 다시 확인해주세요');
        }

    });

}