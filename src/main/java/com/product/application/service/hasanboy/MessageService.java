package com.product.application.service.hasanboy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
    //private MailSender mailSender;

    public void send(String to, String title, String contact) {
      /*  SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(contact);
        mailSender.send(mailMessage);*/
    }

}
