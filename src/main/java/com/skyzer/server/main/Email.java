package com.skyzer.server.main;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.skyzer.server.main.bean.User;

public class Email {

	private Properties props;
	private Session session;
	/** OUTLOOK */
	private final String emailUsername = "email@domain.co.nz";
	private final String emailPassword = "*****";
	
	
	/** GMAIL 
	private final String emailUsername = "skyzertms@gmail.com";
	private final String emailPassword = "Skynet123";
	*/
	
	private final String verifierEmail = "jay.solanki@skyzer.co.nz"; // THIS WILL RECEIVE THE EMAIL TO ACTIVE USER
	private String bodyText = "";
    
	
	public Email() {
		props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        /** OUTLOOK */
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        
        
        /** GMAIL 
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        */
        
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(emailUsername, emailPassword);
                   }
                });
	}
	
	public boolean sendSignUpAcknowledgement(String recipient, String username) {
		try {
		 	
	           Message message = new MimeMessage(session);

	           message.setFrom(new InternetAddress(emailUsername, "Skyzer Technologies"));
	           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	           message.setSubject("SKYZER GUIDE | Welcome to Skyzer Guide");
	          
	           BodyPart messageBodyPart = new MimeBodyPart();
	           bodyText = this.acknowledgementEmailBody(username);
	           messageBodyPart.setContent(bodyText, "text/html");
	           Multipart multipart = new MimeMultipart();
	           multipart.addBodyPart(messageBodyPart);
	          
	           message.setContent(multipart);
	           
	           // Email Synchronization 
	           ExecutorService emailExecutor = Executors.newCachedThreadPool();
	           emailExecutor.execute(new Runnable() {
	               @Override
	               public void run() {
	                   try {
	                	   Transport.send(message);
	                   } catch (Exception e) {
	                	   e.printStackTrace();
	                   }
	               }
	           });
	           
	           return true;
	           
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	return false;
	        }
	}
	
	private String acknowledgementEmailBody(String username) {
		return "<p><span style='font-size:16px;font-family:\"Verdana\",sans-serif;color:#333333;background:white;'><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>Dear "+ username +",</span>&nbsp;</span></p>\r\n" + 
				"<p><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>Thank you for joining the Skyzer guide application.</span></p>\r\n" + 
				"<p><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>This app will provide a better experience with your EFTPOS machines.</span></p>\r\n" + 
				"<p><br><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>However, we&apos;ll send you the account activation email to get started soon!</span></p>\r\n" + 
				"<p><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>We appreciate your patience!</span></p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;'>Kind regards,</span><br><span style=\"color: rgb(51, 51, 51); font-family: Verdana, sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">Team Skyzer</span><br><br></p>\r\n" + 
				"<p style='margin:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;'><span style='font-size:12px;font-family:\"Verdana\",sans-serif;color:#97999C;background:white;'>[If you have any questions, please contact us at <span style=\"font-size:12px;font-family:Verdana,sans-serif;color:#97999C;\"><a href=\"mailto:support@skyzer.co.nz?subject=Skyzer\">support@skyzer.co.nz</a> or call us on <a href=\"tel:+64 9 259 0322\" target=\"_self\">+64 9 259 0322</a></span>]</span></p>";
	}

	public boolean sendToSupportToActiveUser(User user) {
		try {
		 	
	           Message message = new MimeMessage(session);

	           message.setFrom(new InternetAddress(emailUsername, "Skyzer Technologies"));
	           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(verifierEmail));
	           message.setSubject("SKYZER GUIDE | Active New User");
	          
	           BodyPart messageBodyPart = new MimeBodyPart();
	           bodyText = this.activeUserEmailBody(user);
	           messageBodyPart.setContent(bodyText, "text/html");
	           Multipart multipart = new MimeMultipart();
	           multipart.addBodyPart(messageBodyPart);
	          
	           message.setContent(multipart);
	           
	           // Email Synchronization 
	           ExecutorService emailExecutor = Executors.newCachedThreadPool();
	           emailExecutor.execute(new Runnable() {
	               @Override
	               public void run() {
	                   try {
	                	   Transport.send(message);
	                   } catch (Exception e) {
	                	   e.printStackTrace();
	                   }
	               }
	           });
	           
	           return true;
	           
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	return false;
	        }
	}
	
	private String activeUserEmailBody(User user) {
		return "<p style='margin-right:0cm;margin-left:0cm;font-size:10px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"color:gray;\">[Note: This is an automated email, please do not reply]</span></p>\r\n" + 
				"<p><span style='font-size:16px;font-family:\\\"Verdana\\\",sans-serif;color:#333333;background:white;'>Hi Team,</span><br><br><span style='font-size:16px;font-family:\\\"Verdana\\\",sans-serif;color:#333333;background:white;'>Please active this following user for SKYZER GUIDE access.</span></p>\r\n" + 
				"<table style=\"border: none;width:637.65pt;margin-left:-.05pt;background:white;border-collapse: collapse;\">\r\n" + 
				"    <tbody>\r\n" + 
				"        <tr>\r\n" + 
				"            <td colspan=\"3\" style=\"width:453.75pt;border:solid windowtext 1.0pt;background:#E2EFDA;padding:0cm 5.4pt 0cm 5.4pt;height:14.4pt;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><strong><span style=\"font-size:19px;color:#333333;\">SKYZER GUIDE User Activation</span></strong></p>\r\n" + 
				"            </td>\r\n" + 
				"            <td style=\"width:183.9pt;border:solid windowtext 1.0pt;border-left:  none;background:#E2EFDA;padding:0cm 5.4pt 0cm 5.4pt;height:14.4pt;box-sizing: border-box;border-image: initial;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:black;\">&nbsp;</span></p>\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:black;background:#E2EFDA;\">Created an account on: "+ new Date()  +"</span></p>\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:black;\">&nbsp;</span></p>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td style=\"width: 66.3pt;border-right: 1pt solid windowtext;border-bottom: 1pt solid windowtext;border-left: 1pt solid windowtext;border-top: none;background: rgb(226, 239, 218);padding: 0cm 5.4pt;height: 14.4pt;box-sizing: border-box;border-image: initial;vertical-align: bottom;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:#333333;\">Username</span></p>\r\n" + 
				"            </td>\r\n" + 
				"            <td style=\"width: 134.8pt;border-top: none;border-left: none;border-bottom: 1pt solid windowtext;border-right: 1pt solid windowtext;background: rgb(226, 239, 218);padding: 0cm 5.4pt;height: 14.4pt;box-sizing: border-box;vertical-align: bottom;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:#333333;\">Email</span></p>\r\n" + 
				"            </td>\r\n" + 
				"            <td style=\"width: 252.65pt;border-top: none;border-left: none;border-bottom: 1pt solid windowtext;border-right: 1pt solid windowtext;background: rgb(226, 239, 218);padding: 0cm 5.4pt;height: 14.4pt;box-sizing: border-box;vertical-align: bottom;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:#333333;\">Division</span></p>\r\n" + 
				"            </td>\r\n" + 
				"            <td style=\"width: 183.9pt;border-top: none;border-left: none;border-bottom: 1pt solid windowtext;border-right: 1pt solid windowtext;background: rgb(226, 239, 218);padding: 0cm 5.4pt;height: 14.4pt;box-sizing: border-box;vertical-align: bottom;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:#333333;\">Status</span></p>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td style=\"width:66.3pt;border:solid windowtext 1.0pt;border-top:  none;padding:0cm 5.4pt 0cm 5.4pt;height:29.65pt;box-sizing: border-box;border-image: initial;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:black;\">"+ user.getUsername() +"</span></p>\r\n" + 
				"            </td>\r\n" + 
				"            <td style=\"width:134.8pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;height:29.65pt;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style=\"font-size:15px;color:#333333;\"><a href=\"mailto:solanki@ga.com\">"+ user.getEmail() +"</a></span></p>\r\n" + 
				"            </td>\r\n" + 
				"            <td style=\"width:252.65pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;height:29.65pt;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;'><span style='font-size:14px;font-family:\"Helvetica\",sans-serif;color:#333333;'>"+ user.getDivision().getDivision() +"</span></p>\r\n" + 
				"            </td>\r\n" + 
				"            <td style=\"width:183.9pt;border-top:none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;height:29.65pt;\">\r\n" + 
				"                <p style='margin-right:0cm;margin-left:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;margin:0cm;text-align:justify;'><strong><span style='font-size:15px;font-family:\"Verdana\",sans-serif;color:black;'>NOT ACTIVE</span></strong></p>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"    </tbody>\r\n" + 
				"</table>\r\n" + 
				"<p><a href=\"http://10.63.192.13:8080/MyTask/\" style=\"user-select: auto; font-family: Verdana, sans-serif; font-size: 15px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\">Click here to active this user</a><br><br>\r\n" + 
				"<p style='margin:0cm;font-size:15px;font-family:\\\"Verdana\\\",sans-serif;background:white;'><span style='font-size:16px;font-family:\\\"Verdana\\\",sans-serif;color:#333333;'>Kind regards,</span></p>\r\n" + 
				"<p style='margin:0cm;font-size:15px;font-family:\\\"Verdana\\\",sans-serif;background:white;'><span style='font-size:16px;font-family:\\\"Verdana\\\",sans-serif;color:#333333;'>Team Skyzer</span></p>" +
				"<p><br></p>\r\n" + 
				"<p><br></p>" +
				"<p style='margin-right:0cm;margin-left:0cm;font-size:9px;font-family:\"Verdana\",sans-serif;margin:0cm;text-align:center;'><span style=\"color:gray;\">Designed &amp; Maintained by <a href=\"https://www.skyzer.co.nz/\">Skyzer Technologies</a></span></p>";
	}
	
	public boolean sendNitroLink(String recipient, String packageName, String packageLink, String size) {
		try {

			Message message = new MimeMessage(session);

			System.out.println(emailUsername );
			System.out.println(emailPassword );
			message.setFrom(new InternetAddress(emailUsername, "Skyzer Technologies"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("SKYZER GUIDE | NITRO Package");

			BodyPart messageBodyPart = new MimeBodyPart();
			bodyText = this.sendNitroLinkEmailBody(packageName, packageLink, size);
			messageBodyPart.setContent(bodyText, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			// Email Synchronization
			ExecutorService emailExecutor = Executors.newCachedThreadPool();
			emailExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Transport.send(message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String sendNitroLinkEmailBody(String packageName, String packageLink, String packageSize) {
		return "<p><span style='font-size:16px;font-family:\"Verdana\",sans-serif;color:#333333;background:white;'>Dear Customer,</span></p>\r\n" + 
				"<p style='margin:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;'><span style='font-size:16px;font-family:\"Verdana\",sans-serif;color:#333333;background:white;'>Please download the latest NITRO package with one simple click on the button to start your Point of Sale integration experience.</span></p>\r\n" + 
				"<p style='margin:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;'><br></p>\r\n" + 
				"<p style='margin:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;'><span style=\"color: rgb(51, 51, 51); font-family: Verdana, sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">"+ packageName +"("+ packageSize +" MB)</span></p>\r\n" + 
				"<p><a data-saferedirecturl=\"https://www.google.com/url?q="+ packageLink  +"&source=gmail&ust=1633054507170000&usg=AFQjCNGhoic4yCmpbAd92hP0FNWVTkw9MA\" href="+ packageLink  +" style=\"color: rgb(255, 255, 255); background-color: #2F3B67; border-radius: 3px; display: inline-block; font-family: verdana, geneva, sans-serif; font-size: 16px; font-weight: bold; letter-spacing: 0px; line-height: 16px; padding: 12px 18px; text-align: center; text-decoration: none;\" target=\"_blank\"><span class=\"il\">Click here to download</span></a></p>\r\n" + 
				"<p><br></p>\r\n" + 
				"<p><span style=\"color: rgb(51, 51, 51); font-family: Verdana, sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">Thank you for choosing Skyzer NITRO.</span><br><br></p>\r\n" + 
				"<p style='margin:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;background:white;'><span style='font-size:16px;font-family:\"Verdana\",sans-serif;color:#333333;'>Kind regards,</span><br><span style='font-size:16px;font-family:\"Verdana\",sans-serif;color:#333333;'>Team Skyzer</span></p>\r\n" + 
				"<p style='margin:0cm;font-size:15px;font-family:\"Verdana\",sans-serif;'><br></p>\r\n" + 
				"<p style=\"font-size:12px;font-family:Verdana,sans-serif;color:#97999C;\">[If you have any questions, please contact us at <a href=\"mailto:support@skyzer.co.nz?subject=Skyzer\">support@skyzer.co.nz</a> or call us on <a href=\"tel:+64 9 259 0322\" target=\"_self\">+64 9 259 0322</a>]</p>";
	}

	public boolean sendForgotPasswordCodeToUser(String recipient, String username, String code) {
		
		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(emailUsername, "Skyzer Technologies"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("SKYZER GUIDE | Verification code for reset password");

			BodyPart messageBodyPart = new MimeBodyPart();
			bodyText = this.sendForgotPasswordDetailsToUserEmailBody(username, code);
			messageBodyPart.setContent(bodyText, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			// Email Synchronization
			ExecutorService emailExecutor = Executors.newCachedThreadPool();
			emailExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Transport.send(message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String sendForgotPasswordDetailsToUserEmailBody(String username, String code) {
		return "<p style=\"color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\"><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;'>Dear "+ username +",</span></p>\r\n" + 
				"<p style=\"color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\"><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;'>We heard that you have forgotten your password, no problem!</span></p>\r\n" + 
				"<p style=\"color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\"><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;'>Please enter this verification code to reset your password.</span></p>\r\n" + 
				"<p style=\"color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\"><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;'>Code: </span><span style=\"color: rgb(51, 51, 51); font-family: Verdana, sans-serif; font-size: 20px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;\"><strong>"+ code +"</strong></span></p>\r\n" + 
				"<p style=\"color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\"><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;'>Thank you for choosing Skyzer.</span></p>\r\n" + 
				"<p style=\"color: rgb(34, 34, 34); font-family: Arial, Helvetica, sans-serif; font-size: small; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\"><span style='color: rgb(51, 51, 51); font-family: \"Verdana\", sans-serif; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; word-spacing: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;'>Kind regards,<br>Team Skyzer</span></p>\r\n" + 
				"<p style=\"font-size:12px;font-family:Verdana,sans-serif;color:#97999C;\">[If you have any questions, please contact us at <a href=\"mailto:support@skyzer.co.nz?subject=Skyzer\">support@skyzer.co.nz</a> or call us on <a href=\"tel:+64 9 259 0322\" target=\"_self\">+64 9 259 0322</a>]</p>";
	
	}
}