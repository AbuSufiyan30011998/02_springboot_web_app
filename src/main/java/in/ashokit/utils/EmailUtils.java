package in.ashokit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender javaMailSender;


    public boolean sendEmail(File file) {
        boolean status = false;


        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);

            messageHelper.setTo("abu9140sufiyan@gmail.com");
            messageHelper.setSubject("Your Report is ready..");
            messageHelper.setText("<h2>please download your report..</h>", true);
            messageHelper.addAttachment(file.getName(), file);
            javaMailSender.send(message);

            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;


    }
}
