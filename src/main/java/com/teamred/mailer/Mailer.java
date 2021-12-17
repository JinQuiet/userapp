package com.teamred.mailer;

import org.quartz.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Transport;
import java.io.File;
import java.util.Properties;

import static javax.mail.Part.ATTACHMENT;

public class Mailer implements Job{

    Authenticator auth = new Authenticator() {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("yourEmailId", "password"); //Почта, с которой отправляем
        }
    };
    Session session = Session.getInstance(properties, auth);
    Message message = new MimeMessage(session);
    File report = new File("Report.pdf");

    static Properties properties = new Properties();
    static {
        //указать правильные проперти
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
    }


    //Возможно нужно докрутить реализацию
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            //Параметры сообщения
            message.setFrom(new InternetAddress("yourEmailId"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("recepeientMailId"));
            message.setSubject("Red Team Report");
            message.setText("Here's daily report on Red Team activity!");
            attachFile(report);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void attachFile(File file) throws MessagingException {
        FileDataSource fds = new FileDataSource(file);
        message.setDataHandler(new DataHandler(fds));
        message.setFileName(fds.getName());
        message.setDisposition(ATTACHMENT);
    }
}
