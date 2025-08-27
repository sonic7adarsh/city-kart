package com.citykart.user.service;

import com.citykart.user.dto.UserDTO;
import com.citykart.user.entity.User;
import com.citykart.user.enums.Role;

public interface UserService {

    void registerIfNotExists(UserDTO dto);
    User getByPhone(String phone);
}
