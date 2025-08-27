package com.citykart.admin.service.IFace;

import com.citykart.admin.dto.AdminLoginDTO;
import com.citykart.admin.dto.AdminRegisterDTO;
import com.citykart.admin.entity.Admin;

public interface AdminAuthService {
    String login(AdminLoginDTO dto);

    Admin register(AdminRegisterDTO dto);

}
