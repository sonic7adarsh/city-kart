package com.citykart.booking.service;

import com.citykart.auth.context.UserContextHolder;
import com.citykart.booking.dto.BookingDTO;
import com.citykart.booking.entity.Booking;
import com.citykart.booking.enums.BookingStatus;
import com.citykart.booking.repository.BookingRepository;
import com.citykart.booking.service.IFace.BookingService;
import com.citykart.exceptionHandler.UnauthorizedException;
import com.citykart.notification.service.NotificationService;
import com.citykart.product.entity.Product;
import com.citykart.product.repository.ProductRepository;
import com.citykart.user.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;
    private final ProductRepository productRepo;

    private final NotificationService notificationService;

    public BookingServiceImpl(BookingRepository repo, ProductRepository productRepo, NotificationService notificationService) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.notificationService = notificationService;
    }

    public Booking createBooking(final BookingDTO dto) {
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Booking booking = Booking.builder()
                .product(product)
                .user(product.getUser())
                .customerName(dto.getCustomerName())
                .customerPhone(dto.getCustomerPhone())
                .status(BookingStatus.BOOKED)
                .createdAt(LocalDateTime.now())
                .build();
        Booking saved = repo.save(booking);
        notificationService.notifyBooking(product.getUser());
        return saved;
    }

    public List<Booking> getMyBookings() {
        User user = UserContextHolder.get();
        if (!user.isActive()) {
            throw new UnauthorizedException("Subscription expired. Please renew.");
        }
        return repo.findByUser(user);
    }

    public Booking updateStatus(final Long id, final BookingStatus status) {
        User user = UserContextHolder.get();
        if (!user.isActive()) {
            throw new UnauthorizedException("Subscription expired. Please renew.");
        }
        Booking booking = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Only the vendor can update");
        }

        booking.setStatus(status);
        return repo.save(booking);
    }
}
