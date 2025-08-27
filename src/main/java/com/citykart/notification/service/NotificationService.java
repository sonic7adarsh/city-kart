package com.citykart.notification.service;

import com.citykart.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
@Slf4j
public class NotificationService  {

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;
    @Autowired
    private JavaMailSender mailSender;
    public void sendBookingNotification(User user, String customerName) {
        // Future: Call SMS/Email API
        System.out.println("📢 [Booking Notification] to " + user.getPhone() +
                ": You received a new booking from " + customerName);
    }

    public void sendExpiryReminder(User user) {
        System.out.println("⚠️ [Subscription Reminder] to " + user.getPhone() +
                ": Your subscription is expiring soon. Please renew.");
    }


    public void sendBookingEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("New Booking Received");
        message.setText("You received a booking from: " + user.getName());
        mailSender.send(message);

        System.out.println("📧 Email sent to " + user.getEmail());
    }

    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("check OTP for verification");
        message.setText("OTP for verification: " + otp);
        mailSender.send(message);

        log.info("Email sent to email id {} otp {}", email, otp);
        System.out.println("📧 Email sent to " + email);
    }

    public void sendBeforeExpirySMS(User user) {
        Message message = Message.creator(
                new PhoneNumber(user.getPhone()), // To
                new PhoneNumber(twilioPhoneNumber), // From
                "⚠️ [Subscription Reminder] to " + user.getPhone() +
                        ": Your subscription is expiring soon. Please renew.").create();

        System.out.println("📱 SMS sent to " + user.getPhone());
    }

    public void sendBookingSMS(User user) {
        Message message = Message.creator(
                new PhoneNumber(user.getPhone()), // To
                new PhoneNumber(twilioPhoneNumber), // From
                "📢 [Booking Notification] to " + user.getPhone() +
                        ": You received a new booking from " + user.getName()).create();

        System.out.println("📱 SMS sent to " + user.getPhone());
    }

    public void sendOtpSMS(String number, String name, String otp) {
        Message message = Message.creator(
                new PhoneNumber("+91"+number), // To
                new PhoneNumber(twilioPhoneNumber), // From
                "📢 Otp is send to " + number +
                        ": otp for registration "+ otp  +" "+ name).create();

        log.info("Email sent to phone number {} otp {}", number, otp);
    }

    public void notifyBooking(User user) {
        sendBookingEmail(user); // Add email field in Vendor
        sendBookingSMS(user);
    }

    public void notifyOTP(User user, String otp, String name) {
        sendOtpEmail(user.getEmail(), otp); // Add email field in Vendor
        sendOtpSMS(user.getPhone(), name, otp);
    }
}
