package service;

import javax.servlet.ServletException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailService {

  private String to;
  private String subject;
  private String message;

  private final String SMTP_HOST = "smtp.gmail.com";
  private final String SMTP_PORT = "465";
  private final String FROM_ADDR = "{Email account address}";
  private final String FROM_NAME = "備品管理システム";
  private final String MAIL_USER = "{Email account name}";
  private final String MAIL_PASS = "{Email account password}";

  public void setTo(String to) {
    this.to = to;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void send() throws Exception {
    SimpleEmail email = new SimpleEmail();
    try {
      email.setHostName(SMTP_HOST);
      email.setStartTLSEnabled(true);
      email.setSslSmtpPort(SMTP_PORT);
      email.setAuthentication(MAIL_USER, MAIL_PASS);
      email.setFrom(FROM_ADDR, FROM_NAME, "ISO-2022-JP");
      email.addTo(to);
      email.setCharset("ISO-2022-JP");
      email.setSubject(subject);
      email.setMsg(message);
      email.send();
    } catch (EmailException e) {
      throw new ServletException(e);
    }

  }

}
