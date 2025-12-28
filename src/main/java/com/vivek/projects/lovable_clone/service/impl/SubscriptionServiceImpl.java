package com.vivek.projects.lovable_clone.service.impl;

import com.vivek.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.vivek.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.vivek.projects.lovable_clone.dto.subscription.PortalResponse;
import com.vivek.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import com.vivek.projects.lovable_clone.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSession(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
