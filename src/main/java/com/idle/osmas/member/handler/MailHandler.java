//package com.idle.osmas.member.handler;
//
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//public class MailHandler {
//    private JavaMailSender sender;
//    private MimeMessage message;
//    private MimeMessageHelper messageHelper;
//
//    public MailHandler(JavaMailSender jSender) throws MessagingException {
//        this.sender = jSender;
//        message = jSender.createMimeMessage();
//        messageHelper = new MimeMessageHelper(message, true,"UTF-8");
//    }
//    // 보내는 사람 이메일
//    public void setFrom(String address) throws MessagingException {
//        messageHelper.setFrom(address);
//    }
//
//    // 받는 사람 이메일
//    public void setTo(String email) throws MessagingException {
//        messageHelper.setTo(email);
//    }
//    // 제목
//    public void setSubject(String subject) throws MessagingException {
//        messageHelper.setSubject(subject);
//    }
//    //
//    public void setText(String text, boolean useHtml) throws MessagingException {
//        messageHelper.setText(text, useHtml);
//    }
//
//    public void send(){
//        try {
//            sender.send(message);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}
