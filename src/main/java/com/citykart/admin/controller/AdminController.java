package com.citykart.admin.controller;

import com.citykart.booking.repository.BookingRepository;
import com.citykart.product.repository.ProductRepository;
import com.citykart.user.entity.User;
import com.citykart.user.enums.Role;
import com.citykart.user.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepo;
    private final ProductRepository productRepo;


    public AdminController(UserRepository userRepository, BookingRepository bookingRepo, ProductRepository productRepo) {
        this.userRepository = userRepository;
        this.bookingRepo = bookingRepo;
        this.productRepo = productRepo;
    }

    @GetMapping("/vendors")
    public List<User> getAllVendors() {
        return userRepository.findByRole(Role.VENDOR);
    }

    @PutMapping("/vendors/{id}/status")
    public String updateVendorStatus(@PathVariable Long id, @RequestParam boolean active) {
        User v = userRepository.findById(id).orElseThrow(() -> new ResolutionException("Vendor not found"));
        v.setActive(active);
        userRepository.save(v);
        return "Vendor status updated to: " + (active ? "Active" : "Inactive");
    }

    @GetMapping("/bookings")
    public Object getAllBookings() {
        return bookingRepo.findAll();
    }

    @GetMapping("/products")
    public Object getAllProducts() {
        return productRepo.findAll();
    }

}
