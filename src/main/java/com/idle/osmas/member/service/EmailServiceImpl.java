package com.idle.osmas.member.service;

import java.util.Random;

public class EmailServiceImpl {

    private final JavaMailSenderImpl JavaMailSenderImpl;
    public String mailSend(String email){
        MailHandler mailHandler = new MailHandler(JavaMailSenderImpl);

        int result = (int)((Math.random()*9000))+1000; // 인증번호

        mailHandler.setTo(email);

        mailHandler.setFrom("uwill1523@naver.com");

        mailHandler.setSubject("OSMAS 이메일 인증번호입니다.");

        String htmlContent = "<p>인증번호 : " + result+"<p>";

        mailHandler.setText(htmlContent, true);

        mailHandler.send();

        return result+"";


    }
}
