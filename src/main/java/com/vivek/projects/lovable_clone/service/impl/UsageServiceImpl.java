package com.vivek.projects.lovable_clone.service.impl;

import com.vivek.projects.lovable_clone.dto.subscription.PlanLimitResponse;
import com.vivek.projects.lovable_clone.dto.subscription.UsageTodayResponse;
import com.vivek.projects.lovable_clone.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsage(Long userId) {
        return null;
    }

    @Override
    public PlanLimitResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
