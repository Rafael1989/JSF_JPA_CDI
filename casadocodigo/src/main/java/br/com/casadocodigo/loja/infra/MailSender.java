package br.com.casadocodigo.loja.infra;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ApplicationScoped
public class MailSender {
	
	@Resource(mappedName="java:jboss/mail/gmail")
	private Session session;

	public void send(String from, String to, String subject, String body) {
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setRecipients(RecipientType.TO, InternetAddress.parse(to));
			mimeMessage.setFrom(new InternetAddress(from));
			mimeMessage.setSubject(subject);
			mimeMessage.setContent(body, "text/html");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
