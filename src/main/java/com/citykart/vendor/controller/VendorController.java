package com.citykart.vendor.controller;

import com.citykart.subscription.dto.RenewSubscriptionDTO;
import com.citykart.user.dto.UserDTO;
import com.citykart.user.entity.User;
import com.citykart.vendor.service.IFace.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService service;

    public VendorController(VendorService service) {
        this.service = service;
    }


    @GetMapping("/search")
    public List<UserDTO> getByCityAndCategory(@RequestParam String city,
                                                @RequestParam String category) {
        return service.getVendorsByCityAndCategory(city, category);
    }

    @GetMapping("/{phone}")
    public UserDTO getByPhone(@PathVariable String phone) {
        return service.getByPhone(phone);
    }


    @PutMapping("/renew")
    public User renewSubscription(@RequestBody RenewSubscriptionDTO dto) {
        return service.renewSubscription(dto.getPhone(), dto.getNewPlan());
    }

}
