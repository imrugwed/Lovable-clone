package com.rocks.lovable_clone.service.impl;

import com.rocks.lovable_clone.dto.subscription.PlanResponse;
import com.rocks.lovable_clone.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
