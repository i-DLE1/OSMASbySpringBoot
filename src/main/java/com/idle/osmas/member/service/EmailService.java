package com.idle.osmas.member.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    public String mailSend(String email) throws MessagingException, UnsupportedEncodingException;

    public String selectIdByEmail(String email) throws MessagingException, UnsupportedEncodingException;
}
