package com.citykart.subscription.util;

import java.util.Map;

public class SubscriptionPlanUtil {
    private static final Map<String, Long> PLAN_DURATIONS = Map.of(
            "BASIC", 30L * 24 * 60 * 60 * 1000,   // 30 days
            "PRO",  365L * 24 * 60 * 60 * 1000    // 1 year
    );

    public static Long getExpiryMillis(String plan) {
        return PLAN_DURATIONS.getOrDefault(plan.toUpperCase(), 30L * 24 * 60 * 60 * 1000); // default 30 days
    }
}
