/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmailService;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HP
 */

public class EmailService{
   
    
    public static Boolean sendFromGMail(String toEmail, String subject, String body) {
        // TODO code application logic here
        String host="smtp.gmail.com";  
        final String user="doubt.cart@gmail.com";//change accordingly  
        final String password="vufwfvnknviccuiz";//change accordingly  

        String to=toEmail;//change accordingly  

   //Get the session object  
   Properties p = new Properties();
    p.put("mail.smtp.auth", "true");
    p.put("mail.transport.protocol", "smtp");
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "587");
    p.put("mail.smtp.starttls.enable","true");
    p.put("mail.smtp.starttls.required","true"); 

   Session session = Session.getInstance(p,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  

   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject(subject);  
     message.setText(body);  

    //send the message  
     //Transport.send(message);  
    Transport transport = session.getTransport("smtp");
    transport.connect("smtp.gmail.com", user, password);
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();

     System.out.println("message sent successfully..."); 
     return true;

     } catch (MessagingException e) {e.printStackTrace(); return false;} 
    }
}
