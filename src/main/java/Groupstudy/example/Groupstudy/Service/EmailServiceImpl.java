package Groupstudy.example.Groupstudy.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String toEmail, String otpCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Your OTP Code");
            helper.setText("Your OTP is: " + otpCode + "\nIt will expire in 5 minutes.", false);
            mailSender.send(message);
            log.info("OTP email sent to {}", toEmail);
        } catch (Exception ex) {
            // log and continue - registration should not fail because of email issues
            log.error("Failed to send OTP to {} : {}", toEmail, ex.getMessage());
        }
    }
}



