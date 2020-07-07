import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

class SendEmail{
    private String mailTo;
    private String password;
    private String from = "ppestatisticsnow@gmail.com";
    private String mailPass = "apsasapsas";



    public SendEmail(String mailTo, String password) {
        this.mailTo = mailTo;
        this.password = password;
    }

    public void send(){

        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,mailPass);
                    }
                });

        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));
            message.setFrom(new InternetAddress(from));
            message.setSubject("Slaptažodžio atnaujinimas");
            message.setText("Sveiki, ");
            message.setText("Jūsų slaptažodis pakeistas į: "+ password);
            //send message
            Transport.send(message);
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}