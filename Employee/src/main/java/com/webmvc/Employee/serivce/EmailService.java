package com.webmvc.Employee.serivce;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Your OTP Verification Code");

            String htmlContent =
                    "<div style='font-family:Arial,sans-serif;background:#f4f6f8;padding:30px'>" +
                    "  <div style='max-width:500px;margin:auto;background:#ffffff;padding:25px;border-radius:8px'>" +
                    "    <h2 style='color:#1a73e8;text-align:center'>Employee Management System</h2>" +
                    "    <p>Hello,</p>" +
                    "    <p>Your OTP is:</p>" +
                    "    <div style='text-align:center;margin:30px 0'>" +
                    "      <span style='font-size:28px;letter-spacing:6px;font-weight:bold'>" +
                            otp +
                    "      </span>" +
                    "    </div>" +
                    "    <p>This OTP is valid for 5 minutes.</p>" +
                    "  </div>" +
                    "</div>";

            helper.setText(htmlContent, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }
}
