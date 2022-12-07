package com.example.singupandin.service.emailService;

import com.example.singupandin.io.entities.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailServiceInterface{


    @Autowired
    private JavaMailSenderImpl javaMailSender;




    @Override
    public void sendSimpleMail(EmailDetails details) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("amineelkahir@gmail.com");
        simpleMailMessage.setTo(details.getRecipient());
        simpleMailMessage.setSubject(details.getSubject());
        simpleMailMessage.setText(details.getMsgBody());
       javaMailSender.send(simpleMailMessage);
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        return null;
    }
}
