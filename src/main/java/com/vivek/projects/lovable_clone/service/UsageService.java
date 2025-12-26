package com.vivek.projects.lovable_clone.service;

import com.vivek.projects.lovable_clone.dto.subscription.PlanLimitResponse;
import com.vivek.projects.lovable_clone.dto.subscription.UsageTodayResponse;


public interface UsageService {
    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
