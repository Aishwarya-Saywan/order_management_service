package com.test.order_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {
    @NotBlank(message = "customerName is mandatory")
    private String customerName;
    @NotNull(message = "order amount is mandatory")
    @Positive(message = "amount should not be in negative")
    private Double amount;

}
