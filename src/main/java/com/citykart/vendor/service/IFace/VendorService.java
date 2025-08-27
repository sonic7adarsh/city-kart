package com.citykart.vendor.service.IFace;

import com.citykart.user.dto.UserDTO;
import com.citykart.user.entity.User;

import java.util.List;

public interface VendorService {
    void registerVendor(UserDTO dto);

    List<UserDTO> getVendorsByCityAndCategory(String city, String category);

    UserDTO getByPhone(String phone);

    User renewSubscription( String phone, String plan);

    List<UserDTO> getActiveVendorsByCity(String city);

}
