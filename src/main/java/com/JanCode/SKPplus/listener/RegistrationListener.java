package com.JanCode.SKPplus.listener;

import com.JanCode.SKPplus.event.OnRegistrationCompleteEvent;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserService userService;


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        String recipientAddress = user.getEmail();
        String subject = "PTHW+ Potwierdzenie rejestracji";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = ("Rejestracja prawie ukończona!\nAby potwierdzić założenie nowego konta, kliknij w link poniżej:");

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
