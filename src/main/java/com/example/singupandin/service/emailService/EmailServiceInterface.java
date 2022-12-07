package com.example.singupandin.service.emailService;

import com.example.singupandin.io.entities.EmailDetails;

public interface EmailServiceInterface {
    void sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
