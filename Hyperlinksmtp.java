package com.Dhanush.net;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Hyperlinksmtp
{
   public static void main(String[] args)
   {
      // Recipient's email ID needs to be mentioned.
      String to = "saivarma.akarapu@dhanushinfotech.net";

      final String username = "";//change accordingly
      final String password = "";//change accordingly
      SendingEmail(to,username,password);
   }

public static void SendingEmail(String to,String username,String password)
{

	Properties props = System.getProperties();  

	props.setProperty("mail.smtp.host", "smtp.gmail.com"); 
	
	props.put("mail.smtp.auth", "true"); 
	
	props.put("mail.smtp.starttls.enable", "true");

// Get the Session object.
	Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
	});

	try {
      // Create a default MimeMessage object.
		Message message = new MimeMessage(session);

	   // Set From: header field of the header.
		message.setFrom(new InternetAddress(username));

 // Set To: header field of the header.
		message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(to));

 // Set Subject: header field
		message.setSubject("Testing Subject");
 
		 String url="c:/a.txt";

 
		String content="<a href='"+url+"'>"+"Click on this link"+"</a>";
 
		message.setContent("File:"+content,"text/html; charset=utf-8");

		Transport.send(message);

		System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
