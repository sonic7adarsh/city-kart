package com.citykart.subscription.checker;

import com.citykart.notification.service.NotificationService;
import com.citykart.user.entity.User;
import com.citykart.user.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class SubscriptionChecker {

    private final UserRepository userRepository;

    private final NotificationService notificationService;

    public SubscriptionChecker(final UserRepository userRepository, final NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    // ✅ Run once every 24 hours
    @Scheduled(cron = "0 0 1 * * *") // Every day at 1 AM
    public void checkExpiredVendors() {
        long now = Instant.now().toEpochMilli();


        List<User> expired = userRepository.findByActiveTrueAndSubscriptionExpiryLessThan(now);

        expired.forEach(v -> {
            v.setActive(false);
            System.out.println("Deactivated vendor: " + v.getPhone());
        });

        userRepository.saveAll(expired);
    }

    public void notifyBeforeExpiry() {
        long now = Instant.now().toEpochMilli();
        List<User> expiringSoon = userRepository.findByActiveTrueAndSubscriptionExpiryBetween(
                now + (3 * 24 * 60 * 60 * 1000),  // 3 days from now
                now + (4 * 24 * 60 * 60 * 1000)   // Less than 4 days
        );

        expiringSoon.forEach(notificationService::sendBeforeExpirySMS);
    }
}
