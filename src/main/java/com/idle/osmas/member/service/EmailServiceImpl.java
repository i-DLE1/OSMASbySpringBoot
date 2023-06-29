package com.idle.osmas.member.service;


import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage.RecipientType;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;
@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    private static final String ADMIN_ADDRESS = "asdq1523@naver.com";
    private static final String ADMIN_NAME = "HEOYUIL";
    @Override
    public String mailSend(String email) throws MessagingException, UnsupportedEncodingException {
        System.out.println("메일 보내기 전");
        int result = (int) ((Math.random() * 9000)) + 1000; // 인증번호
        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(RecipientType.TO,email);
        message.setSubject("OSMAS 회원가입 이메일 인증");
        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> OSMAS입니다</h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += result + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msgg += "</div>";
        message.setText(msgg,"utf-8","html");
        message.setFrom(new InternetAddress(ADMIN_ADDRESS,ADMIN_NAME));
        try{
            mailSender.send(message);
        }catch (MailException e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        System.out.println("메일 보내기 완");
        return result+"";
    }
}
