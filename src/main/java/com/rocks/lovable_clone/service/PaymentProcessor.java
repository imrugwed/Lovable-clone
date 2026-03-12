package com.rocks.lovable_clone.service;

import com.rocks.lovable_clone.dto.subscription.CheckoutRequest;
import com.rocks.lovable_clone.dto.subscription.CheckoutResponse;
import com.rocks.lovable_clone.dto.subscription.PortalResponse;
import com.stripe.model.StripeObject;

import java.util.Map;

public interface PaymentProcessor {
    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal(Long userId);

    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
