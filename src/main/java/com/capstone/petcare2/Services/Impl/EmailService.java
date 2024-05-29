package  com.capstone.petcare2.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.capstone.petcare2.DTO.EmailDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    public void sendEmail(EmailDetails emailDetails){

        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailSender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getSubject());
            mailMessage.setSubject(emailDetails.getMessageBody());

            javaMailSender.send(mailMessage);
            log.info("mail sent to: {}",emailDetails.getRecipient());
            log.info("mail Sender: {}",emailSender);
        } catch(MailException e){
            throw new RuntimeException(e);
        }

    }

    
}
