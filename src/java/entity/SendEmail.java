package entity;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class SendEmail {

    public void sendEmailHelper(StorageForm storageForm) {
        // Recipient's email ID needs to be mentioned.
        String to = storageForm.getOwnerEmail();

        // Sender's email ID needs to be mentioned
        String from = "OnlineForm@sidra.org";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("smtp-mv3.smrc.sidra.org", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message1 = new MimeMessage(session);
            MimeMessage message2 = new MimeMessage(session);

            // Set From: header field of the header.
            message1.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message1.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message1.setSubject("NAS Request - " + storageForm.getShareName() + " - " + storageForm.getRequestNo());

            // Now set the actual message
            message1.setText(storageForm.getShareName() + " NAS request " + storageForm.getRequestNo() + " has been created.\nStorage team are working on it.");

            // Send message
            Transport.send(message1);
            // Set From: header field of the header.
            from = "OnlineForm@sidra.org";
            message2.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            to = "sidrastoragebackup@sidra.org";
            message2.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message2.setSubject("NAS Request - " + storageForm.getShareName() + " - " + storageForm.getRequestNo());

            // Now set the actual message
            message2.setContent("<h2>NAS request has been submitted.</h2>\n\n"
                    + "<table border='2' style=\"text-align: left\">"
                    + "<tr>"
                    + "<th>Request No.</th>"
                    + "<td>" + storageForm.getRequestNo() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<th>Share Name</th>"
                    + "<td>" + storageForm.getShareName() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<th>Share Type</th>"
                    + "<td>" + storageForm.getShareType() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<th>Share Size (GBs)</th>"
                    + "<td>" + storageForm.getSize() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<th>Requester Email</th>"
                    + "<td>" + storageForm.getOwnerEmail() + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<th>Request Date</th>"
                    + "<td>" + storageForm.getLastUpdatedDate() + "</td>"
                    + "</tr>"
                    + "</table>", "text/html");

            // Send message
            Transport.send(message2);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
