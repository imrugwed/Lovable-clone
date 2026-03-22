package com.rocks.lovable_clone.service;

import com.rocks.lovable_clone.dto.deploy.DeployResponse;

public interface DeploymentService {

    DeployResponse deploy(Long projectId);
}
