package com.Dhanush.net;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailwithlatestCreated {

	public static void main(String[] args) {
		 String to = "saivarma.akarapu@dhanushinfotech.net";

	      final String username = "";//change accordingly
	      final String password = "";//change accordingly
	      sendEmail(to,username,password);
	}
	public static void sendEmail(String to,String username,String password)
	{
		
		Properties props = System.getProperties();  

		props.setProperty("mail.smtp.host", "smtp.gmail.com"); 
		
		props.put("mail.smtp.auth", "true"); 
		
		props.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
		});
		
		
		File fl = new File("D:/");
		File[] files = fl.listFiles(File::isFile);

		
		FileTime lastCreated = null;
		File choice = null;
		
		for (File file : files) {
			BasicFileAttributes attr = null;
			try {
				attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			} catch (Exception e) {
				System.out.println(e);
			}

			if (lastCreated == null)
				lastCreated = attr.creationTime();

			if (attr != null && attr.creationTime().compareTo(lastCreated) !=0 && attr.creationTime().compareTo(lastCreated)==1 ) {
				lastCreated = attr.creationTime();
				choice = file;
			}
		}
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
				String url = choice.getAbsolutePath();
				String content = "<a href='" + url + "'>" + "Click on this link" + "</a>";
				 
				message.setContent(content,"text/html; charset=utf-8");

				Transport.send(message);

				System.out.println("Sent message successfully....");

				} catch (MessagingException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
		
	}
}
