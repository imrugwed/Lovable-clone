package com.rocks.lovable_clone.service;

import com.rocks.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.rocks.lovable_clone.dto.subscription.UsageTodayResponse;

public interface UsageService {
     UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
