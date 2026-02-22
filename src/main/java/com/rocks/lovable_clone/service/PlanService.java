package com.rocks.lovable_clone.service;

import com.rocks.lovable_clone.dto.subscription.PlanResponse;

import java.util.List;

public interface PlanService {
     List<PlanResponse> getAllActivePlans();
}
