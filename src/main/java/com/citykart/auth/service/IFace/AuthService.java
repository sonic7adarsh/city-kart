package com.citykart.auth.service.IFace;

import com.citykart.auth.dto.OtpRequestDTO;
import com.citykart.auth.dto.OtpVerifyDTO;

public interface AuthService {


    String verifyAndGenerateToken(OtpVerifyDTO dto);
    String generateAndSendOtp(OtpRequestDTO dto);

}
