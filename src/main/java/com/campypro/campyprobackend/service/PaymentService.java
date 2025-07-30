package com.campypro.campyprobackend.service;

import com.campypro.campyprobackend.entity.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment savePayment(Payment payment);
    Optional<Payment> getPaymentById(Long id);
    List<Payment> getAllPayments();
    void deletePayment(Long id);
} 