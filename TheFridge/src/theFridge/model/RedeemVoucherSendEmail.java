package theFridge.model;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javafx.scene.image.Image;

public class RedeemVoucherSendEmail {
	
	public void sendEmail(String recipientEmail, Image text) {
		  // Recipient's email ID needs to be mentioned.
	      String to = recipientEmail;//change accordingly

	      // Sender's email ID needs to be mentioned
	      String from = "theFridge.CustomerHelpdesk@gmail.com";
	      final String username = "theFridge.CustomerHelpdesk@gmail.com";
	      final String password = "00000001";

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

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
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Testing Subject");
	         
	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");
	         
	         // First part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         //String htmlText = "<H1>Here's your voucher. Enjoy!</H1><img src="cid:image"><H3>Sorry if I send to wrong email.</H3>";
	         messageBodyPart.setContent("Something", "text/html");
	         
	         // Add it
	         multipart.addBodyPart(messageBodyPart);
	         
	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource("src/theFridge/picture/Barcode.png");

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");
	         
	         // Add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // Put everything together
	         message.setContent(multipart);

	         // Now set the actual message
	         message.setText("Hello, this is sample for to check send "
	            + "email using JavaMailAPI ");

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
	
	public static void main(String[] args) {
		
	}

}
