package com.project.eatTogether.application.dto.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentPrepareResponse {
    private String merchantUid;
    private Integer amount;
}