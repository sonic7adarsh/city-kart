package com.citykart.subscription.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RenewSubscriptionDTO {
    private String phone;
    private String newPlan; // BASIC / PRO
}
