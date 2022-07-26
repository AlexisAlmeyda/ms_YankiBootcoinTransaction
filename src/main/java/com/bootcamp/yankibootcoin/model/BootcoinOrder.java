package com.bootcamp.yankibootcoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.bootcamp.yankibootcoin.events.PaymentMethod;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BootcoinOrder {
    private String transactionNumber;
    private String senderId;
    private String receiverId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
}
