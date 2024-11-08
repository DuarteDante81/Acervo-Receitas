package com.example.loginauthapi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String Remetente;

    public String eviarEmailText(String destinatario, String assunto, String menssagem){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(Remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(menssagem);
            javaMailSender.send(simpleMailMessage);
            return "Email enviado bb!";
        } catch (Exception e) {
            return "Error ao enviar o Email!"+ e.getLocalizedMessage();
        }
    } 
}
